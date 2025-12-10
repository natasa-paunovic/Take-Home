package com.android.take_home.di

import com.android.take_home.data.local.FileCacheManager
import com.android.take_home.data.repository.CompetitionsRepositoryImpl
import com.android.take_home.data.repository.MatchesRepositoryImpl
import com.android.take_home.data.repository.SportsRepositoryImpl
import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.model.sport.Sport
import com.android.take_home.domain.repository.CompetitionsRepository
import com.android.take_home.domain.repository.MatchesRepository
import com.android.take_home.domain.repository.SportsRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

val repositoryModule = module {

    single<MatchesRepository> {
        MatchesRepositoryImpl(
            api = get(),
            cache = FileCacheManager(
                file = File(androidContext().filesDir, "matches.json"),
                serializer = ListSerializer(Match.serializer())
            )
        )
    }

    single<CompetitionsRepository> {
        CompetitionsRepositoryImpl(
            api = get(),
            cache = FileCacheManager(
                file = File(androidContext().filesDir, "competitions.json"),
                serializer = ListSerializer(Competition.serializer())
            )
        )
    }

    single<SportsRepository> {
        SportsRepositoryImpl(
            api = get(),
            cache = FileCacheManager(
                file = File(androidContext().filesDir, "sports.json"),
                serializer = ListSerializer(Sport.serializer())
            )
        )
    }
}