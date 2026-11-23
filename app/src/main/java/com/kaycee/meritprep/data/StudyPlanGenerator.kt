package com.kaycee.meritprep.data

data class StudyTopic(val id: String, val title: String)

data class PlannedStudyActivity(
    val dayIndex: Int,
    val topicId: String,
    val topicTitle: String,
    val activityType: String,
    val estimatedMinutes: Int
)

object StudyPlanGenerator {
    private const val MINUTES_PER_TOPIC = 30

    fun build(topics: List<StudyTopic>, minutesPerDay: Int, days: Int = 7): List<PlannedStudyActivity> {
        require(minutesPerDay >= MINUTES_PER_TOPIC) { "Daily target must allow at least one topic." }
        require(days > 0) { "A plan must contain at least one day." }
        val topicsPerDay = (minutesPerDay / MINUTES_PER_TOPIC).coerceAtLeast(1)
        val scheduled = topics.take(topicsPerDay * days)
        return buildList {
            scheduled.forEachIndexed { index, topic ->
                val day = index / topicsPerDay
                add(PlannedStudyActivity(day, topic.id, topic.title, "Learn lesson", 20))
                add(PlannedStudyActivity(day, topic.id, topic.title, "Practise questions", 5))
                add(PlannedStudyActivity(day, topic.id, topic.title, "Review flashcards", 5))
            }
        }
    }

    /**
     * Prioritises topics using recorded evidence without treating low evidence as mastery.
     * Unattempted topics receive a neutral prior and a small exploration bonus.
     */
    fun prioritiseTopics(
        topics: List<StudyTopic>,
        progress: List<TopicProgress>,
        nowEpochMillis: Long = System.currentTimeMillis()
    ): List<StudyTopic> {
        val progressByTopic = progress.associateBy { it.topicId }
        return topics.withIndex().sortedWith(
            compareByDescending<IndexedValue<StudyTopic>> { indexed ->
                val evidence = progressByTopic[indexed.value.id]
                if (evidence == null) {
                    0.75
                } else {
                    val attempts = evidence.questionsAttempted.coerceAtLeast(0)
                    val correct = evidence.questionsCorrect.coerceIn(0, attempts)
                    val smoothedErrorRate = (attempts - correct + 2.0) / (attempts + 4.0)
                    val lessonGap = if (evidence.lessonsCompleted == 0) 0.15 else 0.0
                    val ageDays = if (evidence.lastStudiedEpochMillis <= 0L) {
                        30.0
                    } else {
                        ((nowEpochMillis - evidence.lastStudiedEpochMillis).coerceAtLeast(0L) / 86_400_000.0)
                    }
                    val recencyGap = (ageDays / 30.0).coerceIn(0.0, 1.0) * 0.10
                    val masteryAdjustment = if (evidence.demonstratedMastery) -0.10 else 0.0
                    smoothedErrorRate + lessonGap + recencyGap + masteryAdjustment
                }
            }.thenBy { indexed -> indexed.index }
        ).map { it.value }
    }

    fun buildAdaptive(
        topics: List<StudyTopic>,
        progress: List<TopicProgress>,
        minutesPerDay: Int,
        days: Int = 7,
        nowEpochMillis: Long = System.currentTimeMillis()
    ): List<PlannedStudyActivity> {
        val prioritised = prioritiseTopics(topics, progress, nowEpochMillis)
        return build(prioritised, minutesPerDay, days)
    }
}
