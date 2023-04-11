package uz.gita.dimadictionary.presenter.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.presenter.screen.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun getCurrentFragment(): Fragment {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
        val currentFragment = navHostFragment.childFragmentManager.findFragmentById(navHostFragment.navController.currentDestination!!.id)
        return currentFragment!!
    }
}