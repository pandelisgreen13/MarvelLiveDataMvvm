package gr.padpad.marvellivedata.commons.extentions

import android.content.Context
import androidx.annotation.StringRes

fun Context.getStringOrDefault(@StringRes stringRes: Int, key: String = ""): String {
    if (key.isNotEmpty()) {
        return getString(stringRes)
    }
    return getString(stringRes)
}