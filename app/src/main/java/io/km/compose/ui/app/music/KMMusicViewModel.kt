package io.km.compose.ui.app.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.km.compose.ui.app.music.data.MusicUiState
import io.km.compose.ui.entity.TabBean
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 * @author pengfei.huang
 * create on 2022/8/9
 */
class KMMusicViewModel : ViewModel() {

    private val mutableDirection: MutableSharedFlow<Int> = MutableSharedFlow()
    val direction: SharedFlow<Int> = mutableDirection

    private val _uiState = MutableStateFlow(MusicUiState.EMPTY)
    val uiState: StateFlow<MusicUiState> = _uiState

    init {
        _uiState.value.allTabs = mutableListOf(
            TabBean(0, "popular"),
            TabBean(1, "R&B"),
        )
    }

    fun fetchHomeTabs() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val tabs = mutableListOf(
                    TabBean(0, "popular"),
                    TabBean(1, "R&B"),
                )
                tabs
            }.onSuccess { tabs ->
                _uiState.value = _uiState.value.copy(
                    userTabs = tabs,
                    isLoading = false
                )
            }.onFailure {

            }
        }
    }

    fun cacheSelectedTab(tab: TabBean) {
        _uiState.value.selectedTab = tab
    }

}