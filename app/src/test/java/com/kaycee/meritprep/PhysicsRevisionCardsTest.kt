package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsRevisionCardsTest {
    @Test fun includesNinetySevenPhysicsCardsAcrossThirtyTwoDevelopedTopics() {
        val cards = starterRevisionCards.filter { it.cardId.startsWith("phy-") }
        assertEquals(97, cards.size)
        assertEquals(32, cards.map { it.topicId }.distinct().size)
    }

    @Test fun physicsCardsHaveUniqueIdsAndCompleteRecallContent() {
        val cards = starterRevisionCards.filter { it.cardId.startsWith("phy-") }
        assertEquals(cards.size, cards.map { it.cardId }.distinct().size)
        cards.forEach {
            assertTrue(it.front.isNotBlank())
            assertTrue(it.back.isNotBlank())
            assertTrue(it.dueAt == 0L)
        }
    }

    @Test fun everyPhysicsCardMapsToADevelopedLesson() {
        val developed = PhysicsCourse.topics.filterNot { it.isSyllabusOutline }.map { it.id }.toSet()
        starterRevisionCards.filter { it.cardId.startsWith("phy-") }.forEach {
            assertTrue(it.topicId in developed)
        }
    }
}
