package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONObject

internal enum class CellSpecimen(val label: String) {
    ONION("Onion epidermis"),
    CHEEK("Cheek epithelial cells")
}

internal data class CellMicroscopyObservation(
    val features: List<Pair<String, String>>,
    val fieldOfViewMm: Double,
    val totalMagnification: Int,
    val interpretation: String
)

/**
 * Deterministic teaching model. The displayed observations are schematic,
 * not real microscope readings. Field of view assumes an 18 mm eyepiece
 * field number and the stated objective lens.
 */
internal object CellMicroscopyModel {
    fun observe(specimen: CellSpecimen, totalMagnification: Int): CellMicroscopyObservation {
        require(totalMagnification in setOf(40, 100, 400))
        val objective = totalMagnification / 10.0
        val fieldOfView = 18.0 / objective
        val features = when (specimen) {
            CellSpecimen.ONION -> listOf(
                "Cell outline" to "Regular, brick-like arrangement; cellulose cell wall forms a clear boundary",
                "Nucleus" to "May be visible as a stained body when iodine is used",
                "Cytoplasm" to "A thin layer near the cell boundary",
                "Vacuole" to "Large central region in a mature epidermal cell",
                "Chloroplasts" to "Not normally seen in onion bulb epidermis"
            )
            CellSpecimen.CHEEK -> listOf(
                "Cell outline" to "Irregular or rounded outline bounded by a cell membrane",
                "Nucleus" to "Usually visible as a stained body after suitable staining",
                "Cytoplasm" to "Fills the region inside the cell membrane",
                "Cell wall" to "Absent",
                "Chloroplasts" to "Absent"
            )
        }
        val interpretation = when (specimen) {
            CellSpecimen.ONION -> "The regular boundaries are cell walls. Onion bulb epidermal cells are plant cells, but this tissue is not normally photosynthetic and should not be expected to show chloroplasts."
            CellSpecimen.CHEEK -> "Cheek epithelial cells are animal cells. They have a cell membrane, cytoplasm and nucleus, but no cellulose cell wall or chloroplasts."
        }
        return CellMicroscopyObservation(features, fieldOfView, totalMagnification, interpretation)
    }
}

