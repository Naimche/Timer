package com.naim.timer.navigation

sealed class AppScreens(val route:String){
    object LoginScreen: AppScreens("Login_Screen")
    object RegisterScreen: AppScreens("Register_Screen")
    object GameLobby: AppScreens("Game_Lobby")
    object GameSettings: AppScreens("Game_Settings")
}
