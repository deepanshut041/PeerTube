package com.squrlabs.peertube.util


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientViewModelStoreOwner
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin

@Composable
inline fun <reified T : ViewModel> viewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    val owner = AmbientViewModelStoreOwner.current
    return remember {
        getKoin().getViewModel(qualifier = qualifier, parameters = parameters, owner = owner)
    }
}

@Composable
inline fun <reified T : ViewModel> getViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val owner = AmbientViewModelStoreOwner.current
    return remember {
        getKoin().getViewModel(qualifier = qualifier, parameters = parameters, owner = owner)
    }
}