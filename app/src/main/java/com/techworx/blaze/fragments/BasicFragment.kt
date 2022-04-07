package com.techworx.blaze.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.techworx.blaze.R


open class BasicFragment : Fragment(), View.OnClickListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.initViewModel()
        this.initValues()
        this.initValuesInViews()
        this.setOnViewClickListener()
    }

    open fun initViewModel() {

    }

    open fun initValues() {}

    open fun initValuesInViews() {}

    open fun setOnViewClickListener() {}

    open fun enableButton(button: Button) {
        button.isEnabled = true
        button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        button.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_enabled);
    }

    open fun disableButton(button: Button) {
        button.isEnabled = false
        button.setTextColor(ContextCompat.getColor(requireContext(), R.color.ghost))
        button.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_disabled)
    }

    override fun onClick(p0: View?) {

    }
}