package com.naim.timer.screens.loginandreg

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
class LoginScreenViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel(){



    var email by savedStateHandle.saveable { mutableStateOf("") }
    var password by savedStateHandle.saveable { mutableStateOf("") }
    var dialogShowState by savedStateHandle.saveable { mutableStateOf(false) }
    var alertError by savedStateHandle.saveable { mutableStateOf("") }

    fun login(callback: (Boolean) -> Unit) {
        DBM.onLogin(email, password){
            if(it ==0 ){
                callback(true)
            }
            else{
                callback(false)
                when(it){
                    1 -> alertError = "No existe credenciales con ese email y contraseña"
                    2 -> alertError = "El Email y la contraseña no pueden estar vacios"
                    3 -> alertError = "Error desconocido intentelo de nuevo mas tarde"
                }
                dialogShowState = true
            }
        }
    }
}