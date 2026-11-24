package com.kaycee.meritprep

import com.kaycee.meritprep.data.AssessmentQuestion

/**
 * Original MeritPrep Physics practice items mapped to developed JAMB syllabus topics.
 * These are not official JAMB or WAEC past questions.
 */
object PhysicsQuestionBank {
    private fun q(id: String, topic: String, prompt: String, options: List<String>, answer: Int, explanation: String) =
        AssessmentQuestion(id, topic, prompt, options, answer, explanation, "MeritPrep original Physics practice · $topic", false)

    val all: List<AssessmentQuestion> = listOf(
        q("phy-meas-001","phy-measurements","Which SI base unit is used for electric current?",listOf("Volt","Ampere","Ohm","Coulomb"),1,"The ampere (A) is the SI base unit for electric current."),
        q("phy-meas-002","phy-measurements","Which instrument is most suitable for measuring the diameter of a thin wire?",listOf("Metre rule","Micrometer screw gauge","Stopwatch","Beam balance"),1,"A micrometer screw gauge has a fine screw mechanism and is designed for small thicknesses and diameters."),
        q("phy-meas-003","phy-measurements","What is the SI unit of pressure?",listOf("Newton","Joule","Pascal","Watt"),2,"Pressure is force per unit area; its SI unit is N m⁻², called the pascal (Pa)."),
        q("phy-meas-004","phy-measurements","Which is the correct standard form of 0.00056?",listOf("5.6 × 10⁻⁴","56 × 10⁻⁴","5.6 × 10⁴","0.56 × 10⁻³"),0,"Move the decimal point four places to the right to obtain 5.6, so the power is 10⁻⁴."),
        q("phy-meas-005","phy-measurements","The dimensions of acceleration are:",listOf("[LT⁻¹]","[LT⁻²]","[MLT⁻²]","[ML⁻¹T⁻²]"),1,"Acceleration is change in velocity per time: (LT⁻¹)/T = LT⁻²."),
        q("phy-vectors-001","phy-vectors","Which of the following is a vector quantity?",listOf("Mass","Time","Temperature","Displacement"),3,"Displacement has magnitude and direction, so it is a vector."),
        q("phy-vectors-002","phy-vectors","A student walks 3 m east and 4 m north. What is the magnitude of the displacement?",listOf("1 m","5 m","7 m","12 m"),1,"The perpendicular components give R = √(3² + 4²) = 5 m."),
        q("phy-vectors-003","phy-vectors","A 10 N force acts at 60° to the horizontal. What is its horizontal component?",listOf("5 N","8.66 N","10 N","20 N"),0,"Fₓ = F cos θ = 10 cos 60° = 5 N."),
        q("phy-vectors-004","phy-vectors","Which statement about distance and displacement is correct?",listOf("Both are vectors","Distance is scalar; displacement is vector","Distance is always less than displacement","Displacement is the total path length"),1,"Distance is total path length and has magnitude only; displacement is the vector change in position."),
        q("phy-vectors-005","phy-vectors","Two perpendicular vectors have magnitudes 6 units and 8 units. What is their resultant magnitude?",listOf("2 units","10 units","14 units","48 units"),1,"R = √(6² + 8²) = √100 = 10 units."),
        q("phy-motion-001","phy-motion","A car starts from rest and accelerates uniformly at 2 m s⁻² for 5 s. What is its final speed?",listOf("2 m s⁻¹","5 m s⁻¹","10 m s⁻¹","25 m s⁻¹"),2,"v = u + at = 0 + (2 × 5) = 10 m s⁻¹."),
        q("phy-motion-002","phy-motion","The gradient of a displacement-time graph represents:",listOf("Acceleration","Velocity","Force","Momentum"),1,"Gradient is change in displacement divided by time, which is velocity."),
        q("phy-motion-003","phy-motion","The area under a velocity-time graph represents:",listOf("Displacement","Acceleration","Jerk","Mass"),0,"The signed area is velocity multiplied by time, giving displacement."),
        q("phy-motion-004","phy-motion","A 2 kg body accelerates at 3 m s⁻². What resultant force acts on it?",listOf("1.5 N","5 N","6 N","9 N"),2,"Newton's second law gives F = ma = 2 × 3 = 6 N."),
        q("phy-motion-005","phy-motion","A body has mass 4 kg and velocity 5 m s⁻¹. Its momentum is:",listOf("0.8 kg m s⁻¹","9 kg m s⁻¹","20 kg m s⁻¹","25 kg m s⁻¹"),2,"Momentum p = mv = 4 × 5 = 20 kg m s⁻¹."),
        q("phy-gravity-001","phy-gravity","The weight of a 5 kg object where g = 10 N kg⁻¹ is:",listOf("0.5 N","5 N","50 N","500 N"),2,"Weight W = mg = 5 × 10 = 50 N."),
        q("phy-gravity-002","phy-gravity","Which quantity remains approximately the same when a person travels from Earth to the Moon?",listOf("Weight","Mass","Gravitational field strength","Gravitational force"),1,"Mass is a measure of inertia and does not depend on location; weight changes with gravitational field strength."),
        q("phy-gravity-003","phy-gravity","The gravitational force between two point masses is inversely proportional to:",listOf("Their separation","The square of their separation","The product of their masses","The sum of their masses"),1,"Newton's law gives F = Gm₁m₂/r², so force varies inversely as r squared."),
        q("phy-gravity-004","phy-gravity","The SI unit of gravitational field strength is:",listOf("kg","N kg⁻¹","J kg","N m"),1,"Field strength is force per unit mass, measured in N kg⁻¹."),
        q("phy-gravity-005","phy-gravity","An object weighs 80 N where g = 10 N kg⁻¹. What is its mass?",listOf("0.125 kg","8 kg","70 kg","800 kg"),1,"m = W/g = 80/10 = 8 kg."),
        q("phy-equilibrium-001","phy-equilibrium","For a particle to be in translational equilibrium, the resultant force must be:",listOf("Maximum","Zero","Equal to its weight only","Constant and non-zero"),1,"Equilibrium requires the vector sum of forces to be zero."),
        q("phy-equilibrium-002","phy-equilibrium","The moment of a force about a pivot equals:",listOf("Force × perpendicular distance","Mass × acceleration","Force ÷ area","Work ÷ time"),0,"Moment = force multiplied by the perpendicular distance from the pivot to the line of action."),
        q("phy-equilibrium-003","phy-equilibrium","A 10 N force acts perpendicular to a spanner 0.2 m from its pivot. What is the moment?",listOf("0.02 N m","2 N m","10.2 N m","50 N m"),1,"Moment = 10 × 0.2 = 2 N m."),
        q("phy-equilibrium-004","phy-equilibrium","A body is in rotational equilibrium when:",listOf("Clockwise moments exceed anticlockwise moments","Clockwise and anticlockwise moments balance","Its weight is zero","No forces act on it"),1,"The algebraic sum of moments about a point is zero."),
        q("phy-equilibrium-005","phy-equilibrium","Lami's theorem applies to a particle in equilibrium under:",listOf("Two parallel forces","Exactly three coplanar concurrent forces","Four non-concurrent forces","Any number of forces"),1,"Lami's theorem is for exactly three coplanar, concurrent and non-parallel forces in equilibrium."),
        q("phy-work-energy-001","phy-work-energy","A 20 N force moves a box 3 m in the same direction as the force. What work is done?",listOf("6 J","17 J","23 J","60 J"),3,"W = Fs cos 0° = 20 × 3 = 60 J."),
        q("phy-work-energy-002","phy-work-energy","The kinetic energy of a body is given by:",listOf("mgh","½mv²","F/t","mv"),1,"Kinetic energy is one-half mass multiplied by speed squared."),
        q("phy-work-energy-003","phy-work-energy","A machine transfers 600 J of useful energy from an input of 800 J. Its efficiency is:",listOf("25%","60%","75%","133%"),2,"Efficiency = (600/800) × 100% = 75%."),
        q("phy-work-energy-004","phy-work-energy","The SI unit of power is:",listOf("Joule","Watt","Newton","Pascal"),1,"Power is energy transferred per unit time; its SI unit is the watt."),
        q("phy-work-energy-005","phy-work-energy","The area under a force-displacement graph gives:",listOf("Power","Momentum","Work done","Acceleration"),2,"Work is the integral of force with respect to displacement; graphically it is the signed area."),
        q("phy-friction-001","phy-friction","Friction acts to oppose:",listOf("Only gravity","Relative motion or its tendency between surfaces","All forces in a body","Mass"),1,"Friction opposes relative sliding or the tendency of sliding between surfaces in contact."),
        q("phy-friction-002","phy-friction","The coefficient of friction is:",listOf("Measured in newtons","Measured in joules","Dimensionless","Measured in metres"),2,"It is a ratio of frictional force to normal reaction, so units cancel."),
        q("phy-friction-003","phy-friction","Limiting friction is the:",listOf("Friction when surfaces are far apart","Maximum static friction just before sliding begins","Friction in a vacuum","Friction that always acts during motion"),1,"Limiting friction is the maximum value of static friction before relative sliding starts."),
        q("phy-friction-004","phy-friction","Which action commonly reduces friction in a machine?",listOf("Adding a suitable lubricant","Increasing roughness","Removing bearings","Increasing unnecessary contact"),0,"A lubricant separates surfaces and reduces direct contact and friction."),
        q("phy-friction-005","phy-friction","A falling sphere reaches terminal velocity when:",listOf("Its weight becomes zero","Its acceleration becomes zero because resultant force is zero","It stops moving","Viscous drag becomes zero"),1,"At terminal velocity, drag plus upthrust balance weight, so resultant force and acceleration are zero."),
        q("phy-machines-001","phy-machines","Mechanical advantage is defined as:",listOf("Effort/load","Load/effort","Distance/load","Work/time"),1,"Mechanical advantage = load divided by effort."),
        q("phy-machines-002","phy-machines","A machine has MA = 3 and VR = 4. Its efficiency is:",listOf("25%","50%","75%","133%"),2,"Efficiency = (MA/VR) × 100% = 75%."),
        q("phy-machines-003","phy-machines","An ideal machine has efficiency:",listOf("0%","50%","75%","100%"),3,"An ideal machine has no energy losses, so output work equals input work."),
        q("phy-machines-004","phy-machines","A pulley system is a simple machine mainly used to:",listOf("Change force direction or provide mechanical advantage","Create energy","Remove gravity","Increase the mass of a load"),0,"Pulley arrangements can change the direction of effort and provide force multiplication."),
        q("phy-machines-005","phy-machines","If a machine lifts a 200 N load using a 50 N effort, its mechanical advantage is:",listOf("0.25","2","4","250"),2,"MA = load/effort = 200/50 = 4.")
    )

    fun forTopic(topicId: String): List<AssessmentQuestion> = all.filter { it.topicId == topicId }
}
