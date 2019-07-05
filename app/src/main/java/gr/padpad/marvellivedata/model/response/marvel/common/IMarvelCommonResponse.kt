package gr.mobile.mvp.kotlin.network.parser.response.marvel.common

interface IMarvelCommonResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}