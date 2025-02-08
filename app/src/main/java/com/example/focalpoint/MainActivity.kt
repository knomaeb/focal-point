package com.example.focalpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.compose.FocalPointTheme
import com.example.focalpoint.ui.components.ColumnGrid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            FocalPointTheme {
//                PomodoroTimerScreen()
                    ColumnGrid()
            }
        }
    }
}
