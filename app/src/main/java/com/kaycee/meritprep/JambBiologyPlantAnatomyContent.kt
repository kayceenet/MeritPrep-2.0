package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led treatment of the JAMB plant and mammalian structure unit. */
internal object JambBiologyPlantAnatomyContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Identify the main tissue systems in flowering plants and explain the functions of their component tissues.",
            listOf(
                JambDeepPoint("Meristematic and permanent tissues",
                    """Plant growth occurs in regions containing meristematic cells. These cells are relatively small, have thin primary walls, dense cytoplasm, prominent nuclei and little or no large permanent vacuole. They divide by mitosis and produce new cells. Apical meristems at root and shoot tips increase length (primary growth); lateral meristems such as vascular cambium and cork cambium contribute to increase in girth (secondary growth) in plants that possess them. Intercalary meristems, found near nodes or leaf bases in some plants such as grasses, allow regrowth after grazing or cutting.

                    As cells mature, they differentiate into permanent tissues with specialised structures. Simple permanent tissues are mainly one cell type: parenchyma commonly stores food, conducts short-distance transport and may photosynthesise; collenchyma has unevenly thickened cellulose-rich walls and provides flexible support in growing organs; sclerenchyma has thick, lignified walls and provides rigid support, usually when mature cells are dead. Complex tissues contain different cell types working together, especially xylem and phloem.""",
                    "A young shoot tip contains actively dividing apical meristem; a celery leaf stalk has collenchyma that provides flexible support.",
                    "Apical meristem increases length; lateral meristem increases girth. Parenchyma = storage/photosynthesis; collenchyma = flexible support; sclerenchyma = rigid support."),
                JambDeepPoint("The three tissue systems",
                    """The dermal or epidermal tissue system forms the plant's outer protective covering. The epidermis may be covered by a waxy cuticle that limits water loss. Root epidermal cells may form root hairs for absorption. In leaves, stomata are pores controlled by guard cells; in woody stems, the epidermis is replaced as the plant expands by protective periderm, which includes cork.

                    The ground tissue system includes much of the cortex, pith and mesophyll. It performs photosynthesis, food storage, packing and support. The vascular tissue system consists of xylem and phloem arranged in vascular bundles or cylinders. The arrangement differs between roots, stems and leaves and is useful when identifying a transverse section. A tissue system is a functional grouping of tissues, not a single cell type.""",
                    "In a leaf, the epidermis protects, mesophyll performs most photosynthesis, and veins contain xylem and phloem.",
                    "Dermal = protection; ground = photosynthesis, storage and support; vascular = transport. Do not call all internal tissue xylem or phloem.")
            ),
            listOf("Meristematic cells divide; mature permanent cells are differentiated for particular functions.",
                  "Collenchyma provides flexible support; sclerenchyma is usually more rigid because of lignification.",
                  "A tissue system is a group of tissues organised to perform related functions."),
            listOf(
                JambDeepCheck("bio-plant-01","Which meristem is mainly responsible for increase in stem length?",listOf("Vascular cambium","Apical meristem","Cork cambium","Mature sclerenchyma"),1,"Apical meristems at shoot and root tips produce primary growth, increasing length."),
                JambDeepCheck("bio-plant-02","Unevenly thickened walls in collenchyma are associated mainly with",listOf("flexible support in growing organs","long-distance sugar transport","water absorption by osmosis only","production of pollen"),0,"Collenchyma supports young growing organs while allowing them to bend without easily breaking."),
                JambDeepCheck("bio-plant-03","The tissue system that includes the epidermis is the",listOf("vascular system","ground system","dermal system","meristematic system"),2,"The dermal or epidermal system forms the outer covering and protects the plant.")
            )
        ),
        JambDeepObjective(
            "Describe the internal structure of roots and stems and relate tissue arrangement to their functions.",
            listOf(
                JambDeepPoint("Root structure in transverse section",
                    """A typical young dicot root has an outer epidermis, often bearing root hairs, followed by a broad cortex of mainly parenchyma cells. The endodermis is the innermost layer of the cortex and regulates movement of water and dissolved ions into the vascular cylinder. Casparian strips in endodermal cell walls contain waterproof suberin and help force water through selectively permeable membranes rather than allowing unrestricted movement through cell walls. Inside the endodermis is the pericycle, from which lateral roots commonly arise.

                    In a typical dicot root, the central vascular tissue often has a star-shaped xylem with phloem between the arms. In many monocot roots, the vascular tissues are arranged in a ring around a central pith, with many xylem strands. These are useful school-level patterns, but exact anatomy varies with species and developmental stage. Root hairs are extensions of epidermal cells, not separate roots or vascular tissues.""",
                    "A transverse section of a typical dicot root shows central star-shaped xylem with phloem located between its arms.",
                    "Root identification clues: root hairs, broad cortex, endodermis and central vascular cylinder; typical dicot root has central star-shaped xylem."),
                JambDeepPoint("Stem structure and vascular bundles",
                    """A typical young dicot stem has an epidermis, cortex, vascular bundles arranged in a ring and a central pith. In each open collateral vascular bundle, phloem is towards the outside, xylem towards the inside and vascular cambium lies between them. The cambium can produce secondary xylem inwards and secondary phloem outwards, contributing to increase in stem girth. In a typical monocot stem, vascular bundles are scattered through the ground tissue and are usually closed, without a vascular cambium capable of ordinary secondary thickening.

                    Stem xylem supports the plant and conducts water and mineral ions; phloem transports assimilates. The cortex and pith may store food or water, and supporting tissues strengthen the stem. Do not identify a section from one feature alone: check the whole arrangement, the position of vascular bundles, presence of pith and the relation between xylem and phloem.""",
                    "A typical dicot stem has vascular bundles in a ring; a typical monocot stem has scattered vascular bundles.",
                    "In a typical dicot stem, xylem is inward and phloem outward in each bundle. Cambium between them enables secondary growth.")
            ),
            listOf("The typical dicot root has central star-shaped xylem; the typical dicot stem has vascular bundles arranged in a ring.",
                  "In stems, xylem is on the inner side of phloem; in a root, identify the whole central vascular pattern.",
                  "Monocot stem bundles are generally scattered and closed; do not expect a continuous vascular cambium ring."),
            listOf(
                JambDeepCheck("bio-plant-04","The endodermis of a root is important because it",listOf("forms pollen grains","regulates entry of water and ions into the vascular cylinder","produces flowers","carries out most photosynthesis"),1,"The endodermis and its Casparian strips regulate movement into the stele."),
                JambDeepCheck("bio-plant-05","A typical dicot root is recognised by",listOf("scattered vascular bundles","central star-shaped xylem","large central air cavity","vascular bundles in a ring around a pith only"),1,"In a typical dicot root, xylem forms a central star with phloem between its arms."),
                JambDeepCheck("bio-plant-06","In a typical dicot stem vascular bundle, the phloem is located",listOf("inside the xylem","outside the xylem","at the centre of the pith","inside the epidermis"),1,"Stem vascular bundles have phloem towards the outside and xylem towards the inside.")
            )
        ),
        JambDeepObjective(
            "Describe the internal structure of a leaf and explain how its tissues support photosynthesis and gaseous exchange.",
            listOf(
                JambDeepPoint("Dorsiventral leaf anatomy",
                    """A typical broad dicot leaf has an upper epidermis covered by a cuticle, a palisade mesophyll layer, a spongy mesophyll layer, vascular bundles (veins) and a lower epidermis. Palisade cells are elongated and commonly contain many chloroplasts, positioning them to absorb light efficiently. Spongy mesophyll cells are more loosely arranged, leaving air spaces through which carbon dioxide, oxygen and water vapour diffuse. The lower epidermis commonly has many stomata in terrestrial dicot leaves, although distribution varies among species.

                    The cuticle reduces uncontrolled evaporation. The epidermis is usually transparent enough to allow light to reach photosynthetic cells. Veins provide support and transport: xylem is generally towards the upper side of the leaf and phloem towards the lower side in a typical dorsiventral leaf. Do not assume every leaf has this exact arrangement; monocot and isobilateral leaves may differ.""",
                    "A leaf section with distinct upper palisade and lower spongy mesophyll is typical of a dorsiventral dicot leaf.",
                    "Palisade = many chloroplasts and photosynthesis; spongy mesophyll = air spaces and diffusion; cuticle = limits water loss."),
                JambDeepPoint("Stomata, guard cells and gas exchange",
                    """A stoma is a pore in the epidermis bordered by guard cells. In many dicot leaves, guard cells are kidney-shaped; in many grasses they are dumbbell-shaped. Guard cells regulate pore opening by changes in turgor. When guard cells take up solutes and water enters by osmosis, they become turgid and the pore usually opens; when they lose water and become flaccid, the pore closes. Light, carbon dioxide concentration, water availability and plant hormones such as abscisic acid influence stomatal behaviour.

                    Carbon dioxide diffuses from the air through open stomata and air spaces to photosynthetic cells. Oxygen produced during photosynthesis can diffuse out; water vapour is also lost through transpiration. Stomata therefore balance carbon dioxide uptake with water conservation. A plant may close stomata during water stress, reducing water loss but also restricting carbon dioxide entry and potentially lowering photosynthesis.""",
                    "During water stress, stomatal closure reduces transpiration but may also limit carbon dioxide uptake for photosynthesis.",
                    "Guard cells control stomatal aperture through turgor changes. Stomata enable gas exchange but are also a major route for transpiration.")
            ),
            listOf("Palisade mesophyll is usually more tightly packed and chloroplast-rich than spongy mesophyll.",
                  "Stomatal opening is linked to guard-cell turgor; stomata are not permanently open.",
                  "Closing stomata conserves water but can reduce carbon dioxide entry and photosynthetic rate."),
            listOf(
                JambDeepCheck("bio-plant-07","Which leaf tissue usually contains the greatest concentration of chloroplasts?",listOf("Palisade mesophyll","Lower epidermis","Xylem vessel","Cork"),0,"Palisade mesophyll cells are positioned to receive light and commonly contain many chloroplasts."),
                JambDeepCheck("bio-plant-08","The air spaces in spongy mesophyll mainly assist",listOf("seed formation","diffusion of gases within the leaf","water absorption from soil","mechanical digestion"),1,"Intercellular air spaces provide pathways for carbon dioxide, oxygen and water vapour."),
                JambDeepCheck("bio-plant-09","When guard cells become flaccid, the stomatal pore usually",listOf("widens greatly","closes or becomes narrower","forms xylem","changes into a root hair"),1,"Loss of guard-cell turgor usually reduces the stomatal aperture.")
            )
        ),
        JambDeepObjective(
            "Distinguish xylem from phloem and explain the movement of water, mineral ions and manufactured food.",
            listOf(
                JambDeepPoint("Xylem: water transport and support",
                    """Xylem is a complex vascular tissue made of vessels, tracheids, xylem fibres and xylem parenchyma. In flowering plants, vessel elements join end to end and lose their end walls or develop perforation plates, forming long conducting tubes. Tracheids are elongated, tapered cells that conduct water through pits. Vessel elements and tracheids are dead at functional maturity and have lignified walls; lignin strengthens the walls and helps prevent collapse as water is pulled upwards under tension. Xylem fibres provide support, while xylem parenchyma can store materials and assist lateral transport.

                    Water enters roots mainly by osmosis and moves across root tissues into xylem. Evaporation from leaf surfaces creates tension that contributes to the transpiration pull; cohesion between water molecules and adhesion to xylem walls help maintain the water column. Root pressure can contribute under some conditions but is not the main explanation for water ascent in tall plants. Mineral ions are absorbed selectively, often using active transport at root membranes.""",
                    "A xylem vessel has a hollow lumen and thick lignified walls suited to water conduction and support.",
                    "Xylem transports water and mineral ions mainly upwards; vessel elements and tracheids are dead at maturity and lignified."),
                JambDeepPoint("Phloem: translocation of assimilates",
                    """Phloem is a complex living tissue containing sieve tube elements, companion cells, phloem parenchyma and often phloem fibres. In flowering plants, sieve tube elements are arranged end to end; their end walls form sieve plates with pores. Mature sieve tube elements have reduced internal contents and lack a nucleus, but remain alive and depend on companion cells for metabolic support. Companion cells contain nuclei and many mitochondria and are connected to sieve tubes by plasmodesmata.

                    Phloem translocation moves dissolved organic substances, especially sucrose, from sources to sinks. A source may be a photosynthesising leaf or a storage organ releasing sugars; a sink may be a growing root, fruit, seed or developing shoot. Different sieve tubes can carry materials in different directions at the same time, depending on source–sink relationships. Thus, phloem movement is not simply always downward. The pressure-flow model explains translocation through loading of sugars, osmotic water entry, pressure-driven mass flow and unloading at sinks.""",
                    "Sucrose made in a mature leaf may be translocated to a growing root or developing fruit.",
                    "Phloem transports organic assimilates from sources to sinks; direction depends on the source–sink relationship, not gravity.")
            ),
            listOf("Xylem conducting elements are dead at maturity; sieve tube elements are living but lack a nucleus at maturity.",
                  "Xylem carries water and mineral ions; phloem carries organic assimilates such as sucrose and amino acids.",
                  "Phloem translocation can be upward or downward in different sieve tubes according to source and sink."),
            listOf(
                JambDeepCheck("bio-plant-10","The lignin in xylem walls mainly helps to",listOf("prevent collapse and provide support","digest starch","form pollen tubes","produce sucrose"),0,"Lignified walls strengthen xylem and resist collapse under the tension generated during transpiration."),
                JambDeepCheck("bio-plant-11","The living cells that support sieve tube elements are",listOf("guard cells","companion cells","root cap cells","vessel elements"),1,"Companion cells provide metabolic support to sieve tube elements."),
                JambDeepCheck("bio-plant-12","Movement of sucrose from a photosynthesising leaf to a developing fruit is",listOf("transpiration","translocation","guttation","diffusion through xylem only"),1,"Translocation is the movement of organic assimilates through phloem from sources to sinks.")
            )
        ),
        JambDeepObjective(
            "Relate mammalian tissues, organs and organ systems to their structures and functions.",
            listOf(
                JambDeepPoint("The four major tissue groups",
                    """Epithelial tissue covers body surfaces, lines cavities and forms glands. Its cells are closely packed with little intercellular material; it protects, absorbs, secretes or permits exchange depending on location. Simple squamous epithelium is thin and suited to diffusion in alveoli and capillaries; cuboidal epithelium commonly performs secretion and absorption in glands and kidney tubules; columnar epithelium lines parts of the gut, where it supports absorption and secretion. Ciliated epithelium moves material along surfaces, such as mucus in airways.

                    Connective tissue supports, binds, protects and transports. Examples include areolar tissue, adipose tissue, cartilage, bone and blood. Muscle tissue contracts: skeletal muscle is usually voluntary and attached to bones; smooth muscle is involuntary and occurs in walls of organs such as the gut and blood vessels; cardiac muscle is involuntary, branched and found in the heart. Nervous tissue consists of neurones and supporting cells and receives, processes and transmits information.""",
                    "Alveoli are lined by very thin squamous epithelium, while the gut contains absorptive columnar epithelial cells.",
                    "Know the four groups: epithelial, connective, muscular and nervous. Link tissue type to its location and function."),
                JambDeepPoint("Organs and organ systems: structure-function examples",
                    """An organ contains two or more tissue types working together. The mammalian heart contains cardiac muscle, connective tissue, blood vessels and nervous tissue; rhythmic contraction pumps blood through pulmonary and systemic circuits. The lungs contain branching bronchi and bronchioles ending in alveoli. Alveoli have thin moist walls, a large combined surface area and a dense capillary network, making diffusion of oxygen into blood and carbon dioxide out efficient. The small intestine has circular folds, villi and microvilli that increase absorptive surface area; epithelial cells absorb digested nutrients, while blood capillaries and lacteals carry absorbed materials away.

                    The kidney contains nephrons, each with a glomerulus and tubule. Ultrafiltration occurs at the glomerulus; selective reabsorption and secretion along the tubule help regulate water, salts and waste. The stomach has muscular walls for churning and glands that secrete gastric juice. A system is a group of organs coordinating a major function. For exam explanations, identify the organ, name the relevant structure and state how that structure enables the function.""",
                    "Alveoli exchange gases; intestinal villi absorb digested nutrients; nephrons filter blood and adjust the composition of urine.",
                    "Heart = pumping; alveoli = gas exchange; villi = absorption; nephron = filtration and selective adjustment of filtrate.")
            ),
            listOf("An organ contains several tissue types; a tissue is not the same as an organ.",
                  "Thin alveolar walls and capillary networks shorten diffusion distance and maintain gradients.",
                  "Villi increase surface area; lacteals absorb most products of fat digestion, while blood capillaries carry many other nutrients."),
            listOf(
                JambDeepCheck("bio-plant-13","Which tissue lines the alveoli to provide a short diffusion distance?",listOf("Stratified squamous epithelium","Simple squamous epithelium","Cartilage","Skeletal muscle"),1,"The alveolar lining is extremely thin, allowing rapid diffusion of respiratory gases."),
                JambDeepCheck("bio-plant-14","The principal function of villi in the small intestine is to",listOf("produce bile","increase surface area for absorption","pump blood","filter urea from blood"),1,"Villi and microvilli greatly increase the surface area available for absorption."),
                JambDeepCheck("bio-plant-15","Which statement correctly distinguishes an organ from a tissue?",listOf("An organ consists of different tissues working together","A tissue is always larger than an organ","An organ contains only one cell","A tissue is a complete organ system"),0,"An organ is made of multiple tissue types that cooperate in a specific function.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-plant-anatomy") objectives else emptyList()
}

/** Interactive practical: identify a tissue section from its arrangement and diagnostic clues. */
@Composable
internal fun PlantMammalSectionLab() {
    data class Slide(val name: String, val source: String, val clues: String, val identification: String, val function: String)
    val slides = listOf(
        Slide("Dicot root", "Young flowering-plant root, transverse section", "Broad cortex; endodermis; central star-shaped xylem with phloem between the arms.", "Typical dicot root", "Root hairs absorb water and ions; the vascular cylinder transports them to the shoot."),
        Slide("Dicot stem", "Young flowering-plant stem, transverse section", "Vascular bundles arranged in a ring; xylem faces inward and phloem outward.", "Typical dicot stem", "The stem supports aerial organs and its vascular bundles conduct water and assimilates."),
        Slide("Monocot stem", "Monocot stem, transverse section", "Vascular bundles are scattered through the ground tissue and are generally closed.", "Typical monocot stem", "Scattered bundles provide transport throughout the stem; ordinary vascular-cambium secondary thickening is absent."),
        Slide("Dicot leaf", "Broad leaf, transverse section", "Upper epidermis; palisade mesophyll; spongy mesophyll with air spaces; lower epidermis with stomata.", "Dorsiventral dicot leaf", "Palisade cells photosynthesise; spongy air spaces support gas diffusion; stomata regulate gas exchange and water loss."),
        Slide("Lung alveolus", "Mammalian lung tissue", "Very thin squamous lining; air space; close association with capillaries.", "Alveolar gas-exchange surface", "Oxygen and carbon dioxide diffuse across a short distance between alveolar air and blood."),
        Slide("Small intestine villus", "Mammalian small-intestine lining", "Finger-like projection; epithelial covering; blood capillary network and central lacteal.", "Intestinal villus", "The villus increases surface area; blood capillaries and lacteal transport absorbed nutrients.")
    )
    var selected by remember { mutableStateOf(0) }
    val slide = slides[selected]
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("VIRTUAL SECTION IDENTIFICATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose a prepared section. Study its clues before revealing the identification and structure-function explanation.")
            slides.chunked(2).forEach { rowSlides ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    rowSlides.forEachIndexed { index, item ->
                        val actualIndex = slides.indexOf(item)
                        OutlinedButton(
                            onClick = { selected = actualIndex },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = if (selected == actualIndex) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface),
                            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 8.dp)
                        ) { Text(item.name, style = MaterialTheme.typography.labelSmall) }
                    }
                    if (rowSlides.size == 1) Spacer(Modifier.weight(1f))
                }
            }
            Text("Specimen: ${slide.source}", fontWeight = FontWeight.SemiBold)
            Text("Observed clues", style = MaterialTheme.typography.labelLarge)
            Text(slide.clues)
            var reveal by remember(selected) { mutableStateOf(false) }
            if (!reveal) {
                Button(onClick = { reveal = true }, modifier = Modifier.fillMaxWidth()) { Text("Reveal identification") }
            } else {
                Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                    Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(slide.identification, fontWeight = FontWeight.Bold)
                        Text(slide.function)
                    }
                }
                TextButton(onClick = { reveal = false }) { Text("Hide answer and try again") }
            }
        }
    }
    BiologyExternalLabLinks("MORE PLANT ANATOMY PRACTICALS", plantAnatomyLabResources)
}
