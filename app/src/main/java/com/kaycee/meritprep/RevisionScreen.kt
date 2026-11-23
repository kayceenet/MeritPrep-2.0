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
import com.kaycee.meritprep.data.RevisionCard
import com.kaycee.meritprep.data.ReviewRating
import com.kaycee.meritprep.data.SpacedRepetitionScheduler

private val starterBiologyCards = listOf(
    "A cell is the basic structural and functional unit of life." to "A cell is the smallest unit that can perform the processes associated with life.",
    "State the three main ideas of cell theory." to "Living organisms are made of cells; the cell is the basic unit of structure and function; new cells arise from pre-existing cells.",
    "What is the main function of the cell-surface membrane?" to "It forms a selectively permeable boundary that regulates movement of substances into and out of the cell.",
    "Which organelle is the main site of aerobic respiration in eukaryotic cells?" to "The mitochondrion. Many stages of aerobic respiration occur there; glycolysis occurs in the cytosol.",
    "What is the function of ribosomes?" to "Ribosomes are the sites of protein synthesis (translation).",
    "What is the role of chloroplasts in green plant cells?" to "Chloroplasts contain chlorophyll and are the main sites of photosynthesis.",
    "What is the function of the plant cell wall?" to "The cellulose cell wall provides support, helps maintain shape and resists excessive expansion when water enters.",
    "Give one key difference between prokaryotic and eukaryotic cells." to "Eukaryotic cells have a membrane-bound nucleus; prokaryotic cells do not.",
    "What is a tissue?" to "A tissue is a group of similar cells working together to perform a particular function.",
    "Arrange these levels from simplest to most complex: organ, cell, organism, tissue, organ system." to "Cell → tissue → organ → organ system → organism.",
    "Why do root hair cells have a long projection?" to "It increases surface area for absorption of water and mineral ions from the soil.",
    "Which structure controls many activities of a typical eukaryotic cell?" to "The nucleus contains most of the cell's genetic material and regulates gene expression; not all cells have a nucleus."
).mapIndexed { index, pair ->
    RevisionCard(
        cardId = "bio-cell-card-${index + 1}",
        topicId = "bio-cell",
        front = pair.first,
        back = pair.second,
        dueAt = 0L
    )
} + listOf(
    RevisionCard("bio-class-card-1","bio-classification","What is the difference between classification and identification?","Classification arranges organisms into groups; identification determines which organism has been observed, often using a key.",0L),
    RevisionCard("bio-class-card-2","bio-classification","State the taxonomic ranks from broadest to most specific.","Kingdom → Phylum (or Division in plants) → Class → Order → Family → Genus → Species.",0L),
    RevisionCard("bio-class-card-3","bio-classification","How is a scientific species name written?","Use two words: Genus with an initial capital, followed by the lower-case specific epithet. Italicise both when typed, e.g. Homo sapiens.",0L),
    RevisionCard("bio-class-card-4","bio-classification","What feature separates prokaryotic from eukaryotic cells?","Prokaryotic cells lack a membrane-bound nucleus; eukaryotic cells have one.",0L),
    RevisionCard("bio-class-card-5","bio-classification","Why are fungi not classified as green plants?","Fungi obtain nutrients by absorption and do not photosynthesise; their cell walls commonly contain chitin rather than cellulose.",0L),
    RevisionCard("bio-class-card-6","bio-classification","Why are viruses excluded from the five traditional kingdoms?","Viruses are acellular and replicate only inside suitable host cells; they are not cellular organisms.",0L),
    RevisionCard("bio-class-card-7","bio-classification","Which plant group has vascular tissue but reproduces by spores rather than seeds?","Ferns have vascular tissue but do not produce seeds.",0L),
    RevisionCard("bio-class-card-8","bio-classification","What is the purpose of a dichotomous key?","It guides identification through a sequence of paired contrasting statements based on observable features.",0L),
    RevisionCard("bio-adapt-card-1","bio-adaptation","Define adaptation.","An inherited characteristic that can improve survival and reproductive success in a particular environment.",0L),
    RevisionCard("bio-adapt-card-2","bio-adaptation","Name the three broad types of adaptation.","Structural (physical features), physiological (internal functions) and behavioural (patterns of activity).",0L),
    RevisionCard("bio-adapt-card-3","bio-adaptation","How does a streamlined body help a fish?","It reduces resistance or drag as the fish moves through water.",0L),
    RevisionCard("bio-adapt-card-4","bio-adaptation","How do root or stem features of xerophytes help conserve water?","Reduced leaf area, thick cuticles and water-storing tissues can reduce water loss or store water; the exact features vary by species.",0L),
    RevisionCard("bio-adapt-card-5","bio-adaptation","What are pneumatophores?","Aerial roots in some mangroves that project above waterlogged sediment and assist gas exchange.",0L),
    RevisionCard("bio-adapt-card-6","bio-adaptation","Distinguish adaptation from acclimatisation.","Adaptation is inherited across generations; acclimatisation is an adjustment within an individual's lifetime.",0L),
    RevisionCard("bio-adapt-card-7","bio-adaptation","What is camouflage?","A feature or pattern that makes an organism less conspicuous against its surroundings.",0L),
    RevisionCard("bio-adapt-card-8","bio-adaptation","Why can nocturnal activity be advantageous in hot environments?","It can reduce exposure to extreme daytime heat and may reduce water loss or heat stress.",0L),
    RevisionCard("bio-plant-card-1","bio-plant-anatomy","What are the three main tissue systems in flowering plants?","Dermal (epidermal), ground and vascular tissue systems.",0L),
    RevisionCard("bio-plant-card-2","bio-plant-anatomy","What is the function of root hairs?","They increase the surface area for absorption of water and mineral ions from the soil.",0L),
    RevisionCard("bio-plant-card-3","bio-plant-anatomy","What does xylem transport?","Water and dissolved mineral ions, mainly from roots towards aerial parts of the plant.",0L),
    RevisionCard("bio-plant-card-4","bio-plant-anatomy","What does phloem transport?","Organic substances such as sucrose between sources and sinks; movement may occur in different directions in different sieve tubes.",0L),
    RevisionCard("bio-plant-card-5","bio-plant-anatomy","What is the role of guard cells?","They regulate the opening and closing of stomata by changes in their turgor.",0L),
    RevisionCard("bio-plant-card-6","bio-plant-anatomy","Why is palisade mesophyll important?","It commonly contains many chloroplasts and is a major site of photosynthesis in a typical leaf.",0L),
    RevisionCard("bio-plant-card-7","bio-plant-anatomy","Why are xylem walls often lignified?","Lignin strengthens the walls and helps prevent collapse during water transport.",0L),
    RevisionCard("bio-plant-card-8","bio-plant-anatomy","How do alveoli support gas exchange?","They provide a large moist surface with thin walls and a rich capillary supply, supporting rapid diffusion.",0L),
    RevisionCard("bio-nut-card-1","bio-nutrition","Write the simplified balanced equation for photosynthesis.","6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂, in the presence of light and chlorophyll.",0L),
    RevisionCard("bio-nut-card-2","bio-nutrition","Name four factors that can affect photosynthesis rate.","Light intensity, carbon dioxide concentration, temperature and water availability; chlorophyll content also matters.",0L),
    RevisionCard("bio-nut-card-3","bio-nutrition","What is the role of magnesium in plants?","Magnesium is a component of the chlorophyll molecule.",0L),
    RevisionCard("bio-nut-card-4","bio-nutrition","What is the role of nitrate ions in plant nutrition?","They provide nitrogen for synthesising amino acids and proteins.",0L),
    RevisionCard("bio-nut-card-5","bio-nutrition","What are the main functions of proteins in the diet?","They provide amino acids for growth, tissue repair, enzymes and other body molecules.",0L),
    RevisionCard("bio-nut-card-6","bio-nutrition","Is bile an enzyme? What does it do?","No. Bile emulsifies fats into smaller droplets and helps neutralise acidic chyme.",0L),
    RevisionCard("bio-nut-card-7","bio-nutrition","Where does most nutrient absorption occur?","In the small intestine, whose folds, villi and microvilli provide a large surface area.",0L),
    RevisionCard("bio-nut-card-8","bio-nutrition","What is assimilation?","The use or incorporation of absorbed nutrients by cells and tissues, such as using amino acids to build proteins.",0L)
) + listOf(
    RevisionCard("bio-trans-card-1","bio-transport","Define diffusion.","The net movement of particles from higher concentration to lower concentration due to random motion.",0L),
    RevisionCard("bio-trans-card-2","bio-transport","Define osmosis.","The net movement of water across a selectively permeable membrane from higher to lower water potential.",0L),
    RevisionCard("bio-trans-card-3","bio-transport","How does active transport differ from diffusion?","Active transport uses energy and transport proteins to move substances against a concentration or electrochemical gradient; diffusion is passive and moves down a concentration gradient.",0L),
    RevisionCard("bio-trans-card-4","bio-transport","What is the role of xylem?","It transports water and dissolved mineral ions mainly from roots towards leaves and also provides support.",0L),
    RevisionCard("bio-trans-card-5","bio-transport","What is transpiration?","Loss of water vapour from aerial parts of a plant, mainly through stomata.",0L),
    RevisionCard("bio-trans-card-6","bio-transport","What is translocation?","Movement of organic assimilates, especially sucrose, through phloem from sources to sinks.",0L),
    RevisionCard("bio-trans-card-7","bio-transport","What is the function of haemoglobin?","It binds oxygen reversibly and transports most oxygen in the blood within red blood cells.",0L),
    RevisionCard("bio-trans-card-8","bio-transport","How do arteries differ from veins?","Arteries carry blood away from the heart and have thick elastic walls; veins return blood to the heart and often contain valves.",0L),
    RevisionCard("bio-trans-card-9","bio-transport","What is the role of capillaries?","Their thin walls allow exchange of gases, nutrients and wastes between blood and tissues.",0L),
    RevisionCard("bio-trans-card-10","bio-transport","What are the two circuits in human double circulation?","Pulmonary circulation is between heart and lungs; systemic circulation is between heart and the rest of the body.",0L)
) + listOf(
    RevisionCard("bio-resp-card-1","bio-respiration","Distinguish breathing from cellular respiration.","Breathing is ventilation, the movement of air into and out of lungs. Cellular respiration is a chemical process in cells that transfers energy from food molecules to ATP.",0L),
    RevisionCard("bio-resp-card-2","bio-respiration","Write the simplified equation for aerobic respiration.","Glucose + oxygen → carbon dioxide + water + energy transferred to ATP and released as heat.",0L),
    RevisionCard("bio-resp-card-3","bio-respiration","Where does glycolysis occur in eukaryotic cells?","In the cytosol.",0L),
    RevisionCard("bio-resp-card-4","bio-respiration","What is produced by anaerobic metabolism in human skeletal muscle?","Lactate is produced from pyruvate, allowing glycolysis to continue when oxygen delivery is insufficient for demand.",0L),
    RevisionCard("bio-resp-card-5","bio-respiration","What are the products of alcoholic fermentation in yeast?","Ethanol and carbon dioxide.",0L),
    RevisionCard("bio-resp-card-6","bio-respiration","Give three features of alveoli that support gas exchange.","Large combined surface area, thin moist walls and a rich capillary supply.",0L),
    RevisionCard("bio-resp-card-7","bio-respiration","What happens to the diaphragm during inspiration?","It contracts and flattens, increasing thoracic volume and helping lower pressure in the lungs.",0L),
    RevisionCard("bio-resp-card-8","bio-respiration","How is most oxygen transported in human blood?","Bound reversibly to haemoglobin in red blood cells.",0L)
) + listOf(
    RevisionCard("bio-exc-card-1","bio-excretion","Distinguish excretion from egestion.","Excretion removes metabolic waste products; egestion removes undigested material from the alimentary canal.",0L),
    RevisionCard("bio-exc-card-2","bio-excretion","Where is urea formed in mammals?","In the liver, mainly through conversion of ammonia generated during amino-acid breakdown.",0L),
    RevisionCard("bio-exc-card-3","bio-excretion","What is the function of a contractile vacuole in a freshwater protist?","It collects and expels excess water to help maintain osmotic balance.",0L),
    RevisionCard("bio-exc-card-4","bio-excretion","What is the functional unit of the kidney?","The nephron.",0L),
    RevisionCard("bio-exc-card-5","bio-excretion","Define ultrafiltration in the kidney.","Pressure-driven filtration at the glomerulus that moves water and small solutes into Bowman's capsule while retaining blood cells and most large proteins.",0L),
    RevisionCard("bio-exc-card-6","bio-excretion","Where is most filtered glucose reabsorbed?","In the proximal convoluted tubule, under normal blood-glucose conditions.",0L),
    RevisionCard("bio-exc-card-7","bio-excretion","How does ADH affect urine concentration?","It increases collecting-duct water permeability, so more water is reabsorbed and urine becomes more concentrated.",0L),
    RevisionCard("bio-exc-card-8","bio-excretion","Which nitrogenous waste do birds commonly excrete to conserve water?","Uric acid.",0L)
) + listOf(
    RevisionCard("bio-sup-card-1","bio-support","Name three plant tissues that contribute to support.","Collenchyma provides flexible support; sclerenchyma provides strength; xylem also contributes support. Turgor in living cells supports soft tissues.",0L),
    RevisionCard("bio-sup-card-2","bio-support","Define a tropism.","A directional growth response of a plant to a stimulus.",0L),
    RevisionCard("bio-sup-card-3","bio-support","What is positive phototropism?","Growth towards a light source, commonly shown by shoots.",0L),
    RevisionCard("bio-sup-card-4","bio-support","What is an exoskeleton?","A rigid external skeleton, as in insects, that protects the body and provides muscle attachment.",0L),
    RevisionCard("bio-sup-card-5","bio-support","What is a hydrostatic skeleton?","Support and movement produced by fluid pressure within a body cavity acting with surrounding muscles.",0L),
    RevisionCard("bio-sup-card-6","bio-support","Distinguish a ligament from a tendon.","A ligament connects bone to bone; a tendon connects muscle to bone.",0L),
    RevisionCard("bio-sup-card-7","bio-support","How do biceps and triceps move the elbow?","They act as an antagonistic pair: biceps contraction flexes the elbow; triceps contraction extends it.",0L),
    RevisionCard("bio-sup-card-8","bio-support","What is included in the axial skeleton?","The skull, vertebral column and rib cage.",0L)
) + listOf(
    RevisionCard("bio-rep-card-1","bio-reproduction","Distinguish sexual from asexual reproduction.","Sexual reproduction involves fusion of gametes and usually produces genetic variation. Asexual reproduction usually involves one parent and no gamete fusion.",0L),
    RevisionCard("bio-rep-card-2","bio-reproduction","Name three examples of asexual reproduction.","Binary fission, budding and vegetative propagation are examples; spore formation and fragmentation also occur.",0L),
    RevisionCard("bio-rep-card-3","bio-reproduction","What is the chromosome status of gametes and the zygote?","Gametes are haploid; fertilisation forms a diploid zygote.",0L),
    RevisionCard("bio-rep-card-4","bio-reproduction","Name the parts of a typical stamen.","Anther and filament.",0L),
    RevisionCard("bio-rep-card-5","bio-reproduction","Define pollination.","Transfer of pollen from anther to stigma.",0L),
    RevisionCard("bio-rep-card-6","bio-reproduction","What do the ovule and ovary commonly develop into after fertilisation?","The ovule develops into a seed; the ovary commonly develops into a fruit.",0L),
    RevisionCard("bio-rep-card-7","bio-reproduction","Where does fertilisation usually occur in humans?","In the oviduct (fallopian tube).",0L),
    RevisionCard("bio-rep-card-8","bio-reproduction","What is the role of the placenta?","It allows exchange of gases, nutrients and wastes between maternal and foetal circulations, which normally remain separate.",0L)
) + listOf(
    RevisionCard("bio-grow-card-1","bio-growth","Distinguish growth from development.","Growth is a relatively permanent increase in size, dry mass or cell number. Development includes changes in structure and function, including differentiation.",0L),
    RevisionCard("bio-grow-card-2","bio-growth","Name three processes involved in growth.","Cell division, cell enlargement and cell differentiation.",0L),
    RevisionCard("bio-grow-card-3","bio-growth","Where does primary growth occur in plants?","At apical meristems near root and shoot tips.",0L),
    RevisionCard("bio-grow-card-4","bio-growth","What three conditions are generally needed for seed germination?","Water, oxygen and a suitable temperature.",0L),
    RevisionCard("bio-grow-card-5","bio-growth","What is imbibition?","The uptake of water by a dry seed at the start of germination.",0L),
    RevisionCard("bio-grow-card-6","bio-growth","Distinguish epigeal from hypogeal germination.","Epigeal: cotyledons are raised above soil. Hypogeal: cotyledons remain below soil.",0L),
    RevisionCard("bio-grow-card-7","bio-growth","What is one role of auxin?","Auxin influences cell elongation, apical dominance and tropic growth responses.",0L),
    RevisionCard("bio-grow-card-8","bio-growth","Which plant hormone is associated with fruit ripening?","Ethylene, a gaseous plant hormone.",0L)
) + listOf(
    RevisionCard("bio-coord-card-1","bio-coordination","Define homeostasis.","Regulation of internal conditions within suitable limits despite internal or external changes.",0L),
    RevisionCard("bio-coord-card-2","bio-coordination","What makes up the central nervous system?","The brain and spinal cord.",0L),
    RevisionCard("bio-coord-card-3","bio-coordination","Name the parts of a simple reflex arc in order.","Receptor → sensory neurone → relay neurone → motor neurone → effector.",0L),
    RevisionCard("bio-coord-card-4","bio-coordination","Distinguish nervous from hormonal coordination.","Nervous coordination uses impulses along neurones and is often rapid and targeted; hormonal coordination uses hormones carried in blood to target cells.",0L),
    RevisionCard("bio-coord-card-5","bio-coordination","What is the effect of insulin on blood glucose?","Insulin promotes glucose uptake and storage, lowering blood glucose.",0L),
    RevisionCard("bio-coord-card-6","bio-coordination","What is the effect of glucagon on blood glucose?","Glucagon promotes breakdown of liver glycogen and release of glucose, raising blood glucose.",0L),
    RevisionCard("bio-coord-card-7","bio-coordination","How does sweating cool the body?","Evaporation of sweat from the skin removes heat from the body.",0L),
    RevisionCard("bio-coord-card-8","bio-coordination","How does ADH help regulate water balance?","It increases water reabsorption in kidney collecting ducts, producing more concentrated urine.",0L)
) + listOf(
    RevisionCard("bio-eco-f-card-1","bio-ecology-factors","Define habitat.","The place where an organism lives.",0L),
    RevisionCard("bio-eco-f-card-2","bio-ecology-factors","Distinguish population from community.","A population is members of one species in an area; a community includes populations of different species in that area.",0L),
    RevisionCard("bio-eco-f-card-3","bio-ecology-factors","Give four abiotic factors.","Examples include temperature, light intensity, rainfall, humidity, pH, salinity, wind and soil properties.",0L),
    RevisionCard("bio-eco-f-card-4","bio-ecology-factors","Give three examples of biotic interactions.","Competition, predation and parasitism; mutualism and commensalism are other examples.",0L),
    RevisionCard("bio-eco-f-card-5","bio-ecology-factors","Which instrument measures relative humidity?","A hygrometer.",0L),
    RevisionCard("bio-eco-f-card-6","bio-ecology-factors","Which instrument measures wind speed?","An anemometer.",0L),
    RevisionCard("bio-eco-f-card-7","bio-ecology-factors","What is a limiting factor?","A condition or resource that restricts growth, survival or distribution when it is scarce or unsuitable.",0L),
    RevisionCard("bio-eco-f-card-8","bio-ecology-factors","Why should ecological measurements be repeated?","To capture variation across locations and times and avoid relying on an unrepresentative reading.",0L)
) + listOf(
    RevisionCard("bio-eco-i-card-1","bio-interactions","What is a producer?","An organism such as a green plant or alga that makes organic substances using light energy in photosynthesis.",0L),
    RevisionCard("bio-eco-i-card-2","bio-interactions","What is a trophic level?","A feeding position in a food chain or web, such as producer, primary consumer or secondary consumer.",0L),
    RevisionCard("bio-eco-i-card-3","bio-interactions","What does an arrow in a food chain show?","The direction of energy transfer from the organism being eaten to the organism that eats it.",0L),
    RevisionCard("bio-eco-i-card-4","bio-interactions","Why does energy decrease along a food chain?","Energy is lost in respiration and heat, waste and uneaten material; not all biomass is transferred to the next level.",0L),
    RevisionCard("bio-eco-i-card-5","bio-interactions","Distinguish mutualism from parasitism.","Mutualism benefits both partners; parasitism benefits one organism and harms the host.",0L),
    RevisionCard("bio-eco-i-card-6","bio-interactions","What is the role of decomposers?","They break down dead organic matter and wastes, returning nutrients to the environment.",0L),
    RevisionCard("bio-eco-i-card-7","bio-interactions","What is nitrogen fixation?","Conversion of atmospheric nitrogen gas into ammonia or related biologically available nitrogen compounds, commonly by bacteria.",0L),
    RevisionCard("bio-eco-i-card-8","bio-interactions","What is denitrification?","Conversion of nitrate to nitrogen gas by denitrifying bacteria, returning nitrogen to the atmosphere.",0L)
) + listOf(
    RevisionCard("bio-hab-card-1","bio-habitats","Define habitat.","The place where an organism lives.",0L),
    RevisionCard("bio-hab-card-2","bio-habitats","What is an ecological niche?","The role and way of life of an organism, including its resource use, tolerances and interactions.",0L),
    RevisionCard("bio-hab-card-3","bio-habitats","What does arboreal mean?","Living mainly in trees or shrubs.",0L),
    RevisionCard("bio-hab-card-4","bio-habitats","What is a microhabitat?","A small, distinct part of a larger habitat with its own local conditions, such as leaf litter or a tree-hole.",0L),
    RevisionCard("bio-hab-card-5","bio-habitats","Name three major aquatic habitat types.","Freshwater, marine and brackish habitats.",0L),
    RevisionCard("bio-hab-card-6","bio-habitats","What is quadrat sampling used for?","Estimating abundance or distribution of suitable stationary organisms in a defined area.",0L),
    RevisionCard("bio-hab-card-7","bio-habitats","What is a transect used for?","Recording changes in organisms or environmental conditions along a line or gradient.",0L),
    RevisionCard("bio-hab-card-8","bio-habitats","Give two ways humans can fragment habitats.","Road construction and urban or agricultural development can divide continuous habitat into separated patches.",0L)
) + listOf(
    RevisionCard("bio-biome-card-1","bio-biomes","What is a biome?","A large ecological region associated with a characteristic climate and dominant vegetation, with its associated animal communities.",0L),
    RevisionCard("bio-biome-card-2","bio-biomes","Name three broad Nigerian ecological environments.","Examples include tropical rainforest, savanna and mangrove or freshwater swamp; montane environments also occur at higher elevations.",0L),
    RevisionCard("bio-biome-card-3","bio-biomes","What characterises tropical rainforest?","High rainfall, layered vegetation, tall broad-leaved trees and high biological diversity.",0L),
    RevisionCard("bio-biome-card-4","bio-biomes","Where are mangroves found in Nigeria?","In tidal coastal and estuarine environments, including parts of the Niger Delta.",0L),
    RevisionCard("bio-biome-card-5","bio-biomes","How does Guinea savanna differ from Sudan savanna?","Guinea savanna is generally wetter with taller grasses and more scattered trees; Sudan savanna is generally drier with shorter grasses and more widely spaced trees.",0L),
    RevisionCard("bio-biome-card-6","bio-biomes","What is the Sahel?","A semi-arid transition with lower and less reliable rainfall and relatively sparse vegetation.",0L),
    RevisionCard("bio-biome-card-7","bio-biomes","Name two Nigerian montane areas.","The Mambilla Plateau and Obudu highlands are examples.",0L),
    RevisionCard("bio-biome-card-8","bio-biomes","Why are biome boundaries often gradual?","Rainfall, soil, altitude, land use and other local conditions vary continuously, creating transition zones.",0L)
) + listOf(
    RevisionCard("bio-pop-card-1","bio-populations","Define population density.","The number of individuals per unit area or volume.",0L),
    RevisionCard("bio-pop-card-2","bio-populations","Which processes add individuals to a population?","Births and immigration.",0L),
    RevisionCard("bio-pop-card-3","bio-populations","What is carrying capacity?","The population size an environment can support over time under prevailing conditions.",0L),
    RevisionCard("bio-pop-card-4","bio-populations","What shape is associated with exponential population growth?","A J-shaped curve under idealised conditions.",0L),
    RevisionCard("bio-pop-card-5","bio-populations","What shape is associated with logistic growth?","An S-shaped curve as growth slows near carrying capacity.",0L),
    RevisionCard("bio-pop-card-6","bio-populations","Distinguish primary from secondary succession.","Primary succession begins where soil is absent or poorly developed; secondary succession occurs where soil remains after disturbance.",0L),
    RevisionCard("bio-pop-card-7","bio-populations","What is a pioneer species?","An early coloniser of a newly available or disturbed habitat during succession.",0L),
    RevisionCard("bio-pop-card-8","bio-populations","Calculate density for 240 seedlings in 60 m².","240 ÷ 60 = 4 seedlings per square metre.",0L)
) + listOf(
    RevisionCard("bio-soil-card-1","bio-soil","Name the main components of soil.","Mineral particles, organic matter, water, air and living organisms.",0L),
    RevisionCard("bio-soil-card-2","bio-soil","How does sandy soil generally behave when water is added?","Its relatively large particles and pore spaces allow water to drain quickly.",0L),
    RevisionCard("bio-soil-card-3","bio-soil","What is soil porosity?","The proportion of soil volume occupied by pore spaces.",0L),
    RevisionCard("bio-soil-card-4","bio-soil","What is soil permeability?","How easily water or air passes through connected soil pores.",0L),
    RevisionCard("bio-soil-card-5","bio-soil","What is humus?","Relatively stable organic matter formed during decomposition.",0L),
    RevisionCard("bio-soil-card-6","bio-soil","What does a soil pH measurement indicate?","How acidic or alkaline the soil solution is.",0L),
    RevisionCard("bio-soil-card-7","bio-soil","Name two soil conservation practices.","Examples include cover crops, mulching, contour farming, terracing, windbreaks and controlled grazing.",0L),
    RevisionCard("bio-soil-card-8","bio-soil","How do earthworms benefit soil?","They mix soil and create channels that can improve aeration, drainage and structure.",0L)
) + listOf(
    RevisionCard("bio-env-card-1","bio-environment","Distinguish communicable from non-communicable disease.","Communicable diseases are infectious and can spread between hosts; non-communicable diseases are not transmitted person to person.",0L),
    RevisionCard("bio-env-card-2","bio-environment","What organism transmits human malaria?","Female Anopheles mosquitoes transmit Plasmodium parasites.",0L),
    RevisionCard("bio-env-card-3","bio-environment","Name three ways to reduce water-borne disease transmission.","Safe drinking water, proper sanitation and handwashing with soap; food hygiene also helps.",0L),
    RevisionCard("bio-env-card-4","bio-environment","How do vaccines help protect against infection?","They stimulate immune responses and immune memory against specific pathogens or antigens.",0L),
    RevisionCard("bio-env-card-5","bio-environment","Why do antibiotics not treat influenza?","Influenza is caused by a virus; antibiotics act against bacteria, not viruses.",0L),
    RevisionCard("bio-env-card-6","bio-environment","What is antimicrobial resistance?","A condition in which microorganisms are no longer effectively inhibited or killed by medicines that previously worked against them.",0L),
    RevisionCard("bio-env-card-7","bio-environment","Name three types of pollution.","Air, water, soil and noise pollution are examples.",0L),
    RevisionCard("bio-env-card-8","bio-environment","Give two ways to conserve biodiversity.","Protect and restore habitats; use natural resources sustainably and reduce pollution.",0L)
)

