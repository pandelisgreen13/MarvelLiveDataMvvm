package gr.padpad.marvellivedata.models.response.commor

interface IResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}