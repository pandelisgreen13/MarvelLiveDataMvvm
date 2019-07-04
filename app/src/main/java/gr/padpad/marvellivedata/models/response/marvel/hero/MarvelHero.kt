package gr.padpad.marvellivedata.models.response.marvel.hero

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.models.response.marvel.common.MarvelThumbnail

data class MarvelHero(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("thumbnail") val thumbnail: MarvelThumbnail
)