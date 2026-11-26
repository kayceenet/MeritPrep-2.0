package com.kaycee.meritprep

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MeritPrepApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashDiagnostics.install(this)
    }
}

/** Offline crash recorder. Reports stay private until the user chooses to share them. */
object CrashDiagnostics {
    private const val DIRECTORY = "crash-diagnostics"
    private const val REPORT_NAME = "crash-report.txt"
    private const val BREADCRUMB_NAME = "recent-activity.txt"

    @Volatile private var applicationContext: Context? = null
    private val lock = Any()

    fun install(context: Context) {
        applicationContext = context.applicationContext
        val previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                writeCrashReport(context.applicationContext, thread, throwable)
            } catch (_: Throwable) {
                // Diagnostics must never replace the original crash.
            }
            if (previousHandler != null) {
                previousHandler.uncaughtException(thread, throwable)
            } else {
                Process.killProcess(Process.myPid())
                kotlin.system.exitProcess(10)
            }
        }
    }

    fun recordBreadcrumb(message: String) {
        val context = applicationContext ?: return
        try {
            synchronized(lock) {
                val directory = File(context.filesDir, DIRECTORY)
                if (!directory.exists()) directory.mkdirs()
                val file = File(directory, BREADCRUMB_NAME)
                val existing = if (file.exists()) file.readLines().takeLast(39) else emptyList()
                val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(Date())
                file.writeText((existing + "$timestamp  $message").joinToString("\n") + "\n")
            }
        } catch (_: Throwable) {
            // Best-effort only.
        }
    }

    private fun writeCrashReport(context: Context, thread: Thread, throwable: Throwable) {
        synchronized(lock) {
            val directory = File(context.filesDir, DIRECTORY)
            if (!directory.exists() && !directory.mkdirs()) return
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z", Locale.US).format(Date())
            val stack = StringWriter().also { writer ->
                throwable.printStackTrace(PrintWriter(writer))
            }.toString()
            val version = try {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "unknown"
            } catch (_: Throwable) { "unknown" }
            val breadcrumbs = File(directory, BREADCRUMB_NAME)
                .takeIf { it.exists() }?.readText() ?: "No recent navigation breadcrumbs were recorded."

            val report = buildString {
                appendLine("MERITPREP CRASH REPORT")
                appendLine("Time: $timestamp")
                appendLine("App: $version (${context.packageName})")
                appendLine("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
                appendLine("Android: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
                appendLine("Build fingerprint: ${Build.FINGERPRINT}")
                appendLine("Process ID: ${Process.myPid()}")
                appendLine("Thread: ${thread.name} (id=${thread.id})")
                appendLine()
                appendLine("EXCEPTION")
                appendLine(stack)
                appendLine()
                appendLine("RECENT APP ACTIVITY")
                appendLine(breadcrumbs)
            }
            FileOutputStream(File(directory, REPORT_NAME), false).use { output ->
                output.write(report.toByteArray(Charsets.UTF_8))
                output.fd.sync()
            }
        }
    }

    fun readReport(context: Context): String? = try {
        reportFile(context).takeIf { it.isFile }?.readText(Charsets.UTF_8)?.takeIf { it.isNotBlank() }
    } catch (_: Exception) { null }

    fun reportFile(context: Context): File =
        File(File(context.filesDir, DIRECTORY), REPORT_NAME)

    fun clearReport(context: Context) {
        try { reportFile(context).delete() } catch (_: Exception) { }
    }

    fun reportUri(context: Context) = FileProvider.getUriForFile(
        context, "${context.packageName}.fileprovider", reportFile(context)
    )
}
