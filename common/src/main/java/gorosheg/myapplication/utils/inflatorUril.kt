package gorosheg.myapplication.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layout: Int): View {
    return LayoutInflater.from(context)
        .inflate(layout, this, false)
}