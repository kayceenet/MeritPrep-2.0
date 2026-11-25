package com.kaycee.meritprep

enum class ScienceMediaType { ANIMATION, INTERACTIVE_SIMULATION, VIRTUAL_LAB, VIDEO, INTERACTIVE_DIAGRAM }
enum class MediaApprovalStatus { EXTERNAL_CANDIDATE, RIGHTS_REVIEW_REQUIRED }

data class ScienceMediaResource(
    val id: String,
    val title: String,
    val subjectCode: String,
    val topicId: String,
    val mediaType: ScienceMediaType,
    val provider: String,
    val summary: String,
    val sourceUrl: String,
    val licenceName: String,
    val licenceUrl: String,
    val permittedUse: String,
    val beforeYouStart: String,
    val afterYouExplore: String,
    val offlineAlternative: String,
    val approvalStatus: MediaApprovalStatus = MediaApprovalStatus.EXTERNAL_CANDIDATE
)

object ScienceMediaCatalog {
    const val BIOLOGY = "BIO"
    const val CHEMISTRY = "CHEM"
    const val PHYSICS = "PHY"

    val resources = listOf(
        ScienceMediaResource(
            id = "bio-membrane-transport-phet",
            title = "Membrane transport",
            subjectCode = BIOLOGY,
            topicId = "bio-transport",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore how substances cross cell membranes and connect particle movement to diffusion and osmosis.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/membrane-transport",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict the direction of net water movement when the solution outside a cell is more dilute than the cell contents.",
            afterYouExplore = "Describe what changed when you altered the concentration difference. Distinguish particle movement from net water movement.",
            offlineAlternative = "Use MeritPrep's local osmosis model and the transport lesson. The external simulation itself is not cached."
        ),
        ScienceMediaResource(
            id = "bio-natural-selection-phet",
            title = "Natural selection",
            subjectCode = BIOLOGY,
            topicId = "bio-evolution-theory",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Investigate how inherited variation and environmental pressures can change trait frequencies in a population.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/natural-selection",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict how a heritable trait may become more common when it improves reproductive success in a particular environment.",
            afterYouExplore = "Explain the population-level change. Do not describe individual organisms as evolving because they choose to adapt.",
            offlineAlternative = "Use the MeritPrep evolution lesson and a teacher-guided population example."
        ),
        ScienceMediaResource(
            id = "bio-gene-expression-phet",
            title = "Gene expression essentials",
            subjectCode = BIOLOGY,
            topicId = "bio-heredity",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore how genetic information is used in gene expression and connect DNA, RNA and protein synthesis.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/gene-expression-essentials",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Recall the roles of DNA, messenger RNA, ribosomes and amino acids.",
            afterYouExplore = "Put transcription and translation in the correct order and explain how a change in DNA may affect a protein.",
            offlineAlternative = "Use the MeritPrep heredity lesson and a labelled transcription/translation diagram."
        ),
        ScienceMediaResource(
            id = "bio-photosynthesis-hhmi",
            title = "Photosynthesis: from leaf to chloroplast",
            subjectCode = BIOLOGY,
            topicId = "bio-nutrition",
            mediaType = ScienceMediaType.ANIMATION,
            provider = "HHMI BioInteractive",
            summary = "A multipart animation follows photosynthesis from the leaf into the chloroplast, including the light reactions and Calvin cycle.",
            sourceUrl = "https://www.biointeractive.org/classroom-resources/photosynthesis",
            licenceName = "Resource-specific HHMI terms; check the page before reuse",
            licenceUrl = "https://www.biointeractive.org/faq",
            permittedUse = "Link to the original resource. Download, embed or redistribute only where the resource's own terms expressly permit it.",
            beforeYouStart = "Identify the inputs and outputs of photosynthesis and locate chloroplasts in a leaf cell.",
            afterYouExplore = "Explain where light energy is captured and how carbon is incorporated into organic molecules.",
            offlineAlternative = "Use the MeritPrep nutrition lesson and the standard photosynthesis equation."
        ),
        ScienceMediaResource(
            id = "bio-labxchange-virtual-labs",
            title = "Biology virtual laboratory collection",
            subjectCode = BIOLOGY,
            topicId = "bio-cell",
            mediaType = ScienceMediaType.VIRTUAL_LAB,
            provider = "LabXchange",
            summary = "A free collection of inquiry-based biology simulations includes photosynthesis and cellular respiration, osmosis, mitosis and other practical investigations.",
            sourceUrl = "https://about.labxchange.org/featured/free-virtual-labs-for-ap-biology",
            licenceName = "Check the licence and terms attached to each individual LabXchange resource",
            licenceUrl = "https://www.labxchange.org/",
            permittedUse = "External launch only. Do not assume that free access grants permission to download, embed or redistribute an activity.",
            beforeYouStart = "Read the investigation question, identify the variable being changed and state what evidence would support your prediction.",
            afterYouExplore = "Record the observation, identify the evidence and explain one limitation of the virtual model.",
            offlineAlternative = "Use the relevant MeritPrep lesson, local simulation or practical-method guide."
        ),
        ScienceMediaResource(
            id = "chem-atom-phet",
            title = "Build an atom",
            subjectCode = CHEMISTRY,
            topicId = "chem-atomic-bonding",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Add or remove protons, neutrons and electrons to investigate atomic number, mass number, charge and isotopes.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/build-an-atom",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Recall the relative charges and locations of protons, neutrons and electrons.",
            afterYouExplore = "Explain which particle determines the element and how changing neutrons affects the isotope.",
            offlineAlternative = "Use the MeritPrep atomic structure lesson and draw a labelled atomic model."
        ),
        ScienceMediaResource(
            id = "chem-molecule-shapes-phet",
            title = "Molecule shapes",
            subjectCode = CHEMISTRY,
            topicId = "chem-atomic-bonding",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Manipulate electron groups and compare molecular shapes using the electron-pair repulsion model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/molecule-shapes",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Count bonding pairs and lone pairs around a central atom in a simple molecule.",
            afterYouExplore = "Compare the arrangement of electron groups with the observed molecular shape.",
            offlineAlternative = "Use MeritPrep's bonding notes and paper or ball-and-stick molecular models."
        ),
        ScienceMediaResource(
            id = "chem-acid-base-phet",
            title = "Acid–base solutions",
            subjectCode = CHEMISTRY,
            topicId = "chem-acids-bases",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore acid and base strength, concentration and pH using a visual solution model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/acid-base-solutions",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Distinguish acid strength from acid concentration and recall what pH measures.",
            afterYouExplore = "Explain why changing concentration and changing acid strength are not the same operation.",
            offlineAlternative = "Use the MeritPrep Chemistry notes and the local concentration and titration practical models."
        ),
        ScienceMediaResource(
            id = "phy-circuit-kit-phet",
            title = "Circuit construction kit: DC virtual lab",
            subjectCode = PHYSICS,
            topicId = "phy-current-electricity",
            mediaType = ScienceMediaType.VIRTUAL_LAB,
            provider = "PhET Interactive Simulations",
            summary = "Construct simple direct-current circuits and investigate how components are connected.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/circuit-construction-kit-dc-virtual-lab",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Sketch a closed circuit containing a cell, connecting wires and a lamp. Predict what happens if the circuit is open.",
            afterYouExplore = "Compare series and parallel arrangements and explain the role of a complete conducting path.",
            offlineAlternative = "Use MeritPrep's local Ohm's law and resistor-network models. Never connect the app to mains electricity."
        ),
        ScienceMediaResource(
            id = "phy-wave-string-phet",
            title = "Wave on a string",
            subjectCode = PHYSICS,
            topicId = "phy-waves",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Change wave settings and observe how amplitude, frequency, damping and tension affect a transverse wave.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/wave-on-a-string",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Identify amplitude, wavelength, frequency and the direction of energy transfer in a transverse wave.",
            afterYouExplore = "Describe which wave properties changed when you adjusted the source or string conditions.",
            offlineAlternative = "Use the MeritPrep wave-properties model and a labelled transverse-wave diagram."
        ),
        ScienceMediaResource(
            id = "phy-forces-motion-phet",
            title = "Forces and motion: basics",
            subjectCode = PHYSICS,
            topicId = "phy-motion",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Investigate how applied force, friction and mass affect motion in a simplified model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/forces-and-motion-basics",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict how the acceleration of an object changes when the net force increases while mass remains constant.",
            afterYouExplore = "Use Newton's second law to explain the pattern you observed and identify the role of friction.",
            offlineAlternative = "Use MeritPrep's local uniformly accelerated motion and force lessons."
        ),
        ScienceMediaResource(
            id = "bio-labxchange-osmosis",
            title = "Osmosis and medicine: why saline is used for IV fluids",
            subjectCode = BIOLOGY,
            topicId = "bio-transport",
            mediaType = ScienceMediaType.VIRTUAL_LAB,
            provider = "LabXchange",
            summary = "Investigate how solution concentration affects water movement into and out of cells and connect the model to intravenous saline.",
            sourceUrl = "https://about.labxchange.org/featured/free-virtual-labs-for-ap-biology",
            licenceName = "Check the licence and terms attached to the individual LabXchange simulation",
            licenceUrl = "https://www.labxchange.org/",
            permittedUse = "External launch only; no copying, rehosting or redistribution without resource-specific permission.",
            beforeYouStart = "Explain why an intravenous fluid should not cause a large net movement of water into or out of blood cells.",
            afterYouExplore = "Relate the observed cell response to the relative solute concentrations inside and outside the cell.",
            offlineAlternative = "Use MeritPrep's local osmosis model and compare plant and animal cell responses."
        ),
        ScienceMediaResource(
            id = "bio-labxchange-mitosis",
            title = "Mitosis: onion root-tip cell division",
            subjectCode = BIOLOGY,
            topicId = "bio-growth",
            mediaType = ScienceMediaType.VIRTUAL_LAB,
            provider = "LabXchange",
            summary = "Examine a virtual investigation of cell division in root-tip tissue and practise interpreting mitotic observations.",
            sourceUrl = "https://about.labxchange.org/featured/free-virtual-labs-for-ap-biology",
            licenceName = "Check the licence and terms attached to the individual LabXchange simulation",
            licenceUrl = "https://www.labxchange.org/",
            permittedUse = "External launch only; no copying, rehosting or redistribution without resource-specific permission.",
            beforeYouStart = "Put the stages of mitosis in order and predict which stage will show chromosomes aligned at the equator.",
            afterYouExplore = "Explain how observations of dividing cells can be used to estimate the proportion of cells in each stage.",
            offlineAlternative = "Use MeritPrep's cell-growth lesson and a labelled mitosis sequence."
        ),
        ScienceMediaResource(
            id = "bio-labxchange-artificial-selection",
            title = "Artificial selection virtual lab",
            subjectCode = BIOLOGY,
            topicId = "bio-evolution-theory",
            mediaType = ScienceMediaType.VIRTUAL_LAB,
            provider = "LabXchange",
            summary = "Explore how selective breeding changes the frequency of chosen inherited characteristics over generations.",
            sourceUrl = "https://about.labxchange.org/featured/free-virtual-labs-for-ap-biology",
            licenceName = "Check the licence and terms attached to the individual LabXchange simulation",
            licenceUrl = "https://www.labxchange.org/",
            permittedUse = "External launch only; no copying, rehosting or redistribution without resource-specific permission.",
            beforeYouStart = "Distinguish artificial selection by humans from natural selection caused by environmental pressures.",
            afterYouExplore = "Identify the selected trait and explain why selection must act on heritable variation to produce change across generations.",
            offlineAlternative = "Use MeritPrep's evolution lesson and a worked selective-breeding example."
        ),
        ScienceMediaResource(
            id = "chem-balancing-equations-phet",
            title = "Balancing chemical equations",
            subjectCode = CHEMISTRY,
            topicId = "chem-combination",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Adjust coefficients in chemical equations and check conservation of atoms on both sides.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/balancing-chemical-equations",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Count each type of atom in a simple unbalanced equation.",
            afterYouExplore = "Explain why balancing changes coefficients rather than the chemical formula subscripts.",
            offlineAlternative = "Use MeritPrep's Chemistry lesson and balance equations using a written atom-count table."
        ),
        ScienceMediaResource(
            id = "chem-concentration-phet",
            title = "Concentration",
            subjectCode = CHEMISTRY,
            topicId = "chem-solubility",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore how solute amount and solution volume affect concentration in a visual solution model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/concentration",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict what happens to concentration when more solute is added without changing the solution volume.",
            afterYouExplore = "Explain how adding solvent changes concentration while the amount of dissolved solute remains constant.",
            offlineAlternative = "Use MeritPrep's local concentration practical and the concentration calculation guide."
        ),
        ScienceMediaResource(
            id = "chem-ph-scale-phet",
            title = "pH scale",
            subjectCode = CHEMISTRY,
            topicId = "chem-acids-bases",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Compare acidic, neutral and basic solutions and explore how dilution changes pH.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/ph-scale",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Recall the pH values associated with acidic, neutral and alkaline solutions at 25 °C.",
            afterYouExplore = "Describe how dilution changes pH and avoid assuming that pH changes linearly with concentration.",
            offlineAlternative = "Use MeritPrep's acid-base notes, indicator guide and titration practical."
        ),
        ScienceMediaResource(
            id = "chem-gas-properties-phet",
            title = "Gas properties",
            subjectCode = CHEMISTRY,
            topicId = "chem-kinetic-gas",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore how particle motion, temperature, volume and pressure are related in a gas model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/gas-properties",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict how gas pressure changes when a gas is heated in a fixed-volume container.",
            afterYouExplore = "Use particle collisions with the container walls to explain the pressure change.",
            offlineAlternative = "Use MeritPrep's gas-law lesson and the particle model explanation."
        ),
        ScienceMediaResource(
            id = "phy-energy-skate-park-phet",
            title = "Energy skate park",
            subjectCode = PHYSICS,
            topicId = "phy-work-energy",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Track kinetic, gravitational potential and thermal energy as an object moves along a track.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/energy-skate-park",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict where a skater has maximum gravitational potential energy and where kinetic energy is greatest.",
            afterYouExplore = "Explain how energy transfers between stores and how friction affects mechanical energy.",
            offlineAlternative = "Use MeritPrep's work-energy lesson and conservation-of-energy calculations."
        ),
        ScienceMediaResource(
            id = "phy-projectile-motion-phet",
            title = "Projectile motion",
            subjectCode = PHYSICS,
            topicId = "phy-motion",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Change launch conditions and investigate the horizontal and vertical components of projectile motion.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/projectile-motion",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "State why horizontal and vertical motion can be analysed independently when air resistance is neglected.",
            afterYouExplore = "Compare how launch angle and initial speed affect flight time, maximum height and range.",
            offlineAlternative = "Use MeritPrep's motion lesson and resolve velocity into horizontal and vertical components."
        ),
        ScienceMediaResource(
            id = "phy-ohms-law-phet",
            title = "Ohm's law",
            subjectCode = PHYSICS,
            topicId = "phy-current-electricity",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Explore the relationship between voltage, current and resistance in a simple circuit model.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/ohms-law",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Predict how current changes when voltage increases while resistance remains constant.",
            afterYouExplore = "Use V = IR to explain the relationship and identify which variable was held constant.",
            offlineAlternative = "Use MeritPrep's local Ohm's law model and resistor-network practical."
        ),
        ScienceMediaResource(
            id = "phy-geometric-optics-phet",
            title = "Geometric optics",
            subjectCode = PHYSICS,
            topicId = "phy-refraction",
            mediaType = ScienceMediaType.INTERACTIVE_SIMULATION,
            provider = "PhET Interactive Simulations",
            summary = "Investigate image formation by lenses and explore how focal length and object position affect images.",
            sourceUrl = "https://phet.colorado.edu/en/simulations/geometric-optics",
            licenceName = "CC BY-NC 4.0 for regular HTML simulations; verify current terms",
            licenceUrl = "https://phet.colorado.edu/en/licensing/",
            permittedUse = "External launch only in MeritPrep. Do not copy, rehost or package the simulation without confirming the applicable licence and attribution requirements.",
            beforeYouStart = "Identify the principal focus and predict how the image changes when an object moves relative to a converging lens.",
            afterYouExplore = "Describe image position, orientation and size for the object positions you tested.",
            offlineAlternative = "Use MeritPrep's refraction lesson and the local ray-diagram practical."
        )
    )

