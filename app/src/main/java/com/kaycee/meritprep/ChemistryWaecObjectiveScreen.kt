package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.AssessmentEngine
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.MistakeNote
import kotlinx.coroutines.delay
import java.util.UUID

private const val CHEMISTRY_WAEC_DURATION_MS = 30L * 60L * 1000L

@Composable
fun ChemistryWaecObjectiveScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val questions = remember { ChemistryWaecObjectiveBank.all.shuffled().map { AssessmentEngine.present(it) } }
    val answers = remember { mutableStateMapOf<String, Int>() }
    var started by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }
    var startedAt by remember { mutableLongStateOf(0L) }
    var remainingMs by remember { mutableLongStateOf(CHEMISTRY_WAEC_DURATION_MS) }
    var saved by remember { mutableStateOf(false) }

    LaunchedEffect(started, submitted) {
        while (started && !submitted) {
            remainingMs = (CHEMISTRY_WAEC_DURATION_MS - (System.currentTimeMillis() - startedAt)).coerceAtLeast(0L)
            if (remainingMs == 0L) {
                submitted = true
                break
            }
            delay(1000L)
        }
    }

    val result = remember(submitted, answers.size) {
        if (submitted) AssessmentEngine.score(questions, answers.toMap()) else null
    }

    LaunchedEffect(submitted) {
        if (submitted && !saved) {
            questions.forEach { question ->
                val selected = answers[question.questionId] ?: -1
                val correct = selected == question.correctPresentedIndex
                database.recordQuestionAttempt(question.topicId, correct, question.questionId)
                if (selected >= 0 && !correct) {
                    database.saveMistake(
                        MistakeNote(
                            UUID.randomUUID().toString(), question.questionId, question.topicId,
                            question.prompt, question.options[selected],
                            question.options[question.correctPresentedIndex], question.explanation,
                            System.currentTimeMillis(), false
                        )
                    )
                }
            }
            saved = true
        }
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("WAEC Chemistry objective", style = MaterialTheme.typography.headlineMedium)
        Text("30 original WAEC-style questions · 30 minutes · Four options per question")
        Text(
            "These are original MeritPrep practice questions, not official WAEC past questions.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (!started) {
            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Before you begin", style = MaterialTheme.typography.titleLarge)
                    Text("Choose one answer for each question. You may change an answer before submitting. Unanswered questions receive no marks. The timer starts when you begin.")
                    Button(onClick = { startedAt = System.currentTimeMillis(); remainingMs = CHEMISTRY_WAEC_DURATION_MS; started = true }) {
                        Text("Start 30-minute assessment")
                    }
                }
            }
        } else {
            val minutes = remainingMs / 60000L
            val seconds = (remainingMs % 60000L) / 1000L
            Text("Time remaining: %02d:%02d".format(minutes, seconds), style = MaterialTheme.typography.titleLarge)
            LinearProgressIndicator(
                progress = { remainingMs.toFloat() / CHEMISTRY_WAEC_DURATION_MS.toFloat() },
                modifier = Modifier.fillMaxWidth()
            )
            Text("Answered: ${answers.size} / ${questions.size}")
            if (!submitted) {
                questions.forEachIndexed { index, question ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Question ${index + 1}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                            Text(question.prompt, style = MaterialTheme.typography.titleMedium)
                            question.options.forEachIndexed { optionIndex, option ->
                                OutlinedButton(
                                    onClick = { answers[question.questionId] = optionIndex },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = if (answers[question.questionId] == optionIndex)
                                        ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                                    else ButtonDefaults.outlinedButtonColors()
                                ) {
                                    Text((if (answers[question.questionId] == optionIndex) "✓  " else "") + option)
                                }
                            }
                        }
                    }
                }
                Button(
                    onClick = { submitted = true },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Submit assessment") }
            } else {
                val score = result?.correctAnswers ?: 0
                val total = result?.totalQuestions ?: questions.size
                val percentage = result?.percentage ?: 0
                Card {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Assessment submitted", style = MaterialTheme.typography.titleLarge)
                        Text("$score / $total", style = MaterialTheme.typography.headlineLarge)
                        Text("$percentage%")
                        LinearProgressIndicator(progress = { percentage / 100f }, modifier = Modifier.fillMaxWidth())
                        Text("Your result is a record of this practice assessment, not a prediction of your WAEC grade.")
                    }
                }
                questions.forEachIndexed { index, question ->
                    val selected = answers[question.questionId] ?: -1
                    val correct = selected == question.correctPresentedIndex
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Question ${index + 1} · " + if (correct) "Correct" else "Review", style = MaterialTheme.typography.labelLarge, color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                            Text(question.prompt, style = MaterialTheme.typography.titleMedium)
                            Text("Your answer: " + if (selected < 0) "Not answered" else question.options[selected])
                            Text("Correct answer: ${question.options[question.correctPresentedIndex]}")
                            Text(question.explanation)
                        }
                    }
                }
            }
        }
    }
}
