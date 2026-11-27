package com.kaycee.meritprep

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Process
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Local-only crash capture. No crash data leaves the device unless the user shares it.
 * The previous Android uncaught-exception handler is always called after persistence.
 */
object CrashReporter {
    private const val REPORT_FILE = "meritprep-last-crash.txt"
    private const val MAX_REPORT_CHARS = 80_000

    fun install(context: Context) {
        val appContext = context.applicationContext
        val previousHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, error ->
            try {
                save(appContext, thread, error)
            } catch (_: Throwable) {
                // Never allow diagnostic failure to hide the original application crash.
            }

            if (previousHandler != null) {
                previousHandler.uncaughtException(thread, error)
            } else {
                Process.killProcess(Process.myPid())
                kotlin.system.exitProcess(10)
            }
        }
    }

    private fun save(context: Context, thread: Thread, error: Throwable) {
        val version = try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            info.versionName ?: "unknown"
        } catch (_: Exception) {
            "unknown"
        }

        val stack = StringWriter().also { writer ->
            error.printStackTrace(PrintWriter(writer))
        }.toString()

        val report = buildString {
            appendLine("MERITPREP CRASH REPORT")
            appendLine("Time: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z", Locale.US).format(Date())}")
            appendLine("App: $version (${context.packageName})")
            appendLine("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
            appendLine("Android: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
            appendLine("Thread: ${thread.name}")
            appendLine()
            appendLine("EXCEPTION")
            appendLine(stack)
        }.take(MAX_REPORT_CHARS)

        val destination = File(context.filesDir, REPORT_FILE)
        val temporary = File(context.filesDir, "$REPORT_FILE.tmp")
        FileOutputStream(temporary).use { stream ->
            stream.write(report.toByteArray(Charsets.UTF_8))
            stream.fd.sync()
        }
        if (!temporary.renameTo(destination)) {
            temporary.copyTo(destination, overwrite = true)
            temporary.delete()
        }
    }

    fun read(context: Context): String? {
        val file = File(context.applicationContext.filesDir, REPORT_FILE)
        return try {
            if (file.isFile) file.readText(Charsets.UTF_8) else null
        } catch (_: Exception) {
            null
        }
    }

    fun clear(context: Context) {
        try {
            File(context.applicationContext.filesDir, REPORT_FILE).delete()
        } catch (_: Exception) {
            // A stale report is harmless and can be cleared on a later launch.
        }
    }
}

@Composable
fun CrashReportPrompt() {
    val context = LocalContext.current
    var report by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        report = CrashReporter.read(context)
    }

    val crashReport = report ?: return

    AlertDialog(
        onDismissRequest = { },
        title = { Text("MeritPrep recovered from a crash") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "A previous session ended unexpectedly. This diagnostic report is stored only on this device. Share it with the developer so the underlying fault can be identified."
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(max = 260.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(crashReport, style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val send = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "MeritPrep crash report")
                    putExtra(Intent.EXTRA_TEXT, crashReport)
                }
                try {
                    context.startActivity(Intent.createChooser(send, "Share crash report"))
                } catch (_: Exception) {
                    Toast.makeText(context, "Unable to open sharing options.", Toast.LENGTH_LONG).show()
                }
            }) {
                Text("Share report")
            }
        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    CrashReporter.clear(context)
                    report = null
                }) { Text("Delete report") }
                TextButton(onClick = { report = null }) { Text("Later") }
            }
        }
    )
}
