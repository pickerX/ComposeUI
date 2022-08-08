package io.km.compose.ui.app

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 *
 * @author pengfei.huang
 * create on 2022/8/8
 */
open class BaseActivity : AppCompatActivity() {


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequirePermissions() {
        val state = rememberPermissionState(
            android.Manifest.permission.CAMERA
        )
        when (state.status) {
            PermissionStatus.Granted -> {
                Text("Camera permission Granted")
            }
            is PermissionStatus.Denied -> {
                Column {
                    val textToShow = if (state.status.shouldShowRationale) {
                        "The camera is important for this app. Please grant the permission."
                    } else {
                        "Camera permission required for this feature to be available. " +
                                "Please grant the permission"
                    }
                    Text(textToShow)
                    Button(onClick = { state.launchPermissionRequest() }) {
                        Text("Request permission")
                    }
                }
            }
        }
    }
}