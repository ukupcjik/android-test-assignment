package com.example.shacklehotelbuddy.ui.screen.main.widget.booking

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.CheckDate
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.utils.noRippleClickable

const val INPUT_DIGITS_LENGTH = 2

@Composable
fun BookingRowItem(
    itemPosition: Int,
    bookingItem: BookingItem,
    onBookingItemUpdateAction: (BookingItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val rowItem = remember(itemPosition) { BookingRowType.getByPosition(position = itemPosition) }
    val itemHeight = AppThemeDimensions.Booking.Item.height

    Row(
        modifier = modifier
            .height(itemHeight)
            .padding(horizontal = AppThemeDimensions.Padding.padding16)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookingRawTitle(
            iconRes = rowItem.iconResource,
            titleRes = rowItem.titleResource,
            modifier = Modifier
                .weight(0.5f)
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(AppThemeDimensions.Border.default)
                .background(ShackleHotelBuddyTheme.colors.colorBorder)
        )
        if (rowItem.isDatePicker) {
            BookingRawInputDate(
                modifier = Modifier
                    .weight(0.5f),
                rowItem = rowItem,
                bookingItem = bookingItem,
                onBookingItemUpdateAction = onBookingItemUpdateAction
            )
        } else {
            BookingRawInputDigits(
                modifier = Modifier
                    .weight(0.5f),
                rowItem = rowItem,
                bookingItem = bookingItem,
                onBookingItemUpdateAction = onBookingItemUpdateAction
            )
        }
    }
}

@Composable
private fun BookingRawTitle(
    @DrawableRes
    iconRes: Int,
    @StringRes
    titleRes: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(AppThemeDimensions.Icon.medium),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = ShackleHotelBuddyTheme.colors.colorBookingItemIconTint
        )
        Text(
            modifier = Modifier
                .padding(AppThemeDimensions.Padding.padding8),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            text = stringResource(id = titleRes),
            style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                color = ShackleHotelBuddyTheme.colors.colorBookingItemTitleText
            ),
        )
    }
}

@Composable
private fun BookingRawInputDigits(
    rowItem: BookingRowType,
    bookingItem: BookingItem,
    onBookingItemUpdateAction: (BookingItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = if (rowItem == BookingRowType.ADULTS) {
        bookingItem.adultCount
    } else {
        bookingItem.childrenCount
    }
    TextField(
        modifier = modifier,
        value = text,
        placeholder = {
            if (text.isBlank()) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Visible,
                    text = stringResource(id = rowItem.textHint),
                    style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                        color = ShackleHotelBuddyTheme.colors.colorBookingItemHintText
                    ),
                )
            }
        },
        onValueChange = { value ->
            if (value.length <= INPUT_DIGITS_LENGTH) {
                val count = value.filter { char ->
                    char.isDigit()
                }
                val result = if (rowItem == BookingRowType.ADULTS) {
                    bookingItem.copy(adultCount = count)
                } else {
                    bookingItem.copy(childrenCount = count)
                }
                onBookingItemUpdateAction(result)
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            disabledTextColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
            focusedIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
            unfocusedIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
            disabledIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
            color = ShackleHotelBuddyTheme.colors.colorBookingItemInputText
        )
    )
}

@Composable
private fun BookingRawInputDate(
    rowItem: BookingRowType,
    bookingItem: BookingItem,
    onBookingItemUpdateAction: (BookingItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val checkDate: CheckDate = if (rowItem == BookingRowType.CHECK_IN) {
        bookingItem.checkInDate
    } else {
        bookingItem.checkOutDate
    }
    val dateColor = if (checkDate.day == 0) {
        ShackleHotelBuddyTheme.colors.colorBookingItemHintText
    } else {
        ShackleHotelBuddyTheme.colors.colorBookingItemInputText
    }
    TextField(
        enabled = false,
        modifier = modifier
            .noRippleClickable {
                DialogDatePicker.showDataPicker(
                    context = context,
                    bookingItem = bookingItem,
                    onBookingItemUpdateAction = onBookingItemUpdateAction,
                    rowType = rowItem
                )
            },
        value = checkDate.toString(),
        onValueChange = {},
        singleLine = true,
        colors = TextFieldDefaults.colors(
            disabledTextColor = ShackleHotelBuddyTheme.colors.colorBookingItemInputText,
            focusedIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
            unfocusedIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
            disabledIndicatorColor = ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent,
        ),
        textStyle = ShackleHotelBuddyTheme.typography.bodyMedium
            .copy(color = dateColor),
    )
}
