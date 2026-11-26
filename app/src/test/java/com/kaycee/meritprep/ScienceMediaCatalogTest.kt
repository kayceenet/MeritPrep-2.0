package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ScienceMediaCatalogTest {
    @Test
    fun resourceIdsAreUniqueAndEveryEntryHasRequiredRightsAndLearningMetadata() {
        assertTrue(ScienceMediaCatalog.validate().joinToString("\n"), ScienceMediaCatalog.validate().isEmpty())
        assertEquals(
            ScienceMediaCatalog.resources.size,
            ScienceMediaCatalog.resources.map { it.id }.distinct().size
        )
    }

    @Test
    fun catalogueContainsAllThreeCoreScienceSubjects() {
        assertTrue(ScienceMediaCatalog.forSubject(ScienceMediaCatalog.BIOLOGY).isNotEmpty())
        assertTrue(ScienceMediaCatalog.forSubject(ScienceMediaCatalog.CHEMISTRY).isNotEmpty())
        assertTrue(ScienceMediaCatalog.forSubject(ScienceMediaCatalog.PHYSICS).isNotEmpty())
    }

    @Test
    fun everyExternalResourceHasPredictionReflectionAndOfflineAlternative() {
        ScienceMediaCatalog.resources.forEach { resource ->
            assertTrue(resource.id, resource.beforeYouStart.isNotBlank())
            assertTrue(resource.id, resource.afterYouExplore.isNotBlank())
            assertTrue(resource.id, resource.offlineAlternative.isNotBlank())
            assertTrue(resource.id, resource.sourceUrl.startsWith("https://"))
            assertTrue(resource.id, resource.creatorOrPublisher.isNotBlank())
            assertTrue(resource.id, resource.attributionText.isNotBlank())
        }
    }

    @Test
    fun lookupAndSubjectFilteringReturnExpectedResources() {
        assertEquals("Membrane transport", ScienceMediaCatalog.find("bio-membrane-transport-phet")?.title)
        assertTrue(ScienceMediaCatalog.forSubject("BIO").all { it.subjectCode == "BIO" })
        assertTrue(ScienceMediaCatalog.forSubject("CHEM").all { it.subjectCode == "CHEM" })
        assertTrue(ScienceMediaCatalog.forSubject("PHY").all { it.subjectCode == "PHY" })
        assertTrue(ScienceMediaCatalog.forSubject("UNKNOWN").isEmpty())
    }
    @Test
    fun everyMediaEntryResolvesToItsMappedSubjectLesson() {
        ScienceMediaCatalog.resources.forEach { resource ->
            val topic = LearningCatalog.find(resource.topicId)
            assertNotNull("Missing mapped topic for ${resource.id}: ${resource.topicId}", topic)
            assertEquals(resource.subjectCode, topic?.subjectCode)
        }
    }

    @Test
    fun fullChemistryAndPhysicsCourseMapsAreAvailableThroughLearningCatalog() {
        assertEquals(18, LearningCatalog.forSubject("CHEM").size)
        assertEquals(39, LearningCatalog.forSubject("PHY").size)
    }

    @Test
    fun everyBiologyChemistryAndPhysicsSyllabusTopicHasAtLeastOneExternalScienceResource() {
        val syllabusTopics = (
            BiologyCurriculum.units.mapNotNull { it.lessonId } +
                ChemistryCourse.topics.map { it.id } +
                PhysicsCourse.topics.map { it.id }
            ).toSet()
        val resourceTopics = ScienceMediaCatalog.resources.map { it.topicId }.toSet()
        val missing = syllabusTopics - resourceTopics
        assertTrue("Science syllabus topics without a curated external resource: $missing", missing.isEmpty())
    }

    @Test
    fun atomicStructureAndPopulationEcologyResourcesAreMappedToTheirExactSyllabusTopics() {
        assertEquals("chem-atomic", ScienceMediaCatalog.find("chem-atom-phet")?.topicId)
        val populationResources = ScienceMediaCatalog.forTopic("bio-populations")
        assertTrue(populationResources.any { it.id == "bio-population-dynamics-hhmi" })
        assertTrue(populationResources.any { it.id == "bio-population-survey-methods-hhmi" })
        assertTrue(populationResources.any { it.id == "bio-population-explosion-concord" })
    }

    @Test
    fun topicLookupReturnsOnlyResourcesMappedToThatTopic() {
        val osmosis = ScienceMediaCatalog.forTopic("bio-transport")
        assertTrue(osmosis.isNotEmpty())
        assertTrue(osmosis.all { it.topicId == "bio-transport" })
        assertTrue(ScienceMediaCatalog.forTopic("topic-with-no-media").isEmpty())
    }

    @Test
    fun phetResourcesCarryNonCommercialAndCommercialUseRestrictions() {
        ScienceMediaCatalog.resources.filter { it.provider == "PhET Interactive Simulations" }.forEach { resource ->
            assertTrue(resource.id, resource.licenceName.contains("CC BY-NC 4.0"))
            assertTrue(resource.id, resource.permittedUse.contains("commercial licence"))
            assertTrue(resource.id, resource.attributionText.contains("University of Colorado Boulder"))
        }
    }

    @Test
    fun expandedCatalogueContainsMoreThanThirtyCuratedResources() {
        assertTrue("Catalogue should contain a broad range of resources", ScienceMediaCatalog.resources.size >= 30)
    }


    @Test
    fun licensedCommonsGifsAreIncludedAsExternalAnimations() {
        val commonsGifs = ScienceMediaCatalog.resources.filter { it.provider.startsWith("Wikimedia Commons — ") }
        assertEquals(9, commonsGifs.size)
        assertTrue(commonsGifs.all { it.mediaType == ScienceMediaType.ANIMATION })
        assertTrue(commonsGifs.all { it.sourceUrl.startsWith("https://commons.wikimedia.org/wiki/File:") })
        assertTrue(commonsGifs.all { it.licenceName.contains("Creative Commons") || it.licenceName.contains("CC0") || it.licenceName.contains("Public domain") })
    }
}