    fun forSubject(subjectCode: String): List<ScienceMediaResource> =
        resources.filter { it.subjectCode == subjectCode }

    fun find(resourceId: String): ScienceMediaResource? =
        resources.firstOrNull { it.id == resourceId }

    fun forTopic(topicId: String): List<ScienceMediaResource> =
        resources.filter { it.topicId == topicId }

    fun validate(): List<String> {
        val issues = mutableListOf<String>()
        if (resources.map { it.id }.distinct().size != resources.size) issues += "Resource IDs must be unique."
        resources.forEach { resource ->
            if (resource.id.isBlank()) issues += "A resource has a blank ID."
            if (resource.title.isBlank() || resource.summary.isBlank()) issues += "${resource.id}: title and summary are required."
            if (resource.subjectCode !in setOf(BIOLOGY, CHEMISTRY, PHYSICS)) issues += "${resource.id}: unsupported subject code."
            if (!resource.sourceUrl.startsWith("https://")) issues += "${resource.id}: source URL must use HTTPS."
            if (!resource.licenceUrl.startsWith("https://")) issues += "${resource.id}: licence/terms URL must use HTTPS."
            if (resource.provider.isBlank() || resource.licenceName.isBlank() || resource.permittedUse.isBlank()) {
                issues += "${resource.id}: provider, licence and permitted-use notes are required."
            }
            if (resource.beforeYouStart.isBlank() || resource.afterYouExplore.isBlank()) {
                issues += "${resource.id}: guided learning prompts are required."
            }
            if (resource.offlineAlternative.isBlank()) issues += "${resource.id}: offline alternative is required."
        }
        return issues
    }
}
