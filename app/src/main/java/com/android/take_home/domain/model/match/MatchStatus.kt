package com.android.take_home.domain.model.match

enum class MatchStatus {

    PRE_MATCH,
    LIVE,
    FINISHED;

    companion object {
        fun fromString(status: String): MatchStatus {
            return when (status.uppercase()) {
                "PRE_MATCH" -> PRE_MATCH
                "LIVE" -> LIVE
                "FINISHED" -> FINISHED
                else -> PRE_MATCH // default fallback
            }
        }
    }
}