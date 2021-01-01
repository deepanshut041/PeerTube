package com.squrlabs.peertube.util.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkPalette = darkColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryLight,
    onPrimary = Color.Black,
    secondary = colorSecondaryLight,
    onSecondary = Color.Black,
    onSurface = Color.White,
    onBackground = Color.White,
    error = colorErrorLight,
    onError = Color.Black
)

private val LightPalette = lightColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryDark,
    onPrimary = Color.White,
    secondary = colorSecondaryDark,
    secondaryVariant = colorSecondaryDark,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    error = colorErrorDark,
    onError = Color.White
)

@Composable
fun PeerTubeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkPalette else LightPalette

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography
    )
}