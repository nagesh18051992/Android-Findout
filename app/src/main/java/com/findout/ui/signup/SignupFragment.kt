package com.findout.ui.signup

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.findout.R
import com.findout.databinding.FragmentSignupBinding
import com.findout.models.UseModel
import com.findout.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private val viewModel: SignupViewModel by viewModels()
    private var _binding:FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindObservers()
    }

    private fun bindObservers() {
        viewModel.appUpdate.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {
                    verifyPage()
                    Timber.d(it.message)
                }
                is NetworkResult.Error -> {
                    Timber.d("error")
                }
                is NetworkResult.Loading -> {
                    Timber.d("loading")
                }
            }
        })
    }

    private fun verifyPage(){
        findNavController().navigate(R.id.action_loginFragment_to_verifyOtpFragment)
    }

    private fun homePage(){
        findNavController().navigate(R.id.action_verifyOtpFragment_to_homeFragment)
    }

    private fun setupClickListeners() {
        binding.signUp.setOnClickListener {
            val phoneNumber = binding.etMobileNo.text.toString()
            val email = binding.etEmail.text.toString()
            val pincode = binding.etPincode.text.toString()
            val name=binding.etName.text.toString()
            val useModel= UseModel()
            useModel.mobile=phoneNumber
            useModel.email=email
            useModel.pincode=pincode
            useModel.name=name
            viewModel.addUser(useModel)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
