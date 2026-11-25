# MeritPrep 2.0 Product Roadmap

The complete agreed product scope and delivery gates are consolidated in [`MASTER_PRODUCT_SPEC.md`](MASTER_PRODUCT_SPEC.md). This roadmap is the implementation status view; the master specification is the requirements baseline.

## Product learning cycle
Learn → Explore → Practise → Assess → Correct → Retrieve → Master.

## A. Foundation
Android navigation, subject catalogue, lesson shell and build automation.

## B. Curriculum
Versioned JAMB/WAEC syllabus entities; subject/topic/objective mapping; content provenance, reviewer, approval and revision history.

## C. Learning
Structured lessons, worked examples, diagrams, formative checks, downloadable learning packs and offline progress.

## D. Examination
Timed CBT sessions, question randomisation, answer review, scoring policies, WAEC objective/theory/practical practice and transparent feedback.

**Implemented in the current alpha:** a 40-question, 30-minute JAMB-style Biology practice mock and a separate 50-question, 60-minute WAEC-style Biology objective mock. Both use topic-stratified question selection, randomised option order, navigation, answer flags, scoring, explanations and local persistence of active session state. The selection algorithm samples across all available syllabus topics before taking second questions from any topic, reducing the chance that larger topic banks dominate a mock. The time limits are MeritPrep practice settings, not claims about official examination timings. The app stores the question order, selected answers, flags and session end time, then attempts to restore an active session on launch. Fifteen original WAEC-style Biology structured-response tasks are available with model responses, self-check marking points, locally saved learner responses and locally persisted checked rubric points. Coverage includes cells, food tests, osmosis, photosynthesis, circulation, ecology, population sampling, genetics, classification, adaptation, respiration, kidney function, flowering-plant reproduction, variation and soil. Previously saved plain-text responses remain readable. These are not official papers or marking schemes. The population ecology lesson includes an interactive quadrat-sampling estimator: learners enter counts from equal-area quadrats and habitat area to calculate mean count, organism density and an estimated population. Both the practical-lab quadrat simulation and the population lesson now use the same shared calculation engine, avoiding divergent formula implementations. It validates non-negative counts, positive finite areas, sampled area not exceeding habitat area, integer overflow and non-finite calculation results; unit tests cover normal estimates and invalid edge cases. A respiration comparison visual distinguishes cellular respiration from breathing and gas exchange, and compares aerobic respiration with anaerobic pathways in muscle and yeast. The population ecology lesson also includes a primary-versus-secondary succession visual that explains the role of soil and why succession does not follow one universal sequence. The flowering-plant reproduction visual now teaches double fertilisation explicitly, distinguishing diploid zygote formation from triploid endosperm formation and identifying the later fates of the ovule, integuments and ovary. The adaptation lesson compares structural, physiological and behavioural adaptations and distinguishes inherited adaptation from individual acclimatisation.

## E. Mastery and revision
Topic mastery based on repeated evidence, spaced retrieval, flashcards, mistake notebook and study planner.

**Implemented in the current alpha:** local revision-card storage, a four-rating spaced-retrieval scheduler, 198 Biology revision cards spanning the mapped Biology topics, a revision screen and unit-tested scheduling rules. The objective-practice mastery indicator requires at least 10 attempts, 8 distinct questions, 2 distinct study days and 80% correct. It is deliberately not presented as proof of mastery across theory or practical work. The mistake notebook captures incorrect and unanswered Biology practice items with the selected answer, correction and explanation; learners can mark items reviewed. The seven-day Biology planner prioritises weaker, unattempted and less recently studied topics using a transparent evidence-based heuristic, stores progress offline and supports daily targets from 60 to 150 minutes.

## C2. Scientific animation and interactive media

**New cross-subject requirement:** audit every Biology, Chemistry and Physics syllabus topic for purposeful scientific animations, short GIF sequences, interactive diagrams, simulations, virtual labs and authentic microscopy/video. Build original Android-friendly models for core concepts where practical; use approved third-party embeds only when exact rights permit; otherwise open the provider's official resource with a guided MeritPrep task. Essential learning must have an offline explanation or approved media fallback. Record asset provenance, licence, attribution, review status, file size and offline availability. LabXchange, HHMI BioInteractive and PhET are discovery sources, not blanket redistribution permissions. The detailed policy and initial Biology topic priorities are in [SCIENCE_MEDIA_INTEGRATION_STANDARD.md](SCIENCE_MEDIA_INTEGRATION_STANDARD.md).

