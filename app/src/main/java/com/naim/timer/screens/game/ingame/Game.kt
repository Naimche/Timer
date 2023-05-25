package com.naim.timer.screens.game.ingame

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.widget.TextViewCompat.AutoSizeTextType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.screens.game.TimerSettings
import com.naim.timer.screens.game.utils.CircularTimerWithBackground
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

        if(viewModel.tempWordList.isEmpty()) {
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
                    text = TimerSettings.teamName,
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
                            if (viewModel.turn) {
                                viewModel.scoreTeam1++
                            } else {
                                viewModel.scoreTeam2++
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

            } else {
                if (viewModel.isFirstLaunch){
                    viewModel.dataWordsByDB()
                    viewModel.isFirstLaunch = false
                }
                viewModel.wordIndex = 0
                viewModel.finishTimerImmediately()
                Text(
                    text = TimerSettings.teamName.uppercase(),
                    fontSize = 54.sp,
                    fontFamily = Poppins
                )
                Text(
                    text = "Ronda " + viewModel.round.toString(),
                    fontSize = 54.sp,
                    fontFamily = Poppins
                )
                Button(onClick = {
                    viewModel.start = true

                }) {
                    Text(text = "Info reglas")
                }


            }

        }
    }
}


