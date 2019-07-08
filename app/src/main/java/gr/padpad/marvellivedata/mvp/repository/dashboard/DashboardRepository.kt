package gr.padpad.marvellivedata.mvp.repository.dashboard

import gr.padpad.marvellivedata.database.MarvelDatabase
import gr.padpad.marvellivedata.database.dao.MarvelTable
import gr.padpad.marvellivedata.model.data.MarvelHeroesModel
import gr.padpad.marvellivedata.model.data.MarvelListModel
import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse
import gr.padpad.marvellivedata.network.client.MarvelClient
import gr.padpad.marvellivedata.mvp.repository.base.BaseRepository

class DashboardRepository(private val marvelClient: MarvelClient?, private val marvelDatabase: MarvelDatabase) : BaseRepository() {

    suspend fun fetchHeroes(): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelHeroesAsync(20, 0)?.await()
        } catch (e: Exception) {
           return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            marvelDatabase.marvelDao().insertHero(MarvelTable(marvelHero.id))

            return@map MarvelHeroesModel(
                    marvelHero.id,
                    marvelHero.name,
                    marvelHero.description,
                    marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension,
                    marvelDatabase.marvelDao().isHeroFavotite(marvelHero.id)
            )
        })
        return MarvelListModel(marvelModelList)


    }
}