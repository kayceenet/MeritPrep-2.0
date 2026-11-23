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
}

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
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = {}, label = { Text("Due: ${dueCards.size}") })
            AssistChip(onClick = {}, label = { Text("Reviewed: $reviewedCount") })
        }
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
