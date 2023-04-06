package uz.gita.dimadictionary.presenter.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.databinding.FragmentMainBinding
import uz.gita.dimadictionary.presenter.screen.main.adapter.MyAdapter
import uz.gita.dimadictionary.presenter.screen.main.viewmodel.MainViewModel
import uz.gita.dimadictionary.presenter.screen.main.viewmodel.MainViewModelImpl
import uz.gita.dimadictionary.util.myApply

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    var tabTitle = arrayOf("Eng-Uzb", "Uzb-Eng")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        val pager = binding.viewPager2

        val t1 = binding.tabLayout

        pager.adapter = activity?.supportFragmentManager?.let { MyAdapter(it, lifecycle) }

        TabLayoutMediator(t1, pager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()


    }
}