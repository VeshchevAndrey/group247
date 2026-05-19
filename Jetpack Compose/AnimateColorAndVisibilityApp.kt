// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.application247.ui.theme.Application247Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// animateDpAsState() - функция выполнения анимации через изменение значения dp
// animateColorAsState() - функция изменения цвета элемента через анимацию. Принимает объект Color
// tween() - функция управления анимацией, т.е. длительность, шаблон анимации, задержка
// keyframes{} - функция управления ключевыми кадрами анимации
// AnimatedVisibility - функция управления появления и скрытия элемента интерфейса через анимацию

@Composable
fun ApplicationScreen(modifier: Modifier) {
    val startColor = Color(0xFF673AB7)
    val endColor = Color(0xFFCDDC39)

    val boxColor = remember { mutableStateOf(startColor) }

    val colorAnimation = animateColorAsState(
        targetValue = boxColor.value,
        animationSpec = tween(durationMillis = 1000)
    )

    val boxColorExtended = remember { mutableStateOf(startColor) }

    val colorAnimationExtended = animateColorAsState(
        targetValue = boxColorExtended.value,
        animationSpec = keyframes {
            durationMillis = 1000
            Color(0xFF9C27B0) at 250
            Color(0xFF8BC34A) at 750
        }
    )
    val imageVisible = remember { mutableStateOf(true) }

    Column(modifier = modifier) {
        Box(modifier = Modifier.size(150.dp).background(colorAnimation.value))
        Button(onClick = {
            boxColor.value = if (boxColor.value == startColor) endColor else startColor
        }) { Text(text = "Изменить цвет") }

        Box(modifier = Modifier
            .size(150.dp)
            .background(colorAnimationExtended.value)
        )
        Button(onClick = {
            boxColorExtended.value =
                if (boxColorExtended.value == startColor) endColor else startColor
        }) { Text(text = "Изменить цвет") }

        AnimatedVisibility(
            visible = imageVisible.value,
            exit = fadeOut() + shrinkHorizontally()
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Placeholder",
                modifier = Modifier.size(150.dp)
            )
        }
        Button(onClick = {
            imageVisible.value = !imageVisible.value
        }) { Text(text = "Скрыть/показать") }
    }
}
