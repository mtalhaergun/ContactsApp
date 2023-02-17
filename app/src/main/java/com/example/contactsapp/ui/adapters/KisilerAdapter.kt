package com.example.contactsapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.data.entity.Kisiler
import com.example.contactsapp.databinding.CardTasarimBinding
import com.example.contactsapp.ui.fragments.AnasayfaFragmentDirections
import com.example.contactsapp.ui.viewmodel.AnasayfaViewModel
import com.example.contactsapp.util.gecisYap
import com.google.android.material.snackbar.Snackbar

class KisilerAdapter (var mContext : Context, var kisilerListesi : List<Kisiler>, val viewModel : AnasayfaViewModel) : RecyclerView.Adapter<KisilerAdapter.CardTasarimHolder>() {
    inner class CardTasarimHolder(binding: CardTasarimBinding) : RecyclerView.ViewHolder(binding.root){
        lateinit var binding : CardTasarimBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding : CardTasarimBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim,parent,false)
        return CardTasarimHolder(binding)
    }

    override fun getItemCount(): Int {
        return kisilerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimHolder, position: Int) {
        val kisi = kisilerListesi.get(position)
        holder.binding.kisiNesnesi = kisi

        holder.binding.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.kisiDetayGecis(kisi = kisi)
            Navigation.gecisYap(it,gecis)
        }

        holder.binding.imageViewSil.setOnClickListener {
            Snackbar.make(it,"${kisi.kisi_ad} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    viewModel.sil(kisi.kisi_id)
                }.show()
        }
    }

}