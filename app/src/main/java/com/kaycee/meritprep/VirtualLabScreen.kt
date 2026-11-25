package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import com.kaycee.meritprep.data.ChemistryPracticalEngine
import com.kaycee.meritprep.data.PhysicsPracticalEngine
import com.kaycee.meritprep.data.PhysicsWaveOpticsEngine
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONArray
import org.json.JSONObject

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
        val safetyGuidance = when (selectedLab) {
            0 -> "These are ideal mathematical models, not physical apparatus. The Ohm's law model is not a real circuit; never connect this app to mains electricity. Physical experiments with electricity, moving masses, sound or optics require suitable equipment, a risk assessment and trained supervision."
            1 -> "This is a concentration calculator; it does not prepare a real solution. In a laboratory, wear eye protection, use clean labelled glassware, never taste chemicals and follow the teacher's instructions."
            else -> "These are simulated outcomes. In real food tests, wear goggles, avoid reagent contact and ingestion, keep ethanol away from flames, and heat Benedict's solution in a water bath under supervision. Handle slides and coverslips carefully; do not touch unknown plants or fungi, enter unsafe field sites or taste specimens. Wash hands after practical work."
        }
        Card(
            Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text("Safety and model limits", fontWeight = FontWeight.Bold)
                Text(safetyGuidance, style = MaterialTheme.typography.bodySmall)
            }
        }
        when (selectedLab) {
            0 -> PhysicsLab()
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
private fun PhysicsLab() {
    var selected by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Physics practical models", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Ohm's law", "Motion", "Vectors", "Moments", "Machines", "Elasticity", "Pressure", "Fluids", "Heat", "Gas laws", "Waves", "Echoes", "Reflection", "Resistors", "Capacitors").forEachIndexed { index, label ->
                FilterChip(selected = selected == index, onClick = { selected = index }, label = { Text(label) })
            }
        }
        when (selected) {
            0 -> OhmsLawLab()
            1 -> UniformMotionLab()
            2 -> VectorAdditionLab()
            3 -> MomentsLab()
            4 -> SimpleMachineLab()
            5 -> PhysicsElasticityLab()
            6 -> PhysicsPressureLab()
            7 -> PhysicsFluidsLab()
            8 -> PhysicsHeatLab()
            9 -> PhysicsGasLawsLab()
            10 -> WavePropertiesLab()
            11 -> EchoDistanceLab()
            12 -> ReflectionLab()
            13 -> PhysicsResistorNetworkLab()
            else -> PhysicsCapacitorNetworkLab()
        }
    }
}

@Composable
private fun UniformMotionLab() {
    var initialVelocity by remember { mutableFloatStateOf(2f) }
    var acceleration by remember { mutableFloatStateOf(1.5f) }
    var time by remember { mutableFloatStateOf(4f) }
    val motion = PhysicsPracticalEngine.uniformMotion(initialVelocity.toDouble(), acceleration.toDouble(), time.toDouble())
    val finalVelocity = motion.finalVelocityMps
    val displacement = motion.displacementM
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Uniformly accelerated motion", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change the initial velocity, constant acceleration and elapsed time.")
            Text("Initial velocity: ${"%.1f".format(initialVelocity)} m/s")
            Slider(value = initialVelocity, onValueChange = { initialVelocity = it }, valueRange = 0f..20f)
            Text("Acceleration: ${"%.1f".format(acceleration)} m/s²")
            Slider(value = acceleration, onValueChange = { acceleration = it }, valueRange = -3f..5f)
            Text("Time: ${"%.1f".format(time)} s")
            Slider(value = time, onValueChange = { time = it }, valueRange = 0f..10f)
            HorizontalDivider()
            Text("Final velocity: ${"%.2f".format(finalVelocity)} m/s", style = MaterialTheme.typography.titleMedium)
            Text("Displacement: ${"%.2f".format(displacement)} m")
            Text("v = u + at     s = ut + ½at²", fontWeight = FontWeight.SemiBold)
        }
    }
    Text("Model assumptions: straight-line motion and constant acceleration. A negative acceleration is defined relative to the selected positive direction; it does not always mean the object is slowing down.")
}

