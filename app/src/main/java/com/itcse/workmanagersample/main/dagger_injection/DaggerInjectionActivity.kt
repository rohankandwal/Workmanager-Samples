package com.itcse.workmanagersample.main.dagger_injection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.itcse.workmanagersample.R
import com.itcse.workmanagersample.main.dagger_injection.di.DaggerSampleComponent
import com.itcse.workmanagersample.main.dagger_injection.factory.SampleWorkerFactory
import kotlinx.android.synthetic.main.activity_dagger_injection.*
import java.lang.Exception

/**
 * Activity for showing example of Dagger injection in [androidx.work.Worker]
 *
 * credits - https://proandroiddev.com/dagger-2-setup-with-workmanager-a-complete-step-by-step-guild-bb9f474bde37
 * @author Rohan Kandwal on 2019-08-04.
 */
class DaggerInjectionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_injection)

        try {
            val factory: SampleWorkerFactory = DaggerSampleComponent.create().factory()
            WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(factory).build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bt_start_work.setOnClickListener {
            WorkManager.getInstance(this).enqueue(
                OneTimeWorkRequestBuilder<HelloWorldWorker>().build()
            )
        }
    }
}