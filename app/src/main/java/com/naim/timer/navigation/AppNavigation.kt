package com.naim.timer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naim.timer.screens.game.GameLobby
import com.naim.timer.screens.game.ingame.Game
import com.naim.timer.screens.game.settings.GameSettings
import com.naim.timer.screens.loginandreg.FirstScreen
import com.naim.timer.screens.loginandreg.RegisterScreen

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
        composable(AppScreens.LoginScreen.route){
           FirstScreen(navController)
        }
        composable(AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(AppScreens.GameLobby.route){
            GameLobby(navController)
        }

        composable(AppScreens.GameSettings.route){
            GameSettings(navController)
        }

        composable(AppScreens.Game.route){
            Game(navController)
        }

    }
}


