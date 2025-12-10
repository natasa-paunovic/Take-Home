package com.android.take_home.domain.model.match

import kotlinx.serialization.Serializable

@Serializable
data class MatchResult(
    val homeScore: Int,
    val awayScore: Int
)
