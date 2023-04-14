package uz.gita.dimadictionary.presenter.page.uz_en.viewmodel

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface UzEnRepository {
    val getAllUzbekWord: LiveData<Cursor>
    val getUzbekWord: LiveData<Cursor>
    val updateCursorLiveData: LiveData<Unit>

    fun searchWord(searchWord: String)
    fun updateDictionary(dictionary: DictionaryEntity)
    fun getAllUzbekWord()
}