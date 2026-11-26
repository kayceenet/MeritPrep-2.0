package com.kaycee.meritprep

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

/** Guided, offline-capable interactive activities embedded in relevant lessons. */
@Composable
fun BiologyInteractiveLessonActivity(topicId: String) {
    when (topicId) {
        "bio-cell" -> CellOrganisationExplorerActivity()
        "bio-transport" -> Column(verticalArrangement = Arrangement.spacedBy(12.dp)) { DiffusionAnimatedLessonActivity(); OsmosisInquiryActivity() }
        "bio-nutrition" -> Column(verticalArrangement = Arrangement.spacedBy(12.dp)) { PhotosynthesisInquiryActivity(); EnzymeInquiryActivity() }
        "bio-growth" -> CellDivisionInquiryActivity()
        "bio-heredity" -> MendelianCrossInquiryActivity()
        "bio-evolution-theory" -> NaturalSelectionInquiryActivity()
        "bio-populations" -> Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BiologyPopulationSamplingCard()
            EcologicalSuccessionCard()
        }
    }
}

@Composable
private fun DiffusionAnimatedLessonActivity() {
    var left by remember { mutableFloatStateOf(1.0f) }
    var right by remember { mutableFloatStateOf(0.2f) }
    var permeability by remember { mutableFloatStateOf(80f) }
    val result = remember(left, right, permeability) {
        DiffusionSimulation.evaluate(DiffusionConditions(left.toDouble(), right.toDouble(), permeability.toDouble()))
    }
    val transition = rememberInfiniteTransition(label = "diffusion-particles")
    val phase by transition.animateFloat(0f, 1f, infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Restart), label = "particle-motion")
    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Interactive concept animation: diffusion", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change the concentration on each side and the membrane permeability. Watch the particle model and compare random movement with net diffusion.")
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                val boundary = size.width / 2f
                val top = size.height * 0.12f
                val bottom = size.height * 0.88f
                drawLine(Color(0xFF607D8B), Offset(boundary, top), Offset(boundary, bottom), strokeWidth = 7f)
                val leftCount = (left * 12).toInt().coerceIn(1, 12)
                val rightCount = (right * 12).toInt().coerceIn(1, 12)
                repeat(leftCount) { i ->
                    val y = top + (bottom - top) * ((i + 0.5f) / leftCount)
                    val crosses = left > right && permeability > 0f
                    val p = (phase + i * 0.13f) % 1f
                    val x = if (crosses) size.width * (0.08f + 0.84f * p) else size.width * (0.12f + 0.28f * ((i % 4) / 3f)) + sin((phase + i) * (2f * PI).toFloat()) * 5f
                    drawCircle(Color(0xFF1976D2), 6f, Offset(x.coerceIn(8f, size.width - 8f), y))
                }
                repeat(rightCount) { i ->
                    val y = top + (bottom - top) * ((i + 0.5f) / rightCount)
                    val crosses = right > left && permeability > 0f
                    val p = (phase + i * 0.13f) % 1f
                    val x = if (crosses) size.width * (0.92f - 0.84f * p) else size.width * (0.60f + 0.28f * ((i % 4) / 3f)) + sin((phase + i) * (2f * PI).toFloat()) * 5f
                    drawCircle(Color(0xFFEF8C32), 6f, Offset(x.coerceIn(8f, size.width - 8f), y))
                }
            }
            Text("Left concentration: ${"%.1f".format(left)}")
            Slider(value = left, onValueChange = { left = it }, valueRange = 0f..1.5f)
            Text("Right concentration: ${"%.1f".format(right)}")
            Slider(value = right, onValueChange = { right = it }, valueRange = 0f..1.5f)
            Text("Membrane permeability: ${permeability.toInt()}%")
            Slider(value = permeability, onValueChange = { permeability = it }, valueRange = 0f..100f)
            HorizontalDivider()
            Text(
                when (result.direction) {
                    DiffusionDirection.HIGH_TO_LOW -> if (left > right) "Net movement: left → right" else "Net movement: right → left"
                    DiffusionDirection.NO_NET_MOVEMENT -> "No net movement; particles still move randomly in both directions"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text("Concentration gradient: ${"%.2f".format(result.concentrationGradient)}")
            Text("Relative rate index: ${"%.0f".format(result.relativeRateIndexPercent)}%")
            Text(result.explanation)
            Text("Learning check: If both sides reach the same concentration, do particles stop moving? Explain random movement versus net diffusion.", fontWeight = FontWeight.SemiBold)
            Text("Model limit: particle positions are animated for visual learning. The rate index is simplified and is not a measured diffusion rate.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun PhotosynthesisInquiryActivity() {
    var light by remember { mutableFloatStateOf(35f) }
    var carbonDioxide by remember { mutableFloatStateOf(55f) }
    var temperature by remember { mutableFloatStateOf(25f) }
    val result = remember(light, carbonDioxide, temperature) {
        PhotosynthesisSimulation.evaluate(PhotosynthesisConditions(light.toDouble(), carbonDioxide.toDouble(), temperature.toDouble()))
    }
    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Interactive investigation: factors affecting photosynthesis", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one factor at a time. Observe how the limiting factor changes as another factor increases.")
            Text("Light intensity: ${light.toInt()}%")
            Slider(value = light, onValueChange = { light = it }, valueRange = 0f..100f)
            Text("Carbon dioxide availability: ${carbonDioxide.toInt()}%")
            Slider(value = carbonDioxide, onValueChange = { carbonDioxide = it }, valueRange = 0f..100f)
            Text("Temperature: ${temperature.toInt()} °C")
            Slider(value = temperature, onValueChange = { temperature = it }, valueRange = 0f..45f)
            HorizontalDivider()
            Text("Limiting factor: ${result.limitingFactor}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Illustrative relative rate index: ${"%.0f".format(result.relativeRatePercent)}%")
            Text(result.explanation)
            Text("Investigation challenge: increase light intensity while keeping carbon dioxide and temperature fixed. When does light stop being the limiting factor?", fontWeight = FontWeight.SemiBold)
            Text("Model limit: response curves and optimum temperature are simplified teaching assumptions. This percentage is not a measured photosynthesis rate and does not represent every plant species.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable private fun OsmosisInquiryActivity() {
 var outside by remember { mutableFloatStateOf(0.2f) }; var inside by remember { mutableFloatStateOf(0.6f) }; var plant by remember { mutableStateOf(true) }
 val r = remember(outside,inside,plant) { OsmosisSimulation.evaluate(outside.toDouble(),inside.toDouble(),if(plant) OsmosisCellType.PLANT else OsmosisCellType.ANIMAL) }
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)) {
 Text("Virtual investigation: osmosis",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
 Text("Vary external and internal solute concentration. Osmosis is net water movement across a selectively permeable membrane down a water-potential gradient.")
 Row { FilterChip(plant,{plant=true},{Text("Plant cell")}); Spacer(Modifier.width(8.dp)); FilterChip(!plant,{plant=false},{Text("Animal cell")}) }
 Text("External solute: "+ "%.2f".format(outside)); Slider(outside,{outside=it},valueRange=0f..1.5f)
 Text("Internal solute: "+ "%.2f".format(inside)); Slider(inside,{inside=it},valueRange=0f..1.5f)
 Text(r.tonicity,fontWeight=FontWeight.Bold); Text("Water movement: "+r.waterMovement); Text("Cell response: "+r.response); Text(r.explanation)
 Text("Predict the cell response when the external solution becomes more concentrated.",fontWeight=FontWeight.SemiBold)
 Text("Model limit: qualitative direction and response only; no actual cell volume or water-potential calculation.",style=MaterialTheme.typography.bodySmall)
 } }
}
@Composable private fun EnzymeInquiryActivity() {
 var profile by remember { mutableIntStateOf(0) }; var temp by remember { mutableFloatStateOf(37f) }; var ph by remember { mutableFloatStateOf(7f) }
 val enzyme=EnzymeProfile.values()[profile]; val r=remember(enzyme,temp,ph){EnzymeActivitySimulation.evaluate(EnzymeConditions(enzyme,temp.toDouble(),ph.toDouble()))}
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){
 Text("Interactive enzyme investigation",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
 EnzymeProfile.values().forEachIndexed{i,e->FilterChip(profile==i,{profile=i},{Text(e.displayName)})}
 Text("Temperature: "+temp.toInt()+" °C"); Slider(temp,{temp=it},valueRange=0f..80f)
 Text("pH: "+ "%.1f".format(ph)); Slider(ph,{ph=it},valueRange=0f..14f)
 Text("Illustrative activity index: "+ "%.0f".format(r.activityIndexPercent)+"%",fontWeight=FontWeight.Bold)
 Text("Limiting condition: "+r.limitingCondition); Text(r.explanation)
 Text("Change one variable at a time. Why is this necessary for a fair test?",fontWeight=FontWeight.SemiBold)
 Text("Model limit: illustrative response curves, not measured kinetic constants.",style=MaterialTheme.typography.bodySmall)
}}
}
@Composable private fun CellDivisionInquiryActivity() {
 var meiosis by remember { mutableStateOf(false) }; var stageIndex by remember { mutableIntStateOf(0) }
 val type=if(meiosis) CellDivisionType.MEIOSIS else CellDivisionType.MITOSIS
 val lesson=remember(type){CellDivisionSimulation.lesson(type,4)}; val stage=lesson.stages[stageIndex.coerceIn(0,lesson.stages.lastIndex)]
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){
 Text("Step-through cell division model",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
 Row { FilterChip(!meiosis,{meiosis=false;stageIndex=0},{Text("Mitosis")}); Spacer(Modifier.width(8.dp)); FilterChip(meiosis,{meiosis=true;stageIndex=0},{Text("Meiosis")}) }
 LinearProgressIndicator(progress={(stageIndex+1).toFloat()/lesson.stages.size},modifier=Modifier.fillMaxWidth())
 Text("Stage "+(stageIndex+1)+": "+stage.title,fontWeight=FontWeight.Bold); Text(stage.description); Text(stage.keyEvent)
 Text("Chromosomes: "+stage.chromosomeCountPerCell+" | chromatids: "+stage.chromatidCountPerCell); Text("DNA content: "+stage.dnaContentPerCell)
 Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){OutlinedButton(onClick={stageIndex=(stageIndex-1).coerceAtLeast(0)},enabled=stageIndex>0){Text("Previous")}; Button(onClick={stageIndex=(stageIndex+1).coerceAtMost(lesson.stages.lastIndex)},enabled=stageIndex<lesson.stages.lastIndex){Text("Next stage")}}
 Text(lesson.summary); Text("Explain why chromosome number and chromatid number are not interchangeable.",fontWeight=FontWeight.SemiBold)
}}
}
@Composable private fun MendelianCrossInquiryActivity() {
 var a by remember { mutableIntStateOf(1) }; var b by remember { mutableIntStateOf(1) }; val g=MendelianGenotype.values(); val r=remember(a,b){MendelianInheritanceSimulation.evaluate(g[a],g[b])}
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){
 Text("Interactive genetics laboratory: Punnett square",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
 Text("Choose parental genotypes. A is dominant; a is recessive. Assumes one gene, complete dominance and equal gamete probabilities.")
 Text("Parent 1"); Row { g.forEachIndexed{i,v->FilterChip(a==i,{a=i},{Text(v.notation)})} }
 Text("Parent 2"); Row { g.forEachIndexed{i,v->FilterChip(b==i,{b=i},{Text(v.notation)})} }
 Text("Possible offspring: "+r.fourPossibleCombinations.joinToString("  |  "),fontWeight=FontWeight.Bold)
 Text("Dominant phenotype: "+r.dominantPhenotypePercentage+"%"); Text("Recessive phenotype: "+r.recessivePhenotypePercentage+"%"); Text(r.explanation)
 Text("Why is a probability not a guarantee for a particular family?",fontWeight=FontWeight.SemiBold)
}}
}
@Composable private fun NaturalSelectionInquiryActivity() {
 var light by remember { mutableFloatStateOf(50f) }; var dark by remember { mutableFloatStateOf(50f) }; var lightFit by remember { mutableFloatStateOf(45f) }; var darkFit by remember { mutableFloatStateOf(80f) }; var gens by remember { mutableFloatStateOf(5f) }
 val r=remember(light,dark,lightFit,darkFit,gens){NaturalSelectionSimulation.evaluate(NaturalSelectionConditions(light.toInt(),dark.toInt(),lightFit.toDouble(),darkFit.toDouble(),gens.toInt().coerceIn(1,20)))}
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){
 Text("Interactive natural selection investigation",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
 Text("Change starting frequencies and relative survival. Observe how inherited trait frequencies shift over generations.")
 Text("Initial light variant: "+light.toInt()); Slider(light,{light=it},valueRange=1f..100f)
 Text("Initial dark variant: "+dark.toInt()); Slider(dark,{dark=it},valueRange=1f..100f)
 Text("Light variant survival: "+lightFit.toInt()+"%"); Slider(lightFit,{lightFit=it},valueRange=1f..100f)
 Text("Dark variant survival: "+darkFit.toInt()+"%"); Slider(darkFit,{darkFit=it},valueRange=1f..100f)
 Text("Generations: "+gens.toInt()); Slider(gens,{gens=it},valueRange=1f..20f,steps=18)
 r.generations.forEach{Text("Generation "+it.generation+": light "+ "%.1f".format(it.lightTraitFrequencyPercent)+"%; dark "+ "%.1f".format(it.darkTraitFrequencyPercent)+"%")}
 Text("Favoured trait: "+r.moreFavouredTrait,fontWeight=FontWeight.Bold); Text(r.explanation)
 Text("Model omits mutation, migration, genetic drift and changing population size.",style=MaterialTheme.typography.bodySmall)
}}
}


