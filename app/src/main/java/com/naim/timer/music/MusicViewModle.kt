package com.naim.timer.music
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.naim.timer.R
import javax.inject.Inject

class MusicViewModel @Inject constructor(
    private val context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val mediaPlayer = MediaPlayer.create(context, R.raw.background_music)

    fun startMusic() {
        mediaPlayer.start()
    }

    fun stopMusic() {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0) // Resetea el MediaPlayer al inicio del archivo de música
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.release() // Libera el MediaPlayer cuando el ViewModel se borra
    }

}