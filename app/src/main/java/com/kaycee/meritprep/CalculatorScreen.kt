package com.kaycee.meritprep
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.CalculationResult
import com.kaycee.meritprep.data.CalculatorEngine
private data class CalculatorTool(val title: String, val subject: String, val formula: String, val fields: List<String>, val calculate: (List<Double>) -> CalculationResult)
private val calculatorTools = listOf(
    CalculatorTool("Percentage", "Mathematics", "Percentage = (part ÷ whole) × 100", listOf("Part", "Whole")) { CalculatorEngine.percentage(it[0], it[1]) },
    CalculatorTool("Arithmetic mean", "Mathematics", "Mean = sum of values ÷ number of values", listOf("Values (comma-separated)")) { CalculatorEngine.mean(it) },
    CalculatorTool("Ohm's law: current", "Physics", "I = V ÷ R", listOf("Voltage (V)", "Resistance (Ω)")) { CalculatorEngine.ohmsLaw(it[0], it[1]) },
    CalculatorTool("Speed", "Physics", "Speed = distance ÷ time", listOf("Distance (m)", "Time (s)")) { CalculatorEngine.speed(it[0], it[1]) },
    CalculatorTool("Density", "Physics / Chemistry", "Density = mass ÷ volume", listOf("Mass (g)", "Volume (cm³)")) { CalculatorEngine.density(it[0], it[1]) },
    CalculatorTool("Molar concentration", "Chemistry", "C = n ÷ V", listOf("Amount (mol)", "Volume (L)")) { CalculatorEngine.molarity(it[0], it[1]) },
    CalculatorTool("Dilution", "Chemistry", "C₁V₁ = C₂V₂", listOf("Initial concentration (C₁)", "Initial volume (V₁)", "Final concentration (C₂)")) { CalculatorEngine.dilution(it[0], it[1], it[2]) }
)
@Composable
fun CalculatorScreen() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val selected = calculatorTools[selectedIndex]
    var inputs by remember(selectedIndex) { mutableStateOf(List(selected.fields.size) { "" }) }
    var result by remember(selectedIndex) { mutableStateOf<CalculationResult?>(null) }
    var error by remember(selectedIndex) { mutableStateOf<String?>(null) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Scientific tools", style = MaterialTheme.typography.headlineMedium)
        Text("Choose a calculation. Enter values with the units shown, then check the formula and working.")
        calculatorTools.map { it.subject }.distinct().forEach { subject ->
            Text(subject, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            calculatorTools.forEachIndexed { index, tool ->
                if (tool.subject == subject) FilterChip(selected = selectedIndex == index, onClick = { selectedIndex = index }, label = { Text(tool.title) })
            }
        }
        HorizontalDivider()
        Text(selected.title, style = MaterialTheme.typography.titleLarge)
        Text(selected.formula, style = MaterialTheme.typography.bodyLarge)
        selected.fields.forEachIndexed { index, field ->
            OutlinedTextField(value = inputs[index], onValueChange = { next -> inputs = inputs.toMutableList().also { it[index] = next }; result = null; error = null }, label = { Text(field) }, placeholder = { Text(if (field.startsWith("Values")) "e.g. 12, 15, 18, 20" else "Enter a number") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        }
        Button(onClick = {
            try {
                result = if (selected.title == "Arithmetic mean") CalculatorEngine.mean(inputs[0].split(",").map { it.trim().toDouble() })
                else selected.calculate(inputs.map { it.trim().toDouble() })
                error = null
            } catch (e: Exception) { result = null; error = e.message ?: "Check the values and try again." }
        }, modifier = Modifier.fillMaxWidth()) { Text("Calculate") }
        result?.let { answer ->
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Result", style = MaterialTheme.typography.labelLarge)
                    Text(CalculatorEngine.format(answer.value) + if (answer.unit.isBlank()) "" else " ${answer.unit}", style = MaterialTheme.typography.headlineMedium)
                    Text("Working: ${answer.working}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Text("Use compatible units. These tools support learning and practice; show full working in examinations.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
