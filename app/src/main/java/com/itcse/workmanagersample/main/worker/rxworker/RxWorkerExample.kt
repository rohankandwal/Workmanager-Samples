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


    /**
     * Override this method to define your actual work and return a {@code Single} of
     * {@link androidx.work.ListenableWorker.Result} which will be subscribed by the
     * {@link WorkManager}.
     * <p>
     * If the returned {@code Single} fails, the worker will be considered as failed.
     * <p>
     * If the {@link RxWorker} is cancelled by the {@link WorkManager} (e.g. due to a constraint
     * change), {@link WorkManager} will dispose the subscription immediately.
     * <p>
     * By default, subscription happens on the shared {@link Worker} pool. You can change it
     * by overriding {@link #getBackgroundScheduler()}.
     * <p>
     * An RxWorker is given a maximum of ten minutes to finish its execution and return a
     * {@link androidx.work.ListenableWorker.Result}.  After this time has expired, the worker will
     * be signalled to stop.
     *
     * @return a {@code Single<Result>} that represents the work.
     */
    override fun createWork(): Single<Result> {
        return Observable.range(0, 100)
            .toList()
            .map { Result.success() }
    }

    override fun getBackgroundScheduler(): Scheduler {
        return Schedulers.computation()
    }
}