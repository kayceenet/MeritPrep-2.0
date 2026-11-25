package com.kaycee.meritprep

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * A compact offline scientific notation formatter.
 *
 * Supports the LaTeX subset commonly used in school science: Greek letters,
 * arrows, operators, powers, subscripts, roots, fractions and common units.
 * It is deliberately not presented as a complete TeX typesetter.
 */
object ScientificNotation {
    private val commands = linkedMapOf(
        "\\times" to "×", "\\cdot" to "·", "\\div" to "÷",
        "\\pm" to "±", "\\neq" to "≠", "\\approx" to "≈",
        "\\leq" to "≤", "\\geq" to "≥", "\\propto" to "∝",
        "\\rightarrow" to "→", "\\longrightarrow" to "⟶",
        "\\leftarrow" to "←", "\\leftrightarrow" to "↔",
        "\\Rightarrow" to "⇒", "\\rightleftharpoons" to "⇌",
        "\\Delta" to "Δ", "\\delta" to "δ", "\\theta" to "θ",
        "\\lambda" to "λ", "\\mu" to "μ", "\\rho" to "ρ",
        "\\pi" to "π", "\\alpha" to "α", "\\beta" to "β",
        "\\gamma" to "γ", "\\omega" to "ω", "\\Omega" to "Ω",
        "\\sigma" to "σ", "\\Sigma" to "Σ", "\\phi" to "φ",
        "\\epsilon" to "ε", "\\varepsilon" to "ϵ", "\\tau" to "τ", "\\nu" to "ν", "\\kappa" to "κ", "\\chi" to "χ", "\\zeta" to "ζ", "\\Gamma" to "Γ", "\\Theta" to "Θ", "\\Lambda" to "Λ", "\\Phi" to "Φ", "\\Pi" to "Π", "\\Xi" to "Ξ", "\\Upsilon" to "Υ", "\\nabla" to "∇", "\\partial" to "∂", "\\cdots" to "⋯", "\\ldots" to "…", "\\equiv" to "≡", "\\in" to "∈", "\\notin" to "∉", "\\subset" to "⊂", "\\cup" to "∪", "\\cap" to "∩", "\\infty" to "∞", "\\degree" to "°",
        "\\perp" to "⊥", "\\parallel" to "∥", "\\sum" to "Σ",
        "\\int" to "∫", "\\therefore" to "∴", "\\because" to "∵", "\\qquad" to "     ", "\\quad" to "   ", "\\log" to "log", "\\sin" to "sin", "\\cos" to "cos", "\\eta" to "η", "\\Psi" to "Ψ", "\\psi" to "ψ"
    )
    private val superscript = mapOf('0' to '⁰','1' to '¹','2' to '²','3' to '³','4' to '⁴','5' to '⁵','6' to '⁶','7' to '⁷','8' to '⁸','9' to '⁹','+' to '⁺','-' to '⁻','=' to '⁼','(' to '⁽',')' to '⁾','a' to 'ᵃ','b' to 'ᵇ','c' to 'ᶜ','d' to 'ᵈ','e' to 'ᵉ','f' to 'ᶠ','g' to 'ᵍ','h' to 'ʰ','i' to 'ⁱ','j' to 'ʲ','k' to 'ᵏ','l' to 'ˡ','m' to 'ᵐ','n' to 'ⁿ','o' to 'ᵒ','p' to 'ᵖ','r' to 'ʳ','s' to 'ˢ','t' to 'ᵗ','u' to 'ᵘ','v' to 'ᵛ','w' to 'ʷ','x' to 'ˣ','y' to 'ʸ','z' to 'ᶻ')
    private val subscript = mapOf('0' to '₀','1' to '₁','2' to '₂','3' to '₃','4' to '₄','5' to '₅','6' to '₆','7' to '₇','8' to '₈','9' to '₉','+' to '₊','-' to '₋','=' to '₌','(' to '₍',')' to '₎','a' to 'ₐ','e' to 'ₑ','h' to 'ₕ','i' to 'ᵢ','j' to 'ⱼ','k' to 'ₖ','l' to 'ₗ','m' to 'ₘ','n' to 'ₙ','o' to 'ₒ','p' to 'ₚ','r' to 'ᵣ','s' to 'ₛ','t' to 'ₜ','u' to 'ᵤ','v' to 'ᵥ','x' to 'ₓ')

