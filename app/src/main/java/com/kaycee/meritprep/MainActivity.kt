package com.kaycee.meritprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
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

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun MeritPrepApp() {
    val nav = rememberNavController()
    val currentEntry = nav.currentBackStackEntryAsState().value
    var navigationRevision by remember { mutableIntStateOf(0) }
    var launcherOpen by remember { mutableStateOf(false) }
    LaunchedEffect(currentEntry) { navigationRevision++ }
    val route = currentEntry?.destination?.route.orEmpty()
    val inLesson = route.startsWith("lesson/")
    val isTablet = LocalConfiguration.current.screenWidthDp >= 720
    MeritPrepTheme {
        Scaffold(
            bottomBar = {
                if (!inLesson && !isTablet) {
                    StudioDockedBottomBar(
                        selectedRoute = route,
                        onNavigate = { destination -> nav.navigate(destination) { launchSingleTop = true } },
                        onOpenLauncher = { launcherOpen = true }
                    )
                }
            }
        ) { padding ->
            Row(Modifier.fillMaxSize().padding(padding)) {
                if (isTablet && !inLesson) {
                    NavigationRail {
                        listOf(
                            Triple("Materials", "home", Icons.Default.Home),
                            Triple("Syllabus", "learn", Icons.Default.MenuBook),
                            Triple("Exam Lab", "practice", Icons.Default.Quiz),
                            Triple("Notebook", "mistakes", Icons.Default.Bookmarks),
                            Triple("Profile", "profile", Icons.Default.Person)
                        ).forEach { (label, destination, icon) ->
                            NavigationRailItem(
                                selected = route == destination,
                                onClick = { nav.navigate(destination) { launchSingleTop = true } },
                                icon = { Icon(icon, contentDescription = label) },
                                label = { Text(label) }
                            )
                        }
                    }
                }
                NavHost(
                    navController = nav,
                    startDestination = "home",
                    modifier = Modifier.weight(1f).fillMaxHeight()
                ) {
                    composable("home") { HomeScreen(nav, navigationRevision) }
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
                        LessonScreen(entry.arguments?.getString("topicId").orEmpty(), onBack = { nav.popBackStack() }, onOpenCalculators = { nav.navigate("calculators") })
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
        if (launcherOpen) {
            ModalBottomSheet(onDismissRequest = { launcherOpen = false }) {
                QuickStudyLauncherSheet(
                    onChoose = { destination ->
                        launcherOpen = false
                        nav.navigate(destination) { launchSingleTop = true }
                    }
                )
            }
        }
    }
}

@Composable
private fun StudioDockedBottomBar(
    selectedRoute: String,
    onNavigate: (String) -> Unit,
    onOpenLauncher: () -> Unit
) {
    Surface(tonalElevation = 4.dp, shadowElevation = 8.dp) {
        Row(
            Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            listOf(
                Triple("Materials", "home", Icons.Default.Home),
                Triple("Syllabus", "learn", Icons.Default.MenuBook)
            ).forEach { (label, destination, icon) ->
                NavigationBarItem(
                    selected = selectedRoute == destination,
                    onClick = { onNavigate(destination) },
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) },
                    alwaysShowLabel = true
                )
            }
            FloatingActionButton(
                onClick = onOpenLauncher,
                modifier = Modifier.size(54.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) { Icon(Icons.Default.Bolt, contentDescription = "Quick study launcher") }
            listOf(
                Triple("Exam Lab", "practice", Icons.Default.Quiz),
                Triple("Profile", "profile", Icons.Default.Person)
            ).forEach { (label, destination, icon) ->
                NavigationBarItem(
                    selected = selectedRoute == destination,
                    onClick = { onNavigate(destination) },
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@Composable
private fun QuickStudyLauncherSheet(onChoose: (String) -> Unit) {
    val tools = listOf(
        Triple("Timed CBT", "A focused examination session", "cbt"),
        Triple("Study plan", "Organise your weekly learning", "study-plan"),
        Triple("Recall cards", "Review concepts due for retrieval", "review"),
        Triple("Notebook", "Saved notes and mistakes", "mistakes"),
        Triple("WAEC Biology theory", "Structured answer practice", "waec-theory"),
        Triple("SS3 placement", "Check your starting level", "placement"),
        Triple("Virtual laboratories", "Explore practical investigations", "labs"),
        Triple("Scientific calculators", "Formula and calculation tools", "calculators")
    )
    Column(
        Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("QUICK STUDY", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Choose what you want to work on", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        tools.forEach { (title, description, destination) ->
            Card(
                onClick = { onChoose(destination) },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                ListItem(
                    headlineContent = { Text(title, fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text(description) },
                    leadingContent = { Icon(Icons.Default.PlayArrow, contentDescription = null) }
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(nav: NavHostController, navigationRevision: Int = 0) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    val allTopics = remember { subjects.flatMap { LearningCatalog.forSubject(it.code) } }
    val studyTopics = remember(allTopics) { allTopics.filterNot { it.isSyllabusOutline } }
    val completedCount = remember(navigationRevision, studyTopics) {
        studyTopics.count { database.isLessonCompleted(it.id) }
    }
    val attempts = remember(navigationRevision) { database.getQuestionAttemptCount() }
    val correct = remember(navigationRevision) { database.getCorrectAnswerCount() }
    val dueReviews = remember(navigationRevision) { database.getDueRevisionCards().size }
    val accuracy = if (attempts == 0) 0 else (correct * 100 / attempts).coerceIn(0, 100)
    val progress = if (studyTopics.isEmpty()) 0f else completedCount.toFloat() / studyTopics.size
    val nextTopic = remember(navigationRevision, studyTopics) {
        val recentId = database.getMostRecentlyStudiedTopicId()
        studyTopics.firstOrNull { it.id == recentId && !database.isLessonCompleted(it.id) }
            ?: studyTopics.firstOrNull { !database.isLessonCompleted(it.id) }
    }
    var search by remember { mutableStateOf("") }
    var subjectFilter by remember { mutableStateOf("All") }
    val query = search.trim()
    val searchResults = remember(query, allTopics) {
        if (query.length < 2) emptyList()
        else allTopics.filter { topic ->
            topic.title.contains(query, ignoreCase = true) ||
                topic.id.contains(query, ignoreCase = true) ||
                topic.objectives.any { it.contains(query, ignoreCase = true) } ||
                topic.sections.any { it.first.contains(query, ignoreCase = true) || it.second.contains(query, ignoreCase = true) }
        }.take(6)
    }
    val filteredSubjects = remember(query, subjectFilter) {
        subjects.filter { subject ->
            val categoryMatches = when (subjectFilter) {
                "STEM" -> subject.code in setOf("BIO", "CHEM", "PHY", "MATH")
                "Other" -> subject.code in setOf("ENG", "ECO", "GOV")
                else -> true
            }
            val textMatches = query.isBlank() ||
                subject.name.contains(query, ignoreCase = true) ||
                subject.description.contains(query, ignoreCase = true)
            categoryMatches && textMatches
        }
    }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.weight(1f)) {
                    Text("MERITPREP  /  LEARNING STUDIO", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("Your study space", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                    Text("Learn the concepts, investigate the science and prepare for your exams.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.large) {
                    Icon(Icons.Default.School, contentDescription = "Learning studio", modifier = Modifier.padding(13.dp), tint = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }
        item {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Search subjects, topics or concepts") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (search.isNotBlank()) IconButton(onClick = { search = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear search")
                    }
                }
            )
        }
        if (query.length >= 2) {
            item {
                Text("Matching lessons", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            if (searchResults.isEmpty()) {
                item { Text("No matching topic found. Try a broader term.", color = MaterialTheme.colorScheme.onSurfaceVariant) }
            } else {
                items(searchResults) { topic ->
                    Card(
                        onClick = { nav.navigate("lesson/" + topic.id) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                Text(topic.title, fontWeight = FontWeight.SemiBold)
                                Text(topic.subjectCode + " · " + topic.objectives.size + " learning objectives", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = "Open lesson", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
        if (dueReviews > 0) {
            item {
                Card(
                    onClick = { nav.navigate("review") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = MaterialTheme.colorScheme.onTertiaryContainer)
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                            Text("Active recall is due", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onTertiaryContainer)
                            Text(dueReviews.toString() + " revision cards are ready. Retrieve the idea before you look at the answer.", color = MaterialTheme.colorScheme.onTertiaryContainer, style = MaterialTheme.typography.bodySmall)
                        }
                        Icon(Icons.Default.ChevronRight, contentDescription = "Open recall cards", tint = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                }
            }
        }
        item {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("Continue learning", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    if (nextTopic != null) {
                        Text(nextTopic.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("Resume your most recently studied topic", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Button(onClick = { nav.navigate("lesson/" + nextTopic.id) }, modifier = Modifier.fillMaxWidth()) {
                            Text("Resume lesson")
                        }
                    } else {
                        Text("You have completed all currently mapped lesson topics.", color = MaterialTheme.colorScheme.onPrimaryContainer)
                        OutlinedButton(onClick = { nav.navigate("learn") }, modifier = Modifier.fillMaxWidth()) { Text("Review courses") }
                    }
                }
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Your progress", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(completedCount.toString() + " / " + studyTopics.size, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
                LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
                Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                    DashboardMetric(attempts.toString(), "QUESTIONS", Modifier.weight(1f))
                    DashboardMetric(accuracy.toString() + "%", "ACCURACY", Modifier.weight(1f))
                    DashboardMetric(dueReviews.toString(), "DUE REVIEWS", Modifier.weight(1f))
                }
            }
        }
        item { Text("Quick access", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeQuickAction("Practice", "JAMB and WAEC", "TEST", Modifier.weight(1f)) { nav.navigate("practice") }
                HomeQuickAction("Virtual labs", "Experiments and models", "LAB", Modifier.weight(1f)) { nav.navigate("labs") }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeQuickAction("Revision", "Review key ideas", "REVISE", Modifier.weight(1f)) { nav.navigate("review") }
                HomeQuickAction("Saved lessons", "Return to bookmarks", "SAVED", Modifier.weight(1f)) { nav.navigate("bookmarks") }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeQuickAction("Study plan", "Organise your learning", "PLAN", Modifier.weight(1f)) { nav.navigate("study-plan") }
                HomeQuickAction("Science media", "Simulations and animations", "EXPLORE", Modifier.weight(1f)) { nav.navigate("science-media") }
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Explore subjects", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    listOf("All", "STEM", "Other").forEach { filter ->
                        FilterChip(subjectFilter == filter, { subjectFilter = filter }, { Text(filter) })
                    }
                }
            }
        }
        items(filteredSubjects.chunked(2)) { pair ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                pair.forEach { subject ->
                    HomeSubjectTile(subject, Modifier.weight(1f)) { nav.navigate("subject/" + subject.code) }
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        if (filteredSubjects.isEmpty()) {
            item { Text("No subjects match this search and filter.", color = MaterialTheme.colorScheme.onSurfaceVariant) }
        }
        item {
            Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("TODAY'S LEARNING PLAN", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("A focused study session", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Build understanding, practise retrieval and review what you missed.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MenuBook, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("1. Learn one topic", fontWeight = FontWeight.SemiBold)
                            Text("Continue your next lesson", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        TextButton(onClick = { nextTopic?.let { nav.navigate("lesson/" + it.id) } ?: nav.navigate("learn") }) { Text("Start") }
                    }
                    HorizontalDivider()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Quiz, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("2. Test your understanding", fontWeight = FontWeight.SemiBold)
                            Text("Answer a short practice set", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        TextButton(onClick = { nav.navigate("practice") }) { Text("Practise") }
                    }
                    HorizontalDivider()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Replay, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("3. Retrieve and revise", fontWeight = FontWeight.SemiBold)
                            Text("Review due cards and mistakes", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        TextButton(onClick = { nav.navigate("review") }) { Text("Review") }
                    }
                }
            }
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Find your starting level", fontWeight = FontWeight.Bold)
                    Text("Take a placement check to identify topics that need attention.")
                    OutlinedButton(onClick = { nav.navigate("placement") }, modifier = Modifier.fillMaxWidth()) { Text("Start placement check") }
                }
            }
        }
    }
}

@Composable
private fun HomeSubjectTile(subject: Subject, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val palette = when (subject.code) {
        "BIO" -> listOf(Color(0xFF176B55), Color(0xFF2B9B79))
        "CHEM" -> listOf(Color(0xFF334E91), Color(0xFF5477C5))
        "PHY" -> listOf(Color(0xFF6B477E), Color(0xFF9870B0))
        "MATH" -> listOf(Color(0xFF92532E), Color(0xFFC47B45))
        "ENG" -> listOf(Color(0xFF315C73), Color(0xFF4E8BA7))
        "ECO" -> listOf(Color(0xFF675A2D), Color(0xFF9A8845))
        else -> listOf(Color(0xFF4A526B), Color(0xFF7783A5))
    }
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            Modifier.fillMaxWidth().background(Brush.linearGradient(palette), RoundedCornerShape(18.dp)).padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(subject.code, style = MaterialTheme.typography.labelMedium, color = Color.White.copy(alpha = 0.82f), fontWeight = FontWeight.Bold)
            Text(subject.name, style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold, minLines = 2)
            Text(subject.description, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.9f), maxLines = 2)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Open " + subject.name, tint = Color.White)
            }
        }
    }
}

@Composable
private fun DashboardMetric(value: String, label: String, modifier: Modifier = Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.fillMaxWidth().padding(11.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun HomeQuickAction(title: String, subtitle: String, tag: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
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
    val objectiveCount = topics.sumOf { it.objectives.size }
    val summary = when (subject.code) {
        "BIO" -> "Start with cell foundations, build your understanding of living systems, then connect each topic to practical Biology and examination questions."
        "CHEM" -> "Follow the mapped Chemistry topics from particles and bonding through reactions, quantitative chemistry, organic chemistry and industry. Subject-specialist review remains outstanding."
        "PHY" -> "Build from measurement and motion through fields, waves, electricity and modern physics. The practical workspaces are linked to the relevant lessons."
        "MATH" -> "Build mathematical fluency through the mapped topic sequence, worked methods and timed examination practice. Independent Mathematics-teacher review remains outstanding."
        else -> subject.description
    }
    val actions = when (subject.code) {
        "MATH" -> listOf(
            Triple("Mathematics practice", "115 topic questions", "mathematics-practice"),
            Triple("Timed JAMB CBT", "40 questions", "mathematics-cbt")
        )
        "PHY" -> listOf(
            Triple("Physics practice", "195 topic questions", "physics-practice"),
            Triple("WAEC practical", "Graphs and data analysis", "physics-waec-practical"),
            Triple("WAEC theory", "Structured answers", "physics-waec-theory"),
            Triple("WAEC objective", "50-question practice", "physics-waec-objective"),
            Triple("Timed JAMB CBT", "40 questions", "physics-cbt")
        )
        "CHEM" -> listOf(
            Triple("Chemistry practice", "90 topic questions", "chemistry-practice"),
            Triple("WAEC theory", "Structured answers", "chemistry-waec-theory"),
            Triple("WAEC objective", "30-question practice", "chemistry-waec-objective"),
            Triple("Qualitative analysis", "Anions, cations and gases", "chemistry-qualitative"),
            Triple("Redox titration", "Iron(II) calculations", "chemistry-redox-titration")
        )
        "BIO" -> listOf(
            Triple("JAMB practice", "Biology topic questions", "cbt"),
            Triple("WAEC theory", "Structured responses", "waec-theory"),
            Triple("WAEC objective", "Objective practice", "waec-objective"),
            Triple("Virtual laboratories", "Practical investigations", "labs")
        )
        else -> listOf(
            Triple("JAMB practice", "Topic questions", "cbt"),
            Triple("WAEC theory", "Structured responses", "waec-theory"),
            Triple("WAEC objective", "Objective practice", "waec-objective")
        )
    }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large) {
                        Text(subject.code, Modifier.padding(horizontal = 13.dp, vertical = 12.dp), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(subject.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text(summary, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                SubjectMetric(topics.size.toString(), "TOPICS", Modifier.weight(1f))
                SubjectMetric(objectiveCount.toString(), "LEARNING OBJECTIVES", Modifier.weight(1f))
            }
        }
        item { Text("Study tools", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
        items(actions.chunked(2)) { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                pair.forEach { action ->
                    HomeQuickAction(action.first, action.second, "OPEN", Modifier.weight(1f)) { nav.navigate(action.third) }
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text("Topic sequence", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Start at the beginning or open a topic you need to revise. Each lesson has its own examples, visualisations, scenarios, practical work and assessment.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        if (topics.isEmpty()) {
            item { Text("Lessons are being prepared for this subject.") }
        } else {
            items(topics) { topic ->
                Card(
                    onClick = { nav.navigate("lesson/" + topic.id) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                            Text((topics.indexOf(topic) + 1).toString().padStart(2, '0'), Modifier.padding(horizontal = 10.dp, vertical = 9.dp), color = MaterialTheme.colorScheme.onSecondaryContainer, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(topic.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                            Text(topic.objectives.size.toString() + " learning objectives · " + topic.sections.size + " teaching sections", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Icon(Icons.Default.ChevronRight, contentDescription = "Open lesson", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectMetric(value: String, label: String, modifier: Modifier = Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
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
