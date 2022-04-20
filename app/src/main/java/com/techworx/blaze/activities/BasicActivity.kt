package com.techworx.blaze.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.techworx.blaze.Blaze
import com.techworx.blaze.R
import com.techworx.blaze.dialog.ProgressDialog
import com.techworx.blaze.fragments.BasicFragment


open class BasicActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var progressDialog: ProgressDialog

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Blaze.activity = this
        this.initValues()
        this.setAppBar()
        this.initValuesInViews()
        this.setClickListeners()
    }

    open fun initValues() {
        this.progressDialog = ProgressDialog(this, getString(R.string.please_wait))
    }

    open fun setAppBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
        window.decorView.setBackgroundColor(Color.WHITE)
    }

    open fun initValuesInViews() {}

    open fun setClickListeners() {}

    override fun onClick(p0: View?) {
    }

    fun addFragment(fragment: BasicFragment, tag: String, addToBackStack: Boolean) {
        val previousAddedFragment = supportFragmentManager.findFragmentByTag(tag)
        if (null != previousAddedFragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(previousAddedFragment)
            transaction.commitAllowingStateLoss()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: BasicFragment, tag: String, addToBackStack: Boolean) {
        val previousAddedFragment = supportFragmentManager.findFragmentByTag(tag)
        if (null != previousAddedFragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(previousAddedFragment)
            transaction.commitAllowingStateLoss()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}