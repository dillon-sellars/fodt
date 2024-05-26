package ot.model

import androidx.compose.runtime.mutableStateOf
import org.apache.commons.codec.binary.Base64
import ot.api.jwt.JwtApi
import java.nio.charset.StandardCharsets

data class JwtParts(val header: String, val payload: String, val signature: String)

fun b64Decode(input: String): String {
    return String(Base64.decodeBase64(input), StandardCharsets.UTF_8)
}

object JwtState {
    private val decodedJwt = mutableStateOf<JwtParts?>(null)
    private val jwtInputText = mutableStateOf("")

    fun decodedJwt(): JwtParts? {
        return this.decodedJwt.value
    }

    fun decodeJwt() {
        this.decodedJwt.value = JwtApi.decodeJwt(jwtInputText.value)
    }

    fun jwtInputText(): String {
        return this.jwtInputText.value
    }

    fun setJwtInputText(jwt: String) {
        this.jwtInputText.value = jwt
    }
}
