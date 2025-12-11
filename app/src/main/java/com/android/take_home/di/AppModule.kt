package com.android.take_home.di

import com.android.take_home.data.remote.networkModule

val appModules = listOf(
    networkModule,
    repositoryModule,
    viewModelModule
)