    fun format(source: String): String {
        var value = source
            .replace("\\(", "").replace("\\)", "")
            .replace("\\[", "").replace("\\]", "")
            .replace("$", "")
        value = replaceBracedFunction(value, "\\frac") { a, b -> "($a)⁄($b)" }
        value = replaceBracedFunction(value, "\\dfrac") { a, b -> "($a)⁄($b)" }
        value = replaceBracedFunction(value, "\\sqrt") { a, _ -> "√($a)" }
        listOf("\\mathrm", "\\text", "\\mathbf", "\\ce", "\\operatorname", "\\mathcal", "\\mathbb", "\\boldsymbol").forEach { cmd ->
            value = replaceSingleBraced(value, cmd)
        }
        commands.entries.sortedByDescending { it.key.length }.forEach { (key, replacement) -> value = value.replace(key, replacement) }
        value = value.replace("\\left", "").replace("\\right", "")
        value = value.replace(Regex("""\^\{([^{}]+)}""")) { m -> m.groupValues[1].map { superscript[it] ?: it }.joinToString("") }
        value = value.replace(Regex("""_\{([^{}]+)}""")) { m -> m.groupValues[1].map { subscript[it] ?: it }.joinToString("") }
        value = value.replace(Regex("""\^([A-Za-z0-9+\-=])""")) { m -> superscript[m.groupValues[1][0]]?.toString() ?: m.value }
        value = value.replace(Regex("""_([A-Za-z0-9+\-=])""")) { m -> subscript[m.groupValues[1][0]]?.toString() ?: m.value }
        value = value.replace("\\ ", " ").replace("\\%", "%")
        return value.replace("{", "").replace("}", "").replace("  ", " ").trim()
    }

    private fun replaceBracedFunction(input: String, command: String, render: (String, String) -> String): String {
        var result = input
        var searchFrom = 0
        while (true) {
            val start = result.indexOf(command, searchFrom)
            if (start < 0) break
            val firstOpen = result.indexOf('{', start + command.length)
            if (firstOpen < 0) break
            val firstEnd = matchingBrace(result, firstOpen)
            if (firstEnd < 0) break
            val secondOpen = if (command == "\\sqrt") -1 else result.indexOf('{', firstEnd + 1)
            val secondEnd = if (secondOpen >= 0) matchingBrace(result, secondOpen) else -1
            val a = result.substring(firstOpen + 1, firstEnd)
            val b = if (secondOpen >= 0 && secondEnd >= 0) result.substring(secondOpen + 1, secondEnd) else ""
            val end = if (secondEnd >= 0) secondEnd + 1 else firstEnd + 1
            result = result.substring(0, start) + render(a, b) + result.substring(end)
            searchFrom = start + 1
        }
        return result
    }

    private fun replaceSingleBraced(input: String, command: String): String {
        var result = input
        var from = 0
        while (true) {
            val start = result.indexOf(command, from)
            if (start < 0) return result
            val open = result.indexOf('{', start + command.length)
            if (open < 0) return result
            val end = matchingBrace(result, open)
            if (end < 0) return result
            val replacement = result.substring(open + 1, end)
            result = result.substring(0, start) + replacement + result.substring(end + 1)
            from = start + replacement.length
        }
    }

    private fun matchingBrace(value: String, open: Int): Int {
        var depth = 0
        for (i in open until value.length) {
            if (value[i] == '{') depth++
            if (value[i] == '}') {
                depth--
                if (depth == 0) return i
            }
        }
        return -1
    }

    fun annotated(source: String): AnnotatedString {
        val formatted = format(source)
        return buildAnnotatedString {
            append(formatted)
            Regex("""[⁰¹²³⁴⁵⁶⁷⁸⁹⁺⁻⁼⁽⁾ⁿⁱ₀₁₂₃₄₅₆₇₈₉₊₋₌₍₎ᵢₙ]+""").findAll(formatted).forEach {
                addStyle(SpanStyle(fontSize = 0.72.em, baselineShift = if (it.value.firstOrNull()?.let { c -> c in '⁰'..'ⁿ' } == true) BaselineShift.Superscript else BaselineShift.Subscript), it.range.first, it.range.last + 1)
            }
        }
    }
}

internal data class ScientificContentSegment(val prose: String? = null, val equation: String? = null)

internal fun parseScientificContent(source: String): List<ScientificContentSegment> {
    val pattern = Regex("""\$\$(.+?)\$\$|\\\[(.+?)\\\]""", setOf(RegexOption.DOT_MATCHES_ALL))
    val segments = mutableListOf<ScientificContentSegment>()
    var cursor = 0
    pattern.findAll(source).forEach { match ->
        if (match.range.first > cursor) {
            val prose = source.substring(cursor, match.range.first).trim()
            if (prose.isNotEmpty()) segments.add(ScientificContentSegment(prose = prose))
        }
        val equation = (match.groups[1]?.value ?: match.groups[2]?.value).orEmpty().trim()
        if (equation.isNotEmpty()) segments.add(ScientificContentSegment(equation = equation))
        cursor = match.range.last + 1
    }
    if (cursor < source.length) {
        val prose = source.substring(cursor).trim()
        if (prose.isNotEmpty()) segments.add(ScientificContentSegment(prose = prose))
    }
    return segments.ifEmpty { listOf(ScientificContentSegment(prose = source)) }
}

