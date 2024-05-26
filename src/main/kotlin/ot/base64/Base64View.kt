package ot.base64

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ot.jwt.MonoText

@Composable
fun Base64View() {
    val decodedBase64 = Base64State.decodedBase64()
    Column {
        TextField(
            value = Base64State.base64InputText(),
            onValueChange = { Base64State.setBase64InputText(it) },
            maxLines = 15,
            label = { Text("Base64") },
            readOnly = false,
            singleLine = false,
            modifier = Modifier.fillMaxWidth().requiredHeightIn(300.dp),
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally)) {
            Button(onClick = {
                Base64State.decodeBase64()
            }) {
                Text("Decode")
            }
            Button(onClick = {
                Base64State.encodeBase64()
            }) {
                Text("Encode")
            }
        }
        if (decodedBase64?.isNotEmpty() == true) {
            Row(modifier = Modifier.padding(15.dp)) {
                SelectionContainer {
                    MonoText(decodedBase64)
                }
            }
        }
    }
}
