package io.km.compose.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.fragment.app.FragmentActivity

/**
 *
 * @author pengfei.huang
 * create on 2022/8/10
 */
val LocalActivity = staticCompositionLocalOf<FragmentActivity> {
    error("Activity not found")
}