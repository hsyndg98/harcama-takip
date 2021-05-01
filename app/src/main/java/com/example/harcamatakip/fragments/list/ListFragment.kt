package com.example.harcamatakip.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harcamatakip.R
import com.example.harcamatakip.api.Constant
import com.example.harcamatakip.api.model.ParaBirimi
import com.example.harcamatakip.data.database.HarcamaDatabase
import com.example.harcamatakip.databinding.FragmentListBinding
import com.example.harcamatakip.viewModel.HarcamaViewModel
import com.example.harcamatakip.viewModel.HarcamaViewModelFactory
import retrofit2.Response


class ListFragment : Fragment() {

    private lateinit var mHarcamaViewModel: HarcamaViewModel
    private val adapter = HarcamaListAdapter()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)



        //harcamaViewModel
        val application = requireNotNull(this.activity).application
        val factory = HarcamaViewModelFactory(application)
        mHarcamaViewModel = ViewModelProvider(this, factory).get(HarcamaViewModel::class.java)

        //recyclerView
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        mHarcamaViewModel.tumHarcamalar.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })


        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_ekleFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnDolar.setOnClickListener {
            apidenKurGetir("USD")
        }

        binding.btnTL.setOnClickListener {
            apidenKurGetir("TRY")
        }
        binding.btnEuro.setOnClickListener {
            apidenKurGetir("EUR")
        }
        binding.btnSterlin.setOnClickListener {
            apidenKurGetir("GBP")
        }

    }

    private fun apidenKurGetir(base:String) {
        mHarcamaViewModel.getPara(base)
        mHarcamaViewModel.kurListesi.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    adapter.setKur(it)
                }
            } else {
                Log.d("api", "Failed")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}