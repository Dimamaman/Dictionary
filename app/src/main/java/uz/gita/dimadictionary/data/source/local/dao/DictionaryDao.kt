package uz.gita.dimadictionary.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.dimadictionary.data.model.Dictionary
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    fun getAllWords(): LiveData<List<DictionaryEntity>>

    @Insert
    fun insert(dictionaryEntity: DictionaryEntity)
}