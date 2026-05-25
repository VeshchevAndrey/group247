package com.example.application247

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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.application247.ui.theme.Application247Theme
import com.example.application247.ui.theme.backgroundGradient
import com.example.application247.ui.theme.boxModifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application247Theme(dynamicColor = false, darkTheme = true) {
                ApplicationScreen()
            }
        }
    }
}

@Composable
fun ApplicationScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "names_screen"){
        composable(route = "names_screen") { CreatureNamesScreen(navController = navController) }
        composable(
            route = "details_screen/{creatureId}",
            arguments = listOf(navArgument("creatureId") { type = NavType.IntType })
            ) { backStackEntry ->
            val creatureId = backStackEntry.arguments?.getInt("creatureId") ?: 0
            CreatureDetailsScreen(navController = navController, creatureId = creatureId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatureNamesScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Бестиарий",
                    style = TextStyle(shadow = Shadow(
                        color = Color.Black, offset = Offset(x = 2f, y = 4f), blurRadius = 0f),
                        fontFamily = FontFamily(Font(R.font.mono_nova)),
                        fontSize = 28.sp
                    )
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004CA8),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black,
        contentColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(items = CreaturesRepository.creatures) { creature ->
                CreatureNameItem(creature = creature) {
                    navController.navigate("details_screen/${creature.id}")
                }
            }
        }
    }
}

@Composable
fun CreatureNameItem(creature: Creature, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(5.dp))
            .background(brush = backgroundGradient, shape = RoundedCornerShape(5.dp))
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = creature.name,
            fontSize = 20.sp,
            style = TextStyle(shadow = Shadow(
                color = Color.Black, offset = Offset(x = 2f, y = 4f), blurRadius = 0f),
                fontFamily = FontFamily(Font(R.font.mono_nova))
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatureDetailsScreen(navController: NavController, creatureId: Int){
    val creature = CreaturesRepository.getCreatureById(creatureId)

    if (creature == null) {
        Text(text = "Такого существа нет!")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = creature.name,
                    style = TextStyle(shadow = Shadow(
                        color = Color.Black, offset = Offset(x = 2f, y = 4f), blurRadius = 0f),
                        fontFamily = FontFamily(Font(R.font.mono_nova)),
                        fontSize = 28.sp
                    )
                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004CA8),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black,
        contentColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(modifier = boxModifier) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.battlebg_ffvii_jungle),
                    contentDescription = "Задний фон",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Image(
                    bitmap = ImageBitmap.imageResource(creature.image),
                    contentDescription = creature.name,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(250.dp)
                )
            }
            Box(modifier = boxModifier) {
                Text(
                    text = creature.description,
                    style = TextStyle(shadow = Shadow(
                        color = Color.Black, offset = Offset(x = 2f, y = 4f), blurRadius = 0f),
                        fontFamily = FontFamily(Font(R.font.mono_nova)),
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}