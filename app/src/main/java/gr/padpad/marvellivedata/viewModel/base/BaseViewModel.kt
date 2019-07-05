package gr.padpad.marvellivedata.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel :ViewModel() , CoroutineScope {

    private val viewModelJob = Job()
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
    protected lateinit var isLoading: MutableLiveData<Boolean>
    protected lateinit var showError: MutableLiveData<Boolean>

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    fun shouldShowError(): LiveData<Boolean> {
        if (!::showError.isInitialized) {
            showError = MutableLiveData()
        }
        return showError
    }

    fun getIsLoading(): LiveData<Boolean> {
        if (!::isLoading.isInitialized) {
            isLoading = MutableLiveData()
        }
        return isLoading
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}