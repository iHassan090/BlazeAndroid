package com.techworx.blaze.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techworx.blaze.databinding.LoginFragmentBinding
import com.techworx.blaze.fragments.BasicFragment
import com.techworx.blaze.helpers.Utils
import com.techworx.blaze.models.KeyValue
import com.techworx.blaze.ui.country.activities.CountrySelection
import com.techworx.blaze.ui.country.models.Country
import com.techworx.blaze.ui.login.viewmodels.LoginViewModel

class LoginFragment : BasicFragment(), TextWatcher {

    companion object {
        var mInstance: LoginFragment? = null
        private fun newInstance() = LoginFragment()
        fun getInstance(): LoginFragment {
            if (mInstance == null)
                mInstance = newInstance()
            return mInstance!!
        }
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private var patternLength: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initValues() {
        super.initValues()
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.getUserCountry().observe(this, userCountryObserver)
        viewModel.getPhoneNumberPattern().observe(this, phonePatternObserver)
        viewModel.getExampleNumber().observe(this, hintObserver)
        viewModel.getResponseObserver().observe(this, responseObserver)
    }

    override fun initClickListeners() {
        super.initClickListeners()
        binding.btnNext.setOnClickListener(this)
        binding.countrySelector.setOnClickListener(this)
        binding.etPhone.addTextChangedListener(this)
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        val id = view?.id
        if (id == binding.btnNext.id) onNextClicked()
        else if (id == binding.countrySelector.id) onCountrySelectorClicked()
    }

    private fun onCountrySelectorClicked() {
        val intent = Intent(activity, CountrySelection::class.java)
        startActivityForResult(intent, 1)
    }

    private fun onNextClicked() {
        Utils.hideKeyboard(requireActivity())

        val phoneNumber = binding.tvDialCode.text.toString() + binding.etPhone.rawText

        viewModel.submitForm(phoneNumber)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        if (p0!!.isNotEmpty() && p0.length == patternLength)
            enableButton(binding.btnNext)
        else
            disableButton(binding.btnNext)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedCountry = data!!.getParcelableExtra<Country>("country")
        viewModel.setUserCountry(selectedCountry)
    }

    private val userCountryObserver = Observer<Country> {
        onUserCountrySelected(it)
    }

    private val phonePatternObserver = Observer<String> {
        onPhonePattern(it)
    }

    private val hintObserver = Observer<String> { onHint(it) }

    private fun onUserCountrySelected(country: Country) {
        binding.etPhone.setText("")
        binding.tvDialCode.text = country.countryCode
    }

    private fun onPhonePattern(pattern: String) {
        binding.etPhone.pattern = pattern
        binding.etPhone.specialChar = "#"
        binding.etPhone.filters = arrayOf<InputFilter>(LengthFilter(pattern.length))
        patternLength = pattern.length
    }

    private fun onHint(hint: String) {
        binding.etPhone.hint = hint
    }

    private val responseObserver = Observer<KeyValue> {

    }

}