package com.example.harcamatakip.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harcama_table")
class Harcama(
    @PrimaryKey(autoGenerate = true)
    var urunId: Int,
    val urunAciklama: String,
    val urunTutar: Double,
    val urunTip: String,
    val paraBirimi: String
)