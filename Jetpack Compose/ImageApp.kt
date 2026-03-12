// package com.example.application247

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            ImageFunction()
        }
    }
}

@Composable
fun ImageFunction(){
    Column() {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.dog),
            contentDescription = "The dog",
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .border(width = 5.dp, color = Color.Yellow, shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.dog2),
            contentDescription = "The dog",
            modifier = Modifier.size(300.dp, 200.dp),
            contentScale = ContentScale.Fit
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.dog_head),
            contentDescription = "The dog",
            modifier = Modifier
                .size(300.dp)
            )
    }
}


@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    ImageFunction()
}
