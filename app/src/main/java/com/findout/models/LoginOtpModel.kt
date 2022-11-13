package com.findout.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginOtpModel {

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

}

data class LoginOtpDataModelResponse (
    @SerializedName("user" ) var user : String? = null,
    @SerializedName("otp"  ) var otp  : Otp?    = Otp(),
    @SerializedName("msg"  ) var msg  : String? = null
)

data class Otp (
    @SerializedName("otp"       ) var otp       : String? = null,
    @SerializedName("userId"    ) var userId    : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("__v"       ) var _v        : Int?    = null
)
