package com.example.contactsapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.contactsapp.data.entity.Kisiler
import com.example.contactsapp.room.KisilerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KisilerDaoRepository (var kdao : KisilerDao) {

    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    // Repodan viewmodele listeyi aktaracak fonksiyon
    fun kisileriGetir() : MutableLiveData<List<Kisiler>>{
        return kisilerListesi
    }

    fun kisiKayit(kisi_ad : String, kisi_tel : String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0,kisi_ad,kisi_tel)
            kdao.kisiEkle(yeniKisi)
        }
    }

    fun kisiGuncelle(kisi_id : Int, kisi_ad : String, kisi_tel : String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisi_id,kisi_ad,kisi_tel)
            kdao.kisiGuncelle(guncellenenKisi)
        }
    }

    fun kisiAra(aramaKelimesi : String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = kdao.kisiArama(aramaKelimesi)
        }
    }

    fun kisiSil(kisi_id: Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(kisi_id,"","")
            kdao.kisiSil(silinenKisi)
            tumKisileriAl()
        }
    }

    fun tumKisileriAl(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = kdao.tumKisiler()
        }
    }
}