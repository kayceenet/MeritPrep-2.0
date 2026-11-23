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
            else -> BiologyLab()
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

private enum class FoodSample(val label: String, val starch: Boolean, val reducingSugar: Boolean, val protein: Boolean, val lipid: Boolean) {
    STARCH("Starch solution", true, false, false, false),
    GLUCOSE("Glucose solution", false, true, false, false),
    ALBUMEN("Egg albumen", false, false, true, false),
    OIL("Vegetable oil", false, false, false, true),
    MILK("Milk", false, true, true, true)
}

private enum class FoodReagent(val label: String) {
    IODINE("Iodine test"),
    BENEDICT("Benedict's test"),
    BIURET("Biuret test"),
    EMULSION("Ethanol emulsion test")
}

@Composable
private fun BiologyLab() {
    var selected by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Biology practicals", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = selected == 0, onClick = { selected = 0 }, label = { Text("Food tests") })
            FilterChip(selected = selected == 1, onClick = { selected = 1 }, label = { Text("Microscope") })
        }
        if (selected == 0) FoodTestLab() else MagnificationLab()
    }
}

@Composable
private fun FoodTestLab() {
    var sample by remember { mutableStateOf(FoodSample.STARCH) }
    var reagent by remember { mutableStateOf(FoodReagent.IODINE) }
    var tested by remember { mutableStateOf(false) }
    val positive = when (reagent) {
        FoodReagent.IODINE -> sample.starch
        FoodReagent.BENEDICT -> sample.reducingSugar
        FoodReagent.BIURET -> sample.protein
        FoodReagent.EMULSION -> sample.lipid
    }
    val observation = when (reagent) {
        FoodReagent.IODINE -> if (positive) "The iodine changes from yellow-brown to blue-black." else "The iodine remains yellow-brown; no blue-black colour develops."
        FoodReagent.BENEDICT -> if (positive) "On heating in a hot-water bath, the blue reagent changes through green, yellow or orange towards brick-red, depending on the amount of reducing sugar." else "After heating in a hot-water bath, the solution remains blue."
        FoodReagent.BIURET -> if (positive) "The blue Biuret reagent develops a lilac or purple colour." else "The mixture remains blue; no lilac or purple colour develops."
        FoodReagent.EMULSION -> if (positive) "After adding water to the ethanolic extract, a cloudy white emulsion forms." else "The mixture remains clear; no milky-white emulsion forms."
    }
    val procedure = when (reagent) {
        FoodReagent.IODINE -> "Place a small amount of sample in a test tube or on a spotting tile. Add a few drops of iodine solution and observe the colour."
        FoodReagent.BENEDICT -> "Add Benedict's reagent to the sample, then heat the mixture in a hot-water bath for about 2–5 minutes. Do not heat the test tube directly in a flame."
        FoodReagent.BIURET -> "Add Biuret reagent to the sample and mix gently. Observe for a lilac or purple colour."
        FoodReagent.EMULSION -> "Shake the sample with ethanol, then pour the ethanolic extract into water. A milky emulsion indicates lipid. Ethanol is flammable: keep it away from flames and use teacher supervision."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Food-test investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose a known sample and a reagent. Predict the result before running the simulated test.")
            Text("Sample", fontWeight = FontWeight.SemiBold)
            FoodSample.values().forEach { option ->
                FilterChip(selected = sample == option, onClick = { sample = option; tested = false }, label = { Text(option.label) })
            }
            Text("Test", fontWeight = FontWeight.SemiBold)
            FoodReagent.values().forEach { option ->
                FilterChip(selected = reagent == option, onClick = { reagent = option; tested = false }, label = { Text(option.label) })
            }
            Button(onClick = { tested = true }, modifier = Modifier.fillMaxWidth()) { Text("Run simulated test") }
            if (tested) {
                HorizontalDivider()
                Text("Expected procedure", fontWeight = FontWeight.SemiBold)
                Text(procedure)
                Text("Simulated observation", fontWeight = FontWeight.SemiBold)
                Text(observation, color = if (positive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                Text(if (positive) "Inference: the selected sample gives a positive result for the tested food substance." else "Inference: the selected sample gives a negative result for the tested food substance.", fontWeight = FontWeight.SemiBold)
            }
        }
    }
    Text("Model limitation: these are qualitative teaching outcomes for defined sample compositions. Real samples may contain mixtures, and observed colour intensity depends on concentration, reagent condition and technique. This simulation does not replace a supervised laboratory practical.")
}