@Composable
internal fun BiologyCellMicroscopyVirtualLab() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val noteId = "micro-lab-session-bio-cell"
    val saved = remember(context) {
        runCatching { JSONObject(database.getNoteBody(noteId) ?: "{}") }.getOrElse { JSONObject() }
    }
    var specimen by remember { mutableStateOf(runCatching { CellSpecimen.valueOf(saved.optString("specimen", "ONION")) }.getOrDefault(CellSpecimen.ONION)) }
    var magnification by remember { mutableStateOf(saved.optInt("magnification", 100).takeIf { it in setOf(40, 100, 400) } ?: 100) }
    var prediction by remember { mutableStateOf(saved.optString("prediction")) }
    var safetyReviewed by remember { mutableStateOf(saved.optBoolean("safetyReviewed")) }
    var hasRun by remember { mutableStateOf(saved.optBoolean("hasRun")) }
    var observationNotes by remember { mutableStateOf(saved.optString("observationNotes")) }
    var conclusion by remember { mutableStateOf(saved.optString("conclusion")) }
    val result = remember(specimen, magnification) { CellMicroscopyModel.observe(specimen, magnification) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Virtual practical: comparing plant and animal cells", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Scientific question: Which cell structures can be distinguished when onion epidermal cells and cheek epithelial cells are viewed with a light microscope?")
                Text("Learning outcomes: identify visible cell features; compare plant and animal cells; relate staining and magnification to observation; distinguish observation from inference.", style = MaterialTheme.typography.bodySmall)
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("Apparatus and materials", fontWeight = FontWeight.Bold)
                Text("Light microscope, clean glass slide, coverslip, dropper, forceps, mounted needle, onion epidermis, sterile single-use cheek swab, water, iodine solution for onion tissue, suitable dilute methylene blue for cheek cells, tissue paper and a waste container.")
                HorizontalDivider()
                Text("Safety", fontWeight = FontWeight.Bold)
                Text("Use teacher supervision. Handle slides and coverslips carefully. Never share a cheek swab; dispose of it as biological waste. Avoid skin and eye contact with stains, do not taste reagents, wash hands and clean the work surface. Do not use a sharp blade without direct supervision.")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = safetyReviewed, onCheckedChange = { safetyReviewed = it })
                    Text("I have reviewed the safety instructions.")
                }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Variables", fontWeight = FontWeight.Bold)
                Text("Independent variable: specimen type. Dependent variable: structures that can be observed and distinguished. Controlled conditions: microscope, illumination, staining procedure, slide preparation and magnification during a direct comparison.")
                Text("Method", fontWeight = FontWeight.Bold)
                listOf(
                    "Prepare a thin onion epidermal peel and place it flat in a drop of water on a clean slide.",
                    "Add a suitable drop of iodine stain, lower a coverslip gently and blot excess liquid.",
                    "Prepare a cheek-cell smear with a sterile single-use swab, add suitable stain and cover with a coverslip.",
                    "Place the slide on the microscope stage. Begin with the lowest-power objective and focus carefully.",
                    "Centre the specimen before changing to a higher-power objective. Use fine focus at high power.",
                    "Record visible structures, compare the two specimens and distinguish what is seen from what is inferred."
                ).forEachIndexed { index, step -> Text("${index + 1}. $step", style = MaterialTheme.typography.bodyMedium) }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("1 · Select the specimen", fontWeight = FontWeight.Bold)
                CellSpecimen.entries.forEach { item ->
                    FilterChip(selected = specimen == item, onClick = { specimen = item; hasRun = false }, label = { Text(item.label) })
                }
                Text("2 · Select total magnification", fontWeight = FontWeight.Bold)
                listOf(40, 100, 400).forEach { value ->
                    FilterChip(selected = magnification == value, onClick = { magnification = value; hasRun = false }, label = { Text("${value}×") })
                }
                Text("Assumption: 10× eyepiece and an 18 mm eyepiece field number. The model estimates field diameter as field number ÷ objective magnification; actual microscopes differ.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("3 · Predict before running", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = prediction,
                    onValueChange = { prediction = it; hasRun = false },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    label = { Text("What structures do you expect to see? Why?") }
                )
                Button(onClick = { hasRun = true }, enabled = safetyReviewed && prediction.isNotBlank(), modifier = Modifier.fillMaxWidth()) { Text("Run simulated microscope") }
            }
        }
        if (hasRun) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("SIMULATED SCHEMATIC OBSERVATION", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("This is a simplified teaching model, not a photograph, real specimen or laboratory measurement.")
                    Text("${result.totalMagnification}× total magnification • estimated field diameter ${"%.2f".format(result.fieldOfViewMm)} mm")
                    result.features.forEach { (feature, description) ->
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(feature, fontWeight = FontWeight.SemiBold)
                            Text(description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    HorizontalDivider()
                    Text(result.interpretation, style = MaterialTheme.typography.bodyMedium)
                    Text("At higher magnification the image appears larger, but the field of view becomes smaller. Resolution and brightness also depend on the microscope and preparation.", style = MaterialTheme.typography.bodySmall)
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("4 · Record and interpret", fontWeight = FontWeight.Bold)
                    OutlinedTextField(value = observationNotes, onValueChange = { observationNotes = it }, modifier = Modifier.fillMaxWidth(), minLines = 2, label = { Text("Record your observations") })
                    OutlinedTextField(value = conclusion, onValueChange = { conclusion = it }, modifier = Modifier.fillMaxWidth(), minLines = 2, label = { Text("Conclusion: answer the scientific question using the observations") })
                    Text("Evaluation: the model uses simplified typical cells. Real specimens vary with preparation, staining, focus, tissue condition and microscope quality. A virtual model does not demonstrate physical slide preparation or microscope handling.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Button(onClick = {
                        val body = JSONObject()
                            .put("specimen", specimen.name)
                            .put("magnification", magnification)
                            .put("prediction", prediction)
                            .put("safetyReviewed", safetyReviewed)
                            .put("hasRun", hasRun)
                            .put("observationNotes", observationNotes)
                            .put("conclusion", conclusion)
                        runCatching { database.saveNote(noteId, "bio-cell", "Cell microscopy virtual lab record", body.toString()) }
                    }, modifier = Modifier.fillMaxWidth()) { Text("Save lab record on this device") }
                }
            }
        }
    }
}
