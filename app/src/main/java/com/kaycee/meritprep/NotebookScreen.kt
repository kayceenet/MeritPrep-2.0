package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.MistakeNote
import com.kaycee.meritprep.data.SavedStudyNote

@Composable
fun NotebookScreen(onOpenLesson: (String) -> Unit) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var selectedTab by remember { mutableIntStateOf(0) }
    var search by remember { mutableStateOf("") }
    var refreshKey by remember { mutableIntStateOf(0) }
    var pendingDelete by remember { mutableStateOf<SavedStudyNote?>(null) }
    val notes = remember(refreshKey, search) { database.getStudyNotes(search) }
    val bookmarks = remember(refreshKey) { database.getBookmarkedItemIds("lesson") }
    val mistakes = remember(refreshKey) { database.getMistakes() }

    Column(Modifier.fillMaxSize().padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column(Modifier.padding(horizontal = 18.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("YOUR NOTEBOOK", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Saved learning", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Keep your explanations, bookmarked lessons and corrections together.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        TabRow(selectedTabIndex = selectedTab) {
            listOf("Notes", "Saved lessons", "Mistakes").forEachIndexed { index, label ->
                Tab(selected = selectedTab == index, onClick = { selectedTab = index }, text = { Text(label) })
            }
        }
        when (selectedTab) {
            0 -> {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    singleLine = true,
                    placeholder = { Text("Search saved notes") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )
                if (notes.isEmpty()) {
                    NotebookEmptyState("No saved notes yet", "Save a lesson note, investigation record or project response. It will appear here for later revision.")
                } else {
                    LazyColumn(Modifier.weight(1f).fillMaxWidth(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                        items(notes, key = { it.noteId }) { note ->
                            Card(Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                                    Text(LearningCatalog.find(note.topicId)?.title ?: note.topicId, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                                    Text(note.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Text(note.body, style = MaterialTheme.typography.bodyMedium, maxLines = 5)
                                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedButton(onClick = { onOpenLesson(note.topicId) }, modifier = Modifier.weight(1f)) { Text("Open lesson") }
                                        IconButton(onClick = { pendingDelete = note }) { Icon(Icons.Default.Delete, contentDescription = "Delete note", tint = MaterialTheme.colorScheme.error) }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            1 -> {
                if (bookmarks.isEmpty()) {
                    NotebookEmptyState("No saved lessons", "Use the bookmark control in a lesson to keep it in this list.")
                } else {
                    LazyColumn(Modifier.weight(1f).fillMaxWidth(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(bookmarks, key = { it }) { id ->
                            Card(Modifier.fillMaxWidth(), onClick = { onOpenLesson(id) }) {
                                ListItem(
                                    headlineContent = { Text(LearningCatalog.find(id)?.title ?: id, fontWeight = FontWeight.SemiBold) },
                                    supportingContent = { Text("Bookmarked lesson") },
                                    leadingContent = { Icon(Icons.Default.Bookmark, contentDescription = null, tint = MaterialTheme.colorScheme.primary) }
                                )
                            }
                        }
                    }
                }
            }
            else -> {
                if (mistakes.isEmpty()) {
                    NotebookEmptyState("No outstanding mistakes", "Incorrect and unanswered practice questions will appear here when they are recorded.")
                } else {
                    LazyColumn(Modifier.weight(1f).fillMaxWidth(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                        items(mistakes, key = { it.mistakeId }) { note ->
                            NotebookMistakeCard(note, database) { refreshKey++ }
                        }
                    }
                }
            }
        }
    }
    if (pendingDelete != null) {
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            title = { Text("Delete saved note?") },
            text = { Text("This removes this note from the device. It does not delete the lesson or investigation.") },
            confirmButton = {
                TextButton(onClick = {
                    database.deleteStudyNote(pendingDelete!!.noteId)
                    pendingDelete = null
                    refreshKey++
                }) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { pendingDelete = null }) { Text("Cancel") } }
        )
    }
}

@Composable
private fun NotebookMistakeCard(note: MistakeNote, database: MeritDatabase, onChanged: () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
            Text(LearningCatalog.find(note.topicId)?.title ?: note.topicId, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            Text(note.prompt, fontWeight = FontWeight.SemiBold)
            Text("Your answer: " + note.selectedAnswer, color = MaterialTheme.colorScheme.error)
            Text("Correct answer: " + note.correctAnswer, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            Text(note.explanation, style = MaterialTheme.typography.bodySmall)
            OutlinedButton(onClick = { database.setMistakeResolved(note.mistakeId, true); onChanged() }, modifier = Modifier.fillMaxWidth()) {
                Text("Mark as reviewed")
            }
        }
    }
}

@Composable
private fun NotebookEmptyState(title: String, description: String) {
    Column(Modifier.fillMaxWidth().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(Icons.Default.MenuBook, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(34.dp))
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
