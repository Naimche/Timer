package com.naim.timer

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.naim.timer.navigation.AppNavigation
import com.naim.timer.navigation.AppScreens
import com.naim.timer.ui.theme.TimerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            StartApp()
        }
    }
}


@Composable
fun StartApp() {
    TimerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background

        ) {
            val user = FirebaseAuth.getInstance().currentUser
            val startDestination= if (user != null) {
                AppScreens.GameLobby.route
            } else {
                AppScreens.LoginScreen.route
            }

            AppNavigation(startDestination)
        }
    }

    //region start Scriptzone
    //DBM.uploadWordsScript("Dlc2", price = 120L, words =  listOf("Mario", "Luigi", "Link", "Princesa Peach", "Donkey Kong", "Samus Aran", "Yoshi", "Kirby", "Pikachu", "Charizard", "Sonic the Hedgehog", "Mega Man", "Solid Snake", "Cloud Strife", "Master Chief", "Kratos", "Lara Croft", "Nathan Drake", "Geralt of Rivia", "Arthur Morgan", "Ezio Auditore", "Commander Shepard", "Marcus Fenix", "Gordon Freeman", "Leon S. Kennedy", "Jill Valentine", "Mario", "Bowser", "Ganondorf", "Fox McCloud", "Captain Falcon", "Peach", "Zelda", "Link", "Sheik", "Ganondorf", "Donkey Kong", "Diddy Kong", "Samus Aran", "Kirby", "Meta Knight", "King Dedede", "Pikachu", "Jigglypuff", "Charizard", "Mewtwo", "Lucario", "Incineroar", "Sonic", "Tails", "Knuckles", "Dr. Robotnik", "Mega Man", "X", "Zero", "Dante", "Leonardo (TMNT)", "Cortana", "Crash Bandicoot", "Spyro", "Rayman", "Ezio Auditore", "Altair", "Connor Kenway", "Edward Kenway", "Lara Croft", "Nathan Drake", "Joel (The Last of Us)", "Ellie (The Last of Us)", "Geralt of Rivia", "Ciri", "Arthur Morgan", "John Marston", "Trevor Phillips", "CJ (GTA San Andreas)", "Niko Bellic", "Tommy Vercetti", "Arthur Morgan", "John Marston", "Kratos", "Aloy", "Atreus", "Ratchet", "Clank", "Jak", "Daxter", "Sly Cooper", "Bentley", "Corvo Attano", "Emily Kaldwin", "Doom Slayer", "Sam Fisher", "Agent 47", "Joel (The Last of Us)", "Ellie (The Last of Us)", "Max Payne", "Marcus Fenix", "Alex Mercer", "Ezio Auditore", "Commander Shepard")){

    //}

    //endregion
}
