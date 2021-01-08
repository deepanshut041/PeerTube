package com.squrlabs.peertube.ui.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MobileViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    val navigate: LiveData<NavigationModel>
        get() = _navigate

    val videoOverlay: StateFlow<Long?>
        get() = _videoOverlay

    private val _videoOverlay = MutableStateFlow<Long?>(null)
    private val _navigate = SingleLiveEvent<NavigationModel>()

    fun navigateTo(path: String, clearBackStack: Boolean = false, launchSingleTop: Boolean=false){
        _navigate.value = NavigationModel(path, clearBackStack, launchSingleTop)
    }

    fun setCurrentHost(instance: InstanceModel) {
        instance.host?.let{
            instanceRepository.setCurrentHost(it)
            navigateTo(MainActions.navigateToHome(), true)
        }
    }

    fun getStartDestination(): String {
        return instanceRepository.getCurrentHost()?.let{ MainActions.navigateToInstances() }?: run{ MainActions.navigateToInstances() }
    }

    fun setVideoModel(id:Long?) {
        _videoOverlay.value = id
    }

    companion object{
        data class NavigationModel(val path: String, val clearBackStack: Boolean, val launchSingleTop: Boolean=false)
    }
}