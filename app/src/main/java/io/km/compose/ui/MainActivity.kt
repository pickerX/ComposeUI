package io.km.compose.ui

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import io.km.compose.ui.config.Entries
import io.km.compose.ui.theme.AppDefaults
import io.km.compose.ui.theme.ComposeUITheme
import io.km.compose.ui.view.toolbar.ComposeToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // https://icons8.com    Icons by Icons8

        setContent { EntryScreen() }
    }
}

fun gifImageLoader(context: Context) = ImageLoader.Builder(context)
    .components {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }
    .build()

@Preview(showBackground = true)
@ExperimentalFoundationApi
@Composable
fun EntryScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    ComposeUITheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { ComposeToolbar("Compose UI", Icons.Rounded.Coffee) },
            floatingActionButton = { HomeFAB(scope, scaffoldState) },
        ) {
            EntryRecyclerView()
        }
    }
}

fun goTo(context: Context, page: Class<*>) {
    val intent = Intent(context, page).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(context, intent, null)
}

@Composable
@ExperimentalFoundationApi
fun EntryRecyclerView() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        Modifier.padding(AppDefaults.contentPadding)
    ) {
        items(Entries) { entry ->
            val context = LocalContext.current
            Column(
                Modifier
                    .animateItemPlacement()
                    .clickable { goTo(context, entry.clazz) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                entry.iconRes?.let {
                    AsyncImage(
                        model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .build(),
                        imageLoader = gifImageLoader(LocalContext.current),
                        modifier = Modifier.size(68.dp),
                        contentDescription = null,
                    )
                }
                entry.iconLottieRes?.let {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(it))
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.size(68.dp)
                    )
                }
                Text(
                    text = entry.name,
                    modifier = Modifier.padding(AppDefaults.contentPadding)
                )
            }
        }
    }
}

@Composable
fun HomeFAB(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    FloatingActionButton(
        onClick = {
            scope.launch {
                val result = scaffoldState.snackbarHostState
                    .showSnackbar(
                        message = "Snackbar",
                        actionLabel = "Action",
                        // Defaults to SnackbarDuration.Short
                        duration = SnackbarDuration.Short
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        /* Handle snackbar action performed */
                    }
                    SnackbarResult.Dismissed -> {
                        /* Handle snackbar dismissed */
                    }
                }
            }
        },
    ) { Image(Icons.Filled.Home, contentDescription = "Back Home") }
}