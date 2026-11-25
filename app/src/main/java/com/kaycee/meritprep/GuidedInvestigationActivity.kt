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
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max

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
        "bio-ecology-factors", "bio-habitats", "bio-biomes", "bio-environment" -> profile("Environmental gradient and distribution", "How does an environmental gradient relate to organism abundance?",
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
        "phy-change-state", "phy-vapours" -> profile("Heating and cooling during change of state", "How does temperature change as a substance is heated through a phase change?",
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
        else -> null
    }
}

@Composable
fun GuidedInvestigationActivity(topicId: String) {
    val profile = remember(topicId) { InvestigationProfiles.find(topicId) } ?: return
    var stage by remember(topicId) { mutableIntStateOf(0) }
    var x by remember(topicId) { mutableFloatStateOf(profile.initialX) }
    var prediction by remember(topicId) { mutableStateOf("") }
    val readings = remember(topicId) { mutableStateListOf<Pair<Float, Float>>() }
    val completedSteps = remember(topicId) { mutableStateListOf<Int>() }
    val y = profile.response(x).coerceAtLeast(0f)
    val stages = listOf("Brief", "Plan", "Run", "Analyse", "Evaluate")

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("Guided virtual investigation", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(profile.title, style = MaterialTheme.typography.titleMedium)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                stages.forEachIndexed { i, name -> FilterChip(stage == i, { stage = i }, { Text(name) }) }
            }
            HorizontalDivider()
            when (stage) {
                0 -> {
                    Text("Investigative question", fontWeight = FontWeight.Bold); Text(profile.question)
                    Text("Apparatus and materials", fontWeight = FontWeight.Bold); Text(profile.apparatus)
                    Text("Safety and precautions", fontWeight = FontWeight.Bold); Text(profile.safety, color = MaterialTheme.colorScheme.error)
                    Text("Model scope", fontWeight = FontWeight.Bold); Text(profile.limitation)
                }
                1 -> {
                    Text("Independent variable: " + profile.independent)
                    Text("Dependent variable: " + profile.dependent)
                    Text("Controlled variables: " + profile.controls)
                    OutlinedTextField(prediction, { prediction = it }, label = { Text("Predict the trend and explain why") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                    Text("Method checklist", fontWeight = FontWeight.Bold)
                    profile.method.forEachIndexed { i, item ->
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            Checkbox(i in completedSteps, { checked -> if (checked) completedSteps.add(i) else completedSteps.remove(i) })
                            Text(item)
                        }
                    }
                    Text("Steps reviewed: " + completedSteps.size + "/" + profile.method.size)
                }
                2 -> {
                    Text(profile.independent + ": " + "%.1f".format(x) + " " + profile.xUnit)
                    Slider(x, { x = it }, valueRange = profile.minX..profile.maxX)
                    Text(profile.dependent + ": " + "%.2f".format(y) + " " + profile.yUnit, style = MaterialTheme.typography.headlineSmall)
                    Text("Select a setting and record the model output. Repeat at different settings.")
                    Button(onClick = { readings.add(x to y) }, modifier = Modifier.fillMaxWidth()) { Text("Record observation") }
                    if (readings.isNotEmpty()) {
                        Text("Readings recorded: " + readings.size)
                        readings.takeLast(6).forEachIndexed { i, pair ->
                            Text((i + 1).toString() + ". " + "%.1f".format(pair.first) + " " + profile.xUnit + " → " + "%.2f".format(pair.second) + " " + profile.yUnit)
                        }
                        TextButton(onClick = { readings.clear() }) { Text("Clear readings") }
                    }
                }
                3 -> {
                    Text("Observation table", fontWeight = FontWeight.Bold)
                    if (readings.isEmpty()) Text("Return to Run and record readings first.")
                    else {
                        Text(profile.independent + " (" + profile.xUnit + ")     " + profile.dependent + " (" + profile.yUnit + ")", fontWeight = FontWeight.SemiBold)
                        readings.forEach { pair -> Text("%.2f".format(pair.first) + "                         " + "%.2f".format(pair.second)) }
                        if (readings.size >= 2) {
                            val first = readings.first(); val last = readings.last()
                            val gradient = if (abs(last.first - first.first) > 0.0001f) (last.second - first.second) / (last.first - first.first) else Float.NaN
                            Text("Endpoint gradient: " + if (gradient.isFinite()) "%.4f".format(gradient) + " " + profile.yUnit + "/" + profile.xUnit else "undefined: identical x values")
                            Text("Explain the gradient in the context of the investigation.")
                        }
                        Canvas(Modifier.fillMaxWidth().height(170.dp)) {
                            drawLine(Color.Gray, Offset(24f, size.height - 16f), Offset(size.width - 8f, size.height - 16f), 2f)
                            drawLine(Color.Gray, Offset(24f, 8f), Offset(24f, size.height - 16f), 2f)
                            val maxX = max(1f, readings.maxOf { it.first }); val maxY = max(1f, readings.maxOf { it.second })
                            val pts = readings.map { Offset(24f + it.first / maxX * (size.width - 36f), size.height - 16f - it.second / maxY * (size.height - 28f)) }
                            pts.forEach { drawCircle(Color(0xFF1565C0), 5f, it) }
                            pts.zipWithNext().forEach { pair -> drawLine(Color(0xFF1565C0), pair.first, pair.second, 2f) }
                        }
                    }
                }
                else -> {
                    Text("Conclusion and evaluation", fontWeight = FontWeight.Bold)
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
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedButton(onClick = { stage = (stage - 1).coerceAtLeast(0) }, enabled = stage > 0) { Text("Previous") }
                Button(onClick = { stage = (stage + 1).coerceAtMost(stages.lastIndex) }, enabled = stage < stages.lastIndex) { Text("Next stage") }
            }
        }
    }
}
