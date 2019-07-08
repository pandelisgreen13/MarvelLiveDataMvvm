package gr.padpad.marvellivedata.mvp.viewModel.heroDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gr.padpad.marvellivedata.model.data.MarvelHeroesModel
import gr.padpad.marvellivedata.mvp.repository.heroDetails.HeroDetailsRepository
import gr.padpad.marvellivedata.mvp.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HeroDetailsViewModel(private val dashboardRepository: HeroDetailsRepository?,  var hero: MarvelHeroesModel?) : BaseViewModel() {

    private lateinit var comics: MutableLiveData<List<MarvelHeroesModel>>

    fun getComics(): LiveData<List<MarvelHeroesModel>> {
        if (!::comics.isInitialized) {
            loadComics()
        }
        return comics
    }

    private fun loadComics() {
        comics = MutableLiveData()
        uiScope.launch {
            try {
                isLoading.value = true
                val response = withContext(bgDispatcher) {
                    dashboardRepository?.fetchComics(hero?.id ?: 0)
                }
                response?.let {
                    showError.value = false
                    comics.value = it.marvelHeroes
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