package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.exp
import kotlin.math.roundToInt

internal val biologySupplementalActivityTopicIds = setOf(
    "bio-classification", "bio-adaptation", "bio-interactions", "bio-plant-anatomy", "bio-respiration", "bio-excretion", "bio-support",
    "bio-reproduction", "bio-coordination", "bio-ecology-factors",
    "bio-habitats", "bio-biomes", "bio-soil", "bio-environment",
    "bio-variation", "bio-biotechnology", "bio-evolution-evidence"
)

@Composable
fun BiologySupplementalLessonActivity(topicId: String) {
    when (topicId) {
        "bio-classification" -> ClassificationExplorer()
        "bio-adaptation" -> AdaptationExplorer()
        "bio-interactions" -> FoodWebExplorer()
        "bio-plant-anatomy" -> TranspirationExplorer()
        "bio-respiration" -> RespirationExplorer()
        "bio-excretion" -> NephronExplorer()
        "bio-support" -> MovementLeverExplorer()
        "bio-reproduction" -> FlowerReproductionExplorer()
        "bio-coordination" -> ReflexArcExplorer()
        "bio-ecology-factors" -> EcologicalToleranceExplorer()
        "bio-habitats", "bio-biomes" -> HabitatExplorer(topicId)
        "bio-soil" -> SoilExplorer()
        "bio-environment" -> PollutionExplorer()
        "bio-variation" -> VariationExplorer()
        "bio-biotechnology" -> FermentationExplorer()
        "bio-evolution-evidence" -> EvolutionEvidenceExplorer()
    }
}

@Composable
private fun BiologyModelCard(title: String, principle: String, limitation: String, content: @Composable ColumnScope.() -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(principle, style = MaterialTheme.typography.bodyMedium)
            content()
            HorizontalDivider()
            Text("Model boundary: $limitation", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ModelSlider(label: String, value: Float, range: ClosedFloatingPointRange<Float>, unit: String, onChange: (Float) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
            Text("${value.roundToInt()} $unit", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        }
        Slider(value = value, onValueChange = onChange, valueRange = range)
    }
}

@Composable
private fun AdaptationExplorer() {
    var habitat by remember { mutableStateOf("Desert") }
    var type by remember { mutableStateOf("Structural") }
    val examples = mapOf(
        "Desert" to mapOf(
            "Structural" to "A cactus has a reduced leaf surface (spines) and a thick, water-storing stem.",
            "Physiological" to "Some desert plants use CAM photosynthesis, opening stomata mainly at night to reduce water loss.",
            "Behavioural" to "Many desert animals are nocturnal and shelter during the hottest part of the day."
        ),
        "Aquatic" to mapOf(
            "Structural" to "Fish have streamlined bodies and fins; floating aquatic plants may have air spaces.",
            "Physiological" to "Marine organisms regulate internal water and ion balance despite surrounding salinity.",
            "Behavioural" to "Some aquatic animals migrate vertically or seasonally to find food and suitable conditions."
        ),
        "Cold region" to mapOf(
            "Structural" to "Insulating fur, feathers or a layer of fat reduces heat loss; compact body shapes reduce surface area relative to volume.",
            "Physiological" to "Some animals use counter-current heat exchange and seasonal changes in metabolism.",
            "Behavioural" to "Huddling, migration and sheltering can reduce exposure to cold."
        )
    )
    BiologyModelCard("Adaptation and survival explorer", "Compare structural, physiological and behavioural features and connect each one to a survival challenge.", "Examples are generalisations. An adaptation is an inherited feature shaped over generations, not a change an individual chooses because it needs it.") {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Desert", "Aquatic", "Cold region").forEach { item -> FilterChip(habitat == item, { habitat = item }, { Text(item) }) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Structural", "Physiological", "Behavioural").forEach { item -> FilterChip(type == item, { type = item }, { Text(item) }) }
        }
        Text("$habitat · $type adaptation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(examples[habitat]?.get(type).orEmpty())
        Text("Test your explanation: identify the selection pressure, describe the inherited feature and explain how it can affect survival or reproduction.")
    }
}

