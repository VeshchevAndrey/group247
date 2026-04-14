// package com.example.application247

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
            val currentSearchState = remember { mutableStateOf(SearchState()) }

            val carList = arrayOf(
                Car(brand = "Volkswagen", model = "Golf II", price = 2000.0),
                Car(brand = "BMW", model = "X5", price = 1800.0),
                Car(brand = "Mercedes-Benz", model = "GLA", price = 2200.0),
                Car(brand = "Toyota", model = "Camry", price = 1500.0)

            )

            MainNavigation(
                navController = navController,
                carList = carList,
                curState = currentSearchState
            )
        }
    }
}

// Хост навигации - указывает возможные пути (routes) к пунктам назначаения назначения
@Composable
fun MainNavigation(
    navController: NavHostController,
    carList: Array<Car>,
    curState:  MutableState<SearchState>
){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            HomeScreen(navController = navController, carList, curState.value)
        }
        composable("search") {
            SearchScreen(navController = navController, curState)
        }
    }
}

// Пункты назначения (destination)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, carList: Array<Car>, searchState: SearchState){
    val filteredItems = remember(
        searchState.brand,
        searchState.model,
        searchState.minCost,
        searchState.maxCost
    ) {
        derivedStateOf { carList.filter { item ->
            val brandMatch = (item.brand.contains(searchState.brand, true)) or
                    (searchState.brand.isEmpty())
            val modelMatch = (item.model.contains(searchState.model, true)) or
                    (searchState.model.isEmpty())
            val minVal = searchState.minCost.toDoubleOrNull() ?: 0.0
            val maxVal = searchState.maxCost.toDoubleOrNull() ?: Double.MAX_VALUE
            val priceMatch = (item.price >= minVal) and (item.price <= maxVal)

            brandMatch and modelMatch and priceMatch
        } }
    }
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
            items(items = filteredItems.value) {car ->
                SingleCar(car)
            }
        }

        if (filteredItems.value.isEmpty()) {
            Text(text = "Items not found!", modifier = Modifier.padding(paddingValues))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, searchState: MutableState<SearchState>){
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
        Column(
            modifier = Modifier.padding(10.dp).padding(paddingValues).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchState.value.brand,
                onValueChange = {searchState.value = searchState.value.copy(brand = it)},
                placeholder = { Text("Brand") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = searchState.value.model,
                onValueChange = { searchState.value = searchState.value.copy(model = it) },
                placeholder = { Text("Model") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = searchState.value.minCost,
                    onValueChange = {searchState.value = searchState.value.copy(minCost = it)},
                    prefix = { Text(text = "cost from") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = searchState.value.maxCost,
                    onValueChange = {searchState.value = searchState.value.copy(maxCost = it)},
                    prefix = { Text(text = "to") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = { navController.navigate("home") },
                shape = RoundedCornerShape(5.dp)
            ) { Text(text = "Confirm") }
        }
    }
}

@Composable
fun SingleCar(item: Car){
    Row(
        modifier = Modifier.padding(10.dp).fillMaxWidth()
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(item.image),
            contentDescription = "${item.model} image"
        )
        Text(text = "${item.brand}, ${item.model}", modifier = Modifier.weight(1f))
        Text(text = "${item.price}")
    }
}

data class SearchState(
    val brand: String = "",
    val model: String = "",
    val minCost: String = "",
    val maxCost: String = ""
)

data class Car(
    val brand: String = "",
    val model: String = "",
    val price: Double = 0.0,
    val image: Int = R.drawable.cap_placeholder
)
