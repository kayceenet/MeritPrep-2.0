package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.PhysicsPracticalExtension

@Composable
fun PhysicsElasticityLab() {
    var springConstant by remember { mutableFloatStateOf(200f) }
    var extension by remember { mutableFloatStateOf(0.05f) }
    val result = PhysicsPracticalExtension.spring(springConstant.toDouble(), extension.toDouble())
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Elasticity and Hooke's law", style = MaterialTheme.typography.titleLarge)
            Text("Change the spring constant and extension. The model assumes the spring remains within its limit of proportionality.")
            Text("Spring constant: ${"%.0f".format(springConstant)} N/m")
            Slider(value = springConstant, onValueChange = { springConstant = it }, valueRange = 50f..500f)
            Text("Extension: ${"%.3f".format(extension)} m")
            Slider(value = extension, onValueChange = { extension = it }, valueRange = 0f..0.1f)
            HorizontalDivider()
            Text("Restoring-force magnitude: ${"%.2f".format(result.restoringForceMagnitudeN)} N", style = MaterialTheme.typography.titleMedium)
            Text("Elastic potential energy: ${"%.3f".format(result.elasticPotentialEnergyJ)} J")
            ScientificText("F = kx     E = ½kx²", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }
    }
    Text("This is an ideal linear-spring model. Real materials may become non-linear or permanently deformed beyond their proportional or elastic limits.")
}

@Composable
fun PhysicsPressureLab() {
    var force by remember { mutableFloatStateOf(100f) }
    var area by remember { mutableFloatStateOf(0.02f) }
    val pressure = PhysicsPracticalExtension.pressure(force.toDouble(), area.toDouble())
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Pressure investigation", style = MaterialTheme.typography.titleLarge)
            Text("Explore how the same force produces different pressure when the contact area changes.")
            Text("Normal force: ${"%.0f".format(force)} N")
            Slider(value = force, onValueChange = { force = it }, valueRange = 0f..1000f)
            Text("Contact area: ${"%.3f".format(area)} m²")
            Slider(value = area, onValueChange = { area = it }, valueRange = 0.001f..0.1f)
            HorizontalDivider()
            Text("Pressure: ${"%.0f".format(pressure)} Pa", style = MaterialTheme.typography.titleLarge)
            ScientificText("p = F/A", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }
    }
    Text("The model uses the force component perpendicular to the surface and assumes uniform pressure over the stated contact area.")
}

@Composable
fun PhysicsFluidsLab() {
    var mass by remember { mutableFloatStateOf(2f) }
    var objectVolume by remember { mutableFloatStateOf(0.001f) }
    var fluidDensity by remember { mutableFloatStateOf(1000f) }
    var displacedVolume by remember { mutableFloatStateOf(0.0005f) }
    var depth by remember { mutableFloatStateOf(2f) }
    val result = PhysicsPracticalExtension.fluid(
        mass.toDouble(), objectVolume.toDouble(), fluidDensity.toDouble(),
        displacedVolume.toDouble(), depth.toDouble()
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Density, liquid pressure and upthrust", style = MaterialTheme.typography.titleLarge)
            Text("Object mass: ${"%.2f".format(mass)} kg")
            Slider(value = mass, onValueChange = { mass = it }, valueRange = 0.1f..5f)
            Text("Object volume: ${"%.4f".format(objectVolume)} m³")
            Slider(value = objectVolume, onValueChange = { objectVolume = it }, valueRange = 0.001f..0.01f)
            Text("Fluid density: ${"%.0f".format(fluidDensity)} kg/m³")
            Slider(value = fluidDensity, onValueChange = { fluidDensity = it }, valueRange = 500f..1500f)
            Text("Displaced fluid volume: ${"%.4f".format(displacedVolume)} m³")
            Slider(value = displacedVolume, onValueChange = { displacedVolume = it }, valueRange = 0f..0.005f)
            Text("Depth: ${"%.1f".format(depth)} m")
            Slider(value = depth, onValueChange = { depth = it }, valueRange = 0f..10f)
            HorizontalDivider()
            Text("Object density: ${"%.1f".format(result.densityKgPerM3)} kg/m³")
            Text("Liquid gauge pressure: ${"%.0f".format(result.gaugePressurePa)} Pa")
            Text("Upthrust: ${"%.3f".format(result.upthrustN)} N", style = MaterialTheme.typography.titleMedium)
            ScientificText("ρ = m/V     p = ρgh     U = ρgV(displaced)", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        }
    }
    Text("The displaced volume is an independent input. For a fully submerged rigid object it equals the object's volume; for a floating object it is the submerged volume. Gauge pressure excludes atmospheric pressure.")
}

