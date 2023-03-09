package com.example.albumlistcreator.feature.main.domain

data class AlbumModel(
    val name: String,
    val artWorkUrl: String,
    val releaseDate: String,
    val genre: String,
    val collectionPrice: Double?,
    val currency: String,
    val copyright: String
    )
