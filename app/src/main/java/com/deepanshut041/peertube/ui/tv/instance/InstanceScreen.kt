package com.deepanshut041.peertube.ui.tv.instance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.deepanshut041.peertube.common.service.model.InstanceModel
import com.deepanshut041.peertube.ui.mobile.instance.InstanceViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun InstanceScreen(
    setCurrentHost: (InstanceModel) -> Unit,
    instanceViewModel: InstanceViewModel = getViewModel()
) {
    val instances by instanceViewModel.instances.collectAsState()
    val instanceParams by instanceViewModel.instanceParams.collectAsState()


}