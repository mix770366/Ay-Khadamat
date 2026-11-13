package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldGreenDarkPrimary,
    onPrimary = Color(0xFF00391A),
    primaryContainer = EmeraldGreenDarkContainer,
    onPrimaryContainer = EmeraldGreenDarkPrimary,
    secondary = AmberAccentDarkSecondary,
    onSecondary = Color(0xFF452200),
    secondaryContainer = Color(0xFF5A3000),
    onSecondaryContainer = AmberAccentDarkSecondary,
    tertiary = Color(0xFF7DD3FC),
    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onBackground = Color(0xFFE5EDE6),
    onSurface = Color(0xFFE5EDE6),
    onSurfaceVariant = Color(0xFFB9C8BA),
    outline = DarkOutline
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = EmeraldGreenContainer,
    onPrimaryContainer = OnEmeraldGreenContainer,
    secondary = AmberAccentSecondary,
    onSecondary = Color.White,
    secondaryContainer = AmberAccentContainer,
    onSecondaryContainer = OnAmberAccentContainer,
    tertiary = TrustBlueTertiary,
    onTertiary = Color.White,
    tertiaryContainer = TrustBlueContainer,
    onTertiaryContainer = OnTrustBlueContainer,
    background = MintBackgroundLight,
    surface = CardSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onBackground = Color(0xFF142016),
    onSurface = Color(0xFF142016),
    onSurfaceVariant = Color(0xFF3E4E41),
    outline = OutlineLight
)

@Composable
fun AyKhadamatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep consistent emerald green brand look by default
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Keep alias for compatibility
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    AyKhadamatTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}
