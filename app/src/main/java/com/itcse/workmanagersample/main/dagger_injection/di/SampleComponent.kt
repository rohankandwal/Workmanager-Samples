package com.itcse.workmanagersample.main.dagger_injection.di

import com.itcse.workmanagersample.main.dagger_injection.factory.SampleWorkerFactory
import dagger.Component

/**
 * Component injecting the [SampleWorkerFactory] which allows injection.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
@Component(
    modules = [
        SampleAssistedInjectModule::class,
        WorkerBindingModule::class,
        DependencyModule::class
    ]
)
interface SampleComponent {
    fun factory(): SampleWorkerFactory
}
