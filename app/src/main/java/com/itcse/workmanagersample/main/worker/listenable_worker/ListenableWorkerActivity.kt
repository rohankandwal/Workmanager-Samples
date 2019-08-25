package com.itcse.workmanagersample.main.worker.listenable_worker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.itcse.workmanagersample.R
import kotlinx.android.synthetic.main.activity_listenable_worker.*
import timber.log.Timber
import java.lang.Exception

/**
 * Listenable worker is used to work on tasks for Listenable Future
 *
 * @author Rohan Kandwal on 2019-08-25.
 */
class ListenableWorkerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listenable_worker)
        try {
            WorkManager.initialize(this, Configuration.Builder().build())
        } catch (e: Exception) {
        }
        val request = OneTimeWorkRequestBuilder<ListenableWorkerExample>().build()

        bt_start_work.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            Timber.d("Finished calls")
            Toast.makeText(this, "Executed RxWorkerExample", Toast.LENGTH_SHORT).show()
        })
    }
}