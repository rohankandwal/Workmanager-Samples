package com.itcse.workmanagersample.main.dagger_injection

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.itcse.workmanagersample.main.dagger_injection.dependency.SampleDependency
import com.itcse.workmanagersample.main.dagger_injection.factory.ChildWorkerFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

/**
 * Class used to show sample of a [androidx.work.Worker] with dagger injection allowed.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */

//public class Foo @Inject constructor()

class HelloWorldWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val dependency: SampleDependency
) : Worker(appContext, params) {

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory

    override fun doWork(): Result {
        Timber.d("Hello world!")
        Timber.d("Got the Key: ${dependency.someExampleStringDependency}")
        return Result.success()
    }

}

