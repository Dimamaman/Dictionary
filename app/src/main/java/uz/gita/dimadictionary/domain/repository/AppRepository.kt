package uz.gita.dimadictionary.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface AppRepository {
    suspend fun getAllEnglishWords(): List<DictionaryEntity>
    suspend fun getAllUzbekWords(): List<DictionaryEntity>
    fun updateDictionary(dictionary: DictionaryEntity)
    suspend fun searchEnglishWord(searchEnglishWord: String): List<DictionaryEntity>?

    suspend fun searchUzbekWord(searchEnglishWord: String): List<DictionaryEntity>?

    suspend fun getAllFavourites(): LiveData<List<DictionaryEntity>>
}