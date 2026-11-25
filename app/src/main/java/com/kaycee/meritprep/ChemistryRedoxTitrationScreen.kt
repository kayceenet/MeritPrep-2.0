package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.ChemistryRedoxTitrationEngine
import com.kaycee.meritprep.data.RedoxTitrationResult

@Composable
fun ChemistryRedoxTitrationScreen() {
    var permanganateConcentration by remember { mutableStateOf("0.0200") }
    var ironAliquot by remember { mutableStateOf("25.00") }
    val initial = remember { mutableStateListOf("0.00", "0.00", "0.00") }
    val final = remember { mutableStateListOf("24.80", "24.85", "25.40") }
    var result by remember { mutableStateOf<RedoxTitrationResult?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Redox titration · iron(II)", style = MaterialTheme.typography.headlineMedium)
        Text("Calculate an unknown Fe²⁺ concentration from acidified potassium permanganate titration data.")
        Card {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Balanced ionic equation", style = MaterialTheme.typography.titleMedium)
                ScientificText("MnO₄⁻ + 5Fe²⁺ + 8H⁺ → Mn²⁺ + 5Fe³⁺ + 4H₂O", style = MaterialTheme.typography.titleMedium)
                ScientificText("The mole ratio is 1 mol permanganate : 5 mol iron(II).", style = MaterialTheme.typography.bodyMedium)
            }
        }
        OutlinedTextField(
            value = permanganateConcentration,
            onValueChange = { permanganateConcentration = it; result = null },
            label = { Text("KMnO₄ concentration (mol dm⁻³)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = ironAliquot,
            onValueChange = { ironAliquot = it; result = null },
            label = { Text("Iron(II) aliquot (cm³)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text("Burette readings", style = MaterialTheme.typography.titleLarge)
        Text("Enter initial and final readings for three titrations. The calculation uses the largest concordant set within 0.20 cm³.")
        repeat(3) { index ->
            Card {
                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Titration ${index + 1}", style = MaterialTheme.typography.titleSmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = initial[index],
                            onValueChange = { initial[index] = it; result = null },
                            label = { Text("Initial (cm³)") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = final[index],
                            onValueChange = { final[index] = it; result = null },
                            label = { Text("Final (cm³)") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
                val parsedInitial = initial.mapNotNull { it.toDoubleOrNull() }
                val parsedFinal = final.mapNotNull { it.toDoubleOrNull() }
                val concentration = permanganateConcentration.toDoubleOrNull()
                val aliquot = ironAliquot.toDoubleOrNull()
                if (parsedInitial.size != 3 || parsedFinal.size != 3 || concentration == null || aliquot == null) {
                    error = "Enter valid numbers in every field."
                    result = null
                } else {
                    runCatching {
                        ChemistryRedoxTitrationEngine.calculateIronIiConcentration(
                            concentration, parsedInitial, parsedFinal, aliquot
                        )
                    }.onSuccess {
                        result = it
                        error = null
                    }.onFailure {
                        result = null
                        error = it.message ?: "Check the readings and try again."
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Calculate Fe²⁺ concentration") }

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        result?.let { calculated ->
            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Calculated result", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "%.4f mol dm⁻³".format(calculated.analyteConcentrationMolPerDm3),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Mean concordant titre: %.3f cm³".format(calculated.meanTitreCm3))
                    Text("Moles of MnO₄⁻: %.6g mol".format(calculated.molesPermanganate))
                    Text("Moles of Fe²⁺: %.6g mol".format(calculated.molesIronIi))
                    ScientificText(calculated.calculation, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Text(
            "Model assumptions: the reaction proceeds in sufficiently acidic solution, the endpoint represents equivalence, the stated permanganate concentration is accurate, and no interfering reducing agent is present. Acidified permanganate is a laboratory reagent; practical work requires trained supervision and appropriate PPE.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