@Composable
private fun VectorAdditionLab() {
    var east by remember { mutableFloatStateOf(3f) }
    var north by remember { mutableFloatStateOf(4f) }
    val vector = PhysicsPracticalEngine.perpendicularVector(east.toDouble(), north.toDouble())
    val magnitude = vector.magnitude
    val angle = vector.angleDegreesFromPositiveX
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Perpendicular vector addition", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Model a journey or two perpendicular vector components. Positive values point east and north.")
            Text("East component: ${"%.1f".format(east)} units")
            Slider(value = east, onValueChange = { east = it }, valueRange = 0f..10f)
            Text("North component: ${"%.1f".format(north)} units")
            Slider(value = north, onValueChange = { north = it }, valueRange = 0f..10f)
            HorizontalDivider()
            Text("Resultant magnitude: ${"%.2f".format(magnitude)} units", style = MaterialTheme.typography.titleMedium)
            Text("Direction: ${"%.1f".format(angle)}° north of east")
            Text("R = √(E² + N²)", fontWeight = FontWeight.SemiBold)
        }
    }
    Text("This model adds two perpendicular components only. It does not model non-perpendicular vectors or measurement uncertainty.")
}

@Composable
private fun MomentsLab() {
    var effort by remember { mutableFloatStateOf(10f) }
    var effortArm by remember { mutableFloatStateOf(0.4f) }
    var load by remember { mutableFloatStateOf(8f) }
    var loadArm by remember { mutableFloatStateOf(0.5f) }
    val clockwise = PhysicsPracticalEngine.moment(effort.toDouble(), effortArm.toDouble()).toFloat()
    val anticlockwise = PhysicsPracticalEngine.moment(load.toDouble(), loadArm.toDouble()).toFloat()
    val netMoment = clockwise - anticlockwise
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Principle of moments", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Adjust the forces and their perpendicular distances from a pivot.")
            Text("Effort force: ${"%.1f".format(effort)} N")
            Slider(value = effort, onValueChange = { effort = it }, valueRange = 0f..20f)
            Text("Effort arm: ${"%.2f".format(effortArm)} m")
            Slider(value = effortArm, onValueChange = { effortArm = it }, valueRange = 0.05f..1f)
            Text("Load force: ${"%.1f".format(load)} N")
            Slider(value = load, onValueChange = { load = it }, valueRange = 0f..20f)
            Text("Load arm: ${"%.2f".format(loadArm)} m")
            Slider(value = loadArm, onValueChange = { loadArm = it }, valueRange = 0.05f..1f)
            HorizontalDivider()
            Text("Effort moment: ${"%.2f".format(clockwise)} N m")
            Text("Load moment: ${"%.2f".format(anticlockwise)} N m")
            Text("Net moment (clockwise positive): ${"%.2f".format(netMoment)} N m", style = MaterialTheme.typography.titleMedium)
            Text(if (kotlin.math.abs(netMoment) < 0.05f) "Approximately balanced in this ideal model." else if (netMoment > 0f) "Clockwise moment is greater." else "Anticlockwise moment is greater.")
        }
    }
    Text("Model assumptions: rigid, weightless lever and forces acting perpendicular to the lever. Real setups may include the lever's own weight and friction at the pivot.")
}

