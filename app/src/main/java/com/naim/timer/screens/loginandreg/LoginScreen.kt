package com.naim.timer.screens.loginandreg

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController) {
    Scaffold() {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    var password1 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


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
            EmailField(onChange = { email = it })
            Spacer(Modifier.height(6.dp))
            PasswordField(onChange = { password1 })
            Spacer(modifier = Modifier.height(6.dp))
            ButtonLogin(navController)
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
                .padding(horizontal = 24.dp, vertical = 12.dp)
                ,
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
fun ButtonLogin(navController: NavController) {
    Button(
        onClick = { TODO() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFd88870))
    ) {
        Text(text = "Iniciar Sesión", color = White)
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val navController = rememberNavController()
    BodyContent(navController = navController)
}





