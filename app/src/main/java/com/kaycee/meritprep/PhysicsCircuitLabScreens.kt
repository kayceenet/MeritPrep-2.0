package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.NetworkConnection
import com.kaycee.meritprep.data.PhysicsCircuitEngine

@Composable
fun PhysicsResistorNetworkLab() {
    var series by remember { mutableStateOf(true) }
    var firstResistance by remember { mutableFloatStateOf(4f) }
    var secondResistance by remember { mutableFloatStateOf(6f) }
    var voltage by remember { mutableFloatStateOf(12f) }
    val result = PhysicsCircuitEngine.resistorNetwork(
        listOf(firstResistance.toDouble(), secondResistance.toDouble()),
        voltage.toDouble(),
        if (series) NetworkConnection.SERIES else NetworkConnection.PARALLEL
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Resistor networks", style = MaterialTheme.typography.titleLarge)
            Text("Compare the total resistance and current when two resistors are connected in series or parallel.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = series, onClick = { series = true }, label = { Text("Series") })
                FilterChip(selected = !series, onClick = { series = false }, label = { Text("Parallel") })
            }
            Text("R₁: ${"%.1f".format(firstResistance)} Ω")
            Slider(value = firstResistance, onValueChange = { firstResistance = it }, valueRange = 1f..20f)
            Text("R₂: ${"%.1f".format(secondResistance)} Ω")
            Slider(value = secondResistance, onValueChange = { secondResistance = it }, valueRange = 1f..20f)
            Text("Supply voltage: ${"%.1f".format(voltage)} V")
            Slider(value = voltage, onValueChange = { voltage = it }, valueRange = 0f..24f)
            HorizontalDivider()
            Text("Equivalent resistance: ${"%.2f".format(result.equivalentResistanceOhms)} Ω", style = MaterialTheme.typography.titleMedium)
            Text("Total current: ${"%.2f".format(result.totalCurrentAmps)} A")
            Text("Total power: ${"%.2f".format(result.totalPowerWatts)} W")
            ScientificText(if (series) "Rₜ = R₁ + R₂" else "1/Rₜ = 1/R₁ + 1/R₂", style = MaterialTheme.typography.titleMedium)
        }
    }
    Text("The model assumes ideal wires and a constant-voltage DC source. Real resistor values vary with tolerance and temperature; components must be selected within their power ratings.")
}

@Composable
fun PhysicsCapacitorNetworkLab() {
    var series by remember { mutableStateOf(true) }
    var firstCapacitance by remember { mutableFloatStateOf(6f) }
    var secondCapacitance by remember { mutableFloatStateOf(3f) }
    var voltage by remember { mutableFloatStateOf(12f) }
    val result = PhysicsCircuitEngine.capacitorNetwork(
        listOf(firstCapacitance.toDouble() * 1e-6, secondCapacitance.toDouble() * 1e-6),
        voltage.toDouble(),
        if (series) NetworkConnection.SERIES else NetworkConnection.PARALLEL
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Capacitor networks", style = MaterialTheme.typography.titleLarge)
            Text("Explore how the equivalent capacitance changes when two capacitors are connected in series or parallel.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = series, onClick = { series = true }, label = { Text("Series") })
                FilterChip(selected = !series, onClick = { series = false }, label = { Text("Parallel") })
            }
            Text("C₁: ${"%.1f".format(firstCapacitance)} μF")
            Slider(value = firstCapacitance, onValueChange = { firstCapacitance = it }, valueRange = 1f..20f)
            Text("C₂: ${"%.1f".format(secondCapacitance)} μF")
            Slider(value = secondCapacitance, onValueChange = { secondCapacitance = it }, valueRange = 1f..20f)
            Text("Supply voltage: ${"%.1f".format(voltage)} V")
            Slider(value = voltage, onValueChange = { voltage = it }, valueRange = 0f..24f)
            HorizontalDivider()
            Text("Equivalent capacitance: ${"%.2f".format(result.equivalentCapacitanceFarads * 1e6)} μF", style = MaterialTheme.typography.titleMedium)
            Text("Stored charge: ${"%.2f".format(result.totalChargeCoulombs * 1e6)} μC")
            Text("Stored energy: ${"%.2f".format(result.storedEnergyJoules * 1e6)} μJ")
            ScientificText(if (series) "1/Cₜ = 1/C₁ + 1/C₂" else "Cₜ = C₁ + C₂", style = MaterialTheme.typography.titleMedium)
        }
    }
    Text("The calculation assumes ideal capacitors and a DC supply after the charging transient. Real capacitors have voltage ratings, leakage and dielectric limits; stored charge must be handled safely.")
}
