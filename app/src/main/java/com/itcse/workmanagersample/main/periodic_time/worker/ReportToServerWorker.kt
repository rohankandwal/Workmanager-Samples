package com.itcse.workmanagersample.main.periodic_time.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.itcse.workmanagersample.main.SampleApplication
import com.itcse.workmanagersample.main.periodic_time.data.Analytics
import com.itcse.workmanagersample.main.periodic_time.utils.Constants
import timber.log.Timber
import java.io.IOException

/**
 * Worker class used for reporting the analytics information to the server.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class ReportToServerWorker(context: Context,  params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val batteryStat = inputData.getString(Constants.BATTERY_PERCENTAGE) ?: "UNKNOWN"
            val netStat = inputData.getLong(Constants.NETWORK_USAGE, 0)

            SampleApplication.thisApplication.apiHelper.remoteApi.reportConfig(Analytics(batteryStat, netStat))
            Timber.d("Reporting data to server battery = $batteryStat ")
            Result.success()

        } catch (e: IOException) {
            Result.failure()
        }
    }

}