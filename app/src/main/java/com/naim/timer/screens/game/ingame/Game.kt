package com.naim.timer.screens.game.ingame

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.navigation.AppScreens
import com.naim.timer.screens.game.TimerSettings
import com.naim.timer.screens.game.utils.CircularTimerWithBackground
import com.naim.timer.screens.loginandreg.Logo
import com.naim.timer.ui.theme.Lobster
import com.naim.timer.ui.theme.Poppins
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Game(navController: NavController) {
    GameBody(navController)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameBody(navController: NavController, viewModel: GameViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = viewModel.refresh.toString(), color = Color.Transparent)
        //lista que se va a modificar

        //Cuando la lista este vacia le añadimos los elementos de la lista original

        if (viewModel.tempWordList.isEmpty()) {
            Log.i("Lista", viewModel.listWords.toString())
            viewModel.tempWordList.addAll(viewModel.listWords)
            viewModel.start = false
        }
        viewModel.tempWordList.removeIf { it.isEmpty() }
        Log.i("size", viewModel.tempWordList.size.toString())

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
                .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (viewModel.start) {
                Log.i("ListaEnTiempoReal", viewModel.tempWordList.toString())
                viewModel.startTimer()
                //Comprobador de ronda y turno


                Text(
                    text = if (viewModel.turn) TimerSettings.teamName else TimerSettings.teamName2,
                    fontFamily = Poppins,
                    fontSize = 64.sp,
                    modifier = Modifier.padding(bottom = 0.dp),
                    color = Color(
                        0xFFC55DF6
                    )
                )

                Card(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .width(350.dp)
                        .height(200.dp), elevation = 9.dp,
                    backgroundColor = Color(0xFFFFFBFF)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = viewModel.tempWordList[viewModel.wordIndex],
                            fontSize = 44.sp,
                            fontFamily = Poppins,
                            color = Color(0xFF8093F1),
                            textAlign = TextAlign.Center
                        )

                    }


                }
                Divider(
                    modifier = Modifier
                        .width(267.dp)
                        .height(2.dp),
                    color = Color.Gray
                )

                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 35.dp), contentAlignment = Alignment.Center
                ) {
                    CircularTimerWithBackground(progress = viewModel.progress, strokeWidth = 15.dp)
                    Text(
                        text = ((viewModel.timeleft + 500L) / 1000L).toString() + "s",
                        fontSize = 24.sp,
                        fontFamily = Poppins
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            Log.i("Indice", viewModel.wordIndex.toString())
                            if (viewModel.turn) {
                                viewModel.scoreTeam1++
                            } else {
                                viewModel.scoreTeam2++
                            }
                            //Al acertar se suma uno al index y se suma uno a la puntuacion del equipo actual.
                            if (viewModel.tempWordList.size == viewModel.wordIndex + 1) {
                                viewModel.start = false
                                viewModel.finishTimerImmediately()
                                viewModel.tempWordList.removeAt(viewModel.wordIndex)
                                Log.i("Ronda", viewModel.wordIndex.toString())
                                if (viewModel.tempWordList.isEmpty()) viewModel.nextRound()


                            } else {
                                viewModel.tempWordList.removeAt(viewModel.wordIndex)

                            }

                            viewModel.refresh = viewModel.refresh.not()


                        }, modifier = Modifier
                            .padding(end = 150.dp)
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

                    val scope = rememberCoroutineScope()
                    val context = LocalContext.current

                    IconButton(
                        onClick = {
                            Log.i("Indice", viewModel.wordIndex.toString())
                            if (viewModel.tempWordList.size == viewModel.wordIndex + 1) {
                                viewModel.finishTimerImmediately()
                            } else {
                                viewModel.wordIndex++

                            }
                            scope.launch {

                                val vibrator =
                                    context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                                if (vibrator.hasVibrator()) {
                                    vibrator.vibrate(
                                        VibrationEffect.createOneShot(
                                            100,
                                            VibrationEffect.DEFAULT_AMPLITUDE
                                        )
                                    )
                                }


                            }
                        }, modifier = Modifier
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

            } else if (viewModel.round in 1..3) {
                if (viewModel.isFirstLaunch) {
                    viewModel.dataWordsByDB()
                    viewModel.isFirstLaunch = false
                }
                viewModel.wordIndex = 0
                Text(
                    text = "Ronda " + viewModel.round.toString(),
                    fontSize = 74.sp,
                    fontFamily = Poppins,
                    modifier = Modifier.padding(top = 20.dp),
                    color = Color(0xFFFAEDAF)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = "Turno de ",
                        fontSize = 34.sp,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center
                    )
                    if (viewModel.turn) {
                        Text(
                            text = if (TimerSettings.teamName.uppercase()
                                    .isNotBlank()
                            ) TimerSettings.teamName.uppercase() else "Equipo 1",
                            fontSize = 34.sp,
                            fontFamily = Poppins, textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = if (TimerSettings.teamName.uppercase()
                                    .isNotBlank()
                            ) TimerSettings.teamName2.uppercase() else "Equipo 2",
                            fontSize = 34.sp,
                            fontFamily = Poppins, textAlign = TextAlign.Center
                        )
                    }
                }


                Spacer(modifier = Modifier.height(20.dp))

                Row {


                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                            Text(
                                text = if (TimerSettings.teamName.uppercase()
                                        .isNotBlank()
                                ) TimerSettings.teamName.uppercase() else "Equipo 1",
                                fontSize = 44.sp,
                                fontFamily = Poppins,
                                color = Color(0xFFFFFFFF)

                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = viewModel.scoreTeam1.toString(),
                                fontSize = 44.sp,
                                fontFamily = Poppins,
                                color = Color(0xFF8093F1),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(end = 10.dp)
                            )


                        }

                        Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                            Text(
                                text = if (TimerSettings.teamName2.uppercase()
                                        .isNotBlank()
                                ) TimerSettings.teamName2.uppercase() else "Equipo 2",
                                fontSize = 44.sp,
                                fontFamily = Poppins,
                                color = Color(0xFFFFFFFF)

                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = viewModel.scoreTeam2.toString(),
                                fontSize = 44.sp,
                                fontFamily = Poppins,
                                color = Color(0xFF8093F1),
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        viewModel.start = true

                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE779FA)
                    )
                ) {
                    Text(
                        text = "¡Empezar!",
                        fontFamily = Lobster,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }


            } else {
                viewModel.win10Coins()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Logo()
                    if (viewModel.scoreTeam1 > viewModel.scoreTeam2) {

                        Text(
                            text = if (TimerSettings.teamName.uppercase()
                                    .isNotBlank()
                            ) "Ganador " + TimerSettings.teamName.uppercase() else "Gana el Equipo 1",
                            fontSize = 34.sp,
                            fontFamily = Poppins,
                            modifier = Modifier.padding(top = 20.dp),
                            color = Color(0xFFFAEDAF)
                        )
                    } else if (viewModel.scoreTeam1 < viewModel.scoreTeam2) {
                        Text(
                            text = if (TimerSettings.teamName2.uppercase()
                                    .isNotBlank()
                            ) "Ganador " + TimerSettings.teamName2.uppercase() else "Gana el Equipo 2",
                            fontSize = 34.sp,
                            fontFamily = Poppins,
                            modifier = Modifier.padding(top = 20.dp),
                            color = Color(0xFFFAEDAF)
                        )
                    } else {
                        Text(
                            text = "¡Empate!",
                            fontSize = 54.sp,
                            fontFamily = Poppins,
                            modifier = Modifier.padding(top = 20.dp),
                            color = Color(0xFFFAEDAF)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            navController.navigate(AppScreens.GameLobby.route) {
                                navController.popBackStack()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFE779FA)
                        )
                    ) {
                        Text(text = "Volver al menú", fontFamily = Lobster, fontSize = 20.sp)
                    }
                }

            }

        }
    }
}


