package com.techworx.blaze.helpers

import com.google.gson.Gson

object Constants {
    const val SPLASH_TIME: Long = 1500
    fun convertObjectToJSON(value: Any): String {
        val gson = Gson()
        return gson.toJson(value).toString()
    }
}