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

