package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.application247.ui.theme.Application247Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userRepository = UserRepository(this)
        val viewModel = UserDataViewModel(userRepository)

        setContent {
            Application247Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(
                        modifier = Modifier.padding(paddingValues),
                        vm = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier, vm: UserDataViewModel){
    val userName = vm.currentUser.collectAsState()

    Column(modifier = modifier) {
        TextField(value = vm.inputText.value, onValueChange = {
            vm.updateTextInput(text = it)
        })
        Button(onClick = {
            vm.updateName(newName = vm.inputText.value)
        }) { Text(text = "Сохранить") }
        Text(text = "Текущий пользователь: ${userName.value}")
    }
}