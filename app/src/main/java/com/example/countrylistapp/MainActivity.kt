package com.example.countrylistapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylistapp.countries.CountriesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerViewAdapter = CountriesAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.countryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recyclerViewAdapter
    }
}