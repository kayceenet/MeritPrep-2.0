package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MicroLessonEngineTest {
    @Test
    fun cellMicroLessonHasEveryIdeaCheckedInAllThreeTeachingStages() {
        val stages = listOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)
        val stageChecks = stages.map { MicroLessonEngine.checks("bio-cell", it) }

        assertEquals(6, stageChecks[0].size)
        assertEquals(6, stageChecks[1].size)
        assertEquals(6, stageChecks[2].size)

        val expectedIdeas = stageChecks.first().map { it.ideaId }.toSet()
        stages.forEachIndexed { index, _ ->
            assertEquals(expectedIdeas, stageChecks[index].map { it.ideaId }.toSet())
            assertEquals(6, stageChecks[index].map { it.id }.toSet().size)
        }
    }

    @Test
    fun everyCheckIsSyllabusMappedAndHasValidFreshTransferQuestion() {
        listOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)
            .flatMap { MicroLessonEngine.checks("bio-cell", it) }
            .forEach { check ->
                assertTrue(check.syllabusRefs.isNotEmpty())
                assertTrue(check.skill.isNotBlank())
                assertTrue(check.prompt.isNotBlank())
                assertTrue(check.options.size >= 2)
                assertTrue(check.correctOption in check.options.indices)
                assertTrue(check.explanation.isNotBlank())
                assertTrue(check.hint.isNotBlank())
                assertTrue(check.transferPrompt.isNotBlank())
                assertTrue(check.transferOptions.size >= 2)
                assertTrue(check.transferCorrectOption in check.transferOptions.indices)
                assertTrue(check.transferExplanation.isNotBlank())
            }
    }

    @Test
    fun oneCorrectAnswerCannotCompleteAConceptCheck() {
        val firstOnly = MicroLessonCheckEvidence(firstPassed = true, transferPassed = false)
        val transferOnly = MicroLessonCheckEvidence(firstPassed = false, transferPassed = true)

        assertFalse(firstOnly.complete)
        assertFalse(transferOnly.complete)
        assertTrue(MicroLessonCheckEvidence(true, true).complete)
    }

    @Test
    fun eachStageUsesDifferentQuestionsForTheSameIdea() {
        val concept = MicroLessonEngine.checks("bio-cell", LessonStage.CONCEPT).first()
        val example = MicroLessonEngine.checks("bio-cell", LessonStage.EXAMPLES).first()
        val context = MicroLessonEngine.checks("bio-cell", LessonStage.APPLICATIONS).first()

        assertNotEquals(concept.prompt, example.prompt)
        assertNotEquals(example.prompt, context.prompt)
        assertNotEquals(concept.transferPrompt, context.transferPrompt)
    }


    @Test
    fun classificationMicroLessonChecksAllSixIdeasInEveryTeachingStage() {
        val stages = listOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)
        val stageChecks = stages.map { MicroLessonEngine.checks("bio-classification", it) }
        assertEquals(6, stageChecks[0].size)
        val expectedIdeas = stageChecks.first().map { it.ideaId }.toSet()
        stages.forEachIndexed { index, _ ->
            assertEquals(expectedIdeas, stageChecks[index].map { it.ideaId }.toSet())
            assertEquals(6, stageChecks[index].map { it.id }.toSet().size)
            stageChecks[index].forEach { check ->
                assertTrue(check.syllabusRefs.contains("JAMB-BIO-A2-DIVERSITY-001"))
                assertTrue(check.syllabusRefs.contains("WAEC-NG-BIO-A1-CLASSIFICATION-001"))
                assertTrue(check.options.size >= 2)
                assertTrue(check.transferOptions.size >= 2)
                assertTrue(check.explanation.isNotBlank())
                assertTrue(check.transferExplanation.isNotBlank())
            }
        }
    }

    @Test
    fun classificationChecksUseDifferentTasksAcrossTeachingStages() {
        val concept = MicroLessonEngine.checks("bio-classification", LessonStage.CONCEPT).first()
        val example = MicroLessonEngine.checks("bio-classification", LessonStage.EXAMPLES).first()
        val context = MicroLessonEngine.checks("bio-classification", LessonStage.APPLICATIONS).first()
        assertNotEquals(concept.prompt, example.prompt)
        assertNotEquals(example.prompt, context.prompt)
        assertNotEquals(concept.transferPrompt, context.transferPrompt)
    }

    @Test
    fun classificationStageEvidenceRequiresEveryIdeaAndBothResponses() {
        val checks = MicroLessonEngine.checks("bio-classification", LessonStage.CONCEPT)
        val partial = checks.mapIndexed { index, check ->
            check.id to MicroLessonCheckEvidence(firstPassed = true, transferPassed = index < checks.lastIndex)
        }.toMap()
        assertFalse(MicroLessonEngine.hasCompleteEvidence("bio-classification", LessonStage.CONCEPT, partial))
        val complete = checks.associate { check ->
            check.id to MicroLessonCheckEvidence(firstPassed = true, transferPassed = true)
        }
        assertTrue(MicroLessonEngine.hasCompleteEvidence("bio-classification", LessonStage.CONCEPT, complete))
    }


    @Test
    fun adaptationMicroLessonChecksAllSevenIdeasInEveryTeachingStage() {
        val stages = listOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)
        val stageChecks = stages.map { MicroLessonEngine.checks("bio-adaptation", it) }
        assertEquals(7, stageChecks[0].size)
        val expectedIdeas = stageChecks.first().map { it.ideaId }.toSet()
        stages.forEachIndexed { index, _ ->
            assertEquals(expectedIdeas, stageChecks[index].map { it.ideaId }.toSet())
            assertEquals(7, stageChecks[index].map { it.id }.toSet().size)
            stageChecks[index].forEach { check ->
                assertTrue(check.syllabusRefs.contains("JAMB-BIO-A3-ADAPTATION-001"))
                assertTrue(check.syllabusRefs.contains("WAEC-NG-BIO-A7-ADAPTATION-001"))
                assertTrue(check.prompt.isNotBlank())
                assertTrue(check.options.size >= 2)
                assertTrue(check.correctOption in check.options.indices)
                assertTrue(check.hint.isNotBlank())
                assertTrue(check.transferPrompt.isNotBlank())
                assertTrue(check.transferOptions.size >= 2)
                assertTrue(check.transferCorrectOption in check.transferOptions.indices)
                assertTrue(check.transferExplanation.isNotBlank())
            }
        }
    }

    @Test
    fun adaptationChecksUseDistinctTasksForConceptExampleAndContext() {
        val concept = MicroLessonEngine.checks("bio-adaptation", LessonStage.CONCEPT).first()
        val example = MicroLessonEngine.checks("bio-adaptation", LessonStage.EXAMPLES).first()
        val context = MicroLessonEngine.checks("bio-adaptation", LessonStage.APPLICATIONS).first()
        assertNotEquals(concept.prompt, example.prompt)
        assertNotEquals(example.prompt, context.prompt)
        assertNotEquals(concept.transferPrompt, context.transferPrompt)
    }

    @Test
    fun unrelatedTopicsDoNotReceiveUnmappedChecks() {
        assertTrue(MicroLessonEngine.checks("bio-adaptation", LessonStage.CONCEPT).isEmpty())
        assertTrue(MicroLessonEngine.checks("bio-cell", LessonStage.LAB).isEmpty())
    }
}
