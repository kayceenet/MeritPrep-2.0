package com.kaycee.meritprep.data
import java.util.Locale
data class CalculationResult(val value: Double, val unit: String, val working: String)
object CalculatorEngine {
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
        val working = "(" + values.joinToString(" + ") + ") ÷ " + values.size
        return CalculationResult(values.average(), "", working)
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
        return CalculationResult(moles / v, "mol/L", "Concentration = amount ÷ volume = $moles mol ÷ $v L")
    }
    fun dilution(c1: Double, v1: Double, c2: Double): CalculationResult {
        val a = positive(c1, "Initial concentration"); val b = positive(v1, "Initial volume"); val c = positive(c2, "Final concentration")
        require(c <= a) { "Final concentration cannot exceed initial concentration for a dilution." }
        return CalculationResult(a * b / c, "same volume unit as V₁", "C₁V₁ = C₂V₂; V₂ = (C₁ × V₁) ÷ C₂")
    }
    fun format(value: Double): String {
        require(value.isFinite()) { "The result is not finite." }
        return String.format(Locale.US, "%.6g", value)
    }
}
