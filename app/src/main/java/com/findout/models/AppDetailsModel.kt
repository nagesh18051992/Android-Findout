package com.findout.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppDetailsModel {

    @SerializedName("app_version")
    @Expose
    var appVersion: Int? = 0

    @SerializedName("living_here_for_months")
    @Expose
    var months: String? = null
}

data class AppDetailsModelResponse(val status: String? = null)
