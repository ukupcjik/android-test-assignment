package com.example.shacklehotelbuddy.ui.screen.search.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.model.SearchItem
import com.example.shacklehotelbuddy.ui.theme.AppThemeDimensions
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.utils.roundUPDecimal

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchResultItem(
    searchItem: SearchItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = ShackleHotelBuddyTheme.colors.colorBackground)
    ) {
        val imageShape =
            RoundedCornerShape(AppThemeDimensions.Search.Item.imageRoundedCorners)
        Box(
            modifier = Modifier
                .height(AppThemeDimensions.Search.Item.imageHeight)
                .fillMaxWidth()
                .clip(imageShape),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .background(ShackleHotelBuddyTheme.colors.colorSearchItemPlaceholder)
                    .height(AppThemeDimensions.Search.Item.imageHeight)
                    .fillMaxWidth()
                    .clip(imageShape),
            )
            GlideImage(
                modifier = Modifier
                    .height(AppThemeDimensions.Search.Item.imageHeight)
                    .fillMaxWidth()
                    .clip(imageShape),
                model = searchItem.imageUrl,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,

                ) { builder ->
                builder
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            }
        }
        Spacer(
            modifier = Modifier
                .padding(top = AppThemeDimensions.Padding.padding8)
        )
        Description(
            modifier = Modifier,
            searchItem = searchItem
        )
    }
}

@Composable
private fun Description(
    searchItem: SearchItem,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = searchItem.name,
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ShackleHotelBuddyTheme.typography.bodyMedium
                    .copy(
                        fontWeight = FontWeight.Bold,
                        color = ShackleHotelBuddyTheme.colors.colorSearchItemNameText
                    ),
            )
            Icon(
                modifier = modifier
                    .padding(bottom = AppThemeDimensions.Padding.padding2)
                    .size(AppThemeDimensions.Icon.small),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(AppThemeDimensions.Padding.padding4)
            )
            Text(
                text = "${searchItem.rating}",
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                    color = ShackleHotelBuddyTheme.colors.colorSearchItemRatingText
                ),
            )
        }

        Spacer(
            modifier = Modifier
                .height(AppThemeDimensions.Padding.padding8)
        )

        Text(
            text = "${searchItem.country}, ${searchItem.city}",
            textAlign = TextAlign.Left,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                color = ShackleHotelBuddyTheme.colors.colorSearchItemLocationText
            ),
        )

        Spacer(
            modifier = Modifier
                .height(AppThemeDimensions.Padding.padding8)
        )

        Text(
            buildAnnotatedString {
                append("${searchItem.priceCurrencySymbol} ${searchItem.price.roundUPDecimal()} ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(searchItem.pricePer)
                }
            },
            textAlign = TextAlign.Left,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = ShackleHotelBuddyTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = ShackleHotelBuddyTheme.colors.colorSearchItemPriceText
            ),
        )
    }
}
