package com.example.albumlistcreator.feature.main.statemachine

import com.example.albumlistcreator.core.view.ViewState
import com.example.albumlistcreator.feature.main.domain.AlbumModel

data class AlbumSearchState(
    val artistName: String,
    val albumList: List<AlbumModel>,
    val isSearching: Boolean,
    val isError: Boolean,
) : ViewState