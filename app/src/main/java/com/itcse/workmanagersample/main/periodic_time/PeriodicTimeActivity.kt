package com.itcse.workmanagersample.main.periodic_time

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.work.*
import com.itcse.workmanagersample.R
import com.itcse.workmanagersample.main.periodic_time.worker.*
import kotlinx.android.synthetic.main.activity_periodic_time.*
import java.util.concurrent.TimeUnit

/**
 * Activity used for loading periodic tasks.
 *
 * credits - https://github.com/SomoGlobal/somo-workmanager-android
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class PeriodicTimeActivity : AppCompatActivity() {
    public companion object {
        val TAG_OUTPUT = "OUTPUT"
        val TAG_UNIQUE_WORK_NAME = "APP_USAGE_ANALYTIC_MANAGER"
        private val REQUEST_READ_PHONE_STATE = 5352
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodic_time)

        // Since we have different types of worker, both default and custom one, we
        try {
            WorkManager.initialize(this, Configuration.Builder().build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // This makes sure that whenever the current workId changes the WorkStatus
        // the UI is listening to changes
        bt_star_periodic_work.setOnClickListener {
            //            startWork()
//            requestPermissions()
            if (arePermissionsGranted()) {
                startWork()
            } else {
                Toast.makeText(this, "Please grant permissions", Toast.LENGTH_SHORT).show()
                requestPermissions()
            }
        }
        if (!arePermissionsGranted()) {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(), packageName
        )
        if (mode == AppOpsManager.MODE_ALLOWED) {

            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    REQUEST_READ_PHONE_STATE
                )
            }

        } else {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
    }

    private fun arePermissionsGranted(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(), packageName
        )
        if (mode == AppOpsManager.MODE_ALLOWED) {
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            return permissionCheck == PackageManager.PERMISSION_GRANTED

        } else {
            return false
        }
    }


    /**
     * We are using WorkManager here to get some config from the server,
     * gather device analytics based on the config and report it to the server.
     */
    private fun startWork() {
        val periodicWorkRequest = PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(periodicWorkRequest)
    }
}