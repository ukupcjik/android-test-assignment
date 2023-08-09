package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ShackleHotelBuddyTheme(
    content: @Composable () -> Unit
) {
    val colors = defaultStylePalette
    val typography = defaultStyleTypography
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            colors.colorAbsolutelyTransparent,
            darkIcons = true
        )
    }

    CompositionLocalProvider(
        LocalShackleThemeColors provides colors,
        LocalShackleThemeTypography provides typography,
    ) {
        content()
    }
}