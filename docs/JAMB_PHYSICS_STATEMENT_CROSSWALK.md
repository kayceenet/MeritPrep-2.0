# JAMB UTME Physics — topic and statement crosswalk

**Primary source:** [JAMB IBASS Physics syllabus PDF](https://ibass.jamb.gov.ng/assets/uploads/Physics.pdf)  
**Source checked:** 25 November 2026  
**Repository map:** `PhysicsCourse.topics`, assembled in official order from `PhysicsCourse.kt` and five course-extension files.

The current course map contains 39 lesson entries, matching the 39 numbered topics in the official JAMB PDF. The existing `PhysicsCourseTest` checks the 39-topic count, ordering, unique IDs and developed lesson structure. That is useful topic-level evidence; it is not a statement-by-statement academic audit.

| No. | Official JAMB topic | Lesson ID | Essential syllabus statements to evidence |
|---:|---|---|---|
| 1 | Measurements and units | `phy-measurements` | Length, area, volume, mass, time, instruments, fundamental/derived quantities, dimensions, measurement limitations, accuracy, errors, significant figures, standard form, position, distance and displacement. |
| 2 | Scalars and vectors | `phy-vectors` | Scalar/vector definitions and examples, relative velocity, vector resolution into perpendicular components and graphical solutions. |
| 3 | Motion | `phy-motion` | Translational/oscillatory/rotational/spin/random motion, relative motion, forces, linear motion, speed/velocity/acceleration, equations, gravity, graphs, projectiles, Newton's laws, momentum, circular motion, SHM, energy and resonance. |
| 4 | Gravitational field | `phy-gravity` | Universal gravitation, gravitational potential, conservative/non-conservative fields, g and its variation, mass/weight, escape velocity, parking orbit and weightlessness. |
| 5 | Equilibrium of forces | `phy-equilibrium` | Coplanar forces, triangle/polygon of forces, Lami's theorem, moments/couples, rigid-body equilibrium, resultants/equilibrants, centre of gravity and stability. |
| 6 | Work, energy and power | `phy-work-energy` | Work, energy, power, energy forms and conservation, transformations, force-distance graph area, energy sources, renewable/non-renewable energy, environmental effects, dams, nuclear and solar energy. |
| 7 | Friction | `phy-friction` | Static/dynamic friction, limiting coefficient and determination, advantages/disadvantages, reduction, viscosity, terminal velocity and Stokes' law. |
| 8 | Simple machines | `phy-machines` | Machine types, mechanical advantage, velocity ratio and efficiency. |
| 9 | Elasticity | `phy-elasticity` | Elastic limit, yield/breaking points, Hooke's law, Young's modulus, spring balance and work per unit volume in springs/strings. |
| 10 | Pressure | `phy-pressure` | Atmospheric pressure and units; barometers/manometers; pressure in liquids and solids; pressure-depth relationships. |
| 11 | Liquids at rest | `phy-liquids-rest` | Density and relative density, upthrust, Archimedes' principle, flotation, pressure in fluids and applications. |
| 12 | Temperature and its measurement | `phy-temperature` | Temperature, thermometric properties, thermometers, fixed points, calibration and temperature scales. |
| 13 | Thermal expansion | `phy-thermal-expansion` | Linear/area/volume expansivity, solids/liquids, real/apparent expansion and anomalous expansion of water. |
| 14 | Gas laws | `phy-gas-laws` | Boyle's, Charles's and pressure laws, absolute zero, combined gas law and ideal-gas equation. |
| 15 | Quantity of heat | `phy-heat-quantity` | Heat capacity, specific heat capacity, measurement methods, method of mixtures, electrical heating and Newton's law of cooling. |
| 16 | Change of state | `phy-change-state` | Latent heat, specific latent heat of fusion/vaporisation, melting, evaporation, boiling, pressure and dissolved-substance effects. |
| 17 | Vapours | `phy-vapours` | Saturated/unsaturated vapour, saturated vapour pressure, boiling, dew point, humidity, relative humidity and wet/dry bulb hygrometry. |
| 18 | Structure of matter and kinetic theory | `phy-kinetic-theory` | Atoms/molecules, Brownian motion, diffusion, surface tension, capillarity, adhesion/cohesion, kinetic-theory assumptions and gas pressure. |
| 19 | Heat transfer | `phy-heat-transfer` | Conduction, convection, radiation, temperature gradient, thermal conductivity, heat flux, surface properties, common materials, thermos flask, land/sea breeze and engines. |
| 20 | Waves | `phy-waves` | Production/propagation, energy transfer, frequency/wavelength/speed, phase, wave equation, mechanical/electromagnetic, transverse/longitudinal, progressive/stationary, reflection/refraction/diffraction/polarisation, interference, beats and Doppler effect. |
| 21 | Propagation of sound waves | `phy-sound-propagation` | Material medium, speed in solids/liquids/air, temperature effects, reflection, echoes, reverberation and applications/disadvantages. |
| 22 | Characteristics of sound waves | `phy-sound-characteristics` | Noise/musical notes, quality, pitch, intensity, loudness, strings, air columns, overtones and resonance. |
| 23 | Light energy | `phy-light-energy` | Natural/artificial sources, luminous/non-luminous objects, speed/frequency/wavelength, shadows, eclipses and pinhole camera. |
| 24 | Reflection of light at plane and curved surfaces | `phy-reflection` | Laws, plane/concave/convex mirrors, image formation, ray diagrams, mirror formula, magnification, periscope, kaleidoscope and sextant. |
| 25 | Refraction of light through plane and curved surfaces | `phy-refraction` | Refraction/speed, Snell's law, refractive index, real/apparent depth, lateral displacement, critical angle, total internal reflection, prisms and lenses. |
| 26 | Optical instruments | `phy-optical-instruments` | Microscopes, telescopes, projectors, cameras, eye, lens power, angular magnification, near/far points and sight defects/corrections. |
| 27 | Dispersion of light and colours | `phy-dispersion` | Prism dispersion, pure spectra, additive/subtractive mixing, colour of objects, filters, rainbows and electromagnetic spectrum. |
| 28 | Electrostatics | `phy-electrostatics` | Charge, charging by friction/contact/induction, electroscope, Coulomb's law, field/potential, discharge and lightning. |
| 29 | Capacitors | `phy-capacitors` | Capacitor types/uses, parallel-plate model, capacitance, factors, series/parallel combinations and stored energy. |
| 30 | Electric cells | `phy-cells` | Simple voltaic-cell defects, Daniell/Leclanché/lead-acid/NiFe and other named cells, maintenance, series/parallel arrangements and efficiency. |
| 31 | Current electricity | `phy-current-electricity` | EMF, p.d., current, internal resistance/lost volts, Ohm's law, resistance measurement, metre bridge, networks, potentiometer and Kirchhoff's laws. |
| 32 | Electrical energy and power | `phy-electrical-power` | Electrical energy/power, commercial units, transmission, heating effect, house wiring, parallel arrangement and fuse rating. |
| 33 | Magnets and magnetic fields | `phy-magnetism` | Natural/artificial magnets, soft iron/steel, magnetisation/demagnetisation, field patterns of magnets/conductors/solenoids, Earth's field, flux/density and navigation. |
| 34 | Force on a current-carrying conductor in a magnetic field | `phy-magnetic-force` | Parallel-current forces, moving charges, DC motor, electromagnets, carbon microphone, moving-coil/moving-iron instruments, galvanometer conversion and sensitivity. |
| 35 | Electromagnetic induction | `phy-induction` | Faraday's laws, induced-EMF factors, Lenz's law, AC/DC generators, transformers, induction coils, inductance, stored energy and eddy currents. |
| 36 | Simple AC circuits | `phy-ac-circuits` | AC/voltage, peak/RMS, resistive/capacitive/inductive circuits, reactance, RLC, vector diagrams, phase, impedance, power factor and resonance. |
| 37 | Conduction of electricity through liquids and gases | `phy-conduction` | Electrolytes/non-electrolytes, electrolysis, Faraday's laws, electroplating, discharge through gases and applications. |
| 38 | Elementary modern physics | `phy-modern` | Atomic models, energy levels/spectra, thermionic/photoelectric emission, Einstein equation/stopping potential, X-rays, radioactivity, half-life, fusion/fission, binding energy, mass defect, matter waves, electron diffraction and uncertainty principle. |
| 39 | Introductory electronics | `phy-electronics` | Conductors/semiconductors/insulators, band gap, intrinsic/extrinsic, n/p type, electron/hole carriers, diodes, rectification and transistor amplification. |

## Implementation evidence and remaining gaps

- The 39-topic sequence and developed lesson structures are covered by `PhysicsCourseTest`.
- Lesson-level interactive routing includes guided investigations and existing concept activities. The exact topic-to-experience matrix should be maintained alongside changes to lesson dispatch.
- A guided investigation is not automatically a full practical for every syllabus statement. Practical method, apparatus, data analysis and safety must be covered only where the official objective requires them.
- The most extensive topics (motion, energy, waves, optics, magnetism, induction, AC circuits and modern physics) need objective-by-objective assessment coverage, not just one interactive model each.
- The WAEC Physics syllabus has not yet been individually retrieved and version-checked; do not treat this JAMB crosswalk as WAEC coverage.

**Release decision:** topic-level mapping and lesson scaffolds exist; statement-level evidence, WAEC crosswalk and independent Physics academic sign-off remain required.
