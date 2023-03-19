package com.naim.timer.screens.game

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.screens.loginandreg.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameLobby(navController: NavController) {
    BodyContent(navController)
}

@Composable
fun BodyContent(navController: NavController) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
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
        isMenuExtended.value = isMenuExtended.value.not()
    }

}

@Composable
fun BodyContent(
    navController: NavController,
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { }
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Logo()
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

            FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
            FabGroup(
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation
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

@Composable
fun BottomNavigationBar() {

}




