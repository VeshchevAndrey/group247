package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            Scaffold() {
                Application4(modifier = Modifier.padding(it))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Application4(modifier: Modifier = Modifier){
    val textInputState = remember { mutableStateOf("") }
    var sliderState by remember { mutableStateOf(20.0f) }
    val answers = arrayOf("Да", "Нет", "Возможно")
    val (value, setValue) = remember { mutableStateOf(answers[0]) }
    val (value2, setValue2) = remember { mutableStateOf(answers[0]) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = textInputState.value,
            onValueChange = {newText -> textInputState.value = newText},
            label = {Text(text = "Введите имя")}
        )
        Text(text = "Значение слайдера: $sliderState")
        Slider(
            value = sliderState,
            onValueChange = {sliderState = it},
            valueRange = 18.0f..65.0f,
        )
        Text(text = "Ты являешься человеком?")
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = answers[0])
            RadioButton(
                selected = value == answers[0],
                onClick = {setValue(answers[0])}
            )
            Text(text = answers[1])
            RadioButton(
                selected = value == answers[1],
                onClick = {setValue(answers[1])}
            )
            Text(text = answers[2])
            RadioButton(
                selected = value == answers[2],
                onClick = {setValue(answers[2])}
            )
        }
        Text(text = "Хотел бы ты стать ИИ?")
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            answers.forEach { answer ->
                Text(text = answer)
                RadioButton(
                    selected = (value2 == answer),
                    onClick = {setValue2(answer)}
                )
            }
        }
    }
}


@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){

}
