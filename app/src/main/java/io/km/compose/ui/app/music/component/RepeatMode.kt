package io.km.compose.ui.app.music.component

/**
 *
 * @author pengfei.huang
 * create on 2022/9/24
 */
sealed interface RepeatMode {
    object RepeatAll : RepeatMode
    object RepeatOne : RepeatMode
    object RepeatNone : RepeatMode
    object RepeatShuffle : RepeatMode

}