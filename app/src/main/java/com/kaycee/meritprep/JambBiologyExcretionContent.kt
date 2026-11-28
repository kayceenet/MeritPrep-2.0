package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led JAMB coverage of excretion, excretory structures and kidney osmoregulation. */
internal object JambBiologyExcretionContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Define excretion, explain its significance and distinguish it from egestion and secretion.",
            listOf(
                JambDeepPoint("Meaning and importance of excretion",
                    """Excretion is the removal from an organism of metabolic waste products and substances present in excess of the body's requirements. Metabolism continuously produces substances that must be removed or controlled. Carbon dioxide is produced during cellular respiration; excess amino acids are deaminated in the liver and the resulting ammonia is converted mainly to urea in mammals. Excess water and mineral ions also need regulation.

                    If carbon dioxide accumulates, it can alter blood pH. If nitrogenous wastes accumulate, they can become toxic. Excess water or salts disturb osmotic balance and cell function. Excretion therefore protects the internal environment and contributes to homeostasis. It is not simply the removal of anything the body does not want: the material must be a metabolic waste or be present in excess.""",
                    "A mammal removes carbon dioxide through the lungs and urea through the kidneys. The kidneys also adjust the volume and concentration of urine as water intake changes.",
                    "Excretion removes metabolic waste and excess substances; it supports homeostasis and prevents harmful accumulation."),
                JambDeepPoint("Excretion, egestion and secretion",
                    """Egestion is the elimination of undigested or unabsorbed food from the alimentary canal as faeces. It is not excretion because faeces are not mainly products of cellular metabolism. Secretion is the release of a substance made by a cell or gland for a function: examples include digestive enzymes, mucus and hormones. A substance can be secreted and later eliminated, but the terms describe different processes.

                    Excretion includes removal of carbon dioxide, urea and excess salts. Sweating removes water and salts and small quantities of urea; kidney filtration removes urea and regulates ions and water. Defaecation is egestion, not excretion. Do not classify all body fluids or all substances leaving the body as excretory products.""",
                    "Removal of undigested fibre in faeces is egestion; release of insulin is secretion; removal of urea in urine is excretion.",
                    "Classify by origin and role: metabolic waste = excretion; undigested food = egestion; useful product released by a gland = secretion."),
                JambDeepPoint("Nitrogenous wastes and habitats",
                    """Ammonia is highly toxic and requires much water for dilution, so it is commonly excreted by many aquatic animals. Many terrestrial mammals convert ammonia to urea in the liver; urea is less toxic and can be transported in blood to the kidneys. Birds, reptiles and many insects excrete nitrogen mainly as uric acid, which is relatively insoluble and conserves water. These patterns are adaptations, not absolute rules for every species.

                    The liver is important in nitrogen waste processing: deamination removes the amino group from excess amino acids, and the urea cycle converts toxic ammonia into urea. The kidneys then remove urea from blood. The liver produces urea, while the kidneys excrete it; these roles should not be confused.""",
                    "A bird's uric acid paste conserves water; a mammal's kidneys remove urea formed mainly in the liver.",
                    "Ammonia is very toxic and needs much water; urea is less toxic; uric acid conserves water particularly well.")
            ),
            listOf("Egestion is not excretion: faeces are mainly undigested food.", "The liver forms urea; the kidneys remove it from blood.", "Excretion includes removal of excess water and ions, not only nitrogenous waste."),
            listOf(
                JambDeepCheck("bio-excr-01","Which statement defines excretion most accurately?",listOf("Removal of undigested food from the gut","Removal of metabolic wastes and substances in excess","Release of enzymes into the intestine","Absorption of digested nutrients"),1,"Excretion removes metabolic waste products and substances present in excess."),
                JambDeepCheck("bio-excr-02","Removal of undigested cellulose in faeces is called",listOf("excretion","secretion","egestion","deamination"),2,"Egestion is the removal of undigested or unabsorbed material from the alimentary canal."),
                JambDeepCheck("bio-excr-03","In mammals, urea is formed mainly in the",listOf("lungs","liver","urinary bladder","skin"),1,"The liver converts toxic ammonia produced during amino-acid deamination into urea.")
            )
        ),
        JambDeepObjective(
            "Identify contractile vacuoles, flame cells, nephridia, Malpighian tubules, kidneys, stomata and lenticels and relate structure to function.",
            listOf(
                JambDeepPoint("Excretory structures in invertebrates",
                    """A contractile vacuole in many freshwater protozoans collects excess water entering by osmosis and periodically expels it. It is primarily osmoregulatory, although dissolved wastes may leave with the water. Flame cells are specialised cells of flatworms; cilia drive fluid through a network of tubules and out through excretory pores. The system removes excess water and helps regulate salts.

                    Nephridia are tubular excretory structures in annelids such as earthworms. Coelomic fluid or blood-derived fluid is processed along the tubule; useful substances can be reabsorbed while wastes and excess water are discharged. Malpighian tubules in insects extend from the gut into the body cavity. They take nitrogenous wastes and ions from haemolymph; water and useful salts are largely recovered in the hindgut, and nitrogen is often eliminated as uric acid.""",
                    "A Paramecium uses a contractile vacuole to expel excess water; an earthworm uses nephridia; a grasshopper uses Malpighian tubules.",
                    "Match each structure to its organism and fluid pathway: contractile vacuole—protozoan; flame cell—flatworm; nephridium—annelid; Malpighian tubule—insect."),
                JambDeepPoint("Kidneys and plant gas-exchange structures",
                    """Vertebrate kidneys contain many nephrons that filter blood and selectively reabsorb useful substances while regulating water and ions. The resulting urine carries nitrogenous wastes out through the urinary tract. In plants, stomata are adjustable pores in the epidermis, commonly surrounded by guard cells. They permit carbon dioxide and oxygen exchange and allow water vapour to diffuse out during transpiration. Lenticels are porous regions in the bark of woody stems and some roots; they permit gas exchange with internal tissues.

                    Stomata and lenticels are not animal kidneys and do not produce urine. Plants do not have a specialised excretory organ comparable to a mammalian kidney. They may release gases by diffusion, store substances in vacuoles or tissues, and remove wastes when leaves or bark are shed.""",
                    "A leaf releases oxygen produced during photosynthesis through stomata; a woody stem exchanges gases through lenticels.",
                    "Stomata are regulated by guard cells; lenticels are porous openings in woody tissues; kidneys contain nephrons."),
                JambDeepPoint("Compare the designs",
                    """Excretory structures differ because organisms differ in size, habitat and body organisation. A single-celled organism can expel water directly through a contractile vacuole. Flatworms and annelids use branching tubules to collect and discharge fluid. Insects conserve water by recovering much of it in the hindgut after Malpighian tubules transfer wastes into the gut. Vertebrate kidneys use filtration and selective reabsorption to regulate the composition of body fluids.

                    In examination questions, identify the organism first, then trace what enters the structure, what is retained or reabsorbed, and what leaves. A structure's name alone is not enough: explain how its design supports removal of waste or regulation of water and salts.""",
                    "An insect's Malpighian tubules work with the hindgut; water recovery helps reduce water loss on land.",
                    "Structure-function explanations should mention collection, filtration, cilia, tubules, reabsorption or controlled pore opening as relevant.")
            ),
            listOf("A contractile vacuole is especially important for freshwater osmoregulation.", "Flame cells belong to flatworms; nephridia to annelids; Malpighian tubules to insects.", "Stomata and lenticels permit plant gas exchange; they do not form urine."),
            listOf(
                JambDeepCheck("bio-excr-04","The contractile vacuole of a freshwater Paramecium mainly",listOf("stores glucose","expels excess water","produces urea","absorbs oxygen actively"),1,"Water enters many freshwater protozoans by osmosis; the contractile vacuole collects and expels excess water."),
                JambDeepCheck("bio-excr-05","Flame cells are characteristic excretory structures of",listOf("flatworms","insects","mammals","flowering plants"),0,"Flame cells form part of the protonephridial system of flatworms."),
                JambDeepCheck("bio-excr-06","Malpighian tubules are found in",listOf("earthworms","insects","fish","amoeba"),1,"Insects use Malpighian tubules to transfer wastes and ions from haemolymph into the gut.")
            )
        ),
        JambDeepObjective(
            "Relate kidney and nephron structure to filtration, selective reabsorption and osmoregulation.",
            listOf(
                JambDeepPoint("Gross structure of the kidney and route of urine",
                    """A mammalian kidney has an outer cortex, an inner medulla and a central renal pelvis that collects urine and funnels it into the ureter. The renal artery brings blood containing wastes to the kidney; branches supply the nephrons. The renal vein carries blood away after its composition has been adjusted. Urine passes from collecting ducts into the renal pelvis, down the ureter to the urinary bladder, and out through the urethra.

                    The cortex contains renal corpuscles and much of the convoluted tubules. The medulla contains loops of Henle and collecting ducts arranged in a way that supports the osmotic gradient needed to concentrate urine. The renal pelvis is a collecting region, not the site where filtration occurs. The urinary bladder stores urine; it does not produce it.""",
                    "Trace the route: renal artery → glomerulus → nephron tubules → collecting duct → renal pelvis → ureter → bladder → urethra.",
                    "Cortex: renal corpuscles and convoluted tubules; medulla: loops and collecting ducts; pelvis: urine collection."),
                JambDeepPoint("Nephron and ultrafiltration",
                    """A nephron begins with Bowman's capsule surrounding a capillary knot called the glomerulus. Blood enters through the wider afferent arteriole and leaves through the narrower efferent arteriole; this arrangement helps create high hydrostatic pressure in glomerular capillaries. Water and small dissolved substances such as glucose, amino acids, salts and urea are forced through the filtration barrier into Bowman's space. Blood cells and most plasma proteins remain in the blood because they are too large to pass through the normal barrier.

                    The fluid entering Bowman's capsule is glomerular filtrate, not urine in its final form. Ultrafiltration is a pressure-driven process; it is not selective reabsorption. The filtrate is modified as it passes through the proximal convoluted tubule, loop of Henle, distal convoluted tubule and collecting duct.""",
                    "A healthy glomerular filtrate normally contains glucose and amino acids, but not blood cells or significant plasma proteins.",
                    "Ultrafiltration occurs at the glomerulus into Bowman's capsule; blood cells and most proteins remain in capillaries."),
                JambDeepPoint("Selective reabsorption and hormonal control",
                    """The proximal convoluted tubule reabsorbs nearly all filtered glucose and amino acids under normal conditions, along with much of the filtered water and salts. Reabsorption uses active transport, facilitated diffusion and osmosis depending on the substance. The loop of Henle establishes a medullary osmotic gradient: the descending limb is relatively permeable to water, while the ascending limb transports salts and is relatively impermeable to water. This countercurrent arrangement helps the kidney produce concentrated urine.

                    The distal tubule and collecting duct fine-tune ion and water balance. Antidiuretic hormone (ADH), released from the posterior pituitary, increases water permeability in the collecting ducts. More ADH generally means more water is reabsorbed and a smaller volume of concentrated urine is produced. With less ADH, less water is reabsorbed and a larger volume of dilute urine is produced. Aldosterone increases sodium reabsorption and potassium secretion in the distal nephron; water may follow sodium depending on conditions.""",
                    "After heavy sweating, increased ADH helps conserve water, so urine volume falls and urine becomes more concentrated.",
                    "Filtration makes the initial filtrate; selective reabsorption returns useful substances; ADH adjusts collecting-duct water permeability.")
            ),
            listOf("Filtrate is not final urine; it is modified along the nephron.", "Glucose is normally reabsorbed in the proximal tubule, not excreted in urine.", "ADH increases water reabsorption and usually reduces urine volume."),
            listOf(
                JambDeepCheck("bio-excr-07","Ultrafiltration takes place in the",listOf("collecting duct","glomerulus and Bowman's capsule","ureter","urinary bladder"),1,"High pressure in glomerular capillaries forces water and small solutes into Bowman's capsule."),
                JambDeepCheck("bio-excr-08","Which substance is normally almost completely reabsorbed from the filtrate in a healthy kidney?",listOf("Urea","Glucose","Creatinine","Excess hydrogen ions"),1,"Filtered glucose is normally reabsorbed in the proximal convoluted tubule."),
                JambDeepCheck("bio-excr-09","When ADH secretion increases, the kidneys usually produce",listOf("a larger volume of dilute urine","a smaller volume of concentrated urine","urine containing more glucose","no glomerular filtrate"),1,"ADH increases water permeability in collecting ducts, increasing water reabsorption.")
            )
        ),
        JambDeepObjective(
            "Identify the excretory products and functions of the lungs and skin.",
            listOf(
                JambDeepPoint("Lungs as excretory organs",
                    """The lungs remove carbon dioxide produced by cellular respiration and water vapour from the body. Carbon dioxide is carried in blood mainly as bicarbonate ions, with smaller amounts dissolved in plasma or bound to haemoglobin. In pulmonary capillaries, reactions convert bicarbonate back to carbon dioxide, which diffuses into alveoli and leaves during expiration. Water evaporates from moist respiratory surfaces and is lost in exhaled air.

                    The lungs are primarily respiratory organs, but removal of carbon dioxide and water vapour is also excretion. Oxygen entering the lungs is not an excretory product; it is taken into the body for aerobic respiration.""",
                    "Exhaled air contains more carbon dioxide and usually more water vapour than inhaled air.",
                    "Lungs excrete carbon dioxide and water vapour; oxygen is absorbed, not excreted."),
                JambDeepPoint("Skin and sweat",
                    """Sweat glands in the skin produce sweat containing mainly water, dissolved salts (especially sodium chloride) and small amounts of urea and other substances. Evaporation of sweat removes heat and helps regulate body temperature. Sweat production also causes some loss of water and salts, but the kidneys are the principal organs regulating long-term water and electrolyte balance and removing most urea.

                    The skin also protects the body and contains sensory receptors. Do not overstate its excretory role: sweat is not the main route for urea removal in humans. Sebaceous glands release sebum, an oily secretion; sebum is not the same as sweat and is not a major excretory product.""",
                    "During hot weather, sweating and evaporation help cool the body; the kidneys conserve water by producing less urine.",
                    "Sweat contains water, salts and traces of urea; its major function in temperature regulation is evaporative cooling."),
                JambDeepPoint("Homeostasis across organs",
                    """The lungs, skin, liver and kidneys contribute to maintaining a stable internal environment. The lungs regulate carbon dioxide removal and therefore influence blood pH. The skin loses water and salts in sweat and regulates heat loss. The liver converts ammonia to urea and processes other substances. The kidneys regulate the excretion of urea, salts, water and hydrogen ions and help maintain blood volume, osmotic concentration and acid-base balance.

                    These organs work together rather than independently. For example, dehydration stimulates thirst and ADH release; the kidneys conserve water, while sweat production may be reduced. During exercise, ventilation and circulation increase to deliver oxygen and remove carbon dioxide and heat.""",
                    "After exercise, increased ventilation removes additional carbon dioxide while sweating and renal responses help regulate temperature and fluid balance.",
                    "Explain homeostasis by linking the organ, substance controlled, mechanism and effect on the internal environment.")
            ),
            listOf("The lungs excrete carbon dioxide; oxygen is absorbed.", "Sweat is mostly water and salts, with only small amounts of urea.", "The kidneys, not the skin, remove most urea in humans."),
            listOf(
                JambDeepCheck("bio-excr-10","The principal gaseous excretory product removed by human lungs is",listOf("oxygen","nitrogen","carbon dioxide","hydrogen"),2,"Carbon dioxide is produced during cellular respiration and removed through the lungs."),
                JambDeepCheck("bio-excr-11","Which substance is normally present in sweat?",listOf("Large quantities of glucose","Water and mineral salts","Bile pigments only","Digestive enzymes"),1,"Sweat consists mainly of water and salts, with small quantities of urea and other substances."),
                JambDeepCheck("bio-excr-12","The main role of sweating in temperature regulation is",listOf("increasing blood glucose","evaporative cooling","producing red blood cells","filtering blood plasma"),1,"Evaporation of sweat from the skin surface removes heat from the body.")
            )
        ),
        JambDeepObjective(
            "Explain the economic and biological importance of excretory products in plants.",
            listOf(
                JambDeepPoint("Gaseous products and plant waste handling",
                    """Plants produce oxygen during photosynthesis and carbon dioxide during respiration. These gases diffuse through stomata, lenticels and other exchange surfaces according to concentration gradients. Oxygen released during photosynthesis supports aerobic respiration in other organisms; carbon dioxide released by respiration can be used in photosynthesis by plants and algae. These gases are useful to ecosystems even though their release is not always described as disposal of a harmful waste.

                    Plants have no specialised kidney. Some unwanted substances are stored in vacuoles, leaves, bark, heartwood or other tissues. Wastes may be removed when leaves, bark or fruits are shed. Some substances diffuse out through roots or leaves, while others are secreted or deposited in specialised structures.""",
                    "Oxygen released by a green leaf in light can be used by animals and microorganisms for aerobic respiration.",
                    "Plants manage wastes by diffusion, storage, deposition and shedding of plant parts."),
                JambDeepPoint("Tannins, resins, gums and mucilage",
                    """Tannins are polyphenolic compounds found in many plant tissues. They can make tissues less palatable, deter herbivores and have commercial uses in leather tanning and some traditional preparations. Resins are sticky substances produced by some plants, especially conifers; they can seal wounds and deter insects and microbes. Commercially, resins are used in varnishes, adhesives, incense and related products.

                    Gums are polysaccharide-rich substances that may ooze from injured plant tissues and can help seal wounds. Some gums, such as gum arabic, have food, pharmaceutical and industrial uses as stabilisers or binders. Mucilage is a slippery, water-holding substance found in plants such as okra and some succulents. It can aid water storage and has food and pharmaceutical applications. These substances have protective or storage roles in plants and can also be economically useful to humans.""",
                    "Gum arabic is used as a stabiliser; plant resins are used in varnishes; okra mucilage contributes to its slippery texture.",
                    "Know the product, plant role and human use; do not assume all plant excretory products are useless toxins."),
                JambDeepPoint("Alkaloids and other useful compounds",
                    """Alkaloids are nitrogen-containing organic compounds made by plants. Many have strong physiological effects and can deter herbivores or pathogens. Examples include quinine from Cinchona, which has been used to treat malaria; caffeine in coffee and tea; nicotine in tobacco; and morphine from the opium poppy, used medically as a potent analgesic under controlled conditions. Their biological activity can make them useful medicines but also toxic or dependence-forming, depending on the compound and dose.

                    Plant products may also include latex, essential oils, crystals and other secondary metabolites. Their functions include defence, wound sealing, attraction of pollinators or seed dispersers and storage. Economic importance should be described accurately: a compound may have industrial or medical value while also presenting risks if misused.""",
                    "Quinine is an alkaloid associated with Cinchona; caffeine is an alkaloid found in coffee and tea plants.",
                    "Alkaloids are nitrogen-containing plant compounds; give a correct example and a balanced use or biological role.")
            ),
            listOf("Oxygen is released by photosynthesis; carbon dioxide is released by respiration.", "Resins, gums, tannins and mucilage have protective roles and commercial uses.", "Alkaloids are biologically active nitrogen-containing compounds; not all are safe."),
            listOf(
                JambDeepCheck("bio-excr-13","Tannins in plants are commonly associated with",listOf("deterring herbivores and leather tanning","oxygen transport in xylem","starch digestion in the gut","urine formation"),0,"Tannins can deter feeding and are used in leather tanning."),
                JambDeepCheck("bio-excr-14","Gum arabic is an example of a plant product used as",listOf("a blood pigment","a food and industrial stabiliser","a respiratory enzyme","a mineral fertiliser"),1,"Gum arabic is used as a stabiliser and binder in food and other products."),
                JambDeepCheck("bio-excr-15","Which statement about plant alkaloids is correct?",listOf("They are all harmless sugars","They are nitrogen-containing compounds, many with strong biological effects","They are found only in animal kidneys","They are the main structural material of xylem"),1,"Alkaloids are nitrogen-containing plant compounds; some have medicinal value and some can be toxic.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-excretion") objectives else emptyList()
}

@Composable
internal fun NephronOsmoregulationLab() {
    var condition by remember { mutableStateOf("Dehydrated") }
    var stage by remember { mutableStateOf(0) }
    val stages = listOf(
        "Glomerulus and Bowman's capsule: blood pressure drives water and small solutes into the filtrate. Blood cells and most plasma proteins remain in the capillaries.",
        "Proximal convoluted tubule: glucose, amino acids, much of the filtered water and useful ions are reabsorbed into surrounding capillaries.",
        "Loop of Henle: water leaves the descending limb; salts leave the ascending limb. This helps establish the medullary osmotic gradient.",
        "Distal tubule and collecting duct: ion balance is adjusted and ADH changes collecting-duct water permeability."
    )
    val outcome = when (condition) {
        "Dehydrated" -> "ADH secretion rises. More water is reabsorbed from collecting ducts, so urine volume falls and urine becomes more concentrated."
        "Well hydrated" -> "ADH is at a usual baseline. The kidneys balance water intake and loss, producing an intermediate urine volume and concentration."
        else -> "ADH secretion falls. Less water is reabsorbed in collecting ducts, so urine volume rises and urine becomes more dilute."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("NEPHRON INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Follow the filtrate", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Select a hydration condition, then move through the nephron. This is a qualitative learning model, not a measured clinical prediction.")
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Dehydrated", "Well hydrated", "High water intake").forEach { option ->
                    FilterChip(selected = condition == option, onClick = { condition = option }, label = { Text(option) })
                }
            }
            Text("Stage ${stage + 1} of ${stages.size}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Text(stages[stage], style = MaterialTheme.typography.bodyLarge)
            LinearProgressIndicator(progress = { (stage + 1) / stages.size.toFloat() }, modifier = Modifier.fillMaxWidth())
            Text("Predicted response", fontWeight = FontWeight.Bold)
            Text(outcome)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { stage = (stage - 1).coerceAtLeast(0) }, enabled = stage > 0) { Text("Previous") }
                Button(onClick = { stage = (stage + 1).coerceAtMost(stages.lastIndex) }, enabled = stage < stages.lastIndex) { Text("Next stage") }
                TextButton(onClick = { stage = 0; condition = "Dehydrated" }) { Text("Reset") }
            }
        }
    }
    BiologyExternalLabLinks(
        title = "FREE KIDNEY AND OSMOREGULATION RESOURCES",
        resources = listOf(
            BiologyLabResource("Kidney and nephron learning resources", "Open educational kidney and nephron activities from the Kidney Foundation of Canada.", "https://kidney.ca/"),
            BiologyLabResource("HHMI BioInteractive", "Explore free biology interactives and classroom resources; search for kidney and homeostasis activities.", "https://www.biointeractive.org/")
        )
    )
}
