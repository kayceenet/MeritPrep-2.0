package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SpacedRepetitionSchedulerTest {
    private val now = 1_800_000_000_000L
    private val fresh = RevisionCard("c1", "bio-cell", "Front", "Back", now, easeFactor = 2.5)

    @Test fun againSchedulesShortRetryAndResetsLearningStreak() {
        val result = SpacedRepetitionScheduler.schedule(fresh.copy(repetitions = 3, intervalDays = 12), ReviewRating.AGAIN, now)
        assertEquals(now + 600_000L, result.dueAt)
        assertEquals(0, result.repetitions)
        assertEquals(0, result.intervalDays)
        assertEquals(1, result.lapses)
    }

    @Test fun goodUsesOneDayThenSixDays() {
        val first = SpacedRepetitionScheduler.schedule(fresh, ReviewRating.GOOD, now)
        val second = SpacedRepetitionScheduler.schedule(first, ReviewRating.GOOD, now)
        assertEquals(1, first.intervalDays)
        assertEquals(6, second.intervalDays)
        assertEquals(2, second.repetitions)
    }

    @Test fun easyUsesLongerIntervalThanGoodForFreshCard() {
        val good = SpacedRepetitionScheduler.schedule(fresh, ReviewRating.GOOD, now)
        val easy = SpacedRepetitionScheduler.schedule(fresh, ReviewRating.EASY, now)
        assertTrue(easy.intervalDays > good.intervalDays)
    }

    @Test fun easeNeverFallsBelowMinimum() {
        var card = fresh
        repeat(20) { card = SpacedRepetitionScheduler.schedule(card, ReviewRating.HARD, now) }
        assertTrue(card.easeFactor >= 1.3)
    }
}
