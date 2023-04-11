package uz.gita.dimadictionary.presenter.screen.favourite.viewModel

import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface FavouriteViewModel {
    suspend fun getAllFavourites(): LiveData<List<DictionaryEntity>>
    fun updateDictionary(dictionary: DictionaryEntity)
}