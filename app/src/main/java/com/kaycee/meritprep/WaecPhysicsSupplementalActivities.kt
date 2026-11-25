package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.*

internal object WaecPhysicsActivityRoutes {
    val supportedTopicIds = setOf(
        "phy-measurements", "phy-motion", "phy-gravity", "phy-elasticity",
        "phy-heat-transfer", "phy-refraction", "phy-light-energy", "phy-magnetism",
        "phy-electronics", "phy-kinetic-theory", "phy-modern"
    )
}

@Composable
fun WaecPhysicsSupplementalActivity(topicId: String) {
    if (topicId !in WaecPhysicsActivityRoutes.supportedTopicIds) return
    when (topicId) {
        "phy-measurements" -> WaecDimensionalAnalysisActivity()
        "phy-motion" -> WaecProjectileActivity()
        "phy-gravity" -> WaecSatelliteActivity()
        "phy-elasticity" -> WaecYoungModulusActivity()
        "phy-heat-transfer" -> WaecBlackbodyActivity()
        "phy-refraction" -> WaecFibreOpticsActivity()
        "phy-light-energy" -> WaecLaserActivity()
        "phy-magnetism" -> WaecMagneticMaterialsActivity()
        "phy-electronics" -> WaecRectificationActivity()
        "phy-kinetic-theory" -> WaecMatterStructureActivity()
        "phy-modern" -> WaecPhotoelectricActivity()
    }
}

