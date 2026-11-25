package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Canvas
import androidx.compose.animation.core.*
import androidx.compose.ui.geometry.Offset
import kotlin.math.sin
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.*

@Composable
fun ChemistryInteractiveLessonActivity(topicId: String) {
    when (topicId) {
        "chem-acids-bases", "chem-redox", "chem-combination" -> ChemistryTitrationInquiry()
        "chem-metals", "chem-nonmetals", "chem-separation" -> ChemistryQualitativeInquiry()
        "chem-rates", "chem-equilibrium", "chem-energy" -> ChemistryRateInquiry()
        "chem-electrolysis" -> ElectrolysisConceptInquiry()
    }
}

@Composable
private fun ChemistryTitrationInquiry() {
    var concentration by remember { mutableFloatStateOf(0.10f) }
    var titre by remember { mutableFloatStateOf(24.8f) }
    var aliquot by remember { mutableFloatStateOf(25f) }
    val result = remember(concentration, titre, aliquot) {
        ChemistryPracticalEngine.calculateOneToOneTitration(concentration.toDouble(), titre.toDouble(), aliquot.toDouble())
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Virtual titration calculation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Model a 1:1 acid–base reaction. Change the standard solution concentration, mean titre and pipetted aliquot. The simulation recalculates the unknown concentration.")
            Text("Titrant concentration: " + "%.3f".format(concentration) + " mol dm⁻³")
            Slider(concentration, { concentration = it }, valueRange = 0.02f..0.5f)
            Text("Mean titre: " + "%.2f".format(titre) + " cm³")
            Slider(titre, { titre = it }, valueRange = 5f..50f)
            Text("Analyte aliquot: " + "%.1f".format(aliquot) + " cm³")
            Slider(aliquot, { aliquot = it }, valueRange = 10f..50f)
            HorizontalDivider()
            Text("Calculated analyte concentration", style = MaterialTheme.typography.labelLarge)
            Text("%.4f mol dm⁻³".format(result.analyteConcentrationMolPerDm3), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Text(result.calculation)
            Text("Think: if the titre doubles while all other values remain constant, what happens to the calculated concentration?", fontWeight = FontWeight.SemiBold)
            Text("Model scope: this activity assumes a 1:1 balanced equation and an accurate endpoint. It is not a real burette or a substitute for learning indicator choice and safe laboratory technique.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun ChemistryQualitativeInquiry() {
    var ion by remember { mutableStateOf("Cu²⁺") }
    var reagent by remember { mutableStateOf("NaOH(aq)") }
    var excess by remember { mutableStateOf(false) }
    var warmed by remember { mutableStateOf(false) }
    val ions = listOf("NH₄⁺", "Ca²⁺", "Pb²⁺", "Cu²⁺", "Fe²⁺", "Fe³⁺", "Al³⁺", "Zn²⁺")
    val observation = remember(ion, reagent, excess, warmed) {
        runCatching { ChemistryPracticalEngine.cationObservation(ion, reagent, excess, warmed) }.getOrElse { it.message.orEmpty() }
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Qualitative analysis: identify a cation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose an unknown ion, select a reagent and decide whether it is added in excess. Predict the observation before revealing the inference.")
            Text("Unknown cation")
            ions.chunked(4).forEach { group -> Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) { group.forEach { value -> FilterChip(ion == value, { ion = value; warmed = false }, { Text(value) }) } } }
            Text("Reagent")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(reagent == "NaOH(aq)", { reagent = "NaOH(aq)" }, { Text("NaOH") })
                FilterChip(reagent == "NH₃(aq)", { reagent = "NH₃(aq)" }, { Text("Ammonia") })
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(excess, { excess = !excess }, { Text("Add in excess") })
                if (ion == "NH₄⁺") FilterChip(warmed, { warmed = !warmed }, { Text("Warm gently") })
            }
            HorizontalDivider()
            Text("Predicted observation", fontWeight = FontWeight.Bold)
            Text(observation)
            Text("Record the colour, precipitate formation and solubility before making an inference. A single observation should be confirmed with a suitable independent test.", style = MaterialTheme.typography.bodySmall)
            Text("Safety: this is a paper/model simulation. Do not handle reagents or heat unknown chemicals without a trained supervisor, PPE and approved laboratory procedures.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
private fun ChemistryRateInquiry() {
    var temperature by remember { mutableFloatStateOf(25f) }
    var concentration by remember { mutableFloatStateOf(1f) }
    var surface by remember { mutableFloatStateOf(1f) }
    var activationEnergy by remember { mutableFloatStateOf(40f) }
    val gasConstant = 8.314
    val temperatureKelvin = temperature.toDouble() + 273.15
    val referenceKelvin = 298.15
    val arrheniusFactor = kotlin.math.exp(-(activationEnergy * 1000.0 / gasConstant) * (1.0 / temperatureKelvin - 1.0 / referenceKelvin))
    val relativeRate = arrheniusFactor * concentration * surface
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Investigating factors affecting reaction rate", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one factor at a time and compare the illustrative relative-rate index. Concentration and exposed surface area affect collision frequency; temperature affects particle kinetic energy and the fraction of collisions energetic enough to react.")
            Text("Temperature: " + temperature.toInt() + " °C")
            Slider(temperature, { temperature = it }, valueRange = 5f..65f)
            Text("Relative concentration: " + "%.1f".format(concentration))
            Slider(concentration, { concentration = it }, valueRange = 0.2f..2f)
            Text("Relative exposed surface area: " + "%.1f".format(surface))
            Slider(surface, { surface = it }, valueRange = 0.2f..2f)
            Text("Activation energy: " + activationEnergy.toInt() + " kJ mol⁻¹")
            Slider(activationEnergy, { activationEnergy = it }, valueRange = 20f..80f)
            HorizontalDivider()
            Text("Illustrative relative-rate index: " + "%.2f".format(relativeRate), style = MaterialTheme.typography.headlineSmall)
            Text("Arrhenius temperature dependence is used for a single assumed reaction pathway; concentration and exposed surface are treated as first-order scaling factors. Real reaction orders, mechanisms and surface behaviour must be measured, so this is a controlled model rather than experimental data.", style = MaterialTheme.typography.bodySmall)
            Text("Investigation: reset to the same starting conditions, change only temperature, and describe the trend. Then repeat for concentration and surface area.", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun ElectrolysisConceptInquiry() {
       var time by remember { mutableFloatStateOf(10f) }
    var current by remember { mutableFloatStateOf(0.5f) }
    val charge = current * time * 60f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Electrolysis: charge passed through an electrolyte", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("An external power supply drives a non-spontaneous redox reaction. Current measures charge flow per second: Q = It.")
            Text("Current: " + "%.2f".format(current) + " A")
            Slider(current, { current = it }, valueRange = 0.1f..2f)
            Text("Time: " + time.toInt() + " min")
            Slider(time, { time = it }, valueRange = 1f..30f)
            Text("Charge transferred: " + "%.1f".format(charge) + " C", style = MaterialTheme.typography.headlineSmall)
            Text("Voltage affects the energy supplied and whether the required reaction can proceed; charge passed is calculated from current and time. This model does not predict electrode products or mass without electrolyte, electrode and Faraday-law details.", style = MaterialTheme.typography.bodySmall)
            Text("Question: at constant current, what happens to charge if electrolysis time is tripled?", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun PhysicsInteractiveLessonActivity(topicId: String) {
    when (topicId) {
        "phy-current-electricity", "phy-electrical-power", "phy-cells" -> ResistorCircuitInquiry()
        "phy-capacitors" -> CapacitorInquiry()
        "phy-waves", "phy-sound-propagation", "phy-sound-characteristics", "phy-light-energy" -> WaveInquiry()
        "phy-motion", "phy-gravity" -> MotionInquiry()
        "phy-elasticity" -> HookesLawInquiry()
        "phy-thermal-expansion" -> ThermalExpansionInquiry()
        "phy-equilibrium", "phy-work-energy" -> MomentsInquiry()
        "phy-reflection", "phy-refraction" -> ReflectionInquiry()
    }
}

@Composable
private fun ResistorCircuitInquiry() {
    var first by remember { mutableFloatStateOf(4f) }
    var second by remember { mutableFloatStateOf(6f) }
    var voltage by remember { mutableFloatStateOf(12f) }
    var parallel by remember { mutableStateOf(false) }
    val result = remember(first, second, voltage, parallel) {
        PhysicsCircuitEngine.resistorNetwork(listOf(first.toDouble(), second.toDouble()), voltage.toDouble(), if (parallel) NetworkConnection.PARALLEL else NetworkConnection.SERIES)
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Interactive resistor circuit", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Build a two-resistor network. Compare series and parallel connections, then change resistance and supply voltage.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(!parallel, { parallel = false }, { Text("Series") })
                FilterChip(parallel, { parallel = true }, { Text("Parallel") })
            }
            Text("R₁: " + "%.1f".format(first) + " Ω"); Slider(first, { first = it }, valueRange = 1f..20f)
            Text("R₂: " + "%.1f".format(second) + " Ω"); Slider(second, { second = it }, valueRange = 1f..20f)
            Text("Supply: " + "%.1f".format(voltage) + " V"); Slider(voltage, { voltage = it }, valueRange = 1f..24f)
            HorizontalDivider()
            Text("Equivalent resistance: " + "%.2f".format(result.equivalentResistanceOhms) + " Ω", fontWeight = FontWeight.Bold)
            Text("Total current: " + "%.3f".format(result.totalCurrentAmps) + " A")
            Text("Total power: " + "%.3f".format(result.totalPowerWatts) + " W")
            Text("Check: why is equivalent resistance lower than either resistor in a parallel network?", fontWeight = FontWeight.SemiBold)
            Text("Ideal DC model: wires have zero resistance, source voltage is constant and resistor values do not change with temperature.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun CapacitorInquiry() {
    var first by remember { mutableFloatStateOf(100f) }
    var second by remember { mutableFloatStateOf(100f) }
    var voltage by remember { mutableFloatStateOf(12f) }
    var parallel by remember { mutableStateOf(true) }
    val result = remember(first, second, voltage, parallel) {
        PhysicsCircuitEngine.capacitorNetwork(listOf(first.toDouble() * 1e-6, second.toDouble() * 1e-6), voltage.toDouble(), if (parallel) NetworkConnection.PARALLEL else NetworkConnection.SERIES)
    }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Capacitor combination simulator", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Row { FilterChip(parallel, { parallel = true }, { Text("Parallel") }); Spacer(Modifier.width(8.dp)); FilterChip(!parallel, { parallel = false }, { Text("Series") }) }
        Text("C₁: " + first.toInt() + " μF"); Slider(first, { first = it }, valueRange = 10f..500f)
        Text("C₂: " + second.toInt() + " μF"); Slider(second, { second = it }, valueRange = 10f..500f)
        Text("Voltage: " + voltage.toInt() + " V"); Slider(voltage, { voltage = it }, valueRange = 1f..24f)
        Text("Equivalent capacitance: " + "%.2f".format(result.equivalentCapacitanceFarads * 1e6) + " μF", fontWeight = FontWeight.Bold)
        Text("Stored charge: " + "%.3f".format(result.totalChargeCoulombs * 1e3) + " mC")
        Text("Stored energy: " + "%.4f".format(result.storedEnergyJoules) + " J")
        Text("Safety: real capacitors can retain charge after disconnection. This is a mathematical model, not a physical circuit.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
    } }
}

@Composable
private fun WaveInquiry() {
    var frequency by remember { mutableFloatStateOf(4f) }
    var wavelength by remember { mutableFloatStateOf(2f) }
    val result = remember(frequency, wavelength) { PhysicsWaveOpticsEngine.waveProperties(frequency.toDouble(), wavelength.toDouble()) }
    val transition = rememberInfiniteTransition(label = "wave-motion")
    val phase by transition.animateFloat(initialValue = 0f, targetValue = (2.0 * Math.PI).toFloat(), animationSpec = infiniteRepeatable(tween(1800, easing = LinearEasing)), label = "wave-phase")
    val waveColor = MaterialTheme.colorScheme.primary
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Wave property simulator", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Change frequency and wavelength. The wave equation v = fλ links frequency, wavelength and propagation speed.")
        Text("Frequency: " + "%.1f".format(frequency) + " Hz"); Slider(frequency, { frequency = it }, valueRange = 0.5f..20f)
        Text("Wavelength: " + "%.2f".format(wavelength) + " m"); Slider(wavelength, { wavelength = it }, valueRange = 0.1f..10f)
        Text("Wave speed: " + "%.2f".format(result.speedMetresPerSecond) + " m/s", fontWeight = FontWeight.Bold)
        Text("Period: " + "%.3f".format(result.periodSeconds) + " s")
        Canvas(Modifier.fillMaxWidth().height(120.dp)) {
            val centre = size.height / 2f
            val amplitude = size.height * 0.32f
            val waveLengthPixels = size.width / 2f
            var x = 0f
            while (x < size.width) {
                val nextX = (x + 3f).coerceAtMost(size.width)
                val y1 = centre + sin((2.0 * Math.PI * x / waveLengthPixels - phase).toDouble()).toFloat() * amplitude
                val y2 = centre + sin((2.0 * Math.PI * nextX / waveLengthPixels - phase).toDouble()).toFloat() * amplitude
                drawLine(waveColor, Offset(x, y1), Offset(nextX, y2), strokeWidth = 4f)
                x = nextX
            }
        }
        Text("At fixed wave speed, increasing frequency must reduce wavelength. Here speed is recalculated from your chosen values; this does not represent a wave travelling in a specified medium.", style = MaterialTheme.typography.bodySmall)
    } }
}

@Composable
private fun MotionInquiry() {
    var initial by remember { mutableFloatStateOf(0f) }
    var acceleration by remember { mutableFloatStateOf(2f) }
    var time by remember { mutableFloatStateOf(5f) }
    val result = remember(initial, acceleration, time) { PhysicsPracticalEngine.uniformMotion(initial.toDouble(), acceleration.toDouble(), time.toDouble()) }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Motion investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("For constant acceleration, v = u + at and s = ut + ½at².")
        Text("Initial velocity: " + "%.1f".format(initial) + " m/s"); Slider(initial, { initial = it }, valueRange = -10f..20f)
        Text("Acceleration: " + "%.1f".format(acceleration) + " m/s²"); Slider(acceleration, { acceleration = it }, valueRange = -5f..5f)
        Text("Time: " + "%.1f".format(time) + " s"); Slider(time, { time = it }, valueRange = 0f..20f)
        Text("Final velocity: " + "%.2f".format(result.finalVelocityMps) + " m/s", fontWeight = FontWeight.Bold)
        Text("Displacement: " + "%.2f".format(result.displacementM) + " m")
        Text("Model assumption: acceleration is constant and motion is one-dimensional. Positive and negative signs represent chosen directions.", style = MaterialTheme.typography.bodySmall)
    } }
}

@Composable
private fun MomentsInquiry() {
    var force by remember { mutableFloatStateOf(10f) }
    var arm by remember { mutableFloatStateOf(0.4f) }
    val moment = PhysicsPracticalEngine.moment(force.toDouble(), arm.toDouble())
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Principle of moments investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Moment = force × perpendicular distance from the pivot.")
        Text("Applied force: " + "%.1f".format(force) + " N"); Slider(force, { force = it }, valueRange = 0f..50f)
        Text("Perpendicular arm: " + "%.2f".format(arm) + " m"); Slider(arm, { arm = it }, valueRange = 0f..1.5f)
        Text("Turning moment: " + "%.2f".format(moment) + " N m", fontWeight = FontWeight.Bold)
        Text("Try doubling the perpendicular arm while keeping force constant. What happens to the turning effect?")
        Text("The arm is measured perpendicular to the line of action of the force. A slanted distance is not automatically the moment arm.", style = MaterialTheme.typography.bodySmall)
    } }
}

