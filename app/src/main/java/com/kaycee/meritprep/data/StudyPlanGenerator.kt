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
}
