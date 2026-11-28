package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB Ecology: abiotic and biotic factors affecting the distribution of organisms. */
internal object JambBiologyEcologyFactorsContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Explain how temperature, rainfall, humidity, wind and altitude affect the distribution of organisms.",
            listOf(
                JambDeepPoint("Temperature and organism distribution",
                    """Temperature affects enzyme activity, respiration, photosynthesis, growth, reproduction and water loss. Each species has a tolerance range with a minimum, optimum and maximum. Near the optimum, growth and reproduction may be highest; below or above the range, physiological processes become less effective and survival may fail. Ectotherms such as many insects and reptiles depend strongly on environmental temperature, while endotherms regulate body temperature metabolically but still face energetic costs in extreme conditions.

                    Temperature changes with latitude, season, time of day, shade and altitude. It also interacts with moisture: high temperature can increase evaporation and water stress, while cold can slow decomposition and nutrient cycling. Distribution patterns should therefore be explained using both temperature and other interacting factors, not a single variable in isolation.""",
                    "A lizard may bask to raise body temperature and become active; a cold high-altitude habitat may support fewer warmth-demanding species.",
                    "Relate temperature to enzyme activity, metabolism, reproduction and the tolerance limits of the organism."),
                JambDeepPoint("Rainfall and relative humidity",
                    """Rainfall determines water availability in soil, ponds and streams and influences plant growth, breeding sites and food supply. Plants in wet habitats may have broad leaves and shallow roots, while plants in dry habitats may have reduced leaves, thick cuticles, deep roots or water-storage tissues. Seasonal rainfall can cause migration, dormancy, breeding or changes in flowering and fruiting.

                    Relative humidity is the amount of water vapour in air expressed relative to the maximum amount the air can hold at that temperature. High humidity reduces the water-vapour gradient between an organism and the air, often reducing evaporation and transpiration. Low humidity increases evaporative water loss and may favour organisms with water-conserving adaptations. Humidity and temperature interact because warm air can hold more water vapour than cool air.""",
                    "Mosses and many amphibians are more common in moist habitats; cacti and some succulents tolerate low rainfall through water conservation.",
                    "Rainfall affects water supply; humidity affects evaporative water loss. Do not treat them as identical factors."),
                JambDeepPoint("Wind and altitude",
                    """Wind speed can increase evaporation and transpiration, cool exposed organisms, influence pollination and disperse seeds and spores. Strong winds can damage tall plants, alter tree shape and restrict the growth of exposed vegetation. Wind direction affects the movement of seeds, pollen, airborne organisms and pollutants. Sheltered habitats may support species that cannot tolerate strong exposure.

                    Altitude influences temperature, atmospheric pressure, oxygen availability, wind exposure and often rainfall. As altitude increases, temperature generally decreases in the lower atmosphere, although local conditions vary. High-altitude habitats may have shorter growing seasons, stronger winds and lower oxygen partial pressure. Organisms living there often show structural or physiological adaptations to cold, wind and reduced oxygen.""",
                    "Wind-pollinated grasses release pollen into moving air; high mountain plants are often low-growing and adapted to cold and exposure.",
                    "Explain wind effects on water loss and dispersal; explain altitude through temperature, pressure, oxygen and exposure.")
            ),
            listOf("Temperature tolerance differs among species and life stages.", "Rainfall is water input; humidity is water vapour in air and influences evaporation.", "Wind and altitude interact with temperature, water loss and dispersal."),
            listOf(
                JambDeepCheck("bio-eco-fac-01","Which factor most directly determines the amount of water entering soil from the atmosphere?",listOf("Rainfall","Wind direction","Altitude alone","Soil colour"),0,"Rainfall supplies water to the soil and strongly influences soil moisture and water availability."),
                JambDeepCheck("bio-eco-fac-02","High relative humidity generally reduces transpiration because it",listOf("increases the water-vapour gradient from leaf to air","reduces the water-vapour gradient from leaf to air","stops photosynthesis in all plants","increases soil salinity"),1,"High humidity reduces the difference in water-vapour concentration between the leaf and surrounding air."),
                JambDeepCheck("bio-eco-fac-03","A common environmental change with increasing altitude is",listOf("increasing temperature at all heights","decreasing temperature and oxygen partial pressure","complete absence of wind","constant rainfall in every region"),1,"Temperature and oxygen partial pressure generally decrease with altitude, while exposure and wind may increase.")
            )
        ),
        JambDeepObjective(
            "Explain how salinity, turbidity, pH and soil conditions affect the distribution of organisms.",
            listOf(
                JambDeepPoint("Salinity in aquatic and coastal habitats",
                    """Salinity is the concentration of dissolved salts in water or soil. It affects water potential and the ability of organisms to maintain osmotic balance. Freshwater organisms tend to gain water by osmosis, while marine organisms face different water and salt challenges. Estuarine and mangrove organisms experience fluctuating salinity as tides and rainfall change the mixture of fresh and seawater; species living there need tolerance or mechanisms for coping with these changes.

                    High soil salinity lowers soil water potential, making it harder for roots to absorb water even when the soil appears wet. Salt-sensitive crops may wilt or show reduced growth in saline soil. Halophytes tolerate salty environments through salt exclusion, storage, secretion or osmotic adjustment. Salinity is therefore a chemical factor that influences both water movement and ion balance.""",
                    "Mangrove plants tolerate changing salinity in estuaries; many ordinary crop plants show poor growth in saline soil.",
                    "Salinity changes water potential and ion balance; link it to osmoregulation and species tolerance."),
                JambDeepPoint("Turbidity and light penetration",
                    """Turbidity describes the cloudiness of water caused by suspended particles such as silt, clay, plankton and organic matter. High turbidity reduces light penetration, which can limit photosynthesis by submerged plants and algae. It may also interfere with visual feeding, clog the gills of some aquatic animals and cover eggs or bottom habitats with sediment. Turbidity can rise after soil erosion, heavy rainfall, construction or disturbance of river beds.

                    A Secchi disc provides a simple estimate of water transparency: it is lowered until it is no longer visible and the depth is recorded. The Secchi depth is not a direct measurement of all suspended particles and depends on light conditions and observer judgement. Compare readings at similar times and locations when monitoring changes.""",
                    "After heavy erosion, river water may become turbid and less light reaches submerged aquatic plants.",
                    "High turbidity reduces light penetration and may affect photosynthesis, feeding and aquatic habitats."),
                JambDeepPoint("pH and edaphic (soil) factors",
                    """pH measures how acidic or alkaline a solution is. Soil pH influences nutrient availability, microbial activity and toxicity of some ions. Very acidic soils can increase the availability of aluminium and manganese to harmful levels and reduce availability of some nutrients; alkaline soils may reduce availability of iron, phosphorus and other elements. Different species have different pH tolerances, so soil pH helps determine which plants and soil organisms can thrive.

                    Edaphic factors are soil-related conditions, including texture, structure, depth, moisture, aeration, drainage, humus content, mineral nutrients and salinity. Sandy soil drains quickly and holds relatively little water; clay soil holds more water but may have poor aeration when compacted; loamy soil often provides a balance of drainage, aeration and water retention. Soil conditions influence root growth, microbial decomposition and the distribution of plants and soil animals.""",
                    "Acid-loving plants may grow well in acidic soils, while crops sensitive to acidity may require liming after soil testing.",
                    "Define edaphic factors and connect soil texture, pH, water retention, aeration and nutrients to plant distribution.")
            ),
            listOf("Saline soil can cause physiological drought even when water is present.", "Turbidity is cloudiness from suspended particles; it is not the same as water depth.", "Soil pH affects nutrient availability and toxicity; the best pH differs among species."),
            listOf(
                JambDeepCheck("bio-eco-fac-04","High salinity in soil can reduce plant water uptake because it",listOf("raises soil water potential","lowers soil water potential","removes all mineral ions","increases oxygen concentration"),1,"Dissolved salts lower soil water potential, making water uptake by roots more difficult."),
                JambDeepCheck("bio-eco-fac-05","High turbidity in a lake is likely to",listOf("increase light penetration","reduce light available to submerged plants","remove all dissolved salts","increase transparency"),1,"Suspended particles scatter and absorb light, reducing penetration into the water."),
                JambDeepCheck("bio-eco-fac-06","Which of the following is an edaphic factor?",listOf("Soil pH","Wind direction","Altitude","Cloud cover"),0,"Edaphic factors are soil-related conditions such as pH, texture, moisture and nutrient content.")
            )
        ),
        JambDeepObjective(
            "Select and use appropriate instruments to measure abiotic environmental factors.",
            listOf(
                JambDeepPoint("Weather and microclimate instruments",
                    """A thermometer measures temperature; take readings in shade when estimating air temperature and record the height and location. A rain gauge collects rainfall over a known area and the depth is usually recorded in millimetres. A hygrometer measures relative humidity. An anemometer measures wind speed, while a wind vane indicates wind direction. A light meter measures light intensity, commonly in lux.

                    Measurements should be taken consistently. Record date, time, units, location, height above ground and whether the instrument was exposed to direct sunlight or sheltered. Repeat readings because environmental conditions vary over time. A single measurement may not represent the conditions experienced by organisms across an entire habitat.""",
                    "A rain gauge records rainfall in millimetres; an anemometer records wind speed; a hygrometer measures relative humidity.",
                    "Match the instrument to the variable and record units and sampling conditions."),
                JambDeepPoint("Aquatic and soil measurements",
                    """A Secchi disc estimates water transparency or turbidity by recording the depth at which the disc disappears from view. A pH meter measures acidity or alkalinity; it should be calibrated with buffer solutions and rinsed between samples. A conductivity meter estimates the ability of water to conduct electricity and can be used as an indicator of dissolved ions or salinity, although the relationship depends on ion composition and temperature. A thermometer measures water temperature.

                    Soil moisture can be estimated with a soil moisture probe or by weighing a soil sample before and after drying. Soil texture can be investigated by sedimentation or particle-size analysis. Porosity and water-holding capacity can be compared using equal volumes of soil and controlled water addition. When comparing sites, use similar sample depths, sample volumes and procedures.""",
                    "A Secchi disc is lowered into a lake to compare transparency between sites; a pH meter tests soil-water suspension.",
                    "Secchi disc = transparency; pH meter = acidity; conductivity = dissolved ions; soil probe = moisture."),
                JambDeepPoint("Field sampling, reliability and interpretation",
                    """A good field investigation uses a clear question, an appropriate sampling strategy and repeated measurements. A transect can reveal changes along an environmental gradient, such as from a river bank to dry land. Quadrat sampling estimates the abundance or percentage cover of organisms that do not move rapidly. Random sampling reduces selection bias; systematic sampling is useful for detecting patterns along a gradient. Abiotic readings should be taken at or near the biological sampling points.

                    Control avoidable sources of variation, use replicates, record units and report uncertainty. A correlation between a factor and the abundance of a species does not prove that the factor caused the distribution pattern: other variables may be involved. Where possible, compare several sites or seasons and consider the organism's tolerance range and interactions with other species.""",
                    "Place quadrats at random points along a transect and measure soil moisture and light at each location.",
                    "Use replicates, consistent methods and appropriate sampling; correlation alone does not establish causation.")
            ),
            listOf("A Secchi disc estimates transparency, not salinity.", "A wind vane gives direction; an anemometer measures speed.", "Use replicates and consistent sampling conditions to improve reliability."),
            listOf(
                JambDeepCheck("bio-eco-fac-07","Which instrument is used to measure relative humidity?",listOf("Hygrometer","Rain gauge","Anemometer","Secchi disc"),0,"A hygrometer measures relative humidity."),
                JambDeepCheck("bio-eco-fac-08","A Secchi disc is used to estimate",listOf("soil pH","water transparency","wind speed","rainfall"),1,"The depth at which a Secchi disc disappears gives an estimate of water transparency."),
                JambDeepCheck("bio-eco-fac-09","Which method is most appropriate for estimating percentage cover of small plants in a grassland?",listOf("Rain gauge","Quadrat sampling","Wind vane","Thermometer"),1,"Quadrats are used to estimate abundance or percentage cover of relatively stationary organisms.")
            )
        ),
        JambDeepObjective(
            "Explain how biotic interactions and human activities influence the distribution of organisms.",
            listOf(
                JambDeepPoint("Competition, predation and parasitism",
                    """Biotic factors arise from living organisms and their interactions. Competition occurs when organisms require the same limited resource, such as food, water, light, space or nesting sites. Intraspecific competition occurs between members of the same species; interspecific competition occurs between different species. Strong competition can restrict a species to habitats where it can obtain enough resources or can lead to resource partitioning.

                    Predation occurs when one organism captures and consumes another; predator and prey populations can influence one another. Parasitism benefits the parasite and harms the host, often reducing host growth, survival or reproduction. Disease organisms can reduce population size and change the distribution of susceptible species. Mutualism, commensalism and other interactions can also influence where organisms occur.""",
                    "A parasite may be more common where its host is abundant; a plant may be excluded from a site by competition for light.",
                    "Name the interaction, identify the organisms involved and explain how it affects survival or reproduction."),
                JambDeepPoint("Food supply, pollinators and habitat structure",
                    """The distribution of consumers depends partly on the availability of food and suitable feeding sites. Herbivores may be abundant where preferred plants grow; predators may follow prey populations. Pollinating insects influence the reproductive success and distribution of plants that depend on them. Seed dispersers can influence where plants establish. Shelter, nesting sites and host organisms may also limit distribution.

                    Relationships are not always simple: a species may use several food sources, and the abundance of one species can depend on multiple predators, competitors and mutualists. When an ecological question asks why a species is absent from a habitat, consider both abiotic tolerance and biotic requirements rather than assuming that temperature or rainfall alone explains the pattern.""",
                    "A flowering plant may be limited where its specialist pollinator is absent, even if soil and rainfall are suitable.",
                    "Distribution can depend on food, hosts, pollinators, competitors, predators and habitat structure."),
                JambDeepPoint("Human activities and distribution",
                    """Human activities can alter habitats and the factors that determine species distribution. Deforestation removes canopy cover, changes temperature and humidity, exposes soil to erosion and destroys shelter. Farming, grazing and burning can replace natural vegetation and change soil fertility and species composition. Urbanisation fragments habitats and creates roads, noise, light and pollution. Mining and construction disturb soil and water bodies. Overfishing and hunting reduce populations and may remove key species from food webs.

                    Pollution can change pH, salinity, turbidity and oxygen availability in aquatic systems. Fertiliser runoff may cause eutrophication: nutrient enrichment stimulates algal growth, and decomposition can reduce dissolved oxygen. Conservation actions such as habitat restoration, protected areas, sustainable harvesting, pollution control and wildlife corridors can help maintain or restore distributions.""",
                    "Fertiliser runoff can cause algal blooms and oxygen depletion, reducing sensitive fish populations in a lake.",
                    "Link a human activity to the environmental change it causes and then to the organism's survival, reproduction or distribution.")
            ),
            listOf("Intraspecific competition occurs within a species; interspecific competition occurs between species.", "A species may be absent because a required pollinator or host is missing, even when abiotic conditions are suitable.", "Human impacts often act through habitat loss, pollution, fragmentation and altered food webs."),
            listOf(
                JambDeepCheck("bio-eco-fac-10","Competition between organisms of the same species is",listOf("interspecific","intraspecific","mutualism","commensalism"),1,"Intraspecific competition occurs among members of the same species."),
                JambDeepCheck("bio-eco-fac-11","A plant that depends on a particular pollinating insect may be absent from a site because",listOf("the pollinator is missing","soil always contains no water","all plants avoid light","temperature has no effect"),0,"The absence of a required pollinator can limit successful reproduction and establishment."),
                JambDeepCheck("bio-eco-fac-12","Nutrient-rich fertiliser runoff may reduce fish populations by causing",listOf("eutrophication and oxygen depletion","permanent increase in water clarity","removal of all bacteria","lower plant growth in every case"),0,"Nutrient enrichment can trigger algal blooms; decomposition then consumes dissolved oxygen.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-ecology-factors") objectives else emptyList()
}

@Composable
internal fun HabitatDistributionInvestigation() {
    var habitat by remember { mutableStateOf("Mangrove estuary") }
    val result = when (habitat) {
        "Mangrove estuary" -> "Key pressures: changing salinity with tides and rainfall, waterlogged low-oxygen mud, tidal flooding and strong sunlight. Mangrove species need salt and waterlogging tolerance and supporting root systems."
        "Mountain grassland" -> "Key pressures: low temperature, stronger wind, reduced oxygen partial pressure and often a short growing season. Low-growing plants can reduce wind exposure and conserve heat."
        "Turbid lake" -> "Key pressures: suspended particles reduce light penetration; turbidity can affect photosynthesis, visual feeding and gill function. Measure transparency with a Secchi disc."
        else -> "Key pressures: low rainfall, high evaporative demand and often high daytime temperature. Water-conserving plants may have reduced leaves, thick cuticles, deep roots or water-storage tissues."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("HABITAT DISTRIBUTION INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Predict which factors limit distribution", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Mangrove estuary","Mountain grassland","Turbid lake","Dry savanna").forEach {
                FilterChip(selected = habitat == it, onClick = { habitat = it }, label = { Text(it) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(habitat, fontWeight = FontWeight.Bold)
                    Text(result)
                }
            }
            Text("Field task: choose two abiotic factors to measure, name the instrument for each, then record one biotic interaction that could also affect distribution.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun EcologyInstrumentSelectionLab() {
    var variable by remember { mutableStateOf("Relative humidity") }
    val instrument = when (variable) {
        "Temperature" -> "Thermometer"
        "Rainfall" -> "Rain gauge"
        "Relative humidity" -> "Hygrometer"
        "Wind speed" -> "Anemometer"
        "Wind direction" -> "Wind vane"
        "Water transparency" -> "Secchi disc"
        "Soil or water pH" -> "pH meter or suitable indicator"
        else -> "Conductivity meter (estimate of dissolved ionic content; calibrate and account for temperature)"
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("FIELD INSTRUMENT PRACTICAL", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose the correct instrument", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Temperature","Rainfall","Relative humidity","Wind speed","Wind direction","Water transparency","Soil or water pH","Dissolved ions / salinity").forEach {
                FilterChip(selected = variable == it, onClick = { variable = it }, label = { Text(it) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Recommended instrument", fontWeight = FontWeight.Bold)
                    Text(instrument)
                }
            }
            Text("Record the unit, location, time and sampling method. Repeat measurements and compare like with like.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
