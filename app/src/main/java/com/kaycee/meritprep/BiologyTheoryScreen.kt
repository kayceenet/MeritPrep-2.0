package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONArray
import org.json.JSONObject

private fun decodeBiologyTheoryProgress(raw: String?): Pair<String, Set<Int>> {
    if (raw.isNullOrBlank()) return "" to emptySet()
    return runCatching {
        val payload = JSONObject(raw)
        val points = payload.optJSONArray("checkedPoints") ?: JSONArray()
        val checked = buildSet {
            for (index in 0 until points.length()) {
                val point = points.optInt(index, -1)
                if (point >= 0) add(point)
            }
        }
        payload.optString("response", "") to checked
    }.getOrElse {
        // Preserve answers saved by earlier app versions as plain text.
        raw to emptySet()
    }
}

@Composable
fun BiologyTheoryScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val tasks = remember { BiologyTheoryBank.all }
    var selectedTaskId by remember { mutableStateOf(tasks.first().id) }
    var menuExpanded by remember { mutableStateOf(false) }
    val task = tasks.first { it.id == selectedTaskId }
    val noteId = "waec-theory-${task.id}"
    val restoredProgress = remember(task.id) { decodeBiologyTheoryProgress(database.getNoteBody(noteId)) }
    var response by remember(task.id) { mutableStateOf(restoredProgress.first) }
    var saved by remember(task.id) { mutableStateOf(false) }
    var rubricVisible by remember(task.id) { mutableStateOf(false) }
    var checkedPoints by remember(task.id) { mutableStateOf(restoredProgress.second) }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("WAEC Biology theory practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Write a structured response, save your work, then compare it with the marking points.")
        Box {
            OutlinedButton(onClick = { menuExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text(task.title)
            }
            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                tasks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.title) },
                        onClick = {
                            selectedTaskId = item.id
                            menuExpanded = false
                        }
                    )
                }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("QUESTION · ${task.marks} MARKS", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text(task.instruction, style = MaterialTheme.typography.bodyLarge)
            }
        }
        OutlinedTextField(
            value = response,
            onValueChange = { response = it; saved = false },
            modifier = Modifier.fillMaxWidth(),
            minLines = 7,
            label = { Text("Write your answer") },
            placeholder = { Text("Organise your response into clear points. Use correct biological terms and show calculations where required.") }
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    val payload = JSONObject()
                        .put("response", response)
                        .put("checkedPoints", JSONArray().apply {
                            checkedPoints.sorted().forEach { put(it) }
                        })
                    database.saveNote(noteId, task.topicId, "WAEC theory · ${task.title}", payload.toString())
                    saved = true
                },
                enabled = response.isNotBlank()
            ) {
                Text("Save answer")
            }
            if (saved) Text("Saved on this device", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelMedium)
        }
        OutlinedButton(
            onClick = { rubricVisible = !rubricVisible },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (rubricVisible) "Hide marking guide" else "Reveal marking guide")
        }
        if (rubricVisible) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Marking points", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Tick each distinct point that your answer contains. Use scientifically equivalent wording where appropriate.")
                    task.markingPoints.forEachIndexed { index, point ->
                        Row(verticalAlignment = Alignment.Top) {
                            Checkbox(
                                checked = index in checkedPoints,
                                onCheckedChange = { checked ->
                                    checkedPoints = if (checked) checkedPoints + index else checkedPoints - index
                                    saved = false
                                }
                            )
                            Text(point, modifier = Modifier.weight(1f).padding(top = 11.dp))
                        }
                    }
                    HorizontalDivider()
                    Text(
                        "Self-check: ${checkedPoints.size} / ${task.marks} marking points identified",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Model response", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(task.modelAnswer, style = MaterialTheme.typography.bodyLarge)
                    TextButton(onClick = { checkedPoints = emptySet() }) { Text("Reset self-check") }
                }
            }
        }
        Text(
            "These are original MeritPrep WAEC-style practice tasks, not official past questions. The marking points are teaching guidance, not an official WAEC marking scheme. Self-assessment is not examiner marking.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
