package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val firstTenElements = listOf(
    "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron",
    "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon"
)
private val firstTenSymbols = listOf("H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne")

/** An interactive, explicitly simplified shell model for the atomic-structure topic. */
@Composable
fun AtomicStructureInquiry() {
    var protonCount by remember { mutableFloatStateOf(6f) }
    var neutronCount by remember { mutableFloatStateOf(6f) }
    var electronCount by remember { mutableFloatStateOf(6f) }

    val protons = protonCount.toInt()
    val neutrons = neutronCount.toInt()
    val electrons = electronCount.toInt()
    val massNumber = protons + neutrons
    val charge = protons - electrons
    val shellOne = electrons.coerceAtMost(2)
    val shellTwo = (electrons - shellOne).coerceAtMost(8)
    val chargeText = when {
        charge == 0 -> "neutral"
        charge > 0 -> "+$charge"
        else -> charge.toString()
    }

    Card(Modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Text(
                "Build an atom: protons, neutrons and electrons",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Change the particles and watch the atom's identity, isotope and charge update. " +
                    "The diagram uses a simplified Bohr-style shell model for learning; electrons do not travel in fixed circular paths in real atoms."
            )

            Canvas(Modifier.fillMaxWidth().height(230.dp)) {
                val centre = Offset(size.width / 2f, size.height / 2f)
                val shellRadii = listOf(65f, 105f)
                shellRadii.forEach { radius ->
                    drawCircle(
                        color = Color(0xFF90A4AE),
                        radius = radius,
                        center = centre,
                        style = Stroke(width = 2f)
                    )
                }

                val nucleons = protons + neutrons
                repeat(nucleons) { index ->
                    val angle = 2.0 * PI * index / nucleons.coerceAtLeast(1)
                    val radius = if (nucleons <= 8) 13f else 22f
                    val centreRadius = if (nucleons <= 8) 0f else 15f
                    val position = Offset(
                        centre.x + cos(angle).toFloat() * centreRadius,
                        centre.y + sin(angle).toFloat() * centreRadius
                    )
                    val colour = if (index < protons) Color(0xFFD84343) else Color(0xFF546E7A)
                    drawCircle(colour, radius, position)
                }

                val shellCounts = listOf(shellOne, shellTwo)
                shellCounts.forEachIndexed { shellIndex, count ->
                    val radius = shellRadii[shellIndex]
                    repeat(count) { index ->
                        val angle = -PI / 2.0 + 2.0 * PI * index / count.coerceAtLeast(1)
                        val position = Offset(
                            centre.x + cos(angle).toFloat() * radius,
                            centre.y + sin(angle).toFloat() * radius
                        )
                        drawCircle(Color(0xFF1565C0), 6f, position)
                    }
                }
            }

            Text("Element: ${firstTenElements[protons - 1]} (${firstTenSymbols[protons - 1]})", style = MaterialTheme.typography.titleMedium)
            Text("Protons: $protons")
            Slider(protonCount, { protonCount = it }, valueRange = 1f..10f, steps = 8)
            Text("Neutrons: $neutrons")
            Slider(neutronCount, { neutronCount = it }, valueRange = 0f..12f, steps = 11)
            Text("Electrons: $electrons")
            Slider(electronCount, { electronCount = it }, valueRange = 0f..10f, steps = 9)

            Text("Atomic number = $protons", fontWeight = FontWeight.SemiBold)
            Text("Mass number = $massNumber")
            Text("Electron arrangement (shell model): $shellOne" + if (shellTwo > 0) ", $shellTwo" else "")
            Text("Net charge: $chargeText", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Text(
                when {
                    electrons == protons -> "The particle counts give a neutral atom: proton number equals electron number."
                    electrons < protons -> "There are fewer electrons than protons, so the particle model represents a positive ion (cation)."
                    else -> "There are more electrons than protons, so the particle model represents a negative ion (anion)."
                }
            )
            Text(
                "Change only the neutron count: the element stays the same but the isotope and mass number change. " +
                    "Change the proton count: the element itself changes. Change the electron count: the ion's charge changes.",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "Model limitation: only the first ten elements and two electron shells are shown. The drawing is a teaching diagram, not a scale model of nuclear size or a probability distribution for electrons.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
