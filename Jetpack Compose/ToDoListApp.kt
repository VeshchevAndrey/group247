// package com.example.application247 - здесь название Вашего приложения!
 
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() {
                ToDoScreen(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun ToDoScreen(modifier: Modifier = Modifier){
    val newTaskTitle = remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Выполнено ${tasks.count { it.status }} задач из ${tasks.size}"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTaskTitle.value,
                onValueChange = { newTaskTitle.value = it },
                placeholder = { Text(text = "Новая задача") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newTaskTitle.value.isNotBlank()){
                        tasks.add(Task(title = newTaskTitle.value))
                        newTaskTitle.value = ""
                    }
                }
            ) { Text(text = "Добавить") }
        }
        LazyColumn() {
            items(items = tasks){ item ->
                TaskFunction(
                    task = item,
                    onStatusChanged = {
                        val index = tasks.indexOf(item)
                        if (index >= 0){
                            tasks[index] = item.copy(status = !item.status)
                        }
                    },
                    onDelete = { tasks.remove(item) }
                )
            }
        }
    }
}

@Composable
fun TaskFunction(task: Task, onStatusChanged: () -> Unit, onDelete: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.status,
            onCheckedChange = { onStatusChanged() }
        )
        Text(
            text = task.title,
            textDecoration = if (task.status) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { onDelete() }) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoScreen()
}

data class Task(val title: String, val status: Boolean = false)
