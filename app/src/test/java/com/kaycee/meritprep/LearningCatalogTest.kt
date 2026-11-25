package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningCatalogTest {
    @Test
    fun topicIdsAreUnique() {
        val ids = LearningCatalog.topics.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyLessonHasUsableTeachingContentAndKnowledgeCheck() {
        LearningCatalog.topics.forEach { topic ->
            assertTrue(topic.title.isNotBlank())
            assertTrue(topic.introduction.isNotBlank())
            assertFalse(topic.objectives.isEmpty())
            assertFalse(topic.sections.isEmpty())
            assertTrue(topic.sections.all { it.first.isNotBlank() && it.second.isNotBlank() })
            assertTrue(topic.checkOptions.size >= 2)
            assertTrue(topic.correctOption in topic.checkOptions.indices)
            assertTrue(topic.explanation.isNotBlank())
        }
    }

    @Test
    fun biologyCurriculumCoversAllFiveJambSectionsWithUniqueUnits() {
        val ids = BiologyCurriculum.units.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        assertEquals(24, BiologyCurriculum.units.size)
        assertEquals(5, BiologyCurriculum.sections.size)
        assertTrue(BiologyCurriculum.units.all { it.title.isNotBlank() && it.scope.isNotBlank() && it.outcomes.isNotBlank() })
        assertTrue(BiologyCurriculum.units.any { it.lessonId == "bio-cell" })
        assertTrue(BiologyCurriculum.waecPracticalStrands.size >= 6)
    }

    @Test
    fun eachInitialSubjectHasAtLeastOneLesson() {
        listOf("BIO", "CHEM", "PHY", "MATH", "ENG", "ECO", "GOV").forEach { code ->
            assertTrue("Missing starter lesson for $code", LearningCatalog.forSubject(code).isNotEmpty())
        }
    }
    @Test
    fun everyBiologySyllabusUnitHasAnAuthoredLessonRoute() {
        val lessonIds = LearningCatalog.topics.filter { it.subjectCode == "BIO" }.map { it.id }.toSet()
        assertEquals(24, lessonIds.size)
        assertTrue(BiologyCurriculum.units.all { it.lessonId != null && it.lessonId in lessonIds })
    }

    @Test
    fun originalBiologyQuestionsCoverEverySyllabusUnit() {
        val questions = BiologyQuestionBank.all
        assertEquals(256, questions.size)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        val syllabusIds = BiologyCurriculum.units.mapNotNull { it.lessonId }.toSet()
        assertEquals(syllabusIds, questions.map { it.topicId }.toSet())
        assertTrue(questions.all { !it.isVerified && it.source.startsWith("MeritPrep original practice item") })
    }

    @Test
    fun biologyLessonsMeetMinimumInstructionalDepth() {
        val biologyLessons = LearningCatalog.topics.filter { it.subjectCode == "BIO" }
        assertEquals(24, biologyLessons.size)
        biologyLessons.forEach { lesson ->
            assertTrue("${lesson.id} needs at least four measurable objectives", lesson.objectives.size >= 4)
            assertTrue("${lesson.id} needs at least eight teaching sections", lesson.sections.size >= 8)
            assertTrue("${lesson.id} needs a four-option knowledge check", lesson.checkOptions.size >= 4)
        }
    }

    @Test
    fun growthLessonTeachesCellCycleMitosisAndMeiosis() {
        val lesson = LearningCatalog.topics.single { it.id == "bio-growth" }
        val sectionText = lesson.sections.joinToString(" ") { it.first + " " + it.second }.lowercase()
        assertTrue(lesson.objectives.any { it.contains("mitosis", ignoreCase = true) })
        assertTrue(lesson.objectives.any { it.contains("meiosis", ignoreCase = true) })
        assertTrue(sectionText.contains("g1"))
        assertTrue(sectionText.contains("s phase"))
        assertTrue(sectionText.contains("crossing over"))
        assertTrue(sectionText.contains("homologous chromosomes"))
        assertTrue(sectionText.contains("sister chromatids"))
        assertTrue(sectionText.contains("23"))
    }

    @Test
    fun biologyLessonKnowledgeCheckAnswersAreBalancedAcrossAllFourPositions() {
        val lessons = LearningCatalog.topics.filter { it.subjectCode == "BIO" }
        val counts = lessons.groupingBy { it.correctOption }.eachCount()
        assertEquals(setOf(0, 1, 2, 3), counts.keys)
        (0..3).forEach { optionIndex ->
            assertEquals("Biology lesson answers should be evenly distributed", 6, counts[optionIndex])
        }
    }

}