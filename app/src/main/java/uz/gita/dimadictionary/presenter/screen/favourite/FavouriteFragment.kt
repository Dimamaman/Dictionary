package uz.gita.dimadictionary.presenter.screen.favourite

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.databinding.FragmentFavouriteBinding
import uz.gita.dimadictionary.presenter.adapter.MyEnUzAdapter
import uz.gita.dimadictionary.presenter.screen.favourite.viewModel.impl.FavouriteViewModelImpl
import uz.gita.dimadictionary.util.myApply
import java.util.*

class FavouriteFragment : Fragment(R.layout.fragment_favourite), TextToSpeech.OnInitListener {
    private val binding by viewBinding(FragmentFavouriteBinding::bind)
    private val viewModel by viewModels<FavouriteViewModelImpl>()
    private val myAdapter by lazy { MyEnUzAdapter() }
    private var tts: TextToSpeech? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        recyclerview.adapter = myAdapter
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        lifecycleScope.launch {
            viewModel.getAllFavourites().observe(viewLifecycleOwner, observer)
        }

        tts = TextToSpeech(requireContext(), this@FavouriteFragment)

        myAdapter.setFavoriteClickListener {
            viewModel.updateDictionary(it)
        }

        myAdapter.setSpeakClickListener {
            speakOut(it)
        }

        myAdapter.setCopyClickListener {
            val clipBoard =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(
                it.english, "${it.english}\n\n${it.transcript}\n\n" +
                        "${it.uzbek}\n"
            )
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
        }
    }

    private val observer = Observer<List<DictionaryEntity>> {
        myAdapter.submitList(it)
    }

    private fun speakOut(englishWord: String) {
        tts!!.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            }
        }
    }
}