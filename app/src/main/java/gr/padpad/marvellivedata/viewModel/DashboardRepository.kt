package gr.padpad.marvellivedata.viewModel

import gr.padpad.marvellivedata.models.response.marvel.hero.MarvelHeroResponse
import gr.padpad.marvellivedata.network.client.MarvelClient
import gr.padpad.marvellivedata.viewModel.base.BaseRepository

class DashboardRepository(private val marvelClient: MarvelClient?) : BaseRepository() {

    suspend fun fetchHeroes(): MarvelHeroResponse? {
        return try {
            marvelClient?.getMarvelHeroesAsync(20, 0)?.await()
        } catch (e: Exception) {
            null
        }
    }
}