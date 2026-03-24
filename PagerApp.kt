// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {

        }
    }
}

val characters = arrayOf(
    Character("Cloud Strife", R.drawable.img1),
    Character("Tifa Lockhart", R.drawable.img2),
    Character("Aerith Gainsborough", R.drawable.img3),
    Character("Barret Wallace", R.drawable.img4)
)

@Composable
fun PagerFunction(){
    val pagerState = rememberPagerState(0) { characters.size }

    HorizontalPager (
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = characters[it].name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
                )
            Image(
                bitmap = ImageBitmap.imageResource(characters[it].image),
                contentDescription = characters[it].name,
                modifier = Modifier.padding(10.dp).size(250.dp)
            )
        }
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    PagerFunction()
}

data class Character(val name: String, val image: Int)
