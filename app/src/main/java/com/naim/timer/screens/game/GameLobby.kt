package com.naim.timer.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Group
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.R
import com.naim.timer.screens.loginandreg.Logo
import com.naim.timer.screens.loginandreg.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameLobby(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigationBar()

    }
    BodyContent(navController)

}


@Composable
fun BodyContent(navcontroller: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF00cecb)
    ) {
        Background()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Logo()
            Spacer(modifier = Modifier.height(60.dp))

            ButtonGameLobby(navcontroller, { navcontroller.navigate("") }, "Normas")

        }
    }

}

@Preview
@Composable
fun GameLobbyPreview() {
    val navController = rememberNavController()
    GameLobby(navController)
}

@Composable
fun ButtonGameLobby(navController: NavController, function: () -> Unit, text: String) {
    Button(
        onClick = function,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFfae079))
    ) {
        Text(text = text, color = Color.White)

    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(80.dp)
            .paint(
                painter = painterResource(
                    id = R.drawable.bottom_navigation
                ),
                contentScale = ContentScale.FillHeight
            )
            .padding(horizontal = 40.dp)
    ) {
        listOf(
            Icons.Filled.CalendarToday, Icons.Filled.Group
        ).map { image ->
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = image, contentDescription = null, tint = Color.White)
            }
        }

    }
}

