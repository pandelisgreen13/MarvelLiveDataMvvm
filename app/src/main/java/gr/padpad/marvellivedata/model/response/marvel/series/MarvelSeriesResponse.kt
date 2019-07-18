package gr.padpad.marvellivedata.model.response.marvel.series

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.model.response.marvel.common.MarvelCommonResponse

data class MarvelSeriesResponse(@SerializedName("data") val seriesData: MarvelSeriesData): MarvelCommonResponse()