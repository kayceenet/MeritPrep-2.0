# MeritPrep 2.0 — Examination Syllabus Knowledge Base and Orchestration Framework

**Status:** Mandatory project-wide academic operating specification  
**Applies to:** Every JAMB UTME and WAEC WASSCE subject, lesson, learning activity, assessment and release  
**Authority:** Current examination-board publications take precedence over textbooks, coaching notes, third-party websites and existing app content.

## 1. Mission and non-negotiable outcome

MeritPrep must provide a carefully sequenced, beginner-to-examination-mastery learning programme aligned to the current official JAMB UTME and WAEC WASSCE requirements relevant to Nigerian candidates.

The knowledge base is not a pile of notes. It is a governed, versioned academic model that connects:

**exam board → examination → subject → official syllabus version → syllabus section → atomic syllabus statement → prerequisite knowledge → learning outcomes → lesson sequence → teaching assets and activities → assessment evidence → feedback/remediation → mastery decision.**

Every taught or assessed item must be traceable to its source and learning purpose. No topic is complete merely because it has a title, a paragraph, a diagram, a simulation link or a handful of questions.

MeritPrep may be designed to support ambitious outcomes, including students targeting 300–380+ UTME scores, but it must never guarantee a score. Report learner evidence and readiness honestly.

## 2. Examination-board separation

Maintain separate authoritative mappings for:

1. **JAMB UTME** — use the current JAMB IBASS syllabus and relevant official JAMB examination guidance.
2. **WAEC WASSCE (Nigeria)** — use the current WAEC syllabus and the Nigeria-specific scope, paper structure and practical requirements.

Do not assume that a JAMB topic list is a WAEC syllabus, or that one board's wording, depth, practical demand, question format, timing or weighting applies to the other. Record overlaps explicitly, but preserve board-specific differences.

For WAEC, distinguish common syllabus content from national/regional sections and identify the Nigerian candidate scope. Do not import Ghana-only or other-country-only material into the Nigerian pathway unless a current official Nigerian source requires it.

For JAMB, distinguish syllabus requirements from general study enrichment. Do not present a coaching-provider prediction, past-question frequency claim or unofficial topic list as a JAMB requirement.

## 3. Source authority and version control

### 3.1 Source hierarchy

Use sources in this order:

1. Current official JAMB IBASS syllabus and official JAMB notices/documents.
2. Current official WAEC Nigeria syllabus and official WAEC notices/documents.
3. Official board-issued specimen papers, examination regulations, practical instructions and examiner materials, where available.
4. Official government curriculum documents only where they clarify foundational school learning and do not override board syllabuses.
5. Reputable textbooks and peer-reviewed educational/scientific references for teaching explanations, not for inventing examination requirements.
6. Secondary syllabus mirrors only as provisional discovery aids until verified against a primary board source.

If the primary source cannot be obtained, mark the mapping **PROVISIONAL — PRIMARY SOURCE NOT VERIFIED**. Do not silently promote a secondary mirror to official status.

### 3.2 Required source metadata

Every source record must contain:
- Exam board and jurisdiction.
- Examination and subject.
- Exact document title.
- Syllabus/session/year/version, exactly as printed.
- Official URL or source location.
- Publication/issue date when shown.
- Date accessed and checked.
- Page count and relevant page ranges.
- File checksum or repository revision where practical.
- Retrieval method and any extraction limitations.
- Verification status and reviewer.

A new syllabus version creates a new immutable source record. Never overwrite historical wording. Compare versions and document additions, removals, renamed statements and altered examination demands.

### 3.3 Source change monitoring

At the beginning of each examination cycle, and whenever a board publishes a change:
1. Recheck official source availability.
2. Compare the new document with the stored version.
3. Identify changed statements and affected lessons/questions.
4. mark impacted material for review.
5. Reapprove before representing it as current.

## 4. Atomic syllabus knowledge model

