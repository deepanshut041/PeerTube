package com.squrlabs.peertube.ui.mobile.instance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
class InstanceViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    private val  _instanceScreenState = MutableStateFlow(InstanceViewState())

    private val _inSearchMode = MutableStateFlow(false)

    private val _instanceParams = MutableStateFlow(GetInstancesParams())

    val instanceScreenState: StateFlow<InstanceViewState>
        get() = _instanceScreenState

    val inSearchMode: StateFlow<Boolean>
        get() = _inSearchMode

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
        }

        viewModelScope.launch {
            _instanceParams
                .debounce(500)
                .distinctUntilChanged()
                .collect{
                    val instances = instanceRepository.getInstances(it)
                    _instanceScreenState.value = _instanceScreenState.value.copy(instances = instances, instanceParams = it)
                }
        }
    }

}