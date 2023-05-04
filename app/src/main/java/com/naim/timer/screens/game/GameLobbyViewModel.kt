package com.naim.timer.screens.game

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import javax.inject.Inject
@OptIn(SavedStateHandleSaveableApi::class)

class GameLobbyViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var isMenuExtended by savedStateHandle.saveable { mutableStateOf(false) }
    var whIsMenu by savedStateHandle.saveable { mutableStateOf(0) }
}