package uz.gita.dimadictionary.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dimadictionary.data.model.Dictionary
import uz.gita.dimadictionary.data.source.local.AppDatabase
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

class AppRepositoryImpl: AppRepository {
    private val database = AppDatabase.getInstance()

    override fun getAllWords(): LiveData<List<DictionaryEntity>> {
        return database.getDao().getAllWords()
    }
}