package io.km.compose.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * app global settings
 *
 * @author pengfei.huang
 * create on 2022/8/10
 */
class SystemViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<SystemUiState> = MutableStateFlow(SystemUiState.EMPTY)
    val uiState: StateFlow<SystemUiState> = _uiState

    fun isNightMode(): Boolean {
        return requireNotNull(uiState.value.isNightMode)
    }

    fun toggleNightMode() {
        _uiState.value = _uiState.value.copy(isNightMode = !_uiState.value.isNightMode)
    }
}

data class SystemUiState(val isNightMode: Boolean = false) {

    companion object {
        val EMPTY = SystemUiState()
    }
}