package ot.utils

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets

fun b64Decode(input: String): String {
    return String(Base64.decodeBase64(input), StandardCharsets.UTF_8)
}

fun b64Encode(input: String): String {
    return String(Base64.encodeBase64(input.toByteArray(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)
}
