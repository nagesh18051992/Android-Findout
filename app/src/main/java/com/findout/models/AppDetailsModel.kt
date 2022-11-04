package com.findout.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppDetailsModel {

    @SerializedName("app_version")
    @Expose
    var appVersion: Int? = 0
}

data class AppDetailsModelResponse(val status: String? = null)
