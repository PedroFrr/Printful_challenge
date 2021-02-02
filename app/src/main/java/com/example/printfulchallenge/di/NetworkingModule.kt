package com.example.printfulchallenge.di

import com.example.printfulchallenge.networking.CatApiClient
import com.example.printfulchallenge.networking.CatApiService
import com.example.printfulchallenge.utils.BASE_URL
import com.example.printfulchallenge.utils.HEADER_AUTHORIZATION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Class that provides Retrofit services for remote integration.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    /**
     * Adds an authorization token to every request made to the API
     */
    @Provides
    fun provideAuthorizationInterceptor() =
        object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()

                val new = originalRequest.newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, "333") //TODO change auth token
                    .build()

                return chain.proceed(new)
            }
        }

    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    @Provides
    @Singleton
    fun provideCatApiService(retrofit: Retrofit): CatApiService =
        retrofit.create(CatApiService::class.java)

    @Provides
    @Singleton
    fun provideCatApiClient(catApiService: CatApiService): CatApiClient =
        CatApiClient(catApiService)
}