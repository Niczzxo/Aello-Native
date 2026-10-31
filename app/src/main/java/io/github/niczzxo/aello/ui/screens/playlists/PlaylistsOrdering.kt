package io.github.niczzxo.aello.ui.screens.playlists

import io.github.niczzxo.aello.data.model.PlaylistInfo
import io.github.niczzxo.aello.data.playlist.PlaylistListOrder
import io.github.niczzxo.aello.data.playlist.sortedFor
import io.github.niczzxo.aello.ui.components.library.PlaylistOwnershipFilter

/**
 * Owned playlists lead saved ones in every order except [PlaylistListOrder.CUSTOM], where the
 * viewer's own arrangement decides.
 */
internal fun orderedPlaylists(
    owned: List<PlaylistInfo>,
    saved: List<PlaylistInfo>,
    filter: PlaylistOwnershipFilter,
    order: PlaylistListOrder,
): List<PlaylistInfo> =
    if (order == PlaylistListOrder.CUSTOM) {
        filter.select(owned, saved).sortedFor(order)
    } else {
        filter.select(owned.sortedFor(order), saved.sortedFor(order))
    }
