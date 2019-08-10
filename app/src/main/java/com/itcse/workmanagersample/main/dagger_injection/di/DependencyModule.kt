package com.itcse.workmanagersample.main.dagger_injection.di

import com.itcse.workmanagersample.main.dagger_injection.dependency.SampleDependency
import dagger.Module
import dagger.Provides

/**
 * Module used for loading dependencies used in the module
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
@Module
class DependencyModule {

    /**
     * Providing dependency for some sample class
     */
    @Provides
    fun provideSampleDependency(): SampleDependency {
        return SampleDependency()
    }

}