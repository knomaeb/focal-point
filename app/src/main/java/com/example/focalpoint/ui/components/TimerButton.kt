package com.example.focalpoint.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerButton(
    minutes :String,
    onClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .padding(7.dp)
            .height(250.dp)
            .width(250.dp)
            .background(MaterialTheme.colorScheme.onSecondaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondaryContainer),
            onClick = {}
        ) {
            Text(
                text = "$minutes Min",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TimerButtonPreview(){
    TimerButton(minutes = "25", onClick = {})
}