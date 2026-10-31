package io.github.niczzxo.aello.ui.screens.settings.content

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.niczzxo.aello.data.local.PlayerPreferences
import io.github.niczzxo.aello.ui.components.library.LibrarySection
import io.github.niczzxo.aello.ui.screens.settings.SettingsViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LibrarySettingsViewModel
    @Inject
    constructor(
        private val preferences: PlayerPreferences,
    ) : SettingsViewModel() {
        internal val hidden = preferences.hiddenLibrarySections.map(LibrarySection::fromStored).asState(emptySet())
        val separatePlaylists = preferences.separatePlaylistKinds.asState(false)
        val shortsContent = preferences.shortsContentEnabled.asState(true)
        val notes = preferences.notesEnabled.asState(true)

        internal fun setShown(
            section: LibrarySection,
            shown: Boolean,
        ) = write { preferences.setLibrarySectionHidden(section.name, hidden = !shown) }
    }
