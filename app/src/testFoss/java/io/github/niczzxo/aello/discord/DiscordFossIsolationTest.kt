package io.github.niczzxo.aello.discord

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DiscordFossIsolationTest {
    @Test
    fun `foss classpath excludes functional Discord implementation`() {
        val forbiddenClasses = listOf(
            "io.github.niczzxo.aello.discord.DiscordTokenStore",
            "io.github.niczzxo.aello.discord.DiscordAuthTokens",
            "io.github.niczzxo.aello.discord.DiscordPlaybackSource",
            "io.github.niczzxo.aello.discord.DiscordPresenceCoordinator",
            "io.github.niczzxo.aello.discord.KizzyDiscordPresenceTransport",
            "io.github.niczzxo.aello.discord.KizzyGatewayProtocol",
        )

        forbiddenClasses.forEach { className ->
            assertThat(runCatching { Class.forName(className) }.isFailure).isTrue()
        }
    }

    @Test
    fun `foss runtime reports Discord unavailable`() {
        assertThat(DiscordPresenceRuntime.settingsState.value.isAvailable).isFalse()
        assertThat(DiscordPresenceRuntime.settingsState.value.isEnabled).isFalse()
        assertThat(DiscordPresenceRuntime.settingsState.value.summary)
            .isEqualTo(DiscordSettingsSummary.UNAVAILABLE)
    }
}
