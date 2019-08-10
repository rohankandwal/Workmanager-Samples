package com.itcse.workmanagersample.main.worker.coroutineworker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.*

/**
 * Unlike Worker, this code does not run on the Executor specified in your Configuration.
 * Instead, it defaults to Dispatchers.Default. You can customize this by providing your own CoroutineContext.
 *
 * @author Rohan Kandwal on 2019-08-08.
 */
class CoroutineWorkerExample(val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {


    override suspend fun doWork(): Result = coroutineScope {
        //
        withContext(Dispatchers.IO) {
            return@withContext try {
                val jobs = (0 until 100).map {
                    async {
                        downloadSynchronously("https://www.google.com")
                    }
                }

                // awaitAll will throw an exception if a download fails, which CoroutineWorker will treat as a failure
                jobs.awaitAll()
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }

    fun downloadSynchronously(string: String) {
        Thread.sleep(1000)
    }

}