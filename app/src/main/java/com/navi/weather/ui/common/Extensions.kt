package com.navi.weather.ui.common


import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.app.ActivityCompat

fun Context.getResource(name:String): Drawable? {
    val resID = this.resources.getIdentifier(name , "drawable", this.packageName)
    return ActivityCompat.getDrawable(this,resID)
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }