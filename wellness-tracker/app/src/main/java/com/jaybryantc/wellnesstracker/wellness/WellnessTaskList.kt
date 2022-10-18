package com.jaybryantc.wellnesstracker.wellness

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WellnessTaskList(
    list: List<WellnessTask>,
    modifier: Modifier = Modifier,
    onTaskClose: (WellnessTask) -> Unit,
    onTaskChecked: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn(modifier = modifier) {
        // specify a key to track item by unique id instead of positioning
        // because when we remove item, we lose track of the item due to change in position
        items(items = list, key = { task -> task.id }) { task ->
            WellnessTaskItem(
                label = task.label,
                checked = task.checked,
                onClose = { onTaskClose(task) },
                onChecked = { newCheckedValue -> onTaskChecked(task, newCheckedValue) }
            )
        }
    }
}
