package gr.padpad.marvellivedata.model.response.marvel.hero

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.model.response.marvel.common.MarvelCommonResponse

data class MarvelHeroResponse(@SerializedName("data") val heroData: MarvelHeroData): MarvelCommonResponse()