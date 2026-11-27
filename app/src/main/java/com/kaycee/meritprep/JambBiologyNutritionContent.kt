package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led JAMB coverage of nutrition in plants and animals. */
internal object JambBiologyNutritionContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Distinguish autotrophic and heterotrophic nutrition and describe important nutritional modes.",
            listOf(
                JambDeepPoint("Autotrophic and heterotrophic nutrition",
                    """Nutrition is the process by which an organism obtains and uses materials for energy, growth, repair and regulation. Autotrophs manufacture organic substances from inorganic materials. Photoautotrophs use light energy; green plants, algae and cyanobacteria are examples. Chemoautotrophic bacteria obtain energy by oxidising inorganic substances and use it to fix carbon dioxide. Heterotrophs obtain organic nutrients from other organisms or their products because they cannot make all the organic food they require from inorganic materials alone.

                    Heterotrophic modes include holozoic nutrition, in which solid or liquid food is ingested and internally digested (many animals); saprotrophic nutrition, in which enzymes are released onto dead organic matter and soluble products are absorbed (many fungi and bacteria); and parasitic nutrition, in which an organism obtains nutrients from a living host, often harming it. Some organisms are symbiotic: partners live in close association and may exchange nutrients, although the relationship can be mutualistic, commensal or parasitic.""",
                    "A green maize plant is photoautotrophic; a mushroom is saprotrophic; a tapeworm is parasitic; a human is holozoic.",
                    "Autotrophs manufacture organic food; heterotrophs obtain it from other organisms. Saprotrophs feed on dead material externally; parasites obtain nutrients from living hosts."),
                JambDeepPoint("Special nutritional relationships and examples",
                    """In mutualism, both partners benefit. Lichens consist of a fungus associated with a photosynthetic alga or cyanobacterium: the photosynthetic partner supplies organic food, while the fungus provides protection, water retention and access to minerals. Nitrogen-fixing bacteria such as Rhizobium live in root nodules of legumes; the bacteria convert atmospheric nitrogen into forms that can enter the nitrogen cycle, while the plant supplies carbohydrates and a protected habitat. The exact exchange depends on environmental conditions and the partners.

                    Insectivorous plants such as pitcher plants and Venus flytraps are photosynthetic autotrophs that trap small animals mainly to obtain mineral nutrients, especially nitrogen, in nutrient-poor habitats. They do not depend on captured insects as their main energy source. A parasite may be an endoparasite inside a host or an ectoparasite on its surface. Distinguish the nutritional relationship from the habitat: living on another organism does not automatically mean the relationship benefits both partners.""",
                    "A pitcher plant photosynthesises but obtains additional nitrogen from trapped insects; Rhizobium obtains sugars from a legume and supplies fixed nitrogen.",
                    "Insectivorous plants remain photosynthetic. Mutualism benefits both partners; parasitism benefits one at the host's expense.")
            ),
            listOf("Nutrition is broader than digestion; digestion is only one stage of holozoic nutrition.",
                  "A saprotroph feeds on dead organic material by external digestion; a parasite feeds from a living host.",
                  "Insectivorous plants trap insects mainly for mineral nutrients, not as their main energy source."),
            listOf(
                JambDeepCheck("bio-nutri-01","Which organism is a typical saprotroph?",listOf("Mushroom","Tapeworm","Goat","Green maize plant"),0,"Many fungi secrete enzymes onto dead material and absorb the soluble products."),
                JambDeepCheck("bio-nutri-02","A pitcher plant traps insects mainly to obtain",listOf("light energy","mineral nutrients such as nitrogen","carbon dioxide","oxygen for photosynthesis"),1,"Pitcher plants photosynthesise but supplement mineral nutrition in nutrient-poor habitats."),
                JambDeepCheck("bio-nutri-03","In a mutualistic association between Rhizobium and a legume",listOf("both partners benefit","the plant is always killed","only the bacterium benefits","neither partner obtains nutrients"),0,"The bacterium receives carbohydrates and shelter; the plant gains access to biologically fixed nitrogen.")
            )
        ),
        JambDeepObjective(
            "Explain photosynthesis, its requirements, the role of chloroplasts and factors that affect its rate.",
            listOf(
                JambDeepPoint("Photosynthesis and the chloroplast",
                    """Photosynthesis is the process by which photoautotrophs convert light energy into chemical energy stored in organic molecules. In oxygenic photosynthesis, carbon dioxide and water are used to form carbohydrates, and oxygen is released. The simplified overall equation is 6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂, in the presence of light and chlorophyll. The equation is a summary: glucose is not necessarily the immediate product exported from the chloroplast, and the pathway involves many intermediate compounds.

                    Chloroplasts contain chlorophyll and other pigments in thylakoid membranes. Light-dependent reactions occur in the thylakoid membranes, where light energy drives electron transfer, water is split, oxygen is released, and ATP and reduced NADP are formed. The Calvin cycle occurs in the stroma, where carbon dioxide is fixed and reduced using ATP and reduced NADP to form carbohydrate precursors. The two stages are linked: products of the light-dependent reactions support carbon fixation.""",
                    "A leaf exposed to light can release oxygen because water is split during the light-dependent reactions of oxygenic photosynthesis.",
                    "Chlorophyll absorbs light; thylakoid membranes host light-dependent reactions; the stroma is the site of the Calvin cycle; oxygen comes from water."),
                JambDeepPoint("Limiting factors and experimental interpretation",
                    """The rate of photosynthesis is influenced by light intensity, carbon dioxide concentration, temperature, water availability and chlorophyll content. If one factor is in shortest supply relative to the plant's requirements, it can limit the rate even when other factors are abundant. Increasing light intensity may raise the rate at low light, but the rate eventually levels off when another factor becomes limiting. Raising carbon dioxide can increase the rate only until light, temperature or another requirement limits it.

                    Photosynthetic reactions are enzyme-controlled, so temperature affects their rate. A rise towards an optimum can increase reaction rate; excessive heat can disrupt enzymes and damage membranes, reducing photosynthesis. Water shortage can cause stomata to close, restricting carbon dioxide entry. Experiments using pondweed may estimate oxygen production by collecting gas volume over time, but bubble counts are less reliable because bubbles differ in size. Keep temperature, plant size and exposure time controlled when comparing treatments.""",
                    "If increasing light intensity no longer increases oxygen production, another factor such as carbon dioxide concentration may be limiting.",
                    "A limiting factor is the factor in shortest supply relative to demand. A plateau does not mean light is unimportant; another factor may now limit the rate.")
            ),
            listOf("The oxygen released in photosynthesis originates from water, not carbon dioxide.",
                  "The simplified equation is an overall summary, not a complete description of the pathway.",
                  "A higher value of one factor cannot keep raising the rate when another factor becomes limiting."),
            listOf(
                JambDeepCheck("bio-nutri-04","The oxygen released during oxygenic photosynthesis originates from",listOf("carbon dioxide","water","glucose","chlorophyll"),1,"Water is split during the light-dependent reactions, releasing oxygen."),
                JambDeepCheck("bio-nutri-05","The Calvin cycle takes place in the",listOf("stroma of the chloroplast","thylakoid lumen only","mitochondrial matrix","cytoplasm of the root hair"),0,"Carbon fixation and carbohydrate synthesis reactions of the Calvin cycle occur in the chloroplast stroma."),
                JambDeepCheck("bio-nutri-06","When increasing light intensity no longer raises the rate of photosynthesis, the most likely explanation is that",listOf("chlorophyll has become a mineral","another factor has become limiting","photosynthesis no longer requires carbon dioxide","the plant has stopped respiring"),1,"The rate levels off when a different factor, such as carbon dioxide or temperature, limits photosynthesis.")
            )
        ),
        JambDeepObjective(
            "State the functions of selected mineral elements in plants and recognise deficiency effects.",
            listOf(
                JambDeepPoint("Major mineral elements and their roles",
                    """Plants absorb mineral ions from soil water through root surfaces. Nitrate ions provide nitrogen for amino acids, proteins, nucleic acids and chlorophyll-related molecules; severe deficiency commonly causes poor growth and yellowing, especially in older leaves as nitrogen can be remobilised. Magnesium is part of the chlorophyll molecule, so deficiency often causes interveinal chlorosis, particularly in older leaves. Phosphate is required for ATP, nucleic acids and phospholipids; deficiency can cause poor root growth, stunting and sometimes dark or purplish leaves.

                    Potassium supports enzyme activation, osmotic regulation and stomatal function; deficiency may cause weak growth and scorching or yellowing at leaf margins. Calcium contributes to cell walls and membrane stability and is important in growing tissues; deficiency often affects root and shoot tips and young leaves. Sulphur is required for certain amino acids and proteins; deficiency can cause chlorosis, often first noticeable in younger leaves. Plants also require trace elements such as iron, which is needed for electron-transfer proteins and chlorophyll synthesis pathways, although it is not part of the chlorophyll molecule itself.""",
                    "Magnesium deficiency can cause interveinal chlorosis because magnesium is a component of chlorophyll.",
                    "Nitrate = amino acids/proteins; magnesium = chlorophyll; phosphate = ATP and nucleic acids; potassium = enzyme/osmotic regulation; calcium = cell walls and growing points."),
                JambDeepPoint("Diagnosing deficiency carefully",
                    """A deficiency symptom is a clue, not a diagnosis by itself. Yellow leaves may result from lack of nitrogen, magnesium, iron, waterlogging, root damage, unsuitable pH or disease. Mobile nutrients such as nitrogen, magnesium and potassium can be moved from older tissues to growing regions, so symptoms may appear first in older leaves. Less mobile nutrients such as calcium and iron often show effects in young tissues. This pattern is useful but not absolute across all species and conditions.

                    A fair investigation compares plants grown under similar conditions while changing one mineral supply at a time. Keep plant species, age, light, water, temperature and growth medium constant. Record growth, leaf colour and root development over time. Avoid concluding that a nutrient is deficient from a single photograph or symptom; confirm with a controlled comparison or suitable analysis.""",
                    "If older leaves become generally yellow while new leaves remain greener, nitrogen deficiency is one possibility; check other growing conditions before concluding.",
                    "Use the nutrient's role, symptom location and controlled evidence together. Mobile nutrient shortages often appear first in older leaves.")
            ),
            listOf("Magnesium is part of chlorophyll; iron is needed for chlorophyll synthesis but is not the central atom of chlorophyll.",
                  "Deficiency symptoms overlap and can also be caused by pH, waterlogging or disease.",
                  "The first leaves affected can help distinguish mobile from less mobile nutrient deficiencies."),
            listOf(
                JambDeepCheck("bio-nutri-07","Which mineral element is a component of the chlorophyll molecule?",listOf("Magnesium","Sodium","Iodine","Fluorine"),0,"Magnesium occupies the central position in the chlorophyll molecule."),
                JambDeepCheck("bio-nutri-08","Nitrate ions are needed by plants mainly for the synthesis of",listOf("amino acids and proteins","cellulose only","oxygen gas","starch grains only"),0,"Nitrogen from nitrate is used to form amino acids and proteins, among other nitrogen-containing compounds."),
                JambDeepCheck("bio-nutri-09","A controlled mineral-deficiency experiment should",listOf("change several mineral supplies at once","keep conditions constant except for the mineral being tested","use plants of different species in each group","measure only one leaf once"),1,"Changing one variable while controlling others makes it possible to attribute differences to the mineral treatment.")
            )
        ),
        JambDeepObjective(
            "Identify the major classes of food, their functions and the features of a balanced diet.",
            listOf(
                JambDeepPoint("Food classes and digestion products",
                    """Carbohydrates include sugars, starch and fibre. Sugars and starch provide energy; excess carbohydrate can be stored as glycogen in animals or converted to fat. Digestible starch is hydrolysed to smaller sugars and ultimately monosaccharides such as glucose. Proteins are made of amino acids and are needed for growth, repair, enzymes, transport proteins, antibodies and some hormones. Dietary protein is digested into peptides and amino acids. Lipids include triglycerides and phospholipids; they provide concentrated energy, insulation and components of cell membranes. Triglycerides are digested mainly into fatty acids and monoglycerides.

                    Vitamins are organic micronutrients required in small amounts. Vitamin A supports vision and epithelial health; vitamin C is needed for collagen formation and antioxidant functions; vitamin D supports calcium absorption and bone health; vitamin K is required for normal blood clotting. Minerals such as calcium, iron and iodine have specific roles. Water is a solvent, transport medium and participant in metabolic reactions. Dietary fibre supports bowel movement and gut health but is not digested by human enzymes into absorbable nutrients.""",
                    "Iron is needed to make haemoglobin; calcium is important for bones, teeth, muscle function and blood clotting.",
                    "Carbohydrates = major energy source; proteins = growth and repair; lipids = energy storage and membranes; vitamins/minerals = regulation; water = solvent and transport; fibre = bowel function."),
                JambDeepPoint("Balanced diet and energy needs",
                    """A balanced diet supplies energy and nutrients in suitable proportions for an individual's needs. Requirements vary with age, body size, activity, pregnancy, illness and other factors. Energy-yielding foods include carbohydrate, protein and fat; fat provides more energy per gram than carbohydrate or protein. A diet with too little energy can cause weight loss and poor growth, while prolonged excess energy intake can contribute to overweight and obesity. Protein-energy malnutrition includes conditions such as marasmus and kwashiorkor, which have different clinical features and can overlap in real patients.

                    A varied diet can include staple carbohydrate foods, legumes or other protein sources, vegetables, fruits, nuts, oils and adequate water. Dietary advice must account for availability, culture, health and individual needs. Do not assume that one food is a complete diet. Food tests can identify nutrients: iodine solution tests for starch, Benedict's reagent with heating tests for reducing sugars, Biuret reagent tests for peptide bonds, and ethanol emulsion or Sudan tests can indicate lipids. Appropriate controls and safe handling are essential.""",
                    "A meal based only on cassava may supply substantial carbohydrate but needs other foods to provide adequate protein, vitamins and minerals.",
                    "A balanced diet depends on the person's needs. Know the food tests and the nutrient each reagent detects.")
            ),
            listOf("Proteins are not the body's preferred main energy source when adequate carbohydrate and fat are available.",
                  "Fibre is important but is not digested into absorbable nutrients by human digestive enzymes.",
                  "Benedict's test detects reducing sugars, not starch; iodine detects starch."),
            listOf(
                JambDeepCheck("bio-nutri-10","Which food test is used to detect starch?",listOf("Biuret test","Iodine test","Benedict's test","Ethanol emulsion test"),1,"Iodine solution changes from brown/yellow to blue-black in the presence of starch."),
                JambDeepCheck("bio-nutri-11","The Biuret test is used to detect",listOf("protein","starch","reducing sugar","vitamin C"),0,"Biuret reagent gives a violet or purple colour when peptide bonds are present."),
                JambDeepCheck("bio-nutri-12","Which statement best describes a balanced diet?",listOf("The same meal for every person","A diet supplying nutrients and energy in proportions suited to the person's needs","A diet containing only protein","A diet excluding all fats"),1,"Nutritional needs vary with age, activity, health and other conditions.")
            )
        ),
        JambDeepObjective(
            "Describe the mammalian alimentary canal and explain the actions of digestive enzymes and accessory organs.",
            listOf(
                JambDeepPoint("The alimentary canal and accessory organs",
                    """The alimentary canal is a continuous tube. In mammals, food enters the mouth, passes through the pharynx and oesophagus to the stomach, then the small intestine (duodenum, jejunum and ileum), large intestine, rectum and anus. Peristalsis is coordinated contraction and relaxation of gut wall muscles that moves contents along. The stomach stores food, churns it and secretes gastric juice. The small intestine completes most chemical digestion and absorbs most nutrients; the large intestine absorbs water and electrolytes and helps form faeces.

                    Accessory organs add secretions but food does not pass through them. Salivary glands release saliva containing amylase and mucus. The liver produces bile, which is stored and concentrated in the gall bladder in humans. The pancreas releases digestive enzymes and bicarbonate into the duodenum. Bicarbonate helps neutralise acidic chyme from the stomach, creating a more suitable pH for intestinal enzymes. Bile is not an enzyme and does not chemically hydrolyse fat; it emulsifies large fat droplets into smaller droplets.""",
                    "Food passes through the oesophagus and stomach but not through the liver, gall bladder or pancreas.",
                    "Know the canal sequence and separate the canal from accessory organs. Liver makes bile; gall bladder stores it; pancreas supplies enzymes and bicarbonate."),
                JambDeepPoint("Digestive enzymes and their substrates",
                    """Enzymes are biological catalysts that speed up chemical reactions without being used up. Their active sites bind particular substrates, so each enzyme has a range of suitable substrates and conditions. Salivary and pancreatic amylase hydrolyse starch into smaller carbohydrates such as maltose and short dextrins; brush-border enzymes complete digestion to monosaccharides. Proteases act on proteins and peptides: pepsin works in the acidic stomach, while pancreatic proteases such as trypsin act in the small intestine. Peptidases complete breakdown to amino acids.

                    Lipase hydrolyses triglycerides mainly into fatty acids and monoglycerides. Bile salts emulsify fat, increasing the surface area available to lipase; they are not enzymes. Nucleases digest nucleic acids into nucleotides, which are further broken down by intestinal enzymes. Enzyme activity is affected by temperature and pH. High temperatures can denature enzymes; unsuitable pH can alter the active site. Pepsin works best in acidic conditions, while many pancreatic enzymes work best in the alkaline environment of the small intestine.""",
                    "Pepsin acts on proteins in the acidic stomach; pancreatic amylase acts on starch in the small intestine.",
                    "Amylase = starch; proteases = proteins/peptides; lipase = triglycerides. Bile emulsifies fats but is not an enzyme.")
            ),
            listOf("Bile is produced by the liver, stored in the gall bladder and is not a digestive enzyme.",
                  "Peristalsis moves food; it does not chemically digest it.",
                  "Pepsin works in an acidic stomach; pancreatic enzymes act in the small intestine."),
            listOf(
                JambDeepCheck("bio-nutri-13","Which organ produces bile?",listOf("Pancreas","Liver","Stomach","Small intestine"),1,"The liver produces bile; the gall bladder stores and concentrates it."),
                JambDeepCheck("bio-nutri-14","The main function of bile in fat digestion is to",listOf("hydrolyse fat chemically","emulsify fat into smaller droplets","convert protein to amino acids","digest starch to maltose"),1,"Bile salts emulsify fat, increasing the surface area available to lipase; bile itself is not an enzyme."),
                JambDeepCheck("bio-nutri-15","Pepsin is most active in the",listOf("acidic stomach","alkaline mouth","large intestine","blood plasma"),0,"Pepsin is a stomach protease that functions best in acidic conditions.")
            )
        ),
        JambDeepObjective(
            "Explain absorption and assimilation of digested nutrients in mammals.",
            listOf(
                JambDeepPoint("Absorption across the small intestine",
                    """Absorption is the movement of digested molecules from the lumen of the alimentary canal into blood or lymph. The small intestine is adapted for absorption by its length, folds, villi and microvilli, which create a large surface area. The epithelial lining is thin, and each villus contains blood capillaries and a lymphatic lacteal. Glucose and amino acids enter epithelial cells and pass into blood capillaries; many products of fat digestion enter epithelial cells, are reassembled into triglycerides and packaged into chylomicrons that enter lacteals.

                    Monosaccharides and amino acids are transported from the intestine to the liver through the hepatic portal vein before entering general circulation. Fat-rich lymph eventually drains into the bloodstream. Water and mineral ions are also absorbed. Different substances use diffusion, facilitated diffusion, active transport or osmosis depending on the molecule and its gradient. Do not say all absorbed food enters blood directly: most long-chain lipid products first enter lymphatic lacteals.""",
                    "A villus has a blood capillary network for glucose and amino acids and a lacteal for much of the absorbed fat.",
                    "Villi and microvilli increase surface area; thin epithelium shortens diffusion distance; capillaries and lacteals transport absorbed products."),
                JambDeepPoint("Assimilation and the fate of nutrients",
                    """Assimilation is the use or incorporation of absorbed nutrients by body cells. Glucose may be oxidised in cellular respiration to release energy, stored as glycogen in liver and muscle, or converted to fat when energy intake exceeds immediate needs. Amino acids are used to synthesise body proteins, enzymes, antibodies and some hormones; excess amino acids cannot be stored as protein and are deaminated mainly in the liver. The nitrogen-containing waste is converted to urea, which is transported in blood to the kidneys for excretion.

                    Fatty acids and glycerol can be used in respiration, stored in adipose tissue or incorporated into cell membranes. Vitamins and minerals support specific metabolic processes. The liver regulates blood glucose, processes absorbed nutrients, stores some vitamins and iron, and detoxifies many substances. Distinguish absorption (entry from gut into blood or lymph) from assimilation (use by tissues) and egestion (removal of undigested material through the anus).""",
                    "After absorption, glucose may be used in respiration or stored as glycogen; amino acids may be used to build new proteins.",
                    "Absorption = movement into blood/lymph; assimilation = use by cells; egestion = removal of undigested food; excretion = removal of metabolic waste.")
            ),
            listOf("Absorption occurs mainly in the small intestine; assimilation occurs in body tissues after absorption.",
                  "Most long-chain fat products enter lacteals, not intestinal blood capillaries directly.",
                  "Egestion is not excretion: undigested food is egested, while metabolic wastes are excreted."),
            listOf(
                JambDeepCheck("bio-nutri-16","Which structure in a villus absorbs most products of fat digestion into lymph?",listOf("Blood capillary","Lacteal","Goblet cell","Muscular layer"),1,"A lacteal is a lymphatic capillary that receives chylomicrons carrying absorbed lipids."),
                JambDeepCheck("bio-nutri-17","The use of absorbed amino acids to build body proteins is called",listOf("egestion","assimilation","peristalsis","emulsification"),1,"Assimilation is the incorporation or use of absorbed nutrients by cells and tissues."),
                JambDeepCheck("bio-nutri-18","Which statement correctly distinguishes egestion from excretion?",listOf("Egestion removes undigested food; excretion removes metabolic wastes","Both terms mean removal of urine only","Excretion removes undigested food from the gut","Egestion is the breakdown of proteins"),0,"Egestion removes material that was not digested or absorbed; excretion removes waste products of metabolism.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-nutrition") objectives else emptyList()
}

@Composable
internal fun PhotosynthesisFactorInvestigation() {
    var light by remember { mutableStateOf(5f) }
    var carbonDioxide by remember { mutableStateOf(5f) }
    var waterAvailable by remember { mutableStateOf(true) }
    var temperature by remember { mutableStateOf("Moderate") }
    val limiting = when {
        !waterAvailable -> "Water availability is restricting photosynthesis"
        temperature == "Too low" -> "Low temperature is slowing enzyme-controlled reactions"
        temperature == "Too high" -> "Excessive temperature may impair enzymes and membranes"
        light < carbonDioxide -> "Light intensity is the more limiting of the two selected factors"
        carbonDioxide < light -> "Carbon dioxide concentration is the more limiting of the two selected factors"
        else -> "Light and carbon dioxide are balanced in this simplified comparison"
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("PHOTOSYNTHESIS FACTOR INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Change one condition at a time and predict why the rate may rise, level off or fall.")
            Text("Light intensity: ${light.toInt()}/10", fontWeight = FontWeight.SemiBold)
            Slider(value = light, onValueChange = { light = it }, valueRange = 0f..10f)
            Text("Carbon dioxide availability: ${carbonDioxide.toInt()}/10", fontWeight = FontWeight.SemiBold)
            Slider(value = carbonDioxide, onValueChange = { carbonDioxide = it }, valueRange = 0f..10f)
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = waterAvailable, onCheckedChange = { waterAvailable = it })
                Text("Water is adequately available")
            }
            Text("Temperature condition", fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("Too low", "Moderate", "Too high").forEach { option ->
                    FilterChip(selected = temperature == option, onClick = { temperature = option }, label = { Text(option, style = MaterialTheme.typography.labelSmall) })
                }
            }
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("INTERPRETATION", fontWeight = FontWeight.Bold)
                    Text(limiting)
                    Text("This is a qualitative teaching model, not a measured biological rate. In a real experiment, measure oxygen production or carbon dioxide uptake while controlling other variables.")
                }
            }
            TextButton(onClick = { light = 5f; carbonDioxide = 5f; waterAvailable = true; temperature = "Moderate" }) { Text("Reset investigation") }
        }
    }
}
