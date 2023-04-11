package uz.gita.dimadictionary.presenter.screen.main.viewModel.impl

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import uz.gita.dimadictionary.R
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.domain.repository.AppRepository
import uz.gita.dimadictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.dimadictionary.presenter.screen.main.viewModel.MainViewModel

class MainViewModelImpl : ViewModel(), MainViewModel {
    private var pagePosition = 0

    private val repository: AppRepository = AppRepositoryImpl()

    override val openFavScreen = MutableLiveData<Unit>()
    override val getEnglishWord = MutableLiveData<List<DictionaryEntity>>()
    override val getUzbekWord = MutableLiveData<List<DictionaryEntity>>()

    override val getAllEnglishWord = MutableLiveData<List<DictionaryEntity>>()
    override val getAllUzbekWord = MutableLiveData<List<DictionaryEntity>>()

    override fun searchWord(searchWord: String) {
        viewModelScope.launch {
            if (pagePosition == 0) {
                getEnglishWord.value = repository.searchEnglishWord(searchWord)
            } else {
                getUzbekWord.value = repository.searchUzbekWord(searchWord)
            }
        }
    }

    override fun openFavouriteScreen() {
        openFavScreen.value = Unit
    }

    override fun saveCurrentPos(pos: Int) {
        pagePosition = pos
    }

    override suspend fun getAllEnglishWord() {
        getAllEnglishWord.value = repository.getAllEnglishWords()
    }

    override suspend fun getAllUzbekWord() {
        getAllUzbekWord.value = repository.getAllUzbekWords()
    }

    override fun updateDictionary(dictionary: DictionaryEntity) { repository.updateDictionary(dictionary) }
}