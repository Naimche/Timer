package com.naim.timer.screens.game.utils

import android.os.CountDownTimer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


enum class TimerState {
    Stopped, Paused, Running
}

@Singleton
class CircularTimerManager @Inject constructor() {

    private val currentPhaseFlow = MutableStateFlow(TimerState.Stopped)
    val currentPhase: Flow<TimerState> = currentPhaseFlow

    private var countDownTimer: CountDownTimer? = null

    private var timeLeftInMillis = 30000L

    fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }.start()
    }
}