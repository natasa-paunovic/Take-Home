package com.android.take_home.presentation.mapper

import com.android.take_home.presentation.MatchesViewModel
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val TAB_TODAY = "Danas"
val TAB_TOMORROW = "Sutra"
val TAB_WEEKEND = "Vikend"
val TAB_NEXT_7_DAYS = "Sledećih 7 dana"

fun String.toTab(): String {

    val timestamp = this.toLongOrNull()
    val matchDate: LocalDate = if (timestamp != null) {
        Instant.ofEpochSecond(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } else {
        try {
            LocalDate.parse(this.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        } catch (e: DateTimeParseException) {
            LocalDate.parse(this.substring(0, 10), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }

    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)

    return when {
        matchDate.isEqual(today) -> TAB_TODAY
        matchDate.isEqual(tomorrow) -> TAB_TOMORROW
        matchDate.dayOfWeek == DayOfWeek.SATURDAY || matchDate.dayOfWeek == DayOfWeek.SUNDAY -> TAB_WEEKEND
        matchDate.isAfter(today) && matchDate.isBefore(today.plusDays(8)) -> TAB_NEXT_7_DAYS
        else -> ""
    }

}

fun String.toMatchTime(): String {
    // 1. Ako je timestamp
    val timestamp = this.toLongOrNull()
    if (timestamp != null) {
        val time = Instant.ofEpochSecond(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
        return time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    if (this.length > 10) {
        val timePart = this.substring(10).trim()   // uzimamo deo posle datuma

        val possibleFormats = listOf(
            "HH:mm",
            "HH:mm:ss",
            "'T'HH:mm:ss",
            " HH:mm",
            " HH:mm:ss",
            "'T'HH:mm",
            "'T'HH:mm:ss"
        )

        for (format in possibleFormats) {
            try {
                val parsed = LocalTime.parse(timePart, DateTimeFormatter.ofPattern(format))
                return parsed.format(DateTimeFormatter.ofPattern("HH:mm"))
            } catch (_: Exception) { }
        }
    }

    // ako ništa ne uspe, vrati prazno
    return ""
}