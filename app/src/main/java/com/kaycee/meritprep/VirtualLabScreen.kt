package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun VirtualLabScreen() {
    var selectedLab by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
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

@Composable
private fun BiologyLab() {
    var selected by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Biology practicals", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = selected == 0, onClick = { selected = 0 }, label = { Text("Food tests") })
            FilterChip(selected = selected == 1, onClick = { selected = 1 }, label = { Text("Osmosis") })
            FilterChip(selected = selected == 2, onClick = { selected = 2 }, label = { Text("Microscope") })
        }
        when (selected) {
            0 -> FoodTestLab()
            1 -> OsmosisLab()
            else -> MagnificationLab()
        }
    }
}

@Composable
private fun FoodTestLab() {
    var sample by remember { mutableStateOf(FoodTestSample.STARCH) }
    var test by remember { mutableStateOf(FoodTestType.IODINE) }
    var tested by remember { mutableStateOf(false) }
    val result = BiologyFoodTestEngine.evaluate(sample, test)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Food-test investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose a known sample and a reagent. Predict the result before running the simulated test.")
            Text("Sample", fontWeight = FontWeight.SemiBold)
            FoodTestSample.values().forEach { option ->
                FilterChip(selected = sample == option, onClick = { sample = option; tested = false }, label = { Text(option.label) })
            }
            Text("Test", fontWeight = FontWeight.SemiBold)
            FoodTestType.values().forEach { option ->
                FilterChip(selected = test == option, onClick = { test = option; tested = false }, label = { Text(option.label) })
            }
            Button(onClick = { tested = true }, modifier = Modifier.fillMaxWidth()) { Text("Run simulated test") }
            if (tested) {
                HorizontalDivider()
                Text("Expected procedure", fontWeight = FontWeight.SemiBold)
                Text(result.procedure)
                Text("Simulated observation", fontWeight = FontWeight.SemiBold)
                Text(result.observation, color = if (result.positive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                Text("Inference: ${result.inference}", fontWeight = FontWeight.SemiBold)
            }
        }
    }
    Text("Model limitation: these are qualitative teaching outcomes for defined sample compositions. Real samples may contain mixtures, and observed colour intensity depends on concentration, reagent condition and technique. This simulation does not replace a supervised laboratory practical.")
}

@Composable
private fun OsmosisLab() {
    var externalConcentration by remember { mutableFloatStateOf(0.1f) }
    var plantCell by remember { mutableStateOf(true) }
    val cellType = if (plantCell) OsmosisCellType.PLANT else OsmosisCellType.ANIMAL
    val result = OsmosisSimulation.evaluate(externalConcentration.toDouble(), 0.3, cellType)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Osmosis investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Model water movement across a selectively permeable cell membrane. The cell's internal solute concentration is fixed at 0.30 mol/L for this demonstration.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = plantCell, onClick = { plantCell = true }, label = { Text("Plant cell") })
                FilterChip(selected = !plantCell, onClick = { plantCell = false }, label = { Text("Animal cell") })
            }
            Text("External solute concentration: ${"%.2f".format(externalConcentration)} mol/L")
            Slider(value = externalConcentration, onValueChange = { externalConcentration = it }, valueRange = 0f..0.6f, steps = 11)
            HorizontalDivider()
            Text(result.tonicity, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Net water movement: " + when (result.waterMovement) {
                WaterMovement.INTO_CELL -> "into the cell"
                WaterMovement.NO_NET_MOVEMENT -> "no net movement"
                WaterMovement.OUT_OF_CELL -> "out of the cell"
            })
            Text("Predicted cell response: ${result.response}")
            Text(result.explanation)
        }
    }
    Text("Model limitation: the internal concentration is fixed at 0.30 mol/L to demonstrate the direction of water movement. Real cell sap and cytoplasm have complex, changing water potentials; the isotonic point varies by cell and experimental conditions.")
}
