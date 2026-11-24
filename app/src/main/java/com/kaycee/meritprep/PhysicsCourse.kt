package com.kaycee.meritprep

/**
 * JAMB UTME Physics syllabus map based on the Physics document served by JAMB IBASS.
 * Topic outlines are not presented as completed lessons. Draft teaching content requires
 * independent review by a qualified Physics teacher before academic approval.
 */
object PhysicsCourse {
    const val SYLLABUS_VERSION = "JAMB-IBASS-Physics-39-topic-map"
    const val SOURCE_URL = "https://ibass.jamb.gov.ng/assets/uploads/Physics.pdf"

    private fun outline(id: String, title: String, scope: String) = LearningTopic(
        id = id,
        title = title,
        subjectCode = "PHY",
        introduction = "This is a mapped topic in the JAMB UTME Physics syllabus. The outline identifies the main area to study; the full beginner lesson and assessment set are still being developed.",
        objectives = listOf("Identify the key physical quantities and principles in this topic", "Explain the relevant physical ideas using correct terms", "Apply the appropriate relationships, units and diagrams to problems"),
        sections = listOf(
            "Syllabus scope" to scope,
            "Learning status" to "This entry is a syllabus outline, not a completed lesson. A full lesson must explain prerequisite ideas, define every new term, work through examples with units, address common misconceptions and include reviewed formative assessment."
        ),
        checkQuestion = "What is the academic status of this entry?",
        checkOptions = listOf("A fully reviewed lesson", "A syllabus outline awaiting full lesson development", "An official past examination paper", "A validated grade predictor"),
        correctOption = 1,
        explanation = "The entry maps the syllabus scope. It must not be marked complete or described as a fully developed lesson.",
        isSyllabusOutline = true
    )

