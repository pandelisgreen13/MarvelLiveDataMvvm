package gr.padpad.marvellivedata.model.response.commor

interface IResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}