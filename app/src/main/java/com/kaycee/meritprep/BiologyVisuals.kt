package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@Composable
fun PlantTransportCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Transport in plants", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Water and mineral ions mainly travel in xylem. Sugars and other organic solutes move through phloem by translocation.")
            Text("Water pathway", fontWeight = FontWeight.SemiBold)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FlowLabel("Soil", "Water", MaterialTheme.colorScheme.tertiaryContainer)
                Text("→", fontWeight = FontWeight.Bold)
                FlowLabel("Root hair", "Absorption", MaterialTheme.colorScheme.surfaceVariant)
                Text("→", fontWeight = FontWeight.Bold)
                FlowLabel("Xylem", "Water + ions", MaterialTheme.colorScheme.secondaryContainer)
                Text("→", fontWeight = FontWeight.Bold)
                FlowLabel("Leaf", "Transpiration", MaterialTheme.colorScheme.tertiaryContainer)
            }
            Text("Food pathway", fontWeight = FontWeight.SemiBold)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FlowLabel("Mature leaf", "Source", MaterialTheme.colorScheme.tertiaryContainer)
                Text("⇄", fontWeight = FontWeight.Bold)
                FlowLabel("Phloem", "Translocation", MaterialTheme.colorScheme.secondaryContainer)
                Text("⇄", fontWeight = FontWeight.Bold)
                FlowLabel("Root / fruit", "Sink", MaterialTheme.colorScheme.surfaceVariant)
            }
            Text("Xylem transport is mainly upward from roots. Phloem moves solutes from sources to sinks; movement can occur in different directions in different sieve tubes.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun NephronFlowCard() {
    val stages = listOf(
        "Glomerulus and Bowman's capsule" to "Pressure-driven ultrafiltration moves water and small dissolved substances into the filtrate. Blood cells and most large plasma proteins remain in the blood.",
        "Proximal convoluted tubule" to "Selective reabsorption returns all glucose in a healthy kidney, amino acids, much of the water and many useful ions to the blood.",
        "Loop of Henle" to "The loop helps establish a concentration gradient in the kidney medulla, supporting the production of concentrated urine.",
        "Distal convoluted tubule" to "Further selective reabsorption and secretion help regulate salts, pH and the composition of the filtrate.",
        "Collecting duct" to "Water reabsorption is adjusted partly by antidiuretic hormone (ADH). The remaining fluid passes towards the renal pelvis as urine."
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("A simplified nephron pathway", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Follow the filtrate from the blood filtration step to the collecting duct.")
            stages.forEachIndexed { index, stage ->
                Surface(
                    color = if (index == 0 || index == stages.lastIndex) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(Modifier.fillMaxWidth().padding(11.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("${index + 1}. ${stage.first}", fontWeight = FontWeight.SemiBold)
                        Text(stage.second, style = MaterialTheme.typography.bodySmall)
                    }
                }
                if (index < stages.lastIndex) Text("↓", Modifier.padding(start = 12.dp), fontWeight = FontWeight.Bold)
            }
            Text("The nephron diagram is simplified. Real kidney function also depends on blood flow, hormones and exchange between tubules and surrounding capillaries.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun FlowerReproductionCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Sexual reproduction in a flowering plant", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Pollination and fertilisation are different events. Pollination transfers pollen from anther to stigma; fertilisation is the fusion of gamete nuclei inside the ovule.")

            val route = listOf(
                "1. Pollination" to "A pollen grain is transferred from an anther to a compatible stigma.",
                "2. Pollen tube growth" to "The pollen grain germinates. A pollen tube grows down through the style towards an ovule in the ovary.",
                "3. Two male gametes" to "The pollen tube carries two male gametes into the embryo sac of the ovule.",
                "4. First fusion" to "One male gamete fuses with the egg cell to form a diploid zygote (2n), which develops into the embryo.",
                "5. Second fusion" to "The other male gamete fuses with the two polar nuclei (usually treated as a single diploid secondary nucleus, 2n) to form a triploid primary endosperm nucleus (3n). The endosperm nourishes the developing embryo."
            )
            route.forEachIndexed { index, step ->
                Surface(
                    color = if (index == 3 || index == 4) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(Modifier.fillMaxWidth().padding(11.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(step.first, fontWeight = FontWeight.Bold)
                        Text(step.second, style = MaterialTheme.typography.bodySmall)
                    }
                }
                if (index < route.lastIndex) Text("↓", Modifier.padding(start = 12.dp), fontWeight = FontWeight.Bold)
            }

            Text("What develops after fertilisation?", fontWeight = FontWeight.Bold)
            Text("The ovule develops into a seed. Its integuments form the seed coat. The ovary usually develops into a fruit. The zygote forms the embryo, while the endosperm provides nourishment.")
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Text(
                    "EXAM CHECK: Double fertilisation means two fusion events occur in the same embryo sac: male gamete + egg (2n zygote), and male gamete + two polar nuclei (usually 3n endosperm nucleus).",
                    Modifier.fillMaxWidth().padding(12.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun NaturalSelectionCard() {
    val stages = listOf(
        "Variation" to "Individuals in a population differ. Some differences are inherited.",
        "Selection pressure" to "A factor such as predation, disease or limited food affects survival or reproduction.",
        "Differential reproduction" to "Individuals with advantageous inherited traits may leave more offspring in that environment.",
        "Population change" to "Over generations, the associated inherited traits can become more common."
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Natural selection over generations", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            stages.forEachIndexed { index, stage ->
                Surface(color = if (index == stages.lastIndex) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium) {
                    Column(Modifier.fillMaxWidth().padding(11.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text("${index + 1}. ${stage.first}", fontWeight = FontWeight.SemiBold)
                        Text(stage.second, style = MaterialTheme.typography.bodySmall)
                    }
                }
                if (index < stages.lastIndex) Text("↓", Modifier.padding(start = 12.dp), fontWeight = FontWeight.Bold)
            }
            Text("Natural selection does not occur because organisms decide to change. Populations evolve as inherited variants contribute differently to later generations.", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
fun RespirationComparisonCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Cellular respiration: with and without oxygen", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Respiration is a set of chemical reactions inside cells that transfers energy from glucose into ATP. It is not the same as breathing or simply taking in oxygen.")

            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("AEROBIC RESPIRATION", fontWeight = FontWeight.Bold)
                    Text("Glucose + oxygen → carbon dioxide + water + energy")
                    Text("Most stages occur in mitochondria; glycolysis begins in the cytoplasm.", style = MaterialTheme.typography.bodySmall)
                    Text("It releases much more energy per glucose molecule than the anaerobic pathways described below.", style = MaterialTheme.typography.bodySmall)
                }
            }

            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("ANAEROBIC PATHWAY IN HUMAN MUSCLE", fontWeight = FontWeight.Bold)
                    Text("Glucose → lactate + a small amount of energy")
                    Text("It occurs in the cytoplasm and can help supply ATP when oxygen delivery cannot meet demand.", style = MaterialTheme.typography.bodySmall)
                }
            }

            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("ALCOHOLIC FERMENTATION IN YEAST", fontWeight = FontWeight.Bold)
                    Text("Glucose → ethanol + carbon dioxide + a small amount of energy")
                    Text("Yeast uses this pathway when oxygen is unavailable or limited.", style = MaterialTheme.typography.bodySmall)
                }
            }

            Text("Remember", fontWeight = FontWeight.Bold)
            Text("Breathing moves air into and out of the lungs. Gas exchange moves oxygen and carbon dioxide across a respiratory surface. Cellular respiration is the chemical process in cells that transfers energy to ATP.")
        }
    }
}


@Composable
fun BiologyPopulationSamplingCard() {
    var countsText by remember { mutableStateOf("4, 6, 5, 7, 3") }
    var quadratAreaText by remember { mutableStateOf("1") }
    var habitatAreaText by remember { mutableStateOf("100") }

    val counts = countsText.split(",").map { it.trim().toIntOrNull() }
    val quadratArea = quadratAreaText.toDoubleOrNull()
    val habitatArea = habitatAreaText.toDoubleOrNull()
    val estimate = if (counts.isNotEmpty() && counts.all { it != null } &&
        quadratArea != null && habitatArea != null
    ) {
        estimatePopulationFromQuadrats(counts.filterNotNull(), quadratArea, habitatArea)
    } else null

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Estimate a population using quadrats", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("A quadrat is a frame used to sample organisms in a defined area. Place it randomly at several locations, count the organisms inside each frame, then use the mean density to estimate the population in the whole habitat.")
            OutlinedTextField(
                value = countsText,
                onValueChange = { countsText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Counts in each quadrat (comma-separated)") },
                supportingText = { Text("Example: 4, 6, 5, 7, 3") },
                singleLine = true
            )
            OutlinedTextField(
                value = quadratAreaText,
                onValueChange = { quadratAreaText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Area of one quadrat (m²)") },
                singleLine = true
            )
            OutlinedTextField(
                value = habitatAreaText,
                onValueChange = { habitatAreaText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Total habitat area (m²)") },
                singleLine = true
            )
            if (estimate != null) {
                Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                    Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text("ESTIMATED POPULATION", fontWeight = FontWeight.Bold)
                        Text("${"%.1f".format(java.util.Locale.UK, estimate.estimatedPopulation)} organisms", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text("Total counted: ${estimate.totalCount} in ${estimate.quadratCount} quadrats")
                        Text("Mean per quadrat: ${"%.2f".format(java.util.Locale.UK, estimate.meanPerQuadrat)}")
                        Text("Estimated density: ${"%.2f".format(java.util.Locale.UK, estimate.densityPerM2)} organisms/m²")
                        Text("Sampled area: ${"%.2f".format(java.util.Locale.UK, estimate.sampledAreaM2)} m²")
                    }
                }
                Text("Worked example", fontWeight = FontWeight.Bold)
                Text("1. Add the counts: 4 + 6 + 5 + 7 + 3 = 25 organisms.")
                Text("2. Calculate sampled area: 5 quadrats × 1 m² = 5 m².")
                Text("3. Calculate density: 25 ÷ 5 = 5 organisms/m².")
                Text("4. Estimate the habitat population: 5 × 100 = 500 organisms.")
                Text("Formula: estimated population = (total organisms counted ÷ total sampled area) × habitat area.", fontWeight = FontWeight.SemiBold)
            } else {
                Text("Enter whole-number counts of zero or more, and positive area values. Separate each quadrat count with a comma. The combined quadrat area must not exceed the habitat area.", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }
            Text("Sampling quality matters: use quadrats of equal known area, place them randomly or with a clearly described unbiased sampling plan, and sample enough locations to represent the habitat. Record the organism and sampling conditions. This method is unsuitable for organisms that move rapidly in and out of the area. The result is an estimate, not an exact census.", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
fun EcologicalSuccessionCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Ecological succession: how communities change", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Succession is a gradual change in the species composition and structure of an ecological community over time.")

            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("PRIMARY SUCCESSION", fontWeight = FontWeight.Bold)
                    Text("Bare rock or a newly exposed surface")
                    Text("↓ Pioneer organisms such as lichens and mosses")
                    Text("↓ Weathering and organic matter help soil develop")
                    Text("↓ Grasses, herbs, shrubs and later trees may establish")
                    Text("It begins where developed soil is absent, so soil formation is a major early process.")
                }
            }

            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("SECONDARY SUCCESSION", fontWeight = FontWeight.Bold)
                    Text("A disturbed area where soil remains")
                    Text("↓ Grasses and other fast-colonising plants")
                    Text("↓ Shrubs and young trees may establish")
                    Text("↓ A more complex community may develop")
                    Text("It often proceeds faster because soil, nutrients, seeds or roots may remain after the disturbance.")
                }
            }

            Text("Key comparison", fontWeight = FontWeight.Bold)
            Text("The presence or absence of developed soil is the main distinction. The sequence and final community depend on climate, soil, disturbance, seed sources and other local conditions; succession does not always follow one fixed path.")
        }
    }
}


