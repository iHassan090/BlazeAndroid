package com.techworx.blaze.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.techworx.blaze.databinding.LoginFragmentBinding
import com.techworx.blaze.fragments.BasicFragment
import com.techworx.blaze.helpers.Utils
import com.techworx.blaze.ui.login.viewmodels.LoginViewModel

class LoginFragment : BasicFragment() {

    companion object {
        var mInstance: LoginFragment? = null
        private fun newInstance() = LoginFragment()
        fun getInstance(): LoginFragment {
            if (mInstance == null)
                mInstance = newInstance()
            return mInstance!!;
        }
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun initValuesInViews() {
        super.initValuesInViews()
        binding.tvDialCode.text = viewModel.mCountryCode
        binding.etPhone.requestFocus()
        Utils.forceShowKeyboard(requireContext())

        disableButton(binding.btnNext)

    }

}