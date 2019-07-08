package gr.padpad.marvellivedata.model.mapper

import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroData
import gr.padpad.marvellivedata.model.response.marvel.hero.MarvelHeroResponse

fun toDashboardViewModel(marvelHeroResponse: MarvelHeroResponse): MarvelHeroData {

    return MarvelHeroData(0, 0, 0, 0, arrayListOf())
}