@Composable
private fun SimpleMachineLab() {
    var load by remember { mutableFloatStateOf(300f) }
    var effort by remember { mutableFloatStateOf(100f) }
    var velocityRatio by remember { mutableFloatStateOf(4f) }
    val machine = PhysicsPracticalEngine.machinePerformance(load.toDouble(), effort.toDouble(), velocityRatio.toDouble())
    val mechanicalAdvantage = machine.mechanicalAdvantage.toFloat()
    val efficiency = machine.efficiencyPercent.toFloat()
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Simple machine efficiency", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change the load, effort and velocity ratio to see how machine efficiency is calculated.")
            Text("Load: ${load.roundToInt()} N")
            Slider(value = load, onValueChange = { load = it }, valueRange = 50f..500f)
            Text("Effort: ${effort.roundToInt()} N")
            Slider(value = effort, onValueChange = { effort = it }, valueRange = 10f..250f)
            Text("Velocity ratio: ${"%.1f".format(velocityRatio)}")
            Slider(value = velocityRatio, onValueChange = { velocityRatio = it }, valueRange = 1f..8f)
            HorizontalDivider()
            Text("Mechanical advantage: ${"%.2f".format(mechanicalAdvantage)}")
            Text("Efficiency: ${"%.1f".format(efficiency)}%", style = MaterialTheme.typography.titleMedium)
            Text("MA = Load/Effort     Efficiency = (MA/VR) × 100%", fontWeight = FontWeight.SemiBold)
            if (efficiency > 100f) Text("This input combination gives efficiency above 100%. It is not physically possible for a passive machine; check the assumed load, effort and velocity ratio.", color = MaterialTheme.colorScheme.error)
        }
    }
    Text("The sliders represent idealised measurements. A real machine's efficiency is at most 100%; friction and deformation reduce useful output work.")
}

@Composable
private fun WavePropertiesLab() {
    var frequency by remember { mutableFloatStateOf(5f) }
    var wavelength by remember { mutableFloatStateOf(2f) }
    val wave = PhysicsWaveOpticsEngine.waveProperties(frequency.toDouble(), wavelength.toDouble())
    val speed = wave.speedMetresPerSecond.toFloat()
    val period = wave.periodSeconds.toFloat()
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Wave speed and period", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Adjust frequency and wavelength to see how wave speed changes.")
            Text("Frequency: ${"%.1f".format(frequency)} Hz")
            Slider(value = frequency, onValueChange = { frequency = it }, valueRange = 1f..20f)
            Text("Wavelength: ${"%.2f".format(wavelength)} m")
            Slider(value = wavelength, onValueChange = { wavelength = it }, valueRange = 0.1f..5f)
            HorizontalDivider()
            Text("Wave speed: ${"%.2f".format(speed)} m/s", style = MaterialTheme.typography.titleMedium)
            Text("Period: ${"%.3f".format(period)} s")
            Text("v = fλ     T = 1/f", fontWeight = FontWeight.SemiBold)
        }
    }
    Text("The model assumes a uniform medium and a constant wave speed for the selected frequency and wavelength. Changing medium may change wave speed.")
}

@Composable
private fun EchoDistanceLab() {
    var soundSpeed by remember { mutableFloatStateOf(340f) }
    var echoTime by remember { mutableFloatStateOf(2f) }
    val distance = PhysicsWaveOpticsEngine.echoDistance(soundSpeed.toDouble(), echoTime.toDouble()).toFloat()
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Echo sounding", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("An echo time includes the journey to the reflecting surface and the return journey.")
            Text("Sound speed: ${"%.0f".format(soundSpeed)} m/s")
            Slider(value = soundSpeed, onValueChange = { soundSpeed = it }, valueRange = 300f..360f)
            Text("Round-trip echo time: ${"%.2f".format(echoTime)} s")
            Slider(value = echoTime, onValueChange = { echoTime = it }, valueRange = 0.1f..4f)
            HorizontalDivider()
            Text("Distance to reflector: ${"%.2f".format(distance)} m", style = MaterialTheme.typography.titleMedium)
            Text("d = vt/2", fontWeight = FontWeight.SemiBold)
        }
    }
    Text("The calculation assumes a stationary reflector, a known uniform sound speed and a correctly measured round-trip time.")
}

