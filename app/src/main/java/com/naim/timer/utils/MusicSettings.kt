package com.naim.timer.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


data class MusicSettings(
val isMusicOn: Boolean = false)
{
}
class MusicSettingsData(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val MUSIC_SETTINGS = stringPreferencesKey("music_setting")
    }

    val accessMusic: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[MUSIC_SETTINGS] ?: ""
    }

    suspend fun saveMusicSetting(musicSetting: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[MUSIC_SETTINGS] = musicSetting.toString()
        }
    }
}