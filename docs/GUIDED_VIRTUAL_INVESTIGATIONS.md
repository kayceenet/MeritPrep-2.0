# Guided virtual investigations in science lessons

**Status:** Implemented as an offline-capable lesson component; academic validation and device usability review remain required.  
**Renderer:** `LessonScreen(topicId)` in `LearningCatalog.kt` invokes `GuidedInvestigationActivity(topicId)` directly inside the lesson.

## Learner workflow

Each supported investigation uses five stages:

1. **Brief** — investigative question, apparatus/materials, safety and model limits.
2. **Plan** — independent, dependent and controlled variables; learner prediction; ordered method checklist.
3. **Run** — learner changes an independent variable and records model readings at selected settings.
4. **Analyse** — observation table, plotted points, endpoint gradient and interpretation prompt.
5. **Evaluate** — evidence-led conclusion, random/systematic error prompts, repeatability, control-variable drift and model limitations.

The activity is local to the lesson and does not require a network connection. Readings are held in the current activity state; they are not yet saved as permanent practical portfolios.

## Current guided investigation coverage

### Biology
Cell microscopy and magnification; classification by observable characters; feeding-structure model; transpiration; photosynthesis; osmosis; respiration in germinating seeds; stomatal water loss; stem support; seed germination; seedling growth; response time; environmental gradients; predator–prey modelling; quadrat sampling; soil water retention; variation; fermentation; characteristics of living things; comparative anatomy/evolution evidence.

Existing lesson activities continue to provide the inheritance and natural-selection experiences.

### Chemistry
Separation/chromatography; reaction rate; solubility; electrolysis charge; radioactive decay; industrial equilibrium; organic homologous series; pollution reasoning; acid–base titration; redox titration; mole ratios/limiting reactants; calorimetry; ideal-gas pressure/temperature relationships.

Existing lesson activities continue to provide ionic formula building, qualitative analysis, gas-law exploration and astronomical spectra.

### Physics
Uniformly accelerated motion; Hooke's law; pressure in liquids; gas laws; wave frequency/wavelength; Ohm's law; pendulum/gravitational acceleration; principle of moments; work and energy; thermometer calibration; specific heat capacity; change of state; photon energy; convex-lens focal length; dispersion; electrostatics; magnetic-field mapping; diode characteristics; friction; machines; thermal expansion; thermal conduction; magnetic force; AC period and frequency.

Existing lesson activities continue to provide measurement, vector components, reflection, refraction, capacitors, induction, electrostatics, circuit networks, waves and other concept explorations.

## WAEC-specific embedded extensions

The lesson renderer also invokes examination-specific extensions:
- **Biology (Nigeria Section C):** cell theory and irritability, taxis/nastic responses, courtship and housefly metamorphosis, specialised feeding, energy transfer, tolerance ranges, habitat adaptation, population sampling, public health, ABO compatibility limitations, agriculture/ecology and practical skills in biological drawing, microscopy, food tests, dichotomous keys, plant sections, germination and soil.
- **Chemistry:** functional-group and food-substance test references, separation-method selection, standard-solution dilution, periodic trends, titration/redox calculations, qualitative cation/anion/gas references and safe simulated gas identification.
- **Physics (WAEC harmonised topics):** dimensional analysis, projectile motion, satellite orbits, Young's modulus, blackbody radiation, fibre optics, LASER types/safety, magnetic materials, diode rectification and photoelectric effect.

These are separate from the JAMB syllabus crosswalk. The WAEC pages currently used for crosswalk drafts are secondary reproductions and are marked provisional until their exact contents are checked against direct WAEC PDFs.

## Model integrity

- Every generated reading is explicitly described as simulated or illustrative.
- Model equations and idealisations are stated in the lesson.
- Selected model ranges are constrained where a physical domain requires it (for example, positive absolute temperature, object distance beyond focal length, positive AC frequency and visible-spectrum wavelength).
- A model response is not presented as a measured laboratory result.
- The user must still learn the physical apparatus and carry out a real practical when the examination or learning objective requires it.

## Current limitations / next engineering work

- Practical data are session-local and are not yet stored in a durable learner portfolio.
- Graphs are lightweight plots; full selectable axis scales, best-fit regression, uncertainty bars and downloadable data sheets remain future work.
- The method checklist tracks review of steps, not yet demonstrated procedural competence.
- The prediction is captured as text but is not yet scored against a structured hypothesis rubric.
- Some topics are better served by a concept simulation or model activity than a wet-lab simulation; the lesson should not invent a physical practical where none is appropriate.
- All scientific models, especially simplified ecological, pollution, industrial and biological relationships, require independent subject review before academic sign-off.

## Verification

- Routing regression tests cover the declared topic IDs.
- Hand-calculation tests cover representative titration, redox, calorimetry, half-life, pendulum and lens models.
- Final acceptance depends on the Android CI run for the latest commit and subsequent small-screen/device review.
