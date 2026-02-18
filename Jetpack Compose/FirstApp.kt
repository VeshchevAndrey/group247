// package com.example.application247 - здесь Ваше имя проекта

import android.icu.text.TimeZoneNames
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            val name = "Мария"
            WelcomeFunction(name)
        }
    }
}

// Пример функции с отрисовкой интерфейса
@Composable // аннотация, указывающая на функцию, задающую элемент интерфейса и взаимодействие с ним
fun WelcomeFunction(name: String){
    Text(
        text = "Привет $name!",
        color = Color(0xFFFF4500)
    )
}

// Функция с условной конструкцией
@Composable
fun DayOrEveningFunction(hour: Byte){
    var timesOfDay = ""
    if (hour in 6..17) timesOfDay = "day" else timesOfDay = "evening"
    Text(
        text = "Good $timesOfDay!"
    )
}

// Функция с циклом 
@Composable
fun Employees(vararg names: String){
    Column {
        for (i in names) {
            Text(i)
        }
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    Employees("Walter", "Jessie", "Saul", "Gustavo")
}

