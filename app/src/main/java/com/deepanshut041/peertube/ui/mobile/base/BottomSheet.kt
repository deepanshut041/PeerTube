package com.deepanshut041.peertube.ui.mobile.base


import androidx.compose.animation.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetContent: @Composable ColumnScope.() -> Unit,
    sheetState: ModalBottomSheetState,
    title: String,
    modifier: Modifier = Modifier,
    action: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable () -> Unit
) {

    val sheetShape =
        animate(if (sheetState.targetValue == ModalBottomSheetValue.Expanded) 0.dp else 24.dp)

    ModalBottomSheetLayout(
        sheetContent = {

            Box(
                Modifier
                    .padding(16.dp)
                    .height(5.dp)
                    .fillMaxWidth(0.1F)
                    .background(MaterialTheme.colors.onBackground.copy(0.1F), CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .weight(0.5F, fill = false)
                        .wrapContentSize()
                )

                if (action != null) action()
            }

            Spacer(modifier = Modifier.height(32.dp))

            sheetContent()
        },
        sheetState = sheetState,
        modifier = modifier,
//        sheetShape = RoundedCornerShape(topLeft = sheetShape, topRight = sheetShape),
        sheetBackgroundColor = MaterialTheme.colors.background,
        content = content,
    )

}