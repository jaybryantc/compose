package com.jaybryantc.wellnesstracker.wellness

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList();
    val tasks: List<WellnessTask>
        get() = _tasks

    fun removeItem(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun updateTaskChecked(task: WellnessTask, newCheckedValue: Boolean) {
        _tasks.find { t -> task.id == t.id }?.let {
            it.checked = newCheckedValue
        }
    }

    private fun getWellnessTasks() = List(30) { index -> WellnessTask(index, "Task #$index") }
}
