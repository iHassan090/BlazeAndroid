package com.techworx.blaze.helpers

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity


class Utils {
    companion object {
        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view: View? = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun showKeyboard(activity: Activity) {
            val view: View? = activity.currentFocus
            val methodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }

        fun showKeyboard(activity: FragmentActivity) {
            val view: View? = activity.currentFocus
            val methodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }

        fun forceShowKeyboard(context: Context) {
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }
}