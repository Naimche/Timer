package com.naim.timer.screens.game.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.R
import com.naim.timer.navigation.AppScreens
import com.naim.timer.screens.loginandreg.Background
import com.naim.timer.screens.loginandreg.Logo

@Composable
fun GameSettings(navController: NavController) {
    GameSettingsBodyContent(navController)
}

@Composable
fun GameSettingsBodyContent(navController: NavController, viewModel: GameSettingsViewModel = hiltViewModel()) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Background()
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 12.dp)) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                Spacer(modifier = Modifier.height(114.dp))
                Row() {
                    Text(
                        text = "Musica de fondo", fontFamily = FontFamily(Font(R.font.lobster_regular)),
                        color = MaterialTheme.colors.onPrimary,
                        fontSize =34.sp,
                    )
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = if (true) Icons.Filled.VolumeUp else Icons.Filled.VolumeOff,
                            contentDescription = if (true) "Música activada" else "Música desactivada",
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.padding(start = 18.dp)

                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Cerrar Sesion")
                }

            }
        }
    }

}
@Preview
@Composable
fun preview(){
    GameSettings(rememberNavController())
}