package com.findout.ui.base

import android.content.Context
import android.content.pm.PackageManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.findout.R
import com.findout.utils.DynamicViewHandler
import com.findout.utils.Status

abstract class BaseFragment : Fragment() {

    private var progressErrorState: MutableLiveData<Status>? = null
    private var dynamicViewHandler: DynamicViewHandler? = null


    private fun observeChanges() {
        progressErrorState?.observe(viewLifecycleOwner, Observer {
            when(it){
                Status.LOADING -> showProgress()
                Status.SUCCESS -> hideProgress()
                Status.ERROR -> hideProgress()
                Status.INTERNET_ERROR -> showInternetError()
                Status.RESET_ERROR_LOADER -> {
                    hideProgress()
                    hideInternetError()
                }
                else -> {}
            }
        })
    }

    private fun showProgress() {

        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        dynamicViewHandler!!.show(R.layout.progress)

        closeKeyBoard()
    }

    private fun closeKeyBoard() {
        if (activity?.currentFocus?.windowToken != null) {
            val inputManager = activity?.getSystemService(
                Context
                .INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun hideProgress() {
        dynamicViewHandler?.hide()
    }

    private fun showInternetError() {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        val view = dynamicViewHandler!!.show(R.layout.internet_error)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(0, 0, 0, 0)
        view?.layoutParams = params
        closeKeyBoard()
    }

    private fun hideInternetError() {
        dynamicViewHandler?.hide()
    }

    fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

}