Break each official syllabus into the smallest meaningful, assessable statements without changing the board's intent. Preserve the original wording verbatim alongside a plain-language interpretation.

Each statement receives a stable identifier, for example:

`JAMB-BIO-2026-A1-OBJ-001` or `WAEC-NG-CHEM-2026-SC-C-004`.

The identifier scheme must include board, subject, version, section and statement sequence. Never reuse an identifier for a different statement.

### 4.1 Required record fields

Each atomic syllabus record must include:

| Field | Requirement |
|---|---|
| statement_id | Stable unique ID |
| board / jurisdiction | JAMB or WAEC; Nigeria scope where applicable |
| exam / subject | UTME or WASSCE and exact subject |
| source_version | Exact linked source record |
| section_path | Official section, topic and subtopic hierarchy |
| source_wording | Exact syllabus wording |
| interpretation | Clear learner-facing meaning; not a replacement for source wording |
| demand_type | Knowledge, understanding, application, analysis, practical, data handling, communication or other justified skill |
| prerequisites | Earlier concepts and assumed skills |
| outcomes | Observable learner actions using measurable verbs |
| lesson_refs | Exact lesson and section IDs |
| asset_refs | Diagram, animation, model, simulation or media IDs |
| practical_refs | Experiment, specimen, apparatus or virtual investigation IDs |
| assessment_refs | JAMB objective and WAEC objective/theory/practical/data item IDs as applicable |
| misconception_refs | Known errors and corrective explanations |
| remediation_refs | Alternate explanation, worked example and fresh practice |
| reviewer / dates | Author, subject reviewer, review date and next review |
| status / evidence | Controlled status and links to proof |

### 4.2 Coverage states

Use these distinct states; do not collapse them into a single percentage:

- **NOT AUDITED** — no statement-level audit yet.
- **SOURCE UNVERIFIED** — source is secondary, outdated or incomplete.
- **GAP** — requirement has no adequate learning provision.
- **IN DEVELOPMENT** — content or activity is being authored.
- **IMPLEMENTED** — learning experience exists and is linked.
- **ASSESSED** — valid assessment evidence exists for the statement.
- **MASTERY-READY** — feedback, remediation and skill-level mastery checks are implemented.
- **ACADEMICALLY VERIFIED** — qualified subject review passed against the official source.
- **RELEASE VERIFIED** — academic and technical release gates passed for the exact app build.

A topic is only as complete as its weakest applicable atomic statement. Do not infer statement coverage from topic counts, lesson counts, word counts or question totals.

## 5. Curriculum architecture and learning sequence

The knowledge base must represent prerequisite relationships as a directed learning graph. Teach foundational knowledge before dependent material. When learners lack a prerequisite, provide a bridge lesson or diagnostic remediation rather than assuming they know it.

For each subject:
1. Organise official syllabus sections and topics without changing their order/meaning unnecessarily.
2. Split broad topics into atomic teachable concepts.
3. Identify prerequisites, vocabulary, numeracy and literacy requirements.
4. Sequence concepts from concrete/familiar to abstract/formal.
5. Identify cross-topic dependencies and cumulative ideas.
6. Map each sequence to actual stable app lesson IDs.
7. Preserve separate JAMB and WAEC pathways where depth or assessment differs.

The app's navigation or feature catalogue is not the curriculum. The authoritative academic map must drive lesson generation, assessments, progress and reporting.

## 6. Mandatory lesson design: zero assumed knowledge to independent mastery

Each substantial learning unit must provide the following sequence, adapted only when a stage is genuinely irrelevant and the reason is recorded.

### Stage 1 — Orientation and diagnostic
- A familiar phenomenon, problem, specimen, text, diagram or question.
- Clear outcomes and why the concept matters.
- Prerequisite check and vocabulary preview.
- A low-stakes diagnostic that identifies misconceptions without shaming the learner.

