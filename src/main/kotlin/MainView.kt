import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ot.model.JwtState

@Composable
fun MainView() {
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
