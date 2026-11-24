package com.kaycee.meritprep

/**
 * JAMB UTME Chemistry syllabus map.
 * Topic headings follow the Chemistry PDF served by JAMB IBASS.
 * Content status is draft until independently reviewed by a qualified Chemistry teacher.
 */
object ChemistryCourse {
    const val SYLLABUS_VERSION = "JAMB-IBASS-2026.1"
    const val SOURCE_URL = "https://ibass.jamb.gov.ng/static/media/Chemistry.9efd0b98043d56922aaa.pdf"

    private fun mapped(
        id: String,
        title: String,
        objective: String,
        scope: String
    ) = LearningTopic(
        id = id,
        title = title,
        subjectCode = "CHEM",
        introduction = "This topic is part of the JAMB UTME Chemistry syllabus. Begin with the key idea, connect it to familiar materials and everyday processes, then practise applying the principle to unfamiliar examples.",
        objectives = listOf(objective),
        sections = listOf(
            "Syllabus scope" to scope,
            "How to study this topic" to "Define the key terms in your own words, explain the underlying particle-level or reaction-level process, work through numerical or symbolic examples where relevant, and practise interpreting observations, tables and graphs. The full lesson, worked examples and independent academic review are still being developed."
        ),
        checkQuestion = "What is the most useful first step when studying this topic?",
        checkOptions = listOf("Memorise isolated facts without context", "Understand the key terms and the principle behind them", "Skip examples and go straight to difficult questions", "Assume every substance behaves in the same way"),
        correctOption = 1,
        explanation = "Understanding the terms and governing principle makes it easier to explain observations and solve new problems.",
        isSyllabusOutline = true
    )

    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "chem-separation", "Separation of mixtures and purification", "CHEM",
            "A glass of muddy water, a sachet of salt and a bottle of petrol are mixtures or products that can be separated or purified in different ways. Chemistry teaches us to choose a method by looking at the physical properties that differ between the substances. The central question is not simply which apparatus to use, but which property makes separation possible.",
            listOf("Distinguish elements, compounds and mixtures", "Separate physical changes from chemical changes", "Explain purity using melting and boiling points", "Choose a separation method from the properties of a mixture"),
            listOf(
                "First identify what you have" to "An element contains one type of atom. A compound contains two or more elements chemically combined in a fixed ratio. A mixture contains substances physically combined in variable proportions; its components retain their chemical identities and can often be separated physically. Air is a mixture, pure water is a compound, and copper is an element.",
                "Physical change or chemical change?" to "A physical change alters state, shape or appearance without creating a new substance. Melting ice and dissolving common salt in water are physical changes. A chemical change produces new substances with different compositions, as when iron rusts or magnesium burns. A change of state alone does not change the chemical identity of a substance.",
                "Purity and characteristic temperatures" to "A pure substance has a characteristic melting point and boiling point at a specified pressure. An impurity often changes the melting point and broadens the melting range; it may also raise or lower the boiling temperature depending on the system. A temperature plateau during a phase change can therefore help assess purity, provided pressure and measurement conditions are controlled.",
                "Choose a method by the property that differs" to "Filtration separates an insoluble solid from a fluid by particle size. Decantation uses settling and careful pouring. Evaporation removes a volatile solvent to recover a dissolved solid. Crystallisation forms purer crystals from a solution. Simple distillation separates a solvent from dissolved substances or liquids with widely different boiling points; fractional distillation is used for miscible liquids with closer boiling points. A separating funnel works for immiscible liquids of different densities. Sublimation is useful when one solid changes directly to vapour. Chromatography separates substances by different affinities for a stationary phase and a moving solvent. Magnetisation separates magnetic material from non-magnetic material.",
                "Worked choice: salt and sand" to "Salt dissolves in water but sand does not. Add water and stir so the salt dissolves; filter the mixture to retain sand; then evaporate some water and allow the concentrated solution to crystallise. The filter paper separates sand by insolubility, while crystallisation recovers the dissolved salt. Dry the products separately.",
                "Common errors to avoid" to "Filtration does not remove dissolved salt from water. Evaporation to dryness may recover a solid but can damage heat-sensitive substances. Distillation depends on volatility and boiling behaviour, not simply on the size of particles. Chromatography is not just filtration: separation depends on differential movement between phases."
            ),
            "Which method is most suitable for separating insoluble sand from a salt solution?",
            listOf("Filtration", "Fractional distillation", "Paper chromatography", "Sublimation"), 0,
            "Sand is insoluble and remains as a solid on the filter paper while the salt solution passes through."
        ),
        LearningTopic(
            "chem-combination", "Chemical combination and the mole concept", "CHEM",
            "Chemical equations are a language for describing how substances react. To use that language accurately, chemists rely on measurements and a small set of laws that explain how mass and elements are conserved. This lesson builds from symbols and formulae to balanced equations, relative masses and the mole.",
            listOf("State the laws of chemical combination", "Interpret chemical symbols, formulae and equations", "Explain relative atomic mass on the carbon-12 scale", "Use the mole concept in simple stoichiometric calculations"),
            listOf(
                "The laws behind chemical formulae" to "The law of conservation of mass states that mass is conserved in a chemical reaction in a closed system. The law of definite proportions states that a pure compound contains the same elements in a fixed mass ratio. The law of multiple proportions compares the different simple whole-number mass ratios in which two elements can combine to form more than one compound. These laws help explain why formulae and balanced equations have definite ratios.",
                "Symbols, formulae and equations" to "A chemical symbol represents an element. A formula shows which atoms are present and their simplest or actual ratio in a substance. An equation represents a reaction: reactants are written on the left and products on the right. Coefficients balance the number of atoms; subscripts are part of the formula and must not be changed to balance an equation. For example, 2H₂ + O₂ → 2H₂O has four hydrogen atoms and two oxygen atoms on each side.",
                "Relative atomic mass and the mole" to "Relative atomic mass (Aᵣ) compares the average mass of an atom of an element with one-twelfth of the mass of a carbon-12 atom. Relative formula or molecular mass is found by adding the relative atomic masses in the formula. One mole contains the Avogadro number of specified entities, approximately 6.022 × 10²³. The molar mass in g mol⁻¹ is numerically equal to the relative formula or molecular mass.",
                "A first mole calculation" to "The relationship is n = m ÷ M, where n is amount in moles, m is mass in grams and M is molar mass in g mol⁻¹. For 18 g of water, M(H₂O) = (2 × 1) + 16 = 18 g mol⁻¹, so n = 18 ÷ 18 = 1 mol. Always write the formula, substitute values with units and check that the final unit is mol.",
                "Stoichiometry: use the balanced equation" to "The coefficients in a balanced equation give mole ratios, not mass ratios. For 2H₂ + O₂ → 2H₂O, two moles of hydrogen react with one mole of oxygen to produce two moles of water. Convert a known mass to moles, use the coefficient ratio, then convert the required amount back to mass if needed."
            ),
            "How many moles are present in 18 g of water? (H = 1, O = 16)",
            listOf("0.5 mol", "1.0 mol", "2.0 mol", "18 mol"), 1,
            "M(H₂O) = 18 g mol⁻¹; n = m ÷ M = 18 ÷ 18 = 1 mol."
        ),
        LearningTopic(
            "chem-kinetic-gas", "Kinetic theory of matter and gas laws", "CHEM",
            "A perfume smell spreads across a room because particles move. A tyre becomes harder when heated because gas particles move faster and collide more forcefully. Kinetic theory connects these familiar observations to the behaviour of particles and provides the foundation for gas-law calculations.",
            listOf("Describe solids, liquids and gases using particle motion", "Explain melting, boiling, evaporation, freezing and condensation", "State and apply Boyle's, Charles's and other gas laws", "Use PV = nRT in simple calculations and interpret gas-law graphs"),
            listOf(
                "The particle model" to "Matter is made of tiny particles in continuous motion. In a solid, particles are close together and vibrate about fixed positions. In a liquid, particles remain close but can move past one another. In a gas, particles are far apart and move rapidly and randomly. The model explains why gases are compressible and why liquids flow while retaining a nearly fixed volume.",
                "Changes of state" to "Heating generally increases the average kinetic energy of particles. During melting or boiling of a pure substance at constant pressure, added energy is used to overcome intermolecular attractions, so the temperature remains constant until the change is complete. Freezing and condensation release energy to the surroundings. Evaporation can occur at the surface below the boiling point; boiling occurs throughout the liquid when its vapour pressure equals the external pressure.",
                "Gas laws: keep track of what is constant" to "Boyle's law: at constant temperature and amount, pressure is inversely proportional to volume (P₁V₁ = P₂V₂). Charles's law: at constant pressure and amount, volume is directly proportional to absolute temperature (V₁/T₁ = V₂/T₂). The pressure law: at constant volume and amount, pressure is directly proportional to absolute temperature (P₁/T₁ = P₂/T₂). Always convert Celsius to kelvin using K = °C + 273.15 before using gas laws.",
                "The ideal gas equation" to "PV = nRT combines the relationships among pressure, volume, amount and absolute temperature. Use a consistent set of units for R, pressure and volume. An ideal gas is a model whose particles have negligible volume and no intermolecular attraction; real gases approach ideal behaviour at relatively low pressure and high temperature. Real gases deviate more at high pressure and low temperature.",
                "Worked example: Boyle's law" to "A gas occupies 600 cm³ at 100 kPa. If its temperature remains constant and the pressure increases to 150 kPa, P₁V₁ = P₂V₂ gives V₂ = (100 × 600) ÷ 150 = 400 cm³. The volume decreases because pressure and volume are inversely related at constant temperature."
            ),
            "Which temperature scale must be used in the combined gas law?",
            listOf("Celsius", "Fahrenheit", "Kelvin", "Any scale gives the same result"), 2,
            "Gas-law proportionalities require absolute temperature, measured in kelvin."
        ),
        LearningTopic(
            "chem-atomic-bonding", "Atomic structure, periodicity and chemical bonding", "CHEM",
            "Why does sodium react vigorously with water while neon is almost unreactive? Why does solid sodium chloride behave differently from copper metal? The answers begin with atomic structure, electron arrangement and the forces that hold particles together.",
            listOf("Distinguish atoms, molecules and ions", "Determine protons, neutrons and electrons from atomic and mass numbers", "Write electron configurations for the first 20 elements", "Explain periodic trends and compare ionic, covalent and metallic bonding"),
            listOf(
                "Inside the atom" to "An atom contains a small, dense nucleus of protons and neutrons, with electrons occupying regions around it. The atomic number Z is the number of protons. The mass number A is the total number of protons and neutrons, so neutrons = A − Z. A neutral atom has equal numbers of protons and electrons. Ions form when atoms or groups of atoms gain or lose electrons; the nucleus does not change during ordinary ion formation.",
                "Isotopes and electron arrangement" to "Isotopes are atoms of the same element with the same proton number but different neutron numbers. Electrons occupy shells and subshells. For the first 20 elements, the introductory shell pattern is commonly represented as 2, 8, 8, 2, while subshell notation gives configurations such as sodium 1s² 2s² 2p⁶ 3s¹. Do not confuse mass number with relative atomic mass, which is an abundance-weighted average for a natural sample.",
                "The periodic table" to "Elements are arranged in order of increasing atomic number. Elements in the same group have related outer-electron configurations and often similar chemical properties. Across a period, effective nuclear attraction generally increases and atomic radius tends to decrease. Down a group, additional electron shells generally increase atomic radius. Ionisation energy and electronegativity show broad periodic trends, with known exceptions that should be learned from evidence rather than treated as absolute rules.",
                "Why atoms bond" to "Atoms form chemical bonds because the resulting arrangement can be more stable. Ionic bonding involves electrostatic attraction between oppositely charged ions, commonly formed by electron transfer. Covalent bonding involves shared pairs of electrons. Metallic bonding is the attraction between positive metal ions and delocalised electrons. Hydrogen bonding and van der Waals forces are intermolecular attractions and help explain physical properties such as boiling point.",
                "Molecular shape and properties" to "Electron-pair repulsion helps predict simple molecular shapes. Carbon dioxide is linear, water is bent, methane is tetrahedral and ammonia is trigonal pyramidal. Bonding and structure explain properties: many ionic solids have high melting points and conduct electricity when molten or dissolved; simple molecular substances often have lower melting points; metals conduct electricity because of mobile electrons."
            ),
            "How many neutrons are in an atom with mass number 23 and atomic number 11?",
            listOf("11", "12", "23", "34"), 1,
            "Neutrons = mass number − atomic number = 23 − 11 = 12."
        ),
        mapped("chem-nuclear", "Nuclear chemistry", "Compare nuclear reactions with ordinary chemical reactions and describe radioactive decay.", "Radioactivity; alpha, beta and gamma radiation; natural and artificial radioactivity; nuclear stability; half-life; fission and fusion; detection and applications of radiation."),
        mapped("chem-solubility", "Solubility", "Interpret solubility curves and distinguish solution types.", "Unsaturated, saturated and supersaturated solutions; solubility curves; solubility calculations; solvents; true solutions, suspensions and colloids."),
        mapped("chem-pollution", "Environmental pollution", "Identify pollutants, their sources, effects and control measures.", "Composition of air; air, water and soil pollution; common gaseous pollutants; sewage and oil pollution; biodegradable and non-biodegradable pollutants."),
        mapped("chem-acids-bases", "Acids, bases and salts", "Explain acid-base properties, prepare salts and perform simple pH and titration calculations.", "Indicators; strength and basicity; salt preparation and classification; pH and pOH; buffers; acid-base titration; salt hydrolysis."),
        mapped("chem-redox", "Oxidation and reduction", "Assign oxidation numbers and identify oxidising and reducing agents.", "Oxidation and reduction by oxygen/hydrogen transfer, electron transfer and oxidation number; balancing simple redox equations; inorganic nomenclature."),
        mapped("chem-electrolysis", "Electrolysis", "Predict products at electrodes and explain factors affecting discharge of ions.", "Electrolytes; Faraday's laws; electrolysis of aqueous and molten substances; metal purification; electrochemical cells; corrosion and protection."),
        mapped("chem-energy", "Energy changes", "Distinguish exothermic and endothermic changes and apply simple energy relationships.", "Enthalpy change; dissolution and reaction with water; entropy; Gibbs free energy; spontaneity and simple energy diagrams."),
        mapped("chem-rates", "Rates of chemical reaction", "Explain how temperature, concentration, pressure, surface area and catalysts affect reaction rate.", "Collision theory; activation energy; reaction-rate curves; effect of light; simple rate calculations and experimental examples."),
        mapped("chem-equilibrium", "Chemical equilibrium", "Explain dynamic equilibrium and predict shifts using Le Chatelier's principle.", "Reversible reactions; equilibrium position; concentration, pressure and temperature effects; industrial applications; equilibrium constant concept."),
        mapped("chem-nonmetals", "Non-metals and their compounds", "Describe preparation, properties, tests and uses of selected non-metals and their compounds.", "Hydrogen; halogens and hydrogen chloride; oxygen and sulphur; oxides; ozone; water, hardness and treatment; relevant laboratory tests."),
        mapped("chem-metals", "Metals and their compounds", "Relate metal properties, extraction and reactivity to their uses.", "General properties; reactivity series; extraction of selected metals; iron, aluminium and copper; alloys; corrosion and prevention."),
        mapped("chem-organic", "Organic compounds", "Classify organic compounds and relate functional groups to their reactions and uses.", "Hydrocarbons; homologous series; isomerism; alkanes, alkenes, alkynes, benzene; alkanols, alkanoic acids and esters; polymers."),
        mapped("chem-industry", "Chemistry and industry", "Explain selected industrial chemical processes and their economic and environmental relevance.", "Industrial raw materials and products; manufacture of acids, ammonia, cement, glass and soap; petroleum processing; industrial safety and pollution control."),
        mapped("chem-astro", "Astronomical chemistry", "Describe the chemical composition of selected celestial bodies and the origin of elements at an introductory level.", "Elements in stars and space; hydrogen and helium abundance; stellar processes and the relationship between astronomy and chemical evidence.")
    )
}
