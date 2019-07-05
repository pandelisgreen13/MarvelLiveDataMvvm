package gr.padpad.marvellivedata.mvp.viewModel.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gr.padpad.marvellivedata.models.response.marvel.hero.MarvelHero
import gr.padpad.marvellivedata.network.client.MarvelClient
import gr.padpad.marvellivedata.mvp.repository.dashobard.DashboardRepository
import gr.padpad.marvellivedata.mvp.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DashboardViewModel(marvelClient: MarvelClient?) : BaseViewModel() {

    private lateinit var heroes: MutableLiveData<List<MarvelHero>>
    private var dashboardRepository = DashboardRepository(marvelClient)

    fun getHeroes(): LiveData<List<MarvelHero>> {
        if (!::heroes.isInitialized) {
            loadHeroes()
        }
        return heroes
    }

    private fun loadHeroes() {
        heroes = MutableLiveData()
        uiScope.launch {
            try {
                isLoading.value = true
                val response = withContext(bgDispatcher) { dashboardRepository.fetchHeroes() }
                response?.heroData?.let {
                    showError.value = false
                    heroes.value = it.results
                } ?: run {
                    showError.value = true
                }
            } catch (e: Exception) {
                Timber.e(e.toString())
                showError.value = true
            } finally {
                isLoading.value = false
            }
        }
    }
}