    private val syllabusEntries: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-measurements", "Measurements and units", "PHY",
            "Physics begins with measurement. When a mechanic checks the length of a bolt, a nurse reads a thermometer or an electrician measures current, the result is meaningful only when the quantity, unit and measuring method are clear. This lesson starts with the language of measurement and builds towards accuracy, dimensions and simple experimental errors.",
            listOf("Distinguish fundamental and derived physical quantities", "State common SI base units and derived units", "Choose suitable instruments for length, mass, time and volume", "Use significant figures and standard form correctly", "Explain instrument resolution, accuracy and simple measurement error", "Use dimensions to check simple equations"),
            listOf(
                "A physical quantity needs a number and a unit" to "A measurement is written as a numerical value multiplied by a unit. Saying a wire is 2.0 is incomplete; saying it is 2.0 metres gives the quantity a usable meaning. The number tells us how many units, while the unit tells us the scale being used.",
                "Fundamental and derived quantities" to "The SI base quantities include length (metre, m), mass (kilogram, kg), time (second, s), electric current (ampere, A), thermodynamic temperature (kelvin, K), amount of substance (mole, mol) and luminous intensity (candela, cd). A derived quantity is formed from base quantities. Speed has unit m s⁻¹, acceleration m s⁻², force newton (N = kg m s⁻²), and pressure pascal (Pa = N m⁻²).",
                "Choose the measuring instrument" to "A metre rule is suitable for ordinary lengths; a vernier caliper measures external diameter, internal diameter and depth more precisely; a micrometer screw gauge measures small thicknesses or diameters. A beam balance compares masses, a stopwatch measures time, and a measuring cylinder measures liquid volume. Choose an instrument whose range and resolution suit the object. Read a scale at eye level to reduce parallax error.",
                "Resolution, accuracy and error" to "Resolution is the smallest change an instrument can show. Accuracy describes closeness to the accepted value; precision describes how closely repeated readings agree. A set of readings can be precise but inaccurate if the instrument has a zero error. For a simple repeated measurement, the mean is the sum of readings divided by the number of readings. Percentage error is (absolute error ÷ measured or accepted value) × 100%, according to the convention specified in the question.",
                "Significant figures and standard form" to "Significant figures communicate the precision of a measured or calculated value. Leading zeros are not significant: 0.0042 has two significant figures. Trailing zeros after a decimal point may be significant: 2.500 has four. Standard form writes a number as a × 10ⁿ, where 1 ≤ a < 10; for example, 450000 = 4.5 × 10⁵.",
                "Dimensions as a consistency check" to "Dimensions express a quantity in terms of base dimensions such as mass [M], length [L] and time [T]. Speed has dimension [LT⁻¹], acceleration [LT⁻²], and force [MLT⁻²]. In a physically meaningful equation, the dimensions on both sides must agree. Dimensional agreement is necessary but does not prove that an equation is correct; it cannot determine a missing dimensionless numerical constant."
            ),
            "Which instrument is most suitable for measuring the diameter of a thin wire accurately?",
            listOf("Metre rule", "Micrometer screw gauge", "Stopwatch", "Beam balance"), 1,
            "A micrometer screw gauge is designed to measure small thicknesses and diameters with finer resolution than a metre rule."
        ),
        LearningTopic(
            "phy-vectors", "Scalars and vectors", "PHY",
            "Some physical quantities need only a size; others need a size and a direction. If a bus travels at 60 km/h, its speed is stated. If it travels at 60 km/h north, its velocity is stated. This difference is the starting point for vectors, which help us describe forces, motion and fields.",
            listOf("Define scalar and vector quantities", "Classify common physical quantities", "Add vectors in one dimension and at right angles", "Resolve a vector into perpendicular components", "Interpret direction and resultant displacement"),
            listOf(
                "Scalar quantities" to "A scalar has magnitude only. Examples include mass, time, temperature, distance, speed, energy and volume. Scalars can be added using ordinary arithmetic when their units and meanings are compatible.",
                "Vector quantities" to "A vector has magnitude and direction. Examples include displacement, velocity, acceleration, force and momentum. A vector is often drawn as an arrow: its length represents magnitude using a chosen scale, and its arrowhead shows direction. Two vectors are equal only when they have the same magnitude and direction.",
                "Adding vectors" to "For vectors along one straight line, choose a positive direction. Add vectors pointing in that direction and subtract vectors pointing opposite to it. For perpendicular vectors A and B, the resultant magnitude is R = √(A² + B²), and its angle to A is θ = tan⁻¹(B/A). State the reference direction with the angle.",
                "Resolving a vector" to "A vector F at angle θ above the positive horizontal has horizontal component Fₓ = F cos θ and vertical component Fᵧ = F sin θ. Components are signed according to their directions. Resolving a force into perpendicular components makes equilibrium and motion problems easier because horizontal and vertical conditions can be treated separately.",
                "Worked example: a right-angle journey" to "A learner walks 3 m east and then 4 m north. The displacement is the straight-line vector from the starting point to the finishing point. Its magnitude is √(3² + 4²) = 5 m. Its direction is tan⁻¹(4/3), about 53.1° north of east. The distance travelled is 3 + 4 = 7 m, which is not the displacement magnitude.",
                "Common misconception" to "Distance and displacement are not interchangeable. Distance is the total path length and is scalar. Displacement is the change in position from start to finish and is vector. A person who completes one lap of a track has travelled a non-zero distance but has zero displacement if they finish at the starting point."
            ),
            "A student walks 3 m east and 4 m north. What is the magnitude of the displacement?",
            listOf("1 m", "5 m", "7 m", "12 m"), 1,
            "The perpendicular components form a 3–4–5 right triangle, so displacement = √(3² + 4²) = 5 m."
        ),
        LearningTopic(
            "phy-motion", "Motion", "PHY",
            "A moving vehicle changes its position with time. Physics describes that change using distance, displacement, speed, velocity and acceleration. We then use graphs and equations to predict motion, explain falling objects and understand how forces change movement.",
            listOf("Distinguish distance, displacement, speed and velocity", "Calculate acceleration from velocity change and time", "Use the equations of uniformly accelerated motion", "Interpret distance-time and velocity-time graphs", "Explain Newton's laws, momentum and impulse", "Describe projectile and circular motion at an introductory level"),
            listOf(
                "Describe motion before choosing an equation" to "Distance is the total path length; displacement is the change in position. Average speed = total distance ÷ total time. Average velocity = displacement ÷ time and has direction. Acceleration is the rate of change of velocity: a = (v − u)/t, where u is initial velocity, v is final velocity and t is elapsed time.",
                "Uniform acceleration equations" to "For motion in a straight line with constant acceleration, the useful equations are v = u + at; s = ut + ½at²; v² = u² + 2as; and s = ½(u + v)t. Here s is displacement. These equations are not automatically valid when acceleration changes. Choose an equation that contains the known quantities and the required unknown, then substitute values with consistent units.",
                "Worked example" to "A car starts from rest and accelerates uniformly at 2 m s⁻² for 5 s. Here u = 0, a = 2 m s⁻² and t = 5 s. Its final velocity is v = u + at = 0 + (2 × 5) = 10 m s⁻¹. Its displacement is s = ut + ½at² = 0 + ½ × 2 × 25 = 25 m.",
                "Read motion graphs" to "On a displacement-time graph, the gradient represents velocity. A horizontal section means position is not changing. On a velocity-time graph, the gradient represents acceleration and the signed area between the graph and the time axis represents displacement. A graph below the time axis represents velocity in the negative direction, not necessarily a negative speed.",
                "Newton's laws and momentum" to "Newton's first law describes inertia: an object remains at rest or in uniform straight-line motion unless acted on by a resultant external force. The second law gives resultant force F = ma for constant mass in the usual school-level treatment. The third law says interacting bodies exert equal and opposite forces on each other; the forces act on different bodies. Momentum p = mv. Impulse equals change in momentum and is also the area under a force-time graph.",
                "Gravity, projectiles and circular motion" to "Near Earth's surface, a freely falling object has downward acceleration g, approximately 9.8 m s⁻² where the question does not specify another value. Neglecting air resistance, horizontal and vertical projectile motions can be analysed independently. In uniform circular motion, velocity direction changes continuously, so centripetal acceleration is directed towards the centre and has magnitude v²/r."
            ),
            "A car starts from rest and accelerates uniformly at 2 m s⁻² for 5 s. What is its final speed?",
            listOf("2 m s⁻¹", "5 m s⁻¹", "10 m s⁻¹", "25 m s⁻¹"), 2,
            "Use v = u + at. With u = 0, a = 2 m s⁻² and t = 5 s, v = 10 m s⁻¹."
        ),
        LearningTopic(
            "phy-gravity", "Gravitational field", "PHY",
            "An object falls when released because Earth exerts a gravitational force on it. Gravity also keeps the Moon in orbit and planets moving around the Sun. This lesson distinguishes mass from weight and introduces the universal law that describes gravitational attraction between masses.",
            listOf("State Newton's law of universal gravitation", "Distinguish gravitational field strength, mass and weight", "Calculate weight using W = mg", "Explain gravitational potential and escape velocity qualitatively", "Describe why g varies with location"),
            listOf(
                "Universal gravitation" to "Any two masses attract each other. For two spherical bodies treated as point masses or spherically symmetric bodies, the force magnitude is F = Gm₁m₂/r², where G is the universal gravitational constant and r is the distance between their centres. The force acts along the line joining their centres and is attractive.",
                "Mass is not weight" to "Mass is a measure of inertia and is measured in kilograms. Weight is the gravitational force on a body: W = mg, measured in newtons. A person's mass is approximately unchanged when travelling to the Moon, but their weight is smaller because the Moon's gravitational field strength is lower.",
                "Gravitational field strength" to "Gravitational field strength g is force per unit mass, so g = F/m. Near Earth's surface, g is approximately 9.8 N kg⁻¹, numerically equal to 9.8 m s⁻². Its value varies with altitude, latitude and local geological conditions. In simple questions, use the value supplied or the stated approximation.",
                "Potential and escape velocity" to "Gravitational potential at a point is the work done per unit mass in bringing a small test mass from infinity to that point, with the usual zero reference at infinity. Escape velocity is the minimum launch speed needed to escape a body's gravitational field without further propulsion, neglecting air resistance and other bodies. For a spherical body, vₑ = √(2GM/R) at its surface.",
                "Worked example" to "A 5 kg object is near Earth's surface where g = 9.8 N kg⁻¹. Its weight is W = mg = 5 × 9.8 = 49 N downward. Do not write 49 kg: kilograms are units of mass, while newtons are units of force."
            ),
            "What is the weight of a 5 kg object where g = 10 N kg⁻¹?",
            listOf("0.5 N", "5 N", "50 N", "500 N"), 2,
            "Weight W = mg = 5 kg × 10 N kg⁻¹ = 50 N."
        ),
        LearningTopic(
            "phy-equilibrium", "Equilibrium of forces", "PHY",
            "A sign hanging from a ceiling may remain still even though several forces act on it. It is not force-free; the forces balance. Physics uses vector addition and the principle of moments to decide when particles and rigid bodies are in equilibrium.",
            listOf("State the conditions for equilibrium of a particle", "Resolve coplanar forces into perpendicular components", "Apply the principle of moments", "Distinguish centre of gravity and stability", "Use Lami's theorem for three concurrent forces in equilibrium"),
            listOf(
                "Equilibrium of a particle" to "A particle is in translational equilibrium when the resultant force is zero. In two dimensions, ΣFₓ = 0 and ΣFᵧ = 0. This means the total force to the right balances the total force to the left, and the total upward force balances the total downward force. An object can move at constant velocity and still be in equilibrium.",
                "Resolve forces before balancing them" to "A force at an angle can be split into horizontal and vertical components. If a 10 N force acts at 30° above the horizontal, its components are 10 cos 30° horizontally and 10 sin 30° vertically. Include signs and use the same axis directions for every force before applying the equilibrium conditions.",
                "Moments and turning effect" to "The moment of a force about a point is force multiplied by the perpendicular distance from the point to the force's line of action: moment = Fd. Its SI unit is N m. For a rigid body in rotational equilibrium, the sum of clockwise moments about a point equals the sum of anticlockwise moments about that same point, and the resultant force must also be zero.",
                "Centre of gravity and stability" to "The centre of gravity is the point through which the resultant weight of a body may be considered to act. A body is generally more stable when it has a low centre of gravity and a wide base. Stable equilibrium returns towards the original position after a small displacement; unstable equilibrium moves farther away; neutral equilibrium remains in its new position.",
                "Lami's theorem" to "For a particle in equilibrium under exactly three coplanar, concurrent and non-parallel forces, each force is proportional to the sine of the angle between the other two: F₁/sin α = F₂/sin β = F₃/sin γ. The angle opposite a force is the angle between the other two forces. Do not apply the theorem to four forces or to non-concurrent forces."
            ),
            "A uniform metre rule balances on a pivot when clockwise and anticlockwise moments are:",
            listOf("Unequal", "Equal", "Both zero in every case", "Unrelated to force"), 1,
            "Rotational equilibrium requires the sum of clockwise moments to equal the sum of anticlockwise moments about the pivot."
        ),
        LearningTopic(
            "phy-work-energy", "Work, energy and power", "PHY",
            "Pushing a loaded trolley, lifting a bag and running a generator all involve energy transfer. Physics gives precise meanings to work, energy and power, allowing us to calculate how much energy is transferred and how quickly it is transferred.",
            listOf("Define work, energy and power", "Calculate work done by a constant force", "Distinguish kinetic and gravitational potential energy", "Apply conservation of mechanical energy in simple systems", "Interpret the area under a force-displacement graph", "Compare renewable and non-renewable energy sources"),
            listOf(
                "Work is energy transferred by a force" to "For a constant force F acting through displacement s at angle θ to the displacement, work done is W = Fs cos θ. The unit is the joule (J). If the force acts in the direction of movement, θ = 0° and W = Fs. If the force is perpendicular to the displacement, it does zero work on the object.",
                "Kinetic and potential energy" to "Kinetic energy is energy due to motion: Eₖ = ½mv². Near Earth's surface, gravitational potential energy relative to a chosen level is Eₚ = mgh. The reference level for gravitational potential energy is chosen for convenience; state it when interpreting a problem.",
                "Conservation of energy" to "Energy is not created or destroyed in an isolated system; it is transferred or transformed. If air resistance and friction are negligible, the sum of kinetic and gravitational potential energy remains constant. When non-conservative forces act, some mechanical energy is transferred to internal energy, sound or other forms; total energy is still conserved.",
                "Power and efficiency" to "Power is the rate of energy transfer or work done: P = W/t = E/t. Its SI unit is the watt (W), equal to one joule per second. Efficiency = useful output energy ÷ total input energy × 100%. A device cannot be more than 100% efficient in the ordinary energy-accounting model.",
                "Force-displacement graphs and energy choices" to "The area under a force-displacement graph gives the work done by the force. Energy sources include fossil fuels, hydroelectricity, wind, solar and nuclear energy. Renewable sources are replenished on human timescales, but they still have environmental and engineering impacts. Evaluate energy options using reliability, cost, storage, land use, emissions and local conditions."
            ),
            "A 20 N force moves a box 3 m in the same direction as the force. How much work is done?",
            listOf("6 J", "17 J", "23 J", "60 J"), 3,
            "W = Fs cos 0° = 20 × 3 = 60 J."
        ),
        LearningTopic(
            "phy-friction", "Friction", "PHY",
            "A vehicle needs friction between its tyres and the road to accelerate and brake. Yet friction in a machine's moving parts can waste energy and cause wear. Friction is not simply good or bad; its effect depends on the task and the surfaces in contact.",
            listOf("Distinguish static and kinetic friction", "Explain limiting friction and coefficient of friction", "Identify useful and harmful effects of friction", "Describe methods of reducing friction", "Explain viscosity and terminal velocity qualitatively"),
            listOf(
                "What friction does" to "Friction is a contact force that opposes relative motion or the tendency of relative motion between surfaces in contact. Static friction acts when surfaces do not slide relative to one another. Kinetic or sliding friction acts when surfaces slide. Friction acts parallel to the contact surfaces and opposes the relative motion or its tendency.",
                "Limiting friction and coefficient" to "As a horizontal force tries to move a stationary block, static friction adjusts to balance it up to a maximum called limiting friction. In the simple dry-friction model, limiting friction Fₘₐₓ = μR, where R is the normal reaction and μ is the coefficient of friction. The coefficient is dimensionless. Kinetic friction is often approximated by μₖR in elementary problems.",
                "Useful and harmful friction" to "Friction allows walking, tyre grip, writing with a pencil and braking. It can also cause heating, noise, energy loss and wear in bearings, engines and gears. Lubricants, ball bearings, polishing and suitable material selection can reduce friction where it is unwanted. Treads and rough surfaces can increase grip where it is needed.",
                "Viscosity and terminal velocity" to "Viscosity describes a fluid's resistance to flow. A small sphere falling through a viscous fluid experiences weight downward, upthrust and viscous drag upward. As its speed increases, drag increases. Terminal velocity is reached when the resultant force becomes zero, so acceleration stops and the sphere continues at constant speed. Stokes' law for a small sphere moving slowly through a viscous fluid is F = 6πηrv under its stated laminar-flow conditions."
            ),
            "A block is just about to slide when the friction force reaches its:",
            listOf("Weight", "Limiting value", "Zero value", "Volume"), 1,
            "At the point where sliding is about to begin, static friction has reached its maximum or limiting value."
        ),
        LearningTopic(
            "phy-machines", "Simple machines", "PHY",
            "A lever helps lift a heavy load, a pulley changes the direction of a force and a screw converts rotation into linear motion. Simple machines make tasks easier by changing the size or direction of a force, or the distance over which it acts. They do not create energy.",
            listOf("Identify common simple machines", "Define mechanical advantage and velocity ratio", "Calculate efficiency", "Explain the law of machine", "Relate force gain to distance moved"),
            listOf(
                "Types and purpose" to "Common simple machines include levers, pulleys, inclined planes, wheel and axle, wedges and screws. A machine may multiply force, change the direction of an applied force or transmit movement. The machine trades force against distance; an ideal machine conserves work.",
                "Mechanical advantage" to "Mechanical advantage (MA) is load divided by effort: MA = L/E. It has no unit. A machine with MA greater than one multiplies the applied force, but the effort usually moves through a greater distance than the load.",
                "Velocity ratio" to "Velocity ratio (VR) is distance moved by effort divided by distance moved by load, measured over the same time interval. For an ideal machine, input work equals output work, so MA = VR. In a real machine, friction and deformation mean useful output work is less than input work.",
                "Efficiency and worked example" to "Efficiency = (useful output work ÷ input work) × 100%, or (MA ÷ VR) × 100%. If a machine lifts a 300 N load using a 100 N effort, MA = 300/100 = 3. If its velocity ratio is 4, efficiency = (3/4) × 100% = 75%. The missing 25% of input energy is not destroyed; it is transferred to forms such as heat and sound.",
                "The law of machine" to "For some machines, effort E and load L are related approximately by E = mL + C, where m and C are constants for that machine. The constant C represents the effort needed to overcome friction and other losses when the load is zero. Read the exact relationship given in a question rather than assuming all machines share the same constants."
            ),
            "A machine has mechanical advantage 3 and velocity ratio 4. What is its efficiency?",
            listOf("25%", "50%", "75%", "133%"), 2,
            "Efficiency = (MA/VR) × 100% = (3/4) × 100% = 75%."
        )
    ) + listOf(
        outline("phy-elasticity", "Elasticity", "Elastic limit, yield point, breaking point, Hooke's law, force-extension graphs, Young's modulus, spring balance and elastic potential energy."),
        outline("phy-pressure", "Pressure", "Atmospheric pressure and its measurement; barometers and manometers; pressure variation with height; pressure in liquids; Pascal's principle and applications."),
        outline("phy-liquids-rest", "Liquids at rest", "Density and relative density; upthrust; Archimedes' principle; law of floatation; hydrometers and floating bodies."),
        outline("phy-temperature", "Temperature and its measurement", "Thermometric properties, thermometer calibration, temperature scales, Celsius-to-kelvin conversion and types of thermometers."),
        outline("phy-thermal-expansion", "Thermal expansion", "Linear, area and volume expansivity of solids; volume expansivity of liquids; real and apparent expansion; anomalous expansion of water."),
        outline("phy-gas-laws", "Gas laws", "Boyle's law, Charles's law, pressure law, absolute zero, combined gas law, ideal gas equation and elementary real-gas behaviour."),
        outline("phy-heat-quantity", "Quantity of heat", "Heat capacity, specific heat capacity, methods of measurement, method of mixtures, electrical heating and Newton's law of cooling."),
        outline("phy-change-state", "Change of state", "Latent heat, specific latent heat of fusion and vaporisation, melting, evaporation, boiling and effects of pressure and dissolved substances."),
        outline("phy-vapours", "Vapours", "Saturated and unsaturated vapour, saturated vapour pressure, boiling, dew point, humidity, relative humidity and wet-and-dry-bulb hygrometry."),
        outline("phy-kinetic-theory", "Structure of matter and kinetic theory", "Atoms and molecules; Brownian motion, diffusion, surface tension, capillarity, cohesion and adhesion; assumptions of kinetic theory and gas pressure."),
        outline("phy-heat-transfer", "Heat transfer", "Conduction, convection and radiation; temperature gradient, thermal conductivity, heat flux, surface properties, thermos flasks, land and sea breezes, engines."),
        outline("phy-waves", "Waves", "Wave production and propagation; energy transfer; frequency, wavelength and speed; phase; mechanical and electromagnetic waves; transverse and longitudinal waves; progressive and stationary waves; diffraction, interference, beats and Doppler effect."),
        outline("phy-sound-propagation", "Propagation of sound waves", "Need for a material medium, speed of sound in solids, liquids and air, temperature effects, reflection, echoes and reverberation."),
        outline("phy-sound-characteristics", "Characteristics of sound waves", "Noise and musical notes; pitch, loudness, intensity and quality; strings and air columns; overtones, resonance and frequency of notes."),
        outline("phy-light-energy", "Light energy", "Natural and artificial sources; luminous and non-luminous objects; speed, frequency and wavelength; shadows, eclipses and pinhole cameras."),
        outline("phy-reflection", "Reflection of light at plane and curved surfaces", "Laws of reflection; plane, concave and convex mirrors; ray diagrams; mirror formula, magnification, periscopes, kaleidoscopes and sextants."),
        outline("phy-refraction", "Refraction of light through plane and curved surfaces", "Refraction and speed of light; Snell's law; refractive index; real and apparent depth; lateral displacement; critical angle; total internal reflection; lenses and prisms."),
        outline("phy-optical-instruments", "Optical instruments", "Microscopes, telescopes, projectors, cameras and the human eye; lens power; angular magnification; near and far points; sight defects and correction."),
        outline("phy-dispersion", "Dispersion of light and colours", "Prism dispersion, pure spectra, additive and subtractive colour mixing, colour of objects, filters, rainbows and the electromagnetic spectrum."),
        outline("phy-electrostatics", "Electrostatics", "Positive and negative charge; charging by friction, contact and induction; electroscopes; Coulomb's law; electric field, potential, discharge and lightning."),
        outline("phy-capacitors", "Capacitors", "Capacitance, types and uses; parallel-plate capacitor; factors affecting capacitance; series and parallel combinations; energy stored."),
        outline("phy-cells", "Electric cells", "Simple voltaic cell and defects; Daniell and Leclanché cells; lead-acid, nickel-iron and other listed cells; maintenance; series and parallel arrangements; efficiency."),
        outline("phy-current-electricity", "Current electricity", "EMF, potential difference, current, internal resistance and lost volts; Ohm's law; resistance, resistivity and conductivity; metre bridge; series and parallel networks; potentiometer and Kirchhoff's laws."),
        outline("phy-electrical-power", "Electrical energy and power", "Electrical energy and power; commercial units; power transmission; heating effect; domestic wiring; parallel circuits and fuse ratings."),
        outline("phy-magnetism", "Magnets and magnetic fields", "Natural and artificial magnets; soft iron and steel; magnetisation and demagnetisation; fields of magnets, conductors and solenoids; Earth's magnetic field, flux and flux density."),
        outline("phy-magnetic-force", "Force on a current-carrying conductor in a magnetic field", "Force between parallel currents; force on moving charges; Fleming's left-hand rule; DC motor; electromagnets; moving-coil and moving-iron instruments; galvanometer conversion."),
        outline("phy-induction", "Electromagnetic induction", "Faraday's laws, factors affecting induced EMF, Lenz's law, AC and DC generators, transformers, induction coils, inductance and eddy currents."),
        outline("phy-ac-circuits", "Simple AC circuits", "Alternating current and voltage; peak and RMS values; resistive, capacitive and inductive circuits; reactance, impedance, phase, power factor, series RLC resonance."),
        outline("phy-conduction", "Conduction of electricity through liquids and gases", "Electrolytes and non-electrolytes; electrolysis and Faraday's laws; electroplating; discharge through gases and applications."),
        outline("phy-modern", "Elementary modern physics", "Atomic models and energy levels; spectra; thermionic and photoelectric emission; Einstein's equation; X-rays; radioactivity, half-life, fission, fusion, binding energy, matter waves and uncertainty principle."),
        outline("phy-electronics", "Introductory electronics", "Conductors, semiconductors and insulators; intrinsic and extrinsic semiconductors; n-type and p-type materials; diodes, rectification and transistor amplification.")
    )

    private val developedById = (PhysicsCourseExtension.topics + PhysicsCourseExtension2.topics + PhysicsCourseExtension3.topics + PhysicsCourseExtension4.topics + PhysicsCourseExtension5.topics).associateBy { it.id }

    /** Preserve the official syllabus sequence while replacing outline entries with developed lessons. */
    val topics: List<LearningTopic> = syllabusEntries.map { entry ->
        developedById[entry.id] ?: entry
    }
}
