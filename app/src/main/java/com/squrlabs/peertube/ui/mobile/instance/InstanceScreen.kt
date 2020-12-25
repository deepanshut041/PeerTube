package com.squrlabs.peertube.ui.mobile.instance

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.mikepenz.iconics.compose.ExperimentalIconics
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.getViewModel
import kotlinx.coroutines.FlowPreview

@ExperimentalIconics
@FlowPreview
@Composable
@Preview
fun InstanceScreen(
    mainViewModel: MobileViewModel = viewModel(),
    instanceViewModel: InstanceViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = instanceViewModel.instanceScreenState.collectAsState().value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    if (!viewState.inSearchMode) {
                        Text(text = "Instances")
                    } else {
                        MainInputText(
                            text = viewState.instanceParams.text ?: "",
                            onTextChanged = instanceViewModel::performSearch,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Search..."
                        )
                    }
                },
                actions = {
                    if (viewState.inSearchMode) {
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!viewState.inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon.cmd_close,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!viewState.inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_magnify,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!viewState.inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_tune,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            )
        },
        bodyContent = {
            Text(text = "Instance Screen")
        }
    )
}

@Composable
fun MainInputText(
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = text,
        onValueChange = onTextChanged,
        backgroundColor = Color.Transparent,
        placeholder = { Text(placeholder) },
        maxLines = 1,

        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Go),
        onImeActionPerformed = { action, keyboardController ->
            if (action == ImeAction.Go) {
                onImeAction()
                keyboardController?.hideSoftwareKeyboard()
            }
        },
        textStyle = MaterialTheme.typography.subtitle2,
        modifier = modifier.fillMaxHeight(.8f).focusRequester(focusRequester)
    )
    onActive {
        focusRequester.requestFocus()
    }
}