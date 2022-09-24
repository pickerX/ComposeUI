package io.km.compose.ui.app.music

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.km.compose.ui.LocalActivity
import io.km.compose.ui.R
import io.km.compose.ui.SystemViewModel
import io.km.compose.ui.app.BaseFragment
import io.km.compose.ui.app.Router
import io.km.compose.ui.theme.MusicUITheme

/**
 *
 * @author pengfei.huang
 * create on 2022/8/8
 */
class KMMusicFragment : BaseFragment(R.layout.fragment_compose) {

    private val systemViewModel: SystemViewModel by activityViewModels()
    private val viewModel: KMMusicViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            MusicUITheme(systemViewModel.isNightMode()) {
                CompositionLocalProvider(LocalActivity provides requireActivity()) {
                    KMMusicScreen()
                }
            }
        }

        viewModel.fetchHomeTabs()
    }

    @Composable
    private fun KMMusicScreen() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            bottomBar = {
                MusicBottomNavigation(
                    modifier = Modifier.fillMaxWidth(),
                    currentDestination = currentDestination,
                    onNavigationSelected = { screen ->
                        navController.navigate(screen.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            },
        ) { paddingValues ->

            MusicNavigation(Modifier.padding(paddingValues), navController)

            FloatingMusicPlayer()
        }
    }

    @Composable
    private fun FloatingMusicPlayer() {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            Row(
                Modifier.padding(AppBarDefaults.ContentPadding),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "musicName")

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.NavigateNext, contentDescription = "")
                }

            }
        }
    }

    @Composable
    private fun MusicBottomNavigation(
        modifier: Modifier,
        currentDestination: NavDestination?,
        onNavigationSelected: (Router) -> Unit
    ) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = contentColorFor(MaterialTheme.colors.surface),
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.Home, null) },
                label = { Text(stringResource(id = R.string.home)) },
                selected = currentDestination?.hierarchy?.any { it.route == Router.Music.Home.path } == true,
                onClick = { onNavigationSelected(Router.Music.Home) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.Search, null) },
                label = { Text(stringResource(id = R.string.search)) },
                selected = currentDestination?.hierarchy?.any { it.route == Router.Music.Search.path } == true,
                onClick = { onNavigationSelected(Router.Music.Search) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            )

            BottomNavigationItem(
                icon = { Icon(Icons.Outlined.Person, null) },
                label = { Text(stringResource(id = R.string.me)) },
                selected = currentDestination?.hierarchy?.any { it.route == Router.Music.Pin.path } == true,
                onClick = { onNavigationSelected(Router.Music.Pin) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            )
        }
    }
}