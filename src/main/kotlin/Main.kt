import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ot.model.DesktopState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Offline Toolbox",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
        val textState = remember { mutableStateOf("") }
        val decodedJwt = DesktopState.decodedJwt()
        MaterialTheme {
            Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {


                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    textStyle = TextStyle(color = Color.DarkGray),
                    maxLines = 15,
                    label = { Text("JWT") },
                    readOnly = false,
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth().requiredHeightIn(300.dp)
                )
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        DesktopState.decodeJwt(textState.value)
                    }) {
                    Text("Decode")
                }
                Row {
                    Text("Header", modifier = Modifier.width(150.dp))
                    Text(decodedJwt?.header ?: "")
                }
                Row {
                    Text("Payload", modifier = Modifier.width(150.dp))
                    Text(decodedJwt?.payload ?: "")
                }
                Row {
                    Text("Signature", modifier = Modifier.width(150.dp))
                    Text(decodedJwt?.signature ?: "")
                }
            }
        }
    }
}