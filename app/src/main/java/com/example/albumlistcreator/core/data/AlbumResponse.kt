package com.example.albumlistcreator.core.data

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class AlbumResponse (
    val resultCount: Int,
    val results: List<Result>
)

@JsonClass(generateAdapter = true)
data class Result (
    val amgArtistId: Int? = null,
    val artistId: Int? = null,
    val artistName: String? = null,
    val artistViewUrl: String? = null,
    val artworkUrl100: String? = null,
    val artworkUrl60: String? = null,
    val collectionCensoredName: String? = null,
    val collectionExplicitness: String? = null,
    val collectionId: Int? = null,
    val collectionName: String? = null,
    val collectionPrice: Double? = null,
    val collectionType: String? = null,
    val collectionViewUrl: String? = null,
    val contentAdvisoryRating: String? = null,
    val copyright: String? = null,
    val country: String? = null,
    val currency: String? = null,
    val primaryGenreName: String? = null,
    val releaseDate: String? = null,
    val trackCount: Int? = null,
    val wrapperType: String? = null
)
