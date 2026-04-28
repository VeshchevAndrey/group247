// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.application247.ui.theme.Application247Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme() {
                Scaffold() { paddingValues ->
                    ApplicationApp(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun ApplicationApp(modifier: Modifier = Modifier){
    val coroutineScore = rememberCoroutineScope()
    val clickCount = remember { mutableStateOf(0) }
    val coroutineDemo = remember { mutableStateOf(0) }
    val buttonEnabled = remember { mutableStateOf(true) }
    val progress = remember { mutableStateOf(0f) }

    Column(modifier = modifier) {
        Text(text = "Нажми на кнопку")
        Button(onClick = {
            coroutineScore.launch() { imitateWork() }
        }) { Text(text = "Нажимай!") }

        Button(onClick = {
            clickCount.value++
        }) { Text(text = "Вы нажали по кнопке ${clickCount.value} раз") }

        Text(text = "Время до окончания работы корутины: ${coroutineDemo.value}")
        Button(onClick = {
            coroutineScore.launch {
                progress.value = 0f
                buttonEnabled.value = false
                for (i in 5 downTo 0){
                    coroutineDemo.value = i
                    progress.value += 0.2f
                    delay(1000)
                }
                buttonEnabled.value = true
            }
        }, enabled = buttonEnabled.value) { Text(text = "Запусти корутину!") }

        LinearProgressIndicator(progress = { progress.value })
    }
}

suspend fun imitateWork(){
    println("Работа начата!")
    delay(5000)
    println("Работа завершена!")
}
