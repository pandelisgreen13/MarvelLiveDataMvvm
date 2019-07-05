package gr.padpad.marvellivedata.model.response.marvel.common

import com.google.gson.annotations.SerializedName
import gr.mobile.mvp.kotlin.network.parser.response.marvel.common.IMarvelCommonResponse

open class MarvelCommonResponse(
    @SerializedName("code") protected var code: Int = 0,
    @SerializedName("status") protected var status: String = "",
    @SerializedName("copyright") protected var copyright: String = "",
    @SerializedName("attributionText") protected var attributionText: String = "",
    @SerializedName("attributionHTML") protected var attributionHTML: String = "",
    @SerializedName("etag") protected var etag: String = ""
) : IMarvelCommonResponse {
    override fun isSuccess(): Boolean {
        return code == 200
    }

    override fun getErrorMessage(): String {
        return status
    }
}