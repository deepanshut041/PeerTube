package com.deepanshut041.peertube.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deepanshut041.peertube.common.service.model.InstanceModel
import com.deepanshut041.peertube.common.service.repository.InstanceRepository
import com.deepanshut041.peertube.ui.MobileActions
import com.deepanshut041.peertube.ui.NavigationModel
import com.deepanshut041.peertube.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TvViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    val navigate: LiveData<NavigationModel>
        get() = _navigate

    val videoOverlay: StateFlow<Long?>
        get() = _videoOverlay

    private val _videoOverlay = MutableStateFlow<Long?>(null)
    private val _navigate = SingleLiveEvent<NavigationModel>()

    fun navigateTo(navigationModel: NavigationModel){
        _navigate.value = navigationModel
    }

    fun setCurrentHost(instance: InstanceModel) {
        instance.host?.let{
            instanceRepository.setCurrentHost(it)
            navigateTo(NavigationModel(MobileActions.navigateToHome(), true))
        }
    }

    fun getStartDestination(): String {
        return instanceRepository.getCurrentHost()?.let{ MobileActions.navigateToHome() }?: run{ MobileActions.navigateToInstances() }
    }

}