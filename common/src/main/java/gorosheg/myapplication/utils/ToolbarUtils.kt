package gorosheg.myapplication.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Toolbar.toolbarSettings(
    activity: AppCompatActivity,
    homeIcon: Boolean,
    callback: (AppCompatActivity) -> Unit
) {
    activity.setSupportActionBar(this)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(homeIcon)

    setNavigationOnClickListener {
        callback.invoke(activity)
    }
}