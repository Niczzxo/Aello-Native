package io.github.niczzxo.aello.ui.screens.settings.taste

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.niczzxo.aello.data.recommendation.music.FavouriteArtistCatalog
import io.github.niczzxo.aello.data.recommendation.music.FavouriteArtistPicker
import io.github.niczzxo.aello.data.recommendation.music.FavouriteArtistsStore
import javax.inject.Inject

@HiltViewModel
internal class FavouriteArtistsViewModel
    @Inject
    constructor(
        store: FavouriteArtistsStore,
        catalog: FavouriteArtistCatalog,
    ) : ViewModel() {
        val picker = FavouriteArtistPicker(viewModelScope, store, catalog)
    }
