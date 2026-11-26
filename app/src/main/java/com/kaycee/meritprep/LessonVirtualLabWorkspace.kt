package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.AssessmentEngine
import com.kaycee.meritprep.data.AssessmentQuestion
import com.kaycee.meritprep.data.PresentedQuestion
import kotlin.random.Random

private enum class LessonLabMode(val label: String) {
    INTERACTIVE("Interactive Lab"),
    INVESTIGATION("Investigation"),
    CHALLENGE("Challenge"),
    EXPLORE("External Labs")
}

@Composable
fun LessonVirtualLabWorkspace(topicId: String, subjectCode: String) {
    var mode by remember(topicId) { mutableStateOf(LessonLabMode.INTERACTIVE) }
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("PRACTICAL LAB WORKSPACE", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose how you want to investigate", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Open a topic-matched interactive simulator, run a structured investigation with a lab notebook, test your understanding in a challenge, or launch a curated external virtual lab.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Row(
            Modifier.fillMaxWidth().horizontalScroll(androidx.compose.foundation.rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            LessonLabMode.entries.forEach { item ->
                FilterChip(
                    selected = mode == item,
                    onClick = { mode = item },
                    label = { Text(item.label) }
                )
            }
        }
        when (mode) {
            LessonLabMode.INTERACTIVE -> {
                Text("TOPIC-MATCHED SCIENTIFIC MODELS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text("Work directly with the structures, processes, apparatus and variables taught in this topic. These are local activities and remain available offline.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                when (subjectCode) {
                    "BIO" -> {
                        BiologyInteractiveLessonActivity(topicId)
                        BiologySupplementalLessonActivity(topicId)
                        if (topicId == "bio-cell") BiologyCellMicroscopyVirtualLab()
                        if (topicId == "bio-adaptation") BiologyAdaptationVirtualLab()
                        WaecBiologyNigeriaSupplementalActivity(topicId)
                    }
                    "CHEM" -> {
                        ChemistryInteractiveLessonActivity(topicId)
                        ChemistrySupplementalLessonActivity(topicId)
                        WaecChemistrySupplementalActivity(topicId)
                    }
                    "PHY" -> {
                        PhysicsInteractiveLessonActivity(topicId)
                        PhysicsSupplementalLessonActivity(topicId)
                        WaecPhysicsSupplementalActivity(topicId)
                    }
                }
                TopicScienceAnimation(topicId)
                LessonPracticalDispatcher(topicId)
            }
            LessonLabMode.INVESTIGATION -> {
                Text("PREDICT → PLAN → RUN → RECORD → ANALYSE → EVALUATE", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                GuidedInvestigationActivity(topicId)
            }
            LessonLabMode.CHALLENGE -> LabChallengeRounds(topicId, subjectCode)
            LessonLabMode.EXPLORE -> {
                val resources = remember(topicId) { ScienceMediaCatalog.forTopic(topicId) }
                if (resources.isEmpty()) {
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text("No external resource is verified for this topic yet.", fontWeight = FontWeight.SemiBold)
                            Text("Use the native bench activities and visual models in this lesson. External links are added only after their source, licence and attribution have been checked.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                } else {
                    ScienceMediaTopicSection(topicId)
                }
            }
        }
    }
}

@Composable
private fun LabChallengeRounds(topicId: String, subjectCode: String) {
    var roundSeed by remember(topicId) { mutableIntStateOf(1) }
    val questions = remember(topicId, subjectCode, roundSeed) {
        val topic = LearningCatalog.find(topicId)
        val bank = when (subjectCode) {
            "BIO" -> BiologyQuestionBank.all.filter { it.topicId == topicId }
            "CHEM" -> ChemistryQuestionBank.all.filter { it.topicId == topicId }
            "PHY" -> PhysicsQuestionBank.all.filter { it.topicId == topicId }
            "MATH" -> MathematicsQuestionBank.all.filter { it.topicId == topicId }
            "ENG" -> EnglishQuestionBank.all.filter { it.topicId == topicId }
            else -> emptyList()
        }
        val fallback = if (topic == null) emptyList() else listOf(
            AssessmentQuestion(
                id = "lab-challenge-" + topicId,
                topicId = topicId,
                prompt = topic.checkQuestion,
                options = topic.checkOptions,
                correctOptionIndex = topic.correctOption,
                explanation = topic.explanation,
                source = "MeritPrep lesson challenge",
                isVerified = false
            )
        )
        val source = (if (bank.isNotEmpty()) bank else fallback).shuffled(Random(roundSeed)).take(3)
        source.mapIndexed { index, item -> AssessmentEngine.present(item, Random(roundSeed * 31 + index)) }
    }
    var current by remember(topicId, roundSeed) { mutableIntStateOf(0) }
    var selected by remember(topicId, roundSeed) { mutableIntStateOf(-1) }
    var checked by remember(topicId, roundSeed) { mutableStateOf(false) }
    var score by remember(topicId, roundSeed) { mutableIntStateOf(0) }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(if (questions.size == 3) "THREE-ROUND KNOWLEDGE CHALLENGE" else "TOPIC KNOWLEDGE CHECK", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            if (current >= questions.size) {
                Text("Challenge complete", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("You answered " + score + " of " + questions.size + " rounds correctly.")
                LinearProgressIndicator(progress = { if (questions.isEmpty()) 0f else score.toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
                Text("Review the explanations in the bench investigation, then try a fresh question set.")
                Button(onClick = { roundSeed++ }, modifier = Modifier.fillMaxWidth()) { Text("Replay with a new set") }
            } else {
                val question = questions[current]
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Round " + (current + 1) + " of " + questions.size, fontWeight = FontWeight.SemiBold)
                    Text("Score " + score, style = MaterialTheme.typography.labelMedium)
                }
                LinearProgressIndicator(progress = { (current + 1f) / questions.size }, modifier = Modifier.fillMaxWidth())
                ScientificContent(question.prompt, style = MaterialTheme.typography.titleMedium)
                question.options.forEachIndexed { index, option ->
                    OutlinedButton(
                        onClick = { if (!checked) selected = index },
                        enabled = !checked,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected == index) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        ScientificText(('A' + index).toString() + ". " + option, modifier = Modifier.fillMaxWidth())
                    }
                }
                if (checked) {
                    val correct = selected == question.correctPresentedIndex
                    Text(if (correct) "Correct" else "Review the explanation", color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                    ScientificContent(question.explanation, style = MaterialTheme.typography.bodyMedium)
                    Button(
                        onClick = {
                            if (current == questions.lastIndex) current = questions.size
                            else {
                                current++
                                selected = -1
                                checked = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(if (current == questions.lastIndex) "View challenge result" else "Next round") }
                } else {
                    Button(
                        onClick = {
                            if (selected == question.correctPresentedIndex) score++
                            checked = true
                        },
                        enabled = selected >= 0,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Check answer") }
                }
            }
        }
    }
}
