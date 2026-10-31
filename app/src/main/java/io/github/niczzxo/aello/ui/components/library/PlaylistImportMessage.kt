package io.github.niczzxo.aello.ui.components.library

import android.content.Context
import io.github.niczzxo.aello.R
import io.github.niczzxo.aello.data.playlist.PlaylistImport

/** What to tell the viewer after an import, from the Playlists screen or a file opened in Aello. */
internal fun PlaylistImport.message(context: Context): String =
    when (this) {
        is PlaylistImport.Imported -> {
            val plural = if (isMusic) R.plurals.music_playlist_imported else R.plurals.playlist_imported
            context.resources.getQuantityString(plural, videoCount, name, videoCount)
        }

        PlaylistImport.NotAPlaylist -> {
            context.getString(R.string.playlist_import_not_a_playlist)
        }

        PlaylistImport.TooNew -> {
            context.getString(R.string.playlist_import_too_new)
        }

        PlaylistImport.Empty -> {
            context.getString(R.string.playlist_import_empty)
        }

        PlaylistImport.Unreadable -> {
            context.getString(R.string.playlist_import_unreadable)
        }
    }
