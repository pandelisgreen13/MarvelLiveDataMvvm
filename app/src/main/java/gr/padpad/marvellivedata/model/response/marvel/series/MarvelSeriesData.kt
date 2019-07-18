package gr.padpad.marvellivedata.model.response.marvel.series

import com.google.gson.annotations.SerializedName

data class MarvelSeriesData(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val results: List<MarvelSeries>
)