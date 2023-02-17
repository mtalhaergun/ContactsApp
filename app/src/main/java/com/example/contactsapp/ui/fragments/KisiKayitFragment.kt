package com.example.contactsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentKisiKayitBinding
import com.example.contactsapp.ui.viewmodel.AnasayfaViewModel
import com.example.contactsapp.ui.viewmodel.KisiKayitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KisiKayitFragment : Fragment() {

    private lateinit var binding : FragmentKisiKayitBinding
    private lateinit var viewModel : KisiKayitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : KisiKayitViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_kayit,container,false)
        binding.kisiKayitFragment = this
        binding.kisiKayitToolbarBaslik = "Kişi Kayıt"


        return binding.root
    }

    fun kaydet(kisi_ad : String, kisi_tel : String){
        viewModel.kayit(kisi_ad,kisi_tel)
    }

}