package uz.gita.dimadictionary.presenter.screen.favourite.viewModel

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface FavouriteViewModel {
    val getAllFavourites: LiveData<Cursor>
    val updateCursorLiveData: LiveData<Unit>
    fun getAllFavourites()
    fun updateDictionary(dictionary: DictionaryEntity)
}