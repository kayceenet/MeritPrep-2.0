package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.PhysicsPracticalAnalysisEngine
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONObject
import kotlin.math.max
import kotlin.math.min

@Composable
fun PhysicsWaecPracticalScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val task = PhysicsWaecPracticalBank.tasks[selectedIndex]
    val noteId = "waec-physics-practical:${task.id}"
    val savedPayload = remember(task.id) { database.getNoteBody(noteId) }
    var learnerResponse by remember(task.id) {
        mutableStateOf(runCatching { JSONObject(savedPayload ?: "{}").optString("answer", "") }.getOrDefault(""))
    }
    var saveMessage by remember(task.id) {
        mutableStateOf(if (savedPayload.isNullOrBlank()) "" else "Your last saved response is available on this device.")
    }
    var showWorkedAnalysis by remember(task.id) { mutableStateOf(false) }
    val fit = remember(task) { PhysicsPracticalAnalysisEngine.linearFit(task.readings) }
    val derivedValue = remember(task, fit) {
        when (task.id) {
            "waec-physics-hooke" -> fit.gradient
            "waec-physics-pendulum" -> PhysicsPracticalAnalysisEngine.gravityFromPendulumGradient(fit.gradient)
            "waec-physics-ohm" -> fit.gradient
            "waec-physics-density" -> fit.gradient
            "waec-physics-resistivity" -> fit.gradient * 0.50e-6
            "waec-physics-moments" -> fit.gradient
            "waec-physics-focal-length" -> 1.0 / fit.intercept
            "waec-physics-specific-heat" -> fit.gradient / 0.20
            "waec-physics-parallel-resistors" -> fit.gradient
            else -> fit.gradient
        }
    }
    val derivedLabel = when (task.id) {
        "waec-physics-hooke" -> "Spring constant, k"
        "waec-physics-pendulum" -> "Gravitational field strength, g"
        "waec-physics-ohm" -> "Resistance, R"
        "waec-physics-density" -> "Density, ρ"
        "waec-physics-resistivity" -> "Resistivity, ρ"
        "waec-physics-moments" -> "Perpendicular distance, d"
        "waec-physics-focal-length" -> "Focal length, f"
        "waec-physics-specific-heat" -> "Specific heat capacity, c"
        "waec-physics-parallel-resistors" -> "Equivalent resistance, R"
        else -> "Gradient"
    }
    val derivedUnit = when (task.id) {
        "waec-physics-hooke" -> "N m⁻¹"
        "waec-physics-pendulum" -> "m s⁻²"
        "waec-physics-ohm" -> "Ω"
        "waec-physics-density" -> "g cm⁻³"
        "waec-physics-resistivity" -> "Ω m"
        "waec-physics-moments" -> "m"
        "waec-physics-focal-length" -> "cm"
        "waec-physics-specific-heat" -> "J kg⁻¹ K⁻¹"
        "waec-physics-parallel-resistors" -> "Ω"
        else -> ""
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("WAEC Physics practical practice", style = MaterialTheme.typography.headlineMedium)
        Text("Practise experimental variables, tables, graphs, gradients, units, precautions and evaluation. The supplied readings are simulated learning data, not official WAEC questions.")
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PhysicsWaecPracticalBank.tasks.forEachIndexed { index, item ->
                FilterChip(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        showWorkedAnalysis = false
                    },
                    label = { Text(item.title.substringBefore(" ")) }
                )
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(task.title, style = MaterialTheme.typography.titleLarge)
                Text("Aim", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                ScientificText(task.aim, style = MaterialTheme.typography.bodyMedium)
                Text("Apparatus", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                Text(task.apparatus.joinToString(" · "))
                Text("Independent variable: ${task.independentVariable}")
                Text("Dependent variable: ${task.dependentVariable}")
                Text("Keep constant", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                task.controlledVariables.forEach { Text("• $it", style = MaterialTheme.typography.bodySmall) }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Recorded readings", style = MaterialTheme.typography.titleMedium)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(task.xAxisLabel, style = MaterialTheme.typography.labelMedium)
                    Text(task.yAxisLabel, style = MaterialTheme.typography.labelMedium)
                }
                HorizontalDivider()
                task.readings.forEachIndexed { index, point ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("%.3f".format(point.x))
                        Text("%.3f".format(point.y))
                    }
                    if (index != task.readings.lastIndex) HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                }
                Text("Use the units shown in the column headings when you copy the readings into your answer.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Graph of the supplied readings", style = MaterialTheme.typography.titleMedium)
                Text("Vertical axis: ${task.yAxisLabel}   ·   Horizontal axis: ${task.xAxisLabel}", style = MaterialTheme.typography.bodySmall)
                PracticalGraph(task.readings, fit.gradient, fit.intercept)
                Text("The points and fitted line are displayed to help you check the trend. In the examination, you must choose a scale, plot points and draw the best-fit line yourself.", style = MaterialTheme.typography.bodySmall)
            }
        }
        Text("Your task", style = MaterialTheme.typography.titleLarge)
        ScientificText(task.taskPrompt, style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = learnerResponse,
            onValueChange = { learnerResponse = it; showWorkedAnalysis = false; saveMessage = "" },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            label = { Text("Write your calculations, graph interpretation and precautions") },
            placeholder = { Text("Include formulae, substitution, units and experimental precautions…") }
        )
        Button(
            onClick = {
                val payload = JSONObject().put("answer", learnerResponse)
                database.saveNote(noteId, task.id, "WAEC Physics practical: ${task.title}", payload.toString())
                saveMessage = "Practical response saved on this device."
            },
            enabled = learnerResponse.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Save my practical response") }
        if (saveMessage.isNotBlank()) {
            Text(saveMessage, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }
        Button(onClick = { showWorkedAnalysis = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Reveal worked analysis and marking checklist")
        }
        if (showWorkedAnalysis) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Worked analysis", style = MaterialTheme.typography.titleLarge)
                    Text("Gradient: ${"%.4f".format(fit.gradient)}")
                    Text("Vertical intercept: ${"%.4f".format(fit.intercept)}")
                    Text("R² for supplied readings: ${"%.4f".format(fit.coefficientOfDetermination)}")
                    Text("$derivedLabel: ${"%.3f".format(derivedValue)} $derivedUnit", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    ScientificText(task.workedInterpretation, style = MaterialTheme.typography.bodyMedium)
                    HorizontalDivider()
                    Text("Examiner checklist", style = MaterialTheme.typography.titleMedium)
                    task.examinerPoints.forEach { ScientificText("• $it", style = MaterialTheme.typography.bodyMedium) }
                    Text("Self-assessment only: the app does not automatically award marks for free-text responses.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        Text("Practical responses are stored locally on this device. Paper 3 practice format: the current working syllabus reference describes three practical questions, of which candidates answer two, in 2¾ hours for 50 marks. Confirm the instructions for your examination session with WAEC or your school.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun PracticalGraph(
    points: List<com.kaycee.meritprep.data.PracticalGraphPoint>,
    gradient: Double,
    intercept: Double
) {
    val minX = points.minOf { it.x }
    val maxX = points.maxOf { it.x }
    val minY = points.minOf { it.y }
    val maxY = points.maxOf { it.y }
    val xPad = (maxX - minX) * 0.08
    val yPad = (maxY - minY) * 0.12
    val x0 = minX - xPad
    val x1 = maxX + xPad
    val y0 = min(0.0, minY - yPad)
    val y1 = max(maxY + yPad, gradient * x1 + intercept)
    val lineColor = MaterialTheme.colorScheme.primary
    val pointColor = MaterialTheme.colorScheme.tertiary
    val gridColor = MaterialTheme.colorScheme.outlineVariant
    Canvas(Modifier.fillMaxWidth().height(220.dp)) {
        val margin = 26f
        val plotWidth = size.width - 2f * margin
        val plotHeight = size.height - 2f * margin
        fun mapX(x: Double) = margin + ((x - x0) / (x1 - x0)).toFloat() * plotWidth
        fun mapY(y: Double) = size.height - margin - ((y - y0) / (y1 - y0)).toFloat() * plotHeight
        for (step in 0..4) {
            val gx = margin + plotWidth * step / 4f
            val gy = margin + plotHeight * step / 4f
            drawLine(gridColor, Offset(gx, margin), Offset(gx, size.height - margin), strokeWidth = 1f)
            drawLine(gridColor, Offset(margin, gy), Offset(size.width - margin, gy), strokeWidth = 1f)
        }
        drawLine(lineColor, Offset(margin, size.height - margin), Offset(size.width - margin, size.height - margin), strokeWidth = 2f)
        drawLine(lineColor, Offset(margin, margin), Offset(margin, size.height - margin), strokeWidth = 2f)
        drawLine(
            lineColor,
            Offset(mapX(x0), mapY(gradient * x0 + intercept)),
            Offset(mapX(x1), mapY(gradient * x1 + intercept)),
            strokeWidth = 3f
        )
        points.forEach { point ->
            drawCircle(pointColor, radius = 5f, center = Offset(mapX(point.x), mapY(point.y)))
        }
    }
}
