package ot.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun MonoText(text: String) {
    Text(text, fontFamily = FontFamily.Monospace, fontSize = 14.sp)
}
