package com.itcse.workmanagersample.main.worker.rxworker

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * By default, RxWorkerExample will subscribe on the thread pool that runs WorkManager Workers.
 * You can change this behavior by overriding getBackgroundScheduler() method.
 *
 * An RxWorkerExample is given a maximum of ten minutes to finish its execution and return a ListenableWorker.Result.
 * After this time has expired, the worker will be signalled to stop.
 *
 * @author Rohan Kandwal on 2019-08-08.
 */
class RxWorkerExample(val context: Context, val workerParameters: WorkerParameters) :
    RxWorker(context, workerParameters) {

    override fun createWork(): Single<Result> {
        return Observable.range(0, 100)
            .toList()
            .map { Result.success() }
    }

    override fun getBackgroundScheduler(): Scheduler {
        return Schedulers.computation()
    }
}