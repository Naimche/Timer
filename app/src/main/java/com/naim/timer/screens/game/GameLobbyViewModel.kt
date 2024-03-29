package com.naim.timer.screens.game

import android.util.Log
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
    var dlcs by savedStateHandle.saveable { mutableStateOf(emptyMap<String, DataWords>()) }
    var expanded by savedStateHandle.saveable { mutableStateOf(false) }
    var expanded2 by savedStateHandle.saveable { mutableStateOf(false) }
    var expanded3 by savedStateHandle.saveable { mutableStateOf(false) }
    var expanded4 by savedStateHandle.saveable { mutableStateOf(false) }
    var categories by savedStateHandle.saveable { mutableStateOf(listOf<String>()) }
    var coins by savedStateHandle.saveable { mutableStateOf(0L) }
    var timer4 = listOf<String>("30", "25", "20", "15")
    var selectedCategory by savedStateHandle.saveable {
        mutableStateOf(
            categories.firstOrNull() ?: ""
        )
    }
    var selectedCategory2 by savedStateHandle.saveable {
        mutableStateOf(
            categories.firstOrNull() ?: ""
        )
    }
    var selectedCategory3 by savedStateHandle.saveable {
        mutableStateOf(
            categories.firstOrNull() ?: ""
        )
    }
    var selectedCategory4 by savedStateHandle.saveable {
        mutableStateOf(
            timer4.firstOrNull() ?: ""
        )
    }
    var showDialogLougout by savedStateHandle.saveable { mutableStateOf(false) }
    var teamName1 by savedStateHandle.saveable { mutableStateOf("") }
    var teamName2 by savedStateHandle.saveable { mutableStateOf("") }

    var ignoreCategories by savedStateHandle.saveable { mutableStateOf(false) }

    var showDialog by savedStateHandle.saveable { mutableStateOf(false) }

    var dlcAcomprar by savedStateHandle.saveable { mutableStateOf(emptyMap<String, Long>()) }
    var loading by savedStateHandle.saveable { mutableStateOf(true) }
    var shopLoading by savedStateHandle.saveable { mutableStateOf(true) }
    fun updateDlcList() {
        viewModelScope.launch {
            DBM.getAllDlcs().collect {
                dlcs = it
                Log.i("DLC", "updateDlcList: $it")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            DBM.logout()
        }
    }

    fun updateCategories() {

        val list = mutableListOf<String>()
        val allDatawords = mutableMapOf<String, DataWords>()
        viewModelScope.launch {
            Log.i("CATEGORIES", "Launch")
            DBM.getAllCategoriesWthAccess().collect {
                it.forEach { mapDatawords ->
                    list.add(mapDatawords.key)
                    allDatawords[mapDatawords.key] = mapDatawords.value
                }
                categories = list
                TimerSettings.allCategory = allDatawords

            }

        }
    }

    fun buyDlc(idDlc: Map<String, Long>, callback: (Int) -> Unit) {
        viewModelScope.launch {
            DBM.buyDlc(idDlc) {
                callback(it)
            }
        }
    }

    fun getCoins() {
        viewModelScope.launch {
            DBM.getCoins {
                coins = it
            }
        }
    }


}