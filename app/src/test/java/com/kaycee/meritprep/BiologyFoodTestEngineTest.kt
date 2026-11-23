package com.kaycee.meritprep

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyFoodTestEngineTest {
    @Test fun iodineDetectsStarchButNotGlucose() {
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.STARCH, FoodTestType.IODINE).positive)
        assertFalse(BiologyFoodTestEngine.evaluate(FoodTestSample.GLUCOSE, FoodTestType.IODINE).positive)
    }

    @Test fun benedictDetectsReducingSugar() {
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.GLUCOSE, FoodTestType.BENEDICT).positive)
        assertFalse(BiologyFoodTestEngine.evaluate(FoodTestSample.STARCH, FoodTestType.BENEDICT).positive)
    }

    @Test fun biuretDetectsProteinAndEmulsionDetectsLipids() {
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.ALBUMEN, FoodTestType.BIURET).positive)
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.OIL, FoodTestType.EMULSION).positive)
    }

    @Test fun milkIsModelledAsAmixture() {
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.MILK, FoodTestType.BENEDICT).positive)
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.MILK, FoodTestType.BIURET).positive)
        assertTrue(BiologyFoodTestEngine.evaluate(FoodTestSample.MILK, FoodTestType.EMULSION).positive)
        assertFalse(BiologyFoodTestEngine.evaluate(FoodTestSample.MILK, FoodTestType.IODINE).positive)
    }

    @Test fun benedictProcedureIncludesSafeHeatingInstruction() {
        val result = BiologyFoodTestEngine.evaluate(FoodTestSample.GLUCOSE, FoodTestType.BENEDICT)
        assertTrue(result.procedure.contains("hot-water bath"))
        assertTrue(result.procedure.contains("not heat the test tube directly"))
    }
}
