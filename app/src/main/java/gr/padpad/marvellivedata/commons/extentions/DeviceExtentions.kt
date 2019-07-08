package gr.padpad.marvellivedata.commons.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.app.NotificationManagerCompat


fun getScreenWidth(context: Context): Int {
    val metrics = getDisplayMetrics(context)
    return metrics.widthPixels
}


fun getScreenHeight(context: Context): Int {
    val metrics = getDisplayMetrics(context)
    return metrics.heightPixels
}

fun getDensityMultiplier(ctx: Context): Float {
    return ctx.resources.displayMetrics.density
}


@SuppressLint("WrongConstant")
fun getDisplayMetrics(context: Context): DisplayMetrics {
    return if (context is Activity) {
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        metrics
    } else {
        val wm = context.getSystemService("window") as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        metrics
    }
}

fun getScreenOrientation(context: Context): Int {
    return context.resources.configuration.orientation
}

fun isPackageInstalled(packagename: String, context: Context): Boolean {
    val pm = context.packageManager
    return try {
        pm.getPackageInfo(packagename, 1)
        true
    } catch (var4: PackageManager.NameNotFoundException) {
        false
    }
}

fun getMetadata(context: Context, name: String): String? {
    return try {
        val appInfo = context.packageManager.getApplicationInfo(context.packageName, 128)
        appInfo.metaData?.getString(name)
    } catch (var3: PackageManager.NameNotFoundException) {
        null
    }
}

fun areNotificationsAvailable(context: Context): Boolean {
    val notificationManager = NotificationManagerCompat.from(context)
    return notificationManager.areNotificationsEnabled()
}

@SuppressLint("WrongConstant")
fun closeSoftKeyboard(activity: Activity) {
    try {
        val inputManager = activity.getSystemService("input_method") as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 2)
    } catch (var3: Exception) {
    }
}