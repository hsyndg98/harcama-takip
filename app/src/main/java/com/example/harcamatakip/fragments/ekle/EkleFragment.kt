package com.example.harcamatakip.fragments.ekle

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.harcamatakip.R
import com.example.harcamatakip.data.model.Harcama
import com.example.harcamatakip.databinding.FragmentEkleBinding
import com.example.harcamatakip.viewModel.HarcamaViewModel
import com.example.harcamatakip.viewModel.HarcamaViewModelFactory


class EkleFragment : Fragment() {
    private lateinit var mHarcamaViewModel: HarcamaViewModel
    private var _binding: FragmentEkleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEkleBinding.inflate(inflater, container, false)

        //harcamaViewModel
        val application = requireNotNull(this.activity).application
        val factory = HarcamaViewModelFactory(application)
        mHarcamaViewModel = ViewModelProvider(this, factory).get(HarcamaViewModel::class.java)

        binding.ekleBtn.setOnClickListener {
            harcamaKaydet()
        }
        return binding.root
    }

    //eklenen harcamayı rooma ekliyoruz
    fun harcamaKaydet() {
        val urunAciklama = binding.urunAciklamaEt2.text.toString()
        val urunTutar = binding.urunTutarEt2.text
        val radioId = binding.urunTip
        val urunTip = radioId.findViewById<RadioButton>(radioId.checkedRadioButtonId)
        val paraTipId = binding.paraBirimiRb
        val paraTip = paraTipId.findViewById<RadioButton>(paraTipId.checkedRadioButtonId)

        if (!bosMu(urunAciklama, urunTutar!!, urunTip.text.toString(), paraTip.text.toString())) {
            val harcama = Harcama(
                0, urunAciklama,
                urunTutar.toString().toDouble(),
                urunTip.text.toString(),
                paraTip.text.toString()
            )
            mHarcamaViewModel.harcamaEkle(harcama)
            Toast.makeText(requireContext(), "Harcama başarıyla eklendi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_ekleFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), " Lütfen boş alan bırakmayınız.", Toast.LENGTH_LONG)
                .show()
        }
    }


    //eksik bilgi var mı kontrol ediliyor
    fun bosMu(
        urunAciklama: String,
        urunTutar: Editable,
        urunTip: String,
        paraTip: String
    ): Boolean {
        if (urunAciklama.isEmpty() || urunTutar.isEmpty() || urunTip.isEmpty() || paraTip.isEmpty()) {
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}