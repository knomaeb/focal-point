package com.example.focalpoint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PomodoroTimerScreen() {
    // State variables
    var selectedTime by remember { mutableStateOf(25 * 60) } // default to 25 minutes
    var remainingTime by remember { mutableStateOf(selectedTime) }
    var isRunning by remember { mutableStateOf(false) }
    var customTimeInput by remember { mutableStateOf("") }

    // Format seconds to mm:ss
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return "%02d:%02d".format(minutes, secs)
    }

    // Timer effect: update remainingTime every second if running.
    if (isRunning) {
        LaunchedEffect(key1 = remainingTime) {
            // Simple timer loop: runs until remainingTime is 0 or isRunning is false
            while (remainingTime > 0 && isRunning) {
                delay(1000L)
                remainingTime--
            }
            // When timer finishes, stop running
            if (remainingTime == 0) {
                isRunning = false
                // Optionally, trigger a notification or sound here.
            }
        }
    }

    // Build the UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Preset Buttons Row
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                selectedTime = 25 * 60
                remainingTime = selectedTime
            }) {
                Text("25 min")
            }
            Button(onClick = {
                selectedTime = 45 * 60
                remainingTime = selectedTime
            }) {
                Text("45 min")
            }
            Button(onClick = {
                selectedTime = 15 * 60
                remainingTime = selectedTime
            }) {
                Text("15 min")
            }
        }

        // Custom Timer Input
        OutlinedTextField(
            value = customTimeInput,
            onValueChange = { customTimeInput = it },
            label = { Text("Custom Minutes") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            // Convert input to integer (in minutes) and update timer state
            customTimeInput.toIntOrNull()?.let { minutes ->
                selectedTime = minutes * 60
                remainingTime = selectedTime
            }
        }) {
            Text("Set Custom Timer")
        }

        // Display the countdown timer
        Text(
            text = formatTime(remainingTime),
            style = MaterialTheme.typography.headlineMedium
        )

        // Start/Pause Button
        Button(onClick = { isRunning = !isRunning }) {
            Text(if (isRunning) "Pause" else "Start")
        }

        // Reset Button
        Button(onClick = {
            isRunning = false
            remainingTime = selectedTime
        }) {
            Text("Reset")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PomodoroTimerScreenPreview() {
    PomodoroTimerScreen()
}

