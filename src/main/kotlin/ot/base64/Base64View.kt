package ot.base64

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
