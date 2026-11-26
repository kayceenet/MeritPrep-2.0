package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun BiologyClassificationVisual() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Taxonomic hierarchy", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Broad groups contain many organisms. As you move down the hierarchy, each group becomes more specific.")
                val ranks = listOf("KINGDOM", "PHYLUM / DIVISION", "CLASS", "ORDER", "FAMILY", "GENUS", "SPECIES")
                ranks.forEachIndexed { index, rank ->
                    Card {
                        Column(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 7.dp)) {
                            Text(rank, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Text(
                                when (index) {
                                    0 -> "Broadest of these ranks"
                                    6 -> "Most specific of these ranks"
                                    else -> "More specific than the rank above"
                                },
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    if (index != ranks.lastIndex) Text("↓", Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text("Plant classifications commonly use Division where animal classifications use Phylum.", style = MaterialTheme.typography.bodySmall)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Plant groups: three diagnostic questions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                ClassificationVisualRow("Vascular tissue absent", "Bryophytes · mosses", "Reproduce by spores; no true xylem or phloem")
                ClassificationVisualRow("Vascular tissue present; seeds absent", "Pteridophytes · ferns", "True roots, stems and leaves; reproduce by spores")
                ClassificationVisualRow("Seeds present; no flowers or fruits", "Gymnosperms · conifers", "Seeds are not enclosed within an ovary-derived fruit")
                ClassificationVisualRow("Flowers and fruits present", "Angiosperms · flowering plants", "Seeds develop inside fruits")
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Major invertebrate phyla", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                ClassificationVisualRow("Porous body; canal system; no true tissues", "Porifera", "Sponges")
                ClassificationVisualRow("Stinging cells; tentacles; often radial symmetry", "Cnidaria", "Hydra, jellyfish and corals")
                ClassificationVisualRow("Dorsoventrally flattened body", "Platyhelminthes", "Flatworms")
                ClassificationVisualRow("Cylindrical, unsegmented body", "Nematoda", "Roundworms")
                ClassificationVisualRow("Segmented body", "Annelida", "Earthworms and leeches")
                ClassificationVisualRow("Jointed appendages; chitinous exoskeleton", "Arthropoda", "Insects, arachnids and crustaceans")
                ClassificationVisualRow("Muscular foot and mantle", "Mollusca", "Snails, clams and octopuses")
                ClassificationVisualRow("Water-vascular system; tube feet", "Echinodermata", "Starfish and sea urchins")
                Text("Use combinations of features. A single characteristic may not be sufficient for every specimen.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Vertebrate classes: use diagnostic evidence", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                ClassificationVisualRow("Fins and gills in adult aquatic life", "Fish", "For example, tilapia")
                ClassificationVisualRow("Moist skin; life cycle linked to water", "Amphibians", "For example, frogs")
                ClassificationVisualRow("Dry keratinised scales", "Reptiles", "For example, lizards")
                ClassificationVisualRow("Feathers", "Birds", "For example, pigeons")
                ClassificationVisualRow("Hair and mammary glands", "Mammals", "For example, bats and whales")
                Text("These are useful diagnostic patterns, not a complete description of every species.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("The five-kingdom teaching model", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Monera · prokaryotic organisms such as bacteria")
                Text("Protista · diverse, mainly unicellular eukaryotes such as Amoeba")
                Text("Fungi · absorptive nutrition; cell walls commonly contain chitin")
                Text("Plantae · multicellular photosynthetic organisms with cellulose walls")
                Text("Animalia · multicellular consumers without cell walls")
                Text("Viruses are acellular and are not placed in these five kingdoms. Modern classification also uses domain-level groupings and can be revised when evidence improves.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun ClassificationVisualRow(feature: String, group: String, explanation: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(feature, fontWeight = FontWeight.SemiBold)
            Text(group, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(explanation, style = MaterialTheme.typography.bodySmall)
        }
    }
}
