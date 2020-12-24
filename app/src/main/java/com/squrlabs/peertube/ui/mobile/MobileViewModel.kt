package com.squrlabs.peertube.ui.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.util.SingleLiveEvent

class MobileViewModel(): ViewModel() {

    val navigate: LiveData<String>
        get() = _navigate

    private val _navigate = SingleLiveEvent<String>()

    fun navigateTo(path: String){
        _navigate.value = path
    }
}