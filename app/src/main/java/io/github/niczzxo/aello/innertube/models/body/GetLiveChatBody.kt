package io.github.niczzxo.aello.innertube.models.body

import io.github.niczzxo.aello.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetLiveChatBody(
    val context: Context,
    val continuation: String,
    val currentPlayerState: CurrentPlayerState? = null,
) {
    @Serializable
    data class CurrentPlayerState(
        val playerOffsetMs: String,
    )
}
