package com.example.shacklehotelbuddy.di

import android.content.Context
import com.example.shacklehotelbuddy.domain.usecase.booking.BookingUseCase
import com.example.shacklehotelbuddy.domain.usecase.booking.BookingUseCaseImpl
import com.example.shacklehotelbuddy.repository.booking.BookingRepository
import com.example.shacklehotelbuddy.repository.booking.BookingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BookingModule {
    @Binds
    @ViewModelScoped
    abstract fun bindBookingUseCases(bookingUseCase: BookingUseCaseImpl): BookingUseCase

}

@Module
@InstallIn(ViewModelComponent::class)
class BookingContextModule {
    @ViewModelScoped
    @Provides
    fun provideBookingRepository(
        @ApplicationContext appContext: Context
    ): BookingRepository =
        BookingRepositoryImpl(context = appContext)
}
