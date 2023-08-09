package com.example.shacklehotelbuddy.ui.screen.main.widget.booking

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.example.shacklehotelbuddy.R

@Stable
enum class BookingRowType(
    @StringRes val titleResource: Int,
    @DrawableRes val iconResource: Int,
    @StringRes val textHint: Int,
    val position: Int,
    val isDatePicker: Boolean,
) {
    CHECK_IN(
        titleResource = R.string.app_main_screen_booking_check_in,
        iconResource = R.drawable.ic_check_in,
        position = 0,
        isDatePicker = true,
        textHint = R.string.app_main_screen_booking_date_hint
    ),
    CHECK_OUT(
        titleResource = R.string.app_main_screen_booking_check_out,
        iconResource = R.drawable.ic_check_out,
        position = 1,
        isDatePicker = true,
        textHint = R.string.app_main_screen_booking_date_hint

    ),
    ADULTS(
        titleResource = R.string.app_main_screen_booking_adults,
        iconResource = R.drawable.ic_adult,
        position = 2,
        isDatePicker = false,
        textHint = R.string.app_main_screen_booking_count_hint

    ),
    CHILDREN(
        titleResource = R.string.app_main_screen_booking_children,
        iconResource = R.drawable.ic_children,
        position = 3,
        isDatePicker = false,
        textHint = R.string.app_main_screen_booking_count_hint
    ),
    ;

    companion object {
        fun getByPosition(position: Int): BookingRowType {
            return values().firstOrNull { raw ->
                raw.position == position
            } ?: CHECK_IN
        }
    }
}
