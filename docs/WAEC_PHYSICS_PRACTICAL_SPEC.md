# WAEC Physics practical practice specification

**Exam track:** WASSCE Physics (Nigeria)  
**Content status:** original practice material; not official WAEC past questions or marking schemes.  
**Reference checked:** 24 November 2026.

## Reference and verification status

The working examination structure below was transcribed from a copy of the WAEC Physics syllabus PDF hosted by SchoolNews:
https://schoolnews.com.ng/wp-content/uploads/2026/11/waec-syllabus-physics.pdf

The hosting site is not WAEC. The PDF copy describes the following paper structure, but the product team must verify the current examination instructions against a WAEC-issued syllabus and the candidate's examination session before presenting them as definitive. WAEC's examination portal is https://www.waeconline.org.ng/.

## Working paper structure

The reference copy describes:
- **Paper 1:** 50 multiple-choice questions, 1 hour 15 minutes, 50 marks.
- **Paper 2:** 1 hour 30 minutes, 60 marks. Section A has seven short-structured questions; candidates answer five for 15 marks. Section B has five essay questions; candidates answer three for 45 marks.
- **Paper 3:** three practical questions; candidates answer two in 2 hours 45 minutes for 50 marks. School candidates sit the practical test; private candidates sit an alternative-to-practical paper.

Do not configure a full timed WAEC Physics examination until the current WAEC-issued instructions have been checked for the relevant session.

## Practical skills the learner must practise

The reference syllabus assesses more than formula recall. Learners should practise:
1. Selecting and safely using apparatus.
2. Identifying independent, dependent and controlled variables.
3. Taking repeated readings and recording quantities with units.
4. Choosing sensible table headings and consistent decimal places.
5. Selecting graph axes, scales and units.
6. Plotting points and drawing a best-fit line rather than joining dots.
7. Calculating a gradient with a large triangle and stating its unit.
8. Interpreting the gradient and intercept in physical terms.
9. Drawing conclusions from patterns and evaluating limitations.
10. Stating precautions and suggesting improvements.

## Implemented original practice tasks

The app currently provides three data-analysis exercises:

| Exercise | Variables and graph | Main result from gradient |
|---|---|---|
| Spring extension | Force F against extension x | Spring constant k |
| Simple pendulum | Period squared T² against length L | g = 4π² / gradient |
| Ohm's law | Voltage V against current I | Resistance R |

Each task includes:
- Aim and apparatus list.
- Independent, dependent and controlled variables.
- Five simulated data pairs with units in the headings.
- A plotted graph showing the supplied points and a fitted straight line.
- A free-text response area.
- Worked gradient and interpretation.
- An examiner checklist covering graph presentation, units, precautions and method.

The graph analysis engine calculates a least-squares straight-line gradient, intercept and coefficient of determination. It also calculates gravitational field strength from the simple-pendulum gradient. The engine and task data have unit tests.

## Important limitations

- The readings are simulated learning data, not observations from a physical experiment.
- The three tasks are original MeritPrep exercises, not official WAEC past questions.
- The app does not automatically award marks to free-text answers.
- The displayed graph is a worked visual aid. It does not assess the learner's ability to choose graph-paper scales and plot points manually.
- The current set does not assess practical apparatus handling, measurement uncertainty, repeatability, error bars or a complete Paper 3 examination.
- Learners must still perform supervised laboratory work where equipment is available.

## Next development work

1. Add practical tasks for density and relative density, moments, focal length, resistivity, specific heat capacity and electrical circuits.
2. Add repeated readings, mean values, uncertainty and percentage-error calculations.
3. Add learner-controlled graph scales and manual point plotting.
4. Add a full alternative-to-practical exercise with apparatus diagrams, observations, calculations and method evaluation.
5. Verify the current WAEC-issued syllabus and paper structure before enabling timed Paper 1, Paper 2 or Paper 3 mock examinations.
6. Obtain independent Physics-teacher review of every task, dataset, worked answer and marking checklist.
