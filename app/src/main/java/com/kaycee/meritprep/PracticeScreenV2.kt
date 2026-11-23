package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.AssessmentEngine
import com.kaycee.meritprep.data.MeritDatabase

@Composable
fun PracticeScreenV2() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val biologyTopics = remember { LearningCatalog.forSubject("BIO") }
    var selectedTopicId by remember { mutableStateOf("bio-cell") }
    var topicMenuExpanded by remember { mutableStateOf(false) }
    val questions = remember(selectedTopicId) {
        val selected = if (selectedTopicId == "all-biology") {
            BiologyQuestionBank.all
        } else {
            BiologyQuestionBank.all.filter { it.topicId == selectedTopicId }
        }
        selected.shuffled().map { AssessmentEngine.present(it) }
    }

    var index by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var complete by remember { mutableStateOf(false) }

    val selectedTopicTitle = if (selectedTopicId == "all-biology") {
        "All Biology topics"
    } else {
        biologyTopics.firstOrNull { it.id == selectedTopicId }?.title ?: "Biology"
    }

    fun resetSession() {
        index = 0
        selectedOption = -1
        checked = false
        score = 0
        complete = false
    }

    if (complete) {
        Column(
            Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Practice complete", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Score: $score / ${questions.size}", style = MaterialTheme.typography.titleLarge)
            LinearProgressIndicator(
                progress = { if (questions.isEmpty()) 0f else score.toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth()
            )
            Text("Review the explanations and revisit any concept you found difficult. This score describes this practice session; it is not an examination prediction.")
            Button(onClick = { resetSession() }) { Text("Practise again") }
        }
    } else if (questions.isEmpty()) {
        Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("No questions available", style = MaterialTheme.typography.headlineSmall)
            Text("There are no practice items mapped to this topic yet.")
            OutlinedButton(onClick = { selectedTopicId = "all-biology"; resetSession() }) {
                Text("Open all Biology questions")
            }
        }
    } else {
        val question = questions[index]
        Column(
            Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Biology practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Choose a focused topic or practise the complete Biology bank.")
            Box {
                OutlinedButton(onClick = { topicMenuExpanded = true }) {
                    Text(selectedTopicTitle + "  ·  Change topic")
                }
                DropdownMenu(
                    expanded = topicMenuExpanded,
                    onDismissRequest = { topicMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("All Biology topics · ${BiologyQuestionBank.all.size} questions") },
                        onClick = {
                            topicMenuExpanded = false
                            selectedTopicId = "all-biology"
                            resetSession()
                        }
                    )
                    biologyTopics.forEach { topic ->
                        val count = BiologyQuestionBank.all.count { it.topicId == topic.id }
                        DropdownMenuItem(
                            text = { Text("${topic.title} · $count") },
                            onClick = {
                                topicMenuExpanded = false
                                selectedTopicId = topic.id
                                resetSession()
                            }
                        )
                    }
                }
            }
            Text("Question ${index + 1} of ${questions.size}", style = MaterialTheme.typography.labelLarge)
            LinearProgressIndicator(
                progress = { (index + 1).toFloat() / questions.size },
                modifier = Modifier.fillMaxWidth()
            )
            Text(question.prompt, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            question.options.forEachIndexed { optionIndex, option ->
                OutlinedButton(
                    onClick = { if (!checked) selectedOption = optionIndex },
                    enabled = !checked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text((if (selectedOption == optionIndex) "✓  " else "") + option)
                }
            }
            if (checked) {
                val correct = selectedOption == question.correctPresentedIndex
                Text(
                    if (correct) "Correct" else "Not correct",
                    color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
                Text(question.explanation)
                Button(
                    onClick = {
                        if (index == questions.lastIndex) {
                            complete = true
                        } else {
                            index++
                            selectedOption = -1
                            checked = false
                        }
                    }
                ) {
                    Text(if (index == questions.lastIndex) "View result" else "Next question")
                }
            } else {
                Button(
                    enabled = selectedOption >= 0,
                    onClick = {
                        val correct = selectedOption == question.correctPresentedIndex
                        database.recordQuestionAttempt(question.topicId, correct)
                        if (correct) score++
                        checked = true
                    }
                ) {
                    Text("Check answer")
                }
            }
            Text(
                "These are original MeritPrep practice items, not official JAMB or WAEC past questions. Their answer keys are awaiting independent academic review.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
