package com.itcse.workmanagersample.main.periodic_time.worker

import android.content.Context
import androidx.annotation.NonNull
import androidx.work.WorkerParameters
import androidx.work.ListenableWorker
import com.google.common.util.concurrent.ListenableFuture


/**
 * @author Rohan Kandwal on 2019-08-11.
 */
class CallbackWorker(context: Context, params: WorkerParameters) : ListenableWorker(context, params) {


    @NonNull
    override fun startWork(): ListenableFuture<Result> {
        ResolvableFuture.create()
        return ListenableFuture(Result.success())
    }
}