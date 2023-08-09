package com.example.shacklehotelbuddy.ui.screen.main.widget.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.utils.noRippleClickable

@Composable
fun HistoryListItem(
    historyItem: BookingItem,
    onResetItemClick: (BookingItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val rowHeight = AppThemeDimensions.History.Item.height
    val backgroundShape = RoundedCornerShape(AppThemeDimensions.History.Item.cornerRounded)
    Card(
        modifier = modifier
            .height(rowHeight)
            .fillMaxWidth(),
        shape = backgroundShape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppThemeDimensions.History.Item.iconContainerWidth)
                    .noRippleClickable {
                        onResetItemClick(historyItem)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(AppThemeDimensions.Icon.medium),
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = null,
                    tint = ShackleHotelBuddyTheme.colors.colorHistoryItemIconTint
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(AppThemeDimensions.Border.default)
                    .background(ShackleHotelBuddyTheme.colors.colorBorder),
            )
            Text(
                modifier = Modifier
                    .padding(start = AppThemeDimensions.Padding.padding16)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "${historyItem.checkInDate} - ${historyItem.checkOutDate}",
                style = ShackleHotelBuddyTheme.typography.bodySmall.copy(
                    color = ShackleHotelBuddyTheme.colors.colorHistoryItemText
                ),

                )
            val adultsCount = historyItem.adultCount.toIntOrNull() ?: 0
            val adultString = if (adultsCount > 0) {
                pluralStringResource(id = R.plurals.adult, count = adultsCount, adultsCount)
            } else {
                ""
            }
            val childrenCount = historyItem.childrenCount.toIntOrNull() ?: 0
            val childString = if (childrenCount > 0) {
                pluralStringResource(id = R.plurals.children, count = childrenCount, childrenCount)
            } else {
                ""
            }
            val spacer = if (adultString.isNotBlank() && childString.isNotBlank()) {
                ", "
            } else {
                ""
            }
            val guestsText = "$adultString$spacer$childString"

            Spacer(modifier = Modifier.width(AppThemeDimensions.Padding.padding8))
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                maxLines = 1,
                overflow = TextOverflow.Visible,
                text = guestsText,
                style = ShackleHotelBuddyTheme.typography.bodySmall.copy(
                    color = ShackleHotelBuddyTheme.colors.colorHistoryItemText
                ),
            )
            Spacer(modifier = Modifier.width(AppThemeDimensions.Padding.padding16))
        }
    }
}
