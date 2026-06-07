package com.azure.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,

    background = Background,
    surface = Surface,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextPrimary,

    error = Error
)

@Composable
fun PokeDecsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        shapes = AppShapes,
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content,
    )
}