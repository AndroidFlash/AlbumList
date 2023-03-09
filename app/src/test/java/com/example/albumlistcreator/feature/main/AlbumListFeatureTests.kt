package com.example.albumlistcreator.feature.main

//import com.example.albumlistcreator.feature.main.domain.MediaDataRepository
//import com.example.albumlistcreator.feature.main.statemachine.AlbumSearchEvent
//import com.example.albumlistcreator.feature.main.view.AlbumSearchViewModel
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.impl.annotations.RelaxedMockK
//import io.mockk.mockk
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import kotlinx.coroutines.test.runTest
//
//@ExperimentalCoroutinesApi
//internal class AlbumListFeatureTests {
//
//
//    @RelaxedMockK
//    private lateinit var repository: MediaDataRepository
//
//    private lateinit var viewModel: AlbumSearchViewModel
//
//    @BeforeEach
//    fun setup(){
//        repository = mockk()
//        viewModel = AlbumSearchViewModel(repository, StandardTestDispatcher())
//    }
//
//
//    @Test
//    fun getAlbums_unhappyPath() = runTest {
//        //val viewModel = AlbumSearchViewModel(repository, StandardTestDispatcher())
//
//        val artist = "john"
//        val exception = mockk<Exception>()
//        coEvery { repository.getAlbumListByArtistName(any()) } throws exception
//
//        viewModel.getAlbumListByArtistName(artist)
//
//        coVerify(exactly = 1) { viewModel.setEvent(AlbumSearchEvent.SearchError(exception))}
//    }
//}