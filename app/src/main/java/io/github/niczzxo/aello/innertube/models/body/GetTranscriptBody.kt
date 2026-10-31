package io.github.niczzxo.aello.innertube.models.body

import io.github.niczzxo.aello.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetTranscriptBody(
    val context: Context,
    val params: String,
)
