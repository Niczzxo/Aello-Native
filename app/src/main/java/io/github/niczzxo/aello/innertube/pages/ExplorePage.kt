package io.github.niczzxo.aello.innertube.pages

import io.github.niczzxo.aello.innertube.models.AlbumItem

data class ExplorePage(
    val newReleaseAlbums: List<AlbumItem>,
    val moodAndGenres: List<MoodAndGenres.Item>,
)
