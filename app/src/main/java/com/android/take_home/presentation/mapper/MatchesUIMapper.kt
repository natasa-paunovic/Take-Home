package com.android.take_home.presentation.mapper

import com.android.take_home.domain.model.competition.Competition
import com.android.take_home.domain.model.match.Match
import com.android.take_home.domain.model.match.MatchStatus
import com.android.take_home.presentation.model.LiveMatchUI
import com.android.take_home.presentation.model.UpcomingMatchUI

fun Match.toLiveMatchUI(competitionMap: Map<Int, Competition>): LiveMatchUI? {
    if (matchStatus != MatchStatus.LIVE) return null
    val competition = competitionMap[competitionId]
    return LiveMatchUI(
        id = id,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamAvatar = homeTeamAvatar,
        awayTeamAvatar = awayTeamAvatar,
        homeScore = result?.home ?: 0,
        awayScore = result?.away ?: 0,
        currentTime = currentTime ?: "",
        competitionName = competition?.name ?: "",
        competitionIcon = competition?.competitionIconUrl ?: ""
    )
}

fun Match.toUpcomingMatchUI(competitionMap: Map<Int, Competition>): UpcomingMatchUI? {
    if (matchStatus != MatchStatus.PRE_MATCH) return null
    val competition = competitionMap[competitionId]

    val tab = date.toTab()

    return UpcomingMatchUI(
        id = id,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeTeamAvatar = homeTeamAvatar,
        awayTeamAvatar = awayTeamAvatar,
        time = date,
        competitionName = competition?.name ?: "",
        competitionIcon = competition?.competitionIconUrl ?: "",
        tab = tab
    )
}