package com.kaycee.meritprep

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.MistakeNote
import com.kaycee.meritprep.data.PlacementAssessmentEngine
import com.kaycee.meritprep.data.PlacementDiagnosticResult

@Composable
fun PlacementAssessmentScreen(onOpenLesson: (String) -> Unit) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val questions = remember { PlacementAssessmentEngine.createDiagnostic(BiologyQuestionBank.all) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswers by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var result by remember { mutableStateOf<PlacementDiagnosticResult?>(null) }
    val current = questions[currentIndex]

    if (result == null) {
        Column(
            Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Biology starting-point check", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("One original practice question from each Biology topic. Use this diagnostic to decide what to study first; it is not an exam prediction.")
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth()
            )
            Text("Question ${currentIndex + 1} of ${questions.size}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            Text(current.prompt, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            current.options.forEachIndexed { index, option ->
                val isSelected = selectedAnswers[current.questionId] == index
                Card(
                    modifier = Modifier.fillMaxWidth().clickable {
                        selectedAnswers = selectedAnswers + (current.questionId to index)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = isSelected, onClick = {
                            selectedAnswers = selectedAnswers + (current.questionId to index)
                        })
                        Text(option, Modifier.padding(start = 8.dp))
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = { currentIndex-- },
                    enabled = currentIndex > 0,
                    modifier = Modifier.weight(1f)
                ) { Text("Previous") }
                if (currentIndex < questions.lastIndex) {
                    Button(onClick = { currentIndex++ }, modifier = Modifier.weight(1f)) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = {
                            val scored = PlacementAssessmentEngine.score(questions, selectedAnswers)
                            questions.forEach { question ->
                                val selected = selectedAnswers[question.questionId] ?: -1
                                val correct = selected == question.correctPresentedIndex
                                database.recordQuestionAttempt(question.topicId, correct, question.questionId)
                                if (!correct) {
                                    database.saveMistake(
                                        MistakeNote(
                                            mistakeId = "placement-${question.questionId}-${System.currentTimeMillis()}",
                                            questionId = question.questionId,
                                            topicId = question.topicId,
                                            prompt = question.prompt,
                                            selectedAnswer = if (selected < 0) "Not answered" else question.options[selected],
                                            correctAnswer = question.options[question.correctPresentedIndex],
                                            explanation = question.explanation,
                                            createdAt = System.currentTimeMillis(),
                                            isResolved = false
                                        )
                                    )
                                }
                            }
                            result = scored
                        },
                        modifier = Modifier.weight(1f)
                    ) { Text("Finish diagnostic") }
                }
            }
            Text(
                "${selectedAnswers.size} of ${questions.size} answered. You may leave items blank and return to them before finishing.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        val summary = result!!
        LazyColumn(
            Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Your starting-point report", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("${summary.correctAnswers} correct out of ${summary.totalQuestions}", style = MaterialTheme.typography.titleLarge)
                Text("${summary.percentage}% diagnostic accuracy · ${summary.unanswered} unanswered")
                Text("This is a snapshot of the topics sampled today, not a validated estimate of your JAMB or WAEC grade.")
                Text("Topics to prioritise", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
            items(summary.topicPerformance) { topic ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(BiologyCurriculum.units.firstOrNull { it.id == topic.topicId }?.title ?: topic.topicId, fontWeight = FontWeight.SemiBold)
                        Text("${topic.correctAnswers} / ${topic.totalQuestions} correct · ${topic.accuracyPercent}%")
                        LinearProgressIndicator(
                            progress = { topic.accuracyPercent / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedButton(onClick = { onOpenLesson(topic.topicId) }) {
                            Text("Study this topic")
                        }
                    }
                }
            }
            item {
                Text("Your results have been added to your local practice history. Incorrect and unanswered items are in your mistake notebook.")
                Button(onClick = onOpenLesson("bio-cell"), modifier = Modifier.fillMaxWidth()) {
                    Text("Start with the first Biology lesson")
                }
            }
        }
    }
}
