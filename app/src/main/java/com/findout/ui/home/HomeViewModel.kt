package com.findout.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findout.models.AppDetailsModelResponse
import com.findout.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AppDetailsModelResponse?>> = MutableLiveData()

    fun init() {

    }
}