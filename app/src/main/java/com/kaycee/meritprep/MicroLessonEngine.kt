package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import org.json.JSONObject

/**
 * A check is attached to one atomic examinable idea and one teaching stage.
 * A learner must pass an initial understanding/method question AND a fresh
 * transfer question. A single guessed response can never complete the check.
 */
internal data class MicroLessonCheck(
    val id: String,
    val ideaId: String,
    val ideaTitle: String,
    val stage: LessonStage,
    val syllabusRefs: List<String>,
    val skill: String,
    val prompt: String,
    val options: List<String>,
    val correctOption: Int,
    val explanation: String,
    val misconceptionFeedback: Map<Int, String>,
    val hint: String,
    val transferPrompt: String,
    val transferOptions: List<String>,
    val transferCorrectOption: Int,
    val transferExplanation: String
)

internal data class MicroLessonCheckEvidence(
    val firstPassed: Boolean = false,
    val transferPassed: Boolean = false
) {
    val complete: Boolean get() = firstPassed && transferPassed
}

internal object MicroLessonEngine {
    private const val JAMB_CELL = "JAMB-BIO-A1-CELL-STRUCTURE-001"
    private const val WAEC_CELL = "WAEC-NG-BIO-A-CELL-STRUCTURE-001"

    private data class Idea(
        val id: String,
        val title: String,
        val refs: List<String>
    )

