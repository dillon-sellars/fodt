package ot.api.jwt

import com.auth0.jwt.JWT
import ot.model.JwtParts
import ot.model.b64Decode

object JwtApi {
    fun decodeJwt(jwt: String): JwtParts {
        val decoded = JWT.decode(jwt)
//        val claimsMap = decoded.claims.filter { it.value.asString() != null }.mapValues { it.value.asString() }
//        this.decodedJwt.value = JwtParts(decoded.issuer, Json.encodeToString(claimsMap), decoded.signature)
        val header = b64Decode(decoded.header)
        val payload = b64Decode(decoded.payload)
        return JwtParts(header, payload, decoded.signature)
    }
}
