package com.android.take_home.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PrematchTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (selected) Color(0xFFFFC82C) else Color(0xFF22282B),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            color = if (selected) Color.Black else Color(0xFF8E9499),
            fontWeight = FontWeight.Bold
        )
    }
}
