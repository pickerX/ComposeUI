package io.km.compose.ui.app.music.data

import io.km.compose.ui.entity.TabBean

/**
 *
 * @author pengfei.huang
 * create on 2022/8/10
 */

data class MusicUiState(
    var userTabs: List<TabBean> = emptyList(),
    var allTabs: List<TabBean> = emptyList(),
    var selectedTab: TabBean? = null,
    var isLoading: Boolean = false,
) {

    companion object {

        val EMPTY = MusicUiState()
    }
}