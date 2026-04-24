// package com.example.application247

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application247.ui.theme.Application247Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme() {
                Scaffold() { paddingValues ->
                    ClickerScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// Реализация "всё в одном"
//@Composable
//fun ClickerScreen(modifier: Modifier = Modifier){
//    val count = rememberSaveable() { mutableStateOf(0) }
//
//    Column(modifier = modifier) {
//        Button(onClick = { count.value++ }) { Text("Кликни на меня!") }
//        Text(text = "Вы кликнули ${count.value} раз")
//    }
//}

// Реализация через ViewModel
class ClickerViewModel : ViewModel() {
    val count = mutableStateOf(0)

    fun increase(){
        count.value++
    }
}

@Composable
fun ClickerScreen(modifier: Modifier = Modifier, vm: ClickerViewModel = viewModel()){
        Column(modifier = modifier) {
        Button(onClick = { vm.increase() }) { Text("Кликни на меня!") }
        Text(text = "Вы кликнули ${vm.count.value} раз")
    }
}
