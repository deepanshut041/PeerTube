package com.squrlabs.peertube.ui.mobile.instance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
class InstanceViewModel(private val instanceRepository: InstanceRepository) : ViewModel() {

    private val _instances = MutableStateFlow(emptyList<InstanceModel>())

    private val _inSearchMode = MutableStateFlow(false)

    private val _instanceParams = MutableStateFlow(GetInstancesParams())

    val instances: StateFlow<List<InstanceModel>>
        get() = _instances

    val inSearchMode: StateFlow<Boolean>
        get() = _inSearchMode

    val instanceParams: StateFlow<GetInstancesParams>
        get() = _instanceParams

    fun switchToSearchMode(inSearchMode: Boolean) {
        _inSearchMode.value = inSearchMode
        if (!inSearchMode) {
            updateParams(query = "")
        }
    }

    fun updateParams(query: String?) {
        _instanceParams.value = _instanceParams.value.let { params ->
            var newParams = params
            query?.let { newParams = params.copy(text = it) }
            newParams
        }
    }

    fun performSearch(query: String) {
        updateParams(query)
    }

    init {
        viewModelScope.launch {
            instanceRepository.fetchInstances()
            fetchInstances(_instanceParams.value)
        }

        viewModelScope.launch {
            _instanceParams
                .debounce(100)
                .distinctUntilChanged()
                .collect {
                    fetchInstances(it)
                }
        }
    }

    private suspend fun fetchInstances(instanceParams: GetInstancesParams) {
        _instances.value = instanceRepository.getInstances(instanceParams).data ?: emptyList()
    }

}