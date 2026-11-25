# MeritPrep 2.0 — Science Media, Animation and Virtual-Lab Integration Standard

## Purpose

MeritPrep must teach science through more than text, static diagrams and multiple-choice questions. Scientific animation, interactive visualisation, simulation and virtual practical work are core instructional features across Biology, Chemistry and Physics.

The objective is to reproduce the *learning value* of high-quality open educational resources inside a coherent MeritPrep course. It does not mean copying, scraping, rehosting or reverse-engineering third-party websites or media.

## 1. Required learning experience

For each syllabus topic, the content team should assess whether one or more of these media types materially improves understanding:

1. **Short scientific animations:** show processes that are difficult to observe directly or understand from a still image, such as mitosis, meiosis, DNA replication, protein synthesis, nerve impulses, circulation, gas exchange, photosynthesis, respiration, fertilisation, immunity and ecological succession.
2. **Interactive diagrams and models:** allow learners to reveal labels, inspect structures, move through stages, compare states or zoom into meaningful details.
3. **Variable-driven simulations:** let learners change one or more scientifically relevant inputs and observe a modelled outcome, for example light intensity and photosynthesis, temperature and enzyme activity, concentration gradients and osmosis, or allele frequencies and selection.
4. **Virtual laboratories:** guide learners through apparatus selection, safe procedures, prediction, experimental controls, observations, data recording, analysis, graphing and evidence-based conclusions.
5. **Scientific image sequences and GIFs:** use short, purposeful loops for movement, cycles, transformations and microscopic processes. Every GIF must have a learning purpose, readable scale and labels where needed, and a text explanation or accessible alternative.
6. **Real-world scientific video and microscopy:** use selected clips or micrographs when authentic observation adds value; explain what is visible, what is inferred and the limitations of the recording.

A media item must support a stated learning objective. Avoid decorative animation, motion that distracts from the concept, or animations that imply scientifically false mechanisms.

## 2A. Current linked-resource catalogue

The current `ScienceMediaCatalog` contains **38 curated resource records** across Biology, Chemistry and Physics. It includes PhET simulations for membrane transport, natural selection, gene expression, atomic structure, molecular geometry, acid–base chemistry, circuits, waves, optics, gas behaviour, reaction rates, projectile motion, gravity/orbits, induction, blackbody radiation, capacitance, pendulums, elasticity, friction, buoyancy and the photoelectric effect, alongside HHMI BioInteractive and LabXchange resources.

These are external launch links with guided pre-/post-exploration prompts and offline alternatives. They are not packaged copies of the third-party simulations. Each resource record carries creator, licence, attribution and permitted-use metadata.

