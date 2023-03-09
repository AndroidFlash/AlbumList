package com.example.albumlistcreator.feature.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.example.albumlistcreator.R
import com.example.albumlistcreator.feature.main.domain.AlbumModel

@Composable
fun AlbumInfo(
    albumModel: AlbumModel,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier,
        title = { Text(stringResource(R.string.Title_Info)) },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(text = stringResource(R.string.Label_Ok)) }

        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        text = {
            Column() {
                Row {
                    Text(text = stringResource(R.string.Label_Genre), fontWeight = FontWeight.Bold)
                    Text(text = albumModel.genre)
                }
                Row {
                    Text(text = stringResource(R.string.Label_Collection_Price), fontWeight = FontWeight.Bold)
                    Text(text = albumModel.collectionPrice?.toString() ?: stringResource(R.string.Not_Available_Text) )
                }
                Row {
                    Text(text = stringResource(R.string.Label_Currency), fontWeight = FontWeight.Bold)
                    Text(text = albumModel.currency)
                }
                Row {
                    Text(text = stringResource(R.string.Label_Copyright), fontWeight = FontWeight.Bold)
                    Text(text = albumModel.copyright)
                }
            }
        }

    )
}

@Preview(showBackground = true)
@Composable
fun AlbumInfoPreview() {
    val album = buildAlbumPreview()
    AlbumInfo(albumModel = album) { }
}