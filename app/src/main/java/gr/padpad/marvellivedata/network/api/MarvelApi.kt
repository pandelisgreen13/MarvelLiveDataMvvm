package gr.padpad.marvellivedata.network.api

import gr.padpad.marvellivedata.commons.Definitions
import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getMarvelHeroesAsync(@Query(Definitions.PARAM_LIMIT) limit: Int, @Query(Definitions.PARAM_OFFSET) offset: Int): Deferred<MarvelHeroResponse>

}