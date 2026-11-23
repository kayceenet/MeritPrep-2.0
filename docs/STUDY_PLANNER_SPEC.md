# Biology study planner specification

## Purpose
Provide a realistic short-horizon study schedule that turns the mapped Biology syllabus into manageable daily actions.

## Current behaviour
- Generates a seven-day plan from the 24 mapped Biology lesson routes.
- Supports daily time targets of 60, 90, 120 or 150 minutes.
- Topics are prioritised using recorded accuracy, incomplete lesson evidence, time since last study and the separate mastery flag. Topics with little evidence receive an exploration priority.
- Each scheduled topic has a 20-minute lesson task, a 5-minute practice task and a 5-minute flashcard review task.
- At 120 minutes per day, all 24 topics are scheduled across six days, leaving one rest or catch-up day.
- At lower daily targets, the plan schedules fewer topics and clearly reports how many topics are included; weaker or less-studied topics are scheduled first.
- Stores the plan and completion state locally in SQLite for offline use.
- A learner can generate a replacement plan; the previous plan is made inactive.
- New plans rank topics using local evidence: smoothed question error rate, incomplete lessons, time since last study and the existing mastery flag. The ranking is deterministic when scores tie.

## Learning safeguards
- A checked activity means the learner marked the activity complete; it is not proof of mastery.
- Topics with no question attempts are treated as unproven and receive an exploration priority; they are not assigned a fabricated score or marked mastered.
- The scheduler is a transparent prioritisation aid, not a validated prediction of examination performance.
- The planner does not predict examination scores or guarantee readiness.
- The current plan is Biology-only because other subject curricula are still starter content.
- Time estimates are planning aids and can be adjusted after learner feedback.
- Priority scores are scheduling heuristics, not validated predictions of examination performance.

## Next improvements
- Add start/end dates and calendar reminders.
- Allow learners to reschedule missed activities without losing history.
- Extend planning to the remaining JAMB and WAEC subjects.
