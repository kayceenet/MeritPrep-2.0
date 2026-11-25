package com.kaycee.meritprep

import com.kaycee.meritprep.data.PracticalGraphPoint

data class PhysicsWaecPracticalTask(
    val id: String,
    val title: String,
    val aim: String,
    val apparatus: List<String>,
    val independentVariable: String,
    val dependentVariable: String,
    val controlledVariables: List<String>,
    val xAxisLabel: String,
    val yAxisLabel: String,
    val readings: List<PracticalGraphPoint>,
    val taskPrompt: String,
    val workedInterpretation: String,
    val examinerPoints: List<String>
)

/** Original WAEC-style practical-skill exercises. These are not official past questions. */
object PhysicsWaecPracticalBank {
    val tasks = listOf(
        PhysicsWaecPracticalTask(
            id = "waec-physics-hooke",
            title = "Load and extension of a spring",
            aim = "Investigate how the extension of a spring changes as an increasing load is applied, and estimate the spring constant in the linear region.",
            apparatus = listOf("Helical spring", "Retort stand and clamp", "Mass hanger and slotted masses", "Metre rule", "Pointer and set square"),
            independentVariable = "Extension x (m)",
            dependentVariable = "Applied force F (N)",
            controlledVariables = listOf("Use the same spring throughout", "Add masses gently and avoid oscillation when taking readings", "Measure extension from the same reference point"),
            xAxisLabel = "Extension, x (m)",
            yAxisLabel = "Force, F (N)",
            readings = listOf(
                PracticalGraphPoint(0.010, 0.49),
                PracticalGraphPoint(0.020, 0.98),
                PracticalGraphPoint(0.030, 1.47),
                PracticalGraphPoint(0.040, 1.96),
                PracticalGraphPoint(0.050, 2.45)
            ),
            taskPrompt = "Use the readings to plot F against x. Find the gradient of the best-fit line and state what physical quantity it represents. Explain why the spring should not be loaded beyond its elastic limit.",
            workedInterpretation = "The graph should be approximately a straight line through the origin in the Hooke's-law region. Its gradient ΔF/Δx is the spring constant k. For the supplied readings, k is approximately 49 N m⁻¹. If the graph begins to curve, the spring is no longer behaving proportionally over that range.",
            examinerPoints = listOf(
                "Label both axes with the correct quantity and SI unit",
                "Choose a uniform scale that uses most of the graph paper",
                "Plot each point accurately and draw a single best-fit straight line",
                "Use a large gradient triangle and show ΔF/Δx with units N m⁻¹",
                "Read extension at eye level and avoid parallax",
                "Add loads gently and wait for oscillations to stop",
                "Do not exceed the elastic limit; remove masses carefully"
            )
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-pendulum",
            title = "Simple pendulum and gravitational field strength",
            aim = "Investigate the relationship between the length of a simple pendulum and the square of its period, then estimate gravitational field strength.",
            apparatus = listOf("Retort stand and clamp", "Light inextensible string", "Small dense bob", "Metre rule", "Stopwatch"),
            independentVariable = "Pendulum length L (m)",
            dependentVariable = "Period squared T² (s²)",
            controlledVariables = listOf("Use a small angular displacement", "Use the same bob and string", "Time the same number of complete oscillations for each length"),
            xAxisLabel = "Length, L (m)",
            yAxisLabel = "Period squared, T² (s²)",
            readings = listOf(
                PracticalGraphPoint(0.40, 1.61),
                PracticalGraphPoint(0.50, 2.02),
                PracticalGraphPoint(0.60, 2.42),
                PracticalGraphPoint(0.70, 2.82),
                PracticalGraphPoint(0.80, 3.22)
            ),
            taskPrompt = "The listed T² values are obtained after timing repeated oscillations and calculating T = t/N. Plot T² against L, determine the gradient and use T² = (4π²/g)L to estimate g.",
            workedInterpretation = "For a small-angle simple pendulum, T² is proportional to L. The gradient of T² against L is 4π²/g. The supplied readings give a gradient close to 4.0 s² m⁻¹, so g = 4π²/gradient is approximately 9.9 m s⁻².",
            examinerPoints = listOf(
                "Measure length from the point of suspension to the centre of the bob",
                "Time at least 10 complete oscillations and divide by the number timed",
                "Repeat timing and use a mean to reduce reaction-time effects",
                "Keep the angular displacement small and release without a push",
                "Plot T² against L with correct units and a best-fit line",
                "Use the gradient 4π²/g and rearrange to find g",
                "Avoid parallax when measuring length and keep the stand stable"
            )
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-ohm",
            title = "Current-voltage relationship of a resistor",
            aim = "Investigate the relationship between current through a resistor and the potential difference across it, and estimate its resistance.",
            apparatus = listOf("Resistor", "DC power supply or cells", "Ammeter", "Voltmeter", "Rheostat", "Switch", "Connecting leads"),
            independentVariable = "Current I (A)",
            dependentVariable = "Potential difference V (V)",
            controlledVariables = listOf("Use the same resistor", "Keep the resistor temperature as steady as practical", "Use correct meter ranges and polarity"),
            xAxisLabel = "Current, I (A)",
            yAxisLabel = "Voltage, V (V)",
            readings = listOf(
                PracticalGraphPoint(0.10, 0.50),
                PracticalGraphPoint(0.20, 1.01),
                PracticalGraphPoint(0.30, 1.49),
                PracticalGraphPoint(0.40, 2.02),
                PracticalGraphPoint(0.50, 2.49)
            ),
            taskPrompt = "Plot V against I. Determine the gradient and state the resistance of the resistor. Comment on whether the data support Ohm's law and give two precautions.",
            workedInterpretation = "For an ohmic resistor at approximately constant temperature, V = IR. The gradient of a V-I graph is resistance R. The supplied readings give a gradient close to 5.0 Ω and show an approximately linear relationship.",
            examinerPoints = listOf(
                "Connect the ammeter in series and the voltmeter in parallel",
                "Check meter polarity and select suitable ranges",
                "Increase current in steps and record paired V and I readings",
                "Plot voltage on the vertical axis and current on the horizontal axis",
                "Use the gradient ΔV/ΔI to obtain resistance in ohms",
                "Open the switch between readings to limit heating",
                "Avoid loose connections and read analogue scales at eye level"
            )
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-density",
            title = "Density from mass-volume readings",
            aim = "Investigate the relationship between mass and volume for samples of the same material and estimate the material's density.",
            apparatus = listOf("Digital or beam balance", "Set of same-material solid samples of different volumes", "Measuring cylinder or vernier caliper", "Thread for displacement method", "Water where displacement is required"),
            independentVariable = "Volume V (cm³)",
            dependentVariable = "Mass m (g)",
            controlledVariables = listOf("Use samples made from the same uniform material", "Use the same balance and volume-measurement method", "Remove trapped air bubbles during displacement readings"),
            xAxisLabel = "Volume, V (cm³)",
            yAxisLabel = "Mass, m (g)",
            readings = listOf(
                PracticalGraphPoint(10.0, 27.0),
                PracticalGraphPoint(20.0, 54.1),
                PracticalGraphPoint(30.0, 80.8),
                PracticalGraphPoint(40.0, 108.2),
                PracticalGraphPoint(50.0, 134.9)
            ),
            taskPrompt = "Plot mass m against volume V. Determine the gradient and state the physical quantity represented. Explain how you would measure the volume of an irregular sample and state two precautions.",
            workedInterpretation = "For samples of the same uniform material, m = ρV. The gradient of a mass-volume graph is density. The supplied readings give a gradient close to 2.70 g cm⁻³. For an irregular solid that sinks and does not dissolve, its volume can be found by water displacement in a measuring cylinder or displacement can.",
            examinerPoints = listOf(
                "Record mass and volume with correct units and consistent precision",
                "Use the same material for every sample",
                "Plot mass on the vertical axis and volume on the horizontal axis",
                "Draw a best-fit line and calculate gradient Δm/ΔV",
                "State density with the correct unit, such as g cm⁻³",
                "Read the measuring cylinder at eye level and use the meniscus correctly",
                "Remove trapped air bubbles and ensure the sample is fully immersed without touching the vessel"
            )
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-resistivity",
            title = "Resistance and length of a wire",
            aim = "Investigate how the resistance of a uniform wire depends on its length and estimate its resistivity.",
            apparatus = listOf("Uniform resistance wire", "Metre rule", "Ammeter", "Voltmeter", "DC supply", "Rheostat", "Switch and connecting leads", "Micrometer screw gauge"),
            independentVariable = "Wire length L (m)",
            dependentVariable = "Resistance R (Ω)",
            controlledVariables = listOf("Use the same wire material and diameter", "Keep current low and open the switch between readings to limit heating", "Use the same meter ranges and connection arrangement"),
            xAxisLabel = "Length, L (m)",
            yAxisLabel = "Resistance, R (Ω)",
            readings = listOf(
                PracticalGraphPoint(0.20, 0.0070),
                PracticalGraphPoint(0.40, 0.0141),
                PracticalGraphPoint(0.60, 0.0209),
                PracticalGraphPoint(0.80, 0.0281),
                PracticalGraphPoint(1.00, 0.0350)
            ),
            taskPrompt = "Plot resistance R against wire length L. Find the gradient. If the wire's cross-sectional area is 0.50 mm², calculate its resistivity using R = ρL/A. Give the SI unit and state two precautions.",
            workedInterpretation = "For a uniform wire, R = ρL/A. The gradient of R against L is ρ/A. The supplied readings give a gradient close to 0.035 Ω m⁻¹. The cross-sectional area 0.50 mm² = 5.0 × 10⁻⁷ m², so ρ = gradient × A ≈ 1.75 × 10⁻⁸ Ω m.",
            examinerPoints = listOf(
                "Measure wire length from the correct reference points",
                "Measure diameter at several positions and in two perpendicular directions",
                "Calculate cross-sectional area using A = πd²/4 and convert to m²",
                "Connect the ammeter in series and voltmeter in parallel",
                "Calculate resistance R = V/I for each length",
                "Plot R against L and use gradient = ρ/A",
                "Open the switch between readings to limit heating and keep the wire taut but not stretched"
            )
        )

        ,
        PhysicsWaecPracticalTask(
            id = "waec-physics-moments",
            title = "Principle of moments and a balanced rule",
            aim = "Investigate how moment depends on the balancing force at a fixed perpendicular distance from a pivot, and use the graph to determine that distance.",
            apparatus = listOf("Metre rule", "Knife edge or triangular prism pivot", "Mass hanger", "Slotted masses", "Thread and balance"),
            independentVariable = "Balancing force F (N)", dependentVariable = "Moment M (N m)",
            controlledVariables = listOf("Keep the pivot position fixed", "Keep the force line of action at a perpendicular distance of 0.20 m", "Measure perpendicular distances from the pivot"),
            xAxisLabel = "Balancing force, F (N)", yAxisLabel = "Moment, M (N m)",
            readings = listOf(PracticalGraphPoint(1.0, 0.20), PracticalGraphPoint(2.0, 0.40), PracticalGraphPoint(3.0, 0.60), PracticalGraphPoint(4.0, 0.80), PracticalGraphPoint(5.0, 1.00)),
            taskPrompt = "The balancing force acts at a perpendicular distance of 0.20 m from the pivot. Calculate each moment using M = Fd and plot M against F. Determine the gradient and explain what it represents. State the conditions for rotational equilibrium and two precautions when balancing the rule.",
            workedInterpretation = "Moment is force multiplied by the perpendicular distance from the pivot. Since M = Fd, the gradient of M against F is the perpendicular distance d. The supplied data give 0.20 m. At equilibrium, clockwise and anticlockwise moments balance and the resultant force is zero.",
            examinerPoints = listOf("Measure distances from the pivot to each line of action", "Use perpendicular distance, not distance along an inclined force", "Include the weight of the rule if its centre of gravity contributes", "Wait for the rule to become horizontal and stationary", "Record force in newtons and moment in newton metres", "Use a best-fit line and state the gradient unit", "Avoid parallax when reading the metre rule")
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-focal-length",
            title = "Focal length of a converging lens",
            aim = "Use object and image distances to verify the thin-lens relationship and estimate the focal length of a converging lens.",
            apparatus = listOf("Converging lens and holder", "Illuminated object", "White screen", "Metre rule or optical bench"),
            independentVariable = "Reciprocal object distance, 1/u (cm⁻¹)", dependentVariable = "Reciprocal image distance, 1/v (cm⁻¹)",
            controlledVariables = listOf("Use the same lens throughout", "Keep the object and lens aligned on the principal axis", "Measure distances from the optical centre of the lens"),
            xAxisLabel = "1/u (cm⁻¹)", yAxisLabel = "1/v (cm⁻¹)",
            readings = listOf(PracticalGraphPoint(0.0250, 0.0750), PracticalGraphPoint(0.0333, 0.0667), PracticalGraphPoint(0.0400, 0.0600), PracticalGraphPoint(0.0500, 0.0500), PracticalGraphPoint(0.0667, 0.0333)),
            taskPrompt = "Plot 1/v against 1/u. Use 1/f = 1/u + 1/v to interpret the gradient and vertical intercept, then estimate the focal length. State why a real image must be formed on the screen and give two precautions.",
            workedInterpretation = "For a thin converging lens using positive real-object and real-image distances, 1/v = -1/u + 1/f. The graph of 1/v against 1/u therefore has gradient approximately -1 and vertical intercept 1/f. The supplied intercept is approximately 0.100 cm⁻¹, giving f approximately 10.0 cm.",
            examinerPoints = listOf("Measure u and v from the optical centre of the lens", "Obtain a sharp image before recording each pair", "Keep the object, lens and screen at the same height", "Use reciprocal distances with units cm⁻¹", "Plot 1/v vertically against 1/u horizontally", "Interpret the negative gradient and positive intercept correctly", "Avoid reading distances from the edge of the lens holder")
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-specific-heat",
            title = "Specific heat capacity by electrical heating",
            aim = "Investigate the relationship between electrical energy supplied and temperature rise, then estimate the specific heat capacity of a sample.",
            apparatus = listOf("Insulated metal block with heater", "Thermometer", "Balance", "Stopwatch", "DC power supply", "Ammeter and voltmeter", "Connecting leads"),
            independentVariable = "Temperature rise, ΔT (K)", dependentVariable = "Electrical energy, E (J)",
            controlledVariables = listOf("Use the same block and heater", "Keep the insulation and thermometer position unchanged", "Use the same electrical connections and record voltage and current"),
            xAxisLabel = "Temperature rise, ΔT (K)", yAxisLabel = "Electrical energy, E (J)",
            readings = listOf(PracticalGraphPoint(2.0, 1680.0), PracticalGraphPoint(4.0, 3360.0), PracticalGraphPoint(6.0, 5040.0), PracticalGraphPoint(8.0, 6720.0), PracticalGraphPoint(10.0, 8400.0)),
            taskPrompt = "Calculate electrical energy using E = VIt for each interval and plot E against ΔT. If the block mass is 0.20 kg, use the gradient to estimate its specific heat capacity. Explain why the measured value may exceed the ideal value.",
            workedInterpretation = "Ignoring heat losses, E = mcΔT. The gradient of E against ΔT is mc. The supplied readings give a gradient of 840 J K⁻¹. For a block mass of 0.20 kg, c = gradient/m = 4200 J kg⁻¹ K⁻¹. In a real experiment some energy heats the surroundings, heater and thermometer, so the simple calculation may overestimate the block's specific heat capacity.",
            examinerPoints = listOf("Measure the block mass in kilograms", "Record voltage, current and heating time for each interval", "Calculate energy using E = VIt with consistent SI units", "Allow temperature to become uniform where appropriate", "Use insulation to reduce heat loss", "Plot energy against temperature rise and calculate the gradient", "Divide the gradient by mass and state J kg⁻¹ K⁻¹")
        ),
        PhysicsWaecPracticalTask(
            id = "waec-physics-parallel-resistors",
            title = "Equivalent resistance of parallel resistors",
            aim = "Investigate the current-voltage relationship of two resistors connected in parallel and determine their equivalent resistance.",
            apparatus = listOf("Two resistors", "DC power supply", "Ammeter", "Voltmeter", "Rheostat", "Switch", "Connecting leads"),
            independentVariable = "Total current I (A)", dependentVariable = "Potential difference V (V)",
            controlledVariables = listOf("Keep the same two resistors in parallel", "Keep resistor temperature as steady as practical", "Use the same meter ranges and connection arrangement"),
            xAxisLabel = "Total current, I (A)", yAxisLabel = "Voltage, V (V)",
            readings = listOf(PracticalGraphPoint(0.10, 0.20), PracticalGraphPoint(0.20, 0.40), PracticalGraphPoint(0.30, 0.60), PracticalGraphPoint(0.40, 0.80), PracticalGraphPoint(0.50, 1.00)),
            taskPrompt = "Plot V against total current I. Determine the gradient and hence the equivalent resistance. For 6 Ω and 3 Ω resistors in parallel, compare your result with 1/R = 1/R₁ + 1/R₂. State two circuit precautions.",
            workedInterpretation = "The gradient of a V-I graph is the equivalent resistance. The supplied data give approximately 2.0 Ω. The theoretical parallel combination is 1/R = 1/6 + 1/3 = 1/2, so R = 2.0 Ω. Real measurements may differ because of resistor tolerance, lead resistance and heating.",
            examinerPoints = listOf("Connect the ammeter in series with the parallel network", "Connect the voltmeter across the complete parallel combination", "Check polarity and meter ranges before closing the switch", "Record paired total-current and voltage readings", "Plot V vertically against I horizontally", "Use the gradient to determine equivalent resistance", "Open the switch between readings to reduce heating")
        )
    )
}
