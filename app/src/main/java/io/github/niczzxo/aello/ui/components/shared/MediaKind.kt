package io.github.niczzxo.aello.ui.components.shared

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.niczzxo.aello.R

enum class MediaKind(
    @param:StringRes val labelRes: Int,
    val icon: ImageVector,
) {
    Videos(R.string.tab_videos, Icons.Outlined.VideoLibrary),
    Music(R.string.tab_music, Icons.Outlined.MusicNote),
}
