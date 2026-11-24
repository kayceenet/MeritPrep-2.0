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
        )
    )
}
