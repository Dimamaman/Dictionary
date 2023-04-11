package uz.gita.dimadictionary.presenter.screen.main.viewModel

import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface MainViewModel {
    val openFavScreen: LiveData<Unit>

    val getEnglishWord: LiveData<List<DictionaryEntity>>
    val getUzbekWord: LiveData<List<DictionaryEntity>>

    val getAllEnglishWord: LiveData<List<DictionaryEntity>>
    val getAllUzbekWord: LiveData<List<DictionaryEntity>>

    fun openFavouriteScreen()

    fun searchWord(searchWord: String)

    fun saveCurrentPos(pos: Int)

    suspend fun getAllEnglishWord()

    suspend fun getAllUzbekWord()

    fun updateDictionary(dictionary: DictionaryEntity)
}