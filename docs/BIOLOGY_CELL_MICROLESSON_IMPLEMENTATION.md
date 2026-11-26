# Biology cell-structure micro-lesson — implementation record

**Lesson route:** `bio-cell`  
**Course area:** JAMB Biology A1, Living organisms; WAEC Biology Section A and Nigeria Section C, Concept of Living.  
**Implementation status:** first integrated reference slice; not a claim that the full Biology unit or either examination syllabus is complete.

## Source and scope boundaries

- JAMB primary source: [JAMB IBASS Biology syllabus](https://ibass.jamb.gov.ng/assets/uploads/Biology.pdf). The repository crosswalk groups the relevant requirements under A1, Living organisms.
- WAEC working source: [WAEC Biology 2026/2027 reproduction](https://waecsyllabus.com/biology/). This is a secondary reproduction. The relevant WAEC mapping remains **PROVISIONAL** until verified against a direct current WAEC Nigeria source.
- The internal identifiers below are MeritPrep traceability keys. They are not official examination-board numbering.

## Atomic ideas implemented in the first reference lesson

| Internal idea ID | Learner outcome | JAMB mapping | WAEC working mapping |
|---|---|---|---|
| `bio-cell-theory` | Explain the central statements of cell theory and apply the origin-of-cells principle. | A1: cell structure and organisation; characteristics of living organisms. | Section A: Concept of Living; Section C: cell theory. |
| `bio-cell-membrane` | Explain selective permeability and apply osmosis to cell boundaries. | A1: cell structure; B3: transport, diffusion and osmosis. | Section A: Concept of Living; Section C: Concept of Living. |
| `bio-cell-organelles` | Relate nucleus, mitochondrion, ribosome, Golgi apparatus, chloroplast and vacuole to function. | A1: plant and animal cell structures and functions. | Section A: cell structure and function. |
| `bio-cell-types` | Compare typical plant and animal cells without treating every plant cell as photosynthetic. | A1: plant and animal cell structures and functions. | Section A: cell structure and function. |
| `bio-prokaryote-eukaryote` | Distinguish prokaryotic and eukaryotic cell organisation. | A1: cell structure and organisation; unicellular examples. | Section A: cell structure and organisation. |
| `bio-levels-organisation` | Sequence cells, tissues, organs, organ systems and organisms; distinguish unicellular organisation. | A1: levels of organisation. | Section A: levels of organisation. |

## Point-of-teaching evidence

Each of the six idea IDs has a separate check in each of these stages:

1. **Concepts:** foundational understanding and discrimination.
2. **Examples:** reasoning in a worked or near-transfer case.
3. **Context:** transfer to a new biological situation.

Every stage check contains an initial question and a fresh transfer question. Both must be answered correctly before the idea is marked as demonstrated for that stage. Incorrect answers receive misconception-specific feedback where available, a hint and an opportunity to retry. Evidence is stored locally through the existing study-note persistence API under a dedicated note key; existing progress records are not replaced.

The closing topic quiz remains separate and cannot substitute for these 18 stage-specific checks.

## Current limits and next implementation work

- The reference implementation covers the six listed atomic ideas in `bio-cell` only. Other Biology topics and all other subjects still require their own statement decomposition, mapped checks and stage coverage.
- The current checks are multiple-choice with a required fresh transfer item. Later authoring batches should add constructed explanations, labelled diagrams, short responses and practical/data responses where appropriate.
- The existing virtual-lab and project pillars remain separate systems. This implementation does not certify the cell lesson's practical, project or academic-review requirements.
- Internal reference keys must be reconciled to the next statement-level syllabus register when exact source wording and page references are captured.
- Independent Biology subject review and direct WAEC primary-source verification remain outstanding.

## Release gate

Do not mark the Biology course complete or academically verified on the strength of this reference slice. Release status is **PARTIAL / IN DEVELOPMENT** until all applicable syllabus statements, six pillars, assessment evidence, reviewer sign-off and technical release checks are complete.


## Follow-on practical and project implementation

The reference lesson now also includes:

- An offline, topic-routed cell microscopy virtual practical comparing onion bulb epidermis with cheek epithelial cells.
- A pre-run prediction and safety acknowledgement, apparatus list, method, independent/dependent/controlled variables, specimen and magnification controls, simulated observation record, learner notes, evidence-based conclusion and model evaluation.
- A deterministic field-of-view model assuming a 10× eyepiece and an 18 mm eyepiece field number. At 40×, 100× and 400× total magnification, the illustrative field diameters are 4.5 mm, 1.8 mm and 0.45 mm respectively. The app labels these as model estimates, not measurements.
- Explicit limits: the schematic does not substitute for physical microscope handling, and actual visibility depends on specimen preparation, staining, focus and microscope quality.
- A cell-field-guide project brief with required diagrams, comparisons, cell-theory explanation, organisation example, root-hair exception and a weighted success rubric.
- Local persistence for the virtual-lab record. Internal evidence and lab-session records are excluded from the learner's ordinary notebook listing.

Automated model tests cover field-of-view scaling, invalid magnification, and correct distinctions for onion and cheek cells. These tests validate the deterministic model; they do not constitute independent academic review or physical-lab validation.
