package com.kaycee.meritprep

data class ChemistryTheoryTask(
    val id: String,
    val topic: String,
    val marks: Int,
    val prompt: String,
    val markingPoints: List<String>,
    val guidance: String
)

object ChemistryWaecTheoryBank {
    val tasks = listOf(
        ChemistryTheoryTask(
            "waec-chem-theory-mole-01", "Mole concept and stoichiometry", 8,
            "Magnesium reacts with dilute hydrochloric acid according to: Mg + 2HCl → MgCl₂ + H₂. (a) State the mole ratio of Mg to HCl. (2 marks) (b) Calculate the amount, in moles, of HCl required to react completely with 0.15 mol of Mg. (2 marks) (c) Calculate the volume of hydrogen produced at r.t.p. if one mole of gas occupies 24.0 dm³. (4 marks)",
            listOf("Correctly identifies Mg:HCl as 1:2.", "Uses the balanced equation to obtain 0.30 mol HCl.", "Identifies the Mg:H₂ ratio as 1:1 and obtains 0.15 mol H₂.", "Uses V = n × 24.0 dm³ and obtains 3.60 dm³ H₂."),
            "Show the balanced equation, mole ratio, substitution and units at each stage. Accept equivalent correct working."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-titration-02", "Acids, bases and titration", 7,
            "25.0 cm³ of sodium hydroxide solution is neutralised by 24.8 cm³ of 0.100 mol dm⁻³ hydrochloric acid. The reaction is HCl + NaOH → NaCl + H₂O. (a) Name a suitable indicator. (1 mark) (b) Calculate the concentration of the sodium hydroxide. (4 marks) (c) State two precautions for obtaining a reliable titre. (2 marks)",
            listOf("Names a suitable indicator, such as phenolphthalein or methyl orange, with a suitable endpoint range.", "Recognises the 1:1 mole ratio from the balanced equation.", "Substitutes C₁V₁ = C₂V₂ with consistent volume units.", "Obtains 0.0992 mol dm⁻³ NaOH.", "States two valid precautions: rinse apparatus with appropriate solutions, remove burette-tip air bubbles, read meniscus at eye level, add dropwise near endpoint, or obtain concordant titres."),
            "A numerical answer without the balanced-equation mole ratio and working should not receive all calculation marks."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-electrolysis-03", "Electrolysis and redox", 6,
            "Molten lead(II) bromide is electrolysed using inert electrodes. (a) Identify the ions present. (2 marks) (b) State the product at each electrode. (2 marks) (c) Write the half-equations. (2 marks)",
            listOf("Identifies Pb²⁺ and Br⁻ ions.", "Lead is produced at the cathode; bromine is produced at the anode.", "Cathode: Pb²⁺ + 2e⁻ → Pb.", "Anode: 2Br⁻ → Br₂ + 2e⁻."),
            "The electrolyte is molten, not aqueous. Do not introduce water-discharge competition into this question."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-organic-04", "Organic chemistry", 7,
            "(a) Define a homologous series. (2 marks) (b) State two characteristics of members of a homologous series. (2 marks) (c) Write the structural formula of ethene and state one chemical test for unsaturation, including the expected observation. (3 marks)",
            listOf("Defines a homologous series as a family of organic compounds with the same functional group and general formula.", "States that successive members differ by CH₂ and have similar chemical properties.", "Gives ethene as CH₂=CH₂.", "States that bromine water is decolourised from orange/brown to colourless by ethene under suitable conditions."),
            "Distinguish structural formula from molecular formula. The double bond is the reactive feature in this test."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-environment-05", "Chemistry and the environment", 6,
            "(a) Distinguish between a primary and a secondary air pollutant. (2 marks) (b) Give one example of each. (2 marks) (c) Explain one effect of acid rain on the environment. (2 marks)",
            listOf("Defines a primary pollutant as emitted directly from a source.", "Defines a secondary pollutant as formed in the atmosphere by chemical reactions involving pollutants.", "Gives valid examples such as SO₂ for primary and ozone or sulphuric acid aerosol for secondary, with context.", "Explains a valid effect such as acidification of lakes/soils, harm to aquatic life or accelerated corrosion of limestone/buildings."),
            "A named pollutant alone is not an explanation of its environmental effect."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-equilibrium-06", "Chemical equilibrium", 6,
            "The forward reaction in a reversible exothermic reaction is represented by A(g) + B(g) ⇌ C(g). (a) Explain dynamic equilibrium. (2 marks) (b) Predict and explain the effect of increasing pressure if the product side has fewer moles of gas. (2 marks) (c) Predict the effect of increasing temperature on the equilibrium yield of C. (2 marks)",
            listOf("States that forward and reverse reactions continue at equal rates in a closed system.", "States that concentrations remain constant at equilibrium, not necessarily equal.", "Increasing pressure shifts equilibrium toward the side with fewer gaseous moles, increasing C yield.", "Increasing temperature favours the endothermic reverse reaction and reduces C yield because the forward reaction is exothermic."),
            "Use Le Chatelier's principle and state the direction of shift. A catalyst changes the rate of reaching equilibrium, not the equilibrium position."

        ),
        ChemistryTheoryTask(
            "waec-chem-theory-atomic-07", "Atomic structure and periodicity", 7,
            "An atom has atomic number 17 and mass number 35. (a) State the numbers of protons, neutrons and electrons in a neutral atom. (3 marks) (b) Write its electronic configuration by shells. (1 mark) (c) Explain why it forms a 1− ion. (2 marks) (d) Name the group to which it belongs. (1 mark)",
            listOf("Identifies 17 protons.", "Calculates 18 neutrons from 35 − 17.", "Identifies 17 electrons in the neutral atom.", "Gives shell arrangement 2,8,7.", "Explains that it gains one electron to complete the outer shell.", "Identifies Group 17 (Group VII in older notation), the halogens."),
            "Atomic number gives proton number. In a neutral atom, electron number equals proton number; neutron number is mass number minus atomic number."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-gas-08", "Gas laws", 6,
            "A fixed mass of gas occupies 240 cm³ at 27 °C and constant pressure. Calculate its volume at 127 °C. State the gas law used and show all working.",
            listOf("Identifies Charles's law because pressure and amount of gas are constant.", "Converts 27 °C to 300 K.", "Converts 127 °C to 400 K.", "Uses V₁/T₁ = V₂/T₂.", "Substitutes V₂ = 240 × 400/300.", "Obtains 320 cm³ with a correct unit."),
            "Use kelvin temperatures, not Celsius, in gas-law ratios. The pressure and amount of gas are assumed constant."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-rates-09", "Rates of reaction", 7,
            "Calcium carbonate reacts with dilute hydrochloric acid. (a) State two ways to increase the initial rate. (2 marks) (b) Explain the effect of increasing temperature using collision theory. (3 marks) (c) Explain the role of a catalyst. (2 marks)",
            listOf("Names a valid factor such as increasing acid concentration, temperature or calcium carbonate surface area.", "Names a second valid factor.", "Explains that particles have greater average kinetic energy at higher temperature.", "Explains that a greater fraction of collisions have energy at least equal to activation energy.", "Links the increased frequency of effective collisions to a faster rate.", "States that a catalyst provides an alternative pathway with lower activation energy.", "States that the catalyst is regenerated and is not used up overall."),
            "Distinguish collision frequency from the fraction of collisions with sufficient energy. A catalyst changes the pathway, not the overall enthalpy change."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-energy-10", "Energy changes", 6,
            "A reaction mixture warms the surroundings as the reaction proceeds. (a) State whether the reaction is exothermic or endothermic. (1 mark) (b) Describe the relative energy of reactants and products. (2 marks) (c) Define activation energy. (1 mark) (d) Explain how a catalyst affects the energy profile. (2 marks)",
            listOf("Identifies the reaction as exothermic.", "States that energy is transferred from the reacting system to the surroundings.", "States that products have lower chemical energy than reactants.", "States that the enthalpy change is negative for the forward reaction.", "Defines activation energy as the minimum energy needed for a successful reaction pathway.", "Explains that a catalyst lowers activation energy by providing an alternative pathway but does not change the enthalpy change."),
            "Use the system–surroundings distinction: warming surroundings indicates heat has been released by the reacting system."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-solubility-11", "Solubility", 6,
            "A saturated solution of potassium nitrate is prepared at a high temperature and then cooled. (a) Define a saturated solution. (2 marks) (b) Predict what may happen as the solution cools. (2 marks) (c) Explain how pure crystals may be obtained. (2 marks)",
            listOf("Defines a saturated solution as one containing the maximum amount of dissolved solute at a stated temperature in equilibrium with undissolved solute.", "Recognises that solubility of potassium nitrate in water decreases substantially on cooling.", "Predicts that excess solute may crystallise from the solution.", "Explains that crystals form as the solution becomes supersaturated on cooling.", "Describes separating crystals by filtration.", "Describes washing crystals with a small amount of cold solvent and drying them appropriately."),
            "Solubility depends on temperature. Do not claim that every solute becomes less soluble when temperature falls; the trend is substance-dependent."
        ),
        ChemistryTheoryTask(
            "waec-chem-theory-metals-12", "Metals and extraction", 7,
            "Iron is extracted from haematite in a blast furnace. (a) Name the main iron ore. (1 mark) (b) State the roles of coke and limestone. (3 marks) (c) Write an equation showing reduction of iron(III) oxide by carbon monoxide. (2 marks) (d) Name the main waste product removed as slag. (1 mark)",
            listOf("Identifies haematite as iron(III) oxide, Fe₂O₃.", "States that coke burns to provide heat and forms carbon monoxide.", "States that carbon monoxide acts as a reducing agent.", "States that limestone decomposes to calcium oxide, which reacts with silica impurity.", "Writes Fe₂O₃ + 3CO → 2Fe + 3CO₂.", "Identifies calcium silicate as the main slag formed when CaO reacts with SiO₂."),
            "Limestone decomposes: CaCO₃ → CaO + CO₂. The CaO removes acidic silica impurity by forming calcium silicate slag."
        )
    )
}
