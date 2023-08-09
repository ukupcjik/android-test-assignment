package com.example.shacklehotelbuddy.ui.screen.main.widget.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HistoryList(
    historyItems: ImmutableList<BookingItem>,
    onResetItemClick: (BookingItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(
                    weight = 1f,
                    fill = false
                )
        ) {
            historyItems.forEach { item ->
                HistoryListItem(
                    modifier = Modifier
                        .padding(
                            bottom = AppThemeDimensions.Padding.padding4
                        ),
                    historyItem = item,
                    onResetItemClick = onResetItemClick
                )
            }
        }
    }
}
