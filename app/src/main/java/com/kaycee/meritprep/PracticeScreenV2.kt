package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.AssessmentEngine
import com.kaycee.meritprep.data.AssessmentQuestion
import com.kaycee.meritprep.data.MeritDatabase

@Composable
fun PracticeScreenV2() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val questions = remember {
        (BiologyQuestionBank.cellBiology + listOf(
            AssessmentQuestion("demo-chem-001", "chem-atomic", "The atomic number of an element is equal to the number of what particles in its nucleus?", listOf("Neutrons", "Protons", "Nucleons", "Isotopes"), 1, "The atomic number is the number of protons in the nucleus.", "MeritPrep demonstration item", false),
            AssessmentQuestion("demo-phy-001", "phy-motion", "What is the SI unit of acceleration?", listOf("m/s", "m/s²", "N", "J"), 1, "Acceleration is change in velocity per unit time, measured in metres per second squared.", "MeritPrep demonstration item", false)
        )).map { AssessmentEngine.present(it) }
    }
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var complete by remember { mutableStateOf(false) }

    if (complete) {
        Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("Practice complete", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Score: $score / ${questions.size}", style = MaterialTheme.typography.titleLarge)
            Text("Review the explanations and revisit any topic you found difficult. These are demonstration questions, not an official past-question set.")
            Button(onClick = { index = 0; selected = -1; checked = false; score = 0; complete = false }) { Text("Try again") }
        }
    } else {
        val q = questions[index]
        Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("Quick practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Question ${index + 1} of ${questions.size}")
            LinearProgressIndicator(progress = { (index + 1).toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
            Text(q.prompt, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            q.options.forEachIndexed { optionIndex, option ->
                OutlinedButton(onClick = { if (!checked) selected = optionIndex }, enabled = !checked, modifier = Modifier.fillMaxWidth()) {
                    Text((if (selected == optionIndex) "✓  " else "") + option)
                }
            }
            if (checked) {
                val correct = selected == q.correctPresentedIndex
                Text(if (correct) "Correct" else "Not correct", color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                Text(q.explanation)
                Button(onClick = {
                    if (index == questions.lastIndex) complete = true
                    else { index++; selected = -1; checked = false }
                }) { Text(if (index == questions.lastIndex) "View result" else "Next question") }
            } else {
                Button(
                    enabled = selected >= 0,
                    onClick = {
                        val correct = selected == q.correctPresentedIndex
                        val topicId = when (q.questionId) {
                            "demo-chem-001" -> "chem-atomic"
                            "demo-phy-001" -> "phy-motion"
                            else -> "bio-cell"
                        }
                        database.recordQuestionAttempt(topicId, correct)
                        if (correct) score++
                        checked = true
                    }
                ) { Text("Check answer") }
            }
            Text("Demonstration questions only. Production question banks require source verification and academic review.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
