package com.kaycee.meritprep

data class PhysicsTheoryTask(
    val id: String,
    val topic: String,
    val marks: Int,
    val prompt: String,
    val markingPoints: List<String>,
    val guidance: String
)

/** Original WAEC-style structured-response practice; not official past questions or a WAEC marking scheme. */
object PhysicsWaecTheoryBank {
    val tasks = listOf(
        PhysicsTheoryTask(
            "waec-physics-theory-motion-01", "Motion, forces and energy", 9,
            "A car of mass 1,200 kg increases its speed uniformly from 5.0 m s⁻¹ to 25.0 m s⁻¹ in 10 s. (a) Calculate its acceleration. (2 marks) (b) Determine the resultant force on the car. (2 marks) (c) Calculate the change in kinetic energy. (3 marks) (d) State two reasons why the actual driving force may be greater than the resultant force. (2 marks)",
            listOf(
                "Uses a = (v − u)/t = (25 − 5)/10 = 2.0 m s⁻².",
                "Uses F = ma = 1,200 × 2.0 = 2,400 N.",
                "Uses ΔKE = ½m(v² − u²).",
                "Obtains ΔKE = ½ × 1,200 × (625 − 25) = 360,000 J.",
                "Explains that the driving force must also overcome resistive forces such as air resistance, rolling friction or drivetrain losses."
            ),
            "Show the formula, substitution and unit for each numerical part. The resultant force is not necessarily equal to the engine's driving force because resistive forces also act."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-thermal-02", "Thermal physics and gas laws", 8,
            "A 0.50 kg sample of water is heated from 25°C to 75°C. Take the specific heat capacity of water as 4,200 J kg⁻¹ K⁻¹. (a) Calculate the heat absorbed. (3 marks) (b) Explain why the temperature of a pure substance remains constant during a change of state at fixed pressure. (2 marks) (c) State Boyle's law and identify the condition required. (3 marks)",
            listOf(
                "Uses Q = mcΔT.",
                "Substitutes m = 0.50 kg, c = 4,200 J kg⁻¹ K⁻¹ and ΔT = 50 K.",
                "Obtains Q = 105,000 J or 1.05 × 10⁵ J.",
                "Explains that energy supplied during a phase change changes internal potential energy or overcomes intermolecular attractions rather than raising average kinetic energy.",
                "States that for a fixed mass of gas at constant temperature, pressure is inversely proportional to volume (pV = constant)."
            ),
            "A temperature difference in °C has the same numerical value as a temperature difference in kelvin. State the constant-temperature condition for Boyle's law."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-waves-03", "Waves and sound", 8,
            "A sound wave in air has frequency 500 Hz and wavelength 0.68 m. (a) Calculate its speed. (2 marks) (b) An echo returns 0.80 s after a sound is emitted. Calculate the distance to the reflecting surface, taking the speed of sound as 340 m s⁻¹. (3 marks) (c) Explain why the time is divided by two in part (b). (1 mark) (d) State two conditions that help a distinct echo to be heard. (2 marks)",
            listOf(
                "Uses v = fλ.",
                "Obtains v = 500 × 0.68 = 340 m s⁻¹.",
                "Uses distance = vt/2 for echo sounding.",
                "Obtains distance = 340 × 0.80/2 = 136 m.",
                "Explains that the measured time is for the sound's journey to the reflector and back.",
                "States suitable conditions such as a sufficiently large/distant reflecting surface and a time separation of about 0.1 s or more, depending on hearing conditions."
            ),
            "An echo time is a round-trip time. Do not use d = vt without dividing by two when finding the one-way distance."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-optics-04", "Refraction and optical instruments", 9,
            "A ray of light travels from air into glass of refractive index 1.50 at an angle of incidence of 30° to the normal. (a) Calculate the angle of refraction. (3 marks) (b) Calculate the critical angle for a glass-air boundary. (2 marks) (c) State the two conditions for total internal reflection. (2 marks) (d) Name the lens used to correct myopia and explain its action. (2 marks)",
            listOf(
                "Applies Snell's law n₁ sin i = n₂ sin r.",
                "Obtains sin r = sin 30°/1.50 = 0.333 and r ≈ 19.5°.",
                "Uses sin C = 1/1.50 and obtains C ≈ 41.8°.",
                "States that light must travel from higher to lower refractive index and the incidence angle must exceed the critical angle.",
                "Identifies a diverging (concave) lens and explains that it diverges incoming rays so the image is focused on the retina."
            ),
            "Measure both incidence and refraction angles from the normal. Use degree mode on the calculator and state the angle to a sensible precision."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-electricity-05", "Current electricity and electromagnetic induction", 10,
            "A 4 Ω resistor and a 6 Ω resistor are connected in series to a 20 V DC supply. (a) Calculate the equivalent resistance. (2 marks) (b) Calculate the circuit current. (2 marks) (c) Calculate the potential difference across the 6 Ω resistor. (2 marks) (d) Calculate the total power supplied. (2 marks) (e) State Faraday's law of electromagnetic induction. (2 marks)",
            listOf(
                "Calculates series resistance Rₜ = 4 + 6 = 10 Ω.",
                "Uses I = V/R = 20/10 = 2 A.",
                "Uses V₆Ω = IR = 2 × 6 = 12 V.",
                "Uses P = VI = 20 × 2 = 40 W.",
                "States that induced emf magnitude is proportional to the rate of change of magnetic flux linkage."
            ),
            "In a series circuit the current is the same through each resistor and the supply voltage is shared. Check that the sum of the resistor voltage drops equals the supply voltage."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-modern-06", "Atomic physics, radioactivity and electronics", 9,
            "A radioactive sample has an initial activity of 800 counts per minute and a half-life of 6 hours. (a) Calculate its activity after 18 hours. (3 marks) (b) Distinguish between nuclear fission and nuclear fusion. (2 marks) (c) State the majority charge carriers in n-type and p-type semiconductors. (2 marks) (d) State one use of a p-n junction diode. (2 marks)",
            listOf(
                "Recognises that 18 hours represents three half-lives.",
                "Calculates activity as 800 × (½)³ = 100 counts per minute.",
                "Defines fission as splitting a heavy nucleus and fusion as combining light nuclei.",
                "Identifies electrons as majority carriers in n-type material and holes in p-type material.",
                "States a valid diode use such as rectification, signal detection or reverse-polarity protection."
            ),
            "Show each half-life step or use A = A₀(½)ⁿ. Distinguish majority carriers from the minority carriers also present in a semiconductor."

        ),
        PhysicsTheoryTask(
            "waec-physics-theory-measurement-07", "Measurements, units and density", 7,
            "A metal block has mass 540 g and dimensions 6.0 cm × 3.0 cm × 2.0 cm. (a) Calculate its volume. (2 marks) (b) Calculate its density in g cm⁻³. (2 marks) (c) Convert the density to kg m⁻³. (2 marks) (d) State one precaution when measuring its dimensions. (1 mark)",
            listOf(
                "Calculates volume = 6.0 × 3.0 × 2.0 = 36.0 cm³.",
                "Uses density = mass/volume = 540/36 = 15 g cm⁻³.",
                "Uses 1 g cm⁻³ = 1,000 kg m⁻³.",
                "Obtains 15,000 kg m⁻³ or 1.5 × 10⁴ kg m⁻³.",
                "States a suitable precaution such as reading the scale at eye level, avoiding parallax or taking repeated readings."
            ),
            "Keep units consistent during each calculation. Since 1 g = 10⁻³ kg and 1 cm³ = 10⁻⁶ m³, 1 g cm⁻³ equals 1,000 kg m⁻³."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-pressure-08", "Pressure and fluids", 7,
            "A hydraulic lift has a small piston of area 0.020 m² and a large piston of area 0.50 m². A force of 120 N is applied to the small piston. (a) State Pascal's principle. (2 marks) (b) Calculate the pressure transmitted through the fluid. (2 marks) (c) Calculate the ideal output force on the large piston. (2 marks) (d) State one reason the actual output may be smaller. (1 mark)",
            listOf(
                "States that pressure applied to an enclosed fluid is transmitted equally and undiminished throughout the fluid.",
                "Uses pressure = force/area = 120/0.020.",
                "Obtains 6,000 Pa.",
                "Uses output force = pressure × large piston area = 6,000 × 0.50.",
                "Obtains 3,000 N.",
                "Identifies friction, leakage or other mechanical losses as a reason for a lower actual output."
            ),
            "The ideal force multiplication equals the ratio of piston areas. Do not confuse pressure with force; their units are different."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-electrostatics-09", "Electrostatics and electric fields", 7,
            "Two point charges of +2.0 μC and +3.0 μC are separated by 0.30 m in air. Take k = 9.0 × 10⁹ N m² C⁻². (a) State Coulomb's law. (2 marks) (b) Calculate the magnitude of the electrostatic force. (3 marks) (c) State whether the force is attractive or repulsive and explain. (2 marks)",
            listOf(
                "States that the force between two point charges is proportional to the product of their charges and inversely proportional to the square of their separation.",
                "Converts charges to coulombs: 2.0 × 10⁻⁶ C and 3.0 × 10⁻⁶ C.",
                "Uses F = k|q₁q₂|/r².",
                "Substitutes values consistently with r = 0.30 m.",
                "Obtains F = 0.60 N.",
                "Identifies the force as repulsive because both charges are positive."
            ),
            "Use the magnitude of the charges in the force calculation, then use the signs to determine whether the force is attractive or repulsive."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-magnetism-10", "Magnetism and electromagnetic induction", 7,
            "A straight wire of length 0.40 m carries a current of 3.0 A at right angles to a uniform magnetic field of flux density 0.50 T. (a) Calculate the magnetic force on the wire. (3 marks) (b) State Fleming's left-hand rule. (2 marks) (c) State two ways to increase the force. (2 marks)",
            listOf(
                "Uses F = BIL sin θ.",
                "Recognises θ = 90° and sin 90° = 1.",
                "Obtains F = 0.50 × 3.0 × 0.40 = 0.60 N.",
                "States that the first finger indicates field, the second finger current and the thumb force or motion, all mutually perpendicular.",
                "Names two valid changes: increase magnetic flux density, current or the length of wire within the magnetic field. The wire is already perpendicular to the field, so its force is at the maximum for the stated field, current and length."
            ),
            "The formula assumes a straight current-carrying conductor in a uniform magnetic field. The force is maximum when the wire is perpendicular to the field."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-oscillations-11", "Oscillations and simple harmonic motion", 7,
            "A simple pendulum completes 30 oscillations in 60 s. (a) Calculate its period. (2 marks) (b) Calculate its frequency. (2 marks) (c) State two conditions under which the pendulum's motion approximates simple harmonic motion. (2 marks) (d) State one factor that affects its period. (1 mark)",
            listOf(
                "Uses period = total time/number of oscillations.",
                "Obtains T = 60/30 = 2.0 s.",
                "Uses frequency = 1/T.",
                "Obtains f = 0.50 Hz.",
                "States small angular displacement and negligible air resistance or pivot friction.",
                "Identifies pendulum length or gravitational field strength as a factor affecting period."
            ),
            "For small angular displacement, T = 2π√(L/g). The period is independent of bob mass in the ideal small-angle model."
        ),
        PhysicsTheoryTask(
            "waec-physics-theory-practical-12", "Experimental skills and graph analysis", 8,
            "In an experiment, the extension of a spring is measured for different loads. (a) State the relationship expected within the elastic limit. (2 marks) (b) Describe the graph of load against extension in this region. (2 marks) (c) Explain how the spring constant is obtained from the graph. (2 marks) (d) State two precautions for reliable readings. (2 marks)",
            listOf(
                "States that extension is directly proportional to applied force within the limit of proportionality.",
                "Links the relationship to Hooke's law, F = kx.",
                "Describes a straight line through the origin for load plotted against extension within the proportional region.",
                "States that the gradient of load against extension is the spring constant k.",
                "Uses consistent SI units so k is in N m⁻¹.",
                "Gives two precautions such as reading the pointer at eye level, adding loads gently, waiting for oscillations to stop, avoiding loads beyond the elastic limit or repeating readings."
            ),
            "The gradient is k only when load (force) is plotted on the vertical axis against extension on the horizontal axis. If the axes are reversed, the gradient is 1/k."
        )
    )
}
