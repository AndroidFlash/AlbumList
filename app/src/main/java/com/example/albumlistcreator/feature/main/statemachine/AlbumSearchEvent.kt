package com.example.albumlistcreator.feature.main.statemachine

import com.example.albumlistcreator.core.view.ViewEvent
import com.example.albumlistcreator.feature.main.domain.AlbumModel

sealed class AlbumSearchEvent : ViewEvent {
    data class SearchWasRequested(val artistName: String) : AlbumSearchEvent()
    object ArtistFieldWasErased : AlbumSearchEvent()
    data class AlbumListWasReceived(val albumList: List<AlbumModel>) : AlbumSearchEvent()
    data class SearchError(val throwable: Throwable) : AlbumSearchEvent()

}
