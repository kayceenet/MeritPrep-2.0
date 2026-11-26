package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import org.json.JSONArray
import org.json.JSONObject
import com.kaycee.meritprep.data.MeritDatabase
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

internal data class InvestigationProfile(
    val title: String, val question: String, val apparatus: String,
    val independent: String, val dependent: String, val controls: String,
    val xUnit: String, val yUnit: String, val safety: String,
    val method: List<String>, val limitation: String, val response: (Float) -> Float,
    val minX: Float = 0f, val maxX: Float = 100f, val initialX: Float = 25f
)

internal object InvestigationProfiles {
    private fun profile(
        title: String, question: String, apparatus: String, independent: String,
        dependent: String, controls: String, xUnit: String, yUnit: String,
        safety: String, method: List<String>, limitation: String,
        response: (Float) -> Float, minX: Float = 0f, maxX: Float = 100f, initialX: Float = 25f
    ) = InvestigationProfile(title, question, apparatus, independent, dependent, controls,
        xUnit, yUnit, safety, method, limitation, response, minX, maxX, initialX)

    fun supports(id: String): Boolean = find(id) != null

    fun find(id: String): InvestigationProfile? = when (id) {
        "bio-living" -> profile("Characteristics of living things", "How can observations be used to distinguish living from non-living material?",
            "Safe specimen images, hand lens and observation checklist", "Number of life-process indicators checked", "Evidence-based classification score", "Same observation criteria and sample set", "indicators", "score",
            "Do not handle unknown organisms; use approved images or safe classroom specimens.",
            listOf("Observe movement, nutrition, respiration, growth and response evidence", "Look for reproduction and excretion evidence where observable", "Record evidence rather than relying on appearance", "Explain why one indicator alone may be insufficient"),
            "Observation framework, not a diagnostic test for life in every biological edge case.", { x -> 0.7f * x })
        "bio-evolution-evidence" -> profile("Comparative anatomy and evolutionary evidence", "How can homologous structures support an inference about common ancestry?",
            "Labelled forelimb diagrams, measuring grid and comparison table", "Number of structural features compared", "Shared structural pattern score", "Same anatomical landmarks and scoring rule", "features", "score",
            "Use educational diagrams; no specimen dissection is required.",
            listOf("Identify corresponding bones in each forelimb", "Compare position and relative arrangement", "Separate structural homology from superficial similarity", "State what the evidence supports and what it cannot prove alone"),
            "A scoring aid for comparing structures; ancestry inference uses multiple independent evidence sources.", { x -> 0.8f * x })
        "bio-cell" -> profile("Microscopy and magnification", "How does magnification affect the apparent size of a specimen?",
            "Prepared slide, microscope, calibrated eyepiece scale and stage micrometer", "Magnification", "Apparent image size", "Same specimen, objective and optical setup", "×", "mm",
            "Handle slides by their edges; begin with low power and use a coverslip.",
            listOf("Select and identify a prepared slide", "Focus under low power before changing objective", "Calibrate the scale against a stage micrometer", "Measure the same feature and repeat"),
            "Illustrative direct-proportion model for a 0.08 mm specimen feature; actual calibration depends on the microscope.", { x -> 0.08f * x }, 1f, 100f, 25f)
        "bio-classification" -> profile("Classification by observable characters", "How can organisms be grouped using shared diagnostic features?",
            "Safe specimen images, hand lens, dichotomous key and recording sheet", "Diagnostic characters examined", "Consistent identification decisions", "Same specimens and identification key", "characters", "decisions",
            "Do not collect protected organisms or handle unknown, stinging or poisonous specimens.",
            listOf("Observe several independent characteristics", "Record visible evidence before naming", "Follow one branch of the key at a time", "Check the identification using another feature"),
            "A key-based reasoning exercise, not a biological growth law.", { x -> 0.6f * x + 2f })
        "bio-adaptation" -> profile("Feeding structures and food collection", "How does a model feeding structure affect food collection?",
            "Blunt forceps, spoons, pegs, paper food items, trays and timer", "Time available", "Food items collected", "Tool, food type, distance and operator", "s", "items",
            "Use blunt tools and paper models; never test model tools on animals.",
            listOf("Choose one tool and one model food type", "Keep starting number and distance constant", "Collect for a fixed time", "Repeat trials and compare means"),
            "A classroom analogy only; it cannot establish evolutionary adaptation on its own.", { x -> 0.55f * x })
        "bio-plant-anatomy" -> profile("Transpiration and leaf area", "How does exposed leaf area affect water loss under controlled conditions?",
            "Leafy shoot, potometer or balance, ruler, timer and sealant", "Exposed leaf area", "Relative water-loss rate", "Light, temperature, humidity, air movement and time", "cm²", "g h⁻¹",
            "Use approved plant material; keep electrical equipment away from water.",
            listOf("Select a healthy leafy shoot and measure leaf area", "Set up a sealed potometer or mass-loss arrangement", "Change leaf area while controlling other conditions", "Record mass loss or bubble movement at fixed intervals"),
            "Illustrative trend only; stomatal behaviour and environment also affect transpiration.", { x -> 0.08f * x })
        "bio-nutrition" -> profile("Photosynthesis and light intensity", "How does light intensity affect the rate of photosynthesis?",
            "Aquatic plant, LED lamp, ruler, timer and hydrogencarbonate solution", "Relative light intensity", "Oxygen bubble rate", "Plant size, CO₂ supply, temperature and observation time", "%", "bubbles min⁻¹",
            "Keep electrical equipment dry and use a cool LED lamp to reduce heating.",
            listOf("Allow the plant to acclimatise", "Set one light intensity", "Count bubbles for a fixed interval", "Repeat at several intensities and control temperature"),
            "Bubble count is an imperfect proxy because bubble sizes vary.", { x -> 0.9f * x / (1f + x / 45f) })
        "bio-transport" -> profile("Osmosis in plant tissue", "How does solution concentration affect potato tissue mass?",
            "Equal potato cylinders, balance, labelled solutions, paper towel and timer", "External solute concentration", "Percentage mass change", "Potato source, size, solution volume, temperature and time", "mol dm⁻³", "%",
            "Use a clean blade only under supervision; never taste laboratory solutions.",
            listOf("Cut equal-sized cylinders and record initial mass", "Place each cylinder in a labelled solution", "Keep immersion time and temperature constant", "Blot consistently and record final mass"),
            "Hypothetical teaching curve; the isotonic point depends on tissue and solute.", { x -> 18f - 0.42f * x })
        "bio-respiration" -> profile("Respiration in germinating seeds", "How does seed condition affect carbon dioxide production?",
            "Germinating seeds, boiled control seeds, respirometer or CO₂ indicator and timer", "Seed condition score", "Relative respiration indicator", "Seed mass, temperature, time and apparatus", "score", "relative units",
            "Use teacher-approved apparatus; do not create pressure in sealed living-material vessels.",
            listOf("Prepare equal-mass germinating and control samples", "Set up identical indicator arrangements", "Record the indicator at fixed intervals", "Compare with the non-germinating control"),
            "Illustrative contrast only; no actual gas measurements are represented.", { x -> 0.7f * x + 2f })
        "bio-excretion" -> profile("Leaf water loss and stomata", "How does exposed leaf surface relate to water loss?",
            "Comparable leaves, balance, transparent bags, timer and hand lens", "Fraction of surface exposed", "Relative water loss", "Leaf area, light, temperature, humidity and duration", "%", "relative units",
            "Use safe specimens and wash hands after handling plant material.",
            listOf("Select comparable leaves", "Cover selected surfaces as directed", "Quantify water loss over a fixed interval", "Compare exposed and covered treatments with repeats"),
            "Cuticle evaporation and treatment effects are not separated in this model.", { x -> 0.06f * x })
        "bio-support" -> profile("Stem support and deflection", "How does applied load affect stem deflection?",
            "Comparable stems or straws, clamp, ruler and small masses", "Applied load", "Stem deflection", "Stem length, diameter, orientation and support points", "g", "mm",
            "Secure clamps and use small loads; keep faces clear of falling masses.",
            listOf("Select stems of similar length and diameter", "Support at fixed points", "Add small loads gradually", "Measure deflection and stop before unsafe failure"),
            "Comparative model only; living stems vary and may not behave elastically.", { x -> 0.015f * x * x }, 0f, 20f, 5f)
        "bio-reproduction" -> profile("Conditions for seed germination", "How does water availability affect germination?",
            "Seeds, labelled dishes, cotton wool, measured water and ruler", "Water supplied", "Germination percentage", "Seed type, number, temperature, oxygen and observation period", "mL", "%",
            "Use clean seeds, wash hands and discard mouldy material safely.",
            listOf("Use equal numbers of viable seeds", "Provide different measured water volumes", "Keep temperature and oxygen comparable", "Count germinated seeds using a defined radicle criterion"),
            "Illustrative response only; excess water may restrict oxygen availability.", { x -> (1.8f * x - 0.012f * x * x).coerceIn(0f, 100f) })
        "bio-growth" -> profile("Seedling growth over time", "How does seedling height change during early growth?",
            "Seedlings, ruler, labels, water and growth record", "Time", "Seedling height", "Species, light, water, soil and temperature", "days", "cm",
            "Avoid mould and wash hands after handling soil.",
            listOf("Select seedlings at a similar stage", "Measure from a defined reference point", "Measure at the same time each day", "Repeat across seedlings and calculate a mean"),
            "Illustrative early-growth curve; real growth varies with species and conditions.", { x -> 0.015f * x * x + 0.2f * x }, 0f, 30f, 10f)
        "bio-coordination" -> profile("Response time and practice", "How does practice affect performance in a ruler-drop response test?",
            "Ruler-drop apparatus, recording sheet and calculator", "Practice trial", "Catch distance", "Participant, ruler, posture and release method", "trial", "cm",
            "Use a seated volunteer; participation must be voluntary and safe.",
            listOf("Explain the test and obtain consent", "Keep hand position and release method constant", "Record catch distance for each trial", "Compare early and later trials"),
            "Illustrative trend; attention, fatigue and anticipation affect responses.", { x -> 32f / (1f + 0.08f * x) })
        "bio-environment" -> profile("Environmental gradient and distribution", "How does an environmental gradient relate to organism abundance?",
            "Quadrat, transect tape, thermometer or light meter and tally sheet", "Distance along transect", "Organisms per quadrat", "Quadrat size, sampling effort, time and identification method", "m", "organisms",
            "Avoid unsafe habitats and disturbance of protected species; follow fieldwork supervision.",
            listOf("Choose a safe representative transect", "Place quadrats at fixed intervals", "Record organisms and environmental conditions", "Repeat transects and compare patterns"),
            "Illustrative abundance gradient; real distributions have several interacting causes.", { x -> (18f - 0.22f * x).coerceAtLeast(0f) })
        "bio-interactions" -> profile("Predator–prey model", "How can prey availability affect predator success in a model ecosystem?",
            "Population cards or counters and time-step record", "Prey availability", "Relative predator success", "Starting populations, time step and search effort", "units", "index",
            "Use counters only; this is not an experiment on live animals.",
            listOf("Set equal starting conditions", "Change prey availability only", "Run the same number of time steps", "Record success and discuss assumptions"),
            "Simplified model omitting age structure, disease, migration and habitat complexity.", { x -> 0.1f * x })
        "bio-populations" -> profile("Quadrat sampling and sampling uncertainty", "How does the relative uncertainty of a mean estimate change as independent samples increase?",
            "Known-area quadrat, tape measure, tally sheet and calculator", "Number of samples", "Relative sampling uncertainty", "Quadrat area, sample variance and sampling method", "samples", "%",
            "Sample only safe sites and avoid damaging organisms or habitats.",
            listOf("Define the target organism and sampling area", "Place quadrats randomly or systematically", "Count individuals in every quadrat", "Calculate mean density and discuss uncertainty"),
            "Simplified inverse-square-root uncertainty model; actual uncertainty depends on variance and sampling design.", { x -> 100f / kotlin.math.sqrt(x.coerceAtLeast(1f)) }, 1f, 100f, 10f)
        "bio-soil" -> profile("Soil water retention", "How does soil type affect water retained after drainage?",
            "Equal soil masses, funnels, filter material, measuring cylinders and water", "Soil type index", "Water retained", "Dry soil mass, water volume, packing and drainage time", "index", "mL",
            "Avoid inhaling dry soil and wash hands after the practical.",
            listOf("Use equal dry masses of soil", "Add the same measured water volume", "Allow the same drainage time", "Measure drainage and calculate retained water"),
            "Illustrative comparison; texture and organic matter both affect retention.", { x -> 8f + 0.35f * x })
        "bio-variation" -> profile("Variation within a population", "How are measurements distributed within a sample?",
            "Ruler or callipers, safe specimens, tally sheet and graph paper", "Specimen number", "Measured feature", "Measurement method, instrument and sampling frame", "specimen", "mm",
            "Use non-hazardous samples and clean tools between specimens.",
            listOf("Define one measurable characteristic", "Select specimens without choosing only extremes", "Measure consistently and record precision", "Calculate mean and range and plot a frequency distribution"),
            "Synthetic pattern for practising analysis, not a real population survey.", { x -> 12f + 0.18f * (x - 50f) })
        "bio-heredity" -> profile("Mendelian inheritance model", "How does the expected dominant phenotype count change with model offspring sample size?",
            "Allele cards, Punnett-square grid, counters and tally sheet", "Number of model offspring", "Expected dominant-phenotype count", "Parental genotypes, dominance relationship and equal segregation",
            "offspring", "expected offspring",
            "This is a probability model; no human or animal breeding experiment is involved.",
            listOf("Write the parental genotypes and possible gametes", "Construct the Punnett square", "Determine genotype and phenotype ratios", "Calculate expected counts for the chosen sample size", "Compare simulated counts with the expected ratio and explain random variation"),
            "Single-gene model with complete dominance and independent segregation; many real traits are polygenic or influenced by environment.", { x -> 0.75f * x }, 0f, 100f, 20f)
        "bio-biotechnology" -> profile("Yeast fermentation", "How does sugar concentration affect carbon dioxide production by yeast?",
            "Yeast, sugar solutions, warm water, fermentation vessel and gas syringe", "Sugar concentration", "CO₂ production rate", "Yeast mass, temperature, pH, volume and time", "%", "mL min⁻¹",
            "Never ingest mixtures or tightly seal a gas-producing vessel.",
            listOf("Prepare equal yeast suspensions", "Add measured sugar concentrations", "Keep temperature and time constant", "Measure gas volume at fixed intervals and repeat"),
            "Illustrative saturating curve; actual kinetics depend on yeast strain and conditions.", { x -> 0.12f * x / (1f + x / 40f) })
        "chem-separation" -> profile("Paper chromatography", "How does a component travel relative to the solvent front?",
            "Chromatography paper, approved ink, solvent chamber, pencil and ruler", "Solvent-front distance", "Component travel distance", "Paper, sample spot, solvent and temperature", "cm", "cm",
            "Use only approved solvents in ventilation and keep them away from flames.",
            listOf("Draw a pencil baseline above solvent level", "Apply a small sample spot", "Develop in a covered chamber", "Mark solvent front and calculate Rf"),
            "Illustrative linear model; Rf depends on both phases and conditions.", { x -> 0.62f * x })
        "chem-rates" -> profile("Reaction rate and temperature", "How does temperature affect reaction rate?",
            "Approved microscale reaction, timer, thermometer, measuring cylinders and PPE", "Temperature", "Relative rate index", "Concentration, volume, surface area and endpoint", "°C", "index",
            "Use only an approved microscale procedure under supervision; never mix unknown chemicals.",
            listOf("Prepare equal volumes and concentrations", "Equilibrate reactants to the chosen temperature", "Mix and start timing consistently", "Record endpoint time and repeat"),
            "Arrhenius-like illustrative index, not a measured rate constant.", { x -> kotlin.math.exp(0.035f * (x - 25f)) })
        "chem-solubility" -> profile("Solubility and temperature", "How does temperature affect the amount of solute that dissolves?",
            "Known solute, water, balance, thermometer, water bath and glassware", "Water temperature", "Solubility", "Solvent mass, solute identity, stirring and equilibrium time", "°C", "g/100 g water",
            "Use approved solutes and a water bath; avoid direct flame and hot glass.",
            listOf("Measure a fixed mass of water", "Set a controlled temperature", "Add weighed solute until saturation", "Record dissolved mass and repeat"),
            "Hypothetical rising curve; actual substances may show different trends.", { x -> 12f + 0.7f * x })
        "chem-electrolysis" -> profile("Electrolysis and charge", "How does current and time determine charge passed?",
            "Low-voltage DC supply, suitable electrolyte, inert electrodes and ammeter", "Time", "Charge passed", "Current, electrode area, electrolyte and temperature", "min", "C",
            "Never use mains electricity; gas products may be hazardous. Use trained supervision.",
            listOf("Identify electrolyte and electrode materials", "Set up a low-voltage circuit with current measurement", "Record current before timing", "Calculate Q = It with time converted to seconds"),
            "Charge is calculated; product identity and mass require further chemical details.", { x -> 30f * x })
        "phy-motion" -> profile("Uniformly accelerated motion", "How does displacement depend on time at constant acceleration?",
            "Dynamics trolley, runway, motion sensor or ticker timer and metre rule", "Elapsed time", "Displacement", "Initial velocity, acceleration and track gradient", "s", "m",
            "Secure the runway and keep hands clear of moving trolleys.",
            listOf("Set the runway and initial conditions", "Release the trolley consistently", "Record displacement at fixed times", "Plot displacement against time and interpret"),
            "Illustrative constant-acceleration model; initial velocity also affects displacement.", { x -> 0.08f * x + 0.006f * x * x }, 0f, 10f, 2.5f)
        "phy-elasticity" -> profile("Hooke's law", "How does extension depend on force within the elastic limit?",
            "Spring, clamp stand, mass hanger, slotted masses and ruler", "Applied force", "Extension", "Spring, initial length, temperature and reading method", "N", "cm",
            "Secure the clamp and keep feet clear of falling masses; do not exceed safe load.",
            listOf("Measure unloaded spring length", "Add small known loads", "Record length and calculate extension", "Unload gradually and check recovery"),
            "Illustrative linear region; real springs cease to obey Hooke's law beyond the limit.", { x -> 0.45f * x })
        "phy-pressure", "phy-liquids-rest" -> profile("Pressure and liquid depth", "How does depth affect hydrostatic pressure?",
            "Water column, pressure sensor or manometer, ruler and water", "Depth below surface", "Gauge pressure", "Liquid density, gravity and sensor position", "m", "kPa",
            "Use stable containers, wipe spills and keep electronic sensors dry.",
            listOf("Fill the container with test liquid", "Measure depth from the free surface", "Record pressure at several depths", "Compare graph gradient with ρg"),
            "Fresh-water model p = ρgh; excludes atmospheric pressure.", { x -> 9.81f * x })
        "phy-gas-laws", "phy-kinetic-theory" -> profile("Charles's law", "How does gas volume vary with absolute temperature at constant pressure?",
            "Gas syringe, pressure control, thermometer and water bath", "Absolute temperature", "Gas volume", "Gas amount, pressure and apparatus dead volume", "K", "cm³",
            "Use low pressure only; never heat a sealed rigid vessel.",
            listOf("Measure a fixed amount of gas in a movable syringe", "Maintain approximately constant pressure", "Change temperature using a water bath", "Record volume after thermal equilibrium"),
            "Illustrative ideal-gas proportionality at constant pressure.", { x -> 0.08f * x }, 100f, 800f, 300f)
        "phy-waves", "phy-sound-propagation", "phy-sound-characteristics" -> profile("Wave frequency and wavelength", "How does wavelength change with frequency in a fixed medium?",
            "Ripple tank or signal generator, speaker, oscilloscope and ruler", "Frequency", "Wavelength", "Medium and wave speed", "Hz", "m",
            "Keep water away from electrical equipment and use safe sound levels.",
            listOf("Keep the medium constant", "Generate waves at a measured frequency", "Measure several wavelengths and find the mean", "Repeat and compare using v = fλ"),
            "Illustrative inverse relation at constant wave speed; dispersion may change speed.", { x -> 20f / x.coerceAtLeast(1f) }, 1f, 1000f, 100f)
        "phy-current-electricity", "phy-electrical-power", "phy-cells" -> profile("Ohm's law", "How does current depend on potential difference for an ohmic conductor?",
            "Low-voltage DC supply, resistor, ammeter, voltmeter and switch", "Potential difference", "Current", "Resistor temperature, connections and meter range", "V", "A",
            "Use low voltage only; switch off before changing connections and never use mains.",
            listOf("Connect ammeter in series and voltmeter in parallel", "Begin at the lowest supply setting", "Record voltage and current pairs", "Plot I against V and assess linearity"),
            "Ideal ohmic conductor at constant temperature; heating can change resistance.", { x -> 0.02f * x })
        "chem-nuclear" -> profile("Radioactive decay and half-life", "How does the expected number of undecayed nuclei change with time?",
            "Radioactive-decay simulation, timer and data table", "Elapsed time", "Expected parent nuclei remaining", "Initial sample size and isotope", "half-lives", "% remaining",
            "This is a computer model; never obtain or handle radioactive material for this activity.",
            listOf("Set an initial population", "Choose a half-life", "Advance time in equal intervals", "Record the expected fraction remaining and plot the decay curve"),
            "Statistical expectation for a large population; individual decay events are random.", { x -> 100f * kotlin.math.exp(-0.693147f * x) })
        "chem-industry", "chem-equilibrium" -> profile("Equilibrium and industrial conditions", "How do pressure and temperature changes affect a gaseous equilibrium?",
            "Equilibrium model, temperature control and pressure display", "Pressure setting", "Qualitative ammonia-favouring index", "Reaction equation, gas composition and catalyst", "relative", "index",
            "Concept model only; no high-pressure equipment or reactive gases are used.",
            listOf("Write and balance the reversible reaction", "Count gaseous mole coefficients on each side", "Identify whether the forward reaction is exothermic", "Predict the shift for a pressure or temperature change"),
            "Qualitative Le Chatelier model, not a numerical yield prediction.", { x -> 0.5f + 0.01f * x })
        "chem-organic" -> profile("Homologous series and molecular formula", "How does carbon-chain length affect the molecular formula of an alkane?",
            "Molecular model kit, formula cards and periodic table", "Number of carbon atoms", "Number of hydrogen atoms", "Hydrocarbon family and acyclic structure", "C atoms", "H atoms",
            "Use model kits safely; small parts must be kept away from young children.",
            listOf("Select the homologous series", "Count carbon atoms in successive members", "Build or draw the structures", "Compare formula differences between adjacent members"),
            "Alkane formula model CnH2n+2; rings and unsaturation require different formulae.", { x -> 2f * x + 2f })
        "chem-pollution" -> profile("Water pollution and eutrophication", "How can nutrient enrichment affect dissolved oxygen in a water body?",
            "Water samples, approved nitrate test, dissolved-oxygen meter and sampling bottles", "Nutrient concentration", "Illustrative oxygen-stress index", "Temperature, flow, sampling depth and time", "mg L⁻¹", "index",
            "Do not sample polluted water without supervision, PPE and an approved field protocol.",
            listOf("Choose safe sampling points and label bottles", "Measure nutrient concentration and dissolved oxygen", "Record temperature and sampling conditions", "Compare sites and discuss alternative explanations"),
            "Hypothetical relationship for reasoning; real oxygen dynamics depend on many factors.", { x -> (100f - 0.7f * x).coerceAtLeast(0f) })
        "phy-friction" -> profile("Friction and normal reaction", "How does maximum static friction depend on normal reaction?",
            "Wooden block, spring balance, adjustable surface and masses", "Normal reaction", "Maximum static friction", "Surface pair, contact condition and pull direction", "N", "N",
            "Use a stable bench and small loads; keep the block away from table edges.",
            listOf("Place the block on the test surface", "Increase pulling force gradually until motion is imminent", "Record limiting friction and normal reaction", "Repeat with several normal loads"),
            "Illustrative proportional model for one fixed surface pair; coefficient varies by condition.", { x -> 0.4f * x })
        "phy-machines" -> profile("Mechanical advantage and efficiency", "How do load and effort determine mechanical advantage?",
            "Pulley or lever model, load, spring balance and metre rule", "Effort force", "Mechanical advantage", "Load, machine geometry and friction condition", "N", "ratio",
            "Secure the apparatus and keep feet clear of suspended loads.",
            listOf("Set up the machine and identify load and effort", "Measure load and effort forces", "Calculate MA = load/effort", "Measure distances moved to determine velocity ratio and efficiency"),
            "Efficiency is MA/VR × 100%; a passive real machine cannot exceed 100%.", { x -> 300f / max(x, 1f) })
        "phy-thermal-expansion" -> profile("Linear thermal expansion", "How does temperature rise affect the length of a metal rod?",
            "Expansion apparatus, metal rod, heater, thermometer and micrometer", "Temperature rise", "Length increase", "Rod material, initial length and heating method", "°C", "mm",
            "Hot metal can burn; use heat-resistant gloves and allow apparatus to cool.",
            listOf("Measure initial rod length", "Record initial temperature", "Heat gradually and measure expansion", "Compare the ratio ΔL/(L₀ΔT) across readings"),
            "Illustrative linear model for a fixed material and modest temperature range.", { x -> 0.0012f * x })
        "phy-heat-transfer", "phy-conduction" -> profile("Thermal conduction", "How does temperature difference affect heat-transfer rate through a material?",
            "Thermal conductivity apparatus, heater, thermometers and timer", "Temperature difference", "Relative heat-transfer rate", "Material, cross-sectional area, length and insulation", "°C", "index",
            "Use low-voltage heaters and avoid touching hot metal; allow equipment to cool.",
            listOf("Set up the same material and geometry", "Establish a measured temperature difference", "Record energy transfer or temperature change over time", "Repeat with controlled conditions"),
            "Simple proportional model; actual rate depends on conductivity and geometry.", { x -> 0.8f * x })
        "phy-magnetic-force" -> profile("Force on a current-carrying conductor", "How does current affect magnetic force at fixed field and geometry?",
            "Low-voltage supply, conductor, magnet, ammeter and force sensor", "Current", "Magnetic force", "Field strength, conductor length and angle", "A", "N",
            "Use low voltage and limit current to prevent conductor heating.",
            listOf("Set conductor perpendicular to a uniform field", "Measure the zero-current baseline", "Increase current in controlled steps", "Record force and reverse current to check direction"),
            "Ideal relation F = BIL sin θ at fixed field and conductor geometry.", { x -> 0.08f * x }, 0f, 10f, 2f)
        "phy-ac-circuits" -> profile("Alternating current and period", "How does frequency determine the period of an AC signal?",
            "Low-voltage signal generator and oscilloscope", "Frequency", "Period", "Waveform and measurement timebase", "Hz", "ms",
            "Use only low-voltage signal sources; never connect an oscilloscope to mains.",
            listOf("Set a sinusoidal signal at low amplitude", "Display several cycles", "Measure one or more cycle durations", "Calculate T = 1/f and compare with the display"),
            "Ideal sinusoidal signal model; distortion and instrument resolution are ignored.", { x -> 1000f / x.coerceAtLeast(1f) }, 1f, 1000f, 50f)
        "phy-modern" -> profile("Photon energy and frequency", "How does photon energy depend on electromagnetic frequency?",
            "Frequency data, calculator and electromagnetic spectrum chart", "Frequency", "Photon energy", "Planck constant and unit conversion", "10¹⁴ Hz", "10⁻¹⁹ J",
            "No ionising-radiation source is used; this is a calculation activity.",
            listOf("Identify the radiation frequency", "Convert frequency to hertz", "Apply E = hf using Planck's constant", "Compare energy across the spectrum"),
            "Energy per photon; intensity depends on photon number as well as photon energy.", { x -> 0.6626f * x })
        "phy-gravity" -> profile("Simple pendulum and gravitational acceleration", "How does pendulum length affect its period?",
            "Retort stand, string, bob, metre rule and stopwatch", "Pendulum length", "Period", "Bob mass, release angle and timing method", "m", "s",
            "Secure the stand and keep the swinging bob clear of people and fragile objects.",
            listOf("Measure length from pivot to bob centre", "Displace the bob by a small angle", "Time several complete oscillations", "Repeat and calculate the mean period"),
            "Small-angle model T = 2π√(L/g); air resistance and pivot friction are neglected.", { x -> 2f * Math.PI.toFloat() * kotlin.math.sqrt(x.coerceAtLeast(0.01f) / 9.81f) }, 0.1f, 2f, 1f)
        "phy-equilibrium" -> profile("Principle of moments", "Under what condition does a horizontal beam balance?",
            "Metre rule, pivot, slotted masses, mass hangers and balance", "Effort arm", "Balancing load moment", "Load, pivot position and force direction", "m", "N m",
            "Secure the pivot and keep feet clear of falling masses.",
            listOf("Balance the rule and locate its centre of gravity", "Place the pivot at a measured position", "Apply known loads at measured distances", "Compare clockwise and anticlockwise moments"),
            "Ideal moment relation τ = Fd for perpendicular forces; apparatus mass and friction may matter.", { x -> 10f * x })
        "phy-work-energy" -> profile("Work done by a constant force", "How does work done depend on displacement in the force direction?",
            "Dynamics trolley, spring balance, track and metre rule", "Displacement", "Work done", "Applied force and angle between force and displacement", "m", "J",
            "Use a stable track and avoid sudden pulls on the spring balance.",
            listOf("Measure the applied force", "Pull the trolley through known distances", "Keep force direction parallel to motion", "Calculate W = Fs and compare readings"),
            "Constant-force model with force parallel to displacement.", { x -> 2f * x })
        "phy-temperature" -> profile("Thermometer calibration", "How does a thermometric property vary with temperature?",
            "Laboratory thermometer or sensor, ice-water bath, warm-water bath and reference thermometer", "Reference temperature", "Thermometric reading", "Same sensor, immersion depth and equilibration time", "°C", "°C",
            "Use warm water rather than boiling water; handle glass thermometers carefully.",
            listOf("Identify the thermometric property", "Record the sensor reading in an ice-water bath", "Record readings at controlled reference temperatures", "Plot sensor reading against reference temperature"),
            "Illustrative linear calibration; real sensors may have offset and non-linearity.", { x -> 1.02f * x + 1.5f })
        "phy-heat-quantity" -> profile("Specific heat capacity", "How does supplied energy relate to temperature rise?",
            "Heater, insulated metal block, thermometer, balance, joulemeter and timer", "Energy supplied", "Temperature rise", "Block mass, material and heat loss", "J", "°C",
            "Use low-voltage equipment; hot blocks can burn and must cool before handling.",
            listOf("Measure the block mass", "Record initial temperature", "Supply measured electrical energy", "Record temperature rise and estimate c = E/(mΔT)"),
            "Ideal energy balance; heat losses and heater efficiency are neglected.", { x -> 0.025f * x })
        "phy-vapours" -> profile("Heating and cooling during change of state", "How does temperature change as a substance is heated through a phase change?",
            "Approved safe substance, thermometer, controlled heater, timer and heat-resistant vessel", "Heating time", "Temperature", "Substance mass, heating power and pressure", "min", "°C",
            "Use only teacher-approved substances and heating equipment; avoid burns and fumes.",
            listOf("Record the starting temperature", "Heat at a controlled rate", "Record temperature at equal time intervals", "Identify any plateau and explain latent heat"),
            "Schematic heating curve; real plateaux depend on purity, pressure and heating rate.", { x -> if (x < 35f) 20f + 1.4f * x else if (x <= 65f) 69f else 69f + 1.4f * (x - 65f) })
        "phy-light-energy" -> profile("Photon energy and electromagnetic radiation", "How does photon energy depend on radiation frequency?",
            "Spectrum reference chart, frequency data and calculator", "Frequency", "Photon energy", "Planck constant and unit conversion", "10¹⁴ Hz", "10⁻¹⁹ J",
            "No ionising-radiation source is used; this is a calculation activity.",
            listOf("Identify the radiation frequency", "Convert frequency to hertz", "Apply E = hf", "Compare energy per photon across spectral regions"),
            "Energy per photon; intensity also depends on photon number.", { x -> 0.6626f * x })
        "phy-optical-instruments" -> profile("Convex lens focal length", "How can the focal length of a converging lens be estimated?",
            "Convex lens, lens holder, illuminated object, screen and metre rule", "Object distance", "Image distance", "Same lens, alignment and object height", "cm", "cm",
            "Never look directly at the Sun through a lens; secure the lens and screen.",
            listOf("Align object, lens and screen on one optical axis", "Form a sharp real image", "Measure u and v from the lens centre", "Calculate f using 1/f = 1/u + 1/v and repeat"),
            "Thin-lens approximation; lens thickness and measurement uncertainty are ignored. Object distance is kept greater than focal length.", { x -> 20f * x / (x - 20f) }, 30f, 100f, 50f)
        "phy-dispersion" -> profile("Dispersion of white light", "How does a prism separate white light into component colours?",
            "Glass prism, narrow light source, screen and angle-measuring scale", "Wavelength", "Relative refractive index", "Prism material, prism angle and temperature", "nm", "index",
            "Do not look directly into intense light sources; secure the prism on a stable surface.",
            listOf("Direct a narrow beam into the prism", "Observe the emergent spectrum on a screen", "Identify the order of colours", "Compare deviation for different wavelengths"),
            "Qualitative dispersion model; refractive index varies with wavelength and material.", { x -> 1.52f + 0.00008f * (700f - x) }, 400f, 700f, 550f)
        "phy-electrostatics" -> profile("Electrostatic force and separation", "How does electrostatic force vary with distance for fixed charges?",
            "Electrostatic force model, charge values and distance scale", "Charge separation", "Relative force", "Charge magnitudes and medium", "relative distance", "relative force",
            "Use a computer model only; do not create high-voltage static discharges.",
            listOf("Set the two charge magnitudes", "Choose like or unlike charge signs", "Change separation only", "Compare force magnitude and direction"),
            "Inverse-square Coulomb model F ∝ 1/r²; point charges and a uniform medium are assumed.", { x -> 100f / (x.coerceAtLeast(1f) * x.coerceAtLeast(1f)) }, 1f, 100f, 10f)
        "phy-magnetism" -> profile("Magnetic field mapping", "How does the direction of a magnetic field vary around a bar magnet?",
            "Bar magnet, plotting compass, paper and field-line recording sheet", "Polar position around an ideal dipole", "Magnetic field direction", "Same magnet axis and observation plane", "°", "°",
            "Keep magnets away from sensitive electronics and medical devices.",
            listOf("Place the magnet under the paper", "Mark the compass needle direction at several positions", "Move the compass in small steps", "Join direction marks to sketch field lines"),
            "Ideal point-dipole field direction in the equatorial plane; a real bar magnet has finite geometry and the model does not calculate field strength.", { x ->
                val theta = Math.toRadians(x.toDouble())
                val bx = 3.0 * kotlin.math.cos(theta) * kotlin.math.cos(theta) - 1.0
                val by = 3.0 * kotlin.math.sin(theta) * kotlin.math.cos(theta)
                ((Math.toDegrees(kotlin.math.atan2(by, bx)) + 360.0) % 360.0).toFloat()
            }, 0f, 360f, 90f)
        "phy-electronics" -> profile("Semiconductor diode characteristic", "How does current through a diode change with forward voltage?",
            "Low-voltage variable supply, diode, series resistor, ammeter and voltmeter", "Forward voltage", "Diode current", "Diode temperature, series resistor and circuit connections", "V", "mA",
            "Use a current-limiting resistor and low voltage; observe diode polarity.",
            listOf("Identify anode and cathode", "Connect diode and current-limiting resistor in series", "Measure diode voltage and current", "Increase voltage gradually and plot the I–V curve"),
            "Simplified forward-bias exponential model with a current scale; real diode behaviour depends on type and temperature.", { x -> (0.05f * (kotlin.math.exp(5f * x) - 1f)).coerceAtLeast(0f) }, 0f, 1f, 0.5f)
        "bio-ecology-factors" -> profile("Abiotic factors and organism distribution", "How does an environmental factor relate to the abundance of a chosen organism?",
            "Light meter or thermometer, soil-moisture probe, quadrat, transect tape and recording sheet", "Measured abiotic factor", "Organism abundance or percentage cover", "Sampling area, quadrat size, time and identification method",
            "relative units", "organisms m⁻²",
            "Use safe field sites, avoid disturbing habitats and do not sample protected species.",
            listOf("Choose one abiotic factor and a measurable response", "Select a representative sampling area", "Measure the factor at several sampling points", "Use quadrats to record abundance at each point", "Plot abundance against the measured factor"),
            "Illustrative tolerance response only; real distributions may be affected by several interacting factors.", { x -> 100f * kotlin.math.exp(-((x - 50f) * (x - 50f)) / 700f) }, 0f, 100f, 50f)
        "bio-habitats" -> profile("Habitat transect and microhabitat survey", "How does the distribution of a plant species change along a habitat gradient?",
            "Transect tape, quadrat, identification guide, thermometer and light meter", "Distance along transect", "Plant abundance or percentage cover", "Transect route, quadrat size, sampling interval and identification criteria",
            "m", "% cover",
            "Stay on approved routes, avoid damaging vegetation and do not handle unknown organisms.",
            listOf("Lay a transect across the habitat gradient", "Place quadrats at fixed intervals", "Identify and record the target species", "Measure one or more abiotic conditions", "Compare abundance with distance and conditions"),
            "Illustrative gradient model; a real transect must be sampled and repeated in the field.", { x -> (80f - 0.8f * kotlin.math.abs(x - 50f)).coerceAtLeast(0f) }, 0f, 100f, 50f)
        "bio-biomes" -> profile("Rainfall and vegetation structure", "How can annual rainfall be compared with vegetation cover across ecological zones?",
            "Climate data table, rain gauge records, vegetation survey and map", "Annual rainfall", "Relative vegetation-cover index", "Data period, altitude band, survey method and land-use category",
            "mm year⁻¹", "index",
            "Use published climate data or approved field records; do not enter private land without permission.",
            listOf("Select comparable ecological locations", "Obtain annual rainfall values for the same period", "Record vegetation structure or cover", "Plot vegetation measure against rainfall", "Discuss altitude, soil and human disturbance as confounding factors"),
            "A deliberately simplified illustrative trend; rainfall alone does not determine biome or vegetation.", { x -> (x * 0.04f).coerceIn(0f, 100f) }, 0f, 2000f, 900f)
        "bio-evolution-theory" -> profile("Natural selection using a model population", "How can repeated selection change the frequency of a heritable phenotype?",
            "Two-colour paper or bead models, tray, forceps, timer and population tally sheet", "Number of selection rounds", "Frequency of the favoured phenotype", "Starting frequency, selection rule, sample size and number selected per round",
            "rounds", "%",
            "Use harmless model beads or paper shapes; do not capture or harm living organisms.",
            listOf("Create a mixed model population with two heritable phenotypes", "Define a consistent selection pressure", "Remove or retain individuals using the stated rule", "Count phenotype frequencies after each round", "Repeat and compare the trend across replicate populations"),
            "Illustrative selection curve, not a prediction for a named species; mutation, migration, drift and changing environments are omitted.", { x -> 100f / (1f + kotlin.math.exp(-0.25f * (x - 10f))) }, 0f, 20f, 5f)
        "chem-acids-bases" -> profile("Acid–base titration", "How does mean titre determine the concentration of an unknown acid in a 1:1 reaction?",
            "Burette, pipette, conical flask, indicator and standard solution", "Mean titre", "Unknown analyte concentration", "Standard concentration, aliquot volume and reaction stoichiometry", "cm³", "mol dm⁻³",
            "Wear goggles, use a pipette filler, clamp the burette securely and follow approved acid/alkali handling procedures.",
            listOf("Rinse and fill the burette with standard solution", "Pipette a measured aliquot into a conical flask", "Add a suitable indicator", "Titrate to the endpoint and repeat for concordant titres"),
            "Illustrative 1:1 calculation using 0.100 mol dm⁻³ titrant and 25.0 cm³ analyte; actual indicator and equation depend on the reagents.", { x -> 0.1f * x / 25f }, 5f, 40f, 25f)
        "chem-redox" -> profile("Redox titration stoichiometry", "How does permanganate titre determine iron(II) concentration in acid solution?",
            "Burette, pipette, conical flask, acidified permanganate and iron(II) sample", "Mean permanganate titre", "Iron(II) concentration", "Permanganate concentration, aliquot and balanced equation", "cm³", "mol dm⁻³",
            "Wear goggles and use the prescribed dilute acid; follow the teacher-approved redox practical method.",
            listOf("Pipette a measured iron(II) aliquot", "Fill the burette with standard permanganate", "Titrate to a persistent pale-pink endpoint", "Repeat and use concordant titres"),
            "Specific acidic permanganate reaction with 1 mol MnO₄⁻ : 5 mol Fe²⁺; model uses 0.020 mol dm⁻³ titrant and 25.0 cm³ aliquot.", { x -> 5f * 0.02f * x / 25f }, 5f, 40f, 20f)
        "chem-combination" -> profile("Mole ratios and limiting reactants", "How does the amount of hydrogen limit water formation when oxygen is in excess?",
            "Balanced equation cards, molecular models and calculator", "Hydrogen available", "Water formed", "Balanced equation and excess oxygen availability", "mol", "mol",
            "This is a calculation/model activity; no gas reaction is carried out.",
            listOf("Write 2H₂ + O₂ → 2H₂O", "Identify the coefficient ratio", "Convert known amounts to moles", "Identify the limiting reactant and calculate product amount"),
            "Ideal stoichiometric model with oxygen fixed in excess; assumes complete reaction.", { x -> x }, 0f, 10f, 2f)
        "chem-energy" -> profile("Calorimetry and heat change", "How does temperature rise determine heat gained by a known mass of water?",
            "Insulated calorimeter, thermometer, balance and heater or approved reaction system", "Temperature rise", "Heat gained", "Water mass, specific heat capacity and heat losses", "K", "J",
            "Use an insulated vessel and safe low-voltage heater; hot solutions require eye protection and supervision.",
            listOf("Measure the water mass", "Record initial temperature", "Supply a known energy or perform the approved reaction", "Record final temperature and calculate Q = mcΔT"),
            "Water-only calculation for 100 g and c = 4180 J kg⁻¹ K⁻¹; calorimeter heat capacity and losses are omitted.", { x -> 418f * x }, 0f, 50f, 10f)
        "chem-kinetic-gas" -> profile("Gas laws and absolute temperature", "How does gas pressure vary with absolute temperature at fixed volume?",
            "Gas pressure sensor, thermometer and sealed low-pressure rigid vessel", "Absolute temperature", "Pressure", "Gas amount, vessel volume and leak tightness", "K", "relative kPa",
            "Never heat a sealed rigid vessel in a practical classroom; this is a low-pressure model only.",
            listOf("Set a fixed gas amount and volume", "Record pressure at a known absolute temperature", "Change temperature in controlled increments", "Plot pressure against kelvin temperature"),
            "Ideal-gas proportionality at fixed volume; displayed pressure is a scaled model index.", { x -> 0.01f * x }, 100f, 800f, 300f)
        "phy-measurements" -> profile("Instrument resolution and uncertainty", "How does the smallest scale division affect the estimated reading uncertainty?",
            "Metre rule, vernier caliper, micrometer screw gauge and repeated-reading sheet", "Smallest scale division", "Estimated reading uncertainty", "Same instrument type, object and reading method",
            "mm", "mm",
            "Check instrument zero before use; handle the micrometer ratchet gently and avoid overtightening.",
            listOf("Identify the instrument and its least count", "Check for zero error", "Measure the same object several times", "Estimate uncertainty from resolution and repeatability", "Report a value with a sensible uncertainty"),
            "Half a scale division is a common analogue estimate, not a universal rule for every instrument or measurement.", { x -> x / 2f }, 0.01f, 1f, 0.1f)
        "phy-vectors" -> profile("Resultant of two forces", "How does the angle between two equal forces affect their resultant?",
            "Force board, pulleys, slotted masses, strings and vector scale diagram", "Angle between two forces", "Resultant force magnitude", "Equal force magnitudes and calibrated masses",
            "degrees", "N",
            "Secure the force board and masses; keep feet clear of suspended loads.",
            listOf("Set two equal known forces", "Change the angle between their directions", "Determine the resultant using the force board", "Calculate the resultant using the cosine rule", "Compare graphical and calculated results"),
            "Ideal two-force model with equal 10 N forces; friction and pulley mass are neglected.", { x -> kotlin.math.sqrt(200f + 200f * kotlin.math.cos(Math.toRadians(x.toDouble()).toFloat())) }, 0f, 180f, 90f)
        "phy-change-state" -> profile("Latent heat of fusion", "How does the mass of ice affect the energy required to melt it at its melting point?",
            "Crushed ice, insulated calorimeter, balance, thermometer and electrical heater or approved energy source", "Mass of ice", "Energy required for melting", "Initial temperature, pressure, insulation and ice condition",
            "kg", "J",
            "Keep electrical connections dry; use low-voltage equipment and handle meltwater safely.",
            listOf("Prepare ice at its melting point", "Measure the mass of ice", "Supply or estimate the energy needed for melting", "Record the mass melted", "Use Q = mL to determine specific latent heat"),
            "Uses the accepted approximate latent heat of fusion of pure ice; heat exchange with the surroundings is neglected.", { x -> 334000f * x }, 0.1f, 2f, 0.5f)
        "phy-reflection" -> profile("Law of reflection", "How does the angle of reflection compare with the angle of incidence?",
            "Plane mirror, ray box, paper, ruler and protractor", "Angle of incidence", "Angle of reflection", "Mirror position, normal line and ray-box alignment",
            "degrees", "degrees",
            "Do not look directly into a bright ray source; use a low-intensity ray box.",
            listOf("Draw a normal to the mirror surface", "Direct a narrow incident ray at the mirror", "Mark the reflected ray", "Measure both angles from the normal", "Repeat for several incident angles"),
            "Ideal plane-mirror model; small errors arise from ray width and alignment.", { x -> x }, 0f, 80f, 30f)
        "phy-refraction" -> profile("Refraction through a transparent block", "How does the angle of refraction depend on the angle of incidence?",
            "Rectangular glass block, ray box, paper, ruler and protractor", "Angle of incidence", "Angle of refraction", "Block material, wavelength and normal alignment",
            "degrees", "degrees",
            "Avoid direct viewing of bright beams and handle glass blocks carefully.",
            listOf("Trace the outline of the block", "Draw a normal at the point of incidence", "Direct a ray at a measured incident angle", "Mark the emergent ray and trace the path", "Measure the refracted angle and test Snell's law"),
            "Illustrative air-to-glass model with refractive index 1.50; dispersion and surface imperfections are ignored.", { x -> Math.toDegrees(kotlin.math.asin((kotlin.math.sin(Math.toRadians(x.toDouble())) / 1.5).coerceIn(-1.0, 1.0))).toFloat() }, 0f, 80f, 40f)
        "phy-capacitors" -> profile("Charging a capacitor", "How does capacitor charge change with time in a simple RC circuit?",
            "Capacitor, resistor, low-voltage DC supply, switch, voltmeter and stopwatch", "Time after closing the switch", "Charge fraction or capacitor voltage", "Resistance, capacitance, supply voltage and initial charge",
            "s", "% of final value",
            "Use a low-voltage supply, observe capacitor polarity and discharge the capacitor safely before handling.",
            listOf("Connect the resistor and capacitor in series", "Record the initial voltage", "Close the switch and start timing", "Record capacitor voltage at fixed intervals", "Plot voltage or charge against time"),
            "First-order RC charging model with a fixed 10 s time constant; leakage and component tolerances are omitted.", { x -> 100f * (1f - kotlin.math.exp(-x / 10f)) }, 0f, 50f, 10f)
        "phy-induction" -> profile("Electromagnetic induction", "How does the rate of change of magnetic flux affect induced emf?",
            "Coil, bar magnet, galvanometer or voltage sensor and motion guide", "Rate of change of magnetic flux linkage", "Magnitude of induced emf", "Coil turns, magnet orientation and circuit resistance",
            "Wb s⁻¹", "V",
            "Secure the magnet and coil; keep strong magnets away from sensitive electronics and medical devices.",
            listOf("Connect the coil to a sensitive galvanometer", "Move the magnet through the coil at controlled speeds", "Record deflection or induced voltage", "Repeat with different speeds and coil turns", "Relate induced emf to the rate of flux-linkage change"),
            "Faraday-law proportionality model; the sign gives direction and is not represented by the magnitude-only response.", { x -> 100f * x }, 0f, 0.05f, 0.02f)
        "chem-atomic-bonding" -> profile("Ionic formula and charge balance", "How do ionic charges determine the simplest neutral formula unit?",
            "Positive and negative ion cards, charge counters and formula worksheet", "Magnitude of cation charge", "Number of anions in the simplest formula unit", "Anion charge fixed at 2− and lowest whole-number ratio",
            "charge units", "ions",
            "A symbolic model only; do not mix unknown chemicals or infer bonding from formula alone.",
            listOf("Write the cation and anion charges", "Find the smallest whole-number ratio that balances total charge", "Write the formula unit with correct subscripts", "Check that the net charge is zero", "Repeat with different ion charges"),
            "Discrete charge-balance model for a fixed 2− anion; covalent bonding and polyatomic-ion structure are not represented.", { x -> if (x.roundToInt() == 2) 1f else 2f }, 1f, 3f, 1f)
        "chem-nonmetals" -> profile("Qualitative gas identification", "How can independent chemical tests distinguish common laboratory gases?",
            "Gas jars, damp litmus, glowing and lighted splints, limewater and approved small gas samples", "Number of independent diagnostic tests", "Evidence-strength index", "Sample identity, test conditions and reagent freshness",
            "tests", "index",
            "Use only teacher-prepared small samples in a well-ventilated laboratory. Never generate or inhale toxic gases; chlorine and sulfur dioxide require specialist controls.",
            listOf("Observe the sample without directly inhaling it", "Select an appropriate confirmatory test", "Record the exact observation", "Compare the observation with the expected gas-test table", "Use a second independent test where safe and appropriate"),
            "Evidence index is a teaching aid; real gas identification depends on specific observations and validated test procedures.", { x -> 25f * x }, 1f, 4f, 2f)
        "chem-metals" -> profile("Metal displacement and reactivity", "How does the relative reactivity of a solid metal affect displacement of a less reactive metal ion?",
            "Approved metal strips, dilute salt solutions, test tubes, forceps and observation sheet", "Relative reactivity rank of the solid metal", "Displacement tendency index", "Ion concentration, solution volume, temperature and surface condition",
            "rank", "index",
            "Wear goggles and gloves, use only dilute teacher-approved solutions and dispose of metal-ion waste correctly.",
            listOf("Clean approved metal strips", "Place each metal in the specified salt solution", "Observe colour change or metal deposition", "Record which combinations react", "Arrange the tested metals in a justified reactivity order"),
            "Ordinal teaching index only; actual displacement depends on the redox couple, concentration, passivation and reaction conditions.", { x -> 20f * x }, 1f, 5f, 3f)
        "chem-astro" -> profile("Emission spectra and photon energy", "How does the wavelength of an emitted spectral line relate to photon energy?",
            "Diffraction grating spectroscope, approved discharge-tube spectra or calibrated spectral images", "Emission wavelength", "Photon energy", "Spectroscope calibration, light source and spectral-line identification",
            "nm", "eV",
            "Use enclosed low-voltage discharge tubes under supervision. Never look directly at the Sun or an intense light source.",
            listOf("Calibrate the wavelength scale", "Observe or inspect the emission spectrum", "Identify a characteristic line", "Record its wavelength", "Calculate photon energy using E = hc/λ and compare line patterns"),
            "Photon-energy calculation uses E = hc/λ; line positions identify transitions but do not alone determine every property of a star.", { x -> 1239.84f / x }, 400f, 700f, 589f)
        else -> null
    }
}

