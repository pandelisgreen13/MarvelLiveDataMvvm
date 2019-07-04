package gr.padpad.marvellivedata.models.response.marvel.comics

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.models.response.marvel.comics.MarvelComics

data class MarvelComicsData(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<MarvelComics>
)