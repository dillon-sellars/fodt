package ot.model

import androidx.compose.runtime.mutableStateOf
import com.auth0.jwt.JWT
import kotlinx.serialization.ExperimentalSerializationApi
import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets

data class JwtParts(val header: String, val payload: String, val signature: String)

fun b64Decode(input: String): String {
    return String(Base64.decodeBase64(input), StandardCharsets.UTF_8);
}

object DesktopState {

    @OptIn(ExperimentalSerializationApi::class)
    fun decodeJwt(jwt: String) {
        val decoded = JWT.decode(jwt)
//        val claimsMap = decoded.claims.filter { it.value.asString() != null }.mapValues { it.value.asString() }
//        this.decodedJwt.value = JwtParts(decoded.issuer, Json.encodeToString(claimsMap), decoded.signature)
        val header = b64Decode(decoded.header)
        println("Header $header")
        val payload = b64Decode(decoded.payload)
        println("Payload $payload")
        this.decodedJwt.value =
            JwtParts(header, payload, decoded.signature)
    }

    private val decodedJwt = mutableStateOf<JwtParts?>(null);
    fun decodedJwt(): JwtParts? {
        return this.decodedJwt.value;
    }
}