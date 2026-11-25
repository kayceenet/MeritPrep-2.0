package com.kaycee.meritprep
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.pow

internal val chemistrySupplementalActivityTopicIds = setOf(
    "chem-atomic-bonding", "chem-nuclear", "chem-solubility",
    "chem-organic", "chem-industry", "chem-pollution", "chem-astro"
)

@Composable
fun ChemistrySupplementalLessonActivity(topicId: String) {
    when(topicId) {
        "chem-atomic-bonding" -> IonicFormulaExplorer()
        "chem-nuclear" -> HalfLifeExplorer()
        "chem-solubility" -> SolubilityExplorer()
        "chem-organic" -> HydrocarbonFormulaExplorer()
        "chem-industry" -> HaberConditionExplorer()
        "chem-pollution" -> PollutionExplorer()
        "chem-astro" -> SpectraExplorer()
    }
}

@Composable private fun IonicFormulaExplorer() {
    var p by remember { mutableIntStateOf(1) }
    var n by remember { mutableIntStateOf(1) }
    val ratio = when(p to n) { 1 to 1 -> "MX"; 1 to 2 -> "M₂X"; 1 to 3 -> "M₃X"; 2 to 1 -> "MX₂"; 2 to 2 -> "MX"; 2 to 3 -> "M₃X₂"; 3 to 1 -> "MX₃"; 3 to 2 -> "M₂X₃"; else -> "MX" }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Ionic bonding: formula builder", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        Text("Choose the charge magnitudes. The simplest formula unit has zero total charge.")
        Text("Positive ion charge: +$p"); Slider(p.toFloat(), { p=it.toInt() }, valueRange=1f..3f, steps=1)
        Text("Negative ion charge: −$n"); Slider(n.toFloat(), { n=it.toInt() }, valueRange=1f..3f, steps=1)
        Text("Formula ratio: $ratio", style=MaterialTheme.typography.headlineMedium, color=MaterialTheme.colorScheme.primary)
        Text("The formula represents the simplest whole-number ion ratio, not a discrete molecule. This model does not show electron configurations or lattice energetics.")
    } }
}

@Composable private fun HalfLifeExplorer() {
    var tHalf by remember { mutableFloatStateOf(4f) }
    var time by remember { mutableFloatStateOf(8f) }
    val fraction=100.0*0.5.pow((time/tHalf).toDouble())
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Radioactive decay and half-life", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        Text("Expected percentage of parent nuclei remaining = 100(1/2)^(t/T½).")
        Text("Half-life: " + "%.1f".format(tHalf)); Slider(tHalf,{tHalf=it},valueRange=1f..20f)
        Text("Elapsed time: " + "%.1f".format(time)); Slider(time,{time=it},valueRange=0f..60f)
        Text("Expected parent nuclei remaining: " + "%.2f".format(fraction) + "%", style=MaterialTheme.typography.headlineSmall)
        Text("Individual decay events are random; this curve describes the statistical behaviour of a large sample. Detector efficiency and daughter decay are not modelled.")
    } }
}

@Composable private fun SolubilityExplorer() {
    var temp by remember { mutableFloatStateOf(20f) }
    var added by remember { mutableFloatStateOf(30f) }
    val limit=15f+0.75f*temp
    val excess=(added-limit).coerceAtLeast(0f)
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Solubility and saturation", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        Text("This is an explicitly hypothetical solid with a rising solubility curve; values are g solute per 100 g water.")
        Text("Temperature: " + temp.toInt() + " °C"); Slider(temp,{temp=it},valueRange=0f..80f)
        Text("Solute added: " + added.toInt() + " g"); Slider(added,{added=it},valueRange=0f..100f)
        Text("Model solubility limit: " + "%.1f".format(limit))
        Text("Undissolved excess: " + "%.1f".format(excess) + " g")
        Text(if(excess>0f) "Saturated: excess solid remains." else "Unsaturated under this model.")
        Text("Do not treat this illustrative curve as data for a real substance. Actual solubility curves differ and must be sourced or measured.")
    } }
}