@Composable
fun AdaptationTypesCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Adaptations: structure, function and behaviour", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("An adaptation is an inherited feature that can improve survival or reproductive success in a particular environment. Adaptations occur in populations over generations.")

            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("STRUCTURAL (BODY FORM)", fontWeight = FontWeight.Bold)
                    Text("A physical feature of an organism.")
                    Text("Examples: a cactus has leaves reduced to spines and a waxy surface that help limit water loss; mangroves may have specialised roots that aid support or gas exchange in waterlogged soils.")
                }
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("PHYSIOLOGICAL (INTERNAL FUNCTION)", fontWeight = FontWeight.Bold)
                    Text("A process inside the organism.")
                    Text("Examples: some desert animals conserve water by producing concentrated urine; some mangroves regulate salt uptake or remove excess salt.")
                }
            }
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("BEHAVIOURAL (WAY OF ACTING)", fontWeight = FontWeight.Bold)
                    Text("A pattern of activity that can reduce risk or improve access to resources.")
                    Text("Examples: some desert animals are active at night to avoid the hottest part of the day; some birds migrate when food or climate conditions change.")
                }
            }

            Text("Do not confuse adaptation with acclimatisation", fontWeight = FontWeight.Bold)
            Text("Adaptation is an inherited population-level feature shaped over generations. Acclimatisation is an adjustment made by an individual during its lifetime and is usually reversible; it does not, by itself, change the genes passed to offspring.")
        }
    }
}
