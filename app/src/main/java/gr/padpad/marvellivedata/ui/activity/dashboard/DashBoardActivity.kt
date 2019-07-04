package gr.padpad.marvellivedata.ui.activity.dashboard

import android.os.Bundle
import android.view.View
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class DashBoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initLayout()
    }

    private fun initLayout() {
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        backButtonImageView.visibility = View.INVISIBLE
    }
}
