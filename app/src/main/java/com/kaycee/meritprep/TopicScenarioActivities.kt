package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class TopicScenario(
    val setting: String,
    val question: String,
    val options: List<String>,
    val answer: Int,
    val explanation: String
)

private object TopicScenarioLibrary {
    fun find(id: String): TopicScenario? = when (id) {
        "bio-transport" -> TopicScenario(
            "A farmer waters a young plant with highly concentrated saline water. After some time, its leaves begin to wilt.",
            "Which process most directly explains the loss of water from many plant cells?",
            listOf("Osmosis: water moves out across selectively permeable membranes", "Active transport of water molecules using ATP", "Photosynthesis stops and converts water into salt"),
            0,
            "The external solution has a lower water potential than the cell sap. Net water movement out of cells by osmosis reduces turgor; severe water loss can cause plasmolysis."
        )
        "bio-nutrition" -> TopicScenario(
            "Two identical aquatic plants receive equal carbon dioxide and water. One is placed under dim light and the other under brighter light, while temperature is controlled.",
            "What should be measured to compare their photosynthetic rates most directly in this setup?",
            listOf("Oxygen produced per unit time", "Leaf colour at the end of the day", "The total mass of the container"),
            0,
            "A rate requires a change per unit time. Oxygen volume per unit time can be used as an indicator, although bubble counts alone are imprecise because bubble sizes vary."
        )
        "bio-populations" -> TopicScenario(
            "A class estimates the abundance of a grass species in a large field using quadrats.",
            "Which sampling approach best reduces deliberate placement bias?",
            listOf("Place every quadrat where the grass looks densest", "Use random coordinates within the defined sampling area", "Choose only sites beside the footpath"),
            1,
            "Random placement reduces selection bias. A suitable number of quadrats and a representative sampling frame are still needed, especially when organisms are clustered."
        )
        "bio-heredity" -> TopicScenario(
            "Two heterozygous tall pea plants (Tt) are crossed. Tallness is dominant over shortness in this simplified single-gene model.",
            "What phenotypic ratio is expected among a large number of offspring?",
            listOf("1 tall : 1 short", "3 tall : 1 short", "All short"),
            1,
            "Tt × Tt produces TT, Tt, Tt and tt in the expected 1:2:1 genotype ratio. Three genotypes express the dominant tall phenotype for every one short."
        )
        "chem-separation" -> TopicScenario(
            "A dry mixture contains sand and sodium chloride. A student must recover both substances.",
            "Which sequence is appropriate?",
            listOf("Add water, filter, then crystallise the filtrate", "Use a magnet, then distil both solids", "Heat the dry mixture until both solids vaporise"),
            0,
            "Sodium chloride dissolves in water while sand remains insoluble. Filtration separates sand; evaporation or crystallisation recovers the salt from the filtrate."
        )
        "chem-rates" -> TopicScenario(
            "Equal masses of the same carbonate react with equal acid concentrations. One carbonate sample is powdered and the other is made of large chips.",
            "Why is the powdered sample expected to react faster?",
            listOf("It has a greater exposed surface area for effective collisions", "It has a greater total mass", "It changes the identity of the acid"),
            0,
            "Powdering increases the exposed surface area. More acid particles can collide with the solid surface per unit time, increasing the reaction rate."
        )
        "chem-equilibrium" -> TopicScenario(
            "The Haber equilibrium is N₂(g) + 3H₂(g) ⇌ 2NH₃(g). The system is at equilibrium and temperature is held constant.",
            "What is the expected effect of increasing pressure?",
            listOf("Equilibrium shifts towards ammonia, the side with fewer gas molecules", "Equilibrium shifts towards nitrogen and hydrogen", "The equilibrium constant increases automatically"),
            0,
            "There are four moles of gaseous reactants and two moles of gaseous product in the balanced equation. Higher pressure favours the side with fewer gas molecules. At constant temperature, K does not change."
        )
        "chem-acids-bases" -> TopicScenario(
            "A student adds a measured volume of dilute sodium hydroxide to a known volume of dilute hydrochloric acid.",
            "Which evidence best indicates that the equivalence point has been reached in a suitable indicator titration?",
            listOf("A persistent indicator colour change near the endpoint", "The solution becomes cloudy in every acid-base reaction", "The burette reading reaches exactly 50 cm³"),
            0,
            "The endpoint is detected by a suitable indicator colour change. It should be close to the equivalence point; the titre volume depends on concentrations and stoichiometry."
        )
        "phy-motion" -> TopicScenario(
            "A car travels on a straight, level road. The driver brakes and the car slows with approximately constant deceleration.",
            "If the initial speed is doubled while the deceleration magnitude stays the same, what happens to the stopping distance in the ideal model?",
            listOf("It doubles", "It becomes four times as large", "It remains unchanged"),
            1,
            "From v² = u² + 2as with final speed zero, stopping distance is proportional to u² for fixed deceleration. Doubling u gives four times the distance."
        )
        "phy-refraction" -> TopicScenario(
            "A narrow ray of light passes from air into a transparent glass block at an angle other than 0° to the normal.",
            "Which direction does the refracted ray bend?",
            listOf("Towards the normal because glass has a higher refractive index", "Away from the normal because glass is denser", "It always continues undeviated"),
            0,
            "Light entering a medium with higher refractive index slows and bends towards the normal. At normal incidence its direction is unchanged, although its speed and wavelength change."
        )
        "phy-current-electricity" -> TopicScenario(
            "Two identical resistors are connected in parallel to an ideal battery. The student adds a third identical resistor in parallel.",
            "What happens to the equivalent resistance of the network?",
            listOf("It decreases", "It increases", "It remains equal to one resistor"),
            0,
            "Adding another parallel branch increases total conductance, so equivalent resistance decreases. The ideal battery voltage across each branch remains the same."
        )
        "phy-pressure" -> TopicScenario(
            "A sharp knife and a blunt knife are pushed with the same perpendicular force against similar material.",
            "Why does the sharp edge usually cut more easily?",
            listOf("Its smaller contact area produces greater pressure", "Its larger mass produces less pressure", "Pressure is independent of contact area"),
            0,
            "Pressure is force per unit area, p = F/A. With the same force, a smaller contact area produces a greater pressure."
        )
        else -> null
    }
}

