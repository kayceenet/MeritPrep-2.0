package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

internal data class JambDeepCheck(val id:String,val question:String,val options:List<String>,val answer:Int,val explanation:String)
internal data class JambDeepPoint(val heading:String,val teaching:String,val example:String,val examFocus:String)
internal data class JambDeepObjective(val objective:String,val points:List<JambDeepPoint>,val traps:List<String>,val checks:List<JambDeepCheck>)

/** Objective-led JAMB content. Every stated candidate objective is taught and assessed separately. */
internal object JambBiologyDeepContent {
 private val livingOrganisms = listOf(
  JambDeepObjective("Differentiate between the characteristics of living and non-living things.",
   listOf(
    JambDeepPoint("Characteristics of life and MRS GREN",
     """Living organisms carry out coordinated life processes. MRS GREN is a useful memory aid: movement, respiration, sensitivity, growth, reproduction, excretion and nutrition. It is not a rigid test requiring every individual to display all seven processes visibly at every moment. A dormant seed is alive even when activity is very low; a sterile animal is alive even though it may not reproduce. Life is recognised from cellular organisation and the integrated capacity for metabolism, regulation, response and continuity.

Movement includes movement of the whole organism or parts of it. Animals may locomote, while plants show directional growth movements such as shoots bending towards light and roots responding to gravity. Sensitivity is detection of and response to stimuli: light, touch, temperature, chemicals, water availability or gravity. Growth is a permanent increase in size, dry mass or cell number due to synthesis of new cellular material, not simply temporary swelling.

Nutrition supplies materials for energy, growth and repair. Green plants make organic food through photosynthesis; animals obtain nutrients by feeding. Excretion removes metabolic waste and substances in excess. Reproduction produces new individuals and allows a species to continue, but it is not a necessary activity of every individual organism.""",
     "A shoot bending towards a window demonstrates sensitivity (phototropism) and growth. A germinating seed demonstrates renewed metabolism and growth even though it appeared inactive.",
     "Questions may describe a dormant seed, a flame or a moving machine. Consider cellular organisation and biological metabolism, not movement alone."),
    JambDeepPoint("Respiration is not breathing",
     """Respiration is the enzyme-controlled release and transfer of energy from organic molecules to ATP. Aerobic respiration uses oxygen as the final electron acceptor and usually releases much more usable energy from glucose than anaerobic pathways. Glycolysis occurs in the cytoplasm; later aerobic stages occur in mitochondria in eukaryotic cells. Anaerobic pathways can produce lactate in human muscle or ethanol and carbon dioxide in yeast.

Breathing (ventilation) is the physical movement of air into and out of respiratory surfaces in many animals. It supports gas exchange but is not the chemical process of cellular respiration. A bacterium, Amoeba, plant root or germinating seed respires without lungs. Living plant cells respire during the day and at night; photosynthesis, unlike respiration, requires light.""",
     "A fish moves water over its gills for gas exchange, while its cells use oxygen in aerobic respiration. A germinating seed respires although it has no lungs.",
     "If an option defines respiration as inhaling and exhaling, it is describing ventilation, not cellular respiration."),
    JambDeepPoint("Excretion, egestion and secretion",
     """Excretion removes metabolic products such as carbon dioxide, urea and excess salts or water. Egestion is the removal of undigested food from the alimentary canal as faeces. Secretion is the release of a useful substance such as an enzyme, hormone, mucus or saliva by a cell or gland. The distinction depends on the origin and role of the material.

In mammals, lungs remove carbon dioxide and water vapour, kidneys remove urea and regulate water and ions, and sweat glands remove small quantities of water, salts and urea. Plants may diffuse oxygen or carbon dioxide out, store wastes in leaves, bark or resins, and remove some wastes when parts are shed.""",
     "Kidney removal of urea is excretion; elimination of undigested food is egestion; release of amylase by a salivary gland is secretion.",
     "Ask whether the material is a metabolic product or undigested food when distinguishing excretion from egestion.")
   ),
   listOf("Respiration is not the same as breathing.","Egestion is not excretion.","A living individual need not reproduce.","Plants respire day and night."),
   listOf(
    JambDeepCheck("bio-life-01","A dry seed appears inactive. Which observation best demonstrates that it is living?",listOf("It has a hard coat","It can germinate and resume metabolism under suitable conditions","It has a fixed shape","It absorbs water only in soil"),1,"Germination demonstrates living tissues capable of renewed metabolism, growth and development."),
    JambDeepCheck("bio-life-02","Which process is correctly matched with its meaning?",listOf("Egestion — removal of urea","Secretion — removal of carbon dioxide","Respiration — cellular release of energy from food","Excretion — elimination of undigested food"),2,"Respiration is cellular energy metabolism. Urea removal is excretion; undigested food removal is egestion."),
    JambDeepCheck("bio-life-03","A shoot bends towards a window. This is primarily an example of",listOf("locomotion","sensitivity to light","excretion","anaerobic respiration"),1,"The shoot detects directional light and grows in response. This is sensitivity (phototropism).")
   )
  ),
  JambDeepObjective("Identify the structures of plant and animal cells.",
   listOf(
    JambDeepPoint("Cell boundary and internal organisation",
     """A cell is the smallest structural and functional unit capable of carrying out life processes. Typical plant and animal cells are eukaryotic: they have a membrane-bound nucleus and membrane-bound organelles. The plasma membrane encloses the cytoplasm and controls exchange with the surroundings. The cytoplasm contains cytosol and suspended structures; many metabolic reactions occur there.

A plant cell has a cellulose cell wall outside the plasma membrane. The wall provides support and resists excessive expansion but is not the selectively permeable boundary. Many mature plant cells have a large permanent central vacuole containing cell sap, bounded by the tonoplast. Animal cells lack a cellulose wall and commonly have a more flexible outline and smaller temporary vacuoles.""",
     "In a typical leaf cell, identify the wall as the outer rigid boundary and the plasma membrane immediately inside it. In a cheek epithelial cell, the plasma membrane is the outer boundary.",
     "Distinguish the cell wall from the plasma membrane: support versus selective control of exchange."),
    JambDeepPoint("Plant-cell structures",
     """Chloroplasts contain chlorophyll in thylakoid membranes; stacks of thylakoids are grana and the surrounding fluid is stroma. Chloroplasts carry out photosynthesis in suitable green tissues. Not every plant cell contains chloroplasts: root hair cells and many storage cells normally lack them. Mitochondria, ribosomes, endoplasmic reticulum, Golgi apparatus and a nucleus are also found in typical living plant cells, although specialised cells may lose some structures.

The large vacuole stores dissolved substances and helps maintain turgor pressure. The cellulose wall supports the cell and helps prevent it from bursting when water enters. Plant cells may contain other plastids: leucoplasts are associated with storage, while chromoplasts contain pigments that colour flowers and fruits.""",
     "Onion bulb epidermal cells usually show a wall, membrane, cytoplasm, nucleus and vacuole, but normally lack chloroplasts. Leaf palisade cells contain many chloroplasts.",
     "Do not identify a cell as plant solely because chloroplasts are visible; many plant cells do not contain them."),
    JambDeepPoint("Animal-cell structures and specialised examples",
     """A typical animal cell has a plasma membrane, cytoplasm, nucleus, mitochondria, ribosomes, endoplasmic reticulum and Golgi apparatus. Lysosomes contain hydrolytic enzymes that digest material taken into the cell and worn-out cell components. Many animal cells have a centrosome with centrioles that helps organise microtubules during cell division. Cilia and flagella contain microtubules and enable movement of cells or materials over cell surfaces.

Specialised cells differ from a generalised diagram. A mature mammalian red blood cell has no nucleus or mitochondria and is packed with haemoglobin. A sperm cell has a haploid nucleus, an acrosome, a mitochondria-rich midpiece and a flagellum. A ciliated epithelial cell has many cilia that move mucus or fluid.""",
     "A sperm cell's midpiece contains many mitochondria to supply ATP for flagellar movement. Its acrosome contains enzymes that assist penetration of egg coverings.",
     "Use the cell type shown in the question. Do not assume every animal cell has the same organelles.")
   ),
   listOf("The cell wall is not the selectively permeable membrane.","Not every plant cell has chloroplasts.","Mature mammalian red blood cells lack a nucleus.","Animal cells do not have cellulose cell walls."),
   listOf(
    JambDeepCheck("bio-cell-01","Which structure controls movement of substances into and out of both plant and animal cells?",listOf("Cellulose wall","Plasma membrane","Nucleolus","Central vacuole"),1,"The plasma membrane is selectively permeable and regulates exchange in both cell types."),
    JambDeepCheck("bio-cell-02","An onion bulb epidermal cell lacks visible chloroplasts because",listOf("it is prokaryotic","all onion cells are dead","it is not normally exposed to light for photosynthesis","it has no cytoplasm"),2,"Bulb epidermal cells are storage tissues and are not normally exposed to light. Plant cells do not all contain chloroplasts."),
    JambDeepCheck("bio-cell-03","Which feature is characteristic of a typical plant cell but absent from a typical animal cell?",listOf("Plasma membrane","Cytoplasm","Mitochondrion","Cellulose cell wall"),3,"Both have plasma membranes, cytoplasm and mitochondria; the cellulose wall is characteristic of plant cells.")
   )
  ),
  JambDeepObjective("Analyse the functions of the components of plant and animal cells.",
   listOf(
    JambDeepPoint("Nucleus and nucleolus",
     """The nucleus is enclosed by a double nuclear envelope with pores that regulate exchange. It contains most of the cell's DNA, organised with proteins into chromatin and chromosomes. Genes are DNA sequences that influence the production of functional RNA and proteins. Regulation of gene expression allows cells to control their activities, specialise and respond to conditions. During cell division, chromatin condenses into visible chromosomes.

The nucleolus is a dense region within the nucleus where ribosomal RNA is produced and ribosomal subunits begin assembly. It is not a membrane-bound organelle. Prokaryotic cells have DNA in a nucleoid region but no membrane-bound nucleus.""",
     "A cell producing large amounts of protein may have a prominent nucleolus because it needs many ribosomes.",
     "The nucleus regulates cell activities but does not directly assemble proteins; ribosomes do that."),
    JambDeepPoint("Mitochondria and ATP",
     """Mitochondria have an outer membrane and a highly folded inner membrane. The folds, called cristae, provide surface area for electron transport and ATP synthesis. The matrix contains enzymes for the link reaction and Krebs cycle. Mitochondria contain their own DNA and ribosomes. In eukaryotes, glycolysis occurs in the cytoplasm, while the link reaction, Krebs cycle and oxidative phosphorylation occur in mitochondria.

ATP is a transferable energy currency. Cells use ATP to drive active transport, muscle contraction, biosynthesis, movement of cilia and other energy-requiring processes. Cells with high energy demand often contain many mitochondria.""",
     "Root hair cells may use ATP for active uptake of mineral ions from dilute soil solution. Sperm cells have many mitochondria in the midpiece to support movement.",
     "Do not state that all respiration occurs in mitochondria: glycolysis occurs in the cytoplasm."),
    JambDeepPoint("Ribosomes, endoplasmic reticulum and Golgi apparatus",
     """Ribosomes are the sites of protein synthesis. Free ribosomes commonly make proteins used within the cytosol; ribosomes attached to rough endoplasmic reticulum make many proteins destined for secretion, membranes or the endomembrane system. Rough ER helps newly made proteins fold and move. Smooth ER lacks attached ribosomes and is involved in lipid synthesis, detoxification and calcium storage in specialised cells.

The Golgi apparatus modifies, sorts and packages proteins and lipids into vesicles. Secretory vesicles may fuse with the plasma membrane and release contents by exocytosis. Lysosomes contain digestive enzymes and are involved in intracellular digestion and recycling.""",
     "A pancreatic cell that secretes digestive enzymes has abundant rough ER and Golgi apparatus because it makes, modifies and packages proteins for export.",
     "Ribosomes synthesise polypeptides; Golgi apparatus modifies, sorts and packages. Do not reverse these functions."),
    JambDeepPoint("Membrane, vacuole, cytoskeleton and plastids",
     """The plasma membrane is a phospholipid bilayer with embedded proteins. It contains channels, carriers, receptors and enzymes and controls exchange, communication and cell recognition. The cytoskeleton supports cell shape, positions organelles and enables intracellular movement. Microtubules form part of the spindle during cell division and the internal framework of cilia and flagella.

The plant vacuole stores cell sap and contributes to turgor. Chloroplasts capture light energy for photosynthesis; other plastids may store starch or contain pigments. The cell wall provides mechanical support. Each structure contributes to the function of the whole cell rather than acting independently.""",
     "When a plant loses water, the vacuole shrinks and turgor pressure falls; the tissue becomes flaccid and may wilt.",
     "Explain the mechanism linking an organelle to the cell's activity, rather than memorising a one-word function.")
   ),
   listOf("Glycolysis occurs in cytoplasm, not mitochondria.","Ribosomes synthesise proteins; Golgi modifies and packages.","Nucleolus is not membrane-bound.","Mitochondria transfer energy to ATP; they do not manufacture glucose."),
   listOf(
    JambDeepCheck("bio-organelle-01","A cell secreting large quantities of protein is expected to have abundant",listOf("rough ER and Golgi apparatus","cell wall and chloroplasts","lipid droplets only","contractile vacuoles only"),0,"Ribosomes on rough ER synthesise secreted proteins; the Golgi apparatus modifies and packages them."),
    JambDeepCheck("bio-organelle-02","Which statement about respiration in a eukaryotic cell is correct?",listOf("All stages occur in the nucleus","Glycolysis occurs in cytoplasm and later aerobic stages occur in mitochondria","Mitochondria manufacture glucose","Ribosomes are the main sites of ATP synthesis"),1,"Glycolysis occurs in cytoplasm. The link reaction, Krebs cycle and oxidative phosphorylation occur in mitochondria."),
    JambDeepCheck("bio-organelle-03","The nucleolus is primarily involved in",listOf("photosynthesis","lipid digestion","ribosomal subunit production","glycolysis"),2,"The nucleolus produces ribosomal RNA and assembles ribosomal subunits.")
   )
  ),
  JambDeepObjective("Compare and contrast the structure of plant and animal cells.",
   listOf(
    JambDeepPoint("Similarities and differences",
     """Both typical plant and animal cells are eukaryotic. They possess a plasma membrane, cytoplasm, nucleus, mitochondria, ribosomes, endoplasmic reticulum and Golgi apparatus. Both carry out respiration, protein synthesis, membrane transport and cell division. Typical plant cells additionally have a cellulose cell wall and a large permanent central vacuole; chloroplasts occur in photosynthetic tissues. Typical animal cells lack a cellulose wall and chloroplasts, and often have smaller temporary vacuoles and lysosomes.

These are typical patterns, not absolute rules for every specialised cell. Root cells commonly lack chloroplasts. Mature mammalian red blood cells lack a nucleus and mitochondria. Some plant conducting cells lose their nucleus at maturity. A correct comparison uses the cell type and context given.""",
     "A palisade cell and a cheek epithelial cell both have a plasma membrane, cytoplasm and nucleus. The palisade cell has a cellulose wall and many chloroplasts; the cheek cell has neither.",
     "For a compare-and-contrast question, state shared structures and then differences. Use 'typical' where exceptions exist."),
    JambDeepPoint("How structural differences support function",
     """The cellulose wall supports plant cells and resists the internal pressure produced when water enters by osmosis. The central vacuole stores cell sap and helps maintain turgor. Chloroplasts enable photosynthesis in exposed green tissues. Animal cells often require flexible shapes for movement, engulfment and specialised tissue functions; the absence of a rigid cellulose wall allows this flexibility.

When plant cells lose water, the vacuole decreases in volume and turgor pressure falls. The cell wall remains but the cell contents pull away from it during severe plasmolysis. The wall does not actively pump water; water movement is governed by water potential and the selectively permeable plasma membrane and tonoplast.""",
     "A wilted leaf is less firm because many cells have lost turgor, not because their cellulose walls have disappeared.",
     "Connect a structural difference to its physiological consequence; avoid unsupported statements such as 'plant cells are stronger'.")
   ),
   listOf("Not every plant cell contains chloroplasts.","Animal cells have mitochondria.","Cell wall is not selectively permeable.","Specialised cells may lack structures found in textbook diagrams."),
   listOf(
    JambDeepCheck("bio-compare-01","Which pair is present in both typical plant and animal cells?",listOf("Cellulose wall and chloroplast","Plasma membrane and mitochondrion","Permanent vacuole and cellulose wall","Chloroplast and centriole in every cell"),1,"Both cell types possess plasma membranes and mitochondria."),
    JambDeepCheck("bio-compare-02","A plant wilts after water loss mainly because of",listOf("increase in chloroplast number","loss of vacuolar water and turgor pressure","formation of cellulose wall","increase in ribosome activity"),1,"Water loss reduces vacuolar volume and turgor pressure, making tissues flaccid.")
   )
  ),
  JambDeepObjective("Trace the levels of organization among organisms in their logical sequence.",
   listOf(
    JambDeepPoint("Cell to organism",
     """In multicellular organisms, organisation proceeds from cell to tissue, organ, organ system and organism. A cell is the basic structural and functional unit. A tissue is a group of similar or coordinated cells performing a function. An organ contains different tissues working together. An organ system consists of organs coordinating a major body function. The organism is the complete individual.

The sequence reflects increasing integration: specialised cells cooperate in tissues; several tissues combine to form organs; organs coordinate in systems; systems maintain the whole organism. Not every organism has all levels of complexity.""",
     "Human example: cardiac muscle cell → cardiac muscle tissue → heart → circulatory system → human. Plant example: xylem element → xylem tissue → root or stem → root/shoot system → whole plant.",
     "Be able to arrange examples and identify whether a named structure is a tissue, organ or system."),
    JambDeepPoint("Unicellular organisms and syllabus examples",
     """A unicellular organism consists of one cell that performs all essential life processes. Euglena is a unicellular eukaryote with a flagellum, chloroplasts and an eyespot. Paramecium uses cilia for movement and food collection and contractile vacuoles for osmoregulation. Chlamydomonas is a unicellular green alga with two flagella, a chloroplast and an eyespot. These organisms do not form tissues, organs and organ systems in the same way as multicellular organisms.

Do not confuse a unicellular organism with a cell from a multicellular organism. It is a complete organism in its own right, carrying out nutrition, respiration, excretion, response and reproduction within one cell.""",
     "Paramecium moves using cilia, collects food through an oral region and regulates water using contractile vacuoles, all within one cell.",
     "A unicellular organism is a complete organism, not merely a cell from a larger organism."),
    JambDeepPoint("Tissues and organs in plants and animals",
     """Epithelial tissue covers surfaces and lines cavities; muscle tissue contracts; nervous tissue conducts impulses; connective tissues support, bind and transport. In plants, epidermal tissue protects, mesophyll carries out photosynthesis, and xylem and phloem transport materials. An organ contains several tissues working together. A leaf contains epidermal, mesophyll and vascular tissues; a kidney contains several tissues arranged to filter blood and regulate water and salts.

The onion bulb is a modified underground shoot and is an organ composed of tissues, not a single cell. The digestive, reproductive and excretory systems each consist of several organs coordinating a body function. Hydra is a simple multicellular animal showing tissue-level organisation.""",
     "A leaf is an organ because its epidermis, mesophyll and vascular tissues cooperate in protection, photosynthesis and transport.",
     "Do not call an onion bulb a cell. Do not put organ before tissue in the hierarchy.")
   ),
   listOf("Correct order is cell → tissue → organ → system → organism.","A unicellular organism is a complete organism.","An onion bulb is an organ, not a cell.","Simple multicellular animals may have less complex organisation."),
   listOf(
    JambDeepCheck("bio-levels-01","Which sequence shows increasing organisation in a human?",listOf("Tissue → cell → organ → organism → system","Cell → tissue → organ → system → organism","Cell → organ → tissue → system → organism","Organ → tissue → cell → system → organism"),1,"The logical sequence is cell, tissue, organ, organ system and organism."),
    JambDeepCheck("bio-levels-02","Paramecium is unicellular because",listOf("it has no organised structures","one cell performs all essential life processes","it contains many organs","it cannot respond to stimuli"),1,"Paramecium is one cell, but it contains specialised structures and carries out movement, feeding, osmoregulation and other life processes.")
   )
  )
 )
 fun forTopic(topicId:String):List<JambDeepObjective> = if(topicId=="bio-cell") livingOrganisms else emptyList()
}

