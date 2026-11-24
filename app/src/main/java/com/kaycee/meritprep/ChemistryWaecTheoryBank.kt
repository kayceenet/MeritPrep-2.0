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
        )
    )
}
