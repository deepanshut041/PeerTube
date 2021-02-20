package com.deepanshut041.peertube.ui.mobile.instance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepanshut041.peertube.common.service.model.InstanceModel
import com.deepanshut041.peertube.common.service.params.InstancesFilterParams
import com.deepanshut041.peertube.common.service.repository.InstanceRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
class InstanceViewModel(private val instanceRepository: InstanceRepository) : ViewModel() {

    private val _instances = MutableStateFlow(emptyList<InstanceModel>())

    private val _inSearchMode = MutableStateFlow(false)

    private val _instanceParams = MutableStateFlow(InstancesFilterParams())

    val instances: StateFlow<List<InstanceModel>>
        get() = _instances

    val inSearchMode: StateFlow<Boolean>
        get() = _inSearchMode

    val instanceParams: StateFlow<InstancesFilterParams>
        get() = _instanceParams

    fun switchToSearchMode(inSearchMode: Boolean) {
        _inSearchMode.value = inSearchMode
        if (!inSearchMode) {
            updateParams(query = "")
        }
    }

    fun updateParams(
        query: String? = null,
        sort: Int? = null,
        health: Int? = null,
        signupAllowed: Int? = null
    ) {
        _instanceParams.value = _instanceParams.value.let { params ->
            var newParams = params
            query?.let { newParams = params.copy(text = it) }
            sort?.let {
                newParams = when (it) {
                    1 -> newParams.copy(sort = "Videos")
                    2 -> newParams.copy(sort = "Following")
                    3 -> newParams.copy(sort = "Followers")
                    4 -> newParams.copy(sort = "Users")
                    else -> newParams.copy(sort = null)
                }
            }
            health?.let {
                newParams = when (it) {
                    1 -> newParams.copy(healthy = true)
                    2 -> newParams.copy(healthy = false)
                    else -> newParams.copy(healthy = null)
                }
            }
            signupAllowed?.let {
                newParams = when (it) {
                    1 -> newParams.copy(signupAllowed = true)
                    2 -> newParams.copy(signupAllowed = false)
                    else -> newParams.copy(signupAllowed = null)
                }
            }
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

    private suspend fun fetchInstances(instanceParams: InstancesFilterParams) {
        _instances.value = instanceRepository.getInstances(instanceParams).data ?: emptyList()
    }

}