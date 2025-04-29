package com.example.focalpoint.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focalpoint.presentation.ui.components.DurationControl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PomodoroTimerScreen(viewModel: PomodoroViewModel = viewModel()) {
    val workDuration by viewModel.workDuration
    val breakDuration by viewModel.breakDuration
    val isRunning by viewModel.isRunning
    val isWorkMode by viewModel.isWorkMode
    val remainingSeconds by viewModel.remainingSeconds
    val coroutineScope = rememberCoroutineScope()

    // Timer logic
    LaunchedEffect(isRunning) {
        if (isRunning) {
            coroutineScope.launch {
                while (isRunning) {
                    delay(1000L)
                    viewModel.updateTimer()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Timer display
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 56.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .background(
                        if (isWorkMode) Color(0xFFE57373) else Color(0xFF81C784)
                    )
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isWorkMode) "Work Time" else "Break Time",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${remainingSeconds / 60}:${String.format("%02d", remainingSeconds % 60)}",
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Start/Pause button
            Button(
                onClick = { viewModel.toggleTimer() },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) Color(0xFFFF7043) else Color(0xFF66BB6A)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Default.Menu else Icons.Default.PlayArrow,
                        contentDescription = if (isRunning) "Pause" else "Start"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = if (isRunning) "Pause" else "Start")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { viewModel.resetTimer() }) {
                Text("Reset")
            }
        }


        // Duration control section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Work duration control
            DurationControl(
                title = "Work Duration",
                value = workDuration,
                onIncrease = { viewModel.increaseWorkDuration() },
                onDecrease = { viewModel.decreaseWorkDuration() },
                isEnabled = !isRunning
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Break duration control
            DurationControl(
                title = "Break Duration",
                value = breakDuration,
                onIncrease = { viewModel.increaseBreakDuration() },
                onDecrease = { viewModel.decreaseBreakDuration() },
                isEnabled = !isRunning
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_3a")
@Composable
fun PomodoroTimerScreenPreview1() {
    PomodoroTimerScreen()
}

