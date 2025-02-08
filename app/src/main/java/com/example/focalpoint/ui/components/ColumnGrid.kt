package com.example.focalpoint.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnGrid(){
    val list : List<String> = listOf("5", "15", "25", "45", "60", "+")

    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        content = {
            items(list.size) { index ->
                TimerButton(list[index], onClick = {})
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ColumnGridPreview(){
    ColumnGrid()
}