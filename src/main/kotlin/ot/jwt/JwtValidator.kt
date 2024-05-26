package ot.jwt

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class JwtResult(
    val header: String?,
    val payload: String?,
    val signature: String?,
) {
    companion object {
        fun fromArray(parts: List<String>): JwtResult {
            return JwtResult(parts.getOrNull(0), parts.getOrNull(1), parts.getOrNull(2))
        }
    }
}

data class JwtError(
    val partiallyValid: Boolean,
    val message: String,
)

fun validateJwt(jwt: String): Result<JwtResult, JwtError> {
    val parts = jwt.split(".")
    return when {
        jwt.isBlank() || parts.isEmpty() || parts.all { it.isBlank() } ->
            Err(JwtError(false, "JWT cannot be blank"))
        parts.size > 3 ->
            Err(JwtError(false, "JWT contains more than 3 parts"))
        else ->
            Ok(JwtResult.fromArray(parts))
    }
}
