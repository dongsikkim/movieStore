package com.sundaydev.kakaoTest

import android.app.Application
import android.content.Context
import com.sundaydev.kakaoTest.di.applicationModules
import com.sundaydev.kakaoTest.util.DayNight
import com.sundaydev.kakaoTest.util.ThemeSelector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class MovieApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            androidLogger()
            modules(applicationModules)
        }
        setDarkTheme()
    }

    private fun setDarkTheme() {
        val darkMode = DayNight.valueOf(
            getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(Key.DARK_MODE.name, DayNight.DEFAULT.name)
                ?: DayNight.DEFAULT.name
        )
        ThemeSelector.applyTheme(darkMode)
    }
}

const val PREF_NAME = "movie.pref"

enum class Key { DARK_MODE }