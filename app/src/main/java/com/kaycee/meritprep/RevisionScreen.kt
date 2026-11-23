package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.RevisionCard
import com.kaycee.meritprep.data.ReviewRating
import com.kaycee.meritprep.data.SpacedRepetitionScheduler

private val starterBiologyCards = listOf(
    "A cell is the basic structural and functional unit of life." to "A cell is the smallest unit that can perform the processes associated with life.",
    "State the three main ideas of cell theory." to "Living organisms are made of cells; the cell is the basic unit of structure and function; new cells arise from pre-existing cells.",
    "What is the main function of the cell-surface membrane?" to "It forms a selectively permeable boundary that regulates movement of substances into and out of the cell.",
    "Which organelle is the main site of aerobic respiration in eukaryotic cells?" to "The mitochondrion. Many stages of aerobic respiration occur there; glycolysis occurs in the cytosol.",
    "What is the function of ribosomes?" to "Ribosomes are the sites of protein synthesis (translation).",
    "What is the role of chloroplasts in green plant cells?" to "Chloroplasts contain chlorophyll and are the main sites of photosynthesis.",
    "What is the function of the plant cell wall?" to "The cellulose cell wall provides support, helps maintain shape and resists excessive expansion when water enters.",
    "Give one key difference between prokaryotic and eukaryotic cells." to "Eukaryotic cells have a membrane-bound nucleus; prokaryotic cells do not.",
    "What is a tissue?" to "A tissue is a group of similar cells working together to perform a particular function.",
    "Arrange these levels from simplest to most complex: organ, cell, organism, tissue, organ system." to "Cell → tissue → organ → organ system → organism.",
    "Why do root hair cells have a long projection?" to "It increases surface area for absorption of water and mineral ions from the soil.",
    "Which structure controls many activities of a typical eukaryotic cell?" to "The nucleus contains most of the cell's genetic material and regulates gene expression; not all cells have a nucleus."
).mapIndexed { index, pair ->
    RevisionCard(
        cardId = "bio-cell-card-${index + 1}",
        topicId = "bio-cell",
        front = pair.first,
        back = pair.second,
        dueAt = 0L
    )
} + listOf(
    RevisionCard("bio-class-card-1","bio-classification","What is the difference between classification and identification?","Classification arranges organisms into groups; identification determines which organism has been observed, often using a key.",0L),
    RevisionCard("bio-class-card-2","bio-classification","State the taxonomic ranks from broadest to most specific.","Kingdom → Phylum (or Division in plants) → Class → Order → Family → Genus → Species.",0L),
    RevisionCard("bio-class-card-3","bio-classification","How is a scientific species name written?","Use two words: Genus with an initial capital, followed by the lower-case specific epithet. Italicise both when typed, e.g. Homo sapiens.",0L),
    RevisionCard("bio-class-card-4","bio-classification","What feature separates prokaryotic from eukaryotic cells?","Prokaryotic cells lack a membrane-bound nucleus; eukaryotic cells have one.",0L),
    RevisionCard("bio-class-card-5","bio-classification","Why are fungi not classified as green plants?","Fungi obtain nutrients by absorption and do not photosynthesise; their cell walls commonly contain chitin rather than cellulose.",0L),
    RevisionCard("bio-class-card-6","bio-classification","Why are viruses excluded from the five traditional kingdoms?","Viruses are acellular and replicate only inside suitable host cells; they are not cellular organisms.",0L),
    RevisionCard("bio-class-card-7","bio-classification","Which plant group has vascular tissue but reproduces by spores rather than seeds?","Ferns have vascular tissue but do not produce seeds.",0L),
    RevisionCard("bio-class-card-8","bio-classification","What is the purpose of a dichotomous key?","It guides identification through a sequence of paired contrasting statements based on observable features.",0L),
    RevisionCard("bio-adapt-card-1","bio-adaptation","Define adaptation.","An inherited characteristic that can improve survival and reproductive success in a particular environment.",0L),
    RevisionCard("bio-adapt-card-2","bio-adaptation","Name the three broad types of adaptation.","Structural (physical features), physiological (internal functions) and behavioural (patterns of activity).",0L),
    RevisionCard("bio-adapt-card-3","bio-adaptation","How does a streamlined body help a fish?","It reduces resistance or drag as the fish moves through water.",0L),
    RevisionCard("bio-adapt-card-4","bio-adaptation","How do root or stem features of xerophytes help conserve water?","Reduced leaf area, thick cuticles and water-storing tissues can reduce water loss or store water; the exact features vary by species.",0L),
    RevisionCard("bio-adapt-card-5","bio-adaptation","What are pneumatophores?","Aerial roots in some mangroves that project above waterlogged sediment and assist gas exchange.",0L),
    RevisionCard("bio-adapt-card-6","bio-adaptation","Distinguish adaptation from acclimatisation.","Adaptation is inherited across generations; acclimatisation is an adjustment within an individual's lifetime.",0L),
    RevisionCard("bio-adapt-card-7","bio-adaptation","What is camouflage?","A feature or pattern that makes an organism less conspicuous against its surroundings.",0L),
    RevisionCard("bio-adapt-card-8","bio-adaptation","Why can nocturnal activity be advantageous in hot environments?","It can reduce exposure to extreme daytime heat and may reduce water loss or heat stress.",0L),
    RevisionCard("bio-plant-card-1","bio-plant-anatomy","What are the three main tissue systems in flowering plants?","Dermal (epidermal), ground and vascular tissue systems.",0L),
    RevisionCard("bio-plant-card-2","bio-plant-anatomy","What is the function of root hairs?","They increase the surface area for absorption of water and mineral ions from the soil.",0L),
    RevisionCard("bio-plant-card-3","bio-plant-anatomy","What does xylem transport?","Water and dissolved mineral ions, mainly from roots towards aerial parts of the plant.",0L),
    RevisionCard("bio-plant-card-4","bio-plant-anatomy","What does phloem transport?","Organic substances such as sucrose between sources and sinks; movement may occur in different directions in different sieve tubes.",0L),
    RevisionCard("bio-plant-card-5","bio-plant-anatomy","What is the role of guard cells?","They regulate the opening and closing of stomata by changes in their turgor.",0L),
    RevisionCard("bio-plant-card-6","bio-plant-anatomy","Why is palisade mesophyll important?","It commonly contains many chloroplasts and is a major site of photosynthesis in a typical leaf.",0L),
    RevisionCard("bio-plant-card-7","bio-plant-anatomy","Why are xylem walls often lignified?","Lignin strengthens the walls and helps prevent collapse during water transport.",0L),
    RevisionCard("bio-plant-card-8","bio-plant-anatomy","How do alveoli support gas exchange?","They provide a large moist surface with thin walls and a rich capillary supply, supporting rapid diffusion.",0L),
    RevisionCard("bio-nut-card-1","bio-nutrition","Write the simplified balanced equation for photosynthesis.","6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂, in the presence of light and chlorophyll.",0L),
    RevisionCard("bio-nut-card-2","bio-nutrition","Name four factors that can affect photosynthesis rate.","Light intensity, carbon dioxide concentration, temperature and water availability; chlorophyll content also matters.",0L),
    RevisionCard("bio-nut-card-3","bio-nutrition","What is the role of magnesium in plants?","Magnesium is a component of the chlorophyll molecule.",0L),
    RevisionCard("bio-nut-card-4","bio-nutrition","What is the role of nitrate ions in plant nutrition?","They provide nitrogen for synthesising amino acids and proteins.",0L),
    RevisionCard("bio-nut-card-5","bio-nutrition","What are the main functions of proteins in the diet?","They provide amino acids for growth, tissue repair, enzymes and other body molecules.",0L),
    RevisionCard("bio-nut-card-6","bio-nutrition","Is bile an enzyme? What does it do?","No. Bile emulsifies fats into smaller droplets and helps neutralise acidic chyme.",0L),
    RevisionCard("bio-nut-card-7","bio-nutrition","Where does most nutrient absorption occur?","In the small intestine, whose folds, villi and microvilli provide a large surface area.",0L),
    RevisionCard("bio-nut-card-8","bio-nutrition","What is assimilation?","The use or incorporation of absorbed nutrients by cells and tissues, such as using amino acids to build proteins.",0L)
) + listOf(
    RevisionCard("bio-trans-card-1","bio-transport","Define diffusion.","The net movement of particles from higher concentration to lower concentration due to random motion.",0L),
    RevisionCard("bio-trans-card-2","bio-transport","Define osmosis.","The net movement of water across a selectively permeable membrane from higher to lower water potential.",0L),
    RevisionCard("bio-trans-card-3","bio-transport","How does active transport differ from diffusion?","Active transport uses energy and transport proteins to move substances against a concentration or electrochemical gradient; diffusion is passive and moves down a concentration gradient.",0L),
    RevisionCard("bio-trans-card-4","bio-transport","What is the role of xylem?","It transports water and dissolved mineral ions mainly from roots towards leaves and also provides support.",0L),
    RevisionCard("bio-trans-card-5","bio-transport","What is transpiration?","Loss of water vapour from aerial parts of a plant, mainly through stomata.",0L),
    RevisionCard("bio-trans-card-6","bio-transport","What is translocation?","Movement of organic assimilates, especially sucrose, through phloem from sources to sinks.",0L),
    RevisionCard("bio-trans-card-7","bio-transport","What is the function of haemoglobin?","It binds oxygen reversibly and transports most oxygen in the blood within red blood cells.",0L),
    RevisionCard("bio-trans-card-8","bio-transport","How do arteries differ from veins?","Arteries carry blood away from the heart and have thick elastic walls; veins return blood to the heart and often contain valves.",0L),
    RevisionCard("bio-trans-card-9","bio-transport","What is the role of capillaries?","Their thin walls allow exchange of gases, nutrients and wastes between blood and tissues.",0L),
    RevisionCard("bio-trans-card-10","bio-transport","What are the two circuits in human double circulation?","Pulmonary circulation is between heart and lungs; systemic circulation is between heart and the rest of the body.",0L)
)

