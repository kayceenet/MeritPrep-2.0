package com.kaycee.meritprep

/** JAMB Mathematics topic map. The first eight units are beginner lesson drafts; remaining units are outlines. */
object MathematicsCourse {
    const val SYLLABUS_VERSION = "JAMB-IBASS-Mathematics-23-topic-map"
    const val SOURCE_URL = "https://ibass.jamb.gov.ng/e-syllabus"

    private fun outline(id: String, title: String, scope: String) = LearningTopic(
        id = id,
        title = title,
        subjectCode = "MATH",
        introduction = "This is a mapped JAMB UTME Mathematics topic. The outline lists the syllabus scope; a complete beginner lesson and reviewed practice set are still being developed.",
        objectives = listOf("Recognise the mathematical ideas and notation in this topic", "Choose suitable methods and formulae", "Solve problems accurately and communicate working clearly"),
        sections = listOf(
            "Syllabus scope" to scope,
            "Learning status" to "This entry is a syllabus outline, not a completed lesson. Full teaching should include prerequisite ideas, worked examples, common errors and reviewed formative assessment."
        ),
        checkQuestion = "What is the status of this topic in the current build?",
        checkOptions = listOf("A fully reviewed official JAMB lesson", "A syllabus outline awaiting full lesson development", "An official past question paper", "A guaranteed grade predictor"),
        correctOption = 1,
        explanation = "This entry maps the syllabus topic but is not yet a complete or independently reviewed lesson.",
        isSyllabusOutline = true
    )

