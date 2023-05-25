package com.naim.timer.screens.game.ingame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

        viewModel.dataWordsByDB()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFFDEAAFF), Color(0xFFFFD6FF), Color(
                                0xFFFAF7FA
                            )
                        )
                    )
                )
                .padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (viewModel.start) {
                Text(text = TimerSettings.teamName, fontFamily = Poppins, fontSize = 64.sp,modifier = Modifier.padding(bottom = 15.dp))
                viewModel.startTimer()
                Card(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .width(350.dp)
                        .height(200.dp), elevation = 9.dp,
                    backgroundColor = Color(0xFFFFFBFF)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "PALABRA",
                            fontSize = 44.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF8093F1),

                        )
                    }


                }
                Divider(
                    modifier = Modifier
                        .width(267.dp)
                        .height(2.dp),
                    color = Color.Gray
                )

                Box(modifier = Modifier
                    .size(250.dp)
                    .padding(top = 35.dp), contentAlignment = Alignment.Center) {
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
                            .padding(end = 150.dp,)
                            .clip(
                                CircleShape
                            )
                            .background(
                                Color(0xFFA7E8BD)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Acierto",
                            modifier = Modifier.size(60.dp),
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                Color(0xFFEFA7A7)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fallar/Pasar",
                            modifier = Modifier.size(60.dp),
                            tint = Color.White
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


