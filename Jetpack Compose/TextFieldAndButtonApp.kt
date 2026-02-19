// package com.example.application247 - указываете имя своего приложения

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            Application1()
        }
    }
}

@Composable
fun Application1(){
    val message = remember { mutableStateOf("") }
    val textMessage = remember { mutableStateOf("") }
    Column() {
        Text(text = "My first app", fontSize = 10.em)
        TextField(value = message.value, onValueChange = {message.value = it})
        Button(onClick = {
            if (message.value != "") {
            textMessage.value = message.value
            message.value = ""
                }
        }
        ) { Text( text = "Click on me!") }
        Text(text = textMessage.value)
    }
}


@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    Application1()
}

