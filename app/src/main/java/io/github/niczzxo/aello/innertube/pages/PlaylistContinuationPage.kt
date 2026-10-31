package io.github.niczzxo.aello.innertube.pages

import io.github.niczzxo.aello.innertube.models.SongItem

data class PlaylistContinuationPage(
    val songs: List<SongItem>,
    val continuation: String?,
)
