package com.example.harcamatakip.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.harcamatakip.R
import com.example.harcamatakip.data.model.Harcama

class HarcamaListAdapter: RecyclerView.Adapter<HarcamaListAdapter.MyViewHolder>() {

    private var harcamaListesi = emptyList<Harcama>()

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val harcama = harcamaListesi[position]

        holder.itemView.findViewById<TextView>(R.id.urunAciklama_txt).text = harcama.urunAciklama
        holder.itemView.findViewById<TextView>(R.id.urunTutar_txt).text = harcama.urunTutar.toString()

    }

    override fun getItemCount(): Int {
        return harcamaListesi.size
    }

    //kullancağımız harcama ürün listesini dışarıdan aldık
    fun setData(harcama: List<Harcama>){
        harcamaListesi = harcama
        notifyDataSetChanged()
    }
}