    private val syllabusEntries: List<LearningTopic> = listOf(
        LearningTopic(
            "math-number-bases", "Number bases and modular arithmetic", "MATH",
            "Our everyday number system uses base ten, but computers and some mathematical problems use other bases. A number's value depends on its digits and their place values. This lesson shows how to read, convert and calculate in different bases, then introduces modular arithmetic as a way of working with remainders.",
            listOf("Explain place value in a base-b system", "Convert whole numbers between bases 2 to 10", "Convert simple fractional parts between bases", "Perform arithmetic in different bases", "Solve simple modular arithmetic problems"),
            listOf(
                "Place value" to "In base b, each position represents a power of b. For example, 231₄ = 2×4² + 3×4¹ + 1×4⁰ = 32 + 12 + 1 = 45₁₀. A valid base-b numeral can contain only digits from 0 to b−1. Thus 29₄ is invalid because digit 9 is not allowed in base four.",
                "Convert to base ten" to "Expand each digit by its place value and add. For 1011₂, the value is 1×8 + 0×4 + 1×2 + 1×1 = 11₁₀. For a fractional part, positions after the point represent negative powers: 0.101₂ = 1/2 + 0/4 + 1/8 = 0.625₁₀.",
                "Convert from base ten" to "To convert a positive whole number to base b, divide repeatedly by b and record each remainder. Read the remainders from bottom to top. For 13₁₀: 13÷2 gives remainder 1, 6÷2 gives 0, 3÷2 gives 1, 1÷2 gives 1; therefore 13₁₀ = 1101₂. For a fractional part, repeatedly multiply the fraction by the new base and record the integer parts in order.",
                "Arithmetic in another base" to "Addition and multiplication use the same place-value logic as base ten, but carrying occurs when a column total reaches the base. In base five, 3 + 4 = 12₅ because 7₁₀ = 1×5 + 2. Convert to base ten to check a result if needed, then convert back.",
                "Modular arithmetic" to "a mod n is the remainder after dividing integer a by positive integer n. For example, 17 mod 5 = 2. Numbers with the same remainder are congruent modulo n: 17 ≡ 2 (mod 5). Addition and multiplication can be performed before or after reducing to the remainder. Clock arithmetic is a familiar example: 15 hours after 9 o'clock is 0 hours after 12, so 9 + 15 ≡ 0 (mod 12)."
            ),
            "What is 1011₂ expressed in base ten?",
            listOf("9", "10", "11", "13"), 2,
            "1011₂ = 1×8 + 0×4 + 1×2 + 1×1 = 11₁₀."
        ),
        LearningTopic(
            "math-fractions-percentages", "Fractions, decimals, approximations and percentages", "MATH",
            "Fractions, decimals and percentages are different ways of expressing parts of a whole. They appear in discounts, examination scores, business transactions, measurement and everyday comparisons. The key is to keep units and the reference whole clear.",
            listOf("Perform the four operations on fractions and decimals", "Round numbers to significant figures and decimal places", "Calculate percentage error", "Solve simple interest, profit and loss problems", "Apply ratio, proportion, rate, shares and VAT"),
            listOf(
                "Fractions and decimals" to "To add or subtract fractions, first use a common denominator. For multiplication, multiply numerators and denominators; for division, multiply by the reciprocal. For example, 2/3 + 1/4 = 8/12 + 3/12 = 11/12. Align decimal points when adding or subtracting decimals.",
                "Significant figures and decimal places" to "Significant figures begin at the first non-zero digit. The number 0.004806 has four significant figures. Decimal places count digits after the decimal point. To round, inspect the next digit: 5 or more rounds the last retained digit up; 4 or less leaves it unchanged. State the requested precision.",
                "Percentage and percentage error" to "Percentage = (part/whole)×100%. Percentage change = (change/original value)×100%. Percentage error = (absolute error/accepted or reference value)×100%, using the reference specified in the question. Do not divide by the new value when calculating a percentage increase from an original value.",
                "Simple interest, profit and loss" to "Simple interest I = Prt, where P is principal, r is annual rate as a decimal and t is time in years. Amount A = P + I. Profit = selling price − cost price; loss = cost price − selling price. Profit or loss percentage is calculated using cost price as the reference unless the question states otherwise.",
                "Ratio, proportion, shares and VAT" to "A ratio compares quantities in the same units. Divide each term by a common factor to simplify. In direct proportion y = kx; in inverse proportion y = k/x. VAT is calculated as the stated percentage of the taxable price; total price = price + VAT. Read whether a question asks for VAT-exclusive or VAT-inclusive price before calculating."
            ),
            "A shirt costs ₦8,000 and is discounted by 15%. What is the sale price?",
            listOf("₦6,400", "₦6,800", "₦7,200", "₦7,850"), 1,
            "Discount = 0.15×₦8,000 = ₦1,200. Sale price = ₦8,000 − ₦1,200 = ₦6,800."
        ),
        LearningTopic(
            "math-indices-logs-surds", "Indices, logarithms and surds", "MATH",
            "Indices are a compact way to write repeated multiplication. Logarithms reverse exponentiation, while surds keep exact values involving roots that are not rational numbers. These ideas make large and small calculations easier and help solve equations.",
            listOf("Apply laws of indices including zero, negative and fractional powers", "Solve simple exponential equations", "Use standard form", "Apply logarithm laws and change of base", "Simplify, rationalise and operate on surds"),
            listOf(
                "Laws of indices" to "For non-zero a, aᵐ×aⁿ = aᵐ⁺ⁿ; aᵐ/aⁿ = aᵐ⁻ⁿ; (aᵐ)ⁿ = aᵐⁿ; a⁰ = 1; and a⁻ⁿ = 1/aⁿ. Fractional indices represent roots: a¹/² = √a and aᵐ/ⁿ = ⁿ√(aᵐ) where the real value is defined. Apply the laws only when the bases and conditions permit.",
                "Standard form and exponential equations" to "Standard form writes a number as A×10ⁿ where 1≤A<10 and n is an integer. To solve aˣ = aʸ for a positive base a≠1, equate exponents: x = y. If bases differ, express them in a common base or use logarithms.",
                "Logarithms" to "logₐ x = y means aʸ = x, where a>0, a≠1 and x>0. The laws are logₐ(xy)=logₐx+logₐy; logₐ(x/y)=logₐx−logₐy; and logₐ(xⁿ)=n logₐx. Change of base: logₐx = log_bx/log_ba. Check that all logarithm arguments are positive.",
                "Surds" to "A surd is an irrational root expression kept in exact form, such as √2. Simplify by extracting perfect-square factors: √72 = √(36×2)=6√2. Like surds can be added after simplification. Rationalise a denominator by multiplying by a suitable surd or conjugate; for example 1/√3 = √3/3.",
                "Worked example" to "Solve 2ˣ = 16. Since 16 = 2⁴, equate exponents to get x=4. For a logarithm example, log₂ 8 = 3 because 2³=8. Always check the original equation and its domain."
            ),
            "Simplify √72.",
            listOf("3√8", "4√3", "6√2", "8√2"), 2,
            "√72 = √(36×2) = 6√2."
        ),
        LearningTopic(
            "math-sets", "Sets", "MATH",
            "A set is a well-defined collection of objects. Sets help organise information such as students who study particular subjects, customers who buy products or numbers that satisfy a condition. Venn diagrams make relationships between sets visible.",
            listOf("Identify common types of sets", "Use set notation and symbols", "Find unions, intersections and complements", "Calculate cardinality of finite sets", "Solve Venn diagram problems involving up to three sets"),
            listOf(
                "Types and notation" to "A set is usually named with a capital letter and its members are written in braces, for example A={1,2,3}. The empty set is ∅; the universal set is U; a subset is written A⊆B; and the complement A′ contains elements in U that are not in A. Disjoint sets have no common elements.",
                "Union and intersection" to "The union A∪B contains elements in A or B or both. The intersection A∩B contains elements common to both sets. The set difference A∖B contains elements in A but not B. Use the definitions carefully; 'or' in set union is inclusive.",
                "Cardinality and two-set formula" to "n(A) is the number of elements in finite set A. For two sets, n(A∪B)=n(A)+n(B)−n(A∩B). The intersection is subtracted once because it was counted in both individual set totals.",
                "Venn diagrams" to "Draw a rectangle for the universal set and circles for the sets. For two sets, fill the intersection first, then the 'A only' and 'B only' regions, then the outside region. For three sets, start with the central triple intersection, then the pairwise-only intersections, then the single-set-only regions.",
                "Worked example" to "In a class of 40 learners, 22 study Mathematics, 18 study Physics and 8 study both. The number studying at least one is 22+18−8=32. The number studying neither is 40−32=8. Check that all regions add to the universal total."
            ),
            "If n(A)=22, n(B)=18 and n(A∩B)=8, find n(A∪B).",
            listOf("26", "30", "32", "40"), 2,
            "n(A∪B)=n(A)+n(B)−n(A∩B)=22+18−8=32."
        ),
        LearningTopic(
            "math-polynomials", "Polynomials", "MATH",
            "Polynomials are expressions built from variables, constants and non-negative integer powers. They describe patterns, model relationships and help solve equations. This lesson builds from collecting like terms to factorisation, roots and polynomial graphs.",
            listOf("Change the subject of a formula", "Add, subtract, multiply and divide polynomials up to degree three", "Factorise polynomials using common factors and identities", "Apply factor and remainder theorems", "Solve simple simultaneous linear-quadratic equations and interpret cubic graphs"),
            listOf(
                "Terms, coefficients and degree" to "A polynomial is a sum of terms such as 3x²−2x+5. The coefficient of x² is 3; the constant term is 5; the degree is the highest power, here 2. Like terms have the same variable raised to the same power and can be combined.",
                "Operations and identities" to "Add or subtract by collecting like terms. Multiply using the distributive law and simplify. Useful identities include (a+b)²=a²+2ab+b²; (a−b)²=a²−2ab+b²; and a²−b²=(a−b)(a+b). Divide only when the divisor and remainder are handled correctly.",
                "Factorisation and roots" to "Factorisation writes a polynomial as a product of simpler factors. A common factor can be extracted first. A root α satisfies f(α)=0. If (x−α) is a factor, then α is a root. For a quadratic, factorise or use the quadratic formula; for a cubic, use a known factor and divide to obtain a quadratic where possible.",
                "Factor and remainder theorems" to "The remainder theorem states that the remainder when f(x) is divided by (x−a) is f(a). The factor theorem states that (x−a) is a factor if and only if f(a)=0. Substitute carefully, including negative signs and powers.",
                "Graphs and simultaneous equations" to "A polynomial graph shows y=f(x). The x-intercepts are real roots; turning points can represent local maxima or minima. To solve one linear and one quadratic equation simultaneously, substitute the linear expression into the quadratic, solve for x, then substitute back for y. Check both solutions in the original equations."
            ),
            "If f(x)=x²−5x+6, which is a factorisation of f(x)?",
            listOf("(x−1)(x−6)", "(x+2)(x+3)", "(x−2)(x−3)", "(x+2)(x−3)"), 2,
            "x²−5x+6=(x−2)(x−3), since −2−3=−5 and (−2)(−3)=6."
        ),
        LearningTopic(
            "math-variation", "Variation", "MATH",
            "Variation describes how one quantity changes when another quantity changes. It is useful for scaling recipes, comparing work rates, modelling travel and solving problems involving several changing quantities.",
            listOf("Recognise direct and inverse variation", "Solve problems involving joint variation", "Solve problems involving partial variation", "Find the constant of variation", "Calculate percentage changes in variation models"),
            listOf(
                "Direct variation" to "If y varies directly as x, then y∝x and y=kx, where k is a constant. Find k using a known pair of values. If x doubles, y doubles when k remains fixed.",
                "Inverse variation" to "If y varies inversely as x, then y∝1/x and y=k/x, or xy=k. If x doubles, y is halved. Check whether the product xy remains constant in a table of values.",
                "Joint variation" to "A quantity may vary jointly as two or more variables. If z varies jointly as x and y, z=kxy. Substitute known values to find k, then use the model to calculate an unknown.",
                "Partial variation" to "Partial variation includes a constant term. If y varies partly as x, y=a+bx, where a and b are constants. Use two known pairs to form simultaneous equations and solve for a and b.",
                "Percentage change" to "For y=kx, a percentage increase in x gives the same percentage increase in y. For inverse variation y=k/x, a percentage change does not produce an equal and opposite percentage change unless the exact relationship is calculated. Substitute the new x into the model."
            ),
            "If y varies directly as x and y=18 when x=6, find y when x=10.",
            listOf("24", "28", "30", "36"), 2,
            "y=kx. Since 18=6k, k=3. When x=10, y=3×10=30."
        ),
        LearningTopic(
            "math-inequalities", "Inequalities", "MATH",
            "An equation asks where two expressions are equal. An inequality asks where one expression is greater or smaller than another. Solutions may be a range of values, shown using number lines or graphs.",
            listOf("Solve linear inequalities in one variable", "Represent solution sets on number lines", "Solve simple quadratic inequalities with integral roots", "Interpret graphical inequalities", "Apply inequality rules correctly when multiplying or dividing by negatives"),
            listOf(
                "Inequality symbols" to "The symbols < and > mean less than and greater than; ≤ and ≥ include equality. A solution set may contain many real numbers. Use an open circle for a strict endpoint and a closed circle when the endpoint is included.",
                "Solve linear inequalities" to "Solve similarly to an equation by performing the same operation on both sides. One crucial rule: when multiplying or dividing both sides by a negative number, reverse the inequality sign. For example, −2x>6 gives x<−3.",
                "Compound inequalities" to "A compound inequality can describe a bounded interval, such as 2<x≤5. Solve each part while preserving the relationship. On a number line, shade between the endpoints and choose open or closed circles according to the symbols.",
                "Quadratic inequalities" to "First find the roots of the corresponding quadratic equation and factorise where possible. The roots divide the number line into intervals. Test one value in each interval to determine where the expression is positive or negative. For a parabola opening upwards, the quadratic is positive outside two distinct real roots and negative between them.",
                "Graphical interpretation" to "For y>f(x), shade the region above the graph of y=f(x); for y<f(x), shade below it. Use a dashed boundary for a strict inequality and a solid boundary when equality is included. Read the axes and boundary equation carefully."
            ),
            "Solve −2x > 6.",
            listOf("x > −3", "x < −3", "x > 3", "x < 3"), 1,
            "Divide both sides by −2 and reverse the inequality sign: x<−3."
        ),
        LearningTopic(
            "math-progression", "Progression", "MATH",
            "A progression is an ordered sequence whose terms follow a pattern. Arithmetic progressions have a constant difference; geometric progressions have a constant ratio. These patterns help calculate future terms and sums without writing every term.",
            listOf("Identify arithmetic and geometric progressions", "Find the nth term of an arithmetic progression", "Calculate sums of arithmetic progressions", "Find terms and sums of geometric progressions", "Determine when a geometric progression has a sum to infinity"),
            listOf(
                "Arithmetic progression" to "An arithmetic progression (AP) has a common difference d between consecutive terms. Its nth term is Tₙ=a+(n−1)d, where a is the first term. For example, 4,7,10,13 has a=4 and d=3.",
                "Sum of an AP" to "The sum of the first n terms is Sₙ=n/2[2a+(n−1)d], or Sₙ=n/2(a+l), where l is the last term. Use the formula that matches the known information.",
                "Geometric progression" to "A geometric progression (GP) has a common ratio r between consecutive terms. Its nth term is Tₙ=arⁿ⁻¹. For example, 3,6,12,24 has a=3 and r=2.",
                "Sum of a GP" to "For r≠1, the sum of the first n terms is Sₙ=a(rⁿ−1)/(r−1), equivalently a(1−rⁿ)/(1−r). Both forms are equal; choose the one that keeps signs clear.",
                "Sum to infinity" to "A GP has a finite sum to infinity only when |r|<1. Then S∞=a/(1−r). If |r|≥1, the infinite series does not converge to a finite sum. Always check the common ratio before using the formula."
            ),
            "Find the 10th term of the arithmetic progression 4, 7, 10, 13, …",
            listOf("28", "30", "31", "34"), 2,
            "a=4, d=3. T₁₀=4+(10−1)×3=4+27=31."
        )
    ) + listOf(
        MathematicsCourseExtension.topics.first { it.id == "math-binary-operations" },
        MathematicsCourseExtension.topics.first { it.id == "math-matrices" },
        MathematicsCourseExtension.topics.first { it.id == "math-euclidean-geometry" },
        MathematicsCourseExtension.topics.first { it.id == "math-mensuration" },
        MathematicsCourseExtension.topics.first { it.id == "math-loci" },
        MathematicsCourseExtension.topics.first { it.id == "math-coordinate-geometry" },
        MathematicsCourseExtension.topics.first { it.id == "math-trigonometry" },
        MathematicsCourseExtension2.topics.first { it.id == "math-differentiation" },
        MathematicsCourseExtension2.topics.first { it.id == "math-application-differentiation" },
        MathematicsCourseExtension2.topics.first { it.id == "math-integration" },
        outline("math-data-representation", "Representation of data", "Frequency distributions; histograms; bar charts; pie charts; interpretation of graphical data."),
        outline("math-measures-location", "Measures of location", "Cumulative frequency; mean, mode and median for ungrouped and grouped data in simple cases; ogives; quartiles and percentiles."),
        outline("math-measures-dispersion", "Measures of dispersion", "Range, mean deviation, variance and standard deviation for ungrouped and grouped data."),
        outline("math-permutation-combination", "Permutation and combination", "Linear and circular arrangements; arrangements involving repeated objects; simple counting problems."),
        outline("math-probability", "Probability", "Experimental probability; sample spaces; addition and multiplication of probabilities; mutually exclusive and independent cases.")
    )

    val topics: List<LearningTopic> = syllabusEntries.map { entry ->
        MathematicsCourseExtension.topics.firstOrNull { it.id == entry.id } ?: entry
    }
}
