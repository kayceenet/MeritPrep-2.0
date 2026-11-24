package com.kaycee.meritprep

/** Second beginner-learning block for the JAMB Physics syllabus. Draft content pending review. */
object PhysicsCourseExtension {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-elasticity", "Elasticity", "PHY",
            "Stretch a spring gently and it becomes longer. Release it and it may return to its original length. Stretch it too far and it may remain permanently deformed or break. Elasticity describes how materials respond to forces and how they recover when the force is removed.",
            listOf("Distinguish elastic and plastic deformation", "State Hooke's law and identify its limit", "Interpret force-extension graphs", "Define stress, strain and Young's modulus", "Calculate elastic potential energy stored in a spring"),
            listOf(
                "Elastic and plastic behaviour" to "A material behaves elastically when it returns to its original shape and size after the deforming force is removed, provided it has not been damaged. Plastic deformation is permanent. The elastic limit is the greatest deformation from which the material can fully recover. The yield point marks the onset of substantial permanent deformation in many materials; the breaking point is where the specimen fractures.",
                "Hooke's law" to "For a spring or wire within its limit of proportionality, extension is directly proportional to the applied force: F = kx. Here F is force in newtons, x is extension in metres and k is the spring constant in N m⁻¹. The limit of proportionality is the point beyond which the force-extension graph is no longer a straight line. Do not apply Hooke's law beyond this region.",
                "Stress, strain and Young's modulus" to "Stress is force per unit cross-sectional area: σ = F/A, measured in pascals. Tensile strain is extension divided by original length: ε = ΔL/L, and has no unit. Young's modulus E = stress/strain = FL/(AΔL), measured in pascals. The simple formula applies to a uniform specimen under the relevant elastic, approximately linear conditions.",
                "Energy stored in a spring" to "For a spring obeying Hooke's law from zero extension to x, elastic potential energy is E = ½Fx = ½kx². This is the area under the force-extension graph. On a graph with force on the vertical axis and extension on the horizontal axis, the gradient of the straight-line region is the spring constant k."
            ),
            "A spring has spring constant 200 N m⁻¹ and is extended by 0.05 m. What force is required within the Hooke's-law region?",
            listOf("4 N", "10 N", "40 N", "4000 N"), 1,
            "F = kx = 200 × 0.05 = 10 N."
        ),
        LearningTopic(
            "phy-pressure", "Pressure", "PHY",
            "A sharp nail enters wood more easily than a blunt one when the same force is applied. The difference is pressure: the force is concentrated over a smaller area. Pressure also explains why water pressure increases with depth and how hydraulic systems transmit force.",
            listOf("Define pressure and state its SI unit", "Calculate pressure from force and area", "Explain atmospheric pressure and common measuring instruments", "Use p = ρgh for pressure due to a liquid column", "Apply Pascal's principle to simple hydraulic systems"),
            listOf(
                "Pressure is force spread over area" to "Pressure is the normal force acting per unit area: p = F/A. Its SI unit is the pascal (Pa), equal to one newton per square metre. For the same force, reducing the contact area increases pressure. This is why a sharp blade cuts more easily than a blunt one.",
                "Atmospheric pressure" to "Air has mass and its weight produces atmospheric pressure. Atmospheric pressure generally decreases as altitude increases because less air lies above a higher location. A mercury barometer measures atmospheric pressure using the height of a mercury column; an aneroid barometer uses deformation of a sealed flexible metal chamber. A manometer compares pressure with a reference pressure using a liquid column.",
                "Pressure in a liquid" to "For a liquid of density ρ at rest, the pressure increase due to a depth h is p = ρgh. This is gauge pressure due to the liquid column; absolute pressure also includes pressure at the surface. At the same depth in the same connected liquid at rest, pressure is the same in all directions. The result assumes uniform density and gravitational field.",
                "Pascal's principle and hydraulics" to "A pressure change applied to an enclosed fluid is transmitted throughout the fluid. In a hydraulic system, F₁/A₁ = F₂/A₂ for ideal static conditions. A larger output piston can provide a larger force, but it moves through a smaller distance. The system does not create energy; it exchanges force for distance."
            ),
            "What is the gauge pressure 2 m below the surface of water of density 1000 kg m⁻³ when g = 10 m s⁻²?",
            listOf("200 Pa", "2000 Pa", "20 000 Pa", "200 000 Pa"), 2,
            "p = ρgh = 1000 × 10 × 2 = 20 000 Pa."
        ),
        LearningTopic(
            "phy-liquids-rest", "Liquids at rest", "PHY",
            "A stone feels lighter when held under water, and a large ship can float even though it is made from dense materials. These observations are explained by upthrust, density and the displacement of fluid.",
            listOf("Calculate density and relative density", "Explain upthrust in terms of pressure difference", "State and apply Archimedes' principle", "Apply the law of floatation", "Explain how hydrometers compare liquid densities"),
            listOf(
                "Density and relative density" to "Density is mass per unit volume: ρ = m/V, measured in kg m⁻³. Relative density is the ratio of the density of a substance to the density of a reference substance, usually water for solids and liquids. Relative density is dimensionless. For an irregular object, use fluid displacement to estimate its volume where appropriate.",
                "Why an immersed object experiences upthrust" to "Fluid pressure increases with depth. The pressure on the lower surface of an immersed object is therefore greater than the pressure on its upper surface, producing a resultant upward force called upthrust or buoyant force. Sideways pressure forces cancel for a fully immersed body in a uniform fluid.",
                "Archimedes' principle" to "A body wholly or partly immersed in a fluid experiences an upthrust equal to the weight of the fluid displaced. Thus U = ρfluid g Vdisplaced. The displaced volume is the volume of the body below the fluid surface. The principle applies to liquids and gases.",
                "Floating and hydrometers" to "A floating body displaces fluid whose weight equals the body's weight. A body with average density less than the fluid can float with part of its volume above the surface. A hydrometer sinks deeper in a less dense liquid and floats higher in a denser liquid. Apparent weight = true weight − upthrust."
            ),
            "A body displaces 0.002 m³ of water. If water density is 1000 kg m⁻³ and g = 10 m s⁻², what is the upthrust?",
            listOf("2 N", "20 N", "200 N", "2000 N"), 1,
            "U = ρgV = 1000 × 10 × 0.002 = 20 N."
        ),
        LearningTopic(
            "phy-temperature", "Temperature and its measurement", "PHY",
            "A thermometer does not measure how much heat an object contains. It measures temperature using a physical property that changes predictably with temperature. This lesson explains thermometric properties, calibration and the Celsius and kelvin scales.",
            listOf("Distinguish temperature from heat", "Identify thermometric properties", "Explain fixed points and thermometer calibration", "Convert temperatures between Celsius and kelvin", "Compare common thermometer types and their uses"),
            listOf(
                "Temperature and thermal equilibrium" to "Temperature indicates the thermal state of a body and determines the direction of net heat transfer when bodies are in thermal contact. Heat is energy transferred because of a temperature difference; it is not a substance stored inside an object. Bodies in thermal equilibrium have the same temperature and no net heat transfer.",
                "Thermometric properties" to "A thermometric property changes measurably with temperature. Examples include liquid volume, gas pressure at fixed volume, electrical resistance, thermoelectric emf and thermal radiation. A useful thermometer needs a predictable relationship between the property and temperature over its working range.",
                "Calibration and fixed points" to "A thermometer is calibrated by assigning known temperature values to reproducible reference states. The Celsius scale uses the ice point and steam point of pure water at standard atmospheric pressure as familiar fixed points. A practical thermometer scale is established by marking or electronically mapping the thermometric property between reference points.",
                "Celsius and kelvin" to "The kelvin is the SI unit of thermodynamic temperature. Convert using T(K) = θ(°C) + 273.15. A temperature interval of 1 K is equal in size to an interval of 1 °C. Kelvin values are not written with a degree sign."
            ),
            "Convert 27 °C to kelvin.",
            listOf("246.15 K", "273.15 K", "300.15 K", "310.15 K"), 2,
            "T = θ + 273.15 = 27 + 273.15 = 300.15 K."
        ),
        LearningTopic(
            "phy-thermal-expansion", "Thermal expansion", "PHY",
            "Railway tracks, bridges and overhead cables change length as temperature changes. Engineers allow for thermal expansion so structures do not buckle or develop excessive stress. Liquids expand too, but water behaves unusually near freezing.",
            listOf("Explain thermal expansion using particle motion", "Calculate linear expansion of a solid", "Distinguish linear, area and volume expansivity", "Explain real and apparent expansion of liquids", "Describe anomalous expansion of water"),
            listOf(
                "Why materials expand when heated" to "When a solid is heated, its particles vibrate with greater energy. The average separation between particles usually increases, so the material expands. Expansion depends on the material, original dimensions and temperature change.",
                "Linear, area and volume expansion" to "For a uniform solid, ΔL = αL₀ΔT, where α is the coefficient of linear expansivity in K⁻¹. For an isotropic solid with small expansion, area expansivity is approximately 2α and volume expansivity approximately 3α. These are approximations and assume equal expansion in all directions.",
                "Expansion of liquids" to "Liquids have no fixed shape, so volume expansion is considered. The apparent expansion observed in a container is less than the liquid's real expansion because the container also expands. Real expansion = apparent expansion + expansion of the container, when all are referred to the same initial volume and temperature interval.",
                "Anomalous expansion of water" to "Water reaches maximum density at about 4 °C. Between 4 °C and 0 °C, water expands as it cools; it also expands on freezing. In a cold environment, this behaviour helps keep denser water near the bottom of a lake while colder water and ice remain above."
            ),
            "A 2 m metal rod has α = 1.2 × 10⁻⁵ K⁻¹. What is its expansion for a 50 K temperature rise?",
            listOf("0.00012 m", "0.0012 m", "0.012 m", "0.12 m"), 1,
            "ΔL = αL₀ΔT = 1.2 × 10⁻⁵ × 2 × 50 = 0.0012 m."
        ),
        LearningTopic(
            "phy-gas-laws", "Gas laws", "PHY",
            "A bicycle pump becomes warm as air is compressed, and a balloon changes size when its temperature changes. Gas laws describe how pressure, volume and temperature are related for a fixed amount of gas. The kelvin scale is essential because the relationships use absolute temperature.",
            listOf("State Boyle's, Charles's and pressure laws", "Use kelvin temperature in gas-law calculations", "Apply the combined gas law", "Use the ideal gas equation", "Explain why real gases depart from ideal behaviour"),
            listOf(
                "Boyle's law" to "For a fixed mass of gas at constant temperature, pressure is inversely proportional to volume: pV = constant. Between two states, p₁V₁ = p₂V₂. If volume is halved at constant temperature, pressure doubles.",
                "Charles's law and pressure law" to "At constant pressure, volume is directly proportional to absolute temperature: V₁/T₁ = V₂/T₂. At constant volume, pressure is directly proportional to absolute temperature: p₁/T₁ = p₂/T₂. Temperature must be in kelvin, not Celsius.",
                "Combined gas law" to "For a fixed amount of gas, pV/T = constant, so p₁V₁/T₁ = p₂V₂/T₂. Before substituting, convert temperatures to kelvin and make sure pressure and volume units are consistent.",
                "Ideal gas equation and real gases" to "The ideal gas equation is pV = nRT, where n is amount in moles and R is the molar gas constant. The model treats particles as having negligible volume and negligible intermolecular forces except during collisions. Real gases depart from ideal behaviour, especially at high pressure and low temperature."
            ),
            "A gas occupies 300 cm³ at 100 kPa. At constant temperature its pressure rises to 150 kPa. What is its new volume?",
            listOf("150 cm³", "200 cm³", "300 cm³", "450 cm³"), 1,
            "Boyle's law: p₁V₁ = p₂V₂, so V₂ = (100 × 300)/150 = 200 cm³."
        ),
        LearningTopic(
            "phy-heat-quantity", "Quantity of heat", "PHY",
            "A small cup of water and a large pot of water can experience the same temperature rise but require different amounts of energy. The quantity of heat transferred depends on mass, material and temperature change. Calorimetry uses this relationship to measure thermal properties.",
            listOf("Distinguish heat capacity and specific heat capacity", "Use Q = mcΔT in calculations", "Apply the principle of mixtures", "Describe electrical heating as a measurement method", "Explain Newton's law of cooling qualitatively"),
            listOf(
                "Heat capacity and specific heat capacity" to "Heat capacity C is the energy needed to raise the temperature of a whole body by 1 K: C = Q/ΔT, measured in J K⁻¹. Specific heat capacity c is the energy needed to raise the temperature of 1 kg of a substance by 1 K: c = Q/(mΔT), measured in J kg⁻¹ K⁻¹. They are related by C = mc.",
                "Calculate heat transferred" to "For a temperature change without a change of state, Q = mcΔT. Here Q is heat energy in joules, m is mass in kilograms, c is specific heat capacity and ΔT is the temperature change in kelvin or degrees Celsius. A cooling body loses energy.",
                "Method of mixtures" to "In an insulated calorimetry experiment, heat lost by the hotter body equals heat gained by the cooler body, provided no energy is lost to the surroundings and no phase change occurs. Include the calorimeter's heat capacity if it is not negligible.",
                "Electrical method and cooling" to "An electrical heater transfers energy E = VIt = I²Rt to a sample. If losses are negligible, this equals mcΔT. Newton's law of cooling states that for a small temperature difference and similar surroundings, the rate of cooling is approximately proportional to the temperature difference."
            ),
            "How much energy is needed to raise 0.5 kg of water by 10 K if c = 4200 J kg⁻¹ K⁻¹?",
            listOf("210 J", "2100 J", "21 000 J", "210 000 J"), 2,
            "Q = mcΔT = 0.5 × 4200 × 10 = 21 000 J."
        ),
        LearningTopic(
            "phy-change-state", "Change of state", "PHY",
            "Ice can melt while its temperature stays near 0 °C, and boiling water can remain near its boiling temperature while a burner continues to supply energy. During a change of state, energy changes the arrangement and separation of particles rather than raising their average kinetic energy.",
            listOf("Distinguish melting, freezing, evaporation and boiling", "Define latent heat and specific latent heat", "Calculate energy during a change of state", "Interpret heating and cooling curves", "Explain effects of pressure and dissolved substances"),
            listOf(
                "States and changes of state" to "Melting changes a solid to a liquid; freezing changes a liquid to a solid; vaporisation changes a liquid to a gas; condensation changes a gas to a liquid; sublimation changes a solid directly to a gas. These are physical changes: the chemical identity remains the same.",
                "Evaporation and boiling" to "Evaporation occurs at the surface of a liquid and can happen below the boiling point. Faster particles escape, leaving the remaining liquid with lower average kinetic energy, so evaporation can cause cooling. Boiling occurs throughout the liquid when its vapour pressure equals the external pressure and bubbles can form within it.",
                "Latent heat and calculation" to "Specific latent heat L is the energy required per unit mass during a change of state: Q = mL. The unit is J kg⁻¹. Use latent heat of fusion for solid-liquid change and latent heat of vaporisation for liquid-gas change. During the phase change of a pure substance at fixed pressure, its temperature remains approximately constant.",
                "Heating curves and pressure" to "On a heating curve, sloping sections show temperature rising within one state; flat sections show a phase change. Increasing external pressure generally raises the boiling point, while reducing pressure lowers it. Dissolved non-volatile solutes generally raise the boiling point and lower the freezing point of a solvent."
            ),
            "How much energy is needed to melt 0.2 kg of ice if its specific latent heat of fusion is 3.34 × 10⁵ J kg⁻¹?",
            listOf("6.68 × 10³ J", "6.68 × 10⁴ J", "3.34 × 10⁵ J", "1.67 × 10⁶ J"), 1,
            "Q = mL = 0.2 × 3.34 × 10⁵ = 6.68 × 10⁴ J."
        )
    )
}
