package com.example.contactsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.data.entity.Kisiler
import com.example.contactsapp.databinding.FragmentAnasayfaBinding
import com.example.contactsapp.ui.adapters.KisilerAdapter
import com.example.contactsapp.ui.viewmodel.AnasayfaViewModel
import com.example.contactsapp.ui.viewmodel.KisiKayitViewModel
import com.example.contactsapp.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentAnasayfaBinding
    private lateinit var viewModel : AnasayfaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)
        binding.anasayfaToolbarBaslik = "Kişiler"
        binding.anasayfaFragment = this
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val adapter = KisilerAdapter(requireContext(),it, viewModel)
            binding.kisilerAdapter = adapter
        }


        // MENU BAĞLAMA İŞLEMLERİ VE SEARCHVİEW
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)


        return binding.root
    }

    fun fabTikla(it : View){
        Navigation.gecisYap(it,R.id.kisiKayitGecis)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }

}