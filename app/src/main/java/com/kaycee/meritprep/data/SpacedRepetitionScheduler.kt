package com.kaycee.meritprep.data

enum class ReviewRating { AGAIN, HARD, GOOD, EASY }

object SpacedRepetitionScheduler {
    private const val MIN_EASE = 1.3
    private const val MINUTE_MS = 60_000L
    private const val DAY_MS = 86_400_000L

    fun schedule(card: RevisionCard, rating: ReviewRating, reviewedAt: Long): RevisionCard {
        val quality = when (rating) {
            ReviewRating.AGAIN -> 0
            ReviewRating.HARD -> 3
            ReviewRating.GOOD -> 4
            ReviewRating.EASY -> 5
        }
        val delta = 5 - quality
        val ease = (card.easeFactor + 0.1 - delta * (0.08 + delta * 0.02)).coerceAtLeast(MIN_EASE)
        if (rating == ReviewRating.AGAIN) {
            return card.copy(
                dueAt = reviewedAt + 10 * MINUTE_MS,
                intervalDays = 0,
                repetitions = 0,
                easeFactor = ease,
                lapses = card.lapses + 1
            )
        }

        val interval = when (rating) {
            ReviewRating.HARD -> when (card.repetitions) {
                0 -> 1
                1 -> 2
                else -> kotlin.math.ceil(card.intervalDays.coerceAtLeast(1) * 1.2).toInt()
            }
            ReviewRating.GOOD -> when (card.repetitions) {
                0 -> 1
                1 -> 6
                else -> kotlin.math.round(card.intervalDays * ease).toInt().coerceAtLeast(1)
            }
            ReviewRating.EASY -> when (card.repetitions) {
                0 -> 4
                1 -> 7
                else -> kotlin.math.round(card.intervalDays * ease * 1.3).toInt().coerceAtLeast(1)
            }
            ReviewRating.AGAIN -> 0
        }
        return card.copy(
            dueAt = reviewedAt + interval * DAY_MS,
            intervalDays = interval,
            repetitions = card.repetitions + 1,
            easeFactor = ease,
            lapses = card.lapses
        )
    }
}
