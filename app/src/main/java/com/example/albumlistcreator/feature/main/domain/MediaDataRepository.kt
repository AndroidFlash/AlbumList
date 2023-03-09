package com.example.albumlistcreator.feature.main.domain

import com.example.albumlistcreator.core.service.ApiService
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaDataRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getAlbumListByArtistName(artistName: String) : List<AlbumModel>{
        val name = URLEncoder.encode(artistName, Charsets.UTF_8.name())
        return apiService.getArtistAlbumList(name).results.map {
            AlbumModel(
                name = it.collectionName ?: "",
                artWorkUrl = it.artworkUrl100 ?: "",
                releaseDate = it.releaseDate ?: "",
                genre = it.primaryGenreName ?: "",
                collectionPrice = it.collectionPrice,
                currency = it.currency ?: "",
                copyright = it.copyright ?: ""
            )
        }
    }
}
