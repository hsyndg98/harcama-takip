package com.example.harcamatakip.fragments.detay

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.harcamatakip.R
import com.example.harcamatakip.databinding.FragmentDetayBinding
import com.example.harcamatakip.viewModel.HarcamaViewModel
import com.example.harcamatakip.viewModel.HarcamaViewModelFactory


class DetayFragment : Fragment() {

    private lateinit var mUrunViewModel: HarcamaViewModel
    private var _binding: FragmentDetayBinding?=null
    private val binding get() = _binding!!
    private val args by navArgs<DetayFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetayBinding.inflate(inflater,container,false)

        //datayı sileceğimiz view model
        val application = requireNotNull(this.activity).application
        val urunFactory = HarcamaViewModelFactory(application)
        mUrunViewModel = ViewModelProvider(this,urunFactory).get(HarcamaViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.urunAciklamaDetayTxt.text = args.harcama.urunAciklama
        binding.urunFiyatDetayTxt.text = args.harcama.urunTutar.toString()

        binding.silBtn.setOnClickListener {
            urunSil()
        }
    }
    fun urunSil(){
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Harcama Silme")
        alertDialog.setMessage("Harcama listenizden silinecek!")

        alertDialog.setPositiveButton("Tamam", DialogInterface.OnClickListener { dialog, which ->
            mUrunViewModel.harcamaSil(args.harcama)
            Toast.makeText(requireContext(),"Başarıyla silindi", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_detayFragment_to_listFragment)
        })
        alertDialog.setNegativeButton("Vazgeç",null)

        val uyari = alertDialog.create()
        uyari.show()

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}