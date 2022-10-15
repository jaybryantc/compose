package com.jaybryantc.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaybryantc.hello.ui.theme.HelloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloTheme {
                HelloApp()
            }
        }
    }
}

@Composable
fun HelloApp() {
    // rememberSaveable will survive the state even in configuration changes
    var shouldShowOnBoardingScreen by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnBoardingScreen)
        OnBoardingScreen { shouldShowOnBoardingScreen = false }
    else
        HelloScreen()
}

@Composable
fun OnBoardingScreen(onContinueClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to the Compose Hello App!")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onContinueClick) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun HelloScreen(names: List<String> = listOf("World", "Compose", "Declarative UI")) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(names) { name ->
                GreetingCard(name)
            }
        }
    }
}

@Composable
fun GreetingCard(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }

            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelloScreenPreview() {
    HelloTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HelloScreen()
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    HelloTheme {
        OnBoardingScreen {
        }
    }
}
