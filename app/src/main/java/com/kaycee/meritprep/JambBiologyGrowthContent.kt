package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB Biology Topic 8: growth, seed germination and germination types. */
internal object JambBiologyGrowthContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Define growth in living organisms and distinguish growth from development.",
            listOf(
                JambDeepPoint("Meaning and measurement of growth",
                    """Growth is a permanent or irreversible increase in size, dry mass, volume or number of cells of an organism, resulting from synthesis of new protoplasm and other cellular materials. In multicellular organisms, growth commonly involves cell division, cell enlargement and differentiation. A temporary increase in mass after drinking water or feeding is not, by itself, biological growth because it may not represent the formation of new living material.

                    Growth can be measured by increase in height, length, volume, fresh mass, dry mass or cell number. Dry mass is often a more reliable measure of accumulated organic material than fresh mass because fresh mass changes with water content. In plants, growth can be measured using a ruler, balance or auxanometer depending on the scale and type of growth.""",
                    "A seedling increasing in dry mass as it forms new tissues is showing growth; a sponge swelling after absorbing water is not necessarily growing.",
                    "Growth is a permanent increase in living material; distinguish it from temporary swelling or change in water content."),
                JambDeepPoint("Cell division, enlargement and differentiation",
                    """Growth in plants is concentrated in meristematic regions where cells divide by mitosis. Newly formed cells then enlarge, often through water uptake and vacuole expansion, and differentiate into specialised tissues. In animals, growth involves cell division and enlargement in tissues, although the timing and distribution vary among organs. Cell division increases cell number; enlargement increases cell size; differentiation produces specialised cell structures and functions.

                    These processes are coordinated by genes, hormones, nutrients and environmental conditions. Plant growth can continue at apical and lateral meristems throughout life, whereas many animal tissues have more limited growth zones or regenerative capacity.""",
                    "A root tip lengthens because meristematic cells divide and cells behind the tip elongate.",
                    "Do not equate growth with cell division alone; cell enlargement and synthesis of new material also contribute."),
                JambDeepPoint("Growth versus development",
                    """Development is the wider process of changes in form and function during an organism's life. It includes growth, differentiation, maturation, ageing and changes in reproductive ability. Growth is quantitative, involving increase in size or mass; development includes qualitative changes in organisation and function. A tadpole developing limbs and changing into a frog undergoes development as well as growth.

                    Organisms may develop without a large increase in overall size at every stage. Metamorphosis involves major developmental changes in body plan, while seed germination and seedling establishment include growth and differentiation. In exam questions, use measurements to describe growth and structural or functional changes to describe development.""",
                    "A caterpillar grows in mass and later undergoes metamorphosis into a butterfly; metamorphosis is development.",
                    "Growth is an increase in size or living material; development includes broader changes in structure and function.")
            ),
            listOf("Growth is not temporary swelling caused only by water uptake.", "Dry mass can be more reliable than fresh mass when water content varies.", "Development includes growth but is broader than growth."),
            listOf(
                JambDeepCheck("bio-growth-01","Which statement best defines biological growth?",listOf("Temporary increase in water content","Permanent increase in size, dry mass or cell number","Movement towards a stimulus","Removal of metabolic waste"),1,"Growth is a lasting increase in living material, commonly measured by size, dry mass or cell number."),
                JambDeepCheck("bio-growth-02","Why is dry mass often preferred to fresh mass when measuring plant growth?",listOf("It includes more water","It is less affected by changing water content","It measures only mineral salts","It cannot be measured"),1,"Fresh mass varies with hydration; dry mass better reflects accumulated organic material."),
                JambDeepCheck("bio-growth-03","The change of a tadpole into a frog is an example of",listOf("egestion","development","excretion only","pollination"),1,"Metamorphosis is a developmental change in form and function.")
            )
        ),
        JambDeepObjective(
            "Explain the conditions necessary for seed germination and relate them to seedling growth.",
            listOf(
                JambDeepPoint("Water and oxygen",
                    """A viable seed requires water to begin germination. Water is absorbed through the seed coat, causing imbibition and swelling. It softens the testa, rehydrates tissues and activates enzymes that mobilise stored food. The embryo uses the soluble products for respiration and growth. Without adequate water, metabolic activity remains low and the seed cannot complete germination.

                    Oxygen is required for aerobic respiration, which releases energy from stored food for cell division and elongation. Waterlogged soil may contain too little oxygen because air spaces are filled with water; seeds can then respire poorly and may decay. Some species tolerate low oxygen better than others, but oxygen is a standard requirement for normal germination of most seeds.""",
                    "A seed in moist, aerated soil can germinate; a seed submerged in stagnant water may lack oxygen.",
                    "Water activates metabolism and mobilises food; oxygen supports aerobic respiration and energy supply."),
                JambDeepPoint("Suitable temperature and seed viability",
                    """Germination requires a suitable temperature range for enzyme activity, membrane function and respiration. Low temperatures slow metabolic reactions; excessively high temperatures can denature enzymes and damage the embryo. The optimum temperature differs among species and reflects the environment to which the plant is adapted. Many common crop seeds germinate well in warm conditions, but do not assume all species have the same optimum.

                    A seed must also be viable: its embryo must be alive and capable of growth. Seed age, storage humidity, temperature, pests and disease affect viability. Some seeds remain dormant even when water, oxygen and temperature are suitable because of hard seed coats, chemical inhibitors or an immature embryo. Dormancy-breaking requirements may include scarification, chilling, light exposure or after-ripening, depending on the species.""",
                    "Aged seeds stored in warm, damp conditions may lose viability; some temperate species require a period of chilling before germination.",
                    "Suitable temperature is species-dependent; viability and dormancy are distinct from the external germination conditions."),
                JambDeepPoint("Light, stored food and early seedling establishment",
                    """Light is not an absolute requirement for germination of every seed. Many seeds germinate in darkness because the embryo initially uses stored food in the cotyledons or endosperm. Some species have light-sensitive seeds and require light or darkness as a germination cue. Once the seedling emerges, light is generally needed for photosynthesis and continued independent growth.

                    During germination, enzymes digest stored starch, proteins and lipids into soluble substances that can be transported to the embryo. The radicle usually emerges first and develops into the primary root; the plumule develops into the shoot. As leaves expand and become photosynthetically active, the seedling gradually becomes less dependent on stored food.""",
                    "A bean can germinate underground without light by using food stored in its cotyledons, but the emerging seedling needs light for sustained photosynthesis.",
                    "Water, oxygen and suitable temperature are general requirements; light requirement varies by species, and stored food supports early growth.")
            ),
            listOf("Most seeds need water, oxygen and a suitable temperature; requirements differ among species.", "Waterlogged conditions can limit oxygen supply.", "Light is not required for germination of every seed, although it is usually needed for later photosynthesis."),
            listOf(
                JambDeepCheck("bio-growth-04","The main role of water during seed germination is to",listOf("prevent enzyme activity","activate metabolism and mobilise stored food","replace the embryo","stop respiration"),1,"Water rehydrates the seed, activates enzymes and enables stored food to be mobilised."),
                JambDeepCheck("bio-growth-05","A seed in waterlogged soil may fail to germinate normally because",listOf("there is too much oxygen","oxygen diffusion to the seed is reduced","light is always absent","the seed coat becomes a leaf"),1,"Water fills soil air spaces and can reduce oxygen available for aerobic respiration."),
                JambDeepCheck("bio-growth-06","Which statement about light and germination is correct?",listOf("All seeds require light to germinate","No seed can germinate in darkness","Light requirement varies among species","Light replaces oxygen"),2,"Some seeds are light-sensitive, but many germinate using stored food without light.")
            )
        ),
        JambDeepObjective(
            "Differentiate epigeal and hypogeal germination using the position of cotyledons and the growth of the hypocotyl or epicotyl.",
            listOf(
                JambDeepPoint("Epigeal germination",
                    """In epigeal germination, the hypocotyl elongates and lifts the cotyledons above the soil surface. The cotyledons may become green and photosynthetic for a period, or they may supply stored food before shrivelling. The plumule develops into the shoot and the radicle forms the root system. The cotyledons are therefore visible above ground during early seedling growth.

                    Epigeal germination occurs in beans and castor. The key diagnostic feature is not simply that the seedling emerges above the soil, because both types do; it is that the cotyledons are carried above the ground by elongation of the hypocotyl.""",
                    "In a germinating bean, the curved hypocotyl lifts the cotyledons above the soil.",
                    "Epigeal = cotyledons above ground; hypocotyl elongates."),
                JambDeepPoint("Hypogeal germination",
                    """In hypogeal germination, the epicotyl elongates while the cotyledons remain below the soil surface. The cotyledons continue to supply stored food to the growing embryo and may remain underground until their reserves are used. The plumule emerges above ground, while the radicle develops into roots. Pea and maize are commonly used as examples of hypogeal germination in school biology.

                    In maize, the cotyledon is represented by the scutellum, which remains within the grain and transfers stored food to the embryo. The protective coleoptile surrounds the emerging plumule, and the coleorhiza protects the radicle. The cotyledon position and which embryonic axis elongates are the main differences from epigeal germination.""",
                    "In pea, the cotyledons remain below ground while the epicotyl carries the plumule upwards.",
                    "Hypogeal = cotyledons remain below ground; epicotyl elongates."),
                JambDeepPoint("Compare and identify seedlings",
                    """Both epigeal and hypogeal germination begin with water uptake, enzyme activation, respiration and emergence of the radicle. In both, the plumule develops into the shoot and the radicle into the root. The distinction concerns which region elongates to position the cotyledons. Epigeal: hypocotyl elongates and cotyledons rise above soil. Hypogeal: epicotyl elongates and cotyledons stay below soil.

                    When examining a seedling, locate the cotyledons first, then identify the hypocotyl (region below the cotyledons) and epicotyl (region above the cotyledons). Do not classify by seed size or whether the plant is a monocot or dicot alone: both patterns are found among dicot plants, while maize is a familiar monocot example of hypogeal germination.""",
                    "Bean is epigeal; pea and maize are hypogeal in common school examples.",
                    "Use cotyledon position and the elongating axis; do not rely only on whether the seedling is a monocot or dicot.")
            ),
            listOf("Epigeal: hypocotyl elongates and lifts cotyledons above soil.", "Hypogeal: epicotyl elongates and cotyledons remain below soil.", "Both types involve radicle emergence and plumule development."),
            listOf(
                JambDeepCheck("bio-growth-07","In epigeal germination, the cotyledons are lifted above the soil mainly by elongation of the",listOf("radicle","hypocotyl","epicotyl","coleorhiza"),1,"The hypocotyl elongates and raises the cotyledons above the ground."),
                JambDeepCheck("bio-growth-08","Which is a common example of hypogeal germination?",listOf("Bean","Castor","Pea","Cotton"),2,"Pea is a common example in which cotyledons remain below the soil."),
                JambDeepCheck("bio-growth-09","The main feature distinguishing hypogeal from epigeal germination is that in hypogeal germination",listOf("the radicle never emerges","cotyledons remain below the soil","the plumule fails to develop","water is not absorbed"),1,"In hypogeal germination, the epicotyl elongates and the cotyledons remain below ground.")
            )

        ),
        JambDeepObjective(
            "Explain the cell cycle and describe the stages and significance of mitosis.",
            listOf(
                JambDeepPoint("The cell cycle and interphase",
                    """The cell cycle is the ordered sequence of events through which a cell grows, duplicates its DNA and divides. It is commonly described as interphase followed by the mitotic phase. Interphase is not a resting period: the cell is metabolically active and prepares for division. In G1, the cell grows, makes proteins and organelles, and carries out normal functions. During S phase, DNA is replicated so that each chromosome consists of two genetically similar sister chromatids joined at a centromere. In G2, the cell continues to grow, checks replicated DNA and prepares the machinery needed for division.

                    DNA replication doubles the amount of DNA, but it does not double the chromosome number when chromosomes are counted by centromeres. A human somatic cell has 46 chromosomes before S phase and still has 46 duplicated chromosomes after S phase; each duplicated chromosome contains two sister chromatids.""",
                    "A cell with 46 chromosomes enters S phase; after DNA replication it still has 46 chromosomes, but 92 chromatids.",
                    "G1 = growth; S = DNA synthesis; G2 = preparation. Interphase includes all three."),
                JambDeepPoint("Mitosis: nuclear division",
                    """Mitosis is nuclear division that distributes one copy of each duplicated chromosome to each daughter nucleus. In prophase, chromatin condenses into visible chromosomes, the nucleolus disappears and the spindle begins to form as the nuclear envelope breaks down in the usual school-level description. In metaphase, chromosomes attach to spindle fibres and line up individually at the equator. In anaphase, centromeres divide and sister chromatids separate, moving to opposite poles. In telophase, chromosomes reach the poles, decondense and new nuclear envelopes form around each set.

                    Cytokinesis is division of the cytoplasm and usually follows mitosis. In animal cells, a cleavage furrow pinches the cell into two. In plant cells, vesicles form a cell plate that develops into a new partition between daughter cells. Do not confuse mitosis (nuclear division) with cytokinesis (cytoplasmic division).""",
                    "An onion root-tip cell with chromosomes aligned at the equator is in metaphase; a cell with chromatids moving to opposite poles is in anaphase.",
                    "Order: prophase, metaphase, anaphase, telophase; cytokinesis divides the cytoplasm."),
                JambDeepPoint("Importance and control of mitosis",
                    """Mitosis maintains chromosome number in daughter cells and produces genetically similar cells, apart from mutations or unusual events. It enables growth in multicellular organisms, replacement of worn-out cells, tissue repair and regeneration. It is also the basis of asexual reproduction in many eukaryotic organisms. In plants, mitosis in apical meristems supports growth in length, while lateral meristems contribute to increase in girth.

                    Cell division is regulated by checkpoints that help ensure DNA is intact and chromosomes are correctly attached to the spindle before separation. Failure of normal cell-cycle control can allow cells to divide uncontrollably and contribute to cancer. Mitosis itself does not create the normal genetic variation produced by meiosis; daughter cells are generally genetically similar to the parent cell.""",
                    "Healing of a cut involves cell division that replaces damaged cells; growth of a root tip depends on mitosis in its apical meristem.",
                    "Mitosis maintains chromosome number and supports growth, repair and asexual reproduction.")
            ),
            listOf("DNA replicates in S phase, not during mitosis.", "Chromosome number is counted by centromeres; replicated chromosomes have two sister chromatids.", "Mitosis is nuclear division; cytokinesis divides cytoplasm."),
            listOf(
                JambDeepCheck("bio-growth-10","During which phase of the cell cycle is DNA replicated?",listOf("G1","S phase","G2","Cytokinesis"),1,"DNA synthesis occurs during the S phase of interphase."),
                JambDeepCheck("bio-growth-11","A chromosome after DNA replication consists of",listOf("two homologous chromosomes","two sister chromatids joined at a centromere","four unrelated chromatids","one chromatid without DNA"),1,"Replication produces two sister chromatids that remain joined at the centromere."),
                JambDeepCheck("bio-growth-12","During which stage of mitosis do chromosomes line up at the equator?",listOf("Prophase","Metaphase","Anaphase","Telophase"),1,"Chromosomes align at the equatorial plane during metaphase."),
                JambDeepCheck("bio-growth-13","The separation of sister chromatids occurs during",listOf("prophase","metaphase","anaphase","telophase"),2,"At anaphase, centromeres divide and sister chromatids move to opposite poles."),
                JambDeepCheck("bio-growth-14","Which process divides the cytoplasm after nuclear division?",listOf("Replication","Cytokinesis","Crossing over","Synapsis"),1,"Cytokinesis divides the cytoplasm to form separate daughter cells.")
            )
        ),
        JambDeepObjective(
            "Explain meiosis, including homologous chromosome pairing, crossing over and reduction of chromosome number.",
            listOf(
                JambDeepPoint("Why meiosis is necessary",
                    """Meiosis is a specialised type of cell division that reduces the chromosome number by half and produces haploid cells. It is essential in sexual life cycles because fertilisation joins two haploid nuclei. If gametes retained the diploid chromosome number, chromosome number would double in every generation. In animals, meiosis produces haploid gametes; in flowering plants, meiosis produces haploid spores that develop into the gametophyte generation.

                    Meiosis involves one round of DNA replication followed by two nuclear divisions, meiosis I and meiosis II. It normally produces four haploid products from one diploid precursor cell, although the products do not all necessarily become functional gametes in every organism.""",
                    "In humans, meiosis produces cells with 23 chromosomes; fertilisation restores the diploid number of 46.",
                    "Meiosis reduces chromosome number; fertilisation restores the diploid number."),
                JambDeepPoint("Meiosis I: homologous chromosomes separate",
                    """During prophase I, homologous chromosomes pair in a process called synapsis, forming bivalents. Non-sister chromatids may exchange corresponding DNA segments at points called chiasmata; this exchange is crossing over. At metaphase I, homologous pairs align at the equator in random orientations. At anaphase I, homologous chromosomes separate and move to opposite poles, while sister chromatids remain joined at their centromeres. This is the reduction division because the chromosome sets are separated into haploid nuclei.

                    Crossing over occurs between non-sister chromatids of homologous chromosomes, not between sister chromatids. Independent assortment results from the random orientation of homologous pairs at metaphase I. Both processes create new combinations of alleles.""",
                    "A pair of homologous chromosomes may exchange segments during prophase I, producing chromatids with new allele combinations.",
                    "Meiosis I separates homologous chromosomes; crossing over occurs in prophase I."),
                JambDeepPoint("Meiosis II and comparison with mitosis",
                    """There is no second round of DNA replication between meiosis I and meiosis II. During meiosis II, chromosomes align individually, and sister chromatids separate when centromeres divide. The result is usually four haploid cells, each with one chromosome from each homologous pair. Meiosis II resembles mitosis in the separation of sister chromatids, but it begins with haploid cells produced by meiosis I.

                    Mitosis has one nuclear division and usually produces two daughter cells with the same chromosome number as the parent. Meiosis has two nuclear divisions after one DNA replication and usually produces four haploid products. Mitosis supports growth and repair; meiosis supports sexual reproduction and genetic variation. In humans, a diploid cell has 46 chromosomes; meiosis produces haploid gametes with 23 chromosomes, and fertilisation restores 46.""",
                    "A human sperm cell and egg cell each contain 23 chromosomes; their nuclei fuse at fertilisation to form a zygote with 46.",
                    "Mitosis: one division, two similar cells. Meiosis: two divisions, four haploid products with genetic variation.")
            ),
            listOf("Homologous chromosomes are different copies of the same chromosome type, one inherited from each parent.", "Crossing over occurs between non-sister chromatids during prophase I.", "DNA is not replicated between meiosis I and meiosis II."),
            listOf(
                JambDeepCheck("bio-growth-15","The pairing of homologous chromosomes during meiosis is called",listOf("cytokinesis","synapsis","replication","translation"),1,"Synapsis is the pairing of homologous chromosomes during prophase I."),
                JambDeepCheck("bio-growth-16","Crossing over normally occurs between",listOf("sister chromatids of one chromosome","non-sister chromatids of homologous chromosomes","two unrelated chromosomes","centrioles"),1,"Crossing over exchanges DNA between non-sister chromatids of a homologous pair."),
                JambDeepCheck("bio-growth-17","At anaphase I of meiosis",listOf("sister chromatids separate","homologous chromosomes separate","DNA is replicated","the cell enters interphase permanently"),1,"Homologous chromosomes move to opposite poles during anaphase I."),
                JambDeepCheck("bio-growth-18","A normal human gamete contains",listOf("46 chromosomes","92 chromosomes","23 chromosomes","44 chromosomes"),2,"Human gametes are haploid and contain 23 chromosomes."),
                JambDeepCheck("bio-growth-19","Which event contributes directly to genetic variation during meiosis?",listOf("DNA replication alone","crossing over and independent assortment","cytokinesis alone","mitotic spindle breakdown"),1,"Crossing over and independent assortment create new combinations of alleles.")
            )
        ),
        JambDeepObjective(
            "Describe the regions of growth in roots and shoots and explain how growth is measured.",
            listOf(
                JambDeepPoint("Regions of growth in a root tip",
                    """A young root tip has distinct regions. The root cap protects the delicate growing point as the root pushes through soil. Immediately behind it is the apical meristem, a region of actively dividing cells. The region of elongation contains cells that increase in length and contribute strongly to root extension. Farther back, cells differentiate and mature into specialised tissues; root hairs develop in this region and increase the surface area for water and mineral absorption.

                    A prepared root-tip slide can be examined under a microscope to identify cells at different stages of mitosis in the meristematic region. The meristem contains small, closely packed cells with dense cytoplasm and prominent nuclei; cells in the elongation region are longer, while mature cells show specialised structures.""",
                    "In a practical, locate the root cap first, then identify the zone of cell division, elongation and maturation behind it.",
                    "Root-tip sequence: root cap, cell division, elongation, differentiation/maturation."),
                JambDeepPoint("Shoot growth and meristems",
                    """The shoot apical meristem is located at the growing tip of a shoot and produces cells that contribute to primary growth in length. Lateral meristems, including vascular cambium and cork cambium in plants that possess them, contribute to secondary growth in thickness. Intercalary meristems occur at the bases of leaves or internodes in some plants, especially grasses, and allow regrowth after grazing or cutting.

                    Meristematic cells divide by mitosis; their daughter cells enlarge and differentiate. The position of meristems explains why roots and shoots can continue growing at particular regions. Secondary growth is prominent in many woody dicotyledonous plants and gymnosperms but is absent or limited in many herbaceous plants and most monocots.""",
                    "Grass can regrow after cutting because intercalary meristems near the bases of leaves and internodes may remain intact.",
                    "Apical meristems produce primary growth; lateral meristems contribute to secondary thickening; intercalary meristems support regrowth in some plants."),
                JambDeepPoint("Measuring growth and interpreting a growth curve",
                    """Growth may be measured as change in length, height, leaf area, volume, fresh mass, dry mass or cell number. Choose a method that matches the organism and the question. An auxanometer can record small increases in shoot length over time. Dry mass is useful for estimating accumulated organic material because fresh mass is affected by water content. In a fair investigation, use the same method and time interval, replicate measurements where possible and control factors such as water, light, temperature and nutrient supply.

                    A typical growth curve may show a lag phase, a rapid or exponential phase, a deceleration phase and a stationary phase. The exact shape depends on the organism and conditions. Describe what the graph shows before proposing an explanation, and label axes with the measured variable and units.""",
                    "Measure seedling length at fixed intervals and plot mean length against time to compare growth under controlled conditions.",
                    "State the measurement, units, time interval and controlled variables; do not infer growth from one isolated measurement.")
            ),
            listOf("The root cap is protective; the apical meristem is the main cell-division region.", "Elongation and differentiation occur behind the meristem.", "Fresh mass varies with water content; dry mass is often more reliable for accumulated material."),
            listOf(
                JambDeepCheck("bio-growth-20","The main region of active cell division in a young root is the",listOf("root hair zone","apical meristem","mature xylem","root cap only"),1,"The apical meristem contains actively dividing cells behind the root cap."),
                JambDeepCheck("bio-growth-21","The region where root cells increase markedly in length is the",listOf("root cap","region of elongation","mature phloem","epidermal cuticle"),1,"Cells elongate behind the region of cell division."),
                JambDeepCheck("bio-growth-22","Which instrument can be used to measure small increases in shoot length?",listOf("Barometer","Auxanometer","Hygrometer","Potometer only"),1,"An auxanometer records small increases in plant shoot length."),
                JambDeepCheck("bio-growth-23","Why is dry mass often used to compare plant growth?",listOf("It is dominated by water content","It is less affected by changing water content","It measures only root length","It cannot be repeated"),1,"Dry mass better reflects accumulated material than fresh mass when hydration varies.")
            )
        ),
        JambDeepObjective(
            "Explain the roles of plant growth regulators and relate tropic responses to directional growth.",
            listOf(
                JambDeepPoint("Major plant growth regulators",
                    """Plant growth regulators are chemical substances that influence growth and development in small concentrations. Auxins promote cell elongation in shoots, contribute to apical dominance and are involved in tropic responses. Gibberellins promote stem elongation in many plants and can stimulate seed germination by promoting mobilisation of stored food. Cytokinins promote cell division and can delay leaf senescence. Ethylene is a gaseous plant hormone involved in fruit ripening, leaf abscission and responses to stress. Abscisic acid is associated with seed dormancy and responses to water stress, including stomatal closure.

                    Hormone effects depend on concentration, tissue type, developmental stage and interaction with other hormones. Avoid treating a hormone as having one identical effect in every plant organ.""",
                    "Commercial fruit ripening can be accelerated by ethylene; gibberellins may be used to promote elongation in selected crops.",
                    "Auxin: elongation and tropisms; gibberellin: stem growth and germination; cytokinin: cell division; ethylene: ripening; ABA: dormancy and stress."),
                JambDeepPoint("Phototropism and gravitropism",
                    """A tropism is a directional growth response of a plant part to a stimulus. Phototropism is a growth response to light; shoots are commonly positively phototropic because they grow towards light. In a shoot exposed to light from one side, auxin becomes distributed unevenly and accumulates more on the shaded side. In shoots, auxin promotes cell elongation, so cells on the shaded side elongate more and the shoot bends towards the light.

                    Gravitropism is a growth response to gravity. Roots are generally positively gravitropic because they grow in the direction of gravity, while shoots are generally negatively gravitropic because they grow away from it. Auxin has different effects in roots and shoots: higher auxin concentration on the lower side of a horizontal root tends to inhibit elongation there, so the upper side grows faster and the root bends downwards. In a shoot, the lower side generally elongates more, causing upward bending.""",
                    "A shoot placed horizontally bends upwards; a root bends downwards as each responds to gravity.",
                    "Positive tropism is growth towards a stimulus; negative tropism is growth away from it. Auxin effects differ between roots and shoots."),
                JambDeepPoint("Other tropisms and experimental interpretation",
                    """Hydrotropism is directional growth in response to water; roots commonly show positive hydrotropism. Thigmotropism is a growth response to touch, as seen when tendrils coil around a support. Chemotropism is growth in response to a chemical stimulus; pollen tubes grow towards the ovule under chemical guidance. These are growth responses, not rapid movements caused by changes in turgor pressure.

                    To investigate phototropism, compare seedlings exposed to light from one direction with seedlings receiving uniform light, while keeping water, temperature, age and other conditions similar. Use a control group, repeat observations and record the direction and extent of bending. A plant bending towards light is evidence of positive phototropism; do not conclude that the plant moves its whole body like an animal.""",
                    "A pea tendril coils around a thin support through a touch-related growth response.",
                    "Tropisms are directional growth responses; nastic movements are not directed by the direction of the stimulus.")
            ),
            listOf("Plant hormones act at low concentrations and their effects depend on tissue and concentration.", "Auxin promotes shoot cell elongation but can inhibit elongation in roots at higher concentrations.", "Tropisms are directional growth responses, unlike non-directional nastic movements."),
            listOf(
                JambDeepCheck("bio-growth-24","Which plant hormone is strongly associated with fruit ripening?",listOf("Auxin","Cytokinin","Ethylene","Abscisic acid"),2,"Ethylene is a gaseous plant hormone involved in fruit ripening."),
                JambDeepCheck("bio-growth-25","A shoot bends towards one-sided light mainly because",listOf("cells on the illuminated side always divide faster","auxin promotes greater elongation on the shaded side","gravity stops acting on the shoot","water leaves the shaded side only"),1,"Auxin accumulates on the shaded side of a shoot and promotes greater cell elongation there."),
                JambDeepCheck("bio-growth-26","A root growing towards a region of greater moisture shows",listOf("phototropism","hydrotropism","thigmotropism","chemotropism"),1,"Hydrotropism is directional growth in response to water."),
                JambDeepCheck("bio-growth-27","A tendril coiling around a support is an example of",listOf("positive phototropism","thigmotropism","negative gravitropism","hydrotropism"),1,"Tendrils respond to contact by directional growth, a form of thigmotropism.")
            )
        )
    )

    fun forTopic(topicId:String):List<JambDeepObjective> =
        if(topicId=="bio-growth") objectives else emptyList()
}

