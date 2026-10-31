package io.github.niczzxo.aello.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.produceState
import androidx.compose.runtime.staticCompositionLocalOf
import io.github.niczzxo.aello.data.localmedia.LocalMediaIds
import io.github.niczzxo.aello.data.model.DeArrowContent
import io.github.niczzxo.aello.data.model.DeArrowResult
import io.github.niczzxo.aello.data.model.Video
import io.github.niczzxo.aello.data.repository.DeArrowBrandingPolicy
import io.github.niczzxo.aello.data.repository.DeArrowRepository

/** The activity's DeArrow lookup, installed by `ProvideVideoCardState`; null in previews and tests. */
val LocalDeArrow = staticCompositionLocalOf<DeArrowRepository?> { null }

/**
 * What DeArrow replaces on [video], or null to keep YouTube's: switched off, a local file, no lookup
 * installed, or nothing to replace. An answer already fetched shows on the first frame.
 */
@Composable
internal fun rememberDeArrowResult(
    video: Video,
    enabled: Boolean,
    useRandomFrame: Boolean,
): DeArrowResult? {
    val repository = LocalDeArrow.current
    val active = enabled && repository != null && !LocalMediaIds.isLocal(video.id)
    val resolve = { content: DeArrowContent? ->
        content?.let {
            DeArrowBrandingPolicy.resolve(
                content = it,
                videoId = video.id,
                fallbackDurationSeconds = video.duration,
                useRandomFrame = useRandomFrame,
                isLiveOrUpcoming = video.isLive || video.isUpcoming,
            )
        }
    }
    return key(video.id, active, useRandomFrame) {
        produceState(initialValue = if (active) resolve(repository?.cached(video.id)) else null, video.id, active, useRandomFrame) {
            if (active) value = resolve(repository?.branding(video.id))
        }.value
    }
}
