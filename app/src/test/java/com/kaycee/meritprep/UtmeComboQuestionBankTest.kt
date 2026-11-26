package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UtmeComboQuestionBankTest {
    @Test
    fun containsTheCorrectFourSubjectDistribution() {
        assertEquals(60, UtmeComboQuestionBank.english.size)
        assertEquals(40, UtmeComboQuestionBank.biology.size)
        assertEquals(40, UtmeComboQuestionBank.chemistry.size)
        assertEquals(40, UtmeComboQuestionBank.physics.size)
        assertEquals(180, UtmeComboQuestionBank.all.size)
    }

    @Test
    fun containsUniqueQuestionIdsAndOriginalPracticeItems() {
        assertEquals(180, UtmeComboQuestionBank.all.map { it.id }.distinct().size)
        assertTrue(UtmeComboQuestionBank.all.all { !it.isVerified })
        assertTrue(UtmeComboQuestionBank.all.all { it.options.size == 4 })
    }

    @Test
    fun scienceCombinationUsesEverySubjectInTheExpectedOrder() {
        assertTrue(UtmeComboQuestionBank.all.take(60).all { it.topicId.startsWith("eng") })
        assertTrue(UtmeComboQuestionBank.all.drop(60).take(40).all { it.topicId.startsWith("bio") })
        assertTrue(UtmeComboQuestionBank.all.drop(100).take(40).all { it.topicId.startsWith("chem") })
        assertTrue(UtmeComboQuestionBank.all.drop(140).take(40).all { it.topicId.startsWith("phy") })
    }
}
