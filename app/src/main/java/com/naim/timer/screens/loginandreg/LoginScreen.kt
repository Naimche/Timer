package com.naim.timer.screens.loginandreg

import android.annotation.SuppressLint
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.graphics.RenderEffect
import android.graphics.Shader
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

import kotlin.math.PI
import kotlin.math.sin
import com.naim.timer.R
import com.naim.timer.navigation.AppScreens
import com.naim.timer.ui.theme.DEFAULT_PADDING
import com.naim.timer.utils.times
import com.naim.timer.utils.transform


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController, viewModel: LoginScreenViewModel = hiltViewModel()) {
    Scaffold() {
        LoginBodyContent(navController)
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun LoginBodyContent(navController: NavController, viewModel: LoginScreenViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
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
            PasswordField(onChange = { viewModel.password = it })
            Spacer(modifier = Modifier.height(6.dp))
            ButtonLogin(onClick = {
                        viewModel.login{
                        viewModel.numAttepmts += 1
                        Log.i("Login", it.toString())
                        //navController.navigate(AppScreens.GameLobby.route)
                        if (it) {
                            Log.i("Login", "Login Success")
                            Toast.makeText(navController.context, "Login Exitoso", Toast.LENGTH_SHORT).show()
                            navController.navigate(AppScreens.GameLobby.route){
                                navController.popBackStack()
                            }

                        }else{
                            Log.i("Login", "Login Failed")
                            if (viewModel.toast){
                                Toast.makeText(navController.context, viewModel.alertError, Toast.LENGTH_SHORT).show()
                                viewModel.toast = false
                            }
                        }

                    }







            })

            MismatchDialog(onDismiss = { viewModel.dialogShowState = false }, text = viewModel.alertError, show = viewModel.dialogShowState)
            //Loading when more than 5 attemps

            if (viewModel.loading){
                CircularProgressIndicator(modifier = Modifier.padding(top = 30.dp), color = Color.White)


            }


        }
        if (viewModel.loading){
            Box(Modifier.fillMaxSize()) {
                AlertDialog(
                    onDismissRequest = { },
                    properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
                    backgroundColor = Color.Transparent,
                    buttons = {},
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }


    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        ButtonRegister(navController)
    }


}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logolila),
        contentDescription = "Logo",
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
            .scale(2.5f)
    )
}


@Composable
fun ButtonRegister(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "¿No tienes cuenta?", color = White)
            OutlinedButton(
                onClick = { navController.navigate("Register_Screen") },
                border = BorderStroke(0.dp, Transparent),
                colors =
                ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Transparent
                ),

                ) {
                Text(text = "Registrarse", color = Color(0xFF00cecb))
            }
        }
    }
}


@Composable
fun ButtonLogin(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFd88870))
    ) {
        Text(text = "Iniciar Sesión", color = White)
    }
}







