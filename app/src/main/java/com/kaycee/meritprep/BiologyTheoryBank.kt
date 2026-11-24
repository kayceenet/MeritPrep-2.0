package com.kaycee.meritprep

data class BiologyTheoryTask(
    val id: String,
    val topicId: String,
    val title: String,
    val instruction: String,
    val marks: Int,
    val markingPoints: List<String>,
    val modelAnswer: String
) {
    init {
        require(id.isNotBlank())
        require(topicId.isNotBlank())
        require(title.isNotBlank())
        require(instruction.isNotBlank())
        require(marks > 0)
        require(markingPoints.isNotEmpty())
        require(markingPoints.size == marks) { "Each self-check point must represent one available mark." }
    }
}

/**
 * Original WAEC-style structured-response practice. These are not past questions
 * and their self-marking points are teaching guidance, not an official WAEC scheme.
 */
object BiologyTheoryBank {
    val all = listOf(
        BiologyTheoryTask(
            "theory-cell-01", "bio-cell", "Plant and animal cells",
            "A student is asked to compare a typical photosynthetic plant cell with a typical animal cell. (a) State three structures found in both cells. (b) Give three structural differences. (c) Explain how the cellulose cell wall helps a plant cell.",
            7,
            listOf(
                "Both have a cell-surface membrane.",
                "Both contain cytoplasm.",
                "Both contain a nucleus in the typical cells described.",
                "A typical plant cell has a cellulose cell wall; an animal cell does not.",
                "A typical plant cell has a large permanent vacuole; an animal cell usually has small temporary vacuoles or none.",
                "Photosynthetic plant cells contain chloroplasts; animal cells do not.",
                "The cellulose wall supports the cell, maintains shape and resists excessive expansion when water enters."
            ),
            "Both cell types have a cell membrane, cytoplasm and a nucleus. A typical photosynthetic plant cell also has a cellulose cell wall, a large permanent vacuole and chloroplasts; a typical animal cell lacks the cellulose wall and chloroplasts and does not have a large permanent vacuole. The cell wall provides support, maintains shape and resists excessive expansion as water enters."
        ),
        BiologyTheoryTask(
            "theory-food-02", "bio-nutrition", "Food tests and observations",
            "Describe how you would test separate food samples for starch, reducing sugar, protein and lipid. For each test, name the reagent or procedure and state a positive observation.",
            8,
            listOf(
                "Starch: add iodine solution to the sample.",
                "A positive starch test changes from yellow-brown to blue-black.",
                "Reducing sugar: add Benedict's reagent and heat in a hot-water bath.",
                "A positive reducing-sugar test changes from blue through green/yellow/orange towards brick-red, depending on concentration.",
                "Protein: add Biuret reagent.",
                "A positive protein test develops a lilac or purple colour.",
                "Lipid: shake the sample with ethanol, then add the ethanolic extract to water.",
                "A positive lipid test forms a cloudy white or milky emulsion; ethanol must be kept away from flames."
            ),
            "Use iodine for starch; a positive result is blue-black. Use Benedict's reagent and heat the mixture in a hot-water bath for reducing sugars; a positive result changes from blue towards green, yellow, orange or brick-red. Add Biuret reagent for protein; a positive result is lilac or purple. For lipids, shake with ethanol and then add water; a milky emulsion indicates lipid. Ethanol is flammable and must be kept away from flames."
        ),
        BiologyTheoryTask(
            "theory-osmosis-03", "bio-transport", "Investigating osmosis",
            "Plan an experiment to investigate how sucrose concentration affects the mass of potato cylinders. State the aim, independent variable, dependent variable, two control variables, method and expected trend.",
            8,
            listOf(
                "Aim: investigate the effect of external sucrose concentration on potato mass or percentage mass change.",
                "Independent variable: sucrose concentration.",
                "Dependent variable: change in mass or percentage change in mass of potato cylinders.",
                "Control variables include cylinder dimensions, potato source, solution volume, immersion time and temperature.",
                "Cut equal-sized cylinders and measure their initial masses.",
                "Place cylinders in labelled sucrose solutions of different concentrations for the same time.",
                "Remove, gently blot consistently and measure final masses.",
                "Calculate percentage mass change and plot against sucrose concentration; mass generally increases in dilute solution and decreases in concentrated solution."
            ),
            "Prepare equal-sized potato cylinders and record their initial masses. Place each in a labelled sucrose solution of known concentration, keeping cylinder dimensions, solution volume, temperature and immersion time constant. After the same period, remove and blot each cylinder in the same way, then record its final mass. Calculate percentage mass change and plot it against sucrose concentration. Water enters potato cells in a more dilute external solution and leaves them in a more concentrated solution, so mass usually rises at low concentrations and falls at high concentrations."
        ),
        BiologyTheoryTask(
            "theory-photosynthesis-04", "bio-nutrition", "Photosynthesis and limiting factors",
            "Explain why increasing light intensity may increase the rate of photosynthesis at first but eventually produces little further increase. Name two other factors that can limit the rate.",
            6,
            listOf(
                "Light provides energy for photosynthesis.",
                "At low light intensity, light may be the limiting factor.",
                "Increasing light intensity increases the rate while light is limiting.",
                "At higher intensity, another factor becomes limiting and the rate levels off.",
                "Carbon dioxide concentration can be limiting.",
                "Temperature can be limiting because photosynthesis involves enzyme-controlled reactions."
            ),
            "Light supplies energy for photosynthesis. When light intensity is low, increasing it can increase the rate because more light energy is available. Eventually another factor, such as carbon dioxide concentration or temperature, limits the process, so further increases in light intensity have little effect. Photosynthesis involves enzyme-controlled reactions, and very high temperatures can reduce enzyme activity."
        ),
        BiologyTheoryTask(
            "theory-circulation-05", "bio-transport", "Double circulation in mammals",
            "Trace the route of a red blood cell from the right ventricle until it returns to the left ventricle. Explain one advantage of double circulation.",
            6,
            listOf(
                "Right ventricle pumps blood into the pulmonary artery.",
                "Blood travels to the lungs and passes through pulmonary capillaries.",
                "Oxygenated blood returns through the pulmonary veins to the left atrium.",
                "Blood passes through the mitral/bicuspid valve into the left ventricle.",
                "The left ventricle pumps blood into the aorta for systemic circulation.",
                "Double circulation keeps pulmonary and systemic circuits separate and supports efficient delivery of oxygenated blood at suitable pressure."
            ),
            "The right ventricle pumps deoxygenated blood through the pulmonary artery to the lungs. After gas exchange, oxygenated blood returns in the pulmonary veins to the left atrium, passes through the mitral valve into the left ventricle, and is pumped into the aorta. Double circulation separates the pulmonary and systemic circuits and allows oxygenated blood to be delivered to the body efficiently."
        ),
        BiologyTheoryTask(
            "theory-ecology-06", "bio-interactions", "Food chains and energy transfer",
            "In a grassland ecosystem, grass is eaten by grasshoppers, which are eaten by frogs, which are eaten by snakes. (a) Write the food chain. (b) Identify the producer and the secondary consumer. (c) Explain why less energy is available at each successive trophic level.",
            6,
            listOf(
                "Correct chain: grass → grasshopper → frog → snake.",
                "Grass is the producer.",
                "Grasshopper is the primary consumer.",
                "Frog is the secondary consumer.",
                "Energy is lost through respiration and released as heat.",
                "Energy is also lost in waste, uneaten material and parts that are not digested or assimilated."
            ),
            "The food chain is grass → grasshopper → frog → snake. Grass is the producer, the grasshopper is the primary consumer and the frog is the secondary consumer. Energy becomes less available at higher trophic levels because organisms use energy in respiration and release heat; energy is also lost in waste, uneaten parts and material that is not assimilated."
        ),
        BiologyTheoryTask(
            "theory-quadrat-07", "bio-populations", "Estimating population density",
            "Four 1 m² quadrats contain 8, 12, 10 and 6 plants. (a) Calculate the mean number of plants per square metre. (b) Estimate the number of plants in a 1,000 m² habitat. (c) State two assumptions or limitations of this estimate.",
            6,
            listOf(
                "Total count = 8 + 12 + 10 + 6 = 36 plants.",
                "Total sampled area = 4 m².",
                "Mean density = 36 ÷ 4 = 9 plants/m².",
                "Estimated population = 9 × 1,000 = 9,000 plants.",
                "Quadrats should be placed randomly and representatively.",
                "Patchy distribution, seasonal change or too few samples can reduce reliability."
            ),
            "The total count is 36 plants in a sampled area of 4 m². Mean density is 36 ÷ 4 = 9 plants/m². Estimated population is 9 × 1,000 = 9,000 plants. This estimate assumes the quadrats were placed randomly and represent the habitat. A clumped distribution, seasonal change or too few samples can make the estimate unreliable."
        ),
        BiologyTheoryTask(
            "theory-genetics-08", "bio-heredity", "A monohybrid genetic cross",
            "In a plant, tallness (T) is dominant to shortness (t). Cross two heterozygous tall plants. Show the parental genotypes, gametes, offspring genotypes and expected genotype and phenotype ratios.",
            8,
            listOf(
                "Parental genotypes: Tt × Tt.",
                "Each parent produces gametes T and t.",
                "Correct combinations: TT, Tt, Tt and tt.",
                "Genotype ratio: 1 TT : 2 Tt : 1 tt.",
                "TT and Tt are tall under complete dominance.",
                "tt is short.",
                "Phenotype ratio: 3 tall : 1 short.",
                "The ratios are expected probabilities for many offspring, not a guarantee for a small family or sample."
            ),
            "The cross is Tt × Tt. Each parent produces T and t gametes. The offspring combinations are TT, Tt, Tt and tt. The genotype ratio is 1 TT : 2 Tt : 1 tt. If T shows complete dominance, TT and Tt are tall while tt is short, giving a phenotype ratio of 3 tall : 1 short. These are expected probabilities, not guaranteed outcomes in a small sample."
        ),
        BiologyTheoryTask(
            "theory-classification-09", "bio-classification", "Using a dichotomous key",
            "Explain how a dichotomous key is used to identify an unfamiliar organism. State two rules that make a key reliable and explain why observable features should be used.",
            6,
            listOf(
                "A dichotomous key presents a sequence of paired alternative statements.",
                "Choose the statement that best matches the specimen and follow its direction or number.",
                "Continue until an identification is reached.",
                "Each pair should contain clear, mutually exclusive alternatives.",
                "Use observable and relatively stable features; avoid vague or subjective descriptions.",
                "Observable features allow another person to check and repeat the identification."
            ),
            "A dichotomous key uses a sequence of paired contrasting statements. At each step, select the statement that matches the specimen and follow the instruction to the next pair or identification. A reliable key uses clear, mutually exclusive alternatives and features that can be observed consistently. Observable features make the identification repeatable and open to checking by another person."
        ),
        BiologyTheoryTask(
            "theory-adaptation-10", "bio-adaptation", "Adaptations in contrasting habitats",
            "Compare two adaptations that help a cactus conserve water with two adaptations that help a floating aquatic plant survive. Link each feature to its function.",
            6,
            listOf(
                "Cactus leaves are reduced to spines, lowering leaf surface area and water loss.",
                "A thick waxy cuticle reduces evaporation.",
                "A succulent stem stores water and may carry out photosynthesis.",
                "Floating leaves commonly have stomata mainly on the upper surface exposed to air.",
                "Large air spaces (aerenchyma) provide buoyancy and aid gas movement.",
                "Flexible or reduced supporting tissues may be sufficient because water supports the plant."
            ),
            "A cactus may have leaves reduced to spines, which lowers surface area for water loss, and a thick waxy cuticle that reduces evaporation. Its succulent stem stores water. A floating aquatic plant may have stomata mainly on the upper leaf surface where they contact air, and large air spaces that provide buoyancy and assist gas movement. These features suit the different water conditions of the habitats."
        ),
        BiologyTheoryTask(
            "theory-respiration-11", "bio-respiration", "Aerobic and anaerobic respiration",
            "Compare aerobic respiration with anaerobic respiration in human muscle cells. State where each occurs, the oxygen requirement, the relative energy yield and the products formed.",
            7,
            listOf(
                "Aerobic respiration requires oxygen; anaerobic respiration does not require oxygen.",
                "Glycolysis occurs in the cytoplasm in both pathways.",
                "In eukaryotic cells, most stages of aerobic respiration occur in mitochondria.",
                "Aerobic respiration releases much more energy per glucose molecule than anaerobic glycolysis.",
                "The overall products of aerobic respiration are carbon dioxide and water.",
                "In human muscle cells, anaerobic respiration converts pyruvate to lactate.",
                "Lactate accumulation is associated with intense exercise; it can later be metabolised when oxygen is available."
            ),
            "Both pathways begin with glycolysis in the cytoplasm. Aerobic respiration requires oxygen and, in eukaryotic cells, most of its later stages occur in mitochondria. It releases much more energy per glucose molecule and its overall products are carbon dioxide and water. When oxygen supply cannot meet demand during intense exercise, muscle cells can continue glycolysis by converting pyruvate to lactate. This anaerobic pathway releases far less energy per glucose molecule. Lactate can later be transported and metabolised."
        ),
        BiologyTheoryTask(
            "theory-kidney-12", "bio-excretion", "Urine formation in the nephron",
            "Describe the main stages involved in urine formation in a mammalian kidney, beginning at the glomerulus. Explain why glucose is normally absent from the urine of a healthy person.",
            7,
            listOf(
                "Blood enters the glomerulus through the afferent arteriole.",
                "High hydrostatic pressure drives ultrafiltration into Bowman's capsule.",
                "Water and small solutes enter the filtrate; blood cells and most plasma proteins remain in the blood.",
                "Selective reabsorption returns useful substances such as all filtered glucose, amino acids and needed ions to the blood under normal conditions.",
                "Much water is reabsorbed along the nephron, with the amount regulated partly by antidiuretic hormone.",
                "Tubular secretion moves selected substances from blood into the tubule.",
                "Glucose is normally completely reabsorbed in the proximal convoluted tubule when blood glucose is within the normal range and transport capacity is not exceeded."
            ),
            "Blood enters the glomerulus through the afferent arteriole. The high pressure in its capillaries forces water and small dissolved substances into Bowman's capsule; blood cells and most plasma proteins remain in the blood. As filtrate passes along the nephron, selective reabsorption returns useful substances, including glucose, amino acids and needed ions, to the blood. Water is also reabsorbed, with hormonal regulation helping adjust its amount. Tubular secretion transfers selected substances from blood into the tubule. Glucose is normally absent from urine because it is reabsorbed in the proximal convoluted tubule, provided the filtered load does not exceed the transport capacity."
        ),
        BiologyTheoryTask(
            "theory-reproduction-13", "bio-reproduction", "Pollination and fertilisation in flowering plants",
            "Distinguish pollination from fertilisation. Trace the events from pollen landing on a compatible stigma to the formation of a seed and fruit.",
            7,
            listOf(
                "Pollination is the transfer of pollen grains from anther to stigma.",
                "Fertilisation is the fusion of male and female gamete nuclei.",
                "A compatible pollen grain hydrates and germinates on the stigma.",
                "A pollen tube grows through the style towards an ovule in the ovary.",
                "The male nucleus or nuclei travel through the pollen tube.",
                "In angiosperms, one haploid male gamete fuses with the haploid egg to form a diploid zygote (2n); the other fuses with the two haploid polar nuclei to form a triploid primary endosperm nucleus (3n). These are the two events of double fertilisation.",
                "The ovule develops into a seed and the ovary usually develops into a fruit."
            ),
            "Pollination is the transfer of pollen from anther to stigma; it is not fertilisation. After compatible pollen lands on the stigma, it can germinate and form a pollen tube that grows down the style towards an ovule. The tube carries two haploid male gametes. One fuses with the haploid egg to form a diploid zygote (2n), which develops into the embryo. The other fuses with the two haploid polar nuclei to form a triploid primary endosperm nucleus (3n); the endosperm nourishes the developing embryo. These two fusion events are called double fertilisation. The ovule develops into a seed, its integuments form the seed coat, and the ovary usually develops into a fruit."
        ),
        BiologyTheoryTask(
            "theory-variation-14", "bio-variation", "Continuous and discontinuous variation",
            "Differentiate continuous variation from discontinuous variation. Give two examples of each and explain the roles of genes and the environment in producing variation.",
            7,
            listOf(
                "Continuous variation shows a range of values with many intermediate forms.",
                "Examples include human height and body mass.",
                "Discontinuous variation falls into distinct categories with no usual intermediate category for the trait as measured.",
                "Examples include ABO blood group and the ability to taste PTC (as commonly taught in introductory genetics).",
                "Continuous traits are often influenced by many genes (polygenic inheritance).",
                "Environmental factors such as nutrition and health can also affect continuous traits.",
                "Discontinuous traits are often strongly influenced by one or a few genes, although the genetic basis varies by trait."
            ),
            "Continuous variation shows a range of values, often with many intermediate forms; human height and body mass are examples. Such traits are commonly influenced by many genes and can also be affected by environmental factors such as nutrition and health. Discontinuous variation places individuals into distinct categories, such as ABO blood groups. Discontinuous traits are often controlled by one or a few genes, although this is not a universal rule. The pattern depends on the genetic and environmental influences on the particular characteristic."
        ),
        BiologyTheoryTask(
            "theory-soil-15", "bio-soil", "Comparing soil types and drainage",
            "A farmer compares sandy, clayey and loamy soils. Describe how their particle size, drainage, water retention and suitability for crop growth differ. Explain why loam is often useful for cultivation.",
            7,
            listOf(
                "Sandy soil has relatively large particles and large pore spaces.",
                "Sandy soil usually drains quickly and retains relatively little water and dissolved nutrients.",
                "Clayey soil has very small particles and small pore spaces.",
                "Clayey soil usually drains slowly and retains more water, but may become waterlogged and poorly aerated.",
                "Loam contains a mixture of sand, silt and clay, often with organic matter.",
                "Loam can provide a balance of drainage, water retention, aeration and nutrient-holding capacity.",
                "Soil suitability also depends on structure, organic matter, pH, climate and the crop being grown."
            ),
            "Sandy soil contains relatively large particles with large pore spaces, so water drains quickly and little water or dissolved nutrient is retained. Clayey soil has very small particles and pore spaces; it holds more water but drains slowly and can become waterlogged and poorly aerated. Loam contains a mixture of sand, silt and clay and often organic matter. It can provide a useful balance of drainage, water retention, aeration and nutrient-holding capacity. The best soil for a crop also depends on soil structure, pH, organic matter, climate and the crop's requirements."
        )

    )
}
