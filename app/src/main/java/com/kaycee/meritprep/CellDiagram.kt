package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

private val wallColor = Color(0xFFB6D7A8)
private val membraneColor = Color(0xFF438B66)
private val cytoplasmColor = Color(0xFFEAF3D8)
private val vacuoleColor = Color(0xFFB7DDF0)
private val nucleusColor = Color(0xFF8F79C6)
private val chloroplastColor = Color(0xFF4C9A5A)
private val mitochondrionColor = Color(0xFFD78A57)

@Composable
fun CellDiagramCard() {
    var plantCell by remember { mutableStateOf(true) }
    var selectedStructure by remember { mutableStateOf("Cell wall") }
    val structures = if (plantCell) {
        listOf(
            "Cell wall" to "A cellulose layer outside the cell membrane. It supports the cell, helps maintain its shape and resists excessive expansion.",
            "Cell membrane" to "A selectively permeable boundary that controls movement of substances into and out of the cell.",
            "Cytoplasm" to "A watery, jelly-like medium where many cell reactions take place and organelles are suspended.",
            "Nucleus" to "Contains most of the cell's genetic material and helps regulate cell activities.",
            "Large vacuole" to "A fluid-filled compartment containing cell sap. In many mature plant cells it helps maintain turgor.",
            "Chloroplast" to "Contains chlorophyll and is the site of photosynthesis in photosynthetic plant cells.",
            "Mitochondrion" to "The main site of aerobic respiration in eukaryotic cells; it produces most cellular ATP."
        )
    } else {
        listOf(
            "Cell membrane" to "A selectively permeable boundary that controls movement of substances into and out of the cell.",
            "Cytoplasm" to "A watery, jelly-like medium where many cell reactions take place and organelles are suspended.",
            "Nucleus" to "Contains most of the cell's genetic material and helps regulate cell activities.",
            "Mitochondrion" to "The main site of aerobic respiration in eukaryotic cells; it produces most cellular ATP.",
            "Ribosome" to "The site of protein synthesis. Ribosomes occur in both plant and animal cells."
        )
    }
    val selectedDescription = structures.firstOrNull { it.first == selectedStructure }?.second ?: structures.first().second

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Explore the cell", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Switch between simplified diagrams, then select a structure to learn its function.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = plantCell,
                    onClick = { plantCell = true; selectedStructure = "Cell wall" },
                    label = { Text("Plant cell") }
                )
                FilterChip(
                    selected = !plantCell,
                    onClick = { plantCell = false; selectedStructure = "Cell membrane" },
                    label = { Text("Animal cell") }
                )
            }
            Canvas(Modifier.fillMaxWidth().height(210.dp)) {
                val w = size.width
                val h = size.height
                if (plantCell) {
                    drawRoundRect(
                        color = wallColor,
                        topLeft = Offset(w * 0.08f, h * 0.08f),
                        size = Size(w * 0.84f, h * 0.84f),
                        cornerRadius = CornerRadius(28f)
                    )
                    drawRoundRect(
                        color = membraneColor,
                        topLeft = Offset(w * 0.12f, h * 0.12f),
                        size = Size(w * 0.76f, h * 0.76f),
                        cornerRadius = CornerRadius(23f)
                    )
                    drawRoundRect(
                        color = cytoplasmColor,
                        topLeft = Offset(w * 0.145f, h * 0.145f),
                        size = Size(w * 0.71f, h * 0.71f),
                        cornerRadius = CornerRadius(20f)
                    )
                    drawRoundRect(
                        color = vacuoleColor,
                        topLeft = Offset(w * 0.29f, h * 0.28f),
                        size = Size(w * 0.39f, h * 0.48f),
                        cornerRadius = CornerRadius(24f)
                    )
                    drawCircle(nucleusColor, radius = h * 0.105f, center = Offset(w * 0.75f, h * 0.36f))
                    drawCircle(Color(0xFF5D4B93), radius = h * 0.035f, center = Offset(w * 0.77f, h * 0.35f))
                    listOf(
                        Offset(w * 0.23f, h * 0.28f),
                        Offset(w * 0.76f, h * 0.72f),
                        Offset(w * 0.22f, h * 0.72f)
                    ).forEach { center ->
                        drawOval(chloroplastColor, Offset(center.x - h * 0.07f, center.y - h * 0.035f), Size(h * 0.14f, h * 0.07f))
                        drawLine(Color(0xFF245E37), Offset(center.x - h * 0.045f, center.y), Offset(center.x + h * 0.045f, center.y), strokeWidth = 2f)
                    }
                    drawOval(mitochondrionColor, Offset(w * 0.54f, h * 0.78f), Size(h * 0.13f, h * 0.07f))
                } else {
                    drawOval(
                        color = membraneColor,
                        topLeft = Offset(w * 0.18f, h * 0.08f),
                        size = Size(w * 0.64f, h * 0.84f)
                    )
                    drawOval(
                        color = cytoplasmColor,
                        topLeft = Offset(w * 0.205f, h * 0.12f),
                        size = Size(w * 0.59f, h * 0.76f)
                    )
                    drawCircle(nucleusColor, radius = h * 0.13f, center = Offset(w * 0.49f, h * 0.48f))
                    drawCircle(Color(0xFF5D4B93), radius = h * 0.045f, center = Offset(w * 0.51f, h * 0.47f))
                    listOf(
                        Offset(w * 0.32f, h * 0.32f),
                        Offset(w * 0.66f, h * 0.65f),
                        Offset(w * 0.33f, h * 0.70f)
                    ).forEach { center ->
                        drawOval(mitochondrionColor, Offset(center.x - h * 0.07f, center.y - h * 0.035f), Size(h * 0.14f, h * 0.07f))
                        drawLine(Color(0xFF8A4C2C), Offset(center.x - h * 0.04f, center.y), Offset(center.x + h * 0.04f, center.y), strokeWidth = 2f)
                    }
                }
            }
            Text(
                "Simplified schematic; organelle sizes and positions are not to scale.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                structures.forEach { (name, _) ->
                    FilterChip(
                        selected = selectedStructure == name,
                        onClick = { selectedStructure = name },
                        label = { Text(name) }
                    )
                }
            }
            Text(selectedStructure, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(selectedDescription, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
