package com.android.take_home.presentation.ui.match.live

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import com.android.take_home.presentation.model.LiveMatchUI

@Composable
fun LiveMatchCard(match: LiveMatchUI) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF1A1F22)
    ) {
        Column(Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = match.competitionIcon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    match.competitionName,
                    color = Color(0xFF9AA0A6),
                    fontSize = 13.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    match.currentTime,
                    color = Color(0xFF00D6C9),
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(14.dp))

            TeamScoreRow(
                match.homeTeam,
                match.homeTeamAvatar,
                match.homeScore
            )

            Spacer(Modifier.height(10.dp))

            TeamScoreRow(
                match.awayTeam,
                match.awayTeamAvatar,
                match.awayScore
            )
        }
    }
}