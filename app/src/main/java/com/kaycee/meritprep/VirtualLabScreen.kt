package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun VirtualLabScreen() {
    var selectedLab by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Virtual laboratory", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Explore scientific relationships by changing experimental variables.")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Physics", "Chemistry", "Biology").forEachIndexed { index, label ->
                FilterChip(selected = selectedLab == index, onClick = { selectedLab = index }, label = { Text(label) })
            }
        }
        when (selectedLab) {
            0 -> OhmsLawLab()
            1 -> ConcentrationLab()
            else -> MagnificationLab()
        }
    }
}

@Composable
private fun OhmsLawLab() {
    var voltage by remember { mutableFloatStateOf(12f) }
    var resistance by remember { mutableFloatStateOf(100f) }
    val currentMilliAmps = voltage / resistance * 1000f
    val powerMilliWatts = voltage * voltage / resistance * 1000f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Ohm's law experiment", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Adjust the supply voltage and resistance. Observe how current changes.")
            Text("Voltage: ${voltage.roundToInt()} V")
            Slider(value = voltage, onValueChange = { voltage = it }, valueRange = 1f..24f, steps = 22)
            Text("Resistance: ${resistance.roundToInt()} Ω")
            Slider(value = resistance, onValueChange = { resistance = it }, valueRange = 10f..1000f, steps = 98)
            HorizontalDivider()
            Text("Calculated current: ${"%.2f".format(currentMilliAmps)} mA", style = MaterialTheme.typography.titleMedium)
            Text("Calculated power: ${"%.2f".format(powerMilliWatts)} mW")
            Text("V = IR   |   I = V/R   |   P = V²/R", fontWeight = FontWeight.SemiBold)
        }
    }
    Text("Model assumptions: ideal DC source and fixed ohmic resistance. This is a mathematical simulation, not a real electrical circuit.")
}

@Composable
private fun ConcentrationLab() {
    var soluteMass by remember { mutableFloatStateOf(5f) }
    var volume by remember { mutableFloatStateOf(0.5f) }
    val molarity = (soluteMass / 58.44f) / volume
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Solution concentration", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Model dissolving sodium chloride (NaCl) in water.")
            Text("NaCl mass: ${"%.1f".format(soluteMass)} g")
            Slider(value = soluteMass, onValueChange = { soluteMass = it }, valueRange = 0.5f..20f)
            Text("Final solution volume: ${"%.2f".format(volume)} L")
            Slider(value = volume, onValueChange = { volume = it }, valueRange = 0.1f..2f)
            HorizontalDivider()
            Text("Molar concentration: ${"%.3f".format(molarity)} mol/L", style = MaterialTheme.typography.titleMedium)
            Text("M = (mass ÷ molar mass) ÷ volume; NaCl molar mass ≈ 58.44 g/mol.")
        }
    }
    Text("This simplified calculator assumes the stated final solution volume and complete dissolution. It does not model temperature or non-ideal solution behaviour.")
}

@Composable
private fun MagnificationLab() {
    var eyepiece by remember { mutableIntStateOf(10) }
    var objective by remember { mutableIntStateOf(40) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Compound microscope", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Eyepiece lens: ${eyepiece}×")
            Slider(value = eyepiece.toFloat(), onValueChange = { eyepiece = it.roundToInt() }, valueRange = 5f..20f, steps = 14)
            Text("Objective lens: ${objective}×")
            Slider(value = objective.toFloat(), onValueChange = { objective = it.roundToInt() }, valueRange = 4f..100f, steps = 95)
            HorizontalDivider()
            Text("Total magnification: ${eyepiece * objective}×", style = MaterialTheme.typography.titleMedium)
            Text("Total magnification = eyepiece magnification × objective magnification.")
        }
    }
    Text("The model calculates nominal magnification. Actual image quality also depends on resolution, illumination, alignment and specimen preparation.")
}
