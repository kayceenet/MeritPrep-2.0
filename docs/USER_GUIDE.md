# MeritPrep 2.0 — Alpha User Guide

## 1. What this build contains

This is an offline-first Android alpha. Biology is the first subject with a broad learning and assessment path. Other subjects currently contain starter material and selected simulations, not full courses.

The app does not require an account or internet connection for its current local learning content. Learning records, saved theory answers, revision schedules, mistake notes and CBT session snapshots are stored on the device.

## 2. Home and subjects

- **Explore subjects** opens the subject catalogue.
- Choose **Biology** to open the 24-unit syllabus map.
- The other subject cards are entry points to starter lessons; do not assume they represent complete syllabus coverage.
- The home workspace also links to revision, mistakes, study planning, the Biology mock and glossary.

## 3. Learn Biology

1. Open **Learn**, then choose **Biology**.
2. Select a syllabus unit.
3. Read the lesson sections in order. The drafts are written for beginners and include definitions, examples and exam connections.
4. Use the interactive visuals in the cell, nutrition, interaction, heredity and transport lessons.
5. Answer the understanding check. The lesson can only be marked complete after the correct answer is selected.
6. Use **My lesson notes** to save your own explanations, questions or examples for that topic. Notes are stored locally on the device. Use **Save this lesson** to add the topic to your saved-lessons list, which is available from Home.
7. Lesson completion records that the lesson was completed; it does not automatically mean the topic is mastered.

The 24 Biology lesson drafts are awaiting independent review by qualified subject teachers.

## 4. Biology glossary

Open **Search Biology glossary** from Home or the Biology course. Search by term, definition or example, or filter by syllabus topic. Each entry links to its related lesson.

## 5. Practice and examination preparation

From **Practice**:

- **Topic practice** lets you choose one Biology topic or the full original Biology objective bank. Questions and options are shuffled.
- **JAMB-style timed practice** starts a 40-question, 30-minute Biology mock. **WAEC Biology objective practice** starts a separate 50-question session with a 60-minute MeritPrep practice timer. You can move between questions, change answers, flag items, submit and review explanations. Active session state, including the current question position, is stored locally so the app can restore an unfinished session.
- **WAEC theory practice** provides ten original structured-response tasks. Write and save your answer, reveal the model response and tick the marking points you believe you covered.
- **Mistake notebook** stores incorrect and unanswered objective responses with the correction and explanation. Mark an item reviewed after you have understood it.

The objective and theory items are original MeritPrep practice content. They are not official JAMB or WAEC past questions. Theory self-check points are not official WAEC marking schemes.

## 6. Practical learning

Open **Labs** to explore the current prototypes:

- Biology: food tests, osmosis, microscope magnification, quadrat sampling, a dichotomous classification key and a biological-drawing self-check.
- Chemistry: solution concentration.
- Physics: Ohm's law.

The simulations are simplified teaching models. Read the safety and model-limit note shown in each lab. The app does not prepare real reagents or specimens. For real practical work, follow teacher supervision, wear appropriate protection, avoid tasting or directly handling unknown specimens, and observe the stated chemical, glassware and fieldwork precautions. The simulations do not replace supervised laboratory work or a complete WAEC practical paper.

## 7. Revision cards

Open **Start Biology revision** from Home. Rate each card:

- **Again** — you could not recall it; schedule it soon.
- **Hard** — you recalled it with difficulty.
- **Good** — you recalled it correctly.
- **Easy** — you recalled it quickly and confidently.

The app schedules the next review using its local spaced-retrieval algorithm. A card rating is self-reported recall, not an independent assessment.

## 8. Study plan

Open **Build a Biology study plan**. Choose a daily time target and generate a seven-day plan. The planner uses recorded topic accuracy, lesson completion, recency and the current mastery indicator to prioritise topics. Each activity links to a lesson, practice or revision screen.

The current objective-practice mastery indicator requires at least 10 attempts, 8 distinct questions, attempts across at least 2 local study days and at least 80% correct. It is a transparent progress rule, not proof of mastery across theory, practical or unfamiliar question formats.

## 9. Learner profile

The Profile screen shows lesson completion, question attempts, overall accuracy and topic-level practice evidence. These metrics describe activity recorded on this device. They are not a predicted JAMB or WAEC score.

## 10. Offline data and limitations

- Current lessons, questions, glossary entries and simulations are bundled with the app and work offline.
- Learner data is stored locally. There is no cross-device cloud synchronisation in this alpha.
- Uninstalling the app or clearing its storage may erase local progress and saved responses. Keep a separate copy of important written answers.
- Biology content, question keys, glossary entries, visual aids, revision cards and practical tasks still require independent academic review.
- The current CBT is a Biology practice mock, not a full multi-subject official JAMB simulation.
- A teacher CMS, full WAEC theory/practical assessment workflow, additional subject courses and production security review remain in development.

## 11. Installing a GitHub Actions build

1. Open the repository's **Actions** tab.
2. Select the latest run with a successful conclusion.
3. In **Artifacts**, download the debug APK artifact.
4. Extract the archive and install `app-debug.apk` on the Android device.
5. If Android blocks installation, allow installation for the browser or file manager used to open the APK, then retry.

Use only a build whose unit tests and APK build both completed successfully.
