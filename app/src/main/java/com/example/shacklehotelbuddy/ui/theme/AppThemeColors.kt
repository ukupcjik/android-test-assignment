package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.ui.graphics.Color

object AppThemeColors {
    val colorWhite = Color(0xFFFFFFFF)
    val colorBlack = Color(0xFF000000)
    val colorTeal = Color(0xFF2CABB1)
    val colorLightGray = Color(0xFFDDDDDD)
    val colorDarkGray = Color(0xFF6D6D6D)
    val colorAbsolutelyTransparent = Color(0x00000000)
}

val defaultStylePalette: ShackleThemeColors = ShackleThemeColors(
    colorPrimaryAccent = AppThemeColors.colorTeal,
    colorAbsolutelyTransparent = AppThemeColors.colorAbsolutelyTransparent,
    colorBackground = AppThemeColors.colorAbsolutelyTransparent,

    colorCardsBackground = AppThemeColors.colorWhite,
    colorBorder = AppThemeColors.colorLightGray,

    colorToolbarBackground = AppThemeColors.colorAbsolutelyTransparent,
    colorToolbarContentTint = AppThemeColors.colorBlack,

    colorBookingTitleText = AppThemeColors.colorWhite,
    colorBookingItemTitleText = AppThemeColors.colorDarkGray,
    colorBookingItemHintText = AppThemeColors.colorDarkGray,
    colorBookingItemInputText = AppThemeColors.colorBlack,
    colorBookingItemIconTint = AppThemeColors.colorBlack,

    colorHistoryTitleText = AppThemeColors.colorWhite,
    colorHistoryItemText = AppThemeColors.colorDarkGray,
    colorHistoryItemIconTint = AppThemeColors.colorTeal,

    colorSearchButtonEnabled = AppThemeColors.colorTeal,
    colorSearchButtonDisabled = AppThemeColors.colorDarkGray,
    colorSearchButtonText = AppThemeColors.colorWhite,

    colorSearchItemNameText = AppThemeColors.colorBlack,
    colorSearchItemLocationText = AppThemeColors.colorDarkGray,
    colorSearchItemRatingText = AppThemeColors.colorBlack,
    colorSearchItemPriceText = AppThemeColors.colorBlack,
    colorSearchItemPlaceholder = AppThemeColors.colorLightGray,

    colorSearchShimmerBackground = AppThemeColors.colorLightGray,
    colorSearchShimmerHighlight = AppThemeColors.colorWhite,
    colorSearchErrorButtonBackground = AppThemeColors.colorDarkGray,
    colorSearchErrorButtonText = AppThemeColors.colorWhite,
    colorSearchErrorTitleText = AppThemeColors.colorBlack,
    colorSearchErrorMessageText = AppThemeColors.colorBlack,
)
