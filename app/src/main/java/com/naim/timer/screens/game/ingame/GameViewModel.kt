package com.naim.timer.screens.game.ingame

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.model.DBM
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
    var scoreTeam1 by savableStateHandle.saveable { mutableStateOf(0) }
    var scoreTeam2 by savableStateHandle.saveable { mutableStateOf(0) }
    private var countDownTimer: CountDownTimer? = null
    val countDownInterval by savableStateHandle.saveable { mutableStateOf(1000L) } // 1 second
    val initialTotalTime = TimerSettings.countDownInterval// 30 seconds
    var timeleft by savableStateHandle.saveable { mutableStateOf(TimerSettings.countDownInterval) }
    var start by savableStateHandle.saveable { mutableStateOf(false) }
    var listWords by savableStateHandle.saveable { mutableStateOf(mutableListOf<String>()) }
    val isPlaying = mutableStateOf(false)
    var wordIndex by savableStateHandle.saveable { mutableStateOf(0) }
    var turn by savableStateHandle.saveable { mutableStateOf(true) }
    var tempWordList by savableStateHandle.saveable { mutableStateOf(mutableListOf<String>()) }
    var round by savableStateHandle.saveable { mutableStateOf(1) }
    var refresh by savableStateHandle.saveable { mutableStateOf(true) }
    var isFirstLaunch by savableStateHandle.saveable { mutableStateOf(true) }
    val progress: Float
        get() = (initialTotalTime - timeleft).toFloat() / initialTotalTime.toFloat()

    fun startTimer() = viewModelScope.launch {
        if (!isPlaying.value) {
            // Verificar si el temporizador ya está en ejecución
            if (countDownTimer == null) {
                countDownTimer = object : CountDownTimer(timeleft, countDownInterval) {
                    override fun onTick(millisUntilFinished: Long) {
                        timeleft = millisUntilFinished
                        Log.i("Tiempo", "Corriendo")
                    }

                    override fun onFinish() {
                        isPlaying.value = true
                        timeleft = 0
                        start = false
                        Log.i("Tiempo", "Termino")
                    }
                }.start()
            }
        } else {
            Log.i("Tiempo", "Cancelado")

            countDownTimer?.cancel()
            countDownTimer = null // Restablecer el temporizador a null
            timeleft = TimerSettings.countDownInterval
            isPlaying.value = false
        }
    }


    fun dataWordsByDB() {
        val list = mutableListOf<String>()
        Log.i("ignorar", TimerSettings.ignoreCategory.toString())
        if (TimerSettings.selectedCategory.isNullOrBlank() && TimerSettings.selectedCategory2.isNullOrBlank() && TimerSettings.selectedCategory3.isNullOrBlank()) {
            TimerSettings.ignoreCategory = true
        }

        if (TimerSettings.ignoreCategory ) {
            TimerSettings.allCategory.values.forEach { dataWords ->
                dataWords.words.forEach {
                    if (!list.contains(it)) {
                        list.add(it)
                    }
                }
            }
        } else {
            TimerSettings.allCategory[TimerSettings.selectedCategory]?.words?.forEach {
                if (!list.contains(it)) {
                    list.add(it)
                }
            }

            TimerSettings.allCategory[TimerSettings.selectedCategory2]?.words?.forEach {
                if (!list.contains(it)) {
                    list.add(it)
                }
            }

            TimerSettings.allCategory[TimerSettings.selectedCategory3]?.words?.forEach {
                if (!list.contains(it)) {
                    list.add(it)
                }
            }
        }

        list.shuffle()
        listWords.addAll(list.take(30))
        Log.i("Lista", listWords.size.toString())
    }


    fun nextRound() {
        round++
        wordIndex = 0
        tempWordList.shuffle()
        start = false
        timeleft = initialTotalTime

        startTimer()
    }

    fun finishTimerImmediately() {
        countDownTimer?.cancel()
        countDownTimer = null
        timeleft = 0
        start = false
        isPlaying.value = true
        turn = !turn
        Log.i("Tiempo", "Terminado inmediatamente")
    }

    fun win10Coins() {
        viewModelScope.launch {
            DBM.win10Coins()
        }
    }
}


