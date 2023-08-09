package com.example.shacklehotelbuddy.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.screen.search.store.SearchAction
import com.example.shacklehotelbuddy.ui.screen.search.widget.SearchResultList
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavHostController,
    searchQuery: BookingItem?,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
) {

    val state by remember { viewModel.state }.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    val searchItems = remember(state.searchItemsFlow, lifecycleOwner) {
        state.searchItemsFlow?.invoke()
    }?.collectAsStateWithLifecycle()?.value ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.init(query = searchQuery)
    }
    Column(
        modifier = modifier
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_search_result_screen_title),
                    textAlign = TextAlign.Center,
                    style = ShackleHotelBuddyTheme.typography.toolbar.copy(
                        color = ShackleHotelBuddyTheme.colors.colorToolbarContentTint
                    ),
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = ShackleHotelBuddyTheme.colors.colorToolbarContentTint,
                        contentDescription = null,
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = ShackleHotelBuddyTheme.colors.colorToolbarBackground
            )
        )
        SearchResultList(
            modifier = Modifier
                .background(ShackleHotelBuddyTheme.colors.colorAbsolutelyTransparent),
            searchState = state,
            searchItems = searchItems.toImmutableList(),
            onRetryClick = remember {
                { viewModel.sendAction(SearchAction.OnRefresh) }
            }
        )
    }
}
