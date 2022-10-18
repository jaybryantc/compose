package com.jaybryantc.wellnesstracker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel;
import com.jaybryantc.wellnesstracker.wellness.WellnessTaskList
import com.jaybryantc.wellnesstracker.wellness.WellnessViewModel

@Composable
fun WellnessScreen(modifier: Modifier = Modifier, viewModel: WellnessViewModel = viewModel()) {
    Column(modifier) {
        WaterStatefulCounter()
        WellnessTaskList(
            list = viewModel.tasks,
            onTaskClose = { task -> viewModel.removeItem(task) },
            onTaskChecked = viewModel::updateTaskChecked
        )
    }
}
