package gr.padpad.marvellivedata.models.response.marvel.hero

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.models.response.marvel.common.MarvelCommonResponse
import gr.padpad.marvellivedata.models.response.marvel.hero.MarvelHeroData

data class MarvelHeroResponse(@SerializedName("data") val heroData: MarvelHeroData): MarvelCommonResponse()