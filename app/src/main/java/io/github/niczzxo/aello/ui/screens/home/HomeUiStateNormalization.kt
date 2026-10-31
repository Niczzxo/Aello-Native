package io.github.niczzxo.aello.ui.screens.home

import io.github.niczzxo.aello.data.local.VideoHistoryEntry
import io.github.niczzxo.aello.data.model.Video
import io.github.niczzxo.aello.data.model.distinctByNonBlankKeyOrSelf

internal fun HomeUiState.withUniqueLazyContent(): HomeUiState {
    val uniqueVideos = videos.distinctByNonBlankKeyOrSelf(Video::id)
    val uniqueShorts = shorts.distinctByNonBlankKeyOrSelf(Video::id)
    val uniqueHistory = continueWatchingVideos.distinctByNonBlankKeyOrSelf(VideoHistoryEntry::videoId)
    return if (
        uniqueVideos === videos &&
        uniqueShorts === shorts &&
        uniqueHistory === continueWatchingVideos
    ) {
        this
    } else {
        copy(
            videos = uniqueVideos,
            shorts = uniqueShorts,
            continueWatchingVideos = uniqueHistory,
        )
    }
}