**PhET licensing control:** Standard HTML simulations are CC BY-NC 4.0. Free classroom access does not grant permission for commercial use inside a paid, subscription-based, ad-supported or otherwise commercially advantageous product. PhET-iO/Studio presets have separate licensing. The app must obtain an appropriate commercial licence before embedding or distributing PhET content commercially; otherwise retain external links and native original alternatives. See the current [PhET licensing terms](https://phet.colorado.edu/en/licensing/).

## 2. Topic-to-media planning

Each syllabus topic should have a media audit. For every topic, record:

- topic and learning objective;
- the misconception or difficult idea the media addresses;
- media type and learner interaction;
- source URL, creator, title and retrieval date;
- ownership, licence and exact permitted uses;
- whether attribution is required and its required wording;
- whether embedding, downloading, modification, redistribution and offline storage are allowed;
- accessibility alternatives, including text description, captions, contrast and reduced-motion consideration;
- data/internet requirements and offline fallback;
- academic reviewer, review date and approval status.

Prioritise media where spatial, temporal, microscopic, dynamic or experimental reasoning is central. A topic does not need an animation merely to satisfy a quota.

## 3. External resources to evaluate

These are discovery sources, not blanket permission to copy or embed every item:

- **LabXchange (Harvard):** free virtual labs and simulations including microscopy, stomata, plant dissection, gel electrophoresis, spectrophotometry, osmosis and photosynthesis/cellular respiration. Many resources can be explored without an account, but individual asset rights and platform terms must still be checked.  
  https://about.labxchange.org/featured/virtual-lab-simulations  
  https://www.labxchange.org/
- **HHMI BioInteractive:** biology animations, interactives, videos, data activities and teaching resources. Check the terms on each resource before downloading, adapting, embedding or redistributing. Free access does not itself grant redistribution rights.  
  https://www.biointeractive.org/
- **PhET Interactive Simulations:** research-based interactive simulations across science and mathematics, including Biology, Chemistry and Physics. Standard simulations are CC BY-NC 4.0; this is non-commercial. Any commercial, monetised, paid or otherwise commercially advantageous use requires separate permission/licensing. Attribution and logo requirements apply. Do not embed or package PhET simulations in a commercial MeritPrep distribution without a suitable licence.  
  https://phet.colorado.edu/  
  https://phet.colorado.edu/en/licensing/
- **Other open educational resources:** evaluate university teaching collections, public research institutions, open textbooks, museum/science-centre interactives and openly licensed microscopy/animation libraries. Verify rights at the individual asset level.

## 4. Integration hierarchy

Use the safest and most maintainable method permitted by each asset's terms:

1. **Native MeritPrep implementation:** build original Compose, Canvas, SVG/vector or HTML5 models for essential curriculum concepts where practical. Keep calculations and scientific rules in testable domain logic separate from the UI.
2. **Licensed embedded experience:** embed an official simulation only when the provider explicitly permits the exact use, platform, distribution model and commercial status. Preserve attribution, branding, credits and required notices. Test WebView compatibility, orientation, accessibility, device performance and offline behaviour.
3. **External launch:** when embedding or redistribution is not permitted or technically reliable, open the provider's official resource page in a browser and show a MeritPrep preparation guide, activity sheet and post-activity questions. Never present an external link as an offline feature.
4. **Approved downloadable media:** package only assets whose licence explicitly allows downloading and redistribution in the app. Keep original files, licence evidence, attribution and version metadata.
5. **Original fallback:** when no suitable reusable asset exists, build a simplified but scientifically accurate MeritPrep animation or simulation. Clearly label models as simplified and state assumptions.

Never scrape a website, extract a protected animation, remove a watermark/logo, hotlink unstable media without permission, or assume that a freely viewable GIF/video/simulation is free to redistribute.

## 5. Offline-first and Nigerian network conditions

- Every essential lesson must remain understandable without a live third-party connection.
- Provide a static key-frame sequence, labelled diagram, concise text explanation or locally stored approved asset as a fallback.
- Prefer lightweight vector animations and small, compressed assets; avoid autoplaying large video files.
- Load optional media on demand, show file size and connection requirements where useful, and provide a clear retry/open-in-browser path.
- Keep core lesson text, questions, notes and progress local.
- Do not make external media availability a condition for completing a lesson or assessment.

## 6. Virtual-lab instructional pattern

A proper virtual lab should use a consistent sequence:

1. **Briefing:** scientific context, objective, apparatus and safety.
2. **Predict:** learner records an expected outcome and explains why.
3. **Explore:** learner manipulates the model or follows the practical procedure.
4. **Observe:** the simulation records visible outcomes and measurements.
5. **Record:** learner enters observations in a table with units.
6. **Analyse:** calculate means/ranges where appropriate, plot a suitable graph and interpret patterns.
7. **Evaluate:** consider anomalous readings, limitations, random/systematic error and improvements.
8. **Reflect:** compare prediction with evidence and answer transfer questions.

Where the real practical involves hazards, scarce equipment or living organisms, the simulation must not imply that virtual practice replaces supervised laboratory training.

## 7. Technical requirements

- Store resource metadata in a versioned catalogue rather than hard-coding source details throughout lesson screens.
- Suggested metadata: `resourceId`, `topicId`, `title`, `mediaType`, `sourceName`, `sourceUrl`, `creator`, `licenceId`, `licenceUrl`, `attributionText`, `permissionScope`, `offlineAssetPath`, `fileSizeBytes`, `contentVersion`, `reviewer`, `reviewedAt`, `approvalStatus`.
- Separate the simulation engine from presentation so numerical behaviour can be unit-tested.
- Use deterministic model rules when the learning objective depends on exact calculations; clearly label stochastic models and provide reproducible settings when randomness is pedagogically useful.
- Validate units, physical/biological ranges, edge cases and numerical stability.
- Provide captions or transcripts for audio/video, meaningful descriptions for visuals, accessible controls and a reduced-motion alternative.
- Avoid collecting personal data from external resource providers unless necessary and transparently disclosed.

## 8. Initial Biology media priorities

The first audit should cover:

- Cell structure, prokaryotic/eukaryotic comparison and microscopy.
- Diffusion, osmosis and active transport.
- Enzymes and factors affecting enzyme activity.
- Photosynthesis and limiting factors.
- Aerobic respiration and anaerobic pathways.
- Mitosis, meiosis, DNA, genes, inheritance and variation.
- Human digestion, absorption, circulation, gas exchange, excretion and nervous/hormonal coordination.
- Plant transport, transpiration and stomatal control.
- Flower structure, pollination, fertilisation and seed formation.
- Ecology, food webs, energy transfer, succession, population sampling and natural selection.
- Microorganisms, pathogens, immunity and disease transmission.

For each, select the most effective combination of animation, interactive model, virtual lab and original assessment. Expand the same approach to Chemistry and Physics rather than treating this as a Biology-only feature.

## 9. Acceptance criteria

A topic's media integration is accepted only when:

- the media is mapped to a clear syllabus objective;
- scientific accuracy has been checked by a subject reviewer;
- provenance and exact reuse rights are documented;
- the learner has an active task, not passive viewing alone;
- a comprehension or evidence-interpretation check follows the activity;
- attribution is visible where required;
- the experience works on supported Android devices or has a clear external launch path;
- offline fallback is available for essential learning;
- automated tests cover any original calculation/model logic;
- the feature does not claim that a simulation replaces real practical competence.

This standard applies to every new science lesson and to the ongoing audit of existing content.
