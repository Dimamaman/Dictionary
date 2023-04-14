package uz.gita.dimadictionary.presenter.page.uz_en.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.dimadictionary.presenter.page.uz_en.viewmodel.UzEnRepository

class UzEnRepositoryImpl: ViewModel(), UzEnRepository {
    private val repository = AppRepositoryImpl.getInstance()

    override val getAllUzbekWord = MutableLiveData<Cursor>()
    override val getUzbekWord = MutableLiveData<Cursor>()
    override val updateCursorLiveData = MutableLiveData<Unit>()

    override fun searchWord(searchWord: String) {
        getUzbekWord.value = repository.searchUzbekWord(searchWord)
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        repository.updateDictionary(dictionary)
        updateCursorLiveData.value = Unit
    }

    override fun getAllUzbekWord() {
        getAllUzbekWord.value = repository.getAllUzbekWords()
    }
}