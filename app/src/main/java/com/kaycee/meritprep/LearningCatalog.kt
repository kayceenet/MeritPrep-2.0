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
            "Which option is a behavioural adaptation that may help an animal avoid extreme daytime heat?",
            listOf("Being active mainly at night","Having a streamlined body","Having a thick layer of fat","Producing concentrated urine"), 0,
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
            "Which plant tissue transports water and dissolved mineral ions mainly from the roots towards the leaves?",
            listOf("Xylem","Phloem","Epidermis","Palisade mesophyll"), 0,
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
            "Which statement about bile is correct?",
            listOf("It emulsifies fats but is not a digestive enzyme","It digests proteins into amino acids","It converts glucose into starch","It is produced by the pancreas"), 0,
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
            "Which process moves mineral ions into root hair cells against a concentration gradient using energy?",
            listOf("Active transport","Diffusion","Osmosis","Transpiration"), 0,
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
            "Which statement correctly describes cellular respiration?",
            listOf("It is a set of cellular chemical reactions that transfers energy from food molecules to ATP","It is simply the movement of air into the lungs","It occurs only in the lungs","It is the same as photosynthesis"), 0,
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
            "Which process forces water and small solutes from glomerular blood into Bowman's capsule?",
            listOf("Ultrafiltration","Egestion","Translocation","Ventilation"), 0,
            "Ultrafiltration is pressure-driven filtration at the glomerulus. Blood cells and most large plasma proteins remain in the blood, while water and small solutes enter Bowman's capsule."
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
