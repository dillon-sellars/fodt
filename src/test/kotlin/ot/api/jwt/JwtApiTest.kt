package ot.api.jwt

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import ot.utils.Base64

class JwtApiTest :
    StringSpec({
        "validateJwt should return error for empty JWT" {
            val result = JwtApi.validateJwt("")
            result.isErr shouldBe true
            result.error.message shouldBe "JWT cannot be blank"
        }

        "validateJwt should return error for JWT with no parts" {
            val encoded = Base64.encode("eee")
            val result = JwtApi.validateJwt(encoded)
            result.isOk shouldBe true
            result.value.parts.header shouldBe encoded
        }

        "validateJwt should return error for JWT with blank parts" {
            val result = JwtApi.validateJwt(" . ")
            result.isErr shouldBe true
            result.error.message shouldBe "JWT cannot be blank"
        }

        "validateJwt hould return error for JWT with one blank part" {
            val result = JwtApi.validateJwt(" .")
            result.isErr shouldBe true
            result.error.message shouldBe "JWT cannot be blank"
        }

        "validateJwt should throw error on jwt with non base64 payload" {
            val result = JwtApi.validateJwt("invalid.jwt.man")
            result.isErr shouldBe true
            result.error.message shouldBe "JWT header is not base64 encoded"
        }

        "validateJwt should return valid jwt parts" {
            val result =
                JwtApi.validateJwt(
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                        "eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NDAyNzA5NzQsImV4cCI6MTY3MTgwNjk3NCwiYXVkIjoid3d3LmV4YW1wbGUuY29t" +
                        "Iiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2Nr" +
                        "ZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0." +
                        "rWtd0UlPgNAMAEI5gu1SYMqmQPZCBRbgTW7dRFJAezg",
                )
            result.value.parts.header shouldBe "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"
            result.value.parts.payload shouldBe
                "eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NDAyNzA5NzQsImV4cCI6MTY3MTgwNjk3NCwiYXVkIjoid3d3LmV4YW1wbGUuY29t" +
                "Iiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2Nr" +
                "ZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0"
            result.value.parts.signature shouldBe "rWtd0UlPgNAMAEI5gu1SYMqmQPZCBRbgTW7dRFJAezg"
        }
    })
