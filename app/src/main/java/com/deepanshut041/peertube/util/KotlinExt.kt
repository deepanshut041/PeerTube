package com.deepanshut041.peertube.util


import android.text.format.DateUtils
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AmbientViewModelStoreOwner
import androidx.lifecycle.ViewModel
import kotlinx.datetime.Instant
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

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


fun Long.duration(): String
{
    return DateUtils.formatElapsedTime(this)
}

fun Long.humanReadableBigNumber(): String
{
    val unit = 1000.0
    if (this < unit)
        return "$this"
    val exp = (ln(this.toDouble()) / ln(unit)).toInt()
    val pre = arrayListOf("k", "M", "Md")[exp - 1]
    return String.format("%.1f%s", this / unit.pow(exp.toDouble()), pre)
}

private fun currentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun Instant.getTimeAgo(): String {

    val SECOND_MILLIS: Long = 1000
    val MINUTE_MILLIS = 60 * SECOND_MILLIS
    val HOUR_MILLIS = 60 * MINUTE_MILLIS
    val DAY_MILLIS = 24 * HOUR_MILLIS
    val MONTH_MILLIS = 30 * DAY_MILLIS
    
    var time = this.toEpochMilliseconds()
    if (time < 1000000000000L) {
        time *= 1000
    }

    val now = currentDate().time
    if (time > now || time <= 0) {
        return "in the future"
    }

    val diff = now - time
    return when {
        diff < MINUTE_MILLIS -> "moments ago"
        diff < 2 * MINUTE_MILLIS -> "a minute ago"
        diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
        diff < 2 * HOUR_MILLIS -> "an hour ago"
        diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
        diff < 48 * HOUR_MILLIS -> "yesterday"
        diff < 30 * DAY_MILLIS -> "${diff / DAY_MILLIS} days ago"
        diff < 12 * MONTH_MILLIS -> "${diff / MONTH_MILLIS} months ago"
        else -> "${diff / (MONTH_MILLIS * 12)} years ago"
    }
}