package com.naim.timer.screens.loginandreg

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.navigation.AppScreens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        RegisterBodyContent(navController = navController)
    }

}

@Composable
fun RegisterBodyContent(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    var infoDialogVisible by remember { mutableStateOf(false) }

    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passwordsMatch by remember { mutableStateOf(false) }
    var emailMatch by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFF00cecb)
    ) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .wrapContentHeight(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Logo()
            Spacer(modifier = Modifier.height(60.dp))
            EmailField(onChange = { viewModel.email = it })
            Spacer(Modifier.height(6.dp))
            PasswordField { viewModel.password = it }
            Spacer(modifier = Modifier.height(6.dp))
            PasswordField { viewModel.password2 = it }
            Spacer(modifier = Modifier.height(6.dp))
            passwordsMatch =
                viewModel.password == viewModel.password2 && viewModel.password.length > 8
            emailMatch = viewModel.email.contains("@") && viewModel.email.contains(".")
            ButtonConfirmation(
                enabledPassword = passwordsMatch,
                enabledEmail = emailMatch,
                functionClick = { viewModel.register{
                    if (it == 0) {
                        Log.i("Register", "Register Success")
                        navController.navigate(AppScreens.GameLobby.route)
                        navController.popBackStack()
                    }
                } })


        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonConfirmation(enabledPassword: Boolean, enabledEmail: Boolean, functionClick: () -> Unit) {
    val dialogShownState = remember { mutableStateOf(false) }
    val color = if (enabledPassword && enabledEmail) Color(0xFFd88870) else Color.Gray.copy(0.1f)
    val elevation = if (!enabledPassword) ButtonDefaults.elevation(
        0.dp,
        0.dp,
        0.dp,
        0.dp,
        0.dp
    ) else ButtonDefaults.elevation(2.dp, 8.dp, 0.dp, 4.dp, 4.dp)
    Button(
        onClick = {
            if (enabledEmail && enabledPassword) {
                functionClick()
            } else if (!enabledEmail) {
                dialogShownState.value = true
            } else if (!enabledPassword) {
                dialogShownState.value = true
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        elevation = elevation,
        modifier = Modifier
            .focusGroup()

    ) {
        Text(text = "¡Registrarme!", color = White)

        if (!enabledEmail && dialogShownState.value) {
            MismatchDialog(
                onDismiss = { dialogShownState.value = false },
                text = "El email no es válido",
                true
            )
        } else if (dialogShownState.value && !enabledPassword) {
            MismatchDialog(
                onDismiss = { dialogShownState.value = false },
                text = "Las contraseñas no coinciden o no tienen 8 caracteres",
                true
            )
        }
    }

}


