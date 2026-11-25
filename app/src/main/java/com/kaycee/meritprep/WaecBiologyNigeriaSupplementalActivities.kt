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
        "bio-cell" -> WaecCellIrritabilityActivity()
        "bio-nutrition" -> WaecFeedingModesActivity()
        "bio-reproduction" -> WaecReproductionExtensionActivity()
        "bio-interactions" -> WaecEnergyFlowActivity()
        "bio-ecology-factors" -> WaecToleranceRangeActivity()
        "bio-habitats" -> WaecHabitatAdaptationActivity()
        "bio-populations" -> WaecPopulationSamplingActivity()
        "bio-environment" -> WaecMicroorganismsHealthActivity()
        "bio-variation" -> WaecVariationApplicationsActivity()
        "bio-adaptation" -> WaecAdaptationExtensionActivity()
        "bio-soil" -> WaecAgricultureEcologyActivity()
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
