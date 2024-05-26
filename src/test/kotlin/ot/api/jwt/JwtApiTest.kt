package ot.api.jwt

import com.auth0.jwt.exceptions.JWTDecodeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class JwtApiTest : StringSpec({
    "should return valid jwt parts" {
        val result =
            JwtApi.decodeJwt(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NDAyNzA5NzQsImV4cCI6MTY3MTgwNjk3NCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.rWtd0UlPgNAMAEI5gu1SYMqmQPZCBRbgTW7dRFJAezg",
            )
        val payload = Json.parseToJsonElement(result.payload)
        val sub = payload.jsonObject["sub"]
        sub?.jsonPrimitive?.content shouldBe "jrocket@example.com"
    }

    "should throw error on jwt with 2 parts" {
        val exception =
            shouldThrow<JWTDecodeException> {
                JwtApi.decodeJwt("invalid.jwt")
            }
        exception.message shouldBe "The token was expected to have 3 parts, but got 2."
    }

    "should throw error on jwt with non base64 payload" {
        val exception =
            shouldThrow<JWTDecodeException> {
                JwtApi.decodeJwt("invalid.jwt.man")
            }
        exception.message shouldEndWith "doesn't have a valid JSON format."
    }
})
