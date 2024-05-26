import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ot.model.JwtState

sealed class Screen {
    object Jwt : Screen()
    object Base64 : Screen()
}

@Composable
fun MainView() {
    val currentScreen = remember { mutableStateOf<Screen>(Screen.Jwt) }
    Surface {
        Row {
            Box(modifier = Modifier.width(Dp(300.0F)), contentAlignment = Alignment.Center) {
                LeftNav(currentScreen)
            }
            when (currentScreen.value) {
                is Screen.Jwt -> JwtView()
                is Screen.Base64 -> Base64View()
            }
        }
    }
}

@Composable
fun LeftNav(currentView: MutableState<Screen>) {
    Column {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Left Nav") })
        }, content = {
            Column {
                Text("JWT", modifier = Modifier.clickable { currentView.value = Screen.Jwt })
                Text("Base64", modifier = Modifier.clickable { currentView.value = Screen.Base64 })
            }
        })
    }
}

@Composable
fun Base64View() {
    val textState = remember { mutableStateOf("") }
    Column {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            maxLines = 15,
            label = { Text("Base64") },
            readOnly = false,
            singleLine = false,
            modifier = Modifier.fillMaxWidth().requiredHeightIn(300.dp),
        )
    }
}

@Composable
fun JwtView() {
    val textState = remember { mutableStateOf("") }
    val decodedJwt = JwtState.decodedJwt()
    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            maxLines = 15,
            label = { Text("JWT") },
            readOnly = false,
            singleLine = false,
            modifier = Modifier.fillMaxWidth().requiredHeightIn(300.dp),
        )
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            JwtState.decodeJwt(textState.value)
        }) {
            Text("Decode")
        }
        if (decodedJwt != null) {
            Row {
                Text("Header", modifier = Modifier.width(150.dp))
                Text(decodedJwt.header)
            }
            Row {
                Text("Payload", modifier = Modifier.width(150.dp))
                Text(decodedJwt.payload)
            }
            Row {
                Text("Signature", modifier = Modifier.width(150.dp))
                Text(decodedJwt.signature)
            }
        }
    }
}
