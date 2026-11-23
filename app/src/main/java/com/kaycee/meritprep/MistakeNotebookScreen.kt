package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.MistakeNote

@Composable
fun MistakeNotebookScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var showResolved by remember { mutableStateOf(false) }
    val notes = remember { mutableStateListOf<MistakeNote>() }

    fun refresh() {
        notes.clear()
        notes.addAll(database.getMistakes(resolvedOnly = showResolved))
    }

    LaunchedEffect(database, showResolved) { refresh() }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Mistake notebook", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Review incorrect and unanswered questions, understand the correction, then mark the concept as reviewed.")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = !showResolved, onClick = { showResolved = false }, label = { Text("To review") })
            FilterChip(selected = showResolved, onClick = { showResolved = true }, label = { Text("Reviewed") })
        }
        if (notes.isEmpty()) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(if (showResolved) "No reviewed mistakes yet" else "Your notebook is clear", fontWeight = FontWeight.Bold)
                    Text(if (showResolved) "When you resolve an item, it will appear here." else "Incorrect answers from Biology practice and timed mocks will be collected here.")
                }
            }
        } else {
            Text("${notes.size} item(s)", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(notes, key = { it.mistakeId }) { note ->
                    val topicTitle = LearningCatalog.topics.firstOrNull { it.id == note.topicId }?.title ?: note.topicId
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(topicTitle, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                            Text(note.prompt, fontWeight = FontWeight.SemiBold)
                            Text("Your answer: ${note.selectedAnswer}", color = MaterialTheme.colorScheme.error)
                            Text("Correct answer: ${note.correctAnswer}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                            Text(note.explanation, style = MaterialTheme.typography.bodySmall)
                            OutlinedButton(
                                onClick = {
                                    database.setMistakeResolved(note.mistakeId, !note.isResolved)
                                    refresh()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(if (note.isResolved) "Return to review list" else "Mark as reviewed") }
                        }
                    }
                }
            }
        }
    }
}
