import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ot.theme.blueTheme

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Free Offline Dev Tools",
            state = rememberWindowState(width = 1024.dp, height = 768.dp),
            icon = BitmapPainter(useResource("icon.png", ::loadImageBitmap)),
        ) {
            blueTheme { MainView() }
        }
    }
