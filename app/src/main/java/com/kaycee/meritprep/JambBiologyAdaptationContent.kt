package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Objective-led treatment of the JAMB structural, functional and behavioural adaptation unit. */
internal object JambBiologyAdaptationContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Explain how structural, functional and behavioural adaptations help organisms survive in their habitats.",
            listOf(
                JambDeepPoint("Three categories of adaptation",
                    """A structural adaptation is a physical feature of an organism, such as a fish's streamlined body, a cactus spine or the thick fur of a cold-climate mammal. A functional (physiological) adaptation is an internal process, such as concentrated urine in a desert mammal, production of a toxin, or control of water loss through stomata. A behavioural adaptation is an activity or pattern of action, such as migration, nocturnal feeding, basking, courtship display or huddling. One organism can show all three categories at the same time.

                    Adaptations are inherited characteristics that become common in populations over generations through evolutionary processes. They improve performance in a particular environment; they are not deliberate changes made because an individual wants to survive. An adaptation can be advantageous in one habitat and costly in another. For examination answers, name the feature, identify the challenge and explain the mechanism by which the feature improves survival or reproduction.""",
                    "A desert lizard may have dry scales (structural), behavioural sheltering during the hottest hours and physiological water conservation.",
                    "Do not confuse structural, functional and behavioural examples. Explain the link between feature and environmental challenge."),
                JambDeepPoint("Habitat-specific adaptations",
                    """Aquatic organisms face buoyancy, water resistance and dissolved-oxygen challenges. Fish have streamlined bodies, fins and gills; many aquatic plants have air spaces and flexible stems. Terrestrial organisms need support against gravity and protection from drying. A land plant commonly has roots, vascular tissue, a waxy cuticle and stomata. A burrowing animal may have strong forelimbs and reduced exposure to heat or predators. Arboreal animals may have grasping digits, claws or prehensile tails that help them move among branches.

                    In waterlogged mangrove soils, oxygen availability is low; some mangroves produce aerial roots or pneumatophores that assist gas exchange. In dry habitats, reduced leaf area, water storage, deep or widespread roots and stomatal regulation can reduce water stress. Always relate a named feature to the actual conditions of the habitat.""",
                    "Pneumatophores project above waterlogged mangrove mud and expose gas-exchange surfaces to air.",
                    "Match the adaptation to the habitat: aquatic, terrestrial, arboreal, burrowing, saline or dry.")
            ),
            listOf("An adaptation is inherited; acclimatisation occurs within an individual's lifetime.",
                  "The same adaptation may not be useful in every environment.",
                  "A feature name alone is incomplete: explain how it solves a specific problem."),
            listOf(
                JambDeepCheck("bio-adapt-01","Which is a behavioural adaptation?",listOf("A fish's streamlined body","A cactus spine","A lizard basking in the sun","A bird's hollow bone"),2,"Basking is an activity or behaviour; the other options are structural features."),
                JambDeepCheck("bio-adapt-02","A waxy cuticle on a land plant mainly helps to",listOf("increase water loss","reduce evaporation from the plant surface","attract pollinators","absorb mineral salts from soil"),1,"The cuticle limits uncontrolled water loss from exposed plant surfaces."),
                JambDeepCheck("bio-adapt-03","Pneumatophores are useful to some mangroves because they",listOf("absorb sunlight for photosynthesis only","assist gas exchange in waterlogged soil","produce seeds","prevent all salt entry"),1,"Aerial roots expose gas-exchange surfaces to air where oxygen in saturated mud is limited.")
            )
        ),
        JambDeepObjective(
            "Categorise adaptive colouration and explain countershading, camouflage, mimicry and warning colouration.",
            listOf(
                JambDeepPoint("Camouflage and countershading",
                    """Camouflage reduces the chance that an organism is detected. A stick insect resembles a twig, while some praying mantises resemble leaves or flowers. Countershading is a pattern in which the upper surface is darker and the underside is lighter. In fish, this can make the animal less conspicuous from above against darker water and from below against brighter surface light. Similar patterns occur in some toads and snakes. The effect depends on the direction of light and the background.

                    Colour matching may help prey avoid predators or help a predator approach prey. Disruptive patterns break up the outline of the body. Seasonal colour changes in some animals can match changing surroundings. Do not assume that every brightly coloured organism is camouflaged; the function depends on the habitat and the visual system of the observer.""",
                    "A fish viewed from above may blend with darker water, while viewed from below its pale underside blends with light from the surface.",
                    "Countershading = darker dorsal surface and lighter ventral surface; link the two views to their backgrounds."),
                JambDeepPoint("Warning colouration and mimicry",
                    """Warning colouration (aposematism) uses conspicuous colours or patterns to advertise that an organism may be toxic, distasteful, venomous or otherwise dangerous. Some poisonous or unpalatable mushrooms have conspicuous caps; the colour acts as a warning signal only where predators have learned or inherited avoidance responses. Mimicry occurs when one organism resembles another organism or object and gains an advantage. In Batesian mimicry, a harmless species resembles a harmful or unpalatable one. In Müllerian mimicry, two or more harmful species share similar warning signals, reinforcing predator avoidance.

                    Colour alone does not prove that a mushroom is poisonous. Identification requires reliable features and expert knowledge; never taste an unknown mushroom. In an examination, distinguish camouflage (avoid detection), warning colouration (advertise defence) and mimicry (resemblance that changes interactions).""",
                    "A conspicuous warning pattern may cause a predator to avoid an unpalatable prey item; a harmless mimic may gain protection by resembling it.",
                    "Warning colouration advertises danger; camouflage hides; mimicry involves resemblance. Do not infer edibility from colour.")
            ),
            listOf("Countershading is not simply random dark and light patches.",
                  "Warning colouration makes an organism conspicuous rather than hidden.",
                  "Mimicry requires resemblance to another organism or signal; camouflage need not."),
            listOf(
                JambDeepCheck("bio-adapt-04","Countershading in fish commonly involves",listOf("a pale dorsal surface and dark belly","a dark dorsal surface and pale underside","bright red spots only","transparent fins only"),1,"The darker upper surface blends with darker water when viewed from above; the pale underside blends with surface light when viewed from below."),
                JambDeepCheck("bio-adapt-05","The main function of warning colouration is to",listOf("advertise that the organism may be harmful or unpalatable","make the organism invisible","increase body temperature","attract every predator"),0,"A conspicuous signal can teach or remind predators to avoid a defended prey organism."),
                JambDeepCheck("bio-adapt-06","A harmless species resembling a harmful species is an example of",listOf("countershading","Batesian mimicry","hibernation","territoriality"),1,"Batesian mimicry occurs when a harmless species gains protection by resembling a harmful or unpalatable one.")
            )
        ),
        JambDeepObjective(
            "Differentiate the castes of social insects, especially termites, and relate their structures to colony functions.",
            listOf(
                JambDeepPoint("Termite castes and division of labour",
                    """A termite colony has a reproductive division of labour. The primary queen lays eggs and may have a greatly enlarged abdomen; the king remains with the queen and fertilises her. Workers are usually wingless, soft-bodied and often pale. They build and repair the nest, care for eggs and young, gather food and maintain the colony. Soldiers have enlarged heads and strong mandibles or chemical defensive structures; they defend the colony and are generally not responsible for routine feeding or reproduction. Winged reproductives (alates) leave the colony during swarming, shed their wings after pairing and may establish new colonies.

                    Caste appearance and exact roles vary among termite species. Workers and soldiers are commonly sterile or reproductively suppressed forms, while the king and queen are reproductive adults. Division of labour allows the colony to carry out feeding, nest maintenance, defence and reproduction efficiently.""",
                    "A termite with a large head and powerful mandibles is likely a soldier; a swollen egg-laying abdomen identifies the queen.",
                    "Queen/king = reproduction; workers = construction, feeding and brood care; soldiers = defence; alates = dispersal and colony founding."),
                JambDeepPoint("Honeybee castes and colony roles",
                    """A honeybee colony contains one queen, many workers and, during reproductive periods, drones. The queen is the principal egg layer. Workers are sterile females that clean cells, nurse larvae, build wax comb, ventilate the hive, guard the entrance and forage for nectar, pollen, water and propolis. Drones are males whose main role is to mate with a queen during a mating flight. Workers perform different tasks as they age, although age-related task patterns are flexible and depend on colony needs.

                    Social insects communicate through chemical signals and behaviours. Honeybees use dances to communicate the direction and approximate distance of food sources relative to the sun. Termites use chemical signals and contact behaviours to coordinate colony activities. Do not mix termite castes with bee castes: termite colonies have a king, queen, workers and soldiers; honeybee colonies have a queen, workers and drones.""",
                    "A worker honeybee may forage for nectar and pollen, while a drone's primary role is mating with a queen.",
                    "Honeybee queen = egg laying; workers = hive maintenance and foraging; drones = mating. Termites have soldiers as a distinct caste.")
            ),
            listOf("A termite soldier's main role is defence, not egg laying.",
                  "A termite colony has a king as well as a queen.",
                  "A honeybee drone is male; worker bees are female."),
            listOf(
                JambDeepCheck("bio-adapt-07","Which termite caste is mainly responsible for colony defence?",listOf("Queen","King","Soldier","Alate only"),2,"Soldiers have defensive structures such as enlarged mandibles or chemical defence systems."),
                JambDeepCheck("bio-adapt-08","The principal reproductive female in a honeybee colony is the",listOf("worker","drone","queen","soldier"),2,"The queen is the main egg-laying female in a honeybee colony."),
                JambDeepCheck("bio-adapt-09","The main role of a honeybee drone is to",listOf("nurse larvae","mate with a queen","build wax comb","guard the hive entrance"),1,"Drones are males whose main biological role is mating with a queen.")
            )
        ),
        JambDeepObjective(
            "Explain basking, territorial behaviour, hibernation and aestivation as behavioural responses to environmental conditions.",
            listOf(
                JambDeepPoint("Basking and territorial behaviour",
                    """Basking is a behavioural method of thermoregulation used by ectothermic animals such as lizards. By exposing the body to sunlight or a warm surface, a lizard raises its body temperature and can increase muscle activity, digestion and movement. It may move into shade or a burrow when too hot. Basking is therefore a response to environmental temperature, not proof that the animal is endothermic.

                    Territorial behaviour involves defending an area or resource such as food, shelter, nesting sites or mates. Animals may mark territories with scent, calls, displays or physical aggression. Territoriality can reduce repeated conflict by spacing individuals, but it costs energy and may fail when resources are scarce. Courtship and territorial displays can overlap, but the terms are not identical: courtship attracts or selects mates, while territoriality defends access to an area or resource.""",
                    "A male Agama lizard may display bright breeding colours and defend a territory; a lizard may bask on a warm rock to raise body temperature.",
                    "Basking is behavioural thermoregulation in ectotherms. Territoriality is defence of a resource or area."),
                JambDeepPoint("Hibernation and aestivation",
                    """Hibernation is a prolonged period of greatly reduced activity and metabolic rate that helps an animal survive cold conditions or winter food shortage. Body temperature, heart rate and oxygen consumption may fall substantially, depending on the species. Aestivation is a period of reduced activity during hot or dry conditions, often helping an animal avoid dehydration and heat stress. Some snails, amphibians and other animals retreat into sheltered places; land snails may seal the shell opening with a temporary membrane called an epiphragm.

                    These are not identical to ordinary sleep. Hibernation is associated mainly with cold or winter conditions; aestivation is associated mainly with heat or drought. Dormancy strategies differ among species, and not every animal in a hot climate aestivates.""",
                    "A land snail may seal its shell opening and remain inactive during a dry season, reducing water loss.",
                    "Hibernation = cold/unfavourable winter; aestivation = hot/dry conditions. Explain the survival advantage.")
            ),
            listOf("Basking is a behaviour, not an internal physiological process by itself.",
                  "Hibernation is linked to cold; aestivation is linked to heat or drought.",
                  "Territoriality is resource defence, not simply movement within a habitat."),
            listOf(
                JambDeepCheck("bio-adapt-10","A lizard basking on a warm rock is mainly",listOf("raising its body temperature","lowering its body temperature","undergoing aestivation","defending a food store"),0,"As an ectotherm, a lizard uses external heat to raise its body temperature."),
                JambDeepCheck("bio-adapt-11","Aestivation is most directly associated with",listOf("cold winter conditions","hot or dry unfavourable conditions","mating only","migration to a breeding site"),1,"Aestivation reduces activity during hot or dry conditions and can limit water loss."),
                JambDeepCheck("bio-adapt-12","Territorial behaviour helps an animal to",listOf("defend access to resources or breeding areas","avoid all predators permanently","become endothermic","stop needing food"),0,"Territories can secure access to food, shelter, nesting sites or mates.")
            )
        ),
        JambDeepObjective(
            "Relate specialised structures to obtaining food, defence, mating, temperature regulation and water conservation.",
            listOf(
                JambDeepPoint("Feeding structures: bird beaks and legs; insect mouthparts",
                    """Bird beaks vary with feeding method. Seed-eating birds often have short, strong conical beaks for cracking seeds; nectar feeders may have long slender beaks; birds of prey have hooked beaks for tearing flesh; probing birds may have long narrow beaks. Legs also reflect lifestyle: wading birds have long legs and spread toes, perching birds have grasping toes, and birds of prey have strong feet with sharp talons. These are functional relationships, not absolute rules for every species.

                    Insect mouthparts are similarly specialised. A mosquito has piercing and sucking mouthparts; the female pierces skin and takes blood, while males generally feed on plant sugars. Butterflies and many moths have a coiled siphoning proboscis for taking nectar. Houseflies have sponging/lapping mouthparts for liquid or liquefied food. A grasshopper has biting and chewing mouthparts. Relate the shape and action of each mouthpart to the food source.""",
                    "A butterfly uncoils its proboscis to reach nectar; a mosquito uses piercing stylets to obtain blood or plant fluids.",
                    "Know mosquito = piercing/sucking; butterfly/moth = siphoning; housefly = sponging; grasshopper = biting/chewing."),
                JambDeepPoint("Protection, mating, temperature and water conservation",
                    """Stick insects resemble twigs and praying mantises may resemble leaves or flowers, reducing detection or helping them approach prey. A toad's cryptic colour, warty skin and defensive secretions can deter predators; some species inflate the body or adopt warning postures. Male Agama lizards may display bright head colours and use postures or territorial contests to gain access to mates. Birds display feathers, calls and dances during courtship; plumage may also provide insulation, waterproofing or camouflage.

                    Feathers and hairs trap insulating air and reduce heat exchange; skin blood flow and sweating or panting help regulate temperature in many animals. Mammalian scales or thickened skin can reduce water loss in some species, while reptiles' keratinised scales reduce evaporation. Desert plants reduce water loss through spines instead of broad leaves, thick cuticles, sunken stomata or water-storing tissues. Cacti store water in stems; their spines provide defence and reduce exposed leaf area.""",
                    "A stick insect resembles a twig to avoid detection; cactus spines reduce leaf surface area and help conserve water.",
                    "For each example, state the structure, function and survival advantage. Do not mix mating displays with camouflage.")
            ),
            listOf("Male and female mosquito feeding habits differ; do not say all mosquitoes feed on blood.",
                  "Butterfly and moth mouthparts are commonly siphoning, not piercing.",
                  "Cactus spines are modified leaves; photosynthesis mainly occurs in the green stem.",
                  "Feathers and hairs help insulation; they are not only for flight or appearance."),
            listOf(
                JambDeepCheck("bio-adapt-13","The coiled proboscis of a butterfly is adapted for",listOf("piercing skin","siphoning nectar","crushing seeds","filtering water"),1,"A butterfly uncoils its proboscis to suck liquid nectar from flowers."),
                JambDeepCheck("bio-adapt-14","Which beak is best suited to cracking hard seeds?",listOf("Short, strong conical beak","Long slender probing beak","Broad flat filtering beak","Hooked tearing beak"),0,"A short, strong conical beak can exert force to crack seeds."),
                JambDeepCheck("bio-adapt-15","The reduced leaf surface of many cacti helps to",listOf("increase transpiration","reduce water loss","absorb animal prey","produce flowers continuously"),1,"Leaves modified into spines greatly reduce exposed surface area; the green stem carries out most photosynthesis."),
                JambDeepCheck("bio-adapt-16","Bright display colours in a male Agama lizard are mainly associated with",listOf("courtship and social signalling","water absorption","spore dispersal","aerial respiration"),0,"Bright colours and displays can signal status and help attract mates.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-adaptation") objectives else emptyList()
}

@Composable
internal fun AdaptationScenarioLab() {
    data class Scenario(val title: String, val challenge: String, val options: List<String>, val answer: Int, val reason: String)
    val scenarios = listOf(
        Scenario("Hot, dry habitat", "A small plant loses too much water through broad leaves.", listOf("Develop a larger, thinner leaf surface","Reduce leaf area and develop a thick cuticle","Keep stomata permanently open","Grow soft aquatic stems"),1,"A reduced leaf surface and thick cuticle lower water loss; many desert plants also store water in stems."),
        Scenario("Waterlogged mangrove mud", "Roots are surrounded by saturated soil with little available oxygen.", listOf("Produce aerial breathing roots","Develop a thick waxy flower","Lose all roots","Use feathers to trap air"),0,"Pneumatophores project above the mud and expose gas-exchange surfaces to air."),
        Scenario("Cold morning", "An ectothermic lizard needs to warm up before becoming active.", listOf("Aestivate underground","Bask in sunlight","Hibernate in hot sand","Increase transpiration"),1,"Basking allows an ectotherm to absorb heat from the environment."),
        Scenario("Predator-rich grassland", "A stick insect is exposed on vegetation.", listOf("Use twig-like body shape and colour","Display bright warning colours without defence","Remain motionless on bare soil","Develop a shell like a snail"),0,"Resemblance to twigs makes the insect harder for predators to detect.")
    )
    var selected by remember { mutableIntStateOf(0) }
    var answer by remember { mutableStateOf<Int?>(null) }
    var checked by remember { mutableStateOf(false) }
    val scenario = scenarios[selected]
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("ADAPTATION SCENARIO LAB", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose a field condition and predict which adaptation would provide the clearest advantage.")
            scenarios.forEachIndexed { index, item ->
                OutlinedButton(onClick = { selected = index; answer = null; checked = false }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (selected == index) "✓  ${item.title}" else item.title, modifier = Modifier.fillMaxWidth())
                }
            }
            Text(scenario.challenge, fontWeight = FontWeight.SemiBold)
            scenario.options.forEachIndexed { index, option ->
                OutlinedButton(
                    onClick = { if (!checked) answer = index },
                    enabled = !checked,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = if (answer == index) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface)
                ) { Text(option, modifier = Modifier.fillMaxWidth()) }
            }
            if (!checked) {
                Button(onClick = { checked = true }, enabled = answer != null, modifier = Modifier.fillMaxWidth()) { Text("Test prediction") }
            } else {
                Text(if (answer == scenario.answer) "Correct prediction" else "Reconsider the environmental challenge", color = if (answer == scenario.answer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                Text(scenario.reason)
                TextButton(onClick = { answer = null; checked = false }) { Text("Try this scenario again") }
            }
        }
    }
}
