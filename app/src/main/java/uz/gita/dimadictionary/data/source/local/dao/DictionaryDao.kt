package uz.gita.dimadictionary.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    suspend fun getAllEnglishWords(): List<DictionaryEntity>

    @Insert
    fun insert(dictionaryEntity: DictionaryEntity)

    @Query("SELECT * FROM dictionary ORDER BY uzbek GLOB '[A-Za-z]*' DESC, uzbek")
    suspend fun getAllUzbekWords(): List<DictionaryEntity>

    @Update
    fun updateDictionary(dictionary: DictionaryEntity)
    @Query("SELECT * FROM dictionary WHERE english LIKE '%' || :searchEnglish || '%'")
    suspend fun searchEnglishWord(searchEnglish: String): List<DictionaryEntity>?

    @Query("SELECT * FROM dictionary WHERE uzbek LIKE '%' || :searchEnglish || '%'")
    suspend fun searchUzbekWord(searchEnglish: String): List<DictionaryEntity>?

    @Query("SELECT * FROM dictionary WHERE favourite = 1")
    fun getAllFavourites(): LiveData<List<DictionaryEntity>>
}