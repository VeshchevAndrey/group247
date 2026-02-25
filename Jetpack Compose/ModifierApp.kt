// package com.example.application247 - здесь имя вашего приложения

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            ModifierFunction()
        }
    }
}

@Composable
fun ModifierFunction(){
    val modifierForText = Modifier
        .padding(5.dp)
        .background(Color(0xFFFFB74D), RoundedCornerShape(5.dp))
        .fillMaxWidth()
        .padding(5.dp)
    Column(
        modifier = Modifier
            .background(Color(0xFFFFE0B2))
    ) {
        Text(
            text = "Привет сосед!",
            modifier = Modifier
                .padding(5.dp) // Модификатор установки отступа от границ контейнера вне блока
                .background(Color(0xFFFF9800), RoundedCornerShape(5.dp)) // Модификатор установки цвета заднего фона
                .fillMaxWidth() // Модификатор установки ширины объекта во всю ширину контейнера
                .padding(5.dp), // Модификатор установки отступа от контента внутри блока
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "\tИ тебе привет!",
            modifier = modifierForText.clickable(onClick = { println("Работает!") })
        )
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    ModifierFunction()
}
