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
import android.speech.tts.TextToSpeech
import java.util.Locale

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
    val explanation: String,
    val isSyllabusOutline: Boolean = false
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
            "Which structure is the main site of aerobic respiration in most eukaryotic cells?",listOf("Mitochondrion", "Ribosome", "Cell wall", "Golgi apparatus"),0,
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
            "Which statement best distinguishes a prokaryotic cell from a eukaryotic cell?",listOf("A prokaryotic cell has no DNA", "A prokaryotic cell has no membrane-bound nucleus", "A prokaryotic cell always has a cellulose cell wall", "A prokaryotic cell is always multicellular"),1,
            "Prokaryotic cells contain DNA and ribosomes but lack a membrane-bound nucleus. Their DNA is located in a nucleoid region."
        ),
        LearningTopic(
            "bio-adaptation", "Adaptation for survival", "BIO",
            "A fish, a cactus, a mangrove tree and a desert fox face very different challenges. Each has inherited features that can improve survival and reproduction in its environment. These features are called adaptations. The key idea is that adaptation is about how a characteristic works in a particular environment, not simply about an organism looking unusual.",
            listOf(
                "Define adaptation in biological terms",
                "Distinguish structural, physiological and behavioural adaptations",
                "Relate adaptations to feeding, movement, protection and water balance",
                "Explain examples from aquatic, terrestrial and Nigerian habitats",
                "Distinguish an inherited adaptation from an individual response or temporary adjustment"
            ),
            listOf(
                "What is an adaptation?" to "An adaptation is an inherited characteristic that, in a particular environment, can improve an organism's chances of surviving and reproducing. Adaptations occur in populations over generations through evolutionary processes such as natural selection. An individual animal does not grow a new inherited feature simply because it needs one. A feature may be useful in one environment but less useful in another.",
                "Structural adaptations: body form and parts" to "Structural adaptations are physical features of an organism. A fish has a streamlined body that reduces resistance as it moves through water, and gills that provide a large, moist surface for gas exchange. A duck's webbed feet help it push against water. A cactus has a thick water-storing stem and a reduced leaf surface in the form of spines, which helps reduce water loss. Mangrove roots may include pneumatophores that project above waterlogged soil and assist gas exchange.",
                "Physiological adaptations: internal processes" to "Physiological adaptations are inherited ways the body functions. Some desert mammals produce very concentrated urine and dry faeces, conserving water. Some plants use photosynthetic pathways that reduce water loss under hot, dry conditions. Salt-tolerant mangroves use mechanisms that limit salt entry, store salt or remove excess salt. The exact mechanism differs among species, so do not assume every mangrove uses the same strategy.",
                "Behavioural adaptations: what organisms do" to "Behavioural adaptations are patterns of activity that can improve survival or reproduction. Nocturnal animals are active mainly at night, which can reduce exposure to daytime heat. Some animals shelter during the hottest part of the day. Seasonal migration can help animals reach food, breeding sites or favourable climates. Social insects such as termites and honeybees show coordinated behaviours and division of labour that support colony survival.",
                "Protection, feeding and reproduction" to "Camouflage makes an organism less conspicuous against its background; it may help prey avoid predators or help a predator approach prey. Warning colouration can advertise that an organism is toxic, distasteful or otherwise dangerous. Mimicry occurs when one organism resembles another organism or object in a way that affects interactions. Beak shapes, teeth and feeding structures are often related to diet. Flowers, fruits, pollination structures and courtship behaviours can improve reproductive success.",
                "Adaptations depend on the environment" to "A thick coat may help an animal conserve heat in a cold environment but create a heat burden in a hot one. Broad floating leaves may be useful for a water plant but unsuitable for a dry terrestrial habitat. In Nigerian habitats, mangrove plants face waterlogged, saline coastal conditions; rainforest organisms experience high moisture and dense vegetation; savanna organisms experience seasonal rainfall and open grassland. Link every example to the challenge it helps address.",
                "Adaptation is not the same as acclimatisation" to "An adaptation is an inherited population characteristic shaped over generations. Acclimatisation is a change within an individual's lifetime, such as a temporary physiological response to a change in temperature or altitude. A person sweating more in hot weather is acclimatising; it is not a new inherited adaptation. Natural selection acts on heritable variation across generations, not on a conscious effort by an organism to change.",
                "Exam reasoning: feature, challenge and advantage" to "For an adaptation question, use a three-step explanation: name the feature, identify the environmental challenge, and explain how the feature helps. Example: a fish's streamlined body reduces water resistance, allowing efficient movement through water. Avoid vague answers such as 'it helps the fish survive' without explaining the mechanism. A good answer links structure or behaviour to a specific function."
            ),
            "Which option is a behavioural adaptation that may help an animal avoid extreme daytime heat?",listOf("Having a streamlined body", "Having a thick layer of fat", "Being active mainly at night", "Producing concentrated urine"),2,
            "Nocturnal activity is a behavioural pattern. It allows an animal to avoid the hottest part of the day. A streamlined body and fat layer are structural features, while concentrated urine is a physiological function."
        ),
        LearningTopic(
            "bio-plant-anatomy", "Plant and mammalian structure", "BIO",
            "A plant leaf, a tree trunk, a human heart and a human lung may look unrelated, but each is built from cells organised into tissues and organs. In this lesson, you will learn how the structure of plant organs supports transport and photosynthesis, then connect mammalian organs to the systems they form.",
            listOf(
                "Identify the main tissue systems in flowering plants",
                "Describe the functions of root, stem and leaf tissues",
                "Distinguish xylem from phloem",
                "Relate basic mammalian organ structure to function",
                "Explain how cells, tissues, organs and organ systems are organised"
            ),
            listOf(
                "Plant tissues: the building blocks" to "A tissue is a group of cells working together. In flowering plants, the epidermal or dermal tissue system forms the outer covering; ground tissues perform functions such as storage, photosynthesis and support; vascular tissues transport materials. Meristematic tissues contain actively dividing cells and allow growth. Mature tissues may be simple, with one main cell type, or complex, with several cell types working together.",
                "The root: anchorage and absorption" to "Roots anchor a plant and absorb water and mineral ions. A root tip contains a protective root cap and regions of cell division and elongation. In the zone of maturation, root hairs are extensions of epidermal cells that increase surface area for absorption. The cortex may store food and conduct water inward. The central vascular cylinder contains xylem and phloem. Do not confuse a root hair with a separate multicellular root: it is an extension of one epidermal cell.",
                "The stem: support and transport" to "A stem supports leaves, flowers and fruits and connects roots with aerial organs. Its vascular bundles contain xylem and phloem. Xylem vessels and tracheids conduct water and dissolved mineral ions, mainly from roots towards leaves, and also contribute mechanical support through lignified walls. Phloem transports organic substances, especially sucrose, from sources such as photosynthesising leaves to sinks such as growing roots, fruits and storage organs. Phloem translocation can occur in different directions in different sieve tubes according to source–sink relationships.",
                "The leaf: a photosynthetic organ" to "A typical broad leaf has an epidermis with a waxy cuticle that reduces water loss. The palisade mesophyll usually contains many chloroplasts and is a major photosynthetic tissue. Spongy mesophyll has air spaces that aid gas diffusion. Stomata are pores, commonly in the lower epidermis of many terrestrial leaves, controlled by guard cells. Carbon dioxide diffuses in for photosynthesis; oxygen and water vapour can diffuse out. Leaf structure varies among species and habitats.",
                "Xylem and phloem compared" to "Xylem conducts water and mineral ions and is usually made mainly of dead, hollow conducting elements at maturity; lignified walls help resist collapse. Phloem transports dissolved organic substances and contains living sieve tube elements supported by companion cells in flowering plants. A useful memory aid is: xylem moves water and minerals; phloem moves food materials. Do not say that phloem transports only downwards or that xylem transports manufactured food.",
                "Mammalian organisation and organ systems" to "In mammals, specialised cells form tissues, tissues form organs, and organs cooperate in systems. Epithelial tissue covers surfaces and lines cavities; muscle tissue contracts; nervous tissue carries signals; connective tissues support, bind or transport. The heart is an organ made of several tissues and pumps blood in the circulatory system. The lungs contain branching airways and alveoli that provide a large, moist surface for gas exchange. The small intestine has folds, villi and microvilli that increase surface area for absorption.",
                "Relating structure to function" to "A good biological explanation connects a visible feature to what it allows the organ to do. Thin alveolar walls shorten the diffusion distance for gases; a dense capillary network maintains concentration gradients; villi provide a large surface area and a blood supply for absorbed nutrients; cardiac muscle contracts rhythmically to pump blood. In plants, root hairs increase absorptive surface area, while lignified xylem walls support water transport under tension.",
                "How to study a transverse section" to "A transverse section is a cut across an organ, at right angles to its length. First identify the outer boundary, then locate major tissue regions and vascular structures. Use the specimen and orientation given; the arrangement of vascular bundles differs among roots, stems and plant groups. Label only structures you can identify. A biological drawing should have clear single lines, accurate proportions, no unnecessary shading and straight label lines that do not cross."
            ),
            "Which plant tissue transports water and dissolved mineral ions mainly from the roots towards the leaves?",listOf("Phloem", "Epidermis", "Palisade mesophyll", "Xylem"),3,
            "Xylem conducts water and dissolved mineral ions from roots towards aerial parts of the plant. Phloem transports organic substances such as sucrose between sources and sinks."
        ),
        LearningTopic(
            "bio-nutrition", "Nutrition in plants and animals", "BIO",
            "Every living organism needs materials and energy to stay alive, grow and reproduce. Plants can manufacture organic food from simple inorganic substances using light energy. Animals obtain organic nutrients by feeding on other organisms. This lesson follows food from its source to digestion, absorption and use by the body.",
            listOf(
                "Distinguish autotrophic and heterotrophic nutrition",
                "Explain photosynthesis and identify factors that affect its rate",
                "State the roles of selected mineral elements in plants",
                "Identify major classes of food and their functions",
                "Describe the mammalian alimentary canal and the role of digestive enzymes",
                "Explain absorption and assimilation of digested food"
            ),
            listOf(
                "Two broad modes of nutrition" to "Nutrition is the process by which organisms obtain and use materials for energy, growth, repair and regulation. Autotrophs manufacture organic substances from inorganic materials, using light or chemical energy. Green plants and algae are photoautotrophs. Heterotrophs obtain organic nutrients from other organisms or their products. Animals, fungi and many microorganisms are heterotrophs. Do not confuse nutrition with digestion: digestion is only one part of nutrition in animals.",
                "Photosynthesis: storing light energy in food" to "Photosynthesis is the process by which green plants and some other organisms use light energy to synthesise organic substances from carbon dioxide and water, releasing oxygen in oxygenic photosynthesis. A simplified balanced equation is: 6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂, in the presence of light and chlorophyll. The equation summarises the overall process; the immediate products and pathways are more complex. Chlorophyll absorbs light energy, and chloroplasts contain the machinery for photosynthesis.",
                "Factors that affect photosynthesis" to "The rate of photosynthesis is affected by light intensity, carbon dioxide concentration, temperature, water availability and chlorophyll content. If one factor is in short supply relative to the others, it may limit the rate. Increasing light intensity does not increase the rate indefinitely: another factor may become limiting, and very high light or unsuitable temperatures can cause damage or reduce efficiency. Temperature affects enzyme-controlled reactions, so the rate rises towards an optimum and falls when enzymes lose function at high temperatures.",
                "Mineral nutrition in plants" to "Plants absorb mineral ions from soil water through their roots. Nitrate ions are needed to make amino acids and proteins; magnesium is part of the chlorophyll molecule; phosphate is needed for ATP, nucleic acids and phospholipids; potassium supports enzyme activity and osmotic regulation. Deficiency symptoms can include poor growth or leaf discolouration, but symptoms overlap, so diagnosis should consider more than one sign and the growing conditions.",
                "Food classes and their functions" to "Carbohydrates are a major energy source; proteins provide amino acids for growth, repair, enzymes and other molecules; lipids store energy, form cell membranes and help insulation; vitamins and minerals support normal body processes; water is a solvent and transport medium and participates in reactions; fibre supports normal bowel function. A balanced diet provides appropriate amounts of these nutrients for a person's needs. Energy requirements vary with age, activity, health and other factors.",
                "The mammalian alimentary canal" to "Food passes through the mouth, oesophagus, stomach, small intestine and large intestine before waste leaves through the rectum and anus. The liver produces bile, which is stored in the gall bladder in humans; bile helps emulsify fats and neutralises acidic chyme but is not an enzyme. The pancreas releases digestive enzymes and bicarbonate into the small intestine. The alimentary canal is a continuous tube; accessory organs add secretions but food does not pass through them.",
                "Digestion: breaking large molecules into absorbable units" to "Mechanical digestion breaks food into smaller pieces, increasing surface area. Chemical digestion uses enzymes to hydrolyse large molecules. Amylase breaks starch into smaller carbohydrates; proteases break proteins into peptides and amino acids; lipases break triglycerides into fatty acids and monoglycerides. Enzymes are specific and work best under suitable temperature and pH conditions. Bile is not a digestive enzyme: it emulsifies fat into smaller droplets, increasing the surface area available to lipase.",
                "Absorption and assimilation" to "Most absorption occurs in the small intestine. Its folds, villi and microvilli provide a large surface area. Glucose and amino acids enter blood capillaries in the villi; many products of fat digestion enter intestinal epithelial cells and are packaged into particles that pass mainly into lacteals of the lymphatic system. Assimilation is the use or incorporation of absorbed nutrients by cells and tissues, for example using amino acids to build proteins or glucose in respiration. The large intestine absorbs water and electrolytes and helps form faeces."
            ),
            "Which statement about bile is correct?",listOf("It emulsifies fats but is not a digestive enzyme", "It digests proteins into amino acids", "It converts glucose into starch", "It is produced by the pancreas"),0,
            "Bile is produced by the liver. It emulsifies fat into smaller droplets and helps neutralise acidic chyme; it is not an enzyme."
        ),
        LearningTopic(
            "bio-transport", "Transport in plants and animals", "BIO",
            "A cell needs water, oxygen, nutrients and other materials, and it must remove wastes. A tiny organism may exchange substances directly with its surroundings. A large plant or mammal needs specialised transport tissues and systems. This lesson begins with movement across membranes and then follows transport through plants and mammals.",
            listOf(
                "Distinguish diffusion, osmosis and active transport",
                "Explain how concentration gradients affect passive movement",
                "Describe water movement through roots and xylem",
                "Explain translocation in phloem",
                "Identify the main components and functions of blood",
                "Compare arteries, veins and capillaries",
                "Trace pulmonary and systemic circulation"
            ),
            listOf(
                "Why transport is necessary" to "In a large multicellular organism, many cells are far from the external environment. Diffusion over long distances is too slow to meet all their needs. Specialised transport systems move materials around the organism, while exchange surfaces allow substances to enter or leave. In plants, xylem and phloem connect different organs; in mammals, the circulatory system transports materials in blood.",
                "Diffusion: movement down a concentration gradient" to "Diffusion is the net movement of particles from a region of higher concentration to a region of lower concentration as a result of their random motion. It is passive and does not require the cell to supply metabolic energy. The rate is affected by the steepness of the concentration gradient, temperature, surface area and diffusion distance. Oxygen can diffuse from alveolar air into blood when its partial pressure is higher in the alveoli than in the blood.",
                "Osmosis: movement of water across a selectively permeable membrane" to "Osmosis is the net movement of water molecules across a selectively permeable membrane from a region of higher water potential to a region of lower water potential. In a simple school-level model, water moves from a more dilute solution towards a more concentrated solution when the membrane allows water through but restricts the solute. Plant cells may become turgid in a dilute solution or plasmolysed in a sufficiently concentrated solution. Animal cells may swell or shrink because they lack a cell wall. The precise water-potential relationship depends on solutes, pressure and the membrane.",
                "Active transport: movement using energy" to "Active transport moves substances across membranes against their concentration or electrochemical gradient using energy, commonly from ATP, and specific transport proteins. Root hair cells can take up mineral ions from dilute soil water even when the concentration inside the cell is higher. Active transport is different from diffusion: diffusion is passive and moves down a gradient; active transport requires energy to move against a gradient.",
                "Water uptake and movement in plants" to "Water enters root hair cells by osmosis when the water potential gradient favours entry. It moves across root tissues towards the xylem through pathways that include cell walls and cytoplasm. Xylem carries water and dissolved mineral ions towards leaves. Evaporation of water from leaf surfaces and diffusion of water vapour through stomata produce transpiration. Cohesion between water molecules and adhesion to xylem walls help maintain a continuous water column pulled upwards by transpiration. This is often called the cohesion–tension mechanism.",
                "Translocation in phloem" to "Translocation is the movement of organic solutes, especially sucrose, through phloem from sources to sinks. A source releases or supplies assimilates, for example a photosynthesising leaf. A sink uses or stores assimilates, for example a growing root, fruit or developing seed. The direction depends on the locations of sources and sinks; different sieve tubes can carry materials in different directions. Do not confuse translocation with the upward movement of water in xylem.",
                "Blood: a transport tissue" to "Blood consists of plasma and formed elements. Plasma carries dissolved nutrients, hormones, carbon dioxide in several forms, urea and other substances. Red blood cells contain haemoglobin, which transports most oxygen. White blood cells contribute to defence against pathogens. Platelets are cell fragments that help blood clot. Blood also distributes heat and helps maintain a stable internal environment.",
                "The heart and blood vessels" to "The human heart has four chambers: right and left atria and right and left ventricles. The right side sends deoxygenated blood to the lungs through the pulmonary artery; oxygenated blood returns to the left atrium through pulmonary veins. The left ventricle pumps blood through the aorta to the body. Arteries carry blood away from the heart and have thick, elastic walls; veins return blood to the heart and often contain valves; capillaries have very thin walls that allow exchange with tissues. Vessel type is defined by direction of flow, not by oxygen content.",
                "Two circuits and the lymphatic system" to "Pulmonary circulation carries blood between the heart and lungs. Systemic circulation carries blood between the heart and the rest of the body. In humans, the two circuits form a double circulatory system. Some fluid leaves blood capillaries and bathes tissues; much returns to capillaries, while excess tissue fluid enters lymphatic vessels as lymph and is eventually returned to the blood. Lymphatic vessels also participate in immune defence and transport absorbed fats from the small intestine."
            ),
            "Which process moves mineral ions into root hair cells against a concentration gradient using energy?",listOf("Diffusion", "Active transport", "Osmosis", "Transpiration"),1,
            "Active transport uses energy and membrane transport proteins to move substances against a concentration or electrochemical gradient."
        ),
        LearningTopic(
            "bio-respiration", "Respiration and gaseous exchange", "BIO",
            "Your muscles need energy when you climb stairs, and a germinating seed needs energy even though it cannot walk or run. Cells obtain usable energy by breaking down organic molecules through cellular respiration. The lungs and other exchange surfaces bring gases close enough for diffusion, but breathing and respiration are not the same process.",
            listOf(
                "Distinguish breathing, gaseous exchange and cellular respiration",
                "Explain the purpose of ATP in cells",
                "Compare aerobic and anaerobic respiration",
                "Write the simplified equation for aerobic respiration",
                "Describe the human respiratory system and alveolar gas exchange",
                "Explain how ventilation moves air into and out of the lungs"
            ),
            listOf(
                "Three processes that must not be confused" to "Breathing or ventilation is the movement of air into and out of the lungs. Gaseous exchange is diffusion of oxygen and carbon dioxide across an exchange surface. Cellular respiration is a series of chemical reactions in cells that releases energy from organic molecules and transfers some of it to ATP. A person can breathe faster during exercise, but breathing itself is not the chemical process that releases energy from glucose.",
                "ATP: a usable energy carrier" to "Cells transfer energy from respiration to adenosine triphosphate (ATP). ATP can be hydrolysed to ADP and inorganic phosphate, releasing energy that can be coupled to processes such as muscle contraction, active transport and biosynthesis. ATP is continually made and used; it is not a long-term energy store like fat or glycogen. Respiration transfers energy rather than creating energy from nothing.",
                "Aerobic respiration" to "Aerobic respiration uses oxygen in the complete oxidation of glucose under suitable conditions. A simplified balanced equation is: C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + energy transferred to ATP and released as heat. Glycolysis occurs in the cytosol; in eukaryotic cells, the link reaction and Krebs cycle occur in the mitochondrial matrix, and the electron transport chain is located on the inner mitochondrial membrane. Do not state that every stage of respiration occurs inside mitochondria.",
                "Anaerobic respiration and fermentation" to "Anaerobic pathways release energy from glucose without using oxygen as the final electron acceptor. In human skeletal muscle under conditions where oxygen delivery cannot meet demand, pyruvate can be reduced to lactate; this allows glycolysis to continue but yields much less ATP per glucose than aerobic respiration. In yeast, alcoholic fermentation produces ethanol and carbon dioxide. Some textbooks use the term anaerobic respiration broadly for these processes, while microbiology distinguishes fermentation from anaerobic respiration that uses an alternative electron acceptor.",
                "Comparing aerobic and anaerobic pathways" to "Aerobic respiration usually yields much more ATP per glucose because glucose is more completely oxidised. Anaerobic pathways provide a rapid but small ATP yield from glycolysis and do not require oxygen. The products depend on the organism and pathway: lactate in human muscle, or ethanol and carbon dioxide in yeast. Do not say that anaerobic respiration produces no energy; it produces less ATP.",
                "The human respiratory system" to "Air passes through the nasal cavity, pharynx, larynx, trachea, bronchi and bronchioles to the alveoli. The trachea and bronchi are supported by cartilage; mucus and cilia help trap and move particles away from the lungs. The alveoli are numerous tiny air sacs surrounded by capillaries. Their thin moist walls, large combined surface area and rich blood supply support rapid diffusion.",
                "Gas exchange at the alveoli" to "Oxygen diffuses from alveolar air into blood because its partial pressure is higher in the alveoli than in incoming deoxygenated blood. Carbon dioxide diffuses in the opposite direction. Ventilation refreshes alveolar air, while blood flow carries gases to and from tissues. Haemoglobin in red blood cells binds most oxygen reversibly. Carbon dioxide is transported mainly as bicarbonate ions in plasma, with smaller amounts dissolved or bound to haemoglobin.",
                "How ventilation works" to "During inspiration, the diaphragm contracts and flattens, and external intercostal muscles lift the ribs up and out. Thoracic volume increases, pressure in the lungs falls below atmospheric pressure and air flows in. During quiet expiration, these muscles relax; elastic recoil reduces thoracic volume, pressure rises and air flows out. Forced expiration uses additional muscles. Air moves because of pressure differences, not because the lungs actively suck it in.",
                "Gas exchange in plants and practical interpretation" to "Plants exchange gases mainly by diffusion through stomata in leaves and lenticels in some stems. Oxygen and carbon dioxide move according to their gradients; the direction can change with photosynthesis and respiration. In a practical investigation, identify the organism and conditions, state which gas or process is being tested, control relevant variables and distinguish an observation from an inference. A colour change in an indicator is an observation; the conclusion about carbon dioxide is an inference."
            ),
            "Which statement correctly describes cellular respiration?",listOf("It is simply the movement of air into the lungs", "It occurs only in the lungs", "It is a set of cellular chemical reactions that transfers energy from food molecules to ATP", "It is the same as photosynthesis"),2,
            "Cellular respiration is a chemical process in cells that releases energy from organic molecules and transfers some of it to ATP. Breathing is ventilation and is a separate process."
        ),
        LearningTopic(
            "bio-excretion", "Excretion and osmoregulation", "BIO",
            "Cells produce waste products as they carry out metabolism. If these substances accumulate, they can damage cells or disturb the internal environment. Organisms therefore remove metabolic wastes and regulate water and ion balance. This lesson separates excretion from egestion and compares the excretory structures of different organisms.",
            listOf(
                "Define excretion and distinguish it from egestion",
                "Identify major metabolic waste products",
                "Compare excretory structures in selected organisms",
                "Describe the main parts of a mammalian kidney and nephron",
                "Explain ultrafiltration and selective reabsorption",
                "Explain the role of the kidney in water and salt balance"
            ),
            listOf(
                "Excretion is not egestion" to "Excretion is the removal of metabolic waste products made by cells, such as carbon dioxide, urea and excess salts. Egestion is the removal of undigested material from the alimentary canal as faeces. Defaecation is not the same as excretion because faeces are not primarily metabolic waste. The lungs excrete carbon dioxide and water vapour; the kidneys excrete urea, excess water and salts; the skin can excrete small amounts of water, salts and urea in sweat.",
                "Nitrogenous waste and water balance" to "When excess amino acids are broken down, their nitrogen-containing groups are removed and nitrogenous waste is produced. Mammals mainly convert toxic ammonia to urea in the liver and excrete urea in urine. Many aquatic animals can excrete ammonia directly because water is abundant. Birds and many reptiles excrete nitrogen mainly as uric acid, which conserves water. The main waste form is related to habitat, water availability and evolutionary history.",
                "Excretory structures in different organisms" to "Amoeba and some other freshwater protists use contractile vacuoles to collect and expel excess water; this is primarily osmoregulation. Flatworms use protonephridia with flame cells to help regulate water and remove wastes. Earthworms use nephridia. Insects such as grasshoppers use Malpighian tubules, which remove nitrogenous wastes from the haemolymph into the gut. Mammals use kidneys. Do not assume all organisms have kidneys or that every excretory organ performs exactly the same functions.",
                "The mammalian kidney and nephron" to "A human kidney has an outer cortex, an inner medulla and a renal pelvis that drains urine into the ureter. The nephron is the functional unit. Blood enters the glomerulus through an afferent arteriole; filtrate passes into Bowman's capsule. The tubule includes the proximal convoluted tubule, loop of Henle, distal convoluted tubule and collecting duct. The nephron and surrounding capillaries selectively return useful substances and water to the blood and adjust the final urine composition.",
                "Ultrafiltration" to "At the glomerulus, high hydrostatic pressure forces water and small dissolved substances from blood into Bowman's capsule. Blood cells and most large plasma proteins remain in the blood. The resulting glomerular filtrate contains water, glucose, amino acids, salts and urea. This pressure-driven filtration is called ultrafiltration; it is not the same as selective reabsorption.",
                "Selective reabsorption and urine formation" to "As filtrate passes along the nephron, useful substances are returned to the blood. Under normal conditions, all filtered glucose and amino acids are reabsorbed, mainly in the proximal convoluted tubule. Much water and many ions are also reabsorbed. The loop of Henle and collecting duct help establish and use concentration gradients that allow the kidney to produce more concentrated or dilute urine. Additional secretion of selected ions and substances into the tubule helps regulate blood composition and pH.",
                "Osmoregulation and hormonal control" to "Osmoregulation is control of water and dissolved-ion balance. When the body needs to conserve water, antidiuretic hormone (ADH) increases the water permeability of parts of the collecting ducts, so more water is reabsorbed and urine becomes more concentrated. When less water needs to be conserved, less ADH is released and a larger volume of dilute urine may be produced. The kidneys also regulate electrolyte balance and contribute to acid–base balance.",
                "Excretion in plants" to "Plants do not have specialised kidneys. Some waste products diffuse out through stomata or lenticels; others are stored in vacuoles, leaves, bark or fruits that may later be shed. Some plants release substances such as resins, gums or latex. Oxygen produced during photosynthesis and carbon dioxide produced during respiration can diffuse out when their concentration gradients favour movement. Plant waste handling varies among species and conditions.",
                "Exam and practical reasoning" to "When asked to identify an excretory structure, connect it to the organism and its function. When asked about urine formation, keep the sequence clear: ultrafiltration forms filtrate; selective reabsorption returns useful substances and water; secretion adds selected substances to the tubule; the remaining fluid becomes urine. For a kidney diagram, label cortex, medulla, pelvis, ureter and blood vessels only when their positions are clear."
            ),
            "Which process forces water and small solutes from glomerular blood into Bowman's capsule?",listOf("Egestion", "Translocation", "Ventilation", "Ultrafiltration"),3,
            "Ultrafiltration is pressure-driven filtration at the glomerulus. Blood cells and most large plasma proteins remain in the blood, while water and small solutes enter Bowman's capsule."
        ),
        LearningTopic(
            "bio-support", "Support and movement", "BIO",
            "A young plant bends towards a window, a grasshopper jumps, and a human arm lifts a bag. These movements depend on structures that support the body and systems that generate force. Plants use cell walls, tissues and growth responses; animals use skeletons, joints and muscles.",
            listOf(
                "Explain how plants support their bodies",
                "Describe tropic responses in plants",
                "Compare hydrostatic skeletons, exoskeletons and endoskeletons",
                "Identify major parts of the mammalian skeleton",
                "Explain the roles of joints, tendons, ligaments and cartilage",
                "Describe how antagonistic muscles produce movement"
            ),
            listOf(
                "Support in plants" to "Plants do not have bones. Their support comes from cell walls, turgor pressure and specialised tissues. Parenchyma cells can provide support when turgid; collenchyma provides flexible support in growing regions; sclerenchyma has thick, often lignified walls and provides strength; xylem also contributes support. When a plant wilts, loss of turgor reduces support and leaves or stems may droop.",
                "Tropisms: directional growth responses" to "A tropism is a directional growth response to a stimulus. Shoots commonly show positive phototropism by growing towards light. Roots commonly show positive gravitropism by growing in the direction of gravity, while shoots commonly show negative gravitropism. Roots often show positive hydrotropism towards moisture. Auxin distribution contributes to differential growth; the response depends on the organ and conditions. A tropism is growth, not simply a rapid movement of the whole plant.",
                "Three broad skeleton types" to "A hydrostatic skeleton uses fluid pressure within a body cavity, with surrounding muscles, to support movement; earthworms are a familiar example. An exoskeleton is a rigid external covering, as in insects and many crustaceans; it protects the body and provides muscle attachment, but must be shed for growth in arthropods. An endoskeleton is an internal framework, as in vertebrates, and grows with the body. Each type has advantages and limitations.",
                "The mammalian skeleton" to "The human skeleton is commonly divided into the axial skeleton and appendicular skeleton. The axial skeleton includes the skull, vertebral column and rib cage; it supports and protects the central body. The appendicular skeleton includes the limb bones and the girdles that attach them. The skeleton supports the body, protects organs, provides attachment for muscles, enables movement and contains marrow involved in blood-cell production.",
                "Joints and connective structures" to "A joint is a place where bones meet. Some joints allow little or no movement; synovial joints allow a wide range of movement. In a synovial joint, cartilage covers the ends of bones, synovial fluid lubricates the joint, and ligaments connect bone to bone and help stabilise it. Tendons connect muscle to bone. Cartilage reduces friction and helps distribute forces; it is not the same as a ligament or tendon.",
                "Muscles work in antagonistic pairs" to "Skeletal muscles pull when they contract; they do not push. At the elbow, the biceps and triceps act as an antagonistic pair. When the biceps contracts and the triceps relaxes, the elbow flexes. When the triceps contracts and the biceps relaxes, the elbow extends. The muscle that produces the main movement is the agonist; the opposing muscle is the antagonist. Muscles attach to bones through tendons.",
                "Movement in animals" to "Movement may involve limbs, body-wall muscles, fins, wings or other specialised structures. Insects use jointed appendages and an exoskeleton; fish use muscles acting on the body and tail with fins for stability and steering; birds use wings and a light but strong skeleton for flight; mammals use coordinated skeletal muscles, joints and sensory feedback. Do not assume every animal moves in the same way.",
                "How to answer support and movement questions" to "Identify the structure, state its role and explain how it contributes to support or movement. For a synovial joint, distinguish cartilage, synovial fluid, ligament and tendon. For a muscle action, state which muscle contracts and which relaxes. For a tropism, identify the stimulus and direction of growth. Precise use of terms often determines whether an answer earns full marks."
            ),
            "At the elbow, which muscle contracts to extend the forearm?",listOf("Triceps", "Biceps", "Deltoid only", "Diaphragm"),0,
            "The triceps contracts to extend the elbow while the biceps relaxes. The two muscles form an antagonistic pair."
        ),
        LearningTopic(
            "bio-reproduction", "Reproduction in plants and animals", "BIO",
            "Living organisms produce offspring, allowing their species to continue. Some organisms reproduce asexually from one parent; others reproduce sexually through the fusion of gametes. Flowering plants and mammals use different structures and processes, but both involve the transfer of genetic information to the next generation.",
            listOf(
                "Distinguish sexual and asexual reproduction",
                "Compare common methods of asexual reproduction",
                "Identify the main parts of a flower and their functions",
                "Explain pollination, fertilisation and seed formation",
                "Describe the main organs of the mammalian reproductive systems",
                "Explain fertilisation, implantation and early development in mammals"
            ),
            listOf(
                "Why organisms reproduce" to "Reproduction is the biological process by which organisms produce offspring. It is not required for an individual organism to stay alive, but it is essential for the continuation of a species. Asexual reproduction usually involves one parent and no fusion of gametes; offspring are genetically very similar to the parent, apart from mutations. Sexual reproduction involves the fusion of male and female gametes and produces genetically varied offspring.",
                "Asexual reproduction" to "Asexual reproduction can occur by binary fission in many bacteria and some protists, budding in yeast and Hydra, spore formation in fungi, fragmentation in some organisms, and vegetative propagation in plants. Vegetative propagation uses structures such as runners, rhizomes, bulbs, corms, tubers or stem cuttings. It can produce many offspring quickly, but low genetic variation may make a population more vulnerable to a shared environmental change.",
                "Sexual reproduction and gametes" to "Sexual reproduction involves the formation of haploid gametes, usually by meiosis, and their fusion at fertilisation to form a diploid zygote. Meiosis reduces chromosome number and contributes to genetic variation. Fertilisation restores the diploid chromosome number. In flowering plants, pollen carries the male gametophyte; in mammals, sperm are male gametes and ova are female gametes.",
                "Flower structure" to "A typical flower has sepals that protect the developing bud and petals that may attract pollinators. The male reproductive part, the stamen, consists of an anther and filament; the anther produces pollen grains. The female part, the carpel or pistil, includes a stigma, style and ovary. The ovary contains ovules. Flower structures vary among species, and not every flower has all parts or both reproductive sexes.",
                "Pollination and fertilisation in flowering plants" to "Pollination is the transfer of pollen from an anther to a stigma. It may occur within the same flower or plant (self-pollination) or between different plants of the same species (cross-pollination). Agents include insects, birds, bats, wind and water. After compatible pollen lands on a stigma, a pollen tube may grow through the style towards an ovule. A male nucleus fuses with the egg nucleus to form a zygote. In flowering plants, a second male nucleus fuses with the polar nuclei to form the endosperm nucleus; this is double fertilisation.",
                "Seeds, fruits and germination" to "After fertilisation, the ovule develops into a seed and the ovary commonly develops into a fruit. The seed contains an embryo and a food store or tissue that supplies nutrients during early development. A fruit helps protect seeds and may aid dispersal. Germination requires suitable conditions, commonly water, oxygen and an appropriate temperature; some seeds also require light or specific dormancy-breaking conditions. A seed does not need soil to begin germination, but it needs suitable conditions and reserves.",
                "The mammalian reproductive systems" to "In human males, the testes produce sperm and testosterone; sperm mature and are stored in the epididymis, then travel through the sperm ducts and urethra. Accessory glands add fluids that form semen. In human females, the ovaries produce ova and hormones; the oviducts carry ova and are the usual site of fertilisation; the uterus supports embryo and foetal development; the cervix is the lower opening of the uterus; and the vagina receives sperm and forms part of the birth canal.",
                "Menstrual cycle, fertilisation and implantation" to "The menstrual cycle involves coordinated changes in the ovaries and uterus. Follicle-stimulating hormone supports follicle development; oestrogen helps rebuild the uterine lining; a surge of luteinising hormone triggers ovulation; progesterone from the corpus luteum helps maintain the uterine lining. If fertilisation occurs, the early embryo divides as it travels towards the uterus and may implant in the uterine lining. If pregnancy does not occur, hormone levels fall and the uterine lining is shed during menstruation. Cycle length varies among individuals.",
                "Pregnancy and development" to "After implantation, the developing embryo receives nutrients and oxygen from maternal blood through exchange across the placenta; maternal and foetal blood normally do not mix directly. The umbilical cord connects the foetus to the placenta. The amniotic sac and fluid cushion the developing foetus. The placenta also allows carbon dioxide and metabolic wastes to pass from foetal to maternal blood. Development proceeds through embryonic and foetal stages, with growth and organ maturation before birth."
            ),
            "What is pollination?",listOf("Fusion of male and female nuclei", "Transfer of pollen from anther to stigma", "Development of an ovule into a seed", "Germination of a seed"),1,
            "Pollination is the transfer of pollen from an anther to a stigma. Fertilisation is the later fusion of gamete nuclei."
        ),
        LearningTopic(
            "bio-growth", "Growth and development", "BIO",
            "A seed becomes a seedling, a child becomes an adult, and a young shoot grows towards light. Growth increases size or dry mass; development involves changes in form and function as an organism matures. This lesson explains how cells divide, enlarge and specialise, and how seeds begin a new plant.",
            listOf(
                "Distinguish growth from development",
                "Identify the main processes responsible for growth",
                "Describe the regions of growth in a plant root and shoot",
                "Explain the conditions needed for seed germination",
                "Compare epigeal and hypogeal germination",
                "Describe selected roles of plant growth regulators",
                "Describe the cell cycle and the stages of mitosis",
                "Compare mitosis and meiosis, including chromosome behaviour and chromosome number"
            ),
            listOf(
                "Growth and development are related but different" to "Growth is a relatively permanent increase in size, dry mass or cell number. Development includes the changes in structure and function that occur as an organism matures, including differentiation and the formation of specialised tissues. An organism can develop without a large increase in size at every moment. Dry mass is often a better measure of growth than fresh mass because fresh mass changes with water content.",
                "How organisms grow" to "Growth commonly involves cell division, cell enlargement and cell differentiation. Mitosis produces genetically similar daughter cells and increases cell number. Cells may then enlarge by taking up water and synthesising cell materials. Differentiation is the process by which cells become specialised in structure and function. In animals, much growth occurs throughout the body during development; in plants, growth is concentrated in meristems.",
                "The cell cycle and mitosis" to "The cell cycle describes the events between one cell division and the next. During interphase, G1 is a period of cell growth and normal activity; during S phase, DNA is replicated; and during G2 the cell continues growing and prepares for division. DNA replication doubles the DNA content and produces two sister chromatids per chromosome, but the chromosome number counted by centromeres remains unchanged. During mitosis, chromosomes condense in prophase, line up individually at the equator in metaphase, and sister chromatids separate in anaphase when centromeres divide. New nuclei form in telophase, and cytokinesis divides the cytoplasm. Mitosis normally produces two daughter cells with the same chromosome number as the parent cell. It supports growth, tissue repair and asexual reproduction.",
                "Meiosis and genetic variation" to "Meiosis consists of two nuclear divisions after one round of DNA replication. In prophase I, homologous chromosomes pair to form bivalents; crossing over may exchange DNA between non-sister chromatids. At metaphase I, homologous pairs line up in random orientations, contributing to independent assortment. Homologous chromosomes separate in anaphase I while sister chromatids remain joined, producing two haploid cells after cytokinesis. DNA is not replicated again between meiosis I and meiosis II. During anaphase II, centromeres divide and sister chromatids separate. Meiosis normally produces four haploid products. It produces gametes directly in many animals and spores in plants. Crossing over and independent assortment create new combinations of alleles. In humans, meiosis reduces 46 chromosomes to 23 in each gamete; fertilisation restores the diploid number of 46.",
                "Plant growth regions" to "Apical meristems at root and shoot tips produce primary growth, increasing length. In roots, the root cap protects the tip; behind it are regions of cell division, elongation and maturation. Lateral meristems, such as vascular cambium and cork cambium in plants that possess them, contribute to secondary growth, increasing thickness. Not all plants show the same degree of secondary growth.",
                "What a seed needs to germinate" to "Most seeds require water, oxygen and a suitable temperature to germinate. Water activates metabolism and softens seed coverings; oxygen is needed for aerobic respiration; temperature affects enzyme-controlled reactions. Some species have additional requirements, such as light, darkness, chilling or scarification, and may show dormancy. Soil is not essential for the first stages of germination if the seed has suitable conditions and stored food.",
                "Events during germination" to "Water uptake by a dry seed is called imbibition. Metabolic activity increases, stored food reserves are mobilised and the embryo resumes growth. The radicle usually emerges first and develops into the primary root; the plumule develops into the shoot. The seedling initially depends on stored food until leaves develop and photosynthesis can supply more organic material.",
                "Epigeal and hypogeal germination" to "In epigeal germination, elongation of the hypocotyl raises the cotyledons above the soil surface; a common example is the bean. In hypogeal germination, the cotyledons remain below the soil while the epicotyl elongates; maize and pea are commonly used examples in school biology. The distinction is based on the position of the cotyledons, not simply on whether the seed is large or small.",
                "Plant growth regulators" to "Plant hormones or growth regulators are chemical messengers active in small amounts. Auxins influence cell elongation, apical dominance and tropic responses. Gibberellins can promote stem elongation and help break dormancy or stimulate germination in some species. Cytokinins promote cell division and can delay leaf senescence. Ethylene is a gaseous hormone involved in fruit ripening and other responses. Effects depend on concentration, tissue and interactions among hormones.",
                "Measuring and interpreting growth" to "Growth can be measured using length, height, leaf area, fresh mass, dry mass or cell number. Use the same method at each measurement point and control relevant variables. A growth curve often shows a lag phase, a rapid or exponential phase and a slowing or stationary phase as resources or space become limiting. A graph should have labelled axes, units and a sensible scale. Describe the pattern before suggesting a biological explanation."
            ),
            "Which condition is needed by most seeds for aerobic respiration during germination?",listOf("Complete darkness in every species", "Soil nutrients before the radicle emerges", "Oxygen", "A fully developed flower"),2,
            "Oxygen is needed for aerobic respiration, which supplies energy for the metabolic processes of germination. Water and a suitable temperature are also generally required."
        ),
        LearningTopic(
            "bio-coordination", "Coordination, control and homeostasis", "BIO",
            "When a ball moves towards your face, you blink and move your head before you have time to think about it. When your body becomes hot, sweating helps cool you. These responses depend on coordination: detecting changes, processing information and activating effectors. This lesson introduces the nervous and endocrine systems and explains how the body keeps internal conditions within suitable limits.",
            listOf(
                "Explain the meaning of coordination and homeostasis",
                "Identify the main parts of the nervous system",
                "Describe a reflex arc",
                "Relate sense organs to the stimuli they detect",
                "Compare nervous and hormonal coordination",
                "Explain selected roles of endocrine glands and hormones",
                "Describe how the body regulates temperature and water balance"
            ),
            listOf(
                "Coordination and homeostasis" to "Coordination is the organised control of activities in an organism. Receptors detect stimuli; coordination centres process information; effectors such as muscles or glands produce responses. Homeostasis is the regulation of internal conditions within suitable limits despite external or internal changes. Examples include regulation of body temperature, blood glucose and water balance. Homeostasis involves monitoring and negative feedback, not keeping every value perfectly constant.",
                "The nervous system" to "The central nervous system consists of the brain and spinal cord. The peripheral nervous system includes nerves that carry information between the central nervous system and the rest of the body. A neurone is a specialised cell that transmits electrical signals. Sensory neurones carry impulses from receptors towards the central nervous system; relay neurones connect pathways within it; motor neurones carry impulses to effectors.",
                "The reflex arc" to "A reflex is a rapid, automatic response to a stimulus that does not require conscious decision-making before the initial response. A simple reflex arc includes a receptor, sensory neurone, relay neurone in the central nervous system, motor neurone and effector. For example, touching a very hot object can trigger withdrawal of the hand. Reflexes protect the body, but not every reflex pathway is identical; some involve the brain as well as the spinal cord.",
                "Sense organs and receptors" to "Receptors are specialised cells or nerve endings that detect particular stimuli. The eye detects light; the ear detects sound and contributes to balance; the nose detects airborne chemicals; the tongue detects dissolved chemicals associated with taste; and the skin contains receptors for touch, pressure, temperature and pain. A receptor converts a stimulus into a signal that can be transmitted through the nervous system.",
                "Nervous and endocrine coordination compared" to "Nervous coordination uses electrical impulses along neurones and chemical neurotransmitters at synapses. It is usually rapid, targeted and relatively short-lived. Endocrine coordination uses hormones released by glands into the blood; hormones act on target cells with suitable receptors. Hormonal responses are often slower to begin and may last longer, although timing varies. The two systems interact to coordinate body functions.",
                "Endocrine glands and hormones" to "The pituitary gland releases hormones that regulate other glands and body functions. The thyroid produces thyroxine, which influences metabolic rate. The pancreas contains islets that release insulin and glucagon to regulate blood glucose. The adrenal glands release hormones including adrenaline, which prepares the body for rapid action. The ovaries and testes produce sex hormones involved in reproduction and secondary sexual characteristics. Hormones act only on target cells with appropriate receptors.",
                "Blood glucose regulation" to "After a meal, blood glucose tends to rise. The pancreas releases insulin, which promotes glucose uptake by many cells and storage as glycogen in liver and muscle, lowering blood glucose. When blood glucose falls, glucagon promotes breakdown of liver glycogen and release of glucose into the blood. This is a negative-feedback system. Insulin and glucagon have opposing effects; they do not both raise blood glucose.",
                "Temperature regulation" to "The hypothalamus helps coordinate thermoregulation. When body temperature rises, sweating and widening of skin blood vessels increase heat loss. When body temperature falls, narrowing of skin blood vessels reduces heat loss, shivering generates heat through muscle activity, and behavioural responses such as seeking warmth may occur. Sweating cools the body as water evaporates from the skin; high humidity can reduce the effectiveness of evaporation.",
                "Water balance and negative feedback" to "Osmoreceptors in the hypothalamus detect changes related to blood water potential. When the body needs to conserve water, more antidiuretic hormone (ADH) is released from the posterior pituitary, increasing water reabsorption in kidney collecting ducts. When water is abundant, ADH release falls and more dilute urine is produced. Negative feedback reduces the original change and helps keep internal conditions within a functional range."
            ),
            "Which hormone lowers blood glucose concentration after a meal?",listOf("Glucagon", "Adrenaline", "Thyroxine", "Insulin"),3,
            "Insulin promotes uptake and storage of glucose, lowering blood glucose. Glucagon has the opposing effect and raises blood glucose when it is low."
        ),
        LearningTopic(
            "bio-ecology-factors", "Factors affecting the distribution of organisms", "BIO",
            "A mangrove crab, a rainforest tree and a grassland ant do not occur everywhere. Their distribution depends on the conditions they can tolerate and the resources they need. Ecology studies how organisms interact with one another and with their environment. This lesson explains the physical and living factors that influence where organisms are found.",
            listOf(
                "Define ecology, habitat, population, community and ecosystem",
                "Distinguish abiotic from biotic environmental factors",
                "Explain how selected environmental factors affect distribution",
                "Identify instruments used to measure field conditions",
                "Explain limiting factors and tolerance ranges"
            ),
            listOf(
                "Ecology: organisms and their environment" to "Ecology is the study of interactions among organisms and between organisms and their physical environment. A habitat is the place where an organism lives. A population is a group of organisms of the same species living in an area at a particular time. A community consists of populations of different species in an area. An ecosystem includes the community and the non-living environment interacting as a system.",
                "Abiotic and biotic factors" to "Abiotic factors are non-living physical or chemical conditions, including light intensity, temperature, rainfall, humidity, wind, pH, salinity, dissolved oxygen and soil properties. Biotic factors arise from living organisms and their interactions, including competition, predation, parasitism, disease, grazing and availability of food or pollinators. Both groups can affect survival, reproduction and distribution.",
                "Light and temperature" to "Light provides energy for photosynthesis and can influence flowering, daily activity and behaviour. Temperature affects enzyme activity, metabolism, water loss and the ability to maintain body temperature. Each species has a range of conditions within which it can survive and reproduce. Extremes may reduce growth or cause death, while temperature and light can interact with water availability and other factors.",
                "Water, humidity and wind" to "Rainfall and soil moisture influence plant growth and the availability of water for animals. Humidity affects evaporation and transpiration; high humidity can reduce the rate of water loss from surfaces. Wind can increase evaporation, affect pollination and seed dispersal, and alter the temperature experienced by organisms. Waterlogging can reduce oxygen available to plant roots, while drought can restrict photosynthesis and growth.",
                "Soil, pH and salinity" to "Soil texture, structure, depth, mineral content, organic matter, drainage and water-holding capacity affect plant distribution. Soil pH influences nutrient availability and the activity of soil organisms. Salinity affects water uptake because high external salt concentration lowers water potential and may cause water stress. Different plants tolerate different soil and salinity conditions.",
                "Biotic interactions" to "Competition occurs when organisms use the same limited resource, such as light, water, food or nesting sites. Predation is one organism capturing and consuming another. Parasitism benefits the parasite while harming the host. Mutualism benefits both partners, while commensalism benefits one without a clear effect on the other. These interactions can influence population size and where species occur.",
                "Measuring environmental conditions" to "Use a thermometer to measure temperature, a rain gauge for rainfall, a hygrometer for relative humidity, a light meter for light intensity, a pH meter or indicator for acidity, and an anemometer for wind speed. Soil moisture meters can estimate soil water content. Record units, location, date, time and method. A single reading may not represent an entire habitat; repeat measurements and sample across relevant locations.",
                "Limiting factors and tolerance" to "A limiting factor is a condition or resource that restricts growth, survival or distribution when it is in short supply or outside a suitable range. A species may have minimum, optimum and maximum tolerance levels for a factor. Distribution is usually influenced by several interacting factors rather than one variable alone. Correlation between an environmental factor and species abundance does not by itself prove that the factor caused the pattern."
            ),
            "Which instrument is used to measure relative humidity?",listOf("Hygrometer", "Rain gauge", "Anemometer", "Thermometer"),0,
            "A hygrometer measures humidity. A rain gauge measures rainfall, an anemometer measures wind speed and a thermometer measures temperature."
        ),
        LearningTopic(
            "bio-interactions", "Ecological relationships and energy flow", "BIO",
            "A grass plant captures light energy, a grasshopper feeds on the plant, and a frog may feed on the grasshopper. When organisms interact, energy and materials move through ecosystems. This lesson explains feeding relationships, ecological roles, symbiosis and the cycling of essential materials.",
            listOf(
                "Define producer, consumer, decomposer and trophic level",
                "Construct and interpret food chains and food webs",
                "Explain why energy decreases along food chains",
                "Distinguish major ecological relationships",
                "Describe the water, carbon and nitrogen cycles"
            ),
            listOf(
                "Producers, consumers and decomposers" to "Producers, such as green plants and algae, make organic substances using light energy in photosynthesis. Consumers obtain organic food by feeding on other organisms. Decomposers, including many bacteria and fungi, break down dead organic matter and wastes, releasing nutrients back into the environment. Decomposition is essential for nutrient recycling.",
                "Food chains and trophic levels" to "A food chain shows a feeding pathway through an ecosystem. A simple example is grass → grasshopper → frog → snake. The arrow points from the organism being eaten to the organism that obtains the food, showing the direction of energy transfer. The producer is usually the first trophic level; primary consumers feed on producers, secondary consumers feed on primary consumers, and so on. Real organisms may feed at more than one trophic level.",
                "Food webs and ecological stability" to "A food web links many food chains because most organisms have more than one food source or predator. Removing one species can affect several other populations. A food web with alternative feeding pathways may be less vulnerable to the loss of a single food source, although the actual effect depends on species interactions and ecosystem conditions. Do not assume every change produces the same response in every ecosystem.",
                "Why energy decreases along a food chain" to "Energy enters most ecosystems as sunlight and is captured by producers. At each trophic transfer, not all biomass is eaten or digested; some energy is lost in faeces and other wastes, and much is released as heat during respiration and other metabolic processes. Less energy is therefore available to the next trophic level. The often-quoted ten per cent transfer is a rough teaching generalisation, not a fixed law for every ecosystem.",
                "Ecological relationships" to "Competition occurs when organisms require the same limited resource. Predation occurs when one organism captures and consumes another. Parasitism benefits the parasite and harms the host. Mutualism benefits both partners, such as a pollinator obtaining nectar while transferring pollen. Commensalism benefits one organism without a clear effect on the other. These relationships can be context-dependent and may change with environmental conditions.",
                "The water cycle" to "Water evaporates from oceans, lakes and soil; plants release water vapour through transpiration. Water vapour condenses to form clouds and returns as precipitation. Some water runs over land into rivers and oceans; some infiltrates the soil and may recharge groundwater. Organisms take up water and return it through respiration, excretion and decomposition. Solar energy drives much of the movement of water through the cycle.",
                "The carbon cycle" to "Plants and algae remove carbon dioxide from the atmosphere or water during photosynthesis and incorporate carbon into organic molecules. Carbon moves through food chains as organisms feed. Respiration by plants, animals and microorganisms returns carbon dioxide to the environment. Decomposition releases carbon from dead organic matter; combustion of biomass and fossil fuels also releases carbon dioxide. Oceans exchange carbon dioxide with the atmosphere and store large amounts of carbon.",
                "The nitrogen cycle" to "Most organisms cannot use atmospheric nitrogen gas directly. Nitrogen-fixing bacteria convert nitrogen gas into ammonia or related compounds. Nitrifying bacteria convert ammonium into nitrite and nitrate. Plants absorb nitrate or ammonium and use nitrogen to make amino acids and nucleic acids. Animals obtain nitrogen by feeding. Decomposers return nitrogen to soil as ammonium through ammonification; denitrifying bacteria convert nitrate to nitrogen gas, returning it to the atmosphere.",
                "Interpreting ecological diagrams and data" to "In a food chain, follow the arrows from food to feeder. In a food web, trace all pathways relevant to the organism in the question. In nutrient-cycle diagrams, identify the process and the material moving between reservoirs. When comparing population data, state the trend and use values from the graph or table. Distinguish a direct observation from an explanation of the cause."
            ),
            "In a food chain, what does an arrow normally show?",listOf("The direction in which an animal walks", "The direction of energy transfer from food to feeder", "The direction of rainfall", "The direction of reproduction"),1,
            "A food-chain arrow points from the organism being eaten to the organism that obtains the food, showing the direction of energy transfer."
        ),
        LearningTopic(
            "bio-habitats", "Habitats and ecological niches", "BIO",
            "A pond, a forest canopy, a grassland and a rocky stream provide different places for organisms to live. A habitat is more than a location: it provides the food, water, shelter and conditions an organism needs. This lesson compares major habitat types and introduces the ecological niche, which describes how an organism lives and interacts within its environment.",
            listOf(
                "Define habitat and ecological niche",
                "Compare aquatic, terrestrial and arboreal habitats",
                "Identify microhabitats within larger habitats",
                "Relate habitat conditions to organism adaptations",
                "Explain why habitat diversity supports biodiversity"
            ),
            listOf(
                "Habitat and niche" to "A habitat is the place where an organism lives, such as a pond, forest, grassland or soil layer. An ecological niche describes the role and way of life of an organism, including the resources it uses, the conditions it tolerates and its interactions with other organisms. Two species may share a habitat but occupy different niches because they use different foods, microhabitats or activity times.",
                "Aquatic habitats" to "Aquatic habitats include freshwater environments such as rivers, streams, lakes and ponds, and marine or brackish environments such as seas, estuaries and mangrove creeks. Important factors include salinity, dissolved oxygen, light penetration, water movement, depth and temperature. Organisms may have adaptations for buoyancy, swimming, attachment, salt balance or low oxygen conditions.",
                "Terrestrial habitats" to "Terrestrial habitats include forests, grasslands, savannas, deserts, mountains and soil. Organisms face challenges such as water loss, temperature variation, wind, gravity and changes in food availability. Plant roots anchor the plant and absorb water; waxy cuticles reduce water loss; animals may burrow, migrate or become active at cooler times. The importance of each adaptation depends on the specific habitat.",
                "Arboreal and microhabitats" to "Arboreal organisms live mainly in trees or shrubs. They may have grasping limbs, claws, adhesive pads or other features that help them climb or move among branches. A microhabitat is a small, distinct part of a larger habitat, such as the underside of a log, a tree-hole, leaf litter, a rock crevice or the surface of a pond. Microhabitats can differ in moisture, light and temperature over short distances.",
                "Habitat structure and resources" to "The structure of a habitat affects where organisms feed, shelter and reproduce. A forest has layers such as canopy, understory and forest floor, each with different light and moisture conditions. A pond has shallow margins, open water and bottom sediments. Grassland vegetation may provide seeds, leaves, nesting materials and hiding places. The distribution of organisms often reflects where essential resources and suitable conditions overlap.",
                "Adaptation and habitat suitability" to "An organism is more likely to survive and reproduce where its inherited features are suited to local conditions. Fish have gills for aquatic gas exchange; many terrestrial plants have structures that limit water loss; mangrove plants tolerate saline and waterlogged conditions; burrowing animals can avoid surface heat or predators. No habitat is universally suitable: a feature that is advantageous in one environment may be disadvantageous in another.",
                "Habitat change and conservation" to "Habitats change naturally through flooding, drought, fire, succession and seasonal variation. Human activities such as deforestation, drainage, mining, pollution, overgrazing and urban development can alter or destroy habitats. Habitat fragmentation separates populations and can reduce access to food, mates and migration routes. Conservation may involve protected areas, restoration, sustainable resource use and reducing pollution.",
                "Field observation of habitats" to "When studying a habitat, record the location, date, time, weather and visible environmental conditions. Describe vegetation structure, water availability, soil or substrate and evidence of organisms. Use quadrats for suitable stationary organisms and transects to examine changes along an environmental gradient. Avoid disturbing organisms unnecessarily, and distinguish what was observed from what you infer."
            ),
            "What is an ecological niche?",listOf("Only the geographical name of a habitat", "The total number of organisms in a country", "The role and way of life of an organism in its environment", "A non-living factor such as rainfall"),2,
            "A niche describes how an organism uses resources, tolerates conditions and interacts with other organisms. A habitat is the place where it lives."
        ),
        LearningTopic(
            "bio-biomes", "Local Nigerian biomes", "BIO",
            "Nigeria contains humid coastal forests, freshwater swamps, mangroves, savannas and highland environments. These regions differ in rainfall, temperature, soil, vegetation and the organisms they support. A biome is a large ecological region characterised by its climate and dominant vegetation, together with associated animal communities.",
            listOf(
                "Define biome and distinguish it from a habitat",
                "Identify major Nigerian vegetation and ecological zones",
                "Compare rainforest, mangrove, swamp and savanna environments",
                "Describe the Guinea, Sudan and Sahel savanna zones",
                "Explain how altitude influences montane environments",
                "Relate climate and vegetation to organism adaptations"
            ),
            listOf(
                "Biome, vegetation zone and habitat" to "A biome is a broad ecological region associated with a characteristic climate and dominant vegetation. A habitat is the particular place where an organism lives within a biome. A vegetation zone describes a broad pattern of plant cover. Boundaries are gradual and can shift with rainfall, land use, fire, soil and climate; they should not be treated as perfectly sharp lines on a map.",
                "Tropical rainforest" to "The humid southern part of Nigeria supports tropical rainforest in areas with high rainfall and a relatively short dry season. Rainforests commonly have layered vegetation, tall broad-leaved trees, lianas, shade-tolerant plants and high biological diversity. The canopy intercepts much of the light, while the forest floor is often shaded. Many organisms are adapted to humid conditions and complex vegetation structure.",
                "Freshwater swamp and mangrove environments" to "Freshwater swamps occur in low-lying, waterlogged areas where soils may remain saturated for long periods. Mangrove forests occur along tidal, brackish coastal environments, including parts of the Niger Delta. Mangrove plants tolerate saline and waterlogged conditions; some have aerial roots or pneumatophores that assist gas exchange in oxygen-poor sediment. Tidal flooding, salinity and sediment conditions strongly influence which species occur.",
                "Guinea savanna" to "The Guinea savanna forms a broad transition between the humid forest zone and drier northern savannas. It commonly has grasses with scattered trees and shrubs, a distinct wet season and dry season, and vegetation influenced by fire and grazing. Tree cover and grass height vary with rainfall, soil and land use. Many grasses regrow after seasonal burning or grazing, although frequent intense disturbance can degrade vegetation.",
                "Sudan savanna and Sahel transition" to "The Sudan savanna is generally drier than the Guinea savanna and has shorter grasses and more widely spaced drought-tolerant trees and shrubs. Further north, the Sahel is a semi-arid transition with lower and less reliable rainfall, sparse grasses and scattered shrubs or trees. Plants may have deep roots, small leaves or seasonal growth patterns that reduce water stress. Drought and land degradation can affect these environments, but desertification is influenced by climate and human land use rather than one cause alone.",
                "Montane environments" to "Higher elevations, including parts of the Jos Plateau, Mambilla Plateau and Obudu highlands, have cooler conditions than nearby lowlands. Temperature generally decreases with altitude, and slopes may differ in rainfall, wind exposure and soil. Montane grasslands, woodland and patches of forest occur according to local conditions. Organisms in these habitats may be adapted to cooler temperatures, strong winds and shorter growing seasons.",
                "Climate, vegetation and animal distribution" to "Rainfall amount and seasonality influence plant growth, water availability and fire frequency. Temperature affects metabolism and growing seasons. Soil and drainage influence which plants establish. Animals respond to food, water, shelter, breeding sites and vegetation structure. A species may occur across more than one vegetation zone if it tolerates the conditions, while local populations may differ in abundance.",
                "Human impacts and conservation in Nigeria" to "Deforestation, agricultural expansion, overgrazing, bush burning, drainage, mining, pollution and infrastructure development alter Nigerian habitats. Conservation measures include protected areas, sustainable farming and forestry, mangrove restoration, watershed protection, erosion control and community-based resource management. Conservation planning should consider local livelihoods and the ecological functions of each habitat.",
                "How to answer Nigerian biome questions" to "Identify the biome from its climate and vegetation, not from a place name alone. Link high rainfall and layered broad-leaved trees with rainforest; tidal brackish water and aerial roots with mangroves; seasonal rainfall and grasses with savanna; and cooler high-altitude conditions with montane habitats. State that zones form transitions and local conditions vary."
            ),
            "Which Nigerian environment is most strongly associated with tidal brackish water and mangrove vegetation?",listOf("Sudan savanna", "Jos Plateau grassland", "Sahel grassland", "Coastal Niger Delta"),3,
            "Mangrove vegetation is characteristic of tidal coastal and estuarine environments, including parts of the Niger Delta, where salinity and waterlogged sediments shape plant adaptations."
        ),
        LearningTopic(
            "bio-populations", "Population ecology and succession", "BIO",
            "A population of grasshoppers may increase after a rainy season, then decline when food becomes scarce. A bare surface may gradually develop into a community of plants and animals. Population ecology studies changes in population size and structure; ecological succession describes changes in communities over time.",
            listOf(
                "Define population size, density and distribution",
                "Explain factors that change population size",
                "Calculate simple population density",
                "Describe exponential and logistic population growth",
                "Explain carrying capacity and limiting factors",
                "Compare primary and secondary succession"
            ),
            listOf(
                "Population size and density" to "Population size is the number of individuals in a population. Population density is the number of individuals per unit area or volume. For example, if 120 plants are counted in 30 square metres, density is 4 plants per square metre. Always state the unit and the area or volume sampled. Density does not describe how individuals are spaced; distribution or dispersion describes their spatial pattern.",
                "How populations change" to "Population size changes through births, deaths, immigration and emigration. Births and immigration add individuals; deaths and emigration remove them. Food availability, disease, predation, competition, climate, habitat quality and human activities can affect these processes. A population may fluctuate seasonally or over longer periods.",
                "Population growth patterns" to "Under ideal conditions with abundant resources, a population may increase rapidly in an exponential or J-shaped pattern. In real environments, resources and space are limited. Logistic or S-shaped growth describes an initial increase followed by slowing as the population approaches the environment's carrying capacity. These are models; actual populations may fluctuate or fail to follow a smooth curve.",
                "Carrying capacity and limiting factors" to "Carrying capacity is the population size that an environment can support over time under prevailing conditions. It depends on resources such as food, water, space and shelter, as well as disease, predation and environmental conditions. Carrying capacity can change when climate, habitat or resource availability changes. It is not a permanent fixed number for a habitat.",
                "Sampling populations" to "Quadrats can estimate the density of plants or slow-moving organisms. Random sampling helps reduce selection bias; systematic sampling can reveal changes along a gradient. For mobile animals, mark–release–recapture may be used under suitable assumptions: marks should not affect survival or recapture probability, marked individuals should mix back into the population, and the population should be reasonably closed during the study. Estimates are uncertain and should not be treated as exact counts.",
                "Primary succession" to "Primary succession begins on a surface where soil is absent or very poorly developed, such as newly exposed rock. Pioneer organisms, including lichens and mosses in some settings, colonise the surface and contribute to weathering and organic matter accumulation. As soil develops, grasses, herbs, shrubs and eventually other communities may establish. The sequence depends on climate, substrate and local species.",
                "Secondary succession" to "Secondary succession occurs where a community has been disturbed but soil remains, such as after cultivation is abandoned or some fires. Seeds, roots, soil organisms and nutrients may remain, so recovery can be faster than primary succession. The sequence varies with disturbance severity, climate, soil and nearby sources of colonising species.",
                "Ecological succession and disturbance" to "Succession is a directional change in community composition over time, but it does not always lead to one universal final community. Repeated fire, grazing, flooding, drought or human activity can maintain or redirect vegetation patterns. In examination answers, identify the starting condition, the role of pioneer species, how soil or habitat conditions change, and why later communities can establish."
            ),
            "A habitat contains 240 seedlings in an area of 60 m². What is the population density?",listOf("4 seedlings/m²", "60 seedlings/m²", "180 seedlings/m²", "300 seedlings/m²"),0,
            "Population density = number of individuals ÷ area = 240 ÷ 60 = 4 seedlings per square metre."
        ),
        LearningTopic(
            "bio-soil", "Soil ecology and conservation", "BIO",
            "Soil is more than loose material beneath our feet. It contains mineral particles, organic matter, water, air and living organisms. Its structure influences plant growth, water movement and the organisms that can live in it. This lesson compares common soil types and explains how soil can be tested, degraded and conserved.",
            listOf(
                "Describe the main components of soil",
                "Compare sandy, clayey and loamy soils",
                "Explain soil texture, porosity, permeability and capillarity",
                "Describe soil horizons and the role of humus",
                "Explain how soil properties affect organisms and plant growth",
                "Identify causes of soil degradation and methods of conservation"
            ),
            listOf(
                "What soil contains" to "Soil contains mineral particles derived from rock, organic matter from living and dead organisms, water, air and a diverse community of organisms. Soil organisms include bacteria, fungi, earthworms, insects and other invertebrates. They contribute to decomposition, nutrient cycling, soil structure and aeration. The proportions of air and water vary with soil texture, structure, drainage and recent rainfall.",
                "Soil texture: sand, silt and clay" to "Soil texture depends on the relative proportions of sand, silt and clay particles. Sandy soils have relatively large particles and large pore spaces; water drains quickly and nutrients may be leached. Clayey soils have very small particles and can retain much water and nutrients, but may drain and aerate poorly when compacted or waterlogged. Loam contains a balanced mixture of particle sizes and often supports good cultivation, although its properties still depend on organic matter and structure.",
                "Porosity, permeability and capillarity" to "Porosity describes the proportion of soil volume made up of pore spaces. Permeability describes how easily water or air passes through connected pores. Capillarity is the movement of water through small spaces due to adhesion, cohesion and surface tension. A soil may have high total porosity but low permeability if many pores are tiny or poorly connected. Soil structure and compaction affect these properties.",
                "Soil profile and horizons" to "A soil profile is a vertical section showing layers called horizons. A typical profile may include an organic surface layer, topsoil rich in mineral particles and organic matter, subsoil with accumulated materials, and weathered parent material above bedrock. The depth and clarity of horizons vary among soils and climates. Do not assume every soil has every horizon equally developed.",
                "Humus, fertility and soil organisms" to "Humus is relatively stable organic matter formed during decomposition. It can improve soil structure, water retention and nutrient supply. Decomposers break down organic residues and release nutrients; earthworms mix soil and create channels that improve aeration and drainage. Soil fertility depends on nutrient availability, pH, organic matter, water, structure and biological activity, not on colour alone.",
                "Soil tests and field investigation" to "Soil texture can be estimated by feel or investigated by settling particles in water, although field methods have limitations. pH can be tested with indicator paper or a calibrated meter. Infiltration tests estimate how quickly water enters soil; moisture sensors estimate water content. For a fair comparison, collect samples from defined depths and locations, use consistent methods, repeat measurements and record units and conditions.",
                "Soil degradation" to "Soil can be degraded by erosion, nutrient depletion, salinisation, compaction, contamination, deforestation, overgrazing and poorly managed irrigation. Water erosion removes topsoil through splash, sheet, rill and gully processes. Wind erosion can remove dry, exposed particles. Loss of topsoil reduces fertility and can increase sedimentation in rivers and reservoirs.",
                "Soil conservation" to "Conservation methods include maintaining vegetation cover, mulching, cover crops, contour farming, terracing on suitable slopes, windbreaks, reduced tillage, controlled grazing, adding appropriate organic matter and managing irrigation to prevent salinisation. The best method depends on slope, rainfall, soil type, land use and local conditions. Conservation should reduce erosion while maintaining soil structure and fertility."
            ),
            "Which soil type generally has the largest particles and the fastest drainage?",listOf("Clayey soil", "Sandy soil", "Peat only", "Compacted clay"),1,
            "Sandy soil has relatively large particles and pore spaces, so water generally drains more quickly than in clayey soil."
        ),
        LearningTopic(
            "bio-environment", "Humans, health and the environment", "BIO",
            "Human health depends on more than the absence of disease. Safe water, sanitation, clean air, balanced nutrition, vaccination and healthy ecosystems all influence well-being. This lesson explains how infectious diseases spread, how communities reduce risk and how pollution and habitat loss affect people and other organisms.",
            listOf(
                "Distinguish communicable and non-communicable diseases",
                "Identify major pathogen groups and routes of transmission",
                "Explain the roles of vectors, hosts and reservoirs",
                "Describe prevention, sanitation, vaccination and responsible medicine use",
                "Identify major forms and effects of pollution",
                "Explain conservation and sustainable environmental practices"
            ),
            listOf(
                "Communicable and non-communicable diseases" to "Communicable diseases are caused by infectious agents and can spread directly or indirectly between hosts. Non-communicable diseases are not transmitted from person to person; examples include many cardiovascular diseases, cancers and inherited disorders. Risk factors for non-communicable disease may include genetics, age, diet, physical activity, tobacco exposure and environmental conditions.",
                "Pathogens and routes of transmission" to "Pathogens include bacteria, viruses, protozoa, fungi and parasitic worms. Transmission may occur through respiratory droplets or aerosols, contaminated food or water, direct contact, blood and other body fluids, or vectors. The route depends on the pathogen. Safe water, food hygiene, ventilation, handwashing, appropriate protective measures and infection prevention can interrupt transmission.",
                "Vectors, hosts and reservoirs" to "A vector is an organism that transmits a pathogen between hosts; female Anopheles mosquitoes transmit malaria parasites. A host is an organism in which a pathogen lives or reproduces. A reservoir is a human, animal or environmental source in which an infectious agent is normally maintained. Not every insect is a vector, and not every disease is vector-borne. Vector control may include removing breeding sites, using insecticide-treated nets where recommended and applying locally approved control measures.",
                "Prevention and vaccination" to "Disease prevention includes personal hygiene, safe water, sanitation, food safety, vaccination, vector control, early diagnosis and appropriate treatment. Vaccines expose the immune system to an antigen or instructions for producing an antigen, helping it develop immune memory without requiring the person to experience the full disease. Vaccine schedules and recommendations vary by age, location and current public-health guidance; follow official health advice.",
                "Medicines and antimicrobial resistance" to "Antibiotics act against bacteria, not viruses, so they do not treat common viral infections such as influenza. Antiviral medicines act against selected viruses; antifungal and antiparasitic medicines target other pathogen groups. Misuse or unnecessary use of antimicrobials can select for resistant organisms. Use medicines only as prescribed or directed by qualified health professionals, and do not share leftover antibiotics.",
                "Water, sanitation and community health" to "Contaminated water can transmit pathogens that cause diarrhoeal diseases. Safe drinking water, proper sewage disposal, handwashing with soap, food hygiene and protection of water sources reduce risk. Open defaecation and poorly managed waste can contaminate soil and water. Public-health measures work best when supported by accessible facilities, community participation and reliable health information.",
                "Pollution and its effects" to "Air pollution can come from vehicle exhaust, industrial emissions, burning waste and household fuels; it can affect respiratory and cardiovascular health. Water pollution may result from sewage, agricultural runoff, oil spills and industrial discharge. Soil pollution can arise from chemicals, heavy metals and poorly managed waste. Noise pollution can affect hearing, sleep and stress. Effects depend on the pollutant, dose, duration and exposure route.",
                "Climate, biodiversity and environmental health" to "Climate change can alter heat exposure, rainfall, water availability, crop productivity and the distribution of some disease vectors. Habitat destruction and biodiversity loss can disrupt ecosystem services such as pollination, water purification and soil formation. Environmental effects are complex; avoid claiming that one environmental change causes every disease outbreak. Risk depends on ecology, infrastructure, behaviour and public-health systems.",
                "Conservation and responsible action" to "Conservation protects species, habitats and ecosystem functions. Approaches include protected areas, restoration, sustainable harvesting, pollution control, waste reduction, erosion control and environmental education. Individuals can reduce litter, conserve water, dispose of waste safely and support evidence-based community programmes. Effective solutions should be adapted to local ecological and social conditions."
            ),
            "Which medicine group is specifically used to treat bacterial infections rather than viral infections?",listOf("Antihistamines", "Antacids", "Antibiotics", "Analgesics"),2,
            "Antibiotics act against bacteria. They do not treat viral infections, and their unnecessary use contributes to antimicrobial resistance."
        ),
        LearningTopic(
            "bio-variation", "Variation in populations", "BIO",
            "No two people are exactly alike, and individuals within the same species differ in many characteristics. Some differences form a continuous range, while others fall into distinct categories. Variation matters because it provides the differences on which natural selection can act, and because inherited and environmental influences both shape organisms.",
            listOf(
                "Define variation within a species",
                "Distinguish continuous and discontinuous variation",
                "Compare genetic and environmental causes of variation",
                "Explain how mutation and meiosis contribute to inherited variation",
                "Describe the ABO blood-group system at an introductory level",
                "Explain why variation is important in populations"
            ),
            listOf(
                "What variation means" to "Variation refers to differences among individuals of the same species. Individuals may differ in height, body mass, blood group, leaf shape, flowering time or other characteristics. Variation may be inherited, caused by environmental conditions, or influenced by both genes and environment. A measured difference is not automatically genetic; the cause must be investigated.",
                "Continuous variation" to "Continuous variation shows a range of values with many intermediate forms, such as human height or body mass. It is often influenced by many genes and environmental factors, so values may form a roughly bell-shaped distribution in a large population. Age, nutrition, health and measurement conditions can affect the observed values.",
                "Discontinuous variation" to "Discontinuous variation places individuals into distinct categories with few or no intermediate forms for the characteristic being studied. The ABO blood groups are a common example: individuals are classified as A, B, AB or O. Some categorical traits are strongly influenced by one or a few genes, although real biological traits can be more complex than a simple school-level model.",
                "Genetic and environmental causes" to "Genetic variation arises from differences in DNA. Mutation creates new alleles; meiosis reshuffles alleles through independent assortment and crossing over; random fertilisation combines gametes in new ways. Environmental factors such as diet, climate, disease and activity can also influence characteristics. Height, for example, has a genetic component but is also affected by nutrition and health during growth.",
                "The ABO blood-group system" to "The ABO blood groups depend on antigens on the surface of red blood cells. The common alleles are Iᴬ, Iᴮ and i. Iᴬ and Iᴮ are codominant, while each is dominant over i. Genotypes IᴬIᴬ or Iᴬi produce group A; IᴮIᴮ or Iᴮi produce group B; IᴬIᴮ produces group AB; and ii produces group O. Blood transfusion compatibility is more complex than matching the ABO label alone because other blood-group antigens and clinical factors matter.",
                "Using genetic crosses to explain variation" to "A genetic cross predicts possible offspring genotypes from parental alleles. For example, a parent with genotype Iᴬi can pass either Iᴬ or i; a parent with genotype Iᴮi can pass Iᴮ or i. A Punnett square lists possible combinations and their probabilities under the model. Probabilities describe expected proportions over many conceptions, not a guarantee for a particular child.",
                "Sex chromosomes and inherited differences" to "In the common human XX/XY pattern, most females have XX sex chromosomes and most males have XY. Eggs normally carry an X chromosome; sperm carry either X or Y, so the sperm usually determines whether the resulting zygote is XX or XY. Biological sex development is more varied than this simplified model, but XX/XY is the standard introductory inheritance model used in many school questions.",
                "Why variation matters" to "Variation can affect how individuals respond to environmental conditions. If a heritable characteristic improves survival or reproductive success in a particular environment, individuals carrying it may leave more offspring. Natural selection changes the frequency of inherited variants in populations over generations. Variation is not produced because organisms consciously need a feature; it arises through genetic processes and is acted on by selection."
            ),
            "Which type of variation is illustrated by the four ABO blood groups?",listOf("Continuous variation", "Environmental variation only", "Seasonal variation", "Discontinuous variation"),3,
            "ABO blood groups are distinct categories (A, B, AB and O), so they are a standard example of discontinuous variation."
        ),
        LearningTopic(
            "bio-heredity", "Heredity and genetics", "BIO",
            "Children often resemble their parents, but they are not identical to them. Heredity is the passing of genetic information from parents to offspring. Genetics studies genes, alleles, chromosomes and how characteristics are inherited. This lesson builds from DNA and genes to simple genetic crosses and the interpretation of inheritance patterns.",
            listOf(
                "Define heredity, gene, allele, chromosome, genotype and phenotype",
                "Explain the relationship between DNA, genes and chromosomes",
                "Distinguish dominant and recessive alleles",
                "Use a Punnett square to predict a simple monohybrid cross",
                "State the basic principles of Mendelian inheritance",
                "Explain sex determination and introductory sex-linked inheritance",
                "Explain multiple alleles and codominance using the ABO blood-group system"
            ),
            listOf(
                "DNA, genes and chromosomes" to "DNA (deoxyribonucleic acid) is the molecule that stores hereditary information in most organisms. A gene is a section of DNA that contributes to a functional product, such as a protein or functional RNA. In eukaryotic cells, DNA is packaged with proteins into chromosomes, which are found mainly in the nucleus. Different versions of the same gene are called alleles. Not all DNA sequences are genes, and not every characteristic is controlled by a single gene.",
                "Genotype and phenotype" to "Genotype is the genetic constitution of an organism for a gene or set of genes. Phenotype is the observable characteristic produced through the interaction of genotype and environment. For a simple gene with two alleles, an organism may be homozygous (two copies of the same allele) or heterozygous (two different alleles). A heterozygote may show the dominant phenotype in a complete-dominance model.",
                "Dominant and recessive alleles" to "In a simple complete-dominance model, a dominant allele is expressed in the phenotype of a heterozygote, while a recessive phenotype appears only when two recessive alleles are present. Dominant does not mean common, stronger, healthier or better. Recessive does not mean rare or harmful. Many real traits involve multiple genes, incomplete dominance, codominance or environmental influences.",
                "Mendel's monohybrid cross" to "A monohybrid cross follows the inheritance of one gene. If T represents a dominant allele and t a recessive allele, a cross between Tt and Tt can produce TT, Tt, Tt and tt. The expected genotype ratio is 1 TT : 2 Tt : 1 tt; under complete dominance, the expected phenotype ratio is 3 dominant : 1 recessive. These are probability expectations, not guaranteed outcomes in a small family.",
                "How to use a Punnett square" to "Write the possible gametes from each parent along the top and side of a grid. Combine one allele from each parent in every box. Count the possible genotypes and, if needed, translate them into phenotypes using the dominance relationship given. State the assumptions, such as equal gamete probability and complete dominance. Do not confuse a genotype ratio with a phenotype ratio.",
                "Mendelian principles and their limits" to "Mendel's law of segregation states that the two alleles for a gene separate during gamete formation, so each gamete receives one allele. Independent assortment applies to genes that assort independently, commonly because they are on different chromosomes or sufficiently far apart on the same chromosome. Genes close together on a chromosome may be linked and do not assort independently in the simple way. Mendelian models are useful starting points but do not explain every inheritance pattern.",
                "Multiple alleles and ABO blood groups" to "The ABO blood group is controlled by three common alleles at one gene locus: Iᴬ, Iᴮ and i. Iᴬ and Iᴮ are codominant, so a person with genotype IᴬIᴮ expresses both antigens and has blood group AB. Each of Iᴬ and Iᴮ is dominant over i: Iᴬi gives group A, Iᴮi gives group B, and ii gives group O. A person inherits one allele from each biological parent. The six common genotypes therefore produce four ABO phenotypes: A, B, AB and O. ABO type is separate from the Rh(D) blood-group system, and this inheritance model must not be used on its own to make clinical transfusion decisions.",
                "Sex determination and sex-linked inheritance" to "In the common human XX/XY model, most females have XX sex chromosomes and most males have XY. Genes on the X chromosome may show X-linked inheritance. A male with one X chromosome has only one copy of most X-linked genes, so a recessive allele on that chromosome may be expressed. A carrier female with one usual and one recessive allele may pass either allele to offspring. A father passes his X chromosome to daughters and his Y chromosome to sons; a mother passes one of her X chromosomes to every child. In a simplified X-linked recessive cross, a carrier mother and an unaffected father can have affected sons, while daughters are not affected in that cross but may be carriers. Use the pedigree or cross information provided rather than assuming a trait is sex-linked; real conditions can show more complex expression.",
                "Mutation and genetic variation" to "Mutation is a change in DNA sequence. Mutations may have no effect, harmful effects or beneficial effects depending on the gene, environment and context. Mutations in cells that contribute to gametes can be inherited; mutations in ordinary body cells are generally not passed to offspring. Meiosis and fertilisation also create new combinations of alleles. Genetic variation is the raw material on which natural selection can act.",
                "Genetics in health and society" to "Genetic information can help explain inherited conditions, blood groups and family resemblance. Genetic risk is not always certainty: many conditions involve multiple genes and environmental factors. Genetic tests require appropriate interpretation, consent and privacy safeguards. Avoid stigmatising language or assuming that a genotype determines a person's worth, behaviour or entire health future."
            ),
            "In a simple complete-dominance cross Tt × Tt, what is the expected genotype ratio?",listOf("1 TT : 2 Tt : 1 tt", "3 TT : 1 tt", "1 Tt : 1 tt", "All Tt"),0,
            "Each parent produces T and t gametes. The four equally likely combinations are TT, Tt, Tt and tt, giving a 1:2:1 genotype ratio."
        ),
        LearningTopic(
            "bio-biotechnology", "Biotechnology", "BIO",
            "People have used living organisms to make useful products for thousands of years, from fermented foods to improved crop varieties. Modern biotechnology can work with cells, enzymes and DNA to produce medicines, improve crops and investigate biological problems. This lesson compares traditional and modern methods and explains why safety and responsible use matter.",
            listOf(
                "Define biotechnology and distinguish traditional from modern methods",
                "Explain the use of microorganisms in fermentation",
                "Describe selective breeding and tissue culture",
                "Outline the basic idea of recombinant DNA technology",
                "Identify applications of biotechnology in medicine and agriculture",
                "Discuss benefits, limitations and biosafety considerations"
            ),
            listOf(
                "What biotechnology means" to "Biotechnology is the use of living organisms, cells or biological processes to develop products and services. Traditional biotechnology includes fermentation and selective breeding. Modern biotechnology may use cell culture, molecular biology, recombinant DNA, genetic testing and genome-editing methods. The boundary between traditional and modern methods is not absolute; both use biological systems to achieve a useful purpose.",
                "Fermentation in food production" to "Yeast can ferment sugars to produce ethanol and carbon dioxide; this process is used in bread-making and some beverage production. Lactic acid bacteria ferment sugars to produce lactic acid in foods such as yoghurt and some fermented vegetables. Fermentation conditions such as temperature, pH, oxygen availability and substrate affect the product and rate. Food fermentation must use suitable organisms and hygienic conditions.",
                "Selective breeding" to "Selective breeding involves choosing parents with desirable inherited characteristics and breeding them over generations. It has been used to improve crop yield, disease resistance, animal productivity and other traits. It can be relatively accessible, but it may take many generations, can reduce genetic diversity and may unintentionally increase inherited problems if the breeding population is too narrow.",
                "Plant tissue culture" to "Plant tissue culture grows plant cells, tissues or organs on a sterile nutrient medium under controlled conditions. Small pieces of plant tissue, called explants, can produce many plantlets through micropropagation. Tissue culture can rapidly multiply selected plants and help produce disease-free planting material when starting material and procedures are appropriate. Contamination, cost, specialised skills and genetic variation among regenerated plants are potential limitations.",
                "Recombinant DNA technology" to "Recombinant DNA technology combines DNA sequences from different sources. A gene of interest may be inserted into a vector, such as a plasmid, and introduced into a host cell. The host can then copy the DNA or express the gene product. In a simplified example, genetically engineered microorganisms can produce human insulin. Real procedures involve careful selection of the gene, vector, host, regulatory sequences and verification of the product.",
                "Applications in medicine and agriculture" to "Biotechnology is used to produce some medicines, diagnostic reagents and vaccines; to investigate inherited conditions; and to develop crops with selected traits. Genetically modified crops may be designed for traits such as insect resistance or herbicide tolerance. Outcomes depend on the crop, trait, farming system and local environment. Biotechnology can support food production and health care but does not replace good farming, public-health systems or clinical care.",
                "Benefits, limitations and biosafety" to "Potential benefits include improved crop traits, more consistent production of medicines, faster propagation of useful plants and better diagnostic tools. Limitations include cost, technical requirements, unequal access, unintended effects and public concerns. Biosafety assesses potential risks to human health and the environment, including gene flow, effects on non-target organisms and development of resistance. Decisions should be based on evidence, regulation and transparent assessment rather than unsupported claims.",
                "Ethics, ownership and responsible use" to "Biotechnology raises questions about consent, privacy of genetic information, access to treatments, intellectual property, effects on farmers and communities, animal welfare and environmental protection. Responsible use includes independent risk assessment, monitoring, clear labelling where required, fair access and public engagement. Avoid assuming that every biotechnology application is automatically safe or automatically harmful."
            ),
            "Which method grows many new plants from small pieces of tissue under sterile conditions?",listOf("Natural pollination only", "Plant tissue culture", "Transpiration", "Selective grazing"),1,
            "Plant tissue culture grows cells, tissues or organs on sterile nutrient media and can be used for rapid multiplication through micropropagation."
        ),
        LearningTopic(
            "bio-evolution-theory", "Evolution and its mechanisms", "BIO",
            "Populations change over generations. Bacteria can become resistant to an antibiotic, and populations of animals may become better suited to local conditions when inherited traits affect reproductive success. Evolution explains these changes through processes that alter inherited variation in populations over time.",
            listOf(
                "Define biological evolution",
                "Compare historical Lamarckian and Darwinian explanations",
                "Explain natural selection using heritable variation",
                "Identify mutation, recombination, gene flow and genetic drift as evolutionary processes",
                "Explain how reproductive isolation can contribute to speciation",
                "Distinguish evolution in populations from changes within an individual's lifetime"
            ),
            listOf(
                "What evolution means" to "Biological evolution is change in inherited characteristics of populations across generations. In population genetics, it can be described as a change in allele frequencies over time. Individuals do not evolve genetically during their own lifetime in the population sense; populations evolve as inherited variants become more or less common. Evolution has no predetermined goal and does not mean that every organism becomes more complex or better in every respect.",
                "Lamarck's historical explanation" to "Jean-Baptiste Lamarck proposed that use and disuse could change organs and that acquired characteristics could be inherited. This was an important historical attempt to explain adaptation, but the general claim that changes acquired through use during an individual's lifetime are passed to offspring is not supported as a broad mechanism of evolution. Modern genetics explains inheritance through DNA and the transmission of genetic variants.",
                "Darwin and natural selection" to "Charles Darwin argued that individuals in populations vary, some variation is heritable, and more offspring are produced than can all survive and reproduce. Individuals with heritable traits that improve reproductive success in a particular environment tend to leave more offspring. Over generations, alleles associated with those traits may become more common. Selection acts on individuals, but evolution is measured as change in populations.",
                "A step-by-step example of selection" to "Imagine a population of insects with heritable variation in colour. On a dark background, predators may more easily detect pale insects. If darker insects survive and reproduce more often, alleles associated with darker colour may increase in frequency over generations. The insects do not change colour because they decide to; selection acts on variation already present or arising through mutation and recombination. If the environment changes, the advantage may also change.",
                "Mutation and recombination" to "Mutation creates new DNA variants and is the ultimate source of new alleles. Recombination during meiosis, including crossing over and independent assortment, creates new combinations of existing alleles. These processes generate variation, but they do not occur because an organism needs a particular adaptation. Mutations are not directed towards what would be useful.",
                "Genetic drift and gene flow" to "Genetic drift is random change in allele frequencies, especially in small populations. It can reduce genetic variation and may cause alleles to become fixed or lost by chance. Gene flow occurs when individuals or gametes move between populations and introduce alleles. Drift and gene flow can change populations even when a trait does not improve survival or reproduction.",
                "Natural selection and adaptation" to "An adaptation is an inherited characteristic that improves survival or reproductive success in a particular environment. Natural selection can increase the frequency of such characteristics when the advantage is heritable. A trait may be advantageous in one environment but disadvantageous in another. Selection acts on whole organisms with many interacting traits, so a characteristic can involve trade-offs.",
                "Speciation and reproductive isolation" to "Speciation is the formation of new species. When populations become reproductively isolated, gene flow between them is reduced or stopped. Different mutations, selection pressures and genetic drift can then cause the populations to diverge. Reproductive isolation may arise through geographical separation or differences in behaviour, timing, anatomy or gamete compatibility. Speciation often takes many generations.",
                "How to explain evolution in an examination" to "Use the sequence: heritable variation exists; an environmental factor creates different reproductive success; individuals with certain variants leave more offspring; allele frequencies change over generations. Avoid saying organisms evolve because they try, need or choose to change. Distinguish an individual's acclimatisation from inherited evolutionary change in a population."
            ),
            "Which statement best describes natural selection?",listOf("Individuals develop useful inherited features because they need them", "All mutations are beneficial", "Heritable variants associated with greater reproductive success can become more common over generations", "Evolution always makes organisms more complex"),2,
            "Natural selection changes populations when heritable variants affect reproductive success. It does not require conscious effort or produce a predetermined direction of change."
        ),
        LearningTopic(
            "bio-evolution-evidence", "Evidence and trends in evolution", "BIO",
            "Scientists do not rely on one observation to explain evolution. Fossils, comparative anatomy, embryology, DNA and the geographical distribution of organisms provide different kinds of evidence. When several independent lines of evidence support the same explanation, confidence in common ancestry and evolutionary change becomes stronger.",
            listOf(
                "Explain how fossils provide evidence of past life and change",
                "Distinguish homologous, analogous and vestigial structures",
                "Describe evidence from embryology and molecular biology",
                "Explain how geographical distribution supports evolutionary explanations",
                "Identify broad evolutionary trends without treating evolution as a ladder of progress"
            ),
            listOf(
                "The fossil record" to "Fossils are preserved remains, impressions or traces of organisms from the past. They occur in sedimentary rocks and other materials such as amber or ice. Fossils show that organisms have changed through geological time, that some species became extinct and that different forms existed in different periods. The fossil record is incomplete because fossilisation is uncommon and many fossils have been destroyed, altered or not yet discovered. Rock layers and radiometric dating help scientists estimate the ages of fossils and surrounding rocks.",
                "Transitional fossils and change through time" to "Some fossils show combinations of features found in older and later groups and help document evolutionary transitions. A transitional fossil is not necessarily a direct ancestor; it may be a close relative that shares features with ancestral and descendant lineages. Fossils must be interpreted with geological context and other evidence rather than treated as a simple sequence of every step.",
                "Homologous structures" to "Homologous structures share an underlying anatomical pattern because of common ancestry, even when they perform different functions. The forelimbs of humans, bats, whales and other mammals contain corresponding bones arranged in a similar basic pattern, although they are used for grasping, flying, swimming or walking. Homology supports divergent evolution, in which related lineages become different as they adapt to different conditions.",
                "Analogous and vestigial structures" to "Analogous structures perform similar functions but evolved independently from different ancestral structures; the wings of insects and birds are a familiar example. Similar environmental pressures can produce convergent evolution. Vestigial structures are reduced features inherited from ancestors in which the structure had a different or more prominent function. A vestigial structure may still have a function; 'vestigial' does not always mean completely useless.",
                "Embryological and developmental evidence" to "Comparisons of early development can reveal shared patterns among related groups. Similarities in embryonic structures may reflect common ancestry, although development also changes over evolutionary time and embryos of different species are not identical. Embryological evidence is most useful when interpreted alongside anatomy, fossils and molecular data.",
                "Molecular evidence" to "All known cellular life uses DNA or RNA-based genetic systems and shares fundamental features of the genetic code and cellular machinery. Comparing DNA or protein sequences can reveal degrees of relatedness: closely related species generally have more similar sequences than distantly related species, although genes can evolve at different rates and some sequences are affected by selection or gene transfer. Molecular evidence can be used to construct phylogenetic trees.",
                "Biogeography: where organisms live" to "Biogeography studies the geographical distribution of organisms. Island species may resemble nearby mainland species but show distinctive adaptations after isolation. Similar fossils on continents now separated by oceans can support past geographical connections. Distribution patterns are influenced by continental movement, climate, barriers, dispersal and extinction, and should be interpreted with geological and molecular evidence.",
                "Broad evolutionary trends in plants and animals" to "Plant evolution includes the development of vascular tissues, seeds and flowers in different lineages. These features changed how plants transport water, reproduce and disperse offspring, but modern plant groups are not a simple ladder from 'primitive' to 'advanced'. Animal lineages show diverse changes in body symmetry, segmentation, skeletons, specialised organs and reproductive strategies. Evolution produces branching lineages, not a single march towards perfection.",
                "How to evaluate evolutionary evidence" to "Identify what is being compared, state the observed similarity or difference, and explain how it supports or limits an evolutionary interpretation. Similar bone patterns may support homology; similar functions with different underlying structures may indicate analogy; DNA similarity can support common ancestry. No single feature should be used carelessly to claim a direct ancestor. Strong explanations combine independent evidence and acknowledge uncertainty where appropriate."
            ),
            "The forelimbs of a human, bat and whale have a similar underlying bone arrangement but different functions. They are examples of",listOf("analogous structures only", "unrelated structures", "artificial selection", "homologous structures"),3,
            "The shared underlying bone pattern supports common ancestry, even though the forelimbs are adapted for different functions. This is homology."
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
    ) + ChemistryCourse.topics + PhysicsCourse.topics + MathematicsCourse.topics

    fun forSubject(code: String): List<LearningTopic> = when (code) {
        "CHEM" -> ChemistryCourse.topics
        "PHY" -> PhysicsCourse.topics
        else -> topics.filter { it.subjectCode == code }
    }

    fun find(topicId: String): LearningTopic? =
        topics.firstOrNull { it.id == topicId }
            ?: ChemistryCourse.topics.firstOrNull { it.id == topicId }
            ?: PhysicsCourse.topics.firstOrNull { it.id == topicId }
}

