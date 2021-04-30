package com.example.harcamatakip.repository

import androidx.lifecycle.LiveData
import com.example.harcamatakip.data.database.HarcamaDao
import com.example.harcamatakip.data.model.Harcama

class HarcamaRepository(private val harcamaDao: HarcamaDao) {

    val tumHarcamalar:LiveData<List<Harcama>> = harcamaDao.harcamaListesi()

    suspend fun harcamaEkle(harcama: Harcama){
        harcamaDao.harcamaEkle(harcama)
    }
    suspend fun harcamaSil(harcama: Harcama){
        harcamaDao.harcamaSil(harcama)
    }
}