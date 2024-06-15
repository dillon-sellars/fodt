package ot.api.jwt

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ot.utils.Base64

data class JwtResult(
    val parts: JwtDecodedParts,
    val error: JwtError?,
)

data class JwtDecodedParts(
    val header: String?,
    val payload: String?,
    val signature: String?,
) {
    companion object {
        fun fromArray(parts: List<String>): JwtDecodedParts = JwtDecodedParts(parts.getOrNull(0), parts.getOrNull(1), parts.getOrNull(2))
    }
}

data class JwtError(
    val partiallyValid: Boolean,
    val message: String,
)

object JwtApi {
    /*
    Split the jwt into parts and validate as much as possible, but do not base64 decode the parts
     */
    fun validateJwt(jwt: String): Result<JwtResult, JwtError> {
        val parts = jwt.split(".")
        return when {
            jwt.isBlank() || parts.isEmpty() || parts.all { it.isBlank() } ->
                Err(JwtError(false, "JWT cannot be blank"))

            parts.size > 3 ->
                Err(JwtError(false, "JWT contains more than 3 parts"))

            else ->
                return validate(parts)
        }
    }

    fun decodeJwt(jwt: JwtDecodedParts): Result<JwtDecodedParts, JwtError> {
        if ((jwt.header == null || jwt.header.isBlank()) || (jwt.payload == null || jwt.payload.isBlank())) {
            return Err(JwtError(false, "JWT cannot be blank"))
        }
        return Ok(JwtDecodedParts(Base64.decode(jwt.header), Base64.decode(jwt.payload), jwt.signature))
    }

    private fun validate(parts: List<String>): Result<JwtResult, JwtError> {
        val partialResult =
            if (!Base64.isBase64Encoded(parts[0])) {
                Err(JwtError(false, "JWT header is not base64 encoded"))
            } else if (parts.size == 1) {
                Ok(JwtResult(JwtDecodedParts.fromArray(parts), JwtError(true, "JWT only has header")))
            } else if (!Base64.isBase64Encoded(parts[1])) {
                Ok(JwtResult(JwtDecodedParts.fromArray(parts), JwtError(true, "JWT payload is not base64 encoded")))
            } else {
                null
            }
        if (partialResult != null) {
            return partialResult
        }
        return Ok(JwtResult(JwtDecodedParts.fromArray(parts), null))
    }
}
