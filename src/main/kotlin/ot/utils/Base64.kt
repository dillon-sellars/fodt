package ot.utils

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets

object Base64 {

    fun decode(input: String): String {
        return String(Base64.decodeBase64(input), StandardCharsets.UTF_8)
    }

    fun encode(input: String): String {
        return String(Base64.encodeBase64(input.toByteArray(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)
    }

    fun isBase64Encoded(input: String): Boolean {
        // Base64 strings should match this regular expression
        val base64Regex = "^[A-Za-z0-9+/]+={0,2}$".toRegex()

        // Check if the string matches the Base64 pattern
        if (!input.matches(base64Regex)) {
            return false
        }

        return try {
            // Try to decode the string
            val decodedBytes = decode(input)
            // Check if re-encoding the decoded bytes matches the original string
            val encodedString = encode(decodedBytes)
            input == encodedString || input == encodedString.dropLastWhile { it == '=' }
        } catch (e: IllegalArgumentException) {
            // If decoding fails, it's not a valid Base64 encoded string
            false
        }
    }
}
