package uz.gita.dimadictionary.presenter.screen.en_uz

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
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.databinding.FragmentEnUzBinding
import uz.gita.dimadictionary.presenter.adapter.MyEnUzAdapter
import uz.gita.dimadictionary.presenter.screen.main.viewModel.MainViewModel
import uz.gita.dimadictionary.presenter.screen.main.viewModel.impl.MainViewModelImpl
import java.util.*

class EnUzFragment : Fragment(R.layout.fragment_en_uz), TextToSpeech.OnInitListener {
    private val binding by viewBinding(FragmentEnUzBinding::bind)
    private val viewModelEnglish: MainViewModel by activityViewModels<MainViewModelImpl>()

    private val myAdapter: MyEnUzAdapter by lazy { MyEnUzAdapter() }
    private var tts: TextToSpeech? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        viewModelEnglish.getEnglishWord.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.progress.show()
            } else {
                binding.progress.hide()
            }
            myAdapter.submitList(it)
        }


        myAdapter.setFavoriteClickListener { dictionary ->
            viewModelEnglish.updateDictionary(dictionary)
        }

        initRecyclerView()

        tts = TextToSpeech(requireContext(), this@EnUzFragment)

        myAdapter.setSpeakClickListener {
            speakOut(it)
        }

        lifecycleScope.launch {
            viewModelEnglish.getAllEnglishWord()
        }

        viewModelEnglish.getAllEnglishWord.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.progress.show()
            } else {
                binding.progress.hide()
            }
            myAdapter.submitList(it)
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

    private fun initRecyclerView() {
        binding.apply {
            recyclerview.adapter = myAdapter
            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            }
        }
    }

    private fun speakOut(englishWord: String) {
        tts!!.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private val observer = androidx.lifecycle.Observer<List<DictionaryEntity>> {
        Toast.makeText(requireContext(), it.size , Toast.LENGTH_SHORT).show()
        myAdapter.submitList(it)
    }
}