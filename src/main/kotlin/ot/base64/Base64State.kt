package ot.base64

import androidx.compose.runtime.mutableStateOf
import ot.jwt.b64Decode

object Base64State {
    private val decodedBase64 = mutableStateOf<String?>(null)
    private val base64InputText = mutableStateOf("")

    fun decodedBase64(): String? {
        return this.decodedBase64.value
    }

    fun decodeBase64() {
        this.decodedBase64.value = b64Decode(base64InputText.value)
    }

    fun base64InputText(): String {
        return this.base64InputText.value
    }

    fun setBase64InputText(str: String) {
        this.base64InputText.value = str
    }
}
