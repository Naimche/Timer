package com.naim.timer.screens.game

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.model.DBM
import com.naim.timer.model.DataWords
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)

class GameLobbyViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var isMenuExtended by savedStateHandle.saveable { mutableStateOf(false) }
    var whIsMenu by savedStateHandle.saveable { mutableStateOf(0) }
    var Dlcs by savedStateHandle.saveable { mutableStateOf(emptyList<DataWords>()) }
    var expanded by savedStateHandle.saveable{ mutableStateOf(false) }
    var expanded2 by savedStateHandle.saveable{ mutableStateOf(false) }
    var expanded3 by savedStateHandle.saveable{ mutableStateOf(false) }
    var expanded4 by savedStateHandle.saveable{ mutableStateOf(false) }
    var categories = listOf<String>("Category 1", "Category 2", "Category 3")
    var categories2 = listOf<String>("Category 1", "Category 2", "Category 3")
    var categories3 = listOf<String>("Category 1", "Category 2", "Category 3")
    var timer4 = listOf<String>("30", "25", "20","15")
    var countSeconds by savedStateHandle.saveable { mutableStateOf(30) }
    var isCountingDown by savedStateHandle.saveable { mutableStateOf(false) }
    var selectedCategory by savedStateHandle.saveable { mutableStateOf(categories.firstOrNull()?: "") }
    var selectedCategory2 by savedStateHandle.saveable { mutableStateOf(categories.firstOrNull()?: "") }
    var selectedCategory3 by savedStateHandle.saveable { mutableStateOf(categories.firstOrNull()?: "") }
    var selectedCategory4 by savedStateHandle.saveable { mutableStateOf(timer4.firstOrNull()?: "") }


    fun updateDlcList() {
        viewModelScope.launch {
            DBM.getAllDlcs().collect() {
                Dlcs = it
            }
        }
    }


}