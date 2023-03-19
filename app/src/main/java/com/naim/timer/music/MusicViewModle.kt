package com.naim.timer.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor() : ViewModel() {

    private val _isMusicOn = MutableStateFlow(true)
    val isMusicOn = _isMusicOn.asStateFlow()

    fun toggleMusic() {
        _isMusicOn.value = !_isMusicOn.value
    }

    fun startMusic() {
        // Aquí se inicia la música
    }

    fun stopMusic() {
        // Aquí se detiene la música
    }

    init {
        viewModelScope.launch {
            if (isMusicOn.value) {
                startMusic()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            stopMusic()
        }
    }
}
