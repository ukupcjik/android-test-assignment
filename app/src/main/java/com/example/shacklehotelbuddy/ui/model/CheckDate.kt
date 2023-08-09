package com.example.shacklehotelbuddy.ui.model

import androidx.compose.runtime.Stable

@Stable
data class CheckDate(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
) {

    override fun toString(): String {
        val dayString = if (day == 0) "DD" else day
        val monthString = if (month == 0) "MM" else month
        val yearString = if (year == 0) "YYYY" else year
        return "$dayString / $monthString / $yearString"
    }
}
