package com.example.albumlistcreator.feature.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.albumlistcreator.R
import com.example.albumlistcreator.feature.main.domain.AlbumModel
import com.example.albumlistcreator.feature.main.statemachine.AlbumSearchEvent
import com.example.albumlistcreator.feature.main.statemachine.AlbumSearchState

@Composable
fun AlbumSearchComponent(viewModel: AlbumSearchViewModel) {

    AlbumSearchStateful(
        state = viewModel.viewState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
    )


}

@Composable
fun AlbumSearchStateful(state: AlbumSearchState, onEventSent: (event: AlbumSearchEvent) -> Unit) {

    var artistName by remember { mutableStateOf("") }


    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isSearching -> Progress()
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchField(
                    field = artistName,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { artistName = it },
                    onDone = {
                        focusManager.clearFocus()
                        onEventSent(AlbumSearchEvent.SearchWasRequested(artistName))
                    })
            }
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                if (state.isError) {
                    Text(text = stringResource(R.string.Error_message))
                } else {
                    AlbumsList(albums = state.albumList)
                }
            }
        }
    }
}


@Composable
fun Progress() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SearchField(
    field: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onDone: KeyboardActionScope.() -> Unit
) {


    OutlinedTextField(
        value = field,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                textAlign = TextAlign.Right,
                text = stringResource(id = R.string.label_search)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onSearch = onDone)
    )
}

@Preview(showBackground = true)
@Composable
fun AlbumSearchStatefulPreview() {
    val albums = List(3) { buildAlbumPreview() }
    AlbumSearchStateful(
        state = AlbumSearchState(
            artistName = "U2",
            albumList = albums,
            isSearching = true,
            isError = false,
        ),
        onEventSent = {},
    )
}

fun buildAlbumPreview() = AlbumModel(
    name = "The Joshua Tree",
    artWorkUrl = "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/8f/e2/c3/8fe2c384-f6cb-9af7-371d-2b6a9b204e59/17UMGIM79292.rgb.jpg/100x100bb.jpg",
    releaseDate = "1987-03-03T08:00:00Z",
    genre = "alternative rock",
    collectionPrice = 8.99,
    currency = "USD",
    copyright = "Irish Records",
)