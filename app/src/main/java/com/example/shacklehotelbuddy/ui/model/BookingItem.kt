package com.example.shacklehotelbuddy.ui.model

import androidx.compose.runtime.Stable

@Stable
data class BookingItem(
    val id: Int,
    val checkInDate: CheckDate = CheckDate(),
    val checkOutDate: CheckDate = CheckDate(),
    val childrenCount: String = "",
    val adultCount: String = ""
)
