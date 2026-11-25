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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TopicScienceAnimation(topicId: String) {
    when (topicId) {
        "phy-waves", "phy-sound-propagation", "phy-sound-characteristics" -> WavePropagationAnimation()
        "phy-refraction", "phy-optical-instruments" -> RefractionRayAnimation()
        "phy-current-electricity", "phy-cells", "phy-electrical-power" -> ElectronFlowAnimation()
        "chem-equilibrium", "chem-industry" -> ReversibleReactionAnimation()
    }
}

@Composable
private fun WavePropagationAnimation() {
    var frequency by remember { mutableFloatStateOf(1.0f) }
    var amplitude by remember { mutableFloatStateOf(0.7f) }
    val transition = rememberInfiniteTransition(label = "wave-propagation")
    val phase by transition.animateFloat(0f, (2f * PI).toFloat(), infiniteRepeatable(tween((2400f / frequency).toInt().coerceAtLeast(500), easing = LinearEasing), RepeatMode.Restart), label = "wave-phase")
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Wave propagation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                val mid = size.height * 0.5f
                val path = Path()
                for (i in 0..180) {
                    val x = size.width * i / 180f
                    val y = mid - sin(i / 180f * 4f * PI.toFloat() - phase) * amplitude * size.height * 0.32f
                    if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }
                drawLine(Color.Gray, Offset(0f, mid), Offset(size.width, mid), 1.5f)
                drawPath(path, primary, style = Stroke(width = 4f, cap = StrokeCap.Round))
                val particleX = size.width * 0.5f
                val particleY = mid - sin(2f * PI.toFloat() - phase) * amplitude * size.height * 0.32f
                drawCircle(secondary, 7f, Offset(particleX, particleY))
            }
            Text("Frequency: " + "%.1f".format(frequency) + " Hz")
            Slider(frequency, { frequency = it }, valueRange = 0.5f..3f)
            Text("Amplitude (relative): " + "%.2f".format(amplitude))
            Slider(amplitude, { amplitude = it }, valueRange = 0.2f..1f)
            ScientificText("v = f\\\\lambda    T = \\\\frac{1}{f}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("The animation shows a travelling transverse wave. The highlighted point oscillates; it does not travel along with the wave. The model keeps the medium and wave speed fixed.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun RefractionRayAnimation() {
    var incidence by remember { mutableFloatStateOf(40f) }
    var refractiveIndex by remember { mutableFloatStateOf(1.0f) }
    val sinR = 1.5 * sin(Math.toRadians(incidence.toDouble())) / refractiveIndex
    val totalInternalReflection = sinR > 1.0
    val refraction = if (totalInternalReflection) 90.0 else Math.toDegrees(asin(sinR.coerceIn(-1.0, 1.0)))
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Refraction at a boundary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(170.dp)) {
                val cx = size.width * 0.5f
                val cy = size.height * 0.48f
                val length = size.height * 0.42f
                drawLine(Color.Gray, Offset(0f, cy), Offset(size.width, cy), 2f)
                drawLine(Color.Gray.copy(alpha = 0.65f), Offset(cx, 4f), Offset(cx, size.height - 4f), 1.5f, pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(7f, 6f)))
                val a = Math.toRadians(incidence.toDouble())
                val start = Offset(cx - sin(a).toFloat() * length, cy - cos(a).toFloat() * length)
                drawLine(primary, start, Offset(cx, cy), 4f, cap = StrokeCap.Round)
                if (totalInternalReflection) {
                    val reflected = Offset(cx + sin(a).toFloat() * length, cy - cos(a).toFloat() * length)
                    drawLine(secondary, Offset(cx, cy), reflected, 4f, cap = StrokeCap.Round)
                } else {
                    val r = Math.toRadians(refraction)
                    val end = Offset(cx + sin(r).toFloat() * length, cy + cos(r).toFloat() * length)
                    drawLine(secondary, Offset(cx, cy), end, 4f, cap = StrokeCap.Round)
                }
            }
            Text("Angle of incidence: " + incidence.toInt() + "°")
            Slider(incidence, { incidence = it }, valueRange = 5f..85f)
            Text("Refractive index of second medium (n₂): " + "%.2f".format(refractiveIndex))
            Slider(refractiveIndex, { refractiveIndex = it }, valueRange = 0.8f..1.5f)
            ScientificText("n_1\\\\sin i = n_2\\\\sin r", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(if (totalInternalReflection) "Total internal reflection condition reached for this simplified boundary model." else "Calculated refracted angle: " + "%.1f".format(refraction) + "°. For n₂ < n₁, the ray bends away from the normal; at the critical angle the refracted ray travels along the boundary.", style = MaterialTheme.typography.bodySmall)
            Text("The diagram is schematic. The calculation assumes light travels from glass (n₁ = 1.50) into the selected second medium. The slider can reach the critical-angle condition.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ElectronFlowAnimation() {
    var voltage by remember { mutableFloatStateOf(6f) }
    var resistance by remember { mutableFloatStateOf(6f) }
    val current = voltage / resistance
    val transition = rememberInfiniteTransition(label = "electron-flow")
    val phase by transition.animateFloat(0f, 1f, infiniteRepeatable(tween((1800f / current.coerceAtLeast(0.1f)).toInt().coerceIn(250, 5000), easing = LinearEasing), RepeatMode.Restart), label = "electron-phase")
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Direct-current circuit", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(145.dp)) {
                val left = size.width * 0.18f
                val right = size.width * 0.82f
                val top = size.height * 0.22f
                val bottom = size.height * 0.78f
                drawLine(primary, Offset(left, top), Offset(size.width * 0.42f, top), 3f)
                drawLine(primary, Offset(size.width * 0.58f, top), Offset(right, top), 3f)
                drawLine(primary, Offset(right, top), Offset(right, bottom), 3f)
                drawLine(primary, Offset(right, bottom), Offset(left, bottom), 3f)
                drawLine(primary, Offset(left, bottom), Offset(left, top), 3f)
                drawLine(secondary, Offset(size.width * 0.42f, top - 13f), Offset(size.width * 0.42f, top + 13f), 5f)
                drawLine(secondary, Offset(size.width * 0.48f, top - 8f), Offset(size.width * 0.48f, top + 8f), 3f)
                drawRect(primary.copy(alpha = 0.12f), Offset(size.width * 0.58f, top - 10f), androidx.compose.ui.geometry.Size(size.width * 0.16f, 20f))
                drawRect(primary, Offset(size.width * 0.58f, top - 10f), androidx.compose.ui.geometry.Size(size.width * 0.16f, 20f), style = Stroke(2.5f))
                val perimeter = listOf(Offset(left, top), Offset(right, top), Offset(right, bottom), Offset(left, bottom), Offset(left, top))
                val segment = (phase * 4f).toInt().coerceIn(0, 3)
                val local = phase * 4f - segment
                val a = perimeter[segment]
                val b = perimeter[segment + 1]
                val electron = Offset(a.x + (b.x - a.x) * local, a.y + (b.y - a.y) * local)
                drawCircle(secondary, 6f, electron)
            }
            Text("Supply voltage: " + "%.1f".format(voltage) + " V")
            Slider(voltage, { voltage = it }, valueRange = 1f..12f)
            Text("Resistance: " + "%.1f".format(resistance) + " Ω")
            Slider(resistance, { resistance = it }, valueRange = 2f..20f)
            ScientificText("I = \\\\frac{V}{R} = " + "%.2f".format(current) + " A", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("Ideal fixed-resistance model. The moving marker is illustrative charge flow, not a simulation of individual electron drift speed or the electromagnetic field.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ReversibleReactionAnimation() {
    var equilibriumConstant by remember { mutableFloatStateOf(1f) }
    val productFraction = equilibriumConstant / (1f + equilibriumConstant)
    val transition = rememberInfiniteTransition(label = "equilibrium-particles")
    val phase by transition.animateFloat(0f, (2f * PI).toFloat(), infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Restart), label = "equilibrium-phase")
    val reactantColor = MaterialTheme.colorScheme.primary
    val productColor = MaterialTheme.colorScheme.tertiary
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Dynamic chemical equilibrium", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Canvas(Modifier.fillMaxWidth().height(145.dp)) {
                val split = size.width * 0.5f
                drawLine(Color.Gray, Offset(split, 8f), Offset(split, size.height - 8f), 1.5f)
                for (i in 0 until 24) {
                    val isProduct = i < (24f * productFraction).toInt()
                    val xBase = if (isProduct) split + 25f else split - 25f
                    val col = if (isProduct) i % 4 else i % 4
                    val row = i / 4
                    val x = xBase + (col - 1.5f) * 24f + sin(phase + i) * 4f
                    val y = 18f + row * 19f + cos(phase * 1.2f + i * 0.7f) * 3f
                    drawCircle(if (isProduct) productColor else reactantColor, 6f, Offset(x, y))
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Reactants A", color = reactantColor, fontWeight = FontWeight.Bold)
                Text("Products B", color = productColor, fontWeight = FontWeight.Bold)
            }
            Text("Equilibrium constant K: " + "%.2f".format(equilibriumConstant))
            Slider(equilibriumConstant, { equilibriumConstant = it }, valueRange = 0.1f..10f)
            ScientificText("A \\\\rightleftharpoons B     K_c = \\\\frac{[B]}{[A]}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("Model product fraction: " + "%.1f".format(productFraction * 100f) + "%. At dynamic equilibrium, forward and reverse reaction rates are equal; particles continue reacting even though macroscopic concentrations remain constant.", style = MaterialTheme.typography.bodySmall)
            Text("This is a one-step idealised A ⇌ B model. Real equilibrium expressions depend on the balanced reaction and activities/concentrations.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
