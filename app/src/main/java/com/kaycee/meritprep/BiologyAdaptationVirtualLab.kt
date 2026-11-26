package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONArray
import org.json.JSONObject

internal enum class SelectionHabitat(val label: String) {
    DARK_BARK("Dark tree bark"),
    LIGHT_SAND("Light sand"),
    MIXED("Mixed background")
}

internal data class AdaptationSelectionConditions(
    val habitat: SelectionHabitat,
    val initialDarkPercent: Int,
    val selectionIntensityPercent: Int,
    val generations: Int
) {
    init {
        require(initialDarkPercent in 1..99)
        require(selectionIntensityPercent in 10..90)
        require(generations in 1..20)
    }

    fun toSimulationConditions(): NaturalSelectionConditions {
        val darkFitness = when (habitat) {
            SelectionHabitat.DARK_BARK -> 100.0
            SelectionHabitat.LIGHT_SAND -> 100.0 - selectionIntensityPercent
            SelectionHabitat.MIXED -> 100.0
        }
        val lightFitness = when (habitat) {
            SelectionHabitat.DARK_BARK -> 100.0 - selectionIntensityPercent
            SelectionHabitat.LIGHT_SAND -> 100.0
            SelectionHabitat.MIXED -> 100.0
        }
        return NaturalSelectionConditions(
            initialLightTraitCount = 100 - initialDarkPercent,
            initialDarkTraitCount = initialDarkPercent,
            lightTraitSurvivalPercent = lightFitness,
            darkTraitSurvivalPercent = darkFitness,
            generations = generations
        )
    }
}

