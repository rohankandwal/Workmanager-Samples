package com.itcse.workmanagersample.main.periodic_time.worker

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.itcse.workmanagersample.main.periodic_time.utils.Constants

/**
 * Worker for detecting the current battery percentage.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class BatteryUsageWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        // If the Worker was stopped we send failure
        if (isStopped) {
            return Result.failure()
        }
        val isNeeded = inputData.getBoolean(Constants.BATTERY_STAT, false)

        if (isNeeded) {
            val batteryIntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = applicationContext.registerReceiver(null, batteryIntentFilter)
            val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPercent = level?.div(scale?.toFloat() ?: 0f)
            return Result.success(createOutputData(batteryPercent))
        }
        return Result.success()
    }

    private fun createOutputData(batteryPercent: Float?): Data {
        return Data.Builder().putString(Constants.BATTERY_PERCENTAGE, batteryPercent.toString()).build()
    }
}