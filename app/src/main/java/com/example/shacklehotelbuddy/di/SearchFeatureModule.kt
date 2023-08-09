package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.domain.usecase.search.SearchUseCase
import com.example.shacklehotelbuddy.domain.usecase.search.SearchUseCaseImpl
import com.example.shacklehotelbuddy.network.ApiConfig
import com.example.shacklehotelbuddy.network.RapidApiClient
import com.example.shacklehotelbuddy.repository.search.SearchRepository
import com.example.shacklehotelbuddy.repository.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {
    @Binds
    @ViewModelScoped
    /**
     * change SearchUseCaseImpl to MockSearchUseCaseImpl for tests without network
     * */
    abstract fun bindSearchUseCases(searchUseCase: SearchUseCaseImpl): SearchUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository
}

@Module
@InstallIn(ViewModelComponent::class)
class SearchNetworkModule {
    @ViewModelScoped
    @Provides
    fun provideRetrofitNetworkClient(): RapidApiClient {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RapidApiClient::class.java)
    }
}
