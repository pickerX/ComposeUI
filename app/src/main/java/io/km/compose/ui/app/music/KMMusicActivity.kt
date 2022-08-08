package io.km.compose.ui.app.music

import android.os.Bundle
import androidx.core.view.WindowCompat
import io.km.compose.ui.app.BaseActivity

/**
 *
 * @author pengfei.huang
 * create on 2022/8/8
 */
class KMMusicActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

    }

}