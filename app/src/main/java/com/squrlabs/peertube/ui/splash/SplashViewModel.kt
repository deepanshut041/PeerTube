package com.squrlabs.peertube.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val instanceRepository: InstanceRepository): ViewModel() {


    private val _fetchInstances = SingleLiveEvent<Resource<Unit>>()

    fun fetchInstance(){
        _fetchInstances.value = Resource.loading()
        viewModelScope.launch(Dispatchers.Main) {
            instanceRepository.getInstances().let {
                when(it.state){
                    Resource.SUCCESS -> {
                        _fetchInstances.value = Resource.success()
                        Log.i("Success", it.data!!.size.toString())
                    }
                    Resource.ERROR -> {
                        _fetchInstances.value = Resource.error()
                        Log.i("Success", it.toString())
                    }
                }
            }

        }
    }


}