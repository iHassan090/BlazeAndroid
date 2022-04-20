package com.techworx.blaze.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.techworx.blaze.R
import com.techworx.blaze.databinding.LayoutProgressBinding


class ProgressDialog(val context: Context, val title: String) {
    private lateinit var dialog: Dialog
    private lateinit var binding: LayoutProgressBinding

    fun show(): Dialog {
        binding = LayoutProgressBinding.inflate(LayoutInflater.from(context))

        if (!title.isNullOrEmpty())
            binding.title.text = title

        binding.parent.setBackgroundColor(Color.parseColor("#60000000"))
        binding.cardView.setBackgroundColor(Color.parseColor("#70000000"))

        this.setColorFilter(
            binding.progressBar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        )

        binding.title.setTextColor(Color.WHITE)

        this.dialog = Dialog(context, R.style.ProgressDialogTheme)
        this.dialog.setContentView(binding.root)
        this.dialog.setCancelable(false)
        this.dialog.show()

        return this.dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= 29) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}