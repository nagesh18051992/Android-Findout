package com.findout.utils.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.findout.R


class OtpEditText : PinEntryEditText {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            typeface = ResourcesCompat.getFont(context, R.font.rajdhani_light)
        }
    }
}