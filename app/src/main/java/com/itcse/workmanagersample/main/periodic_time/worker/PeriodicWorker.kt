package com.itcse.workmanagersample.main.periodic_time.worker

import android.content.Context
import androidx.work.*
import com.itcse.workmanagersample.main.periodic_time.PeriodicTimeActivity
import com.itcse.workmanagersample.main.periodic_time.utils.Constants

/**
 * @author Rohan Kandwal on 2019-08-10.
 */
class PeriodicWorker(val context: Context, val workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    private val TAG_UNIQUE_WORK_NAME = "APP_USAGE_ANALYTIC_MANAGER"

    override fun doWork(): Result {
        val workManager = WorkManager.getInstance(context)

        // Request object to get the config from the server
        val continuation = workManager
            .beginUniqueWork(
                PeriodicTimeActivity.TAG_UNIQUE_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(GetConfigWorker::class.java))

        // Request object to get the Battery status of the device
        val batteryStatBuilder = OneTimeWorkRequest.Builder(BatteryUsageWorker::class.java)

        // Request object to get the Network usage of the device
        val netStatBuilder = OneTimeWorkRequest.Builder(NetworkUsageWorker::class.java)

        // Create constraint for to specify battery level and network type
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        //Request object to report it to the server
        val reportBuilder = OneTimeWorkRequest.Builder(ReportToServerWorker::class.java)
            .addTag(PeriodicTimeActivity.TAG_OUTPUT)
            .setConstraints(constraints)

        continuation
            // Chaining the GetConfigWorker with BatteryUsageWorker and NetworkUsageWorker
            .then(listOf(batteryStatBuilder.build(), netStatBuilder.build())) // Now, gathering analytics will happen in parallel
            .then(reportBuilder.build())  // Chaining the analytics request to server reporting
            .enqueue() // Finally, Don't forget to schedule your work :)

        // If the Worker was stopped we send failure
        if (isStopped) {
            return Result.failure()
        }
        return Result.success()
    }
}