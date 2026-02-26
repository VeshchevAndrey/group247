// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {Text("Приложение с вопросами")}
                    )
                },
                bottomBar = {
                    BottomAppBar() {
                        Button(
                            onClick = {}
                        ) { Text("Нажимай!") }
                    }
                }
            ) {
                Application3(modifier = Modifier.padding(it))
            }

        }
    }
}

@Composable
fun Application3(modifier: Modifier = Modifier){
    val radioSelected = remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Выбери один из трёх вариантов:")
        Row() {
            Answer("Первый", radioSelected.value)
            Answer("Второй", radioSelected.value)
            Answer("Третий", radioSelected.value)
        }
    }
}

@Composable
fun Answer(option: String, select: Boolean){
    val selectRadio = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectRadio.value,
            onClick = {selectRadio.value = !selectRadio.value}
        )
        Text(text = option)
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    Application3()
}
