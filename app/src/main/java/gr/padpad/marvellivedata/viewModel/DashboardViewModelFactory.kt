package gr.padpad.marvellivedata.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gr.padpad.marvellivedata.network.client.MarvelClient


class DashboardViewModelFactory(private val marvelClient: MarvelClient?) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(marvelClient) as T
    }
}