// package com.example.application247

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Контроллер навигации - отвечает за стэк навигации
            val navController = rememberNavController()

            MainNavigation(navController = navController)
        }
    }
}

// Хост навигации - указывает возможные пути (routes) к пунктам назначаения назначения
@Composable
fun MainNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeScreen(navController = navController) }
        composable("search") { SearchScreen(navController = navController) }
    }
}

// Пункты назначения (destination)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Домашняя страница") },
                actions = { IconButton(onClick = { navController.navigate("search") }) {
                    Icon(Icons.Rounded.Search, "Поиск") }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item { Text("Домашняя страница") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Страница поиска") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            "Домой"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("Страница поиска")
        }
    }
}
