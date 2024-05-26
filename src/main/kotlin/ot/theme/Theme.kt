package ot.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import ot.theme.attr.Elevations
import ot.theme.attr.LocalElevations
import ot.theme.attr.LocalPaddings
import ot.theme.attr.Paddings
import ot.theme.attr.Shapes
import ot.theme.attr.Typography

@Composable
fun jetpackComposeMaterialTheme(
    lightColorPalette: Colors,
    darkColorPalette: Colors,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content:
        @Composable()
        () -> Unit,
) {
    val colors =
        if (darkTheme) {
            darkColorPalette
        } else {
            lightColorPalette
        }
    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalElevations provides Elevations(card = 8.dp),
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}
