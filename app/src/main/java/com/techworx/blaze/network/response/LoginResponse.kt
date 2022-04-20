package com.techworx.blaze.network.response

data class LoginResponse(val otp: String, val token: Token)
data class Token(val authToken: String, val expireTime: String)
