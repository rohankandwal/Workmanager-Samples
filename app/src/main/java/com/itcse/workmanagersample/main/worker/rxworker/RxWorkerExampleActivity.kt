package com.itcse.workmanagersample.main.worker.rxworker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.itcse.workmanagersample.R
import kotlinx.android.synthetic.main.activity_rx_worker.*

/**
 * Activity used to show example of a Rx worker
 *
 * @author Rohan Kandwal on 2019-08-08.
 */
class RxWorkerExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_worker)

        WorkManager.initialize(this, Configuration.Builder().build())

        val request = OneTimeWorkRequestBuilder<RxWorkerExample>().build()

        bt_start_work.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            Toast.makeText(this, "Executed RxWorkerExample", Toast.LENGTH_SHORT).show()
        })
    }
}