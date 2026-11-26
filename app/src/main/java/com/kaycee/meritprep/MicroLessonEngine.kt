package com.kaycee.meritprep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
        Idea("bio-cell-membrane", "Cell membrane and exchange", listOf(JAMB_CELL, "JAMB-BIO-B3-TRANSPORT-001", WAEC_CELL)),
        Idea("bio-cell-organelles", "Organelles and their functions", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-cell-types", "Plant and animal cell differences", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-prokaryote-eukaryote", "Prokaryotic and eukaryotic cells", listOf(JAMB_CELL, WAEC_CELL)),
        Idea("bio-levels-organisation", "Levels of biological organisation", listOf(JAMB_CELL, WAEC_CELL))
    )

    private const val JAMB_CLASSIFICATION = "JAMB-BIO-A2-DIVERSITY-001"
    private const val WAEC_CLASSIFICATION = "WAEC-NG-BIO-A1-CLASSIFICATION-001"

    private val classificationIdeas = listOf(
        Idea("bio-classification-purpose", "Classification, identification and nomenclature", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION)),
        Idea("bio-taxonomic-hierarchy", "Taxonomic hierarchy and species", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION)),
        Idea("bio-binomial-nomenclature", "Scientific naming and binomial nomenclature", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION)),
        Idea("bio-five-kingdoms", "Characteristics of the five traditional kingdoms", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION)),
        Idea("bio-plant-divisions", "Major plant divisions", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION)),
        Idea("bio-animal-groups", "Major animal groups", listOf(JAMB_CLASSIFICATION, WAEC_CLASSIFICATION))
    )
 
    private const val JAMB_ADAPTATION = "JAMB-BIO-A3-ADAPTATION-001"
    private const val WAEC_ADAPTATION = "WAEC-NG-BIO-A7-ADAPTATION-001"
    private const val WAEC_NIGERIA_EVOLUTION = "WAEC-NG-BIO-C-EVOLUTION-001"

    private val adaptationIdeas = listOf(
        Idea("bio-adaptation-definition", "Adaptation, natural selection and acclimatisation", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION)),
        Idea("bio-structural-adaptation", "Structural adaptations", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION, WAEC_NIGERIA_EVOLUTION)),
        Idea("bio-physiological-adaptation", "Physiological adaptations", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION, WAEC_NIGERIA_EVOLUTION)),
        Idea("bio-behavioural-adaptation", "Behavioural adaptations, hibernation and aestivation", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION)),
        Idea("bio-adaptive-colouration", "Camouflage, warning colouration and mimicry", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION, WAEC_NIGERIA_EVOLUTION)),
        Idea("bio-social-insect-castes", "Social behaviour and castes in termites and bees", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION)),
        Idea("bio-adaptation-functions", "Adaptations for feeding, defence, mate attraction, temperature and water balance", listOf(JAMB_ADAPTATION, WAEC_ADAPTATION, WAEC_NIGERIA_EVOLUTION))
    )

    fun checks(topicId: String, stage: LessonStage): List<MicroLessonCheck> {
        if (stage !in setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)) return emptyList()
        val selectedIdeas = when (topicId) {
            "bio-cell" -> ideas
            "bio-classification" -> classificationIdeas
            "bio-adaptation" -> adaptationIdeas
            else -> return emptyList()
        }
        return selectedIdeas.mapIndexed { index, idea ->
            val pair = when (topicId) { "bio-classification" -> classificationQuestions(index, stage); "bio-adaptation" -> adaptationQuestions(index, stage); else -> questions(index, stage) }
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



    private fun adaptationQuestions(i: Int, stage: LessonStage): QuestionPair {
        val banks = when (stage) {
            LessonStage.CONCEPT -> listOf(
                QuestionPair("Which statement best defines a biological adaptation?", listOf("A temporary change an individual makes because it needs to survive", "An inherited feature that can improve survival or reproductive success in a particular environment", "Any unusual body feature, whether useful or not", "A behaviour learned by one animal during its lifetime"), 1, "An adaptation is a heritable characteristic that can affect survival or reproductive success in a particular environment. Its frequency can change in a population over generations through natural selection.", mapOf(0 to "That describes an individual adjustment, not an inherited adaptation.", 2 to "A feature is not an adaptation merely because it looks unusual; its function and evolutionary history matter.", 3 to "Learning may change behaviour, but it does not by itself create an inherited adaptation."), "Ask whether the feature is heritable and whether it can affect reproductive success across generations.", "A person sweats more after moving to a hot climate. Is this change, by itself, an inherited adaptation?", listOf("Yes, because it occurs in hot weather", "No, it is an individual acclimatisation response", "Yes, because all sweating is genetic evolution", "No, because humans cannot adapt"), 1, "Increased sweating during an individual's lifetime is acclimatisation. It does not by itself alter inherited traits in the population."),
                QuestionPair("Which is a structural adaptation?", listOf("A cactus has leaves reduced to spines", "A mammal produces concentrated urine", "A bird migrates seasonally", "A lizard enters dormancy during an unfavourable season"), 0, "Structural adaptations are physical features of body form or anatomy. Cactus spines are modified leaves and can reduce exposed surface area.", mapOf(1 to "Concentrated urine is a physiological function.", 2 to "Migration is a behavioural pattern.", 3 to "Seasonal dormancy is a behavioural or physiological response, not a body structure."), "Choose the option that describes a physical body feature rather than an internal process or action.", "A mangrove has roots that project above waterlogged mud and expose tissues to air. What type of adaptation is this?", listOf("Structural", "Behavioural", "Learned", "A reproductive disease"), 0, "Pneumatophores are specialised root structures that help gas exchange in waterlogged, oxygen-poor soils."),
                QuestionPair("Which example is primarily a physiological adaptation?", listOf("A fish has a streamlined body", "A desert mammal produces highly concentrated urine", "A bird migrates to another region", "A grasshopper has enlarged hind legs"), 1, "Physiological adaptations involve internal processes. Producing concentrated urine helps some desert mammals conserve water.", mapOf(0 to "Streamlined body shape is structural.", 2 to "Migration is behavioural.", 3 to "Enlarged hind legs are structural."), "Look for an internal body process rather than a visible body part or activity.", "Some mangrove species limit salt entry or remove excess salt from tissues. This is mainly what type of adaptation?", listOf("Physiological", "Structural only", "Behavioural", "Camouflage"), 0, "Salt regulation involves internal physiological mechanisms; the exact mechanism differs among mangrove species."),
                QuestionPair("Which is a behavioural adaptation?", listOf("A fish has gills", "A cactus has a thick water-storing stem", "An animal is active mainly at night", "A mammal has insulating fur"), 2, "Nocturnal activity is a behavioural pattern. It can reduce exposure to daytime heat or alter contact with predators and prey.", mapOf(0 to "Gills are respiratory structures.", 1 to "A thick stem is a structural feature.", 3 to "Fur is a structural feature that can reduce heat loss."), "Choose the option describing a pattern of activity.", "A West African lungfish survives a dry period by becoming dormant in moist mud. What is this seasonal strategy called?", listOf("Aestivation", "Migration", "Camouflage", "Courtship"), 0, "Aestivation is dormancy during hot or dry unfavourable conditions. Some African lungfish can aestivate in mud."),
                QuestionPair("A harmless organism resembles a harmful or defended organism and gains protection because predators avoid it. What is this called?", listOf("Camouflage", "Mimicry", "Hibernation", "Migration"), 1, "Mimicry is resemblance to another organism or object that changes interactions. In Batesian mimicry, a harmless species resembles a defended species.", mapOf(0 to "Camouflage reduces visibility against a background.", 2 to "Hibernation is seasonal dormancy, usually associated with cold conditions.", 3 to "Migration is movement between areas."), "The organism resembles another organism, rather than simply blending into the background.", "A moth's wing pattern makes it difficult to distinguish from tree bark. Which adaptive colouration is this?", listOf("Camouflage", "Warning colouration", "Aestivation", "Territoriality"), 0, "Camouflage makes an organism less conspicuous against its surroundings."),
                QuestionPair("Which statement correctly describes caste organisation in a termite colony?", listOf("Every termite performs exactly the same task", "Workers, soldiers and reproductives have different roles", "Only the queen can feed", "Soldiers are the only reproductive individuals"), 1, "Termite colonies commonly include reproductives, workers and soldiers. Their specialised roles support colony reproduction, maintenance and defence.", mapOf(0 to "Division of labour means individuals perform different roles.", 2 to "Workers gather and process food and care for the colony; the queen's main role is reproduction.", 3 to "Soldiers specialise in defence; reproductives produce offspring."), "Think about division of labour: which caste reproduces, which maintains the nest, and which defends it?", "In a honeybee colony, which caste normally lays most of the eggs?", listOf("Queen", "Worker", "Drone", "Soldier"), 0, "The queen is the principal reproductive female; workers perform many maintenance and foraging tasks, while drones are males involved in reproduction."),
                QuestionPair("A male bird displays bright feathers and performs a courtship dance. Which function is most directly served?", listOf("Mate attraction", "Water conservation", "Respiration", "Excretion"), 0, "Courtship displays can increase the chance of attracting a mate and reproducing. A feature may have a reproductive function even if it carries costs such as increased visibility.", mapOf(1 to "Water conservation concerns limiting water loss.", 2 to "Respiration is gas exchange and cellular energy release.", 3 to "Excretion removes metabolic waste."), "Identify the function linked most directly to reproductive success.", "A duck's webbed feet increase the surface area pushing against water during swimming. Which function is served?", listOf("Locomotion and feeding access", "Mate attraction only", "Warning colouration", "Aestivation"), 0, "Webbed feet help generate thrust in water, supporting movement and access to food.")
            )
            LessonStage.EXAMPLES -> listOf(
                QuestionPair("A population of insects contains heritable light and dark colour variants. Birds more easily see the light insects on dark tree bark. What is expected after several generations, if other conditions are similar?", listOf("Every light insect changes its colour to dark", "The dark variant may become more frequent because its carriers leave more offspring", "The population stops reproducing", "Colour is no longer inherited"), 1, "Natural selection changes the frequency of heritable variants when they differ in reproductive success. Individuals do not change their inherited colour because they need to.", mapOf(0 to "Natural selection does not cause an individual to transform its inherited trait.", 2 to "The scenario does not imply reproduction stops.", 3 to "The question states the variants are heritable."), "Track which individuals survive and reproduce more often, then ask which inherited variant their offspring may carry.", "After insecticide use, mosquitoes with a heritable resistance variant survive and reproduce more often. What may happen to resistance frequency?", listOf("It may increase over generations", "Every mosquito becomes resistant immediately", "Resistance disappears because of exposure", "Mosquitoes stop inheriting genes"), 0, "If resistance is heritable and resistant mosquitoes leave more offspring, resistance can become more common in the population."),
                QuestionPair("A fish lives in fast-moving water. Which feature is most directly useful for efficient movement?", listOf("A streamlined body that reduces drag", "A thick insulating coat", "A water-storing stem", "A pollen-producing flower"), 0, "A streamlined body reduces resistance as the fish moves through water. Fins help control direction and stability.", mapOf(1 to "Insulating coats are associated with reducing heat loss in some animals.", 2 to "A water-storing stem is a plant feature.", 3 to "Flowers are reproductive structures of angiosperms."), "Connect the feature to the physical challenge of moving through water.", "A desert plant has a thick cuticle and leaves reduced to spines. Which challenge is it mainly addressing?", listOf("Excessive water loss", "Low oxygen in deep water", "High salt in seawater only", "Lack of pollinators only"), 0, "A thick cuticle and reduced leaf surface can reduce water loss in dry environments."),
                QuestionPair("A desert rodent produces urine with a high solute concentration. What is the advantage?", listOf("It conserves water by reducing water lost in urine", "It increases water loss", "It allows photosynthesis", "It prevents all respiration"), 0, "Concentrated urine removes dissolved wastes in a smaller volume of water, helping conserve body water.", mapOf(1 to "The adaptation reduces, rather than increases, water loss.", 2 to "Animals do not photosynthesise.", 3 to "Respiration is essential and is not stopped."), "Compare the volume of water lost when wastes are excreted in dilute versus concentrated urine.", "A salt-tolerant plant limits salt entry into its tissues. Which environmental challenge is it responding to?", listOf("High salinity", "Low light only", "Predation by birds", "Cold winter only"), 0, "Salt regulation helps plants maintain internal water and ion balance in saline environments."),
                QuestionPair("Which comparison of hibernation and aestivation is most accurate?", listOf("Hibernation is dormancy associated with cold conditions; aestivation is dormancy during hot or dry conditions", "Both mean daily sleep at night", "Aestivation occurs only in birds", "Hibernation is the same as migration"), 0, "Hibernation is a prolonged dormant state associated with cold or food scarcity in some animals. Aestivation occurs during hot or dry unfavourable periods. Details vary among species.", mapOf(1 to "Ordinary sleep is not the same as seasonal dormancy.", 2 to "Aestivation occurs in several animal groups, including some fish and amphibians.", 3 to "Migration is movement between regions, not dormancy."), "Compare the environmental condition that triggers each state.", "A lungfish remains inactive in a mud chamber during drought. Which term best describes this?", listOf("Aestivation", "Hibernation", "Courtship", "Camouflage"), 0, "Aestivation helps some animals survive hot or dry periods."),
                QuestionPair("A harmless hoverfly has yellow-and-black markings similar to a stinging wasp. Which concept best explains the possible protection?", listOf("Batesian mimicry", "Aestivation", "Thermoregulation", "Migration"), 0, "In Batesian mimicry, a harmless species resembles a defended or harmful species and may be avoided by predators that have learned the warning pattern.", mapOf(1 to "Aestivation is dormancy in hot or dry conditions.", 2 to "Thermoregulation is control of body temperature.", 3 to "Migration is seasonal movement."), "The hoverfly resembles a defended species; it is not simply matching the background.", "A brightly coloured toxic animal advertises that it is dangerous. What is this pattern called?", listOf("Warning colouration", "Camouflage", "Nocturnality", "Aestivation"), 0, "Warning colouration signals that an organism may be toxic, distasteful or otherwise defended."),
                QuestionPair("In a termite colony, which caste is most directly specialised for defence?", listOf("Soldiers", "Workers", "Queen", "Alates only"), 0, "Soldier termites defend the colony. Workers maintain the nest, gather/process food and care for young; reproductives produce offspring.", mapOf(1 to "Workers mainly maintain and provision the colony.", 2 to "The queen is a reproductive female.", 3 to "Winged reproductives disperse and establish new colonies."), "Match the caste to the job of protecting the nest from attack.", "Which honeybee caste commonly forages for nectar and pollen and cares for brood?", listOf("Worker", "Drone", "Queen", "Soldier"), 0, "Worker bees perform foraging, brood care, comb construction and many colony maintenance tasks."),
                QuestionPair("A bird's beak shape is suited to cracking hard seeds. Which adaptation function is illustrated?", listOf("Feeding", "Mate attraction", "Hibernation", "Water loss"), 0, "Beak form can improve access to particular food types. The relationship between form and feeding function is one example of adaptation.", mapOf(1 to "Mate attraction concerns reproductive signalling.", 2 to "Hibernation is seasonal dormancy.", 3 to "Water loss is not the direct function described."), "Ask what task the beak performs when the bird feeds.", "A porcupine's sharp quills discourage predators from attacking. Which function is served?", listOf("Defence", "Pollination", "Photosynthesis", "Migration"), 0, "Quills are structural defences that can reduce the likelihood of successful predation.")
            )
            else -> listOf(
                QuestionPair("A dark-coloured insect becomes common on dark tree bark after birds preferentially eat more visible light insects. Which explanation is best?", listOf("Natural selection increases the frequency of a heritable variant with higher reproductive success", "Individual insects deliberately darken their bodies", "The environment changes the genes of every insect in one generation", "All colour variants have identical survival"), 0, "The dark variant is less conspicuous in this setting. If colour is heritable, differential survival and reproduction can increase its frequency across generations.", mapOf(1 to "Individuals do not change inherited colour by intention.", 2 to "The model does not imply every individual gene changes at once.", 3 to "The scenario explicitly describes differential predation."), "Follow the sequence: inherited variation → different survival/reproduction → change in population frequency.", "A resistant mosquito survives insecticide exposure and leaves more offspring than susceptible mosquitoes. What is the population-level outcome?", listOf("Resistance may become more frequent", "All mosquitoes become resistant by choice", "The resistant mosquito loses its genes", "The species stops evolving"), 0, "Natural selection can increase a heritable resistance variant when carriers leave more offspring."),
                QuestionPair("A plant growing in waterlogged, oxygen-poor soil has aerial roots with openings above the mud. What is their adaptive role?", listOf("Improve gas exchange with the air", "Attract pollinating insects", "Produce seeds in cones", "Store animal fat"), 0, "Pneumatophores project above waterlogged soil and assist gas exchange where soil oxygen is limited.", mapOf(1 to "Pollinator attraction is associated with flowers and signals.", 2 to "Cone-bearing seeds are a gymnosperm reproductive feature.", 3 to "Plants do not store animal fat."), "Think about which resource is limited in saturated mud: oxygen for root respiration.", "A cactus reduces leaf area and has a thick waxy cuticle. Which environmental pressure is most relevant?", listOf("Water scarcity and evaporative loss", "High dissolved oxygen in a pond", "Low salinity in seawater", "Lack of gravity"), 0, "Reduced leaf area and a cuticle can lower water loss in arid conditions."),
                QuestionPair("A plant in a hot, dry habitat opens its stomata mainly at night and fixes carbon dioxide into organic acids for later use. What advantage does this CAM pathway provide?", listOf("It can reduce water loss while allowing carbon dioxide uptake", "It makes the plant independent of water", "It changes the plant into an animal", "It prevents all respiration"), 0, "CAM plants take in much of their carbon dioxide at night when conditions are cooler and often more humid, reducing water loss compared with opening stomata during the hot day.", mapOf(1 to "CAM reduces water loss; it does not remove the need for water.", 2 to "Photosynthetic pathways do not change a plant into an animal.", 3 to "Plants still respire."), "Relate the timing of stomatal opening to temperature, humidity and evaporation.", "A salt-tolerant mangrove limits salt entry at the root surface. Which internal challenge is this mechanism helping manage?", listOf("Excess salt and water-potential imbalance", "Predation by birds", "Low light intensity only", "Loss of pollinators"), 0, "Salt exclusion or regulation helps maintain ion and water balance in saline environments."),
                QuestionPair("During drought, some West African lungfish survive in a dormant state within moist mud. Which statement is correct?", listOf("This is aestivation, a response to hot or dry unfavourable conditions", "This is hibernation caused by winter snow", "This is camouflage that changes the fish's colour", "This is migration to a new river"), 0, "Aestivation is dormancy during hot or dry conditions. Some African lungfish can survive drought in a moist mud chamber.", mapOf(1 to "The described condition is drought, not cold winter.", 2 to "The example describes dormancy, not colour matching.", 3 to "The animal remains dormant rather than migrating."), "Focus on the dry-season trigger and the dormant state.", "A temperate mammal enters prolonged dormancy during a cold winter. Which term is usually used?", listOf("Hibernation", "Aestivation", "Mimicry", "Territorial display"), 0, "Hibernation is prolonged dormancy associated with cold conditions in some animals."),
                QuestionPair("A butterfly rests on a leaf whose colour and pattern closely match its wings. What advantage may this provide?", listOf("Reduced detection by predators", "Increased salt excretion", "Faster photosynthesis", "Production of milk"), 0, "Camouflage can make an organism less conspicuous against its background, reducing detection by predators or prey.", mapOf(1 to "Salt excretion is a physiological process in some organisms.", 2 to "Animals do not photosynthesise.", 3 to "Milk production is a mammalian reproductive feature."), "The butterfly blends with its surroundings rather than warning predators of toxicity.", "A toxic frog has bright colours that predators learn to avoid. Which function do the colours serve?", listOf("Warning signal", "Camouflage", "Water storage", "Migration route"), 0, "Warning colouration advertises chemical defence or unpalatability."),
                QuestionPair("A termite mound contains workers that maintain the nest, soldiers that defend it and reproductives that produce offspring. What biological principle does this show?", listOf("Division of labour within a social colony", "All individuals have identical roles", "Acclimatisation of one individual", "Asexual reproduction by every caste"), 0, "Social insects show specialised roles among castes. This division of labour can improve colony maintenance, defence and reproduction.", mapOf(1 to "The castes have different roles.", 2 to "Caste organisation is not the same as an individual's short-term adjustment.", 3 to "Not every caste reproduces; workers and soldiers usually have different functions."), "Identify how different groups of colony members contribute different tasks.", "A worker honeybee gathers nectar and feeds larvae. Which broad caste role is this?", listOf("Worker", "Drone", "Queen", "Soldier"), 0, "Worker bees perform foraging and brood-care tasks that support the colony."),
                QuestionPair("A duck has webbed feet, a polar fox has dense insulating fur and a male bird performs a courtship display. Which sequence gives their main functions?", listOf("Swimming/locomotion; reducing heat loss; mate attraction", "Mate attraction; photosynthesis; water storage", "Defence; salt excretion; migration", "Respiration; camouflage; hibernation"), 0, "Webbed feet increase the effective surface pushing against water; dense fur traps insulating air; courtship displays can attract mates.", mapOf(1 to "The sequence does not match the structures and behaviour.", 2 to "These are not the primary functions of the three examples.", 3 to "The listed processes do not describe the examples."), "Match each feature to the environmental or reproductive challenge it addresses.", "A desert animal is active at night and shelters during the hottest hours. Which challenge is it mainly reducing?", listOf("Heat exposure and water loss", "Lack of feathers", "Low oxygen in tree roots", "Competition for pollinators"), 0, "Nocturnality and sheltering can reduce heat exposure and help conserve water in hot environments.")
            )
        }
        return banks[i]
    }

    private fun classificationQuestions(i: Int, stage: LessonStage): QuestionPair {
        val banks = when (stage) {
            LessonStage.CONCEPT -> listOf(
                QuestionPair("A learner uses a leaf key to decide that a specimen is a hibiscus. Which activity is this?", listOf("Identification", "Nomenclature", "Evolution", "Nutrition"), 0, "Identification is deciding which organism has been observed by using diagnostic features or a key.", mapOf(1 to "Nomenclature is the formal naming of an organism.", 2 to "Evolution concerns inherited change in populations over generations.", 3 to "Nutrition is obtaining and using food."), "A key helps you decide what the unknown specimen is.", "A student compares an unknown insect with a dichotomous key and reaches the name housefly. What has the student done?", listOf("Identified the specimen", "Named a new kingdom", "Observed natural selection", "Measured its population density"), 0, "Using diagnostic alternatives in a key to determine an organism's identity is identification."),
                QuestionPair("Which sequence lists the main taxonomic ranks from broadest to most specific?", listOf("Species, genus, family, order, class, phylum, kingdom", "Kingdom, phylum, class, order, family, genus, species", "Kingdom, class, phylum, family, order, species, genus", "Genus, species, kingdom, family, class, order, phylum"), 1, "The familiar sequence is Kingdom, Phylum, Class, Order, Family, Genus and Species. In plant classification, Division is often used instead of Phylum.", mapOf(0 to "This reverses the hierarchy.", 2 to "Phylum comes before class; genus comes before species.", 3 to "Genus and species are the most specific ranks, not the broadest."), "Move from the largest inclusive group down to the most specific group.", "Two organisms belong to the same genus but different species. Which statement is generally expected?", listOf("They share a more recent and specific grouping than organisms in different genera", "They must be identical individuals", "They belong to different kingdoms", "They cannot share any characteristics"), 0, "Sharing a genus indicates a closer taxonomic grouping than merely sharing a kingdom, though it does not mean the organisms are identical."),
                QuestionPair("Which is the correctly written scientific name for humans?", listOf("homo Sapiens", "Homo sapiens", "HOMO SAPIENS", "Homo Sapiens"), 1, "In binomial nomenclature, the genus begins with a capital letter and the specific epithet with a lower-case letter; both are italicised when typed.", mapOf(0 to "The genus must begin with a capital and the epithet with lower-case.", 2 to "Scientific names are not written entirely in capitals.", 3 to "The specific epithet begins with a lower-case letter."), "Check the first letter of each word: genus capital, specific epithet lower-case.", "Which typed form follows binomial nomenclature correctly for the maize species name?", listOf("zea Mays", "Zea mays", "Zea Mays", "ZEA MAYS"), 1, "The genus Zea begins with a capital letter and the specific epithet mays begins with a lower-case letter; both are italicised in formal typed text."),
                QuestionPair("Which feature is characteristic of fungi?", listOf("Chlorophyll-containing cells that make food by photosynthesis", "Absorptive nutrition and commonly chitin-containing cell walls", "Cells without DNA or ribosomes", "All members are motile animals"), 1, "Fungi are eukaryotes that obtain nutrients by absorption; their cell walls commonly contain chitin.", mapOf(0 to "This describes many green plants, not fungi.", 2 to "Fungal cells contain DNA and ribosomes.", 3 to "Fungi are not animals and most are not motile."), "Think about how fungi obtain nutrients and what their cell walls contain.", "A mushroom grows on dead wood and releases digestive enzymes before absorbing soluble products. Which kingdom does it belong to?", listOf("Fungi", "Plantae", "Animalia", "Monera"), 0, "Absorptive heterotrophic nutrition and fungal hyphae identify the mushroom as a fungus."),
                QuestionPair("Which plant group has vascular tissue but reproduces by spores rather than seeds?", listOf("Mosses", "Ferns", "Conifers", "Flowering plants"), 1, "Ferns possess vascular tissues but do not produce seeds; they reproduce by spores.", mapOf(0 to "Mosses are non-vascular plants.", 2 to "Conifers produce seeds, commonly exposed on cone scales.", 3 to "Flowering plants produce seeds enclosed within fruits."), "Separate the presence of vascular tissue from the presence of seeds.", "A plant has true roots, stems and leaves with vascular tissue, but no flowers or seeds. Which group best fits?", listOf("Fern", "Moss", "Flowering plant", "Conifer"), 0, "Ferns are vascular plants that reproduce by spores and do not form seeds."),
                QuestionPair("Which statement correctly distinguishes vertebrates from invertebrates?", listOf("Vertebrates possess a vertebral column; invertebrates do not", "Vertebrates are always larger", "Invertebrates all live in water", "Only vertebrates have muscles"), 0, "The defining school-level distinction is the presence of a vertebral column in vertebrates.", mapOf(1 to "Body size does not define vertebrate status.", 2 to "Invertebrates occupy aquatic and terrestrial habitats.", 3 to "Many invertebrates have muscles."), "Look for the presence or absence of a backbone, not size or habitat.", "An earthworm has no vertebral column. To which broad group does it belong?", listOf("Invertebrates", "Vertebrates", "Mammals", "Birds"), 0, "Earthworms are invertebrates because they lack a vertebral column.")
            )
            LessonStage.EXAMPLES -> listOf(
                QuestionPair("A student sorts specimens into groups, decides the identity of an unknown leaf using a key, then writes its scientific name. Which order matches the three tasks?", listOf("Classification → identification → nomenclature", "Identification → nutrition → evolution", "Nomenclature → classification → respiration", "Evolution → classification → excretion"), 0, "Grouping by shared features is classification; using a key to determine identity is identification; applying the formal scientific name is nomenclature.", mapOf(1 to "Nutrition and evolution do not describe these three taxonomic tasks.", 2 to "The order of the tasks is reversed and respiration is unrelated.", 3 to "Evolution and excretion are not the naming/identification sequence."), "Match each action to grouping, deciding identity, or applying a formal name.", "A botanist places a specimen in the family Poaceae, uses a key to identify it as maize, and records Zea mays. Which action is nomenclature?", listOf("Recording Zea mays", "Using the key", "Placing it in a family", "Measuring leaf length"), 0, "Writing the accepted two-part scientific name is nomenclature."),
                QuestionPair("A specimen is in Class Mammalia, Order Primates and Family Hominidae. Which rank is more specific than Order?", listOf("Class", "Family", "Phylum", "Kingdom"), 1, "Family is below Order and therefore more specific in the taxonomic hierarchy.", mapOf(0 to "Class is broader than order.", 2 to "Phylum is broader than class and order.", 3 to "Kingdom is the broadest of these ranks."), "Ranks become more specific as you move down: order, then family.", "Which rank immediately follows Family as organisms are grouped more specifically?", listOf("Genus", "Order", "Class", "Phylum"), 0, "Genus is below family and above species."),
                QuestionPair("A learner writes the maize name as Zea Mays. What correction is required?", listOf("Change the specific epithet to lower-case: Zea mays", "Write both words in capitals", "Reverse the words", "Remove the genus name"), 0, "The specific epithet begins with a lower-case letter. The genus begins with a capital letter.", mapOf(1 to "Scientific names are not written entirely in capitals.", 2 to "The genus is written first.", 3 to "A binomial name contains both genus and specific epithet."), "Only the genus word begins with a capital letter.", "A student types Homo sapiens correctly but forgets italics. What is the standard formatting correction?", listOf("Italicise both words", "Italicise only sapiens", "Capitalise both words", "Put the genus in brackets"), 0, "Both components of a scientific name are italicised when typed; handwritten names are conventionally underlined separately."),
                QuestionPair("An organism is multicellular, has cellulose cell walls and makes organic food by photosynthesis. Which kingdom is the best fit in the traditional five-kingdom scheme?", listOf("Plantae", "Fungi", "Animalia", "Monera"), 0, "The combination of multicellularity, cellulose walls and photosynthetic nutrition is characteristic of Plantae in the traditional scheme.", mapOf(1 to "Fungi have absorptive nutrition and commonly chitin walls.", 2 to "Animal cells lack cell walls and animals are heterotrophic.", 3 to "Monera are prokaryotic."), "Use several characteristics together: cell type, wall composition and nutrition.", "A multicellular organism has chitin-containing walls and absorbs dissolved nutrients. Which kingdom is most likely?", listOf("Fungi", "Plantae", "Animalia", "Protista"), 0, "Chitin-containing walls and absorptive heterotrophic nutrition point to Fungi."),
                QuestionPair("A small green plant has no vascular tissue and reproduces by spores. Which group is most likely?", listOf("Mosses", "Ferns", "Conifers", "Flowering plants"), 0, "Mosses are non-vascular plants that reproduce by spores.", mapOf(1 to "Ferns have vascular tissue.", 2 to "Conifers are seed plants.", 3 to "Flowering plants produce flowers and seeds enclosed in fruits."), "First ask whether vascular tissue is present; then consider seeds and flowers.", "A plant has vascular tissue and produces seeds in cones, not flowers. Which group is it?", listOf("Conifers", "Mosses", "Ferns", "Flowering plants"), 0, "Conifers are gymnosperms that produce seeds, commonly on cone scales."),
                QuestionPair("A specimen has feathers, a beak and forelimbs modified as wings. Which vertebrate class is supported by these features?", listOf("Birds", "Fish", "Amphibians", "Mammals"), 0, "Feathers are a defining feature of birds; wings alone would not be enough because bats also have wings.", mapOf(1 to "Fish have fins and gills, not feathers.", 2 to "Amphibians do not have feathers.", 3 to "Mammals have hair and mammary glands; bats have wings but no feathers."), "Use the diagnostic feature that is distinctive, not just the presence of wings.", "A mammal has forelimbs modified into wings but its body is covered with hair and it feeds its young with milk. Which animal is it?", listOf("Bat", "Pigeon", "Lizard", "Tilapia"), 0, "A bat is a mammal; wings do not make an animal a bird.")
            )
            else -> listOf(
                QuestionPair("Why is a scientific classification system revised when molecular evidence changes?", listOf("Classification is a scientific model that is refined as evidence improves", "All organisms change kingdoms every year", "Local names become illegal", "New evidence has no effect on relationships"), 0, "Classification reflects hypotheses about shared characteristics and evolutionary relationships; new evidence can require revisions.", mapOf(1 to "Taxonomic changes are evidence-based, not annual arbitrary changes.", 2 to "Local names remain in use but are less standardised.", 3 to "New molecular evidence can change how relationships are understood."), "Classification is evidence-based, not a fixed list that can never change.", "Two organisms look similar but molecular evidence shows they are distantly related. What should taxonomists do?", listOf("Reassess their classification using the full evidence", "Keep them together only because they look alike", "Ignore molecular evidence", "Classify them by body size alone"), 0, "Classification should integrate reliable evidence, including molecular data, rather than rely on superficial resemblance."),
                QuestionPair("An organism is described as genus Panthera and species leo. Which taxonomic rank is more specific?", listOf("Species", "Genus", "Family", "Order"), 0, "Species is the most specific of these ranks; Panthera leo is the binomial name of the lion.", mapOf(1 to "Genus contains one or more species.", 2 to "Family is broader than genus and species.", 3 to "Order is broader still."), "Move down the hierarchy to the most specific rank.", "A lion and a tiger belong to genus Panthera but are different species. Which rank do they share?", listOf("Genus", "Species", "Family only", "Order only"), 0, "Lions and tigers share the genus Panthera but are different species."),
                QuestionPair("A handwritten biology answer includes the name Homo sapiens. How should the two words be marked under the usual convention?", listOf("Underline each word separately", "Underline only sapiens", "Circle the genus only", "Write both words in capitals"), 0, "When handwritten, each word in a scientific name is conventionally underlined separately to represent italics.", mapOf(1 to "Both genus and specific epithet are underlined separately.", 2 to "The complete binomial name is marked.", 3 to "Capitalisation follows genus upper-case and specific epithet lower-case."), "Handwriting uses separate underlining as the equivalent of italics.", "A printed answer shows _Homo sapiens_ in italics. Is the format correct?", listOf("Yes, both words are italicised", "No, only the genus should be italicised", "No, both words should be capitals", "No, the words should be reversed"), 0, "Both parts of a binomial scientific name are italicised in print."),
                QuestionPair("A student calls a virus a bacterium because both can cause disease. Which correction is scientifically sound?", listOf("A virus is acellular and lacks its own ribosomes; bacteria are cellular prokaryotes", "Viruses are fungi", "Bacteria have no genetic material", "All viruses are single-celled organisms"), 0, "Viruses are acellular infectious agents and rely on host cells for replication. Bacteria are living cellular prokaryotes with ribosomes.", mapOf(1 to "Viruses are not fungi or members of the five kingdoms.", 2 to "Bacteria possess DNA.", 3 to "Viruses are not cells."), "Compare cellular organisation, not whether both cause disease.", "Which feature prevents a virus from being classified as a bacterium?", listOf("It is acellular and replicates only inside host cells", "It contains genetic material", "It can cause disease", "It is too small to see unaided"), 0, "Viruses lack cellular organisation and cannot reproduce independently, unlike bacteria."),
                QuestionPair("A botanist compares moss, fern, conifer and flowering plant. Which sequence correctly shows increasing complexity of transport and seed/flower features?", listOf("Moss → fern → conifer → flowering plant", "Fern → moss → flowering plant → conifer", "Conifer → moss → fern → flowering plant", "Flowering plant → conifer → fern → moss"), 0, "Mosses lack vascular tissue; ferns have vascular tissue but no seeds; conifers have seeds but no flowers; flowering plants produce flowers and fruits enclosing seeds. This is a teaching comparison, not a claim that evolution is a ladder of progress.", mapOf(1 to "Ferns have vascular tissue whereas mosses do not; conifers do not precede flowering plants in this feature sequence.", 2 to "This reverses the main diagnostic features.", 3 to "This reverses the broad comparison."), "Compare vascular tissue, seeds and flowers separately; do not assume that one living group evolved directly into another.", "A plant produces flowers and seeds enclosed in fruits. Which broad group is it?", listOf("Angiosperms", "Bryophytes", "Pteridophytes", "Gymnosperms"), 0, "Angiosperms are flowering plants whose seeds develop within fruits."),
                QuestionPair("A bat flies, but its body has hair and females produce milk. How should it be classified?", listOf("Mammal", "Bird", "Insect", "Reptile"), 0, "Mammary glands and hair identify mammals. Flight is a functionally similar feature that evolved in bats and birds but does not make them the same class.", mapOf(1 to "Birds have feathers, not hair or mammary glands.", 2 to "Insects have an exoskeleton and jointed legs.", 3 to "Reptiles do not have mammary glands."), "Use diagnostic body features rather than a single behaviour such as flying.", "A whale lives in water but breathes with lungs and feeds its young with milk. Which class does it belong to?", listOf("Mammalia", "Fish", "Amphibia", "Reptilia"), 0, "Whales are mammals; habitat does not determine vertebrate class.")
            )
        }
        return banks[i]
    }

    internal fun hasCompleteEvidence(
        topicId: String,
        stage: LessonStage,
        evidence: Map<String, MicroLessonCheckEvidence>
    ): Boolean {
        val required = checks(topicId, stage)
        return required.isEmpty() || required.all { evidence[it.id]?.complete == true }
    }

    internal fun hasCompleteEvidence(topicId: String, stage: LessonStage, evidenceJson: String?): Boolean {
        val required = checks(topicId, stage)
        if (required.isEmpty()) return true
        return runCatching {
            val root = JSONObject(evidenceJson ?: "{}")
            val saved = JSONObject(root.optString(stage.name, "{}"))
            val evidence = required.associate { check ->
                val item = saved.optJSONObject(check.id) ?: JSONObject()
                check.id to MicroLessonCheckEvidence(
                    firstPassed = item.optBoolean("firstPassed"),
                    transferPassed = item.optBoolean("transferPassed")
                )
            }
            hasCompleteEvidence(topicId, stage, evidence)
        }.getOrDefault(false)
    }

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
            Text("${checks.size} syllabus-linked ideas • each check includes a fresh transfer question. One correct guess is not enough to verify understanding.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
                if (allPassed) "All ${checks.size} ideas have passed the initial and transfer checks. This stage can now be verified."
                else "Stage verification: ${evidence.values.count { it.complete }} of ${checks.size} ideas demonstrated.",
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
