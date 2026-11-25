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
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScienceMediaScreen(onOpenLabs: () -> Unit, onOpenLesson: (String) -> Unit) {
    val context = LocalContext.current
    var selectedSubject by remember { mutableIntStateOf(0) }
    var lightIntensity by remember { mutableFloatStateOf(35f) }
    var carbonDioxideIndex by remember { mutableFloatStateOf(55f) }
    var temperatureCelsius by remember { mutableFloatStateOf(25f) }
    var selectedEnzymeIndex by remember { mutableIntStateOf(0) }
    var enzymeTemperature by remember { mutableFloatStateOf(37f) }
    var enzymePh by remember { mutableFloatStateOf(7f) }
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
                    Text("Rights: ${resource.licenceName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Use: ${resource.permittedUse}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
