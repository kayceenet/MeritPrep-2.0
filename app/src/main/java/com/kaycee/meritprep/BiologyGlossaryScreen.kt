package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BiologyGlossaryScreen(onOpenLesson: (String) -> Unit = {}) {
    var query by remember { mutableStateOf("") }
    var selectedTopicId by remember { mutableStateOf("all") }
    var topicMenuExpanded by remember { mutableStateOf(false) }
    val topicOptions = remember {
        BiologyCurriculum.units.mapNotNull { unit ->
            unit.lessonId?.let { it to unit.title }
        }
    }
    val selectedTopicTitle = topicOptions.firstOrNull { it.first == selectedTopicId }?.second
        ?: "All topics"
    val filteredEntries = remember(query, selectedTopicId) {
        BiologyGlossary.entries.filter { entry ->
            (selectedTopicId == "all" || entry.topicId == selectedTopicId) &&
                (query.isBlank() ||
                    entry.term.contains(query, ignoreCase = true) ||
                    entry.definition.contains(query, ignoreCase = true) ||
                    entry.example.contains(query, ignoreCase = true))
        }.sortedBy { it.term.lowercase() }
    }

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text("Biology glossary", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Clear definitions, examples and links to the related lesson. Available offline.")
        }
        item {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search a term or definition") },
                singleLine = true
            )
        }
        item {
            Box {
                OutlinedButton(onClick = { topicMenuExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                    Text(selectedTopicTitle)
                }
                DropdownMenu(
                    expanded = topicMenuExpanded,
                    onDismissRequest = { topicMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("All topics") },
                        onClick = { selectedTopicId = "all"; topicMenuExpanded = false }
                    )
                    topicOptions.forEach { (id, title) ->
                        DropdownMenuItem(
                            text = { Text(title) },
                            onClick = { selectedTopicId = id; topicMenuExpanded = false }
                        )
                    }
                }
            }
        }
        item {
            Text("${filteredEntries.size} terms", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (filteredEntries.isEmpty()) {
            item { Text("No glossary terms match this search. Try another word or choose All topics.") }
        } else {
            items(filteredEntries, key = { it.term }) { entry ->
                val topicTitle = topicOptions.firstOrNull { it.first == entry.topicId }?.second ?: "Biology"
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        Text(entry.term, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(entry.definition, style = MaterialTheme.typography.bodyMedium)
                        Text("Example: ${entry.example}", style = MaterialTheme.typography.bodySmall)
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(topicTitle, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                            TextButton(onClick = { onOpenLesson(entry.topicId) }) { Text("Open lesson") }
                        }
                    }
                }
            }
        }
        item {
            Text(
                "These glossary entries are original learning definitions and remain subject to independent Biology review.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
