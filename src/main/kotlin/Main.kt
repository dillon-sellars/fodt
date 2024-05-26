import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ot.theme.BlueTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Offline Toolbox",
        state = rememberWindowState(width = 1024.dp, height = 768.dp)
    ) {
        BlueTheme { MainView() }
    }
}
