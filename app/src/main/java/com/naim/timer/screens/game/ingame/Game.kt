package com.naim.timer.screens.game.ingame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CancelPresentation
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.screens.game.GameLobbyViewModel
import com.naim.timer.screens.game.TimerSettings
import com.naim.timer.screens.game.utils.CircularTimerWithBackground
import com.naim.timer.screens.loginandreg.Background
import com.naim.timer.screens.loginandreg.Logo
import com.naim.timer.ui.theme.Poppins

@Composable
fun Game(navController: NavController) {
    GameBody(navController)
}

@Composable
fun GameBody(navController: NavController, viewModel: GameViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF3E989B))
                .padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (viewModel.start) {
                Spacer(modifier = Modifier.height(54.dp))
                Card(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .width(350.dp)
                        .height(200.dp), elevation = 8.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "PALABRA",
                            fontSize = 44.sp,
                            fontFamily = Poppins,
                            color = Color(0xFFf2ca48)
                        )
                    }


                }
                Divider(
                    modifier = Modifier
                        .width(260.dp)
                        .height(2.dp),
                    color = Color.Gray
                )

                Box(modifier = Modifier.size(250.dp), contentAlignment = Alignment.Center) {
                    CircularTimerWithBackground(progress = viewModel.progress, strokeWidth = 15.dp)
                    Text(
                        text = ((viewModel.timeleft + 500L) / 1000L).toString() + "s",
                        fontSize = 24.sp,
                        fontFamily = Poppins
                    )
                }
                Spacer(modifier = Modifier.height(54.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .padding(end = 70.dp)
                            .clip(
                                CircleShape
                            )
                            .background(
                                Color.White
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Acierto",
                            modifier = Modifier.size(90.dp)
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                Color.White
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fallar/Pasar",
                            modifier = Modifier.size(90.dp)
                        )

                    }
                }
            } else {
                Text(
                    text = TimerSettings.teamName.uppercase(),
                    fontSize = 54.sp,
                    fontFamily = Poppins
                )
            }

        }
    }
}