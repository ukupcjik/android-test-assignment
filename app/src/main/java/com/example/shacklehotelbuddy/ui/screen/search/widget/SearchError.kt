package com.example.shacklehotelbuddy.ui.screen.search.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@Composable
fun SearchError(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.app_search_result_screen_error_title),
            style = ShackleHotelBuddyTheme.typography.headerMedium
                .copy(
                    fontWeight = FontWeight.W500,
                    color = ShackleHotelBuddyTheme.colors.colorSearchErrorTitleText,
                    textAlign = TextAlign.Center,
                ),
        )
        Text(
            modifier = Modifier
                .padding(
                    top = AppThemeDimensions.Padding.padding8,
                ),
            text = stringResource(id = R.string.app_search_result_screen_error_message),
            style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                color = ShackleHotelBuddyTheme.colors.colorSearchErrorMessageText
            ),
        )
        Button(
            modifier = Modifier
                .padding(
                    top = AppThemeDimensions.Padding.padding8,
                )
                .fillMaxWidth()
                .height(AppThemeDimensions.Search.Error.errorButtonHeight),
            onClick = onRetryClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ShackleHotelBuddyTheme.colors.colorSearchErrorButtonBackground,
            ),
            shape = RoundedCornerShape(AppThemeDimensions.Search.Error.errorButtonCornerRound),
            contentPadding = PaddingValues(),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                text = stringResource(id = R.string.app_search_result_screen_error_button),
                style = ShackleHotelBuddyTheme.typography.button.copy(
                    textAlign = TextAlign.Center,
                    color = ShackleHotelBuddyTheme.colors.colorSearchErrorButtonText
                )
            )
        }
    }
}
