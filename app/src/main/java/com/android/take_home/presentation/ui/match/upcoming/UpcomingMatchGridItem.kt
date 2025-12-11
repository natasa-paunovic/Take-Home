package com.android.take_home.presentation.ui.match.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.android.take_home.presentation.mapper.toMatchTime
import com.android.take_home.presentation.mapper.toTab
import com.android.take_home.presentation.model.UpcomingMatchUI

@Composable
fun UpcomingMatchGridItem(match: UpcomingMatchUI) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1F22), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeamAvatar(match.homeTeam, match.homeTeamAvatar)
        }


        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(match.competitionIcon)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )


            Spacer(Modifier.height(6.dp))

            Text(
                text = match.competitionName,
                color = Color.LightGray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = match.time.toTab(),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))
            Text(
                text = match.time.toMatchTime(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TeamAvatar(match.awayTeam, match.awayTeamAvatar)
        }
    }
}