@Composable
fun LessonScreen(topicId: String) {
    val topic = LearningCatalog.find(topicId)
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var completed by remember(topicId) { mutableStateOf(database.isLessonCompleted(topicId)) }
    var bookmarked by remember(topicId) { mutableStateOf(database.isBookmarked(topicId)) }
    val noteId = "lesson-note-$topicId"
    var noteText by remember(topicId) { mutableStateOf(database.getNoteBody(noteId).orEmpty()) }
    var noteSaved by remember(topicId) { mutableStateOf(false) }
    var selected by remember(topicId) { mutableIntStateOf(-1) }
    var checked by remember(topicId) { mutableStateOf(false) }

    var speechReady by remember(topicId) { mutableStateOf(false) }
    var speechEngine by remember(topicId) { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(topicId, context) {
        val engine = TextToSpeech(context.applicationContext) { status ->
            speechReady = status == TextToSpeech.SUCCESS
            if (status == TextToSpeech.SUCCESS) speechEngine?.language = Locale.UK
        }
        speechEngine = engine
        onDispose {
            engine.stop()
            engine.shutdown()
            speechEngine = null
            speechReady = false
        }
    }

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

        BiologyInteractiveLessonActivity(topicId)
        ChemistryInteractiveLessonActivity(topicId)
        ChemistrySupplementalLessonActivity(topicId)
        if (topicId == "chem-kinetic-gas") ParticleKineticAnimation(temperatureKelvin = 300f)
        PhysicsInteractiveLessonActivity(topicId)
        PhysicsSupplementalLessonActivity(topicId)
        GuidedInvestigationActivity(topicId)
        WaecBiologyNigeriaSupplementalActivity(topicId)
        WaecPhysicsSupplementalActivity(topicId)
        WaecChemistrySupplementalActivity(topicId)

        ScienceMediaTopicSection(topicId)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    val narration = buildString {
                        append(topic.title).append(". ")
                        append(topic.introduction).append(". ")
                        topic.objectives.forEach { append(it).append(". ") }
                        topic.sections.forEach { append(it.first).append(". ").append(it.second).append(". ") }
                        append("Check your understanding. ").append(topic.checkQuestion)
                    }
                    speechEngine?.language = Locale.UK
                    speechEngine?.speak(narration, TextToSpeech.QUEUE_FLUSH, null, "lesson-${topic.id}")
                },
                enabled = speechReady,
                modifier = Modifier.weight(1f)
            ) { Text(if (speechReady) "Listen to lesson" else "Preparing audio…") }
            OutlinedButton(onClick = { speechEngine?.stop() }, enabled = speechReady) {
                Text("Stop")
            }
        }
        Text(
            "Audio uses your device's text-to-speech voice and works offline when a compatible voice is installed.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedButton(
            onClick = {
                if (bookmarked) database.removeBookmark(topic.id)
                else database.bookmark(topic.id, "lesson")
                bookmarked = !bookmarked
            }
        ) { Text(if (bookmarked) "Saved to bookmarks" else "Save this lesson") }
        when (topic.id) {
            "bio-adaptation" -> AdaptationTypesCard()
            "bio-cell" -> CellDiagramCard()
            "bio-nutrition" -> PhotosynthesisFlowCard()
            "bio-respiration" -> RespirationComparisonCard()
            "bio-interactions" -> FoodChainDiagramCard()
            "bio-populations" -> {
                BiologyPopulationSamplingCard()
                EcologicalSuccessionCard()
            }
            "bio-heredity" -> PunnettSquareCard()
            "bio-transport" -> {
                PlantTransportCard()
                DoubleCirculationCard()
            }
            "bio-excretion" -> NephronFlowCard()
            "bio-soil" -> SoilWaterInvestigationCard()
            "bio-reproduction" -> FlowerReproductionCard()
            "bio-evolution-theory" -> NaturalSelectionCard()
        }
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
        if (topic.id == "bio-growth") {
            CellDivisionLessonCard()
            CellDivisionKnowledgeCheckCard()
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("My lesson notes", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Write your own summary, questions or examples. Notes are saved locally on this device.")
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it; noteSaved = false },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 8,
                    label = { Text("Your notes for this topic") }
                )
                Button(
                    onClick = {
                        database.saveNote(noteId, topic.id, "${topic.title} notes", noteText)
                        noteSaved = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Save lesson notes") }
                if (noteSaved) Text("Notes saved on this device.", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
            }
        }
        HorizontalDivider()
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
            enabled = !topic.isSyllabusOutline && !completed && checked && selected == topic.correctOption,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (topic.isSyllabusOutline) "Lesson in development" else if (completed) "Lesson completed" else "Complete lesson")
        }
        if (!completed) {
            Text(
                "To complete this lesson, answer the understanding check correctly. You can retry after reading the explanation.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            when {
                topic.isSyllabusOutline -> "Academic status: syllabus outline only. Full lesson development is in progress; this outline cannot be marked as a completed lesson."
                topic.subjectCode == "BIO" -> "Academic status: beginner-focused Biology teaching draft mapped to its syllabus topic. Independent subject review is still required before formal approval."
                topic.subjectCode == "PHY" -> "Academic status: beginner-focused Physics teaching draft mapped to the JAMB IBASS topic. Independent Physics-teacher review is still required before formal approval."
                else -> "Academic status: starter teaching content. Full syllabus mapping and independent subject review are still required."
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
