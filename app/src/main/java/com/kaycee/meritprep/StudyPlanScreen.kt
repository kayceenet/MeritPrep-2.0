package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaycee.meritprep.data.MeritDatabase
import com.kaycee.meritprep.data.StudyPlanGenerator
import com.kaycee.meritprep.data.StudyPlanItem
import com.kaycee.meritprep.data.StudyPlanSnapshot
import com.kaycee.meritprep.data.StudyTopic
import java.util.UUID

@Composable
fun StudyPlanScreen(
    onOpenLesson: (String) -> Unit = {},
    onOpenPractice: () -> Unit = {},
    onOpenRevision: () -> Unit = {}
) {
    val context = LocalContext.current
    val database = remember(context) { MeritDatabase(context) }
    var plan by remember { mutableStateOf<StudyPlanSnapshot?>(null) }
    var dailyMinutes by remember { mutableIntStateOf(120) }
    var restoring by remember { mutableStateOf(true) }

    LaunchedEffect(database) {
        plan = database.getActiveStudyPlan()
        if (plan != null) dailyMinutes = plan!!.minutesPerDay
        restoring = false
    }

    fun generatePlan() {
        val topics = BiologyCurriculum.units.mapNotNull { unit ->
            unit.lessonId?.let { StudyTopic(it, unit.title) }
        }
        val activities = StudyPlanGenerator.buildAdaptive(
            topics = topics,
            progress = database.getAllTopicProgress(),
            minutesPerDay = dailyMinutes,
            days = 7
        )
        val planId = UUID.randomUUID().toString()
        val now = System.currentTimeMillis()
        val items = activities.map { activity ->
            StudyPlanItem(
                itemId = UUID.randomUUID().toString(),
                dayIndex = activity.dayIndex,
                topicId = activity.topicId,
                topicTitle = activity.topicTitle,
                activityType = activity.activityType,
                estimatedMinutes = activity.estimatedMinutes,
                isCompleted = false
            )
        }
        database.createStudyPlan(planId, now, dailyMinutes, items)
        plan = database.getActiveStudyPlan()
    }

    if (restoring) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val currentPlan = plan
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Biology study planner", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("A practical seven-day schedule built from the 24 Biology syllabus units. Your plan and completion ticks are stored offline.")
        Text("New plans prioritise topics with weaker recent results, incomplete lessons and longer gaps since study. Topics with no attempts are treated as not yet evidenced, not as mastered.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        if (currentPlan == null) {
            Text("Choose a daily study target", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf(60, 90, 120, 150).forEach { minutes ->
                    FilterChip(
                        selected = dailyMinutes == minutes,
                        onClick = { dailyMinutes = minutes },
                        label = { Text("$minutes min") }
                    )
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("What the plan includes", fontWeight = FontWeight.Bold)
                    Text("Each scheduled topic has a lesson task (20 min), a short practice task (5 min) and a flashcard review (5 min).")
                    Text("When you generate a plan, MeritPrep uses your locally recorded study evidence to bring weaker or less recently studied topics forward.")
                    Text("A 120-minute target schedules all 24 topics across six study days, leaving the seventh day for rest or catch-up.")
                }
            }
            Button(onClick = { generatePlan() }, modifier = Modifier.fillMaxWidth()) {
                Text("Generate my 7-day plan")
            }
        } else {
            val completed = currentPlan.items.count { it.isCompleted }
            val total = currentPlan.items.size
            val topicsScheduled = currentPlan.items.map { it.topicId }.toSet().size
            Text("Your current plan", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("$topicsScheduled of 24 syllabus topics scheduled · ${currentPlan.minutesPerDay} minutes per day")
            LinearProgressIndicator(
                progress = { if (total == 0) 0f else completed.toFloat() / total },
                modifier = Modifier.fillMaxWidth()
            )
            Text("$completed of $total activities completed", style = MaterialTheme.typography.labelLarge)
            OutlinedButton(onClick = { dailyMinutes = currentPlan.minutesPerDay; generatePlan() }, modifier = Modifier.fillMaxWidth()) {
                Text("Generate a new plan")
            }
            (0..6).forEach { day ->
                val dayItems = currentPlan.items.filter { it.dayIndex == day }
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Day ${day + 1}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        if (dayItems.isEmpty()) {
                            Text("Rest or catch-up day. Use this time to revisit difficult ideas without rushing.")
                        } else {
                            dayItems.forEach { item ->
                                Row(verticalAlignment = androidx.compose.ui.Alignment.Top) {
                                    Checkbox(
                                        checked = item.isCompleted,
                                        onCheckedChange = { checked ->
                                            database.setStudyPlanItemCompleted(item.itemId, checked)
                                            plan = currentPlan.copy(
                                                items = currentPlan.items.map {
                                                    if (it.itemId == item.itemId) it.copy(isCompleted = checked) else it
                                                }
                                            )
                                        }
                                    )
                                    Column(Modifier.weight(1f).padding(top = 10.dp)) {
                                        Text(item.topicTitle, fontWeight = FontWeight.SemiBold)
                                        Text("${item.activityType} · ${item.estimatedMinutes} min", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                    TextButton(
                                        onClick = {
                                            when (item.activityType) {
                                                "Learn lesson" -> onOpenLesson(item.topicId)
                                                "Practise questions" -> onOpenPractice()
                                                else -> onOpenRevision()
                                            }
                                        }
                                    ) { Text("Open") }
                                }
                            }
                        }
                    }
                }
            }
            Text("Completing a planned activity records your study progress; it does not by itself demonstrate topic mastery.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
