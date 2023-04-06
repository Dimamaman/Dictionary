package uz.gita.dimadictionary.presenter.screen.en_uz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.databinding.FragmentEnUzBinding
import uz.gita.dimadictionary.presenter.screen.en_uz.adapter.MyAdapter
import uz.gita.dimadictionary.presenter.screen.main.viewmodel.MainViewModel
import uz.gita.dimadictionary.presenter.screen.main.viewmodel.MainViewModelImpl
import uz.gita.dimadictionary.util.myApply

class EnUzFragment: Fragment(R.layout.fragment_en_uz) {
    private val binding by viewBinding(FragmentEnUzBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val myAdapter: MyAdapter by lazy { MyAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        viewModel.getAllWord().observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        recyclerview.adapter = myAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}