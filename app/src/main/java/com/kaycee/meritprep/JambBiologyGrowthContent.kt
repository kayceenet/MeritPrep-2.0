package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB Biology Topic 8: growth, seed germination and germination types. */
internal object JambBiologyGrowthContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Define growth in living organisms and distinguish growth from development.",
            listOf(
                JambDeepPoint("Meaning and measurement of growth",
                    """Growth is a permanent or irreversible increase in size, dry mass, volume or number of cells of an organism, resulting from synthesis of new protoplasm and other cellular materials. In multicellular organisms, growth commonly involves cell division, cell enlargement and differentiation. A temporary increase in mass after drinking water or feeding is not, by itself, biological growth because it may not represent the formation of new living material.

                    Growth can be measured by increase in height, length, volume, fresh mass, dry mass or cell number. Dry mass is often a more reliable measure of accumulated organic material than fresh mass because fresh mass changes with water content. In plants, growth can be measured using a ruler, balance or auxanometer depending on the scale and type of growth.""",
                    "A seedling increasing in dry mass as it forms new tissues is showing growth; a sponge swelling after absorbing water is not necessarily growing.",
                    "Growth is a permanent increase in living material; distinguish it from temporary swelling or change in water content."),
                JambDeepPoint("Cell division, enlargement and differentiation",
                    """Growth in plants is concentrated in meristematic regions where cells divide by mitosis. Newly formed cells then enlarge, often through water uptake and vacuole expansion, and differentiate into specialised tissues. In animals, growth involves cell division and enlargement in tissues, although the timing and distribution vary among organs. Cell division increases cell number; enlargement increases cell size; differentiation produces specialised cell structures and functions.

                    These processes are coordinated by genes, hormones, nutrients and environmental conditions. Plant growth can continue at apical and lateral meristems throughout life, whereas many animal tissues have more limited growth zones or regenerative capacity.""",
                    "A root tip lengthens because meristematic cells divide and cells behind the tip elongate.",
                    "Do not equate growth with cell division alone; cell enlargement and synthesis of new material also contribute."),
                JambDeepPoint("Growth versus development",
                    """Development is the wider process of changes in form and function during an organism's life. It includes growth, differentiation, maturation, ageing and changes in reproductive ability. Growth is quantitative, involving increase in size or mass; development includes qualitative changes in organisation and function. A tadpole developing limbs and changing into a frog undergoes development as well as growth.

                    Organisms may develop without a large increase in overall size at every stage. Metamorphosis involves major developmental changes in body plan, while seed germination and seedling establishment include growth and differentiation. In exam questions, use measurements to describe growth and structural or functional changes to describe development.""",
                    "A caterpillar grows in mass and later undergoes metamorphosis into a butterfly; metamorphosis is development.",
                    "Growth is an increase in size or living material; development includes broader changes in structure and function.")
            ),
            listOf("Growth is not temporary swelling caused only by water uptake.", "Dry mass can be more reliable than fresh mass when water content varies.", "Development includes growth but is broader than growth."),
            listOf(
                JambDeepCheck("bio-growth-01","Which statement best defines biological growth?",listOf("Temporary increase in water content","Permanent increase in size, dry mass or cell number","Movement towards a stimulus","Removal of metabolic waste"),1,"Growth is a lasting increase in living material, commonly measured by size, dry mass or cell number."),
                JambDeepCheck("bio-growth-02","Why is dry mass often preferred to fresh mass when measuring plant growth?",listOf("It includes more water","It is less affected by changing water content","It measures only mineral salts","It cannot be measured"),1,"Fresh mass varies with hydration; dry mass better reflects accumulated organic material."),
                JambDeepCheck("bio-growth-03","The change of a tadpole into a frog is an example of",listOf("egestion","development","excretion only","pollination"),1,"Metamorphosis is a developmental change in form and function.")
            )
        ),
        JambDeepObjective(
            "Explain the conditions necessary for seed germination and relate them to seedling growth.",
            listOf(
                JambDeepPoint("Water and oxygen",
                    """A viable seed requires water to begin germination. Water is absorbed through the seed coat, causing imbibition and swelling. It softens the testa, rehydrates tissues and activates enzymes that mobilise stored food. The embryo uses the soluble products for respiration and growth. Without adequate water, metabolic activity remains low and the seed cannot complete germination.

                    Oxygen is required for aerobic respiration, which releases energy from stored food for cell division and elongation. Waterlogged soil may contain too little oxygen because air spaces are filled with water; seeds can then respire poorly and may decay. Some species tolerate low oxygen better than others, but oxygen is a standard requirement for normal germination of most seeds.""",
                    "A seed in moist, aerated soil can germinate; a seed submerged in stagnant water may lack oxygen.",
                    "Water activates metabolism and mobilises food; oxygen supports aerobic respiration and energy supply."),
                JambDeepPoint("Suitable temperature and seed viability",
                    """Germination requires a suitable temperature range for enzyme activity, membrane function and respiration. Low temperatures slow metabolic reactions; excessively high temperatures can denature enzymes and damage the embryo. The optimum temperature differs among species and reflects the environment to which the plant is adapted. Many common crop seeds germinate well in warm conditions, but do not assume all species have the same optimum.

                    A seed must also be viable: its embryo must be alive and capable of growth. Seed age, storage humidity, temperature, pests and disease affect viability. Some seeds remain dormant even when water, oxygen and temperature are suitable because of hard seed coats, chemical inhibitors or an immature embryo. Dormancy-breaking requirements may include scarification, chilling, light exposure or after-ripening, depending on the species.""",
                    "Aged seeds stored in warm, damp conditions may lose viability; some temperate species require a period of chilling before germination.",
                    "Suitable temperature is species-dependent; viability and dormancy are distinct from the external germination conditions."),
                JambDeepPoint("Light, stored food and early seedling establishment",
                    """Light is not an absolute requirement for germination of every seed. Many seeds germinate in darkness because the embryo initially uses stored food in the cotyledons or endosperm. Some species have light-sensitive seeds and require light or darkness as a germination cue. Once the seedling emerges, light is generally needed for photosynthesis and continued independent growth.

                    During germination, enzymes digest stored starch, proteins and lipids into soluble substances that can be transported to the embryo. The radicle usually emerges first and develops into the primary root; the plumule develops into the shoot. As leaves expand and become photosynthetically active, the seedling gradually becomes less dependent on stored food.""",
                    "A bean can germinate underground without light by using food stored in its cotyledons, but the emerging seedling needs light for sustained photosynthesis.",
                    "Water, oxygen and suitable temperature are general requirements; light requirement varies by species, and stored food supports early growth.")
            ),
            listOf("Most seeds need water, oxygen and a suitable temperature; requirements differ among species.", "Waterlogged conditions can limit oxygen supply.", "Light is not required for germination of every seed, although it is usually needed for later photosynthesis."),
            listOf(
                JambDeepCheck("bio-growth-04","The main role of water during seed germination is to",listOf("prevent enzyme activity","activate metabolism and mobilise stored food","replace the embryo","stop respiration"),1,"Water rehydrates the seed, activates enzymes and enables stored food to be mobilised."),
                JambDeepCheck("bio-growth-05","A seed in waterlogged soil may fail to germinate normally because",listOf("there is too much oxygen","oxygen diffusion to the seed is reduced","light is always absent","the seed coat becomes a leaf"),1,"Water fills soil air spaces and can reduce oxygen available for aerobic respiration."),
                JambDeepCheck("bio-growth-06","Which statement about light and germination is correct?",listOf("All seeds require light to germinate","No seed can germinate in darkness","Light requirement varies among species","Light replaces oxygen"),2,"Some seeds are light-sensitive, but many germinate using stored food without light.")
            )
        ),
        JambDeepObjective(
            "Differentiate epigeal and hypogeal germination using the position of cotyledons and the growth of the hypocotyl or epicotyl.",
            listOf(
                JambDeepPoint("Epigeal germination",
                    """In epigeal germination, the hypocotyl elongates and lifts the cotyledons above the soil surface. The cotyledons may become green and photosynthetic for a period, or they may supply stored food before shrivelling. The plumule develops into the shoot and the radicle forms the root system. The cotyledons are therefore visible above ground during early seedling growth.

                    Epigeal germination occurs in beans and castor. The key diagnostic feature is not simply that the seedling emerges above the soil, because both types do; it is that the cotyledons are carried above the ground by elongation of the hypocotyl.""",
                    "In a germinating bean, the curved hypocotyl lifts the cotyledons above the soil.",
                    "Epigeal = cotyledons above ground; hypocotyl elongates."),
                JambDeepPoint("Hypogeal germination",
                    """In hypogeal germination, the epicotyl elongates while the cotyledons remain below the soil surface. The cotyledons continue to supply stored food to the growing embryo and may remain underground until their reserves are used. The plumule emerges above ground, while the radicle develops into roots. Pea and maize are commonly used as examples of hypogeal germination in school biology.

                    In maize, the cotyledon is represented by the scutellum, which remains within the grain and transfers stored food to the embryo. The protective coleoptile surrounds the emerging plumule, and the coleorhiza protects the radicle. The cotyledon position and which embryonic axis elongates are the main differences from epigeal germination.""",
                    "In pea, the cotyledons remain below ground while the epicotyl carries the plumule upwards.",
                    "Hypogeal = cotyledons remain below ground; epicotyl elongates."),
                JambDeepPoint("Compare and identify seedlings",
                    """Both epigeal and hypogeal germination begin with water uptake, enzyme activation, respiration and emergence of the radicle. In both, the plumule develops into the shoot and the radicle into the root. The distinction concerns which region elongates to position the cotyledons. Epigeal: hypocotyl elongates and cotyledons rise above soil. Hypogeal: epicotyl elongates and cotyledons stay below soil.

                    When examining a seedling, locate the cotyledons first, then identify the hypocotyl (region below the cotyledons) and epicotyl (region above the cotyledons). Do not classify by seed size or whether the plant is a monocot or dicot alone: both patterns are found among dicot plants, while maize is a familiar monocot example of hypogeal germination.""",
                    "Bean is epigeal; pea and maize are hypogeal in common school examples.",
                    "Use cotyledon position and the elongating axis; do not rely only on whether the seedling is a monocot or dicot.")
            ),
            listOf("Epigeal: hypocotyl elongates and lifts cotyledons above soil.", "Hypogeal: epicotyl elongates and cotyledons remain below soil.", "Both types involve radicle emergence and plumule development."),
            listOf(
                JambDeepCheck("bio-growth-07","In epigeal germination, the cotyledons are lifted above the soil mainly by elongation of the",listOf("radicle","hypocotyl","epicotyl","coleorhiza"),1,"The hypocotyl elongates and raises the cotyledons above the ground."),
                JambDeepCheck("bio-growth-08","Which is a common example of hypogeal germination?",listOf("Bean","Castor","Pea","Cotton"),2,"Pea is a common example in which cotyledons remain below the soil."),
                JambDeepCheck("bio-growth-09","The main feature distinguishing hypogeal from epigeal germination is that in hypogeal germination",listOf("the radicle never emerges","cotyledons remain below the soil","the plumule fails to develop","water is not absorbed"),1,"In hypogeal germination, the epicotyl elongates and the cotyledons remain below ground.")
            )
        )
    )

    fun forTopic(topicId:String):List<JambDeepObjective> =
        if(topicId=="bio-growth") objectives else emptyList()
}

