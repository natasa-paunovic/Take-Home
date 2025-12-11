package com.android.take_home.presentation.ui.sports

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import com.android.take_home.domain.model.sport.Sport
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SportsTabRow(
    sports: List<Sport>,
    selectedSportId: Int?,
    onSportSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sports) { sport ->
            SportTab(
                sport = sport,
                selected = sport.id == selectedSportId,
                onClick = { onSportSelected(sport.id) }
            )
        }
    }
}

