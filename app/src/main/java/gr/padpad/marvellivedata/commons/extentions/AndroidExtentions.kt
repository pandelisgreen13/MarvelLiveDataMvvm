package gr.padpad.marvellivedata.commons.extentions

import android.os.Build

fun isPie(): Boolean = Build.VERSION.SDK_INT > Build.VERSION_CODES.O

fun isOreo(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isNougat(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M