@Composable
private fun ReflectionInquiry() {
    var angle by remember { mutableFloatStateOf(30f) }
    val reflected = PhysicsWaveOpticsEngine.reflectedAngleDegrees(angle.toDouble())
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Law of reflection explorer", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Angles are measured from the normal, an imaginary line perpendicular to the mirror.")
        Text("Angle of incidence: " + angle.toInt() + "°"); Slider(angle, { angle = it }, valueRange = 0f..90f)
        Text("Angle of reflection: " + reflected.toInt() + "°", fontWeight = FontWeight.Bold)
        Text("The incident ray, reflected ray and normal lie in one plane. The angle of incidence equals the angle of reflection.")
        Text("Do not measure either angle from the mirror surface; doing so gives the complementary angle.", style = MaterialTheme.typography.bodySmall)
    } }
}

@Composable
private fun HookesLawInquiry() {
    var springConstant by remember { mutableFloatStateOf(20f) }
    var extension by remember { mutableFloatStateOf(0.10f) }
    val force = springConstant * extension
    val energy = 0.5f * springConstant * extension * extension
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Hooke's law: stretch a spring", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Within the limit of proportionality, the applied force is proportional to extension: F = kx.")
        Text("Spring constant: " + springConstant.toInt() + " N/m"); Slider(springConstant, { springConstant = it }, valueRange = 5f..100f)
        Text("Extension: " + "%.3f".format(extension) + " m"); Slider(extension, { extension = it }, valueRange = 0.01f..0.30f)
        Text("Required force: " + "%.2f".format(force) + " N", fontWeight = FontWeight.Bold)
        Text("Elastic potential energy: " + "%.3f".format(energy) + " J")
        Text("The model assumes an ideal linear spring and that the extension remains below the proportional limit. Real springs may deform permanently if overloaded.", style = MaterialTheme.typography.bodySmall)
        Text("Investigation: double the extension while keeping k constant. What happens to force and stored energy?")
    } }
}

