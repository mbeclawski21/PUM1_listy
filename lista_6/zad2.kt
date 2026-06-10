package com.example.myapplication.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
data class Task(val id: Int, val description: String, val maxPoints: Int)
data class AssignmentList(val id: String, val subject: String, val listNumber: Int, val grade: Double, val tasks: List<Task>)

val sampleAssignmentLists = listOf(
    AssignmentList("Matematyka_L1", "Matematyka", 1, 3.5, listOf(
        Task(1, "Jeśli masz 3 jabłka i zjesz 1, ile jabłek zostanie?", 1),
        Task(2, "Jeśli kupisz 5 cukierków za 2 złote, ile kosztują 3 cukierki?", 1)
    )),
    AssignmentList("Matematyka_L2", "Matematyka", 2, 4.5, listOf(
        Task(1, "Zadanie trudniejsze z matematyki", 5)
    )),
    AssignmentList("PUM_L1", "PUM", 1, 5.0, listOf(
        Task(1, "Implementacja FizzBuzz", 3),
        Task(2, "Sprawdzenie palindromu", 3)
    )),
    AssignmentList("Fizyka_L1", "Fizyka", 1, 4.5, listOf(
        Task(1, "Oblicz prędkość spadającego jabłka", 2)
    ))
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppSimple()
        }
    }
}

//NAWIGACJA STANOWA ---
@Composable
fun MainAppSimple() {
    var currentScreen by remember { mutableStateOf("e1") }
    var selectedListId by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentScreen == "e1" || currentScreen == "e3",
                    onClick = { currentScreen = "e1" },
                    label = { Text("Listy zadań") },
                    icon = { Text("📋") }
                )
                NavigationBarItem(
                    selected = currentScreen == "e2",
                    onClick = { currentScreen = "e2" },
                    label = { Text("Oceny") },
                    icon = { Text("⭐") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                "e1" -> AssignmentListsScreen(onItemClick = { id ->
                    selectedListId = id
                    currentScreen = "e3"
                })
                "e2" -> GradesSummaryScreen()
                "e3" -> ListDetailScreen(listId = selectedListId)
            }
        }
    }
}

//E1: EKRAN LIST ZADAŃ
@Composable
fun AssignmentListsScreen(onItemClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { Text("Moje Listy Zadań", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp)) }
        items(sampleAssignmentLists) { list ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { onItemClick(list.id) }) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = list.subject, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Liczba zadań: ${list.tasks.size}", style = MaterialTheme.typography.bodySmall)
                    }
                    Column {
                        Text(text = "Lista ${list.listNumber}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Ocena: ${list.grade}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

//E2:EKRAN PODSUMOWANIA OCEN
@Composable
fun GradesSummaryScreen() {
    val summaryData = sampleAssignmentLists.groupBy { it.subject }.mapValues { entry ->
        entry.value.map { it.grade }.average()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { Text("Moje Oceny", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp)) }
        items(summaryData.toList()) { (subject, averageGrade) ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = subject, style = MaterialTheme.typography.titleMedium)
                        val count = sampleAssignmentLists.count { it.subject == subject }
                        Text(text = "Liczba list: $count", style = MaterialTheme.typography.bodySmall)
                    }
                    Text(text = "Średnia: %.1f".format(averageGrade), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

//E3: EKRAN SZCZEGÓŁÓW LISTY
@Composable
fun ListDetailScreen(listId: String) {
    val currentList = sampleAssignmentLists.find { it.id == listId }

    if (currentList == null) {
        Text("Nie znaleziono listy", modifier = Modifier.padding(16.dp))
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(text = "${currentList.subject}\nLista ${currentList.listNumber}", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))
        }
        items(currentList.tasks) { task ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Zadanie ${task.id}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "pkt: ${task.maxPoints}", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = task.description, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}