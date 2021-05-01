package com.example.harcamatakip.api.service

import com.example.harcamatakip.api.model.ParaBirimi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ParaApi {
    @GET("/latest")
    suspend fun getPara(
        @Query("base") base:String
    ): Response<ParaBirimi>
}