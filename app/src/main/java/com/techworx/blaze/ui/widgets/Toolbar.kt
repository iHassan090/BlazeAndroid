package com.techworx.blaze.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.techworx.blaze.Blaze
import com.techworx.blaze.R
import com.techworx.blaze.databinding.ToolbarBinding


class Toolbar : ConstraintLayout {
    private var backgroundEnabled: Boolean = false
    private lateinit var binding: ToolbarBinding

    constructor(context: Context) : super(context) {
        this.getAttributes(null)
        this.initViews()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.getAttributes(attrs)
        this.initViews()
    }

    constructor(
        context: Context, attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        this.getAttributes(attrs)
        this.initViews()
    }

    private fun getAttributes(attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Toolbar, 0, 0
        )

        try {
            //get the text and colors specified using the names in attrs.xml
            backgroundEnabled = a.getBoolean(R.styleable.Toolbar_backgroundEnabled, false)
        } finally {
            a.recycle();
        }
    }

    private fun initViews() {
        binding = ToolbarBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)

        binding.ivBack.setOnClickListener {
            return@setOnClickListener
            Blaze.activity!!.onBackPressed()
        }
    }
}