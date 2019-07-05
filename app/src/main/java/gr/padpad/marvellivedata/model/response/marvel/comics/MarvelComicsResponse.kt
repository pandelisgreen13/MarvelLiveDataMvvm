package gr.padpad.marvellivedata.model.response.marvel.comics

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.model.response.marvel.common.MarvelCommonResponse

data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
): MarvelCommonResponse()