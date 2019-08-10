package com.itcse.workmanagersample.main.dagger_injection.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

/**
 * Interface containing Worker factory contract function
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
public interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}