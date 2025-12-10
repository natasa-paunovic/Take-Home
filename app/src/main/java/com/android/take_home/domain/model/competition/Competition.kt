package com.android.take_home.domain.model.competition

import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    val id: Int,
    val sportId: Int,
    val name: String,
    val competitionIconUrl: String
)