@Composable
private fun WaecDimensionalAnalysisActivity() {
    var choice by remember { mutableStateOf("Speed = distance / time") }
    val detail = when (choice) {
        "Speed = distance / time" -> "Dimension: [L][T]⁻¹. SI unit: m s⁻¹."
        "Force = mass × acceleration" -> "Dimension: [M][L][T]⁻². SI unit: kg m s⁻² = N."
        "Pressure = force / area" -> "Dimension: [M][L]⁻¹[T]⁻². SI unit: N m⁻² = Pa."
        else -> "Dimension: [M][L]²[T]⁻². SI unit: kg m² s⁻² = J."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: dimensions and derived units", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Speed = distance / time", "Force = mass × acceleration", "Pressure = force / area", "Work = force × distance").forEach {
                FilterChip(choice == it, { choice = it }, { Text(it) })
            }
            Text(detail)
            Text("Dimensional homogeneity is necessary for a physically meaningful equation, but it does not prove that an equation is correct.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecProjectileActivity() {
    var speed by remember { mutableFloatStateOf(25f) }
    var angle by remember { mutableFloatStateOf(45f) }
    val radians = Math.toRadians(angle.toDouble())
    val range = speed * speed * sin(2.0 * radians) / 9.81
    val time = 2.0 * speed * sin(radians) / 9.81
    val height = speed * speed * sin(radians).pow(2.0) / (2.0 * 9.81)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: projectile motion", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Launch speed: " + "%.1f".format(speed) + " m s⁻¹")
            Slider(speed, { speed = it }, valueRange = 5f..60f)
            Text("Launch angle: " + "%.0f".format(angle) + "°")
            Slider(angle, { angle = it }, valueRange = 5f..85f)
            HorizontalDivider()
            Text("Horizontal range: " + "%.2f".format(range) + " m")
            Text("Time of flight: " + "%.2f".format(time) + " s")
            Text("Maximum height: " + "%.2f".format(height) + " m", style = MaterialTheme.typography.headlineSmall)
            Text("Ideal model: no air resistance, constant g = 9.81 m s⁻², and landing at the same height as launch. The range is greatest at 45° only under these assumptions.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecSatelliteActivity() {
    var altitude by remember { mutableFloatStateOf(400f) }
    val gConst = 6.67430e-11
    val earthMass = 5.972e24
    val earthRadius = 6.371e6
    val orbitalRadius = earthRadius + altitude * 1000.0
    val speed = sqrt(gConst * earthMass / orbitalRadius)
    val period = 2.0 * Math.PI * orbitalRadius / speed
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: satellites and orbital motion", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Altitude above Earth's surface: " + "%.0f".format(altitude) + " km")
            Slider(altitude, { altitude = it }, valueRange = 200f..36000f)
            Text("Orbital speed: " + "%.0f".format(speed) + " m s⁻¹")
            Text("Orbital period: " + "%.1f".format(period / 60.0) + " minutes", style = MaterialTheme.typography.headlineSmall)
            Text("Uses a circular orbit and a spherical Earth model. Atmospheric drag, Earth rotation, non-spherical gravity and orbital perturbations are ignored. A geostationary orbit is near 35,786 km altitude with a period of one sidereal day.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecYoungModulusActivity() {
    var force by remember { mutableFloatStateOf(10f) }
    var length by remember { mutableFloatStateOf(1f) }
    val area = 1.0e-6
    val young = 2.0e11
    val extension = force * length / (area * young)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: Young's modulus", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Applied force: " + "%.1f".format(force) + " N")
            Slider(force, { force = it }, valueRange = 1f..50f)
            Text("Original wire length: " + "%.2f".format(length) + " m")
            Slider(length, { length = it }, valueRange = 0.5f..2f)
            Text("Assumed cross-sectional area: 1.0 × 10⁻⁶ m²; assumed Young's modulus: 2.0 × 10¹¹ Pa.")
            Text("Extension: " + "%.4f".format(extension) + " m", style = MaterialTheme.typography.headlineSmall)
            Text("Uses ΔL = FL/(AY) for a uniform wire within its elastic limit. The constants are illustrative steel-like values; real material properties and dimensions must be measured.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecBlackbodyActivity() {
    var temperature by remember { mutableFloatStateOf(300f) }
    val ratio = (temperature / 300.0).pow(4.0)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: thermal radiation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Blackbody temperature: " + "%.0f".format(temperature) + " K")
            Slider(temperature, { temperature = it }, valueRange = 200f..1200f)
            Text("Radiated power per unit area relative to 300 K: " + "%.2f".format(ratio), style = MaterialTheme.typography.headlineSmall)
            Text("Stefan–Boltzmann model: emitted power per unit area is proportional to T⁴ for an ideal blackbody. Real surfaces have emissivity below one; spectral distribution also changes with temperature.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecFibreOpticsActivity() {
    var core by remember { mutableFloatStateOf(1.50f) }
    var cladding by remember { mutableFloatStateOf(1.40f) }
    val critical = if (core > cladding) Math.toDegrees(asin((cladding.toDouble() / core.toDouble()).coerceIn(0.0, 1.0))) else Double.NaN
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: fibre optics and total internal reflection", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Core refractive index: " + "%.2f".format(core))
            Slider(core, { core = it }, valueRange = 1.40f..1.80f)
            Text("Cladding refractive index: " + "%.2f".format(cladding))
            Slider(cladding, { cladding = it }, valueRange = 1.30f..1.60f)
            if (critical.isNaN()) Text("Total internal reflection is not possible: the core index must exceed the cladding index.", color = MaterialTheme.colorScheme.error)
            else Text("Critical angle at the core–cladding boundary: " + "%.1f".format(critical) + "°", style = MaterialTheme.typography.headlineSmall)
            Text("Light is guided when it travels in the higher-index core and strikes the boundary above the critical angle. Real fibre losses include absorption, scattering and bending losses.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecLaserActivity() {
    var type by remember { mutableStateOf("Semiconductor") }
    val detail = when (type) {
        "Solid state" -> "An active solid medium, often a doped crystal or glass, is optically pumped. Applications include material processing and some medical procedures."
        "Gas" -> "A gas discharge excites the active medium. Examples include helium–neon and carbon dioxide lasers, used in alignment, measurement and material processing."
        "Liquid" -> "A dye solution provides a tunable active medium. Wavelength can be varied over a range."
        else -> "A semiconductor junction provides the active medium. Compact laser diodes are used in optical communication, barcode readers and data storage."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: laser types and safety", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Solid state", "Gas", "Liquid", "Semiconductor").forEach {
                FilterChip(type == it, { type = it }, { Text(it) })
            }
            Text(detail)
            Text("Laser light is highly directional and may be intense. Never look into a beam or its specular reflection. Use wavelength-appropriate protective controls and follow the device's laser class and safety procedure.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
private fun WaecMagneticMaterialsActivity() {
    var material by remember { mutableStateOf("Soft iron") }
    val detail = if (material == "Soft iron") {
        "Soft iron is readily magnetised and demagnetised. It is useful for temporary electromagnet cores and transformer cores where low coercivity is desirable."
    } else {
        "Steel generally retains magnetisation more strongly. It is useful for permanent magnets because it has higher coercivity than soft iron."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: magnetic materials", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Soft iron", "Steel").forEach {
                FilterChip(material == it, { material = it }, { Text(it) })
            }
            Text(detail)
            Text("Compare ease of magnetisation, retentivity and coercivity. The exact properties depend on alloy composition and heat treatment.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecRectificationActivity() {
    var mode by remember { mutableStateOf("Half-wave") }
    val pulses = if (mode == "Half-wave") 1 else 2
    val explanation = if (mode == "Half-wave") {
        "A single diode passes one half-cycle and blocks the other. Output is pulsating DC with ripple frequency equal to the supply frequency."
    } else {
        "A bridge or centre-tapped full-wave circuit uses both half-cycles, reversing one half so the load current keeps the same direction. Ripple frequency is twice the supply frequency."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: diode rectification", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Half-wave", "Full-wave").forEach {
                FilterChip(mode == it, { mode = it }, { Text(it) })
            }
            Text(explanation)
            Text("For a 50 Hz AC supply, ideal ripple frequency: " + (50 * pulses) + " Hz.", style = MaterialTheme.typography.headlineSmall)
            Text("A practical power supply usually adds a smoothing capacitor and voltage regulation. This concept model does not calculate ripple amplitude or diode losses.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecMatterStructureActivity() {
    var state by remember { mutableStateOf("Crystalline solid") }
    val detail = if (state == "Crystalline solid") {
        "Particles are arranged in a regular repeating lattice. The ordered structure gives characteristic symmetry and often a definite melting point."
    } else {
        "Particles lack long-range periodic order. Amorphous solids soften over a range of temperatures rather than having one sharp melting point."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: crystalline and amorphous matter", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Crystalline solid", "Amorphous solid").forEach {
                FilterChip(state == it, { state = it }, { Text(it) })
            }
            Text(detail)
            Text("Examples: sodium chloride and quartz are crystalline; ordinary glass is amorphous. Brownian motion and diffusion provide evidence for particle motion.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecPhotoelectricActivity() {
    var frequency by remember { mutableFloatStateOf(8f) }
    var workFunction by remember { mutableFloatStateOf(3f) }
    val planck = 4.136e-15
    val photonEnergy = planck * frequency * 1e14
    val emissionOccurs = photonEnergy >= workFunction
    val maximumKinetic = if (emissionOccurs) photonEnergy - workFunction else 0.0
    val threshold = workFunction / (planck * 1e14)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC harmonised focus: photoelectric effect", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Frequency: " + "%.1f".format(frequency) + " × 10¹⁴ Hz")
            Slider(frequency, { frequency = it }, valueRange = 1f..15f)
            Text("Work function: " + "%.1f".format(workFunction) + " eV")
            Slider(workFunction, { workFunction = it }, valueRange = 1f..6f)
            Text("Threshold frequency: " + "%.2f".format(threshold) + " × 10¹⁴ Hz")
            if (emissionOccurs) Text("Maximum photoelectron kinetic energy: " + "%.2f".format(maximumKinetic) + " eV", style = MaterialTheme.typography.headlineSmall)
            else Text("No photoemission: each photon has less energy than the work function.", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
            Text("Einstein equation: Kmax = hf − φ. Emission occurs only when photon energy reaches or exceeds the work function. Intensity changes the number of emitted electrons, not the maximum kinetic energy at fixed frequency.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
