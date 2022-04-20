package com.techworx.blaze.widgets.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import com.techworx.blaze.Blaze
import com.techworx.blaze.R
import com.techworx.blaze.databinding.ToolbarBinding


class Toolbar : ConstraintLayout, View.OnClickListener, SearchView.OnQueryTextListener {
    private var backButtonEnabled: Boolean = true
    private var canSearch: Boolean = false
    private var isSearchViewBeingDisplayed = false
    private var title: String = ""
    private lateinit var binding: ToolbarBinding
    private lateinit var toolbarListener: ToolbarListener


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
            backButtonEnabled = a.getBoolean(R.styleable.Toolbar_backButtonEnabled, true)
            canSearch = a.getBoolean(R.styleable.Toolbar_canSearch, false)
            if (a.getString(R.styleable.Toolbar_title) != null)
                title = a.getString(R.styleable.Toolbar_title)!!

        } finally {
            a.recycle();
        }
    }

    private fun initViews() {
        binding = ToolbarBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)

        initClickListeners()

        changeBackButtonVisibility()
        changeSearchIconVisibility()
        changeTitleViewVisibility()
    }

    private fun changeBackButtonVisibility() {
        if (backButtonEnabled)
            binding.ivBack.visibility = VISIBLE
        else binding.ivBack.visibility = GONE
    }

    private fun changeSearchIconVisibility() {
        if (canSearch) {
            binding.ivSearch.visibility = VISIBLE
        } else binding.ivSearch.visibility = GONE
    }

    private fun changeTitleViewVisibility() {
        if (title.isEmpty())
            binding.tvTitle.visibility = GONE
        else {
            binding.tvTitle.visibility = VISIBLE
            binding.tvTitle.text = title
        }
    }


    private fun initClickListeners() {
        binding.ivBack.setOnClickListener(this)
        binding.ivSearch.setOnClickListener(this)
        binding.ivClear.setOnClickListener(this)
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            binding.ivBack.id -> onBackClicked()
            binding.ivSearch.id -> onSearchClicked()
            binding.ivClear.id -> onClearClicked()
        }
    }

    private fun onClearClicked() {
        binding.searchView.setQuery("", false)
    }

    private fun onBackClicked() {
        if (isSearchViewBeingDisplayed)
            changeSearchViewVisibility(false)
        else
            Blaze.activity?.onBackPressed()
    }

    private fun onSearchClicked() {
        if (isSearchViewBeingDisplayed) return
        changeSearchViewVisibility(true)
    }

    private fun changeSearchViewVisibility(isVisible: Boolean) {
        isSearchViewBeingDisplayed = isVisible
        if (isVisible) {
            binding.tvTitle.visibility = GONE
            binding.ivSearch.visibility = INVISIBLE
            binding.searchView.visibility = VISIBLE
            binding.searchView.setIconifiedByDefault(false)
            binding.searchView.isIconified = false
        } else {
            binding.tvTitle.visibility = VISIBLE
            binding.ivSearch.visibility = VISIBLE
            binding.searchView.visibility = GONE
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!!.isNotEmpty())
            binding.ivClear.visibility = VISIBLE
        else
            binding.ivClear.visibility = GONE
        toolbarListener.onQueryChanged(newText)
        return false
    }

    fun setToolbarListener(listener: ToolbarListener) {
        toolbarListener = listener
    }
}