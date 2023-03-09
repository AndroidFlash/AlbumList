package com.example.albumlistcreator.feature.main.view

import androidx.lifecycle.viewModelScope
import com.example.albumlistcreator.core.di.IODispatcher
import com.example.albumlistcreator.core.view.BaseViewModel
import com.example.albumlistcreator.feature.main.domain.MediaDataRepository
import com.example.albumlistcreator.feature.main.statemachine.AlbumSearchEvent
import com.example.albumlistcreator.feature.main.statemachine.AlbumSearchState
import com.google.common.annotations.VisibleForTesting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumSearchViewModel @Inject constructor(
    private val mediaRepo: MediaDataRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<AlbumSearchEvent, AlbumSearchState>() {

    override fun setInitialState() = AlbumSearchState(
        artistName = "",
        albumList = emptyList(),
        isSearching = false,
        isError = false,
    )

    override fun handleEvents(event: AlbumSearchEvent) {
        when (event) {
            AlbumSearchEvent.ArtistFieldWasErased -> Unit
            is AlbumSearchEvent.SearchWasRequested -> {
                setState { copy(isSearching = true, isError = false, artistName = event.artistName, albumList = emptyList()) }
                getAlbumListByArtistName(event.artistName)
            }
            is AlbumSearchEvent.AlbumListWasReceived -> {
                setState { copy(isSearching = false, albumList = event.albumList) }
            }
            is AlbumSearchEvent.SearchError -> setState {
                copy(
                    isSearching = false,
                    isError = true
                )
            }
        }
    }

    @VisibleForTesting
    fun getAlbumListByArtistName(artistName: String) {
        viewModelScope.launch(context = dispatcher) {

            try {
                val albumList = mediaRepo.getAlbumListByArtistName(artistName)
                setEvent(AlbumSearchEvent.AlbumListWasReceived(albumList))
            } catch (ex: Exception) {
                setEvent(AlbumSearchEvent.SearchError(ex))
            }
        }
    }
}



