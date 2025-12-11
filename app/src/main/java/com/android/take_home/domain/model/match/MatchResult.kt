package com.android.take_home.domain.model.match

import kotlinx.serialization.Serializable

@Serializable
data class MatchResult(
    val home: Int,
    val away: Int
)