    private val ideas = listOf(
        Idea("bio-cell-theory", "Cell theory", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-cell-membrane", "Cell membrane and exchange", listOf(JAMB_CELL, "JAMB-BIO-2026-B3-OBJ-001", WAEC_CELL)),
        Idea("bio-cell-organelles", "Organelles and their functions", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-cell-types", "Plant and animal cell differences", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-prokaryote-eukaryote", "Prokaryotic and eukaryotic cells", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-levels-organisation", "Levels of biological organisation", listOf(JAMB_CELL, WAEC_CELL))
    )

    fun checks(topicId: String, stage: LessonStage): List<MicroLessonCheck> {
        if (topicId != "bio-cell" || stage !in setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)) return emptyList()
        return ideas.mapIndexed { index, idea ->
            val pair = questions(index, stage)
            MicroLessonCheck(
                id = "${idea.id}-${stage.name.lowercase()}",
                ideaId = idea.id,
                ideaTitle = idea.title,
                stage = stage,
                syllabusRefs = idea.refs,
                skill = when (stage) {
                    LessonStage.CONCEPT -> "Explain or distinguish the core idea"
                    LessonStage.EXAMPLES -> "Apply the idea to a fresh worked example"
                    else -> "Transfer the idea to an unfamiliar biological context"
                },
                prompt = pair.firstPrompt,
                options = pair.firstOptions,
                correctOption = pair.firstAnswer,
                explanation = pair.firstExplanation,
                misconceptionFeedback = pair.feedback,
                hint = pair.hint,
                transferPrompt = pair.transferPrompt,
                transferOptions = pair.transferOptions,
                transferCorrectOption = pair.transferAnswer,
                transferExplanation = pair.transferExplanation
            )
        }
    }

    private data class QuestionPair(
        val firstPrompt: String, val firstOptions: List<String>, val firstAnswer: Int,
        val firstExplanation: String, val feedback: Map<Int, String>, val hint: String,
        val transferPrompt: String, val transferOptions: List<String>, val transferAnswer: Int,
        val transferExplanation: String
    )

    private fun questions(i: Int, stage: LessonStage): QuestionPair {
        val banks = when (stage) {
            LessonStage.CONCEPT -> listOf(
                QuestionPair("Which statement is part of cell theory?", listOf("All cells have a cell wall", "New cells arise from pre-existing cells", "All organisms contain many cells", "Cells arise spontaneously from non-living matter"), 1, "Cell theory states that new cells arise from pre-existing cells by cell division. It does not claim that every cell has a wall or that every organism is multicellular.", mapOf(0 to "Animal cells do not have a cellulose cell wall.", 2 to "Bacteria and Amoeba are unicellular.", 3 to "Spontaneous generation is not part of modern cell theory."), "Recall the three central ideas: organisms are cellular; the cell is the basic unit; cells come from existing cells.", "A cut on the skin heals as cells divide. Which cell-theory idea explains the formation of new skin cells?", listOf("Cells are formed from pre-existing cells", "Every cell contains chloroplasts", "Cells are all identical", "Only organs can grow"), 0, "New skin cells are produced by division of existing cells in the tissue."),
                QuestionPair("What property allows a cell membrane to regulate exchange?", listOf("It is completely impermeable", "It is selectively permeable", "It is made of cellulose", "It allows every substance through equally"), 1, "A selectively permeable membrane permits some substances to cross more readily than others, helping the cell regulate its internal environment.", mapOf(0 to "A completely impermeable boundary would prevent essential exchange.", 2 to "The cell membrane is not a cellulose wall.", 3 to "Selective permeability means exchange is not unrestricted."), "Think about why a cell must control what enters and leaves rather than allow everything through.", "A cell is placed in a solution. Which structure directly controls the movement of water across its boundary?", listOf("Cell wall", "Cell membrane", "Nucleolus", "Ribosome"), 1, "Water crosses the selectively permeable cell membrane; the wall may resist expansion but does not provide the main selective barrier."),
                QuestionPair("Which organelle is the main site of aerobic respiration in most eukaryotic cells?", listOf("Ribosome", "Mitochondrion", "Golgi apparatus", "Chloroplast"), 1, "Mitochondria are the principal sites of aerobic respiration in most eukaryotic cells and are central to ATP production.", mapOf(0 to "Ribosomes synthesise proteins.", 2 to "The Golgi apparatus modifies and packages materials.", 3 to "Chloroplasts carry out photosynthesis."), "Match each organelle to its principal role: protein synthesis, packaging, photosynthesis or aerobic respiration.", "A muscle cell requires a large supply of ATP for contraction. Which organelle would you expect it to contain in substantial numbers?", listOf("Mitochondria", "Chloroplasts", "Cell walls", "Large central vacuoles"), 0, "Muscle cells have high energy demands and typically contain many mitochondria."),
                QuestionPair("Which feature is characteristic of a typical plant cell but absent from an animal cell?", listOf("Cell membrane", "Cytoplasm", "Cellulose cell wall", "Ribosomes"), 2, "A typical plant cell has a cellulose cell wall. Both plant and animal cells have a membrane, cytoplasm and ribosomes.", mapOf(0 to "Both cell types have a cell membrane.", 1 to "Both cell types contain cytoplasm.", 3 to "Both cell types contain ribosomes."), "Look for a feature that provides structural support outside the plasma membrane.", "A leaf cell remains firm partly because of a rigid cellulose layer outside its membrane. Name that layer.", listOf("Cell wall", "Nuclear envelope", "Golgi body", "Mitochondrial membrane"), 0, "The cellulose cell wall supports the plant cell and resists excessive expansion."),
                QuestionPair("Which statement distinguishes a prokaryotic cell from a eukaryotic cell?", listOf("A prokaryote has no DNA", "A prokaryote lacks a membrane-bound nucleus", "A prokaryote has no ribosomes", "A prokaryote is always multicellular"), 1, "Prokaryotes have DNA and ribosomes, but their DNA is not enclosed within a membrane-bound nucleus.", mapOf(0 to "Prokaryotes contain DNA in a nucleoid region.", 2 to "Prokaryotes contain ribosomes for protein synthesis.", 3 to "Prokaryotes are commonly unicellular."), "The key difference concerns the presence or absence of a membrane-bound nucleus.", "A bacterium is examined under an electron microscope. Its DNA occupies a nucleoid region. How should it be classified?", listOf("Eukaryotic", "Prokaryotic", "Acellular virus", "Multicellular tissue"), 1, "Bacteria are prokaryotes because they lack a membrane-bound nucleus."),
                QuestionPair("Which sequence correctly shows increasing organisation in a multicellular animal?", listOf("Organ → cell → tissue → system", "Cell → tissue → organ → organ system", "Tissue → cell → system → organ", "Cell → organ system → tissue → organ"), 1, "Similar cells form tissues; tissues form organs; organs work together in organ systems.", mapOf(0 to "The sequence is reversed and incomplete.", 2 to "A tissue is made of cells, not the other way around.", 3 to "An organ system is built from organs, which contain tissues."), "Start with the smallest living structural unit and move towards larger coordinated structures.", "Cardiac muscle cells form cardiac muscle tissue, which contributes to the heart. What level comes immediately after the heart in this hierarchy?", listOf("Cell", "Tissue", "Organ system", "Organelle"), 2, "The heart is an organ; it functions as part of the circulatory system.")
            )
            LessonStage.EXAMPLES -> listOf(
                QuestionPair("A student observes cells in a growing root tip. Which conclusion is consistent with cell theory?", listOf("New cells appear without parent cells", "Existing cells divide to produce new cells", "All cells must contain chloroplasts", "The root tip is not made of cells"), 1, "Growth in the root tip involves cell division: new cells arise from existing cells.", mapOf(0 to "This contradicts cell theory.", 2 to "Root cells commonly lack chloroplasts.", 3 to "Root tissues are cellular."), "Apply the cell theory statement about the origin of cells.", "A tissue sample shows many recently divided cells. What process produced them?", listOf("Cell division", "Photosynthesis", "Diffusion only", "Protein packaging"), 0, "Mitosis and related cell-cycle events produce new cells in growing tissues."),
                QuestionPair("A red blood cell is placed in distilled water. Which boundary controls the water entering it?", listOf("Cell membrane", "Cell wall", "Nucleus", "Ribosome"), 0, "Water moves across the selectively permeable plasma membrane by osmosis. A red blood cell has no cell wall.", mapOf(1 to "Mature mammalian red blood cells have no cell wall.", 2 to "A mature mammalian red blood cell lacks a nucleus.", 3 to "Ribosomes do not form the cell boundary."), "Identify the selectively permeable boundary and remember that red blood cells are animal cells.", "Which process describes net water movement through a selectively permeable membrane from higher water potential to lower water potential?", listOf("Osmosis", "Active transport", "Protein synthesis", "Exocytosis"), 0, "Osmosis is the net movement of water down a water-potential gradient through a selectively permeable membrane."),
                QuestionPair("A gland cell secretes many proteins. Which pair of structures is especially involved in making and processing those proteins?", listOf("Ribosomes and Golgi apparatus", "Chloroplasts and cell wall", "Vacuole and centrioles only", "Cell wall and lysosome"), 0, "Ribosomes synthesise polypeptides; the rough endoplasmic reticulum and Golgi apparatus help process, sort and package many proteins for secretion.", mapOf(1 to "Chloroplasts and cellulose walls are not the protein-secretory pathway.", 2 to "This pair does not explain protein synthesis and packaging.", 3 to "Neither structure performs the complete secretory pathway."), "Separate the stages: protein synthesis first, then modification and packaging.", "A newly made secretory protein is modified and packaged into vesicles. Which organelle performs the packaging step?", listOf("Golgi apparatus", "Nucleolus", "Cell wall", "Chloroplast"), 0, "The Golgi apparatus modifies, sorts and packages proteins into vesicles."),
                QuestionPair("A plant cell has a large central vacuole and a cellulose wall. Which interpretation is best?", listOf("It is a typical plant cell", "It must be a bacterium", "It is an animal cell", "It cannot contain a membrane"), 0, "A large permanent vacuole and cellulose wall are characteristic of many typical plant cells.", mapOf(1 to "Bacteria do not have this eukaryotic plant-cell organisation.", 2 to "Animal cells lack a cellulose cell wall.", 3 to "Plant cells also have a cell membrane."), "Use the combination of features rather than one feature alone.", "Which structure in a plant cell contains cell sap and helps maintain turgor?", listOf("Large permanent vacuole", "Ribosome", "Nucleolus", "Centromere"), 0, "The vacuole contains cell sap; water uptake helps generate turgor pressure against the wall."),
                QuestionPair("An organism's cells contain circular DNA in a nucleoid and no mitochondria. What is the most likely cell type?", listOf("Eukaryotic plant cell", "Prokaryotic cell", "Mammalian cell", "Fungal cell"), 1, "A nucleoid and absence of membrane-bound organelles such as mitochondria indicate a prokaryotic cell.", mapOf(0 to "Plant cells are eukaryotic and contain a nucleus and mitochondria.", 2 to "Mammalian cells are eukaryotic.", 3 to "Fungal cells are eukaryotic."), "Look for the nucleoid and the absence of a membrane-bound nucleus.", "Which structure is absent in a typical bacterial cell?", listOf("Ribosome", "Cell membrane", "Membrane-bound nucleus", "DNA"), 2, "Bacteria have DNA, ribosomes and a cell membrane, but no membrane-bound nucleus."),
                QuestionPair("A biology diagram shows muscle cells, muscle tissue and the heart. Which statement is correct?", listOf("The heart is a tissue", "Muscle tissue is made from similar specialised cells", "A cell is larger than an organ", "An organ system is smaller than a cell"), 1, "A tissue consists of cells working together; the heart is an organ containing several tissues.", mapOf(0 to "The heart is an organ, not a tissue.", 2 to "Cells are smaller structural units than organs.", 3 to "An organ system is much larger and more complex than a cell."), "Identify what each example represents before ordering them.", "Which level is represented by the heart itself?", listOf("Cell", "Tissue", "Organ", "Organism"), 2, "The heart contains multiple tissues and performs an organ-level function.")
            )
            else -> listOf(
                QuestionPair("A unicellular Amoeba carries out nutrition, respiration and excretion. What does this demonstrate?", listOf("One cell can perform essential life processes", "Every organism needs organs", "Amoeba has tissues and organ systems", "Cells cannot function independently"), 0, "Amoeba is unicellular; its single cell performs the essential life processes needed by the organism.", mapOf(1 to "Unicellular organisms do not possess organs.", 2 to "Amoeba has no tissues or organ systems.", 3 to "A unicellular cell functions as the whole organism."), "Apply the cell concept to a whole organism made of one cell.", "Which organism is a common example of a unicellular eukaryote?", listOf("Amoeba", "Human", "Maize plant", "Earthworm"), 0, "Amoeba is a unicellular eukaryote."),
                QuestionPair("A selectively permeable membrane separates a dilute solution from a more concentrated solution. Which way does water move by osmosis?", listOf("Net movement towards the more concentrated solution", "Only from concentrated to dilute", "No movement is possible", "Solute always crosses instead of water"), 0, "Water moves down its water-potential gradient; in this simplified comparison, the more concentrated solution has lower water potential.", mapOf(1 to "This reverses the net direction for the stated conditions.", 2 to "Water molecules move in both directions, but there is a net movement.", 3 to "Osmosis specifically describes water movement."), "Compare water potential, not simply the number of solute particles moving.", "A plant cell is placed in a strongly concentrated sucrose solution. What is the likely effect as water leaves?", listOf("The protoplast may shrink away from the wall", "The cell immediately gains turgor", "The cellulose wall dissolves", "The nucleus becomes a cell wall"), 0, "Water loss can cause plasmolysis, where the protoplast pulls away from the cell wall."),
                QuestionPair("A person has a high energy demand in active muscle. Which organelle-function relationship best explains the need?", listOf("Mitochondria — ATP production during aerobic respiration", "Ribosomes — photosynthesis", "Golgi — DNA storage", "Cell wall — protein synthesis"), 0, "Mitochondria support aerobic respiration and ATP generation needed by energy-demanding cells.", mapOf(1 to "Ribosomes synthesise proteins; they do not photosynthesise.", 2 to "The nucleus stores most cellular DNA; Golgi packages materials.", 3 to "Cell walls provide support, not protein synthesis."), "Match the energy requirement to the organelle involved in aerobic respiration.", "Which organelle is especially abundant in cells specialised for sustained aerobic activity?", listOf("Mitochondrion", "Chloroplast", "Cell wall", "Golgi vesicle"), 0, "Cells with sustained energy demands commonly contain many mitochondria."),
                QuestionPair("A root hair cell has a cell wall but no chloroplast. Is it still a plant cell?", listOf("Yes; root cells are plant cells and usually lack chloroplasts", "No; every plant cell must photosynthesise", "No; cell walls occur only in bacteria", "Yes, because it has no membrane"), 0, "Root hair cells are specialised plant cells. They normally lack chloroplasts because they are underground and do not photosynthesise.", mapOf(1 to "Not all plant cells photosynthesise.", 2 to "Cellulose walls are characteristic of plants, fungi and some other groups, not only bacteria.", 3 to "Plant cells do have cell membranes."), "Consider the function and location of a root hair cell.", "Which feature is expected in a root hair cell?", listOf("Large surface area for absorption", "Numerous chloroplasts for capturing sunlight", "No cell membrane", "No cytoplasm"), 0, "The long projection increases surface area for water and mineral-ion absorption."),
                QuestionPair("A scientist observes a cell with DNA, ribosomes and a plasma membrane but no nucleus. Which inference is justified?", listOf("It is prokaryotic", "It is a virus", "It has no genetic material", "It is definitely a plant cell"), 0, "The combination of DNA and ribosomes with no membrane-bound nucleus is characteristic of prokaryotes.", mapOf(1 to "Viruses are acellular and do not have ribosomes.", 2 to "The observation explicitly includes DNA.", 3 to "Plant cells are eukaryotic and have a nucleus."), "Use the absence of a membrane-bound nucleus; do not mistake absence of a nucleus for absence of DNA.", "Which group contains prokaryotic organisms?", listOf("Bacteria", "Animals", "Flowering plants", "Fungi"), 0, "Bacteria are prokaryotes."),
                QuestionPair("In a school garden, several leaf cells form tissue, and different tissues form a leaf. What is the leaf?", listOf("An organ", "A cell", "A tissue", "An organ system"), 0, "A leaf is a plant organ containing different tissues that work together, for example in photosynthesis and gas exchange.", mapOf(1 to "A cell is a smaller unit.", 2 to "A leaf contains several tissue types.", 3 to "A leaf is one organ, not an organ system."), "An organ contains different tissues working together.", "Which level is made of several organs working together?", listOf("Organ system", "Tissue", "Cell", "Organelle"), 0, "An organ system consists of organs that coordinate to perform major functions.")
            )
        }
        return banks[i]
    }
}

/** Stage-level check workspace with durable local evidence and a fresh transfer item. */
@Composable
internal fun MicroLessonStageChecks(
    topicId: String,
    stage: LessonStage,
    database: MeritDatabase,
    onEvidenceChanged: (Boolean) -> Unit
) {
    val checks = remember(topicId, stage) { MicroLessonEngine.checks(topicId, stage) }
    val noteId = "micro-lesson-evidence-" + topicId
    var evidence by remember(topicId, stage) {
        mutableStateOf(runCatching {
            val root = JSONObject(database.getNoteBody(noteId) ?: "{}")
            val saved = JSONObject(root.optString(stage.name, "{}"))
            checks.associate { check ->
                val item = saved.optJSONObject(check.id) ?: JSONObject()
                check.id to MicroLessonCheckEvidence(item.optBoolean("firstPassed"), item.optBoolean("transferPassed"))
            }
        }.getOrDefault(checks.associate { it.id to MicroLessonCheckEvidence() }))
    }
    val allPassed = checks.isNotEmpty() && checks.all { evidence[it.id]?.complete == true }
    LaunchedEffect(topicId, stage, allPassed) { onEvidenceChanged(allPassed) }

    if (checks.isEmpty()) return
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                when (stage) {
                    LessonStage.CONCEPT -> "Check each concept before moving on"
                    LessonStage.EXAMPLES -> "Prove you can work with each idea"
                    else -> "Apply each idea in a new context"
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text("Six syllabus-linked ideas • each check includes a fresh transfer question. One correct guess is not enough to verify understanding.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            checks.forEachIndexed { index, check ->
                MicroLessonCheckItem(
                    number = index + 1,
                    check = check,
                    evidence = evidence[check.id] ?: MicroLessonCheckEvidence(),
                    onEvidence = { updated ->
                        evidence = evidence + (check.id to updated)
                        runCatching {
                            val root = JSONObject(database.getNoteBody(noteId) ?: "{}")
                            val saved = JSONObject(root.optString(stage.name, "{}"))
                            saved.put(check.id, JSONObject()
                                .put("firstPassed", updated.firstPassed)
                                .put("transferPassed", updated.transferPassed))
                            root.put(stage.name, saved)
                            database.saveNote(noteId, topicId, "Concept check evidence", root.toString())
                        }
                    }
                )
            }
            Text(
                if (allPassed) "All six ideas have passed the initial and transfer checks. This stage can now be verified."
                else "Stage verification: ${evidence.values.count { it.complete }} of six ideas demonstrated.",
                color = if (allPassed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun MicroLessonCheckItem(
    number: Int,
    check: MicroLessonCheck,
    evidence: MicroLessonCheckEvidence,
    onEvidence: (MicroLessonCheckEvidence) -> Unit
) {
    var firstSelection by remember(check.id) { mutableStateOf<Int?>(null) }
    var transferSelection by remember(check.id, evidence.firstPassed) { mutableStateOf<Int?>(null) }
    var firstSubmitted by remember(check.id, evidence.firstPassed) { mutableStateOf(evidence.firstPassed) }
    var transferSubmitted by remember(check.id, evidence.transferPassed) { mutableStateOf(evidence.transferPassed) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("$number. ${check.ideaTitle}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(check.skill + " • ${check.syllabusRefs.joinToString()}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(check.prompt, fontWeight = FontWeight.Medium)
        check.options.forEachIndexed { option, label ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = firstSelection == option, onClick = { firstSelection = option })
                Text(label, Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
            }
        }
        Button(
            onClick = {
                val passed = firstSelection == check.correctOption
                firstSubmitted = true
                if (passed) onEvidence(evidence.copy(firstPassed = true))
            },
            enabled = firstSelection != null && !evidence.firstPassed,
            modifier = Modifier.fillMaxWidth()
        ) { Text(if (evidence.firstPassed) "Understanding check passed" else "Check understanding") }
        if (firstSubmitted || evidence.firstPassed) {
            Text(
                if (evidence.firstPassed) check.explanation
                else firstSelection?.let { check.misconceptionFeedback[it] } ?: check.explanation,
                color = if (evidence.firstPassed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            if (!evidence.firstPassed) {
                Text("Hint: ${check.hint}", style = MaterialTheme.typography.bodySmall)
                OutlinedButton(onClick = { firstSelection = null; firstSubmitted = false }, modifier = Modifier.fillMaxWidth()) { Text("Try the concept check again") }
            }
        }
        if (evidence.firstPassed) {
            Text("Fresh transfer task", fontWeight = FontWeight.Bold)
            Text(check.transferPrompt)
            check.transferOptions.forEachIndexed { option, label ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = transferSelection == option, onClick = { transferSelection = option })
                    Text(label, Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                }
            }
            Button(
                onClick = {
                    val passed = transferSelection == check.transferCorrectOption
                    transferSubmitted = true
                    if (passed) onEvidence(evidence.copy(firstPassed = true, transferPassed = true))
                },
                enabled = transferSelection != null && !evidence.transferPassed,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (evidence.transferPassed) "Transfer demonstrated" else "Check transfer") }
            if (transferSubmitted || evidence.transferPassed) {
                Text(
                    if (evidence.transferPassed) check.transferExplanation else "Not yet. Revisit the explanation, then try a different reasoning route. ${check.transferExplanation}",
                    color = if (evidence.transferPassed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                if (!evidence.transferPassed) OutlinedButton(onClick = { transferSelection = null; transferSubmitted = false }, modifier = Modifier.fillMaxWidth()) { Text("Retry transfer task") }
            }
        }
    }
}
