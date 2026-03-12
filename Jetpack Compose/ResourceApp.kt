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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса на экране приложения
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.profile)) }
                    )
                }
            ) {
                Application5(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun Application5(modifier: Modifier = Modifier){
    val name = "Андрей"
    val surname = "Вещев"
    val age = 27
    val unreadMessages = 101
    val profiles = stringArrayResource(R.array.links)

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.app_name) // Ссылка на строковый ресурс
        )
        Text(
            text = stringResource(R.string.user_data, name, surname, age)
        )
        Text(
            text = pluralStringResource(
                R.plurals.messages,
                unreadMessages,
                unreadMessages
            )
        )
        profiles.forEach {
            Text( text = it)
        }
        Button(
            onClick = {}
        ) {
            Text(
                text = stringResource(R.string.send_button)
            )
        }
    }
}

@Preview(showBackground = true) // аннотация для предпросмотра Composable-функций
@Composable
fun PreviewForMyFunctions(){
    Application5()
}
