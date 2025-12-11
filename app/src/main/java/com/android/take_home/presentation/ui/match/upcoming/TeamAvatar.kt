package com.android.take_home.presentation.ui.match.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun TeamAvatar(name: String, avatar: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatar)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = name,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF2A2F33)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = name,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}