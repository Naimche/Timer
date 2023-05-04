package com.naim.timer
import android.content.Context
import com.naim.timer.music.MusicSettingsData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideMusicSettingsData(@ApplicationContext context: Context): MusicSettingsData {
        return MusicSettingsData(context)
    }
}
