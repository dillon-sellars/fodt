package ot.jwt

import androidx.compose.runtime.mutableStateOf
import ot.api.jwt.JwtApi
import ot.api.jwt.JwtDecodedParts

object JwtState {
    private val decodedJwt = mutableStateOf<JwtDecodedParts?>(null)
    private val jwtInputText = mutableStateOf("")

    fun decodedJwt(): JwtDecodedParts? = this.decodedJwt.value

    fun decodeJwt() {
        val validatedJwt = JwtApi.validateJwt(jwtInputText.value)
        if (validatedJwt.isOk) {
            val result = JwtApi.decodeJwt(validatedJwt.value.parts)
            if (result.isOk) {
                decodedJwt.value = result.value
            }
        }
    }

    fun jwtInputText(): String = this.jwtInputText.value

    fun setJwtInputText(jwt: String) {
        this.jwtInputText.value = jwt
    }
}
