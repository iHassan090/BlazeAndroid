package com.techworx.blaze.ui.country.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.techworx.blaze.databinding.RowCountryBinding
import com.techworx.blaze.interfaces.RecyclerViewListener
import com.techworx.blaze.ui.country.models.Country
import java.util.*
import kotlin.collections.ArrayList

class CountriesAdapter(
    private val context: Context,
    private val countries: ArrayList<Country>,
    private val listener: RecyclerViewListener
) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>(), Filterable {
    private lateinit var binding: RowCountryBinding
    private var filteredCountries: ArrayList<Country> = countries

    inner class CountryViewHolder(val binding: RowCountryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = RowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(holder) {
            val country = filteredCountries[position]
            binding.name.text = country.countryName
            binding.flag.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    country.flagDrawable
                )
            )
            binding.parent.setOnClickListener {
                listener.onItemClicked(filteredCountries[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredCountries.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty())
                    filteredCountries = countries
                else {
                    val filteredList: ArrayList<Country> = ArrayList()
                    for (country in countries) {
                        if (country.countryISO.equals(
                                charString,
                                ignoreCase = true
                            ) || country.countryName.toLowerCase(Locale.getDefault())
                                .contains(charString.toLowerCase(Locale.getDefault()))
                        ) {
                            filteredList.add(country)
                        }
                    }
                    filteredCountries = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredCountries
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredCountries = filterResults.values as ArrayList<Country>
                notifyDataSetChanged()
            }
        }
    }
}