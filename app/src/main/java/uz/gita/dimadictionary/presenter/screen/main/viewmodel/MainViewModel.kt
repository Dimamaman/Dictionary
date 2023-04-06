package uz.gita.dimadictionary.presenter.screen.main.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dimadictionary.data.source.local.entity.DictionaryEntity

interface MainViewModel {
    fun getAllWord(): LiveData<List<DictionaryEntity>>
}