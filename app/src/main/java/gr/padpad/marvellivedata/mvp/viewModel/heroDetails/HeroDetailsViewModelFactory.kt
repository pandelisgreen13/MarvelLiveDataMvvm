package gr.padpad.marvellivedata.mvp.viewModel.heroDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gr.padpad.marvellivedata.model.data.MarvelHeroesModel
import gr.padpad.marvellivedata.mvp.repository.heroDetails.HeroDetailsRepository


class HeroDetailsViewModelFactory(private val repository: HeroDetailsRepository?, private val hero: MarvelHeroesModel?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeroDetailsViewModel(repository, hero) as T
    }
}