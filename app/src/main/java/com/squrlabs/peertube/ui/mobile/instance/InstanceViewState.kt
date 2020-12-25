package com.squrlabs.peertube.ui.mobile.instance

import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.params.GetInstancesParams

data class InstanceViewState(
    val inSearchMode:Boolean=false,
    val instanceParams: GetInstancesParams = GetInstancesParams(),
    val instances: Resource<List<InstanceModel>> = Resource.loading(data = emptyList())
)