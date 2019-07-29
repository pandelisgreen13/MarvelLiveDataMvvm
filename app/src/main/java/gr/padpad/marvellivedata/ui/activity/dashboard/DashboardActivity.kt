package gr.padpad.marvellivedata.ui.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import gr.padpad.marvellivedata.R
import gr.padpad.marvellivedata.commons.BUNDLE
import gr.padpad.marvellivedata.commons.Definitions
import gr.padpad.marvellivedata.commons.application.MarvelApplication
import gr.padpad.marvellivedata.database.MarvelDatabase
import gr.padpad.marvellivedata.mvp.repository.base.BaseViewModelFactory
import gr.padpad.marvellivedata.mvp.repository.dashboard.DashboardRepository
import gr.padpad.marvellivedata.mvp.viewModel.dashboard.DashboardViewModel
import gr.padpad.marvellivedata.ui.activity.base.BaseActivity
import gr.padpad.marvellivedata.ui.activity.heroDetails.HeroDetailsActivity
import gr.padpad.marvellivedata.ui.activity.series.SeriesActivity
import gr.padpad.marvellivedata.ui.adapters.dashboard.DashboardRecyclerViewAdapter
import gr.padpad.marvellivedata.ui.custom.pagination.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_pagination_recyclerview.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DashboardActivity : BaseActivity<DashboardViewModel>() {

    private var dashboardRecyclerViewAdapter: DashboardRecyclerViewAdapter? = null
    private var paginationScrollListener: PaginationScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initLayout()
        initViewModel()
    }

    private fun initViewModel() {
        val dashboardViewModelFactory = BaseViewModelFactory {
            DashboardViewModel(DashboardRepository(MarvelApplication.get()?.marvelClient, MarvelDatabase.get(this)))
        }
        /**
         * ViewModelProviders , keeping the ViewModel alive and paired with the scope
         */
        viewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)
        viewModel?.getHeroes()?.observe(this, Observer { heroes ->
            // update UI
            moreProgressView?.visibility = View.GONE
            heroes?.let {
                dashboardRecyclerViewAdapter?.setHeroesList(it.toMutableList())
            } ?: run { emptyView.visibility = View.VISIBLE }
        })

        viewModel?.getIsLoading()?.observe(this, Observer { value ->
            value?.let { show ->
                loadingView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        viewModel?.shouldShowError()?.observe(this, Observer { value ->
            value?.let { show ->
                emptyView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun initLayout() {
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        backButtonImageView.visibility = View.INVISIBLE
        closeButtonImageView.visibility = View.VISIBLE
        closeButtonImageView.setOnClickListener { startActivity(Intent(this, SeriesActivity::class.java)) }

        val linearLayoutManager = LinearLayoutManager(this)
        dashboardRecyclerView.layoutManager = linearLayoutManager
        dashboardRecyclerViewAdapter = DashboardRecyclerViewAdapter(
                onFavouriteClicked = { heroId ->
                    viewModel?.updateFavourite(heroId)
                },
                onHeroClicked = { hero ->
                    val intent = Intent(this, HeroDetailsActivity::class.java)
                    intent.putExtra(BUNDLE.HERO_DETAILS, hero)
                    startActivity(intent)
                })

        paginationScrollListener = PaginationScrollListener(
                linearLayoutManager,
                {
                    moreProgressView?.visibility = View.VISIBLE
                    viewModel?.loadHeroes()
                },
                Definitions.PAGINATION_SIZE
        )
        paginationScrollListener?.let {
            dashboardRecyclerView.addOnScrollListener(it)
        }

        dashboardRecyclerView.adapter = dashboardRecyclerViewAdapter

    }
}
