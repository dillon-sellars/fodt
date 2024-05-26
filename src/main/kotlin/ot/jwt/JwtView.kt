package ot.jwt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ot.model.JwtState

@Composable
fun JwtView() {
    val decodedJwt = JwtState.decodedJwt()
    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
        TextField(
            value = JwtState.jwtInputText(),
            onValueChange = { JwtState.setJwtInputText(it) },
            maxLines = 15,
            label = { Text("JWT") },
            readOnly = false,
            singleLine = false,
            modifier = Modifier.fillMaxWidth().requiredHeightIn(300.dp),
            visualTransformation = ColorsTransformation(),
        )
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            JwtState.decodeJwt()
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
