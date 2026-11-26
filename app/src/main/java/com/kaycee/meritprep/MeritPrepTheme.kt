package com.kaycee.meritprep

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val MeritLightColors = lightColorScheme(
    primary = Color(0xFF176B62),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCEFEA),
    onPrimaryContainer = Color(0xFF103B36),
    secondary = Color(0xFF315A73),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE1ECF2),
    onSecondaryContainer = Color(0xFF19384B),
    tertiary = Color(0xFFB47A32),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF7EBD7),
    onTertiaryContainer = Color(0xFF51350F),
    background = Color(0xFFF5F7F7),
    onBackground = Color(0xFF172B33),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF172B33),
    surfaceVariant = Color(0xFFEBF0EF),
    onSurfaceVariant = Color(0xFF52666A),
    outline = Color(0xFFB9C9C6),
    outlineVariant = Color(0xFFDCE5E2),
    error = Color(0xFFB64040)
)

private val MeritDarkColors = darkColorScheme(
    primary = Color(0xFF75C9B8),
    onPrimary = Color(0xFF073B35),
    primaryContainer = Color(0xFF14554D),
    onPrimaryContainer = Color(0xFFD5F4EC),
    secondary = Color(0xFF9BC5DB),
    onSecondary = Color(0xFF17394B),
    secondaryContainer = Color(0xFF294B5D),
    onSecondaryContainer = Color(0xFFDDECF4),
    tertiary = Color(0xFFE5B66F),
    onTertiary = Color(0xFF432A08),
    tertiaryContainer = Color(0xFF62471F),
    onTertiaryContainer = Color(0xFFF9E9CA),
    background = Color(0xFF101D22),
    onBackground = Color(0xFFEAF1EF),
    surface = Color(0xFF17272D),
    onSurface = Color(0xFFEAF1EF),
    surfaceVariant = Color(0xFF22363C),
    onSurfaceVariant = Color(0xFFB9CBC7),
    outline = Color(0xFF526B6C),
    outlineVariant = Color(0xFF30464A),
    error = Color(0xFFFF9990)
)

@Composable
fun MeritPrepTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val shapes = Shapes(
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(22.dp)
    )
    MaterialTheme(
        colorScheme = if (darkTheme) MeritDarkColors else MeritLightColors,
        typography = Typography(),
        shapes = shapes,
        content = content
    )
}
