package gr.padpad.marvellivedata.mvp.repository.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gr.padpad.marvellivedata.mvp.repository.series.SeriesRepository
import gr.padpad.marvellivedata.mvp.viewModel.series.SeriesViewModel

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory< T>(val creator: () -> T) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}


class SeriesViewModelFactory<SeriesViewModel>(private val seriesRepository: SeriesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeriesViewModel(seriesRepository) as T
    }
}