@Composable
internal fun SeedGerminationInvestigationLab() {
    var water by remember { mutableStateOf(true) }
    var oxygen by remember { mutableStateOf(true) }
    var temperature by remember { mutableStateOf("Suitable") }
    var viable by remember { mutableStateOf(true) }
    val canGerminate = water && oxygen && temperature == "Suitable" && viable
    val explanation = when {
        !viable -> "The embryo is not viable, so the seed cannot germinate even if the external conditions are suitable."
        !water -> "Without water, the seed cannot complete imbibition or activate the enzymes needed to mobilise stored food."
        !oxygen -> "Without adequate oxygen, aerobic respiration cannot supply enough energy for normal germination."
        temperature != "Suitable" -> "The temperature is outside the suitable range for this seed; enzyme activity and metabolism are impaired."
        else -> "The seed has water, oxygen, a suitable temperature and a viable embryo. Germination can proceed, assuming dormancy has been overcome."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("SEED GERMINATION INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Test the conditions for germination", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Change one factor at a time. This is a qualitative model, not measured experimental data.")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = water, onCheckedChange = { water = it })
                Text("Water available")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = oxygen, onCheckedChange = { oxygen = it })
                Text("Oxygen available")
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = viable, onCheckedChange = { viable = it })
                Text("Seed is viable")
            }
            Text("Temperature", fontWeight = FontWeight.SemiBold)
            listOf("Too low", "Suitable", "Too high").forEach {
                FilterChip(selected = temperature == it, onClick = { temperature = it }, label = { Text(it) })
            }
            Surface(color = if (canGerminate) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(if (canGerminate) "Conditions support germination" else "Germination is limited", fontWeight = FontWeight.Bold)
                    Text(explanation)
                }
            }
            Text("Practical extension: set up four groups of the same viable seeds, changing only one condition in each group. Record the number germinated each day and calculate percentage germination. Keep seed number, species and observation time constant.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun GerminationTypeComparisonLab() {
    var type by remember { mutableStateOf("Epigeal") }
    val description = if (type == "Epigeal") {
        "Hypocotyl elongates and lifts the cotyledons above the soil. Example: bean or castor."
    } else {
        "Epicotyl elongates while cotyledons remain below the soil. Example: pea or maize."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("GERMINATION COMPARISON", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Identify the germination pattern", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Epigeal", "Hypogeal").forEach {
                FilterChip(selected = type == it, onClick = { type = it }, label = { Text(it) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(type, fontWeight = FontWeight.Bold)
                    Text(description)
                }
            }
            Text("Exam task: draw a seedling, label cotyledon, hypocotyl, epicotyl, radicle and plumule, then explain which axis elongates.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
