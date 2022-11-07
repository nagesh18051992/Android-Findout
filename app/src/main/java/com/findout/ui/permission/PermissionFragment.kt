package com.findout.ui.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.findout.databinding.FragmentPermissionsBinding
import com.findout.utils.NetworkResult
import androidx.lifecycle.Observer
import com.findout.ui.base.BaseFragment
import android.Manifest
import android.content.Context
import androidx.navigation.fragment.findNavController
import com.findout.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionFragment : BaseFragment() {

    private val viewModel:PermissionViewModel by viewModels()
    private var _binding:FragmentPermissionsBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG: String = PermissionFragment::class.java.simpleName
        var PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.agree.setOnClickListener {
            activity?.let {
                if (hasPermissions(activity as Context, PERMISSIONS)) {
                    login()
                } else {
                    permReqLauncher.launch(PERMISSIONS)
                }
            }
        }

        bindObservers()
    }

    private fun login(){
        findNavController().navigate(R.id.action_permissionFragment_to_loginFragment)
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

    private val permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
               login()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}