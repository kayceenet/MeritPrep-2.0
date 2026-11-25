package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
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

@Composable
fun PhysicsWaecTheoryScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val tasks = PhysicsWaecTheoryBank.tasks
    var selectedIndex by remember { mutableIntStateOf(0) }
    val task = tasks[selectedIndex]
    val noteId = "waec-physics-theory:${task.id}"
    val savedPayload = remember(task.id) { database.getNoteBody(noteId) }
    var answer by remember(task.id) {
        mutableStateOf(
            runCatching { JSONObject(savedPayload ?: "{}").optString("answer", "") }
                .getOrDefault("")
        )
    }
    var revealed by remember(task.id) { mutableStateOf(false) }
    var checkedPoints by remember(task.id) {
        mutableStateOf(
            runCatching {
                val values = JSONObject(savedPayload ?: "{}").optJSONArray("checkedPoints") ?: JSONArray()
                buildSet { for (i in 0 until values.length()) add(values.getInt(i)) }
            }.getOrDefault(emptySet())
        )
    }
    var saveMessage by remember(task.id) {
        mutableStateOf(if (savedPayload.isNullOrBlank()) "" else "Your last saved response is available on this device.")
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("WAEC Physics theory practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Original MeritPrep structured-response tasks. These are not official WAEC past questions or an official marking scheme.")
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tasks.forEachIndexed { index, _ ->
                FilterChip(selected = selectedIndex == index, onClick = { selectedIndex = index }, label = { Text("Q${index + 1}") })
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(task.topic, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("${task.marks} marks", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                ScientificText(task.prompt, style = MaterialTheme.typography.bodyLarge)
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it; saveMessage = "" },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6,
                    label = { Text("Write your answer before revealing the guidance") },
                    placeholder = { Text("Show equations, substitutions, units and explanations…") }
                )
                Button(
                    onClick = {
                        val payload = JSONObject()
                            .put("answer", answer)
                            .put("checkedPoints", JSONArray(checkedPoints.sorted()))
                        database.saveNote(noteId, task.id, "WAEC Physics theory: ${task.topic}", payload.toString())
                        saveMessage = "Response and self-check progress saved on this device."
                    },
                    enabled = answer.isNotBlank() || checkedPoints.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Save my response") }
                if (saveMessage.isNotBlank()) {
                    Text(saveMessage, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                }
                Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (revealed) "Marking guidance revealed" else "Reveal marking guidance")
                }
            }
        }
        if (revealed) {
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Indicative marking points", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("Compare your answer with each point. Tick only what you have correctly included.")
                    task.markingPoints.forEachIndexed { index, point ->
                        Row(verticalAlignment = Alignment.Top) {
                            Checkbox(
                                checked = index in checkedPoints,
                                onCheckedChange = { checked ->
                                    checkedPoints = if (checked) checkedPoints + index else checkedPoints - index
                                    saveMessage = ""
                                }
                            )
                            ScientificText(point, modifier = Modifier.weight(1f).padding(top = 12.dp), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                    Text("${checkedPoints.size} of ${task.markingPoints.size} marking points identified", fontWeight = FontWeight.SemiBold)
                    HorizontalDivider()
                    Text("Examiner guidance", fontWeight = FontWeight.Bold)
                    ScientificText(task.guidance, style = MaterialTheme.typography.bodyMedium)
                    OutlinedButton(
                        onClick = {
                            val payload = JSONObject()
                                .put("answer", answer)
                                .put("checkedPoints", JSONArray(checkedPoints.sorted()))
                            database.saveNote(noteId, task.id, "WAEC Physics theory: ${task.topic}", payload.toString())
                            saveMessage = "Response and self-check progress saved on this device."
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Save answer and checklist") }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { selectedIndex = (selectedIndex - 1).coerceAtLeast(0) }, enabled = selectedIndex > 0) { Text("Previous task") }
            Button(onClick = { selectedIndex = (selectedIndex + 1).coerceAtMost(tasks.lastIndex) }, enabled = selectedIndex < tasks.lastIndex) { Text("Next task") }
        }
        Text("Self-check only: the checklist is not an official mark allocation. Your written response is not submitted or graded by a teacher. Responses are stored locally on this device. Independent Physics-teacher review is still required.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
