package com.example.harcamatakip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.harcamatakip.data.database.HarcamaDatabase
import com.example.harcamatakip.data.model.Harcama
import com.example.harcamatakip.repository.HarcamaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HarcamaViewModel(application: Application) : AndroidViewModel(application) {

    val tumHarcamalar: LiveData<List<Harcama>>
    private val harcamaRepository: HarcamaRepository

    init {
        val harcamaDao = HarcamaDatabase.getDatabase(application).harcamaDao()
        harcamaRepository = HarcamaRepository(harcamaDao)
        tumHarcamalar = harcamaRepository.tumHarcamalar
    }

    fun harcamaEkle(harcama: Harcama) {
        viewModelScope.launch(Dispatchers.IO) {
            harcamaRepository.harcamaEkle(harcama)
        }
    }

    fun harcamaSil(harcama: Harcama) {
        viewModelScope.launch(Dispatchers.IO) {
            harcamaRepository.harcamaSil(harcama)
        }
    }
}
