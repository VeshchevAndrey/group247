// package com.example.application247

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val counter = remember { mutableStateOf(0) }
            val double = remember { derivedStateOf { counter.value * 2 } }

            Column() {
                OutlinedButton(onClick = {counter.value++}) {
                    Text(text = "Счётчик: ${counter.value}", fontSize = 28.sp)
                }
                Text(text = "Удвоенный счётчик: ${double.value}", fontSize = 28.sp)
                InputAndButton()
            }
        }
    }
}

@Composable
fun InputAndButton(){
    val textInput = remember { mutableStateOf("") }
    val isButtonEnabled = remember { derivedStateOf { isValid(textInput.value) } }

    Column() {
        OutlinedTextField(
            value = textInput.value,
            onValueChange = { textInput.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(mask = '?')
        )
        OutlinedButton(
            onClick = {},
            enabled = isButtonEnabled.value
        ) { Text("Нажми на меня!") }
    }
}

fun isValid(text: String): Boolean{
    return text.length >= 8
}
