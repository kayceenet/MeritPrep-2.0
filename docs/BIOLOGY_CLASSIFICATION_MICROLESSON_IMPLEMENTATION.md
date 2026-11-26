# Biology classification and diversity micro-lesson — implementation record

**Lesson route:** `bio-classification`  
**JAMB area:** A2, Diversity/evolution among organisms.  
**WAEC working area:** Section A, Topic 1, Concept of Living.  
**Status:** first integrated reference slice; WAEC primary-source verification and independent academic sign-off remain outstanding.

## Internal examinable-idea map

The following IDs are MeritPrep traceability keys, not official board statement numbers.

| Idea ID | Teaching outcome | Stage evidence |
|---|---|---|
| `bio-classification-purpose` | Distinguish classification, identification and nomenclature. | Separate Concepts, Examples and Context checks. |
| `bio-taxonomic-hierarchy` | Order taxonomic ranks and explain why lower ranks are more specific; describe the biological species concept and its limitations. | Separate checks at all three stages. |
| `bio-binomial-nomenclature` | Write binomial names with correct order, capitalisation and italics/underlining. | Separate checks at all three stages. |
| `bio-five-kingdoms` | Compare Monera, Protista, Fungi, Plantae and Animalia using cell type, organisation, nutrition and cell-wall features. | Separate checks at all three stages. |
| `bio-plant-divisions` | Distinguish bryophytes, pteridophytes, gymnosperms and angiosperms using vascular tissue, spores, seeds, flowers and fruits. | Separate checks at all three stages and plant dichotomous key. |
| `bio-animal-groups` | Distinguish vertebrates and invertebrates and identify representative vertebrate classes using diagnostic features. | Separate checks at all three stages and animal dichotomous key. |

Each stage contains six mapped checks. Every check requires an initial response and a fresh transfer response before the idea is recorded as demonstrated. This is separate from the closing topic quiz.

## Expanded beginner-to-exam teaching

The lesson now includes additional authored explanations covering:
- Diagnostic characteristics of the major invertebrate phyla: Porifera, Cnidaria, Platyhelminthes, Nematoda, Annelida, Mollusca, Arthropoda and Echinodermata.
- Diagnostic features of fish, amphibians, reptiles, birds and mammals, including examples that prevent common errors (bats are mammals; whales are mammals).
- Plant-group distinctions based on vascular tissue, spores, seeds, flowers and fruit; introductory monocot/dicot comparisons are explicitly identified as generalisations.
- Construction and use of dichotomous keys, the need for observable mutually exclusive alternatives, and how to report uncertain identification.
- The difference between a useful school-level classification framework and modern evidence-based revisions; viruses are described as acellular and not members of the five kingdoms.
- Evolutionary interpretation without presenting living groups as a ladder of progress.

## Interactive practical equivalent

An offline practical workspace provides two guided dichotomous keys:
- Plant specimens: moss, fern, pine and hibiscus.
- Animal specimens: earthworm, pigeon, bat, tilapia, frog and lizard.

The learner inspects a specimen clue card, answers paired diagnostic alternatives in sequence, receives corrective feedback, reaches an identification and records the evidence and decision path. The activity is a guided identification exercise; it does not claim to replace physical specimen handling or an official WAEC specimen list.

## Project

The project asks learners to create a local-organism identification guide with two keys, a five-kingdom comparison, correctly formatted binomial names and an explanation of the acellular status of viruses. Its rubric assesses key logic, scientific accuracy, terminology, evidence-based explanation and communication.

## Engineering and evidence controls

- Stage checks are routed through the shared micro-lesson engine.
- Lesson-stage completion and restored completion now require current initial-plus-transfer evidence for topics that have mapped checks.
- The dichotomous-key decision model is isolated from the Compose UI and covered by unit tests for valid plant and animal paths.
- Lab-session records use the existing local database and remain excluded from the ordinary learner notebook.
- Automated tests cover idea-to-stage coverage, board mapping references, fresh transfer tasks and complete evidence.

## Limits and release decision

