package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONObject

internal enum class ClassificationKeyTrack(val label: String) {
    PLANTS("Plant groups"),
    ANIMALS("Animal groups")
}

internal data class ClassificationKeyStep(
    val prompt: String,
    val yesMeaning: String,
    val noMeaning: String
)

internal data class ClassificationKeySpecimen(
    val id: String,
    val name: String,
    val track: ClassificationKeyTrack,
    val observedClues: String,
    val decisions: List<Boolean>,
    val result: String,
    val explanation: String
)

internal object ClassificationKeyModel {
    private val plantSteps = listOf(
        ClassificationKeyStep("Does the specimen have vascular tissue (xylem and phloem)?", "Yes, vascular tissue is present", "No, vascular tissue is absent"),
        ClassificationKeyStep("Does it produce seeds?", "Yes, seeds are produced", "No, it reproduces by spores"),
        ClassificationKeyStep("Are its seeds enclosed within fruits formed from flowers?", "Yes, flowers and fruits are present", "No, seeds are exposed, commonly on cone scales")
    )
    private val animalSteps = listOf(
        ClassificationKeyStep("Does the specimen have a vertebral column (backbone)?", "Yes, a vertebral column is present", "No, a vertebral column is absent"),
        ClassificationKeyStep("Does it have feathers?", "Yes, feathers are present", "No, feathers are absent"),
        ClassificationKeyStep("Does it have hair and mammary glands that produce milk?", "Yes, hair and mammary glands are present", "No, these mammalian features are absent"),
        ClassificationKeyStep("Does it have fins and gills for aquatic respiration throughout adult life?", "Yes, fins and gills are present", "No, this combination is absent"),
        ClassificationKeyStep("Does it have moist, permeable skin and a life cycle commonly linked to water?", "Yes, these amphibian features are present", "No, it has dry, keratinised scales typical of the reptile example in this key")
    )

    private val specimens = listOf(
        ClassificationKeySpecimen("moss", "Plant specimen A", ClassificationKeyTrack.PLANTS, "Small green plant; no true vascular tissue; reproduces by spores.", listOf(false), "Bryophyte (moss)", "Mosses are non-vascular plants. They do not have true xylem and phloem and reproduce by spores."),
        ClassificationKeySpecimen("fern", "Plant specimen B", ClassificationKeyTrack.PLANTS, "Has true roots, stems and leaves with vascular tissue; reproduces by spores; no seeds.", listOf(true, false), "Pteridophyte (fern)", "Ferns have vascular tissue but do not produce seeds. Their reproduction involves spores."),
        ClassificationKeySpecimen("pine", "Plant specimen C", ClassificationKeyTrack.PLANTS, "Woody vascular plant; produces seeds on cone scales; no flowers or fruits.", listOf(true, true, false), "Gymnosperm (conifer)", "Conifers are gymnosperms. Their seeds are not enclosed within an ovary-derived fruit."),
        ClassificationKeySpecimen("hibiscus", "Plant specimen D", ClassificationKeyTrack.PLANTS, "Vascular plant with flowers; seeds develop inside fruits.", listOf(true, true, true), "Angiosperm (flowering plant)", "Angiosperms produce flowers and seeds enclosed within fruits."),
        ClassificationKeySpecimen("earthworm", "Animal specimen A", ClassificationKeyTrack.ANIMALS, "Soft, segmented body; no internal vertebral column.", listOf(false), "Invertebrate", "Earthworms are annelid invertebrates. They have no vertebral column."),
        ClassificationKeySpecimen("pigeon", "Animal specimen B", ClassificationKeyTrack.ANIMALS, "Backbone present; body covered with feathers; forelimbs form wings.", listOf(true, true), "Vertebrate — bird (Aves)", "Feathers are the diagnostic feature used here for birds. Wings alone would not be sufficient because bats also have wings."),
        ClassificationKeySpecimen("bat", "Animal specimen C", ClassificationKeyTrack.ANIMALS, "Backbone present; body has hair; females feed young with milk; forelimbs form wings.", listOf(true, false, true), "Vertebrate — mammal (Mammalia)", "Hair and mammary glands identify mammals. Flight does not make a bat a bird."),
        ClassificationKeySpecimen("tilapia", "Animal specimen D", ClassificationKeyTrack.ANIMALS, "Backbone present; fins and gills function in aquatic life throughout adulthood.", listOf(true, false, false, true), "Vertebrate — fish", "Tilapia is a fish: it has fins and uses gills for aquatic respiration throughout adult life."),
        ClassificationKeySpecimen("frog", "Animal specimen E", ClassificationKeyTrack.ANIMALS, "Backbone present; no feathers or hair; adult has moist skin; reproduction and early development are linked to water.", listOf(true, false, false, false, true), "Vertebrate — amphibian (Amphibia)", "Frogs are amphibians. Their moist skin and life cycle linked to water distinguish the example from the reptile in this key."),
        ClassificationKeySpecimen("lizard", "Animal specimen F", ClassificationKeyTrack.ANIMALS, "Backbone present; no feathers or hair; no fins and gills; dry keratinised scales.", listOf(true, false, false, false, false), "Vertebrate — reptile (Reptilia)", "Lizards are reptiles. Dry, keratinised scales help reduce water loss.")
    )

