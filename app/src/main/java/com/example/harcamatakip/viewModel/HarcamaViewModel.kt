package com.example.harcamatakip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harcamatakip.api.model.ParaBirimi
import com.example.harcamatakip.api.service.ParaApi
import com.example.harcamatakip.api.service.ParaServiceProvider
import com.example.harcamatakip.data.database.HarcamaDatabase
import com.example.harcamatakip.data.model.Harcama
import com.example.harcamatakip.repository.HarcamaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HarcamaViewModel(application: Application) : AndroidViewModel(application) {

    val tumHarcamalar: LiveData<List<Harcama>>
    private val harcamaRepository: HarcamaRepository
    var kurListesi: MutableLiveData<Response<ParaBirimi>> = MutableLiveData()


    init {
        val api=ParaServiceProvider.apiService
        val harcamaDao = HarcamaDatabase.getDatabase(application).harcamaDao()
        harcamaRepository = HarcamaRepository(harcamaDao,api)
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
    fun getPara(base:String){
        viewModelScope.launch {
            val kurlar = harcamaRepository.getPara(base)
            kurListesi.value = kurlar
        }
    }
}
