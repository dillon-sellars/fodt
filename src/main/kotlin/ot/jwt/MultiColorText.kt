package ot.jwt

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

data class TextWithColor(
    val text: String,
    val color: Color,
)

@Composable
fun MultiColorText(textAndColors: Array<TextWithColor>) {
    Text(
        buildAnnotatedString {
            textAndColors.map {
                withStyle(style = SpanStyle(color = it.color)) {
                    append(it.text)
                }
            }
        },
    )
}

class ColorsTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            buildAnnotatedStringJwt(text.toString()),
            OffsetMapping.Identity,
        )
}

val colors = listOf(Color(0xFF57965C), Color(0xFFC94F4F), Color(0xFF9595FF))

fun buildAnnotatedStringJwt(text: String): AnnotatedString {
    val sections = text.split(".")
    val builder = AnnotatedString.Builder()
    if (sections.isEmpty() || sections.size == 1 && sections[0].isBlank() || sections.size > 3) {
        builder.append(text)
        return builder.toAnnotatedString()
    }

    sections.forEachIndexed { idx, element ->
        builder.withStyle(style = SpanStyle(color = colors[idx % 3])) {
            append(element + if (idx == sections.size - 1) "" else ".")
        }
    }
    return builder.toAnnotatedString()
}
