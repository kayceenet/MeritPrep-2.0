package com.kaycee.meritprep

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.ln

/** Topic-specific visual laboratories for Physics syllabus areas previously shown as generic calculators. */
@Composable
fun PhysicsTopicVirtualLab(topicId: String) {
    when (topicId) {
        "phy-temperature" -> TemperatureScaleLaboratory()
        "phy-heat-quantity" -> SpecificHeatLaboratory()
        "phy-change-state" -> PhaseChangeLaboratory()
        "phy-vapours" -> HumidityLaboratory()
        "phy-optical-instruments" -> ConvergingLensLaboratory()
        "phy-dispersion" -> DispersionLaboratory()
        "phy-magnetism" -> SolenoidFieldLaboratory()
        "phy-conduction" -> ThermalConductionLaboratory()
        "phy-electronics" -> SemiconductorDiodeLaboratory()
    }
}

@Composable
private fun TemperatureScaleLaboratory() {
    var celsius by remember { mutableFloatStateOf(25f) }
    val kelvin = celsius + 273.15f
    val fahrenheit = celsius * 9f / 5f + 32f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Interactive thermometer and temperature scales", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Raise or lower the temperature and watch the thermometer column respond. Compare Celsius, kelvin and Fahrenheit.")
            Canvas(Modifier.fillMaxWidth().height(190.dp)) {
                val cx = size.width * 0.5f
                val top = 18f
                val bottom = size.height - 28f
                val tubeWidth = 22f
                val fraction = ((celsius + 100f) / 300f).coerceIn(0f, 1f)
                drawRoundRect(Color(0xFFCFD8DC), Offset(cx - tubeWidth / 2, top), Size(tubeWidth, bottom - top), cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f))
                drawRoundRect(Color(0xFFE53935), Offset(cx - tubeWidth / 2 + 4f, bottom - (bottom - top - 8f) * fraction), Size(tubeWidth - 8f, (bottom - top - 8f) * fraction), cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f))
                drawCircle(Color(0xFFE53935), 18f, Offset(cx, bottom + 8f))
                for (i in 0..10) {
                    val y = bottom - i * (bottom - top) / 10f
                    drawLine(Color.DarkGray, Offset(cx + 18f, y), Offset(cx + if (i % 2 == 0) 40f else 30f, y), 2f)
                }
            }
            Text("Temperature: ${"%.1f".format(celsius)} °C")
            Slider(celsius, { celsius = it }, valueRange = -100f..200f)
            Text("Kelvin: ${"%.2f".format(kelvin)} K  |  Fahrenheit: ${"%.1f".format(fahrenheit)} °F", fontWeight = FontWeight.SemiBold)
            Text("Absolute zero is 0 K (−273.15 °C). A temperature interval of 1 K is the same size as an interval of 1 °C; the zero points are different.")
            Text("Try −40 °C. Why do Celsius and Fahrenheit show the same numerical value there?", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun SpecificHeatLaboratory() {
    var energy by remember { mutableFloatStateOf(12000f) }
    var mass by remember { mutableFloatStateOf(1f) }
    var materialIndex by remember { mutableIntStateOf(0) }
    val materials = listOf("Water" to 4200f, "Aluminium" to 900f, "Copper" to 385f)
    val material = materials[materialIndex]
    val rise = energy / (mass * material.second)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Calorimetry: compare specific heat capacities", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Apply the same energy to different materials. The temperature rise depends on mass and specific heat capacity.")
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                materials.forEachIndexed { index, item ->
                    FilterChip(materialIndex == index, { materialIndex = index }, { Text(item.first) })
                }
            }
            Text("Energy supplied: ${energy.toInt()} J")
            Slider(energy, { energy = it }, valueRange = 1000f..50000f)
            Text("Mass: ${"%.2f".format(mass)} kg")
            Slider(mass, { mass = it }, valueRange = 0.1f..3f)
            Canvas(Modifier.fillMaxWidth().height(120.dp)) {
                val maxRise = energy / (0.1f * 385f)
                val barWidth = size.width * 0.22f
                materials.forEachIndexed { index, item ->
                    val delta = energy / (mass * item.second)
                    val height = (delta / maxRise).coerceIn(0f, 1f) * (size.height - 22f)
                    val x = size.width * (0.16f + index * 0.34f)
                    drawRect(if (index == materialIndex) Color(0xFFEF6C00) else Color(0xFF78909C), Offset(x, size.height - height), Size(barWidth, height))
                }
            }
            Text("Selected material: ${material.first} (c = ${material.second.toInt()} J kg⁻¹ K⁻¹)")
            Text("Predicted temperature rise: ${"%.2f".format(rise)} K", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Q = mcΔT. The bars compare the three materials for the same supplied energy and mass.")
            Text("Model assumes no heat loss, no phase change and constant specific heat capacity over the temperature interval.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun PhaseChangeLaboratory() {
    var energy by remember { mutableFloatStateOf(500f) }
    val phase = when {
        energy < 334f -> "Melting: solid and liquid coexist"
        energy < 752f -> "Liquid water warming"
        energy < 3009f -> "Boiling: liquid and vapour coexist"
        else -> "Steam warming"
    }
    val transition = rememberInfiniteTransition(label = "phase-particles")
    val time by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2600, easing = LinearEasing), RepeatMode.Restart), label = "phase-motion")
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Heating curve: melting and boiling of water", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Add energy to one kilogram of water initially at 0 °C. Follow the melting plateau, liquid warming and boiling plateau.")
            Canvas(Modifier.fillMaxWidth().height(170.dp)) {
                val solid = energy < 334f
                val gas = energy >= 2594f
                repeat(28) { index ->
                    val col = index % 7
                    val row = index / 7
                    val x: Float
                    val y: Float
                    if (solid) {
                        x = size.width * 0.25f + col * 16f + sin(time * 2f * PI.toFloat() + index) * 2f
                        y = size.height * 0.30f + row * 17f + cos(time * 2f * PI.toFloat() + index) * 2f
                    } else if (gas) {
                        x = ((index * 47f + time * size.width * 0.8f) % (size.width - 24f)) + 12f
                        y = ((index * 31f + time * size.height * 0.5f) % (size.height - 24f)) + 12f
                    } else {
                        x = size.width * 0.18f + col * 22f + sin(time * 2f * PI.toFloat() + index) * 8f
                        y = size.height * 0.48f + row * 13f + cos(time * 2f * PI.toFloat() + index) * 7f
                    }
                    drawCircle(if (index % 2 == 0) Color(0xFF1976D2) else Color(0xFF26A69A), 5f, Offset(x, y))
                }
            }
            Text("Energy supplied per kilogram: ${energy.toInt()} kJ")
            Slider(energy, { energy = it }, valueRange = 0f..3200f)
            Text("Model state: $phase", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Fusion: 334 kJ kg⁻¹  |  Heating water from 0 °C to 100 °C: about 418 kJ kg⁻¹  |  Vaporisation: about 2257 kJ kg⁻¹")
            Text("During a phase change at constant pressure, added energy changes the arrangement and separation of particles while temperature remains approximately constant.")
            Text("This simplified model shows the major latent-heat plateaux but does not calculate the full temperature of each phase.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun HumidityLaboratory() {
    var temperature by remember { mutableFloatStateOf(25f) }
    var relativeHumidity by remember { mutableFloatStateOf(60f) }
    val saturationPressure = 0.6108 * exp(17.27 * temperature / (temperature + 237.3))
    val actualPressure = saturationPressure * relativeHumidity / 100.0
    val gamma = ln((relativeHumidity / 100.0).coerceAtLeast(0.001)) + 17.27 * temperature / (237.3 + temperature)
    val dewPoint = 237.3 * gamma / (17.27 - gamma)
    val transition = rememberInfiniteTransition(label = "water-vapour")
    val phase by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2600, easing = LinearEasing), RepeatMode.Restart), label = "vapour-motion")
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Water vapour and relative humidity", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change air temperature and relative humidity. The chamber illustrates invisible water vapour and the conditions at which condensation can begin.")
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                drawRoundRect(Color(0xFFE3F2FD), Offset(8f, 8f), Size(size.width - 16f, size.height - 16f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(14f))
                val count = (relativeHumidity / 100f * 34f).toInt().coerceIn(2, 34)
                repeat(count) { index ->
                    val x = 20f + ((index * 53f + phase * 45f) % (size.width - 40f))
                    val y = 18f + ((index * 29f + sin(phase * 2f * PI.toFloat() + index) * 8f) % (size.height - 36f))
                    drawCircle(Color(0xFF039BE5).copy(alpha = 0.7f), 3.5f, Offset(x, y))
                }
                if (relativeHumidity > 90f) {
                    repeat(7) { index ->
                        val x = size.width * (0.18f + index * 0.1f)
                        drawCircle(Color(0xFF0288D1), 4f, Offset(x, size.height - 16f))
                    }
                }
            }
            Text("Air temperature: ${temperature.toInt()} °C")
            Slider(temperature, { temperature = it }, valueRange = 0f..40f)
            Text("Relative humidity: ${relativeHumidity.toInt()}%")
            Slider(relativeHumidity, { relativeHumidity = it }, valueRange = 10f..100f)
            Text("Saturation vapour pressure: ${"%.3f".format(saturationPressure)} kPa")
            Text("Actual vapour pressure: ${"%.3f".format(actualPressure)} kPa", fontWeight = FontWeight.SemiBold)
            Text("Approximate dew point: ${"%.1f".format(dewPoint)} °C", style = MaterialTheme.typography.titleMedium)
            Text("Relative humidity = (actual vapour pressure ÷ saturation vapour pressure) × 100%. Cooling air without changing its water-vapour content raises relative humidity; at the dew point, condensation can begin.")
            Text("The chamber is illustrative. Saturation pressure uses the Magnus approximation over ordinary atmospheric temperatures; it is not a full weather model.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun ConvergingLensLaboratory() {
    var focalLength by remember { mutableFloatStateOf(10f) }
    var objectDistance by remember { mutableFloatStateOf(25f) }
    val imageDistance = focalLength * objectDistance / (objectDistance - focalLength)
    val magnification = -imageDistance / objectDistance
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Converging lens: interactive ray diagram", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Move the object and change the focal length. Follow the principal rays to see where the image forms.")
            Text("Focal length: ${"%.1f".format(focalLength)} cm")
            Slider(focalLength, { focalLength = it; if (objectDistance <= it + 1f) objectDistance = it + 1f }, valueRange = 5f..20f)
            Text("Object distance: ${"%.1f".format(objectDistance)} cm")
            Slider(objectDistance, { objectDistance = it.coerceAtLeast(focalLength + 1f) }, valueRange = (focalLength + 1f)..60f)
            Canvas(Modifier.fillMaxWidth().height(210.dp)) {
                val axisY = size.height * 0.56f
                val lensX = size.width * 0.5f
                val scale = size.width / 90f
                val objX = lensX - objectDistance * scale
                val imgX = (lensX + imageDistance * scale).coerceIn(8f, size.width - 8f)
                val objTop = axisY - 48f
                val imgTop = axisY - (48f * magnification).coerceIn(-100f, 100f)
                drawLine(Color.Gray, Offset(4f, axisY), Offset(size.width - 4f, axisY), 2f)
                drawLine(Color(0xFF00897B), Offset(lensX, 8f), Offset(lensX, size.height - 8f), 4f)
                drawLine(Color.DarkGray, Offset(lensX - focalLength * scale, axisY - 8f), Offset(lensX - focalLength * scale, axisY + 8f), 2f)
                drawLine(Color.DarkGray, Offset(lensX + focalLength * scale, axisY - 8f), Offset(lensX + focalLength * scale, axisY + 8f), 2f)
                drawLine(Color(0xFF1565C0), Offset(objX, axisY), Offset(objX, objTop), 4f)
                drawLine(Color(0xFF1565C0), Offset(objX, objTop), Offset(lensX, objTop), 2f)
                drawLine(Color(0xFF1565C0), Offset(lensX, objTop), Offset(imgX, axisY - 48f * magnification), 2f)
                drawLine(Color(0xFFEF6C00), Offset(objX, objTop), Offset(lensX, axisY), 2f)
                drawLine(Color(0xFFEF6C00), Offset(lensX, axisY), Offset(imgX, axisY - 48f * magnification), 2f)
                drawLine(Color(0xFF2E7D32), Offset(imgX, axisY), Offset(imgX, imgTop), 4f)
            }
            Text("Image distance: ${"%.2f".format(imageDistance)} cm  |  Magnification: ${"%.2f".format(magnification)}", fontWeight = FontWeight.SemiBold)
            Text("The image is real and inverted when the object is outside the focal point. Move the object inside the focal length to investigate the virtual-image case in a later extension.")
            Text("Thin-lens model; ray diagram is schematic and not to scale.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun DispersionLaboratory() {
    var wavelength by remember { mutableFloatStateOf(550f) }
    val refractiveIndex = 1.5046f + 0.0042f / ((wavelength / 1000f) * (wavelength / 1000f))
    val colours = listOf(Color(0xFF7B1FA2), Color(0xFF3949AB), Color(0xFF039BE5), Color(0xFF43A047), Color(0xFFFDD835), Color(0xFFFB8C00), Color(0xFFE53935))
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Dispersion: separate white light into colours", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("A prism refracts different wavelengths by different amounts. Shorter visible wavelengths generally experience a higher refractive index in ordinary glass.")
            Text("Selected wavelength: ${wavelength.toInt()} nm")
            Slider(wavelength, { wavelength = it }, valueRange = 400f..700f)
            Canvas(Modifier.fillMaxWidth().height(180.dp)) {
                val apex = Offset(size.width * 0.48f, 20f)
                val lowerLeft = Offset(size.width * 0.25f, size.height - 20f)
                val lowerRight = Offset(size.width * 0.72f, size.height - 20f)
                val prism = Path().apply { moveTo(apex.x, apex.y); lineTo(lowerLeft.x, lowerLeft.y); lineTo(lowerRight.x, lowerRight.y); close() }
                drawPath(prism, Color(0xFF80DEEA).copy(alpha = 0.28f))
                drawPath(prism, Color(0xFF00838F), style = Stroke(3f))
                drawLine(Color.White, Offset(8f, size.height * 0.45f), Offset(size.width * 0.39f, size.height * 0.45f), 5f)
                colours.forEachIndexed { index, colour ->
                    val spread = (index - 3) * 11f
                    drawLine(colour, Offset(size.width * 0.57f, size.height * 0.52f), Offset(size.width - 8f, size.height * 0.52f + spread), 4f)
                }
            }
            Text("Illustrative refractive index at selected wavelength: ${"%.4f".format(refractiveIndex)}")
            Text("Violet light is deviated more than red light in normal dispersion. The seven rays show the visible spectrum; the slider selects one wavelength for the material model.")
            Text("The displayed refractive-index equation uses illustrative Cauchy constants for glass. Actual dispersion depends on the glass composition.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun SolenoidFieldLaboratory() {
    var current by remember { mutableFloatStateOf(2f) }
    var turnsPerMetre by remember { mutableFloatStateOf(800f) }
    val fieldMilliTesla = 4f * PI.toFloat() * 1e-7f * current * turnsPerMetre * 1000f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Magnetic field of a current-carrying solenoid", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change the current and number of turns per metre. Observe how the field inside a long solenoid changes.")
            Canvas(Modifier.fillMaxWidth().height(170.dp)) {
                val left = size.width * 0.22f
                val right = size.width * 0.78f
                val top = size.height * 0.30f
                val bottom = size.height * 0.70f
                for (i in 0..12) {
                    val x = left + i * (right - left) / 12f
                    drawLine(Color(0xFF8D6E63), Offset(x, top), Offset(x, bottom), 2f)
                }
                drawLine(Color(0xFF1565C0), Offset(left, top), Offset(right, top), 3f)
                drawLine(Color(0xFF1565C0), Offset(left, bottom), Offset(right, bottom), 3f)
                for (i in 0..3) {
                    val y = top + 10f + i * (bottom - top - 20f) / 3f
                    drawLine(Color(0xFF00897B), Offset(left + 12f, y), Offset(right - 12f, y), 2.5f)
                    drawLine(Color(0xFF00897B), Offset(right - 22f, y - 6f), Offset(right - 12f, y), 2.5f)
                    drawLine(Color(0xFF00897B), Offset(right - 22f, y + 6f), Offset(right - 12f, y), 2.5f)
                }
                drawCircle(Color(0xFFEF6C00), 7f, Offset(left, (top + bottom) / 2f))
                drawCircle(Color(0xFF1565C0), 7f, Offset(right, (top + bottom) / 2f))
            }
            Text("Current: ${"%.2f".format(current)} A")
            Slider(current, { current = it }, valueRange = 0f..5f)
            Text("Turns per metre: ${turnsPerMetre.toInt()} m⁻¹")
            Slider(turnsPerMetre, { turnsPerMetre = it }, valueRange = 100f..2000f)
            Text("Field inside ideal long solenoid: ${"%.3f".format(fieldMilliTesla)} mT", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("B = μ₀nI. The field inside is approximately uniform and parallel to the solenoid axis; the external field is weaker and curves around the ends.")
            Text("Assumes an ideal long air-cored solenoid. Iron cores, finite length and fringing fields are not represented.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun ThermalConductionLaboratory() {
    var materialIndex by remember { mutableIntStateOf(0) }
    var area by remember { mutableFloatStateOf(0.02f) }
    var deltaT by remember { mutableFloatStateOf(40f) }
    var length by remember { mutableFloatStateOf(0.02f) }
    val materials = listOf("Copper" to 400f, "Aluminium" to 205f, "Glass" to 1f, "Wood" to 0.12f)
    val material = materials[materialIndex]
    val rate = material.second * area * deltaT / length
    val transition = rememberInfiniteTransition(label = "conduction-particles")
    val phase by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1800, easing = LinearEasing), RepeatMode.Restart), label = "heat-flow")
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Thermal conduction through a solid rod", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Select a material and change its dimensions and temperature difference. Animated markers show the direction of energy transfer from hot to cold.")
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                materials.forEachIndexed { index, item ->
                    FilterChip(materialIndex == index, { materialIndex = index }, { Text(item.first) })
                }
            }
            Canvas(Modifier.fillMaxWidth().height(100.dp)) {
                drawRect(Brush.horizontalGradient(listOf(Color(0xFFD84343), Color(0xFF90CAF9))), Offset(12f, 22f), Size(size.width - 24f, 46f))
                repeat(8) { index ->
                    val x = 20f + ((index * 37f + phase * (size.width - 40f)) % (size.width - 40f))
                    drawCircle(Color.White, 4f, Offset(x, size.height * 0.45f))
                }
                drawLine(Color.DarkGray, Offset(18f, 82f), Offset(size.width - 18f, 82f), 2f)
            }
            Text("Cross-sectional area: ${"%.3f".format(area)} m²")
            Slider(area, { area = it }, valueRange = 0.001f..0.05f)
            Text("Temperature difference: ${deltaT.toInt()} K")
            Slider(deltaT, { deltaT = it }, valueRange = 1f..100f)
            Text("Rod length: ${"%.3f".format(length)} m")
            Slider(length, { length = it }, valueRange = 0.005f..0.1f)
            Text("Thermal conductivity: ${material.second} W m⁻¹ K⁻¹")
            Text("Steady heat-transfer rate: ${"%.2f".format(rate)} W", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Rate = kAΔT/L. The animation shows energy transfer direction, not a molecular-scale solution of the heat equation.")
            Text("Assumes steady one-dimensional conduction and constant conductivity. Real rods lose heat to their surroundings.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun SemiconductorDiodeLaboratory() {
    var voltage by remember { mutableFloatStateOf(0.65f) }
    val thermalVoltage = 0.02585
    val saturationCurrent = 1e-12
    val currentAmp = saturationCurrent * (exp((voltage / (2.0 * thermalVoltage)).coerceIn(-40.0, 20.0)) - 1.0)
    val currentMilliAmp = currentAmp * 1000.0
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Semiconductor diode: explore the I–V characteristic", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Reverse-bias and forward-bias the diode. The current–voltage curve is strongly non-linear; it is not an ordinary fixed resistor.")
            Canvas(Modifier.fillMaxWidth().height(190.dp)) {
                val left = 36f
                val right = size.width - 12f
                val top = 12f
                val bottom = size.height - 28f
                val zeroX = left + (right - left) * 0.52f
                val zeroY = bottom - (bottom - top) * 0.22f
                drawLine(Color.Gray, Offset(left, zeroY), Offset(right, zeroY), 2f)
                drawLine(Color.Gray, Offset(zeroX, top), Offset(zeroX, bottom), 2f)
                val curve = Path()
                for (i in 0..120) {
                    val v = -1.0 + i * 1.9 / 120.0
                    val amps = saturationCurrent * (exp((v / (2.0 * thermalVoltage)).coerceIn(-40.0, 20.0)) - 1.0) * 1000.0
                    val x = left + (v + 1.0) / 1.9 * (right - left)
                    val y = (zeroY - amps.toFloat() * 18f).coerceIn(top, bottom)
                    if (i == 0) curve.moveTo(x, y) else curve.lineTo(x, y)
                }
                drawPath(curve, Color(0xFF1565C0), style = Stroke(3f))
                val pointX = left + (voltage + 1f) / 1.9f * (right - left)
                val pointY = (zeroY - currentMilliAmp.toFloat() * 18f).coerceIn(top, bottom)
                drawCircle(Color(0xFFE65100), 6f, Offset(pointX, pointY))
            }
            Text("Applied voltage: ${"%.2f".format(voltage)} V")
            Slider(voltage, { voltage = it }, valueRange = -1f..0.9f)
            Text("Model current: ${"%.4f".format(currentMilliAmp)} mA", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(if (voltage < 0f) "Reverse bias: the model current is a very small reverse saturation current." else "Forward bias: current rises rapidly as the forward voltage increases.")
            Text("Shockley diode model with ideality factor n = 2 and assumed saturation current. Real diode curves depend on device, temperature and series resistance; reverse breakdown is not shown.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
