package com.example.shacklehotelbuddy.domain.usecase.search

import com.example.shacklehotelbuddy.ui.model.BookingItem
import com.example.shacklehotelbuddy.ui.model.SearchItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

class MockSearchUseCaseImpl @Inject constructor() : SearchUseCase {

    override val searchItemsFlow: MutableStateFlow<List<SearchItem>> = MutableStateFlow(
        emptyList()
    )

    override suspend fun getHostelsList(query: BookingItem) {
        delay(1000)
        generateResponse()
    }

    private fun generateResponse() {
        val result: MutableList<SearchItem> = mutableListOf()
        val count = 30
        val locations = listOf(

            Triple("Japan", "Tokio", "¥"),
            Triple("Russia", "Moscow", "₽"),
            Triple("Qatar", "Doha", "QR"),
            Triple("France", "Paris", "€"),
            Triple("UK", "London", "£"),
            Triple("USA", "New York", "$")
        )
        val pics = listOf(
            "https://uploads-ssl.webflow.com/62ffc9c0c88a2bf8805a90b7/63316ca32484317028c77490_vila%20volman%20top.png",
            "https://vila-saltanat-41-varna.nochi.com/data/Photos/OriginalPhoto/10409/1040932/1040932465/Vila-Saltanat-41-Hotel-Varna-Exterior.JPEG",
            "https://q-xx.bstatic.com/xdata/images/hotel/max1024x768/270093281.jpg?k=9e461be2d8f0adf6facc75cb2d708e424580bad9d2d340c92d718fa6f686bafe&o=",
            "https://q-xx.bstatic.com/xdata/images/hotel/840x460/281507892.jpg?k=d974fae4d7dd4120150268771f0f9407cde46a8fcf1abf97d6f3258b273ccb2c&o=",
            "https://www.silverjet.nl/content/photos/res-957950411-res-1736806108-res-751828467-res-1295778732-res-655592676-res-1153178587-res-1272414819-co_ii_-_exterior_pool_view.jpg?v2.171",
            "https://cf.bstatic.com/xdata/images/hotel/max1024x768/134475469.jpg?k=986e0385365fa9e17ef6497e2fb7d5e16552358ad343c4ad8fc35b29802eacac&o=&hp=1",
        )

        val pricePerTime = listOf(
            "per day",
            "per night",
            "per month",
            "per year",
        )
        for (i in 0..count) {
            val location = locations.random()
            val item = SearchItem(
                id = i.toString(),
                imageUrl = pics.random(),
                name = "Private Palace №$i at ${location.second}",
                rating = Random.nextInt(0, 50).toDouble() / 10,
                price = Random.nextInt(0, 999999).toDouble() / 100,
                city = location.second,
                country = location.first,
                priceCurrencySymbol = location.third,
                pricePer = pricePerTime.random()
            )
            result.add(item)
        }
        val isReturnError = Random.nextInt(from = 0, until = 10) > 5
        if (isReturnError) throw Exception("Random Test Exception")
        searchItemsFlow.tryEmit(result)
    }
}
