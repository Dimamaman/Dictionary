package uz.gita.dimadictionary.presenter.screen.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.databinding.FragmentMainBinding
import uz.gita.dimadictionary.presenter.adapter.MyAdapter
import uz.gita.dimadictionary.presenter.screen.activity.MainActivity
import uz.gita.dimadictionary.presenter.screen.main.viewModel.MainViewModel
import uz.gita.dimadictionary.presenter.screen.main.viewModel.impl.MainViewModelImpl
import uz.gita.dimadictionary.util.myApply

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    var tabTitle = arrayOf("Eng-Uzb", "Uzb-Eng")
    private val viewModel by activityViewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openFavScreen.observe(this, openFavScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        val pager = binding.viewPager2
        val t1 = binding.tabLayout

        val myAdapter = activity?.supportFragmentManager?.let { MyAdapter(it, lifecycle) }
        pager.adapter = myAdapter

        TabLayoutMediator(t1, pager) { tab, position -> tab.text = tabTitle[position] }.attach()

        floatingBtn.setOnClickListener {
            viewModel.openFavouriteScreen()
        }

        viewPager2.registerOnPageChangeCallback(myChangeCallBack)

        outlinedTextField.editText?.doAfterTextChanged {
            viewModel.searchWord(it.toString())
        }
    }

    private val myChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.saveCurrentPos(position)
        }
    }

    private val openFavScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction =
            activity?.supportFragmentManager?.beginTransaction()!!
        if (fragment is MainFragment) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.container, fragment).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager2.unregisterOnPageChangeCallback(myChangeCallBack)
    }

}