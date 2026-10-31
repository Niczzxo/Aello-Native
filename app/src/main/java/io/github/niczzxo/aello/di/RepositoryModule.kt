package io.github.niczzxo.aello.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.niczzxo.aello.data.local.PlayerPreferences
import io.github.niczzxo.aello.data.repository.YouTubeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideYouTubeRepository(playerPreferences: PlayerPreferences): YouTubeRepository = YouTubeRepository.getInstance(playerPreferences)

    @Provides
    @Singleton
    fun provideSubscriptionRepository(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.SubscriptionRepository =
        io.github.niczzxo.aello.data.local.SubscriptionRepository
            .getInstance(context)

    @Provides
    @Singleton
    fun provideLikedVideosRepository(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.LikedVideosRepository =
        io.github.niczzxo.aello.data.local.LikedVideosRepository
            .getInstance(context)

    @Provides
    @Singleton
    fun provideViewHistory(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.ViewHistory =
        io.github.niczzxo.aello.data.local.ViewHistory
            .getInstance(context)

    @Provides
    @Singleton
    fun provideHomeFeedCacheRepository(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.HomeFeedCacheRepository =
        io.github.niczzxo.aello.data.local
            .HomeFeedCacheRepository(context)

    @Provides
    @Singleton
    fun provideMusicPlaylistRepository(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.music.PlaylistRepository =
        io.github.niczzxo.aello.data.music
            .PlaylistRepository(context)

    // VideoDownloadManager is now @Singleton @Inject — Hilt provides it automatically
    @Provides
    @Singleton
    fun providePlayerPreferences(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.PlayerPreferences =
        io.github.niczzxo.aello.data.local
            .PlayerPreferences(context)

    /** Transitional: the music player manager still reaches the queue store through getInstance, so this hands injected consumers the same instance. */
    @Provides
    @Singleton
    fun provideQueuePersistence(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.QueuePersistence =
        io.github.niczzxo.aello.data.local.QueuePersistence
            .getInstance(context)

    /**
     * Transitional: [io.github.niczzxo.aello.data.local.BackupRepository] still builds its own
     * collaborators, so it is provided here rather than injected through its constructor. One
     * instance serves the whole app.
     */
    @Provides
    @Singleton
    fun provideBackupRepository(
        @ApplicationContext context: Context,
    ): io.github.niczzxo.aello.data.local.BackupRepository =
        io.github.niczzxo.aello.data.local
            .BackupRepository(context)
}
