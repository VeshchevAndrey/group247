// package com.example.application247 - здесь название Вашего приложения

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            Scaffold() {
                CompanionsScreen(companions, Modifier.padding(it))
            }
        }
    }
}

val companion: Companion = Companion("Cloud Strife", R.drawable.img1, "Whatever")

val companions = arrayOf(
    Companion("Cloud Strife", R.drawable.img1, "Whatever"),
    Companion("Tifa Lockhart", R.drawable.img2, "Hello"),
    Companion("Aerith Gainsborough", R.drawable.img3, "Hi"),
    Companion("Barret Wallace", R.drawable.img4, "No, thanks."),
    Companion(name = "Cid Highwind", lastMessage = "The future belongs to those who believe in the beauty of their dreams.")
)

@Composable
fun CompanionsScreen(companions: Array<Companion>, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        companions.forEach { comp -> SingleCompanion(comp) }
    }
}

@Composable
fun SingleCompanion(companion: Companion){
    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.padding(10.dp).size(100.dp).clip(CircleShape),
            bitmap = ImageBitmap.imageResource(companion.image),
            contentDescription = "${companion.name} avatar"
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = companion.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(
                text = companion.lastMessage,
                fontSize = 27.sp,
                fontWeight = FontWeight.W300,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    SingleCompanion(companion)
}

// Объявление Data-класса
data class Companion(
    val name: String,
    val image: Int = R.drawable.placeholder,
    val lastMessage: String
)