@Composable
private fun ReflectionLab() {
    var incidentAngle by remember { mutableFloatStateOf(35f) }
    val reflectedAngle = PhysicsWaveOpticsEngine.reflectedAngleDegrees(incidentAngle.toDouble()).toFloat()
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Law of reflection", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Angles are measured from the normal, not from the mirror surface.")
            Text("Angle of incidence: ${"%.0f".format(incidentAngle)}°")
            Slider(value = incidentAngle, onValueChange = { incidentAngle = it }, valueRange = 0f..90f, steps = 89)
            HorizontalDivider()
            Text("Angle of reflection: ${"%.0f".format(reflectedAngle)}°", style = MaterialTheme.typography.titleMedium)
            Text("i = r", fontWeight = FontWeight.SemiBold)
            Text("The incident ray, reflected ray and normal lie in the same plane.")
        }
    }
    Text("This is an ideal plane-mirror model. Real surfaces may scatter light if they are rough compared with the wavelength.")
}

@Composable
private fun ConcentrationLab() {
    var selected by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Chemistry practicals", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = selected == 0, onClick = { selected = 0 }, label = { Text("Concentration") })
            FilterChip(selected = selected == 1, onClick = { selected = 1 }, label = { Text("Acid-base titration") })
            FilterChip(selected = selected == 2, onClick = { selected = 2 }, label = { Text("Cation tests") })
        }
        when (selected) {
            0 -> SolutionConcentrationSimulation()
            1 -> AcidBaseTitrationSimulation()
            else -> CationIdentificationSimulation()
        }
    }
}

@Composable
private fun SolutionConcentrationSimulation() {
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
            Text("C = (mass ÷ molar mass) ÷ final solution volume; NaCl molar mass ≈ 58.44 g/mol.")
        }
    }
    Text("This simplified model assumes complete dissolution and the stated final solution volume. It does not model temperature or non-ideal solution behaviour.")
}

