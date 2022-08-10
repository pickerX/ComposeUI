package io.km.compose.ui.app.music

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import io.km.compose.ui.R
import kotlinx.coroutines.launch

/**
 *
 * @author pengfei.huang
 * create on 2022/8/8
 */
class KMMusicActivity : AppCompatActivity() {

    private val viewModel: KMMusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_main)

        findNavController(R.id.nav_host_fragment).apply {
            graph = navInflater.inflate(R.navigation.music_graph)
        }

        lifecycleScope.launch {
            viewModel.direction.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { dir ->

                }
        }
    }

}