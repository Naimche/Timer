package com.naim.timer

import android.content.pm.ActivityInfo
import android.os.Bundle
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
            var startDestination = ""
            startDestination= if (user != null) {
                AppScreens.GameLobby.route
            } else {
                AppScreens.LoginScreen.route
            }

            AppNavigation(startDestination)
        }
    }

    //region start Scriptzone
    //DBM.uploadWordsScript("Dlc1", listOf("Campana", "Sirena", "Alarma", "Viento", "Rayo", "Trueno", "Llanto", "Gruñido", "Maullido", "Ladrido", "Aullido", "Chirrido", "Rugido", "Silbido", "Tocino", "Huevo", "Helado", "Pizza", "Pastel", "Burrito", "Popcorn", "Coca Cola", "Leche", "Té", "Café", "Cerveza", "Vino", "Whisky", "Garganta", "Estornudo", "Ronquido", "Carcajada", "Bostezo", "Suspiro", "Lloriqueo", "Hipo", "Tos", "Golpe", "Caída", "Explosión", "Disparo", "Chirriar", "Zumbido", "Silencio", "Canto", "Aplausos", "Tararear", "Zapping", "Teléfono", "Timbre", "Ruido de la calle", "Portazo", "Silla", "Pasos", "Ducha", "Lavadora", "Secadora", "Cepillarse los dientes", "Cortar el césped", "Perforadora", "Licuadora", "Tocadiscos", "Piano", "Guitarra", "Batería", "Violín", "Flauta", "Saxofón", "Trompeta", "Helicóptero", "Avión", "Tren", "Coche", "Moto", "Bicicleta", "Barco", "Submarino", "Sátelite", "Ventilador", "Ascensor", "Puerta automática", "Abanico", "Grifo", "Almohada", "Silla de playa", "Tumbona", "Cama", "Saco de dormir", "Tienda de campaña", "Chimenea", "Cascada", "Río", "Mar", "Piscina", "Lluvia", "Nieve", "Tormenta", "Fuego artificial", "Carrusel", "Parque de atracciones")){

    //}

    //endregion
}
