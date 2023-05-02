package com.naim.timer.screens.loginandreg

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.model.DBM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@OptIn(SavedStateHandleSaveableApi::class)
class RegisterScreenViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {


    var email by savedStateHandle.saveable { mutableStateOf("") }
    var password by savedStateHandle.saveable { mutableStateOf("") }
    var password2 by savedStateHandle.saveable { mutableStateOf("") }

    fun register(callback: (Int) -> Unit) {
        if (password != password2) {
            callback(1)

        } else {
            DBM.onRegister(email, password) {
                if (it == 0) {
                    Log.i("RegisterScreenViewModel", "register: ")
                    callback(0)
                } else {
                    callback(2)
                }
            }
        }
    }


}