package com.findout.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.findout.databinding.FragmentLandingBinding
import com.findout.ui.base.BaseFragment
import com.findout.ui.login.LoginFragment
import com.findout.ui.signup.SignupFragment
import com.findout.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LandingFragment : BaseFragment() {
    private val viewModel: LandingViewModel by viewModels()
    private var _binding:FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
         binding.signIn.setOnClickListener {
             val fragment =LoginFragment.newInstance()
             fragment.isCancelable = false
             parentFragmentManager.let {
                 fragment.show(it,LoginFragment.TAG)
             }
         }
         binding.singUp.setOnClickListener {
             val fragment = SignupFragment.newInstance()
             fragment.isCancelable = false
             parentFragmentManager.let {
                 fragment.show(it,SignupFragment.TAG)
             }
         }
    }

    private fun bindObservers() {
        viewModel.appUpdate.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {

                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

}