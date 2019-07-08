package gr.padpad.marvellivedata.ui.activity.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import gr.padpad.marvellivedata.mvp.viewModel.base.BaseViewModel

@SuppressLint("Registered")
open class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected var viewModel: T? = null
}