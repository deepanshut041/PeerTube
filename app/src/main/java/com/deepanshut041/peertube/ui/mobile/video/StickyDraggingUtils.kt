package com.deepanshut041.peertube.ui.mobile.video

import android.content.Context
import android.content.res.Configuration
import android.view.ViewConfiguration
import androidx.compose.animation.animate
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

fun interpolate(
    value: Float,
    input: ClosedFloatingPointRange<Float>,
    output: ClosedFloatingPointRange<Float>,
): Float {
    val (inputMin, inputMax) = input
    val (outputMin, outputMax) = output

    if (outputMin == outputMax) {
        return outputMin
    }
    return if (inputMin == inputMax) {
        if (value <= inputMin) {
            outputMin
        } else outputMax
    } else outputMin + (outputMax - outputMin) * (value - inputMin) / (inputMax - inputMin)
}

private operator fun <T : Comparable<T>> ClosedFloatingPointRange<T>.component1(): T {
    return this.start
}

private operator fun <T : Comparable<T>> ClosedFloatingPointRange<T>.component2(): T {
    return this.endInclusive
}

fun getNavigationBarHeight(context: Context): Int {
    val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
    val resourceId: Int =
        context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0 && !hasMenuKey) {
        context.resources.getDimensionPixelSize(resourceId)
    } else 0
}

fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId: Int =
        context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

data class ScreenDimensions(val height: Dp, val width: Dp)

@Composable
fun screenDimensions(): ScreenDimensions {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics

    with(LocalDensity.current) {
        val height = displayMetrics.heightPixels.toDp() - 56.dp
        val width = displayMetrics.widthPixels.toDp()
        val statusBarHeight = getStatusBarHeight(context).toDp()
        val orientation: Int = context.resources.configuration.orientation

        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ScreenDimensions(height = height, width = width)
        } else {
//            height -= (statusBarHeight)
            ScreenDimensions(height = height, width = width)
        }
    }
}

class StickyDraggingConfig(
    private val initiallyExpanded: Boolean,
    private val minSize: Dp = 0.dp,
    private val maxSize: Dp,
) {
    private val initialOffset: Dp = if (initiallyExpanded) maxSize else minSize

    private var _offset by mutableStateOf(initialOffset)
    private var originalOffset: Dp? = null

    var isDragging by mutableStateOf(false)
        private set

    val isExpanded get() = _offset == maxSize

    @Composable
    val offset
        get() = animate(_offset)

    @Composable
    val progress
        get() = offset / maxSize


    internal fun onDragStart(startedPosition: Offset) {
        isDragging = true
        originalOffset = _offset
    }

    fun onDrag(distance: Dp) {
        val newVal = _offset + distance


        if (newVal in minSize..maxSize) {
            _offset = newVal
        }
    }

    fun onDragEnd(velocity: Float) {
        val progress = _offset / maxSize
        isDragging = false

        _offset = if (abs(velocity) > 200) {
            if (originalOffset == maxSize) {
                minSize
            } else {
                maxSize
            }
        } else {
            if (progress > 0.5f) {
                maxSize
            } else {
                minSize
            }
        }

        originalOffset = null
    }

    fun toggle() {
        _offset = if (this.initiallyExpanded) minSize else maxSize
    }

    fun expand() {
        _offset = minSize
    }

    fun minimize() {
        _offset = maxSize
    }

    fun reset() {
        _offset = initialOffset
    }
}

fun Modifier.stickyDrag(config: StickyDraggingConfig): Modifier = composed {
    this.offset(y = config.offset)
        .draggable(
            orientation = Orientation.Vertical,
            onDragStarted = config::onDragStart,
            onDragStopped = config::onDragEnd,
        ) { config.onDrag(it.toDp()) }
}