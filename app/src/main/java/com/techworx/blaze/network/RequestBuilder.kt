package com.techworx.blaze.network

import com.techworx.blaze.network.data.LoginData

object RequestBuilder {
    fun getLoginData(phoneNumber: String): LoginData {
        return LoginData(phoneNumber)
    }
}