package com.naim.timer.screens.loginandreg

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.model.DBM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(SavedStateHandleSaveableApi::class)
class LoginScreenViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {


    var email by savedStateHandle.saveable { mutableStateOf("") }
    var password by savedStateHandle.saveable { mutableStateOf("") }
    var dialogShowState by savedStateHandle.saveable { mutableStateOf(false) }
    var alertError by savedStateHandle.saveable { mutableStateOf("") }
    var toast by savedStateHandle.saveable { mutableStateOf(false) }
    var numAttepmts by savedStateHandle.saveable { mutableStateOf(0) }
    var loading by savedStateHandle.saveable { mutableStateOf(false) }

    fun login(callback: (Boolean) -> Unit) {

        viewModelScope.launch {
            if (numAttepmts > 5) {
                loading = true
                delay(5000)
                loading = false
            }

            DBM.onLogin(email, password) {
                if (it == 0) {
                    callback(true)
                } else {
                    callback(false)
                    when (it) {
                        1 -> {
                            alertError = "No existe credenciales con ese email y contraseña"
                            dialogShowState = true
                        }
                        2 -> {
                            alertError = "El Email y la contraseña no pueden estar vacios"
                            dialogShowState = true
                        }
                        3 -> {
                            alertError =
                                "Parece que hay problemas de conexion, intentelo de nuevo mas tarde"
                            toast = true
                        }
                        4 -> {
                            alertError = "Demasiados intentos, intentelo mas tarde"
                            toast = true
                        }
                        9 -> {
                            alertError = "Error desconocido intentelo de nuevo mas tarde"
                        }
                    }

                }
            }

        }


    }
}