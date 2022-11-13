package com.findout.ui.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findout.models.AddUserModelResponse
import com.findout.models.UseModel
import com.findout.repository.AppDetailRepository
import com.findout.utils.NetworkResult
import com.findout.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor(private val repository: AppDetailRepository, private val application: Application) : ViewModel() {

    val appUpdate: MutableLiveData<NetworkResult<AddUserModelResponse?>> = MutableLiveData()

    fun init() {

    }

    fun addUser(userModel: UseModel?){
        appUpdate.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(application.applicationContext)){
                    val response = repository.fetchCreateUser(userModel)
                    appUpdate.postValue(NetworkResult.Success(response.body()))
                }else{
                    appUpdate.postValue(NetworkResult.Error("No Internet Connection"))
                }
            }catch (ex:Exception){
                when (ex) {
                    is IOException -> appUpdate.postValue(NetworkResult.Error("Network Failure " +  ex.localizedMessage))
                    else -> appUpdate.postValue(NetworkResult.Error("Conversion Error"))
                }
            }
        }
    }
}