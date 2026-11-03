package io.github.niczzxo.aello.data.update

import android.os.Build
import io.github.niczzxo.aello.BuildConfig
import io.github.niczzxo.aello.data.local.LocalDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val STABLE_RELEASE_API = "https://api.github.com/repos/Niczzxo/Aello-Native/releases/latest"
private const val NIGHTLY_RELEASE_API = "https://api.github.com/repos/Niczzxo/Aello-Native/releases/tags/nightly"
private const val NIGHTLY_CHANNEL = "nightly"
private const val GITHUB_JSON = "application/vnd.github+json"

/** The check runs on slow mobile data too, so it gets its own long timeouts and retries. */
private const val CHECK_ATTEMPTS = 3
private const val CHECK_CONNECT_TIMEOUT_S = 30L
private const val CHECK_READ_TIMEOUT_S = 120L
private const val CHECK_CALL_TIMEOUT_S = 300L
private const val CHECK_RETRY_DELAY_MS = 2_000L

/** Where an automatic check may surface a release; each announces a version once. */
enum class UpdateAnnouncement { LAUNCH_PAGE, NOTIFICATION }

/** How the automatic checks space themselves out and which releases they may show. */
internal object UpdateSchedule {
    val COOLDOWN_MS: Long = TimeUnit.HOURS.toMillis(12)

    fun isDue(
        lastCheckMs: Long,
        nowMs: Long,
    ): Boolean = nowMs < lastCheckMs || nowMs - lastCheckMs >= COOLDOWN_MS

    fun mayAnnounce(
        version: String,
        skippedVersion: String?,
        announcedVersion: String?,
    ): Boolean = version != skippedVersion && version != announcedVersion
}

/** An HTTP answer from the update check, kept apart from network failures so only the latter retry. */
private class HttpStatusException(
    val code: Int,
) : IOException("Update check failed: HTTP $code")

/**
 * The one place Aello asks GitHub about releases. The launch check, the background worker and the
 * Settings button all come through here, so a cold start never fetches twice and a skipped version
 * stays skipped everywhere.
 */
@Singleton
class UpdateRepository
    @Inject
    constructor(
        private val client: OkHttpClient,
        private val dataManager: LocalDataManager,
    ) {
        private val mutex = Mutex()
        private val isNightly = BuildConfig.UPDATE_CHANNEL == NIGHTLY_CHANNEL
        private val _latest = MutableStateFlow<AppRelease?>(null)

        /** The newest release found this process, or null when none is known or this build is current. */
        val latest: StateFlow<AppRelease?> = _latest.asStateFlow()

        /** Asks GitHub now. Throws when the request fails, so a check the user started can say so. */
        suspend fun fetch(): AppRelease? = mutex.withLock { fetchLocked() }

        /**
         * For the automatic checks: fetches only once the cooldown has passed, and returns a release
         * only if [announcement] has not shown this version before and the user has not skipped it.
         */
        suspend fun releaseToAnnounce(
            announcement: UpdateAnnouncement,
            nowMs: Long = System.currentTimeMillis(),
        ): AppRelease? =
            mutex.withLock {
                if (!UpdateSchedule.isDue(dataManager.lastUpdateCheck.first(), nowMs)) return@withLock null
                val release = runCatching { fetchLocked() }.getOrNull() ?: return@withLock null
                val announced =
                    when (announcement) {
                        UpdateAnnouncement.LAUNCH_PAGE -> dataManager.promptedUpdateVersion.first()
                        UpdateAnnouncement.NOTIFICATION -> dataManager.notifiedUpdateVersion.first()
                    }
                if (!UpdateSchedule.mayAnnounce(release.version, dataManager.skippedUpdateVersion.first(), announced)) {
                    return@withLock null
                }
                when (announcement) {
                    UpdateAnnouncement.LAUNCH_PAGE -> dataManager.setPromptedUpdateVersion(release.version)
                    UpdateAnnouncement.NOTIFICATION -> dataManager.setNotifiedUpdateVersion(release.version)
                }
                release
            }

        suspend fun skip(version: String) = dataManager.setSkippedUpdateVersion(version)

        private suspend fun fetchLocked(): AppRelease? {
            val body =
                withContext(Dispatchers.IO) {
                    val checkClient =
                        client
                            .newBuilder()
                            .connectTimeout(CHECK_CONNECT_TIMEOUT_S, TimeUnit.SECONDS)
                            .readTimeout(CHECK_READ_TIMEOUT_S, TimeUnit.SECONDS)
                            .callTimeout(CHECK_CALL_TIMEOUT_S, TimeUnit.SECONDS)
                            .build()
                    var failure: IOException? = null
                    repeat(CHECK_ATTEMPTS) { attempt ->
                        try {
                            val request =
                                Request
                                    .Builder()
                                    .url(if (isNightly) NIGHTLY_RELEASE_API else STABLE_RELEASE_API)
                                    .header("Accept", GITHUB_JSON)
                                    .cacheControl(CacheControl.FORCE_NETWORK)
                                    .build()
                            checkClient.newCall(request).execute().use { response ->
                                if (!response.isSuccessful) throw HttpStatusException(response.code)
                                return@withContext response.body.string()
                            }
                        } catch (e: IOException) {
                            failure = e
                            // A 4xx answer (rate limit, missing release) will not change on retry.
                            if (e is HttpStatusException && e.code in 400..499 && e.code != 429) throw e
                            if (attempt + 1 < CHECK_ATTEMPTS) delay(CHECK_RETRY_DELAY_MS * (attempt + 1))
                        }
                    }
                    throw failure ?: IOException("Update check failed")
                }
            val parsed = parseGitHubRelease(body)
            val release =
                if (isNightly) {
                    parsed.toNightlyReleaseIfNewer(BuildConfig.NIGHTLY_RUN)
                } else {
                    parsed.toAppReleaseIfNewer(BuildConfig.VERSION_NAME, Build.SUPPORTED_ABIS.toList())
                }
            dataManager.setLastUpdateCheck(System.currentTimeMillis())
            _latest.value = release
            return release
        }
    }