@Composable private fun HydrocarbonFormulaExplorer() {
    var family by remember { mutableStateOf("Alkane") }
    var count by remember { mutableIntStateOf(3) }
    val h=when(family) { "Alkane" -> 2*count+2; "Alkene" -> 2*count; else -> 2*count-2 }
    val valid=family=="Alkane" || count>=2
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Hydrocarbon homologous series", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        Row(horizontalArrangement=Arrangement.spacedBy(5.dp)) { listOf("Alkane","Alkene","Alkyne").forEach { FilterChip(family==it,{family=it},{Text(it)}) } }
        Text("Carbon atoms: $count"); Slider(count.toFloat(),{count=it.toInt()},valueRange=1f..10f,steps=8)
        Text("Formula: " + if(valid) "C" + count + "H" + h else "No member in this acyclic series for one carbon", style=MaterialTheme.typography.headlineSmall)
        Text("General formula: " + when(family) { "Alkane" -> "CₙH₂ₙ₊₂"; "Alkene" -> "CₙH₂ₙ"; else -> "CₙH₂ₙ₋₂" })
        Text("These formulae apply to the specified acyclic homologous series; rings and additional multiple bonds require different treatment.")
    } }
}

@Composable private fun HaberConditionExplorer() {
    var pressure by remember { mutableFloatStateOf(150f) }
    var temp by remember { mutableFloatStateOf(450f) }
    var catalyst by remember { mutableStateOf(true) }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Haber process: condition investigation", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        Text("N₂(g) + 3H₂(g) ⇌ 2NH₃(g); forward reaction is exothermic.")
        Text("Pressure: " + pressure.toInt() + " atm"); Slider(pressure,{pressure=it},valueRange=50f..300f)
        Text("Temperature: " + temp.toInt() + " °C"); Slider(temp,{temp=it},valueRange=300f..600f)
        Row { FilterChip(catalyst,{catalyst=true},{Text("Catalyst")}); FilterChip(!catalyst,{catalyst=false},{Text("No catalyst")}) }
        Text(if(pressure>150f) "Higher pressure favours ammonia: fewer gas molecules on product side." else "Lower pressure favours the side with more gas molecules; ammonia is less favoured.")
        Text(if(temp<450f) "Lower temperature favours the exothermic forward reaction, but slows kinetics." else "Higher temperature speeds kinetics but shifts equilibrium away from exothermic ammonia formation.")
        Text(if(catalyst) "Catalyst speeds forward and reverse reactions; equilibrium position is unchanged." else "Without catalyst, equilibrium is approached more slowly.")
        Text("Qualitative Le Chatelier model only; no numerical yield or actual industrial rate is calculated.")
    } }
}

@Composable private fun PollutionExplorer() {
    var item by remember { mutableStateOf("Nitrate runoff") }
    val effect=when(item) { "Nitrate runoff" -> "Nutrient enrichment can promote eutrophication."; "Sulfur dioxide" -> "Can contribute to acid deposition and respiratory irritation."; "Oil discharge" -> "Can coat organisms and interfere with gas exchange."; else -> "Persistent litter and microplastics can affect organisms and habitats." }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Environmental pollution: cause and effect", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        listOf("Nitrate runoff","Sulfur dioxide","Oil discharge","Plastic waste").forEach { FilterChip(item==it,{item=it},{Text(it)}) }
        Text("Potential environmental effect",fontWeight=FontWeight.Bold); Text(effect)
        Text("Investigation: identify a plausible source, propose a sampling plan, choose suitable chemical measurements and recommend a control strategy.")
        Text("This is a reasoning model, not a site-specific causal conclusion. Real assessment requires sampling and analysis.")
    } }
}

@Composable private fun SpectraExplorer() {
    var element by remember { mutableStateOf("Hydrogen") }
    val lines=when(element) { "Hydrogen" -> "Visible Balmer lines include approximately 656, 486, 434 and 410 nm."; "Sodium" -> "A prominent yellow emission doublet occurs near 589 nm."; else -> "Helium has characteristic visible lines including yellow and blue-green features." }
    Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Text("Astronomical chemistry: spectral fingerprints", style=MaterialTheme.typography.titleLarge, fontWeight=FontWeight.Bold)
        listOf("Hydrogen","Sodium","Helium").forEach { FilterChip(element==it,{element=it},{Text(it)}) }
        Text("Reference element: $element",fontWeight=FontWeight.Bold); Text(lines)
        Text("Matching spectral line positions provide evidence for an element in emitting or absorbing material.")
        Text("Real stellar spectra also involve temperature, pressure, ionisation, Doppler shifts, line broadening and overlapping lines. This is a simplified identification activity.")
    } }
}
