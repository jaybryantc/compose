package com.jaybryantc.wellnesstracker.wellness

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class WellnessTask(val id: Int, val label: String, val initialCheckedValue: Boolean = false) {
    var checked by mutableStateOf(initialCheckedValue)
}
