package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A short, sequential retrieval-practice check using the authored cell-division question bank.
 * Progress is session-local; the main lesson completion and attempt history remain unchanged.
 */
@Composable
fun CellDivisionKnowledgeCheckCard() {
    val questions = remember {
        BiologyQuestionBank.all
            .filter { it.id in (11..22).map { number -> "bio-grow-${number.toString().padStart(3, '0')}" } }
            .sortedBy { it.id }
    }
    var questionIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    Card(Modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Cell division knowledge check", style = MaterialTheme.typography.titleLarge)
            Text(
                "Test your understanding of chromosome behaviour, mitosis and meiosis. This practice check is separate from the lesson-completion check.",
                style = MaterialTheme.typography.bodyMedium
            )
            if (questions.isEmpty()) {
                Text("The cell-division question set is not available yet.")
            } else if (finished) {
                Text("You scored $score out of ${questions.size}.", style = MaterialTheme.typography.titleMedium)
                Text(
                    if (score == questions.size) "Excellent. You answered every question correctly."
                    else "Review the explanations in the questions you missed, then try again."
                )
                Button(
                    onClick = {
                        questionIndex = 0
                        selectedOption = -1
                        checked = false
                        score = 0
                        finished = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Try the check again") }
            } else {
                val question = questions[questionIndex]
                Text("Question ${questionIndex + 1} of ${questions.size}", color = MaterialTheme.colorScheme.primary)
                LinearProgressIndicator(
                    progress = { (questionIndex + 1).toFloat() / questions.size },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(question.prompt, style = MaterialTheme.typography.titleMedium)
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
                    val correct = selectedOption == question.correctOptionIndex
                    Text(
                        if (correct) "Correct" else "Not quite",
                        color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                    Text(question.explanation, style = MaterialTheme.typography.bodyMedium)
                    Button(
                        onClick = {
                            if (correct) score += 1
                            if (questionIndex == questions.lastIndex) {
                                finished = true
                            } else {
                                questionIndex += 1
                                selectedOption = -1
                                checked = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(if (questionIndex == questions.lastIndex) "See my result" else "Next question") }
                } else {
                    Button(
                        onClick = { checked = true },
                        enabled = selectedOption >= 0,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Check answer") }
                }
            }
        }
    }
}
