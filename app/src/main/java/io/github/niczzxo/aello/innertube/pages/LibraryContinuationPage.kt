package io.github.niczzxo.aello.innertube.pages

import io.github.niczzxo.aello.innertube.models.YTItem

data class LibraryContinuationPage(
    val items: List<YTItem>,
    val continuation: String?,
)
