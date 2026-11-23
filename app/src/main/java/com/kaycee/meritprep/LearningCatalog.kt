package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase

data class LearningTopic(
    val id: String,
    val title: String,
    val subjectCode: String,
    val introduction: String,
    val objectives: List<String>,
    val sections: List<Pair<String, String>>,
    val checkQuestion: String,
    val checkOptions: List<String>,
    val correctOption: Int,
    val explanation: String
)

object LearningCatalog {
    val topics = listOf(
        LearningTopic(
            "bio-cell", "Cell structure and organisation", "BIO",
            "Begin with something familiar: your skin, blood, muscles and every organ in your body are made of cells. A cell is the smallest unit that can carry out the processes of life. Some organisms, such as bacteria and Amoeba, consist of one cell; humans and flowering plants consist of many specialised cells working together. This lesson builds from what a cell is, to how its parts work, and finally to how cells form tissues, organs and complete organisms.",
            listOf("Explain the three core statements of cell theory", "Identify the main structures in typical plant and animal cells", "Relate each major organelle to its function", "Compare prokaryotic and eukaryotic cells at an introductory level", "Arrange the levels of biological organisation in the correct order"),
            listOf(
                "Cell theory" to "Cell theory states that living organisms are made of one or more cells, the cell is the basic unit of life, and new cells arise from pre-existing cells. Unicellular organisms perform all life processes within one cell; multicellular organisms have specialised cells.",
                "Cell membrane and cytoplasm" to "The cell-surface membrane forms a selectively permeable boundary. It regulates movement of substances into and out of the cell. The cytoplasm contains the structures where many metabolic reactions occur.",
                "Nucleus and organelles" to "The nucleus contains most of the cell's genetic material and helps control cellular activities. Mitochondria are major sites of aerobic respiration. Ribosomes synthesise proteins. Chloroplasts contain chlorophyll and carry out photosynthesis in plants and algae.",
                "Plant and animal cells" to "Typical plant cells have a cellulose cell wall, a large permanent vacuole and may contain chloroplasts. Animal cells do not have a cellulose cell wall or chloroplasts, and their vacuoles are usually small or temporary.",
                "A guided tour of the cell" to "Think of a cell as a small, organised workplace. The cell membrane controls what enters and leaves. The cytoplasm is the medium in which many reactions occur. The nucleus stores DNA and regulates gene expression. Mitochondria release usable energy through aerobic respiration. Ribosomes assemble proteins. The rough endoplasmic reticulum helps make and move proteins; the smooth endoplasmic reticulum is involved in lipid synthesis and other processes. The Golgi apparatus modifies, sorts and packages materials. Lysosomes contain digestive enzymes in many animal cells. Chloroplasts capture light energy for photosynthesis in plants and algae. A vacuole stores cell sap and helps maintain turgor in plant cells.",
                "Prokaryotic and eukaryotic cells" to "Prokaryotic cells, such as bacteria, do not have a membrane-bound nucleus. Their DNA is located in a nucleoid region, and they lack membrane-bound organelles such as mitochondria. Eukaryotic cells, including plant and animal cells, have a membrane-bound nucleus and membrane-bound organelles. Both cell types have a cell membrane, cytoplasm, DNA and ribosomes. Do not describe bacteria as having no DNA or no ribosomes: they have both.",
                "From cells to organisms" to "In a multicellular organism, similar cells may work together as a tissue; different tissues combine to form an organ; organs work together in an organ system; and organ systems make up an organism. Example: muscle cells form muscle tissue; cardiac muscle tissue forms part of the heart; the heart works in the circulatory system; the circulatory system belongs to a human organism. A unicellular organism does not need this hierarchy: its one cell performs all essential life functions.",
                "How to read a biological diagram" to "When studying a cell diagram, first identify the outline and orientation. Locate the nucleus, then use the relative positions and shapes of other structures. A label line should touch the structure being named, should be straight where possible, and should not cross another label line. Draw only what is visible; do not add an organelle simply because you expect it to be present. In an examination, clear proportions and correct labels matter more than artistic shading.",
                "Common misconceptions to avoid" to "The cell wall is not the same as the cell membrane: the wall provides support, while the membrane controls exchange. Not every plant cell contains chloroplasts; root cells usually lack them because they are not exposed to light for photosynthesis. Mitochondria are not the only place where energy-related reactions occur, but they are the main site of aerobic respiration in most eukaryotic cells. Cells are not all identical in shape or function; their structures are specialised for their roles."
            ),
            "Which structure is the main site of aerobic respiration in most eukaryotic cells?",
            listOf("Ribosome", "Mitochondrion", "Cell wall", "Golgi apparatus"), 1,
            "Mitochondria contain the machinery for most stages of aerobic respiration and ATP production in eukaryotic cells."
        ),
        LearningTopic(
            "bio-classification", "Classification and diversity of organisms", "BIO",
            "Imagine being asked to identify every living thing in a forest. There are millions of kinds of organisms, and many look alike. Biologists solve this problem by classifying organisms: arranging them into groups using shared characteristics and evidence of relationships. Classification gives scientists a common language and helps us compare the diversity of life.",
            listOf(
                "Explain why biologists classify organisms",
                "Distinguish classification, identification and naming",
                "Arrange the main taxonomic ranks from broad to specific",
                "Use binomial nomenclature correctly",
                "Compare the broad features of the five traditional kingdoms",
                "Distinguish major plant and animal groups using observable features"
            ),
            listOf(
                "Why classification is useful" to "Classification organises the enormous variety of organisms into manageable groups. It helps us identify organisms, communicate accurately, compare similarities and differences, study evolutionary relationships and predict features shared by related organisms. A classification system is a scientific model: groups may be revised when new evidence, especially molecular evidence, becomes available.",
                "Three words that mean different things" to "Classification is the arrangement of organisms into groups. Identification is the process of deciding which organism you have observed, using features or a key. Nomenclature is the formal system used to give organisms scientific names. For example, using a leaf key to identify a plant is identification; placing it in its family is classification; writing its accepted scientific name is nomenclature.",
                "The taxonomic hierarchy" to "The familiar ranks, from broadest to most specific, are Kingdom, Phylum (called Division in many plant classifications), Class, Order, Family, Genus and Species. Remember the sequence as K–P–C–O–F–G–S. A kingdom contains many phyla; a phylum contains classes; each lower rank contains organisms with increasingly shared characteristics. A species is commonly treated as a group of organisms that can interbreed naturally to produce fertile offspring, although this definition does not fit every organism, especially asexual and fossil forms.",
                "Scientific naming: binomial nomenclature" to "Each species is given a two-part scientific name: the genus name followed by the specific epithet. The genus begins with a capital letter; the specific epithet begins with a lower-case letter. Both words are italicised when typed, for example Homo sapiens; when handwritten, underline each word separately. After the first full mention, the genus may be abbreviated, as in H. sapiens. Scientific names reduce confusion caused by different local names for the same organism.",
                "The five traditional kingdoms" to "In the traditional five-kingdom scheme, Monera includes prokaryotic organisms such as bacteria; they lack a membrane-bound nucleus. Protista includes diverse mainly unicellular eukaryotes such as Amoeba and many algae. Fungi are eukaryotes that absorb nutrients, commonly have chitin in their cell walls and reproduce by spores. Plantae are multicellular photosynthetic organisms with cellulose cell walls. Animalia are multicellular consumers whose cells lack cell walls. This is a syllabus-level framework; modern biology also uses domain-level classification and has revised the placement of some groups.",
                "How to compare organisms" to "Do not classify an organism from one superficial feature alone. Compare several useful characteristics: cell type, number of cells, cell-wall composition, mode of nutrition, movement, body organisation and reproduction. For example, a mushroom is not a plant: it does not photosynthesise, obtains nutrients by absorption and has chitin-containing cell walls. A bacterium is not a tiny animal: it is prokaryotic and has no membrane-bound nucleus.",
                "A first look at plant and animal diversity" to "Plants may be grouped by features such as vascular tissue, seeds and flowers. Mosses are non-vascular and reproduce by spores; ferns have vascular tissue but no seeds; conifers produce naked seeds, commonly in cones; flowering plants produce flowers and seeds enclosed in fruits. Animals are often introduced through major invertebrate groups and vertebrates. Invertebrates lack a vertebral column; vertebrates possess a backbone and include fish, amphibians, reptiles, birds and mammals. Use diagnostic features rather than size or habitat alone.",
                "Viruses and the boundary of life" to "Viruses are acellular infectious agents: they are not made of cells and cannot reproduce independently. They contain genetic material (DNA or RNA) inside a protein coat, and some have an outer envelope. They replicate only inside suitable host cells using host machinery. For this reason, viruses are not placed in any of the five traditional kingdoms. Avoid calling a virus a bacterium or describing it as a cell.",
                "How to answer classification questions" to "Read the question for the feature it asks about, then compare the options using that feature. If asked to identify a prokaryote, look for the absence of a membrane-bound nucleus. If asked to distinguish fungi from green plants, consider nutrition and cell-wall composition. For a naming question, check the two-part format and capitalisation. In a practical examination, describe visible evidence first and make an identification only when the evidence supports it."
            ),
            "Which statement best distinguishes a prokaryotic cell from a eukaryotic cell?",
            listOf(
                "A prokaryotic cell has no DNA",
                "A prokaryotic cell has no membrane-bound nucleus",
                "A prokaryotic cell always has a cellulose cell wall",
                "A prokaryotic cell is always multicellular"
            ), 1,
            "Prokaryotic cells contain DNA and ribosomes but lack a membrane-bound nucleus. Their DNA is located in a nucleoid region."
        ),
        LearningTopic(
            "chem-atomic", "Atomic structure", "CHEM",
            "All ordinary matter is made of atoms. The arrangement and number of subatomic particles explain an element's identity, isotope and electrical charge.",
            listOf("Identify protons, neutrons and electrons", "Explain atomic number and mass number", "Determine the numbers of subatomic particles in simple atoms and ions"),
            listOf(
                "Subatomic particles" to "A proton has a relative charge of +1 and a relative mass of about 1. A neutron has no charge and a relative mass of about 1. An electron has a charge of -1 and a much smaller relative mass, approximately 1/1836.",
                "Atomic and mass numbers" to "The atomic number (proton number) is the number of protons in the nucleus. The mass number (nucleon number) is the total number of protons and neutrons. In a neutral atom, the number of electrons equals the number of protons.",
                "Isotopes and ions" to "Isotopes are atoms of the same element with the same proton number but different numbers of neutrons. An ion forms when an atom or group of atoms gains or loses electrons. Losing electrons forms a positive ion; gaining electrons forms a negative ion."
            ),
            "An atom has atomic number 11 and mass number 23. How many neutrons does it contain?",
            listOf("11", "12", "23", "34"), 1,
            "Neutrons = mass number − atomic number = 23 − 11 = 12."
        ),
        LearningTopic(
            "phy-motion", "Motion and acceleration", "PHY",
            "Mechanics describes how objects move and how forces affect their motion. Clear definitions and correct units are essential when solving motion problems.",
            listOf("Distinguish distance from displacement", "Define speed, velocity and acceleration", "Use basic equations of uniform acceleration"),
            listOf(
                "Distance and displacement" to "Distance is the total length of the path travelled and is a scalar quantity. Displacement is the straight-line change in position in a specified direction and is a vector quantity.",
                "Speed and velocity" to "Average speed is total distance divided by total time. Average velocity is displacement divided by time. Speed has magnitude only, while velocity includes direction.",
                "Acceleration" to "Acceleration is the rate of change of velocity with time: a = (v − u)/t. Its SI unit is metre per second squared (m/s²). A negative value may indicate acceleration opposite to the chosen positive direction."
            ),
            "A car's velocity increases from 5 m/s to 17 m/s in 4 s. What is its average acceleration?",
            listOf("2 m/s²", "3 m/s²", "4 m/s²", "5.5 m/s²"), 1,
            "a = (v − u)/t = (17 − 5)/4 = 3 m/s²."
        ),
        LearningTopic(
            "math-algebra", "Introduction to algebra", "MATH",
            "Algebra uses letters and symbols to represent numbers and relationships. It allows general rules to be written and unknown values to be found systematically.",
            listOf("Identify variables, coefficients and constants", "Simplify like terms", "Solve simple linear equations"),
            listOf(
                "Expressions and terms" to "In 4x + 7, x is the variable, 4 is its coefficient and 7 is a constant. Terms are separated by addition or subtraction signs. Only like terms, which have the same variable part, can be combined.",
                "Simplifying expressions" to "Collect like terms by adding or subtracting their coefficients. For example, 3x + 5x − 2 becomes 8x − 2. Unlike terms such as x and x² cannot be combined.",
                "Solving linear equations" to "An equation remains balanced when the same operation is applied to both sides. To solve 2x + 5 = 17, subtract 5 from both sides to get 2x = 12, then divide by 2 to obtain x = 6."
            ),
            "Solve the equation 3x − 4 = 11.",
            listOf("3", "5", "7", "15"), 1,
            "Add 4 to both sides: 3x = 15. Divide by 3: x = 5."
        ),
        LearningTopic(
            "eng-comprehension", "Reading comprehension", "ENG",
            "Comprehension tests the ability to understand a passage, identify its main ideas and interpret information accurately. Answers should be grounded in the passage rather than assumptions.",
            listOf("Identify the central idea of a passage", "Distinguish stated facts from inferences", "Use context to interpret unfamiliar words"),
            listOf(
                "Read for the central idea" to "Read the passage once to understand its subject and purpose. Then identify the sentence or group of sentences that expresses the writer's main message. A title or repeated phrase may provide a clue, but the whole passage must support the answer.",
                "Locate supporting evidence" to "For factual questions, return to the relevant paragraph and locate the evidence. Avoid choosing an answer simply because it is generally true; it must answer what the passage says.",
                "Infer meaning from context" to "An inference is a conclusion supported by clues in the text. For an unfamiliar word, examine nearby words, contrast markers and the sentence's overall meaning before selecting a definition."
            ),
            "When answering a factual comprehension question, what should guide your answer?",
            listOf("Personal opinion", "Information supported by the passage", "What is generally believed", "An unrelated example"), 1,
            "A comprehension answer must be supported by the passage. Personal knowledge should not replace textual evidence."
        ),
        LearningTopic(
            "eco-demand", "Demand and supply", "ECO",
            "Demand and supply describe the behaviour of buyers and sellers in a market. Their interaction helps explain how market prices and quantities are determined.",
            listOf("Define demand and supply", "Explain the law of demand", "Distinguish a movement along a curve from a shift of the curve"),
            listOf(
                "Demand" to "Demand is the quantity of a good or service consumers are willing and able to buy at different prices over a given period. The law of demand states that, other things being equal, quantity demanded generally falls as price rises.",
                "Supply" to "Supply is the quantity producers are willing and able to offer for sale at different prices over a given period. The law of supply states that, other things being equal, quantity supplied generally rises as price rises.",
                "Market equilibrium" to "Equilibrium occurs where quantity demanded equals quantity supplied. A price below equilibrium may create a shortage; a price above equilibrium may create a surplus. Changes in income, tastes, input costs or technology can shift demand or supply."
            ),
            "If the price of a normal good rises, what usually happens to its quantity demanded, other things being equal?",
            listOf("It increases", "It decreases", "It remains unlimited", "Supply becomes zero"), 1,
            "The law of demand predicts an inverse relationship between a good's price and its quantity demanded, other things being equal."
        ),
        LearningTopic(
            "gov-arms", "Arms of government", "GOV",
            "The separation of governmental powers distributes public authority among institutions. In a constitutional democracy, this helps limit concentration of power and supports accountability.",
            listOf("Identify the legislative, executive and judicial arms", "State the primary function of each arm", "Explain checks and balances in a constitutional system"),
            listOf(
                "Legislature" to "The legislature makes laws, approves public expenditure through budgetary processes and performs oversight functions. In Nigeria, legislative authority at the federal level is vested in the National Assembly, subject to the Constitution.",
                "Executive" to "The executive implements and administers laws and public policy. At the federal level, executive powers are vested in the President, exercised subject to the Constitution and other applicable laws.",
                "Judiciary" to "The judiciary interprets and applies the law and resolves disputes. Courts can determine whether actions by public authorities comply with the Constitution and valid laws.",
                "Checks and balances" to "Checks and balances allow each arm to limit or scrutinise aspects of the others' powers. The exact powers and procedures are defined by the applicable constitution and laws."
            ),
            "Which arm of government is primarily responsible for interpreting the law?",
            listOf("Legislature", "Executive", "Judiciary", "Civil service"), 2,
            "The judiciary interprets and applies the law when deciding cases and resolving legal disputes."
        )
    )

