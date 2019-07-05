package gr.padpad.marvellivedata.mvp.viewModel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gr.padpad.marvellivedata.mvp.repository.dashboard.DashboardRepository
import gr.padpad.marvellivedata.network.client.MarvelClient


class DashboardViewModelFactory(private val repository: DashboardRepository?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository) as T
    }
}