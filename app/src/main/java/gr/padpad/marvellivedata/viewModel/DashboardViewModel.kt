package gr.padpad.marvellivedata.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gr.padpad.marvellivedata.commons.application.MarvelApplication
import gr.padpad.marvellivedata.models.response.marvel.hero.MarvelHero
import gr.padpad.marvellivedata.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class DashboardViewModel : BaseViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */

    private lateinit var heroes: MutableLiveData<List<MarvelHero>>

    fun getHeroes(): LiveData<List<MarvelHero>> {
        if (!::heroes.isInitialized) {
            heroes = MutableLiveData()
            loadHeroes()
        }
        return heroes
    }

    private fun loadHeroes() {
        launch {
            try {
                isLoading.value = true
                val result = MarvelApplication.get()?.marvelClient?.getMarvelHeroesAsync(20, 0)
                result?.await()?.let {
                    showError.value = false
                    heroes.value = it.heroData.results
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