@Composable
fun ScientificContent(
    content: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge
) {
    val segments = remember(content) { parseScientificContent(content) }
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        segments.forEach { segment ->
            when {
                segment.equation != null -> EquationDisplayCard(
                    formula = segment.equation,
                    caption = "SCIENTIFIC EXPRESSION"
                )
                !segment.prose.isNullOrBlank() -> ScientificText(segment.prose, style = style)
            }
        }
    }
}

@Composable
fun ScientificText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null
) {
    val formatted = remember(text) { ScientificNotation.annotated(text) }
    Text(formatted, modifier = modifier, style = style, color = color, fontWeight = fontWeight)
}

private data class EquationSegment(
    val plain: String? = null,
    val numerator: String? = null,
    val denominator: String? = null
)

private fun parseEquationSegments(source: String): List<EquationSegment> {
    val parts = mutableListOf<EquationSegment>()
    val plain = StringBuilder()
    var index = 0
    while (index < source.length) {
        val command = when {
            source.startsWith("\\dfrac", index) -> "\\dfrac"
            source.startsWith("\\frac", index) -> "\\frac"
            else -> null
        }
        if (command == null) {
            plain.append(source[index])
            index++
            continue
        }
        val numeratorOpen = source.indexOf('{', index + command.length)
        val numeratorEnd = if (numeratorOpen >= 0) findMatchingMathBrace(source, numeratorOpen) else -1
        val denominatorOpen = if (numeratorEnd >= 0) source.indexOf('{', numeratorEnd + 1) else -1
        val denominatorEnd = if (denominatorOpen >= 0) findMatchingMathBrace(source, denominatorOpen) else -1
        if (numeratorOpen < 0 || numeratorEnd < 0 || denominatorOpen < 0 || denominatorEnd < 0) {
            plain.append(source[index])
            index++
            continue
        }
        if (plain.isNotEmpty()) {
            parts.add(EquationSegment(plain = plain.toString()))
            plain.clear()
        }
        parts.add(
            EquationSegment(
                numerator = source.substring(numeratorOpen + 1, numeratorEnd),
                denominator = source.substring(denominatorOpen + 1, denominatorEnd)
            )
        )
        index = denominatorEnd + 1
    }
    if (plain.isNotEmpty()) parts.add(EquationSegment(plain = plain.toString()))
    return parts
}

private fun findMatchingMathBrace(source: String, open: Int): Int {
    var depth = 0
    for (index in open until source.length) {
        if (source[index] == '{') depth++
        if (source[index] == '}') {
            depth--
            if (depth == 0) return index
        }
    }
    return -1
}

@Composable
private fun EquationExpression(
    source: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineSmall
) {
    val parts = remember(source) { parseEquationSegments(source) }
    Row(modifier = modifier, verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        parts.forEach { part ->
            if (part.numerator != null && part.denominator != null) {
                Column(
                    modifier = Modifier.width(IntrinsicSize.Max),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    EquationExpression(part.numerator, style = style.copy(fontSize = style.fontSize * 0.72f))
                    androidx.compose.foundation.layout.Box(
                        Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colorScheme.onSurface)
                    )
                    EquationExpression(part.denominator, style = style.copy(fontSize = style.fontSize * 0.72f))
                }
            } else if (!part.plain.isNullOrEmpty()) {
                ScientificText(part.plain, style = style)
            }
        }
    }
}

@Composable
fun EquationDisplayCard(
    formula: String,
    modifier: Modifier = Modifier,
    caption: String = "KEY EQUATION"
) {
    val lines = remember(formula) { formula.split('\n').map { it.trim() }.filter { it.isNotEmpty() } }
    Surface(
        modifier = modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min).background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f))) {
            androidx.compose.foundation.layout.Box(Modifier.width(4.dp).fillMaxHeight().background(MaterialTheme.colorScheme.primary))
            Column(Modifier.weight(1f).padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(caption, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                lines.forEach { line ->
                    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
                        EquationExpression(line, style = MaterialTheme.typography.headlineSmall.copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold))
                    }
                }
            }
        }
    }
}
