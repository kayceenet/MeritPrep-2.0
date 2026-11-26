# Biology adaptation and survival micro-lesson — implementation record

**Lesson route:** `bio-adaptation`  
**JAMB area:** A3, Adaptation.  
**WAEC working areas:** Section A adaptation/survival and relevant Section C evolution/adaptation content.  
**Status:** implementation in progress; WAEC primary-source verification and independent academic review remain outstanding.

## Internal examinable-idea map

These are MeritPrep teaching-idea IDs, not official board statement numbers.

| Idea ID | Learner outcome | Stage evidence |
|---|---|---|
| `bio-adaptation-definition` | Explain inherited adaptation, natural selection and acclimatisation as distinct concepts. | Concepts, Examples and Context checks. |
| `bio-structural-adaptation` | Relate physical features to environmental challenges. | Three stage checks and worked examples. |
| `bio-physiological-adaptation` | Explain internal processes that support water, salt and temperature balance. | Three stage checks and applied examples. |
| `bio-behavioural-adaptation` | Distinguish nocturnality, migration, hibernation and aestivation. | Three stage checks and seasonal-survival examples. |
| `bio-adaptive-colouration` | Distinguish camouflage, warning colouration and mimicry, including Batesian mimicry. | Three stage checks and visual explanations. |
| `bio-social-insect-castes` | Explain division of labour in termite and honeybee colonies. | Three stage checks and caste-role examples. |
| `bio-adaptation-functions` | Link adaptations to feeding, defence, mate attraction, thermoregulation and water conservation. | Three stage checks and project deliverables. |

Seven ideas are checked separately in Concepts, Examples and Context (21 checks total). Each check requires an initial response and a fresh transfer response. The closing topic quiz remains separate.

## Expanded beginner teaching

The lesson now explicitly covers:
- Structural, physiological and behavioural adaptations, with environmental challenge → feature → function reasoning.
- Nigerian habitat contexts, including mangrove pneumatophores, saline coastal conditions, rainforest moisture and savanna seasonality.
- Hibernation and aestivation, including African lungfish aestivation during drought.
- Social organisation and caste roles in termites and honeybees.
- Camouflage, warning colouration, Batesian mimicry and Müllerian mimicry.
- Feeding structures, defensive structures, courtship/mate attraction, temperature regulation and water conservation.
- The distinction between inherited population-level adaptation and individual acclimatisation.

## Virtual practical: natural selection and camouflage

A native offline simulation uses the existing deterministic `NaturalSelectionSimulation` model. The learner:
1. Chooses a dark-bark, light-sand or mixed background.
2. Sets the starting proportion of light and dark inherited variants.
3. Sets the illustrative selection-intensity value and number of generations.
4. Records a prediction before running the model.
5. Compares the frequencies across generations.
6. Explains the result and records a conclusion using the simulated data.

The model uses hypothetical relative survival/reproductive-success indices, not empirical survival probabilities. It assumes two heritable phenotypes, fixed environmental conditions and constant relative fitness. Population size is normalised; mutation, migration, genetic drift, age structure and other ecological interactions are omitted. It illustrates selection on existing inherited variation; it does not model individuals changing colour because they need to.

The lab record is stored locally. The learner must run the simulation, provide a prediction and conclusion, and save the completed record before the LAB pillar can be marked complete.

## Project

The adaptation field-guide project requires structural, physiological and behavioural examples, Nigerian habitat context, colouration, seasonal dormancy, social-insect castes, adaptation functions and a clear distinction between inherited adaptation and acclimatisation. A weighted rubric assesses accuracy, feature–challenge–function reasoning, evidence and communication.

## Evidence and engineering controls

- The shared micro-lesson engine now supports seven mapped adaptation ideas in all three teaching stages.
- The check count is dynamic; the interface does not assume every topic contains exactly six ideas.
- The practical model has deterministic controls and is covered by tests for dark-background selection, light-background selection, neutral conditions and invalid settings.
- Saved virtual-practical evidence is required before the LAB pillar can be marked complete for this topic.
- Internal lab records remain excluded from the learner's ordinary notebook.

## Limits and release decision

- Exact JAMB source wording/page references and independent subject review remain outstanding.
- WAEC mapping remains provisional pending direct verification of the current WAEC Nigeria syllabus.
- Complete board-specific objective, structured-theory and practical assessment mapping is still in development.
- The simulator is an educational model, not empirical ecological data or a substitute for field observation.

Release status: **PARTIAL / IN DEVELOPMENT**.
