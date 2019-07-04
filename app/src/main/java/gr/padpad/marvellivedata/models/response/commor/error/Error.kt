package gr.padpad.marvellivedata.models.response.commor.error

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("name") val name: String = "",
    @SerializedName("errorMessage") val errorMessage: String = ""
)