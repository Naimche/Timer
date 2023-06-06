package com.naim.timer.screens.game.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable
import javax.inject.Inject

class GameSettingsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
        var page by savedStateHandle
            .saveable { mutableStateOf(0) }
}