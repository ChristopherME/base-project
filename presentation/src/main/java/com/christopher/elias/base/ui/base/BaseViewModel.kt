package com.christopher.elias.base.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.christopher.elias.domain.entity.Failure
import java.lang.ref.WeakReference

/**
 * Created by Christopher Elias on 3/02/2020.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
abstract class BaseViewModel<T> : ViewModel() {

    //TODO: Change this. Use Navigation Components from Android JetPack.
    //      This is just a temporal solution to fix memory leaks due to the reference to activity.
    //      The final solution will be using Navigation with multiple fragments and one activity.
    //      By making navigator weakReference the garbage collector will be able to clean the  "context"
    //      from memory after the viewModel has been cleared..
    private var navigator: WeakReference<T>? = null

    // Shows or hide progress loading bar if the have it.
    private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    // Shows or hide and empty view layout if the view have it
    private val _showEmptyView = MutableLiveData(false)
    val showEmptyView : LiveData<Boolean>
        get() = _showEmptyView

    //Shows, hide, init or stop refreshing of Swipe refresh layout if the view have it.
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing : LiveData<Boolean>
        get() = _isRefreshing

    //Shows, hide, error message view.
    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause : LiveData<Boolean>
        get() = _showErrorCause

    // The resource default value of the error or any error(Exception, server side, etc).
    private val _errorCause = MutableLiveData<Any>()
    val errorCause: LiveData<Any>
        get() = _errorCause

    protected fun logError(errorMessage: String?) {
        Log.e(this.javaClass.simpleName, errorMessage?:"error message is null.")
    }

    protected fun logInfo(infoMessage: String?) {
        Log.i(this.javaClass.simpleName, infoMessage?:"info message is null.")
    }

    fun getNavigator() : T? {
        return navigator?.get()
    }

    fun setNavigator(navigator: T) {
        this.navigator = WeakReference(navigator)
    }

    /*
     * The following functions are just for presentation purposes
     */
    protected fun setRefreshing(refreshValue: Boolean) {
        _isRefreshing.value = refreshValue
    }

    protected fun showLoading(loadingValue: Boolean) {
        _isLoading.value = loadingValue
    }

    protected fun shouldShowEmptyView(show: Boolean?) {
        _showEmptyView.value = show
    }

    protected fun showErrorCause(show: Boolean) {
        _showErrorCause.value = show
    }

    /**
     * This will perform common actions such as stop loading, refreshing, hide empty view, and show error cause.
     * In case of a failure from any Use Case.
     */
    protected fun handleUseCaseFailureFromBase(failure: Failure){
        when(failure) {
            is Failure.UnauthorizedOrForbidden -> logError("Log Out") /* Log out of the app*/
            is Failure.None -> setError("None"/*R.string.snack_bar_error_failure_none*/)
            is Failure.NetworkConnectionLostSuddenly -> setError("Connection lost suddenly. Check the wifi or mobile data."/*R.string.snack_bar_error_failure_network_connection_lost_suddenly*/)
            is Failure.NoNetworkDetected -> setError("No network detected"/*R.string.snack_bar_error_failure_no_network_detected*/)
            is Failure.SSLError -> setError("WARNING: SSL Exception"/*R.string.snack_bar_error_failure_ssl*/)
            is Failure.TimeOut -> setError("Time out."/*R.string.snack_bar_error_failure_time_out*/)
            is Failure.ServerBodyError -> setError(failure.message)
            is Failure.DataToDomainMapperFailure -> setError("Data to domain mapper failure: ${failure.mapperException}"/*failure.mapperException?:R.string.snack_bar_error_general*/)
            is Failure.ServiceUncaughtFailure -> setError(failure.uncaughtFailureMessage)
        }
        showLoading(false)
        setRefreshing(false)
        shouldShowEmptyView(false)
        showErrorCause(true)
    }

    /**
     * Set [_errorCause] value in order to observe the changes on lifecycle owner.
     *
     * @param cause the error cause can be a plain [String] or Int(string resource id)
     */
    protected fun setError(cause: Any) {
        //Print directly on console if cause is String.
        if (cause is String) {
            logError(cause)
        }
        _errorCause.value = cause
    }

    override fun onCleared() {
        // Clear object for prevention of memory leaks.
        navigator?.clear()
        super.onCleared()
    }
}
