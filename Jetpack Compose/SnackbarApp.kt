// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import com.example.application247.ui.theme.Application247Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme() {
                ApplicationApp()
            }
        }
    }
}

// Пример вызова простейшего всплывающего окна
//@Composable
//fun ApplicationApp(modifier: Modifier = Modifier){
//    Column(modifier = modifier) {
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//
//        Button(onClick = {
//            scope.launch {
//                snackbarHostState.showSnackbar(message = "Я всплывающее окно!")
//            }
//        }) { Text(text = "Нажми для открытия окна!") }
//        SnackbarHost(hostState = snackbarHostState)
//        Text(text = "Это текст!")
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationApp(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val color = remember { mutableStateOf(Color.White) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch { snackbarHostState.showSnackbar(message = "Нажал на супер-кнопку!") }
            }) {
                Icon(Icons.Rounded.Create, contentDescription = "Create")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                    message = "Это новое окно!",
                    actionLabel = "Жми!",
                    duration = SnackbarDuration.Short,
                    withDismissAction = true)

                    when (result){
                        SnackbarResult.ActionPerformed -> { color.value = Color.Yellow }
                        SnackbarResult.Dismissed -> { color.value = Color.White }
                    }
                }
            }) { Text(text = "Нажми для открытия окна!") }
            Text(text = "Это текст!")
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Placeholder",
                modifier = Modifier.fillMaxSize().background(color.value)
            )
        }
    }
}
