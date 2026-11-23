package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
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
        Row(
            Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(selected = selected == 0, onClick = { selected = 0 }, label = { Text("Food tests") })
            FilterChip(selected = selected == 1, onClick = { selected = 1 }, label = { Text("Osmosis") })
            FilterChip(selected = selected == 2, onClick = { selected = 2 }, label = { Text("Microscope") })
            FilterChip(selected = selected == 3, onClick = { selected = 3 }, label = { Text("Quadrat sampling") })
            FilterChip(selected = selected == 4, onClick = { selected = 4 }, label = { Text("Classification key") })
        }
        when (selected) {
            0 -> FoodTestLab()
            1 -> OsmosisLab()
            2 -> MagnificationLab()
            3 -> QuadratSamplingLab()
            else -> BiologyClassificationLab()
        }
    }
}

@Composable
private fun FoodTestLab() {
    var sample by remember { mutableStateOf(FoodTestSample.STARCH) }
    var test by remember { mutableStateOf(FoodTestType.IODINE) }
    var tested by remember { mutableStateOf(false) }
    var prediction by remember { mutableStateOf<Boolean?>(null) }
    val result = BiologyFoodTestEngine.evaluate(sample, test)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Food-test investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose a known sample and a reagent. Predict the result before running the simulated test.")
            Text("Sample", fontWeight = FontWeight.SemiBold)
            FoodTestSample.values().forEach { option ->
                FilterChip(selected = sample == option, onClick = { sample = option; tested = false; prediction = null }, label = { Text(option.label) })
            }
            Text("Test", fontWeight = FontWeight.SemiBold)
            FoodTestType.values().forEach { option ->
                FilterChip(selected = test == option, onClick = { test = option; tested = false; prediction = null }, label = { Text(option.label) })
            }
            Text("Your prediction", fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = prediction == true,
                    onClick = { prediction = true; tested = false },
                    label = { Text("Positive") }
                )
                FilterChip(
                    selected = prediction == false,
                    onClick = { prediction = false; tested = false },
                    label = { Text("Negative") }
                )
            }
            Button(
                onClick = { tested = true },
                enabled = prediction != null,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Run simulated test") }
            if (tested) {
                HorizontalDivider()
                Text(
                    if (prediction == result.positive) "Your prediction was correct." else "Your prediction was not correct. Compare it with the observation and explanation.",
                    color = if (prediction == result.positive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold
                )
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
private fun QuadratSamplingLab() {
    val counts = remember { mutableStateListOf(8, 12, 10, 6) }
    var quadratArea by remember { mutableFloatStateOf(1f) }
    var habitatArea by remember { mutableFloatStateOf(1_000f) }
    val estimate = EcologySamplingEngine.estimate(
        individualsPerQuadrat = counts.toList(),
        quadratAreaSquareMetres = quadratArea.toDouble(),
        habitatAreaSquareMetres = habitatArea.toDouble()
    )

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Quadrat sampling investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Record the number of plants or slow-moving organisms found in each sample quadrat. The model uses four sample locations.")
            counts.forEachIndexed { index, count ->
                Text("Quadrat ${index + 1}: $count organisms", fontWeight = FontWeight.SemiBold)
                Slider(
                    value = count.toFloat(),
                    onValueChange = { counts[index] = it.roundToInt() },
                    valueRange = 0f..30f,
                    steps = 29
                )
            }
            HorizontalDivider()
            Text("Area of each quadrat: ${"%.2f".format(quadratArea)} m²")
            Slider(value = quadratArea, onValueChange = { quadratArea = it }, valueRange = 0.25f..4f)
            Text("Habitat area: ${habitatArea.roundToInt()} m²")
            Slider(value = habitatArea, onValueChange = { habitatArea = it }, valueRange = 100f..10_000f, steps = 98)
            HorizontalDivider()
            Text("Sampling results", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Total organisms counted: ${estimate.totalIndividuals}")
            Text("Total sampled area: ${"%.2f".format(estimate.sampledAreaSquareMetres)} m²")
            Text("Estimated density: ${"%.2f".format(estimate.meanDensityPerSquareMetre)} organisms/m²")
            Text(
                "Estimated habitat population: ${estimate.estimatedHabitatPopulation.roundToInt()}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    Text(
        "Method: density = total organisms counted ÷ total area sampled. Estimated population = mean density × habitat area. The estimate assumes the quadrats are placed randomly and represent the habitat. Patchy distributions, seasonal changes and poor sampling design can make estimates unreliable.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
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


@Composable
private fun BiologyClassificationLab() {
    var photosynthetic by remember { mutableStateOf(true) }
    var vascular by remember { mutableStateOf(true) }
    var seeds by remember { mutableStateOf(true) }
    var seedsEnclosed by remember { mutableStateOf(true) }
    var oneCotyledon by remember { mutableStateOf(true) }
    var fungus by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<ClassificationResult?>(null) }

    fun resetResult() { result = null }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Dichotomous classification key", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Imagine that you have collected an unfamiliar specimen. Record the observable features, then follow the key. Change a feature to explore a different branch.")
            Text("Observed features", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = photosynthetic, onCheckedChange = { photosynthetic = it; resetResult() })
                Text("Contains chlorophyll and photosynthesises")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = vascular, onCheckedChange = { vascular = it; resetResult() })
                Text("Has vascular tissue (xylem and phloem)")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = seeds, onCheckedChange = { seeds = it; resetResult() })
                Text("Produces seeds")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = seedsEnclosed, onCheckedChange = { seedsEnclosed = it; resetResult() })
                Text("Seeds are enclosed in a fruit")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = oneCotyledon, onCheckedChange = { oneCotyledon = it; resetResult() })
                Text("Seed has one cotyledon (one seed leaf)")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = fungus, onCheckedChange = { fungus = it; resetResult() })
                Text("Specimen has fungal features")
            }
            Button(
                onClick = {
                    result = BiologyClassificationEngine.identify(
                        SpecimenFeatures(photosynthetic, vascular, seeds, seedsEnclosed, oneCotyledon, fungus)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Follow the key and identify") }
            result?.let { answer ->
                HorizontalDivider()
                Text(answer.identification ?: "Identification not supported by this key", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = if (answer.identification == null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
                Text(answer.explanation)
                Text("Decision path", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                answer.keySteps.forEachIndexed { index, step -> Text("${index + 1}. $step") }
            }
            OutlinedButton(onClick = {
                photosynthetic = true
                vascular = true
                seeds = true
                seedsEnclosed = true
                oneCotyledon = true
                fungus = false
                result = null
            }) { Text("Reset specimen features") }
        }
    }
    Text(
        "Teaching model limitation: this key distinguishes six selected examples, not every plant or fungus. Real specimen identification uses several additional features and an appropriate regional key. A single feature should not be used to classify all organisms.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
