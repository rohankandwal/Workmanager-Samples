package com.itcse.workmanagersample.main.dagger_injection.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * Assisted module helps in providing Dagger Injection
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
@Module(includes = [AssistedInject_SampleAssistedInjectModule::class])
@AssistedModule
interface SampleAssistedInjectModule