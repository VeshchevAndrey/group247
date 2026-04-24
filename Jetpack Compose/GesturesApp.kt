// package com.example.application247

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.application247.ui.theme.Application247Theme
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme() {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Моё приложение") },
                        )
                    }
                ) { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier = Modifier){
    val colorState = remember { mutableStateOf(0x00FFFFFF) }
    val textState = remember { mutableStateOf("Нажми на меня!") }

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(Color(colorState.value))
                .fillMaxWidth()
                .size(Random.nextInt(50..150).dp)
                .clickable(onClick = { colorState.value = Random.nextInt() })
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Image"
            )
            Text(text = "Нажми на меня!")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = { textState.value = "Вы зажали этот объект!" },
                        onTap = { textState.value = "Вы коснулись объекта!" },
                        onDoubleTap = { textState.value = "Вы дважды кликнули по объекту!" },
                        onPress = { textState.value = "Вы нажали на объект!" }
                    )
                }
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Image"
            )
            Text(text = textState.value, style = MaterialTheme.typography.labelSmall)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Image"
            )
            Button(onClick = {}) {
                Text(text = "Нажми на меня!")
            }
        }
    }
}
