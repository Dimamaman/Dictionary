package uz.gita.dimadictionary.presenter.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.presenter.screen.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container,MainFragment()).commit()
    }
}