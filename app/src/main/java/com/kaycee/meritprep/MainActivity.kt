package com.kaycee.meritprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.kaycee.meritprep.data.MeritDatabase

private val subjects = listOf(
    Subject("Mathematics", "Numbers, algebra, geometry and statistics", "MATH"),
    Subject("English Language", "Comprehension, lexis, grammar and oral English", "ENG"),
    Subject("Biology", "Living organisms, ecology and genetics", "BIO"),
    Subject("Chemistry", "Matter, reactions and quantitative chemistry", "CHEM"),
    Subject("Physics", "Mechanics, waves, electricity and modern physics", "PHY"),
    Subject("Economics", "Microeconomics, macroeconomics and development", "ECO"),
    Subject("Government", "Political concepts and Nigerian government", "GOV")
)
private data class Subject(val name:String,val description:String,val code:String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MeritPrepApp() }
    }
}

@Composable
private fun MeritPrepApp() {
    val nav = rememberNavController()
    val currentEntry = nav.currentBackStackEntryAsState().value
    var navigationRevision by remember { mutableIntStateOf(0) }
    LaunchedEffect(currentEntry) { navigationRevision++ }
    MeritPrepTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    listOf("Home" to "home", "Learn" to "learn", "Practice" to "practice", "Labs" to "labs", "Profile" to "profile").forEach { (label, route) ->
                        NavigationBarItem(
                            selected = currentEntry?.destination?.route == route,
                            onClick = { nav.navigate(route) { launchSingleTop = true } },
                            icon = { Icon(when(route) { "home" -> Icons.Default.Home; "learn" -> Icons.Default.MenuBook; "practice" -> Icons.Default.Quiz; "labs" -> Icons.Default.Science; else -> Icons.Default.Person }, contentDescription = label) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(navController = nav, startDestination = "home", modifier = Modifier.padding(padding)) {
                composable("home") { HomeScreen(nav) }
                composable("learn") { LearnScreen(nav) }
                composable("subject/{code}") { entry ->
                    val code = entry.arguments?.getString("code").orEmpty()
                    if (code == "BIO") {
                        BiologyCourseScreen(onOpenLesson = { topicId -> nav.navigate("lesson/$topicId") }, onOpenGlossary = { nav.navigate("glossary") })
                    } else {
                        SubjectScreen(subjects.firstOrNull { it.code == code } ?: subjects.first(), nav)
                    }
                }
                composable("lesson/{topicId}") { entry ->
                    LessonScreen(entry.arguments?.getString("topicId").orEmpty())
                }
                composable("practice") { PracticeScreenV2(onStartCbt = { nav.navigate("cbt") }, onStartWaecObjective = { nav.navigate("waec-objective") }, onStartTheory = { nav.navigate("waec-theory") }, onOpenMistakes = { nav.navigate("mistakes") }, onOpenStudyPlan = { nav.navigate("study-plan") }) }
                composable("chemistry-practice") { ChemistryPracticeScreen() }
                composable("physics-practice") { PhysicsPracticeScreen() }
                composable("mathematics-practice") { MathematicsPracticeScreen() }
                composable("mathematics-cbt") { CbtScreen(CbtMode.MATHEMATICS_UTME, MathematicsQuestionBank.all) }
                composable("physics-waec-practical") { PhysicsWaecPracticalScreen() }
                composable("physics-waec-theory") { PhysicsWaecTheoryScreen() }
                composable("physics-waec-objective") { CbtScreen(CbtMode.WAEC_PHYSICS_OBJECTIVE, PhysicsWaecObjectiveBank.all) }
                composable("chemistry-waec-theory") { ChemistryWaecTheoryScreen() }
                composable("chemistry-waec-objective") { ChemistryWaecObjectiveScreen() }
                composable("chemistry-qualitative") { ChemistryQualitativeGuideScreen() }
                composable("chemistry-redox-titration") { ChemistryRedoxTitrationScreen() }
                composable("placement") { PlacementAssessmentScreen(onOpenLesson = { nav.navigate("lesson/$it") }) }
                composable("cbt") { CbtScreen(CbtMode.JAMB_UTME) }
                composable("waec-objective") { CbtScreen(CbtMode.WAEC_OBJECTIVE) }
                composable("physics-cbt") { CbtScreen(CbtMode.PHYSICS_UTME, PhysicsQuestionBank.all) }
                composable("waec-theory") { BiologyTheoryScreen() }
                composable("review") { RevisionScreen() }
                composable("mistakes") { MistakeNotebookScreen() }
                composable("study-plan") { StudyPlanScreen(onOpenLesson = { nav.navigate("lesson/$it") }, onOpenPractice = { nav.navigate("practice") }, onOpenRevision = { nav.navigate("review") }) }
                composable("glossary") { BiologyGlossaryScreen(onOpenLesson = { nav.navigate("lesson/$it") }) }
                composable("bookmarks") { BookmarksScreen(onOpenLesson = { nav.navigate("lesson/$it") }) }
                composable("labs") { VirtualLabScreen() }
                composable("science-media") { ScienceMediaScreen(onOpenLabs = { nav.navigate("labs") }, onOpenLesson = { nav.navigate("lesson/$it") }) }
                composable("calculators") { CalculatorScreen() }
                composable("profile") { ProfileScreen(navigationRevision) }
            }
        }
    }
}

@Composable
private fun HomeScreen(nav: NavHostController) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("MERITPREP  /  LEARNING STUDIO", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text("Learn with clarity.\nPrepare with confidence.", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Text("Build understanding from the foundations, explore scientific ideas and practise for your examinations.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("Your learning workspace", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text("Continue with a subject, enter a virtual laboratory or test what you already know.", color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { nav.navigate("learn") }, modifier = Modifier.weight(1f)) { Text("Explore lessons") }
                        OutlinedButton(onClick = { nav.navigate("labs") }, modifier = Modifier.weight(1f)) { Text("Open labs") }
                    }
                }
            }
        }
        item { Text("Quick access", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeQuickAction("Practice", "JAMB and WAEC", "TEST") { nav.navigate("practice") }
                HomeQuickAction("Study plan", "Organise your work", "PLAN") { nav.navigate("study-plan") }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeQuickAction("Revision", "Review key ideas", "REVISE") { nav.navigate("review") }
                HomeQuickAction("Saved lessons", "Return to bookmarks", "SAVED") { nav.navigate("bookmarks") }
            }
        }
        item { Text("Choose a subject", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
        items(subjects) { subject -> SubjectCard(subject) { nav.navigate("subject/${subject.code}") } }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Need a starting point?", fontWeight = FontWeight.Bold)
                    Text("Take a placement check to identify topics that need attention.")
                    OutlinedButton(onClick = { nav.navigate("placement") }, modifier = Modifier.fillMaxWidth()) { Text("Start placement check") }
                }
            }
        }
    }
}

