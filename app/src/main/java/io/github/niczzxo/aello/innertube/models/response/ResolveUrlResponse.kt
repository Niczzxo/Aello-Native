package io.github.niczzxo.aello.innertube.models.response

import io.github.niczzxo.aello.innertube.models.NavigationEndpoint
import kotlinx.serialization.Serializable

@Serializable
data class ResolveUrlResponse(
    val endpoint: NavigationEndpoint? = null,
)
