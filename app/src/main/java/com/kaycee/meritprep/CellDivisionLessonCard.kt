package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Reusable, stage-by-stage chromosome model shared by the Science Media area and Biology lesson.
 */
@Composable
fun CellDivisionLessonCard() {
    var selectedDivisionIndex by remember { mutableIntStateOf(0) }
    var stageIndex by remember { mutableIntStateOf(0) }
    val divisionType = if (selectedDivisionIndex == 0) CellDivisionType.MITOSIS else CellDivisionType.MEIOSIS
    val lesson = CellDivisionSimulation.lesson(divisionType)
    val stage = lesson.stages[stageIndex.coerceIn(0, lesson.stages.lastIndex)]

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("MeritPrep interactive model: mitosis and meiosis", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Follow each stage in order. Chromosomes are counted by centromeres; a replicated chromosome still counts as one chromosome until its sister chromatids separate.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = selectedDivisionIndex == 0, onClick = { selectedDivisionIndex = 0; stageIndex = 0 }, label = { Text("Mitosis") })
                FilterChip(selected = selectedDivisionIndex == 1, onClick = { selectedDivisionIndex = 1; stageIndex = 0 }, label = { Text("Meiosis") })
            }
            Text("Stage ${stageIndex + 1} of ${lesson.stages.size}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            CellDivisionDiagram(divisionType, stageIndex)
            Text("Chromosome schematic · not to scale", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(stage.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(stage.description)
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("Chromosome accounting", fontWeight = FontWeight.Bold)
                    Text("Chromosome sets: ${stage.chromosomeSetsPerCell}")
                    Text("Chromosomes in the cell: ${stage.chromosomeCountPerCell}")
                    Text("Chromatids in the cell: ${stage.chromatidCountPerCell}")
                    if (stage.daughterCellsAtEnd > 0) Text("Cells produced at this division: ${stage.daughterCellsAtEnd}")
                }
            }
            Text("Key event: ${stage.keyEvent}")
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = { stageIndex = (stageIndex - 1).coerceAtLeast(0) },
                    enabled = stageIndex > 0,
                    modifier = Modifier.weight(1f)
                ) { Text("Previous") }
                Button(
                    onClick = { stageIndex = (stageIndex + 1).coerceAtMost(lesson.stages.lastIndex) },
                    enabled = stageIndex < lesson.stages.lastIndex,
                    modifier = Modifier.weight(1f)
                ) { Text("Next stage") }
            }
            if (stageIndex == lesson.stages.lastIndex) {
                Text(
                    "Outcome: ${lesson.finalDaughterCellCount} daughter cells · ${lesson.finalChromosomeNumberPerDaughter} chromosomes per daughter · ${lesson.finalPloidy}",
                    fontWeight = FontWeight.Bold
                )
                Text(lesson.summary)
                OutlinedButton(onClick = { stageIndex = 0 }, modifier = Modifier.fillMaxWidth()) { Text("Restart model") }
            }
            Text(
                "Simplified schematic model: real chromosome numbers vary by species. The model uses a default diploid number of 4 for clear counting.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
