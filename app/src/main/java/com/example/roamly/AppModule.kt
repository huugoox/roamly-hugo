package com.example.roamly


import android.content.Context
import android.content.SharedPreferences
import com.example.roamly.domain.repository.ItineraryRepository
import com.example.roamly.domain.repository.ItineraryRepositoryImpl
import com.example.roamly.domain.repository.TripRepository
import com.example.roamly.domain.repository.TripRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.example.roamly.ui.viewmodel.RegisterViewModel
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("${BuildConfig.APPLICATION_ID}_preferences", Context.MODE_PRIVATE)

    // context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) //bad implementation

    @Provides
    @Singleton
    fun provideFormValidationViewModel(
        @ApplicationContext context: Context
    ): RegisterViewModel = RegisterViewModel(context)

    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository {
        return TripRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideItineraryRepository(): ItineraryRepository {
        return ItineraryRepositoryImpl()
    }
}
