package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StudyPlanGeneratorTest {
    private val topics = (1..24).map { StudyTopic("topic-$it", "Topic $it") }

    @Test fun twoHourDailyTargetSchedulesAllTwentyFourTopicsAcrossSevenDays() {
        val plan = StudyPlanGenerator.build(topics, minutesPerDay = 120)
        assertEquals(72, plan.size)
        assertEquals(24, plan.map { it.topicId }.toSet().size)
        assertEquals((0..5).toSet(), plan.map { it.dayIndex }.toSet())
        assertTrue(plan.all { it.estimatedMinutes > 0 })
    }

    @Test fun sixtyMinuteTargetSchedulesTwoTopicsPerDay() {
        val plan = StudyPlanGenerator.build(topics, minutesPerDay = 60)
        assertEquals(14, plan.map { it.topicId }.toSet().size)
        assertEquals(42, plan.size)
    }

    @Test fun weakerTopicIsPrioritisedAheadOfDemonstratedStrongTopic() {
        val now = 1_800_000_000_000L
        val evidence = listOf(
            TopicProgress("topic-1", 1, 20, 19, now, true),
            TopicProgress("topic-2", 1, 10, 3, now, false)
        )
        val ordered = StudyPlanGenerator.prioritiseTopics(topics.take(2), evidence, now)
        assertEquals("topic-2", ordered.first().id)
    }

    @Test fun unattemptedTopicIsPrioritisedAsUnprovenRatherThanMastered() {
        val now = 1_800_000_000_000L
        val evidence = listOf(TopicProgress("topic-1", 1, 12, 12, now, true))
        val ordered = StudyPlanGenerator.prioritiseTopics(topics.take(2), evidence, now)
        assertEquals("topic-2", ordered.first().id)
    }

    @Test fun olderStudyEvidenceRaisesPriorityAndTiesRemainStable() {
        val now = 1_800_000_000_000L
        val recent = TopicProgress("topic-1", 1, 8, 4, now, false)
        val old = TopicProgress("topic-2", 1, 8, 4, now - 45L * 86_400_000L, false)
        val ordered = StudyPlanGenerator.prioritiseTopics(topics.take(2), listOf(recent, old), now)
        assertEquals("topic-2", ordered.first().id)
        val tied = StudyPlanGenerator.prioritiseTopics(topics.take(2), emptyList(), now)
        assertEquals(listOf("topic-1", "topic-2"), tied.map { it.id })
    }

    @Test fun adaptiveBuildPreservesTheThreeActivitiesPerScheduledTopic() {
        val plan = StudyPlanGenerator.buildAdaptive(topics.take(3), emptyList(), 60, days = 2)
        assertEquals(9, plan.size)
        assertEquals(3, plan.map { it.topicId }.toSet().size)\n        assertEquals(setOf("Learn lesson", "Practise questions", "Review flashcards"), plan.map { it.activityType }.toSet())
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsDailyTargetBelowThirtyMinutes() {
        StudyPlanGenerator.build(topics, minutesPerDay = 20)
    }
    @Test fun adaptivePriorityPlacesWeakUnmasteredTopicsBeforeStrongMasteredTopics() {
        val now = 1_800_000_000_000L
        val strong = TopicProgress("strong", 1, 20, 20, now, true)
        val weak = TopicProgress("weak", 0, 20, 2, now - 60L * 86_400_000L, false)
        val ordered = StudyPlanGenerator.prioritiseTopics(
            listOf(StudyTopic("strong", "Strong"), StudyTopic("weak", "Weak")),
            listOf(strong, weak),
            now
        )
        assertEquals("weak", ordered.first().id)
    }
}

