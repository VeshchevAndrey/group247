package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            MutableFunction()
        }
    }
}

@Composable
fun MutableFunction(){
    val message = remember { mutableStateOf("Привет") }
    val checkState = remember { mutableStateOf(true) }
    var radioState by remember { mutableStateOf(true) }
    var colorState by rememberSaveable { mutableStateOf(0xFFFF9800) }

    Column() {
        Text("Выбери одно из двух")
        Row() {
            RadioButton(
                selected = radioState,
                onClick = {radioState = true}
            )
            RadioButton(
                selected = !radioState,
                onClick = {radioState = false}
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkState.value,
                onCheckedChange = {checkState.value = it}
            )
            Text(text = "Принимаешь условия?")
        }

        Text(
            text = message.value,
            modifier = Modifier
                .clickable(
                    enabled = checkState.value,
                    onClick = {message.value = "Пока!"}
                )
                .padding(10.dp)
        )
        Text(
            text = "Меняю цвет при нажатии!",
            modifier = Modifier
                .background(Color(colorState))
                .padding(10.dp)
                .clickable(
                    onClick = { colorState = 0xFF006064 }
                )
        )
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    MutableFunction()
}
