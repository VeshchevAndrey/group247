// package com.example.application247 - здевь название Вашего проекта

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {

        }
    }
}

@Composable
fun FunctionWithColumn(name: String, surname: String, age: Byte){
    // расположение элементов по вертикали
    Column(
        modifier = Modifier.size(200.dp, 150.dp), // указание размеров блока
        horizontalAlignment = Alignment.CenterHorizontally, // выравнивание элементов по горизонтали
        verticalArrangement = Arrangement.SpaceAround // выравнивание элементов по вертикали
    ) {
        Text("Имя: $name")
        Text("Фамилия: $surname")
        Text("Возраст: $age")
    }
}

@Composable
fun FunctionWithRow(){
    // расположение элементов по горизонтали
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, // выравнивание элементов по горизонтали
        horizontalArrangement = Arrangement.SpaceEvenly // выравнивание элементов по вертикали
    ) {
        Text("Ready?")
        Button(onClick = {}) { Text("GO!") }
    }
}

@Composable
fun LayoutFunction(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.width(250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Готов?")
            Button({}) { Text("Да") }
        }
        Row(
            modifier = Modifier.width(250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Точно готов?")
            Button({}) { Text("Точно") }
        }
        Row(
            modifier = Modifier.width(250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Ты уверен?")
            Button({}) { Text("Полностью!") }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFE65100,
    device = "id:pixel_5"
) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FunctionWithColumn("Андрей", "Вещев", 27)
        FunctionWithRow()
        LayoutFunction()
    }
}

