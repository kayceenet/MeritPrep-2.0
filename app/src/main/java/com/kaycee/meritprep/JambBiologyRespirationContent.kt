package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led JAMB coverage of respiration and gaseous exchange. */
internal object JambBiologyRespirationContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Distinguish breathing, gaseous exchange and cellular respiration and explain the role of ATP.",
            listOf(
                JambDeepPoint("Three processes with different meanings",
                    """Breathing or ventilation is the physical movement of air into and out of lungs or water across gills. Gaseous exchange is the diffusion of oxygen and carbon dioxide across an exchange surface between an organism and its environment, or between blood and tissues. Cellular respiration is a series of enzyme-controlled chemical reactions in cells that transfers energy from organic molecules to ATP. Breathing supplies oxygen for aerobic respiration and removes carbon dioxide, but breathing itself is not the chemical process that releases energy from glucose.

                    Respiration occurs in living cells, including plant cells, animal cells and microorganisms. Plants respire day and night; photosynthesis occurs only when suitable light is available. During the day, photosynthesis may exceed respiration, giving net oxygen release and carbon dioxide uptake. At night, photosynthesis stops but respiration continues, so plants take in oxygen and release carbon dioxide. Do not say plants respire only at night or that breathing and respiration mean the same thing.""",
                    "A germinating seed respires even though it has no lungs; a human breathes to ventilate lungs, while every body cell carries out cellular respiration.",
                    "Breathing = ventilation; gaseous exchange = diffusion across surfaces; cellular respiration = metabolic energy transfer in cells."),
                JambDeepPoint("ATP as an immediate energy carrier",
                    """Energy released during respiration is transferred to adenosine triphosphate (ATP). ATP consists of adenine, ribose and three phosphate groups. Hydrolysis of ATP to ADP and inorganic phosphate can release usable energy that is coupled to processes such as active transport, muscle contraction, synthesis of macromolecules, cell division and movement of cilia. ATP is continually regenerated from ADP and phosphate using energy from respiration or photosynthesis.

                    ATP is an immediate energy-transfer molecule, not a long-term energy store. Cells store energy mainly in molecules such as glycogen and lipids. Respiration does not create energy; it transfers energy from chemical bonds into ATP and heat. The amount of ATP available from a molecule of glucose depends on the pathway, organism and conditions, so school-level values are estimates rather than universal fixed numbers.""",
                    "ATP hydrolysis supplies energy for the sodium-potassium pump to move ions against their gradients.",
                    "ATP couples energy-releasing reactions to energy-requiring cellular work; it is recycled, not stored as the body's main energy reserve.")
            ),
            listOf("Plants respire continuously; photosynthesis requires light.",
                  "Breathing is not cellular respiration; respiration occurs inside cells.",
                  "ATP is an immediate energy carrier, not a long-term storage molecule."),
            listOf(
                JambDeepCheck("bio-resp-01","Cellular respiration is best described as",listOf("movement of air into lungs","diffusion of gases across alveoli","enzyme-controlled transfer of energy from food to ATP","movement of blood through arteries"),2,"Cellular respiration is a metabolic process that transfers energy from organic molecules to ATP."),
                JambDeepCheck("bio-resp-02","Which statement about plant respiration is correct?",listOf("It occurs only at night","It occurs continuously in living cells","It occurs only in green leaves","It stops whenever photosynthesis occurs"),1,"Plant cells respire day and night; photosynthesis may occur at the same time in light."),
                JambDeepCheck("bio-resp-03","ATP is especially important because it",listOf("stores all body energy permanently","transfers usable energy to cellular processes","forms the cell wall","carries oxygen in blood"),1,"ATP is an immediate energy carrier that couples energy release to cellular work.")
            )
        ),
        JambDeepObjective(
            "Describe aerobic respiration, its equation, stages and sites in eukaryotic cells.",
            listOf(
                JambDeepPoint("Overall equation and glycolysis",
                    """Aerobic respiration is the breakdown and oxidation of organic molecules using oxygen as the final electron acceptor in the electron transport chain. The simplified balanced equation for glucose is C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O, with energy transferred to ATP and released as heat. The equation summarises the overall process; it does not mean glucose reacts with oxygen in a single step.

                    Glycolysis occurs in the cytosol. One six-carbon glucose molecule is converted into two three-carbon pyruvate molecules. The pathway uses ATP in early steps and produces ATP and reduced NAD (NADH) later, giving a net gain of two ATP per glucose in glycolysis. Glycolysis does not require oxygen directly and can occur in both aerobic and anaerobic pathways. In the presence of oxygen and suitable mitochondrial function, pyruvate is transported into mitochondria for further oxidation.""",
                    "Glycolysis occurs in the cytosol and produces two pyruvate molecules from one glucose molecule.",
                    "Glycolysis is in the cytosol, yields a net 2 ATP per glucose and does not directly require oxygen."),
                JambDeepPoint("Link reaction, Krebs cycle and electron transport",
                    """In eukaryotic cells, pyruvate enters the mitochondrial matrix and is converted to acetyl coenzyme A in the link reaction. Carbon dioxide is released and NAD is reduced. Acetyl-CoA enters the Krebs (citric acid) cycle in the mitochondrial matrix. The cycle releases carbon dioxide, produces a small amount of ATP (or an equivalent nucleotide), and transfers high-energy electrons to NAD and FAD, forming NADH and FADH₂.

                    The electron transport chain is located on the inner mitochondrial membrane. Electrons from NADH and FADH₂ pass through carriers; the released energy pumps protons across the membrane. Protons flow back through ATP synthase, driving ATP formation by oxidative phosphorylation. Oxygen accepts electrons at the end of the chain and combines with protons to form water. Most ATP from aerobic respiration is generated through oxidative phosphorylation. Do not claim that all stages occur in mitochondria: glycolysis occurs in the cytosol.""",
                    "The inner mitochondrial membrane contains the electron transport chain and ATP synthase; oxygen is the final electron acceptor.",
                    "Link reaction and Krebs cycle occur in the matrix; electron transport and oxidative phosphorylation occur at the inner mitochondrial membrane.")
            ),
            listOf("Glycolysis occurs in the cytosol, not inside mitochondria.",
                  "Oxygen is the final electron acceptor in the electron transport chain, not a reactant used directly in glycolysis.",
                  "Most ATP in aerobic respiration is made by oxidative phosphorylation."),
            listOf(
                JambDeepCheck("bio-resp-04","Glycolysis takes place in the",listOf("mitochondrial matrix","cytosol","nucleus","inner mitochondrial membrane"),1,"Glycolysis is a cytosolic pathway that converts glucose into pyruvate."),
                JambDeepCheck("bio-resp-05","The Krebs cycle occurs mainly in the",listOf("mitochondrial matrix","cytosol","ribosome","Golgi apparatus"),0,"In eukaryotic cells, the Krebs cycle takes place in the mitochondrial matrix."),
                JambDeepCheck("bio-resp-06","Oxygen acts as the final electron acceptor in the",listOf("glycolysis pathway","electron transport chain","formation of glucose in the Calvin cycle","protein synthesis pathway"),1,"Oxygen accepts electrons at the end of the mitochondrial electron transport chain and is reduced to water.")
            )
        ),
        JambDeepObjective(
            "Compare aerobic respiration with anaerobic pathways in animals, plants and microorganisms.",
            listOf(
                JambDeepPoint("Anaerobic pathways in animals and yeast",
                    """When oxygen delivery cannot meet the demand for ATP, human skeletal muscle can convert pyruvate to lactate. This regenerates NAD so glycolysis can continue, producing a net two ATP per glucose. Lactate can be transported in blood and metabolised, including by the liver. The older term oxygen debt describes the additional oxygen required after strenuous activity to restore resting conditions, process lactate and replenish energy stores; recovery involves several processes, not simply removal of all lactate by breathing.

                    Yeast carries out alcoholic fermentation under anaerobic conditions. Pyruvate is converted to ethanol and carbon dioxide, regenerating NAD so glycolysis continues. The net ATP yield remains two ATP per glucose from glycolysis. Fermentation is important in bread making and brewing; carbon dioxide makes dough rise, while ethanol is produced in alcoholic fermentation. Some school texts use anaerobic respiration broadly for these pathways; strict microbiology distinguishes fermentation from anaerobic respiration using alternative electron acceptors.""",
                    "Yeast fermentation produces ethanol and carbon dioxide; human muscle may produce lactate when oxygen supply is insufficient.",
                    "Muscle pathway = lactate; yeast pathway = ethanol + CO₂. Both depend on glycolysis and yield much less ATP than aerobic respiration."),
                JambDeepPoint("Comparison and biological significance",
                    """Aerobic respiration uses oxygen as the final electron acceptor and usually oxidises glucose more completely, yielding far more ATP than anaerobic pathways. Anaerobic pathways allow ATP production when oxygen is absent or limited, but only the small ATP yield from glycolysis is obtained per glucose. The products differ by organism and pathway. In some microorganisms, anaerobic respiration uses nitrate, sulfate or another acceptor in an electron transport chain; this is different from fermentation.

                    Anaerobic metabolism is useful during short bursts of intense muscular activity and in microorganisms living in oxygen-poor habitats. It is less energy-efficient per glucose and cannot sustain the same long-term ATP demand as aerobic respiration in many animal tissues. Do not write that anaerobic respiration produces no energy, and do not state that all anaerobic processes produce lactic acid: yeast produces ethanol and carbon dioxide.""",
                    "A sprinting muscle may temporarily rely more on anaerobic glycolysis; yeast uses alcoholic fermentation when oxygen is unavailable.",
                    "Aerobic = oxygen-dependent electron transport and high ATP yield; anaerobic pathways = no oxygen as final acceptor and low ATP yield.")
            ),
            listOf("Anaerobic pathways still produce ATP; the yield is low, not zero.",
                  "Yeast produces ethanol and carbon dioxide; human muscle produces lactate.",
                  "Fermentation and anaerobic respiration are distinguished in strict microbiology."),
            listOf(
                JambDeepCheck("bio-resp-07","The main end products of alcoholic fermentation in yeast are",listOf("lactate and oxygen","ethanol and carbon dioxide","water and oxygen","glucose and starch"),1,"Yeast converts pyruvate to ethanol and carbon dioxide during alcoholic fermentation."),
                JambDeepCheck("bio-resp-08","Anaerobic glycolysis in human skeletal muscle produces",listOf("lactate","ethanol","oxygen","urea"),0,"When oxygen delivery is insufficient, pyruvate can be reduced to lactate to regenerate NAD."),
                JambDeepCheck("bio-resp-09","Compared with aerobic respiration, anaerobic pathways generally",listOf("yield more ATP per glucose","yield less ATP per glucose","require more oxygen","occur only in plants"),1,"Anaerobic pathways obtain only the small ATP yield from glycolysis per glucose.")
            )
        ),
        JambDeepObjective(
            "Relate respiratory structures in organisms to their habitats and modes of gaseous exchange.",
            listOf(
                JambDeepPoint("Exchange surfaces in aquatic and terrestrial organisms",
                    """Small unicellular organisms exchange gases directly across their cell surface by diffusion because their surface-area-to-volume ratio is large and diffusion distances are short. Fish use gills with many filaments and lamellae, providing a large moist surface and rich blood supply. Water flows over the gills while blood flows through lamellae in the opposite direction in many bony fish, maintaining a concentration gradient by countercurrent exchange. This allows efficient oxygen uptake from water, which contains less oxygen per unit volume than air.

                    Insects use a tracheal system: air enters through spiracles, passes along branching tracheae and reaches tissues through fine tracheoles. Oxygen can diffuse directly to cells, so insect blood (haemolymph) usually plays little role in oxygen transport. Earthworms exchange gases through moist skin supplied with blood capillaries. Amphibians may exchange gases through lungs and moist skin. These structures are adapted to the organism's size, habitat and metabolic needs.""",
                    "Fish gill lamellae provide a large surface area; insect tracheoles deliver air close to body cells.",
                    "Fish = gills; insects = tracheal system; earthworm = moist skin; unicellular organisms = cell surface."),
                JambDeepPoint("Plant gaseous exchange",
                    """Plants exchange gases mainly by diffusion through stomata in leaves and young green stems, and through lenticels in woody stems. Air spaces in spongy mesophyll connect stomata to photosynthetic cells. During photosynthesis, carbon dioxide diffuses inward and oxygen diffuses outward when the concentration gradients favour those directions. During respiration, oxygen is taken up and carbon dioxide released, but net gas exchange depends on the balance between photosynthesis and respiration.

                    Roots exchange gases through air spaces in soil and by diffusion across root surfaces. Waterlogged soil contains less air, limiting oxygen supply to roots and potentially reducing aerobic respiration. Some wetland plants have air spaces (aerenchyma) that help move gases internally. Do not state that plants use only stomata for all gaseous exchange: roots and woody stems also exchange gases through appropriate surfaces.""",
                    "A waterlogged root may receive too little oxygen for normal aerobic respiration; aerenchyma can help some wetland plants move gases.",
                    "Stomata are important in leaves; lenticels occur in woody stems; roots exchange gases with soil air.")
            ),
            listOf("Insects use tracheae and tracheoles; their haemolymph usually does not carry most oxygen.",
                  "Fish gills exchange dissolved oxygen in water, not atmospheric air directly.",
                  "Plants respire continuously; net daytime gas exchange may be dominated by photosynthesis."),
            listOf(
                JambDeepCheck("bio-resp-10","The respiratory surface of an earthworm is its",listOf("dry cuticle","moist skin","spiracle","gill lamella"),1,"Earthworms exchange gases through moist skin with an underlying capillary network."),
                JambDeepCheck("bio-resp-11","In insects, oxygen reaches tissues mainly through",listOf("red blood cells","tracheae and tracheoles","the pulmonary artery","xylem vessels"),1,"The tracheal system delivers air through branching tubes close to cells."),
                JambDeepCheck("bio-resp-12","Countercurrent flow in fish gills helps to",listOf("remove all water from the gills","maintain a diffusion gradient for oxygen uptake","stop blood circulation","prevent water from touching lamellae"),1,"Opposite flow directions help maintain a gradient along the length of the lamellae.")
            )
        ),
        JambDeepObjective(
            "Describe the mammalian respiratory system and explain how alveoli are adapted for gaseous exchange.",
            listOf(
                JambDeepPoint("Air pathway and conducting structures",
                    """Air normally enters through the nose or mouth, passes through the pharynx and larynx, then the trachea, bronchi and bronchioles before reaching alveoli. The nasal cavity filters, warms and moistens air. The trachea and bronchi are supported by C-shaped cartilage rings or plates that help keep airways open. Their lining contains mucus-secreting cells and cilia; mucus traps dust and microbes, while cilia move it towards the throat for removal. Bronchioles have smooth muscle that changes airway diameter.

                    The right lung usually has three lobes and the left lung two, leaving space for the heart. The pleural membranes and a thin layer of pleural fluid reduce friction during breathing. The diaphragm and intercostal muscles change thoracic volume to move air. The alveoli are the main exchange surfaces; conducting airways move and condition air but do not provide most gas exchange.""",
                    "Mucus traps particles in the airways and cilia move the mucus towards the pharynx.",
                    "Air pathway: nose/mouth → pharynx → larynx → trachea → bronchi → bronchioles → alveoli."),
                JambDeepPoint("Alveolar adaptations",
                    """Alveoli occur in enormous numbers, creating a very large total surface area. Their walls are one squamous epithelial cell thick, and capillary walls are also thin, producing a short diffusion distance. The lining is moist so gases dissolve before diffusing. A dense capillary network maintains blood flow and concentration gradients. Elastic fibres allow alveoli to expand during inhalation and recoil during exhalation. Surfactant reduces surface tension and helps prevent alveolar collapse, especially at low lung volumes.

                    Oxygen diffuses from alveolar air into blood because its partial pressure is higher in alveoli than in incoming pulmonary capillary blood. Carbon dioxide diffuses from blood into alveoli because its partial pressure is higher in blood. Ventilation continually refreshes alveolar air, while blood flow removes oxygen and brings carbon dioxide. Effective exchange therefore depends on ventilation-perfusion matching as well as surface structure.""",
                    "Thin moist alveolar walls, large surface area and dense capillaries support rapid diffusion of oxygen and carbon dioxide.",
                    "Large area + short diffusion distance + moisture + rich blood supply + ventilation maintain efficient gas exchange.")
            ),
            listOf("Most gaseous exchange occurs in alveoli, not in the trachea or bronchi.",
                  "Cartilage supports the trachea and bronchi; bronchioles contain smooth muscle.",
                  "Surfactant reduces surface tension and helps prevent alveolar collapse."),
            listOf(
                JambDeepCheck("bio-resp-13","The main site of gaseous exchange in mammalian lungs is the",listOf("trachea","larynx","alveoli","nasal cavity"),2,"Alveoli provide the thin, moist and highly vascular surface for gas exchange."),
                JambDeepCheck("bio-resp-14","The main function of cartilage in the trachea is to",listOf("produce oxygen","keep the airway open","digest mucus","pump blood"),1,"Cartilage supports the tracheal wall and helps prevent collapse."),
                JambDeepCheck("bio-resp-15","Pulmonary surfactant helps to",listOf("increase surface tension","reduce surface tension and prevent alveolar collapse","carry oxygen in plasma","close the bronchi permanently"),1,"Surfactant lowers surface tension in alveoli, making them easier to inflate and less likely to collapse.")
            )
        ),
        JambDeepObjective(
            "Explain the mechanics of inhalation and exhalation and relate ventilation to pressure changes.",
            listOf(
                JambDeepPoint("Inhalation",
                    """During quiet inhalation, the diaphragm contracts and flattens, moving downwards. The external intercostal muscles contract, lifting the ribs and sternum upwards and outwards. These actions increase thoracic cavity volume. Intrapulmonary pressure falls slightly below atmospheric pressure, so air flows into the lungs down the pressure gradient. The lungs expand because they are coupled to the chest wall through the pleural membranes and fluid.

                    In deep or forced inhalation, accessory muscles can assist. The movement of air is caused by pressure differences created by changes in thoracic volume, not by the lungs actively sucking air. Boyle's law describes the inverse relationship between pressure and volume for a fixed amount of gas at constant temperature: increasing volume lowers pressure.""",
                    "When the diaphragm contracts and flattens, thoracic volume increases and air enters the lungs.",
                    "Inhalation: diaphragm contracts/flattens; ribs move up and out; thoracic volume rises; pressure falls; air enters."),
                JambDeepPoint("Exhalation and control of breathing",
                    """During quiet exhalation, the diaphragm and external intercostal muscles relax. The diaphragm becomes dome-shaped again, ribs move downwards and inwards, and elastic recoil of lungs and chest wall reduces thoracic volume. Intrapulmonary pressure rises above atmospheric pressure, so air flows out. Quiet exhalation is mainly passive. During forced exhalation, internal intercostal and abdominal muscles contract to reduce thoracic volume more strongly.

                    Breathing rhythm is regulated by respiratory centres in the brainstem, especially the medulla and pons. Chemoreceptors respond strongly to changes associated with carbon dioxide and hydrogen ion concentration in blood and cerebrospinal fluid; oxygen levels also influence ventilation, especially when low. During exercise, increased carbon dioxide production and other signals raise ventilation. Do not say that oxygen concentration is the only factor controlling breathing rate.""",
                    "During forced exhalation, internal intercostal and abdominal muscles contract to push air out more rapidly.",
                    "Exhalation: diaphragm relaxes; thoracic volume falls; pressure rises; air leaves. CO₂/H⁺ changes are major drivers of ventilation control.")
            ),
            listOf("Quiet exhalation is usually passive due to muscle relaxation and elastic recoil.",
                  "Air flows from higher pressure to lower pressure.",
                  "Carbon dioxide and hydrogen ion concentration are major chemical drivers of breathing regulation."),
            listOf(
                JambDeepCheck("bio-resp-16","During inhalation, the diaphragm",listOf("relaxes and becomes dome-shaped","contracts and flattens","moves into the lungs","closes the trachea"),1,"Contraction flattens the diaphragm and increases thoracic volume."),
                JambDeepCheck("bio-resp-17","Air enters the lungs during inhalation because intrapulmonary pressure",listOf("rises above atmospheric pressure","falls below atmospheric pressure","becomes zero","equals blood pressure"),1,"Increasing thoracic volume lowers intrapulmonary pressure below atmospheric pressure."),
                JambDeepCheck("bio-resp-18","Quiet exhalation occurs mainly because",listOf("the diaphragm contracts more strongly","the diaphragm relaxes and elastic recoil reduces lung volume","the ribs move up and out","alveoli actively pump air out"),1,"Relaxation and elastic recoil reduce thoracic and lung volume, raising intrapulmonary pressure.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-respiration") objectives else emptyList()
}

@Composable
internal fun VentilationMechanicsLab() {
    var phase by remember { mutableStateOf("Inhalation") }
    val explanation = if (phase == "Inhalation") {
        "Diaphragm contracts and flattens; external intercostal muscles lift ribs up and out. Thoracic volume increases, intrapulmonary pressure falls below atmospheric pressure, and air enters."
    } else {
        "Diaphragm relaxes and becomes dome-shaped; ribs move down and in. Thoracic volume decreases, intrapulmonary pressure rises above atmospheric pressure, and air leaves."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("VENTILATION MECHANICS", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Switch between the two phases and trace the sequence from muscle action to airflow.")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Inhalation", "Exhalation").forEach { option ->
                    FilterChip(selected = phase == option, onClick = { phase = option }, label = { Text(option) })
                }
            }
            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(phase.uppercase(), fontWeight = FontWeight.Bold)
                    Text(explanation)
                }
            }
            Text("Exam chain: muscle action → thoracic volume change → pressure change → direction of airflow.", style = MaterialTheme.typography.bodySmall)
        }
    }
    BiologyExternalLabLinks(
        "FREE RESPIRATION SIMULATIONS",
        listOf(
            BiologyLabResource("SciSim: Cellular Respiration", "Interactive simulation exploring energy release and the role of oxygen in muscle cells.", "https://scisim.org/"),
            BiologyLabResource("Harvard LabXchange: Photosynthesis and Cellular Respiration", "A free virtual lab comparing plant photosynthesis and respiration using a BTB indicator.", "https://about.labxchange.org/sim-landing-page/photosynthesis-and-cellular-respiration")
        )
    )
}
