package com.itcse.workmanagersample.main.dagger_injection.di

import androidx.work.ListenableWorker
import com.itcse.workmanagersample.main.dagger_injection.HelloWorldWorker
import com.itcse.workmanagersample.main.dagger_injection.di.WorkerBindingModule.WorkerKey
import com.itcse.workmanagersample.main.dagger_injection.factory.ChildWorkerFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Module for binding the [androidx.work.Worker] class with [WorkerKey].
 *
 * @author Rohan Kandwal on 2019-08-04.
 */

@Module
interface WorkerBindingModule {

    @MapKey
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class WorkerKey(val value: KClass<out ListenableWorker>)

    @Binds
    @IntoMap
    @WorkerKey(HelloWorldWorker::class)
    fun bindHelloWorldWorker(factory: HelloWorldWorker.Factory): ChildWorkerFactory
}
