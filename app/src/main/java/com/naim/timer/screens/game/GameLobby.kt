package com.naim.timer.screens.game

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.music.MusicViewModel
import com.naim.timer.screens.loginandreg.*
import com.naim.timer.utils.MusicSettingsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameLobby(navController: NavController) {
    GameLobbyBodyContent(navController)
}

@Composable
fun GameLobbyBodyContent(navController: NavController, musicViewModel: MusicViewModel = hiltViewModel(), viewModel: GameLobbyViewModel = hiltViewModel()) {


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

    BodyContent(
        navController = navController,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress
    ) {
        viewModel.isMenuExtended = viewModel.isMenuExtended.not()
    }

}

@Composable
fun BodyContent(
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },

    ) {
    val context = LocalContext.current

    val musicState = remember {
        mutableStateOf(false)
    }
    val musicStore = MusicSettingsData(context)
    val musicStateObtained = musicStore.accessMusic.collectAsState(initial = false)

    println(musicStateObtained.value)

    if (musicStateObtained.value == "true") {
        musicViewModel.playMusic(context)
    }


    Surface(modifier = Modifier.fillMaxSize()) {

        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Logo()
            Button(onClick = {
                musicState.value = false
                CoroutineScope(Dispatchers.IO).launch { musicStore.saveMusicSetting(musicState.value) }
            }) {
                Text(text = "Play")
            }
        }

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

            FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress, onClick1 = { println(1)}, onClick2 = { println(2)}, onClick3 = {})
            FabGroup(
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation,
                onClick1 = { println(1)}, onClick2 = {}, onClick3 = {}
            )
            Circle(
                color = Color.White,
                animationProgress = clickAnimationProgress
            )
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





