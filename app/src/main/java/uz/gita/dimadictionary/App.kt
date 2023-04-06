package uz.gita.dimadictionary

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import uz.gita.dimadictionary.data.source.local.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppDatabase.init(this)
    }
}