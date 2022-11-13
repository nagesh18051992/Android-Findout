package com.findout.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

fun hasInternetConnection(@ApplicationContext context: Context): Boolean {
    return true
}