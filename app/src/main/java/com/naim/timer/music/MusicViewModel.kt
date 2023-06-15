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
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.naim.timer.R

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
@OptIn(SavedStateHandleSaveableApi::class)
class MusicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val musicSettingsData: MusicSettingsData
) : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    var musicState by savedStateHandle.saveable { mutableStateOf(false) }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }

    fun playMusic(context: Context) {
        if (!isPlaying) {
           // mediaPlayer = MediaPlayer.create(context, R.raw.background_music)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
            isPlaying = true
            musicState = true
        }

    }

    fun stopMusic() {

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
        musicState = false

    }

    fun saveMusicState(value: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            musicSettingsData.saveMusicSetting(value)
        }
    }

    companion object {
        private var IS_MUSIC_ON_KEY = booleanPreferencesKey("is_music_on")
    }


}