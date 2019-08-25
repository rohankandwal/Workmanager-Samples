package com.itcse.workmanagersample.main.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itcse.workmanagersample.R
import com.itcse.workmanagersample.main.dagger_injection.DaggerInjectionActivity
import com.itcse.workmanagersample.main.one_time.OneTimeActivity
import com.itcse.workmanagersample.main.periodic_time.PeriodicTimeActivity
import com.itcse.workmanagersample.main.worker.coroutineworker.CoroutineWorkerExampleActivity
import com.itcse.workmanagersample.main.worker.listenable_worker.ListenableWorkerActivity
import com.itcse.workmanagersample.main.worker.listenable_worker.ListenableWorkerExample
import com.itcse.workmanagersample.main.worker.rxworker.RxWorkerExampleActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity containing redirection to various examples.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_one_time.setOnClickListener {
            val intent = Intent(this, OneTimeActivity::class.java)
            startActivity(intent)
        }
        bt_periodic_request.setOnClickListener {
            startActivity(Intent(this, PeriodicTimeActivity::class.java))
        }

        bt_start_dagger_injection.setOnClickListener {
            startActivity(Intent(this, DaggerInjectionActivity::class.java))
        }
        bt_listenable_worker.setOnClickListener {
            startActivity(Intent(this, ListenableWorkerActivity::class.java))
        }
        bt_rx_worker_example.setOnClickListener {
            startActivity(Intent(this, RxWorkerExampleActivity::class.java))
        }

        bt_coroutine_worker_example.setOnClickListener {
            startActivity(Intent(this, CoroutineWorkerExampleActivity::class.java))
        }
    }
}
