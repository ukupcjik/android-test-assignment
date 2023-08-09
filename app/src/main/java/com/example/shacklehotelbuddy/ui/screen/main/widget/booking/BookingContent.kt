package com.example.shacklehotelbuddy.ui.screen.main.widget.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@Composable
fun BookingContent(
    bookingItem: BookingItem,
    onBookingItemUpdateAction: (BookingItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundShape = RoundedCornerShape(AppThemeDimensions.Booking.Card.cornerRounded)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = backgroundShape,
    ) {
        val rowSize = BookingRowType.values().size
        BookingRowType.values().forEachIndexed { index, _ ->
            BookingRowItem(
                itemPosition = index,
                bookingItem = bookingItem,
                onBookingItemUpdateAction = onBookingItemUpdateAction,
            )
            val isHorizontalSpacerVisible = index < rowSize - 1
            if (isHorizontalSpacerVisible) {
                Spacer(
                    modifier = modifier
                        .background(ShackleHotelBuddyTheme.colors.colorBorder)
                        .fillMaxWidth()
                        .height(AppThemeDimensions.Border.default)
                )
            }
        }
    }
}
