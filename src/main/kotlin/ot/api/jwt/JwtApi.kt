package ot.api.jwt

import com.auth0.jwt.JWT
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import ot.jwt.JwtParts
import ot.utils.b64Decode

@OptIn(ExperimentalSerializationApi::class)
val prettyJson =
    Json {
        prettyPrint = true
        prettyPrintIndent = "  "
    }

fun prettyJson(json: String) = prettyJson.encodeToString(prettyJson.decodeFromString<JsonObject>(json))

object JwtApi {
    fun decodeJwt(jwt: String): JwtParts {
        val decoded = JWT.decode(jwt)
        val header = prettyJson(b64Decode(decoded.header))
        val payload = prettyJson(b64Decode(decoded.payload))
        return JwtParts(header, payload, decoded.signature)
    }
}