**First implementation delivered:** an in-app Explore Visual Science screen is linked from Learn. Its 22 curated resources cover Biology, Chemistry and Physics and include PhET simulations, LabXchange virtual-lab activities and an HHMI photosynthesis animation. Each entry carries a course topic ID, source, licence/terms reference, permitted-use note, pre-exploration prediction, post-exploration reflection and offline alternative. The screen filters by subject and opens the mapped MeritPrep lesson as well as the provider's original resource. It records when a learner opens an external resource and saves the learner's written observation/reflection in the existing on-device study-notes store. Native Biology explorers allow learners to vary light intensity, carbon dioxide availability and temperature in a photosynthesis limiting-factor model; compare salivary amylase, pepsin and trypsin across temperature and pH in an enzyme activity model; and follow inherited light/dark trait frequencies across generations in a deterministic natural-selection model with adjustable starting frequencies and relative survival values. Additional native Biology models now let learners explore Mendelian inheritance with a live Punnett square, predict plant- and animal-cell responses to osmosis, and investigate diffusion direction and a simplified permeability/concentration-gradient rate index. The selection model explicitly excludes mutation, migration, genetic drift and population-size changes, and explains that individuals do not change inherited traits during their lifetime. Both calculation engines validate inputs and are unit-tested; both clearly label their outputs as simplified illustrative indices rather than measured biological data, and the enzyme profiles' optima are approximate teaching values. The current external resources are links, not copied media; no third-party asset is embedded or redistributed without an individual rights review.

**Next delivery gates:** add further native simulations and step-through scientific animations; audit all syllabus topics for relevant media; verify individual resource availability and reuse rights; add learner activity/progress records; and create approved offline media packs with accessible fallbacks.

## F. Practical learning
Inventory existing APK assets and lab logic; rebuild Biology, Chemistry and Physics simulations with safe, accessible controls and clear learning objectives.

**Implemented prototypes:** Biology food-test interpretation with prediction feedback, osmosis, microscope magnification, quadrat-based ecological sampling, a dichotomous classification key, a biological drawing self-check and a persistent experiment planner with results entry, a mean-based results graph, observed-trend description, graph-interpretation guidance, conclusion writing and evaluation of errors; introductory Physics Ohm's law and Chemistry concentration simulations. The experiment planner saves locally through the existing study-notes store. Repeated readings are screened for possible unusual values using an interquartile-range rule when at least five readings are available; the app flags values for investigation and never deletes them automatically. These are learning prototypes, not a complete WAEC practical examination system.

## G. Platform operations
Authenticated sync, teacher/admin CMS, analytics, backups, privacy and security review.

**Governance foundation added:** `ContentGovernancePolicy` now evaluates content provenance, syllabus mapping, version, authorship, independent approval, reviewer/date and reuse rights. It also blocks an official-past-question label unless the item is a reviewed question with verified exam year, paper and question number. This policy is unit-tested. It is not yet wired into SQLite, the learner publishing path or a teacher/admin CMS.

## Current alpha status

- Biology syllabus map: 24 units represented; each unit resolves to an authored lesson draft.
- Biology lessons: 24 beginner-oriented lesson drafts, mapped to the syllabus units; independent academic review is pending.
- Biology content integrity: automated unit tests check all 24 syllabus-to-lesson mappings, required beginner-lesson teaching elements, valid formative answer keys, unique question IDs, question provenance, glossary uniqueness and glossary-to-lesson links.
- Biology original objective practice: 244 items across the 24 units; items are explicitly marked as original and unverified.
- WAEC-style theory: 15 original structured-response tasks with model responses, self-check points and locally saved responses; independent review is pending.
- Practice: topic-focused sessions and a full Biology question-bank mode with shuffled question and option order.
- Timed CBT: a 40-question, 30-minute Biology mock with topic-stratified question selection, shuffled options, answer navigation, flags, explanations and local active-session recovery logic.
- Revision: 198 offline Biology cards with Again/Hard/Good/Easy scheduling.
- Mistake notebook: offline capture of incorrect quick-practice answers and incorrect/unanswered CBT items, with review status.
- Study planner: adaptive seven-day Biology schedule with configurable daily time target, offline activity completion and shortcuts into lessons, practice and revision.
- Mastery indicator: objective-question evidence threshold of 10 attempts, 8 distinct questions, 2 study days and at least 80% correct; theory and practical evidence are not yet included.
- Practical learning: seven Biology practical prototypes, including a quadrat population estimator with worked calculations, a controlled soil drainage/water-retention investigation and a locally saved experiment planner/results record. The planner captures variables, method, observations, conclusion and evaluation; calculates means and ranges from repeated readings; plots a mean-based results graph; describes the observed trend; and flags possible unusual readings for investigation without removing them. It does not automatically grade the scientific validity of a plan or conclusion. Introductory Physics and Chemistry simulations are also present.
- Academic approval: pending independent subject review; no generated question is presented as an official past question.
- Other subjects: starter lesson content only; full syllabus mapping and course development remain outstanding.

## Acceptance principles
- Do not equate lesson completion with mastery.
- Never present unverified questions, answer keys or marking rules as official.
- Store source, rights/licence, reviewer, approval status and content version.
- Core downloaded content should remain usable offline.
- Never promise a particular examination score.


