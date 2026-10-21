package com.bunjay.stocksdemo.common.di

import com.bunjay.stocksdemo.common.api.TwseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://openapi.twse.com.tw/v1/"
private const val TIMEOUT_SECONDS = 30L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =OkHttpClient.Builder().connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()

    @Provides
    @Singleton
    fun provideTwseApi(retrofit: Retrofit): TwseApi = retrofit.create(TwseApi::class.java)
}