package com.findout.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.findout.R
import com.findout.databinding.FragmentLoginBinding
import com.findout.models.LoginOtpModel
import com.findout.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentLoginBinding.inflate(inflater, container, false)
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
        binding.signIn.setOnClickListener {
            val phoneNumber = binding.etMobileNo.text.toString()
            val loginOtpModel=LoginOtpModel()
            loginOtpModel.mobile=phoneNumber
            viewModel.loginWithOtp(loginOtpModel)
        }
    }

    private fun bindObservers() {
        viewModel.appUpdate.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {
                    verifyPage()
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun verifyPage(){
        findNavController().navigate(R.id.action_loginFragment_to_verifyOtpFragment)
    }
}