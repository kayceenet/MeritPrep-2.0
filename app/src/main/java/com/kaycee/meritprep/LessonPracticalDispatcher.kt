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

enum class LessonPracticalKind {
    CHEMISTRY_REDOX, CHEMISTRY_QUALITATIVE, PHYSICS_ELASTICITY, PHYSICS_PRESSURE,
    PHYSICS_FLUIDS, PHYSICS_HEAT, PHYSICS_GAS_LAWS, PHYSICS_CAPACITORS, PHYSICS_RESISTOR_NETWORK
}

object LessonPracticalRouting {
    fun forTopic(topicId: String): LessonPracticalKind? = when (topicId) {
        "chem-acids-bases", "chem-redox" -> LessonPracticalKind.CHEMISTRY_REDOX
        "chem-metals", "chem-nonmetals" -> LessonPracticalKind.CHEMISTRY_QUALITATIVE
        "phy-elasticity" -> LessonPracticalKind.PHYSICS_ELASTICITY
        "phy-pressure" -> LessonPracticalKind.PHYSICS_PRESSURE
        "phy-liquids-rest" -> LessonPracticalKind.PHYSICS_FLUIDS
        "phy-temperature", "phy-heat-quantity", "phy-change-state" -> LessonPracticalKind.PHYSICS_HEAT
        "phy-gas-laws", "phy-vapours", "phy-kinetic-theory" -> LessonPracticalKind.PHYSICS_GAS_LAWS
        "phy-capacitors" -> LessonPracticalKind.PHYSICS_CAPACITORS
        "phy-cells", "phy-current-electricity", "phy-electrical-power" -> LessonPracticalKind.PHYSICS_RESISTOR_NETWORK
        else -> null
    }
}

@Composable
fun LessonPracticalDispatcher(topicId: String) {
    val kind = LessonPracticalRouting.forTopic(topicId) ?: return
    val title = when (kind) {
        LessonPracticalKind.CHEMISTRY_REDOX -> "Quantitative analysis: redox titration"
        LessonPracticalKind.CHEMISTRY_QUALITATIVE -> "Qualitative chemical analysis"
        LessonPracticalKind.PHYSICS_ELASTICITY -> "Spring extension and Hooke's law"
        LessonPracticalKind.PHYSICS_PRESSURE -> "Pressure and contact area"
        LessonPracticalKind.PHYSICS_FLUIDS -> "Fluid density, pressure and upthrust"
        LessonPracticalKind.PHYSICS_HEAT -> "Thermal physics practical"
        LessonPracticalKind.PHYSICS_GAS_LAWS -> "Gas laws investigation"
        LessonPracticalKind.PHYSICS_CAPACITORS -> "Capacitor network practical"
        LessonPracticalKind.PHYSICS_RESISTOR_NETWORK -> "Resistor network practical"
    }

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Topic-specific practical workspace", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text(title, style = MaterialTheme.typography.titleLarge)
                Text("This practical complements the guided investigation. Work through the controls and calculations, then explain the result using the topic's governing principle.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        when (kind) {
            LessonPracticalKind.CHEMISTRY_REDOX -> ChemistryRedoxTitrationScreen(embedded = true)
            LessonPracticalKind.CHEMISTRY_QUALITATIVE -> ChemistryQualitativeGuideScreen(embedded = true)
            LessonPracticalKind.PHYSICS_ELASTICITY -> PhysicsElasticityLab()
            LessonPracticalKind.PHYSICS_PRESSURE -> PhysicsPressureLab()
            LessonPracticalKind.PHYSICS_FLUIDS -> PhysicsFluidsLab()
            LessonPracticalKind.PHYSICS_HEAT -> PhysicsHeatLab()
            LessonPracticalKind.PHYSICS_GAS_LAWS -> PhysicsGasLawsLab()
            LessonPracticalKind.PHYSICS_CAPACITORS -> PhysicsCapacitorNetworkLab()
            LessonPracticalKind.PHYSICS_RESISTOR_NETWORK -> PhysicsResistorNetworkLab()
        }
    }
}
