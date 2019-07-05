package gr.padpad.marvellivedata.mvp.repository.dashboard

import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse
import gr.padpad.marvellivedata.network.client.MarvelClient
import gr.padpad.marvellivedata.mvp.repository.base.BaseRepository

class DashboardRepository(private val marvelClient: MarvelClient?) : BaseRepository() {

    suspend fun fetchHeroes(): MarvelHeroResponse? {
        return try {
            marvelClient?.getMarvelHeroesAsync(20, 0)?.await()
        } catch (e: Exception) {
            null
        }
    }
}