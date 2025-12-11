package com.android.take_home.presentation.ui.match.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.android.take_home.R
import com.android.take_home.presentation.mapper.TAB_NEXT_7_DAYS
import com.android.take_home.presentation.mapper.TAB_TODAY
import com.android.take_home.presentation.mapper.TAB_TOMORROW
import com.android.take_home.presentation.mapper.TAB_WEEKEND
import com.android.take_home.presentation.mapper.toTab
import com.android.take_home.presentation.model.UpcomingMatchUI
import com.android.take_home.presentation.ui.components.PrematchTab
import com.android.take_home.presentation.ui.components.SectionTitle

@Composable
fun UpcomingMatchesSection(
    matches: List<UpcomingMatchUI>,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionTitle(stringResource(R.string.prematch))

        Spacer(Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(listOf(TAB_TODAY, TAB_TOMORROW, TAB_WEEKEND, TAB_NEXT_7_DAYS)) { tab ->
                PrematchTab(
                    text = tab,
                    selected = tab == selectedTab,
                    onClick = { onTabSelected(tab) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (matches.isNotEmpty()) {
                matches.forEach { match ->
                    UpcomingMatchGridItem(match)
                    Spacer(Modifier.height(16.dp))
                }
            } else {
                //  empty state message
                val emptyMessage = stringResource(R.string.no_matches_available)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = emptyMessage,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

