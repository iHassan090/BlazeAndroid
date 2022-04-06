package com.techworx.blaze.activities

import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.techworx.blaze.R
import com.techworx.blaze.databinding.ActivitySplashBinding
import java.lang.Thread.sleep

class Splash : BasicActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        // Make Splash screen to appear as full screen activity
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)
    }


    override fun initValues() {
        super.initValues()
        val thread = Thread() {
            run() {
                try {
                    sleep(1500)
                    // TODO Move to next screen
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        val topToBottomAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
        val bottomToTopAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top)
        this.binding.ivIcon.animation = topToBottomAnim
        this.binding.tvTitle.animation = bottomToTopAnim
    }
}