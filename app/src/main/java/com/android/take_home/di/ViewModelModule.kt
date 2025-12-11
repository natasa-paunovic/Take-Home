package com.android.take_home.di

import com.android.take_home.presentation.MatchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MatchesViewModel(
            matchesRepository = get(),
            competitionsRepository = get(),
            sportsRepository = get()
        )
    }
}