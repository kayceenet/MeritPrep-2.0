package com.kaycee.meritprep

/** Fourth beginner-learning block for JAMB Physics. Draft content pending independent review. */
object PhysicsCourseExtension4 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-electrostatics", "Electrostatics", "PHY",
            "A balloon rubbed against dry hair can attract small pieces of paper. A plastic comb can also make hair strands repel one another. These effects arise from electric charge at rest, the forces between charges and the electric fields they produce.",
            listOf("Distinguish positive and negative electric charge", "Explain charging by friction, contact and induction", "Apply Coulomb's law to simple point charges", "Describe electric field and electric potential", "Explain earthing, discharge and lightning protection"),
            listOf(
                "Electric charge" to "There are two kinds of electric charge, conventionally called positive and negative. Like charges repel; unlike charges attract. Charge is measured in coulombs (C). In ordinary electrostatic processes, charge is transferred from one body to another; it is not created from nothing. Charge is quantised in units of the elementary charge magnitude e.",
                "Methods of charging" to "Charging by friction involves transfer of electrons when suitable materials are rubbed together. Charging by contact occurs when a charged body touches another conductor and charge is shared. Charging by induction occurs without direct contact: a nearby charged body redistributes charge in a conductor, and earthing can leave the conductor with a net charge after the earth connection and inducing body are removed in the correct sequence.",
                "Coulomb's law" to "For two stationary point charges in a vacuum, the magnitude of the electrostatic force is F = k|q₁q₂|/r², where k = 1/(4πε₀), q₁ and q₂ are charges and r is their separation. The force acts along the line joining the charges. The direction is repulsive for like charges and attractive for unlike charges. In a material medium, the effective force also depends on the medium's permittivity.",
                "Electric field and potential" to "Electric field strength at a point is force per unit positive test charge: E = F/q, measured in N C⁻¹ or V m⁻¹. Field direction is the direction a positive test charge would be pushed. Electric potential is potential energy per unit charge, measured in volts. For a point charge, potential relative to infinity is V = kq/r under the usual isolated-charge model.",
                "Discharge and lightning" to "A conductor can be discharged by providing a path for charge to flow to Earth. A lightning conductor provides a safe conducting path to earth and helps protect a building from lightning strikes. Earthing and bonding are important safety measures, but they must be designed and installed to applicable electrical standards."
            ),
            "Two like electric charges placed near one another will:",
            listOf("Attract", "Repel", "Lose all charge immediately", "Become magnetic poles"), 1,
            "Like charges repel, while unlike charges attract."
        ),
        LearningTopic(
            "phy-capacitors", "Capacitors", "PHY",
            "A capacitor stores separated electric charge and energy in an electric field. Capacitors are used in power supplies, electronic circuits, timing systems and motor-starting arrangements. Their behaviour depends on capacitance, voltage and how capacitors are connected.",
            listOf("Define capacitance and state its SI unit", "Use Q = CV and calculate stored energy", "Explain factors affecting a parallel-plate capacitor", "Calculate equivalent capacitance in series and parallel", "Identify common uses of capacitors"),
            listOf(
                "Capacitance" to "Capacitance is the charge stored per unit potential difference: C = Q/V. Its SI unit is the farad (F), equal to one coulomb per volt. A capacitor's capacitance depends on its geometry and the insulating material (dielectric) between its conductors, not simply on the amount of charge currently stored.",
                "Parallel-plate capacitor" to "For an ideal parallel-plate capacitor with plate area A and separation d, C = εA/d, where ε is the permittivity of the dielectric. Increasing plate area increases capacitance; increasing separation decreases capacitance. Inserting a dielectric with higher permittivity increases capacitance, provided the geometry is unchanged.",
                "Energy stored" to "The energy stored in an ideal capacitor is E = ½CV² = ½QV = Q²/(2C). Energy is stored in the electric field between the plates. A capacitor may retain charge after a power supply is disconnected, so it must be discharged safely before handling in real equipment.",
                "Series and parallel combinations" to "For capacitors in parallel, the equivalent capacitance is Cₑq = C₁ + C₂ + … because each capacitor has the same potential difference and charges add. For capacitors in series, 1/Cₑq = 1/C₁ + 1/C₂ + … because each has the same magnitude of charge and the potential differences add. These relationships are the reverse of the simple resistor-combination rules.",
                "Applications and limitations" to "Capacitors smooth voltage fluctuations, couple or decouple signals, store short bursts of energy and set timing constants with resistors. Real capacitors have voltage ratings, leakage, equivalent series resistance and dielectric limits. Never exceed the rated voltage or assume a capacitor is discharged simply because the circuit is switched off."
            ),
            "A 100 μF capacitor is connected across 12 V. What charge does it store?",
            listOf("0.12 μC", "1.2 μC", "120 μC", "1200 μC"), 2,
            "Q = CV = 100 × 10⁻⁶ × 12 = 1.2 × 10⁻³ C = 1200 μC. Check the conversion: 1 mC = 1000 μC."
        ),
        LearningTopic(
            "phy-cells", "Electric cells", "PHY",
            "A torch lights because a cell converts chemical energy into electrical energy. Cells can be connected in series to increase total electromotive force or in parallel to provide greater capacity under suitable conditions. Real cells also have internal resistance, so terminal voltage can differ from emf when current flows.",
            listOf("Distinguish emf and terminal potential difference", "Explain internal resistance and lost volts", "Compare primary and secondary cells", "Describe series and parallel cell arrangements", "Identify safe handling and maintenance principles"),
            listOf(
                "How a cell supplies energy" to "A cell uses chemical reactions to maintain a potential difference between its terminals. Electromotive force (emf) is the energy supplied by the source per unit charge around the complete circuit. It is measured in volts and is not a mechanical force. Primary cells are designed for one use or limited recharge; secondary cells are rechargeable.",
                "Internal resistance and terminal voltage" to "A real cell has internal resistance r. When it supplies current I, some voltage is lost inside the cell: lost volts = Ir. For a discharging cell, terminal potential difference V = ε − Ir, where ε is emf. When no current is drawn, terminal voltage is approximately equal to emf in the simple model.",
                "Cell combinations" to "For identical cells in series aiding, emfs add and internal resistances also add. Series connection can provide a higher voltage. For identical cells in parallel, the emf remains that of one cell while the effective internal resistance decreases; the arrangement can supply current for longer under suitable load conditions. Do not connect cells of different types, states of charge or ratings in parallel without an approved design.",
                "Types of cells" to "Common examples include zinc-carbon and alkaline primary cells, and rechargeable lead-acid, nickel-metal hydride and lithium-ion cells. Their voltage, energy density, current capability, cycle life and maintenance needs differ. A lead-acid battery is rechargeable and widely used for vehicle starting and backup power; lithium-ion cells require suitable protection and charging control.",
                "Safe use" to "Use the correct chemistry and voltage for a device. Avoid short circuits, puncturing, crushing, overheating and charging a non-rechargeable cell. Follow the manufacturer's charging and disposal instructions. Damaged or swollen rechargeable cells should be isolated and handled under appropriate safety procedures."
            ),
            "A cell has emf 1.5 V, internal resistance 0.5 Ω and supplies 1 A. What is its terminal voltage while discharging?",
            listOf("0.5 V", "1.0 V", "1.5 V", "2.0 V"), 1,
            "V = ε − Ir = 1.5 − (1 × 0.5) = 1.0 V."
        ),
        LearningTopic(
            "phy-current-electricity", "Current electricity", "PHY",
            "A lamp lights when a complete circuit allows charge to flow. Current, potential difference and resistance describe how electrical energy is transferred in a circuit. Circuit diagrams and simple rules help predict how components behave when connected in series or parallel.",
            listOf("Define current, potential difference, emf and resistance", "Apply Ohm's law under constant physical conditions", "Calculate equivalent resistance in series and parallel", "Use current and voltage rules in simple circuits", "Explain resistivity and the factors affecting resistance"),
            listOf(
                "Current and potential difference" to "Electric current is the rate of flow of charge: I = Q/t, measured in amperes. Potential difference between two points is energy transferred per unit charge: V = W/Q, measured in volts. Conventional current direction is the direction positive charge would move; in metal wires, electrons drift in the opposite direction.",
                "Ohm's law" to "For a metallic conductor at constant temperature and other unchanged physical conditions, current is directly proportional to potential difference: V = IR. Resistance R is measured in ohms (Ω). A component that obeys this relationship over the tested range is ohmic; a filament lamp and diode are examples of non-ohmic components over ordinary operating ranges.",
                "Resistance and resistivity" to "For a uniform wire, R = ρL/A, where ρ is resistivity in Ω m, L is length and A is cross-sectional area. A longer wire has greater resistance; a thicker wire has lower resistance for the same material and length. Resistivity is a material property and usually changes with temperature.",
                "Series and parallel circuits" to "In a series circuit, the same current flows through every component and the supply voltage is shared; total resistance is Rₑq = R₁ + R₂ + …. In a parallel circuit, each branch has the same potential difference and the total current is the sum of branch currents; 1/Rₑq = 1/R₁ + 1/R₂ + …. Adding a parallel branch reduces equivalent resistance.",
                "Kirchhoff's rules and measurement" to "At a junction, total current entering equals total current leaving (conservation of charge). Around a closed loop, the algebraic sum of potential rises and drops is zero (conservation of energy). An ammeter is connected in series and has low resistance; a voltmeter is connected in parallel and has high resistance. A metre bridge and potentiometer can be used for more precise resistance and emf comparisons."
            ),
            "Two resistors of 4 Ω and 6 Ω are connected in series. What is their equivalent resistance?",
            listOf("2.4 Ω", "4 Ω", "6 Ω", "10 Ω"), 3,
            "For series resistors, Rₑq = R₁ + R₂ = 4 + 6 = 10 Ω."
        ),
        LearningTopic(
            "phy-electrical-power", "Electrical energy and power", "PHY",
            "An electric kettle converts electrical energy into thermal energy, while a motor converts electrical energy into mechanical output. Electrical power tells us how quickly energy is transferred, and energy calculations help estimate running costs and choose suitable ratings.",
            listOf("Calculate electrical power using P = VI", "Relate electrical energy to power and time", "Use the kilowatt-hour as a commercial energy unit", "Explain the heating effect of current", "Describe safe domestic wiring, fuses and circuit protection"),
            listOf(
                "Electrical power" to "Power is the rate of energy transfer. In a DC circuit, P = VI. Combining this with Ohm's law gives P = I²R and P = V²/R for an ohmic resistor. The SI unit is the watt (W). Choose the form that matches the quantities supplied in the question.",
                "Electrical energy and commercial units" to "Electrical energy transferred is E = Pt. The joule is the SI unit, but electricity bills commonly use the kilowatt-hour (kWh). One kWh = 1000 W × 3600 s = 3.6 × 10⁶ J. Energy cost is the number of kWh multiplied by the tariff per kWh, excluding any fixed charges or additional fees.",
                "Heating effect of current" to "When current flows through a resistance, electrical energy is transferred to internal energy. For a constant resistor, heat energy H = I²Rt = VIt = V²t/R. This is useful in kettles and heaters but can be unwanted in cables and electronic components, where excessive heating may damage insulation or cause fire.",
                "Transmission and efficiency" to "Power stations transmit electrical energy over long distances at high voltage so that current is lower for a given power. Since cable power loss is I²R, reducing current reduces resistive losses. Transformers change AC voltage levels; step-up transformers raise voltage for transmission and step-down transformers lower voltage for distribution and use.",
                "Domestic wiring and protection" to "Domestic appliances are normally connected in parallel so each receives the supply voltage and can be controlled independently. The live conductor is fused or protected by a circuit breaker; the neutral completes the circuit and the protective earth connects exposed conductive parts to earth. Fuses and breakers must be correctly rated. Never work on live mains wiring without the required competence, isolation and safety procedures."
            ),
            "A 2 kW heater operates for 3 hours. How much electrical energy does it use?",
            listOf("0.67 kWh", "2 kWh", "5 kWh", "6 kWh"), 3,
            "E = Pt = 2 kW × 3 h = 6 kWh."
        )
    )
}
