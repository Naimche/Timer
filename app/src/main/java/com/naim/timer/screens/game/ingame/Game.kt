package com.naim.timer.screens.game.ingame

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.naim.timer.screens.loginandreg.Background
import com.naim.timer.screens.loginandreg.Logo

fun Game() {

}
@Composable
fun GameBody() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Background()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Spacer(modifier = Modifier.height(24.dp))
    }
}