package com.naim.timer.screens.game.ingame

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.screens.game.utils.CircularTimer
import com.naim.timer.screens.game.utils.CircularTimerViewModel
import com.naim.timer.screens.game.utils.CircularTimerWithBackground
import com.naim.timer.screens.loginandreg.Background
import com.naim.timer.screens.loginandreg.ButtonLogin
import com.naim.timer.screens.loginandreg.Logo

@Composable
fun Game(navController: NavController) {
    GameBody(navController)
}

@Composable
fun GameBody(navController: NavController, viewModel: GameViewModel = hiltViewModel(), viewModelTimer: CircularTimerViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Background()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Spacer(modifier = Modifier.height(14.dp))
        CircularTimerWithBackground(progress = viewModelTimer.progress)
        Button(onClick = { viewModelTimer.startTimer()}) {
            Text(text ="Start")
        }
    }
}