@Composable
private fun HomeQuickAction(title: String, subtitle: String, tag: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.weight(1f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.small) {
                Text(tag, Modifier.padding(horizontal = 8.dp, vertical = 5.dp), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSecondaryContainer, fontWeight = FontWeight.Bold)
            }
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun LearnScreen(nav: NavHostController) {
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("LEARNING LIBRARY", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Choose your subject", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Open a course to follow its topics and lessons.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        items(subjects) { subject -> SubjectCard(subject) { nav.navigate("subject/${subject.code}") } }
    }
}

@Composable
private fun SubjectCard(subject: Subject, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.large) {
                Text(subject.code, Modifier.padding(horizontal = 12.dp, vertical = 11.dp), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondaryContainer)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(subject.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(subject.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "Open subject", tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun SubjectScreen(subject: Subject, nav: NavHostController) {
    val topics = LearningCatalog.forSubject(subject.code)
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(subject.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(if (subject.code == "CHEM") "18 JAMB Chemistry topics are mapped from the IBASS syllabus. All 18 JAMB topics now have beginner-oriented lesson drafts and 90 original practice questions. Content still requires independent subject-teacher review and separate WAEC alignment." else "Start with a structured lesson. This is an initial content set, not yet a complete official syllabus map.")
        }
        if (subject.code == "MATH") {
            item {
                Text("JAMB Mathematics map: 23 numbered topics from the IBASS syllabus. All twenty-three topics now have beginner-focused lesson drafts and 115 original practice questions. Independent Mathematics-teacher review is outstanding.")
            }
            item {
                Button(onClick = { nav.navigate("mathematics-practice") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Practise Mathematics · 115 questions")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("mathematics-cbt") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Timed Mathematics JAMB-style CBT · 40 questions")
                }
            }
        }
        if (subject.code == "PHY") {
            item {
                Text("JAMB Physics map: 39 numbered topics from the IBASS syllabus. All 39 topics now have beginner-focused lesson drafts and 195 original practice questions. Independent Physics-teacher review is still outstanding.")
            }
            item {
                Button(onClick = { nav.navigate("physics-practice") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Practise Physics · 195 questions")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("physics-waec-practical") }, modifier = Modifier.fillMaxWidth()) {
                    Text("WAEC Physics practical · graph and data analysis")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("physics-waec-theory") }, modifier = Modifier.fillMaxWidth()) {
                    Text("WAEC Physics theory · structured answers")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("physics-waec-objective") }, modifier = Modifier.fillMaxWidth()) {
                    Text("WAEC Physics objective · 50-question practice")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("physics-cbt") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Start timed Physics CBT · 40 questions")
                }
            }
        }
        if (subject.code == "CHEM") {
            item {
                Button(onClick = { nav.navigate("chemistry-practice") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Practise Chemistry · 90 questions")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("chemistry-waec-theory") }, modifier = Modifier.fillMaxWidth()) {
                    Text("WAEC Chemistry theory practice")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("chemistry-waec-objective") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Timed WAEC Chemistry objective · 30 questions")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("chemistry-qualitative") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Qualitative analysis · anions and gases")
                }
            }
            item {
                OutlinedButton(onClick = { nav.navigate("chemistry-redox-titration") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Redox titration · iron(II) calculation")
                }
            }
        }
        if (topics.isEmpty()) {
            item { Text("Lessons are being prepared for this subject.") }
        }
        items(topics) { topic ->
            Card(onClick = { nav.navigate("lesson/" + topic.id) }, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(topic.title, fontWeight = FontWeight.SemiBold)
                        Text("Lesson · " + topic.objectives.size + " learning objectives", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = "Open lesson")
                }
            }
        }
    }
}

@Composable
private fun PracticeScreen() {
    var selected by remember { mutableIntStateOf(-1) }
    var submitted by remember { mutableStateOf(false) }
    val options = listOf("Mitochondrion", "Ribosome", "Cell wall", "Nucleus")
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Quick practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Biology · Cell biology")
        Text("Which cell organelle is the principal site of aerobic respiration?")
        options.forEachIndexed { index, option ->
            OutlinedButton(onClick = { selected = index; submitted = false }, modifier = Modifier.fillMaxWidth()) {
                Text((if(selected == index) "✓  " else "") + option)
            }
        }
        Button(onClick = { submitted = true }, enabled = selected >= 0) { Text("Check answer") }
        if(submitted) {
            val correct = selected == 0
            Text(if(correct) "Correct. The mitochondrion is the main site of aerobic respiration in eukaryotic cells." else "Not quite. Review the function of the mitochondrion, then try again.", color = if(correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
        }
        Text("Demonstration question only. Production question banks require source verification and academic review.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun ProfileScreen(refreshKey: Int = 0) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val completedLessons = remember(refreshKey) { database.getCompletedLessonCount() }
    val attemptedQuestions = remember(refreshKey) { database.getQuestionAttemptCount() }
    val correctAnswers = remember(refreshKey) { database.getCorrectAnswerCount() }
    val topicProgress = remember(refreshKey) { database.getAllTopicProgress() }
    val unresolvedMistakes = remember(refreshKey) { database.getUnresolvedMistakeCount() }
    val accuracy = if (attemptedQuestions == 0) 0 else (correctAnswers * 100) / attemptedQuestions

    LazyColumn(
        Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Learner profile", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Your learning activity and practice evidence are stored on this device.")
        }
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Lessons completed", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(completedLessons.toString(), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Mistakes to review", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(unresolvedMistakes.toString(), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Questions attempted", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(attemptedQuestions.toString(), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Practice accuracy", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("$accuracy%", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                    LinearProgressIndicator(progress = { accuracy / 100f }, modifier = Modifier.fillMaxWidth())
                    Text("$correctAnswers correct answers out of $attemptedQuestions attempts", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        item {
            Text("Topic-level practice evidence", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Use this to identify topics that need more practice. It is not a mastery score.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (topicProgress.isEmpty()) {
            item {
                Text("No topic-level activity yet. Complete a lesson or answer practice questions to build your progress record.")
            }
        } else {
            items(topicProgress, key = { it.topicId }) { progress ->
                val title = LearningCatalog.find(progress.topicId)?.title ?: progress.topicId
                val topicAccuracy = if (progress.questionsAttempted == 0) 0
                    else (progress.questionsCorrect * 100) / progress.questionsAttempted
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(title, fontWeight = FontWeight.SemiBold)
                        Text(
                            "${progress.questionsCorrect} correct / ${progress.questionsAttempted} attempted · ${progress.lessonsCompleted} lesson(s) completed",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        LinearProgressIndicator(progress = { topicAccuracy / 100f }, modifier = Modifier.fillMaxWidth())
                        Text("$topicAccuracy% practice accuracy · " + if (progress.demonstratedMastery) "mastery evidence recorded" else "mastery not yet demonstrated", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
        item {
            Text(
                "Accuracy is based only on recorded practice attempts. It is not an examination score or a prediction of JAMB/WAEC performance.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
