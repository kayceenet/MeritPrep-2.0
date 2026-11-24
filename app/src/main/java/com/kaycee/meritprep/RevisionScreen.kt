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

internal val starterRevisionCards = listOf(
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
) + listOf(
    RevisionCard("bio-var-card-1","bio-variation","Define variation.","Differences among individuals of the same species.",0L),
    RevisionCard("bio-var-card-2","bio-variation","Distinguish continuous from discontinuous variation.","Continuous variation shows a range with intermediates; discontinuous variation places individuals into distinct categories.",0L),
    RevisionCard("bio-var-card-3","bio-variation","Name three processes that create inherited genetic variation.","Mutation, crossing over and independent assortment during meiosis; random fertilisation also creates new combinations.",0L),
    RevisionCard("bio-var-card-4","bio-variation","What are the three alleles in the basic ABO blood-group system?","Iᴬ, Iᴮ and i.",0L),
    RevisionCard("bio-var-card-5","bio-variation","How are Iᴬ and Iᴮ related?","They are codominant; genotype IᴬIᴮ produces blood group AB.",0L),
    RevisionCard("bio-var-card-6","bio-variation","What genotype produces blood group O in the basic ABO model?","ii.",0L),
    RevisionCard("bio-var-card-7","bio-variation","Why is height an example of a complex trait?","It is influenced by many genes and environmental factors such as nutrition and health.",0L),
    RevisionCard("bio-var-card-8","bio-variation","How does natural selection use variation?","It acts on heritable differences that affect reproductive success and can change variant frequencies over generations.",0L)
) + listOf(
    RevisionCard("bio-her-card-1","bio-heredity","What is a gene?","A section of DNA that contributes to a functional product, such as a protein or functional RNA.",0L),
    RevisionCard("bio-her-card-2","bio-heredity","What are alleles?","Alternative versions of the same gene.",0L),
    RevisionCard("bio-her-card-3","bio-heredity","Distinguish genotype from phenotype.","Genotype is the genetic constitution; phenotype is the observable characteristic produced by genotype interacting with the environment.",0L),
    RevisionCard("bio-her-card-4","bio-heredity","What is the expected genotype ratio from Tt × Tt?","1 TT : 2 Tt : 1 tt.",0L),
    RevisionCard("bio-her-card-5","bio-heredity","What is the expected phenotype ratio from Tt × Tt under complete dominance?","3 dominant : 1 recessive.",0L),
    RevisionCard("bio-her-card-6","bio-heredity","State Mendel's law of segregation.","The two alleles for a gene separate during gamete formation, so each gamete receives one allele.",0L),
    RevisionCard("bio-her-card-7","bio-heredity","What is a mutation?","A change in DNA sequence; its effect may be neutral, harmful or beneficial depending on context.",0L),
    RevisionCard("bio-her-card-8","bio-heredity","What does homozygous mean?","Having two identical alleles at a gene locus.",0L)
) + listOf(
    RevisionCard("bio-bt-card-1","bio-biotechnology","Define biotechnology.","Use of living organisms, cells or biological processes to develop useful products and services.",0L),
    RevisionCard("bio-bt-card-2","bio-biotechnology","What does yeast produce during alcoholic fermentation?","Ethanol and carbon dioxide.",0L),
    RevisionCard("bio-bt-card-3","bio-biotechnology","What is selective breeding?","Choosing parents with desirable inherited characteristics and breeding them over generations.",0L),
    RevisionCard("bio-bt-card-4","bio-biotechnology","What is plant tissue culture?","Growing plant cells, tissues or organs on sterile nutrient media under controlled conditions.",0L),
    RevisionCard("bio-bt-card-5","bio-biotechnology","What is micropropagation?","Rapid multiplication of plants using tissue culture techniques.",0L),
    RevisionCard("bio-bt-card-6","bio-biotechnology","What is recombinant DNA technology?","Combining DNA sequences from different sources, often using a vector and host cell.",0L),
    RevisionCard("bio-bt-card-7","bio-biotechnology","Give one medical application of genetic engineering.","Genetically engineered microorganisms can produce human insulin.",0L),
    RevisionCard("bio-bt-card-8","bio-biotechnology","What does biosafety assessment examine?","Potential risks of a biological technology to human health and the environment.",0L)
) + listOf(
    RevisionCard("bio-evo-t-card-1","bio-evolution-theory","Define biological evolution.","Change in inherited characteristics or allele frequencies in populations across generations.",0L),
    RevisionCard("bio-evo-t-card-2","bio-evolution-theory","What is the central idea of natural selection?","Heritable variants that improve reproductive success in a particular environment can become more common over generations.",0L),
    RevisionCard("bio-evo-t-card-3","bio-evolution-theory","What is the ultimate source of new alleles?","Mutation, a change in DNA sequence.",0L),
    RevisionCard("bio-evo-t-card-4","bio-evolution-theory","What is genetic drift?","Random change in allele frequencies, often with stronger effects in small populations.",0L),
    RevisionCard("bio-evo-t-card-5","bio-evolution-theory","What is gene flow?","Transfer of alleles between populations through migrating individuals or gametes.",0L),
    RevisionCard("bio-evo-t-card-6","bio-evolution-theory","How can reproductive isolation lead to speciation?","It reduces gene flow, allowing populations to diverge through mutation, selection and drift over generations.",0L),
    RevisionCard("bio-evo-t-card-7","bio-evolution-theory","Does natural selection create mutations because organisms need them?","No. Mutations arise without regard to need; selection acts on heritable variation already present or arising.",0L),
    RevisionCard("bio-evo-t-card-8","bio-evolution-theory","Distinguish acclimatisation from evolution.","Acclimatisation is an individual's adjustment during life; evolution is inherited population change across generations.",0L)
) + listOf(
    RevisionCard("bio-evo-e-card-1","bio-evolution-evidence","What do fossils show?","Preserved remains or traces of past organisms that document past life, extinction and change through geological time.",0L),
    RevisionCard("bio-evo-e-card-2","bio-evolution-evidence","What are homologous structures?","Structures with a similar underlying anatomical pattern due to common ancestry, even if their functions differ.",0L),
    RevisionCard("bio-evo-e-card-3","bio-evolution-evidence","What are analogous structures?","Structures with similar functions that evolved independently from different ancestral structures.",0L),
    RevisionCard("bio-evo-e-card-4","bio-evolution-evidence","What is a vestigial structure?","A reduced feature inherited from ancestors in which it had a different or more prominent function; it may retain some function.",0L),
    RevisionCard("bio-evo-e-card-5","bio-evolution-evidence","How does DNA comparison provide evolutionary evidence?","Sequence similarities and differences can help infer relatedness and construct phylogenetic trees.",0L),
    RevisionCard("bio-evo-e-card-6","bio-evolution-evidence","Define biogeography.","The study of the geographical distribution of organisms and the processes that shape those patterns.",0L),
    RevisionCard("bio-evo-e-card-7","bio-evolution-evidence","Why is the fossil record incomplete?","Fossilisation is uncommon; fossils may be destroyed, altered or remain undiscovered.",0L),
    RevisionCard("bio-evo-e-card-8","bio-evolution-evidence","Does evolution form a ladder of progress?","No. Evolution produces branching lineages with no predetermined goal or universal direction towards greater complexity.",0L)
) + listOf(
    RevisionCard("phy-measure-card-1","phy-measurements","What are the seven SI base quantities?","Length, mass, time, electric current, thermodynamic temperature, amount of substance and luminous intensity.",0L),
    RevisionCard("phy-measure-card-2","phy-measurements","What is the SI unit of force?","The newton (N), equivalent to kg m s⁻².",0L),
    RevisionCard("phy-measure-card-3","phy-measurements","What are the dimensions of acceleration?","[LT⁻²], because acceleration is change in velocity per unit time.",0L),
    RevisionCard("phy-vectors-card-1","phy-vectors","Distinguish a scalar from a vector.","A scalar has magnitude only. A vector has magnitude and direction.",0L),
    RevisionCard("phy-vectors-card-2","phy-vectors","How do you find the resultant of perpendicular components A and B?","R = √(A² + B²). The direction from A is θ = tan⁻¹(B/A), with the correct quadrant stated.",0L),
    RevisionCard("phy-vectors-card-3","phy-vectors","A learner walks 3 m east then 4 m north. What are the distance and displacement magnitude?","Distance = 7 m. Displacement magnitude = √(3² + 4²) = 5 m.",0L),
    RevisionCard("phy-motion-card-1","phy-motion","Write the four equations of uniformly accelerated straight-line motion.","v = u + at; s = ut + ½at²; v² = u² + 2as; s = ½(u + v)t. They require constant acceleration.",0L),
    RevisionCard("phy-motion-card-2","phy-motion","What does the gradient of a displacement-time graph represent?","Velocity.",0L),
    RevisionCard("phy-motion-card-3","phy-motion","What does the area under a velocity-time graph represent?","Signed displacement over the time interval.",0L),
    RevisionCard("phy-motion-card-4","phy-motion","State Newton's second law in its common constant-mass form.","The resultant force on a body is F = ma, where m is mass and a is acceleration.",0L),
    RevisionCard("phy-gravity-card-1","phy-gravity","Distinguish mass from weight.","Mass measures inertia and is measured in kilograms. Weight is gravitational force W = mg and is measured in newtons.",0L),
    RevisionCard("phy-gravity-card-2","phy-gravity","State Newton's law of universal gravitation.","F = Gm₁m₂/r² for two point masses or spherically symmetric bodies, with r measured between their centres.",0L),
    RevisionCard("phy-gravity-card-3","phy-gravity","What is gravitational field strength?","Gravitational force per unit mass: g = F/m, measured in N kg⁻¹.",0L),
    RevisionCard("phy-equilibrium-card-1","phy-equilibrium","What are the conditions for translational equilibrium in two dimensions?","ΣFₓ = 0 and ΣFᵧ = 0.",0L),
    RevisionCard("phy-equilibrium-card-2","phy-equilibrium","Define the moment of a force.","Moment = force × perpendicular distance from the pivot to the force's line of action; unit N m.",0L),
    RevisionCard("phy-equilibrium-card-3","phy-equilibrium","When may Lami's theorem be used?","For a particle in equilibrium under exactly three coplanar, concurrent and non-parallel forces.",0L),
    RevisionCard("phy-energy-card-1","phy-work-energy","Write the equation for work done by a constant force at angle θ to displacement.","W = Fs cos θ.",0L),
    RevisionCard("phy-energy-card-2","phy-work-energy","Write the expressions for kinetic and gravitational potential energy near Earth's surface.","Kinetic energy Eₖ = ½mv². Gravitational potential energy Eₚ = mgh relative to a chosen reference level.",0L),
    RevisionCard("phy-energy-card-3","phy-work-energy","Define power and state its SI unit.","Power is the rate of energy transfer or work done: P = E/t = W/t. SI unit: watt (W).",0L),
    RevisionCard("phy-friction-card-1","phy-friction","What is limiting friction?","The maximum static friction just before relative sliding begins.",0L),
    RevisionCard("phy-friction-card-2","phy-friction","In the simple dry-friction model, how is limiting friction related to normal reaction?","Fₘₐₓ = μR, where μ is the coefficient of friction and R is the normal reaction.",0L),
    RevisionCard("phy-friction-card-3","phy-friction","What is terminal velocity?","The constant speed reached when the resultant force becomes zero, so acceleration is zero.",0L),
    RevisionCard("phy-machines-card-1","phy-machines","Define mechanical advantage and velocity ratio.","MA = load/effort. VR = distance moved by effort/distance moved by load.",0L),
    RevisionCard("phy-machines-card-2","phy-machines","How is machine efficiency calculated?","Efficiency = (MA/VR) × 100% or (useful output work/input work) × 100%.",0L),
    RevisionCard("phy-machines-card-3","phy-machines","Why is the efficiency of a real machine usually below 100%?","Some input energy is transferred to non-useful forms such as heat and sound because of friction, deformation and other losses.",0L)
) + listOf(
    RevisionCard("phy-elasticity-card-1","phy-elasticity","State Hooke's law.","Within the limit of proportionality, extension is directly proportional to applied force: F = kx.",0L),
    RevisionCard("phy-elasticity-card-2","phy-elasticity","Define Young's modulus.","Young's modulus = tensile stress/tensile strain = FL/(AΔL), for a uniform specimen in the linear elastic region.",0L),
    RevisionCard("phy-elasticity-card-3","phy-elasticity","What does the area under a force-extension graph represent?","Work done in stretching the spring, stored as elastic potential energy for an ideal elastic spring.",0L),
    RevisionCard("phy-pressure-card-1","phy-pressure","Write the expression for pressure in a static liquid at depth h.","Gauge pressure p = ρgh, assuming uniform density and gravitational field.",0L),
    RevisionCard("phy-pressure-card-2","phy-pressure","What is Pascal's principle?","A pressure change applied to an enclosed fluid is transmitted throughout the fluid.",0L),
    RevisionCard("phy-pressure-card-3","phy-pressure","Distinguish gauge pressure from absolute pressure.","Gauge pressure is relative to the surface or atmospheric reference; absolute pressure includes atmospheric pressure.",0L),
    RevisionCard("phy-liquids-card-1","phy-liquids-rest","Define density and state its SI unit.","Density = mass/volume, measured in kg m⁻³.",0L),
    RevisionCard("phy-liquids-card-2","phy-liquids-rest","State Archimedes' principle.","A body wholly or partly immersed in a fluid experiences an upthrust equal to the weight of fluid displaced.",0L),
    RevisionCard("phy-liquids-card-3","phy-liquids-rest","What is the law of floatation?","A floating body displaces a fluid whose weight equals the weight of the body.",0L),
    RevisionCard("phy-temperature-card-1","phy-temperature","How do you convert Celsius temperature to kelvin?","T(K) = θ(°C) + 273.15. Kelvin values are written without a degree sign.",0L),
    RevisionCard("phy-temperature-card-2","phy-temperature","What is a thermometric property?","A measurable physical property that changes predictably with temperature, such as electrical resistance or liquid volume.",0L),
    RevisionCard("phy-temperature-card-3","phy-temperature","What is thermal equilibrium?","Bodies in thermal equilibrium have the same temperature and no net heat transfer between them.",0L),
    RevisionCard("phy-expansion-card-1","phy-thermal-expansion","Write the equation for linear thermal expansion.","ΔL = αL₀ΔT, where α is the coefficient of linear expansivity.",0L),
    RevisionCard("phy-expansion-card-2","phy-thermal-expansion","What is the approximate volume expansivity of an isotropic solid in terms of linear expansivity α?","Approximately 3α for small expansion and an isotropic solid.",0L),
    RevisionCard("phy-expansion-card-3","phy-thermal-expansion","At what temperature does water have its maximum density?","Approximately 4 °C. Water expands as it cools from 4 °C to 0 °C.",0L),
    RevisionCard("phy-gas-card-1","phy-gas-laws","State Boyle's law.","For a fixed mass of gas at constant temperature, pV = constant.",0L),
    RevisionCard("phy-gas-card-2","phy-gas-laws","State Charles's law.","At constant pressure, the volume of a fixed mass of gas is directly proportional to its absolute temperature: V/T = constant.",0L),
    RevisionCard("phy-gas-card-3","phy-gas-laws","Write the ideal gas equation and state its variables.","pV = nRT; p is pressure, V volume, n amount in moles, R molar gas constant and T absolute temperature.",0L),
    RevisionCard("phy-heat-card-1","phy-heat-quantity","Distinguish heat capacity from specific heat capacity.","Heat capacity is energy needed to raise a whole body's temperature by 1 K; specific heat capacity is energy needed per kilogram per kelvin.",0L),
    RevisionCard("phy-heat-card-2","phy-heat-quantity","Write the equation for heat transfer without a change of state.","Q = mcΔT.",0L),
    RevisionCard("phy-heat-card-3","phy-heat-quantity","In an ideal insulated method-of-mixtures experiment, what is the energy balance?","Heat lost by hotter bodies equals heat gained by cooler bodies.",0L),
    RevisionCard("phy-state-card-1","phy-change-state","Write the equation for energy during a change of state.","Q = mL, where L is the specific latent heat.",0L),
    RevisionCard("phy-state-card-2","phy-change-state","How does evaporation differ from boiling?","Evaporation occurs at the liquid surface and can happen below the boiling point; boiling occurs throughout the liquid at its boiling condition.",0L),
    RevisionCard("phy-state-card-3","phy-change-state","What happens to the temperature of a pure substance during melting at constant pressure?","It remains approximately constant while latent heat is supplied for the change of state.",0L)
) + listOf(
    RevisionCard("phy-vapours-card-1","phy-vapours","Define saturated vapour pressure.","The pressure of a vapour in dynamic equilibrium with its liquid at a stated temperature.",0L),
    RevisionCard("phy-vapours-card-2","phy-vapours","When does a liquid boil?","When its vapour pressure equals the external pressure.",0L),
    RevisionCard("phy-vapours-card-3","phy-vapours","Define relative humidity.","Actual water-vapour pressure divided by saturated vapour pressure at the same temperature, multiplied by 100%.",0L),
    RevisionCard("phy-kinetic-card-1","phy-kinetic-theory","What causes Brownian motion?","Uneven collisions of rapidly moving fluid molecules with small suspended particles.",0L),
    RevisionCard("phy-kinetic-card-2","phy-kinetic-theory","How is gas pressure produced in kinetic theory?","Gas molecules collide with container walls and transfer momentum, producing force per unit area.",0L),
    RevisionCard("phy-kinetic-card-3","phy-kinetic-theory","What is diffusion?","Net movement of particles from higher to lower concentration due to random motion.",0L),
    RevisionCard("phy-heat-transfer-card-1","phy-heat-transfer","Distinguish conduction, convection and radiation.","Conduction transfers energy without bulk movement; convection uses bulk fluid movement; radiation transfers energy by electromagnetic waves.",0L),
    RevisionCard("phy-heat-transfer-card-2","phy-heat-transfer","Which mode of heat transfer can cross a vacuum?","Radiation.",0L),
    RevisionCard("phy-heat-transfer-card-3","phy-heat-transfer","Why are dark, dull surfaces good thermal emitters?","They generally emit thermal radiation more effectively than light, shiny surfaces.",0L),
    RevisionCard("phy-waves-card-1","phy-waves","Write the relationship between wave speed, frequency and wavelength.","v = fλ.",0L),
    RevisionCard("phy-waves-card-2","phy-waves","Distinguish transverse and longitudinal waves.","Transverse oscillations are perpendicular to propagation; longitudinal oscillations are parallel to propagation.",0L),
    RevisionCard("phy-waves-card-3","phy-waves","What is diffraction?","Spreading of waves around obstacles or through openings.",0L),
    RevisionCard("phy-sound-prop-card-1","phy-sound-propagation","Why can sound not travel through a vacuum?","Sound is a mechanical wave and requires a material medium to transmit the disturbance.",0L),
    RevisionCard("phy-sound-prop-card-2","phy-sound-propagation","How do you calculate distance to a reflecting wall from an echo time t?","Distance = vt/2 because the sound travels to the wall and back.",0L),
    RevisionCard("phy-sound-prop-card-3","phy-sound-propagation","What is reverberation?","Persistence of sound in an enclosed space due to repeated reflections after the source stops.",0L),
    RevisionCard("phy-sound-char-card-1","phy-sound-characteristics","What physical quantity is most closely related to pitch?","Frequency.",0L),
    RevisionCard("phy-sound-char-card-2","phy-sound-characteristics","What is sound intensity?","Sound power transmitted per unit area, measured in W m⁻².",0L),
    RevisionCard("phy-sound-char-card-3","phy-sound-characteristics","What is the fundamental frequency of an ideal pipe closed at one end?","f = v/(4L).",0L),
    RevisionCard("phy-light-card-1","phy-light-energy","Distinguish luminous and non-luminous objects.","Luminous objects produce their own light; non-luminous objects are seen by reflecting or scattering light.",0L),
    RevisionCard("phy-light-card-2","phy-light-energy","Write the wave equation for light in a vacuum.","c = fλ, where c is approximately 3.00 × 10⁸ m s⁻¹.",0L),
    RevisionCard("phy-light-card-3","phy-light-energy","Why is the image in a pinhole camera inverted?","Rays from the top and bottom of the object cross at the pinhole before reaching the screen.",0L),
    RevisionCard("phy-reflection-card-1","phy-reflection","State the law of reflection.","The angle of incidence equals the angle of reflection, measured from the normal.",0L),
    RevisionCard("phy-reflection-card-2","phy-reflection","Describe the image formed by a plane mirror.","Virtual, upright, same size as the object and apparently the same distance behind the mirror as the object is in front.",0L),
    RevisionCard("phy-reflection-card-3","phy-reflection","Why are convex mirrors used as vehicle side mirrors?","They form virtual, upright and diminished images and provide a wide field of view.",0L)
) + listOf(
    RevisionCard("phy-refraction-card-1","phy-refraction","State Snell's law for two media.","n₁ sin i = n₂ sin r, with both angles measured from the normal.",0L),
    RevisionCard("phy-refraction-card-2","phy-refraction","When can total internal reflection occur?","When light travels from a higher-index medium to a lower-index medium and the incidence angle exceeds the critical angle.",0L),
    RevisionCard("phy-refraction-card-3","phy-refraction","How is apparent depth related to real depth for near-normal viewing from air?","Apparent depth ≈ real depth divided by the refractive index of the liquid relative to air.",0L),
    RevisionCard("phy-optics-card-1","phy-optical-instruments","How is lens power calculated?","P = 1/f, where focal length f is in metres and power is in dioptres.",0L),
    RevisionCard("phy-optics-card-2","phy-optical-instruments","Which lens corrects myopia?","A diverging (concave) lens.",0L),
    RevisionCard("phy-optics-card-3","phy-optical-instruments","Which lens corrects hyperopia?","A converging (convex) lens.",0L),
    RevisionCard("phy-dispersion-card-1","phy-dispersion","What causes dispersion in a glass prism?","The refractive index varies with wavelength, so different colours refract by different amounts.",0L),
    RevisionCard("phy-dispersion-card-2","phy-dispersion","Which visible colour is deviated most by ordinary glass?","Violet is deviated most; red is deviated least under normal dispersion.",0L),
    RevisionCard("phy-dispersion-card-3","phy-dispersion","What are the primary additive colours of light?","Red, green and blue.",0L)
) + listOf(
    RevisionCard("phy-electrostatics-card-1","phy-electrostatics","State Coulomb's law.","For two stationary point charges, F = k|q₁q₂|/r² along the line joining them.",0L),
    RevisionCard("phy-electrostatics-card-2","phy-electrostatics","Define electric field strength.","Force per unit positive test charge: E = F/q.",0L),
    RevisionCard("phy-electrostatics-card-3","phy-electrostatics","What is charging by induction?","Charging a conductor without direct contact by redistributing charge with a nearby charged body and using earthing in the correct sequence.",0L),
    RevisionCard("phy-capacitors-card-1","phy-capacitors","Define capacitance.","Charge stored per unit potential difference: C = Q/V.",0L),
    RevisionCard("phy-capacitors-card-2","phy-capacitors","Write the energy stored in a capacitor.","E = ½CV² = ½QV = Q²/(2C).",0L),
    RevisionCard("phy-capacitors-card-3","phy-capacitors","How do capacitances combine in parallel?","Cₑq = C₁ + C₂ + …",0L),
    RevisionCard("phy-cells-card-1","phy-cells","Define electromotive force.","Energy supplied by a source per unit charge around the complete circuit.",0L),
    RevisionCard("phy-cells-card-2","phy-cells","Write the terminal voltage of a discharging cell with internal resistance.","V = ε − Ir, where ε is emf, I current and r internal resistance.",0L),
    RevisionCard("phy-cells-card-3","phy-cells","What happens to emf when identical cells are connected in series aiding?","Their emfs add; internal resistances also add.",0L),
    RevisionCard("phy-current-card-1","phy-current-electricity","Define electric current.","Rate of flow of charge: I = Q/t.",0L),
    RevisionCard("phy-current-card-2","phy-current-electricity","State Ohm's law.","For a conductor at constant temperature and unchanged physical conditions, V = IR.",0L),
    RevisionCard("phy-current-card-3","phy-current-electricity","How are two resistors combined in parallel?","1/Rₑq = 1/R₁ + 1/R₂.",0L),
    RevisionCard("phy-electrical-power-card-1","phy-electrical-power","Write the electrical power equation.","P = VI; for an ohmic resistor P = I²R = V²/R.",0L),
    RevisionCard("phy-electrical-power-card-2","phy-electrical-power","Convert 1 kWh to joules.","1 kWh = 3.6 × 10⁶ J.",0L),
    RevisionCard("phy-electrical-power-card-3","phy-electrical-power","Why is electricity transmitted at high voltage?","For a given power, higher voltage means lower current, reducing resistive cable losses I²R.",0L)
) + listOf(
    RevisionCard("phy-magnetism-card-1","phy-magnetism","How do like and unlike magnetic poles interact?","Like poles repel; unlike poles attract.",0L),
    RevisionCard("phy-magnetism-card-2","phy-magnetism","What is the SI unit of magnetic flux density?","The tesla (T). Magnetic flux is measured in webers (Wb).",0L),
    RevisionCard("phy-magnetism-card-3","phy-magnetism","What is the right-hand grip rule used for?","It gives the direction of the magnetic field around a straight current-carrying conductor.",0L),
    RevisionCard("phy-magnetic-force-card-1","phy-magnetic-force","Write the force on a straight current-carrying wire in a magnetic field.","F = BIL sin θ, where θ is the angle between the current and magnetic field.",0L),
    RevisionCard("phy-magnetic-force-card-2","phy-magnetic-force","What does Fleming's left-hand rule identify?","The relationship between magnetic field direction, conventional current and the resulting force or motion.",0L),
    RevisionCard("phy-magnetic-force-card-3","phy-magnetic-force","How does a split-ring commutator help a simple DC motor rotate continuously?","It reverses the current in the coil every half-turn so the turning effect continues in the same rotational direction.",0L),
    RevisionCard("phy-induction-card-1","phy-induction","State Faraday's law of electromagnetic induction.","The magnitude of induced emf is proportional to the rate of change of magnetic flux linkage.",0L),
    RevisionCard("phy-induction-card-2","phy-induction","What does Lenz's law state?","The induced current produces a magnetic effect that opposes the change in flux that caused it.",0L),
    RevisionCard("phy-induction-card-3","phy-induction","What is the ideal transformer turns-voltage relationship?","Vₛ/Vₚ = Nₛ/Nₚ.",0L),
    RevisionCard("phy-ac-card-1","phy-ac-circuits","How is rms voltage related to peak voltage for a sinusoidal supply?","Vᵣₘₛ = V₀/√2.",0L),
    RevisionCard("phy-ac-card-2","phy-ac-circuits","Write the capacitive and inductive reactance equations.","Xc = 1/(2πfC); Xl = 2πfL.",0L),
    RevisionCard("phy-ac-card-3","phy-ac-circuits","What is the condition for series RLC resonance?","Inductive reactance equals capacitive reactance: Xl = Xc.",0L),
    RevisionCard("phy-conduction-card-1","phy-conduction","What is an electrolyte?","A substance that conducts electricity when molten or in aqueous solution because it contains mobile ions.",0L),
    RevisionCard("phy-conduction-card-2","phy-conduction","State Faraday's first law of electrolysis.","The mass deposited or liberated at an electrode is proportional to the quantity of charge passed, for the same electrolyte.",0L),
    RevisionCard("phy-conduction-card-3","phy-conduction","What is required for current to pass through a gas?","The gas must contain charged particles such as ions and electrons, usually produced by ionisation.",0L),
    RevisionCard("phy-modern-card-1","phy-modern","What is the photoelectric effect?","Emission of electrons from a material when electromagnetic radiation of sufficiently high frequency falls on it.",0L),
    RevisionCard("phy-modern-card-2","phy-modern","What is half-life?","The time taken for the number of undecayed radioactive nuclei, or activity, to fall to half its initial value.",0L),
    RevisionCard("phy-modern-card-3","phy-modern","What is the difference between nuclear fission and fusion?","Fission splits a heavy nucleus; fusion combines light nuclei. Both can release energy under suitable conditions.",0L),
    RevisionCard("phy-electronics-card-1","phy-electronics","What is the difference between an n-type and p-type semiconductor?","An n-type semiconductor has electrons as majority charge carriers; a p-type semiconductor has holes as majority carriers.",0L),
    RevisionCard("phy-electronics-card-2","phy-electronics","What is the main function of a p-n junction diode?","It allows current much more readily in the forward direction than in the reverse direction under normal operating conditions.",0L),
    RevisionCard("phy-electronics-card-3","phy-electronics","What does a transistor commonly do in a simple electronic circuit?","It can act as an electronic switch or amplify a signal.",0L)
) + listOf(
    RevisionCard("math-bases-card-1","math-number-bases","How do you convert a base-b numeral to base ten?","Multiply each digit by its place value (a power of b) and add the results.",0L),
    RevisionCard("math-bases-card-2","math-number-bases","How do you convert a positive whole number from base ten to base b?","Divide repeatedly by b, record remainders and read the remainders from bottom to top.",0L),
    RevisionCard("math-bases-card-3","math-number-bases","What does a mod n represent?","The remainder when integer a is divided by positive integer n.",0L),
    RevisionCard("math-fractions-card-1","math-fractions-percentages","How do you add fractions with different denominators?","Express them with a common denominator, add the numerators and simplify.",0L),
    RevisionCard("math-fractions-card-2","math-fractions-percentages","How do you calculate simple interest?","I = Prt, where P is principal, r is annual rate as a decimal and t is time in years.",0L),
    RevisionCard("math-fractions-card-3","math-fractions-percentages","What is the usual reference value for profit percentage?","Cost price: profit percentage = profit/cost price × 100%.",0L),
    RevisionCard("math-indices-card-1","math-indices-logs-surds","State the product law of indices.","aᵐ × aⁿ = aᵐ⁺ⁿ for a valid non-zero base.",0L),
    RevisionCard("math-indices-card-2","math-indices-logs-surds","What does logₐ x = y mean?","aʸ = x, where a>0, a≠1 and x>0.",0L),
    RevisionCard("math-indices-card-3","math-indices-logs-surds","How do you simplify √72?","√72 = √(36×2) = 6√2.",0L),
    RevisionCard("math-sets-card-1","math-sets","What does A∪B mean?","The union: elements in A or B or both.",0L),
    RevisionCard("math-sets-card-2","math-sets","What does A∩B mean?","The intersection: elements common to both A and B.",0L),
    RevisionCard("math-sets-card-3","math-sets","Write the two-set cardinality formula.","n(A∪B)=n(A)+n(B)−n(A∩B).",0L),
    RevisionCard("math-polynomials-card-1","math-polynomials","State the factor theorem.","(x−a) is a factor of f(x) if and only if f(a)=0.",0L),
    RevisionCard("math-polynomials-card-2","math-polynomials","State the remainder theorem.","The remainder when f(x) is divided by (x−a) is f(a).",0L),
    RevisionCard("math-polynomials-card-3","math-polynomials","What is the degree of a polynomial?","The highest power of the variable with a non-zero coefficient.",0L),
    RevisionCard("math-variation-card-1","math-variation","Write the model for direct variation.","y = kx, where k is the constant of variation.",0L),
    RevisionCard("math-variation-card-2","math-variation","Write the model for inverse variation.","y = k/x, or xy = k.",0L),
    RevisionCard("math-variation-card-3","math-variation","Write a model for partial variation.","y = a + bx, where a and b are constants.",0L),
    RevisionCard("math-inequalities-card-1","math-inequalities","What happens to an inequality sign when multiplying by a negative number?","Reverse the inequality sign.",0L),
    RevisionCard("math-inequalities-card-2","math-inequalities","How do you solve a quadratic inequality with two real roots?","Find the roots, split the number line into intervals and test the sign in each interval.",0L),
    RevisionCard("math-inequalities-card-3","math-inequalities","Which endpoint is used for a strict inequality such as x<4?","An open endpoint at 4, because 4 is excluded.",0L),
    RevisionCard("math-progression-card-1","math-progression","Write the nth term of an arithmetic progression.","Tₙ = a + (n−1)d.",0L),
    RevisionCard("math-progression-card-2","math-progression","Write the sum of the first n terms of an arithmetic progression.","Sₙ = n/2[2a+(n−1)d].",0L),
    RevisionCard("math-progression-card-3","math-progression","When does a geometric progression have a finite sum to infinity?","When |r|<1; then S∞ = a/(1−r).",0L)
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
        database.seedRevisionCards(starterRevisionCards)
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
