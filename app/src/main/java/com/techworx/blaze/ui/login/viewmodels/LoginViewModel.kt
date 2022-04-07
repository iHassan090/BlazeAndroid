package com.techworx.blaze.ui.login.viewmodels

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var mNetworkCodes: ArrayList<String>? = null
    val mCountryCode = "+92"

    init {
        this.initValues()
    }

    private fun initValues() {
        mNetworkCodes = ArrayList()
        for (i in 300 until 350)
            mNetworkCodes!!.add(mCountryCode + i)
    }
}