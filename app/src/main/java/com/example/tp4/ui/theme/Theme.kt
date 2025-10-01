package com.example.tp4.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,       // Boutons principaux, top bars, éléments actifs
    onPrimary = OnPrimaryWhite,  // Texte/icônes sur les éléments primaires (rouge)

    background = AppWhite, // Couleur de fond générale de l'app (gris très clair)
    onBackground = DarkText,     // Texte sur le fond

    surface = AppWhite,          // Couleur des cartes, feuilles, popups (blanc pur)
    onSurface = DarkText,        // Texte sur les surfaces (noir)
    surfaceVariant = AppWhite,   // Fond des champs de texte, zones légèrement différentes
    onSurfaceVariant = MediumGreyText, // Texte sur les surfaces variantes

    // Les autres couleurs que tu peux ajuster si nécessaire
    secondary = PrimaryOrange,      // Ici, on garde la même couleur que primaire pour la cohérence
    onSecondary = OnPrimaryWhite,
    tertiary = PrimaryOrange,
    onTertiary = OnPrimaryWhite,
    error = Color(0xFFB00020),   // Une couleur d'erreur standard
    onError = Color(0xFFFFFFFF),
    primaryContainer = PrimaryOrange, // Conteneur pour les éléments primaires (ex: avatar couleur de fond)
    onPrimaryContainer = OnPrimaryWhite // Texte sur le conteneur primaire
)

@Composable
fun Tp4Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}