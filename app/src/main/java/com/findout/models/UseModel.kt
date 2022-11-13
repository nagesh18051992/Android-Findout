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

data class AddUserModelResponse (
    @SerializedName("name"    ) var name    : String? = null,
    @SerializedName("email"   ) var email   : String? = null,
    @SerializedName("pincode" ) var pincode : Int?    = null,
    @SerializedName("mobile"  ) var mobile  : Int?    = null,
    @SerializedName("_id"     ) var Id      : String? = null,
    @SerializedName("date"    ) var date    : String? = null,
    @SerializedName("__v"     ) var _v      : Int?    = null
)
