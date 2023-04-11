package uz.gita.dimadictionary.domain.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.AppDatabase
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.domain.repository.AppRepository

class AppRepositoryImpl: AppRepository {
    private val database = AppDatabase.getInstance()

    override suspend fun getAllEnglishWords(): List<DictionaryEntity> {
        return database.getDao().getAllEnglishWords()
    }

    override suspend fun getAllUzbekWords(): List<DictionaryEntity> {
        return database.getDao().getAllUzbekWords()
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        database.getDao().updateDictionary(dictionary)
    }

    override suspend fun searchEnglishWord(searchEnglishWord: String): List<DictionaryEntity>? {
        return database.getDao().searchEnglishWord(searchEnglishWord)
    }

    override suspend fun searchUzbekWord(searchEnglishWord: String): List<DictionaryEntity>? {
        return database.getDao().searchUzbekWord(searchEnglishWord)
    }

    override suspend fun getAllFavourites(): LiveData<List<DictionaryEntity>> {
        return database.getDao().getAllFavourites()
    }
}