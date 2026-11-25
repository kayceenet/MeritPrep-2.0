package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal object WaecChemistryActivityRoutes {
    val supportedTopicIds = setOf("chem-organic", "chem-separation", "chem-acids-bases", "chem-atomic-bonding")
}

fun WaecChemistrySupplementalActivity(topicId: String) {
    if (topicId !in WaecChemistryActivityRoutes.supportedTopicIds) return
    when (topicId) {
        "chem-organic" -> WaecOrganicTestsAndBiochemistryActivity()
        "chem-separation" -> WaecSeparationPracticalActivity()
        "chem-acids-bases" -> WaecStandardSolutionActivity()
        "chem-atomic-bonding" -> WaecPeriodicTrendsActivity()
    }
}

@Composable
private fun WaecOrganicTestsAndBiochemistryActivity() {
    var target by remember { mutableStateOf("Alkene") }
    val test = when (target) {
        "Alkene" -> "Add bromine water: the orange/brown colour is decolourised by an alkene under suitable conditions."
        "Alkanol" -> "A primary/secondary alkanol can be oxidised by acidified dichromate(VI): orange changes to green. Conditions and product depend on the alkanol."
        "Alkanoic acid" -> "Add aqueous carbonate or hydrogencarbonate: effervescence occurs as carbon dioxide is released."
        "Reducing sugar" -> "Warm with Benedict's solution: blue may change through green/yellow/orange to a brick-red precipitate depending on concentration."
        "Starch" -> "Add iodine solution: a blue-black colour indicates starch."
        "Protein" -> "Biuret test: in alkaline solution, copper(II) ions produce a violet/purple colour with peptide bonds."
        else -> "Fats and oils can be tested with the ethanol emulsion test: a milky emulsion forms after adding water to an ethanolic extract."
    }
    var polymer by remember { mutableStateOf("Addition polymer") }
    val polymerDetail = if (polymer == "Addition polymer") {
        "Addition polymerisation joins unsaturated monomers, commonly alkenes, without eliminating a small molecule. Poly(ethene) is a standard example."
    } else {
        "Condensation polymerisation joins monomers with two functional groups and eliminates a small molecule such as water or HCl. Polyesters and polyamides are examples."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC focus: organic functional-group tests", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Alkene", "Alkanol", "Alkanoic acid", "Reducing sugar", "Starch", "Protein", "Fat or oil").forEach {
                FilterChip(target == it, { target = it }, { Text(it) })
            }
            HorizontalDivider()
            Text("Test and expected observation", fontWeight = FontWeight.Bold)
            Text(test)
            Text("Biochemistry and synthetic polymers", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            listOf("Addition polymer", "Condensation polymer").forEach {
                FilterChip(polymer == it, { polymer = it }, { Text(it) })
            }
            Text(polymerDetail)
            Text("Safety: these are simulated observations. Some reagents are corrosive, toxic or require heating. Do not carry out tests without the approved school procedure and teacher supervision.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
private fun WaecSeparationPracticalActivity() {
    var mixture by remember { mutableStateOf("Insoluble solid and liquid") }
    val method = when (mixture) {
        "Insoluble solid and liquid" -> "Filtration. The insoluble solid remains as residue; the liquid passes through as filtrate."
        "Soluble solid from solution" -> "Crystallisation/evaporation. Concentrate the solution and allow crystals to form; avoid overheating substances that decompose."
        "Two miscible liquids" -> "Fractional distillation when boiling points are sufficiently different; a fractionating column supports repeated vaporisation/condensation."
        "Dissolved coloured components" -> "Paper chromatography. Components partition differently between stationary paper and mobile solvent; calculate Rf."
        else -> "Sublimation. A sublimable solid vaporises on heating and deposits on a cool surface, leaving non-sublimable material behind."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC focus: choose a separation technique", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Insoluble solid and liquid", "Soluble solid from solution", "Two miscible liquids", "Dissolved coloured components", "Sublimable and non-sublimable solids").forEach {
                FilterChip(mixture == it, { mixture = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(method)
            Text("For a quantitative practical, record masses/volumes and calculate percentage recovery or Rf where appropriate. Purity can be assessed using a characteristic melting or boiling point.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecStandardSolutionActivity() {
    var stock by remember { mutableFloatStateOf(1.0f) }
    var target by remember { mutableFloatStateOf(0.1f) }
    var finalVolume by remember { mutableFloatStateOf(250f) }
    val stockVolume = target * finalVolume / stock
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC focus: preparing a standard solution by dilution", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Stock concentration: " + "%.2f".format(stock) + " mol dm⁻³")
            Slider(stock, { stock = it.coerceAtLeast(target) }, valueRange = 0.1f..2f)
            Text("Target concentration: " + "%.2f".format(target) + " mol dm⁻³")
            Slider(target, { target = it.coerceAtMost(stock) }, valueRange = 0.05f..1f)
            Text("Final volume: " + "%.0f".format(finalVolume) + " cm³")
            Slider(finalVolume, { finalVolume = it }, valueRange = 50f..500f)
            HorizontalDivider()
            Text("Stock volume required: " + "%.2f".format(stockVolume) + " cm³", style = MaterialTheme.typography.headlineSmall)
            Text("Use C₁V₁ = C₂V₂. Measure the calculated stock volume with a suitable pipette, transfer to a volumetric flask and make up to the calibration mark with solvent.")
            Text("The stock concentration must be at least the target concentration. This is a calculation aid; laboratory preparation requires correct glassware, meniscus reading and safe handling.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecPeriodicTrendsActivity() {
    var element by remember { mutableStateOf("Sodium") }
    val detail = when (element) {
        "Sodium" -> "Period 3 metal: loses one outer electron readily, forms Na⁺ and a basic oxide/hydroxide."
        "Magnesium" -> "Period 3 metal: forms Mg²⁺; its oxide is basic and the metal reacts with dilute acids."
        "Aluminium" -> "Forms Al³⁺; aluminium oxide/hydroxide is amphoteric and reacts with both acids and strong bases."
        "Silicon" -> "Metalloid with a giant covalent structure; silicon dioxide is a giant covalent oxide."
        "Phosphorus" -> "Non-metal; forms covalent compounds and acidic oxides. Common allotropes include white and red phosphorus."
        "Sulfur" -> "Non-metal with allotropes; forms acidic oxides and sulfuric acid."
        else -> "Non-metal gas; exists as diatomic molecules and forms acidic oxides in relevant reactions."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC focus: periodic chemistry across Period 3", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine").forEach {
                FilterChip(element == it, { element = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("Across Period 3, metallic character generally decreases and oxides change from strongly basic through amphoteric to acidic. Explain trends using nuclear charge, shielding and valence-electron structure; do not treat every property as a perfectly smooth trend.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
