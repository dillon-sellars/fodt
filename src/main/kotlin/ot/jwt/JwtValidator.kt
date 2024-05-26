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
            return when (parts.size) {
                1 -> JwtResult(parts[0], null, null)
                2 -> JwtResult(parts[0], parts[1], null)
                else -> JwtResult(parts[0], parts[1], parts[2])
            }
        }
    }
}

data class JwtError(
    val partiallyValid: Boolean,
    val message: String,
)

fun validateJwt(jwt: String): Result<JwtResult, JwtError> {
    val parts = jwt.split(".")
    if (jwt.isBlank() || parts.isEmpty()) {
        return Err(JwtError(false, "JWT cannot be blank"))
    }
    if (parts.size == 1 && parts[0].isBlank()) {
        return Err(JwtError(false, "JWT cannot be blank"))
    }
    if (parts.size == 2 && parts[0].isBlank() && parts[1].isBlank()) {
        return Err(JwtError(false, "JWT cannot be blank"))
    }
    if (parts.size > 3) {
        return Err(JwtError(false, "JWT contains more than 3 parts"))
    }

    return Ok(JwtResult.fromArray(parts))
}