@Composable
internal fun JambBiologyDeepLessonPanel(topicId:String) {
 val objectives=JambBiologyDeepContent.forTopic(topicId)
 if(objectives.isEmpty()) return
 Column(verticalArrangement=Arrangement.spacedBy(12.dp)) {
  Card(Modifier.fillMaxWidth()) {
   Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(6.dp)) {
    Text("JAMB SYLLABUS-DEPTH TEACHING",color=MaterialTheme.colorScheme.primary,style=MaterialTheme.typography.labelLarge,fontWeight=FontWeight.Bold)
    Text("Each examinable objective is taught and checked independently.",style=MaterialTheme.typography.bodyMedium)
    Text("Coverage: \${objectives.size} objectives • \${objectives.sumOf { it.checks.size }} concept checks",style=MaterialTheme.typography.labelMedium,color=MaterialTheme.colorScheme.onSurfaceVariant)
   }
  }
  objectives.forEachIndexed { index,obj ->
   Card(Modifier.fillMaxWidth()) {
    Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)) {
     Text("SYLLABUS OBJECTIVE \${index+1}",style=MaterialTheme.typography.labelSmall,color=MaterialTheme.colorScheme.primary,fontWeight=FontWeight.Bold)
     Text(obj.objective,style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.Bold)
     obj.points.forEach { point ->
      Column(verticalArrangement=Arrangement.spacedBy(6.dp)) {
       Text(point.heading,style=MaterialTheme.typography.titleSmall,fontWeight=FontWeight.SemiBold)
       Text(point.teaching,style=MaterialTheme.typography.bodyMedium)
       Surface(color=MaterialTheme.colorScheme.secondaryContainer,shape=MaterialTheme.shapes.medium) {
        Column(Modifier.fillMaxWidth().padding(12.dp),verticalArrangement=Arrangement.spacedBy(4.dp)) {
         Text("APPLIED EXAMPLE",style=MaterialTheme.typography.labelSmall,fontWeight=FontWeight.Bold)
         Text(point.example,style=MaterialTheme.typography.bodyMedium)
        }
       }
       Text("Examination focus: \${point.examFocus}",style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.primary)
      }
     }
     Surface(color=MaterialTheme.colorScheme.errorContainer.copy(alpha=0.4f),shape=MaterialTheme.shapes.medium) {
      Column(Modifier.fillMaxWidth().padding(12.dp),verticalArrangement=Arrangement.spacedBy(5.dp)) {
       Text("COMMON EXAMINATION TRAPS",style=MaterialTheme.typography.labelSmall,fontWeight=FontWeight.Bold)
       obj.traps.forEach { Text("• \$it",style=MaterialTheme.typography.bodySmall) }
      }
     }
     obj.checks.forEachIndexed { i,check -> ObjectiveKnowledgeCheck(check,i+1) }
    }
   }
  }
 }
}

