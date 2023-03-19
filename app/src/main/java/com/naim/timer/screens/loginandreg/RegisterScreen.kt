package com.naim.timer.screens.loginandreg

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding()) {
        BodyContent()
    }
}

@Composable
fun BodyContent() {
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
            EmailField(onChange = { email = it })
            Spacer(Modifier.height(6.dp))
            PasswordField { password1 = it }
            Spacer(modifier = Modifier.height(6.dp))
            PasswordField { password2 = it }
            Spacer(modifier = Modifier.height(6.dp))
            passwordsMatch = password1 == password2 && password1.length > 8
            emailMatch = email.contains("@") && email.contains(".")
            ButtonConfirmation(enabledPassword = passwordsMatch, enabledEmail = emailMatch)


        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    val navController = rememberNavController()
    BodyContent(navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonConfirmation(enabledPassword: Boolean, enabledEmail: Boolean) {
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
                TODO()
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
                text = "El email no es válido"
            )
        } else if (dialogShownState.value && !enabledPassword) {
            MismatchDialog(
                onDismiss = { dialogShownState.value = false },
                text = "Las contraseñas no coinciden o no tienen 8 caracteres"
            )
        }
    }

}

@Composable
fun MismatchDialog(onDismiss: () -> Unit, text: String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.width(280.dp),
        title = {
            Text(
                text = "Error",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "OK",
                    color = Color(0xFFd88870),
                    style = MaterialTheme.typography.button
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface
    )
}
