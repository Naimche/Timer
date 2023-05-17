package com.naim.timer.screens.game.utils

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CircularTimerViewModel @Inject constructor(
    private val circularTimerManager: CircularTimerManager, saveableStateHandle: SavedStateHandle
) : ViewModel() {
    private var countDownTimer: CountDownTimer? = null
    val currentPhase = circularTimerManager.currentPhase
    val countDownInterval by saveableStateHandle.saveable { mutableStateOf(1000L) } // 1 second
    var initialTotalTime by saveableStateHandle.saveable { mutableStateOf(30000L) } // 30 seconds
    val timeleft = mutableStateOf(initialTotalTime)
    val isPlaying = mutableStateOf(false)

    val progress: Float
        get() = (initialTotalTime - timeleft.value).toFloat() / initialTotalTime.toFloat()

    fun startTimer() = viewModelScope.launch {
        isPlaying.value = true
        if (timeleft.value == initialTotalTime) {
            // Reset the timer only if it hasn't been started yet
            timeleft.value = initialTotalTime
        }
        countDownTimer = object : CountDownTimer(timeleft.value, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("Timer", timeleft.value.toString())
                timeleft.value = millisUntilFinished
            }

            override fun onFinish() {
                isPlaying.value = false
                timeleft.value = 0
            }
        }.start()
    }
}


