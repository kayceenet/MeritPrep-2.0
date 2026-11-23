package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PhotosynthesisFlowCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Photosynthesis: inputs and products", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Plants use light energy to make glucose from carbon dioxide and water.")
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FlowLabel("Carbon dioxide", "CO₂", MaterialTheme.colorScheme.tertiaryContainer)
                FlowLabel("Water", "H₂O", MaterialTheme.colorScheme.tertiaryContainer)
                FlowLabel("Light", "Energy", MaterialTheme.colorScheme.tertiaryContainer)
            }
            Text("↓  Light energy is absorbed by chlorophyll in chloroplasts", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                Text("PHOTOSYNTHESIS", Modifier.fillMaxWidth().padding(14.dp), fontWeight = FontWeight.Bold)
            }
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FlowLabel("Glucose", "C₆H₁₂O₆", MaterialTheme.colorScheme.secondaryContainer)
                FlowLabel("Oxygen", "O₂", MaterialTheme.colorScheme.secondaryContainer)
            }
            Text("Word equation: carbon dioxide + water → glucose + oxygen", fontWeight = FontWeight.SemiBold)
            Text("The balanced chemical equation is 6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂. Light energy is required; chlorophyll absorbs the light.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun FoodChainDiagramCard() {
    val chain = listOf("Grass" to "Producer", "Grasshopper" to "Primary consumer", "Frog" to "Secondary consumer", "Snake" to "Tertiary consumer")
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("A simple food chain", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                chain.forEachIndexed { index, pair ->
                    Surface(color = if (index == 0) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                        Column(Modifier.padding(10.dp)) {
                            Text(pair.first, fontWeight = FontWeight.Bold)
                            Text(pair.second, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                    if (index < chain.lastIndex) Text("→", style = MaterialTheme.typography.titleLarge)
                }
            }
            Text("The arrows point from the organism being eaten to the organism that receives the food energy.")
            Text("Energy becomes less available at higher trophic levels because organisms use energy in respiration and lose energy as heat, waste and uneaten material.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun PunnettSquareCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Monohybrid cross: Tt × Tt", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("T = tall (dominant); t = short (recessive). Each parent can produce T or t gametes.")
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    PunnettCell("Gametes", true)
                    PunnettCell("T", true)
                    PunnettCell("t", true)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    PunnettCell("T", true)
                    PunnettCell("TT", false)
                    PunnettCell("Tt", false)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    PunnettCell("t", true)
                    PunnettCell("Tt", false)
                    PunnettCell("tt", false)
                }
            }
            Text("Genotype ratio: 1 TT : 2 Tt : 1 tt")
            Text("Phenotype ratio: 3 tall : 1 short, assuming complete dominance.", fontWeight = FontWeight.SemiBold)
            Text("These are expected probabilities across many offspring, not a guarantee for a small number of offspring.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun DoubleCirculationCard() {
    val route = listOf(
        "Body tissues",
        "Venae cavae",
        "Right atrium",
        "Right ventricle",
        "Pulmonary artery",
        "Lungs",
        "Pulmonary veins",
        "Left atrium",
        "Left ventricle",
        "Aorta",
        "Body tissues"
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Double circulation in mammals", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Follow the route of blood through the pulmonary and systemic circuits.")
            route.forEachIndexed { index, label ->
                Surface(
                    color = when {
                        label.contains("Right") || label.contains("Venae") || label == "Pulmonary artery" -> MaterialTheme.colorScheme.tertiaryContainer
                        label.contains("Left") || label == "Aorta" || label == "Pulmonary veins" -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(label, Modifier.fillMaxWidth().padding(9.dp), fontWeight = FontWeight.SemiBold)
                }
                if (index < route.lastIndex) Text("↓", Modifier.padding(start = 12.dp), fontWeight = FontWeight.Bold)
            }
            Text("Teal marks deoxygenated blood; red marks oxygenated blood.", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
            Text("The pulmonary circuit carries blood between the heart and lungs. The systemic circuit carries blood between the heart and the rest of the body.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun FlowLabel(title: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Surface(color = color, shape = MaterialTheme.shapes.medium) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 9.dp)) {
            Text(title, style = MaterialTheme.typography.labelMedium)
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PunnettCell(value: String, header: Boolean) {
    Surface(
        modifier = Modifier.width(100.dp),
        color = if (header) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Box(Modifier.padding(vertical = 12.dp), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}