@Composable
fun TopicScenarioActivity(topicId: String): Boolean {
    val scenario = remember(topicId) { TopicScenarioLibrary.find(topicId) } ?: return false
    var selected by remember(topicId) { mutableIntStateOf(-1) }
    var revealed by remember(topicId) { mutableStateOf(false) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Applied science scenario", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(scenario.setting, style = MaterialTheme.typography.bodyLarge)
            Text(scenario.question, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            scenario.options.forEachIndexed { index, option ->
                OutlinedButton(
                    onClick = { if (!revealed) selected = index },
                    enabled = !revealed,
                    modifier = Modifier.fillMaxWidth()
                ) { ScientificText(('A' + index) + ".  " + option, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.bodyMedium) }
            }
            if (revealed) {
                Text(if (selected == scenario.answer) "Sound scientific decision" else "Revisit the principle", color = if (selected == scenario.answer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                ScientificText(scenario.explanation, style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { selected = -1; revealed = false }, modifier = Modifier.fillMaxWidth()) { Text("Try the scenario again") }
            } else {
                Button(onClick = { revealed = true }, enabled = selected >= 0, modifier = Modifier.fillMaxWidth()) { Text("Check reasoning") }
            }
        }
    }
    return true
}

@Composable
fun TopicScenarioReflectionCard(topicId: String, topicTitle: String) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val database = remember(context) { com.kaycee.meritprep.data.MeritDatabase(context) }
    val noteId = "scenario-reflection-" + topicId
    var response by remember(topicId) { mutableStateOf(database.getNoteBody(noteId).orEmpty()) }
    var saved by remember(topicId) { mutableStateOf(true) }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Application challenge", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose a real system in which " + topicTitle + " matters. Explain the scientific principle involved, predict what would happen if one relevant variable changed, and identify the evidence you would collect to test your prediction.")
            OutlinedTextField(
                value = response,
                onValueChange = { response = it; saved = false },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                label = { Text("Your applied explanation") }
            )
            Button(
                onClick = {
                    database.saveNote(noteId, topicId, topicTitle + " application challenge", response)
                    saved = true
                },
                enabled = !saved && response.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("Save application response") }
            Text(if (saved) "Response saved on this device." else "Unsaved response.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
