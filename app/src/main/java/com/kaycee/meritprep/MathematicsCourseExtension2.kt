package com.kaycee.meritprep

/** Calculus learning block for JAMB Mathematics. Draft content pending independent review. */
object MathematicsCourseExtension2 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "math-differentiation", "Differentiation", "MATH",
            "Differentiation measures how quickly one quantity changes as another changes. It gives the gradient of a curve at a point and is used to model velocity, acceleration, growth and many other rates of change. Begin with the idea of a limit, then learn rules that make derivatives easier to calculate.",
            listOf(
                "Interpret a derivative as an instantaneous rate of change",
                "Find derivatives from first principles in simple cases",
                "Apply the power, constant, sum and simple trigonometric rules",
                "Differentiate simple polynomial and trigonometric functions",
                "Interpret the derivative as the gradient of a tangent"
            ),
            listOf(
                "Gradient of a curve" to "A straight line has a constant gradient, but the gradient of a curve usually changes from point to point. The derivative dy/dx gives the gradient of the tangent to y=f(x) at a particular x-value. It also describes the instantaneous rate at which y changes with x.",
                "First principles and limits" to "The derivative from first principles is f'(x)=lim(h→0)[f(x+h)−f(x)]/h, where the limit exists. The difference quotient gives the average rate of change over a small interval; taking the limit as h approaches zero gives the instantaneous rate. A limit is approached, not substituted as h=0 at the start.",
                "Power and constant rules" to "For y=xⁿ, dy/dx=nxⁿ⁻¹. The derivative of a constant is zero. A constant multiplier remains as a factor, and the derivative of a sum is the sum of the derivatives. For example, d/dx(3x²−5x+4)=6x−5.",
                "Simple trigonometric derivatives" to "When angles are measured in radians, d/dx(sin x)=cos x and d/dx(cos x)=−sin x. The derivative of tan x is sec²x where defined. If the question uses degrees, convert the angle variable or account for the π/180 factor; the standard rules assume radians.",
                "Worked example" to "Differentiate y=2x³−4x²+7x−9 term by term: dy/dx=6x²−8x+7. The constant −9 differentiates to zero. If x=2, the gradient is 6×4−8×2+7=15.",
                "Notation and common errors" to "dy/dx, f'(x) and Dₓf(x) are common derivative notations. Reduce powers carefully: the exponent becomes a coefficient and decreases by one. Do not confuse a derivative with an integral, and do not forget that trigonometric derivative rules use radians."
            ),
            "Differentiate y = 3x² − 5x + 4.",
            listOf("6x−5", "3x−5", "6x²−5", "3x²−5x"), 0,
            "Apply the power rule term by term: d(3x²)/dx=6x, d(−5x)/dx=−5 and the derivative of 4 is zero."
        ),
        LearningTopic(
            "math-application-differentiation", "Application of differentiation", "MATH",
            "A derivative can answer practical questions: how fast a vehicle is moving, where a curve is steepest, or where a quantity reaches a maximum or minimum. These applications depend on interpreting the derivative correctly and checking the meaning of the result in the original problem.",
            listOf(
                "Relate displacement, velocity and acceleration through differentiation",
                "Find gradients of tangents and normals",
                "Locate stationary points by setting the first derivative to zero",
                "Classify simple stationary points using the second derivative",
                "Solve simple optimisation and rate-of-change problems"
            ),
            listOf(
                "Rates of change in motion" to "If displacement s is a function of time t, velocity v=ds/dt and acceleration a=dv/dt=d²s/dt². Units follow from the variables: if s is in metres and t in seconds, v is in m s⁻¹ and a is in m s⁻².",
                "Tangents and normals" to "The gradient of the tangent to y=f(x) at x=a is f'(a). The normal is perpendicular to the tangent, so if the tangent gradient is m and m is not zero, the normal gradient is −1/m. Use the point-gradient equation y−y₁=m(x−x₁) to form either line.",
                "Stationary points" to "A stationary point occurs where f'(x)=0, so the tangent is horizontal. Solve the derivative equation to find candidate x-values, then substitute into the original function to find the corresponding y-values. A stationary point may be a maximum, a minimum or a stationary point of inflection.",
                "Second derivative test" to "At a stationary point, if f''(x)>0 the curve is locally concave upwards and the point is a local minimum; if f''(x)<0 it is locally concave downwards and the point is a local maximum. If f''(x)=0, the test is inconclusive and further analysis is needed.",
                "Worked example: a minimum" to "For f(x)=x²−4x+3, f'(x)=2x−4. Set 2x−4=0 to get x=2. Since f''(x)=2>0, the stationary point is a minimum. f(2)=4−8+3=−1, so the minimum point is (2,−1).",
                "Optimisation and interpretation" to "Translate the word problem into a function of one variable, state any physical restrictions, differentiate, solve for stationary points and check which value gives the required maximum or minimum. A mathematical optimum outside the permitted domain is not a valid practical solution."
            ),
            "For f(x)=x²−4x+3, at what x-value does the stationary point occur?",
            listOf("−2", "0", "2", "4"), 2,
            "f'(x)=2x−4. Set the derivative to zero: 2x−4=0, so x=2."
        ),
        LearningTopic(
            "math-integration", "Integration", "MATH",
            "Integration reverses differentiation in many common cases. It can recover a function from its rate of change and calculate areas under curves. The constant of integration matters for indefinite integrals because many functions have the same derivative.",
            listOf(
                "Interpret integration as the reverse process of differentiation",
                "Apply the power rule for simple algebraic functions",
                "Integrate simple trigonometric functions",
                "Evaluate simple definite integrals",
                "Interpret a definite integral as signed area"
            ),
            listOf(
                "Antiderivatives and the constant of integration" to "If F'(x)=f(x), then F is an antiderivative of f. The indefinite integral ∫f(x)dx=F(x)+C, where C is an arbitrary constant. It is needed because the derivative of any constant is zero.",
                "Power rule for integration" to "For n≠−1, ∫xⁿ dx = xⁿ⁺¹/(n+1)+C. Increase the power by one, then divide by the new power. For example, ∫3x² dx = x³+C. The rule does not apply directly to x⁻¹; ∫1/x dx = ln|x|+C.",
                "Simple trigonometric integrals" to "∫cos x dx=sin x+C and ∫sin x dx=−cos x+C when x is in radians. Differentiate the proposed antiderivative to check the sign and result.",
                "Definite integrals" to "For a definite integral ∫ₐᵇ f(x)dx, find an antiderivative F(x), then calculate F(b)−F(a). The arbitrary constant cancels, so it is not needed in the final evaluation. Keep the upper and lower limits in the correct order.",
                "Worked example" to "Evaluate ∫₀² 2x dx. An antiderivative is x². Substitute the limits: [x²]₀²=2²−0²=4. The result is 4 square units if the integral represents an area in a suitable context.",
                "Area and signed area" to "A definite integral gives signed area: regions above the x-axis contribute positively and regions below contribute negatively. For total geometric area, split the interval where the curve crosses the axis and add the positive magnitudes. A graph or sign analysis helps avoid treating negative signed area as a physical negative area."
            ),
            "Evaluate ∫ 3x² dx.",
            listOf("x³ + C", "3x³ + C", "6x + C", "x² + C"), 0,
            "Increase the power from 2 to 3 and divide by 3: ∫3x²dx=3×(x³/3)+C=x³+C."
        )
    )
}
