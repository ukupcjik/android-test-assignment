package com.example.shacklehotelbuddy.ui.screen.main.widget.booking

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.CheckDate
import java.util.Calendar
import java.util.concurrent.TimeUnit

object DialogDatePicker {

    fun showDataPicker(
        context: Context,
        rowType: BookingRowType,
        bookingItem: BookingItem,
        onBookingItemUpdateAction: (BookingItem) -> Unit,
    ) {
        val calendar = Calendar.getInstance()

        val currentDate = CheckDate(
            day = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR)
        )
        val currentDateUnix = calendar.timeInMillis

        val checkInUnix = if (bookingItem.checkInDate.day != 0) {
            calendar.set(
                bookingItem.checkInDate.year,
                bookingItem.checkInDate.month - 1,
                bookingItem.checkInDate.day,
            )
            calendar.timeInMillis
        } else {
            null
        }

        val checkOutUnix = if (bookingItem.checkOutDate.day != 0) {
            calendar.set(
                bookingItem.checkOutDate.year,
                bookingItem.checkOutDate.month - 1,
                bookingItem.checkOutDate.day,
            )
            calendar.timeInMillis
        } else {
            null
        }

        val oneDayInMills = TimeUnit.DAYS.toMillis(1)
        val checkInMin = currentDateUnix
        val checkInMax = checkOutUnix?.let { time ->
            time - oneDayInMills
        }
        val checkOutMin = checkInUnix?.let { time ->
            time + oneDayInMills
        } ?: currentDateUnix

        val calendarMinDate = if (rowType == BookingRowType.CHECK_IN) {
            checkInMin
        } else {
            checkOutMin
        }

        val calendarMaxDate = if (rowType == BookingRowType.CHECK_IN) {
            checkInMax
        } else {
            null
        }

        val initDay = when {
            (rowType == BookingRowType.CHECK_IN) && (bookingItem.checkInDate.day > 0) -> bookingItem.checkInDate.day
            (rowType == BookingRowType.CHECK_OUT) && (bookingItem.checkOutDate.day > 0) -> bookingItem.checkOutDate.day
            else -> currentDate.day
        }

        val initMonth = when {
            rowType == BookingRowType.CHECK_IN && (bookingItem.checkInDate.month > 0) -> bookingItem.checkInDate.month - 1
            rowType == BookingRowType.CHECK_OUT && (bookingItem.checkOutDate.month > 0) -> bookingItem.checkOutDate.month - 1
            else -> currentDate.month
        }

        val initYear = when {
            (rowType == BookingRowType.CHECK_IN) && (bookingItem.checkInDate.year > 0) -> bookingItem.checkInDate.year
            (rowType == BookingRowType.CHECK_OUT) && (bookingItem.checkOutDate.year > 0) -> bookingItem.checkOutDate.year
            else -> currentDate.year
        }

        val dialog = DatePickerDialog(
            context, R.style.DatePickerDialogTheme,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                val date = CheckDate(
                    day = day,
                    month = month + 1,
                    year = year
                )
                val result = if (rowType == BookingRowType.CHECK_IN) {
                    bookingItem.copy(checkInDate = date)
                } else {
                    bookingItem.copy(checkOutDate = date)
                }
                onBookingItemUpdateAction(result)
            }, initYear, initMonth, initDay
        )
        calendarMaxDate?.let { maxDate ->
            dialog.datePicker.maxDate = maxDate
        }
        dialog.datePicker.minDate = calendarMinDate
        dialog.show()
    }
}
