package com.naim.timer.screens.game.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.music.MusicViewModel
import com.naim.timer.ui.theme.Poppins
import com.naim.timer.ui.theme.Roboto
import com.naim.timer.ui.theme.RobotoBold
import kotlin.math.absoluteValue

@Composable
fun GameSettings(navController: NavController) {
    GameSettingsBodyContent(navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun GameSettingsBodyContent(
    navController: NavController? = null,
    viewModel: GameSettingsViewModel = hiltViewModel(),
    viewModelMusic: MusicViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0D103B))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "REGLAS DEL JUEGO",
                    fontSize = 23.sp,
                    fontFamily = Poppins,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                val pagerState = rememberPagerState()
                HorizontalPager(pageCount = 4, state = pagerState) { page ->
                    Card(
                        Modifier
                            .size(300.dp)
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }, backgroundColor = Color.Transparent
                    ) {
                        Box(
                            modifier = Modifier.background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFEE5959),
                                        Color(0xFFDD384F),
                                        Color(0xFFDC3454),
                                        Color(0xFFDD1D5E),
                                        Color(0xFFDD1D5E)
                                    )
                                )
                            )
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = when (pagerState.currentPage) {
                                        0 -> "Equipos"
                                        1 -> "RONDA 1"
                                        else -> "Hola"
                                    },
                                    fontFamily = RobotoBold,
                                    fontSize = 45.sp,
                                    color = Color.White,
                                )
                                Spacer(modifier = Modifier.height(24.dp))

                                when (pagerState.currentPage) {
                                    0 -> {
                                        Text(
                                            text = "Divide a los jugadores en dos equipos",
                                            fontFamily = Roboto,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(horizontal = 20.dp)
                                        )

                                    }
                                    1->{
                                        Text(
                                            text = "Descripción verbal",
                                            fontFamily = Roboto,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(horizontal = 20.dp)
                                        )
                                    }
                                }




                            }
                        }

                        // Card content
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = when (pagerState.currentPage) {
                        0 -> "REGLAS"
                        1 -> "DESCRIPCIÓN VERBAL"

                        else -> "Hola"
                    },
                    fontFamily = RobotoBold,
                    fontSize = 30.sp,
                    color = Color.White,

                    )
                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = when (pagerState.currentPage) {
                        0 -> "Cada equipo debe tener al menos dos jugadores."
                        2 -> "El describiente tiene 30 segundos para describir tantas palabras o frases como sea posible, sin usar las palabras clave."
                        else -> "Hola"
                    },
                    fontFamily = Roboto,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                    ) {


                        repeat(4) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) Color(0xFFD84D46) else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)

                            )
                        }


                    }


                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Row(
            horizontalArrangement = Arrangement.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, bottom = 22.dp), verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier.clickable { })
            {
                Text(
                    text = "INICIO",
                    fontFamily = Roboto,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.End, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp, end = 22.dp), verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier.clickable { })
            {
                Text(
                    text = "SIGUIENTE",
                    fontFamily = Roboto,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}
/**
.background(
brush = Brush.linearGradient(
colors = listOf(
Color(0xFF001d3d),
Color(0xFF0081a7),
Color(0xFF00afb9),
Color(0xFF90e0ef),
Color(0xFFcaf0f8)
),
),
)*/