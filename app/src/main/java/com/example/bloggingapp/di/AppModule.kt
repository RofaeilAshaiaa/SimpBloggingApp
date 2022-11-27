package com.example.bloggingapp.di

import com.example.bloggingapp.data.source.remote.AuthorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL = "https://sym-json-server.herokuapp.com/"

    @Provides
    fun provideAuthorService(): AuthorService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthorService::class.java)
    }

}