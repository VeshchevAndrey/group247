// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.platform.LocalConfiguration
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

// animateDpAsState - функция выполнения анимации через изменение значения dp

@Composable
fun ApplicationScreen(modifier: Modifier){
    val startOffset = 0
    val endOffset = LocalConfiguration.current.screenWidthDp - 100

    val boxOffset = remember { mutableStateOf(startOffset) }
    val offsetAnimation = animateDpAsState(
        targetValue = boxOffset.value.dp,
        animationSpec = tween(
            durationMillis = 2500,
            delayMillis = 0,
            easing = FastOutSlowInEasing
        )
    )

    Column(modifier = modifier) {
        Box(modifier = Modifier
            .offset(x = offsetAnimation.value)
            .size(100.dp)
            .background(Color.LightGray)
        )
        Box(modifier = Modifier
            .size(offsetAnimation.value)
            .background(Color.LightGray)
        )
        Button(onClick = {
            boxOffset.value = if (boxOffset.value == startOffset) endOffset else startOffset
        }) { Text(text = "Запустить") }
    }
}