### Stage 2 — Foundations
- Explain every essential term before relying on it.
- Build from simple concrete examples.
- Teach prerequisite numeracy, graph reading, units, language or scientific ideas where needed.
- Check understanding before introducing dependent concepts.

### Stage 3 — Full concept teaching
- Explain what it is, how it works, why it happens and when it applies.
- Include accepted definitions, principles, classifications, structures, functions and mechanisms required by the syllabus.
- Explain causal sequences and relationships rather than offering memorisation-only notes.
- State conditions, exceptions, limitations and common confusions.
- Use correct formal terminology after the plain-language explanation.

### Stage 4 — Visual and interactive understanding
- Embed accurate diagrams, animations, models, simulations or specimen views at the exact point they teach.
- Let learners pause, replay, inspect, manipulate or compare where pedagogically useful.
- Ask prediction, observation and explanation questions.
- Explain every essential visual in text; do not make colour the only carrier of meaning.
- External resources are supplementary. Core learning must remain usable offline wherever practical.

### Stage 5 — Worked examples and guided practice
- Show complete reasoning, not just the final answer.
- For calculations, show formula choice, symbols, substitution, units, conversions, significant figures and interpretation.
- For language and humanities, model close reading, evidence selection, argument construction, chronology, comparison, source evaluation and structured writing as appropriate.
- Fade scaffolding gradually: worked example → partially completed example → guided task → independent task.

### Stage 6 — Misconceptions and error analysis
- State likely incorrect ideas explicitly.
- Explain why each is wrong and how to detect the error.
- Provide contrasting examples and corrective questions.

### Stage 7 — Application and transfer
- Use unfamiliar but syllabus-relevant contexts.
- Include interpretation of diagrams, tables, graphs, maps, passages, sources, specimens, experimental results or scenarios as appropriate.
- Require learners to explain choices and justify conclusions.

### Stage 8 — Examination practice
- Train the exact command words and response formats used by the board.
- Include mixed-difficulty questions mapped to atomic syllabus statements.
- Explain correct answers and why distractors or weak responses fail.
- Provide marking guidance without falsely claiming it is an official marking scheme.

### Stage 9 — Retrieval, remediation and mastery
- Test recall without notes.
- Revisit learning through spaced retrieval and cumulative practice.
- If a learner fails a skill, provide a different explanation and fresh equivalent assessment.
- Record evidence by skill, not just lesson completion.
- A learner must not gain mastery by opening a lesson, watching an animation or guessing a single answer.

## 7. Board-specific assessment blueprint

### 7.1 JAMB UTME
For every applicable statement, create original objective practice that samples:
- Accurate recall and terminology.
- Conceptual understanding.
- Application and problem solving.
- Interpretation of data, diagrams, passages, tables or scenarios.
- Multi-step reasoning and common misconceptions.

Use plausible distractors, one defensible best answer, an explanation for the key and explanations for distractors. Balance difficulty and avoid repetitive template questions. Timed mocks must follow the current official JAMB format only after the current official guidance has been verified. Otherwise label the timer as a MeritPrep practice setting.

### 7.2 WAEC WASSCE
Map relevant syllabus statements to the applicable components of the current WAEC subject examination:
- Objective items.
- Structured and essay/theory responses.
- Calculations, diagrams, drawings, graphs and data interpretation.
- Practical or alternative-to-practical tasks where prescribed.
- Required subject-specific skills and national content.

Provide model responses, indicative marking points, accepted equivalents and common mark-losing errors. Label these as MeritPrep learning guidance unless directly sourced from an official marking scheme. Do not hard-code paper duration, marks, compulsory sections or candidate choices until checked against the current official WAEC document.

### 7.3 Question quality and provenance
Every item must have:
- Unique item ID and original/licensed provenance.
- Board, subject, source version and atomic statement links.
- Skill/cognitive demand and difficulty rationale.
- Stem, options or response prompt, correct key/model answer.
- Full explanation and distractor analysis where applicable.
- Marking rubric and acceptable alternatives for constructed responses.
- Reviewer and validation status.
- Duplicate/similarity check.