@Composable
internal fun SeedGerminationInvestigationLab() {
    var water by remember { mutableStateOf(true) }
    var oxygen by remember { mutableStateOf(true) }
    var temperature by remember { mutableStateOf("Suitable") }
    var viable by remember { mutableStateOf(true) }
    val canGerminate = water && oxygen && temperature == "Suitable" && viable
    val explanation = when {
        !viable -> "The embryo is not viable, so the seed cannot germinate even if the external conditions are suitable."
        !water -> "Without water, the seed cannot complete imbibition or activate the enzymes needed to mobilise stored food."
        !oxygen -> "Without adequate oxygen, aerobic respiration cannot supply enough energy for normal germination."
        temperature != "Suitable" -> "The temperature is outside the suitable range for this seed; enzyme activity and metabolism are impaired."
        else -> "The seed has water, oxygen, a suitable temperature and a viable embryo. Germination can proceed, assuming dormancy has been overcome."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("SEED GERMINATION INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Test the conditions for germination", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one factor at a time. This is a qualitative model, not measured experimental data.")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = water, onCheckedChange = { water = it })
                Text("Water available")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = oxygen, onCheckedChange = { oxygen = it })
                Text("Oxygen available")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = viable, onCheckedChange = { viable = it })
                Text("Seed is viable")
            }
            Text("Temperature", fontWeight = FontWeight.SemiBold)
            listOf("Too low", "Suitable", "Too high").forEach {
                FilterChip(selected = temperature == it, onClick = { temperature = it }, label = { Text(it) })
            }
            Surface(color = if (canGerminate) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(if (canGerminate) "Conditions support germination" else "Germination is limited", fontWeight = FontWeight.Bold)
                    Text(explanation)
                }
            }
            Text("Practical extension: set up four groups of the same viable seeds, changing only one condition in each group. Record the number germinated each day and calculate percentage germination. Keep seed number, species and observation time constant.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun GerminationTypeComparisonLab() {
    var type by remember { mutableStateOf("Epigeal") }
    val description = if (type == "Epigeal") {
        "Hypocotyl elongates and lifts the cotyledons above the soil. Example: bean or castor."
    } else {
        "Epicotyl elongates while cotyledons remain below the soil. Example: pea or maize."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("GERMINATION COMPARISON", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Identify the germination pattern", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Epigeal", "Hypogeal").forEach {
                FilterChip(selected = type == it, onClick = { type = it }, label = { Text(it) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(type, fontWeight = FontWeight.Bold)
                    Text(description)
                }
            }
            Text("Exam task: draw a seedling, label cotyledon, hypocotyl, epicotyl, radicle and plumule, then explain which axis elongates.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
