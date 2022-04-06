package com.techworx.blaze.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity : AppCompatActivity(), View.OnClickListener {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        this.initValues()
        this.setAppBar()
        this.initValuesInViews()
        this.setClickListeners()
    }

    open fun initValues() {}

    open fun setAppBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
        window.decorView.setBackgroundColor(Color.WHITE)
    }

    open fun initValuesInViews() {}

    open fun setClickListeners() {}

    override fun onClick(p0: View?) {
    }
}