@Composable
private fun FoodWebExplorer() {
    var producerEnergy by remember { mutableFloatStateOf(1000f) }
    var transfer by remember { mutableFloatStateOf(10f) }
    var levels by remember { mutableFloatStateOf(3f) }
    val remaining = producerEnergy * (transfer / 100f).coerceIn(0f, 1f).let { fraction -> Math.pow(fraction.toDouble(), levels.roundToInt().toDouble()).toFloat() }
    BiologyModelCard("Food-chain energy transfer simulator", "Change the energy captured by producers, the transfer fraction and the number of trophic transfers.", "The often-taught 10% transfer is a rough ecological rule, not a fixed law. Real efficiencies vary among ecosystems and trophic links.") {
        ModelSlider("Producer energy", producerEnergy, 100f..5000f, "kJ") { producerEnergy = it }
        ModelSlider("Energy transfer per level", transfer, 5f..20f, "%") { transfer = it }
        ModelSlider("Number of transfers", levels, 1f..4f, "levels") { levels = it }
        EquationDisplayCard("E_n = E_0 \\times (\\frac{t}{100})^n", caption = "SIMPLIFIED ENERGY-TRANSFER MODEL")
        Text("Energy reaching the selected trophic level: ${remaining.roundToInt()} kJ", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Energy is lost from the transfer pathway through respiration, movement, heat, waste and uneaten or undigested material. Matter is recycled; energy flows through ecosystems.")
    }
}

@Composable
private fun ClassificationExplorer() {
    var specimen by remember { mutableStateOf("Mushroom") }
    var step by remember { mutableIntStateOf(0) }
    val profiles = mapOf(
        "Bacterium" to listOf(false, false, false),
        "Fern" to listOf(true, true, false),
        "Mushroom" to listOf(true, false, true),
        "Grasshopper" to listOf(true, false, false)
    )
    val questions = listOf(
        "Does the organism have a membrane-bound nucleus?",
        "Does it contain chlorophyll and photosynthesise?",
        "Does it obtain nutrients by absorption from organic material?"
    )
    val features = profiles[specimen].orEmpty()
    val outcome = when {
        !features[0] -> "Prokaryote: Bacteria"
        features[1] -> "Photosynthetic eukaryote: Plantae in this example"
        features[2] -> "Fungus"
        else -> "Animal"
    }
    BiologyModelCard("Interactive classification key", "Follow observable or testable characteristics in sequence. Each answer narrows the possible group.", "This is a teaching key for four example organisms, not a complete taxonomic key. Modern classification uses evolutionary relationships and molecular evidence.") {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            profiles.keys.forEach { item -> FilterChip(specimen == item, { specimen = item; step = 0 }, { Text(item) }) }
        }
        Text("Key question ${step + 1} of 3", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(questions[step], style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        val answer = features[step]
        Text("For the selected specimen: ${if (answer) "Yes" else "No"}", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { step = (step - 1).coerceAtLeast(0) }, enabled = step > 0) { Text("Previous") }
            Button(onClick = { step = (step + 1).coerceAtMost(questions.lastIndex) }, enabled = step < questions.lastIndex) { Text("Next characteristic") }
        }
        Text("Classification reached: $outcome", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Explain why the characteristic is useful, then state one limitation of using a single visible feature to classify an organism.")
    }
}

@Composable
private fun TranspirationExplorer() {
    var light by remember { mutableFloatStateOf(60f) }
    var humidity by remember { mutableFloatStateOf(40f) }
    var wind by remember { mutableFloatStateOf(35f) }
    var leafArea by remember { mutableFloatStateOf(60f) }
    val index = (0.15f + light / 100f * .32f + wind / 100f * .25f + leafArea / 100f * .28f) * (1f - humidity / 125f)
    BiologyModelCard("Transpiration control laboratory", "Change environmental conditions and predict their combined effect on water loss from a leafy shoot.", "This is a relative teaching index, not a measured rate or a calibrated potometer model.") {
        ModelSlider("Light intensity", light, 0f..100f, "%") { light = it }
        ModelSlider("Relative humidity", humidity, 0f..100f, "%") { humidity = it }
        ModelSlider("Air movement", wind, 0f..100f, "%") { wind = it }
        ModelSlider("Exposed leaf area", leafArea, 0f..100f, "%") { leafArea = it }
        Text("Relative transpiration tendency", fontWeight = FontWeight.SemiBold)
        LinearProgressIndicator(progress = { index.coerceIn(0f, 1f) }, modifier = Modifier.fillMaxWidth())
        Text(when { index > .65f -> "Higher predicted water-loss tendency"; index > .38f -> "Moderate predicted water-loss tendency"; else -> "Lower predicted water-loss tendency" }, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Explain the effect of each variable separately. High humidity reduces the water-vapour gradient; moving air removes the humid boundary layer around the leaf.")
    }
}

@Composable
private fun RespirationExplorer() {
    var oxygen by remember { mutableFloatStateOf(80f) }
    var glucose by remember { mutableFloatStateOf(70f) }
    val aerobic = oxygen >= 35f
    BiologyModelCard("Cellular respiration pathway explorer", "Explore how oxygen availability changes the pathway used to release energy from glucose.", "ATP yields vary with cell type and shuttle systems; the displayed values are conventional teaching approximations.") {
        ModelSlider("Oxygen availability", oxygen, 0f..100f, "%") { oxygen = it }
        ModelSlider("Glucose availability", glucose, 0f..100f, "%") { glucose = it }
        Text(if (aerobic) "Predominant model pathway: aerobic respiration" else "Predominant model pathway: anaerobic metabolism / fermentation", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        EquationDisplayCard(if (aerobic) "C_6H_{12}O_6 + 6O_2 \rightarrow 6CO_2 + 6H_2O" else "C_6H_{12}O_6 \rightarrow 2C_3H_6O_3", caption = "SIMPLIFIED OVERALL EQUATION")
        Text(if (aerobic) "Aerobic respiration can yield about 30–32 ATP per glucose in many eukaryotic-cell estimates." else "Glycolysis yields a net 2 ATP per glucose. Animal cells form lactate; yeast and some microorganisms can form ethanol and carbon dioxide.")
        LinearProgressIndicator(progress = { (glucose / 100f).coerceIn(0f, 1f) }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun NephronExplorer() {
    var pressure by remember { mutableFloatStateOf(65f) }
    var size by remember { mutableStateOf("Small solute") }
    val filtered = pressure >= 30f && size != "Plasma protein" && size != "Blood cell"
    BiologyModelCard("Nephron filtration model", "Change the filtration-driving pressure and choose a substance to trace what can pass from glomerular blood into the filtrate.", "The pressure scale is illustrative. Real glomerular filtration depends on hydrostatic and oncotic pressures, renal blood flow and barrier integrity.") {
        ModelSlider("Relative filtration pressure", pressure, 0f..100f, "units") { pressure = it }
        Text("Substance entering the glomerulus", fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Small solute", "Plasma protein", "Blood cell").forEach { item -> FilterChip(size == item, { size = item }, { Text(item) }) }
        }
        Text(if (filtered) "Model result: can enter the filtrate" else if (pressure < 30f) "Model result: insufficient filtration-driving pressure" else "Model result: normally retained in blood", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Small molecules such as water, glucose, ions and urea are filtered. Blood cells and most plasma proteins are retained by the filtration barrier.")
        Text("Next step: selective reabsorption returns useful substances and much of the water to the blood; tubular secretion adds selected substances to the filtrate.")
    }
}

@Composable
private fun MovementLeverExplorer() {
    var effortArm by remember { mutableFloatStateOf(60f) }
    var loadArm by remember { mutableFloatStateOf(30f) }
    var load by remember { mutableFloatStateOf(100f) }
    val effort = load * loadArm / effortArm.coerceAtLeast(1f)
    BiologyModelCard("Muscle, bone and joint lever model", "A joint acts as a pivot. Muscle force applied at one distance balances the load and its moment about the pivot.", "A simplified static lever; real limbs involve changing muscle angles, multiple muscles and dynamic motion.") {
        ModelSlider("Effort arm", effortArm, 10f..100f, "cm") { effortArm = it }
        ModelSlider("Load arm", loadArm, 10f..100f, "cm") { loadArm = it }
        ModelSlider("External load", load, 10f..200f, "N") { load = it }
        EquationDisplayCard("F_e d_e = F_l d_l", caption = "MOMENT BALANCE")
        Text("Ideal effort required: ${effort.roundToInt()} N", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Compare the effort and load arms. A shorter effort arm requires a larger muscle force for the same load.")
    }
}

@Composable
private fun FlowerReproductionExplorer() {
    var stage by remember { mutableIntStateOf(0) }
    val stages = listOf("Pollination", "Pollen germination", "Pollen tube growth", "Fertilisation", "Seed and fruit development")
    val explanations = listOf(
        "Pollen is transferred from anther to a receptive stigma. Pollination is not fertilisation.",
        "A compatible pollen grain germinates on the stigma and forms a pollen tube.",
        "The pollen tube grows through the style towards an ovule, carrying the male gametes.",
        "In flowering plants, double fertilisation forms a diploid zygote and a triploid endosperm nucleus.",
        "The ovule develops into a seed; the ovary usually develops into a fruit."
    )
    BiologyModelCard("Flowering-plant reproduction animation", "Move through the sequence and distinguish pollen transfer from the fusion of gamete nuclei.", "A sequence diagram in words; it does not model pollen growth rate or all variations among flowering plants.") {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            stages.forEachIndexed { index, label -> FilterChip(stage == index, { stage = index }, { Text((index + 1).toString()) }) }
        }
        Text(stages[stage], style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(explanations[stage])
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { stage = (stage - 1).coerceAtLeast(0) }, enabled = stage > 0) { Text("Previous") }
            Button(onClick = { stage = (stage + 1).coerceAtMost(stages.lastIndex) }, enabled = stage < stages.lastIndex) { Text("Next stage") }
        }
    }
}

@Composable
private fun ReflexArcExplorer() {
    var step by remember { mutableIntStateOf(0) }
    val labels = listOf("Receptor", "Sensory neurone", "Relay neurone", "Motor neurone", "Effector")
    val details = listOf("A receptor detects a stimulus, such as heat or pressure.", "An impulse travels along a sensory neurone towards the central nervous system.", "A relay neurone in the spinal cord links sensory and motor pathways.", "A motor neurone carries the impulse to the responding organ.", "A muscle or gland produces the response. The pathway is rapid and helps protect the body.")
    BiologyModelCard("Reflex arc interactive pathway", "Follow the direction of information from stimulus detection to response.", "The sequence is a simplified spinal reflex; some reflexes involve different circuits and the brain is informed as well.") {
        labels.forEachIndexed { index, label ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(color = if (index == step) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.small) {
                    Text((index + 1).toString(), Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                }
                Column(Modifier.weight(1f)) {
                    Text(label, fontWeight = FontWeight.SemiBold)
                    if (index == step) Text(details[index], style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { step = (step - 1).coerceAtLeast(0) }, enabled = step > 0) { Text("Previous") }
            Button(onClick = { step = (step + 1).coerceAtMost(labels.lastIndex) }, enabled = step < labels.lastIndex) { Text("Trace impulse") }
        }
    }
}

@Composable
private fun EcologicalToleranceExplorer() {
    var factor by remember { mutableFloatStateOf(50f) }
    var optimum by remember { mutableFloatStateOf(55f) }
    val suitability = exp(-((factor - optimum) * (factor - optimum)) / 700f)
    val curveColor = MaterialTheme.colorScheme.primary
    val markerColor = MaterialTheme.colorScheme.tertiary
    BiologyModelCard("Ecological tolerance and distribution", "Move an environmental factor away from or towards the species' optimum and observe the modelled suitability.", "A Gaussian teaching curve, not measured data for a real species. Different species have different tolerance limits and response curves.") {
        ModelSlider("Environmental factor", factor, 0f..100f, "units") { factor = it }
        ModelSlider("Species optimum", optimum, 0f..100f, "units") { optimum = it }
        Text("Relative suitability: ${(suitability * 100).roundToInt()}%", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Canvas(Modifier.fillMaxWidth().height(110.dp)) {
            val mid = size.height * .9f
            drawLine(Color.Gray, Offset(0f, mid), Offset(size.width, mid), 2f)
            val points = (0..100).map { x ->
                val y = exp(-((x - optimum) * (x - optimum)) / 700f)
                Offset(size.width * x / 100f, mid - y * size.height * .82f)
            }
            for (i in 0 until points.lastIndex) drawLine(curveColor, points[i], points[i + 1], 4f)
            val marker = points[factor.roundToInt().coerceIn(0, 100)]
            drawCircle(markerColor, 7f, marker)
        }
        Text("Name the factor being modelled (for example temperature, pH or salinity) before interpreting the curve.")
    }
}

@Composable
private fun HabitatExplorer(topicId: String) {
    var biome by remember(topicId) { mutableStateOf("Guinea savanna") }
    val descriptions = mapOf(
        "Tropical rainforest" to "High rainfall, humid conditions, layered vegetation and many epiphytes; plants often show drip tips and shade adaptations.",
        "Mangrove swamp" to "Saline, waterlogged intertidal soils; mangroves may have aerial roots and salt-management adaptations.",
        "Guinea savanna" to "Seasonal rainfall, grasses and scattered trees; organisms tolerate a pronounced dry season.",
        "Sudan savanna" to "Lower rainfall and shorter grasses than the Guinea savanna; drought-resistant plants are common.",
        "Sahel transition" to "Semi-arid conditions, sparse vegetation and strong water limitation; drought avoidance is critical.",
        "Montane grassland" to "Cooler high-altitude conditions; plants and animals are adapted to lower temperatures and exposed habitats."
    )
    BiologyModelCard("Nigerian habitat and biome explorer", "Choose a Nigerian ecological zone and connect its climate and vegetation to organism adaptations.", "Descriptions are broad teaching summaries; local conditions vary with altitude, season and human land use.") {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            descriptions.keys.forEach { item -> FilterChip(biome == item, { biome = item }, { Text(item) }) }
        }
        Text(biome, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(descriptions[biome].orEmpty())
        if (topicId == "bio-habitats") Text("For a habitat study, record microhabitat, abiotic conditions, organisms observed and evidence of adaptation.")
    }
}

@Composable
private fun SoilExplorer() {
    var soil by remember { mutableStateOf("Loam") }
    val properties = mapOf(
        "Sand" to "Large particles; rapid drainage and aeration; low water and nutrient retention.",
        "Clay" to "Very small particles; high water retention and capillarity, but slow drainage and poor aeration when compacted.",
        "Loam" to "A balanced mixture of sand, silt and clay with useful drainage, aeration and water retention."
    )
    BiologyModelCard("Soil texture and water movement", "Select a soil type and compare its expected drainage, aeration and water-holding behaviour.", "Qualitative comparison only; real soil behaviour also depends on structure, organic matter, compaction and mineral composition.") {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            properties.keys.forEach { item -> FilterChip(soil == item, { soil = item }, { Text(item) }) }
        }
        Text(soil, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(properties[soil].orEmpty())
        Text("Investigation prompt: use equal dry masses of soil, the same water volume and the same drainage time. Measure water retained and water collected; repeat before comparing means.")
    }
}

@Composable
private fun PollutionExplorer() {
    var source by remember { mutableStateOf("Fertiliser runoff") }
    val effects = mapOf(
        "Fertiliser runoff" to "Nutrients enter water → algal growth may increase → decomposition raises biological oxygen demand → dissolved oxygen may fall.",
        "Oil spill" to "Oil coats surfaces and organisms → light penetration and gas exchange may be reduced → aquatic organisms can be harmed.",
        "Sulfur dioxide" to "Atmospheric oxidation and reaction with water can form acidic deposition → soils and water may acidify and sensitive organisms can be affected.",
        "Plastic waste" to "Persistent debris can entangle organisms or be ingested → injury and food-web transfer of fragments may occur."
    )
    BiologyModelCard("Pollution cause-and-effect investigation", "Select a pollution source and trace a plausible pathway from release to ecological effect.", "The pathway is a general mechanism, not proof that a specific site is polluted. Field measurements and source investigation are required.") {
        effects.keys.forEach { item -> FilterChip(source == item, { source = item }, { Text(item) }) }
        Text(effects[source].orEmpty())
        Text("Evidence to collect: source location, water or air quality indicators, affected organisms, control sites and repeated measurements.")
    }
}

@Composable
private fun VariationExplorer() {
    var type by remember { mutableStateOf("Continuous") }
    var environment by remember { mutableFloatStateOf(50f) }
    BiologyModelCard("Variation and inheritance explorer", "Distinguish continuous variation, which spans a range, from discontinuous categories with distinct groups.", "The environmental slider illustrates a possible influence; it is not a genetic prediction or a real population dataset.") {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Continuous", "Discontinuous").forEach { item -> FilterChip(type == item, { type = item }, { Text(item) }) }
        }
        if (type == "Continuous") {
            ModelSlider("Nutrition index (illustrative)", environment, 0f..100f, "%") { environment = it }
            Text("Illustrative nutrition setting: ${environment.roundToInt()}%. Nutrition can influence growth and the distribution of height, alongside many genes and other environmental factors.")
            Text("Example: human height is influenced by many genes and environmental factors such as nutrition. Values form a range and often show an approximately normal distribution in a large population.")
            Text("Record many measurements, choose equal class intervals and plot a frequency histogram.")
        } else {
            Text("Example: ABO blood group has distinct categories (A, B, AB and O). A person's category is inherited; nutrition and ordinary environmental variation do not turn one ABO group into another.")
            Text("Count each category and display the frequencies in a bar chart. Do not treat categories as a continuous measurement.")
        }
    }
}

@Composable
private fun FermentationExplorer() {
    var sugar by remember { mutableFloatStateOf(60f) }
    var temperature by remember { mutableFloatStateOf(30f) }
    val temperatureEffect = exp(-((temperature - 35f) * (temperature - 35f)) / 250f)
    val rate = (sugar / 100f * temperatureEffect).coerceIn(0f, 1f)
    BiologyModelCard("Yeast fermentation simulation", "Adjust substrate availability and temperature to explore how conditions can affect carbon dioxide production.", "The response curve is illustrative, not a calibrated yeast growth or fermentation model. Very high temperatures can denature enzymes and damage cells.") {
        ModelSlider("Sugar concentration", sugar, 0f..100f, "% relative") { sugar = it }
        ModelSlider("Temperature", temperature, 5f..65f, "°C") { temperature = it }
        Text("Relative carbon dioxide production tendency: ${(rate * 100).roundToInt()}%", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        LinearProgressIndicator(progress = { rate }, modifier = Modifier.fillMaxWidth())
        EquationDisplayCard("C_6H_{12}O_6 \rightarrow 2C_2H_5OH + 2CO_2", caption = "ALCOHOLIC FERMENTATION")
        Text("A practical investigation could collect gas in a syringe at fixed time intervals while controlling yeast mass, solution volume and pH.")
    }
}

@Composable
private fun EvolutionEvidenceExplorer() {
    var structure by remember { mutableStateOf("Homologous") }
    val explanation = if (structure == "Homologous") {
        "Forelimbs of humans, bats, whales and other mammals share a basic arrangement of bones despite different functions. This supports common ancestry; the structures are homologous."
    } else {
        "Wings of birds and insects perform a similar function but have different underlying structural origins. Similar function alone does not establish close common ancestry; these are analogous structures."
    }
    BiologyModelCard("Comparative anatomy evidence lab", "Compare structural similarity with functional similarity before making an evolutionary inference.", "A single anatomical comparison is not conclusive by itself; combine fossils, molecular evidence, embryology and biogeography.") {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Homologous", "Analogous").forEach { item -> FilterChip(structure == item, { structure = item }, { Text(item) }) }
        }
        Text(structure, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(explanation)
        Text("Evidence question: which shared features are inherited structural patterns, and which similarities may have evolved independently under similar selection pressures?")
    }
}
