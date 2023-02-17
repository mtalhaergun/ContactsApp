package com.example.contactsapp.room

import androidx.room.*
import com.example.contactsapp.data.entity.Kisiler

@Dao
interface KisilerDao {

    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler() : List<Kisiler>

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :aramaKelimesi || '%' ")
    suspend fun kisiArama(aramaKelimesi : String) : List<Kisiler>

    @Insert
    suspend fun kisiEkle(kisi : Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi : Kisiler)

    @Delete
    suspend fun kisiSil(kisi:Kisiler)
}