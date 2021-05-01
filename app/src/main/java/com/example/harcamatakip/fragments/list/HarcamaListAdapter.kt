package com.example.harcamatakip.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.harcamatakip.R
import com.example.harcamatakip.api.Constant
import com.example.harcamatakip.api.model.ParaBirimi
import com.example.harcamatakip.data.model.Harcama
import kotlin.math.round

class HarcamaListAdapter() : RecyclerView.Adapter<HarcamaListAdapter.MyViewHolder>() {

    private var harcamaListesi = emptyList<Harcama>()
    private var kurListesi = listOf<ParaBirimi>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val urunAciklama = itemView.findViewById<TextView>(R.id.urunAciklama_txt)
        val urunTutar = itemView.findViewById<TextView>(R.id.urunTutar_txt)
        val urunResim = itemView.findViewById<ImageView>(R.id.urun_icon)
        val urunTiklandi = itemView.findViewById<ConstraintLayout>(R.id.urun_bilgisi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val harcama = harcamaListesi[position]
        val kur = kurListesi.firstOrNull()

        when (harcama.paraBirimi) {

            Constant.TL -> holder.urunTutar.text =
                kur?.rates?.tRY?.let { round(harcama.urunTutar.div(it)).toString() }
            Constant.EURO -> holder.urunTutar.text =
                kur?.rates?.eUR?.let { round(harcama.urunTutar.div(it)).toString() }
            Constant.DOLAR -> holder.urunTutar.text =
                kur?.rates?.uSD?.let { round(harcama.urunTutar.div(it)).toString() }
            Constant.STERLIN -> holder.urunTutar.text =
                kur?.rates?.gBP?.let { round(harcama.urunTutar.div(it)).toString() }
        }

        holder.urunAciklama.text = harcama.urunAciklama

        holder.urunTiklandi.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetayFragment(
                0,
                holder.urunTutar.text.toString(),
                holder.urunAciklama.text.toString()
            )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return harcamaListesi.size
    }

    fun setKur(rates: ParaBirimi) {
        kurListesi = listOf(rates)
        Log.v("api", "success" + kurListesi[0].rates.uSD)
        notifyDataSetChanged()
    }

    //kullancağımız harcama ürün listesini dışarıdan aldık
    fun setData(harcama: List<Harcama>) {
        harcamaListesi = harcama
        notifyDataSetChanged()
    }
}