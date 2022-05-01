package com.example.footballfixtures.data.di

import android.app.Application
import androidx.room.Room
import com.example.footballfixtures.data.FootballAPI
import com.example.footballfixtures.data.local.FootballFixturesDao
import com.example.footballfixtures.data.local.FootballFixturesDatabase
import com.example.footballfixtures.data.repository.FootballFixturesRepositoryImpl
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideTextBooksAndJournalsApi(client: OkHttpClient): FootballAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(FootballAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideTextbooksAndJournalsDatabase(app: Application): FootballFixturesDatabase {
        return Room.databaseBuilder(
            app,
            FootballFixturesDatabase::class.java,
            "football_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: FootballFixturesDao, api: FootballAPI): FootballFixturesRepository {
        return FootballFixturesRepositoryImpl(dao, api)
    }
}