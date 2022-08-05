package io.km.compose.ui.view.toolbar

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun ToolbarPreview() {
    ComposeToolbar(title = "App title")
}

@Composable
fun ComposeToolbar(title: String, icon: ImageVector = Icons.Outlined.NavigateBefore) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            Image(
                icon,
                contentDescription = "Back press",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
            )
        },
    )

}