package uz.gita.dimadictionary.presenter.screen.uz_en

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.databinding.FragmentUzEnBinding
import uz.gita.dimadictionary.presenter.adapter.MyUzEnAdapter
import uz.gita.dimadictionary.presenter.screen.main.viewModel.MainViewModel
import uz.gita.dimadictionary.presenter.screen.main.viewModel.impl.MainViewModelImpl
import uz.gita.dimadictionary.util.myApply
import java.util.*

class UzEnFragment: Fragment(R.layout.fragment_uz_en),TextToSpeech.OnInitListener {

    private val binding by viewBinding(FragmentUzEnBinding::bind)

    private val viewModelUzbek: MainViewModel by activityViewModels<MainViewModelImpl>()

    private val myAdapter: MyUzEnAdapter by lazy { MyUzEnAdapter() }
    private var tts: TextToSpeech? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        viewModelUzbek.getUzbekWord.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }

        recyclerview.adapter = myAdapter
        recyclerview.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )

        tts = TextToSpeech(requireContext(), this@UzEnFragment)

        myAdapter.setFavoriteClickListener { dictionary ->
            viewModelUzbek.updateDictionary(dictionary)
        }

        myAdapter.setSpeakClickListener {
            speakOut(it)
        }

        lifecycleScope.launch {
            viewModelUzbek.getAllUzbekWord()
        }

        viewModelUzbek.getAllUzbekWord.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }


        myAdapter.setCopyClickListener {
            val clipBoard =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(it.english,"${it.uzbek}\n\n${it.english}\n\n" +
                    "${it.transcript}\n")
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
                Toast.makeText(requireContext(), "The Language not supported!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun speakOut(englishWord: String) {
        tts!!.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}