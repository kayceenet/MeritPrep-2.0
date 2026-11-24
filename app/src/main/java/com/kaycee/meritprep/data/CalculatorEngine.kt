package com.kaycee.meritprep.data
import java.util.Locale
data class CalculationResult(val value: Double, val unit: String, val working: String)
object CalculatorEngine {
    private const val FARADAY_CONSTANT = 96485.33212
    private fun positive(value: Double, name: String): Double {
        require(value.isFinite()) { "$name must be a valid number." }
        require(value > 0.0) { "$name must be greater than zero." }
        return value
    }
    fun percentage(part: Double, whole: Double): CalculationResult {
        require(part.isFinite() && whole.isFinite()) { "Enter valid numbers." }
        require(whole != 0.0) { "The whole value cannot be zero." }
        return CalculationResult(part / whole * 100.0, "%", "($part ÷ $whole) × 100")
    }
    fun mean(values: List<Double>): CalculationResult {
        require(values.isNotEmpty()) { "Enter at least one value." }
        require(values.all { it.isFinite() }) { "All values must be valid numbers." }
        return CalculationResult(values.average(), "", "(" + values.joinToString(" + ") + ") ÷ " + values.size)
    }
    fun ohmsLaw(voltage: Double, resistance: Double): CalculationResult {
        require(voltage.isFinite()) { "Voltage must be a valid number." }
        val r = positive(resistance, "Resistance")
        return CalculationResult(voltage / r, "A", "I = V ÷ R = $voltage V ÷ $r Ω")
    }
    fun speed(distanceMetres: Double, timeSeconds: Double): CalculationResult {
        val d = positive(distanceMetres, "Distance"); val t = positive(timeSeconds, "Time")
        return CalculationResult(d / t, "m/s", "Speed = distance ÷ time = $d m ÷ $t s")
    }
    fun density(massGrams: Double, volumeCm3: Double): CalculationResult {
        val m = positive(massGrams, "Mass"); val v = positive(volumeCm3, "Volume")
        return CalculationResult(m / v, "g/cm³", "Density = mass ÷ volume = $m g ÷ $v cm³")
    }
    fun molarity(moles: Double, volumeLitres: Double): CalculationResult {
        require(moles.isFinite() && moles >= 0.0) { "Amount of substance cannot be negative." }
        val v = positive(volumeLitres, "Solution volume")
        return CalculationResult(moles / v, "mol/L", "C = n ÷ V = $moles mol ÷ $v L")
    }
    fun dilution(c1: Double, v1: Double, c2: Double): CalculationResult {
        val a = positive(c1, "Initial concentration"); val b = positive(v1, "Initial volume"); val c = positive(c2, "Final concentration")
        require(c <= a) { "Final concentration cannot exceed initial concentration for a dilution." }
        return CalculationResult(a * b / c, "same volume unit as V₁", "C₁V₁ = C₂V₂; V₂ = ($a × $b) ÷ $c")
    }
    fun molesFromMass(massGrams: Double, molarMass: Double): CalculationResult {
        val m = positive(massGrams, "Mass"); val mm = positive(molarMass, "Molar mass")
        return CalculationResult(m / mm, "mol", "n = m ÷ M = $m g ÷ $mm g mol⁻¹")
    }
    fun boylesLaw(p1: Double, v1: Double, v2: Double): CalculationResult {
        val p = positive(p1, "Initial pressure"); val a = positive(v1, "Initial volume"); val b = positive(v2, "Final volume")
        return CalculationResult(p * a / b, "same pressure unit as P₁", "P₁V₁ = P₂V₂; P₂ = ($p × $a) ÷ $b")
    }
    fun charlesLaw(v1: Double, t1Kelvin: Double, t2Kelvin: Double): CalculationResult {
        val v = positive(v1, "Initial volume"); val t1 = positive(t1Kelvin, "Initial temperature in kelvin"); val t2 = positive(t2Kelvin, "Final temperature in kelvin")
        return CalculationResult(v * t2 / t1, "same volume unit as V₁", "V₁/T₁ = V₂/T₂; V₂ = ($v × $t2) ÷ $t1")
    }
    fun heatEnergy(massKg: Double, specificHeat: Double, temperatureChangeC: Double): CalculationResult {
        val m = positive(massKg, "Mass"); val c = positive(specificHeat, "Specific heat capacity")
        require(temperatureChangeC.isFinite()) { "Temperature change must be a valid number." }
        val q = m * c * temperatureChangeC
        require(q.isFinite()) { "The calculated energy is outside the supported range." }
        return CalculationResult(q, "J", "Q = mcΔT = $m kg × $c J kg⁻¹ K⁻¹ × $temperatureChangeC K")
    }
    fun pH(hydrogenIonConcentration: Double): CalculationResult {
        val c = positive(hydrogenIonConcentration, "Hydrogen-ion concentration")
        val value = -kotlin.math.log10(c)
        require(value.isFinite()) { "The calculated pH is outside the supported range." }
        return CalculationResult(value, "pH", "pH = −log₁₀[H⁺] = −log₁₀($c mol L⁻¹)")
    }
    fun electrolysisMass(currentAmps: Double, timeSeconds: Double, molarMass: Double, electronsTransferred: Double): CalculationResult {
        val i = positive(currentAmps, "Current"); val t = positive(timeSeconds, "Time"); val m = positive(molarMass, "Molar mass"); val z = positive(electronsTransferred, "Electrons transferred per ion")
        require(z % 1.0 == 0.0) { "Electrons transferred must be a whole number." }
        val deposited = i * t * m / (z * FARADAY_CONSTANT)
        require(deposited.isFinite()) { "The calculated mass is outside the supported range." }
        return CalculationResult(deposited, "g", "m = ItM ÷ zF; F = 96485.33212 C mol⁻¹")
    }
    fun format(value: Double): String {
        require(value.isFinite()) { "The result is not finite." }
        return String.format(Locale.US, "%.6g", value)
    }
}
