package com.example.ktor_example.data.di

import com.example.ktor_example.data.network.MovieHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun getHttpClient(movieHttpClient: MovieHttpClient):HttpClient = movieHttpClient.getHttpClient()


}