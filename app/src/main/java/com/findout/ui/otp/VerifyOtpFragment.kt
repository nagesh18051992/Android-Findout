package com.findout.ui.otp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.findout.R
import com.findout.databinding.FragmentVerifyotpBinding
import com.findout.models.VerifyOtpModel
import com.findout.utils.NetworkResult


class VerifyOtpFragment :Fragment() {

    private val viewModel: VerifyOtpViewModel by viewModels()
    private var _binding:FragmentVerifyotpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentVerifyotpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindObservers()
    }

    private fun setupClickListeners() {
        binding.verify.setOnClickListener {
           otpTextWatcher
        }
    }


    private fun bindObservers() {
        viewModel.appUpdate.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {
                    homePage()
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun homePage(){
        findNavController().navigate(R.id.action_verifyOtpFragment_to_homeFragment)
    }


    private var otpTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.toString()?.let {
                if (it.length == 6) {
                    validateOtpLength(it)
                }
            }
        }
    }

    fun validateOtpLength(otp: String) {
        if (otp.length == 6) {
            val verifyOtpModel=VerifyOtpModel()
            verifyOtpModel.userId=""
            verifyOtpModel.otp=otp

            viewModel.fetchVerifyOtp(verifyOtpModel)
        }
    }
}