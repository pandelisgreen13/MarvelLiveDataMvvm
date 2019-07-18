package gr.padpad.marvellivedata.mvp.repository.series

import gr.padpad.marvellivedata.model.response.marvel.series.MarvelSeriesResponse
import gr.padpad.marvellivedata.mvp.repository.base.BaseRepository
import gr.padpad.marvellivedata.network.client.MarvelClient

class SeriesRepository(private val marvelClient: MarvelClient?) : BaseRepository() {

    suspend fun fetchSeries(): MarvelSeriesResponse? {
        return  try {
            marvelClient?.getMarvelSeriesAsync(20, 0)?.await()
        } catch (e: Exception) {
            return null
        }
    }
}