Do not label original MeritPrep items as past questions. Past questions require verified year/source and permitted use.

## 8. Practical work, investigations and virtual laboratories

Where a syllabus requires practical competence, teach both the physical technique and a meaningful digital practice experience. A virtual lab is not a substitute for real apparatus handling where schools can provide it.

A complete virtual investigation includes applicable elements:
- Briefing, scientific question and outcomes.
- Apparatus/materials, labels, functions and setup.
- Safety, hazards, PPE, precautions and disposal.
- Independent, dependent and controlled variables.
- Ordered method and learner decisions.
- Prediction and scientific justification before execution.
- Meaningful experiment controls and scientifically coherent model behaviour.
- Qualitative observations and learner-recorded readings.
- Repeats, data tables, units and precision.
- Graph choice, plotting, scale, best-fit and gradient/intercept interpretation where relevant.
- Calculations with equations, substitutions and units.
- Evidence-based conclusion.
- Errors, limitations, reliability, validity and improvements.
- Separate assessment of method, observation, data, graph, calculation and evaluation.

All simulated data must be labelled **SIMULATED**. Document equations, algorithms, assumptions, valid ranges, initial conditions, boundaries, expected limiting cases and model limitations. Never describe model outputs as measurements from a real experiment.

For non-science subjects, provide authentic applied activities where appropriate: map interpretation, source analysis, case studies, structured debates, document analysis, economic data interpretation, civic scenarios, language production and other syllabus-aligned tasks.

## 9. Subject-specific standards

Apply the shared framework to every subject, then add discipline-specific quality controls.

- **Biology:** biological organisation, accurate structures/processes, classification, microscopy, specimens, drawings, ecological field methods, genetics, experimental reasoning and conservation.
- **Chemistry:** particle model, atomic/molecular representations, equations, stoichiometry, units, conditions, reaction mechanisms at required level, laboratory safety, qualitative/quantitative analysis and practical calculations.
- **Physics:** physical models, vectors, diagrams, derivations at required level, units/dimensions, graphs, measurement uncertainty, apparatus, experimental data and boundary cases.
- **Mathematics:** definitions, notation, prerequisite fluency, derivations/proofs where required, multiple solution methods, worked reasoning, graphs, modelling and error analysis.
- **English Language:** comprehension, vocabulary in context, summary, grammar, lexis/structure, oral/aural skills where required, writing genres, evidence-based feedback and exam-specific response practice.
- **Economics:** concepts, assumptions, diagrams, calculations, data interpretation, policy/application contexts and evaluation of arguments.
- **Government:** constitutional concepts, institutions, political systems, Nigerian and comparative contexts, chronology, source interpretation and structured argument.

These are discipline controls, not a complete syllabus list. Exact teaching content must come from each current official subject syllabus.

## 10. Quality assurance and academic sign-off

Every subject and lesson batch must pass the following reviews:

1. **Source audit:** exact current board source and version verified.
2. **Coverage audit:** every atomic statement has a traceable mapping; gaps are explicit.
3. **Pedagogical audit:** beginner-to-advanced sequence is coherent and sufficiently deep.
4. **Subject accuracy audit:** qualified subject reviewer checks facts, methods, calculations, terminology and scope.
5. **Assessment audit:** items are valid, unambiguous, balanced and mapped to the right demands.
6. **Practical/media audit:** activity is scientifically/pedagogically meaningful, safe, accessible and rights-compliant.
7. **Technical audit:** lesson routing, persistence, offline behaviour, accessibility, performance and tests pass.
8. **Release audit:** all blockers are resolved or explicitly disclosed; exact commit and build evidence are recorded.

No author may self-certify a high-stakes subject as academically verified without independent review evidence. Store reviewer identity/role, review date, scope, findings and resolution.

