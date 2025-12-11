package com.android.take_home.presentation.ui.match.live

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun TeamScoreRow(name: String, avatar: String, score: Int) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatar)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            name,
            modifier = Modifier.weight(1f),
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            score.toString(),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}