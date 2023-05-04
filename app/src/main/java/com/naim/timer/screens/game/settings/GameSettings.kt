package com.naim.timer.screens.game.settings

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naim.timer.R
import com.naim.timer.music.MusicSettingsData
import com.naim.timer.music.MusicViewModel

import com.naim.timer.screens.loginandreg.Background
import com.naim.timer.screens.loginandreg.Logo
import com.naim.timer.ui.theme.AmarilloSettings
import com.naim.timer.ui.theme.VerdeSettings

@Composable
fun GameSettings(navController: NavController) {
    GameSettingsBodyContent(navController)
}

@Preview
@Composable
fun GameSettingsBodyContent(
    navController: NavController? = null,
    viewModel: GameSettingsViewModel = hiltViewModel(),
    viewModelMusic: MusicViewModel = hiltViewModel()
) {

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

    Column(modifier = Modifier.padding(start = 50.dp, top = 300.dp)) {
        Row() {
            Text(
                text = "MUSICA",
                fontSize = 36.sp,
                color = VerdeSettings,
                fontFamily = FontFamily(
                    Font(
                        R.font.adelia,
                        FontWeight.Normal,
                        FontStyle.Italic
                    )
                )
            )
            IconToggleButton(
                checked = viewModelMusic.musicState,
                onCheckedChange = {
                    viewModelMusic.musicState = viewModelMusic.musicState.not()
                    if (viewModelMusic.musicState) {
                        viewModelMusic.saveMusicState(true)
                    } else {
                        viewModelMusic.saveMusicState(false)
                    }
                },
                modifier = Modifier
                    .padding(start = 20.dp)
                    .offset(y = (3).dp)
            ) {
                if (viewModelMusic.musicState) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        tint = Color.White,
                        contentDescription = "Música activada",
                        modifier = Modifier.size(50.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.MusicOff,
                        tint = Color.Black,
                        contentDescription = "Música desactivada",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

        }
        Spacer(modifier =Modifier.height(54.dp))
        Row() {
            Text(
                text = "CREDITOS",
                fontSize = 36.sp,
                color = AmarilloSettings,
                fontFamily = FontFamily(
                    Font(
                        R.font.adelia,
                        FontWeight.Normal,
                        FontStyle.Italic
                    )
                )
            )
        }
        Spacer(modifier = Modifier.height(24.dp))



    }

    Spacer(modifier = Modifier.height(24.dp))

}
