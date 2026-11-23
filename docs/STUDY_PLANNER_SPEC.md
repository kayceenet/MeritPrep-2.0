# Biology study planner specification

## Purpose
Provide a realistic short-horizon study schedule that turns the mapped Biology syllabus into manageable daily actions.

## Current behaviour
- Generates a seven-day plan from the 24 mapped Biology lesson routes.
- Supports daily time targets of 60, 90, 120 or 150 minutes.
- Each scheduled topic has a 20-minute lesson task, a 5-minute practice task and a 5-minute flashcard review task.
- At 120 minutes per day, all 24 topics are scheduled across six days, leaving one rest or catch-up day.
- At lower daily targets, the plan schedules fewer topics and clearly reports how many topics are included.
- Stores the plan and completion state locally in SQLite for offline use.
- A learner can generate a replacement plan; the previous plan is made inactive.

## Learning safeguards
- A checked activity means the learner marked the activity complete; it is not proof of mastery.
- The planner does not predict examination scores or guarantee readiness.
- The current plan is Biology-only because other subject curricula are still starter content.
- Time estimates are planning aids and can be adjusted after learner feedback.

## Next improvements
- Add start/end dates and calendar reminders.
- Generate plans from weak-topic analytics and learner availability.
- Allow learners to reschedule missed activities without losing history.
- Extend planning to the remaining JAMB and WAEC subjects.