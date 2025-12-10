package com.android.take_home.domain.model.sport

import kotlinx.serialization.Serializable

@Serializable
data class Sport(
    val id: Int,
    val name: String,
    val sportIconUrl: String
)
