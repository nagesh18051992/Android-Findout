package com.findout.ui.signup

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.findout.R
import com.findout.databinding.FragmentSignupBinding
import com.findout.models.UseModel
import com.findout.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignupFragment : DialogFragment() {

    private val viewModel: SignupViewModel by viewModels()
    private var _binding:FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private fun getDialogMargin() = resources.getDimension(R.dimen._8dp).toInt()

    companion object {
        val TAG: String = SignupFragment::class.java.simpleName
        fun newInstance(): DialogFragment {
            return SignupFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = Resources.getSystem().displayMetrics.widthPixels
        val marginInfo = 2 * getDialogMargin()
        dialog?.window?.setLayout(width - marginInfo, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setGravity(Gravity.BOTTOM)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }


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
                    dismiss()
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


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
    }

}
