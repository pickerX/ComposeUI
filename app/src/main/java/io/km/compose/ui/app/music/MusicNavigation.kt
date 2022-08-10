package io.km.compose.ui.app.music

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.km.compose.ui.app.Router
import io.km.compose.ui.app.music.screen.MusicHomeScreen

@Composable
fun MusicNavigation(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Router.Music.Home.path,
    ) {
        composable(Router.Music.Home.path) {
            MusicHomeScreen()
        }
        composable(Router.Music.Pin.path) {
            //SearchScreen()
        }
        composable(Router.Music.Search.path) {
            //PinScreen()
        }
    }
}