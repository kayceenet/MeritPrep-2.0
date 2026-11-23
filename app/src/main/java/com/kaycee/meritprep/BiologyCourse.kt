package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class BiologySyllabusUnit(
    val id: String, val section: String, val title: String,
    val scope: String, val outcomes: String, val waecLinks: List<String>,
    val lessonId: String? = null
)

object BiologyCurriculum {
    const val VERSION = "2026.1"
    const val JAMB_SOURCE = "JAMB IBASS Biology syllabus, 2026 copy"
    const val WAEC_SOURCE = "WAEC Biology examination syllabus, Nigerian Section C"

    val units = listOf(
        BiologySyllabusUnit("bio-living","A · Variety of organisms","Living organisms and cell organisation","Characteristics of life; cell theory; plant and animal cells; organelles; levels of organisation.","Distinguish living from non-living things; identify cell structures and functions; compare plant and animal cells; explain levels of organisation.",listOf("Concept of living","Organisation of life","Cell structure and functions"),"bio-cell"),
        BiologySyllabusUnit("bio-classification","A · Variety of organisms","Classification and diversity of organisms","Prokaryotes and eukaryotes; Monera, Protista, Fungi, Plantae and Animalia; major plant divisions and animal groups.","Recognise major groups, compare their characteristics and relate organisms to their environments.",listOf("Classification","Forms of living cells")),
        BiologySyllabusUnit("bio-adaptation","A · Variety of organisms","Adaptation for survival","Structural, physiological and behavioural adaptations; camouflage; warning colouration; feeding structures; social insects; hibernation and aestivation.","Relate adaptations to feeding, protection, reproduction, temperature regulation and water conservation.",listOf("Adaptation for survival","Behavioural adaptation")),
        BiologySyllabusUnit("bio-plant-anatomy","B · Form and functions","Plant and mammalian structure","Internal structure of roots, stems and leaves; supporting and vascular tissues; arrangement and functions of mammalian organs.","Identify transverse sections; connect tissue structure to function; locate major mammalian organs.",listOf("Plant structure","Animal structure")),
        BiologySyllabusUnit("bio-nutrition","B · Form and functions","Nutrition in plants and animals","Autotrophic and heterotrophic nutrition; photosynthesis; mineral nutrition; food classes and tests; teeth; alimentary canal; digestion and absorption.","Explain nutrition; interpret food-test results; relate digestive structures and enzymes to their functions.",listOf("Plant nutrition","Animal nutrition")),
        BiologySyllabusUnit("bio-transport","B · Form and functions","Transport in plants and animals","Diffusion, osmosis and active transport; blood and lymph; heart and vessels; xylem, phloem, transpiration and translocation.","Explain transport needs; compare transport tissues; trace circulation and movement of materials.",listOf("Transport","Cell environment")),
        BiologySyllabusUnit("bio-respiration","B · Form and functions","Respiration and gaseous exchange","Respiratory surfaces; gaseous exchange; aerobic and anaerobic respiration; ATP; fermentation.","Compare respiratory surfaces; explain respiration and interpret simple experiments.",listOf("Cellular respiration","Respiratory system")),
        BiologySyllabusUnit("bio-excretion","B · Form and functions","Excretion and osmoregulation","Waste products; contractile vacuoles, flame cells, nephridia, Malpighian tubules and kidneys; excretion by lungs, skin and plants.","Distinguish excretion from egestion; relate excretory structures to function; explain kidney roles.",listOf("Excretion","Homeostasis")),
        BiologySyllabusUnit("bio-support","B · Form and functions","Support and movement","Plant supporting tissues and tropisms; skeletons; bones, cartilage and chitin; joints; muscles and locomotion.","Explain support and movement; identify skeleton types and joints; relate plant responses to stimuli.",listOf("Supporting systems","Movement")),
        BiologySyllabusUnit("bio-reproduction","B · Form and functions","Reproduction in plants and animals","Asexual and sexual reproduction; vegetative propagation; flowers; pollination; fertilisation; mammalian reproduction and development.","Compare reproductive strategies; trace fertilisation and development; relate structures to functions.",listOf("Reproduction","Human reproduction")),
        BiologySyllabusUnit("bio-growth","B · Form and functions","Growth and development","Growth measurements; seed structure and germination; epigeal and hypogeal germination; plant growth regions and regulators.","Explain growth; identify germination conditions; distinguish germination types.",listOf("Growth","Development")),
        BiologySyllabusUnit("bio-coordination","B · Form and functions","Coordination, control and homeostasis","Nervous system; impulses; reflexes; sense organs; endocrine glands; hormones; temperature and water balance.","Relate nervous and hormonal structures to function; explain reflexes and homeostasis.",listOf("Coordination","Sense organs","Hormonal control")),
        BiologySyllabusUnit("bio-ecology-factors","C · Ecology","Factors affecting distribution of organisms","Abiotic and biotic factors; temperature, rainfall, humidity, light, pH, salinity, soil and human activities; field measurement.","Explain environmental effects on distribution and describe field measurement instruments.",listOf("Ecological factors","Fieldwork")),
        BiologySyllabusUnit("bio-interactions","C · Ecology","Ecological relationships and energy flow","Food chains and webs; trophic levels; symbiosis, parasitism, predation, competition; carbon, water and nitrogen cycles.","Interpret food chains and webs; classify relationships; explain nutrient cycles.",listOf("Interdependence","Energy flow","Nutrient cycles")),
        BiologySyllabusUnit("bio-habitats","C · Ecology","Habitats and Nigerian biomes","Aquatic, terrestrial and arboreal habitats; rainforest, Guinea and Sudan savanna, desert and montane environments.","Associate organisms with habitats; locate Nigerian biomes and explain their features.",listOf("Habitats","Nigerian biomes")),
        BiologySyllabusUnit("bio-biomes","C · Ecology","Local Nigerian biomes","Tropical rainforest; southern and northern Guinea savanna; Sudan savanna; desert; montane forests and grasslands of the Obudu, Jos and Mambilla Plateaus.","Locate major Nigerian biomes and relate rainfall, vegetation, temperature and altitude to their characteristic organisms.",listOf("Nigerian biomes","Habitat adaptation")),
        BiologySyllabusUnit("bio-populations","C · Ecology","Population ecology and succession","Population size and density; overcrowding; competition; limiting factors; primary and secondary succession.","Calculate population density; explain population change and ecological succession.",listOf("Population ecology","Competition","Succession")),
        BiologySyllabusUnit("bio-soil","C · Ecology","Soil ecology and conservation","Sandy, clayey and loamy soils; soil profile, texture, porosity, capillarity, humus, fertility and conservation.","Compare soil types; interpret soil experiments; explain fertility loss and conservation.",listOf("Soil","Soil conservation")),
        BiologySyllabusUnit("bio-environment","C · Ecology","Humans, health and the environment","Communicable diseases and vectors; prevention; vaccination; drug misuse; pollution; sanitation; conservation and Nigerian protected areas.","Relate pathogens and vectors to transmission and control; evaluate pollution and conservation.",listOf("Human health","Pollution","Conservation")),
        BiologySyllabusUnit("bio-variation","D · Heredity and variations","Variation in populations","Continuous and discontinuous variation; inherited and environmental influences; morphology, physiology, fingerprints and ABO blood groups.","Compare variation types; interpret frequency distributions; apply blood-group knowledge.",listOf("Variation","Blood groups")),
        BiologySyllabusUnit("bio-heredity","D · Heredity and variations","Heredity and genetics","Genes, alleles, chromosomes and DNA; Mendelian inheritance; genetic crosses; sex determination; sex-linked traits; applications.","Use genetic terminology; interpret simple crosses; explain inheritance and applications.",listOf("Genetics","Inheritance")),
        BiologySyllabusUnit("bio-biotechnology","D · Heredity and variations","Biotechnology","Traditional and modern biotechnology; fermentation; tissue culture; selective breeding; recombinant DNA; biosafety.","Define biotechnology and explain applications, benefits, limitations and biosafety.",listOf("Biotechnology","Genetic engineering")),
        BiologySyllabusUnit("bio-evolution-theory","E · Evolution","Theories and mechanisms of evolution","Organic evolution; Lamarckian and Darwinian explanations; natural selection; variation and mutation.","Compare theories and explain how heritable variation and selection change populations.",listOf("Theories of evolution","Natural selection")),
        BiologySyllabusUnit("bio-evolution-evidence","E · Evolution","Evidence and trends in evolution","Fossil records; comparative anatomy, physiology, biochemistry and embryology; geographical distribution; evolutionary trends.","Evaluate evidence and trace broad evolutionary trends in plants and animals.",listOf("Evidence of evolution","Evolutionary trends"))
    )

