package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MathematicsRevisionCardsTest {
    @Test fun includesFiftyFourMathematicsCardsAcrossEighteenDevelopedTopics() {
        val cards = starterRevisionCards.filter { it.cardId.startsWith("math-") }
        assertEquals(54, cards.size)
        assertEquals(18, cards.map { it.topicId }.distinct().size)
    }

    @Test fun mathematicsCardsHaveUniqueIdsAndCompleteRecallContent() {
        val cards = starterRevisionCards.filter { it.cardId.startsWith("math-") }
        assertEquals(cards.size, cards.map { it.cardId }.distinct().size)
        cards.forEach {
            assertTrue(it.front.isNotBlank())
            assertTrue(it.back.isNotBlank())
            assertEquals(0L, it.dueAt)
        }
    }

    @Test fun everyMathematicsCardMapsToADevelopedLesson() {
        val developed = MathematicsCourse.topics.filterNot { it.isSyllabusOutline }.map { it.id }.toSet()
        starterRevisionCards.filter { it.cardId.startsWith("math-") }.forEach {
            assertTrue(it.topicId in developed)
        }
    }
}
