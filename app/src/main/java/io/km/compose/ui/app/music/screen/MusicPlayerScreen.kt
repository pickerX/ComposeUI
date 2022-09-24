package io.km.compose.ui.app.music.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun MusicPlayerScreen() {
    val context = LocalContext.current
    val playerView = StyledPlayerView(context)

    Column() {
        playerView

    }
}