package com.example.shacklehotelbuddy.ui.screen.search.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shacklehotelbuddy.ui.model.SearchItem
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchContentState
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchState
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchResultList(
    searchState: SearchState,
    searchItems: ImmutableList<SearchItem>,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                top = AppThemeDimensions.Padding.padding24,
                start = AppThemeDimensions.Padding.padding16,
                end = AppThemeDimensions.Padding.padding16
            )
            .navigationBarsPadding()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        when (searchState.searchContentState) {
            is SearchContentState.Initial,
            is SearchContentState.Loading -> {
                Column(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false)
                ) {
                    val shimmerCount = 5
                    for (i in 0..shimmerCount) {
                        SearchResultItemShimmer(
                            modifier = Modifier
                                .padding(
                                    bottom = AppThemeDimensions.Padding.padding24,
                                ),
                        )
                    }
                }
            }

            is SearchContentState.Content -> {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                ) {
                    searchItems.forEach { item ->
                        SearchResultItem(
                            modifier = Modifier
                                .padding(
                                    bottom = AppThemeDimensions.Padding.padding24,
                                ),
                            searchItem = item
                        )
                    }
                }
            }

            is SearchContentState.Error -> {
                SearchError(
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
        }
    }
}
