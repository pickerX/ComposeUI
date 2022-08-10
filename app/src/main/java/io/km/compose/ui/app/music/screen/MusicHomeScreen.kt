package io.km.compose.ui.app.music.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.km.compose.ui.LocalActivity
import io.km.compose.ui.SystemViewModel
import io.km.compose.ui.app.music.KMMusicViewModel
import io.km.compose.ui.entity.TabBean
import io.km.compose.ui.utils.collectAsLifecycleAwareState

@Composable
fun MusicHomeScreen(
    systemViewModel: SystemViewModel = viewModel(LocalActivity.current),
    musicViewModel: KMMusicViewModel = viewModel(LocalActivity.current),
) {
    val uiState by musicViewModel.uiState.collectAsLifecycleAwareState()

    Scaffold(
        topBar = {},
    ) { paddingValues ->
        val userTabs = uiState.userTabs
        Column(modifier = Modifier.padding(paddingValues)) {
            if (userTabs.isNotEmpty()) {
                var selectedTab by remember {
                    mutableStateOf(
                        uiState.selectedTab ?: userTabs.first()
                    )
                }

                MusicStyleTabs(
                    modifier = Modifier.fillMaxWidth(),
                    tabs = userTabs,
                    selectedTab = selectedTab,
                    onSelectedTabChanged = { tab ->
                        selectedTab = tab
                        musicViewModel.cacheSelectedTab(tab)
                    }
                )
            }

            Text(text = "Home")
        }
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun MusicStyleTabs(
    modifier: Modifier,
    tabs: List<TabBean>,
    selectedTab: TabBean,
    onSelectedTabChanged: (language: TabBean) -> Unit,
) {
    ScrollableTabRow(
        modifier = modifier.shadow(AppBarDefaults.TopAppBarElevation),
        selectedTabIndex = tabs.indexOf(selectedTab),
        edgePadding = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary,
    ) {
        for (tab in tabs) {
            Tab(
                selected = selectedTab == tab,
                onClick = { onSelectedTabChanged(tab) },
                text = { Text(text = tab.title) },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMusicHome() {
    MusicHomeScreen()
}