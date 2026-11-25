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
    primary = Color(0xFF5A78D6),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8EEFF),
    onPrimaryContainer = Color(0xFF1A2858),
    secondary = Color(0xFF4B438F),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFECE9FF),
    onSecondaryContainer = Color(0xFF29235F),
    tertiary = Color(0xFFE87983),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE8EA),
    onTertiaryContainer = Color(0xFF681F2A),
    background = Color(0xFFF4F6FC),
    onBackground = Color(0xFF1D223B),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1D223B),
    surfaceVariant = Color(0xFFEDF1F9),
    onSurfaceVariant = Color(0xFF646E8A),
    outline = Color(0xFFC8D2E6),
    outlineVariant = Color(0xFFE2E8F5),
    error = Color(0xFFDC4A58)
)

private val MeritDarkColors = darkColorScheme(
    primary = Color(0xFF9BB5FF),
    onPrimary = Color(0xFF172653),
    primaryContainer = Color(0xFF293967),
    onPrimaryContainer = Color(0xFFE4EAFF),
    secondary = Color(0xFFB7AEFF),
    onSecondary = Color(0xFF29235F),
    secondaryContainer = Color(0xFF3B3477),
    onSecondaryContainer = Color(0xFFECE9FF),
    tertiary = Color(0xFFFFA0A7),
    onTertiary = Color(0xFF4B121D),
    tertiaryContainer = Color(0xFF642C37),
    onTertiaryContainer = Color(0xFFFFE8EA),
    background = Color(0xFF101426),
    onBackground = Color(0xFFF5F6FF),
    surface = Color(0xFF171E34),
    onSurface = Color(0xFFF5F6FF),
    surfaceVariant = Color(0xFF242D49),
    onSurfaceVariant = Color(0xFFC4CBE0),
    outline = Color(0xFF596487),
    outlineVariant = Color(0xFF303B5C),
    error = Color(0xFFFF929A)
)

@Composable
fun MeritPrepTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val shapes = Shapes(
        extraSmall = RoundedCornerShape(8.dp),
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(22.dp),
        extraLarge = RoundedCornerShape(28.dp)
    )
    MaterialTheme(
        colorScheme = if (darkTheme) MeritDarkColors else MeritLightColors,
        typography = Typography(),
        shapes = shapes,
        content = content
    )
}
