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
import java.util.UUID

@Composable
fun PhysicsPracticeScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val topics = remember { PhysicsCourse.topics.filterNot { it.isSyllabusOutline } }
    var selectedTopicId by remember { mutableStateOf("all-physics") }
    var topicMenuExpanded by remember { mutableStateOf(false) }
    val questions = remember(selectedTopicId) {
        val source = if (selectedTopicId == "all-physics") PhysicsQuestionBank.all
        else PhysicsQuestionBank.forTopic(selectedTopicId)
        source.shuffled().map { AssessmentEngine.present(it) }
    }
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var completed by remember { mutableStateOf(false) }
    fun reset() { index = 0; selected = -1; checked = false; score = 0; completed = false }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Physics practice", style = MaterialTheme.typography.headlineMedium)
        Text("Original MeritPrep questions mapped to the developed Physics lessons. These are practice questions, not official JAMB or WAEC past questions.")
        Box {
            OutlinedButton(onClick = { topicMenuExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text(if (selectedTopicId == "all-physics") "All developed Physics topics" else topics.firstOrNull { it.id == selectedTopicId }?.title ?: "Choose topic")
            }
            DropdownMenu(expanded = topicMenuExpanded, onDismissRequest = { topicMenuExpanded = false }) {
                DropdownMenuItem(text = { Text("All developed topics · ${PhysicsQuestionBank.all.size}") }, onClick = { topicMenuExpanded = false; selectedTopicId = "all-physics"; reset() })
                topics.forEach { topic ->
                    DropdownMenuItem(text = { Text("${topic.title} · ${PhysicsQuestionBank.forTopic(topic.id).size}") }, onClick = { topicMenuExpanded = false; selectedTopicId = topic.id; reset() })
                }
            }
        }
        if (completed) {
            Text("Practice complete", style = MaterialTheme.typography.titleLarge)
            Text("Score: $score / ${questions.size}", style = MaterialTheme.typography.headlineSmall)
            LinearProgressIndicator(progress = { if (questions.isEmpty()) 0f else score.toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
            Text("This score describes this practice session only. It is not a prediction of your JAMB or WAEC result.")
            Button(onClick = { reset() }) { Text("Practise again") }
        } else if (questions.isNotEmpty()) {
            val question = questions[index]
            Text("Question ${index + 1} of ${questions.size}", style = MaterialTheme.typography.labelLarge)
            LinearProgressIndicator(progress = { (index + 1).toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
            ScientificText(question.prompt, style = MaterialTheme.typography.titleMedium)
            question.options.forEachIndexed { optionIndex, option ->
                OutlinedButton(onClick = { if (!checked) selected = optionIndex }, enabled = !checked, modifier = Modifier.fillMaxWidth()) {
                    ScientificText((if (selected == optionIndex) "✓  " else "") + option, style = MaterialTheme.typography.bodyMedium)
                }
            }
            if (!checked) {
                Button(onClick = {
                    if (selected >= 0) {
                        checked = true
                        val correct = selected == question.correctPresentedIndex
                        if (correct) score++
                        database.recordQuestionAttempt(question.topicId, correct, question.questionId)
                        if (!correct) {
                            database.saveMistake(MistakeNote(UUID.randomUUID().toString(), question.questionId, question.topicId, question.prompt, question.options[selected], question.options[question.correctPresentedIndex], question.explanation, System.currentTimeMillis(), false))
                        }
                    }
                }, enabled = selected >= 0, modifier = Modifier.fillMaxWidth()) { Text("Check answer") }
            } else {
                val correct = selected == question.correctPresentedIndex
                Text(if (correct) "Correct" else "Not quite", color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium)
                ScientificText(question.explanation, style = MaterialTheme.typography.bodyMedium)
                Button(onClick = {
                    if (index == questions.lastIndex) completed = true
                    else { index++; selected = -1; checked = false }
                }, modifier = Modifier.fillMaxWidth()) { Text(if (index == questions.lastIndex) "Finish practice" else "Next question") }
            }
        } else {
            Text("No Physics questions are available for this topic yet.")
        }
    }
}
