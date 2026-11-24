package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class QualitativeTest(
    val id: String,
    val category: String,
    val target: String,
    val reagentOrTest: String,
    val observation: String,
    val inference: String,
    val caution: String
)

object ChemistryQualitativeGuide {
    val tests = listOf(
        QualitativeTest("anion-carbonate","Anions","Carbonate (CO₃²⁻)","Add a dilute acid; identify any evolved gas with limewater.","Effervescence; the gas turns limewater milky.","Carbon dioxide is released, supporting the presence of carbonate or hydrogencarbonate.","Do not identify an ion from effervescence alone; confirm the gas and consider other carbon dioxide sources."),
        QualitativeTest("anion-sulfate","Anions","Sulfate (SO₄²⁻)","Acidify the sample, then use a soluble barium salt in a supervised laboratory test.","A dense white precipitate remains insoluble in dilute acid.","Barium sulfate formation supports sulfate ions.","Barium salts are toxic; use microscale quantities and approved disposal."),
        QualitativeTest("anion-chloride","Anions","Chloride (Cl⁻)","Acidify with dilute nitric acid, then add silver nitrate solution.","A white precipitate forms; it dissolves in dilute ammonia solution.","Silver chloride supports chloride ions.","Silver nitrate stains and nitric acid is corrosive; use eye protection and teacher supervision."),
        QualitativeTest("anion-bromide","Anions","Bromide (Br⁻)","Acidify with dilute nitric acid, then add silver nitrate solution.","A cream precipitate forms; it is only sparingly soluble in concentrated ammonia.","Silver bromide supports bromide ions.","Silver nitrate stains; ammonia vapour is irritating. Avoid inhalation."),
        QualitativeTest("anion-iodide","Anions","Iodide (I⁻)","Acidify with dilute nitric acid, then add silver nitrate solution.","A yellow precipitate forms and remains insoluble in ammonia solution.","Silver iodide supports iodide ions.","Silver nitrate stains; collect silver-containing waste for proper disposal."),
        QualitativeTest("anion-sulfite","Anions","Sulfite (SO₃²⁻)","Add a dilute acid and test any evolved gas with acidified potassium dichromate paper under supervision.","A pungent gas may be evolved; acidified dichromate changes from orange to green.","Sulfur dioxide formation and its reducing action support the presence of sulfite ions.","Sulfur dioxide is toxic and irritating. Do not smell the gas directly; use a fume cupboard and teacher supervision."),
        QualitativeTest("anion-nitrate","Anions","Nitrate (NO₃⁻)","Brown-ring test is a specialist confirmatory procedure using iron(II) sulfate and carefully layered concentrated sulfuric acid.","A brown ring appears at the liquid interface when the test is positive.","The brown ring is consistent with nitrate ions.","Concentrated sulfuric acid is highly corrosive and mixing is strongly exothermic. This test is reference-only here and must only be performed by a trained teacher in a suitable laboratory."),
        QualitativeTest("gas-carbon-dioxide","Gases","Carbon dioxide (CO₂)","Bubble the gas through clear limewater.","Limewater turns milky.","Calcium carbonate forms; this is a common test for carbon dioxide.","Excess carbon dioxide can make the milkiness disappear as soluble hydrogencarbonate forms."),
        QualitativeTest("gas-ammonia","Gases","Ammonia (NH₃)","Expose damp red litmus paper to the gas.","Damp red litmus turns blue.","Ammonia is alkaline in water.","Ammonia is an irritant; never smell a gas directly."),
        QualitativeTest("gas-hydrogen","Gases","Hydrogen (H₂)","A trained demonstrator tests a small sample with a lighted splint.","A squeaky pop may be heard.","The result supports hydrogen, but the test requires strict control of gas volume and ignition risk.","Hydrogen forms explosive mixtures with air. Do not attempt this outside a supervised laboratory."),
        QualitativeTest("gas-oxygen","Gases","Oxygen (O₂)","Introduce a glowing splint into a sample of the gas.","The glowing splint relights.","Oxygen supports combustion.","Oxygen intensifies fires; keep away from flames and combustible materials."),
        QualitativeTest("gas-chlorine","Gases","Chlorine (Cl₂)","Observe the effect on damp indicator paper in a fume cupboard demonstration.","Damp litmus may turn red and then become bleached.","The bleaching action in moist conditions is characteristic of chlorine.","Chlorine is highly toxic. This is a teacher demonstration only in a functioning fume cupboard; never generate or inhale it."),
        QualitativeTest("gas-hydrogen-chloride","Gases","Hydrogen chloride (HCl)","Expose damp blue litmus paper to the gas in a controlled teacher demonstration.","Damp blue litmus turns red.","Hydrogen chloride dissolves in moisture to form an acidic solution.","Hydrogen chloride gas is corrosive and irritating. Avoid inhalation; demonstration requires suitable ventilation and trained supervision."),
        QualitativeTest("gas-sulfur-dioxide","Gases","Sulfur dioxide (SO₂)","A teacher may use acidified potassium dichromate paper in a fume cupboard.","Orange dichromate changes to green.","Sulfur dioxide acts as a reducing agent and can reduce dichromate(VI).","Sulfur dioxide is toxic and irritating. This is a supervised fume-cupboard demonstration only.")
    )
}

@Composable
fun ChemistryQualitativeGuideScreen() {
    var selectedCategory by remember { mutableStateOf("Anions") }
    val entries = remember(selectedCategory) { ChemistryQualitativeGuide.tests.filter { it.category == selectedCategory } }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Qualitative analysis guide", style = MaterialTheme.typography.headlineMedium)
        Text("Use the observations to connect a test result to a chemical inference. These are reference notes, not instructions for unsupervised experiments.")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = selectedCategory == "Anions", onClick = { selectedCategory = "Anions" }, label = { Text("Anions") })
            FilterChip(selected = selectedCategory == "Gases", onClick = { selectedCategory = "Gases" }, label = { Text("Gases") })
        }
        entries.forEach { item ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Text(item.target, style = MaterialTheme.typography.titleMedium)
                    Text("Test / reagent", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Text(item.reagentOrTest)
                    Text("Expected observation", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Text(item.observation)
                    Text("Inference", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                    Text(item.inference)
                    Text("Safety and limitations", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.error)
                    Text(item.caution, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Text(
            "A positive observation is evidence, not always proof. Confirmatory tests, reagent purity, concentration and interfering ions matter. Practical work must follow the school's approved risk assessment and teacher supervision.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
