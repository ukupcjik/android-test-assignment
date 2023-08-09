package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.letterSpacing003
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.lineHeight20
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.lineHeight216
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.lineHeight22
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.lineHeight484
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.textSize12
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.textSize14
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.textSize18
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.textSize20
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions.Text.textSize44

val circularStdFontFamily = FontFamily(
    Font(R.font.circular_std_book, FontWeight.Normal),
    Font(R.font.circular_std_medium, FontWeight.Medium),
    Font(R.font.circular_std_bold, FontWeight.Bold),
)

val defaultStyleTypography = ShackleThemeTypography(
    headerLarge = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize44,
        lineHeight = lineHeight484,
    ),
    headerMedium = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize20,
        lineHeight = lineHeight22,
    ),
    bodySmall = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize12,
        lineHeight = lineHeight20,
    ),
    bodyMedium = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize14,
        lineHeight = lineHeight20,
    ),
    toolbar = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize18,
        lineHeight = lineHeight20,
    ),
    button = TextStyle(
        fontFamily = circularStdFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSize18,
        lineHeight = lineHeight216,
        letterSpacing = letterSpacing003,
    ),
)
