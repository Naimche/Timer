package com.naim.timer.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.naim.timer.music.MusicViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class GameLobbyViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val musicViewModel: MusicViewModel,) : ViewModel() {
    private val _isMusicOn = MutableStateFlow(true)
    val isMusicOn = _isMusicOn.asStateFlow()

    init {
        viewModelScope.launch {
            // Inicia la música si la variable _isMusicOn es true
            if (_isMusicOn.value) {
                musicViewModel.startMusic()
            }
        }
    }

    fun toggleMusic() {
        _isMusicOn.value = !_isMusicOn.value
        viewModelScope.launch {
            // Detiene o inicia la música según el valor de la variable _isMusicOn
            if (_isMusicOn.value) {
                musicViewModel.startMusic()
            } else {
                musicViewModel.stopMusic()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            musicViewModel.stopMusic()
        }
    }
}