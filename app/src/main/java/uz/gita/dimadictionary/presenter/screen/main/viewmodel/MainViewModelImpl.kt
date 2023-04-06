package uz.gita.dimadictionary.presenter.screen.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity
import uz.gita.dimadictionary.domain.repository.AppRepository
import uz.gita.dimadictionary.domain.repository.AppRepositoryImpl

class MainViewModelImpl: ViewModel(), MainViewModel {
    private val repository: AppRepository by lazy { AppRepositoryImpl() }

    override fun getAllWord(): LiveData<List<DictionaryEntity>> {
//        _allWords.value = repository.getAllWords()
        return repository.getAllWords()
    }


}