package com.findout.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyOtpModel {

    @SerializedName("otp")
    @Expose
    var otp: String? = null

    @SerializedName("userId")
    @Expose
    var userId: String? = null
}

data class VerifyOtpModelResponse (
    @SerializedName("token" ) var token : String? = null,
    @SerializedName("user"  ) var user  : User?   = User()
)

data class User (
    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("otp"       ) var otp       : String? = null,
    @SerializedName("userId"    ) var userId    : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("__v"       ) var _v        : Int?    = null
)