@Composable
fun PhysicsHeatLab() {
    var mass by remember { mutableFloatStateOf(0.5f) }
    var specificHeat by remember { mutableFloatStateOf(4200f) }
    var temperatureChange by remember { mutableFloatStateOf(10f) }
    var latentHeat by remember { mutableFloatStateOf(334000f) }
    val sensible = PhysicsPracticalExtension.heat(mass.toDouble(), specificHeat.toDouble(), temperatureChange.toDouble())
    val latent = PhysicsPracticalExtension.latentHeat(mass.toDouble(), latentHeat.toDouble())
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Calorimetry and change of state", style = MaterialTheme.typography.titleLarge)
            Text("Mass: ${"%.2f".format(mass)} kg")
            Slider(value = mass, onValueChange = { mass = it }, valueRange = 0.1f..2f)
            Text("Specific heat capacity: ${"%.0f".format(specificHeat)} J kg⁻¹ K⁻¹")
            Slider(value = specificHeat, onValueChange = { specificHeat = it }, valueRange = 500f..5000f)
            Text("Temperature change: ${"%.1f".format(temperatureChange)} K")
            Slider(value = temperatureChange, onValueChange = { temperatureChange = it }, valueRange = -20f..100f)
            HorizontalDivider()
            Text("Sensible heat transfer: ${"%.0f".format(sensible)} J", style = MaterialTheme.typography.titleMedium)
            ScientificText("Q = mcΔT", style = MaterialTheme.typography.titleMedium)
            Text("Specific latent heat (fusion example): ${"%.0f".format(latentHeat)} J/kg")
            Slider(value = latentHeat, onValueChange = { latentHeat = it }, valueRange = 100000f..500000f)
            Text("Energy for phase change: ${"%.0f".format(latent)} J", style = MaterialTheme.typography.titleMedium)
            ScientificText("Q = mL", style = MaterialTheme.typography.titleMedium)
        }
    }
    Text("The sensible-heat equation assumes no phase change and approximately constant specific heat capacity. The latent-heat calculation assumes the substance is already at its phase-change temperature.")
}

@Composable
fun PhysicsGasLawsLab() {
    var boyleMode by remember { mutableStateOf(true) }
    var pressure by remember { mutableFloatStateOf(100000f) }
    var initialVolume by remember { mutableFloatStateOf(0.004f) }
    var finalVolume by remember { mutableFloatStateOf(0.002f) }
    var initialTemperature by remember { mutableFloatStateOf(300f) }
    var finalTemperature by remember { mutableFloatStateOf(450f) }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = boyleMode, onClick = { boyleMode = true }, label = { Text("Boyle's law") })
            FilterChip(selected = !boyleMode, onClick = { boyleMode = false }, label = { Text("Charles's law") })
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                Text(if (boyleMode) "Boyle's law: constant temperature" else "Charles's law: constant pressure", style = MaterialTheme.typography.titleLarge)
                Text("Initial volume: ${"%.4f".format(initialVolume)} m³")
                Slider(value = initialVolume, onValueChange = { initialVolume = it }, valueRange = 0.001f..0.01f)
                if (boyleMode) {
                    Text("Initial pressure: ${"%.0f".format(pressure)} Pa")
                    Slider(value = pressure, onValueChange = { pressure = it }, valueRange = 50000f..300000f)
                    Text("Final volume: ${"%.4f".format(finalVolume)} m³")
                    Slider(value = finalVolume, onValueChange = { finalVolume = it }, valueRange = 0.001f..0.01f)
                    val result = PhysicsPracticalExtension.boylesLawFinalPressure(pressure.toDouble(), initialVolume.toDouble(), finalVolume.toDouble())
                    HorizontalDivider()
                    Text("Final pressure: ${"%.0f".format(result.finalPressurePa)} Pa", style = MaterialTheme.typography.titleLarge)
                    ScientificText("p₁V₁ = p₂V₂", style = MaterialTheme.typography.titleMedium)
                } else {
                    Text("Initial temperature: ${"%.0f".format(initialTemperature)} K")
                    Slider(value = initialTemperature, onValueChange = { initialTemperature = it }, valueRange = 200f..500f)
                    Text("Final temperature: ${"%.0f".format(finalTemperature)} K")
                    Slider(value = finalTemperature, onValueChange = { finalTemperature = it }, valueRange = 200f..500f)
                    val result = PhysicsPracticalExtension.charlesLawFinalVolume(initialVolume.toDouble(), initialTemperature.toDouble(), finalTemperature.toDouble())
                    HorizontalDivider()
                    Text("Final volume: ${"%.4f".format(result.finalVolumeM3)} m³", style = MaterialTheme.typography.titleLarge)
                    Text("V₁/T₁ = V₂/T₂")
                }
            }
        }
        Text(if (boyleMode) "Boyle's law assumes a fixed amount of gas at constant temperature. The model treats the gas as ideal." else "Charles's law assumes a fixed amount of gas at constant pressure. Temperatures are absolute temperatures in kelvin; never substitute Celsius values.")
    }
}
