package com.kaycee.meritprep

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONObject


private fun decodeScienceMediaProgress(raw: String?): Pair<Boolean, String> {
    if (raw.isNullOrBlank()) return false to ""
    return runCatching {
        val record = JSONObject(raw)
        record.optBoolean("opened", false) to record.optString("reflection", "")
    }.getOrElse { false to raw }
}

@Composable
fun ScienceMediaScreen(onOpenLabs: () -> Unit, onOpenLesson: (String) -> Unit) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var selectedSubject by remember { mutableIntStateOf(0) }
    var lightIntensity by remember { mutableFloatStateOf(35f) }
    var carbonDioxideIndex by remember { mutableFloatStateOf(55f) }
    var temperatureCelsius by remember { mutableFloatStateOf(25f) }
    var selectedEnzymeIndex by remember { mutableIntStateOf(0) }
    var enzymeTemperature by remember { mutableFloatStateOf(37f) }
    var enzymePh by remember { mutableFloatStateOf(7f) }
    var initialLightCount by remember { mutableFloatStateOf(50f) }
    var initialDarkCount by remember { mutableFloatStateOf(50f) }
    var lightSurvival by remember { mutableFloatStateOf(45f) }
    var darkSurvival by remember { mutableFloatStateOf(80f) }
    var selectionGenerations by remember { mutableFloatStateOf(5f) }
    val selectionResult = NaturalSelectionSimulation.evaluate(
        NaturalSelectionConditions(
            initialLightCount.toInt(),
            initialDarkCount.toInt(),
            lightSurvival.toDouble(),
            darkSurvival.toDouble(),
            selectionGenerations.toInt()
        )
    )
    val enzymeProfile = EnzymeProfile.values()[selectedEnzymeIndex]
    val enzymeActivity = EnzymeActivitySimulation.evaluate(
        EnzymeConditions(enzymeProfile, enzymeTemperature.toDouble(), enzymePh.toDouble())
    )
    val photosynthesis = PhotosynthesisSimulation.evaluate(
        PhotosynthesisConditions(lightIntensity.toDouble(), carbonDioxideIndex.toDouble(), temperatureCelsius.toDouble())
    )
    val subjectCodes = listOf("ALL", "BIO", "CHEM", "PHY")
    val subjectLabels = listOf("All", "Biology", "Chemistry", "Physics")
    val resources = if (selectedSubject == 0) ScienceMediaCatalog.resources
        else ScienceMediaCatalog.forSubject(subjectCodes[selectedSubject])

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Explore visual science", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("Animations, interactive models and virtual practicals connected to your science topics.")
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("How to use these resources", fontWeight = FontWeight.Bold)
                    Text("Before opening a resource, read the prediction prompt. Explore the original activity, then return to MeritPrep and answer the reflection prompt. These are curated external links, not copied media.")
                    Text("Internet access is required for external resources. Each card includes a local MeritPrep alternative.")
                    OutlinedButton(onClick = onOpenLabs, modifier = Modifier.fillMaxWidth()) {
                        Text("Open MeritPrep virtual laboratories")
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                subjectLabels.forEachIndexed { index, label ->
                    FilterChip(
                        selected = selectedSubject == index,
                        onClick = { selectedSubject = index },
                        label = { Text(label) }
                    )
                }
            }
        }
        if (selectedSubject == 0 || selectedSubject == 1) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("MeritPrep interactive model: natural selection", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Model a heritable light/dark trait in a population. Change the starting frequencies and relative survival values, then follow the trait frequencies over generations.")
                        Text("Starting light-trait individuals: ${initialLightCount.toInt()}")
                        Slider(value = initialLightCount, onValueChange = { initialLightCount = it }, valueRange = 1f..100f)
                        Text("Starting dark-trait individuals: ${initialDarkCount.toInt()}")
                        Slider(value = initialDarkCount, onValueChange = { initialDarkCount = it }, valueRange = 1f..100f)
                        Text("Light-trait survival/reproductive success index: ${lightSurvival.toInt()}%")
                        Slider(value = lightSurvival, onValueChange = { lightSurvival = it; if (it == 0f && darkSurvival == 0f) darkSurvival = 1f }, valueRange = 0f..100f)
                        Text("Dark-trait survival/reproductive success index: ${darkSurvival.toInt()}%")
                        Slider(value = darkSurvival, onValueChange = { darkSurvival = it; if (it == 0f && lightSurvival == 0f) lightSurvival = 1f }, valueRange = 0f..100f)
                        Text("Generations: ${selectionGenerations.toInt()}")
                        Slider(value = selectionGenerations, onValueChange = { selectionGenerations = it }, valueRange = 1f..10f, steps = 8)
                        Text("Trait with higher modelled success: ${selectionResult.moreFavouredTrait}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        Text(selectionResult.explanation)
                        Text("Generation-by-generation trait frequencies", fontWeight = FontWeight.SemiBold)
                        selectionResult.generations.forEach { generation ->
                            Text("Generation ${generation.generation}: light ${"%.1f".format(generation.lightTraitFrequencyPercent)}% · dark ${"%.1f".format(generation.darkTraitFrequencyPercent)}%")
                        }
                        Text("Investigate: set both survival values equally and observe the frequencies. Then increase one value while keeping the other fixed. Explain why the frequency changes across generations rather than an individual changing its inherited trait.")
                        Text("Model assumptions: this deterministic model assumes the trait is inherited and that the supplied survival values represent relative reproductive contribution. It excludes mutation, migration, genetic drift, population-size changes and other biological factors. It illustrates selection; it does not predict a real population.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        if (selectedSubject == 0 || selectedSubject == 1) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("MeritPrep interactive model: photosynthesis", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Change one condition at a time and observe which factor limits the illustrative photosynthesis rate.")
                        Text("Light intensity index: ${lightIntensity.toInt()}")
                        Slider(value = lightIntensity, onValueChange = { lightIntensity = it }, valueRange = 0f..100f)
                        Text("Carbon dioxide availability index: ${carbonDioxideIndex.toInt()}")
                        Slider(value = carbonDioxideIndex, onValueChange = { carbonDioxideIndex = it }, valueRange = 0f..100f)
                        Text("Temperature: ${temperatureCelsius.toInt()} °C")
                        Slider(value = temperatureCelsius, onValueChange = { temperatureCelsius = it }, valueRange = 0f..45f)
                        Text("Illustrative relative rate index: ${"%.1f".format(photosynthesis.relativeRatePercent)}%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Limiting factor: ${photosynthesis.limitingFactor}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                        Text("Light response: ${"%.1f".format(photosynthesis.lightResponsePercent)}% · CO₂ response: ${"%.1f".format(photosynthesis.carbonDioxideResponsePercent)}% · Temperature response: ${"%.1f".format(photosynthesis.temperatureResponsePercent)}%", style = MaterialTheme.typography.bodySmall)
                        Text(photosynthesis.explanation)
                        Text("Investigation prompt", fontWeight = FontWeight.SemiBold)
                        Text("Keep two conditions fixed. Change the third in steps and record the relative index. When does increasing that factor stop increasing the modelled rate?")
                        Text("Model limitation: this is a simplified limiting-factor demonstration. Its response curves and 30 °C illustrative optimum are not measured values and are not universal for all plant species or conditions.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        if (selectedSubject == 0 || selectedSubject == 1) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("MeritPrep interactive model: enzyme activity", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Choose a digestive enzyme, then investigate how temperature and pH affect its modelled activity.")
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            EnzymeProfile.values().forEachIndexed { index, profile ->
                                FilterChip(
                                    selected = selectedEnzymeIndex == index,
                                    onClick = {
                                        selectedEnzymeIndex = index
                                        enzymePh = profile.illustrativeOptimumPh.toFloat()
                                    },
                                    label = { Text(profile.displayName) }
                                )
                            }
                        }
                        Text("Temperature: ${enzymeTemperature.toInt()} °C")
                        Slider(value = enzymeTemperature, onValueChange = { enzymeTemperature = it }, valueRange = 0f..70f)
                        Text("pH: ${"%.1f".format(enzymePh)}")
                        Slider(value = enzymePh, onValueChange = { enzymePh = it }, valueRange = 0f..14f, steps = 27)
                        Text("Illustrative activity index: ${"%.1f".format(enzymeActivity.activityIndexPercent)}%", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Most limiting condition: ${enzymeActivity.limitingCondition}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                        Text("Temperature response: ${"%.1f".format(enzymeActivity.temperatureResponsePercent)}% · pH response: ${"%.1f".format(enzymeActivity.pHResponsePercent)}%", style = MaterialTheme.typography.bodySmall)
                        Text(enzymeActivity.explanation)
                        Text("Investigation prompt", fontWeight = FontWeight.SemiBold)
                        Text("Keep pH fixed while changing temperature. Then keep temperature fixed and change pH. Compare the patterns for amylase, pepsin and trypsin.")
                        Text("Model limitation: the 37 °C temperature optimum and enzyme-specific pH optima are approximate teaching values. Actual enzyme activity depends on the enzyme source, preparation and experimental conditions; this is not a measured kinetic curve.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        items(resources, key = { it.id }) { resource ->
            val noteId = "science-media-${resource.id}"
            val restoredProgress = remember(resource.id) { decodeScienceMediaProgress(database.getNoteBody(noteId)) }
            var resourceOpened by remember(resource.id) { mutableStateOf(restoredProgress.first) }
            var reflection by remember(resource.id) { mutableStateOf(restoredProgress.second) }
            var reflectionSaved by remember(resource.id) { mutableStateOf(restoredProgress.second.isNotBlank()) }
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(resource.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("${resource.provider} · ${resource.mediaType.name.lowercase().replace('_', ' ')}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(resource.summary)
                    val linkedTopic = LearningCatalog.find(resource.topicId)
                    Text("Course topic: ${linkedTopic?.title ?: resource.topicId}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Before you explore", fontWeight = FontWeight.SemiBold)
                    Text(resource.beforeYouStart, style = MaterialTheme.typography.bodyMedium)
                    Text("After you explore", fontWeight = FontWeight.SemiBold)
                    Text(resource.afterYouExplore, style = MaterialTheme.typography.bodyMedium)
                    Text("Offline alternative", fontWeight = FontWeight.SemiBold)
                    Text(resource.offlineAlternative, style = MaterialTheme.typography.bodySmall)
                    Text("Creator/publisher: ${resource.creatorOrPublisher}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Attribution: ${resource.attributionText}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Rights: ${resource.licenceName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Permitted use: ${resource.permittedUse}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    OutlinedTextField(
                        value = reflection,
                        onValueChange = { reflection = it; reflectionSaved = false },
                        label = { Text("Your observation and reflection") },
                        placeholder = { Text("What did you observe? What evidence supports your explanation?") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            val record = JSONObject()
                                .put("opened", resourceOpened)
                                .put("reflection", reflection)
                            database.saveNote(
                                noteId,
                                resource.topicId,
                                "Science media reflection · ${resource.title}",
                                record.toString()
                            )
                            reflectionSaved = true
                        },
                        enabled = reflection.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save reflection on this device")
                    }
                    if (resourceOpened) {
                        Text("Original resource opened · reflection: ${if (reflectionSaved) "saved on this device" else "not yet saved"}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    }
                    if (linkedTopic != null) {
                        OutlinedButton(
                            onClick = { onOpenLesson(resource.topicId) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Open related MeritPrep lesson")
                        }
                    }
                    Button(
                        onClick = {
                            try {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(resource.sourceUrl)))
                                resourceOpened = true
                                database.saveNote(
                                    noteId,
                                    resource.topicId,
                                    "Science media reflection · ${resource.title}",
                                    JSONObject().put("opened", true).put("reflection", reflection).toString()
                                )
                                reflectionSaved = reflection.isNotBlank()
                            } catch (_: ActivityNotFoundException) {
                                Toast.makeText(context, "No browser is available to open this resource.", Toast.LENGTH_LONG).show()
                            } catch (_: Exception) {
                                Toast.makeText(context, "Unable to open this resource. Check your connection and try again.", Toast.LENGTH_LONG).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open original resource")
                    }
                }
            }
        }
        item {
            Text(
                "Rights and attribution are checked at resource level before any media is embedded, downloaded or redistributed in the APK. A free-to-view resource is not automatically free to copy.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ScienceMediaTopicSection(topicId: String) {
    val context = LocalContext.current
    val resources = remember(topicId) { ScienceMediaCatalog.forTopic(topicId) }
    if (resources.isEmpty()) return

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Explore this topic", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Use these guided animations and simulations alongside the lesson.")
            resources.forEach { resource ->
                Text(resource.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(resource.summary)
                Text("Before you explore: ${resource.beforeYouStart}", style = MaterialTheme.typography.bodySmall)
                Text("After you explore: ${resource.afterYouExplore}", style = MaterialTheme.typography.bodySmall)
                Text("Offline alternative: ${resource.offlineAlternative}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Creator/publisher: ${resource.creatorOrPublisher}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Attribution: ${resource.attributionText}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Rights: ${resource.licenceName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                OutlinedButton(
                    onClick = {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(resource.sourceUrl)))
                        } catch (_: ActivityNotFoundException) {
                            Toast.makeText(context, "No browser is available to open this resource.", Toast.LENGTH_LONG).show()
                        } catch (_: Exception) {
                            Toast.makeText(context, "Unable to open this resource. Check your connection and try again.", Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open ${resource.provider} resource")
                }
            }
        }
    }
}