@Composable
fun RevisionScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var dueCards by remember { mutableStateOf(emptyList<RevisionCard>()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var revealed by remember { mutableStateOf(false) }
    var reviewedCount by remember { mutableIntStateOf(0) }

    fun refresh() {
        database.seedRevisionCards(starterBiologyCards)
        dueCards = database.getDueRevisionCards()
        currentIndex = 0
        revealed = false
    }
    LaunchedEffect(database) { refresh() }

    val current = dueCards.getOrNull(currentIndex)
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Spaced revision", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Recall first. Reveal the explanation. Then rate how difficult it was to remember.")
        Text("Due now: ${dueCards.size}   ·   Reviewed this session: $reviewedCount", style = MaterialTheme.typography.labelLarge)
        if (current == null) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(if (dueCards.isEmpty()) "You are up to date" else "Review session complete", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(if (dueCards.isEmpty()) "No cards are due right now. Your next review will appear when its scheduled time arrives." else "You have completed the cards currently due in this session.")
                    OutlinedButton(onClick = { refresh() }) { Text("Refresh due cards") }
                }
            }
        } else {
            Text("Card ${currentIndex + 1} of ${dueCards.size}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("RECALL", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text(current.front, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    if (revealed) {
                        HorizontalDivider()
                        Text("ANSWER", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                        Text(current.back, style = MaterialTheme.typography.bodyLarge)
                    } else {
                        Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth()) { Text("Reveal answer") }
                    }
                }
            }
            if (revealed) {
                Text("How well did you recall it?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                listOf(
                    ReviewRating.AGAIN to ("Again" to "Forgot or could not recall"),
                    ReviewRating.HARD to ("Hard" to "Remembered with difficulty"),
                    ReviewRating.GOOD to ("Good" to "Recalled correctly"),
                    ReviewRating.EASY to ("Easy" to "Recalled quickly and confidently")
                ).forEach { (rating, copy) ->
                    OutlinedButton(
                        onClick = {
                            val updated = SpacedRepetitionScheduler.schedule(current, rating, System.currentTimeMillis())
                            database.reviewRevisionCard(updated)
                            reviewedCount++
                            dueCards = dueCards.toMutableList().also { it.removeAt(currentIndex) }
                            revealed = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.fillMaxWidth()) {
                            Text(copy.first, fontWeight = FontWeight.SemiBold)
                            Text(copy.second, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
        Text("Scheduling is an on-device learning aid, not a guarantee of retention or exam performance. Cards are original study prompts, not official past questions.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
