package gr.padpad.marvellivedata.ui.activity.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.ui.activity.base.BaseActivity
import gr.padpad.marvellivedata.ui.adapters.DashboardRecyclerViewAdapter
import gr.padpad.marvellivedata.viewModel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DashboardActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initLayout()
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.getHeroes().observe(this, Observer { heroes ->
            // update UI
            heroes?.let {
                dashboardRecyclerView.layoutManager = LinearLayoutManager(this)
                dashboardRecyclerView.adapter =  DashboardRecyclerViewAdapter(it.toMutableList())
            }
        })

        viewModel.getIsLoading().observe(this, Observer { value ->
            value?.let { show ->
                loadingView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        viewModel.shouldShowError().observe(this, Observer { value ->
            value?.let { show ->
                emptyView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun initLayout() {
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        backButtonImageView.visibility = View.INVISIBLE
    }
}
