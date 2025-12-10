package com.android.take_home.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://take-home-api-7m87.onrender.com/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RetrofitApiService> {
        get<Retrofit>().create(RetrofitApiService::class.java)
    }
}