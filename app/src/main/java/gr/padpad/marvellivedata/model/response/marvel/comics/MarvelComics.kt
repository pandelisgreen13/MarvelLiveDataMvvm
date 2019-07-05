package gr.padpad.marvellivedata.model.response.marvel.comics

import com.google.gson.annotations.SerializedName
import gr.padpad.marvellivedata.model.response.marvel.common.MarvelThumbnail

data class MarvelComics(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String ?,
    @SerializedName("description") val description: String ?,
    @SerializedName("thumbnail") val thumbnail: MarvelThumbnail
)