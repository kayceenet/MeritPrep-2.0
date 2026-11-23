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

    @Test(expected = IllegalArgumentException::class)
    fun rejectsDailyTargetBelowThirtyMinutes() {
        StudyPlanGenerator.build(topics, minutesPerDay = 20)
    }
}
