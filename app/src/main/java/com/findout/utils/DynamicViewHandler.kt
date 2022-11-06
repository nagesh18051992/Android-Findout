package com.findout.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment

class DynamicViewHandler {

    private var visibleView: View? = null
    private var mParent: ViewGroup? = null

    constructor(activity: Activity) {
        try {
            mParent = activity.window.decorView as ViewGroup
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    constructor(fragment: Fragment) {
        mParent = fragment.view as ViewGroup?
        try {
            if (mParent is ScrollView || mParent is NestedScrollView) {
                mParent = mParent!!.getChildAt(0) as ViewGroup
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    constructor(view: View?) {
        mParent = view as ViewGroup?
    }

    fun show(@LayoutRes layoutId: Int): View? {
        if (visibleView != null) {
            hide()
        }
        inflateView(layoutId)
        if (mParent != null) {
            mParent!!.addView(visibleView, 0)
            mParent!!.bringChildToFront(visibleView)
            visibleView!!.requestFocus()
        }
        return visibleView
    }

    fun hide() {
        if (mParent != null) {
            mParent!!.removeView(visibleView)
            if (visibleView != null) {
                visibleView!!.clearFocus()
            }
        }
        visibleView = null
    }

    private fun inflateView(@LayoutRes layout: Int) {
        if (mParent != null) {
            val inflater = LayoutInflater.from(mParent!!.context)
            visibleView = inflater.inflate(layout, mParent, false)
            ViewCompat.setElevation(visibleView!!, 96f)
        }
    }
}