@Composable
private fun CellOrganisationExplorerActivity() {
    var level by remember { mutableStateOf("Cell") }
    var cellType by remember { mutableStateOf("Plant") }
    val levels = listOf("Cell", "Tissue", "Organ", "System", "Organism")
    val description = if (cellType == "Prokaryote" && level != "Cell" && level != "Organism") {
        "A typical bacterial organism is unicellular and does not form true tissues, organs or organ systems."
    } else when (level) {
        "Cell" -> "A cell is the smallest structural and functional unit of a living organism. Examples include a red blood cell, neurone and palisade cell."
        "Tissue" -> "A tissue is a group of similar cells working together for a particular function. Examples include xylem, phloem, muscle and epithelial tissue."
        "Organ" -> "An organ contains different tissues working together. Examples include a leaf, root, heart and kidney."
        "System" -> "An organ system is a group of organs coordinating a major function, such as digestion, transport or excretion."
        else -> "An organism is an individual living thing. It may be unicellular, such as Amoeba, or multicellular, such as a maize plant or human."
    }
    val example = if (cellType == "Prokaryote" && level != "Cell" && level != "Organism") {
        "Return to the cell level: a bacterial cell performs the organism's life processes without specialised multicellular organisation."
    } else when (level) {
        "Cell" -> if (cellType == "Plant") "Palisade cell: chloroplast-rich and specialised for photosynthesis." else if (cellType == "Animal") "Neurone: a specialised cell that transmits electrical impulses." else "Bacterial cell: prokaryotic, with no membrane-bound nucleus."
        "Tissue" -> if (cellType == "Plant") "Xylem tissue transports water and mineral ions and also supports the plant." else "Muscle tissue contracts; epithelial tissue covers and lines body surfaces."
        "Organ" -> if (cellType == "Plant") "A leaf contains epidermal, mesophyll and vascular tissues arranged for photosynthesis and exchange." else "The heart contains cardiac muscle, connective, nervous and epithelial tissues."
        "System" -> if (cellType == "Plant") "The shoot system includes stems, leaves, buds and flowers; the root system anchors and absorbs." else "The human digestive system includes the alimentary canal and accessory organs."
        else -> if (cellType == "Plant") "A maize plant is a multicellular photosynthetic organism." else "A human is a multicellular organism with specialised tissues and organ systems."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Interactive cell structure and levels of organisation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Move from the cell to the whole organism. At each level, identify the structure and explain how it contributes to the next level.")
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("Plant", "Animal", "Prokaryote").forEach {
                    FilterChip(cellType == it, { cellType = it }, { Text(it) })
                }
            }
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                levels.forEach { FilterChip(level == it, { level = it }, { Text(it) }) }
            }
            Text(level, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Text(description)
            Text(example, fontWeight = FontWeight.SemiBold)
            Text("Check the boundary: cells form tissues in multicellular organisms, tissues form organs, organs form systems, and systems contribute to the organism. A unicellular organism carries out all life processes within one cell.", style = MaterialTheme.typography.bodySmall)
            Text("Cell-type distinction: plant and animal cells are eukaryotic; bacterial cells are prokaryotic. A bacterial cell wall is not made of cellulose.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
