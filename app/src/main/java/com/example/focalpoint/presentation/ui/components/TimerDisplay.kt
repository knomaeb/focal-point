package com.example.focalpoint.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerDisplay(
    remTime : Int
){
    // Format seconds to mm:ss
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return "%02d:%02d".format(minutes, secs)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .wrapContentHeight(align = Alignment.CenterVertically),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = formatTime(remTime),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ActionButton("Start", onClick = {})
                ActionButton("Reset", onClick = {})
            }
        }
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick,
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraLight,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun TimerDisplayPreview(){
    TimerDisplay(remTime = 1500)
}
