package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led JAMB treatment of evolution and organism diversity. */
internal object JambBiologyEvolutionContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Analyse the external features and characteristics of Monera, Protista and Fungi.",
            listOf(
                JambDeepPoint("Monera: bacteria and cyanobacteria",
                    """Bacteria are prokaryotic, usually unicellular organisms. They have no membrane-bound nucleus; their circular DNA lies in a nucleoid region, and many also contain small plasmids. A typical bacterial cell has a cell membrane, cytoplasm, ribosomes and usually a cell wall made mainly of peptidoglycan. Some have a capsule, flagella or pili. Common shapes are cocci (spherical), bacilli (rod-shaped), spirilla (spiral) and vibrios (comma-shaped). Bacteria may be autotrophic or heterotrophic, aerobic or anaerobic, and reproduce mainly by binary fission. Under unfavourable conditions some species form resistant endospores; an endospore is a survival structure, not a reproductive spore.

                    Cyanobacteria, traditionally called blue-green algae, are photosynthetic prokaryotes. They contain chlorophyll a and accessory pigments but have no chloroplasts. Some form filaments and specialised heterocysts that fix atmospheric nitrogen. They are bacteria, not true algae, because their cells lack a membrane-bound nucleus and chloroplasts.""",
                    "A bacterial smear may show rod-shaped bacilli; a filamentous cyanobacterium may show chains of cells and, in some species, larger heterocysts.",
                    "A bacterium has ribosomes and DNA but no true nucleus. Cyanobacteria photosynthesise without chloroplasts. Binary fission is the usual reproductive method."),
                JambDeepPoint("Protista: Amoeba, Euglena and Paramecium",
                    """Protists are diverse eukaryotes; the syllabus examples are mainly unicellular. Amoeba has an irregular, changing outline and forms temporary pseudopodia for movement and engulfing food. Its food vacuoles digest captured particles, while a contractile vacuole expels excess water in freshwater habitats. Euglena is elongated, has a pellicle rather than a rigid cellulose cell wall, and commonly has a flagellum, an eyespot and chloroplasts. It can photosynthesise in light and obtain organic nutrients under suitable conditions, so it is described as mixotrophic. Paramecium is slipper-shaped, covered with cilia, and has an oral groove that directs food into the cell. It contains contractile vacuoles and typically has a macronucleus for everyday cell functions and a micronucleus involved in sexual processes.

                    All three possess a true nucleus and membrane-bound organelles. Their specialised structures perform functions that tissues and organs perform in larger organisms. Do not confuse their locomotory structures: Amoeba uses pseudopodia, Euglena uses a flagellum, and Paramecium uses cilia.""",
                    "A labelled comparison diagram should identify pseudopodia in Amoeba, a flagellum and eyespot in Euglena, and cilia plus an oral groove in Paramecium.",
                    "Know the locomotory organelle, feeding method and contractile-vacuole role for each organism. Euglena is photosynthetic but is not a plant cell."),
                JambDeepPoint("Fungi: Rhizopus and mushroom",
                    """Fungi are eukaryotic, non-photosynthetic organisms that obtain nutrients by absorption after extracellular digestion. Their cell walls contain chitin, not cellulose. Many fungi consist of thread-like hyphae; a mass of hyphae is a mycelium. Rhizopus (bread mould) has a cottony mycelium, with stolons spreading over the substrate, rhizoids anchoring and absorbing nutrients, and upright sporangiophores bearing sporangia. Sporangia produce numerous spores for asexual reproduction. Sexual reproduction can occur when compatible hyphae fuse and form a resistant zygospore.

                    A mushroom is the visible fruiting body of certain fungi. Its cap (pileus) bears gills or pores where spores are produced; the stalk (stipe) supports the cap. The underground mycelium absorbs nutrients from decaying material or other substrates. A mushroom is not a plant: it lacks chlorophyll and does not manufacture food by photosynthesis.""",
                    "On mouldy bread, the visible upright pin-like structures are sporangiophores; the fine cottony network is the mycelium.",
                    "Fungal nutrition is absorptive and heterotrophic; cell wall is chitinous. Distinguish the mushroom fruiting body from its feeding mycelium.")
            ),
            listOf("Blue-green algae are cyanobacteria, not eukaryotic algae with chloroplasts.",
                  "Fungal cell walls contain chitin; plant cell walls contain cellulose.",
                  "Amoeba, Euglena and Paramecium are eukaryotic protists, not bacteria.",
                  "A contractile vacuole removes excess water; a food vacuole digests food."),
            listOf(
                JambDeepCheck("bio-evo-01","Which feature confirms that cyanobacteria are prokaryotes?",listOf("They contain chlorophyll","They lack a membrane-bound nucleus","They live in water","They reproduce by cell division"),1,"Photosynthesis and aquatic habitats do not make an organism eukaryotic. Cyanobacteria lack a membrane-bound nucleus."),
                JambDeepCheck("bio-evo-02","Which organism moves mainly by cilia?",listOf("Amoeba","Euglena","Paramecium","Rhizopus"),2,"Paramecium uses numerous cilia for locomotion and to direct food towards its oral groove."),
                JambDeepCheck("bio-evo-03","The cell wall of a typical fungus is mainly made of",listOf("cellulose","chitin","peptidoglycan","lignin"),1,"Chitin is characteristic of fungal cell walls. Peptidoglycan is typical of bacterial cell walls."),
                JambDeepCheck("bio-evo-04","The structure that produces asexual spores in Rhizopus is the",listOf("rhizoid","stolon","sporangium","flagellum"),2,"The sporangium is the sac at the tip of a sporangiophore in which asexual spores develop.")
            )
        ),
        JambDeepObjective(
            "Use observable features to demonstrate increasing structural complexity among the groups studied.",
            listOf(
                JambDeepPoint("From prokaryotic cells to multicellular bodies",
                    """A useful comparison begins with cell organisation, not a claim that every modern organism is a direct ancestor of another. Bacteria are prokaryotic and unicellular. Amoeba, Euglena and Paramecium are eukaryotic but remain unicellular: one cell carries out nutrition, movement, response and reproduction. Many fungi form multicellular hyphae organised as a mycelium, although some fungi are unicellular. Plants show increasing organisation from simple thalloid bodies to forms with specialised tissues, vascular systems, roots, stems and leaves. Animals show increasing organisation from simple tissue-level forms to animals with organ systems.

                    Structural complexity can involve cell specialisation, division of labour, tissue formation, organs, organ systems and more specialised transport, support, nervous and reproductive systems. It is not a simple ladder in which a living bacterium turns into a human. All present-day groups have their own evolutionary histories and are adapted to their environments.""",
                    "Compare a single Paramecium cell with a fern: the fern has specialised roots, stems, leaves and vascular tissues, while Paramecium performs all essential functions in one cell.",
                    "Explain complexity using observable organisation and division of labour. Avoid presenting modern kingdoms as a literal ancestor-to-descendant sequence."),
                JambDeepPoint("Body plans and specialisation",
                    """A body plan describes how an organism's cells, tissues and body parts are arranged. In plants, vascular tissue transports water, mineral salts and manufactured food over distances; true roots and leaves support efficient absorption and photosynthesis. In animals, specialised digestive, respiratory, circulatory, excretory and nervous systems coordinate functions. The degree of specialisation differs among groups: Hydra has tissue-level organisation and a simple body cavity, while an earthworm has organ-system organisation with a complete digestive tract and a closed blood vascular system. Insects have jointed appendages and specialised body regions; vertebrates possess an internal skeleton and a vertebral column.

                    Complexity should be compared with a stated criterion. A larger organism is not automatically more complex, and a simple-looking organism may have highly specialised physiology.""",
                    "Hydra has a sac-like body with one opening, whereas an earthworm has a complete gut with separate mouth and anus and several organ systems.",
                    "Use named structures as evidence. Distinguish tissue-level, organ-level and organ-system-level organisation.")
            ),
            listOf("Evolution is branching, not a straight ladder from bacteria to humans.",
                  "Do not equate larger body size with greater structural complexity.",
                  "State the feature used for comparison, such as vascular tissue or organ systems."),
            listOf(
                JambDeepCheck("bio-evo-05","Which statement best describes increasing structural complexity?",listOf("All organisms become larger through time","Specialised cells and tissues can form organs and organ systems","Every unicellular organism is a juvenile","Modern bacteria are ancestors of every living animal"),1,"Increasing complexity is described by greater organisation and division of labour, not simply size or a linear ladder."),
                JambDeepCheck("bio-evo-06","Which pair best illustrates a difference in animal organisation?",listOf("Hydra: tissue level; earthworm: organ-system level","Hydra: organ-system level; earthworm: cellular level","Both have no specialised tissues","Both are unicellular"),0,"Hydra has tissue-level organisation, while an earthworm has several specialised organ systems.")
            )
        ),
        JambDeepObjective(
            "Trace the life histories of representative organisms and relate reproductive stages to their environments.",
            listOf(
                JambDeepPoint("Life history in algae, mosses and ferns",
                    """Spirogyra is a filamentous green alga. It commonly reproduces vegetatively by fragmentation: a filament breaks and each viable fragment grows. Sexual reproduction occurs by conjugation, in which contents of adjacent cells pass through a conjugation tube and fuse to form a zygospore. The zygospore is a resistant stage that can survive unfavourable conditions; after germination and meiosis, a new haploid filament develops.

                    In mosses such as Brachymenium, the conspicuous green leafy plant is the haploid gametophyte. It produces sperm in antheridia and eggs in archegonia. Sperm require a film of water to swim to the egg. The diploid zygote develops into a sporophyte attached to and nutritionally dependent on the gametophyte; a capsule produces haploid spores by meiosis. In ferns such as Dryopteris, the mature sporophyte is the familiar leafy plant. Sori on the underside of fronds contain sporangia that release spores. A spore grows into a small heart-shaped gametophyte (prothallus), which bears antheridia and archegonia. Water is needed for sperm to reach the egg; the zygote develops into a new sporophyte.""",
                    "A fern life-cycle diagram should show mature sporophyte → sori/sporangia → spores → prothallus → gametes and fertilisation → young sporophyte.",
                    "Mosses have a dominant gametophyte; ferns have a dominant sporophyte. Both require water for fertilisation."),
                JambDeepPoint("Life histories in seed plants and animals",
                    """Seed plants reproduce through pollen and ovules. In gymnosperms, pollen commonly reaches exposed ovules borne on cone scales; seeds are not enclosed in a fruit. In angiosperms, pollen reaches a stigma, a pollen tube grows towards an ovule, and double fertilisation forms the embryo and endosperm. The ovule develops into a seed and the ovary into a fruit. Seed dormancy and dispersal help offspring survive and colonise new sites.

                    Animal life histories vary. A mosquito undergoes complete metamorphosis: egg → aquatic larva → pupa → adult. A butterfly also has egg, larva (caterpillar), pupa and adult stages. A cockroach has incomplete metamorphosis: egg → nymph → adult, with no pupal stage. A frog develops from egg to aquatic tadpole and then to an adult with limbs and lungs, although adults may retain aquatic habits. These stages show how reproduction, development and habitat use are linked.""",
                    "Distinguish a mosquito larva and pupa from the winged adult; the larva and pupa are aquatic, while the adult is mainly aerial/terrestrial.",
                    "Memorise the order of stages and identify which organisms undergo complete or incomplete metamorphosis.")
            ),
            listOf("A spore is not a seed; ferns do not produce seeds.",
                  "Moss gametophyte is dominant; fern sporophyte is dominant.",
                  "A cockroach has no pupal stage; mosquito and butterfly do.",
                  "Pollen removes the need for swimming sperm in seed plants."),
            listOf(
                JambDeepCheck("bio-evo-07","The dominant visible generation in a moss is the",listOf("sporophyte","gametophyte","zygospore","seedling"),1,"The green leafy moss plant is the gametophyte; the sporophyte capsule remains attached to it."),
                JambDeepCheck("bio-evo-08","Which sequence represents complete metamorphosis?",listOf("Egg → nymph → adult","Egg → larva → pupa → adult","Egg → pupa → nymph → adult","Larva → egg → adult"),1,"Mosquitoes and butterflies undergo complete metamorphosis through larval and pupal stages."),
                JambDeepCheck("bio-evo-09","The fern gametophyte is commonly called the",listOf("prothallus","cone","sorus","rhizome"),0,"A fern spore germinates into a small gametophyte called the prothallus."),
                JambDeepCheck("bio-evo-10","In Spirogyra, conjugation results in the formation of a",listOf("flower","zygospore","seed","fruit"),1,"Fusion of cell contents during conjugation produces a resistant zygospore.")
            )
        ),
        JambDeepObjective(
            "Explain how plant reproductive adaptations relate to the transition from aquatic to terrestrial habitats.",
            listOf(
                JambDeepPoint("From water-dependent reproduction to protected embryos",
                    """Green algal ancestors lived in water, where gametes and reproductive cells could move in a moist environment. In bryophytes, the plant body is adapted to land but fertilisation still depends on a surface film of water because flagellated sperm must swim to the egg. Bryophytes lack true vascular tissue and are generally small; water and dissolved substances move mainly over short distances. Their embryos are retained and protected on the parent gametophyte, an important terrestrial adaptation.

                    Pteridophytes have vascular tissues, true roots, stems and leaves. These features allow taller growth and more effective transport, but their flagellated sperm still need water to reach the egg. Seed plants reduce this dependence: pollen carries the male gametophyte and a pollen tube delivers sperm to the ovule. The seed protects the embryo, contains stored food and can remain dormant. This combination allows reproduction in drier terrestrial environments.""",
                    "Mosses commonly grow in damp places because fertilisation needs water; a conifer can reproduce using pollen and seeds without free water for sperm transfer.",
                    "The major sequence is non-vascular spore plants → vascular spore plants → seed plants. Link each innovation to its advantage."),
                JambDeepPoint("Land adaptations and their limits",
                    """Terrestrial plants face risks of drying, gravity and the need to exchange gases while conserving water. A waxy cuticle reduces water loss; stomata regulate gas exchange; lignified vascular tissues support the plant and transport materials; roots anchor the plant and absorb water and minerals. Seeds protect developing embryos, while pollen permits fertilisation without a film of external water. These adaptations do not mean every plant is independent of water for all stages: water remains essential for metabolism and growth, and some plants are specialised for aquatic habitats.

                    The transition was gradual and involved several interacting adaptations. Do not state that bryophytes are completely aquatic or that ferns have seeds. The key contrast is that bryophytes and pteridophytes still need external water for fertilisation, while seed plants use pollen and a pollen tube.""",
                    "A fern has roots and xylem but reproduces by spores and requires water for fertilisation; a flowering plant forms pollen and seeds.",
                    "Separate adaptations for transport/support from adaptations for fertilisation and embryo protection.")
            ),
            listOf("Ferns are vascular plants but are seedless.",
                  "Bryophytes have no true vascular tissue and still require water for fertilisation.",
                  "Pollen and pollen tubes reduce dependence on external water for fertilisation; seeds protect embryos."),
            listOf(
                JambDeepCheck("bio-evo-11","Which innovation most directly removes the need for a film of water for sperm to reach the egg?",listOf("Rhizoids","Pollen and pollen tube","Spore capsule","Cuticle alone"),1,"Pollen transports the male gametophyte and the pollen tube delivers sperm to the ovule."),
                JambDeepCheck("bio-evo-12","Why are bryophytes commonly associated with moist habitats?",listOf("They have no chlorophyll","Their sperm need water for fertilisation and they lack vascular tissue","They produce fruits","They have deep woody roots"),1,"Bryophytes lack vascular tissue and their flagellated sperm require water to reach the egg.")
            )
        ),
        JambDeepObjective(
            "Trace major plant groups and distinguish their diagnostic structures and reproductive features.",
            listOf(
                JambDeepPoint("Thallophyta, Bryophyta and Pteridophyta",
                    """In the syllabus grouping, Thallophyta is represented by Spirogyra, a simple filamentous alga with no true roots, stems, leaves or vascular tissue. Its cells contain spiral chloroplasts and it commonly reproduces by fragmentation and conjugation. Bryophyta includes mosses and liverworts such as Brachymenium and Marchantia. They have simple leaf-like and stem-like structures or a thallus, rhizoids for anchorage, no true vascular bundles, and reproduce by spores. The gametophyte is the dominant generation and fertilisation requires water.

                    Pteridophyta includes ferns such as Dryopteris. Ferns have true roots, stems and leaves, vascular tissues and a dominant sporophyte. They reproduce by spores in sporangia, often grouped in sori on fronds, and do not form flowers, fruits or seeds. Their sperm still require water for fertilisation. These features provide a reliable way to distinguish the three groups in specimen-based questions.""",
                    "A specimen with sori on the underside of fronds is a fern; a small non-vascular plant with a capsule on a stalk is a moss.",
                    "Use vascular tissue, true organs, dominant generation and reproductive unit to identify each group."),
                JambDeepPoint("Spermatophyta: gymnosperms and angiosperms",
                    """Spermatophytes are seed-producing plants. Gymnosperms, including cycads and conifers, bear ovules and seeds that are not enclosed within an ovary-derived fruit. Many produce male and female cones; conifers commonly have needle-like or scale-like leaves and woody stems. Angiosperms are flowering plants: ovules are enclosed in an ovary, and after fertilisation the ovary develops into a fruit. Their flowers contain reproductive structures and may be adapted for wind, insect, bird or other pollination.

                    Angiosperms include monocotyledons and dicotyledons in the traditional school-level classification. Maize is a monocot: it typically has one cotyledon, parallel leaf venation, fibrous roots and floral parts in threes. Waterleaf is treated as a dicot example in the syllabus: dicots typically have two cotyledons, net-like venation, a taproot system and floral parts in fours or fives. These are general patterns; identify the specimen using several features rather than one alone.""",
                    "A maize seed has one cotyledon and the mature plant commonly has parallel-veined leaves; a conifer seed is exposed on a cone scale rather than enclosed in a fruit.",
                    "Gymnosperm means naked seed; angiosperm means enclosed seed. Monocot/dicot comparisons are characteristic patterns, not exception-free laws.")
            ),
            listOf("A fern has spores, not seeds; a moss has no true vascular tissue.",
                  "Gymnosperms produce seeds but no fruits; angiosperms produce flowers and fruits.",
                  "Maize is a monocot; waterleaf is a dicot example in the syllabus.",
                  "Do not identify a plant from leaf shape alone."),
            listOf(
                JambDeepCheck("bio-evo-13","Which feature distinguishes a fern from a seed plant?",listOf("Presence of chlorophyll","Production of spores rather than seeds","Presence of cellulose","Multicellular body"),1,"Ferns reproduce by spores and do not produce seeds, flowers or fruits."),
                JambDeepCheck("bio-evo-14","The seed of a gymnosperm is described as naked because it",listOf("has no embryo","is not enclosed in an ovary-derived fruit","has no seed coat","cannot germinate"),1,"Gymnosperm ovules and seeds are exposed rather than enclosed within an ovary that develops into a fruit."),
                JambDeepCheck("bio-evo-15","Which combination is typical of maize?",listOf("Two cotyledons and net venation","One cotyledon and parallel venation","Naked seeds and cones","Spores and sori"),1,"Maize is a monocot with one cotyledon and typically parallel-veined leaves.")
            )
        ),
        JambDeepObjective(
            "Classify plants as annuals, biennials and perennials according to life-cycle duration.",
            listOf(
                JambDeepPoint("Annual, biennial and perennial plants",
                    """An annual completes its life cycle from germination through growth, flowering, seed production and death within one growing season or year. Examples include maize and many beans. A biennial normally completes vegetative growth in the first season, often storing food in an underground or swollen organ, then flowers, produces seeds and dies in the second season. Carrot and cabbage are common biennial examples when allowed to complete their full life cycle, although they may be harvested before flowering. A perennial lives for more than two years and may flower and produce seeds repeatedly; examples include mango, guava and many grasses.

                    Classification depends on the natural life cycle, not how long a farmer chooses to keep a crop. A biennial harvested for its leaves or root in year one has not completed its natural two-season cycle. Environmental conditions and cultivation can alter timing, so use the normal life-history pattern in examination answers.""",
                    "Maize is an annual; carrot is usually biennial; mango is perennial. A carrot harvested in its first season has not yet completed its full biennial cycle.",
                    "Annual = one season/year; biennial = two growing seasons; perennial = more than two years.")
            ),
            listOf("Biennial does not mean twice a year.",
                  "Harvesting a biennial before flowering does not change its natural life-cycle category.",
                  "Perennial plants may lose leaves seasonally and still remain alive."),
            listOf(
                JambDeepCheck("bio-evo-16","A plant that grows vegetatively in the first season and flowers in the second is a",listOf("annual","biennial","perennial","parasite"),1,"A biennial normally completes its life cycle over two growing seasons."),
                JambDeepCheck("bio-evo-17","Which is a typical perennial plant?",listOf("Maize","Mango","A short-lived annual bean crop","A biennial carrot"),1,"Mango lives for many years and can flower and fruit repeatedly, so it is perennial.")
            )
        ),
        JambDeepObjective(
            "Trace advancement among the major invertebrate animal groups using body organisation and specialised structures.",
            listOf(
                JambDeepPoint("From simple body plans to segmented animals",
                    """Hydra represents the cnidarians (coelenterates): it has radial symmetry, a sac-like body with one opening, tentacles bearing stinging cells (cnidocytes) and tissue-level organisation. Taenia, a flatworm, is bilaterally symmetrical, dorsoventrally flattened, unsegmented and acoelomate; it has no digestive tract and absorbs nutrients through its body surface. Nematodes are cylindrical, unsegmented roundworms with a pseudocoelom and a complete digestive tract with mouth and anus. Earthworms are annelids with repeated segments, a true coelom, a complete gut and a closed blood vascular system.

                    These comparisons show changes in symmetry, body cavity, segmentation and organ-system development. They are useful teaching comparisons, but evolution is branching rather than a simple line in which every group directly evolved into the next.""",
                    "A cross-section helps distinguish a flatworm without a body cavity from a roundworm with a pseudocoelom and an annelid with a true coelom.",
                    "Hydra: radial, one opening, cnidocytes. Taenia: flat, acoelomate, no gut. Nematode: round, pseudocoelom, complete gut. Earthworm: segmented, coelomate, closed circulation."),
                JambDeepPoint("Arthropods and molluscs",
                    """Arthropods have a segmented body, jointed appendages, a chitinous exoskeleton and an open circulatory system. Their exoskeleton provides support and protection but must be shed during growth by moulting. Insects have three body regions (head, thorax and abdomen), three pairs of legs and usually one pair of antennae; many have wings. The named syllabus examples include mosquito, cockroach, housefly, bee and butterfly. Arachnids such as spiders have four pairs of legs and no antennae, so they are not insects.

                    Molluscs such as snails generally have a soft unsegmented body, a muscular foot, a visceral mass and a mantle; many land snails have a shell. Most molluscs possess a radula for feeding, although it is absent in bivalves. Compare these structures with the jointed limbs and exoskeleton of arthropods. Classification questions often test the body plan, not just habitat.""",
                    "A cockroach has three pairs of jointed legs and a head, thorax and abdomen; a snail has a muscular foot and mantle.",
                    "Insects have six legs; spiders have eight. Arthropods moult their exoskeleton. Molluscs have a mantle and muscular foot.")
            ),
            listOf("Do not describe Taenia as having a complete digestive tract; it absorbs nutrients.",
                  "Nematodes are unsegmented; annelids are segmented.",
                  "Insects have three pairs of legs, not four.",
                  "Arthropods have an exoskeleton; vertebrates have an internal vertebral column."),
            listOf(
                JambDeepCheck("bio-evo-18","Which animal has stinging cells on its tentacles?",listOf("Taenia","Hydra","Earthworm","Snail"),1,"Hydra is a cnidarian and bears cnidocytes used in prey capture and defence."),
                JambDeepCheck("bio-evo-19","Which feature distinguishes an annelid such as an earthworm from a nematode?",listOf("Bilateral symmetry","A complete gut","Segmentation and a true coelom","A cylindrical body"),2,"Earthworms are segmented coelomate annelids; nematodes are unsegmented and pseudocoelomate."),
                JambDeepCheck("bio-evo-20","An adult insect typically has",listOf("four pairs of legs","three pairs of legs","five pairs of legs","one pair of legs"),1,"Insects have six legs, arranged as three pairs on the thorax."),
                JambDeepCheck("bio-evo-21","Which structure is characteristic of a snail?",listOf("Jointed appendages","Mantle and muscular foot","Cnidocytes","Vertebral column"),1,"A typical snail is a mollusc with a mantle, muscular foot and visceral mass.")
            )
        ),
        JambDeepObjective(
            "Explain the ecological and economic importance of the insects studied.",
            listOf(
                JambDeepPoint("Useful roles of insects",
                    """Insects perform essential ecosystem services. Bees, butterflies and some flies pollinate flowering plants as they move between flowers. Many insects decompose dead material or feed on dung, helping nutrient recycling. Predatory insects such as some ladybird beetles and dragonflies reduce populations of pest organisms. Insects are food for fish, amphibians, reptiles, birds and mammals. Honeybees provide honey and beeswax, while silkworm larvae produce silk. Some insects are used in biological control and scientific research.

                    These roles can have direct economic value through crop pollination, useful products and reduced pest damage. The same species may be beneficial in one context and harmful in another; assessment should specify the ecological role and the human consequence.""",
                    "A bee visiting a flower transfers pollen between flowers and may increase fruit or seed production; honey and beeswax are additional products.",
                    "State the insect, its role and the resulting ecological or economic benefit."),
                JambDeepPoint("Harmful roles and disease transmission",
                    """Mosquitoes are important vectors: female Anopheles mosquitoes transmit malaria parasites, while Aedes mosquitoes can transmit viruses such as dengue and yellow fever in regions where those viruses circulate. Houseflies can mechanically transfer disease-causing organisms from faeces and refuse to food. Some mosquitoes and flies also cause nuisance and blood loss. Cockroaches contaminate food and surfaces and may worsen allergies or asthma in susceptible people. Caterpillars and other insect pests can damage crops; stored-product insects damage grains and other food supplies.

                    Control should be matched to the biology of the insect and should protect people and the environment. Examples include removing stagnant water where mosquitoes breed, covering food, proper waste management, insecticide-treated nets for malaria prevention and integrated pest management for crops. Do not say all mosquitoes transmit malaria: malaria transmission is associated with infected female Anopheles mosquitoes.""",
                    "A housefly may carry microbes mechanically from refuse to uncovered food; it does not need to reproduce in the food to contaminate it.",
                    "Differentiate biological vectors from mechanical carriers. Name the correct mosquito genus for malaria transmission.")
            ),
            listOf("Only female Anopheles mosquitoes transmit human malaria parasites.",
                  "Houseflies are commonly mechanical carriers, not the biological vector of malaria.",
                  "Pollination and decomposition are beneficial insect roles; crop damage and disease transmission are harmful roles."),
            listOf(
                JambDeepCheck("bio-evo-22","Which insect is the principal vector of human malaria?",listOf("Male Aedes","Female Anopheles","Housefly","Honeybee"),1,"Female Anopheles mosquitoes transmit Plasmodium parasites to humans."),
                JambDeepCheck("bio-evo-23","The main ecological role of bees in many flowering ecosystems is",listOf("pollination","soil erosion","nitrogen fixation","predation on all crop pests"),0,"Bees transfer pollen between flowers and support plant reproduction and crop production."),
                JambDeepCheck("bio-evo-24","Houseflies commonly spread pathogens by",listOf("injecting venom","mechanically transferring microbes from contaminated materials to food","fixing nitrogen","producing pollen"),1,"Houseflies can carry microbes on their body surfaces and in regurgitated or faecal material.")
            )
        ),
        JambDeepObjective(
            "Trace advancement among vertebrate groups and relate their features to aquatic and terrestrial life.",
            listOf(
                JambDeepPoint("Fish and amphibians",
                    """Fish are aquatic vertebrates with a streamlined body, fins and gills. Cartilaginous fish such as sharks have a skeleton mainly of cartilage, exposed gill slits and no swim bladder. Bony fish have a skeleton mainly of bone, gills usually covered by an operculum and commonly a swim bladder for buoyancy. Scales and mucus protect the body and reduce friction.

                    Amphibians such as frogs and toads typically have moist glandular skin, limbs and a life cycle that includes aquatic eggs and larvae. Tadpoles generally use gills; adults use lungs and their skin for gas exchange. Most require water or moist conditions for reproduction because their eggs lack a hard protective shell and are vulnerable to drying. Amphibians therefore illustrate a partial transition to land while retaining strong links to water.""",
                    "A shark has exposed gill slits and a cartilaginous skeleton; a typical bony fish has an operculum covering the gills.",
                    "Cartilaginous versus bony fish: skeleton, gill covering and buoyancy structure. Amphibians: moist skin and water-dependent reproduction."),
                JambDeepPoint("Reptiles, birds and mammals",
                    """Reptiles such as lizards, snakes and turtles have dry, keratinised scales or scutes, lungs and internal fertilisation. Their amniotic eggs have protective membranes and usually leathery or hard shells, reducing dependence on water for reproduction. Birds have feathers, wings, a beak, lungs with air sacs and a four-chambered heart; they are endothermic and lay amniotic eggs with hard shells. Mammals have hair or fur, mammary glands that produce milk, lungs and a four-chambered heart; most give birth to live young, although monotremes lay eggs.

                    The amniotic egg and internal fertilisation are important adaptations for reproduction away from open water. Birds and mammals maintain a relatively constant internal body temperature, while most fish, amphibians and reptiles are ectothermic. These are broad patterns, and exceptions should be recognised where relevant.""",
                    "A lizard has dry scales and lungs; a bird has feathers and a beak; a mammal feeds its young with milk.",
                    "Identify reptiles by dry scales and amniotic reproduction; birds by feathers; mammals by hair and mammary glands.")
            ),
            listOf("Amphibians have moist skin; reptiles have dry keratinised scales.",
                  "Birds are vertebrates with feathers, not simply animals that fly.",
                  "Mammals are defined by mammary glands and hair, not by giving birth to live young alone.",
                  "A shark is cartilaginous; a typical tilapia is bony."),
            listOf(
                JambDeepCheck("bio-evo-25","Which feature is typical of cartilaginous fish?",listOf("Operculum covering the gills","Skeleton mainly of cartilage","Feathers","Four limbs"),1,"Cartilaginous fish such as sharks have skeletons composed mainly of cartilage."),
                JambDeepCheck("bio-evo-26","The moist skin of many amphibians is important for",listOf("gas exchange","producing feathers","forming a hard shell","making milk"),0,"Many amphibians exchange gases through their moist skin as well as through lungs."),
                JambDeepCheck("bio-evo-27","Which feature is unique to mammals among the listed vertebrate classes?",listOf("Lungs","A backbone","Mammary glands that produce milk","Internal fertilisation"),2,"Mammary glands producing milk are the defining mammalian feature."),
                JambDeepCheck("bio-evo-28","Which adaptation allows reptiles to reproduce more independently of open water?",listOf("External fertilisation","Amniotic egg with protective membranes","Gills in the adult","Moist permeable skin"),1,"The amniotic egg protects the embryo and reduces the need for a free-standing aquatic environment.")
            )
        ),
        JambDeepObjective(
            "Assess the economic and environmental importance of representative animals.",
            listOf(
                JambDeepPoint("Useful and harmful effects of animals",
                    """Animals support ecosystems and human livelihoods in many ways. Earthworms improve soil structure and aeration and help incorporate organic matter. Snails and other molluscs form part of food webs and are consumed in some communities, although some species damage crops or act as intermediate hosts for parasites. Fish provide protein and livelihoods; amphibians consume many insects and are indicators of environmental change; birds pollinate some plants, disperse seeds and control pests. Mammals provide food, fibre, transport and other services, while predators help regulate prey populations.

                    Harmful effects also matter. Some flatworms and roundworms are parasites of humans, livestock or crops. Mosquitoes and some other animals transmit disease. Rodents may damage stored food and spread pathogens. Large herbivores can damage crops when populations are poorly managed. An accurate answer identifies the species or group, the interaction and its consequence rather than labelling an entire group as useful or harmful.""",
                    "Earthworms improve soil structure, while a parasitic tapeworm obtains nutrients from its host and may cause disease.",
                    "Use balanced examples: food, pollination, pest control, soil improvement, disease, parasitism and crop damage."),
                JambDeepPoint("Conservation and ecosystem balance",
                    """Animals are linked through food chains, pollination networks, decomposition and nutrient cycling. Removing a predator can allow prey populations to increase; loss of pollinators can reduce the reproduction of some plants; overfishing can alter aquatic food webs. Habitat destruction, pollution, invasive species, overharvesting and climate change can reduce animal populations. Conservation measures include protecting habitats, sustainable harvesting, controlling pollution, maintaining wildlife corridors and enforcing relevant laws.

                    Economic value should not be judged only by immediate market price. A species may have indirect value through ecosystem services, genetic resources, tourism or maintaining ecological balance. Conservation decisions often weigh livelihood needs against long-term ecosystem health.""",
                    "Protecting mangrove and wetland habitats supports fish nurseries, feeding sites for birds and shoreline protection.",
                    "Explain direct economic value and indirect ecosystem value. Link threats to a realistic conservation response.")
            ),
            listOf("Do not claim every member of a group has the same economic effect.",
                  "A parasite benefits at the host's expense; a predator kills and consumes prey.",
                  "Environmental importance includes ecosystem services, not only products sold by humans."),
            listOf(
                JambDeepCheck("bio-evo-29","Earthworms contribute to soil fertility mainly by",listOf("preventing all decomposition","improving aeration and mixing organic matter into soil","producing chlorophyll","fixing atmospheric nitrogen in root nodules"),1,"Earthworm burrowing improves aeration and mixing of organic matter, supporting soil processes."),
                JambDeepCheck("bio-evo-30","A balanced assessment of an animal's importance should",listOf("consider only its market price","consider its ecological role and effects on people","assume all members are pests","ignore food-web relationships"),1,"Animals may have direct economic uses and indirect ecosystem roles; both should be considered.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-classification") objectives else emptyList()
}

internal data class EvolutionSpecimen(
    val name: String,
    val group: String,
    val evidence: String,
    val diagnosticFeature: String
)

@Composable
internal fun EvolutionClassificationLab() {
    val specimens = listOf(
        EvolutionSpecimen("Amoeba", "Protista", "Irregular outline; temporary pseudopodia; food and contractile vacuoles.", "Pseudopodia are used for movement and engulfing food."),
        EvolutionSpecimen("Euglena", "Protista", "Elongated cell; flagellum; eyespot; chloroplasts; flexible pellicle.", "A flagellum and photosynthetic chloroplasts occur in the same unicellular organism."),
        EvolutionSpecimen("Paramecium", "Protista", "Slipper-shaped cell covered in cilia; oral groove; contractile vacuoles.", "Cilia provide locomotion and help direct food into the oral groove."),
        EvolutionSpecimen("Rhizopus", "Fungi", "Cottony mycelium; rhizoids; stolons; upright sporangiophores and sporangia.", "A sporangium produces asexual spores; the fungal wall contains chitin."),
        EvolutionSpecimen("Moss", "Bryophyta", "Small non-vascular plant; rhizoids; leafy gametophyte; capsule on a seta.", "The gametophyte is dominant and sperm require water for fertilisation."),
        EvolutionSpecimen("Fern", "Pteridophyta", "True roots, stems and fronds; sori on frond undersides; vascular tissue.", "Sori contain sporangia; the plant reproduces by spores, not seeds."),
        EvolutionSpecimen("Maize", "Angiosperm monocot", "One cotyledon; parallel leaf venation; fibrous roots; flowers in threes.", "A monocot typically has one cotyledon and parallel venation."),
        EvolutionSpecimen("Snail", "Mollusca", "Soft unsegmented body; muscular foot; mantle; usually a shell.", "The muscular foot and mantle are characteristic molluscan structures."),
        EvolutionSpecimen("Earthworm", "Annelida", "Elongated segmented body; moist skin; bristles in many species; true coelom.", "Repeated body segments and a true coelom distinguish annelids."),
        EvolutionSpecimen("Bee", "Arthropoda: Insecta", "Head, thorax and abdomen; three pairs of jointed legs; antennae; wings.", "Six legs and three body regions identify an insect; bees also pollinate flowers."),
        EvolutionSpecimen("Frog", "Amphibia", "Moist skin; strong hind limbs; aquatic tadpole stage; adult lungs and skin respiration.", "Amphibians commonly have aquatic larvae and remain dependent on moist conditions."),
        EvolutionSpecimen("Bird", "Aves", "Feathers; wings; beak; lungs with air sacs; hard-shelled amniotic eggs.", "Feathers are diagnostic of birds; flight is not required for classification."),
        EvolutionSpecimen("Mammal", "Mammalia", "Hair or fur; mammary glands; lungs; differentiated teeth in many species.", "Mammary glands that produce milk are the defining mammalian feature.")
    )
    var selectedName by remember { mutableStateOf("Amoeba") }
    val selected = specimens.first { it.name == selectedName }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("VIRTUAL SPECIMEN IDENTIFICATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text("Choose a specimen, inspect its evidence and identify the feature that supports its classification.", style = MaterialTheme.typography.bodyMedium)
                specimens.chunked(3).forEach { group ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        group.forEach { specimen ->
                            OutlinedButton(
                                onClick = { selectedName = specimen.name },
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 7.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (selectedName == specimen.name) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
                                )
                            ) { Text(specimen.name, style = MaterialTheme.typography.labelSmall) }
                        }
                        repeat(3 - group.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
                Text(selected.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Classification: ${selected.group}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                Text("Observed evidence", fontWeight = FontWeight.SemiBold)
                Text(selected.evidence)
                Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                    Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("DIAGNOSTIC FEATURE", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                        Text(selected.diagnosticFeature)
                    }
                }
            }
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("INVESTIGATION TASK", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("Select three organisms from different groups. For each, record two visible or described features, identify its group, and explain why one feature is diagnostic. Then compare how its body organisation differs from a unicellular organism.")
                Text("Practical skill: classification from observable evidence, not guesswork from habitat or size.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