## 11. Learner mastery and readiness reporting

Track performance by syllabus statement and skill domain, not merely by total questions answered. At minimum, distinguish:
- Recall and terminology.
- Explanation and conceptual understanding.
- Application and transfer.
- Quantitative/problem-solving skill where relevant.
- Practical, data-handling and communication skill where relevant.
- Examination execution: timing, accuracy and response quality.

Use repeated evidence across fresh items and cumulative assessments. Set subject-specific thresholds only after validation. Critical safety or method failures must not be hidden by a high aggregate score.

Readiness reports must show:
- Verified syllabus coverage versus provisional/unverified areas.
- Mastery by topic and skill.
- Weak areas and recommended remediation.
- Timed practice performance and trend.
- Practical/theory evidence where applicable.
- Limitations and unassessed requirements.

Do not turn a small diagnostic sample into a score prediction. Any score estimate requires a validated predictive model and transparent uncertainty; otherwise show observed practice performance only.

## 12. Knowledge-base governance and app integration

The academic knowledge base is the source of truth. App screens, lesson catalogues, question banks, virtual labs, revision cards, study plans and progress dashboards must consume or link to its stable IDs.

Requirements:
- Stable IDs across releases; use explicit migration maps if an ID must change.
- No orphaned syllabus statements, lesson sections, assessment items or practical activities.
- No unreachable mapped activities or invalid topic routing.
- Board-specific filters for JAMB and WAEC pathways.
- Version-aware content so a syllabus update can invalidate only affected mappings.
- Offline-first storage for core content and learner evidence.
- Clear distinction between official syllabus text, original MeritPrep teaching prose, third-party licensed media and simulated data.
- Rights/attribution record for every external media resource.
- Do not copy teaching content from the separate original Merit repository; it is a read-only UI/interaction reference only. All MeritPrep academic prose, questions, examples and investigations must be independently authored.

## 13. Orchestrated development workflow

Work in coherent, reviewable subject slices. Do not scatter shallow edits across every subject.

**Phase 0 — Repository and source baseline**
- Read `AGENTS.md`, this framework, the academic mastery standard, release checklist and source register.
- Inspect current branch, commit, source files, topic IDs, database schema, tests and existing crosswalks.
- Verify official source versions and identify unresolved source blockers.

**Phase 1 — Syllabus ingestion**
- Capture the exact official syllabus.
- Extract hierarchy and atomic statements.
- Assign stable IDs and preserve exact wording.
- Validate extraction against page images/PDF where text extraction is uncertain.

**Phase 2 — Coverage and dependency mapping**
- Map every statement to lesson IDs and prerequisite graph.
- Compare JAMB/WAEC overlap and differences.
- Produce gap report with no assumed coverage.

**Phase 3 — Instructional design**
- Write measurable outcomes.
- Design lesson progression, examples, visuals, simulations/practicals and misconception checks.
- Specify assessments and remediation before declaring content complete.

**Phase 4 — Content authoring**
- Author original, sufficiently detailed instruction from beginner level.
- Add worked examples, diagrams, interactions, practice and examiner guidance.
- Review scientific/disciplinary accuracy and language.

**Phase 5 — Implementation**
- Connect stable syllabus IDs to app lessons, assets, practicals, questions and learner progress.
- Preserve existing architecture and functionality.
- Ensure offline operation and graceful error states.

**Phase 6 — Validation**
- Run source/coverage audits, content tests, calculations, routing, duplicate checks, accessibility checks and device/build tests.
- Complete the per-run release checklist with PASS/FAIL/N/A and evidence.

**Phase 7 — Release and maintenance**
- Commit to `kayceenet/MeritPrep-2.0` only.
- Verify exact commit, CI run and artifact.
- Publish only the statuses actually earned.
- Re-audit when board syllabuses change.

