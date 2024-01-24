package com.navi.weather.ui.common


import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityCompat

fun Context.getResource(name:String): Drawable? {
    val resID = this.resources.getIdentifier(name , "drawable", this.packageName)
    return ActivityCompat.getDrawable(this,resID)
}