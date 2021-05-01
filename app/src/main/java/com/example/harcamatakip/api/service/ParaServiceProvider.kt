package com.example.harcamatakip.api.service

import com.example.harcamatakip.api.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ParaServiceProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ParaApi by lazy {
        retrofit.create(ParaApi::class.java)
    }
 }