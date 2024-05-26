package ot.jwt

import androidx.compose.runtime.mutableStateOf
import ot.api.jwt.JwtApi

data class JwtParts(val header: String, val payload: String, val signature: String)

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
