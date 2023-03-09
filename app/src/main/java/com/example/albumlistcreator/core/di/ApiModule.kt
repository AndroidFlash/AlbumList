package com.example.albumlistcreator.core.di

import com.example.albumlistcreator.BuildConfig
import com.example.albumlistcreator.core.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi): ApiService {
        return Retrofit.Builder()
//            .client()
            .baseUrl(BuildConfig.API_BASE_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}