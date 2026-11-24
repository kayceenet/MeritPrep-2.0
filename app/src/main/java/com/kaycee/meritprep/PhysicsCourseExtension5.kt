package com.kaycee.meritprep

/** Final beginner-learning block for JAMB Physics. Draft content pending independent review. */
object PhysicsCourseExtension5 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-magnetism", "Magnets and magnetic fields", "PHY",
            "A compass needle turns when brought near a magnet because it experiences a magnetic field. Electric currents can also produce magnetic fields, allowing engineers to build electromagnets, motors and relays.",
            listOf("Describe magnetic poles and their interactions", "Compare permanent magnets and electromagnets", "Interpret magnetic field patterns", "Explain magnetic flux density and its unit", "Describe the Earth's magnetic field and simple magnetic applications"),
            listOf(
                "Magnetic poles and materials" to "A magnet has north-seeking and south-seeking poles. Like poles repel and unlike poles attract. Magnetic materials include iron, nickel and cobalt and some of their alloys. Soft iron is readily magnetised and demagnetised, making it useful for electromagnet cores; steel tends to retain magnetisation and is used for permanent magnets.",
                "Magnetic field" to "A magnetic field is a region where a magnetic pole or moving charge experiences a magnetic force. Field lines are drawn from north to south outside a magnet; they never cross. Closer field lines represent a stronger field. A compass aligns with the local magnetic field direction.",
                "Fields due to electric current" to "A current in a straight conductor produces circular magnetic field lines around the wire. The right-hand grip rule gives the field direction: thumb points in conventional current direction and curled fingers show the magnetic field direction. A coil or solenoid produces a field similar to that of a bar magnet; adding turns or current strengthens the field.",
                "Magnetic flux and flux density" to "Magnetic flux Φ is a measure of the magnetic field passing through a surface, measured in webers (Wb). Magnetic flux density B is flux per unit area normal to the field for a uniform field, measured in teslas (T). For a uniform field crossing a flat area at angle θ to the normal, Φ = BA cos θ.",
                "Earth's magnetic field" to "Earth behaves approximately like a large magnet, although its magnetic poles do not coincide exactly with geographic poles and they move over time. A compass aligns with the horizontal component of Earth's magnetic field. Magnetic declination is the angle between geographic and magnetic north; inclination is the angle the field makes with the horizontal."
            ),
            "The SI unit of magnetic flux density is:",
            listOf("Weber", "Tesla", "Henry", "Coulomb"), 1,
            "Magnetic flux density is measured in teslas (T); magnetic flux is measured in webers (Wb)."
        ),
        LearningTopic(
            "phy-magnetic-force", "Force on a current-carrying conductor in a magnetic field", "PHY",
            "A current-carrying wire can move when placed in a magnetic field. This motor effect is the basis of electric motors and many measuring instruments. The force depends on the magnetic field, current, length of wire in the field and the angle between the current and field.",
            listOf("State the motor-effect force relationship", "Apply Fleming's left-hand rule", "Explain the force between parallel current-carrying conductors", "Describe the basic operation of a DC motor", "Explain how a galvanometer can be adapted as an ammeter or voltmeter"),
            listOf(
                "Force on a current-carrying conductor" to "For a straight conductor of length L carrying current I in a uniform magnetic field B, the force magnitude is F = BIL sin θ, where θ is the angle between current direction and magnetic field. The force is maximum when the conductor is perpendicular to the field and zero when it is parallel. The SI unit of B is tesla.",
                "Fleming's left-hand rule" to "Hold the left thumb, first finger and second finger mutually perpendicular. The first finger indicates magnetic field direction (north to south), the second finger indicates conventional current direction, and the thumb indicates force or motion. Use the rule consistently with conventional current.",
                "Force between parallel currents" to "Two long parallel conductors carrying currents in the same direction attract; currents in opposite directions repel. Each conductor produces a magnetic field that acts on the current in the other conductor. The force per unit length is proportional to the product of the currents and inversely proportional to their separation in the ideal long-wire model.",
                "DC motor" to "A simple DC motor has a current-carrying coil in a magnetic field. Opposite sides of the coil experience forces in opposite directions, producing a turning effect. A split-ring commutator reverses the coil current every half-turn so the torque continues in the same rotational direction. The motor converts electrical energy to mechanical energy.",
                "Moving-coil instruments" to "A moving-coil galvanometer uses the turning effect on a current-carrying coil in a magnetic field to produce pointer deflection. A low-resistance shunt connected in parallel adapts it as an ammeter; a high resistance connected in series adapts it as a voltmeter. The instrument must be correctly rated and connected."
            ),
            "A 0.20 m wire carries 3 A perpendicular to a 0.5 T magnetic field. What force acts on it?",
            listOf("0.03 N", "0.15 N", "0.30 N", "3.0 N"), 2,
            "F = BIL sin 90° = 0.5 × 3 × 0.20 = 0.30 N."
        ),
        LearningTopic(
            "phy-induction", "Electromagnetic induction", "PHY",
            "A voltage can be induced in a coil when the magnetic field through it changes. Moving a magnet into a coil, rotating a coil in a magnetic field or changing current in a nearby coil can all induce an electromotive force. This is the operating principle of generators and transformers.",
            listOf("Explain magnetic flux linkage", "State Faraday's and Lenz's laws", "Identify factors affecting induced emf", "Describe AC and DC generators", "Explain transformer action and eddy currents"),
            listOf(
                "Magnetic flux linkage" to "Magnetic flux Φ through one turn depends on magnetic field, area and orientation. For a coil of N turns, flux linkage is NΦ. An emf is induced when the flux linkage through a circuit changes; a steady magnetic field through a stationary coil does not by itself produce a continuous induced emf.",
                "Faraday's law" to "The magnitude of the induced emf is proportional to the rate of change of magnetic flux linkage: |ε| = N|ΔΦ/Δt| for an average change over a time interval. A faster change, stronger field, more turns or larger effective coil area can increase induced emf, depending on the geometry.",
                "Lenz's law" to "The induced current flows in a direction that produces a magnetic effect opposing the change in flux that caused it. This opposition is a consequence of conservation of energy. It does not mean the induced field always opposes the original magnetic field; it opposes the change in flux.",
                "Generators" to "An AC generator rotates a coil in a magnetic field, causing flux linkage to vary and an alternating emf to be induced. A simple DC generator uses a split-ring commutator to produce a unidirectional output at the terminals, although the internal induced emf in the coil alternates. Mechanical energy is converted into electrical energy.",
                "Transformers and eddy currents" to "A transformer uses changing current in a primary coil to produce changing magnetic flux in a core, inducing emf in a secondary coil. For an ideal transformer, Vₛ/Vₚ = Nₛ/Nₚ. Transformers operate with changing current, normally AC. Eddy currents are circulating currents induced in bulk conductors; they can cause heating and energy loss, but are also used in induction heating and braking."
            ),
            "An ideal transformer has 500 turns on its primary and 100 turns on its secondary. If the primary voltage is 240 V, the secondary voltage is:",
            listOf("24 V", "48 V", "120 V", "480 V"), 1,
            "Vₛ/Vₚ = Nₛ/Nₚ = 100/500 = 0.2, so Vₛ = 240 × 0.2 = 48 V."
        ),
        LearningTopic(
            "phy-ac-circuits", "Simple AC circuits", "PHY",
            "The mains supply alternates direction many times each second. Alternating current is useful for power distribution because transformers can change AC voltage. Resistors, capacitors and inductors respond differently to AC, creating phase differences and frequency-dependent opposition to current.",
            listOf("Distinguish alternating and direct current", "Relate peak and rms values for a sinusoidal supply", "Explain capacitive and inductive reactance", "Use impedance in simple series AC circuits", "Explain resonance and power factor qualitatively"),
            listOf(
                "Alternating current and rms values" to "Alternating current changes magnitude and direction periodically. For a sinusoidal voltage, Vᵣₘₛ = V₀/√2, where V₀ is peak voltage. The rms value is the equivalent DC value that would produce the same average power in a resistor. Similarly, Iᵣₘₛ = I₀/√2 for a sinusoidal current.",
                "Resistive AC circuit" to "In a pure resistor, current and voltage are in phase. The resistance does not depend on frequency in the ideal model. Average power is P = Vᵣₘₛ Iᵣₘₛ for a pure resistor, and energy is dissipated as heat.",
                "Capacitive and inductive reactance" to "A capacitor opposes changes in voltage with capacitive reactance Xc = 1/(2πfC). An inductor opposes changes in current with inductive reactance Xl = 2πfL. Increasing frequency decreases Xc and increases Xl. Reactance is measured in ohms.",
                "Impedance and phase" to "Impedance Z is the total opposition to alternating current, combining resistance and reactance. For an ideal series RLC circuit, Z = √(R² + (Xl − Xc)²). The phase relationship between voltage and current depends on whether the circuit is more inductive or capacitive. Use rms values for ordinary AC power calculations unless the question specifies peak values.",
                "Resonance and power factor" to "In a series RLC circuit, resonance occurs when Xl = Xc, so impedance is minimum and current is maximum for a fixed rms supply voltage. The resonant frequency is f₀ = 1/(2π√(LC)). Power factor is cos φ, where φ is the phase angle between voltage and current; it indicates the fraction of apparent power converted to real power in the simple sinusoidal model."
            ),
            "For a sinusoidal AC voltage with peak value 200 V, the rms value is approximately:",
            listOf("100 V", "141 V", "200 V", "283 V"), 1,
            "Vᵣₘₛ = V₀/√2 = 200/1.414 ≈ 141 V."
        ),
        LearningTopic(
            "phy-conduction", "Conduction of electricity through liquids and gases", "PHY",
            "A metal wire conducts through mobile electrons, while an electrolyte conducts through moving ions. A gas normally conducts poorly, but under suitable conditions it can become ionised and carry electric current. These differences explain electrolysis, electroplating and gas-discharge devices.",
            listOf("Distinguish metallic and electrolytic conduction", "Identify electrolytes and non-electrolytes", "Explain electrolysis and electrode reactions", "State Faraday's laws of electrolysis", "Describe electrical discharge through gases and its applications"),
            listOf(
                "Conduction in metals and electrolytes" to "In metals, current is carried mainly by mobile electrons. In electrolytes, current is carried by positive and negative ions moving towards oppositely charged electrodes. An electrolyte conducts when molten or dissolved in a suitable solvent; a non-electrolyte does not produce mobile ions in the relevant conditions.",
                "Electrolysis" to "Electrolysis is the chemical change produced when direct current passes through an electrolyte. The cathode is the negative electrode in an electrolytic cell and attracts cations; reduction occurs there. The anode is positive and attracts anions; oxidation occurs there. The products depend on the ions present, electrode material and concentration.",
                "Faraday's laws" to "The mass of substance deposited or liberated at an electrode is proportional to the total charge passed, m ∝ Q = It, for a specified electrolyte and electrode reaction. For one mole of an electron charge, Faraday's constant F is approximately 96485 C mol⁻¹. In quantitative problems, use the balanced electrode equation to connect electrons, moles and deposited mass.",
                "Electroplating" to "Electroplating coats an object with a thin layer of another metal. The object to be plated is usually the cathode; the plating metal may be the anode, and a solution containing its ions acts as the electrolyte. Cleaning the object and controlling current, time and solution conditions affect coating quality.",
                "Conduction through gases" to "A gas can conduct when it contains enough ions and free electrons. Ionisation may occur through high voltage, radiation or heating. Gas discharge is used in devices such as fluorescent lamps and some display tubes. High voltage and ionising radiation require suitable shielding, insulation and trained supervision."
            ),
            "During electrolysis, reduction occurs at the:",
            listOf("Anode", "Cathode", "Salt bridge", "Power switch"), 1,
            "Reduction is gain of electrons and occurs at the cathode in an electrolytic cell."
        ),
        LearningTopic(
            "phy-modern", "Elementary modern physics", "PHY",
            "Classical ideas explain many everyday motions, but experiments involving atoms, electrons and light revealed behaviour that required new models. Modern physics includes atomic energy levels, the photoelectric effect, X-rays, radioactivity and nuclear energy.",
            listOf("Describe the basic idea of atomic energy levels and spectra", "Explain thermionic and photoelectric emission", "Describe X-ray production at an introductory level", "Calculate radioactive decay using half-life", "Distinguish nuclear fission and fusion and identify radiation risks"),
            listOf(
                "Atomic models and spectra" to "Atoms contain a small positively charged nucleus with electrons occupying allowed energy states. When an electron moves between allowed energy levels, it absorbs or emits a photon whose energy equals the energy difference: ΔE = hf. Because only certain energy differences are allowed, atoms produce characteristic line spectra.",
                "Thermionic and photoelectric emission" to "Thermionic emission is the release of electrons from a heated metal surface. Photoelectric emission occurs when light of sufficiently high frequency shines on a suitable metal and electrons are emitted. In Einstein's photoelectric equation, hf = φ + Kₘₐₓ, where φ is work function and Kₘₐₓ is maximum electron kinetic energy. Increasing intensity above threshold frequency increases the number of emitted electrons, not their maximum kinetic energy in the simple model.",
                "X-rays" to "X-rays can be produced when high-speed electrons strike a metal target and are rapidly decelerated; characteristic X-rays may also arise from inner-shell electronic transitions. X-rays are ionising electromagnetic radiation. Their use in medical imaging and industry requires controlled exposure, shielding and appropriate safety procedures.",
                "Radioactivity and half-life" to "Radioactive decay is spontaneous and random for an individual nucleus, but a large sample follows a predictable statistical law. Half-life is the time for the number of undecayed nuclei or activity to fall to half its initial value. After n half-lives, the remaining fraction is (1/2)ⁿ. Alpha particles are helium nuclei, beta radiation involves fast electrons or positrons, and gamma radiation is high-energy electromagnetic radiation.",
                "Fission, fusion and matter waves" to "Nuclear fission splits a heavy nucleus into lighter nuclei, releasing energy and often neutrons; a controlled chain reaction is used in nuclear reactors. Fusion combines light nuclei and releases energy, powering stars. Matter waves associate wavelength λ = h/p with a particle's momentum p. The uncertainty principle states that position and momentum cannot both be known with arbitrary precision: ΔxΔp ≥ ħ/2."
            ),
            "A radioactive sample has a half-life of 5 days. What fraction remains after 15 days?",
            listOf("1/2", "1/4", "1/8", "1/16"), 2,
            "15 days is three half-lives. Remaining fraction = (1/2)³ = 1/8."
        ),
        LearningTopic(
            "phy-electronics", "Introductory electronics", "PHY",
            "A diode can allow current mainly in one direction, while a transistor can control a larger current using a smaller input signal. These devices rely on semiconductors, whose electrical properties can be changed by adding small amounts of selected impurities.",
            listOf("Distinguish conductors, insulators and semiconductors", "Explain intrinsic and extrinsic semiconductors", "Distinguish p-type and n-type materials", "Describe diode action and rectification", "Explain the basic operation of a transistor as a switch or amplifier"),
            listOf(
                "Conductors, insulators and semiconductors" to "Conductors have many mobile charge carriers; insulators have very few under ordinary conditions. Semiconductors have conductivity between these extremes and their conductivity can change significantly with temperature, light and impurity concentration. Silicon and germanium are common semiconductor materials.",
                "Intrinsic and extrinsic semiconductors" to "An intrinsic semiconductor is a pure semiconductor in which charge carriers arise from thermal generation of electron-hole pairs. Doping adds a small controlled amount of impurity to increase conductivity. An n-type semiconductor is doped to provide extra electrons; a p-type semiconductor is doped to create holes as the majority carriers.",
                "The p-n junction diode" to "A p-n junction forms when p-type and n-type regions meet. Forward bias reduces the junction barrier and allows substantial current after the forward voltage is sufficient; reverse bias widens the depletion region and allows only a small leakage current until breakdown. A diode is therefore used for one-way current control in suitable circuits.",
                "Rectification" to "A half-wave rectifier uses one diode to pass one half-cycle of an AC input. A full-wave bridge rectifier uses four diodes to produce output of one polarity during both half-cycles. A smoothing capacitor can reduce ripple, but the output is not perfectly steady unless further regulation is used.",
                "Transistors" to "A bipolar junction transistor has emitter, base and collector regions. A small base current can control a larger collector current in the active region, allowing amplification. A transistor can also operate as a switch between cut-off and saturation. Correct biasing and component ratings are essential; real circuits need current-limiting and thermal design."
            ),
            "An n-type semiconductor has electrons as its:",
            listOf("Minority carriers only", "Majority charge carriers", "Positive ions in the conduction band", "Only mobile positive charges"), 1,
            "Doping an n-type semiconductor provides electrons as the majority charge carriers; holes are the minority carriers."
        )
    )
}
