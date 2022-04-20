package com.techworx.blaze.ui.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techworx.blaze.models.KeyValue
import com.techworx.blaze.network.EndPoints.SEND_OTP
import com.techworx.blaze.network.RequestBuilder
import com.techworx.blaze.network.RetroHelper
import com.techworx.blaze.ui.country.CountriesHelper
import com.techworx.blaze.ui.country.models.Country


class LoginViewModel : ViewModel() {
    private lateinit var userCountry: MutableLiveData<Country>
    private lateinit var phoneNumberPattern: MutableLiveData<String>
    private lateinit var exampleNumber: MutableLiveData<String>
    private lateinit var responseObserver: MutableLiveData<KeyValue>
    private lateinit var errorObserver: MutableLiveData<KeyValue>
    private lateinit var progressObserver: MutableLiveData<KeyValue>


    init {
        this.initValues()
    }

    private fun initValues() {
        responseObserver = MutableLiveData()
        errorObserver = MutableLiveData()
        progressObserver = MutableLiveData()
        userCountry = MutableLiveData()
        phoneNumberPattern = MutableLiveData()
        exampleNumber = MutableLiveData()
        setUserCountry(null)
    }

    fun submitForm(phoneNumber: String) {
        RetroHelper().getInstance()
            .sendApiCall(
                SEND_OTP,
                RequestBuilder.getLoginData(phoneNumber),
                progressObserver,
                responseObserver,
                errorObserver
            )
    }

    fun getUserCountry(): MutableLiveData<Country> {
        return userCountry
    }

    fun getPhoneNumberPattern(): MutableLiveData<String> {
        return phoneNumberPattern
    }

    fun getExampleNumber(): MutableLiveData<String> {
        return exampleNumber
    }

    fun setUserCountry(selectedCountry: Country?) {
        var country: Country? = selectedCountry
        if (selectedCountry == null)
            country = CountriesHelper.getUserCountryInfo()

        userCountry.value = country!!
        phoneNumberPattern.value =
            CountriesHelper.getPhoneNumberPattern(
                userCountry.value!!.countryISO,
                userCountry.value!!.countryCode
            )
        exampleNumber.value = CountriesHelper.exampleNumber?.nationalNumber.toString()
    }

    fun getResponseObserver(): MutableLiveData<KeyValue> {
        return responseObserver
    }

}