package com.example.harcamatakip.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.harcamatakip.data.model.Harcama

@Dao
interface HarcamaDao {
    @Insert
    suspend fun harcamaEkle(harcama: Harcama)

    @Delete
    suspend fun harcamaSil(harcama: Harcama)

    @Query("SELECT * FROM harcama_table ORDER BY urunId ASC")
    fun harcamaListesi(): LiveData<List<Harcama>>
}