@Composable
private fun ObjectiveKnowledgeCheck(check:JambDeepCheck,number:Int) {
 var selected by remember(check.id) { mutableStateOf<Int?>(null) }
 Column(verticalArrangement=Arrangement.spacedBy(5.dp)) {
  Text("CONCEPT CHECK $number",style=MaterialTheme.typography.labelSmall,color=MaterialTheme.colorScheme.tertiary,fontWeight=FontWeight.Bold)
  Text(check.question,style=MaterialTheme.typography.bodyLarge,fontWeight=FontWeight.SemiBold)
  check.options.forEachIndexed { index,option ->
   Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(5.dp)) {
    RadioButton(selected=selected==index,onClick={selected=index})
    Text(option,Modifier.weight(1f).padding(top=11.dp),style=MaterialTheme.typography.bodyMedium)
   }
  }
  Button(onClick={if(selected==null) selected = -1},enabled=selected==null,modifier=Modifier.fillMaxWidth()) { Text("Check understanding") }
  if(selected!=null) {
   val correct=selected==check.answer
   Surface(color=if(correct) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer,shape=MaterialTheme.shapes.medium) {
    Column(Modifier.fillMaxWidth().padding(12.dp),verticalArrangement=Arrangement.spacedBy(5.dp)) {
     Text(if(correct) "Correct" else if(selected == -1) "Choose an answer first" else "Review this idea",fontWeight=FontWeight.Bold)
     if(selected != -1) Text(check.explanation,style=MaterialTheme.typography.bodyMedium)
    }
   }
   if(selected != -1) TextButton(onClick={selected=null}) { Text("Try again") }
  }
 }
}
