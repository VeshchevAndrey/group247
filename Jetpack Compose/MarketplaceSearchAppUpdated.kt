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
import androidx.compose.material.icons.rounded.Clear
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
import androidx.compose.runtime.State
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
            val searchState = remember { mutableStateOf(SearchState()) }

            val carList = arrayOf(
                Car(brand = "Volkswagen", model = "Golf II", price = 2000.0),
                Car(brand = "BMW", model = "X5", price = 1800.0),
                Car(brand = "Mercedes-Benz", model = "GLA", price = 2200.0),
                Car(brand = "Toyota", model = "Camry", price = 1500.0)

            )

            val filteredItems = remember(
                searchState.value.brand,
                searchState.value.model,
                searchState.value.minCost,
                searchState.value.maxCost
            ) {
                derivedStateOf { carList.filter { item ->
                    val brandMatch = (item.brand.contains(searchState.value.brand, true)) or
                            (searchState.value.brand.isEmpty())
                    val modelMatch = (item.model.contains(searchState.value.model, true)) or
                            (searchState.value.model.isEmpty())
                    val minVal = searchState.value.minCost.toDoubleOrNull() ?: 0.0
                    val maxVal = searchState.value.maxCost.toDoubleOrNull() ?: Double.MAX_VALUE
                    val priceMatch = (item.price >= minVal) and (item.price <= maxVal)

                    brandMatch and modelMatch and priceMatch
                } }
            }

            MainNavigation(
                navController = navController,
                carList = filteredItems,
                curState = searchState,
                filteredCount = filteredItems.value.size
            )
        }
    }
}

// Хост навигации - указывает возможные пути (routes) к пунктам назначения
@Composable
fun MainNavigation(
    navController: NavHostController,
    carList:  State<List<Car>>,
    curState:  MutableState<SearchState>,
    filteredCount: Int
){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            HomeScreen(navController = navController, carList)
        }
        composable("search") {
            SearchScreen(navController = navController, curState, filteredCount)
        }
    }
}

// Пункты назначения (destination)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, carList: State<List<Car>>){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Car list (${carList.value.size})") },
                actions = { IconButton(onClick = { navController.navigate("search") }) {
                    Icon(Icons.Rounded.Search, "Поиск") }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items = carList.value) {car ->
                SingleCar(car)
            }
        }

        if (carList.value.isEmpty()) {
            Text(text = "Items not found!", modifier = Modifier.padding(paddingValues))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchState: MutableState<SearchState>,
    count: Int){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
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
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {searchState.value = searchState.value.copy(brand = "")}) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
            OutlinedTextField(
                value = searchState.value.model,
                onValueChange = { searchState.value = searchState.value.copy(model = it) },
                placeholder = { Text("Model") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {searchState.value = searchState.value.copy(model = "")}) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
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
                shape = RoundedCornerShape(5.dp),
                enabled = (count > 0)
            ) { Text(text = "Confirm (${count})") }
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
