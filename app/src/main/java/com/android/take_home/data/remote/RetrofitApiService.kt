package com.android.take_home.data.remote

import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.model.sport.Sport
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApiService {

    @GET("matches")
    suspend fun getMatches(): List<Match>

    @GET("competitions")
    suspend fun getCompetitions(): List<Competition>

    @GET("sports")
    suspend fun getSports(): Response<List<Sport>>
}