@Composable
private fun ThermalExpansionInquiry() {
    var alpha by remember { mutableFloatStateOf(12f) }
    var length by remember { mutableFloatStateOf(2f) }
    var temperatureChange by remember { mutableFloatStateOf(50f) }
    val changeMetres = alpha * 1e-6f * length * temperatureChange
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Thermal expansion of a solid rod", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("For a small temperature change and an isotropic solid, ΔL = αL₀ΔT.")
        Text("Linear expansion coefficient: " + alpha.toInt() + " × 10⁻⁶ K⁻¹"); Slider(alpha, { alpha = it }, valueRange = 5f..30f)
        Text("Original length: " + "%.2f".format(length) + " m"); Slider(length, { length = it }, valueRange = 0.5f..10f)
        Text("Temperature rise: " + temperatureChange.toInt() + " K"); Slider(temperatureChange, { temperatureChange = it }, valueRange = 0f..150f)
        Text("Increase in length: " + "%.3f".format(changeMetres * 1000f) + " mm", fontWeight = FontWeight.Bold)
        Text("The coefficient is assumed constant over the temperature interval. Real materials may expand non-linearly and have different coefficients along different directions.", style = MaterialTheme.typography.bodySmall)
        Text("Engineering connection: explain why bridges and long rails need expansion allowances.")
    } }
}
