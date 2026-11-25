package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun WaecBiologyNigeriaSupplementalActivity(topicId: String) {
    when (topicId) {
        "bio-cell" -> {
            WaecCellIrritabilityActivity()
            WaecBiologicalDrawingActivity()
        }
        "bio-excretion" -> WaecExcretoryHealthActivity()
        "bio-coordination" -> WaecSenseOrgansActivity()
        "bio-classification" -> WaecDichotomousKeyActivity()
        "bio-nutrition" -> {
            WaecFeedingModesActivity()
            WaecFoodTestActivity()
            WaecNitrogenCycleActivity()
        }
        "bio-plant-anatomy" -> WaecPlantTissueActivity()
        "bio-reproduction" -> {
            WaecReproductionExtensionActivity()
            WaecSeedGerminationActivity()
        }
        "bio-interactions" -> {
            WaecEnergyFlowActivity()
            WaecNutrientCycleActivity()
        }
        "bio-ecology-factors" -> WaecToleranceRangeActivity()
        "bio-habitats" -> WaecHabitatAdaptationActivity()
        "bio-populations" -> WaecPopulationSamplingActivity()
        "bio-environment" -> WaecMicroorganismsHealthActivity()
        "bio-variation" -> WaecVariationApplicationsActivity()
        "bio-adaptation" -> WaecAdaptationExtensionActivity()
        "bio-soil" -> {
            WaecAgricultureEcologyActivity()
            WaecSoilPracticalActivity()
        }
    }
}

