package com.example.albumlistcreator.core.service

import com.example.albumlistcreator.core.data.AlbumResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getArtistAlbumList(
        @Query("term") artistName: String,
        @Query("media") mediaType: String = "music",
        @Query("entity") entityType: String = "album",
        @Query("attribute") attribute: String = "artistTerm"
    ): AlbumResponse
}