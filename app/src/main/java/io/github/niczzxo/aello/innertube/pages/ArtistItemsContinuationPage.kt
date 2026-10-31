package io.github.niczzxo.aello.innertube.pages

import io.github.niczzxo.aello.innertube.models.YTItem

data class ArtistItemsContinuationPage(
    val items: List<YTItem>,
    val continuation: String?,
)