Each run must deliver a coherent learning slice and an evidence report: files changed, statements covered, statements still blocked, tests/builds run, academic review state and next slice. Do not claim completion from a plan.

## 14. Initial repository audit and known source limitations

At the time this framework was added, the repository already contained:
- JAMB Biology, Chemistry and Physics crosswalk documents.
- A Mathematics curriculum/coverage map.
- WAEC Biology, Chemistry and Physics working crosswalks/maps.
- An official syllabus source register.
- Academic mastery and release-checklist documents.

These assets are useful foundations, not proof of complete syllabus compliance.

The source register dated 25 November 2026 records official JAMB IBASS PDFs for Biology, Chemistry and Physics. It explicitly states that JAMB statement-level coverage still needs verification. It also records that direct current WAEC PDFs had not been located from WAEC's official domain; secondary 2026/2027 copies were provisional working references. Therefore:
- Do not claim full JAMB statement-level compliance for those subjects until audited and reviewed.
- Do not claim WAEC source verification or full Nigerian WAEC coverage until direct primary-source verification is completed.
- Do not treat seven subject entry points as complete syllabus coverage.
- Expand official source acquisition and mapping to every subject the app offers, not only the current science crosswalks.

## 15. Definition of done

A subject is **not complete** until:
- Every current official statement is captured and versioned.
- Every statement has a valid lesson/practical/assessment mapping appropriate to its demand.
- Teaching depth meets the zero-to-mastery standard.
- JAMB and WAEC differences are preserved.
- Required visual, interactive and practical learning is present or a reasoned N/A is recorded.
- Assessment evidence, feedback and remediation exist.
- Qualified independent subject review passes.
- Technical tests, routing, offline/accessibility checks and Android build pass.
- The exact release commit and evidence are recorded.

A subject may be reported as **in progress**, **source provisional**, **partially mapped**, **implemented pending review**, or **verified**. Never hide uncertainty behind a completion percentage.

**Governing principle:** syllabus fidelity first; teaching depth second; meaningful practice and mastery evidence third; interface polish and feature count after the academic foundation is sound.

## 16. NetAcad-inspired one-to-one tutorial delivery model

MeritPrep lessons must be delivered as guided tutorial experiences, not long pages of notes. Adapt the learning principles visible in Cisco Networking Academy: modular progression, plain-language explanations, embedded interactive checks, guided and open practice, simulation/lab work, immediate feedback, replayability and evidence of skill mastery. This is an instructional adaptation for school subjects; it is not a claim of affiliation with or endorsement by Cisco.

Cisco's public course descriptions document the use of modules, practice labs, interactive activities, quizzes, module exams and final exams. Cisco also describes topic-level checks, guided practice, open exploration, immediate feedback and reports identifying stronger and weaker skills. See the source register for the references used in this design.

### 16.1 The learner experience: one concept, one guided teaching sequence

A lesson should be divided into short, coherent **micro-lessons**. Each micro-lesson teaches one main idea or one tightly related group of ideas. The learner should not face a wall of text or be expected to understand several new concepts at once.

Each micro-lesson follows this conversational teaching cycle:

1. **Teacher sets the scene:** introduce a relatable question, observation, diagram, short story, specimen, experiment or real-life problem.
2. **Teacher explains one idea:** use clear beginner language, then introduce the correct academic vocabulary and formal explanation.
3. **Teacher demonstrates:** walk through a diagram, process, calculation, proof, worked example, source or experiment step by step.
4. **Teacher asks the learner:** present one purposeful question or prediction before revealing the answer.
5. **Learner responds:** support suitable response modes such as selecting, ordering, labelling, drawing, calculating, typing a short explanation, interpreting data or manipulating a simulation.
6. **Teacher diagnoses:** explain what is correct, identify the exact misconception or missing step, and distinguish a lucky guess from demonstrated understanding.
7. **Teacher adapts:** give a hint, simpler analogy, prerequisite explanation, another worked example or a more challenging extension according to the learner's response.
8. **Learner practises independently:** remove scaffolding gradually and require a fresh attempt.
9. **Teacher confirms the checkpoint:** show the skill demonstrated, what remains weak and the next appropriate step.