    fun forSubject(code: String): List<LearningTopic> = topics.filter { it.subjectCode == code }
    fun find(topicId: String): LearningTopic? = topics.firstOrNull { it.id == topicId }
}

@Composable
fun LessonScreen(topicId: String) {
    val topic = LearningCatalog.find(topicId)
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var completed by remember(topicId) { mutableStateOf(database.isLessonCompleted(topicId)) }
    var selected by remember(topicId) { mutableIntStateOf(-1) }
    var checked by remember(topicId) { mutableStateOf(false) }

    if (topic == null) {
        Column(Modifier.fillMaxSize().padding(24.dp)) {
            Text("Lesson not available", style = MaterialTheme.typography.headlineSmall)
            Text("This lesson has not yet been added to the current learning catalogue.")
        }
        return
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(topic.subjectCode, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text(topic.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(topic.introduction, style = MaterialTheme.typography.bodyLarge)
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("By the end of this lesson, you should be able to", fontWeight = FontWeight.SemiBold)
                topic.objectives.forEach { objective -> Text("• $objective", style = MaterialTheme.typography.bodyMedium) }
            }
        }
        topic.sections.forEachIndexed { index, section ->
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("${index + 1}. ${section.first}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                Text(section.second, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Divider()
        Text("Check your understanding", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(topic.checkQuestion, style = MaterialTheme.typography.titleMedium)
        topic.checkOptions.forEachIndexed { index, option ->
            OutlinedButton(
                onClick = { if (!checked) selected = index },
                enabled = !checked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text((if (selected == index) "✓  " else "") + option)
            }
        }
        if (checked) {
            val correct = selected == topic.correctOption
            Text(
                if (correct) "Correct" else "Review this concept",
                color = if (correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
            Text(topic.explanation)
            OutlinedButton(onClick = { selected = -1; checked = false }) { Text("Try again") }
        } else {
            Button(onClick = { checked = true }, enabled = selected >= 0, modifier = Modifier.fillMaxWidth()) {
                Text("Check answer")
            }
        }
        Button(
            onClick = {
                database.markLessonCompleted(topic.id)
                completed = true
            },
            enabled = !completed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (completed) "Lesson completed" else "Mark lesson complete")
        }
        Text(
            "Academic status: beginner-focused teaching draft mapped to the JAMB cell-organisation objectives and WAEC cell-structure outcomes. Independent subject review is still required before formal approval.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
