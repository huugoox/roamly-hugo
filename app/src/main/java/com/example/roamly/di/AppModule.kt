package com.example.roamly.di


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.roamly.BuildConfig
import com.example.roamly.data.local.AppDatabase
import com.example.roamly.data.local.dao.ItineraryDao
import com.example.roamly.data.local.dao.TripDao
import com.example.roamly.data.local.dao.UsersDao
import com.example.roamly.domain.repository.ItineraryRepository
import com.example.roamly.data.repository.ItineraryRepositoryImpl
import com.example.roamly.domain.repository.TripRepository
import com.example.roamly.data.repository.TripRepositoryImpl
import com.example.roamly.data.repository.UserRepositoryImpl
import com.example.roamly.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.example.roamly.ui.viewmodel.RegisterViewModel
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
        @ApplicationContext context: Context,
        userRepository: UserRepository,
        usersDao: UsersDao
    ): RegisterViewModel = RegisterViewModel(context, userRepository, usersDao)

    @Provides
    @Singleton
    fun provideTripRepository(tripDao: TripDao, itineraryDao: ItineraryDao): TripRepository {
        return TripRepositoryImpl(tripDao, itineraryDao)
    }

    @Provides
    @Singleton
    fun provideItineraryRepository(itineraryDao: ItineraryDao): ItineraryRepository {
        return ItineraryRepositoryImpl(itineraryDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(usersDao: UsersDao): UserRepository {
        return UserRepositoryImpl(usersDao)
    }

    @Provides
    fun provideTripDao(db: AppDatabase): TripDao {
        return db.tripDao()
    }

    @Provides
    fun provideItineraryDao(db: AppDatabase): ItineraryDao {
        return db.itineraryDao()
    }

    @Provides
    fun provideUsersDao(db: AppDatabase): UsersDao {
        return db.usersDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "roamly_database"
        )//.fallbackToDestructiveMigration().build()
            .build()
    }
}
