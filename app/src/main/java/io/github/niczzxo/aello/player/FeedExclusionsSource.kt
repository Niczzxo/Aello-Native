package io.github.niczzxo.aello.player

import io.github.niczzxo.aello.data.recommendation.FeedExclusions

/** What the viewer hid, so autoplay picked outside the player screen skips it too. */
fun interface FeedExclusionsSource {
    suspend fun current(): FeedExclusions
}
