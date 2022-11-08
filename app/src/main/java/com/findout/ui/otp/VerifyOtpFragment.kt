package com.findout.ui.otp

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.findout.R
import androidx.lifecycle.Observer
import com.findout.databinding.FragmentVerifyotpBinding
import com.findout.models.UseModel
import com.findout.utils.NetworkResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VerifyOtpFragment : BottomSheetDialogFragment() {

    private val viewModel: VerifyOtpViewModel by viewModels()
    private var _binding:FragmentVerifyotpBinding? = null
    private val binding get() = _binding!!
    private fun getDialogMargin() = resources.getDimension(R.dimen._8dp).toInt()

    companion object {
        val TAG: String = VerifyOtpFragment::class.java.simpleName
        fun newInstance(): DialogFragment {
            return VerifyOtpFragment()
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
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
            val useModel= UseModel()
            useModel.otp=otp
            viewModel.fetchVerifyOtp(useModel)
        }
    }
}