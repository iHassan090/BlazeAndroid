package com.techworx.blaze

import android.app.Application
import com.techworx.blaze.activities.BasicActivity

class Blaze : Application() {
    companion object {
        var activity: BasicActivity? = null
    }
}