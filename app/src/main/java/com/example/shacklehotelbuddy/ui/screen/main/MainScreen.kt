package com.example.shacklehotelbuddy.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.navigation.Screens
import com.example.shacklehotelbuddy.navigation.Screens.Companion.SEARCH_SCREEN_ARG_BODY
import com.example.shacklehotelbuddy.ui.screen.main.store.MainAction
import com.example.shacklehotelbuddy.ui.screen.main.store.MainStoreEvent
import com.example.shacklehotelbuddy.ui.screen.main.widget.booking.BookingContent
import com.example.shacklehotelbuddy.ui.screen.main.widget.history.HistoryList
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.utils.collectAsEffect
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val state by remember { viewModel.state }.collectAsState()

    viewModel.events.collectAsEffect { event ->
        when (event) {
            is MainStoreEvent.OnNavigationToSearch -> {
                navController.navigate(
                    Screens.SearchScreen.route.replace(
                        SEARCH_SCREEN_ARG_BODY,
                        event.bookingItemJSON
                    )
                )
            }
        }
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .paint(
                painterResource(id = R.drawable.background_main),
                contentScale = ContentScale.Crop
            )
            .padding(
                top = AppThemeDimensions.Padding.padding16,
                start = AppThemeDimensions.Padding.padding16,
                end = AppThemeDimensions.Padding.padding16,
                bottom = AppThemeDimensions.Padding.padding40,
            )
    ) {
        val (
            bookingTitle,
            bookingContent,
            historyTitle,
            historyContent,
            searchButton,
        ) = createRefs()

        Text(
            modifier = Modifier.constrainAs(bookingTitle) {
                bottom.linkTo(bookingContent.top, margin = AppThemeDimensions.Padding.padding24)
                start.linkTo(parent.start)
            },
            text = stringResource(id = R.string.app_main_screen_booking_title),
            style = ShackleHotelBuddyTheme.typography.headerLarge.copy(
                color = ShackleHotelBuddyTheme.colors.colorBookingTitleText

            ),
        )

        BookingContent(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(bookingContent) {
                    bottom.linkTo(searchButton.top)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            bookingItem = state.bookingData,
            onBookingItemUpdateAction = { bookingItem ->
                viewModel.sendAction(MainAction.OnBookingDataChanged(bookingItem))
            }
        )

        if (state.isRecentHistoryAvailable) {
            Text(
                modifier = Modifier.constrainAs(historyTitle) {
                    top.linkTo(
                        bookingContent.bottom,
                        margin = AppThemeDimensions.Padding.padding24
                    )
                    start.linkTo(parent.start)
                },
                maxLines = 1,
                overflow = TextOverflow.Visible,
                text = stringResource(id = R.string.app_main_screen_history_title),
                style = ShackleHotelBuddyTheme.typography.headerMedium.copy(
                    color = ShackleHotelBuddyTheme.colors.colorHistoryTitleText
                ),
            )

            HistoryList(
                modifier = Modifier
                    .constrainAs(historyContent) {
                        bottom.linkTo(
                            searchButton.top,
                            margin = AppThemeDimensions.Padding.padding4
                        )
                        top.linkTo(
                            historyTitle.bottom,
                            margin = AppThemeDimensions.Padding.padding16
                        )
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                historyItems = state.historyData.toImmutableList(),
                onResetItemClick = { historyItem ->
                    viewModel.sendAction(MainAction.OnApplyRecentBooking(historyItem))
                }
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(searchButton) {
                    bottom.linkTo(
                        parent.bottom,
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(AppThemeDimensions.Booking.Button.height),
            onClick = {
                viewModel.sendAction(MainAction.OnSearchAction)
            },
            shape = RoundedCornerShape(AppThemeDimensions.Booking.Button.cornerRounded),
            colors = ButtonDefaults.buttonColors(
                containerColor = ShackleHotelBuddyTheme.colors.colorSearchButtonEnabled,
                disabledContainerColor = ShackleHotelBuddyTheme.colors.colorSearchButtonDisabled,
            ),
            enabled = state.isSearchButtonDisabled.not()
        ) {
            Text(
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                text = stringResource(id = R.string.app_main_screen_booking_search_button),
                style = ShackleHotelBuddyTheme.typography.button.copy(
                    color = ShackleHotelBuddyTheme.colors.colorSearchButtonText
                ),
            )
        }
    }
}
