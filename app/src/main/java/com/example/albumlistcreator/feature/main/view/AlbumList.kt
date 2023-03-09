package com.example.albumlistcreator.feature.main.view

import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.rememberGlidePreloadingData
import com.example.albumlistcreator.R
import com.example.albumlistcreator.feature.main.domain.AlbumModel
import java.util.*

private const val THUMBNAIL_DIMENSION = 50
private val THUMBNAIL_SIZE = Size(THUMBNAIL_DIMENSION.toFloat(), THUMBNAIL_DIMENSION.toFloat())

@Composable
fun AlbumsList(
    albums: List<AlbumModel>
) {
    val showItemDialog = remember { mutableStateOf(false) }
    var selectedAlbum: AlbumModel? by remember { mutableStateOf(null) }
    val requestBuilderTransform =
        { item: AlbumModel, requestBuilder: RequestBuilder<Drawable> ->
            requestBuilder.load(item.artWorkUrl)
        }

    val preloadingData =
        rememberGlidePreloadingData(
            albums,
            THUMBNAIL_SIZE,
            requestBuilderTransform = requestBuilderTransform,
        )
    val context = LocalContext.current
    val state = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = state) {
            items(preloadingData.size) { index ->
                val (albumItem, preloadRequestBuilder) = preloadingData[index]
                AlbumListItem(
                    album = albumItem,
                    preloadRequestBuilder = preloadRequestBuilder,
                    onItemClick = {
                        showItemDialog.value = true
                        selectedAlbum = albumItem
                    }
                )
            }
        }
    }
    if (showItemDialog.value) {
        selectedAlbum?.let { AlbumInfo(it) { showItemDialog.value = false } }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumListItem(
    album: AlbumModel,
    preloadRequestBuilder: RequestBuilder<Drawable>,
    onItemClick: (AlbumModel) -> Unit
) {
    val paddingXXSmall = 2.dp
    val paddingMedium = 16.dp
    val avatarSize = 72.dp
    val dividerStartIndent = 88.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(album)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            GlideImage(
                model = album.artWorkUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(avatarSize)
                    .padding(end = paddingMedium)
            ) {
                it.thumbnail(preloadRequestBuilder)
            }
            Column {
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(paddingXXSmall))

                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                val dateTime = sdf.parse(album.releaseDate)
                val outputFormatter = SimpleDateFormat("MM/dd/yyyy")
                val albumDate: String = outputFormatter.format(dateTime)
                Text(
                    text = stringResource(R.string.release_date) + albumDate,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Divider(
            startIndent = dividerStartIndent,
            modifier = Modifier.padding(end = paddingMedium)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AlbumListPreview() {
    val albums = List(3) { buildAlbumPreview() }
    AlbumsList(albums = albums)
}

