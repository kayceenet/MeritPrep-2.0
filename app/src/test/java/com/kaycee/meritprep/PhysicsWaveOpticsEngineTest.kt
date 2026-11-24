package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsWaveOpticsEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PhysicsWaveOpticsEngineTest {
    @Test fun calculatesWaveSpeedAndPeriod() {
        val result = PhysicsWaveOpticsEngine.waveProperties(5.0, 2.0)
        assertEquals(10.0, result.speedMetresPerSecond, 1e-9)
        assertEquals(0.2, result.periodSeconds, 1e-9)
    }

    @Test fun calculatesEchoDistanceFromRoundTripTime() {
        assertEquals(340.0, PhysicsWaveOpticsEngine.echoDistance(340.0, 2.0), 1e-9)
        assertEquals(0.0, PhysicsWaveOpticsEngine.echoDistance(340.0, 0.0), 1e-9)
    }

    @Test fun planeMirrorReflectionEqualsIncidenceAngle() {
        assertEquals(35.0, PhysicsWaveOpticsEngine.reflectedAngleDegrees(35.0), 1e-9)
        assertEquals(0.0, PhysicsWaveOpticsEngine.reflectedAngleDegrees(0.0), 1e-9)
        assertEquals(90.0, PhysicsWaveOpticsEngine.reflectedAngleDegrees(90.0), 1e-9)
    }

    @Test fun rejectsInvalidWaveEchoAndReflectionInputs() {
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.waveProperties(0.0, 2.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.waveProperties(5.0, -1.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.echoDistance(0.0, 2.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.echoDistance(340.0, -1.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.reflectedAngleDegrees(-1.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsWaveOpticsEngine.reflectedAngleDegrees(91.0) }
    }
}
