package com.android.take_home.presentation.ui.match.live

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.take_home.R
import com.android.take_home.presentation.model.LiveMatchUI
import com.android.take_home.presentation.ui.components.SectionTitle

@Composable
fun LiveMatchesSection(matches: List<LiveMatchUI>) {
    Column(Modifier.padding(horizontal = 16.dp)) {

        SectionTitle(stringResource(R.string.live_matches))

        Spacer(Modifier.height(16.dp))

        matches.forEach { match ->
            LiveMatchCard(match)
            Spacer(Modifier.height(12.dp))
        }
    }
}