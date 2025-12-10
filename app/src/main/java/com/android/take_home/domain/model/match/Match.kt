package com.android.take_home.domain.model.match

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamAvatar: String,
    val awayTeamAvatar: String,
    val date: String,
    val status: String, // "PRE_MATCH", "LIVE", "FINISHED"
    val currentTime: String?,
    val result: MatchResult?,
    val sportId: Int,
    val competitionId: Int
)
