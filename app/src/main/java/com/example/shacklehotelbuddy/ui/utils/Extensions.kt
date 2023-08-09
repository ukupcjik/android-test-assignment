package com.example.shacklehotelbuddy.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.math.RoundingMode

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ) { onClick() }
    }

@Composable
fun <T> Flow<T>.collectAsEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = lifecycleState) {
            collectLatest { data ->
                action(data)
            }
        }
    }
}

fun Double.roundUPDecimal(): Double {
    return this.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
}

inline fun <T> MutableStateFlow<T>.update(function: (T) -> T) {
    while (true) {
        val prevValue = value
        val nextValue = function(prevValue)
        if (compareAndSet(prevValue, nextValue)) {
            return
        }
    }
}
