package com.squrlabs.peertube.mobile.ui.instance

import androidx.lifecycle.*
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.util.SingleLiveEvent
import kotlinx.coroutines.launch

class InstanceViewModel(private val instanceRepository: InstanceRepository) : ViewModel() {

    val launchState: LiveData<LaunchState>
        get() = _launchState

    val instanceParams: LiveData<GetInstancesParams>
        get() = _instanceParams

    val getInstance: LiveData<Resource<List<InstanceModel>>>
        get() = _instanceParams.switchMap { liveData { emit(instanceRepository.getInstances(it)) } }

    val fetchInstances: LiveData<Resource<Unit>>
        get() = _fetchInstances

    private val _launchState = SingleLiveEvent<LaunchState>()
    private val _instanceParams = MutableLiveData<GetInstancesParams>()
    private val _fetchInstances = SingleLiveEvent<Resource<Unit>>()

    fun setParams(params: GetInstancesParams) {
        this._instanceParams.value = params
    }

    fun fetchInstances() {
        viewModelScope.launch {
            _fetchInstances.value = instanceRepository.fetchInstances()
        }
    }

    fun startLoading() {
        _fetchInstances.value = Resource.loading()
    }

    fun setCurrentHost(instanceModel: InstanceModel, launchSignup: Boolean = false) {
        instanceRepository.setCurrentHost(instanceModel.host!!)
        _launchState.value = LaunchState(if (launchSignup) LaunchState.STATE_LAUNCH_SIGNUP else LaunchState.STATE_LAUNCH_MAIN)
    }

    companion object {
        class LaunchState(var code: Int) {
            companion object {
                const val STATE_LAUNCH_MAIN = 0
                const val STATE_LAUNCH_SIGNUP = 1
            }
        }
    }
}
