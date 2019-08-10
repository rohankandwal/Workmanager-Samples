package com.itcse.workmanagersample.main.periodic_time.netwok

import com.itcse.workmanagersample.main.periodic_time.RemoteApi
import com.itcse.workmanagersample.main.periodic_time.data.Analytics
import com.itcse.workmanagersample.main.periodic_time.data.ConfigResponse

/**
 * Helper used for getting sample configurations for the app and reporting Analytics data to server.
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
class ApiHelper(var remoteApi: RemoteApi) {

    val config: ConfigResponse? get() {
        return remoteApi.config.execute().body()
    }

    fun reportConfig(analytics: Analytics) {
        remoteApi.reportConfig(analytics).execute()
    }
}