package ot.base64

import androidx.compose.runtime.mutableStateOf
import ot.utils.b64Decode
import ot.utils.b64Encode

object Base64State {
    private val outputText = mutableStateOf<String?>(null)
    private val base64InputText = mutableStateOf("")

    fun decodedBase64(): String? {
        return this.outputText.value
    }

    fun decodeBase64() {
        this.outputText.value = b64Decode(base64InputText.value)
    }

    fun encodeBase64() {
        this.outputText.value = b64Encode(base64InputText.value)
    }

    fun base64InputText(): String {
        return this.base64InputText.value
    }

    fun setBase64InputText(str: String) {
        this.base64InputText.value = str
    }
}
