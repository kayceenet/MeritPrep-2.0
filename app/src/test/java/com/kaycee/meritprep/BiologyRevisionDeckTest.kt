package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyRevisionDeckTest {
    @Test fun revisionDeckHasUniqueCardsMappedToEveryBiologyTopic() {
        val cards = starterRevisionCards.filter { it.topicId.startsWith("bio-") }
        assertEquals(198, cards.size)
        assertEquals(cards.size, cards.map { it.cardId }.toSet().size)
        val lessonIds = BiologyCurriculum.units.mapNotNull { it.lessonId }.toSet()
        assertEquals(lessonIds, cards.map { it.topicId }.toSet())
        assertTrue(cards.all { it.front.isNotBlank() && it.back.isNotBlank() })
    }
}