@Composable
private fun WaecCellIrritabilityActivity() {
    var example by remember { mutableStateOf("Euglena moving towards light") }
    val result = when (example) {
        "Euglena moving towards light" -> "Positive phototaxis: the whole motile organism moves towards a light stimulus."
        "Earthworm moving away from light" -> "Negative phototaxis: directional movement is away from the light stimulus."
        "Root growing towards water" -> "Positive hydrotropism: a plant organ grows directionally in response to water."
        "Mimosa leaves folding after touch" -> "Thigmonastic response: a non-directional movement response to touch."
        else -> "Positive geotropism: the root grows in the direction of gravity."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: cell theory and irritability", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Irritability is the ability to detect and respond to a stimulus. Select an example and classify the response.")
            listOf("Euglena moving towards light", "Earthworm moving away from light", "Root growing towards water", "Mimosa leaves folding after touch", "Root growing downwards").forEach {
                FilterChip(example == it, { example = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(result)
            Text("Cell theory reminder: organisms are made of cells; the cell is the basic structural and functional unit; new cells arise from pre-existing cells. A response may occur at cell, tissue, organ or organism level.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecFeedingModesActivity() {
    var mode by remember { mutableStateOf("Chemosynthetic") }
    val detail = when (mode) {
        "Chemosynthetic" -> "Certain bacteria obtain energy by oxidising inorganic substances and use that energy to fix carbon dioxide. Light is not the energy source."
        "Carnivorous plant" -> "Sundew and bladderwort trap small animals, usually to supplement mineral nutrients such as nitrogen; they still photosynthesise."
        "Filter feeding" -> "Water is passed across a filtering structure that retains suspended food particles, as in some aquatic animals."
        "Fluid feeding" -> "Liquid food is taken using specialised mouthparts, as in mosquitoes and butterflies."
        "Parasitic" -> "A parasite obtains nutrients from a host and may harm it; tapeworm is a standard example."
        else -> "Saprophytes obtain nutrients from dead organic matter by extracellular digestion and absorption; Rhizopus is a standard example."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: specialised feeding modes", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Chemosynthetic", "Carnivorous plant", "Filter feeding", "Fluid feeding", "Parasitic", "Saprophytic").forEach {
                FilterChip(mode == it, { mode = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("Do not confuse carnivorous plants with heterotrophic animals: carnivorous plants retain chlorophyll and make sugars by photosynthesis.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecReproductionExtensionActivity() {
    var stage by remember { mutableIntStateOf(0) }
    var behaviour by remember { mutableStateOf("Display") }
    val stages = listOf("Egg", "Larva (maggot)", "Pupa", "Adult")
    val explanation = when (stage) {
        0 -> "The egg is laid by the adult female. Development proceeds within the egg before hatching."
        1 -> "The larva feeds and grows through moults. It is the main feeding and growth stage."
        2 -> "The pupa is a non-feeding transformation stage in complete metamorphosis."
        else -> "The adult emerges, disperses and reproduces. The cycle begins again when eggs are laid."
    }
    val courtship = when (behaviour) {
        "Pairing" -> "Individuals come together as a pair before mating."
        "Display" -> "Visual or behavioural displays, such as feather display by birds, may attract mates."
        "Territoriality" -> "An individual defends an area or resource that increases access to mates."
        else -> "Seasonal movement may bring mature individuals together at breeding sites."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: housefly life history and courtship", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Housefly complete metamorphosis")
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                stages.forEachIndexed { index, name -> FilterChip(stage == index, { stage = index }, { Text(name) }) }
            }
            Text(stages[stage], style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Text(explanation)
            Text("Courtship behaviour")
            listOf("Pairing", "Display", "Territoriality", "Seasonal migration").forEach {
                FilterChip(behaviour == it, { behaviour = it }, { Text(it) })
            }
            Text(courtship)
            Text("Embryo nourishment differs among animals: yolk supplies nutrients in many eggs; the mammalian placenta supports exchange between maternal and fetal circulations without normally mixing their blood directly.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecEnergyFlowActivity() {
    var producerEnergy by remember { mutableFloatStateOf(1000f) }
    val herbivore = producerEnergy * 0.1f
    val carnivore = herbivore * 0.1f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: energy transformation in ecosystems", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Set energy captured by producers: " + producerEnergy.toInt() + " kJ")
            Slider(producerEnergy, { producerEnergy = it }, valueRange = 100f..10000f)
            Text("Illustrative energy passed to primary consumers: " + "%.1f".format(herbivore) + " kJ")
            Text("Illustrative energy passed to secondary consumers: " + "%.1f".format(carnivore) + " kJ")
            Text("Energy not transferred to the next trophic level is used in respiration and movement, lost as heat, or remains in uneaten/undigested material.")
            Text("The 10% transfer figure is a teaching approximation, not a universal biological constant. Real efficiencies vary by ecosystem, organisms and trophic level.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecToleranceRangeActivity() {
    var factor by remember { mutableFloatStateOf(50f) }
    val response = when {
        factor <= 20f || factor >= 80f -> 0f
        factor <= 50f -> (factor - 20f) / 30f * 100f
        else -> (80f - factor) / 30f * 100f
    }
    val zone = when {
        factor < 20f -> "Below the modelled minimum: the organism cannot maintain a viable population."
        factor > 80f -> "Above the modelled maximum: the condition exceeds the tolerance range."
        factor in 40f..60f -> "Near the optimum: the model predicts the highest performance."
        else -> "Within the tolerance range but away from the optimum: performance is reduced."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: ecological tolerance", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one environmental factor (for example temperature or salinity): " + "%.0f".format(factor))
            Slider(factor, { factor = it }, valueRange = 0f..100f)
            Text("Relative performance index: " + "%.0f".format(response), style = MaterialTheme.typography.headlineSmall)
            Text(zone)
            Text("A tolerance curve has minimum and maximum limits and an optimum range. Different species have different tolerance ranges; this triangular model is illustrative only.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecHabitatAdaptationActivity() {
    var habitat by remember { mutableStateOf("Marine") }
    val detail = when (habitat) {
        "Marine" -> "High salinity and wave action are important conditions. Examples include salt regulation, streamlined bodies and attachment structures."
        "Estuarine" -> "Salinity changes with tides and freshwater input. Organisms tolerate fluctuating salinity."
        "Freshwater" -> "The surrounding water is dilute relative to body fluids in many animals; osmoregulation is essential."
        "Marsh" -> "Waterlogged soils may have low oxygen. Plants may show air spaces, shallow roots or specialised support."
        "Forest" -> "Light varies vertically. Climbing, shade tolerance, broad leaves and canopy structure influence distribution."
        "Grassland" -> "Seasonal drought, fire and grazing shape adaptations such as deep roots, narrow leaves and rapid regrowth."
        else -> "Water is scarce and evaporation is high. Water storage, reduced leaves, deep roots and nocturnal activity can reduce water loss."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: habitat distribution and adaptations", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Marine", "Estuarine", "Freshwater", "Marsh", "Forest", "Grassland", "Arid land").forEach {
                FilterChip(habitat == it, { habitat = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("Describe the habitat condition first, then link each adaptation to a survival function. Do not assume every species in a habitat has the same adaptation.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecPopulationSamplingActivity() {
    var count by remember { mutableFloatStateOf(18f) }
    var area by remember { mutableFloatStateOf(2f) }
    val density = count / area.coerceAtLeast(0.1f)
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: population studies by sampling", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Organisms counted: " + count.toInt())
            Slider(count, { count = it }, valueRange = 0f..100f)
            Text("Sampled area: " + "%.1f".format(area) + " m²")
            Slider(area, { area = it }, valueRange = 0.5f..10f)
            Text("Observed density: " + "%.2f".format(density) + " organisms m⁻²", style = MaterialTheme.typography.headlineSmall)
            Text("Density = number of individuals ÷ sampled area. For a population estimate, use representative random/systematic samples and multiply mean density by the total habitat area.")
            Text("Sampling uncertainty rises with patchy distribution, small sample size, poor placement and inconsistent identification.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecMicroorganismsHealthActivity() {
    var context by remember { mutableStateOf("Food hygiene") }
    val detail = when (context) {
        "Food hygiene" -> "Prevent contamination through clean hands and utensils, safe storage temperatures, separation of raw and cooked foods and adequate cooking."
        "Water hygiene" -> "Protect sources from sewage and animal waste; use approved filtration/disinfection and safe storage. Clear water is not necessarily microbiologically safe."
        "Vector control" -> "Reduce breeding sites, use physical barriers and approved vector-control programmes. Control depends on the vector's life cycle and local ecology."
        "Microbial benefits" -> "Microorganisms decompose organic matter and are used in fermentation, medicine and industrial biotechnology."
        else -> "Pathogens may spread by contaminated food/water, droplets, direct contact or vectors. Prevention includes sanitation, vaccination where available and interrupting transmission."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: microorganisms, man and health", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Food hygiene", "Water hygiene", "Vector control", "Microbial benefits", "Disease transmission").forEach {
                FilterChip(context == it, { context = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("Microorganisms include bacteria, viruses, protozoa, algae and fungi. Not all microorganisms are harmful; distinguish pathogen, vector, reservoir and route of transmission.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecVariationApplicationsActivity() {
    var donor by remember { mutableStateOf("O") }
    var recipient by remember { mutableStateOf("A") }
    val compatible = when (donor) {
        "O" -> true
        "A" -> recipient == "A" || recipient == "AB"
        "B" -> recipient == "B" || recipient == "AB"
        else -> recipient == "AB"
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: applications of variation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Educational ABO red-cell compatibility model (Rh factor is not included).")
            Text("Donor group")
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("O", "A", "B", "AB").forEach { FilterChip(donor == it, { donor = it }, { Text(it) }) }
            }
            Text("Recipient group")
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("O", "A", "B", "AB").forEach { FilterChip(recipient == it, { recipient = it }, { Text(it) }) }
            }
            Text(if (compatible) "ABO red-cell compatibility: compatible in this simplified model." else "ABO red-cell compatibility: incompatible in this simplified model.", color = if (compatible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
            Text("Blood-group evidence can exclude some proposed biological relationships but ABO type alone cannot establish paternity. Real transfusion requires full compatibility testing, including Rh and crossmatching.", style = MaterialTheme.typography.bodySmall)
            Text("Variation also supports identification through fingerprints and other inherited traits; evidence must be interpreted with appropriate limitations.")
        }
    }
}

@Composable
private fun WaecAdaptationExtensionActivity() {
    var feature by remember { mutableStateOf("Camouflage") }
    val detail = when (feature) {
        "Camouflage" -> "Colour or pattern reduces detection by predators or prey. Its effectiveness depends on background and behaviour."
        "Warning colouration" -> "Conspicuous colour can signal chemical defence or unpalatability, reducing attacks after predators learn the signal."
        "Mimicry" -> "A harmless organism resembles a harmful or unpalatable organism; the benefit depends on predator responses."
        "Feeding structures" -> "Beaks, legs and mouthparts differ in shape and function, matching the food type and feeding method."
        else -> "Competition occurs when organisms require the same limited resource. Intra-specific competition occurs within a species; inter-specific competition occurs between species."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: adaptation, competition and colouration", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Camouflage", "Warning colouration", "Mimicry", "Feeding structures", "Competition").forEach {
                FilterChip(feature == it, { feature = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("A sound explanation links an observable feature to a selective advantage in a particular environment. Avoid describing an adaptation as a deliberate change made because an organism needed it.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecAgricultureEcologyActivity() {
    var practice by remember { mutableStateOf("Crop rotation") }
    val detail = when (practice) {
        "Crop rotation" -> "Growing different crops in sequence can interrupt pest cycles and improve nutrient management, especially when legumes are included."
        "Mulching" -> "A soil cover reduces evaporation and erosion, moderates temperature and can suppress weeds."
        "Tillage" -> "Tillage can prepare a seedbed but excessive disturbance may increase erosion, compaction and loss of soil structure."
        "Fertiliser use" -> "Apply nutrients according to crop need and soil evidence. Excess nutrients can leach or run off and cause eutrophication."
        "Bush burning" -> "Burning may temporarily clear land but can destroy organisms, expose soil, reduce organic matter and increase erosion."
        else -> "Integrated pest management combines monitoring, cultural, biological and carefully selected chemical controls to reduce harm."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: agriculture and ecological management", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Crop rotation", "Mulching", "Tillage", "Fertiliser use", "Bush burning", "Pest control").forEach {
                FilterChip(practice == it, { practice = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("Evaluate agricultural practices by their effects on soil, water, biodiversity, crop yield and long-term ecosystem stability.", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
private fun WaecBiologicalDrawingActivity() {
    var magnification by remember { mutableFloatStateOf(100f) }
    val actualSize = 0.1f
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: biological drawing and microscopy", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Assumed actual specimen dimension: " + actualSize + " mm")
            Text("Magnification: " + magnification.toInt() + "×")
            Slider(magnification, { magnification = it }, valueRange = 40f..400f)
            Text("Expected image dimension: " + "%.1f".format(actualSize * magnification) + " mm", style = MaterialTheme.typography.headlineSmall)
            Text("Magnification = image size ÷ actual size. Convert both measurements to the same unit before dividing.")
            Text("Drawing checklist: large and centred; clear single lines; no shading or sketching; accurate proportions; a concise title; straight label lines that do not cross; labels placed outside the drawing.")
            Text("For microscopy, begin with low power, focus carefully, use the fine adjustment at high power and record the objective/eyepiece magnification.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecFoodTestActivity() {
    var food by remember { mutableStateOf("Starch") }
    val observation = when (food) {
        "Starch" -> "Add iodine solution: yellow-brown changes to blue-black."
        "Reducing sugar" -> "Warm with Benedict's solution in a hot-water bath: blue may form a green/yellow/orange/brick-red precipitate."
        "Protein" -> "Add sodium hydroxide followed by a few drops of copper(II) sulfate (Biuret): violet/lilac colour."
        "Lipid" -> "Shake with ethanol, then add water: a milky-white emulsion forms."
        "Vitamin C" -> "Add sample to blue DCPIP: the dye is decolourised when sufficient vitamin C is present."
        else -> "For a non-reducing sugar, first hydrolyse with dilute acid, cool and neutralise, then carry out Benedict's test."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: food tests", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Starch", "Reducing sugar", "Protein", "Lipid", "Vitamin C", "Non-reducing sugar").forEach {
                FilterChip(food == it, { food = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(observation)
            Text("Use a separate clean sample for each test and record the actual colour change before making an inference. Ethanol is flammable; use a water bath for heating and follow teacher supervision.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecDichotomousKeyActivity() {
    var leaf by remember { mutableStateOf("Parallel venation") }
    var structure by remember { mutableStateOf("Simple leaf") }
    val identification = if (leaf == "Parallel venation") {
        if (structure == "Simple leaf") "Likely a monocot leaf such as maize or grass; confirm using other diagnostic characters."
        else "A compound leaf with parallel-veined leaflets is unusual; recheck the specimen and key choices."
    } else {
        if (structure == "Simple leaf") "Likely a dicot simple leaf such as mango; confirm margin, arrangement and other features."
        else "Likely a dicot compound leaf such as flamboyant; confirm leaflet arrangement and other characters."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: identification using a dichotomous key", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose observable features, not a guessed name.")
            listOf("Parallel venation", "Net-like venation").forEach {
                FilterChip(leaf == it, { leaf = it }, { Text(it) })
            }
            listOf("Simple leaf", "Compound leaf").forEach {
                FilterChip(structure == it, { structure = it }, { Text(it) })
            }
            Text(identification)
            Text("A dichotomous key uses paired, mutually exclusive statements. Each choice should be based on a visible character, and each step should lead to another pair or a taxon.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecPlantTissueActivity() {
    var organ by remember { mutableStateOf("Root") }
    val detail = when (organ) {
        "Root" -> "The root cross-section shows an outer epidermis, cortex and central vascular tissue. Xylem and phloem arrangement helps distinguish root types."
        "Stem" -> "The stem cross-section contains epidermis, cortex and vascular bundles. In many dicots, bundles form a ring; in many monocots, bundles are scattered."
        else -> "The leaf cross-section includes upper/lower epidermis, mesophyll and vascular bundles. Palisade mesophyll is commonly rich in chloroplasts."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: internal structure of flowering plants", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Root", "Stem", "Leaf").forEach {
                FilterChip(organ == it, { organ = it }, { Text(it) })
            }
            Text(detail)
            Text("When drawing a transverse section, orient the specimen correctly, draw only structures visible at the given magnification and label xylem, phloem and tissues only where they are actually present.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecSeedGerminationActivity() {
    var water by remember { mutableStateOf(true) }
    var oxygen by remember { mutableStateOf(true) }
    var warmth by remember { mutableStateOf(true) }
    val germination = water && oxygen && warmth
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: conditions for seed germination", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) { Checkbox(water, { water = it }); Text("Water available") }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) { Checkbox(oxygen, { oxygen = it }); Text("Oxygen available") }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) { Checkbox(warmth, { warmth = it }); Text("Suitable temperature") }
            Text(if (germination) "The three basic external conditions are present in this simplified model." else "At least one essential external condition is absent; normal germination is inhibited.", fontWeight = FontWeight.Bold)
            Text("Viability and species-specific requirements also matter. Some seeds require light, darkness, scarification or a period of dormancy-breaking treatment.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecSoilPracticalActivity() {
    var soil by remember { mutableStateOf("Sandy") }
    val detail = when (soil) {
        "Sandy" -> "Large particles and larger pore spaces usually give rapid drainage, good aeration and low water retention."
        "Clayey" -> "Very small particles and fine pores usually give slow drainage and high water retention; aeration may be poor when waterlogged."
        else -> "A balanced mixture of sand, silt, clay and organic matter often supports good drainage, aeration and nutrient retention."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC practical skill: soil texture and water retention", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Sandy", "Clayey", "Loamy").forEach {
                FilterChip(soil == it, { soil = it }, { Text(it) })
            }
            Text(detail)
            Text("A simple settling test separates particles by settling rate, but soil classification should use a recognised texture method. Soil structure and organic matter also influence field performance.", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
private fun WaecExcretoryHealthActivity() {
    var condition by remember { mutableStateOf("Kidney stones") }
    val detail = when (condition) {
        "Nephritis" -> "Inflammation of kidney tissue can impair filtration and cause blood/protein in urine, swelling or reduced kidney function. Clinical assessment and cause-specific care are required."
        "Kidney stones" -> "Mineral crystals form stones that may cause severe flank pain, blood in urine or urinary obstruction. Management depends on size, location and clinical assessment."
        "Diuresis" -> "Increased urine production may follow increased fluid intake, diuretic substances or disease. Excessive diuresis can disturb water and electrolyte balance."
        "Infective hepatitis" -> "Inflammation of the liver due to infection can impair liver function. Prevention depends on the virus and includes hygiene, safe practices and vaccination where available."
        "Liver cancer" -> "Malignant growth in liver tissue can impair metabolism and other liver functions. Risk reduction and treatment require medical care."
        else -> "Gallstones form from components of bile and may obstruct bile flow, causing pain or inflammation. Symptoms require clinical evaluation."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: kidney and liver disorders", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Nephritis", "Kidney stones", "Diuresis", "Infective hepatitis", "Liver cancer", "Gallstones").forEach {
                FilterChip(condition == it, { condition = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("This is syllabus-level biology, not a diagnostic or treatment guide. Suspected kidney or liver disease requires assessment by a qualified health professional.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecSenseOrgansActivity() {
    var organ by remember { mutableStateOf("Nose") }
    val detail = when (organ) {
        "Nose" -> "Olfactory receptor cells in the nasal epithelium detect dissolved airborne chemicals and transmit signals through the olfactory pathway."
        "Tongue" -> "Taste receptor cells in taste buds detect dissolved chemicals associated with sweet, salty, sour, bitter and umami sensations."
        else -> "The skin contains receptors for touch, pressure, vibration, temperature and pain. Different receptor endings respond to different stimuli."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: sense organs", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Nose", "Tongue", "Skin").forEach {
                FilterChip(organ == it, { organ = it }, { Text(it) })
            }
            Text(detail)
            Text("A receptor converts a stimulus into a signal that can be processed by the nervous system. Receptors have adequate stimuli and may adapt at different rates.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecNitrogenCycleActivity() {
    var process by remember { mutableStateOf("Nitrogen fixation") }
    val detail = when (process) {
        "Nitrogen fixation" -> "Nitrogen gas (N₂) is converted into ammonia/ammonium by nitrogen-fixing bacteria, some in legume root nodules, or by lightning and industrial processes."
        "Nitrification" -> "Nitrifying bacteria oxidise ammonium first to nitrite and then nitrate under suitable aerobic conditions."
        "Assimilation" -> "Plants absorb nitrate or ammonium and incorporate nitrogen into amino acids, proteins and nucleic acids. Animals obtain nitrogen by feeding."
        "Ammonification" -> "Decomposers convert organic nitrogen in dead material and waste into ammonia/ammonium."
        else -> "Denitrifying bacteria reduce nitrate to gaseous nitrogen forms, returning nitrogen to the atmosphere, commonly under low-oxygen conditions."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: nitrogen cycle", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Nitrogen fixation", "Nitrification", "Assimilation", "Ammonification", "Denitrification").forEach {
                FilterChip(process == it, { process = it }, { Text(it) })
            }
            HorizontalDivider()
            Text(detail)
            Text("The cycle depends on soil conditions, oxygen, microbial communities and plant uptake. Nitrogen fixation and denitrification are not the same process.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun WaecNutrientCycleActivity() {
    var cycle by remember { mutableStateOf("Carbon cycle") }
    val detail = if (cycle == "Carbon cycle") {
        "Photosynthesis removes carbon dioxide from the atmosphere; feeding transfers carbon through food chains; respiration, decomposition and combustion return carbon dioxide. Fossil fuel use changes the rate of transfer."
    } else {
        "Evaporation and transpiration move water to the atmosphere; condensation forms clouds; precipitation returns water to land and oceans; infiltration, runoff and groundwater flow redistribute it."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("WAEC Nigeria focus: nutrient cycles", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Carbon cycle", "Water cycle").forEach {
                FilterChip(cycle == it, { cycle = it }, { Text(it) })
            }
            Text(detail)
            Text("Distinguish a matter cycle from energy flow: atoms are recycled through ecosystems, while energy flows through trophic levels and is eventually dissipated as heat.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
