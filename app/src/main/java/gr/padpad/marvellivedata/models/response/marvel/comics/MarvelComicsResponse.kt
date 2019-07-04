package gr.padpad.marvellivedata.models.response.marvel.comics

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.models.response.marvel.comics.MarvelComicsData
import gr.padpad.marvellivedata.models.response.marvel.common.MarvelCommonResponse

data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
): MarvelCommonResponse()