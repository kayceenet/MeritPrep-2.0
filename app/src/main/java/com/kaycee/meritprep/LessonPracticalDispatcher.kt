package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

/**
 * Routes a lesson to a subject-specific practical workspace when a developed
 * native lab already exists. General investigations remain in GuidedInvestigationActivity.
 */
@Composable
fun LessonPracticalDispatcher(topicId: String) {
    val title = when (topicId) {
        "chem-acids-bases", "chem-redox" -> "Quantitative analysis: redox titration"
        "chem-metals", "chem-nonmetals" -> "Qualitative chemical analysis"
        "phy-elasticity" -> "Spring extension and Hooke's law"
        "phy-pressure" -> "Pressure and contact area"
        "phy-liquids-rest" -> "Fluid density, pressure and upthrust"
        "phy-temperature", "phy-heat-quantity", "phy-change-state" -> "Thermal physics practical"
        "phy-gas-laws", "phy-vapours", "phy-kinetic-theory" -> "Gas laws investigation"
        "phy-capacitors" -> "Capacitor network practical"
        "phy-cells", "phy-current-electricity", "phy-electrical-power" -> "Resistor network practical"
        else -> null
    } ?: return

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Topic-specific practical workspace", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text(title, style = MaterialTheme.typography.titleLarge)
                Text("This practical complements the guided investigation. Work through the controls and calculations, then explain the result using the topic's governing principle.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        when (topicId) {
            "chem-acids-bases", "chem-redox" -> ChemistryRedoxTitrationScreen(embedded = true)
            "chem-metals", "chem-nonmetals" -> ChemistryQualitativeGuideScreen(embedded = true)
            "phy-elasticity" -> PhysicsElasticityLab()
            "phy-pressure" -> PhysicsPressureLab()
            "phy-liquids-rest" -> PhysicsFluidsLab()
            "phy-temperature", "phy-heat-quantity", "phy-change-state" -> PhysicsHeatLab()
            "phy-gas-laws", "phy-vapours", "phy-kinetic-theory" -> PhysicsGasLawsLab()
            "phy-capacitors" -> PhysicsCapacitorNetworkLab()
            "phy-cells", "phy-current-electricity", "phy-electrical-power" -> PhysicsResistorNetworkLab()
        }
    }
}