@Composable
internal fun BiologyAdaptationVirtualLab() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val noteId = "micro-lab-session-bio-adaptation"
    val saved = remember(context) {
        runCatching { JSONObject(database.getNoteBody(noteId) ?: "{}") }.getOrElse { JSONObject() }
    }
    var habitat by remember { mutableStateOf(runCatching { SelectionHabitat.valueOf(saved.optString("habitat", "DARK_BARK")) }.getOrDefault(SelectionHabitat.DARK_BARK)) }
    var initialDark by remember { mutableIntStateOf(saved.optInt("initialDarkPercent", 50).coerceIn(1, 99)) }
    var intensity by remember { mutableFloatStateOf(saved.optInt("selectionIntensityPercent", 60).coerceIn(10, 90).toFloat()) }
    var generations by remember { mutableFloatStateOf(saved.optInt("generations", 8).coerceIn(1, 20).toFloat()) }
    var prediction by remember { mutableStateOf(saved.optString("prediction")) }
    var conclusion by remember { mutableStateOf(saved.optString("conclusion")) }
    var hasRun by remember { mutableStateOf(saved.optBoolean("hasRun")) }

    val conditions = remember(habitat, initialDark, intensity, generations) {
        AdaptationSelectionConditions(habitat, initialDark, intensity.toInt(), generations.toInt())
    }
    val result = remember(conditions) { NaturalSelectionSimulation.evaluate(conditions.toSimulationConditions()) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("Virtual investigation: natural selection and camouflage", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Scientific question: If a population contains inherited light and dark colour variants, how can background colour and differential survival change the frequency of each variant over generations?")
                Text("Learning outcomes: explain selection pressure; predict which inherited variant may become more common; interpret a frequency table; distinguish population change from an individual changing its own inherited trait.", style = MaterialTheme.typography.bodySmall)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("Apparatus and safety", fontWeight = FontWeight.Bold)
                Text("This is an offline computer model. No live animals, chemicals or physical apparatus are used. No biological hazard is introduced by the simulation.")
                Text("Physical practical extension: observe naturally occurring colour variation only under teacher guidance. Do not capture, injure or expose organisms to predators or harmful conditions for this exercise.", style = MaterialTheme.typography.bodySmall)
                HorizontalDivider()
                Text("Variables", fontWeight = FontWeight.Bold)
                Text("Independent: background habitat and selection-intensity setting. Dependent: percentage frequency of the light and dark inherited variants. Controlled: starting population composition, number of generations and the simplified model rules.")
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Method and prediction", fontWeight = FontWeight.Bold)
                Text("1. Set the initial proportion of dark and light variants. 2. Choose a background. 3. Set the modelled selection intensity. 4. Predict which variant will become more frequent and explain why. 5. Run the generations. 6. Record the frequency changes. 7. Repeat under a different background and compare.")
                Text("Choose the background", fontWeight = FontWeight.Bold)
                SelectionHabitat.entries.forEach { option ->
                    FilterChip(selected = habitat == option, onClick = { habitat = option; hasRun = false }, label = { Text(option.label) })
                }
                Text("Initial dark variant: $initialDark%  •  light variant: ${100 - initialDark}%")
                Slider(value = initialDark.toFloat(), onValueChange = { initialDark = it.toInt(); hasRun = false }, valueRange = 1f..99f)
                Text("Selection intensity: ${intensity.toInt()}%")
                Slider(value = intensity, onValueChange = { intensity = it; hasRun = false }, valueRange = 10f..90f)
                Text("Number of generations: ${generations.toInt()}")
                Slider(value = generations, onValueChange = { generations = it; hasRun = false }, valueRange = 1f..20f, steps = 18)
                OutlinedTextField(value = prediction, onValueChange = { prediction = it; hasRun = false }, modifier = Modifier.fillMaxWidth(), minLines = 2, label = { Text("Predict which variant will increase and explain your reasoning") })
                Button(onClick = { hasRun = true }, enabled = prediction.isNotBlank(), modifier = Modifier.fillMaxWidth()) { Text("Run selection model") }
            }
        }
        if (hasRun) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("SIMULATED POPULATION DATA", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("The survival/reproductive-success values are hypothetical relative indices selected for teaching. They are not measured survival probabilities from a real habitat.")
                    Text("Modelled relative values — light: ${conditions.toSimulationConditions().lightTraitSurvivalPercent.toInt()} • dark: ${conditions.toSimulationConditions().darkTraitSurvivalPercent.toInt()}", style = MaterialTheme.typography.bodySmall)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Generation", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.7f))
                        Text("Light (%)", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.65f))
                        Text("Dark (%)", fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.65f))
                    }
                    result.generations.forEach { row ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(row.generation.toString(), modifier = Modifier.weight(0.7f))
                            Text("%.1f".format(row.lightTraitFrequencyPercent), modifier = Modifier.weight(0.65f))
                            Text("%.1f".format(row.darkTraitFrequencyPercent), modifier = Modifier.weight(0.65f))
                        }
                    }
                    HorizontalDivider()
                    Text("Favoured variant in this model: ${result.moreFavouredTrait}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(result.explanation)
                    Text("Final frequencies", fontWeight = FontWeight.SemiBold)
                    Text("Light variant: ${"%.1f".format(result.generations.last().lightTraitFrequencyPercent)}%")
                    LinearProgressIndicator(progress = { (result.generations.last().lightTraitFrequencyPercent / 100.0).toFloat() }, modifier = Modifier.fillMaxWidth())
                    Text("Dark variant: ${"%.1f".format(result.generations.last().darkTraitFrequencyPercent)}%")
                    LinearProgressIndicator(progress = { (result.generations.last().darkTraitFrequencyPercent / 100.0).toFloat() }, modifier = Modifier.fillMaxWidth())
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Analyse and conclude", fontWeight = FontWeight.Bold)
                    Text("Compare the starting and final frequencies. Explain how the environmental background changes which variant is less conspicuous. State why the model requires the colour trait to be heritable.")
                    OutlinedTextField(value = conclusion, onValueChange = { conclusion = it }, modifier = Modifier.fillMaxWidth(), minLines = 3, label = { Text("Write an evidence-based conclusion using values from the table") })
                    Text("Model assumptions and limitations: only two inherited phenotypes are represented; relative fitness remains constant; the environment does not change; population size is normalised; and mutation, migration, genetic drift, age structure and other ecological interactions are omitted. This deterministic model illustrates a principle; it is not a prediction for a real population.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Button(onClick = {
                        val rows = JSONArray()
                        result.generations.forEach { row ->
                            rows.put(JSONObject()
                                .put("generation", row.generation)
                                .put("lightFrequencyPercent", row.lightTraitFrequencyPercent)
                                .put("darkFrequencyPercent", row.darkTraitFrequencyPercent))
                        }
                        val body = JSONObject()
                            .put("habitat", habitat.name)
                            .put("initialDarkPercent", initialDark)
                            .put("selectionIntensityPercent", intensity.toInt())
                            .put("generations", generations.toInt())
                            .put("prediction", prediction)
                            .put("conclusion", conclusion)
                            .put("hasRun", hasRun)
                            .put("simulatedRows", rows)
                        runCatching { database.saveNote(noteId, "bio-adaptation", "Natural selection virtual investigation", body.toString()) }
                    }, modifier = Modifier.fillMaxWidth()) { Text("Save investigation record on this device") }
                }
            }
        }
    }
}
