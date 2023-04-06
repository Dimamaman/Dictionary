package uz.gita.dimadictionary.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dimadictionary.data.model.Dictionary
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface AppRepository {
    fun getAllWords(): LiveData<List<DictionaryEntity>>
}