package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyLearningCatalogTest {
    @Test
    fun everyBiologySyllabusUnitOpensOneMatchingBeginnerLesson() {
        val biologyTopics = LearningCatalog.topics.filter { it.subjectCode == "BIO" }
        val lessonIds = biologyTopics.map { it.id }
        val syllabusLessonIds = BiologyCurriculum.units.mapNotNull { it.lessonId }

        assertEquals("The course should provide one Biology lesson for each mapped syllabus unit.", BiologyCurriculum.units.size, biologyTopics.size)
        assertEquals("Biology lesson IDs must be unique.", lessonIds.size, lessonIds.toSet().size)
        assertEquals("Every syllabus unit must map to a unique lesson.", syllabusLessonIds.size, syllabusLessonIds.toSet().size)
        assertEquals("Every mapped syllabus lesson must exist in the learning catalogue.", syllabusLessonIds.toSet(), lessonIds.toSet())
    }

    @Test
    fun biologyLessonsContainTheMinimumElementsOfACompleteLearningExperience() {
        LearningCatalog.topics.filter { it.subjectCode == "BIO" }.forEach { topic ->
            assertTrue("${topic.id}: title is required", topic.title.isNotBlank())
            assertTrue("${topic.id}: beginner introduction is required", topic.introduction.isNotBlank())
            assertTrue("${topic.id}: learning objectives are required", topic.objectives.isNotEmpty())
            assertTrue("${topic.id}: objectives must not be blank", topic.objectives.all { it.isNotBlank() })
            assertTrue("${topic.id}: lesson sections are required", topic.sections.isNotEmpty())
            assertTrue("${topic.id}: section headings and explanations are required", topic.sections.all { it.first.isNotBlank() && it.second.isNotBlank() })
            assertTrue("${topic.id}: a retrieval-practice question is required", topic.checkQuestion.isNotBlank())
            assertTrue("${topic.id}: at least two answer options are required", topic.checkOptions.size >= 2)
            assertTrue("${topic.id}: answer options must not be blank", topic.checkOptions.all { it.isNotBlank() })
            assertTrue("${topic.id}: the correct answer index must be valid", topic.correctOption in topic.checkOptions.indices)
            assertTrue("${topic.id}: feedback must explain the answer", topic.explanation.isNotBlank())
        }
    }

    @Test
    fun syllabusUnitsHaveLearningOutcomesAndPracticalLinks() {
        BiologyCurriculum.units.forEach { unit ->
            assertTrue("${unit.id}: title is required", unit.title.isNotBlank())
            assertTrue("${unit.id}: scope is required", unit.scope.isNotBlank())
            assertTrue("${unit.id}: measurable learning outcomes are required", unit.outcomes.isNotBlank())
            assertTrue("${unit.id}: WAEC cross-references are required", unit.waecLinks.isNotEmpty())
            assertTrue("${unit.id}: a matching lesson ID is required", unit.lessonId?.let { id -> LearningCatalog.topics.any { it.id == id } } == true)
        }
        assertTrue("The practical strand list must be available.", BiologyCurriculum.waecPracticalStrands.isNotEmpty())
    }
}
