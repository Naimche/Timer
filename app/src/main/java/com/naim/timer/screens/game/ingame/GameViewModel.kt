package com.naim.timer.screens.game.ingame

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.model.DataWords
import com.naim.timer.screens.game.GameLobbyViewModel
import com.naim.timer.screens.game.TimerSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class GameViewModel @Inject constructor(
    savableStateHandle: SavedStateHandle,
    private val gameLobbyViewModel: GameLobbyViewModel
) : ViewModel() {
    private var countDownTimer: CountDownTimer? = null
    val countDownInterval by savableStateHandle.saveable { mutableStateOf(1000L) } // 1 second
    var initialTotalTime by savableStateHandle.saveable { mutableStateOf(TimerSettings.countDownInterval) } // 30 seconds
    var timeleft by savableStateHandle.saveable { mutableStateOf(initialTotalTime) }
    var start by savableStateHandle.saveable { mutableStateOf(true) }

    val isPlaying = mutableStateOf(false)


    val progress: Float
        get() = (initialTotalTime - timeleft).toFloat() / initialTotalTime.toFloat()

    fun startTimer() = viewModelScope.launch {
        Log.i("Timer", timeleft.toString())
        isPlaying.value = true

        countDownTimer = object : CountDownTimer(timeleft, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("Timer", timeleft.toString())
                Log.i("Timer", (timeleft / 1000L).toString())
                timeleft = millisUntilFinished
            }

            override fun onFinish() {
                isPlaying.value = false
                timeleft = 0
            }
        }.start()
    }


    fun dataWordsByDB() {
        val list = mutableListOf("")
        TimerSettings.allCategory.values.forEach { words -> words.words.forEach { list.add(it) } }
        Log.i("GameTimer", list.toString())

    }

}


