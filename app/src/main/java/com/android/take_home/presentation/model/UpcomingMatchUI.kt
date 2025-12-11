package com.android.take_home.presentation.model

data class UpcomingMatchUI(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamAvatar: String,
    val awayTeamAvatar: String,
    val time: String,
    val competitionName: String,
    val competitionIcon: String,
    val tab: String
)