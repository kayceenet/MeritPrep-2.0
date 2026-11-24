package com.kaycee.meritprep

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.kaycee.meritprep.data.AssessmentQuestion
import com.kaycee.meritprep.data.CbtResult
import com.kaycee.meritprep.data.CbtSessionEngine
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.MistakeNote
import com.kaycee.meritprep.data.QuestionAttemptRecord
import com.kaycee.meritprep.data.PresentedQuestion
import kotlinx.coroutines.delay
import java.util.UUID

enum class CbtMode(
    val sessionSubjectId: String,
    val title: String,
    val subtitle: String,
    val questionCount: Int,
    val durationSeconds: Int,
    val durationLabel: String,
    val resultTitle: String
) {
    JAMB_UTME(
        "BIO", "Timed Biology CBT",
        "A JAMB-style practice mock built from MeritPrep's original Biology question bank.",
        40, 30 * 60, "30-minute countdown", "Biology JAMB-style practice result"
    ),
    WAEC_OBJECTIVE(
        "BIO_WAEC", "WAEC Biology Objective Practice",
        "A timed objective practice session built from MeritPrep's original Biology question bank.",
        50, 60 * 60, "60-minute MeritPrep practice timer", "Biology WAEC-style objective result"
    ),
    PHYSICS_UTME(
        "PHY", "Timed Physics CBT",
        "A JAMB-style Physics practice mock drawn from all 39 syllabus-mapped topics in MeritPrep's original question bank.",
        40, 30 * 60, "30-minute MeritPrep practice timer", "Physics JAMB-style practice result"
    ),
    WAEC_PHYSICS_OBJECTIVE(
        "PHY_WAEC", "WAEC Physics Objective Practice",
        "50 original MeritPrep Physics questions in a WAEC-style objective session. The timer follows a working syllabus reference and must be verified for the candidate's examination session.",
        50, 75 * 60, "75-minute working-reference practice timer", "Physics WAEC-style objective result"
    )
}

