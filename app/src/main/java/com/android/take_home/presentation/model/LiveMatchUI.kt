package com.android.take_home.presentation.model

data class LiveMatchUI(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamAvatar: String,
    val awayTeamAvatar: String,
    val homeScore: Int,
    val awayScore: Int,
    val currentTime: String,
    val competitionName: String,
    val competitionIcon: String
)