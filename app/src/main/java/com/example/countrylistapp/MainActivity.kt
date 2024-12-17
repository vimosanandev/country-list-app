package com.example.countrylistapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrylistapp.countries.CountriesAdapter
import com.example.countrylistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModel.provideFactory((application as CountryListApplication).appContainer.countriesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = countriesViewModel
        binding.lifecycleOwner = this


        val recyclerViewAdapter = CountriesAdapter()
        binding.countryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.countryRecyclerView.adapter = recyclerViewAdapter

        countriesViewModel.countries.observe(this, { countries ->
            countries?.let {
                recyclerViewAdapter.submitList(it)
            }
        })

        countriesViewModel.fetchCountries()
    }
}