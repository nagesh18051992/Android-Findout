package com.findout.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UseModel {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("pincode")
    @Expose
    var  pincode: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("otp")
    @Expose
    var otp: String? = null
}