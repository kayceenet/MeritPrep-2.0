package com.kaycee.meritprep

/** Second beginner-learning block for JAMB Mathematics. Draft content pending independent review. */
object MathematicsCourseExtension {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "math-binary-operations", "Binary operations", "MATH",
            "A binary operation combines two elements from a set to produce another element. Addition and multiplication are familiar examples on the real numbers. In examination questions, the operation may be defined by a new symbol, so the safest approach is to substitute carefully and check which set the operation acts on.",
            listOf(
                "Explain a binary operation on a set",
                "Evaluate expressions using a defined operation",
                "Test closure, commutativity and associativity",
                "Find identity and inverse elements where they exist",
                "Use operation tables for finite sets"
            ),
            listOf(
                "Meaning of a binary operation" to "A binary operation on a set S combines any ordered pair of elements of S and gives exactly one result in S. If the operation is written as *, then a*b must be defined for every a and b in S. Closure is essential: if a result falls outside S, the operation is not closed on S.",
                "Substitution into a defined operation" to "Suppose a*b = a + b + ab. Then 2*3 = 2 + 3 + 2×3 = 11. Do not assume the symbol means ordinary multiplication. Replace the symbol with the rule given in the question, use brackets where necessary and simplify in order.",
                "Properties of operations" to "An operation is commutative if a*b = b*a for all permitted a,b. It is associative if (a*b)*c = a*(b*c). Ordinary addition and multiplication on real numbers have both properties. Subtraction is not commutative, because 5−2 is not equal to 2−5.",
                "Identity and inverse" to "An identity element e satisfies a*e = e*a = a for every a in the set. For a*b = a+b+ab on the real numbers, a*0 = a, so 0 is the identity. An inverse of a is an element x satisfying a*x = e. For a=2, 2*x = 0 gives 2+x+2x=0, so x=−2/3. An inverse may not exist for every element.",
                "Worked check" to "For a*b = a+b+ab, 2*3 = 11 and 3*2 = 11, so this example is commutative. To test associativity, expand both (a*b)*c and a*(b*c) using the definition. Do not conclude associativity from one numerical pair; the property must hold for all elements in the set.",
                "Operation tables" to "For a finite set, an operation table lists the result for every ordered pair. Read the row and column headings carefully. A row or column containing every element once can help identify inverses, while a fixed element that leaves every entry unchanged acts as an identity."
            ),
            "If a*b = a + b + ab, what is 2*3?",
            listOf("5", "6", "11", "13"), 2,
            "Substitute a=2 and b=3: 2*3 = 2 + 3 + 2×3 = 11."
        ),
        LearningTopic(
            "math-matrices", "Matrices and determinants", "MATH",
            "Matrices organise numbers into rows and columns. They are useful for solving simultaneous equations, representing transformations and handling structured data. The order of a matrix matters, and multiplication is not generally commutative.",
            listOf(
                "Identify the order and elements of a matrix",
                "Add, subtract and multiply compatible matrices",
                "Calculate the determinant of a 2×2 matrix",
                "Find the inverse of a non-singular 2×2 matrix",
                "Use matrices to solve simple simultaneous equations"
            ),
            listOf(
                "Order and equality" to "The order of a matrix is rows × columns. A matrix with two rows and three columns has order 2×3. Two matrices are equal only when they have the same order and corresponding elements are equal.",
                "Addition and scalar multiplication" to "Matrices can be added or subtracted only when they have the same order. Add corresponding entries. Multiplying a matrix by a scalar means multiplying every entry by that scalar. For example, 2[[1,2],[3,4]] = [[2,4],[6,8]].",
                "Matrix multiplication" to "If A has order m×n and B has order n×p, the product AB exists and has order m×p. Each entry is obtained by multiplying a row of A by a column of B and adding the products. In general AB is not equal to BA, and one product may exist even when the reverse product does not.",
                "Determinant and inverse" to "For A = [[a,b],[c,d]], det(A) = ad−bc. The matrix has an inverse only when ad−bc is not zero. Its inverse is (1/(ad−bc))[[d,−b],[−c,a]]. A zero determinant means the matrix is singular and has no inverse.",
                "Worked example" to "For A = [[2,1],[3,4]], det(A) = 2×4 − 1×3 = 5. Since 5 is not zero, A is invertible and A⁻¹ = (1/5)[[4,−1],[−3,2]]. Check by multiplying A by A⁻¹ to obtain the identity matrix.",
                "Matrices and simultaneous equations" to "A pair of linear equations can be written AX=B. If A is invertible, X=A⁻¹B. Check the order of the matrices before multiplying. Always substitute the resulting values into the original equations to confirm the solution."
            ),
            "Find the determinant of [[2,1],[3,4]].",
            listOf("3", "5", "8", "11"), 1,
            "The determinant is ad−bc = 2×4 − 1×3 = 5."
        ),
        LearningTopic(
            "math-euclidean-geometry", "Euclidean geometry", "MATH",
            "Geometry studies shapes, angles, lengths and their relationships. A diagram can help you see a problem, but a correct solution depends on the stated geometric facts and a clear chain of reasoning. This lesson builds from angle rules to triangles and circle geometry.",
            listOf(
                "Use angle properties of parallel lines",
                "Apply angle sums in triangles and polygons",
                "Distinguish congruent and similar figures",
                "Use Pythagoras' theorem in right-angled triangles",
                "Apply common circle theorems"
            ),
            listOf(
                "Angles and parallel lines" to "Angles on a straight line add to 180°; angles around a point add to 360°; vertically opposite angles are equal. When a transversal crosses parallel lines, corresponding angles are equal, alternate interior angles are equal and co-interior angles add to 180°.",
                "Triangles and polygons" to "The interior angles of a triangle add to 180°. An exterior angle of a triangle equals the sum of the two opposite interior angles. The sum of interior angles of an n-sided polygon is (n−2)×180°. The exterior angles of any convex polygon add to 360°.",
                "Congruence and similarity" to "Congruent shapes have the same size and shape; corresponding sides and angles are equal. Similar shapes have the same shape but may differ in size; corresponding angles are equal and corresponding sides are in a constant ratio. For similar plane figures, area ratio is the square of the length ratio.",
                "Pythagoras' theorem" to "In a right-angled triangle, the square of the hypotenuse equals the sum of the squares of the other two sides: c²=a²+b². The hypotenuse is opposite the right angle and is the longest side. The theorem applies only to right-angled triangles.",
                "Circle geometry" to "The angle at the centre standing on an arc is twice the angle at the circumference standing on the same arc. Angles in the same segment are equal. The angle in a semicircle is 90°. A tangent is perpendicular to the radius at the point of contact. The angle between a tangent and a chord equals the angle in the alternate segment.",
                "Reasoning from a diagram" to "Mark equal angles, parallel lines and right angles clearly. State the theorem used at each step. A diagram is not necessarily drawn to scale, so do not estimate an angle or length unless the question explicitly asks for an estimate."
            ),
            "Two angles in a triangle are 48° and 67°. Find the third angle.",
            listOf("55°", "65°", "75°", "85°"), 1,
            "The angles in a triangle add to 180°: 180°−48°−67° = 65°."
        ),
        LearningTopic(
            "math-mensuration", "Mensuration", "MATH",
            "Mensuration is the measurement of lengths, perimeters, areas, surface areas and volumes. The main challenge is choosing the correct formula and using consistent units. A diagram should be labelled before numbers are substituted.",
            listOf(
                "Calculate perimeters and areas of plane shapes",
                "Use arc length and sector area for circles",
                "Calculate surface areas and volumes of common solids",
                "Convert units of length, area and volume",
                "Solve practical problems involving composite shapes"
            ),
            listOf(
                "Perimeter and area" to "Perimeter is the total distance around a plane shape. Rectangle area A=lb; triangle area A=½bh; parallelogram area A=bh; trapezium area A=½(a+b)h; circle area A=πr² and circumference C=2πr. Use perpendicular height, not a sloping side, in area formulae.",
                "Sectors and arcs" to "For a sector of angle θ degrees, arc length = (θ/360)×2πr and sector area = (θ/360)×πr². If θ is measured in radians, arc length = rθ and sector area = ½r²θ. Use one angle unit consistently.",
                "Surface area and volume" to "Cuboid volume V=lbh; cylinder volume V=πr²h; cone volume V=⅓πr²h; sphere volume V=4/3πr³. Curved surface area of a cylinder is 2πrh; total surface area includes both circular ends. A sphere surface area is 4πr².",
                "Units and scale" to "When length is multiplied by a scale factor k, area changes by k² and volume by k³. Convert units before substitution: 1 m² = 10,000 cm² and 1 m³ = 1,000,000 cm³. Squared and cubed units must be converted with the same power.",
                "Worked example: cylinder" to "A cylinder has radius 3 cm and height 10 cm. Its volume is V=πr²h = π×3²×10 = 90π cm³, approximately 282.7 cm³. Keep the cubic unit in the final answer.",
                "Composite shapes" to "Split an irregular composite shape into familiar parts, calculate each area or volume, then add or subtract as required. For a hollow solid, subtract the empty part from the outer volume. Draw and label the component shapes before calculating."
            ),
            "Find the volume of a cylinder of radius 3 cm and height 10 cm.",
            listOf("30π cm³", "60π cm³", "90π cm³", "180π cm³"), 2,
            "V=πr²h = π×3²×10 = 90π cm³."
        ),
        LearningTopic(
            "math-loci", "Loci", "MATH",
            "A locus is the path followed by a point that obeys a stated condition. For example, every point a fixed distance from a fixed centre lies on a circle. Locus questions combine geometry, compass-and-ruler construction and logical intersection of conditions.",
            listOf(
                "Define a locus and interpret a locus condition",
                "Construct points at a fixed distance from a point",
                "Construct points equidistant from two points or lines",
                "Construct angle bisectors and perpendicular bisectors",
                "Find regions satisfying two or more locus conditions"
            ),
            listOf(
                "Fixed distance from a point" to "The locus of points at a fixed distance r from a fixed point O is a circle with centre O and radius r. Points within the circle are less than r away; points outside are more than r away.",
                "Equidistant from two points" to "The locus of points equidistant from two fixed points A and B is the perpendicular bisector of AB. Every point on this line has equal distances to A and B. Construct it by drawing equal-radius arcs from A and B and joining the arc intersections.",
                "Equidistant from two intersecting lines" to "The locus of points equidistant from two intersecting straight lines consists of the two angle bisectors. The perpendicular distance to each line must be equal; the distance is not measured along the lines.",
                "Fixed distance from a line" to "The locus of points at a fixed perpendicular distance d from a straight line consists of two lines parallel to the given line, one on each side, each at distance d.",
                "Combining conditions" to "If a point must be within a fixed distance of one point and equidistant from two other points, draw both loci and identify their intersection or permitted region. Use solid or dashed boundaries according to whether the condition includes equality.",
                "Construction accuracy" to "Use a sharp pencil, compass and ruler. Keep the compass radius fixed when drawing equal arcs. Show construction arcs where required; a guessed sketch is not a geometrical construction."
            ),
            "The locus of points equidistant from two fixed points A and B is the:",
            listOf("Circle with centre A","Perpendicular bisector of AB","Line AB only","Angle bisector at A"), 1,
            "Every point on the perpendicular bisector of AB is the same perpendicular distance from A and B."
        ),
        LearningTopic(
            "math-coordinate-geometry", "Coordinate geometry", "MATH",
            "Coordinate geometry uses algebra to describe points and lines on a plane. Coordinates allow lengths, gradients, midpoints and equations of lines to be calculated instead of estimated from a drawing.",
            listOf(
                "Calculate the gradient of a straight line",
                "Find the distance and midpoint between two points",
                "Use equations of straight lines",
                "Determine parallel and perpendicular line relationships",
                "Solve simple coordinate-geometry problems"
            ),
            listOf(
                "Coordinates and gradient" to "A point is written (x,y). The gradient of a line through (x₁,y₁) and (x₂,y₂) is m=(y₂−y₁)/(x₂−x₁), provided x₂≠x₁. A positive gradient rises from left to right; a negative gradient falls. A vertical line has undefined gradient.",
                "Distance and midpoint" to "Distance between two points is d=√((x₂−x₁)²+(y₂−y₁)²), from Pythagoras' theorem. The midpoint is ((x₁+x₂)/2,(y₁+y₂)/2). The midpoint averages the x-coordinates and y-coordinates separately.",
                "Equation of a straight line" to "The slope-intercept form is y=mx+c, where m is gradient and c is the y-intercept. The point-gradient form is y−y₁=m(x−x₁). Substitute a known point and gradient, then simplify to the requested form.",
                "Parallel and perpendicular lines" to "Non-vertical parallel lines have equal gradients. For two non-vertical perpendicular lines, m₁m₂=−1. A horizontal line has gradient 0 and a vertical line has undefined gradient; treat these cases separately rather than applying the product rule blindly.",
                "Worked example" to "Find the gradient through A(2,3) and B(6,11): m=(11−3)/(6−2)=8/4=2. Using y−3=2(x−2) gives y=2x−1. Check by substituting both points.",
                "Coordinate geometry and shapes" to "Use distance formula to test equal sides, midpoint formula to find a diagonal's midpoint, and gradients to test parallel or perpendicular sides. Keep the order of subtraction consistent in the numerator and denominator."
            ),
            "Find the gradient of the line through (2,3) and (6,11).",
            listOf("1", "2", "3", "4"), 1,
            "m=(11−3)/(6−2)=8/4=2."
        ),
        LearningTopic(
            "math-trigonometry", "Trigonometry", "MATH",
            "Trigonometry connects angles and side lengths. It is used in surveying, navigation, construction and the study of periodic motion. Start with right-angled triangles, then extend the ideas to any triangle, bearings and trigonometric graphs.",
            listOf(
                "Use sine, cosine and tangent in right-angled triangles",
                "Solve problems involving angles of elevation and depression",
                "Apply the sine rule and cosine rule",
                "Use exact values and simple trigonometric identities",
                "Solve bearings and simple trigonometric equations"
            ),
            listOf(
                "Sine, cosine and tangent" to "For an acute angle θ in a right-angled triangle: sinθ=opposite/hypotenuse, cosθ=adjacent/hypotenuse and tanθ=opposite/adjacent. Remember SOH-CAH-TOA. Identify the angle first, then label the sides relative to it.",
                "Angles of elevation and depression" to "An angle of elevation is measured upward from the horizontal; an angle of depression is measured downward from the horizontal. Draw a right-angled triangle and identify the horizontal distance and vertical height before choosing a ratio.",
                "Sine rule and cosine rule" to "For any triangle, a/sinA=b/sinB=c/sinC. The cosine rule is a²=b²+c²−2bc cosA, where a is opposite angle A. Use the sine rule when an opposite side-angle pair is known; use the cosine rule when two sides and the included angle or all three sides are known.",
                "Exact values and identities" to "Common exact values include sin30°=½, cos60°=½, sin45°=cos45°=√2/2, and tan45°=1. The identity sin²θ+cos²θ=1 holds for all angles where the functions are defined. Use degree or radian mode as the question requires.",
                "Bearings" to "A bearing is measured clockwise from north and written as a three-digit angle from 000° to 360°. Draw north lines at relevant points and mark the clockwise angle. Bearings are not measured from the east-west horizontal axis.",
                "Worked example" to "In a right-angled triangle, the opposite side to θ is 3 cm and the hypotenuse is 5 cm. sinθ=3/5=0.6, so θ≈36.9°. Use the inverse sine function and ensure the calculator is in degree mode if the angle is required in degrees."
            ),
            "In a right-angled triangle, the opposite side is 3 cm and the hypotenuse is 5 cm. Find sin θ.",
            listOf("3/5", "4/5", "3/4", "5/3"), 0,
            "sinθ = opposite/hypotenuse = 3/5."
        )
    )
}
