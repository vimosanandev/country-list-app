package com.example.countrylistapp.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylistapp.databinding.CountryItemBinding
import com.example.countrylistapp.databinding.LabelTextBinding
import com.example.countrylistapp.model.Country
import com.example.countrylistapp.model.CountryItem

class CountriesAdapter :
    ListAdapter<CountryItem, RecyclerView.ViewHolder>(CountryDiffCallback()) {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_COUNTRY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CountryItem.Header -> TYPE_HEADER
            is CountryItem.ItemCountry -> TYPE_COUNTRY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val labelTextBinding =
                    LabelTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CountryLabelViewHolder(labelTextBinding)
            }

            TYPE_COUNTRY -> {
                val binding =
                    CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CountryViewHolder(binding)
            }

            else -> throw IllegalArgumentException("INVALID VIEW TYPE")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CountryItem.Header -> (holder as CountryLabelViewHolder).bind(item.startsAt)
            is CountryItem.ItemCountry -> (holder as CountryViewHolder).bind(item.country)
        }
    }

    class CountryViewHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.country = country
        }
    }

    class CountryLabelViewHolder(private val binding: LabelTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(startAt: String) {
            binding.countryLabel.text = startAt
        }
    }

    class CountryDiffCallback : DiffUtil.ItemCallback<CountryItem>() {
        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return when {
                oldItem is CountryItem.Header && newItem is CountryItem.Header -> oldItem.startsAt == newItem.startsAt
                oldItem is CountryItem.ItemCountry && newItem is CountryItem.ItemCountry -> oldItem.country.code == newItem.country.code
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem == newItem
        }
    }
}