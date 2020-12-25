package com.squrlabs.peertube.ui.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.util.SingleLiveEvent

class MobileViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    val navigate: LiveData<NavigationModel>
        get() = _navigate

    private val _navigate = SingleLiveEvent<NavigationModel>()

    fun navigateTo(path: String, clearBackStack: Boolean = false, launchSingleTop: Boolean=false){
        _navigate.value = NavigationModel(path, clearBackStack, launchSingleTop)
    }

    fun setCurrentHost(instance: InstanceModel) {
        instance.host?.let{
            instanceRepository.setCurrentHost(it)
            navigateTo(MobileNavigation.navigateHome(), true)
        }
    }

    fun getStartDestination(): String {
        return instanceRepository.getCurrentHost()?.let{ MobileNavigation.navigateHome() }?: run{ MobileNavigation.navigateInstances() }
    }

    companion object{
        data class NavigationModel(val path: String, val clearBackStack: Boolean, val launchSingleTop: Boolean=false)
    }
}