@Composable
fun CbtScreen(mode: CbtMode = CbtMode.JAMB_UTME, questionPool: List<AssessmentQuestion> = BiologyQuestionBank.all) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var questions by remember { mutableStateOf(emptyList<PresentedQuestion>()) }
    val answers = remember { mutableStateMapOf<String, Int>() }
    val flags = remember { mutableStateMapOf<String, Boolean>() }
    var currentIndex by remember { mutableIntStateOf(0) }
    var remainingSeconds by remember(mode) { mutableIntStateOf(mode.durationSeconds) }
    var started by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }
    var showSubmitDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<CbtResult?>(null) }
    var sessionId by remember { mutableStateOf<String?>(null) }
    var endsAtMillis by remember { mutableLongStateOf(0L) }
    var restoring by remember { mutableStateOf(true) }

    LaunchedEffect(database) {
        val snapshot = database.loadActiveCbtSession(mode.sessionSubjectId)
        if (snapshot != null) {
            val sourceById = questionPool.associateBy { it.id }
            val restoredQuestions = snapshot.questions.mapNotNull { saved ->
                val source = sourceById[saved.questionId] ?: return@mapNotNull null
                PresentedQuestion(
                    questionId = source.id,
                    topicId = source.topicId,
                    prompt = source.prompt,
                    options = saved.options,
                    correctPresentedIndex = saved.correctPresentedIndex,
                    explanation = source.explanation
                )
            }
            if (restoredQuestions.size == snapshot.totalQuestions) {
                questions = restoredQuestions
                snapshot.questions.forEach { saved ->
                    if (saved.selectedIndex != null) answers[saved.questionId] = saved.selectedIndex
                    if (saved.flagged) flags[saved.questionId] = true
                }
                sessionId = snapshot.sessionId
                currentIndex = snapshot.currentIndex.coerceIn(0, restoredQuestions.lastIndex)
                endsAtMillis = snapshot.endsAt
                remainingSeconds = ((snapshot.endsAt - System.currentTimeMillis()) / 1000L).toInt().coerceAtLeast(0)
                started = true
            } else {
                database.abandonCbtSession(snapshot.sessionId)
            }
        }
        restoring = false
    }

    LaunchedEffect(sessionId, currentIndex) {
        sessionId?.let { database.updateCbtCurrentIndex(it, currentIndex) }
    }

    fun finishSession() {
        if (!submitted && questions.isNotEmpty()) {
            val finalResult = CbtSessionEngine.score(questions, answers)
            val submittedAt = System.currentTimeMillis()
            val attemptRecords = questions.map { question ->
                val selected = answers[question.questionId]
                QuestionAttemptRecord(
                    questionId = question.questionId,
                    topicId = question.topicId,
                    isCorrect = selected != null && selected == question.correctPresentedIndex
                )
            }
            val mistakes = questions.mapNotNull { question ->
                val selected = answers[question.questionId]
                if (selected == question.correctPresentedIndex) null else MistakeNote(
                    mistakeId = UUID.randomUUID().toString(),
                    questionId = question.questionId,
                    topicId = question.topicId,
                    prompt = question.prompt,
                    selectedAnswer = selected?.let { question.options.getOrNull(it) } ?: "Not answered",
                    correctAnswer = question.options[question.correctPresentedIndex],
                    explanation = question.explanation,
                    createdAt = submittedAt,
                    isResolved = false
                )
            }
            sessionId?.let { database.completeCbtSession(it, finalResult.correctAnswers, attemptRecords, mistakes, submittedAt) }
            result = finalResult
            submitted = true
            showSubmitDialog = false
        }
    }

    LaunchedEffect(started, submitted, endsAtMillis) {
        while (started && !submitted) {
            val secondsLeft = ((endsAtMillis - System.currentTimeMillis()) / 1000L).toInt().coerceAtLeast(0)
            remainingSeconds = secondsLeft
            if (secondsLeft <= 0) {
                finishSession()
                break
            }
            delay(1_000)
        }
    }

    if (restoring) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    } else if (!started) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(mode.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(mode.subtitle)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Practice session", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("${mode.questionCount} randomly selected questions")
                    Text(mode.durationLabel)
                    Text("Answer navigation, review flags and final explanations")
                    HorizontalDivider()
                    Text("These are original, unverified practice questions, not official JAMB past questions. The session length is a MeritPrep practice setting, not a claim about the official examination timing.", style = MaterialTheme.typography.bodySmall)
                }
            }
            Button(
                onClick = {
                    val generated = CbtSessionEngine.createQuestions(questionPool, mode.questionCount)
                    val now = System.currentTimeMillis()
                    val newSessionId = UUID.randomUUID().toString()
                    database.createCbtSession(newSessionId, mode.sessionSubjectId, now, now + mode.durationSeconds * 1000L, generated)
                    questions = generated
                    answers.clear()
                    flags.clear()
                    currentIndex = 0
                    remainingSeconds = mode.durationSeconds
                    endsAtMillis = now + mode.durationSeconds * 1000L
                    sessionId = newSessionId
                    result = null
                    submitted = false
                    started = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Start timed mock") }
        }
    } else if (submitted) {
        val summary = result ?: CbtSessionEngine.score(questions, answers)
        Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Mock completed", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SummaryTile("Score", "${summary.correctAnswers}/${summary.totalQuestions}", Modifier.weight(1f))
                SummaryTile("Accuracy", "${summary.percentage}%", Modifier.weight(1f))
                SummaryTile("Unanswered", summary.unanswered.toString(), Modifier.weight(1f))
            }
            val topicPerformance = CbtSessionEngine.topicPerformance(questions, answers)
            Text("Topic performance", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Use this breakdown to choose what to revise next. Unanswered questions count as not correct.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            topicPerformance.forEach { performance ->
                val topicTitle = LearningCatalog.find(performance.topicId)?.title ?: performance.topicId
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(topicTitle, fontWeight = FontWeight.SemiBold)
                        Text("${performance.correctAnswers}/${performance.totalQuestions} correct · ${performance.accuracyPercent}%")
                        LinearProgressIndicator(
                            progress = { performance.accuracyPercent / 100f },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Text("Answer review", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(questions, key = { _, q -> q.questionId }) { index, question ->
                    val chosen = answers[question.questionId]
                    val correct = chosen == question.correctPresentedIndex
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text("Question ${index + 1} · ${if (correct) "Correct" else "Review"}", fontWeight = FontWeight.Bold, color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                            Text(question.prompt, fontWeight = FontWeight.SemiBold)
                            Text("Your answer: ${chosen?.let { question.options.getOrNull(it) } ?: "Not answered"}")
                            Text("Correct answer: ${question.options[question.correctPresentedIndex]}")
                            Text(question.explanation, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Button(
                onClick = {
                    started = false
                    submitted = false
                    questions = emptyList()
                    answers.clear()
                    flags.clear()
                    result = null
                    sessionId = null
                    endsAtMillis = 0L
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Return to CBT start") }
        }
    } else {
        val question = questions[currentIndex]
        val answeredCount = answers.size
        val flaggedCount = flags.values.count { it }
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(mode.resultTitle, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Question ${currentIndex + 1} of ${questions.size}", style = MaterialTheme.typography.bodySmall)
                }
                Surface(color = if (remainingSeconds <= 300) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                    Text(formatCbtTime(remainingSeconds), Modifier.padding(horizontal = 12.dp, vertical = 8.dp), fontWeight = FontWeight.Bold)
                }
            }
            LinearProgressIndicator(progress = { answeredCount.toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Answered $answeredCount/${questions.size}", style = MaterialTheme.typography.labelMedium)
                Text("Flagged $flaggedCount", style = MaterialTheme.typography.labelMedium)
            }
            Text("Question navigator", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                questions.forEachIndexed { index, item ->
                    val isCurrent = index == currentIndex
                    val isAnswered = answers.containsKey(item.questionId)
                    val isFlagged = flags[item.questionId] == true
                    FilledTonalButton(
                        onClick = { currentIndex = index },
                        modifier = Modifier.size(42.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = when {
                                isCurrent -> MaterialTheme.colorScheme.primary
                                isFlagged -> MaterialTheme.colorScheme.tertiaryContainer
                                isAnswered -> MaterialTheme.colorScheme.secondaryContainer
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            contentColor = if (isCurrent) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                        )
                    ) { Text(if (isFlagged) "${index + 1}⚑" else "${index + 1}") }
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("QUESTION ${currentIndex + 1}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text(question.prompt, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    question.options.forEachIndexed { optionIndex, option ->
                        OutlinedButton(
                            onClick = {
                                answers[question.questionId] = optionIndex
                                sessionId?.let { database.saveCbtAnswer(it, question.questionId, optionIndex, flags[question.questionId] == true) }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (answers[question.questionId] == optionIndex) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text((if (answers[question.questionId] == optionIndex) "✓  " else "") + option)
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        val nextFlag = flags[question.questionId] != true
                        flags[question.questionId] = nextFlag
                        sessionId?.let { database.saveCbtAnswer(it, question.questionId, answers[question.questionId], nextFlag) }
                    },
                    modifier = Modifier.weight(1f)
                ) { Text(if (flags[question.questionId] == true) "Unflag" else "Flag for review") }
                OutlinedButton(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    modifier = Modifier.weight(1f)
                ) { Text("Previous") }
                Button(
                    onClick = { if (currentIndex < questions.lastIndex) currentIndex++ },
                    enabled = currentIndex < questions.lastIndex,
                    modifier = Modifier.weight(1f)
                ) { Text(if (currentIndex == questions.lastIndex) "Last" else "Next") }
            }
            OutlinedButton(onClick = { showSubmitDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Submit mock")
            }
        }
    }

    if (showSubmitDialog && !submitted) {
        val unanswered = questions.count { !answers.containsKey(it.questionId) }
        val flagged = flags.values.count { it }
        AlertDialog(
            onDismissRequest = { showSubmitDialog = false },
            title = { Text("Submit this mock?") },
            text = { Text("$unanswered question(s) are unanswered and $flagged question(s) are flagged. You cannot change answers after submission.") },
            confirmButton = { Button(onClick = { finishSession() }) { Text("Submit now") } },
            dismissButton = { TextButton(onClick = { showSubmitDialog = false }) { Text("Continue mock") } }
        )
    }
}

@Composable
private fun SummaryTile(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
    }
}

private fun formatCbtTime(seconds: Int): String {
    val minutes = (seconds.coerceAtLeast(0) / 60).toString().padStart(2, '0')
    val remainder = (seconds.coerceAtLeast(0) % 60).toString().padStart(2, '0')
    return "$minutes:$remainder"
}