The app should feel responsive and personal, but must not pretend that a live human tutor is present. A deterministic offline teaching engine can deliver authored feedback and branching. Optional AI assistance may expand explanations, but it must be grounded in the approved lesson knowledge base, constrained to the syllabus, and clearly distinguish generated assistance from reviewed core content.

### 16.2 Micro-lesson screen sequence

A standard micro-lesson can use these anchored stages:

- **Start:** title, estimated effort, learning goal, prerequisite status and relevance.
- **Recall:** one or two short prerequisite retrieval prompts.
- **Discover:** a concrete observation, question, visual or demonstration.
- **Learn:** small sections of carefully authored explanation, revealed progressively.
- **See it:** labelled diagram, narrated animation, worked visualisation or model.
- **Try together:** scaffolded activity with hints and step-by-step support.
- **Your turn:** independent question or practical decision.
- **Check understanding:** short formative checkpoint with explanatory feedback.
- **Apply:** transfer to a new context or a board-style problem.
- **Reflect and continue:** concise recap, confidence check, mastery status and recommended next step.

Not every screen needs to be a separate navigation page. Keep the learner in one continuous lesson workspace with clear progress, persistent navigation and no unnecessary screen transitions. Provide pause/resume and remember the exact checkpoint.

### 16.3 Tutorial orchestration and branching rules

Create a lesson state machine, not a loose collection of buttons. Each activity has a stable ID, prerequisite IDs, learning outcome, response schema, answer key or rubric, feedback branches, retry policy and completion evidence.

Minimum response pathways:

- **Correct with sound reasoning:** confirm why it is correct, then increase complexity or move forward.
- **Correct but guessed/unsupported:** ask for a brief justification or give a near-transfer question before awarding mastery.
- **Partially correct:** identify the correct part and isolate the missing concept or step.
- **Incorrect:** explain the misconception respectfully, revisit the relevant teaching segment and provide a different example.
- **Repeated difficulty:** move to a prerequisite bridge, simpler representation or alternate explanation, then return to the original outcome.
- **Repeated success:** reduce scaffolding and introduce unfamiliar application.
- **Learner requests help:** offer a hint first, then a worked step, then the complete explanation; record which support was used.
- **Learner skips:** allow navigation where appropriate, but record the skipped evidence and do not mark the outcome mastered.

Do not make a learner repeat an identical question until they memorise its answer. Use equivalent fresh items that test the same skill. Separate completion, correctness, confidence and independent mastery.

### 16.4 Feedback standard

Feedback must be instructional, specific and immediate. Avoid feedback that says only "Correct", "Wrong" or "Try again".

For every meaningful response, explain:
- what the learner did correctly;
- the exact step, term or idea that needs correction;
- why the correct reasoning works;
- what clue or method to use next time;
- a short retry or transfer task.

For multiple-choice items, explain why the selected option is right or wrong and why the distractors are tempting but incorrect. For calculations, identify the first incorrect step, not merely the final wrong answer. For theory responses, compare the response with the marking rubric and identify missing scientific points, unsupported claims or incorrect terminology.

### 16.5 Practice ladder

Build practice in increasing independence and transfer:

1. Recognition and vocabulary.
2. Recall without prompts.
3. Explain in the learner's own words.
4. Label, classify, order or complete a process.
5. Guided worked example.
6. Partially scaffolded problem.
7. Independent routine application.
8. Unfamiliar-context transfer.
9. Mixed-topic cumulative practice.
10. Timed examination practice.

The exact ladder should be adapted to the subject and syllabus demand. A language essay, physics calculation, biology practical and government source analysis require different response forms.

