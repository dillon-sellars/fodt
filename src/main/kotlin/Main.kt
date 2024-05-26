import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ot.theme.blueTheme

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Offline Toolbox",
            state = rememberWindowState(width = 1024.dp, height = 768.dp),
        ) {
            blueTheme { MainView() }
        }
    }
