package com.example.w05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val counterState = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CounterApp(count = counterState)

        Spacer(modifier = Modifier.height(32.dp))

        StopWatchApp()
    }
}

@Composable
fun CounterApp(count: MutableState<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            Button(onClick = { count.value++ }) {
                Text("Increase")
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { count.value = 0 }) {
                Text("Reset")
            }
        }
    }
}

@Composable
fun StopWatchApp() {
    var timeInMillis by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            while (true) {
                delay(10L)
                timeInMillis += 10L
            }
        }
    }

    StopwatchScreen(
        timeInMillis = timeInMillis,
        onStartClick = { isRunning = true },
        onStopClick = { isRunning = false },
        onResetClick = {
            isRunning = false
            timeInMillis = 0L
        }
    )
}

@Composable
fun StopwatchScreen(
    timeInMillis: Long,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit,
    onResetClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = formatTime(timeInMillis),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            Button(onClick = onStartClick) { Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) { Text("Stop") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onResetClick) { Text("Reset") }
        }
    }
}

private fun formatTime(timeInMillis: Long): String {
    val minutes = (timeInMillis / 1000) / 60
    val seconds = (timeInMillis / 1000) % 60
    val millis = (timeInMillis % 1000) / 10
    return String.format("%02d:%02d:%02d", minutes, seconds, millis)
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    val count = remember { mutableStateOf(0) }
    CounterApp(count)
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatchApp()
}