    val waecPracticalStrands = listOf(
        "Specimen observation, biological drawing and labelling",
        "Classification and comparison using observable features",
        "Food tests and interpretation of colour changes",
        "Microscopy, cells and temporary preparations",
        "Osmosis, diffusion and simple physiological investigations",
        "Ecological sampling, field measurements, tables and graphs",
        "Experimental design: aim, hypothesis, variables, method, results and conclusion"
    )
    val sections = units.map { it.section }.distinct()
}

@Composable
fun BiologyCourseScreen(onOpenLesson: (String) -> Unit) {
    LazyColumn(Modifier.fillMaxSize().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Biology", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("A guided course from first principles to examination-level application.")
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Curriculum map · ${BiologyCurriculum.units.size} JAMB topic blocks", fontWeight = FontWeight.Bold)
                    Text("Mapped to JAMB Biology and cross-referenced with WAEC Biology. The syllabus map is complete as a structure; lessons are released only after authoring and academic checks.")
                    Text("Course version ${BiologyCurriculum.VERSION}", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
        BiologyCurriculum.sections.forEach { section ->
            item { Text(section, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
            items(BiologyCurriculum.units.filter { it.section == section }, key = { it.id }) { unit ->
                val ready = unit.lessonId != null
                Card(onClick = { unit.lessonId?.let(onOpenLesson) }, enabled = ready, modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(unit.title, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
                            AssistChip(onClick = { unit.lessonId?.let(onOpenLesson) }, enabled = ready,
                                label = { Text(if (ready) "Open lesson" else "In development") })
                        }
                        Text(unit.scope, style = MaterialTheme.typography.bodyMedium)
                        Text("JAMB outcome: ${unit.outcomes}", style = MaterialTheme.typography.bodySmall)
                        Text("WAEC links: ${unit.waecLinks.joinToString(" · ")}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
        item {
            Text("WAEC practical preparation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            BiologyCurriculum.waecPracticalStrands.forEachIndexed { index, strand -> Text("${index + 1}. $strand") }
            Text("Practical tasks, marking rubrics and specimen sets will be added as separately reviewed assessment content.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
