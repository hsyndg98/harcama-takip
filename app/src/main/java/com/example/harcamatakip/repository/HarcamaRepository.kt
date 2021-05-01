package com.example.harcamatakip.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.harcamatakip.api.Constant
import com.example.harcamatakip.api.model.ParaBirimi
import com.example.harcamatakip.api.service.ParaApi
import com.example.harcamatakip.api.service.ParaServiceProvider
import com.example.harcamatakip.data.database.HarcamaDao
import com.example.harcamatakip.data.model.Harcama
import retrofit2.Response

class HarcamaRepository(private val harcamaDao: HarcamaDao,private val api: ParaApi) {

    val tumHarcamalar:LiveData<List<Harcama>> = harcamaDao.harcamaListesi()

    suspend fun harcamaEkle(harcama: Harcama){
        harcamaDao.harcamaEkle(harcama)
    }
    suspend fun harcamaSil(harcama: Harcama){
        harcamaDao.harcamaSil(harcama)
    }

    suspend fun getPara(base:String):Response<ParaBirimi>{
        return api.getPara(base)
    }
}