package com.kaycee.meritprep

/** Statistics, counting and probability learning block. Draft content pending independent review. */
object MathematicsCourseExtension3 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "math-data-representation", "Representation of data", "MATH",
            "Data become easier to understand when they are organised and displayed clearly. A frequency table shows how often values occur; charts and graphs reveal patterns, comparisons and unusual values. The correct display depends on the type of data and the question being asked.",
            listOf(
                "Organise raw data into frequency tables",
                "Choose suitable bar charts, histograms and pie charts",
                "Calculate class midpoints and use class boundaries",
                "Construct and interpret cumulative-frequency tables",
                "Read information accurately from statistical diagrams"
            ),
            listOf(
                "Raw data and frequency tables" to "Raw data are observations collected before organisation. A frequency table lists each value or class and the number of observations in it. The total frequency is the number of observations. For grouped data, use non-overlapping class intervals that cover the full data range.",
                "Bar charts and histograms" to "A bar chart represents categories or discrete values using separated bars; bar width has no numerical meaning. A histogram represents continuous grouped data using adjoining rectangles. If class widths differ, frequency density = frequency/class width and rectangle area represents frequency.",
                "Pie charts" to "A pie chart represents a whole as 360°. The sector angle for a category is (category frequency/total frequency)×360°. Check that all sector angles add to 360°. For example, 18 observations out of 60 give an angle of 18/60×360°=108°.",
                "Cumulative frequency and ogives" to "Cumulative frequency is the running total of frequencies up to each class boundary. A cumulative-frequency curve (ogive) plots cumulative frequency against the upper class boundary. It can be used to estimate the median, quartiles and percentiles by reading the corresponding cumulative-frequency levels.",
                "Choosing a suitable display" to "Use a bar chart for categories, a histogram for continuous grouped data, a line graph for change over time, and a pie chart for proportions of a whole. Label axes, units and categories. Choose a scale that makes comparisons clear and does not distort the data.",
                "Worked example" to "A group has 60 learners, and 18 choose Physics as their favourite subject. The corresponding pie-chart sector is (18/60)×360°=108°. The remaining categories must account for the remaining 252°."
            ),
            "A category occurs 18 times in a data set of 60 observations. What angle should represent it in a pie chart?",
            listOf("18°", "60°", "108°", "180°"), 2,
            "Sector angle = (18/60)×360° = 108°."
        ),
        LearningTopic(
            "math-measures-location", "Measures of location", "MATH",
            "Measures of location describe a typical or central value in a data set. The mean uses every observation, the median is the middle value after sorting, and the mode is the most frequent value. The best measure depends on the shape of the data and whether extreme values are present.",
            listOf(
                "Calculate mean, median and mode for ungrouped data",
                "Calculate weighted and combined means in simple cases",
                "Estimate the mean from grouped frequency data",
                "Use cumulative frequency to estimate the median and quartiles",
                "Select a suitable measure of location for a data set"
            ),
            listOf(
                "Arithmetic mean" to "For ungrouped data, mean = sum of observations/number of observations. For a frequency table, mean = Σfx/Σf, where x is the value or class midpoint and f is its frequency. The mean uses every value but can be affected by extreme observations.",
                "Median" to "Arrange observations in ascending order. For an odd number n, the median is the value at position (n+1)/2. For an even number, it is the average of the values at positions n/2 and n/2+1. For grouped data, the median is estimated from the cumulative-frequency distribution.",
                "Mode" to "The mode is the value that occurs most frequently. A data set may have one mode, more than one mode or no repeated value. For grouped continuous data, the modal class is the class with the highest frequency density when class widths differ.",
                "Weighted mean" to "When values have different weights, weighted mean = Σwx/Σw. The weights represent the contribution or frequency of each value. Do not use a simple mean when the question gives unequal weights.",
                "Worked example" to "For the values 3, 4, 4, 5 and 9, the mean is (3+4+4+5+9)/5 = 25/5 = 5. The median is 4 and the mode is 4. These measures describe the same data in different ways.",
                "Choosing a measure" to "The median is often more representative than the mean when data are strongly skewed or contain extreme values. The mode is useful for the most common category or value. State which measure you are calculating and show how the data were arranged or weighted."
            ),
            "Find the median of 3, 4, 4, 5 and 9.",
            listOf("3", "4", "5", "9"), 1,
            "The values are already in order and the middle (third) value is 4."
        ),
        LearningTopic(
            "math-measures-dispersion", "Measures of dispersion", "MATH",
            "Two groups can have the same mean but very different spreads. Measures of dispersion describe how far observations are spread around a central value. They help compare consistency and variability rather than just the typical value.",
            listOf(
                "Calculate the range and interquartile range",
                "Find variance and standard deviation for simple data sets",
                "Estimate variance and standard deviation from frequency tables",
                "Interpret dispersion in context",
                "Distinguish population and sample variance conventions"
            ),
            listOf(
                "Range and quartiles" to "Range = maximum−minimum. The lower quartile Q₁ marks approximately the 25th percentile and the upper quartile Q₃ marks approximately the 75th percentile. Interquartile range IQR=Q₃−Q₁ describes the spread of the middle half of the observations.",
                "Variance" to "For a population, variance σ² = Σ(x−mean)²/N. It is the mean of the squared deviations from the mean. For a frequency table, σ² = Σf(x−mean)²/Σf. Some sample-statistics contexts use N−1 in the denominator; follow the convention stated in the question.",
                "Standard deviation" to "Standard deviation is the positive square root of variance. It is expressed in the same unit as the original data, while variance is in squared units. A smaller standard deviation indicates that values are more closely grouped around the mean.",
                "Worked example" to "For the population values 2, 4, 4 and 6, the mean is 4. The squared deviations are 4, 0, 0 and 4. Population variance = (4+0+0+4)/4 = 2, so standard deviation = √2 ≈ 1.41.",
                "Comparing two data sets" to "If two groups have the same mean, the group with the larger standard deviation has greater spread. A range is quick to calculate but depends only on the two extreme values. IQR is less affected by extreme observations.",
                "Common errors" to "Do not forget to square each deviation before summing for variance. Do not take the square root before dividing by the number of observations. State whether you are using population variance or sample variance when the question makes that distinction."
            ),
            "For the population values 2, 4, 4 and 6, what is the variance?",
            listOf("1", "1.5", "2", "4"), 2,
            "The mean is 4. The squared deviations sum to 8; divide by 4 to obtain population variance 2."
        ),
        LearningTopic(
            "math-permutation-combination", "Permutation", "MATH",
            "Counting methods help solve problems where listing every possible arrangement would take too long. A permutation is an arrangement in which order matters. Factorials and the nPr formula provide a systematic way to count ordered selections.",
            listOf(
                "Use factorial notation",
                "Calculate arrangements of distinct objects",
                "Apply nPr to ordered selections",
                "Count arrangements involving repeated objects",
                "Recognise when order matters"
            ),
            listOf(
                "Factorials" to "For a positive integer n, n! = n×(n−1)×…×2×1, and 0! = 1. Factorials count arrangements of distinct objects. For example, 4! = 4×3×2×1 = 24.",
                "Permutations of distinct objects" to "The number of arrangements of n distinct objects in a line is n!. The number of ordered ways to choose r objects from n is nPr = n!/(n−r)!. The first position has n choices, the next n−1, and so on.",
                "Arrangements with repeated objects" to "If n objects include repeated groups of sizes a, b and so on, the number of distinct linear arrangements is n!/(a!b!…). For example, BANANA has six letters with A repeated three times and N twice, so its distinct arrangements are 6!/(3!2!) = 60.",
                "Worked example" to "The number of ways to arrange 3 of 5 different books in a row is 5P3 = 5!/(5−3)! = 5×4×3 = 60. Order matters because changing the order creates a different arrangement.",
                "Restrictions" to "For arrangements with restrictions, place the restricted objects first or use complementary counting. If two objects must stay together, treat them as one block, arrange the block with the other objects, then arrange the objects inside the block.",
                "Recognising permutations" to "Use permutations for ordered roles, positions, codes and rankings. If the question asks only which objects are selected and not their order, use combinations instead."
            ),
            "How many ways can 3 different books be arranged in a row?",
            listOf("3", "6", "9", "12"), 1,
            "There are 3! = 3×2×1 = 6 arrangements."
        ),
        LearningTopic(
            "math-combination", "Combination", "MATH",
            "A combination is a selection in which order does not matter. Choosing three committee members from a group is a combination because the same three people form the same committee regardless of the order in which they are named.",
            listOf(
                "Distinguish combinations from permutations",
                "Calculate nCr using factorials",
                "Use symmetry of combinations",
                "Solve simple selection and committee problems",
                "Combine counting methods with basic probability"
            ),
            listOf(
                "Meaning of a combination" to "A combination selects r objects from n distinct objects without arranging them. The number of combinations is nCr = n!/[r!(n−r)!]. Dividing by r! removes the different orders of the same selected group.",
                "Worked example" to "The number of ways to choose 2 learners from 5 is 5C2 = 5!/[2!3!] = (5×4)/(2×1) = 10. Choosing A then B is the same committee as choosing B then A.",
                "Symmetry property" to "nCr = nC(n−r). Choosing r objects to include is equivalent to choosing n−r objects to leave out. This identity can simplify calculations.",
                "Permutations compared with combinations" to "Use nPr when order matters, such as assigning first, second and third positions. Use nCr when order does not matter, such as selecting a team. For the same n and r, nPr = nCr×r!.",
                "Restrictions and groups" to "For selection problems with restrictions, count the permitted groups carefully. You may split the problem into cases, use complementary counting or choose required members first. Avoid counting the same group more than once.",
                "Combination in probability" to "Combinations are useful when outcomes are groups rather than ordered sequences. For equally likely selections, probability = number of favourable groups/total possible groups, provided every group is equally likely."
            ),
            "How many different pairs can be selected from 5 people?",
            listOf("5", "10", "20", "25"), 1,
            "The number of pairs is 5C2 = 5!/(2!3!) = 10."
        )
    )
}