- The JAMB crosswalk still needs exact verbatim source statements, page references and independent subject review.
- WAEC mappings are provisional until the direct current WAEC Nigeria syllabus PDF is verified.
- This lesson does not yet have independently reviewed practical marking rubrics, complete WAEC structured-response coverage or official specimen-list validation.
- The app's wider Biology syllabus and other subjects remain in development.

Release status: **PARTIAL / IN DEVELOPMENT**. Do not represent the entire classification syllabus block, Biology course or WAEC pathway as academically verified.


## Expanded specimen-key and visual coverage

The interactive practical now keeps specimen identities hidden behind neutral specimen labels until the learner completes the key. It includes:
- Plant specimens representing bryophytes, pteridophytes, gymnosperms and angiosperms.
- Invertebrate examples representing Porifera, Cnidaria, Platyhelminthes, Nematoda, Annelida, Arthropoda, Mollusca and Echinodermata.
- Vertebrate examples representing fish, amphibians, reptiles, birds and mammals.

The animal key first separates vertebrates from invertebrates, then follows the appropriate diagnostic branch. The full decision path is displayed and saved with the learner's practical record. An incorrect choice cannot produce an identification; each step must match the recorded specimen feature.

The concept visual now presents the taxonomic hierarchy, plant divisions, major invertebrate phyla, vertebrate classes and the five-kingdom teaching model. The project brief now requires learners to construct a key for the major invertebrate phyla as well as the vertebrate classes.


## Run acceptance checklist and verification evidence

**App-source commit:** `e32ab01fa1230cca568fdda7fba53da3b895cb08`  
**Latest documentation commit at verification:** `4470f33ed2973c0820d65e0319619002f8bf559b`  
**CI:** [Android CI run 45864437243](https://github.com/kayceenet/MeritPrep-2.0/actions/runs/45864437243)

| Acceptance item | Status | Evidence / outstanding work |
|---|---|---|
| Correct repository and working branch | PASS | `kayceenet/MeritPrep-2.0`, `docs/exam-syllabus-orchestration`; original Merit repository untouched |
| Mandatory academic framework and Biology crosswalks consulted | PASS | AGENTS, orchestration, academic standard, release checklist, source register, JAMB and WAEC Biology crosswalks |
| JAMB/WAEC primary-source verification | PARTIAL | JAMB IBASS Biology source register checked; WAEC mapping remains provisional pending direct current WAEC PDF |
| Lesson content and mapped teaching ideas | IMPLEMENTED / REVIEW PENDING | Expanded explanations and six internal teaching-idea IDs; exact verbatim source wording/page references and independent review remain outstanding |
| Concepts, Examples and Context checks | PASS (implementation) | Six ideas × three stages; each check requires initial and fresh transfer evidence; unit tests cover map and evidence completeness |
| Visual teaching | PASS (implementation) | Hierarchy, plant divisions, invertebrate phyla, vertebrate classes and five-kingdom comparison visuals |
| Interactive practical equivalent | PASS (implementation) | Hidden specimen labels, branching plant/animal keys, eight invertebrate phyla, five vertebrate classes, decision-path record and model tests |
| Project and rubric | PASS (implementation) | Field-guide deliverables and weighted rubric included |
| JAMB/WAEC exam assessment completeness | FAIL / IN DEVELOPMENT | Existing question bank remains; complete statement-by-statement objective, theory and practical mapping and marking guidance still required |
| Independent subject review | NOT DONE | Qualified Biology reviewer sign-off required |
| Android unit tests | PASS | CI run 45864437243 — `testDebugUnitTest` succeeded |
| Android debug APK build | PASS | CI run 45864437243 — `assembleDebug` succeeded |
| APK artifact | PASS | Artifact `MeritPrep-debug-apk`, SHA-256 `69430f896648ea708b261c824827741babb35fbc4505326ecfd6591b2c5c7e39` |
| Physical-device, accessibility and performance QA | NOT RUN | Must be tested on supported Android devices, including small-screen layout and offline persistence |

**Release decision:** the classification micro-lesson is an implemented reference slice, not an academically verified or release-complete syllabus unit. No whole-course completion claim is made.
