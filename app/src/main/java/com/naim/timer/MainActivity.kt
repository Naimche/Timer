package com.naim.timer

import android.app.PendingIntent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.naim.timer.navigation.AppNavigation
import com.naim.timer.navigation.AppScreens
import com.naim.timer.ui.theme.TimerTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

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
            AppNavigation()
        }
    }

}