**Cell-division learning model added:** a staged mitosis/meiosis explorer now teaches chromosome and chromatid accounting, DNA replication, homologous chromosome pairing and separation, crossing over, independent assortment and the final daughter-cell outcomes. It uses a configurable pure-Kotlin model with a default diploid number of 4 for teaching clarity; the automated tests also check a human diploid count of 46. The learner advances stage by stage and can compare mitosis with meiosis. The model is a simplified schematic, not a biological microscopy animation.


**Cell-division visual update:** the mitosis/meiosis lesson now includes a stage-responsive Compose chromosome schematic. It shows replicated chromosomes, chromatid separation, the two-cell intermediate after meiosis I, and the four haploid products after meiosis II. Anaphase counts and the diagram's representative chromosome counts were reviewed to distinguish homologous-chromosome separation from sister-chromatid separation. The drawing is explicitly labelled as schematic and not to scale.


**Cell-division assessment coverage:** added 12 original, syllabus-linked objective questions covering chromosome/chromatid counting, mitosis, meiosis I and II, crossing over, independent assortment, the single DNA-replication cycle, human gamete chromosome number and fertilisation. A dedicated content test checks that all 12 items remain present and that the set covers the core concepts.


**Structured-response practice:** added an eight-mark original WAEC-style theory task comparing mitosis and meiosis using a diploid example (2n = 6). Its eight marking points assess chromosome behaviour, crossing over, chromosome reduction, daughter-cell outcomes, genetic variation and biological importance. A regression test checks the task and marking guide.


**ABO inheritance explorer:** added a tested multiple-allele model for the ABO blood-group system and an interactive parent-genotype cross. It teaches the codominance of Iᴬ and Iᴮ, dominance of each over i, all six common genotypes and four phenotypes. The model explicitly excludes Rh(D), transfusion compatibility and rare variants.


**ABO examination practice:** four new original objective questions test genotype-to-phenotype mapping, gamete transmission and offspring probabilities for ABO crosses. Their answer positions preserve the bank-wide A–D balance.


**Genetics glossary expansion:** added definitions and examples for the cell cycle, interphase, sister chromatids, homologous chromosomes, crossing over, haploid and diploid cells, codominance, multiple alleles and ABO blood groups. Regression coverage checks that these terms remain present, accurate and linked to the curriculum.


**X-linked inheritance explorer:** added a probability model for a single X-linked recessive allele, with selectable mother and father genotypes, gametes, offspring genotypes and sex-specific outcomes. Tests cover carrier mothers, affected mothers, affected fathers and probability totals. The model is explicitly limited to a simplified recessive example and is not a clinical risk calculator.


**X-linked exam practice:** four new original questions cover the X chromosome's inheritance from each parent, why recessive X-linked alleles may be expressed in males, and conditional versus overall offspring probabilities. The additions preserve exact A–D answer balance across the 264-question Biology bank.


**Cell-division glossary:** expanded the learner reference with chromosome, chromatid, centromere, cytokinesis, bivalent, spindle fibre and independent assortment. Mitosis and meiosis entries now link to the growth lesson and explicitly distinguish the single DNA-replication round from the two meiotic divisions. Regression tests check the new terms and key definitions.


**Cell-division model validation:** the chromosome-count engine now rejects values that could overflow its integer arithmetic. Tests explicitly check chromosome and chromatid totals during mitotic anaphase, meiotic anaphase I and meiotic anaphase II, as well as invalid and excessively large starting chromosome numbers.


**Meiosis visual refinement:** the chromosome diagram now depicts paired homologous chromosomes as bivalents during prophase I and metaphase I, before showing homologous separation in anaphase I. This makes the difference between homologous-chromosome separation and sister-chromatid separation easier to see.


**Cell-division retrieval practice:** the Biology growth lesson now includes a separate, sequential 12-question interactive check built from the original cell-division question bank. Learners select an answer, receive immediate correctness feedback and an explanation, then progress through the set to a final score with a retry option. The existing lesson-completion check and local lesson tracking remain unchanged.


**Lesson integration:** extracted the stage-by-stage mitosis/meiosis model into a reusable Compose card and embedded it directly in the Biology growth lesson, alongside the 12-question retrieval check. The same model remains available in the Science Media area; the two entry points now share the same implementation rather than maintaining separate copies.


**DNA-content accounting:** the mitosis/meiosis stage model now tracks DNA content in C units alongside chromosome and chromatid counts. It distinguishes the 2C starting state, 4C state after S phase, the intermediate DNA content in each meiotic cell after meiosis I, and the 1C haploid products after meiosis II. Tests verify the key transitions.


**WAEC Chemistry theory expansion:** increased the original structured-response bank from 6 to 12 tasks. New marking-guided questions cover atomic structure and periodicity, Charles's law, collision theory and reaction rates, energy profiles, solubility and crystallisation, and iron extraction. Each task includes explicit marks, a stepwise marking guide and examiner-facing guidance. Regression tests check the expanded count, unique IDs, completeness and selected scientific details.
