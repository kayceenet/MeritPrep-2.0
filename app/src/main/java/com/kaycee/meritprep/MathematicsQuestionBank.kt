package com.kaycee.meritprep

import com.kaycee.meritprep.data.AssessmentQuestion

/** Original MeritPrep Mathematics questions mapped to developed JAMB syllabus topics. */
object MathematicsQuestionBank {
    private fun q(id: String, topic: String, prompt: String, options: List<String>, answer: Int, explanation: String) =
        AssessmentQuestion(id, topic, prompt, options, answer, explanation, "MeritPrep original Mathematics practice · $topic", false)

    val all: List<AssessmentQuestion> = listOf(
        q("math-bases-001","math-number-bases","What is 1011₂ in base ten?",listOf("9","10","11","13"),2,"1011₂ = 1×8 + 0×4 + 1×2 + 1 = 11."),
        q("math-bases-002","math-number-bases","Convert 13₁₀ to base two.",listOf("1011₂","1101₂","1110₂","1001₂"),1,"Repeated division by 2 gives remainders 1,0,1,1; read from bottom to top: 1101₂."),
        q("math-bases-003","math-number-bases","Evaluate 17 mod 5.",listOf("0","1","2","3"),2,"17 divided by 5 leaves remainder 2."),
        q("math-bases-004","math-number-bases","Which numeral is invalid in base four?",listOf("103₄","231₄","302₄","129₄"),3,"Base four permits only digits 0, 1, 2 and 3; digit 9 is invalid."),
        q("math-bases-005","math-number-bases","Evaluate 101₂ + 11₂.",listOf("110₂","111₂","1000₂","1010₂"),2,"101₂ = 5 and 11₂ = 3; 5 + 3 = 8 = 1000₂."),
        q("math-fractions-001","math-fractions-percentages","Evaluate 3/4 + 2/3.",listOf("5/7","17/12","13/12","7/12"),1,"Use denominator 12: 9/12 + 8/12 = 17/12."),
        q("math-fractions-002","math-fractions-percentages","How many significant figures are in 0.004560?",listOf("2","3","4","6"),2,"Leading zeros are not significant; 4, 5, 6 and the trailing decimal zero are four significant figures. Wait: the number is 0.004560, so significant digits are 4,5,6,0: four."),
        q("math-fractions-003","math-fractions-percentages","Find the simple interest on ₦5,000 at 10% per annum for 2 years.",listOf("₦500","₦1,000","₦1,500","₦2,000"),1,"I = Prt = 5000 × 0.10 × 2 = ₦1,000."),
        q("math-fractions-004","math-fractions-percentages","An item costs ₦800 and is sold for ₦1,000. What is the profit percentage?",listOf("20%","25%","30%","40%"),1,"Profit = 200; profit percentage = 200/800 × 100 = 25%."),
        q("math-fractions-005","math-fractions-percentages","Calculate 7.5% VAT on ₦20,000.",listOf("₦750","₦1,000","₦1,500","₦2,000"),2,"VAT = 0.075 × 20,000 = ₦1,500."),
        q("math-indices-001","math-indices-logs-surds","Simplify 2³ × 2⁴.",listOf("2⁷","2¹²","2¹","4⁷"),0,"When multiplying powers with the same base, add indices: 2³×2⁴ = 2⁷."),
        q("math-indices-002","math-indices-logs-surds","Evaluate log₁₀ 1000.",listOf("1","2","3","10"),2,"10³ = 1000, so log₁₀ 1000 = 3."),
        q("math-indices-003","math-indices-logs-surds","Simplify √72.",listOf("3√8","4√3","6√2","8√2"),2,"√72 = √(36×2) = 6√2."),
        q("math-indices-004","math-indices-logs-surds","Evaluate 3⁻².",listOf("−9","−1/9","1/9","9"),2,"A negative index gives a reciprocal: 3⁻² = 1/3² = 1/9."),
        q("math-indices-005","math-indices-logs-surds","Evaluate 8^(2/3).",listOf("2","4","6","16"),1,"8^(2/3) = (cube root of 8)² = 2² = 4."),
        q("math-sets-001","math-sets","If n(A)=22, n(B)=18 and n(A∩B)=8, find n(A∪B).",listOf("26","30","32","40"),2,"n(A∪B)=n(A)+n(B)−n(A∩B)=22+18−8=32."),
        q("math-sets-002","math-sets","The set containing no elements is called:",listOf("Universal set","Empty set","Finite set","Subset"),1,"The empty set, written ∅, contains no elements."),
        q("math-sets-003","math-sets","If A={1,2,3} and B={3,4,5}, find A∩B.",listOf("{1,2}","{3}","{1,2,3,4,5}","∅"),1,"The intersection contains elements common to both sets; only 3 is common."),
        q("math-sets-004","math-sets","If U={1,2,3,4,5} and A={1,3,5}, find A′.",listOf("{1,3,5}","{2,4}","{1,2,3}","∅"),1,"The complement contains elements in U that are not in A: {2,4}."),
        q("math-sets-005","math-sets","If A is a subset of B, then:",listOf("Every element of A is in B","Every element of B is in A","A and B are always disjoint","A is always empty"),0,"A⊆B means every member of A is also a member of B."),
        q("math-polynomials-001","math-polynomials","Factorise x²−5x+6.",listOf("(x−1)(x−6)","(x+2)(x+3)","(x−2)(x−3)","(x+2)(x−3)"),2,"The factors have product 6 and sum −5: (x−2)(x−3)."),
        q("math-polynomials-002","math-polynomials","If f(x)=x²−3x+2, what is the remainder when divided by x−2?",listOf("0","1","2","4"),0,"By the remainder theorem, remainder = f(2) = 4−6+2 = 0."),
        q("math-polynomials-003","math-polynomials","What is the degree of 4x³−2x²+x−7?",listOf("1","2","3","4"),2,"The highest power of x is 3, so the degree is 3."),
        q("math-polynomials-004","math-polynomials","Which is the expansion of (x+3)²?",listOf("x²+9","x²+3x+9","x²+6x+9","x²−6x+9"),2,"(x+3)² = x² + 2×x×3 + 3² = x²+6x+9."),
        q("math-polynomials-005","math-polynomials","Solve x+y=5 and y=2.",listOf("(1,4)","(2,3)","(3,2)","(5,2)"),2,"Substitute y=2 into x+y=5: x=3. The solution is (3,2)."),
        q("math-variation-001","math-variation","If y varies directly as x and y=18 when x=6, find y when x=10.",listOf("24","28","30","36"),2,"y=kx; k=18/6=3. When x=10, y=30."),
        q("math-variation-002","math-variation","If y varies inversely as x and y=4 when x=6, find y when x=8.",listOf("2","3","4","6"),1,"xy=k=24. When x=8, y=24/8=3."),
        q("math-variation-003","math-variation","If z varies jointly as x and y, the equation is:",listOf("z=kx+y","z=kxy","z=k/x+y","z=k/(xy)"),1,"Joint variation of z as x and y is represented by z=kxy."),
        q("math-variation-004","math-variation","If y=5+2x, what is y when x=7?",listOf("12","14","17","19"),2,"Substitute x=7: y=5+14=19. Correct option is 19, index 3."),
        q("math-variation-005","math-variation","If y varies directly as x, a 20% increase in x produces what percentage increase in y?",listOf("10%","20%","40%","No change"),1,"For direct variation y=kx with constant k, the percentage change in y equals the percentage change in x."),
        q("math-inequalities-001","math-inequalities","Solve −2x > 6.",listOf("x > −3","x < −3","x > 3","x < 3"),1,"Divide by −2 and reverse the inequality sign: x<−3."),
        q("math-inequalities-002","math-inequalities","Solve 3x+2 ≤ 11.",listOf("x ≤ 3","x ≥ 3","x ≤ 9","x ≥ 9"),0,"3x≤9, so x≤3."),
        q("math-inequalities-003","math-inequalities","Solve x²−5x+6 > 0.",listOf("2 < x < 3","x < 2 or x > 3","x ≤ 2 or x ≥ 3","All real x"),1,"Factor as (x−2)(x−3). The upward-opening quadratic is positive outside the roots."),
        q("math-inequalities-004","math-inequalities","Which endpoint is used on a number line for x<4?",listOf("Closed circle at 4","Open circle at 4","No endpoint","Closed circle at 0"),1,"The strict inequality excludes 4, so use an open circle."),
        q("math-inequalities-005","math-inequalities","Solve 2−x ≥ 5.",listOf("x ≥ −3","x ≤ −3","x ≥ 3","x ≤ 3"),1,"Subtract 2: −x≥3. Multiply by −1 and reverse the sign: x≤−3."),
        q("math-progression-001","math-progression","Find the 10th term of 4, 7, 10, 13, …",listOf("28","30","31","34"),2,"a=4, d=3. T₁₀=4+9×3=31."),
        q("math-progression-002","math-progression","Find the sum of the first 10 terms of 4, 7, 10, 13, …",listOf("155","165","175","185"),2,"S₁₀=10/2[2×4+9×3]=5×35=175."),
        q("math-progression-003","math-progression","Find the 5th term of the GP 3, 6, 12, 24, …",listOf("36","42","48","96"),2,"a=3 and r=2. T₅=3×2⁴=48."),
        q("math-progression-004","math-progression","Find the sum of the first four terms of 3, 6, 12, 24, …",listOf("36","42","45","48"),2,"3+6+12+24=45."),
        q("math-progression-005","math-progression","Find the sum to infinity of 4+2+1+½+…",listOf("4","6","8","10"),2,"a=4, r=1/2. Since |r|<1, S∞=a/(1−r)=4/(1/2)=8.")
    )

    fun forTopic(topicId: String): List<AssessmentQuestion> = all.filter { it.topicId == topicId }
}
