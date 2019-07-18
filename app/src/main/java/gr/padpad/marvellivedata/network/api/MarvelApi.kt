package gr.padpad.marvellivedata.network.api

import gr.padpad.marvellivedata.commons.Definitions
import gr.padpad.marvellivedata.commons.Definitions.PARAM_CHARACTER_ID
import gr.padpad.marvellivedata.model.response.marvel.comics.MarvelComicsResponse
import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse
import gr.padpad.marvellivedata.model.response.marvel.series.MarvelSeriesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getMarvelHeroesAsync(@Query(Definitions.PARAM_LIMIT) limit: Int, @Query(Definitions.PARAM_OFFSET) offset: Int): Deferred<MarvelHeroResponse>

    @GET("characters/{$PARAM_CHARACTER_ID}/comics")
    fun getMarvelComicsAsync(@Path(PARAM_CHARACTER_ID) heroId: Int): Deferred<MarvelComicsResponse>

    @GET("series")
    fun getMarvelSeries(@Query(Definitions.PARAM_LIMIT) limit: Int, @Query(Definitions.PARAM_OFFSET) offset: Int): Deferred<MarvelSeriesResponse>

}