package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.kaycee.meritprep.data.MeritDatabase

@Composable
fun BookmarksScreen(onOpenLesson: (String) -> Unit) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var savedIds by remember { mutableStateOf(database.getBookmarkedItemIds("lesson")) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Saved lessons", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Keep important lessons close for quick revision. Your saved list is stored offline.")
        if (savedIds.isEmpty()) {
            Text("No saved lessons yet. Open a lesson and select Save this lesson.")
        } else {
            Text("${savedIds.size} saved lesson(s)", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(savedIds, key = { it }) { id ->
                    val title = LearningCatalog.find(id)?.title ?: id
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(title, fontWeight = FontWeight.SemiBold)
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = { onOpenLesson(id) }, modifier = Modifier.weight(1f)) { Text("Open lesson") }
                                OutlinedButton(
                                    onClick = {
                                        database.removeBookmark(id)
                                        savedIds = database.getBookmarkedItemIds("lesson")
                                    }
                                ) { Text("Remove") }
                            }
                        }
                    }
                }
            }
        }
    }
}
