package com.deepanshut041.peertube.ui.mobile.base

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun MainInputText(
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

//    TextField(
//        value = text,
//        onValueChange = onTextChanged,
//        backgroundColor = Color.Transparent,
//        placeholder = { Text(placeholder) },
//        maxLines = 1,
//
//        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Go),
//        keyboardActions= { action, keyboardController ->
////            if (action == ImeAction.Go) {
////                onImeAction()
////                keyboardController?.hideSoftwareKeyboard()
////            }
//        },
//        textStyle = MaterialTheme.typography.subtitle2,
//        modifier = modifier.fillMaxHeight(.8f).focusRequester(focusRequester)
//    )
//    OnActive {
//        focusRequester.requestFocus()
//    }
}