    fun steps(track: ClassificationKeyTrack): List<ClassificationKeyStep> =
        if (track == ClassificationKeyTrack.PLANTS) plantSteps else animalSteps

    fun specimens(track: ClassificationKeyTrack): List<ClassificationKeySpecimen> =
        specimens.filter { it.track == track }

    fun specimen(id: String): ClassificationKeySpecimen? = specimens.firstOrNull { it.id == id }

    fun expectedDecision(specimenId: String, stepIndex: Int): Boolean? =
        specimen(specimenId)?.decisions?.getOrNull(stepIndex)

    fun isCorrectDecision(specimenId: String, stepIndex: Int, answerYes: Boolean): Boolean? =
        expectedDecision(specimenId, stepIndex)?.let { it == answerYes }

    fun identify(specimenId: String, answers: List<Boolean>): String? =
        specimen(specimenId)?.takeIf { answers == it.decisions }?.result

    fun isComplete(specimenId: String, completedSteps: Int): Boolean =
        specimen(specimenId)?.let { completedSteps >= it.decisions.size } ?: false
}

@Composable
internal fun BiologyClassificationKeyPractical() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val noteId = "micro-lab-session-bio-classification"
    val saved = remember(context) {
        runCatching { JSONObject(database.getNoteBody(noteId) ?: "{}") }.getOrElse { JSONObject() }
    }
    var track by remember { mutableStateOf(runCatching { ClassificationKeyTrack.valueOf(saved.optString("track", "PLANTS")) }.getOrDefault(ClassificationKeyTrack.PLANTS)) }
    var specimenId by remember { mutableStateOf(saved.optString("specimenId", "moss")) }
    var completedSteps by remember { mutableStateOf(saved.optInt("completedSteps", 0).coerceAtLeast(0)) }
    var selectedYes by remember { mutableStateOf<Boolean?>(null) }
    var feedback by remember { mutableStateOf("") }
    var feedbackIsError by remember { mutableStateOf(false) }
    var observationRecord by remember { mutableStateOf(saved.optString("observationRecord")) }
    val specimen = remember(track, specimenId) {
        ClassificationKeyModel.specimens(track).firstOrNull { it.id == specimenId }
            ?: ClassificationKeyModel.specimens(track).first()
    }
    val steps = remember(track) { ClassificationKeyModel.steps(track) }
    val identifiedResult = ClassificationKeyModel.identify(specimen.id, specimen.decisions.take(completedSteps))
    val finished = identifiedResult != null
    val stepIndex = completedSteps.coerceAtMost((steps.size - 1).coerceAtLeast(0))
    val decisionPath = steps.take(completedSteps).mapIndexed { index, step ->
        "${index + 1}. ${step.prompt} — ${if (specimen.decisions[index]) "Yes" else "No"}"
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("Practical skill: construct and use a dichotomous key", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("A dichotomous key identifies an organism through a sequence of paired alternatives. Each decision should use an observable feature, and the two alternatives should be mutually exclusive for the specimen being identified.")
                Text("Aim: use visible or stated diagnostic features to identify plant divisions and broad animal groups. This is an interactive identification exercise, not a substitute for examining real specimens.", style = MaterialTheme.typography.bodySmall)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Choose a key", fontWeight = FontWeight.Bold)
                ClassificationKeyTrack.entries.forEach { option ->
                    FilterChip(
                        selected = track == option,
                        onClick = {
                            track = option
                            specimenId = ClassificationKeyModel.specimens(option).first().id
                            completedSteps = 0
                            selectedYes = null
                            feedback = ""
                            feedbackIsError = false
                        },
                        label = { Text(option.label) }
                    )
                }
                Text("Choose an unknown specimen", fontWeight = FontWeight.Bold)
                ClassificationKeyModel.specimens(track).forEach { item ->
                    FilterChip(
                        selected = specimen.id == item.id,
                        onClick = {
                            specimenId = item.id
                            completedSteps = 0
                            selectedYes = null
                            feedback = ""
                            feedbackIsError = false
                        },
                        label = { Text(item.name) }
                    )
                }
                Text("Specimen observations", fontWeight = FontWeight.Bold)
                Text(specimen.observedClues)
            }
        }
        if (!finished) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Decision ${completedSteps + 1} of ${specimen.decisions.size}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(steps[stepIndex].prompt)
                    Text("Choose the statement that matches the specimen:", style = MaterialTheme.typography.bodySmall)
                    FilterChip(selected = selectedYes == true, onClick = { selectedYes = true; feedback = ""; feedbackIsError = false }, label = { Text(steps[stepIndex].yesMeaning) })
                    FilterChip(selected = selectedYes == false, onClick = { selectedYes = false; feedback = ""; feedbackIsError = false }, label = { Text(steps[stepIndex].noMeaning) })
                    Button(
                        onClick = {
                            val correct = selectedYes?.let { ClassificationKeyModel.isCorrectDecision(specimen.id, completedSteps, it) } == true
                            if (correct) {
                                completedSteps += 1
                                feedback = "Correct. Your choice follows the diagnostic feature stated in the key."
                                feedbackIsError = false
                                selectedYes = null
                            } else {
                                feedback = "Recheck the specimen observations. A dichotomous key must follow the feature actually present, not the habitat or your first guess."
                                feedbackIsError = true
                            }
                        },
                        enabled = selectedYes != null,
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Check this decision") }
                    if (feedback.isNotBlank()) Text(feedback, color = if (feedbackIsError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                    OutlinedButton(onClick = { completedSteps = 0; selectedYes = null; feedback = ""; feedbackIsError = false }, modifier = Modifier.fillMaxWidth()) { Text("Restart this specimen") }
                }
            }
        } else {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Identification reached", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text(identifiedResult.orEmpty(), style = MaterialTheme.typography.headlineSmall)
                    Text(specimen.explanation)
                    Text("Decision path", fontWeight = FontWeight.Bold)
                    decisionPath.forEach { Text(it, style = MaterialTheme.typography.bodySmall) }
                    Text("The identification is only as reliable as the observed diagnostic features. If a specimen is damaged, immature or atypical, use additional evidence and an appropriate key rather than forcing an uncertain identification.", style = MaterialTheme.typography.bodySmall)
                    Button(onClick = { completedSteps = 0; selectedYes = null; feedback = "" }, modifier = Modifier.fillMaxWidth()) { Text("Identify this specimen again") }
                }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Practical record", fontWeight = FontWeight.Bold)
                Text("Record the observable evidence and the sequence of decisions you used. Do not write only the final name.")
                androidx.compose.material3.OutlinedTextField(
                    value = observationRecord,
                    onValueChange = { observationRecord = it },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    label = { Text("Observation, decision path and identification") }
                )
                Button(onClick = {
                    val body = JSONObject()
                        .put("track", track.name)
                        .put("specimenId", specimen.id)
                        .put("completedSteps", completedSteps)
                        .put("decisionPath", decisionPath.joinToString("\n"))
                        .put("observationRecord", observationRecord)
                        .put("identified", finished)
                    runCatching { database.saveNote(noteId, "bio-classification", "Dichotomous key practical record", body.toString()) }
                }, modifier = Modifier.fillMaxWidth()) { Text("Save practical record on this device") }
            }
        }
    }
}
