package com.techworx.blaze.ui.country.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.techworx.blaze.activities.BasicActivity
import com.techworx.blaze.databinding.ActivityCountrySelectionBinding
import com.techworx.blaze.interfaces.RecyclerViewListener
import com.techworx.blaze.ui.country.CountriesHelper
import com.techworx.blaze.ui.country.adapters.CountriesAdapter
import com.techworx.blaze.ui.country.models.Country
import com.techworx.blaze.widgets.toolbar.ToolbarListener

class CountrySelection : BasicActivity(), ToolbarListener, RecyclerViewListener {
    private lateinit var binding: ActivityCountrySelectionBinding
    private val countries = CountriesHelper.getAllCountries()
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountrySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initValues() {
        super.initValues()
        adapter = CountriesAdapter(this, countries, this)
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun setClickListeners() {
        super.setClickListeners()
        binding.toolbar.setToolbarListener(this)
    }

    override fun onQueryChanged(text: String) {
        adapter.filter.filter(text)
    }

    override fun onItemClicked(item: Any) {
        val intent = Intent()
        intent.putExtra("country", item as Country)
        setResult(1, intent)
        finish()
    }
}