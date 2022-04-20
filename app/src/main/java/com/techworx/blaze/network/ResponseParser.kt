package com.techworx.blaze.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techworx.blaze.network.EndPoints.SEND_OTP
import com.techworx.blaze.network.response.LoginResponse
import com.techworx.blaze.network.response.Response

object ResponseParser {
    fun parseResponse(tag: String, response: Response): Any {
        return when (tag) {
            SEND_OTP -> {
                val loginResponse: LoginResponse = Gson().fromJson(response.returnData.toString())
                loginResponse
            }
            else -> ""
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)
}