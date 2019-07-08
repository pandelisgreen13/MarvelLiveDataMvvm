package gr.padpad.marvellivedata.commons.extentions

import android.content.Context
import android.util.TypedValue

fun Float.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics).toInt()
}
