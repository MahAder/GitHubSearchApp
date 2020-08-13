package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {
    val BASE_URL: String = BuildConfig.HOST

    @Provides
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit =
        builder.baseUrl(BASE_URL).build()

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .client(client)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    fun getLoggingInterceptor() : HttpLoggingInterceptor {
        val intereptor = HttpLoggingInterceptor()
        intereptor.level = HttpLoggingInterceptor.Level.BODY
        return intereptor
    }
}