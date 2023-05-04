package com.naim.timer.screens.game

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.music.MusicSettingsData
import com.naim.timer.music.MusicViewModel
import com.naim.timer.navigation.AppScreens
import com.naim.timer.screens.loginandreg.*
import com.naim.timer.ui.theme.Lobster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameLobby(navController: NavController) {
    GameLobbyBodyContent(navController)
}

@Composable
fun GameLobbyBodyContent(
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    viewModel: GameLobbyViewModel = hiltViewModel()
) {


    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (viewModel.isMenuExtended) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (viewModel.isMenuExtended) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    GameLobbyBodyContent(
        navController = navController,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress,
    ) {
        viewModel.isMenuExtended = viewModel.isMenuExtended.not()


    }

}

@Composable
fun GameLobbyBodyContent(
    viewModel: GameLobbyViewModel = hiltViewModel(),
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },


    ) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Background()
        //region start configurationGame
        when (viewModel.whIsMenu) {

            0 ->{

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .wrapContentHeight().padding(bottom = 70.dp)
                ) {
                    Logo()
                    Spacer(modifier =Modifier.height(16.dp))
                    Card(
                        backgroundColor = Color(0xCEFFD7D7).copy(alpha = 0.2f),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(15.dp).padding(horizontal = 25.dp, vertical = 16.dp)
                            .wrapContentSize(),

                        ) {
                        Text(
                            text = "¡Bienvenido a Timer, el juego de mesa Times Up ahora en tu móvil! Adivina palabras y frases antes de que se acabe el tiempo!",
                            modifier = Modifier.padding(start = 16.dp, end = 10.dp, top = 16.dp, bottom = 16.dp),
                            style = TextStyle(color = Color.White),
                            fontSize = 26.sp,
                            fontFamily = Lobster,
                            color = Color(0xFFB885FF)
                        )
                    }
                    Button(
                        onClick = { /* acción al pulsar el botón */ },
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFfae079))
                    ) {
                        Text(
                            text = "Jugar ahora",
                            fontFamily = Lobster,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }


                }


            }
        }

        //endregion

        //region start Navigation
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            CustomBottomNavigation()
            Circle(
                color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                animationProgress = 0.5f
            )

            FabGroup(
                renderEffect = renderEffect,
                animationProgress = fabAnimationProgress,
                onClick1 = {  },
                onClick2 = {  },
                onClick3 = {})
            FabGroup(
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation,
                onClick1 = { viewModel.whIsMenu = 0  },
                onClick2 = {viewModel.whIsMenu = 1 },
                onClick3 = {viewModel.whIsMenu = 2 }
            )
            Circle(
                color = Color.White,
                animationProgress = clickAnimationProgress
            )
        }

        //endregion




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





