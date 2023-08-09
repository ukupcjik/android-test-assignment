package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Stable
data class ShackleThemeColors(
    val colorPrimaryAccent: Color,
    val colorAbsolutelyTransparent: Color,
    val colorBackground: Color,
    val colorCardsBackground: Color,
    val colorBorder: Color,
    val colorToolbarBackground: Color,
    val colorToolbarContentTint: Color,

    val colorBookingTitleText: Color,
    val colorBookingItemTitleText: Color,
    val colorBookingItemHintText: Color,
    val colorBookingItemInputText: Color,
    val colorBookingItemIconTint: Color,

    val colorHistoryTitleText: Color,
    val colorHistoryItemText: Color,
    val colorHistoryItemIconTint: Color,

    val colorSearchButtonEnabled: Color,
    val colorSearchButtonDisabled: Color,
    val colorSearchButtonText: Color,

    val colorSearchItemNameText: Color,
    val colorSearchItemLocationText: Color,
    val colorSearchItemRatingText: Color,
    val colorSearchItemPriceText: Color,
    val colorSearchItemPlaceholder: Color,

    val colorSearchShimmerBackground: Color,
    val colorSearchShimmerHighlight: Color,

    val colorSearchErrorButtonBackground: Color,
    val colorSearchErrorButtonText: Color,
    val colorSearchErrorTitleText: Color,
    val colorSearchErrorMessageText: Color,
)

@Stable
data class ShackleThemeTypography(
    val toolbar: TextStyle,
    val headerLarge: TextStyle,
    val headerMedium: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val button: TextStyle
)

object ShackleHotelBuddyTheme {
    val colors: ShackleThemeColors
        @Composable
        get() = LocalShackleThemeColors.current

    val typography: ShackleThemeTypography
        @Composable
        get() = LocalShackleThemeTypography.current

}

val LocalShackleThemeColors = staticCompositionLocalOf<ShackleThemeColors> {
    error("Colors not provided")
}

val LocalShackleThemeTypography = staticCompositionLocalOf<ShackleThemeTypography> {
    error("Typography not provided")
}
