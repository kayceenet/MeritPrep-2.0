package com.kaycee.meritprep

/** Third beginner-learning block for JAMB Physics. Draft content pending independent review. */
object PhysicsCourseExtension2 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-vapours", "Vapours", "PHY",
            "Water evaporates from a wet floor even when it is not boiling. On a humid day, the same floor may dry more slowly. Vapour pressure, humidity and dew point help explain these everyday observations and the formation of clouds, mist and rain.",
            listOf("Distinguish saturated and unsaturated vapour", "Explain saturated vapour pressure and boiling", "Define dew point and relative humidity", "Interpret wet-and-dry-bulb hygrometer readings", "Explain condensation, dew, mist and fog"),
            listOf(
                "Evaporation and vapour" to "A vapour is the gaseous form of a substance that is normally liquid or solid under the conditions being considered. Evaporation occurs when some particles at a liquid surface have enough energy to escape into the space above it. In a closed container, vapour particles return to the liquid by condensation.",
                "Saturated vapour pressure" to "In a closed space at a fixed temperature, a dynamic equilibrium can form when the rate of evaporation equals the rate of condensation. The vapour is then saturated, and its pressure is the saturated vapour pressure (SVP). SVP increases with temperature because a greater fraction of molecules can escape from the liquid.",
                "Boiling and vapour pressure" to "A liquid boils when its vapour pressure becomes equal to the external pressure. Raising external pressure generally raises the boiling temperature; reducing external pressure lowers it. This explains why water can boil below 100 °C at high altitude and above 100 °C in a pressure cooker.",
                "Humidity and dew point" to "Relative humidity is the ratio of actual water-vapour pressure to saturated vapour pressure at the same temperature, multiplied by 100%. Dew point is the temperature to which air must be cooled, at approximately constant pressure and water-vapour content, for saturation to occur. When air cools to its dew point, water may condense as dew, mist, fog or cloud droplets.",
                "Wet-and-dry-bulb hygrometer" to "A wet-bulb thermometer is cooled by evaporation from its damp covering. The drier the air, the faster evaporation occurs and the larger the wet-bulb depression. Relative humidity can be estimated using a psychrometric table or chart with the dry-bulb temperature and wet-bulb depression. Do not assume the temperature difference alone gives an exact percentage without the appropriate table."
            ),
            "Relative humidity is defined as the ratio of actual vapour pressure to:",
            listOf("Atmospheric pressure", "Saturated vapour pressure at the same temperature", "Liquid density", "Wind speed"), 1,
            "Relative humidity = actual water-vapour pressure ÷ saturated vapour pressure at the same temperature × 100%."
        ),
        LearningTopic(
            "phy-kinetic-theory", "Structure of matter and kinetic theory", "PHY",
            "A drop of perfume spreads through a room, pollen grains move irregularly in water and a liquid rises in a narrow tube. These observations reveal that matter is made of tiny particles in continuous motion and that attractive forces act between particles.",
            listOf("Distinguish atoms and molecules", "State the main assumptions of the kinetic theory of gases", "Explain Brownian motion and diffusion", "Explain cohesion, adhesion, surface tension and capillarity", "Relate gas pressure to molecular motion"),
            listOf(
                "Particles make up matter" to "An atom is the smallest unit of an element that retains its chemical identity in ordinary chemical reactions. A molecule is a group of atoms held together by chemical bonds. Substances may consist of atoms, molecules or ions. The particles are separated by spaces and are in continuous motion, although their arrangement and freedom of movement differ between solids, liquids and gases.",
                "Kinetic theory of gases" to "In the simple ideal-gas model, gas particles are very small compared with the distances between them, move randomly and continuously, and undergo elastic collisions with one another and the container walls. Intermolecular forces are neglected except during collisions. The average kinetic energy of the particles is proportional to absolute temperature.",
                "Why gas pressure occurs" to "Gas particles strike the walls of their container and change momentum during collisions. The combined force of many collisions per unit area produces pressure. Raising the temperature at constant volume increases the average molecular speed and the rate and momentum change of collisions, so pressure rises.",
                "Brownian motion and diffusion" to "Brownian motion is the irregular movement of small visible particles suspended in a fluid, caused by uneven collisions with the much smaller, rapidly moving fluid molecules. Diffusion is the net movement of particles from a region of higher concentration to lower concentration due to random motion. Diffusion is generally faster at higher temperatures and over shorter distances.",
                "Cohesion, adhesion and capillarity" to "Cohesion is attraction between like molecules; adhesion is attraction between unlike substances. Surface tension results from the net inward cohesive effect on molecules at a liquid surface, making the surface behave as if it were stretched. Capillary rise or fall occurs when surface tension and adhesive/cohesive forces act in a narrow tube. Water commonly rises in clean glass because adhesion to glass is strong; mercury is depressed because cohesion is stronger than adhesion to glass."
            ),
            "The irregular motion of tiny particles suspended in a fluid due to molecular collisions is called:",
            listOf("Convection", "Brownian motion", "Reflection", "Sublimation"), 1,
            "Brownian motion is caused by unbalanced collisions from rapidly moving molecules of the surrounding fluid."
        ),
        LearningTopic(
            "phy-heat-transfer", "Heat transfer", "PHY",
            "A metal spoon becomes hot when left in a pot, warm air rises above a heater and sunlight warms your skin across empty space. These are examples of conduction, convection and radiation: three different ways thermal energy moves.",
            listOf("Distinguish conduction, convection and radiation", "Explain heat transfer in solids, liquids and gases", "Use the idea of thermal conductivity and temperature gradient", "Explain how surface properties affect radiation", "Describe the working principles of a thermos flask and land/sea breezes"),
            listOf(
                "Conduction" to "Conduction transfers thermal energy through a material without bulk movement of the material. In metals, mobile electrons transfer energy efficiently; lattice vibrations also contribute. Metals are generally good thermal conductors, while materials such as wood, air and foam are relatively poor conductors.",
                "Convection" to "Convection transfers thermal energy by the bulk movement of a fluid. Heating usually makes a fluid expand and become less dense; the warmer fluid rises while cooler, denser fluid moves in to replace it. Natural convection occurs because of density differences, while forced convection uses a fan or pump.",
                "Radiation" to "Thermal radiation is electromagnetic radiation emitted by matter because of its temperature. It does not require a material medium and can travel through a vacuum. Dark, dull surfaces are generally good absorbers and emitters; light, shiny surfaces are generally poorer absorbers and emitters and better reflectors.",
                "Temperature gradient and conductivity" to "Thermal conduction depends on the temperature difference, area, thickness and thermal conductivity of the material. For a uniform slab in steady one-dimensional conduction, the rate of heat transfer is approximately Q/t = kAΔT/L, where k is thermal conductivity, A is area, ΔT is the temperature difference and L is thickness. The model assumes constant properties and negligible edge effects.",
                "Thermos flask and local breezes" to "A thermos flask reduces heat transfer by using a vacuum or insulating gap to limit conduction and convection, reflective surfaces to reduce radiation, and a narrow stopper to reduce air movement and conduction. During the day, land often heats faster than the sea, so warm air rises over land and cooler sea air moves inland as a sea breeze. At night the pattern can reverse."
            ),
            "Which mode of heat transfer can occur through a vacuum?",
            listOf("Conduction", "Convection", "Radiation", "All three equally"), 2,
            "Radiation is electromagnetic and does not require particles or a material medium."
        ),
        LearningTopic(
            "phy-waves", "Waves", "PHY",
            "A ripple travels across water after a stone is dropped, and a pulse moves along a rope when one end is shaken. A wave transfers energy from place to place while the particles of the medium oscillate about their equilibrium positions.",
            listOf("Define wave motion and distinguish it from particle transport", "Relate wave speed, frequency and wavelength", "Distinguish transverse and longitudinal waves", "Compare mechanical and electromagnetic waves", "Explain reflection, refraction, diffraction, interference and stationary waves"),
            listOf(
                "What a wave transfers" to "Wave motion is a disturbance that transfers energy and information without requiring net transport of the medium from the source to the destination. In a mechanical wave, particles of the medium oscillate around equilibrium positions. A water wave can carry energy across a pond even though individual water particles do not travel all the way across the pond.",
                "Wave quantities" to "Frequency f is the number of complete oscillations per second, measured in hertz. Period T is the time for one oscillation, with T = 1/f. Wavelength λ is the distance between successive points in phase. Wave speed v = fλ. Keep units consistent: if f is in hertz and λ in metres, v is in m s⁻¹.",
                "Transverse and longitudinal waves" to "In a transverse wave, oscillations are perpendicular to the direction of propagation, as in an ideal wave on a stretched string. In a longitudinal wave, oscillations are parallel to propagation, producing compressions and rarefactions, as in sound in air. Mechanical waves require a medium; electromagnetic waves can travel through a vacuum.",
                "Wave behaviour" to "Reflection is a change in direction at a boundary; refraction is a change in direction caused by a change in wave speed; diffraction is spreading around an obstacle or through an opening; interference is the superposition of waves. Diffraction is most noticeable when the opening or obstacle size is comparable to the wavelength.",
                "Progressive and stationary waves" to "A progressive wave travels and transfers energy. A stationary wave is formed by superposition of two similar waves travelling in opposite directions; it has nodes of zero displacement and antinodes of maximum displacement. In an ideal stationary wave there is no net energy transfer along the medium. Polarisation is a property of transverse waves and can be used to distinguish them from longitudinal waves."
            ),
            "A wave has frequency 5 Hz and wavelength 2 m. What is its speed?",
            listOf("2.5 m s⁻¹", "7 m s⁻¹", "10 m s⁻¹", "25 m s⁻¹"), 2,
            "v = fλ = 5 × 2 = 10 m s⁻¹."
        ),
        LearningTopic(
            "phy-sound-propagation", "Propagation of sound waves", "PHY",
            "You hear a drum because vibrations create pressure variations that travel through air to your ear. Sound needs particles to transmit these disturbances, which is why sound cannot travel through a vacuum.",
            listOf("Explain why sound requires a material medium", "Describe sound as a longitudinal mechanical wave in air", "Compare sound speed in solids, liquids and gases", "Calculate distance or speed using echoes", "Explain reverberation and its control"),
            listOf(
                "Sound is a mechanical wave" to "A vibrating source causes nearby particles in a medium to oscillate, creating compressions and rarefactions that travel as a longitudinal wave. The particles vibrate about their equilibrium positions; they do not travel from the source all the way to the listener. Sound cannot propagate through a perfect vacuum because there are no particles to transmit the disturbance.",
                "Speed of sound" to "Sound generally travels faster in solids than in liquids and faster in liquids than in gases because the elastic response and particle interactions differ. In air, the speed increases with temperature. The exact speed depends on the medium and conditions; use the value supplied in a numerical question when one is given.",
                "Echo calculations" to "An echo is reflected sound heard separately from the original sound. If sound travels to a reflecting surface and back, total distance travelled is twice the one-way distance. Therefore distance to the reflecting surface = vt/2, where v is sound speed and t is the time delay. This assumes the source and reflector are stationary and the speed is uniform.",
                "Reverberation" to "Reverberation is the persistence of sound in an enclosed space because of repeated reflections after the source stops. A small amount can enrich music, but excessive reverberation reduces speech clarity. Soft porous materials, carpets, curtains and acoustic panels absorb sound and can reduce reverberation.",
                "Conditions and applications" to "A distinct echo requires a sufficient time gap between the original and reflected sound for the ear to distinguish them. Echo sounding and sonar use reflected sound to estimate distance or depth. The method depends on knowing the speed of sound in the medium and measuring the return time."
            ),
            "A sound pulse travels at 340 m s⁻¹ and returns as an echo after 2 s. How far away is the reflecting wall?",
            listOf("170 m", "340 m", "680 m", "1360 m"), 1,
            "The sound travels to the wall and back. Distance = vt/2 = 340 × 2 ÷ 2 = 340 m."
        ),
        LearningTopic(
            "phy-sound-characteristics", "Characteristics of sound waves", "PHY",
            "A flute and a drum can play the same note but sound different. A loud note and a quiet note may have the same pitch. Physics separates these features using frequency, amplitude, waveform and the way instruments vibrate.",
            listOf("Distinguish pitch, loudness, intensity and quality", "Relate pitch to frequency and loudness to amplitude/intensity", "Explain harmonics and overtones", "Describe resonance in strings and air columns", "Use simple open- and closed-pipe relationships"),
            listOf(
                "Pitch and frequency" to "Pitch is the perception of how high or low a note sounds and is mainly related to frequency. Higher frequency is generally heard as higher pitch. Frequency is an objective physical quantity measured in hertz; pitch is a perceptual response and also depends on hearing conditions.",
                "Loudness and intensity" to "Sound intensity is power transmitted per unit area, measured in W m⁻². For a simple progressive wave, intensity is related to the square of amplitude under fixed conditions. Loudness is the human perception of sound strength and depends on intensity, frequency and the listener's hearing. Do not use loudness and intensity as exact synonyms.",
                "Quality, harmonics and overtones" to "Quality or timbre helps distinguish sounds from different sources even when pitch and loudness are similar. A musical note may contain a fundamental frequency and higher-frequency components called harmonics. The fundamental is the first harmonic; the first overtone is the next higher resonant frequency, which is not always the second harmonic in every system.",
                "Strings and air columns" to "For a stretched string fixed at both ends, the fundamental frequency is f₁ = (1/2L)√(T/μ), where L is vibrating length, T is tension and μ is mass per unit length. An ideal open pipe has fundamental frequency f₁ = v/(2L); an ideal pipe closed at one end has f₁ = v/(4L). End corrections may be needed in precise experiments.",
                "Resonance" to "Resonance occurs when a system is driven near one of its natural frequencies, producing a large response if damping is small. Musical instruments use resonance to strengthen selected frequencies. Excessive resonance in buildings, bridges or machinery can be harmful, so engineers control it with damping and design."
            ),
            "The fundamental frequency of an ideal pipe closed at one end is:",
            listOf("v/L", "v/(2L)", "v/(4L)", "2v/L"), 2,
            "For an ideal pipe closed at one end, the fundamental wavelength is 4L, so f = v/λ = v/(4L)."
        ),
        LearningTopic(
            "phy-light-energy", "Light energy", "PHY",
            "We see objects because light reaches our eyes from them. A lamp emits light, while a book is visible because it reflects light. Light travels through a vacuum, forms shadows and can be modelled using straight rays when studying everyday optical systems.",
            listOf("Distinguish luminous and non-luminous objects", "Compare natural and artificial light sources", "Relate speed, frequency and wavelength of light", "Explain shadows and eclipses", "Describe the pinhole camera and image formation"),
            listOf(
                "Sources and visible objects" to "A luminous object produces its own light, such as the Sun, a flame or an LED. A non-luminous object is seen because it reflects or scatters light from a source. Sources may be natural or artificial. The Moon is non-luminous; it reflects sunlight.",
                "Light as an electromagnetic wave" to "In a vacuum, light travels at approximately 3.00 × 10⁸ m s⁻¹. Its speed, frequency and wavelength are related by c = fλ. When light enters a different transparent medium, its speed and wavelength change while frequency remains constant at the boundary.",
                "Rectilinear propagation, shadows and eclipses" to "In a uniform transparent medium, light travels in straight lines. An opaque object blocks light and produces a shadow. A solar eclipse occurs when the Moon passes between the Sun and Earth; a lunar eclipse occurs when Earth lies between the Sun and Moon and its shadow falls on the Moon. The apparent sizes and alignment determine the type of eclipse.",
                "Pinhole camera" to "A pinhole camera forms an inverted image because rays from the top and bottom of an object pass through the small opening and cross. For a distant object and a small pinhole, the image is approximately sharp but dim. Image size depends on object distance and the distance from pinhole to screen; similar triangles can be used to calculate magnification.",
                "Ray model and limitations" to "The ray model is useful for reflection, shadows and image formation when objects and openings are much larger than the wavelength of light. It does not explain interference or diffraction, which require a wave model. A very large pinhole produces a brighter but less sharp image because rays from each object point spread over a larger region of the screen."
            ),
            "Which object is non-luminous?",
            listOf("The Sun", "A lit candle flame", "The Moon", "An operating LED"), 2,
            "The Moon does not produce its own visible light; it reflects sunlight."
        ),
        LearningTopic(
            "phy-reflection", "Reflection of light at plane and curved surfaces", "PHY",
            "A plane mirror forms an upright image, a shaving mirror can magnify a face at close range and a vehicle side mirror gives a wider field of view. These differences follow from the laws of reflection and the shape of the reflecting surface.",
            listOf("State the laws of reflection", "Describe images formed by plane, concave and convex mirrors", "Draw and interpret simple principal ray diagrams", "Use the mirror formula and magnification", "Identify practical applications of mirrors"),
            listOf(
                "Laws of reflection" to "The incident ray, reflected ray and normal at the point of incidence lie in the same plane. The angle of incidence equals the angle of reflection, measured from the normal. A normal is perpendicular to the reflecting surface at the point where the ray strikes.",
                "Plane mirror images" to "A plane mirror forms a virtual, upright image the same size as the object. The image appears as far behind the mirror as the object is in front. It is laterally inverted in the usual description: left and right appear exchanged relative to the observer.",
                "Concave and convex mirrors" to "A concave mirror converges parallel rays towards a principal focus and can form real or virtual images depending on object position. A convex mirror diverges reflected rays and forms a virtual, upright and diminished image; it provides a wide field of view and is useful as a vehicle side mirror.",
                "Mirror formula and magnification" to "For spherical mirrors under the usual paraxial approximation, the mirror formula is 1/f = 1/u + 1/v using the sign convention specified by the course or question. Linear magnification m = image height/object height; with a consistent Cartesian sign convention it may also be written m = −v/u. Always use one stated sign convention throughout a calculation.",
                "Applications and diagram method" to "Periscopes use plane mirrors or prisms to redirect light; kaleidoscopes use multiple reflections to form repeated patterns; vehicle mirrors use convex mirrors for a wide view; concave mirrors can be used in torches and shaving mirrors. In ray diagrams, draw the principal axis, mark the pole, focus and centre of curvature, and use at least two standard rays to locate the image."
            ),
            "A convex mirror is commonly used as a vehicle side mirror because it forms an image that is:",
            listOf("Real, inverted and enlarged", "Virtual, upright and diminished", "Real, upright and enlarged", "Virtual, inverted and same size"), 1,
            "A convex mirror forms a virtual, upright and diminished image and gives a wider field of view."
        )
    )
}
