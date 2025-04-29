package com.example.focalpoint.presentation.ui.components

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandlerCard(
    breakTime: Long
){
    var showBottomSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .wrapContentHeight(align = Alignment.CenterVertically),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            if (showBottomSheet){
                ModalBottomSheet(
                    modifier = Modifier
                        .fillMaxHeight(0.5f),
                    sheetState = sheetState,
                    onDismissRequest = {showBottomSheet = false}
                ) { }
            }
        }
    }
}


@Composable
fun LengthButton(text: String, time: Int){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .wrapContentHeight(align = Alignment.CenterVertically),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Serif
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HandlerCardPreview(){
    HandlerCard(300)
}