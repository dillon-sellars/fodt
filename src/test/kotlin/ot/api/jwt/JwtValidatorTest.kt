package ot.api.jwt

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import ot.jwt.validateJwt

class JwtValidatorTest : StringSpec({
    "should return error for empty JWT" {
        val result = validateJwt("")
        result.isErr shouldBe true
        result.error.message shouldBe "JWT cannot be blank"
    }

    "should return error for JWT with no parts" {
        val result = validateJwt("eee")
        result.isOk shouldBe true
        result.value.header shouldBe "eee"
    }

    "should return error for JWT with blank parts" {
        val result = validateJwt(" . ")
        result.isErr shouldBe true
        result.error.message shouldBe "JWT cannot be blank"
    }

    "should return error for JWT with one blank part" {
        val result = validateJwt(" .")
        result.isErr shouldBe true
        result.error.message shouldBe "JWT cannot be blank"
    }
})
