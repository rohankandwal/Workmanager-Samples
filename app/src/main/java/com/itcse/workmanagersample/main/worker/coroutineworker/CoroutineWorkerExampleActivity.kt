package com.itcse.workmanagersample.main.worker.coroutineworker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.itcse.workmanagersample.R
import kotlinx.android.synthetic.main.activity_coroutine_worker.*

/**
 * Activity used for showing Coroutine implementation for WorkManager.
 *
 * @author Rohan Kandwal on 2019-08-08.
 */
class CoroutineWorkerExampleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_worker)

        // Since we have different types of worker, both default and custom one, we
        try {
            WorkManager.initialize(this, Configuration.Builder().build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val worker = OneTimeWorkRequestBuilder<CoroutineWorkerExample>().build()
        bt_start_work.setOnClickListener {
            WorkManager.getInstance(this).enqueue(worker)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(worker.id).observe(this, Observer {
            Toast.makeText(this, "Coroutine worker executed", Toast.LENGTH_SHORT).show()
        })
    }
}