@Composable
fun RevisionScreen() {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var dueCards by remember { mutableStateOf(emptyList<RevisionCard>()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var revealed by remember { mutableStateOf(false) }
    var reviewedCount by remember { mutableIntStateOf(0) }

    fun refresh() {
        database.seedRevisionCards(starterBiologyCards)
        dueCards = database.getDueRevisionCards()
        currentIndex = 0
        revealed = false
    }
    LaunchedEffect(database) { refresh() }

    val current = dueCards.getOrNull(currentIndex)
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Spaced revision", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Recall first. Reveal the explanation. Then rate how difficult it was to remember.")
        Text("Due now: ${dueCards.size}   ·   Reviewed this session: $reviewedCount", style = MaterialTheme.typography.labelLarge)
        if (current == null) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(if (dueCards.isEmpty()) "You are up to date" else "Review session complete", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(if (dueCards.isEmpty()) "No cards are due right now. Your next review will appear when its scheduled time arrives." else "You have completed the cards currently due in this session.")
                    OutlinedButton(onClick = { refresh() }) { Text("Refresh due cards") }
                }
            }
        } else {
            Text("Card ${currentIndex + 1} of ${dueCards.size}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text("RECALL", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text(current.front, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    if (revealed) {
                        HorizontalDivider()
                        Text("ANSWER", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                        Text(current.back, style = MaterialTheme.typography.bodyLarge)
                    } else {
                        Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth()) { Text("Reveal answer") }
                    }
                }
            }
            if (revealed) {
                Text("How well did you recall it?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                listOf(
                    ReviewRating.AGAIN to ("Again" to "Forgot or could not recall"),
                    ReviewRating.HARD to ("Hard" to "Remembered with difficulty"),
                    ReviewRating.GOOD to ("Good" to "Recalled correctly"),
                    ReviewRating.EASY to ("Easy" to "Recalled quickly and confidently")
                ).forEach { (rating, copy) ->
                    OutlinedButton(
                        onClick = {
                            val updated = SpacedRepetitionScheduler.schedule(current, rating, System.currentTimeMillis())
                            database.reviewRevisionCard(updated)
                            reviewedCount++
                            dueCards = dueCards.toMutableList().also { it.removeAt(currentIndex) }
                            revealed = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(Modifier.fillMaxWidth()) {
                            Text(copy.first, fontWeight = FontWeight.SemiBold)
                            Text(copy.second, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
        Text("Scheduling is an on-device learning aid, not a guarantee of retention or exam performance. Cards are original study prompts, not official past questions.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
