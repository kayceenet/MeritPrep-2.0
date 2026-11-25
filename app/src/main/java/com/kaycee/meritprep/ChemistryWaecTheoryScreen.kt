package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ChemistryWaecTheoryScreen() {
    val tasks = ChemistryWaecTheoryBank.tasks
    var selectedIndex by remember { mutableIntStateOf(0) }
    val task = tasks[selectedIndex]
    var answer by remember(task.id) { mutableStateOf("") }
    var revealed by remember(task.id) { mutableStateOf(false) }
    var checkedPoints by remember(task.id) { mutableStateOf(emptySet<Int>()) }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("WAEC Chemistry theory practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Original MeritPrep structured-response tasks. These are not official WAEC past questions or an official marking scheme.")
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tasks.forEachIndexed { index, item ->
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
                    onValueChange = { answer = it },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 6,
                    label = { Text("Write your answer before revealing the guidance") },
                    placeholder = { Text("Show equations, working, units and explanations where required…") }
                )
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
                                }
                            )
                            ScientificText(point, modifier = Modifier.weight(1f).padding(top = 12.dp), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                    Text("${checkedPoints.size} of ${task.markingPoints.size} marking points identified", fontWeight = FontWeight.SemiBold)
                    HorizontalDivider()
                    Text("Examiner guidance", fontWeight = FontWeight.Bold)
                    ScientificText(task.guidance, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { selectedIndex = (selectedIndex - 1).coerceAtLeast(0) }, enabled = selectedIndex > 0) { Text("Previous task") }
            Button(onClick = { selectedIndex = (selectedIndex + 1).coerceAtMost(tasks.lastIndex) }, enabled = selectedIndex < tasks.lastIndex) { Text("Next task") }
        }
        Text("Self-check only: the checklist is not an official mark allocation. Your written response is not submitted or graded by a teacher. Independent subject-teacher review is still required.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
