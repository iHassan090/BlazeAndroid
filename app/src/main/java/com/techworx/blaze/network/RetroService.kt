package com.techworx.blaze.network

import com.techworx.blaze.network.EndPoints.SEND_OTP
import com.techworx.blaze.network.data.LoginData
import com.techworx.blaze.network.response.Response
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroService {

    @POST(SEND_OTP)
    fun login(@Body login: LoginData): Observable<Response>
}