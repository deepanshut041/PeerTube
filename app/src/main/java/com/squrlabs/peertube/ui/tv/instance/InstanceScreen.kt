package com.squrlabs.peertube.ui.tv.instance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.ui.mobile.instance.InstanceViewModel
import com.squrlabs.peertube.util.getViewModel

@Composable
fun InstanceScreen(
    setCurrentHost: (InstanceModel) -> Unit,
    instanceViewModel: InstanceViewModel = getViewModel()
) {
    val instances by instanceViewModel.instances.collectAsState()
    val instanceParams by instanceViewModel.instanceParams.collectAsState()


}