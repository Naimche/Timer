package com.naim.timer.music

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.media.MediaPlayer
import android.preference.PreferenceDataStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.R
import com.naim.timer.utils.MusicSettings

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(application: Application, savedStateHandle: SavedStateHandle) : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    fun playMusic(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.background_music)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

    }

    fun stopMusic() {

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

    }

    companion object {
        private var IS_MUSIC_ON_KEY = booleanPreferencesKey("is_music_on")
    }


}



