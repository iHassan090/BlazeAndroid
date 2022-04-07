package com.techworx.blaze.ui.login.activities

import android.os.Bundle
import com.techworx.blaze.activities.BasicActivity
import com.techworx.blaze.databinding.ActivityLoginBinding
import com.techworx.blaze.ui.login.fragments.LoginFragment

class Login : BasicActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        replaceFragment(LoginFragment.getInstance(), "Login Fragment", true)
    }
}