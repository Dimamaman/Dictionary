package uz.gita.dimadictionary.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.dimadictionary.presenter.screen.en_uz.EnUzFragment
import uz.gita.dimadictionary.presenter.screen.uz_en.UzEnFragment

class MyAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                EnUzFragment()
            }
            else -> {
                UzEnFragment()
            }
        }
    }
}