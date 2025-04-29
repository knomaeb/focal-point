package com.example.focalpoint.presentation.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PomodoroViewModel: ViewModel() {
    // Timer settings (in minutes)
    private val _workDuration = mutableStateOf(25)
    val workDuration: MutableState<Int> = _workDuration

    private val _breakDuration = mutableStateOf(5)
    val breakDuration: MutableState<Int> = _breakDuration

    // Timer state
    private val _isRunning = mutableStateOf(false)
    val isRunning: MutableState<Boolean> = _isRunning

    private val _isWorkMode = mutableStateOf(true)
    val isWorkMode: MutableState<Boolean> = _isWorkMode

    private val _remainingSeconds = mutableStateOf(workDuration.value * 60)
    val remainingSeconds: MutableState<Int> = _remainingSeconds

    fun increaseWorkDuration() {
        if (!isRunning.value) {
            _workDuration.value = (_workDuration.value + 1).coerceAtMost(60)
            if (_isWorkMode.value) {
                _remainingSeconds.value = _workDuration.value * 60
            }
        }
    }

    fun decreaseWorkDuration() {
        if (!isRunning.value) {
            _workDuration.value = (_workDuration.value - 1).coerceAtLeast(1)
            if (_isWorkMode.value) {
                _remainingSeconds.value = _workDuration.value * 60
            }
        }
    }

    fun increaseBreakDuration() {
        if (!isRunning.value) {
            _breakDuration.value = (_breakDuration.value + 1).coerceAtMost(30)
            if (!_isWorkMode.value) {
                _remainingSeconds.value = _breakDuration.value * 60
            }
        }
    }

    fun decreaseBreakDuration() {
        if (!isRunning.value) {
            _breakDuration.value = (_breakDuration.value - 1).coerceAtLeast(1)
            if (!_isWorkMode.value) {
                _remainingSeconds.value = _breakDuration.value * 60
            }
        }
    }

    fun toggleTimer() {
        _isRunning.value = !_isRunning.value
    }

    fun resetTimer() {
        _isRunning.value = false
        _isWorkMode.value = true
        _remainingSeconds.value = _workDuration.value * 60
    }

    fun updateTimer() {
        if (_remainingSeconds.value > 0) {
            _remainingSeconds.value -= 1
        } else {
            // Switch modes when timer reaches zero
            _isWorkMode.value = !_isWorkMode.value
            _remainingSeconds.value = if (_isWorkMode.value) {
                _workDuration.value * 60
            } else {
                _breakDuration.value * 60
            }
        }
    }



}