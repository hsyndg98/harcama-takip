package com.example.harcamatakip.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HarcamaViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HarcamaViewModel::class.java)){
            return HarcamaViewModel(application) as T
        }
        throw IllegalArgumentException("Beklenmedik sınıf")
    }
}