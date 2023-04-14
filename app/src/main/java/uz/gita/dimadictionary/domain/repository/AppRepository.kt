package uz.gita.dimadictionary.domain.repository

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface AppRepository {
    fun getAllEnglishWords(): Cursor
    fun getAllUzbekWords(): Cursor
    fun updateDictionary(dictionary: DictionaryEntity)
    fun searchEnglishWord(searchEnglishWord: String): Cursor
    fun searchUzbekWord(searchEnglishWord: String): Cursor

    fun getAllFavourites(): Cursor
}