### 16.6 Module structure and examinations

Organise a course as:

**Course → board-specific pathway → syllabus section → module → topic → micro-lessons → activities → module review → module assessment → cumulative review → mock examination.**

A module begins with outcomes and a diagnostic. It contains a sequence of micro-lessons, embedded checks and practice. It ends with a review that revisits the module's key outcomes, a mixed assessment and a remediation route for weak outcomes.

A final course examination must sample the mapped syllabus proportionately and include cumulative retrieval. It must not simply repeat questions shown during teaching.

### 16.7 Personalisation without sacrificing syllabus coverage

Personalise the route and level of support, not the official curriculum obligations.

The system may:
- slow down and teach prerequisites when a learner struggles;
- skip redundant explanations only when diagnostic evidence supports that choice;
- recommend additional practice for weak skills;
- provide enrichment and challenge for learners who demonstrate mastery;
- vary examples and representations;
- schedule spaced retrieval based on forgetting risk and performance.

The system must not permanently skip a syllabus requirement just because a learner passes one diagnostic question. Required outcomes still need valid evidence. Record exemptions, accelerated routes and evidence supporting them.

### 16.8 Teacher-quality content authoring template

Every micro-lesson authoring record must specify:

- board, subject, syllabus version and atomic statement IDs;
- module, topic, micro-lesson and prerequisite IDs;
- one principal learning outcome and measurable success criteria;
- assumed prior knowledge and vocabulary;
- opening hook or diagnostic prompt;
- teaching script/explanation with enough depth for the intended level;
- concept breakdown and formal academic treatment;
- worked example with full reasoning;
- diagram/animation/simulation/practical references and their teaching purpose;
- common misconceptions and targeted correction;
- guided activity and independent transfer activity;
- checkpoint items, answer/rubric and response-specific feedback;
- remediation branch and extension branch;
- estimated learning time, accessibility notes and offline assets;
- academic author, independent reviewer, review date and status.

### 16.9 One-to-one tutor voice and interaction quality

The tutor voice should be warm, patient, precise and intellectually serious. Address the learner directly when useful. Explain unfamiliar terms before using them. Never ridicule errors or imply that a learner is unintelligent. Avoid artificial praise after every tap; acknowledge real progress specifically.

Use natural teacher prompts such as:
- "Before I explain, what do you think will happen?"
- "Let's examine the diagram together."
- "You identified the correct structure. Now tell me what it does."
- "Your method is right up to this step. Let's find where the calculation changed."
- "Try a new example without looking at the worked solution."

Do not use the same script mechanically in every subject. The teaching interaction must match the discipline and learning outcome.

### 16.10 Mobile-first, low-bandwidth delivery

MeritPrep must work well on ordinary Android phones and unreliable or expensive data connections:
- cache core text, diagrams, question sets and essential lesson assets for offline use;
- offer compressed media, captions/transcripts and user-controlled downloads;
- avoid requiring continuous streaming to complete a lesson;
- save learner answers and checkpoint state safely;
- make animations optional when device performance is limited, with a still diagram and explanation as fallback;
- provide accessible text alternatives, scalable text, adequate touch targets and clear contrast;
- avoid excessive battery, memory and background network use.

### 16.11 One-to-one quality acceptance tests

A micro-lesson fails review if:
- it reads like a short note rather than teaching;
- it introduces unexplained terminology;
- it reveals answers without eliciting learner thinking;
- it has no meaningful worked demonstration where one is needed;
- it gives generic feedback unrelated to the learner's response;
- it allows a guessed answer to establish mastery;
- it repeats identical questions as the only remediation;
- its visual or simulation is decorative rather than instructionally useful;
- it cannot be resumed reliably;
- it is not mapped to official syllabus evidence.

A module fails if the learner can finish it by tapping through without demonstrating the stated outcomes. Completion is a navigation event; mastery is an evidence-based academic decision.

