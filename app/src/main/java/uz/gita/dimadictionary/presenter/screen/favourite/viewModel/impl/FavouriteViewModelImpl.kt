package uz.gita.dimadictionary.presenter.screen.favourite.viewModel.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.domain.repository.AppRepository
import uz.gita.dimadictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.dimadictionary.presenter.screen.favourite.viewModel.FavouriteViewModel

class FavouriteViewModelImpl: ViewModel(), FavouriteViewModel {
    private val repository: AppRepository by lazy { AppRepositoryImpl() }
    override val getAllFavourites = MutableLiveData<Cursor>()
    override val updateCursorLiveData = MutableLiveData<Unit>()

    override fun getAllFavourites() {
        getAllFavourites.value = repository.getAllFavourites()
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        repository.updateDictionary(dictionary)
        getAllFavourites()
    }
}