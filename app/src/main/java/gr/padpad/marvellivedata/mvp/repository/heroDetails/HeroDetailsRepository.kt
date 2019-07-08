package gr.padpad.marvellivedata.mvp.repository.heroDetails

import gr.padpad.marvellivedata.model.data.MarvelHeroesModel
import gr.padpad.marvellivedata.model.data.MarvelListModel
import gr.padpad.marvellivedata.mvp.repository.base.BaseRepository
import gr.padpad.marvellivedata.network.client.MarvelClient

class HeroDetailsRepository(private val marvelClient: MarvelClient?) : BaseRepository() {

    suspend fun fetchComics(heroId: Int): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelComicsAsync(heroId)?.await()
        } catch (e: Exception) {
            return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            return@map MarvelHeroesModel(
                    marvelHero.id,
                    marvelHero.title ?: "",
                    marvelHero.description ?: "",
                    marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension)
        })
        return MarvelListModel(marvelModelList)
    }
}