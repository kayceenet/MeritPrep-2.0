package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led JAMB treatment of transport in plants and mammals. */
internal object JambBiologyTransportContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Distinguish diffusion, osmosis and active transport and explain movement down concentration gradients.",
            listOf(
                JambDeepPoint("Diffusion and factors affecting its rate",
                    """Diffusion is the net movement of particles from a region of higher concentration to a region of lower concentration as a result of random molecular motion. It is passive: cells do not directly supply metabolic energy for the movement. Diffusion continues in both directions, but there is a net movement down the concentration gradient until equilibrium is approached. Oxygen diffuses from alveolar air into blood when its partial pressure is higher in the alveoli; carbon dioxide diffuses in the opposite direction when its partial pressure is higher in blood.

                    Diffusion is faster when the concentration gradient is steeper, temperature is higher, surface area is larger and diffusion distance is shorter. Small, non-polar molecules such as oxygen and carbon dioxide can cross lipid membranes relatively easily; ions and many polar molecules require channels or carriers. Facilitated diffusion uses membrane proteins but still moves substances down their gradient without direct ATP input. Do not confuse facilitated diffusion with active transport.""",
                    "Oxygen moves from alveoli into pulmonary capillary blood down its partial-pressure gradient.",
                    "Diffusion is passive and moves down a gradient. Rate depends on gradient, temperature, surface area and diffusion distance."),
                JambDeepPoint("Osmosis and active transport",
                    """Osmosis is the net movement of water molecules across a selectively permeable membrane from higher water potential to lower water potential. In a simplified comparison, water moves from a more dilute solution towards a more concentrated solution if the membrane allows water through but restricts the solute. A plant cell in a dilute solution may become turgid as its vacuole takes up water; in a sufficiently concentrated solution, the protoplast may pull away from the cell wall (plasmolysis). Animal cells have no cell wall and may swell and burst in a very dilute solution or shrink in a concentrated solution.

                    Active transport moves substances across membranes against a concentration or electrochemical gradient using energy, commonly from ATP, and specific carrier proteins or pumps. Root hair cells can absorb mineral ions from dilute soil water even when the concentration inside the cell is higher. The sodium-potassium pump in animal cells is another example. The key distinction is energy and direction: diffusion and osmosis are passive; active transport can move substances against a gradient.""",
                    "A red blood cell placed in pure water swells because water enters by osmosis; a root hair cell can actively absorb nitrate ions.",
                    "Osmosis concerns water and a selectively permeable membrane; active transport uses energy to move solutes against a gradient.")
            ),
            listOf("Osmosis is the movement of water, not the movement of dissolved sugar or salt.",
                  "Facilitated diffusion uses a protein but does not directly require ATP.",
                  "Active transport moves against a gradient and requires energy; diffusion moves down a gradient."),
            listOf(
                JambDeepCheck("bio-trans-01","The net movement of oxygen from alveoli into blood is mainly by",listOf("active transport","diffusion","osmosis","translocation"),1,"Oxygen moves down its partial-pressure gradient across the thin alveolar and capillary walls."),
                JambDeepCheck("bio-trans-02","Osmosis is the net movement of",listOf("solute from low to high concentration","water across a selectively permeable membrane down a water-potential gradient","all particles using ATP","oxygen through xylem"),1,"Osmosis specifically describes net water movement across a selectively permeable membrane."),
                JambDeepCheck("bio-trans-03","Root hair cells may absorb mineral ions against a concentration gradient by",listOf("osmosis","active transport","simple diffusion","transpiration"),1,"Active transport uses energy and membrane proteins to take up ions against their gradient.")
            )
        ),
        JambDeepObjective(
            "Describe water uptake by roots and the movement of water through root tissues into xylem.",
            listOf(
                JambDeepPoint("Root hairs and pathways through the root",
                    """Root hairs are extensions of epidermal cells that greatly increase the surface area in contact with soil water. Their thin walls and close contact with soil particles support water uptake. Water enters root hair cells by osmosis when the water potential outside is higher than inside. Mineral ions may enter by diffusion or active transport depending on their gradients and the plant's needs.

                    Water can move across the cortex by the apoplast pathway (through cell walls and intercellular spaces) or the symplast pathway (through cytoplasm connected by plasmodesmata). The Casparian strip in the endodermis blocks unrestricted apoplastic movement and forces water and ions to cross a selectively permeable membrane before entering the vascular cylinder. The pericycle lies just inside the endodermis and can give rise to lateral roots. Xylem in the stele then carries water towards the shoot.""",
                    "The Casparian strip helps regulate which water and dissolved ions enter the vascular cylinder.",
                    "Root hairs increase surface area; apoplast = cell walls; symplast = cytoplasm; endodermis controls entry into the stele."),
                JambDeepPoint("Water potential and root pressure",
                    """Water potential describes the tendency of water to move. Water moves from a region of higher water potential to lower water potential when a suitable pathway exists. Dissolved solutes lower water potential; pressure can raise it. In the school-level relationship, water potential is the sum of solute potential and pressure potential. Root cells can maintain solute gradients that favour water entry, and active ion uptake can contribute to water movement into the xylem.

                    Root pressure can develop when ions accumulate in xylem and water enters osmotically, sometimes causing guttation at leaf margins when transpiration is low. Root pressure is not sufficient to explain water ascent in tall trees; transpiration pull and cohesion-tension are the main explanation for long-distance upward movement. Avoid saying water is pushed up the entire plant solely by roots.""",
                    "Guttation droplets may appear at leaf margins when root pressure drives xylem sap out through hydathodes.",
                    "Water moves down a water-potential gradient. Root pressure may contribute, but transpiration pull explains ascent in tall plants.")
            ),
            listOf("Root hairs are single-cell extensions, not separate multicellular roots.",
                  "The Casparian strip blocks unrestricted apoplastic flow at the endodermis.",
                  "Root pressure can cause guttation but does not explain water ascent in tall trees by itself."),
            listOf(
                JambDeepCheck("bio-trans-04","Root hairs increase the rate of water absorption mainly by",listOf("reducing root surface area","increasing surface area in contact with soil water","producing glucose","preventing osmosis"),1,"Their numerous extensions provide a large surface area for absorption."),
                JambDeepCheck("bio-trans-05","The Casparian strip is found in the",listOf("epidermis","endodermis","palisade mesophyll","phloem sieve plate"),1,"The Casparian strip in endodermal walls regulates movement into the vascular cylinder."),
                JambDeepCheck("bio-trans-06","Guttation is most directly associated with",listOf("root pressure and water loss through hydathodes","evaporation through stomata only","food movement in phloem","oxygen release in photosynthesis"),0,"Root pressure can force xylem sap through hydathodes, producing droplets at leaf margins.")
            )
        ),
        JambDeepObjective(
            "Explain transpiration, the cohesion-tension mechanism and factors affecting water loss from leaves.",
            listOf(
                JambDeepPoint("Transpiration and the cohesion-tension mechanism",
                    """Transpiration is the loss of water vapour from aerial parts of a plant, mainly through stomata. Water evaporates from moist mesophyll cell walls into leaf air spaces and diffuses out through open stomata. This evaporation lowers water potential in leaf tissues and creates tension in xylem. Because water molecules cohere to one another through hydrogen bonding and adhere to xylem walls, a continuous water column can be pulled upwards. This is the cohesion-tension mechanism.

                    Xylem vessels and tracheids have lignified walls that resist collapse under tension. Water moving through xylem replaces water lost from leaves and carries dissolved mineral ions. Transpiration also contributes to cooling. The rate is affected by stomatal aperture, leaf area, cuticle thickness, air movement, humidity, temperature and light. Do not describe transpiration as the same process as water absorption: absorption occurs mainly at roots, while transpiration is water-vapour loss from aerial parts.""",
                    "On a hot, dry and windy day, transpiration may increase because evaporation and diffusion of water vapour are promoted.",
                    "Evaporation from mesophyll creates tension; cohesion maintains the water column; xylem carries the water upward."),
                JambDeepPoint("Factors and fair tests of transpiration",
                    """High temperature generally increases evaporation; low relative humidity maintains a steep water-vapour gradient between leaf air spaces and the atmosphere; wind removes the moist boundary layer around leaves; and bright light often promotes stomatal opening. These conditions can increase transpiration. High humidity, still air, low temperature, darkness and stomatal closure usually reduce it. Water shortage may cause stomata to close, reducing water loss but also limiting carbon dioxide uptake.

                    A potometer estimates water uptake by a leafy shoot and is often used as an indirect estimate of transpiration rate. It does not measure water-vapour loss directly because some absorbed water is used in photosynthesis, growth or maintaining cell turgor. Cut the shoot under water where possible, seal the apparatus to prevent leaks, introduce an air bubble, allow equilibration and change one environmental factor at a time. Repeat readings and calculate distance or volume moved per unit time.""",
                    "A potometer can compare how quickly an air bubble moves when wind speed changes, provided other conditions are controlled.",
                    "Potometer measures water uptake as a proxy, not transpiration directly. Control leaf area, temperature, humidity and light.")
            ),
            listOf("Transpiration is loss of water vapour; guttation is loss of liquid xylem sap.",
                  "A potometer estimates transpiration indirectly by measuring water uptake.",
                  "Wind usually increases transpiration by removing humid air near the leaf, but extreme water stress may close stomata."),
            listOf(
                JambDeepCheck("bio-trans-07","The immediate force that creates tension in leaf xylem during transpiration is",listOf("evaporation of water from mesophyll cell walls","active transport of glucose","photosynthesis in roots","movement of sucrose in phloem"),0,"Evaporation from moist mesophyll surfaces creates tension that pulls water through xylem."),
                JambDeepCheck("bio-trans-08","A potometer measures",listOf("water vapour leaving a leaf directly","water uptake by a shoot as an estimate of transpiration","photosynthetic glucose production","mineral ion concentration in soil"),1,"A potometer measures water uptake, which is used as an indirect estimate of transpiration."),
                JambDeepCheck("bio-trans-09","Which condition is most likely to increase transpiration?",listOf("High humidity and still air","Low temperature and darkness","Dry air and moving air","Closed stomata and high humidity"),2,"Dry moving air maintains a steep water-vapour gradient and removes the humid boundary layer.")
            )
        ),
        JambDeepObjective(
            "Explain translocation of manufactured food through phloem from sources to sinks.",
            listOf(
                JambDeepPoint("Sources, sinks and phloem structure",
                    """Translocation is the movement of dissolved organic assimilates, especially sucrose, through phloem. A source releases assimilates, for example a mature photosynthesising leaf or a storage organ mobilising stored food. A sink uses or stores assimilates, for example a growing root, developing fruit, seed or young shoot. A plant organ can act as a source at one time and a sink at another as its developmental state changes.

                    Phloem contains sieve tube elements, companion cells, phloem parenchyma and often fibres. Sieve tube elements are living cells at maturity but have reduced contents and lack a nucleus; sieve plates connect adjacent elements. Companion cells contain nuclei and many mitochondria and support sieve tube elements metabolically. Plasmodesmata connect companion cells and sieve elements. Phloem is not simply a downward-flowing tissue: movement can occur in different directions in separate sieve tubes according to source-sink relationships.""",
                    "A mature leaf may export sucrose to a developing fruit; a germinating seed may export stored food to the growing embryo.",
                    "Source supplies assimilates; sink uses or stores them. Phloem sieve tubes are supported by companion cells."),
                JambDeepPoint("Pressure-flow model",
                    """In the pressure-flow model, sucrose is loaded into sieve tubes at a source, often using transport proteins and energy. The increased solute concentration lowers water potential, so water enters from nearby xylem by osmosis. This raises hydrostatic pressure at the source. At a sink, sucrose is unloaded and used or stored; water potential rises, water may leave the phloem, and pressure falls. The resulting pressure gradient drives bulk flow of phloem sap from source to sink.

                    This model explains mass flow of solution through sieve tubes. It does not mean every solute is moved by simple diffusion from leaf to root. Loading and unloading mechanisms vary among plants and sinks, and some details remain active areas of research. For JAMB-level questions, be able to identify sources and sinks, explain water entry by osmosis, and distinguish phloem translocation from xylem transport.""",
                    "Sucrose loading at a leaf lowers sieve-tube water potential; water enters from xylem and creates pressure that helps drive sap towards a sink.",
                    "Loading increases solute concentration; water enters by osmosis; pressure drives mass flow; unloading at sinks reduces pressure.")
            ),
            listOf("Phloem transports organic assimilates, not only sucrose and not only downwards.",
                  "Xylem transports water and mineral ions; phloem transports assimilates from sources to sinks.",
                  "Pressure-flow involves osmotic water entry and hydrostatic pressure differences."),
            listOf(
                JambDeepCheck("bio-trans-10","A mature photosynthesising leaf exporting sucrose is a",listOf("sink","source","hydathode","root cap"),1,"A source is an organ that releases assimilates for transport."),
                JambDeepCheck("bio-trans-11","In the pressure-flow model, water enters sieve tubes at a source because",listOf("sucrose loading lowers their water potential","sucrose raises water potential","xylem stops functioning","stomata close permanently"),0,"Sucrose loading lowers phloem water potential, favouring osmosis from adjacent xylem."),
                JambDeepCheck("bio-trans-12","The main function of companion cells is to",listOf("support sieve tube elements metabolically","form lignified xylem vessels","produce red blood cells","open stomata"),0,"Companion cells provide metabolic support and assist loading and unloading of assimilates.")
            )
        ),
        JambDeepObjective(
            "Identify the components of blood and explain their transport, defence and clotting functions.",
            listOf(
                JambDeepPoint("Plasma and red blood cells",
                    """Blood is a fluid connective tissue consisting of plasma and formed elements. Plasma is mostly water and carries dissolved nutrients, hormones, urea, ions, proteins and carbon dioxide in several forms. Plasma proteins include albumin, which contributes to osmotic balance; globulins, which include antibodies; and fibrinogen, which is involved in clotting. Plasma also distributes heat and helps maintain pH and fluid balance.

                    Red blood cells (erythrocytes) in mammals are biconcave, flexible and lack a nucleus when mature, leaving more room for haemoglobin. Haemoglobin binds oxygen reversibly to form oxyhaemoglobin in the lungs and releases oxygen in tissues where oxygen partial pressure is lower. Red blood cells also contribute to carbon dioxide transport, although most carbon dioxide is carried as bicarbonate ions in plasma after conversion in red cells. Their biconcave shape gives a large surface area relative to volume and a short diffusion distance.""",
                    "Haemoglobin in red blood cells binds oxygen in the lungs and releases it in respiring tissues.",
                    "Plasma transports dissolved materials; red blood cells transport most oxygen using haemoglobin; most CO₂ travels as bicarbonate."),
                JambDeepPoint("White blood cells and platelets",
                    """White blood cells (leucocytes) defend the body against pathogens. Phagocytes such as neutrophils engulf and digest microbes by phagocytosis. Lymphocytes include B cells, which can differentiate into antibody-producing plasma cells, and T cells, which coordinate immune responses or destroy infected cells. White blood cells have nuclei and can leave capillaries by squeezing between endothelial cells (diapedesis).

                    Platelets are small cell fragments formed from megakaryocytes in bone marrow. They help form a platelet plug at damaged blood vessels and release factors that support the clotting cascade. In the presence of suitable factors and calcium ions, soluble fibrinogen is converted to insoluble fibrin threads that form a mesh trapping blood cells and helping stabilise the clot. Clotting limits blood loss and reduces entry of pathogens. Do not confuse platelets with complete nucleated cells or antibodies with phagocytes.""",
                    "A neutrophil can engulf bacteria; platelets help initiate a clot that is stabilised by fibrin.",
                    "Phagocytes engulf pathogens; lymphocytes coordinate specific immunity and antibody production; platelets support clotting.")
            ),
            listOf("Mature mammalian red blood cells lack a nucleus; most other mammalian blood cells have nuclei.",
                  "Platelets are cell fragments, not complete cells.",
                  "Most carbon dioxide is transported as bicarbonate ions, not simply dissolved gas bound to haemoglobin."),
            listOf(
                JambDeepCheck("bio-trans-13","The oxygen-carrying pigment in red blood cells is",listOf("fibrin","haemoglobin","insulin","keratin"),1,"Haemoglobin binds oxygen reversibly to form oxyhaemoglobin."),
                JambDeepCheck("bio-trans-14","Which blood component is mainly responsible for phagocytosis?",listOf("Red blood cells","Phagocytic white blood cells","Platelets","Plasma water"),1,"Phagocytes such as neutrophils engulf and digest pathogens."),
                JambDeepCheck("bio-trans-15","Fibrin in a blood clot is formed from",listOf("haemoglobin","fibrinogen","albumin","glucose"),1,"During clotting, soluble fibrinogen is converted into insoluble fibrin threads.")
            )
        ),
        JambDeepObjective(
            "Compare arteries, veins and capillaries and relate their structures to blood flow and exchange.",
            listOf(
                JambDeepPoint("Arteries and veins",
                    """Arteries carry blood away from the heart. They have relatively thick walls with abundant smooth muscle and elastic tissue to withstand and smooth out the high, pulsatile pressure generated by ventricular contraction. Their lumen is generally narrower relative to wall thickness than that of a comparable vein. Most arteries carry oxygenated blood, but the pulmonary artery carries deoxygenated blood from the right ventricle to the lungs, and the umbilical arteries carry deoxygenated blood from a fetus to the placenta.

                    Veins carry blood towards the heart. They have thinner walls, less muscle and elastic tissue, and a wider lumen because blood pressure is lower. Many veins, especially in the limbs, contain valves that prevent backflow; skeletal muscle contractions and breathing movements help return venous blood. Most veins carry deoxygenated blood, but pulmonary veins carry oxygenated blood from the lungs to the left atrium. Classify a vessel by direction of flow, not oxygen content.""",
                    "The pulmonary artery carries deoxygenated blood away from the heart; pulmonary veins carry oxygenated blood towards the heart.",
                    "Artery = away from heart, thick elastic wall, high pressure; vein = towards heart, thinner wall, valves often present, low pressure."),
                JambDeepPoint("Capillaries and tissue exchange",
                    """Capillaries are microscopic vessels linking arterioles and venules. Their walls are mainly one endothelial cell thick, creating a short diffusion distance. They form extensive networks with a large total surface area and narrow lumens that bring blood close to tissue cells. Oxygen and dissolved nutrients move from blood to tissues when gradients favour movement; carbon dioxide and other wastes move into blood. At many capillary beds, fluid filters out at the arterial end because hydrostatic pressure is relatively high; much is reabsorbed at the venous end as pressure falls and plasma proteins retain water. Excess tissue fluid enters lymphatic capillaries.

                    Exchange depends on concentration gradients, blood flow, permeability and surface area. Capillary walls are thin but not simply absent. The narrow lumen slows blood and brings red cells close to the wall, supporting exchange. Do not say that capillaries carry blood only to organs: they form networks within tissues and connect the arterial and venous sides of circulation.""",
                    "A capillary network in muscle allows oxygen and glucose to leave blood and carbon dioxide to enter it.",
                    "Capillary walls are one endothelial cell thick; large surface area and short diffusion distance support exchange.")
            ),
            listOf("Arteries are defined by carrying blood away from the heart, not by carrying oxygenated blood.",
                  "Veins are defined by carrying blood towards the heart; pulmonary veins carry oxygenated blood.",
                  "Capillaries have thin endothelial walls for exchange, not thick muscular walls for pressure."),
            listOf(
                JambDeepCheck("bio-trans-16","Which vessel carries blood away from the heart?",listOf("Vein","Artery","Capillary","Lacteal"),1,"Arteries are defined by the direction of blood flow away from the heart."),
                JambDeepCheck("bio-trans-17","The pulmonary vein carries",listOf("deoxygenated blood to the lungs","oxygenated blood to the left atrium","blood from the right ventricle to the lungs","lymph to the heart"),1,"Pulmonary veins return oxygenated blood from the lungs to the left atrium."),
                JambDeepCheck("bio-trans-18","Capillary walls are well suited to exchange because they are",listOf("many layers thick","one endothelial cell thick","made of cartilage","surrounded by valves"),1,"A thin endothelial wall shortens the diffusion distance between blood and tissue fluid.")
            )
        ),
        JambDeepObjective(
            "Trace pulmonary and systemic circulation and explain the role of the heart and lymphatic system.",
            listOf(
                JambDeepPoint("Heart chambers, valves and blood pathway",
                    """The human heart has four chambers. The right atrium receives deoxygenated blood from the venae cavae; it passes through the tricuspid valve into the right ventricle. The right ventricle pumps blood through the pulmonary valve into the pulmonary artery and to the lungs. Oxygenated blood returns through pulmonary veins to the left atrium, passes through the bicuspid (mitral) valve into the left ventricle, and is pumped through the aortic valve into the aorta and systemic circulation. Valves prevent backflow and maintain one-way movement.

                    The left ventricular wall is thicker than the right because it must generate higher pressure to drive blood around the systemic circuit. The right ventricle pumps blood to the nearby lungs at lower pressure. The septum separates the right and left sides and prevents mixing of blood in a normal heart. Coronary arteries supply the heart muscle itself. The cardiac cycle involves atrial contraction, ventricular contraction and relaxation; electrical activity coordinates the sequence.""",
                    "Right ventricle → pulmonary artery → lungs → pulmonary veins → left atrium is the pulmonary route.",
                    "Memorise chamber order and valve positions. Pulmonary artery is deoxygenated; pulmonary veins are oxygenated."),
                JambDeepPoint("Double circulation and lymph",
                    """Pulmonary circulation carries blood from the right side of the heart to the lungs and back to the left side. Systemic circulation carries blood from the left side of the heart to body tissues and back to the right side. Mammals have double circulation because blood passes through the heart twice in one complete circuit. This keeps oxygenated and deoxygenated blood separate and allows different pressures in the pulmonary and systemic circuits.

                    Some plasma filters out of capillaries to form tissue fluid, bathing cells and allowing exchange. Much of this fluid returns to blood capillaries; excess enters lymphatic capillaries and becomes lymph. Lymph travels through vessels with valves and is returned to the bloodstream through large ducts near the subclavian veins. Lymph nodes filter lymph and contain immune cells. Lacteals in intestinal villi absorb many products of fat digestion into lymph. The lymphatic system therefore helps maintain fluid balance, supports immunity and transports absorbed lipids.""",
                    "Lymphatic vessels return excess tissue fluid to blood and transport absorbed fats from intestinal lacteals.",
                    "Double circulation = pulmonary and systemic circuits; lymph returns excess tissue fluid, supports defence and transports absorbed lipids.")
            ),
            listOf("The left ventricle has the thickest wall because it pumps blood through the systemic circuit.",
                  "Pulmonary artery carries blood from the right ventricle to the lungs; pulmonary veins return blood to the left atrium.",
                  "Lymph is derived from excess tissue fluid; it is not a separate kind of blood cell."),
            listOf(
                JambDeepCheck("bio-trans-19","Which chamber pumps blood into the aorta?",listOf("Right atrium","Right ventricle","Left atrium","Left ventricle"),3,"The left ventricle pumps oxygenated blood through the aortic valve into the aorta."),
                JambDeepCheck("bio-trans-20","Mammals have double circulation because blood",listOf("passes through the heart twice in one complete circuit","never enters the lungs","flows in only one vessel","mixes freely between the two ventricles"),0,"The pulmonary and systemic circuits mean blood passes through the heart twice per complete circulation."),
                JambDeepCheck("bio-trans-21","One important function of the lymphatic system is to",listOf("produce bile","return excess tissue fluid to the blood","pump blood into the aorta","carry air into the lungs"),1,"Lymphatic vessels collect excess tissue fluid and return it to the bloodstream.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-transport") objectives else emptyList()
}

@Composable
internal fun OsmosisInvestigationLab() {
    var solution by remember { mutableStateOf("Dilute") }
    var organism by remember { mutableStateOf("Plant cell") }
    val outcome = when (solution) {
        "Dilute" -> if (organism == "Plant cell") "Water enters by osmosis. The vacuole expands and the cell becomes turgid; the cell wall resists bursting." else "Water enters by osmosis. The cell swells and may burst because it has no cell wall."
        "Concentrated" -> if (organism == "Plant cell") "Water leaves by osmosis. The protoplast shrinks away from the cell wall: plasmolysis." else "Water leaves by osmosis. The cell shrinks and becomes crenated."
        else -> "There is no net movement of water when water potentials are equal, although water molecules continue moving in both directions."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("OSMOSIS INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose a cell type and the surrounding solution. Predict the net movement of water and inspect the outcome.")
            Text("Cell type", fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("Plant cell", "Animal cell").forEach { option ->
                    FilterChip(selected = organism == option, onClick = { organism = option }, label = { Text(option) })
                }
            }
            Text("Surrounding solution", fontWeight = FontWeight.SemiBold)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("Dilute", "Equal water potential", "Concentrated").forEach { option ->
                    OutlinedButton(onClick = { solution = option }, modifier = Modifier.fillMaxWidth()) {
                        Text(if (solution == option) "✓  $option" else option)
                    }
                }
            }
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("PREDICTED OUTCOME", fontWeight = FontWeight.Bold)
                    Text(outcome)
                }
            }
            Text("Practical extension: place equal-sized potato cylinders in different sucrose concentrations, record initial and final masses, calculate percentage change in mass, and plot percentage change against concentration.", style = MaterialTheme.typography.bodySmall)
        }
    }
    BiologyExternalLabLinks(
        "FREE OSMOSIS VIRTUAL LABS",
        listOf(
            BiologyLabResource("Donat: Osmosis in Potato Tissue", "Investigate mass change in potato cylinders placed in sugar solutions.", "https://www.donatstem.co.rw/biology/simulations"),
            BiologyLabResource("LabXchange: Free Virtual Lab Simulations", "Explore the egg lab and other interactive membrane-transport activities.", "https://about.labxchange.org/featured/virtual-lab-simulations")
        )
    )
}