@Composable
private fun AcidBaseTitrationSimulation() {
    var acidConcentration by remember { mutableStateOf("0.100") }
    var baseAliquot by remember { mutableStateOf("25.00") }
    val readings = remember { mutableStateListOf("0.00", "24.80", "0.10", "24.85", "0.00", "25.40") }
    var result by remember { mutableStateOf<com.kaycee.meritprep.data.TitrationResult?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val labels = listOf("Trial 1 initial reading (cm³)", "Trial 1 final reading (cm³)", "Trial 2 initial reading (cm³)", "Trial 2 final reading (cm³)", "Trial 3 initial reading (cm³)", "Trial 3 final reading (cm³)")
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("HCl–NaOH titration", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Enter the initial and final burette readings for up to three trials. The model selects the largest set of titres concordant within 0.20 cm³.")
            Text("HCl + NaOH → NaCl + H₂O", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(value = acidConcentration, onValueChange = { acidConcentration = it; result = null }, label = { Text("HCl concentration (mol/dm³)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = baseAliquot, onValueChange = { baseAliquot = it; result = null }, label = { Text("NaOH aliquot (cm³)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            labels.forEachIndexed { index, label ->
                OutlinedTextField(
                    value = readings[index],
                    onValueChange = { readings[index] = it; result = null },
                    label = { Text(label) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            Button(onClick = {
                try {
                    val parsed = readings.map { it.trim().toDouble() }
                    val meanTitre = ChemistryPracticalEngine.meanConcordantTitre(
                        listOf(parsed[0], parsed[2], parsed[4]),
                        listOf(parsed[1], parsed[3], parsed[5])
                    )
                    result = ChemistryPracticalEngine.calculateOneToOneTitration(
                        acidConcentration.trim().toDouble(),
                        meanTitre,
                        baseAliquot.trim().toDouble()
                    )
                    error = null
                } catch (e: Exception) {
                    result = null
                    error = e.message ?: "Check the entered readings."
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Find concordant mean and calculate") }
            result?.let {
                HorizontalDivider()
                Text("Mean concordant titre: ${"%.3f".format(it.titreCm3)} cm³")
                Text("NaOH concentration: ${"%.4f".format(it.analyteConcentrationMolPerDm3)} mol/dm³", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Working: ${it.calculation}")
            }
            error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
    Text("The calculation assumes a 1:1 reaction and a 0.20 cm³ concordance tolerance. Real practical work requires careful meniscus readings, suitable indicator selection, correct rinsing and endpoint technique. The model does not simulate colour change or certify practical skill.")
}

@Composable
private fun CationIdentificationSimulation() {
    val ions = listOf("NH₄⁺", "Ca²⁺", "Pb²⁺", "Cu²⁺", "Fe²⁺", "Fe³⁺", "Al³⁺", "Zn²⁺")
    var ion by remember { mutableStateOf("Cu²⁺") }
    var reagent by remember { mutableStateOf("NaOH(aq)") }
    var excess by remember { mutableStateOf(false) }
    var warmed by remember { mutableStateOf(false) }
    var observation by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Qualitative cation analysis", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Select a cation, reagent and test condition. Predict how a known ion behaves in the simplified reference scheme.")
            Text("Cation", fontWeight = FontWeight.SemiBold)
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ions.chunked(4).forEach { group ->
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        group.forEach { item ->
                            FilterChip(selected = ion == item, onClick = { ion = item; observation = null; error = null }, label = { Text(item) })
                        }
                    }
                }
            }
            Text("Reagent", fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("NaOH(aq)", "NH₃(aq)").forEach { item ->
                    FilterChip(selected = reagent == item, onClick = { reagent = item; observation = null; error = null }, label = { Text(item) })
                }
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = excess, onCheckedChange = { excess = it; observation = null })
                Text("Reagent added in excess")
            }
            if (ion == "NH₄⁺") {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Checkbox(checked = warmed, onCheckedChange = { warmed = it; observation = null })
                    Text("Sample warmed gently")
                }
            }
            Button(onClick = {
                try {
                    observation = ChemistryPracticalEngine.cationObservation(ion, reagent, excess, warmed)
                    error = null
                } catch (e: Exception) {
                    observation = null
                    error = e.message ?: "This test combination is not supported."
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Show expected observation") }
            observation?.let {
                HorizontalDivider()
                Text("Expected observation", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(it)
                Text("Inference: use this result alongside appropriate confirmatory tests. Do not identify an unknown ion from one observation alone.", style = MaterialTheme.typography.bodySmall)
            }
            error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
    Text("This is a simplified reference for selected cations and two reagents. It is not a complete qualitative-analysis scheme. Real results depend on concentration, interfering ions and technique. Practical work involving chemicals must be conducted under qualified supervision.")
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
            FilterChip(selected = selected == 5, onClick = { selected = 5 }, label = { Text("Biological drawing") })
            FilterChip(selected = selected == 6, onClick = { selected = 6 }, label = { Text("Plan an experiment") })
        }
        when (selected) {
            0 -> FoodTestLab()
            1 -> OsmosisLab()
            2 -> MagnificationLab()
            3 -> QuadratSamplingLab()
            4 -> BiologyClassificationLab()
            5 -> BiologyDrawingLab()
            else -> BiologyExperimentPlannerLab()
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
            Text("Field procedure", fontWeight = FontWeight.SemiBold)
            Text("1. Mark out the habitat area and, where practical, divide it into a grid.")
            Text("2. Select sample positions randomly so that you do not choose only places where the organism is easy to see.")
            Text("3. Place the same quadrat at each selected position and count the target organisms. A common boundary rule is to include organisms touching the top and right edges but exclude those touching the bottom and left edges, preventing double-counting between adjacent quadrats.")
            Text("4. Repeat at enough positions to represent the habitat. Record the quadrat size, locations and conditions.")
            Text("5. Add the counts, divide by the total sampled area to obtain density, then multiply by habitat area to estimate population.")
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
                Checkbox(checked = fungus, onCheckedChange = { selected ->
                    fungus = selected
                    if (selected) {
                        photosynthetic = false
                        vascular = false
                        seeds = false
                        seedsEnclosed = false
                        oneCotyledon = false
                    }
                    resetResult()
                })
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

 
@Composable
private fun BiologyDrawingLab() {
    var checkedCriteria by remember { mutableStateOf(emptySet<String>()) }
    val total = BiologyDrawingGuide.criteria.size
    val completed = checkedCriteria.size.coerceAtMost(total)

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Biological drawing self-check", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Use these criteria while making a biological drawing on paper. Tick each item only after checking your own drawing.")
            LinearProgressIndicator(
                progress = { if (total == 0) 0f else completed.toFloat() / total },
                modifier = Modifier.fillMaxWidth()
            )
            Text("$completed of $total drawing criteria checked", style = MaterialTheme.typography.labelLarge)
            BiologyDrawingGuide.criteria.forEach { criterion ->
                Row(verticalAlignment = androidx.compose.ui.Alignment.Top) {
                    Checkbox(
                        checked = criterion.id in checkedCriteria,
                        onCheckedChange = { checked ->
                            checkedCriteria = if (checked) checkedCriteria + criterion.id else checkedCriteria - criterion.id
                        }
                    )
                    Column(Modifier.weight(1f).padding(top = 10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(criterion.title, fontWeight = FontWeight.SemiBold)
                        Text(criterion.rationale, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            if (completed == total) {
                Text("Self-check complete. Recheck that every label points to the correct visible structure.", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            }
            OutlinedButton(onClick = { checkedCriteria = emptySet() }) { Text("Reset checklist") }
        }
    }
    Text(
        "These are learning prompts, not an official WAEC marking scheme. Always follow the instructions and specimen requirements given in the actual examination.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

 
@Composable
private fun BiologyExperimentPlannerLab() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val noteId = "biology-experiment-planner"
    val restored = remember {
        runCatching { JSONObject(database.getNoteBody(noteId) ?: "{}") }.getOrElse { JSONObject() }
    }
    var investigation by remember { mutableStateOf(restored.optString("investigation", "How does light intensity affect the rate of photosynthesis?")) }
    var independent by remember { mutableStateOf(restored.optString("independent", "Light intensity (or distance from the lamp)")) }
    var dependent by remember { mutableStateOf(restored.optString("dependent", "Number of oxygen bubbles released per minute")) }
    var controls by remember { mutableStateOf(restored.optString("controls", "Plant species and size, carbon dioxide concentration, temperature, and counting time")) }
    var method by remember { mutableStateOf(restored.optString("method", "Allow the plant to equilibrate, change one light level at a time, count bubbles for the same time interval, repeat each level and calculate a mean.")) }
    var conclusion by remember { mutableStateOf(restored.optString("conclusion", "")) }
    var evaluation by remember { mutableStateOf(restored.optString("evaluation", "")) }
    var graphReview by remember { mutableStateOf(false) }
    var showReview by remember { mutableStateOf(false) }
    var saved by remember { mutableStateOf(false) }
    val resultRows = remember {
        mutableStateListOf<Pair<String, String>>().apply {
            val rows = restored.optJSONArray("results")
            if (rows != null && rows.length() > 0) {
                for (index in 0 until rows.length()) {
                    val row = rows.optJSONObject(index) ?: continue
                    add(row.optString("value") to row.optString("result"))
                }
            } else {
                add("10 cm" to "")
                add("20 cm" to "")
                add("30 cm" to "")
            }
        }
    }

    fun savePlan() {
        val rows = JSONArray()
        resultRows.forEach { (value, result) ->
            rows.put(JSONObject().put("value", value).put("result", result))
        }
        val payload = JSONObject()
            .put("investigation", investigation)
            .put("independent", independent)
            .put("dependent", dependent)
            .put("controls", controls)
            .put("method", method)
            .put("conclusion", conclusion)
            .put("evaluation", evaluation)
            .put("results", rows)
        database.saveNote(noteId, "bio-experiment-planning", "Biology experiment plan", payload.toString())
        saved = true
    }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Plan a biological investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Build a fair test, record observations, interpret a graph and evaluate the quality of your evidence. Your work is saved on this device.")
            OutlinedTextField(value = investigation, onValueChange = { investigation = it; saved = false; showReview = false }, label = { Text("Research question / aim") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(value = independent, onValueChange = { independent = it; saved = false; showReview = false }, label = { Text("Independent variable - what you change") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(value = dependent, onValueChange = { dependent = it; saved = false; showReview = false }, label = { Text("Dependent variable - what you measure") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(value = controls, onValueChange = { controls = it; saved = false; showReview = false }, label = { Text("Control variables - what you keep constant") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(value = method, onValueChange = { method = it; saved = false; showReview = false }, label = { Text("Method, repeats and recording") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            Button(onClick = { showReview = true }, enabled = investigation.isNotBlank() && independent.isNotBlank() && dependent.isNotBlank() && controls.isNotBlank() && method.isNotBlank(), modifier = Modifier.fillMaxWidth()) {
                Text("Review my investigation plan")
            }
            if (showReview) {
                HorizontalDivider()
                Text("Plan checklist", fontWeight = FontWeight.Bold)
                Text("• State a focused, testable question.")
                Text("• Change one independent variable and measure a clearly defined dependent variable.")
                Text("• Keep relevant control variables constant.")
                Text("• Use a suitable range, consistent method, repeats and appropriate apparatus.")
                Text("• Record measurements with units and calculate means where appropriate.")
                Text("A completed checklist does not automatically prove that the method is scientifically suitable. Consider safety, measurement limits and whether the method actually tests the question.")
            }
        }
    }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Record your results", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Enter the value of the independent variable and the measured outcome. Include units in your headings or values. Use repeated readings where the method requires them.")
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Independent variable", modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
                Text("Measured result", modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
            }
            resultRows.forEachIndexed { index, row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = row.first,
                        onValueChange = { resultRows[index] = it to row.second; saved = false },
                        modifier = Modifier.weight(1f),
                        label = { Text("Value \${index + 1}") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = row.second,
                        onValueChange = { resultRows[index] = row.first to it; saved = false },
                        modifier = Modifier.weight(1f),
                        label = { Text("Result \${index + 1}") },
                        singleLine = true
                    )
                }
            }
            OutlinedButton(
                onClick = { if (resultRows.size < 8) { resultRows.add("" to ""); saved = false } },
                enabled = resultRows.size < 8,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Add results row") }
            Text("Graph skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("For a continuous independent variable, plot it on the horizontal (x) axis and the measured dependent variable on the vertical (y) axis. Label both axes with units, choose even scales, plot points accurately and draw a suitable line or curve of best fit. A bar chart is generally used for categories.")
            OutlinedTextField(
                value = if (graphReview) "Check: Are the axes correctly assigned and labelled? Are units shown? Does the pattern support a relationship? Are there anomalous points, and should they be investigated rather than silently removed?" else "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Graph interpretation guide") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            OutlinedButton(onClick = { graphReview = !graphReview }, modifier = Modifier.fillMaxWidth()) {
                Text(if (graphReview) "Hide graph guide" else "Show graph interpretation checklist")
            }
            OutlinedTextField(
                value = conclusion,
                onValueChange = { conclusion = it; saved = false },
                label = { Text("Conclusion - what do the results show?") },
                supportingText = { Text("Answer the research question and refer to the actual pattern or numerical evidence. Avoid claiming more than the data supports.") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            OutlinedTextField(
                value = evaluation,
                onValueChange = { evaluation = it; saved = false },
                label = { Text("Evaluation - limitations, errors and improvements") },
                supportingText = { Text("Identify specific sources of error, explain their likely effect, then suggest a practical improvement. Distinguish random variation from systematic error.") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Button(onClick = { savePlan() }, modifier = Modifier.fillMaxWidth()) {
                Text("Save experiment plan and results")
            }
            if (saved) {
                Text("Saved on this device. Your entries will be restored when you reopen this practical.", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            }
        }
    }
    Text("Teaching example: the photosynthesis investigation is a model for planning and recording. Bubble counts are an indirect estimate of oxygen production and are less precise than measuring gas volume. In a real investigation, use an appropriate supervised method and record its limitations.")
}
