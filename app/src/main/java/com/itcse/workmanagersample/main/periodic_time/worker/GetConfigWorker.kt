package com.itcse.workmanagersample.main.periodic_time.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.itcse.workmanagersample.main.SampleApplication
import com.itcse.workmanagersample.main.periodic_time.data.ConfigResponse
import com.itcse.workmanagersample.main.periodic_time.utils.Constants
import java.io.IOException

/**
 * Worker to load the app configuration from server.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class GetConfigWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private var configResponse : ConfigResponse? = null

    override fun doWork(): Result {
        try {
            configResponse = SampleApplication.thisApplication.apiHelper.config
            return Result.success(createOutputData(configResponse))

        } catch (e: IOException) {
            return Result.failure()
        }

    }

    private fun createOutputData(configResponse: ConfigResponse?): Data {
        return Data.Builder()
            .putBoolean(Constants.BATTERY_STAT, configResponse?.isBatteryStatRequired ?: false)
            .putBoolean(Constants.NET_STAT, configResponse?.isNetUsageStatRequired ?: false)
            .build()
    }
}