@Composable
fun GuidedInvestigationActivity(topicId: String) {
    val profile = remember(topicId) { InvestigationProfiles.find(topicId) } ?: return
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val noteId = "guided-investigation-" + topicId
    val restored = remember(topicId) {
        runCatching { JSONObject(database.getNoteBody(noteId) ?: "{}") }.getOrElse { JSONObject() }
    }
    var stage by remember(topicId) { mutableIntStateOf(restored.optInt("stage", 0).coerceIn(0, 4)) }
    var x by remember(topicId) { mutableFloatStateOf(restored.optDouble("x", profile.initialX.toDouble()).toFloat().coerceIn(profile.minX, profile.maxX)) }
    var prediction by remember(topicId) { mutableStateOf(restored.optString("prediction", "")) }
    var saved by remember(topicId) { mutableStateOf(true) }
    val readings = remember(topicId) {
        mutableStateListOf<Pair<Float, Float>>().apply {
            val rows = restored.optJSONArray("readings")
            if (rows != null) for (index in 0 until rows.length()) {
                val row = rows.optJSONObject(index) ?: continue
                val readingX = row.optDouble("x", Double.NaN).toFloat()
                val readingY = row.optDouble("y", Double.NaN).toFloat()
                if (readingX.isFinite() && readingY.isFinite()) add(readingX to readingY)
            }
        }
    }
    val completedSteps = remember(topicId) {
        mutableStateListOf<Int>().apply {
            val steps = restored.optJSONArray("completedSteps")
            if (steps != null) for (index in 0 until steps.length()) {
                val step = steps.optInt(index, -1)
                if (step in profile.method.indices) add(step)
            }
        }
    }
    fun saveLabRecord() {
        val rows = JSONArray()
        readings.forEach { reading -> rows.put(JSONObject().put("x", reading.first).put("y", reading.second)) }
        val steps = JSONArray()
        completedSteps.forEach { steps.put(it) }
        val body = JSONObject()
            .put("stage", stage)
            .put("x", x.toDouble())
            .put("prediction", prediction)
            .put("readings", rows)
            .put("completedSteps", steps)
        database.saveNote(noteId, topicId, profile.title + " investigation", body.toString())
        saved = true
    }
    val y = profile.response(x).coerceAtLeast(0f)
    val analysedPoints = remember(readings.size, readings.toList()) {
        readings.groupBy { it.first }
            .map { (setting, repeats) ->
                BiologyExperimentPoint(setting.toDouble(), repeats.map { it.second.toDouble() }.average(), repeats.size)
            }
            .sortedBy { it.independentValue }
    }
    val observedTrend = remember(analysedPoints) {
        if (analysedPoints.size >= 2) BiologyExperimentAnalysis.describeTrend(analysedPoints) else null
    }
    val stages = listOf("Brief", "Plan", "Run", "Analyse", "Evaluate")

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Guided virtual investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(profile.title, style = MaterialTheme.typography.titleMedium)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                stages.forEachIndexed { i, name -> FilterChip(stage == i, { stage = i; saveLabRecord() }, { Text(name) }) }
            }
            HorizontalDivider()
            when (stage) {
                0 -> {
                    Text("Investigative question", fontWeight = FontWeight.Bold); Text(profile.question)
                    Text("Apparatus and materials", fontWeight = FontWeight.Bold); Text(profile.apparatus)
                    InvestigationApparatusIllustration(topicId)
                    Text("Safety and precautions", fontWeight = FontWeight.Bold); Text(profile.safety, color = MaterialTheme.colorScheme.error)
                    Text("Model scope", fontWeight = FontWeight.Bold); Text(profile.limitation)
                }
                1 -> {
                    Text("Independent variable: " + profile.independent)
                    Text("Dependent variable: " + profile.dependent)
                    Text("Controlled variables: " + profile.controls)
                    OutlinedTextField(prediction, { prediction = it; saved = false }, label = { Text("Predict the trend and explain why") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    Text("Method checklist", fontWeight = FontWeight.Bold)
                    profile.method.forEachIndexed { i, item ->
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            Checkbox(i in completedSteps, { checked -> if (checked) completedSteps.add(i) else completedSteps.remove(i); saved = false })
                            Text(item)
                        }
                    }
                    Text("Steps reviewed: " + completedSteps.size + "/" + profile.method.size)
                }
                2 -> {
                    Text(profile.independent + ": " + "%.1f".format(x) + " " + profile.xUnit)
                    Slider(x, { x = it; saved = false }, valueRange = profile.minX..profile.maxX)
                    Text(profile.dependent + ": " + "%.2f".format(y) + " " + profile.yUnit, style = MaterialTheme.typography.headlineSmall)
                    Text("Select a setting and record the model output. Repeat at different settings.")
                    Button(onClick = { readings.add(x to y); saveLabRecord() }, modifier = Modifier.fillMaxWidth()) { Text("Record observation") }
                    if (readings.isNotEmpty()) {
                        Text("Readings recorded: " + readings.size)
                        readings.takeLast(6).forEachIndexed { i, pair ->
                            Text((i + 1).toString() + ". " + "%.1f".format(pair.first) + " " + profile.xUnit + " → " + "%.2f".format(pair.second) + " " + profile.yUnit)
                        }
                        TextButton(onClick = { readings.clear(); saveLabRecord() }) { Text("Clear readings") }
                    }
                }
                3 -> {
                    Text("Observation table", fontWeight = FontWeight.Bold)
                    if (readings.isEmpty()) Text("Return to Run and record readings first.")
                    else {
                        Text(profile.independent + " (" + profile.xUnit + ")     " + profile.dependent + " (" + profile.yUnit + ")", fontWeight = FontWeight.SemiBold)
                        readings.forEach { pair -> Text("%.2f".format(pair.first) + "                         " + "%.2f".format(pair.second)) }
                        readings.groupBy { it.first }.forEach { (setting, repeats) ->
                            if (repeats.size > 1) {
                                val mean = repeats.map { it.second.toDouble() }.average()
                                Text("Mean of ${repeats.size} repeats at " + "%.2f".format(setting) + " " + profile.xUnit + ": " + "%.2f".format(mean) + " " + profile.yUnit)
                            }
                            if (repeats.size >= 5) {
                                val unusual = BiologyExperimentAnalysis.possibleOutliers(repeats.joinToString(",") { it.second.toString() })
                                if (unusual.isNotEmpty()) {
                                    Text("Review possible unusual reading(s) at " + "%.2f".format(setting) + " " + profile.xUnit + ": " + unusual.joinToString(), color = MaterialTheme.colorScheme.tertiary)
                                    Text("These values are flagged for investigation, not deleted. Check the original observation and method before deciding what to do.", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                        if (observedTrend != null) {
                            Text("Observed pattern", fontWeight = FontWeight.Bold)
                            Text(observedTrend)
                            Text("This describes the recorded model results; it does not by itself prove causation.")
                        }
                        Text("Horizontal axis: " + profile.independent + " (" + profile.xUnit + ")")
                        Text("Vertical axis: " + profile.dependent + " (" + profile.yUnit + ")")
                        Canvas(Modifier.fillMaxWidth().height(190.dp)) {
                            val left = 34f
                            val right = size.width - 10f
                            val top = 10f
                            val bottom = size.height - 25f
                            drawLine(Color.Gray, Offset(left, bottom), Offset(right, bottom), 2f)
                            drawLine(Color.Gray, Offset(left, top), Offset(left, bottom), 2f)
                            val minX = analysedPoints.minOf { it.independentValue }.toFloat()
                            val maxX = analysedPoints.maxOf { it.independentValue }.toFloat()
                            val minY = analysedPoints.minOf { it.meanResult }.toFloat()
                            val maxY = analysedPoints.maxOf { it.meanResult }.toFloat()
                            val xSpan = (maxX - minX).takeIf { it > 0f } ?: 1f
                            val ySpan = (maxY - minY).takeIf { it > 0f } ?: 1f
                            val pts = analysedPoints.map { point ->
                                Offset(
                                    left + (point.independentValue.toFloat() - minX) / xSpan * (right - left),
                                    bottom - (point.meanResult.toFloat() - minY) / ySpan * (bottom - top)
                                )
                            }
                            pts.forEach { drawCircle(Color(0xFF1565C0), 6f, it) }
                            pts.zipWithNext().forEach { pair ->
                                drawLine(Color(0xFF1565C0), pair.first, pair.second, 2.5f)
                            }
                        }
                        Text("Graph plots the mean at each setting; repeated observations remain visible in the table above.", style = MaterialTheme.typography.bodySmall)
                    }
                }
                else -> {
                    Text("Conclusion and evaluation", fontWeight = FontWeight.Bold)
                    if (prediction.isNotBlank()) Text("Your recorded prediction: " + prediction)
                    if (readings.size >= 2) {
                        val a = readings.first(); val b = readings.last()
                        val trend = if (b.second > a.second) "increased" else if (b.second < a.second) "decreased" else "did not change"
                        Text("The model response " + trend + " from " + "%.2f".format(a.second) + " to " + "%.2f".format(b.second) + " " + profile.yUnit + " across the first and last recorded settings.")
                    } else Text("Record at least two readings before writing an evidence-based conclusion.")
                    Text("Write a conclusion that answers the question, quotes readings and states whether the prediction was supported.")
                    Text("Evaluation: identify random and systematic errors; explain why repeats help; identify a control variable that may drift; propose one improvement.")
                    Text("All outputs are simulated teaching values, not measurements from a physical laboratory.", style = MaterialTheme.typography.bodySmall)
                }
            }
            Button(onClick = { saveLabRecord() }, enabled = !saved, modifier = Modifier.fillMaxWidth()) { Text("Save lab notebook") }
            Text(if (saved) "Lab notebook saved on this device." else "Unsaved changes. Save your prediction, method review and current settings before leaving.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(onClick = { stage = (stage - 1).coerceAtLeast(0); saveLabRecord() }, enabled = stage > 0) { Text("Previous") }
                Button(onClick = { stage = (stage + 1).coerceAtMost(stages.lastIndex); saveLabRecord() }, enabled = stage < stages.lastIndex) { Text("Next stage") }
            }
        }
    }
}
