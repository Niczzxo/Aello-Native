package io.github.niczzxo.aello.service

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.niczzxo.aello.player.LifecyclePlaybackPreferences

/** [VideoPlayerService] is not a Hilt entry point, so the singletons it reads come through here. */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface PlaybackServiceEntryPoint {
    fun lifecyclePlaybackPreferences(): LifecyclePlaybackPreferences
}
