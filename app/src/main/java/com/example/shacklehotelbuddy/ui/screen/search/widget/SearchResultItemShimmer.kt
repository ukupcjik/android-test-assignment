package com.example.shacklehotelbuddy.ui.screen.search.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun SearchResultItemShimmer(
    modifier: Modifier = Modifier
) {
    val highlightColor = ShackleHotelBuddyTheme.colors.colorSearchShimmerHighlight
    val contentColor = ShackleHotelBuddyTheme.colors.colorSearchShimmerBackground

    Column(
        modifier = modifier
            .background(color = ShackleHotelBuddyTheme.colors.colorBackground)
    ) {
        val shape =
            RoundedCornerShape(AppThemeDimensions.Search.Item.imageRoundedCorners)

        Spacer(
            modifier = Modifier
                .height(AppThemeDimensions.Search.Item.imageHeight)
                .fillMaxWidth()
                .clip(shape)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = highlightColor,
                    ),
                    color = contentColor,
                ),
        )
        for (i in 0..2) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = AppThemeDimensions.Search.Shimmer.shimmerTextPadding
                    )
            )

            Spacer(
                modifier = Modifier
                    .run {
                        when (i) {
                            0 -> fillMaxWidth()
                            1 -> width(AppThemeDimensions.Search.Shimmer.shimmerSecondLineWidth)
                            else -> width(AppThemeDimensions.Search.Shimmer.shimmerThirdLineWidth)
                        }
                    }
                    .height(AppThemeDimensions.Search.Shimmer.shimmerTextHeight)
                    .clip(shape)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = highlightColor,
                        ),
                        color = contentColor,
                    )
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = AppThemeDimensions.Padding.padding2
                )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SearchResultItemShimmer()
}
