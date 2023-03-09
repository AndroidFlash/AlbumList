package com.example.albumlistcreator.feature.main.domain

import com.example.albumlistcreator.core.data.AlbumResponse
import com.example.albumlistcreator.core.data.Result
import com.example.albumlistcreator.core.service.ApiService
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

//@ExperimentalCoroutinesApi
//internal class MediaDataRepositoryTests {
//
//    @RelaxedMockK
//    private lateinit var apiService: ApiService
//
//    private lateinit var repository: MediaDataRepository
//
//   @BeforeEach
//   fun setup(){
//       repository = spyk(MediaDataRepository(apiService))
//   }
//
//    @Test
//    fun getMedia_happy_path() = runTest {
//        // given
//        val albumName = "U2"
//        val response = AlbumResponse(2, listOf(Result(collectionName = albumName)))
//
//        coEvery { apiService.getArtistAlbumList(any()) }.returns(response)
//        // when
//        val albums = repository.getAlbumListByArtistName("AA")

//        coVerify(exactly = 1) { apiService.getArtistAlbumList("AA")}
//
//        Truth.assertThat(albums.size).isEqualTo(1)
//        Truth.assertThat(albums.first().name).isEqualTo(albumName)
//    }
//}